package com.kangdainfo.tcbw.view.drgex;


import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0006Db;
import com.kangdainfo.tcbw.model.bo.Drg0007Db;
import com.kangdainfo.tcbw.model.bo.Drg5002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.drgin.DRGIN0102F;


public class DRGEX0109F extends DRGEX0101F{
	
	String caseType ;
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {return httpRequest;}
	public void setHttpRequest(javax.servlet.ServletRequest r) {this.httpRequest = r;}
	
	private String applNo;	//案件號碼	VARCHAR(11)
	private String status;	//案件狀態	VARCHAR(2)
	private String drgLev;	//風險等級	VARCHAR(1)
	
	private String drg07id;
	private String checkResult07;	//清查結果	NVARCHAR(100)
	private String survey07;	//調查結果	NVARCHAR(100)
	private String precaution07;	//預防矯正措施及改善時程	NVARCHAR(100)
	
	private String permitKey;	//許可證字	VARCHAR(14)
	private String permitNo;	//許可證號	VARCHAR(14)
	private String chProduct; 	//商品名稱中文	NVARCHAR(100)
	private String enProduct; 	//商品名稱英文	VARCHAR(100)
	private String ingredient;	//有效成分名稱	NVARCHAR(50)
	private String medModel;	//劑型	VARCHAR(2)
	private String medModelOther;	//劑型(描述)	NVARCHAR(50)
	private String applicationName;	//藥商/申請商	NVARCHAR(50)
	private String manufactorName;	//製造商/製造廠	NVARCHAR(50)
	private String manufactorNo; //批號
	//不良品缺陷-----------------------------------------------------------------
	private String[] mainCode;
	private String[] subCode;
	private String[] otherDescribe;	
	private String hisData1; //歷年本藥品之通報件數
	private String hisData2; //歷年本藥品同此次瑕疵之通報件數
	private String hisData3; //一年內本藥品之通報件數
	private String hisData4; //一年內本藥品同此次瑕疵之通報件數
	private String hisData5; //一年內本藥品同此次瑕疵之高風險通報件數
	private String hisData6; //一年內本藥品同此次瑕疵案件
	private String hisData7; //一年內本藥品同此次瑕疵之高風險案件
	
	private String hisApplNoY; //一年內本藥品同此次瑕疵案件之同批號的各案件編號
	private String hisApplNoN; //一年內本藥品同此次瑕疵案件之不同批號的各案件編號
	
	private String analyDate;	//分析日期	VARCHAR(7)
	public String getAnalyDate() {return checkGet(analyDate);}
	public void setAnalyDate(String analyDate) {this.analyDate = checkSet(analyDate);}
		
	public void doUpdateType() throws Exception{
		
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		Drg0007Db obj = (Drg0007Db)View.getObject(" from Drg0007Db where applNo = " + Common.sqlChar(getApplNo())+" order by id desc");
		if(obj != null)
		{
			obj.setCheckResult(Common.get(getCheckResult07()));
			obj.setSurvey(Common.get(getSurvey07()));
			obj.setPrecaution(Common.get(getPrecaution07()));
			obj.setModifier(getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			ServiceGetter.getInstance().getCommonService().update(obj);
		}
	}
	
	public void doReplyOver() throws Exception{
		
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0007Db obj = (Drg0007Db)View.getObject(" from Drg0007Db where applNo = " + Common.sqlChar(getApplNo())+" order by id desc");
		if(obj != null)
		{
			obj.setAssessDate(yyymmdd);
			obj.setCheckResult(Common.get(getCheckResult07()));
			obj.setSurvey(Common.get(getSurvey07()));
			obj.setPrecaution(Common.get(getPrecaution07()));
			obj.setModifier(getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			ServiceGetter.getInstance().getCommonService().update(obj);
			ServiceGetter.getInstance().getTcbwService().getDrgexDao().doReplyByDrgEX0109F(this);
		}
	}
	
	public Object doQueryOne() throws Exception {
		DRGEX0109F obj = this;
		String hql ="";
		if("".equals(obj.getDrg07id())){		
			hql = " from Drg0007Db where applNo="+Common.sqlChar(obj.getApplNo())+" order by id desc";
		}else{
			hql = " from Drg0007Db where id="+ Common.getInt(obj.getDrg07id());
		}
		Drg0007Db drg07 = (Drg0007Db) View.getObject(hql);
		System.out.println("[TCBW]-[DRGEX0109F]-[doQueryOne] : " + hql);		
		if (drg07!=null) {
			obj.setCheckResult07(Common.get(drg07.getCheckResult())); //清查結果
			obj.setSurvey07(Common.get(drg07.getSurvey())); //調查結果
			obj.setPrecaution07(Common.get(drg07.getPrecaution())); //預防矯正措施及改善時程
		}
		String analyDate = View.getLookupField("select analyDate from Drg0006Db where applNo="+Common.sqlChar(obj.getApplNo()));
		obj.setAnalyDate(analyDate);
		//取得不良品缺陷資料
		String drg01id = View.getLookupField("select id from Drg0001Db where applNo="+Common.sqlChar(obj.getApplNo()));
		String hql2 = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.get(drg01id);
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql2+" order by id asc");
		String[] mainCode = new String[objList.size()];
        String[] subCode = new String[100];
        String subCodeList =""; 
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i =0;
			int k =0;
			while (it.hasNext()) 
			{					
				Drg0002Db o = (Drg0002Db) it.next();
				
				mainCode[i] = String.valueOf( o.getMainCode() );
				String[] subList = o.getSubCode().split(",");
				for(int j=0; j<subList.length; j++){
					if(Common.get(subList[j]) != ""){
						subCodeList += "subCode like ('%"+subList[j]+"%') or ";
						subCode[k] = subList[j];
						k++;
					}
				}
				i++;
			}
		}
		if(subCodeList != null) subCodeList = subCodeList.substring(0, subCodeList.length()-3);
		
		obj.setMainCode(mainCode);
		obj.setSubCode(subCode);
		//取得風險等級
		String drgLev = View.getLookupField("select drgLev from Drg0004Db where applNo="+Common.sqlChar(obj.getApplNo()));
		obj.setDrgLev(drgLev);
		
	    //歷年本藥品之通報件數
	    String sql1 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo());		     
	    int hisData1 = ServiceGetter.getInstance().getCommonService().loadCount(sql1);
	    obj.setHisData1(Common.get(hisData1));
       
	    //歷年本藥品同此次瑕疵之通報件數
	    String sql2 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                   " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
	    int hisData2 = ServiceGetter.getInstance().getCommonService().loadCount(sql2);
	    obj.setHisData2(Common.get(hisData2));
	     
	    //一年內本藥品之通報件數
	    String sql3 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                   " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()));
        int hisData3 = ServiceGetter.getInstance().getCommonService().loadCount(sql3);
        obj.setHisData3(Common.get(hisData3));
        
        //一年內本藥品同此次瑕疵之通報件數
	    String sql4 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                   " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
	                   " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
        int hisData4 = ServiceGetter.getInstance().getCommonService().loadCount(sql4);
        obj.setHisData4(Common.get(hisData4));
        
        //一年內本藥品同此次瑕疵之高風險通報件數
	    String sql5 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                  " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))"+
                      " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
                      " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
        int hisData5 = ServiceGetter.getInstance().getCommonService().loadCount(sql5);
        obj.setHisData5(Common.get(hisData5));
        
        //一年內本藥品同此次瑕疵案件
        String sql6 = " select manufactorNo,count(applNo) from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
                      " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
                      " group by manufactorNo";
        java.util.List hql6List = ServiceGetter.getInstance().getCommonService().load(sql6);
        String hisData6 ="";
	    if(hql6List!=null && hql6List.size()>0)				
	    {		
	    	for (int i=0; i<hql6List.size(); i++) {
				Object[] o = (Object[]) hql6List.get(i);
				hisData6 += "["+o[0]+"] : "+"<input class=\"field_RO\" type=\"text\" size=\"3\" value=\""+o[1]+"\" >"+"件 ,";
			}	    	 
	    }
	    if(hisData6 != "") hisData6 = hisData6.substring(0,hisData6.length()-1);
	    else hisData6 = "<input class=\"field_RO\" type=\"text\" size=\"3\" value=\"0\" >"+"件"; 
	    obj.setHisData6(hisData6);	     
	    
        //一年內本藥品同此次瑕疵之高風險案件
        String sql7 = " select manufactorNo,count(applNo) from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
                      " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
                      " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))"+
                      " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))"+
                      " group by manufactorNo"; 
        java.util.List hql7List = ServiceGetter.getInstance().getCommonService().load(sql7);
        String hisData7 ="";
        if(hql7List!=null && hql7List.size()>0)
        {       	 
        	for (int i=0; i<hql7List.size(); i++) {       		
        		Object[] o = (Object[]) hql7List.get(i);       		
        		hisData7 += "["+o[0]+"] : "+"<input class=\"field_RO\" type=\"text\" size=\"3\" value=\""+o[1]+"\" >"+"件 ,";       	 
        	}
        }
        if(hisData7 != "") hisData7 = hisData7.substring(0,hisData7.length()-1);
        else hisData7 = "<input class=\"field_RO\" type=\"text\" size=\"3\" value=\"0\" >"+"件";  		
        obj.setHisData7(hisData7);
        
        //B級案件
        if("04".equals(drgLev)){
        	//一年內本藥品同此次瑕疵案件「同批號的各案件編號、不同批號的各案件編號」      
        	String sql8 = " select manufactorNo,applNo from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+                      
        	              " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+                   
        	              " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";		
        	java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(sql8+" order by id asc");		
        	String hisApplNoY="",hisApplNoN="";		
        	if(objList2!=null && objList2.size()>0)		
        	{			
        		java.util.Iterator it = objList2.iterator();			
        		while (it.hasNext())			
        		{				
        			Object[] itobj = (Object[])it.next();				
        			if(Common.get(itobj[0]).equals(getManufactorNo())) hisApplNoY = itobj[1]+",";
        			else hisApplNoN = itobj[1]+",";
        		}	
        	}		
        	if(hisApplNoY!="") hisApplNoY = hisApplNoY.substring(0,hisApplNoY.length()-1);		
        	if(hisApplNoN!="") hisApplNoN = hisApplNoN.substring(0,hisApplNoN.length()-1);		
        	obj.setHisApplNoY(hisApplNoY);        
        	obj.setHisApplNoN(hisApplNoN);
        }
		return obj;
	}
	
	public Object doQueryAll() throws Exception {
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Drg0007Db  where applNo ="+Common.sqlChar(getApplNo());
		
		System.out.println("[TCBW]-[DRGEX0109F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id desc ", this.getRecordStart(), this.getPageSize());
			
			if(objList!=null && objList.size()>0)
			{
				for(Object dtlObj : objList)
				{
					Drg0007Db dtl = (Drg0007Db)dtlObj;
 
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getAssessDate());
					rowArray[2] = Common.get(dtl.getCheckResult());
					rowArray[3] = Common.get(dtl.getSurvey());
					rowArray[4] = Common.get(dtl.getPrecaution());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		this.setState("queryAllSuccess");
		return arrList;
	}
	
    public static String getCheckBoxOption2(String className, String mainCode, String subCode, String otherDescribe, String sql, String[] selectedCheckBox1, String[] selectedCheckBox2 ,String id) {
    	StringBuilder sb = new StringBuilder();  	
    	java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
    	String otherDescribeValue ="";
    	if (list!=null && list.size()>0) {    		
    		int j=0;
        	for(int i=0 ; i<list.size();i++){
        		Object[] o = (Object[]) list.get(i);
        		//放置說明的value
        		otherDescribeValue = View.getLookupField(" select otherDescribe from Drg5002Db where drg5001Db.id="+Common.getLong(id) +" and mainCode="+Common.sqlChar(Common.get(o[0])));
        		sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( mainCode ).append( "\" value=\"" ).append( o[0] ).append( "\"");
        		if (selectedCheckBox1!=null && selectedCheckBox1.length>0) {
        			for (j=0; j<selectedCheckBox1.length; j++) {
        				if(Common.get(o[0]).equals(selectedCheckBox1[j])) sb.append(" checked");
        			}
        		}
        		sb.append(">").append(o[1]).append(" ").append("<br>");
        		String otherDescribeType ="hidden";
        		String showType ="1"; //描述說明顯示方式
        		//第2層 
        		java.util.List list2 = PersistenceServiceGetter.getInstance().getHibernateTemplate().find("select dpdKind,dpdKindName from Drg1001Db where substring(dpdKind,1,2)='"+o[0]+"' and isStop='N' order by dpdKind");
        		if (list2!=null && list2.size()>0) {
        			int l=0;
        			for(int k=0 ; k<list2.size(); k++){
        				Object[] o2 = (Object[]) list2.get(k);
        				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        				sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( subCode ).append( "\" value=\"" ).append( o2[0] ).append( "\"");
                		if (selectedCheckBox2!=null && selectedCheckBox2.length>0) {
                			for (l=0; l<selectedCheckBox2.length; l++) {
                				if(Common.get(o2[0]).equals(selectedCheckBox2[l])) sb.append("checked");
                			}
                		}
                		sb.append(">").append(o2[1]).append(" ");
                		//其他選項
                		if("ZZ".equals(Common.get(o2[0]).substring(2,4))){
                			otherDescribeType = "text";                			
                		}
        			}
        		}else{
        			//其他說明
        			otherDescribeType = "text";
        			showType="2";
        		}
        		if("2".equals(showType)){
        		    sb.append("請描述：&nbsp;&nbsp;<input class=\"" ).append( className ).append( "\" type=\"").append(otherDescribeType).append( "\" size=100 maxlength=100 name=\"" ).append( otherDescribe ).append( "\" value=\"" ).append(otherDescribeValue).append( "\"").append("><br>");
        		}else{
        			sb.append("<input class=\"" ).append( className ).append( "\" type=\"").append(otherDescribeType).append( "\" name=\"" ).append( otherDescribe ).append( "\" value=\"" ).append(otherDescribeValue).append( "\"><br>");
        		}
        	}    		
    	}
        return sb.toString();    	
    } 
	
	public String getApplNo() {return checkGet(applNo);}
	public void setApplNo(String applNo) {this.applNo = checkSet(applNo);}
	public String getStatus() {return checkGet(status);}
	public void setStatus(String status) {this.status = checkSet(status);}
	
	public String getDrg07id() {return checkGet(drg07id);}
	public void setDrg07id(String drg07id) {this.drg07id = checkSet(drg07id);}
	public String getCheckResult07() {return checkGet(checkResult07);}
	public void setCheckResult07(String checkResult07) {this.checkResult07 = checkSet(checkResult07);}
	public String getSurvey07() {return checkGet(survey07);}
	public void setSurvey07(String survey07) {this.survey07 = checkSet(survey07);}
	public String getPrecaution07() {return checkGet(precaution07);}
	public void setPrecaution07(String precaution07) {this.precaution07 = checkSet(precaution07);}
	
	public String getPermitKey() {return checkGet(permitKey);}
	public void setPermitKey(String permitKey) {this.permitKey = checkSet(permitKey);}
	public String getPermitNo() {return checkGet(permitNo);}
	public void setPermitNo(String permitNo) {this.permitNo = checkSet(permitNo);}
	public String getChProduct() {return checkGet(chProduct);}
	public void setChProduct(String chProduct) {this.chProduct = checkSet(chProduct);}
	public String getEnProduct() {return get(enProduct);}
	public void setEnProduct(String enProduct) {this.enProduct = checkSet(enProduct);}
	public String getIngredient() {return checkGet(ingredient);}
	public void setIngredient(String ingredient) {this.ingredient = checkSet(ingredient);}	
	public String getMedModel() {return checkGet(medModel);}
	public void setMedModel(String medModel) {this.medModel = checkSet(medModel);}
	public String getMedModelOther() {return checkGet(medModelOther);}
	public void setMedModelOther(String medModelOther) {this.medModelOther = checkSet(medModelOther);}
	public String getApplicationName() {return checkGet(applicationName);}
	public void setApplicationName(String applicationName) {this.applicationName = checkSet(applicationName);}
	public String getManufactorName() {return checkGet(manufactorName);}
	public void setManufactorName(String manufactorName) {this.manufactorName = checkSet(manufactorName);}
	public String getManufactorNo() {return checkGet(manufactorNo);}
	public void setManufactorNo(String manufactorNo) {this.manufactorNo = checkSet(manufactorNo);}
	
	public String[] getMainCode() {return mainCode;}
	public void setMainCode(String[] mainCode) {this.mainCode = mainCode;}
	public String[] getSubCode() {return subCode;}	
	public void setSubCode(String[] subCode) {this.subCode = subCode;}
	public String[] getOtherDescribe() {return otherDescribe;}
	public void setOtherDescribe(String[] otherDescribe) {this.otherDescribe = otherDescribe;}
	
	public String getHisData1() {return checkGet(hisData1);}
	public void setHisData1(String hisData1) {this.hisData1 = checkSet(hisData1);}
	public String getHisData2() {return checkGet(hisData2);}
	public void setHisData2(String hisData2) {this.hisData2 = checkSet(hisData2);}
	public String getHisData3() {return checkGet(hisData3);}
	public void setHisData3(String hisData3) {this.hisData3 = checkSet(hisData3);}
	public String getHisData4() {return checkGet(hisData4);}
	public void setHisData4(String hisData4) {this.hisData4 = checkSet(hisData4);}
	public String getHisData5() {return checkGet(hisData5);}
	public void setHisData5(String hisData5) {this.hisData5 = checkSet(hisData5);}
	public String getHisData6() {return get(hisData6);}
	public void setHisData6(String hisData6) {this.hisData6 = set(hisData6);}
	public String getHisData7() {return get(hisData7);}
	public void setHisData7(String hisData7) {this.hisData7 = set(hisData7);}
	public String getDrgLev() {return checkGet(drgLev);}
	public void setDrgLev(String drgLev) {this.drgLev = checkSet(drgLev);}
	
	public String getHisApplNoY() {return checkGet(hisApplNoY);}
	public void setHisApplNoY(String hisApplNoY) {this.hisApplNoY = checkSet(hisApplNoY);}
	public String getHisApplNoN() {return checkGet(hisApplNoN);}
	public void setHisApplNoN(String hisApplNoN) {this.hisApplNoN = checkSet(hisApplNoN);}
}
