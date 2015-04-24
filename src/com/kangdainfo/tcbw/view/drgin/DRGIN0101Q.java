package com.kangdainfo.tcbw.view.drgin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGIN0101Q extends SuperBean{
	
	private String q_isQuery;
	private String q_id;				//ID
	private String q_applNoS;			//案件編號S
	private String q_applNoE;			//案件編號E
	private String q_occurDateS;		//發生日期S
	private String q_occurDateE;		//發生日期E
	private String q_notifierRepDateS;	//通報日期S
	private String q_notifierRepDateE;	//通報日期E	
	private String q_enrolledDateS;	    //收案日期S
	private String q_enrolledDateE;	    //收案日期E
	private String q_notifierSource;  	//通報來源
	private String q_notifierDept;		//通報者服務機構
	private String q_notifierType;		//通報單位
	private String q_permitNo;			//許可證字號
	private String q_chProduct;			//藥品品名
	private String q_ingredient;		//有效成份名稱
	private String q_applicationName;	//申請商
	private String q_manufactorName;	//製造廠
	private String q_status;			//案件狀態
	private String[] q_drgLev;			//風險等級-drg0004Db
	private String[] q_mainCode;		//不良品缺陷
	private String[] q_subCode;			
	private String q_permitKey;
	private String q_isTrans;  	        //是否為歷史移轉資料

	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}
	
	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String q_id) {
		this.q_id = checkSet(q_id);
	}

	public String getQ_applNoS() {
		return checkGet(q_applNoS);
	}

	public void setQ_applNoS(String q_applNoS) {
		this.q_applNoS = checkSet(q_applNoS);
	}
	
	public String getQ_applNoE() {
		return checkGet(q_applNoE);
	}

	public void setQ_applNoE(String q_applNoE) {
		this.q_applNoE = checkSet(q_applNoE);
	}

	public String getQ_occurDateS() {
		return checkGet(q_occurDateS);
	}

	public void setQ_occurDateS(String q_occurDateS) {
		this.q_occurDateS = checkSet(q_occurDateS);
	}
	
	public String getQ_occurDateE() {
		return checkGet(q_occurDateE);
	}

	public void setQ_occurDateE(String q_occurDateE) {
		this.q_occurDateE = checkSet(q_occurDateE);
	}

	public String getQ_enrolledDateS() {
		return checkGet(q_enrolledDateS);
	}

	public void setQ_enrolledDateS(String q_enrolledDateS) {
		this.q_enrolledDateS = checkSet(q_enrolledDateS);
	}

	public String getQ_enrolledDateE() {
		return checkGet(q_enrolledDateE);
	}

	public void setQ_enrolledDateE(String q_enrolledDateE) {
		this.q_enrolledDateE = checkSet(q_enrolledDateE);
	}

	public String getQ_notifierRepDateS() {
		return checkGet(q_notifierRepDateS);
	}

	public void setQ_notifierRepDateS(String q_notifierRepDateS) {
		this.q_notifierRepDateS = checkSet(q_notifierRepDateS);
	}
	
	public String getQ_notifierRepDateE() {
		return checkGet(q_notifierRepDateE);
	}

	public void setQ_notifierRepDateE(String q_notifierRepDateE) {
		this.q_notifierRepDateE = checkSet(q_notifierRepDateE);
	}

	public String getQ_notifierSource() {
		return checkGet(q_notifierSource);
	}

	public void setQ_notifierSource(String q_notifierSource) {
		this.q_notifierSource = checkSet(q_notifierSource);
	}

	public String getQ_notifierType() {
		return checkGet(q_notifierType);
	}

	public void setQ_notifierType(String qNotifierType) {
		q_notifierType = checkSet(qNotifierType);
	}

	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}

	public void setQ_permitNo(String q_permitNo) {
		this.q_permitNo = checkSet(q_permitNo);
	}

	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}

	public void setQ_chProduct(String q_chProduct) {
		this.q_chProduct = checkSet(q_chProduct);
	}

	public String getQ_ingredient() {
		return checkGet(q_ingredient);
	}

	public void setQ_ingredient(String q_ingredient) {
		this.q_ingredient = checkSet(q_ingredient);
	}

	public String getQ_applicationName() {
		return checkGet(q_applicationName);
	}

	public void setQ_applicationName(String q_applicationName) {
		this.q_applicationName = checkSet(q_applicationName);
	}

	public String getQ_manufactorName() {
		return checkGet(q_manufactorName);
	}

	public void setQ_manufactorName(String q_manufactorName) {
		this.q_manufactorName = checkSet(q_manufactorName);
	}

	public String getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String q_status) {
		this.q_status = checkSet(q_status);
	}

	public String[] getQ_drgLev() {
		return q_drgLev;
	}

	public void setQ_drgLev(String[] q_drgLev) {
		this.q_drgLev = q_drgLev;
	}
	
	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}

	public void setQ_permitKey(String qPermitKey) {
		q_permitKey = checkSet(qPermitKey);
	}
	
	public String getQ_notifierDept() {
		return checkGet(q_notifierDept);
	}

	public void setQ_notifierDept(String qNotifierDept) {
		q_notifierDept = checkSet(qNotifierDept);
	}
	
	public String[] getQ_mainCode() {
		return q_mainCode;
	}

	public void setQ_mainCode(String[] qMainCode) {
		q_mainCode = qMainCode;
	}

	public String[] getQ_subCode() {
		return q_subCode;
	}

	public void setQ_subCode(String[] qSubCode) {
		q_subCode = qSubCode;
	}	

	public String getQ_isTrans() {
		return checkGet(q_isTrans);
	}

	public void setQ_isTrans(String q_isTrans) {
		this.q_isTrans = checkSet(q_isTrans);
	}

	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg0001Db as a where 1 = 1 ";

		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			
			if(!"".equals(getQ_applNoS()) && !"".equals(getQ_applNoE())) 
				hql += " and applNo >= " +  Common.sqlChar(getQ_applNoS())+ " and applNo <= " + Common.sqlChar(getQ_applNoE());
			else if(!"".equals(getQ_applNoS()) && "".equals(getQ_applNoE()))
				hql += " and applNo like " + Common.sqlChar("%"+getQ_applNoS()+"%");
			else if("".equals(getQ_applNoS()) && !"".equals(getQ_applNoE()))
				hql += " and applNo like " + Common.sqlChar("%"+getQ_applNoE()+"%");
			
			if(!"".equals(getQ_occurDateS())) 
				hql += " and occurDate >= " + Common.sqlChar(getQ_occurDateS());
			if(!"".equals(getQ_occurDateE()))
				hql += " and occurDate <= " + Common.sqlChar(getQ_occurDateE());
			
			if(!"".equals(getQ_enrolledDateS())) 
				hql += " and enrolledDate >= " + Common.sqlChar(getQ_enrolledDateS());
			if(!"".equals(getQ_enrolledDateE())) 
				hql += " and enrolledDate <= " + Common.sqlChar(getQ_enrolledDateE());
			
			if(!"".equals(getQ_notifierRepDateS())) 
				hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
			if(!"".equals(getQ_notifierRepDateE())) 
				hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
			
			if(!"".equals(getQ_notifierSource())) 
				hql += " and notifierSource = " + Common.sqlChar(Common.get(getQ_notifierSource()));
			
			if(!"".equals(getQ_notifierDept())) 
				hql += " and notifierDept like " + Common.sqlChar("%" + Common.get(getQ_notifierDept()) + "%");

			if(!"".equals(getQ_permitKey())) 
				hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
			
			if(!"".equals(getQ_permitNo())) 
				hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
			
			if(!"".equals(getQ_chProduct()))
				hql += " and (chProduct like " + Common.sqlChar("%" + Common.get(getQ_chProduct()) + "%") + " or enProduct like " + Common.sqlChar("%" + Common.get(getQ_chProduct()) + "%") + ")";
			
			if(!"".equals(getQ_ingredient())) 
				hql += " and ingredient like " + Common.sqlChar("%" + Common.get(getQ_ingredient()) + "%");
			if(!"".equals(getQ_notifierType()))
				hql += " and notifierType = " + Common.sqlChar(getQ_notifierType());
			if(!"".equals(getQ_applicationName())) 
				hql += " and applicationName like " + Common.sqlChar("%" + getQ_applicationName() + "%");
			
			if(!"".equals(getQ_status())) 
				hql += " and status = " + Common.sqlChar(getQ_status());
			
			if(!"".equals(getQ_manufactorName())) 
				hql += " and manufactorName like " + Common.sqlChar("%" + getQ_manufactorName() + "%");
			//風險等級
			if(getQ_drgLev() != null){
				String temp = "";
				for(String a:getQ_drgLev()){
					temp +="'" + a + "',";
				}
				hql += " and a.applNo in (select applNo from Drg0004Db where drgLev in (" +  temp.substring(0, temp.length()-1) + "))";
			}
		
			String subCodeList ="";
			String mainCodeList ="";
			if(!"".equals(Common.get(getQ_mainCode())) || !"".equals(Common.get(getQ_subCode()))){
				if(!"".equals(Common.get(getQ_mainCode()))){
					for(int j=0;j<getQ_mainCode().length;j++){
						if(!"".equals(Common.get(mainCodeList))) mainCodeList += ",";
						mainCodeList += Common.sqlChar(getQ_mainCode()[j]);
					}
				}
				if(!"".equals(Common.get(getQ_subCode()))){
					for(int k=0;k<getQ_subCode().length;k++){
						if(k!=0) subCodeList += " or ";	
						else subCodeList += " ( ";
						subCodeList += " b.subCode like " + Common.sqlChar("%"+getQ_subCode()[k]+"%");			
					}
				}
				if(subCodeList == ""){				
					hql += " and a.id in ( select b.drg0001Db.id from Drg0002Db b where b.mainCode in ("+mainCodeList+"))";
				}else{
					hql += " and a.id in ( select b.drg0001Db.id from Drg0002Db b where b.mainCode in ("+mainCodeList+") and "+subCodeList+" or b.subCode='' ))";
				}
			}
			if(!"".equals(getQ_isTrans())){
				if("Y".equals(getQ_isTrans())) hql += " and a.trans='Y' ";
				else if ("N".equals(getQ_isTrans())) hql += " and ( a.trans is null or a.trans='' ) ";
			}
		}
		System.out.println("[TCBW]-[DRGIN0101Q]-[不良品-QUERYALL] : " + hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql+"order by a.id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRGST1");
				java.util.Map<String, String> dpdMap = TCBWCommon.getCommonCodeMap("DRG0105"); //不良品主代碼				
				java.util.Map<String, String> subMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Drg1001Db"); //缺陷子代碼
				
				for(Object dtlObj : objList) {				
					Drg0001Db dtl = (Drg0001Db)dtlObj;	
					
					String hql2 = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(dtl.getId());
					java.util.List drg02List = ServiceGetter.getInstance().getCommonService().load(hql2+" order by id asc");
					String dpdList ="";
					if(drg02List!=null && drg02List.size()>0)
					{
						java.util.Iterator it = drg02List.iterator();
						while (it.hasNext()) 
						{					
							Drg0002Db o = (Drg0002Db) it.next();
							if(dpdList.length()>0) dpdList += ", ";
							dpdList += dpdMap.get(o.getMainCode())+":";
							
							if(!"".equals(Common.get(o.getSubCode()))){
								String subCodeList ="";
								String[] subCode = Common.get(o.getSubCode()).split(",");
								for(int j=0; j<subCode.length; j++){
									if(subCodeList.length()>0) subCodeList += ",";
									subCodeList += subMap.get(subCode[j]);
									if("ZZ".equals(subCode[j].substring(2))){
										subCodeList += (":"+o.getOtherDescribe());										
									}
								}
								dpdList += subCodeList;
							}else{							
								dpdList += o.getOtherDescribe();
							}
						}
					}
					drg02List.clear();					
					if(dpdList != "") dpdList = Common.get(dpdList).substring(0,Common.get(dpdList).length());
					
					String[] rowArray = new String[13];
					
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.formatYYYMMDD(Common.get(dtl.getNotifierRepDate()),4);
					rowArray[3] = Common.get(View.getCommonCodeName("DRGPKID", dtl.getPermitKey()))+"-"+Common.get(dtl.getPermitNo());
					rowArray[4] = Common.get(dtl.getChProduct());
					rowArray[5] = Common.get(dtl.getEnProduct());
					rowArray[6] = Common.get(dtl.getIngredient());
					rowArray[7] = Common.get(dtl.getApplicationName());
					rowArray[8] = Common.get(dtl.getManufactorName());
					rowArray[9] = Common.get(dtl.getManufactorNo());
					rowArray[10] = Common.get(dpdList);
					rowArray[11] = Common.get(statusMap.get(dtl.getStatus()));
					//rowArray[12] =  "<input type='button' class='toolbar_default' name='btn_Data' value='明　細' onClick=\"listContainerRowClick('"+dtl.getId()+"');queryOne('" + dtl.getId() + "');\"> ";
					arrList.add(rowArray);
				}
				objList.clear();
			}		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {}

	@Override
	public void doUpdate() throws Exception {}

	@Override
	public void doDelete() throws Exception {}
	
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
        		sb.append(" id='"+o[0]+"' onclick='checkChildrenSubCode(this);'");
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
                		sb.append(" id='"+o2[0]+"' onclick='checkParentMainCode(this);'");
                		sb.append(">").append(o2[1]).append(" ");
        			}
        			sb.append("<br>");
        		}
        	}    		
    	}
        return sb.toString();    	
    }

}
