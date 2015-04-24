package com.kangdainfo.tcbw.view.drgex;


import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.model.bo.Drg5002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;



public class DRGEX0102F extends DRGEX0101F{
	
	String tabId ;
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}	
	
	private String isNeedBackQuery;	
	private String isEnabledUpdate;
	private String updateType;//接收前端按鈕狀態
	
	private String autoUpdate; //主動補件
	private String drg0001Id; //主檔Id
	
	private String caseType ;
	private String applNo;	//案件號碼	VARCHAR(11)
	private String status;	//案件狀態	VARCHAR(2)
	private String chargeMan;	//作業人員	VARCHAR(50)
	private String revision; //版次	VARCHAR(3)
	
	//基本資料------------------------------------------------------------------
	private String occurDate;	//發生日期	VARCHAR(7)
	private String notifierRevDate;	//通報者接獲日期	VARCHAR(7)
	private String notifierRepDate;	//通報中心接獲通報日期	VARCHAR(7)
	private String notifierSource;	//通報來源	VARCHAR(2)
	private String notifierUserID; //  通報者ID VARCHAR(50)
	private String notifierName;	//通報者姓名	NVARCHAR(20)
	private String notifierDept;	//通報者服務機構	NVARCHAR(50)
	private String notifierDeptID;	//通報者服務機構ID	VARCHAR(20)
	private String notifierTel;	//通報者電話	VARCHAR(10)
	private String notifierTelArea;	//通報者電話區碼	VARCHAR(2)
	private String notifierTelExt;	//通報者電話分機   VARCHAR(3)
	private String notifierCounty;	//通報者電話	VARCHAR(2)
	private String notifierZipCode;	//通報者電話	VARCHAR(5)
	private String notifierAddress;	//通報者地址	NVARCHAR(100)
	private String notifierEmail;	//通報者電子信箱	VARCHAR(50)
	private String notifierType;	//通報者屬性	VARCHAR(2)
	private String notifierTitle;	//通報者職稱	VARCHAR(2)
	
	//不良藥品資料--------------------------------------------------------------
	private String permitKey;	//許可證字	VARCHAR(14)
	private String permitNo;	//許可證號	VARCHAR(14)
	private String chProduct; 	//商品名稱中文	NVARCHAR(100)
	private String enProduct; 	//商品名稱英文	VARCHAR(100)
	private String ingredient;	//有效成分名稱	NVARCHAR(50)
	private String content;	//單位含量	NVARCHAR(20)
	private String medModel;	//劑型	VARCHAR(2)
	private String medModelOther;	//劑型(描述)	NVARCHAR(50)
	private String medPackage;	//包裝形式	VARCHAR(2)
	private String medPackageOther;	//包裝形式(描述)	NVARCHAR(50)
	private String applicationID;	//藥商/申請商(統編)	VARCHAR(10)
	private String applicationName;	//藥商/申請商	NVARCHAR(50)
	private String manufactorName;	//製造商/製造廠	NVARCHAR(50)
	private String manufactorCountry;	//製造商/製造廠國別	NVARCHAR(50)
	private String manufactorNo;	//製造批號	VARCHAR(11)
	private String manufactorDate;	//製造日期	VARCHAR(7)
	private String expirationDate;	//保存期限	VARCHAR(7)
	private String storage;	//儲存環境	VARCHAR(2)
	private String storageOther;	//儲存環境(描述)	NVARCHAR(50)
	private String isFindYn;	//是否一經拆封即發現本不良品缺陷	VARCHAR(1)
	private String isSingleYn;	//本次通報事件是否為單一個案	VARCHAR(1)
	private String sameNum;	//同批號件數	VARCHAR(6)
	private String diffNum;	//不同批號件數	VARCHAR(6)
	private String isHarmYn;	//是否已對人體健康產生危害	VARCHAR(1)
	private String isUsedYn;	//是否為病人使用後發現不良品，向醫療人員反應
	private String evenContactYn;	//是否已連絡廠商	VARCHAR(1)
	private String dealWith;	//後續處理	VARCHAR(2)
	private String isContactYn;	//是否提供聯絡資訊供廠商後續調查評估	VARCHAR(1)
	private String defectDesc;	//不良品缺陷描述  VARCHAR(1000)
	private String firstResult;	//不良品原因初評	VARCHAR(2)
	private String firstRemark;	//不良品原因初評(描述)	NVARCHAR(50)	
	
	//不良品缺陷-----------------------------------------------------------------
	private String[] mainCode;
	private String[] subCode;
	private String[] otherDescribe;	
	
	private String revisionId;
	private String[] oldSubCode;
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	

	//主動補件完成
	public void autoUpdateSend() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getDrgexDao().autoReUpdateByDrgEX0102F(this);
	}
	
	// FOR 新增登入該頁面時，自動新增一筆資料
	public void doInsert()throws Exception
	{
		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if(c == null)
		{
			c = new CommonUser();
			CommonDepartment d = new CommonDepartment();
			d.setShortCode("01");
			c.setCommonDepartment(d);
			System.out.println("[TCBW]-[DRGEX0102F]-[新增]-[無法辨別登入的使用者]");
		}

		Drg5001Db obj = new Drg5001Db();
		obj.setStatus("00");
		obj.setRevision("1");
		
		//基本資料------------------------------------------------------------------
		obj.setNotifierUserID(Common.get(c.getUserId()));
		obj.setNotifierName(Common.get(c.getUserName()));
		obj.setNotifierDeptID(Common.get(c.getUserJob()));		
		obj.setNotifierDept(Common.get(TCBWCommon.getNotifierDeptName(c.getCommonDepartment().getDepartmentCode(),c.getUserJob())));
		obj.setNotifierTelArea(Common.get(c.getUserTelArea()));
		obj.setNotifierTel(Common.get(c.getUserTel()));
		obj.setNotifierTelExt(Common.get(c.getUserTelExt()));
		obj.setNotifierCounty(Common.get(c.getUserCounty()));
		obj.setNotifierZipCode(Common.get(c.getUserZipCode()));
		obj.setNotifierAddress(Common.get(c.getUserAddr()));
		obj.setNotifierEmail(Common.get(c.getUserEmail()));
		obj.setNotifierType(Common.get(c.getCommonDepartment().getDepartmentCode()));
		obj.setNotifierTitle(Common.get(c.getJobTitle()));
		

		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}

	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrgexDao().updateByDrgEX0102F(this);
	}	

    public static String getCheckBoxOption2(String className, String mainCode, String subCode, String otherDescribe, String sql, String[] selectedCheckBox1, String[] selectedCheckBox2 ,String id, String oldId, String[] oldSubCode) {
    	StringBuilder sb = new StringBuilder();  	
    	java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
    	String otherDescribeValue ="",otherDescribeValueOld="";
    	if (list!=null && list.size()>0) {    		
        	for(int i=0 ; i<list.size();i++){
        		Object[] o = (Object[]) list.get(i);
        		//取出上一版本
        		if(!"".equals(Common.get(oldId))){        			
        			otherDescribeValueOld = View.getLookupField(" select otherDescribe from Drg5002Db where drg5001Db.id="+Common.getLong(oldId) +" and mainCode="+Common.sqlChar(Common.get(o[0])));
        		}
        		//放置說明的value
        		otherDescribeValue = View.getLookupField(" select otherDescribe from Drg5002Db where drg5001Db.id="+Common.getLong(id) +" and mainCode="+Common.sqlChar(Common.get(o[0])));
        		sb.append(o[1]).append("：");
        		sb.append("<input style=\"display:none\" class=\"" ).append( "field_RO" ).append( "\" type=\"checkbox\" name=\"" ).append( mainCode ).append( "\" value=\"" ).append( o[0] ).append( "\"");
        		if (selectedCheckBox1!=null && selectedCheckBox1.length>0) {
        			for (int j=0; j<selectedCheckBox1.length; j++) {        				
        				if(Common.get(o[0]).equals(selectedCheckBox1[j])) sb.append(" checked");
        			}
        		}
        		sb.append(">");
        		String otherDescribeType ="hidden";
        		String showType ="1"; //描述說明顯示方式
        		
        		//第2層 
        		java.util.List list2 = PersistenceServiceGetter.getInstance().getHibernateTemplate().find("select dpdKind,dpdKindName from Drg1001Db where substring(dpdKind,1,2)='"+o[0]+"' and isStop='N' order by dpdKind");
        		if (list2!=null && list2.size()>0) {
        			for(int k=0 ; k<list2.size(); k++){
        				Object[] o2 = (Object[]) list2.get(k);
        				boolean a = false;
        				boolean b = false;        				
        				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        				sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( subCode ).append( "\" value=\"" ).append( o2[0] ).append( "\"");
                		//若勾選"顏色異常"，需提示"若為雙/三腔軟袋於操作前發現顏色異常，請詳加描述隔膜及外袋之完整性"
        				if("0101".equals(Common.get(o2[0]))){
        					sb.append(" onclick=\"alertAbnormalColor(this);\"");
        				}        					
        				if (selectedCheckBox2!=null && selectedCheckBox2.length>0) {        					
                			for (int l=0; l<selectedCheckBox2.length; l++) {
                				if(selectedCheckBox2[l] != null){                					
                					if(Common.get(o2[0]).equals(selectedCheckBox2[l])){                    					
                    					sb.append("checked");
                    					a = true;
                    					break;
                    				}                					                    				                  				
                				}               			
                			}
                		}
        				if(!"".equals(Common.get(oldId)) && oldSubCode!=null && oldSubCode.length>0){        						
    						for (int m=0; m < oldSubCode.length; m++) {               							
    							if(oldSubCode[m] != null){                								
    								if(Common.get(oldSubCode[m]).equals(o2[0])){
    									b = true;
    									break;
    								}                								
    							}
                    		}
    					}
                		sb.append("  onClick=\"updateMainCode(this);\" ");
                		if(!"".equals(Common.get(oldId)) && a != b) sb.append(" style=\"background-color:'#00A002'\" ");
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
        		    sb.append("請描述：&nbsp;&nbsp;<input class=\"").append( className ).append( "\" type=\"").append(otherDescribeType).append( "\" size=100 maxlength=100 name=\"" ).append( otherDescribe ).append( "\"");
        		    sb.append("id=\"otherDescribe").append(o[0]).append("\"").append("value=\"" ).append(otherDescribeValue).append("\"").append("onChange=\"updateMainCode2(this);\"");
        		    if(!"".equals(Common.get(oldId)) && !Common.get(otherDescribeValueOld).equals(Common.get(otherDescribeValue))){
        		    	sb.append(" style=\"background-color:'#00A002'\" ");
        		    }
        		    sb.append("><br>");
        		}else{
        			sb.append("<input class=\"" ).append( className ).append( "\" type=\"").append(otherDescribeType).append( "\" name=\"" ).append( otherDescribe ).append( "\"");
        			sb.append("id=\"otherDescribe").append(o[0]).append("\"").append("value=\"" ).append(otherDescribeValue).append("\"").append("onChange=\"updateSubCodeZZ(this);\"");
        			if(!"".equals(Common.get(oldId)) && !Common.get(otherDescribeValueOld).equals(Common.get(otherDescribeValue))){
        		    	sb.append(" style=\"background-color:'#00A002'\" ");
        		    }
        			sb.append("><br>");
        		}
        	}    		
    	}
        return sb.toString();    	
    } 
	
    //不良品通報表列印
	public DefaultTableModel getDefaultTableModel() throws Exception
	{
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[] 
    	        {"id","applNo","status","chargeMan","occurDate","notifierRevDate",
    			"notifierRepDate","notifierSource","notifierName","notifierDept","notifierTel","address","notifierEamil",//6-12
    			"notifierType","notifierTitle","permitNo","chProduct","enProduct","ingredient","content","medModel",//13-20
    			"medPackage","applicationName","manufactorName","manufactorNo","manufactorDate","expirationDate",//21-26
    			"storage","isFindYn","isSingleYn","isHarmYn","evenContactYn","dealWith",//27-32
    			"isContactYn","firstResult","defectDesc","isUsedYn","defectMemo"  //33-38
    	        };
    	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = "from Drg5001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) {
			java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
			java.util.Map<String, String> titleMap = TCBWCommon.getCommonCodeMap("TITLE");
			for (int i=0; i<list.size(); i++) {
				Drg5001Db obj = (Drg5001Db) list.get(i);
				String rowArray[]=new String[cols.length];
				
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.get(obj.getChargeMan()); 
				rowArray[4]=Common.formatYYYMMDD(obj.getOccurDate(),2);
				rowArray[5]=Common.formatYYYMMDD(obj.getNotifierRevDate(),2);
				rowArray[6]=Common.formatYYYMMDD(obj.getNotifierRepDate(),2);
				rowArray[7]=getNotifierSource(Common.get(obj.getNotifierSource()));
				rowArray[8]=Common.get(obj.getNotifierName());
				rowArray[9]=Common.get(obj.getNotifierDept());
				rowArray[10]=Common.get(obj.getNotifierTel());
				rowArray[11]=Common.get(obj.getNotifierAddress());
				rowArray[12]=Common.get(obj.getNotifierEmail());
				rowArray[13]=Common.get(obj.getNotifierType());
				rowArray[14]=Common.get(titleMap.get(obj.getNotifierTitle()));
				String permitKey ="";
				if(!"".equals(obj.getPermitKey())){
					permitKey = pkidMap.get(obj.getPermitKey());
				}
				rowArray[15]=Common.get(permitKey+"字第"+obj.getPermitNo()+"號");
				rowArray[16]=Common.get(obj.getChProduct());
				rowArray[17]=Common.get(obj.getEnProduct());
				rowArray[18]=Common.get(obj.getIngredient());
				rowArray[19]=Common.get(obj.getContent());
				
				rowArray[20]=getMedModel(Common.get(obj.getMedModel()),Common.get(obj.getMedModelOther()));
				rowArray[21]=getPackage(Common.get(obj.getMedPackage()),Common.get(obj.getMedPackageOther()));
				rowArray[22]=Common.get(obj.getApplicationName());
				rowArray[23]=Common.get(obj.getManufactorName());
				rowArray[24]=Common.get(obj.getManufactorNo());
				rowArray[25]=Common.formatYYYMMDD(obj.getManufactorDate(),2);
				rowArray[26]=Common.formatYYYMMDD(obj.getExpirationDate(),2);
			
				rowArray[27]=getStorage(Common.get(obj.getStorage()),Common.get(obj.getStorageOther()));
			
				if("Y".equals(Common.get(obj.getIsFindYn()))) {
					rowArray[28]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getIsFindYn()))) {
					rowArray[28]="□ 是　■ 否";
				} else {
					rowArray[28]="□ 是　□ 否";
				}
				
				if("Y".equals(Common.get(obj.getIsSingleYn()))) {
					rowArray[29]="■ 是\n□ 否，同批號，共____件；不同批號，共____件";
				} else if("N".equals(Common.get(obj.getIsSingleYn()))) {
					rowArray[29]="□ 是\n■ 否，";
					if("".equals(Common.get(obj.getDiffNum()))) {
						rowArray[29]+="同批號，共" + Common.get(obj.getSameNum()) + "件；不同批號，共____件";
					} else if("".equals(Common.get(obj.getSameNum()))) {
						rowArray[29]+="同批號，共____件；不同批號，共" + Common.get(obj.getDiffNum())+ "件";
					} else if("".equals(Common.get(obj.getSameNum())) && "".equals(Common.get(obj.getDiffNum()))){
						rowArray[29]+="同批號，共____件；不同批號，共____件";
					} else {
						rowArray[29]+="同批發，共" + Common.get(obj.getSameNum()) + "件；不同批號，共" + Common.get(obj.getDiffNum()) + "件";
					}
				} else {
					rowArray[29]="□　是\n□　否，同批號，共____件；不同批號，共____件";
				}
				
				if("Y".equals(Common.get(obj.getIsHarmYn()))) {
					rowArray[30]="■ 是，並請同時作藥品不良反應通報。\n□ 否";
				} else if("N".equals(Common.get(obj.getIsHarmYn()))) {
					rowArray[30]="□ 是，並請同時作藥品不良反應通報。\n■ 否";
				} else {
					rowArray[30]="□ 是，並請同時作藥品不良反應通報。\n□ 否";
				}
				
				if("Y".equals(Common.get(obj.getEvenContactYn()))) {
					rowArray[31]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getEvenContactYn()))) {
					rowArray[31]="□ 是　■ 否";
				} else {
					rowArray[31]="□ 是　□ 否";
				}
				
				rowArray[32]=getDealWith(Common.get(obj.getDealWith()));
				if("Y".equals(Common.get(obj.getIsContactYn()))) {
					rowArray[33]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getIsContactYn()))) {
					rowArray[33]="□ 是　■ 否";
				} else {
					rowArray[33]="□ 是　□ 否";
				}
				
				rowArray[34]=getFirstResult(Common.get(obj.getFirstResult()),Common.get(obj.getFirstRemark()));	
				
				if(obj.getDrg5002Dbs()!=null && obj.getDrg5002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getDrg5002Dbs().iterator();
					String[] maincode = null;
					String[] subcode = null;
					String main = "";
					String sub = "";
					
					while(it2.hasNext())
					{
						Drg5002Db drg5002Db = (Drg5002Db)it2.next();
						
						main += drg5002Db.getMainCode() + ",";
						sub += drg5002Db.getSubCode() + ",";
					}
					maincode = main.split(",");
					subcode = sub.split(",");
					
					rowArray[35]=getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId",maincode,subcode,Common.get(obj.getId()));
			
				} else {
					rowArray[35]="";
				}
				
				if("Y".equals(Common.get(obj.getIsUsedYn()))) {
					rowArray[36]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getIsUsedYn()))) {
					rowArray[36]="□ 是　■ 否";
				} else {
					rowArray[36]="□ 是　□ 否";
				}
				
				rowArray[37]=Common.get(obj.getDefectDesc());	
				
				arrList.add(rowArray);
			}
		}
		if (arrList!=null && arrList.size()>0) {
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		return model;
		
	}
	
	//不良品缺陷之描述
	 public static String getCheckBoxOption2(String sql, String[] selectedCheckBox1, String[] selectedCheckBox2 ,String id) {
	    	StringBuilder sb = new StringBuilder();  	
	    	java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
	    	String otherDescribeValue ="";
	    	if (list!=null && list.size()>0) {    		
	    		int j=0;
	        	for(int i=0 ; i<list.size();i++){
	        		Object[] o = (Object[]) list.get(i);
	        		//放置說明的value
	        		otherDescribeValue = View.getLookupField(" select otherDescribe from Drg5002Db where drg5001Db.id="+Common.getLong(id) +" and mainCode="+Common.sqlChar(Common.get(o[0])));
	        		
	        		if (selectedCheckBox1!=null && selectedCheckBox1.length>0) {
	        			for (j=0; j<selectedCheckBox1.length; j++) {
	        				if(Common.get(o[0]).equals(selectedCheckBox1[j]));
	        			}
	        		}
	        		sb.append(o[1]).append(" ").append("\n");
	        		String showType ="1"; //描述說明顯示方式
	        		//第2層 
	        		java.util.List list2 = PersistenceServiceGetter.getInstance().getHibernateTemplate().find("select dpdKind,dpdKindName from Drg1001Db where substring(dpdKind,1,2)='"+o[0]+"' and isStop='N' order by dpdKind");
	        		if (list2!=null && list2.size()>0) {
	        			int l=0;
	        			for(int k=0 ; k<list2.size(); k++){
	        				Object[] o2 = (Object[]) list2.get(k);
//	        				sb.append( o2[0] );
	                		if (selectedCheckBox2!=null && selectedCheckBox2.length>0) {
	                			for (l=0; l<selectedCheckBox2.length; l++) {
	                				if(Common.get(o2[0]).equals(selectedCheckBox2[l])) {
	                					sb.append("■");
	                				}
	                			}
	                			
	                		}
	                		//沒被選取的印"□"
	                		if(!"■".equals(sb.substring(sb.length()-1))) {
	                			sb.append("□");
	                		}
	                		sb.append(o2[1]).append(" ");
	        			}
	        		}else{
	        			showType="2";
	        		}
	        		if("2".equals(showType)){
	        			if(!"".equals(otherDescribeValue)) {
		        		    sb.append("請描述：" ).append(otherDescribeValue).append("\n").append( "\n");
	        			} else {
	        				sb.append("請描述：" ).append("_____").append("\n").append( "\n");
	        			}
	        		}else{
	        			if(!"".equals(otherDescribeValue)) {
		        			sb.append(otherDescribeValue).append( "\n").append( "\n");
	        			} else {
		        			sb.append("_____").append( "\n").append( "\n");
	        			}
	        		}
	        	}    		
	    	}
	    	return sb.toString();
	} 
	//通報來源
	public String getNotifierSource(String NotifierSource) {
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRGNFS");
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(NotifierSource)) 
				{
					checkbox = "■" + obj.getCodeName() + "　";

				}
				else
				{
					checkbox = checkbox + obj.getCodeName() + "　";
				}
				
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
	//不良藥品劑型
	public String getMedModel(String NotifierSource,String MedModelOther) {
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRGFLN");
		if (list!=null && list.size()>0) 
		{
			int c = 0;
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if(obj.getCodeId().equals(NotifierSource)) 
				{
					checkbox = "■" + obj.getCodeName() + "　　";
					if("10".equals(NotifierSource)) {
						checkbox += MedModelOther;
					}
					
					if(obj.getCodeName().length() == 2) {
						checkbox += "　　　";
					}
					if (obj.getCodeName().length() == 3) {
						checkbox += "　　";
					}
					if((i+1)%3==0) {
						checkbox += "\n";
					}
					
				}
				else
				{
					checkbox =checkbox + obj.getCodeName() + "　　";
					if(obj.getCodeName().length() == 2) {
						checkbox += "　　　";
					}
					if (obj.getCodeName().length() == 3) {
						checkbox += "　　";
					}
					if((i+1)%3==0) {
						checkbox += "\n";
					}
				}
				
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
	//儲存環境
	public String getStorage(String Storage,String StorageOther) {
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRG0103");
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(Storage)) 
				{
					checkbox = "■" + obj.getCodeName() + "　";
					if("03".equals(obj.getCodeId())) {
						checkbox += "\n";
					}
					if("ZZ".equals(Storage)) {
						checkbox += StorageOther;
					}
				}
				else
				{
					checkbox = checkbox + obj.getCodeName() + "　";
					if("03".equals(obj.getCodeId())) {
						checkbox += "\n";
					}
					if("ZZ".equals(Storage)) {
						checkbox += StorageOther;
					}
				}
				
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
	//包裝形式
	public String getPackage(String Package,String MedPackageOther) {
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRG0102");
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(Package)) 
				{
					checkbox = "■" + obj.getCodeName() + "　";
					if("03".equals(obj.getCodeId())) {
						checkbox += "\n";
					}
					if("ZZ".equals(Package)) {
						checkbox += MedPackageOther;
					}
				}
				else
				{
					checkbox = checkbox + obj.getCodeName() + "　";
					if("03".equals(obj.getCodeId())) {
						checkbox += "\n";
					}
					if("ZZ".equals(Package)) {
						checkbox += MedPackageOther;
					}
				}
				
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
	
	//後續處理
	public String getDealWith(String DealWith) {
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRG0104");
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(DealWith)) 
				{
					checkbox = "■" + obj.getCodeName() + "　";
					if("03".equals(obj.getCodeId())) {
						checkbox += "\n";
					}
					if("04".equals(obj.getCodeId())) {
						checkbox += "　　";
					}
				}
				else
				{
					checkbox = checkbox + obj.getCodeName() + "　";
					if("03".equals(obj.getCodeId())) {
						checkbox += "\n";
					}
					if("04".equals(obj.getCodeId())) {
						checkbox += "　　";
					}
				}
				
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
	//不良品原因初評
	public String getFirstResult(String FirstResult,String FirstRemark) {
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRG0106");
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(FirstResult)) 
				{
					checkbox = "■" + obj.getCodeName() + "　";
					if("01".equals(obj.getCodeId())) {
						checkbox += "　　　";
					}
					if("03".equals(obj.getCodeId())) {
						checkbox += "\n";
					}
					if("ZZ".equals(FirstResult)) {
						checkbox += FirstRemark;
					}
				}
				else
				{
					checkbox = checkbox + obj.getCodeName() + "　";
					if("01".equals(obj.getCodeId())) {
						checkbox += "　　　";
					}
					if("03".equals(obj.getCodeId())) {
						checkbox += "\n";
					}
					if("ZZ".equals(FirstResult)) {
						checkbox += FirstRemark;
					}
				}
				
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
    
	@Override
	public Object doQueryOne() throws Exception {
		DRGEX0102F obj = this;
		obj.setIsEnabledUpdate("N");
		
		Drg5001Db c = (Drg5001Db) View.getObject(" from Drg5001Db where id=" + Common.getInt(obj.getId()));
		
		System.out.println("[TCBW]-[DRGEX0102F]-[doQueryOne] : " + obj.getId());
		
		if (c!=null) {
			obj.setApplNo(Common.get(c.getApplNo()));
			obj.setRevision(Common.get(c.getRevision()));
			obj.setStatus(Common.get(c.getStatus()));
			obj.setDrg0001Id(Common.get(c.getDrg0001Id()));
			
			if("00,01,02,22".indexOf(Common.get(obj.getStatus())) != -1){
				obj.setIsEnabledUpdate("Y");
			}
			
			//基本資料------------------------------------------------------------------		
			obj.setOccurDate(Common.get(c.getOccurDate()));		
			obj.setNotifierRepDate(Common.get(c.getNotifierRepDate()));		
			obj.setNotifierRevDate(Common.get(c.getNotifierRevDate()));		
			obj.setNotifierSource(Common.get(c.getNotifierSource()));		
			
			obj.setNotifierName(Common.get(c.getNotifierName()));
			obj.setNotifierUserID(Common.get(c.getNotifierUserID()));			
			obj.setNotifierDept(Common.get(c.getNotifierDept()));
			obj.setNotifierDeptID(Common.get(c.getNotifierDeptID()));
			obj.setNotifierTel(Common.get(c.getNotifierTel()));
			obj.setNotifierTelArea(Common.get(c.getNotifierTelArea()));	
			obj.setNotifierTelExt(Common.get(c.getNotifierTelExt()));	
			obj.setNotifierCounty(Common.get(c.getNotifierCounty()));	
			obj.setNotifierZipCode(Common.get(c.getNotifierZipCode()));	
			obj.setNotifierAddress(Common.get(c.getNotifierAddress()));		
			obj.setNotifierEmail(Common.get(c.getNotifierEmail()));		
			obj.setNotifierType(Common.get(c.getNotifierType()));
			obj.setNotifierTitle(Common.get(c.getNotifierTitle()));	

		
			//不良藥品資料--------------------------------------------------------------	
			obj.setPermitKey(Common.get(c.getPermitKey()));
			obj.setPermitNo(Common.get(c.getPermitNo()));
			obj.setChProduct(Common.get(c.getChProduct()));
			obj.setEnProduct(Common.get(c.getEnProduct()));
			obj.setIngredient(Common.get(c.getIngredient()));		
			obj.setContent(Common.get(c.getContent()));		
			obj.setMedModel(Common.get(c.getMedModel()));		
			obj.setMedModelOther(Common.get(c.getMedModelOther()));
			obj.setMedPackage(Common.get(c.getMedPackage()));		
			obj.setMedPackageOther(Common.get(c.getMedPackageOther()));
			obj.setApplicationID(Common.get(c.getApplicationID()));
			obj.setApplicationName(Common.get(c.getApplicationName()));		
			obj.setManufactorName(Common.get(c.getManufactorName()));		
			obj.setManufactorNo(Common.get(c.getManufactorNo()));
			obj.setManufactorCountry(Common.get(c.getManufactorCountry()));	
			obj.setManufactorDate(Common.get(c.getManufactorDate()));		
			obj.setExpirationDate(Common.get(c.getExpirationDate()));		
			obj.setStorage(Common.get(c.getStorage()));		
			obj.setStorageOther(Common.get(c.getStorageOther()));		
			obj.setIsFindYn(Common.get(c.getIsFindYn()));		
			obj.setIsSingleYn(Common.get(c.getIsSingleYn()));		
			obj.setSameNum(Common.get(c.getSameNum()));
			obj.setDiffNum(Common.get(c.getDiffNum()));		
			obj.setIsHarmYn(Common.get(c.getIsHarmYn()));
			obj.setIsUsedYn(Common.get(c.getIsUsedYn()));
			obj.setEvenContactYn(Common.get(c.getEvenContactYn()));		
			obj.setDealWith(Common.get(c.getDealWith()));		
			obj.setIsContactYn(Common.get(c.getIsContactYn()));
			obj.setDefectDesc(Common.get(c.getDefectDesc()));
			obj.setFirstResult(Common.get(c.getFirstResult()));		
			obj.setFirstRemark(Common.get(c.getFirstRemark()));
			
			String hql2 = " from Drg5002Db where 1=1 and drg5001Db.id="+Common.get(getId());
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql2+" order by id asc");
			String[] mainCode = new String[objList.size()];
	        String[] subCode = new String[50]; 
	        
			if(objList!=null && objList.size()>0)
			{
				java.util.Iterator it = objList.iterator();
				int i =0;
				int k =0;
				while (it.hasNext()) 
				{					
					Drg5002Db o = (Drg5002Db) it.next();
					
					mainCode[i] = String.valueOf( o.getMainCode() );
					String[] subList = o.getSubCode().split(",");
					for(int j=0; j<subList.length; j++){
						if(Common.get(subList[j]) != ""){
							subCode[k] = subList[j];
							k++;
						}
					}
					i++;
				}
			}
			obj.setMainCode(mainCode);
			obj.setSubCode(subCode);	
			
		}
		return obj;
	}
	
	public Object getOldRevisionData() throws Exception {
		DRGEX0102F obj = this;
		Drg5001Db c = (Drg5001Db)View.getObject(" from Drg5001Db where id = " + Common.getLong(getId()));
		if(c!=null){
			if(Common.getInt(c.getRevision())-1 >= 1){
				String revisionId = View.getLookupField("select id from Drg5001Db where applNo="+Common.sqlChar(c.getApplNo())+" and revision="+Common.sqlChar(Common.get(Common.getInt(c.getRevision())-1)));
				
				String hql2 = " from Drg5002Db where 1=1 and drg5001Db.id="+Common.get(revisionId);
				java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql2+" order by id asc");				
		        String[] oldSubCode = new String[50];		        
				if(objList!=null && objList.size()>0)
				{
					java.util.Iterator it = objList.iterator();
					int i =0;
					int k =0;
					while (it.hasNext()) 
					{					
						Drg5002Db o = (Drg5002Db) it.next();						
						String[] subList = o.getSubCode().split(",");
						for(int j=0; j<subList.length; j++){
							if(Common.get(subList[j]) != ""){
								oldSubCode[k] = subList[j];
								k++;
							}
						}
						i++;
					}
				}
				obj.setRevisionId(revisionId);
				obj.setOldSubCode(oldSubCode);
			}
		}
		return obj;
	}
	
	public Object doQueryAll() throws Exception {		
		return null;
	}

	public void doDelete() throws Exception {
		Drg5001Db obj = (Drg5001Db)View.getObject(" from Drg5001Db where id = " + Common.getLong(getId()));
		if(obj != null){
			deleteCon0001DbByDrgex0102(obj.getId(),"1");//刪除檔案上傳資料
			ServiceGetter.getInstance().getTcbwService().delete(obj);
		}		
	}
	
	public String deleteCon0001DbByDrgex0102(Long id,String type) throws Exception {
		if("1".equals(type)){ //刪除全部檔案
			String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG010001' and dbName='Drg5001Db' and upLoadId="+Common.getLong(id);		
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");		
			if(objList!=null && objList.size()>0)		
			{			
				java.util.Iterator it = objList.iterator();			
				while (it.hasNext())			
				{				
					Con0001Db o = (Con0001Db) it.next();				
					ServiceGetter.getInstance().getTcbwService().getConinDao().deleteCon0001Db(o.getId());			
				}		
			}
		}else{ //僅刪除新上傳檔案(主動補件取消)
			String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG010001' and dbName='Drg5001Db' and isInsert='Y' and upLoadId="+Common.getLong(id);			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");			
			if(objList!=null && objList.size()>0)			
			{				
				java.util.Iterator it = objList.iterator();				
				while (it.hasNext())				
				{					
					Con0001Db o = (Con0001Db) it.next();				
					ServiceGetter.getInstance().getTcbwService().getConinDao().deleteCon0001Db(o.getId());				
				}		
			}			
		}
		return null;
	}
	
	
	public String getAddFile() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus = View.getLookupField("select status from Drg5001Db where id="+Common.getLong(getId()));
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG010001' and dbName='Drg5001Db' and upLoadId="+Common.getLong(getId());

		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");
				sb.append("<a href='#' class='text_link_b' onClick=\"downLoadFile('DRG','").append(attFile).append("')\"\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");				
				sb.append("<td style='text-align:center'>");
				if("00,01,02".indexOf(Common.get(ststus)) != -1){			
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	
	public String getDrg0001Id() {return checkGet(drg0001Id);}
	public void setDrg0001Id(String drg0001Id) {this.drg0001Id = checkSet(drg0001Id);}
	public String getAutoUpdate() {return checkGet(autoUpdate);}
	public void setAutoUpdate(String autoUpdate) {this.autoUpdate = checkSet(autoUpdate);}
	public String getUpdateType() {		return checkGet(updateType);	}
	public void setUpdateType(String updateType) {		this.updateType = checkSet(updateType);	}
	
	public String getCaseType() {
		return checkGet(caseType);
	}
	public void setCaseType(String caseType) {
		this.caseType = checkSet(caseType);
	}

	public String getIsNeedBackQuery() {
		return checkGet(isNeedBackQuery);
	}
	public void setIsNeedBackQuery(String isNeedBackQuery) {
		this.isNeedBackQuery = checkSet(isNeedBackQuery);
	}
	
	public String getIsEnabledUpdate() {
		return checkGet(isEnabledUpdate);
	}
	public void setIsEnabledUpdate(String isEnabledUpdate) {
		this.isEnabledUpdate = checkSet(isEnabledUpdate);
	}
	
	public String getApplNo() {
		return checkGet(applNo);
	}
	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}
	public String getStatus() {
		return checkGet(status);
	}
	public void setStatus(String status) {
		this.status = checkSet(status);
	}
	public String getChargeMan() {
		return checkGet(chargeMan);
	}
	public void setChargeMan(String chargeMan) {
		this.chargeMan = checkSet(chargeMan);
	}
	public String getOccurDate() {
		return checkGet(occurDate);
	}
	public void setOccurDate(String occurDate) {
		this.occurDate = checkSet(occurDate);
	}
	public String getNotifierRevDate() {
		return checkGet(notifierRevDate);
	}
	public void setNotifierRevDate(String notifierRevDate) {
		this.notifierRevDate = checkSet(notifierRevDate);
	}
	public String getNotifierRepDate() {
		return checkGet(notifierRepDate);
	}
	public void setNotifierRepDate(String notifierRepDate) {
		this.notifierRepDate = checkSet(notifierRepDate);
	}	
	public String getNotifierSource() {
		return checkGet(notifierSource);
	}
	public void setNotifierSource(String notifierSource) {
		this.notifierSource = checkSet(notifierSource);
	}
	public String getNotifierName() {
		return checkGet(notifierName);
	}
	public void setNotifierName(String notifierName) {
		this.notifierName = checkSet(notifierName);
	}
	public String getNotifierDept() {
		return checkGet(notifierDept);
	}
	public void setNotifierDept(String notifierDept) {
		this.notifierDept = checkSet(notifierDept);
	}
	public String getNotifierTel() {
		return checkGet(notifierTel);
	}
	public void setNotifierTel(String notifierTel) {
		this.notifierTel = checkSet(notifierTel);
	}
	public String getNotifierAddress() {
		return checkGet(notifierAddress);
	}
	public void setNotifierAddress(String notifierAddress) {
		this.notifierAddress = checkSet(notifierAddress);
	}
	public String getNotifierEmail() {
		return checkGet(notifierEmail);
	}
	public void setNotifierEmail(String notifierEmail) {
		this.notifierEmail = checkSet(notifierEmail);
	}
	public String getNotifierType() {
		return checkGet(notifierType);
	}
	public void setNotifierType(String notifierType) {
		this.notifierType = checkSet(notifierType);
	}
	public String getNotifierTitle() {
		return checkGet(notifierTitle);
	}
	public void setNotifierTitle(String notifierTitle) {
		this.notifierTitle = checkSet(notifierTitle);
	}
	public String getPermitKey() {
		return checkGet(permitKey);
	}
	public void setPermitKey(String permitKey) {
		this.permitKey = checkSet(permitKey);
	}
	public String getPermitNo() {
		return checkGet(permitNo);
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = checkSet(permitNo);
	}
	public String getChProduct() {
		return checkGet(chProduct);
	}
	public void setChProduct(String chProduct) {
		this.chProduct = checkSet(chProduct);
	}
	public String getEnProduct() {
		return checkGet(enProduct);
	}
	public void setEnProduct(String enProduct) {
		this.enProduct = checkSet(enProduct);
	}
	public String getIngredient() {
		return checkGet(ingredient);
	}
	public void setIngredient(String ingredient) {
		this.ingredient = checkSet(ingredient);
	}
	public String getContent() {
		return checkGet(content);
	}
	public void setContent(String content) {
		this.content = checkSet(content);
	}
	public String getMedModel() {
		return checkGet(medModel);
	}
	public void setMedModel(String medModel) {
		this.medModel = checkSet(medModel);
	}
	public String getMedModelOther() {
		return checkGet(medModelOther);
	}
	public void setMedModelOther(String medModelOther) {
		this.medModelOther = checkSet(medModelOther);
	}
	public String getMedPackage() {
		return checkGet(medPackage);
	}
	public void setMedPackage(String medPackage) {
		this.medPackage = checkSet(medPackage);
	}
	public String getMedPackageOther() {
		return checkGet(medPackageOther);
	}
	public void setMedPackageOther(String medPackageOther) {
		this.medPackageOther = checkSet(medPackageOther);
	}	
	public String getApplicationID() {
		return checkGet(applicationID);
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = checkSet(applicationID);
	}
	public String getApplicationName() {
		return checkGet(applicationName);
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = checkSet(applicationName);
	}
	public String getManufactorName() {
		return checkGet(manufactorName);
	}
	public void setManufactorName(String manufactorName) {
		this.manufactorName = checkSet(manufactorName);
	}
	public String getManufactorNo() {
		return checkGet(manufactorNo);
	}
	public void setManufactorNo(String manufactorNo) {
		this.manufactorNo = checkSet(manufactorNo);
	}
	public String getManufactorDate() {
		return checkGet(manufactorDate);
	}
	public void setManufactorDate(String manufactorDate) {
		this.manufactorDate = checkSet(manufactorDate);
	}
	public String getExpirationDate() {
		return checkGet(expirationDate);
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = checkSet(expirationDate);
	}
	public String getStorage() {
		return checkGet(storage);
	}
	public void setStorage(String storage) {
		this.storage = checkSet(storage);
	}
	public String getStorageOther() {
		return checkGet(storageOther);
	}
	public void setStorageOther(String storageOther) {
		this.storageOther = checkSet(storageOther);
	}
	public String getIsFindYn() {
		return checkGet(isFindYn);
	}
	public void setIsFindYn(String isFindYn) {
		this.isFindYn = checkSet(isFindYn);
	}
	public String getIsSingleYn() {
		return checkGet(isSingleYn);
	}
	public void setIsSingleYn(String isSingleYn) {
		this.isSingleYn = checkSet(isSingleYn);
	}
	public String getSameNum() {
		return checkGet(sameNum);
	}
	public void setSameNum(String sameNum) {
		this.sameNum = checkSet(sameNum);
	}
	public String getDiffNum() {
		return checkGet(diffNum);
	}
	public void setDiffNum(String diffNum) {
		this.diffNum = checkSet(diffNum);
	}
	public String getIsHarmYn() {
		return checkGet(isHarmYn);
	}
	public void setIsHarmYn(String isHarmYn) {
		this.isHarmYn = checkSet(isHarmYn);
	}
	public String getEvenContactYn() {
		return checkGet(evenContactYn);
	}
	public void setEvenContactYn(String evenContactYn) {
		this.evenContactYn = checkSet(evenContactYn);
	}
	public String getDealWith() {
		return checkGet(dealWith);
	}
	public void setDealWith(String dealWith) {
		this.dealWith = checkSet(dealWith);
	}
	public String getIsContactYn() {
		return checkGet(isContactYn);
	}
	public void setIsContactYn(String isContactYn) {
		this.isContactYn = checkSet(isContactYn);
	}
	public String getFirstResult() {
		return checkGet(firstResult);
	}
	public void setFirstResult(String firstResult) {
		this.firstResult = checkSet(firstResult);
	}
	public String getFirstRemark() {
		return checkGet(firstRemark);
	}
	public void setFirstRemark(String firstRemark) {
		this.firstRemark = checkSet(firstRemark);
	}
	public String[] getMainCode() {
		return mainCode;
	}
	public void setMainCode(String[] mainCode) {
		this.mainCode = mainCode;
	}
	public String[] getSubCode() {
		return subCode;
	}
	public void setSubCode(String[] subCode) {
		this.subCode = subCode;
	}
	public String[] getOtherDescribe() {
		return otherDescribe;
	}
	public void setOtherDescribe(String[] otherDescribe) {
		this.otherDescribe = otherDescribe;
	}
	public String getNotifierUserID() {
		return checkGet(notifierUserID);
	}
	public void setNotifierUserID(String notifierUserID) {
		this.notifierUserID = checkSet(notifierUserID);
	}
	public String getNotifierDeptID() {
		return checkGet(notifierDeptID);
	}
	public void setNotifierDeptID(String notifierDeptID) {
		this.notifierDeptID = checkSet(notifierDeptID);
	}
	public String getNotifierTelArea() {
		return checkGet(notifierTelArea);
	}
	public void setNotifierTelArea(String notifierTelArea) {
		this.notifierTelArea = checkSet(notifierTelArea);
	}
	public String getNotifierTelExt() {
		return checkGet(notifierTelExt);
	}
	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt = checkSet(notifierTelExt);
	}
	public String getNotifierCounty() {
		return checkGet(notifierCounty);
	}
	public void setNotifierCounty(String notifierCounty) {
		this.notifierCounty = checkSet(notifierCounty);
	}
	public String getNotifierZipCode() {
		return checkGet(notifierZipCode);
	}
	public void setNotifierZipCode(String notifierZipCode) {
		this.notifierZipCode = checkSet(notifierZipCode);
	}
	public String getManufactorCountry() {
		return checkGet(manufactorCountry);
	}
	public void setManufactorCountry(String manufactorCountry) {
		this.manufactorCountry = checkSet(manufactorCountry);
	}
	public String getRevisionId() {
		return checkGet(revisionId);
	}
	public void setRevisionId(String revisionId) {
		this.revisionId = checkSet(revisionId);
	}
	public String[] getOldSubCode() {
		return oldSubCode;
	}
	public void setOldSubCode(String[] oldSubCode) {
		this.oldSubCode = oldSubCode;
	}
	public String getRevision() {
		return checkGet(revision);
	}
	public void setRevision(String revision) {
		this.revision = checkSet(revision);
	}
	public String getIsUsedYn() {
		return checkGet(isUsedYn);
	}
	public void setIsUsedYn(String isUsedYn) {
		this.isUsedYn = checkSet(isUsedYn);
	}
	public String getDefectDesc() {
		return checkGet(defectDesc);
	}
	public void setDefectDesc(String defectDesc) {
		this.defectDesc = checkSet(defectDesc);
	}
	
}
