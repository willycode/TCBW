package com.kangdainfo.tcbw.view.drgin;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg0004Db;
import com.kangdainfo.tcbw.model.bo.Drg0005Db;
import com.kangdainfo.tcbw.model.bo.Drg1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGIN0119F extends DRGIN0102F{
	
	private String q_notifierSdateHis;	//通報日報start
	private String q_notifierEdateHis;	//通報日報end
	private String q_manufactorNoHis;	//批號
	private String q_notifierSourceHis;	//通報單位
	private String q_notifierDeptHis;	//通報者服務機構
	private String[] q_mainCodeHis;		//不良品缺陷
	private String[] q_subCodeHis;			

	public String getQ_notifierSdateHis() {
		return checkGet(q_notifierSdateHis);
	}

	public void setQ_notifierSdateHis(String qNotifierSdateHis) {
		q_notifierSdateHis = checkSet(qNotifierSdateHis);
	}

	public String getQ_notifierEdateHis() {
		return checkGet(q_notifierEdateHis);
	}

	public void setQ_notifierEdateHis(String qNotifierEdateHis) {
		q_notifierEdateHis = checkSet(qNotifierEdateHis);
	}

	public String getQ_manufactorNoHis() {
		return checkGet(q_manufactorNoHis);
	}

	public void setQ_manufactorNoHis(String qManufactorNoHis) {
		q_manufactorNoHis = checkSet(qManufactorNoHis);
	}

	public String getQ_notifierSourceHis() {
		return checkGet(q_notifierSourceHis);
	}

	public void setQ_notifierSourceHis(String qNotifierSourceHis) {
		q_notifierSourceHis = checkSet(qNotifierSourceHis);
	}

	public String getQ_notifierDeptHis() {
		return checkGet(q_notifierDeptHis);
	}

	public void setQ_notifierDeptHis(String qNotifierDeptHis) {
		q_notifierDeptHis = checkSet(qNotifierDeptHis);
	}

	public String[] getQ_mainCodeHis() {
		return q_mainCodeHis;
	}

	public void setQ_mainCodeHis(String[] qMainCodeHis) {
		q_mainCodeHis = qMainCodeHis;
	}

	public String[] getQ_subCodeHis() {
		return q_subCodeHis;
	}

	public void setQ_subCodeHis(String[] qSubCodeHis) {
		q_subCodeHis = qSubCodeHis;
	}

	public Object doQueryAllDrg0119() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();	
		String hql = getHql();  
	    System.out.println("[TCBW]-[DRGIN0119F]-[不良品-doQueryAllDrg0119] : " + hql);
	    java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
	    
	    this.processCurrentPageAttribute(objList!=null?objList.size():0);
	    if(getTotalRecord() > 0){
	    	if(getState().indexOf("query")<0) 
	    		ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
	    	
	    	objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id desc ", this.getRecordStart(), this.getPageSize());
	    	if(objList!=null && objList.size()>0){
	    		java.util.Map<String, String> notifierTypeMap = TCBWCommon.getCommonCodeMap("CONNFT1");
	    		java.util.Map<String, String> drgLevMap = TCBWCommon.getCommonCodeMap("DRGRKL");
	    		java.util.Map<String, String> dpdMap = TCBWCommon.getCommonCodeMap("DRG0105"); //不良品主代碼				
				java.util.Map<String, String> subMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Drg1001Db"); //缺陷子代碼
				
				for(Object dtlObj : objList)
				{
					Drg0001Db dtl = (Drg0001Db)dtlObj;
					String[] rowArray = new String[11];
					//抓取2檔不良品缺陷
					String dpdList ="";
					if (dtl.getDrg0002Dbs()!=null && dtl.getDrg0002Dbs().size()>0){
						for (Object drg02Obj : dtl.getDrg0002Dbs()) {
							Drg0002Db o = (Drg0002Db) drg02Obj;
							if(dpdList.length()>0) dpdList += ", ";
							dpdList += dpdMap.get(o.getMainCode())+":";
							
							if(!"".equals(Common.get(o.getSubCode()))){
								String subCodeStr ="";
								String[] subCode = Common.get(o.getSubCode()).split(",");
								for(int j=0; j<subCode.length; j++){
									if(subCodeStr.length()>0) subCodeStr += ",";
									subCodeStr += subMap.get(subCode[j]);
									if("ZZ".equals(subCode[j].substring(2))){
										subCodeStr += (":"+o.getOtherDescribe());										
									}
								}
								dpdList += subCodeStr;
							}else{							
								dpdList += o.getOtherDescribe();
							}
						}
					}					
					if(dpdList != "") dpdList = Common.get(dpdList).substring(0,Common.get(dpdList).length());
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.formatYYYMMDD(Common.get(dtl.getNotifierRepDate()),4);
					rowArray[2] = Common.get(dtl.getApplNo());
					rowArray[3] = Common.get(dtl.getEnProduct());
					rowArray[4] = "";
					if(dpdList.length()>0)
						rowArray[4] = Common.get(dpdList);
					rowArray[5] = Common.get(dtl.getManufactorNo());
					rowArray[6] = Common.get(dtl.getManufactorName());
					String tempDrgLev = (String)View.getObject("select drgLev from Drg0004Db where applNo = " + Common.sqlChar(getApplNo()));
					rowArray[7] = Common.get(drgLevMap.get(tempDrgLev));					
					rowArray[8] = Common.get(notifierTypeMap.get(dtl.getNotifierType()));
					rowArray[9] = "評估表";
					rowArray[10] = "";
					java.util.List<Con0001Db> conList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind='DRG' and systemType like 'DRG01%' and dbName='Drg0001Db' and upLoadId="+dtl.getId());
					if(null != conList && !conList.isEmpty()){
						rowArray[10] = "附件";
					}
					arrList.add(rowArray);
				}
				this.setState("queryAllSuccess");
	    	}
	    }
		return arrList;
	}
	
	public Object doQueryAllDrg0119Capa() throws Exception {
		java.util.ArrayList<String[]> arrList2 = new java.util.ArrayList<String[]>();		
		Drg0001Db drg01 = (Drg0001Db)View.getObject(" from Drg0001Db where id ="+Common.getLong(getId()));
		String tempAsDate = (String)View.getObject("select assessDate from Drg0007Db where applNo = " + Common.sqlChar(drg01.getApplNo()) + " order by assessDate desc");
		if(null != tempAsDate && !"".equals(tempAsDate)){
			String sql = " from Drg0002Db where drg0001Db.id = " + Common.getLong(getId());				
		    java.util.List objList = ServiceGetter.getInstance().getCommonService().load(sql + " order by id desc");				
		    String subCodeList =""; 				
		    if(objList!=null && objList.size()>0)				
		    {
		    	java.util.Iterator it = objList.iterator();
		    	while (it.hasNext()){					
					Drg0002Db o = (Drg0002Db) it.next();						
					String[] subList = o.getSubCode().split(",");
					for(int j=0; j<subList.length; j++){
						if(Common.get(subList[j]) != ""){							
							subCodeList += "subCode like ('%"+subList[j]+"%') or ";
						}						
					}				
		    	}	
		    	if(subCodeList != null) subCodeList = subCodeList.substring(0, subCodeList.length()-3);
		    }
		     
		    //歷年本藥品同此次瑕疵之通報件數
		    if(subCodeList!=null&&!"".equals(subCodeList)){	    	
			    String hql = " from Drg0001Db where (enrolledDate is not null or enrolledDate <> '') "+	
			                 " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg01.getApplNo())+") "+
			    	         " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";    
			    if(drg01!=null)
			    	   hql +=" and permitKey = " + Common.sqlChar(drg01.getPermitKey()) + " and permitNo = " + Common.sqlChar(drg01.getPermitNo());
			    
			    System.out.println("[TCBW]-[DRGIN0119F]-[不良品-doQueryAllDrg0119Capa] : " + hql);
			    java.util.List objList2 = ServiceGetter.getInstance().getTcbwService().load(hql);	    	
			    if(objList2!=null && objList2.size()>0){
		    		java.util.Map<String, String> drgLevMap = TCBWCommon.getCommonCodeMap("DRGRKL");
		    		java.util.Map<String, String> dpdMap = TCBWCommon.getCommonCodeMap("DRG0105"); //不良品主代碼				
					java.util.Map<String, String> subMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Drg1001Db"); //缺陷子代碼
					for(Object dtlObj : objList2)
					{	
						Drg0001Db dtl = (Drg0001Db)dtlObj;
						
						String[] rowArray = new String[10];
						//抓取2檔不良品缺陷
						String dpdList ="";
						if (dtl.getDrg0002Dbs()!=null && dtl.getDrg0002Dbs().size()>0){
							for (Object drg02Obj : dtl.getDrg0002Dbs()) {
								Drg0002Db o = (Drg0002Db) drg02Obj;
								if(dpdList.length()>0) dpdList += ", ";
								dpdList += dpdMap.get(o.getMainCode())+":";
								
								if(!"".equals(Common.get(o.getSubCode()))){
									String subCodeStr ="";
									String[] subCode = Common.get(o.getSubCode()).split(",");
									for(int j=0; j<subCode.length; j++){
										if(subCodeStr.length()>0) subCodeStr += ",";
										subCodeStr += subMap.get(subCode[j]);
										if("ZZ".equals(subCode[j].substring(2))){
											subCodeStr += (":"+o.getOtherDescribe());										
										}
									}
									dpdList += subCodeStr;
								}else{							
									dpdList += o.getOtherDescribe();
								}
							}
						}					
						if(dpdList != "") dpdList = Common.get(dpdList).substring(0,Common.get(dpdList).length());
						
						Drg0004Db drg0004Db = (Drg0004Db)View.getObject(" from Drg0004Db where applNo = " + Common.sqlChar(getApplNo()));
						if(drg0004Db != null){
							rowArray[1] = Common.formatYYYMMDD(Common.get(drg0004Db.getPostDate()),4);
							rowArray[2] = Common.get(drg0004Db.getPostNo());
							rowArray[7] = Common.get(drgLevMap.get(drg0004Db.getDrgLev()));
						}else{
							rowArray[1] = "";
							rowArray[2] = "";
							rowArray[7] = "";
						}
						rowArray[0] = Common.get(dtl.getId());
						
						rowArray[3] = Common.formatYYYMMDD(Common.get(dtl.getNotifierRepDate()),4);
						rowArray[4] = Common.get(dtl.getApplNo());
						rowArray[5] = Common.get(dpdList);
						rowArray[6] = Common.get(dtl.getManufactorNo());
						rowArray[8] = Common.formatYYYMMDD(Common.get(tempAsDate),4);
						rowArray[9] = Common.get("明細");
						arrList2.add(rowArray);
					}	    	
			    }		    
		    }
		}else{
			System.out.println("[TCBW]-[DRGIN0119F]-[不良品-doQueryAllDrg0119Capa] : 無評估日期Drg0007Db:assessDate");
		}   	    
		return arrList2;
	}
	
	public DefaultTableModel getDefaultTableModel() throws Exception
	{		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[]{"NO","NotifierRepDate","ApplNo","ChProduct","EnProduct","Drg0002",
    			"ManufactorNo","ManufactorName","DrgLev","NotifierType"};  
    	//java.util.ArrayList<String[]> arrList =  (ArrayList<String[]>)doQueryAllDrg0119();
    	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
    	String hql = getHql();
	    java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id");
    	if(objList!=null && objList.size()>0){
    		int i = 1;
    		java.util.Map<String, String> notifierTypeMap = TCBWCommon.getCommonCodeMap("CONNFT1");
    		java.util.Map<String, String> drgLevMap = TCBWCommon.getCommonCodeMap("DRGRKL");
    		java.util.Map<String, String> dpdMap = TCBWCommon.getCommonCodeMap("DRG0105"); //不良品主代碼				
			java.util.Map<String, String> subMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Drg1001Db"); //缺陷子代碼
			
			for(Object dtlObj : objList)
			{
				Drg0001Db dtl = (Drg0001Db)dtlObj;
				String[] rowArray = new String[10];
				//抓取2檔不良品缺陷
				String dpdList ="";
				if (dtl.getDrg0002Dbs()!=null && dtl.getDrg0002Dbs().size()>0){
					for (Object drg02Obj : dtl.getDrg0002Dbs()) {
						Drg0002Db o = (Drg0002Db) drg02Obj;
						if(dpdList.length()>0) dpdList += ", ";
						dpdList += dpdMap.get(o.getMainCode())+":";
						
						if(!"".equals(Common.get(o.getSubCode()))){
							String subCodeStr ="";
							String[] subCode = Common.get(o.getSubCode()).split(",");
							for(int j=0; j<subCode.length; j++){
								if(subCodeStr.length()>0) subCodeStr += ",";
								subCodeStr += subMap.get(subCode[j]);
								if("ZZ".equals(subCode[j].substring(2))){
									subCodeStr += (":"+o.getOtherDescribe());										
								}
							}
							dpdList += subCodeStr;
						}else{							
							dpdList += o.getOtherDescribe();
						}
					}
				}					
				if(dpdList != "") dpdList = Common.get(dpdList).substring(0,Common.get(dpdList).length());
				rowArray[0] = String.valueOf(i++);
				rowArray[1] = Common.formatYYYMMDD(Common.get(dtl.getNotifierRepDate()),4);
				rowArray[2] = Common.get(dtl.getApplNo());
				rowArray[3] = Common.get(dtl.getChProduct());
				rowArray[4] = Common.get(dtl.getEnProduct());
				rowArray[5] = "";
				if(dpdList.length()>0)
					rowArray[5] = Common.get(dpdList);
				rowArray[6] = Common.get(dtl.getManufactorNo());
				rowArray[7] = Common.get(dtl.getManufactorName());
				String tempDrgLev = (String)View.getObject("select drgLev from Drg0004Db where applNo = " + Common.sqlChar(getApplNo()));
				rowArray[8] = Common.get(drgLevMap.get(tempDrgLev));					
				rowArray[9] = Common.get(notifierTypeMap.get(dtl.getNotifierType()));
				arrList.add(rowArray);
			}
    	}	    
		if (arrList!=null && arrList.size()>0) 
		{
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}	
		return model;    	
	}	
	
	public static String getCheckBoxOption(String className, String mainCode, String subCode, String sql, String[] selectedCheckBox1, String[] selectedCheckBox2) {
    	StringBuilder sb = new StringBuilder();  	
    	java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
    	String otherDescribeValue ="";
    	if (list!=null && list.size()>0) {    		
    		int j=0;
        	for(int i=0 ; i<list.size();i++){
        		Object[] o = (Object[]) list.get(i);
        		sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( mainCode ).append( "\" value=\"" ).append( o[0] ).append( "\"");
        		if (selectedCheckBox1!=null && selectedCheckBox1.length>0) {
        			for (j=0; j<selectedCheckBox1.length; j++) {
        				if(Common.get(o[0]).equals(selectedCheckBox1[j])) sb.append(" checked");
        			}
        		}
        		sb.append(" id='his"+o[0]+"' onclick='checkChildrenSubCodeHis(this);'");
        		sb.append(">").append(o[1]).append(" ").append("<br>");        		
        		//第2層 
        		java.util.List list2 = PersistenceServiceGetter.getInstance().getHibernateTemplate().find("select dpdKind,dpdKindName from Drg1001Db where substring(dpdKind,1,2)='"+o[0]+"' and isStop='N' order by dpdKind");
        		if (list2!=null && list2.size()>0) {
        			int l=0;
        			sb.append("&nbsp;&nbsp;&nbsp;");
        			for(int k=0 ; k<list2.size(); k++){
        				Object[] o2 = (Object[]) list2.get(k);
        				sb.append("&nbsp;&nbsp;");
        				sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( subCode ).append( "\" value=\"" ).append( o2[0] ).append( "\"");
                		if (selectedCheckBox2!=null && selectedCheckBox2.length>0) {
                			for (l=0; l<selectedCheckBox2.length; l++) {
                				if(Common.get(o2[0]).equals(selectedCheckBox2[l])) sb.append("checked");
                			}
                		}
                		sb.append(" id='his"+o2[0]+"' onclick='checkParentMainCodeHis(this);'");
                		sb.append(">").append(o2[1]).append(" ");
        			}
        			sb.append("<br>");
        		}
        	}    		
    	}
        return sb.toString();    	
    }
	   
	   
	public String getHql(){
		Drg0001Db drg01 = (Drg0001Db)View.getObject(" from Drg0001Db where id ="+Common.getLong(getId()));
		//歷年本藥品之通報件數
	    String hql = " from Drg0001Db where (enrolledDate is not null or enrolledDate <> '' )"+
	                 " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg01.getApplNo())+") ";
	    if(drg01!=null)
	    	hql += " and permitKey = " + Common.sqlChar(drg01.getPermitKey()) + " and permitNo = " + Common.sqlChar(drg01.getPermitNo());
	    if(!"".equals(getQ_notifierSdateHis()))
	    	hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierSdateHis());
	    if(!"".equals(getQ_notifierEdateHis()))
	    	hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierEdateHis());
	    if(!"".equals(getQ_manufactorNoHis()))
	    	hql += " and manufactorNo = " + Common.sqlChar(getQ_manufactorNoHis());
	    if(!"".equals(getQ_notifierDeptHis()))
	    	hql += " and notifierDept like " + Common.sqlChar("%" + getQ_notifierDeptHis() + "%");
		//不良品缺陷
	    String subCodeList ="";
		String mainCodeList ="";
		if(!"".equals(Common.get(getQ_mainCodeHis())) || !"".equals(Common.get(getQ_subCodeHis()))){
			if(!"".equals(Common.get(getQ_mainCodeHis()))){
				for(int j=0;j<getQ_mainCodeHis().length;j++){
					if(!"".equals(Common.get(mainCodeList))) mainCodeList += ",";
					mainCodeList += Common.sqlChar(getQ_mainCodeHis()[j]);
				}
			}
			if(!"".equals(Common.get(getQ_subCodeHis()))){
				for(int k=0;k<getQ_subCodeHis().length;k++){
					if(k!=0) subCodeList += " or ";	
					else subCodeList += " ( ";
					subCodeList += " b.subCode like " + Common.sqlChar("%"+getQ_subCodeHis()[k]+"%");			
				}
			}
			if(subCodeList == ""){				
				hql += " and id in ( select b.drg0001Db.id from Drg0002Db b where b.mainCode in ("+mainCodeList+"))";
			}else{
				hql += " and id in ( select b.drg0001Db.id from Drg0002Db b where b.mainCode in ("+mainCodeList+") and "+subCodeList+" or b.subCode='' ))";
			}
		}			
		return hql;
	}
}
