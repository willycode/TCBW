package com.kangdainfo.tcbw.view.drgin;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg0004Db;
import com.kangdainfo.tcbw.model.bo.Drg0005Db;
import com.kangdainfo.tcbw.model.bo.Drg0006Db;
import com.kangdainfo.tcbw.model.bo.Drg0007Db;
import com.kangdainfo.tcbw.model.bo.Drg0008Db;
import com.kangdainfo.tcbw.model.bo.Drg0009Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGIN0102F extends SuperBean{
	
	private String id;//序號	NUMERIC(19,0)
	private String isComplete ;
	private String doType;	
	private String caseType ;
	private String isNeedBackQuery;	
	private String isEnabledUpdate;
	private String updateType;//接收前端按鈕狀態	
	private String applNo;	//案件號碼	VARCHAR(11)
	private String status;	//案件狀態	VARCHAR(2)
	private String chargeMan;	//作業人員	VARCHAR(50)	
	private String tabId ;
	
	//基本資料------------------------------------------------------------------
	private String occurDate;	//發生日期	VARCHAR(7)
	private String notifier;
	private String notifierRevDate;	//通報者接獲日期	VARCHAR(7)
	private String notifierRepDate;	//通報中心接獲通報日期	VARCHAR(7)
	private String enrolledDate;    //收案日期	VARCHAR(7)
	private String notifierSource;	//通報來源	VARCHAR(2)
	private String notifierName;	//通報者姓名	NVARCHAR(20)
	private String notifierUserID; //通報者ID  VARCHAR(50)
	private String notifierDeptID;	//通報者服務機構ID	VARCHAR(20)
	private String notifierDept;	//通報者服務機構	NVARCHAR(50)
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
	private String isUsedYn;	//是否為病人使用後發現不良品，向醫療人員反應 VARCHAR(1)
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
	private String commonUser;
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	//案件分級(drg0106)--------------------------------------------------------
	private String drg03id; //初評資料檔ID
	private String assessDate;	//初評日期	VARCHAR(7)
	private String assessMan;	//初評人員	VARCHAR(50)
	private String firstResult1;	//不良品風險評估結果	VARCHAR(2) 名稱與登錄相同+1區隔..
	private String notifierAging;	//通報時效	VARCHAR(1)
	private String notifierQuality;	//通報品質	VARCHAR(1)
	private String intervalDays;	//間隔天數	VARCHAR(3)
	private String remark;	//備註	NVARCHAR(100)	
	private String hisData1; //歷年本藥品之通報件數
	private String hisData2; //歷年本藥品同此次瑕疵之通報件數
	private String hisData3; //一年內本藥品之通報件數
	private String hisData4; //一年內本藥品同此次瑕疵之通報件數
	private String hisData5; //一年內本藥品同此次瑕疵之高風險通報件數
	private String hisData6; //一年內本藥品同此次瑕疵案件
	private String hisData7; //一年內本藥品同此次瑕疵之高風險案件	
	private String hisApplNoY; //一年內本藥品同此次瑕疵案件之同批號的各案件編號
	private String hisApplNoN; //一年內本藥品同此次瑕疵案件之不同批號的各案件編號
	
	//分級確認(drg0109)---------------------------------------------------------
	private String drgLevType ;	
	private String gradeMan;	//分級確認人員	VARCHAR(50)
	private String gradeDate;	//分級確認日期	VARCHAR(7)
	private String drgLev;	//風險等級	VARCHAR(1)	
	private String fileData; //夾檔資訊(不存入資料庫)	
	private String[] fds;

	//案件評估(drg0112)---------------------------------------------------------
	private String lotNo05;	//批號	VARCHAR(50)
	private String replyDate05;	//回覆日期	VARCHAR(7)
	private String beforeOrLater05;	//前次CAPA執行前或執行後製造	VARCHAR(1)
	private String capaDate05; //前次CAPA執行日期	
	private String analyDate06;	//分析日期	VARCHAR(7)
	private String medicineType06;	//學名藥/原廠藥	VARCHAR(1)
	private String produceType06;	//國產/輸入	VARCHAR(1)
	private String lotType06;	//批號範圍	VARCHAR(1)
	private String defect06;	//不良品缺陷	VARCHAR(2)
	private String defectOther06;	//不良品缺陷(其他)	NVARCHAR(50)
	private String survey06;	//調查結果	VARCHAR(2)
	private String surveyOther06;	//調查結果(其他)	NVARCHAR(50)
	private String precaution06;	//預防措施	VARCHAR(2)
	private String precautionOther06;	//預防措施(其他)	NVARCHAR(50)	
	private String drg07id;
	private String checkResult07;	//清查結果	NVARCHAR(100)
	private String survey07;	//調查結果	NVARCHAR(100)
	private String precaution07;	//預防矯正措施及改善時程	NVARCHAR(100)
	private String isPostYn08;	//是否發文	VARCHAR(1)	
	private String reason08;	//不發文理由	NVARCHAR(50)
	private String fdaPostNo08;	//FDA發文字號	VARCHAR(20)
	private String delayDate08;	//展延日期	VARCHAR(7)
	private String payDate08;	//廠商交付CAPA日期	VARCHAR(7)
	private String drgLev08;	//風險等級       VARCHAR(2)
	private String capaDownDate08;  //CAPA執行完成日期  VARCHAR(7)
	private String drgReason08;	//本案原由       NVARCHAR(1000)
	private String checkResult08;	//清查結果	NVARCHAR(100)
	private String survey08;	//調查結果	NVARCHAR(100)
	private String precaution08;	//預防矯正措施及改善時程	NVARCHAR(100)
	private String[] dealWith08;	//擬辦事項	VARCHAR(2)
	private String assessResult08;	//評估結果	NVARCHAR(100)
	private String addDocDate08;	//補件日期	VARCHAR(7)
	private String reAssessMan08;     //再評估人員	VARCHAR(50)
	private String reAssessDate08;    //再評估日期 	VARCHAR(7)	
	private String correctionReason;  //重新校正理由 (僅存於流程敘述)
	
	//案件分析(drg0114)-------------------------------------------------------
	private String medicineType09;	//學名藥/原廠藥	VARCHAR(2)
	private String produceType09;	//國產/輸入	VARCHAR(2)
	private String lotType09;	//批號範圍	VARCHAR(2)
	private String defect09;	//不良品缺陷	VARCHAR(2)
	private String defectOther09;	//不良品缺陷(說明)	NVARCHAR(50)
	private String survey09;	//調查結果	VARCHAR(2)
	private String surveyOther09;	//調查結果(說明)	NVARCHAR(50)
	private String precaution09;	//預防措施	VARCHAR(2)
	private String precautionOther09;	//預防措施(說明)	NVARCHAR(50)

	
	//查詢條件------------------------------------------------------------------
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
	private String q_isTrans;  	        //資料狀態(1:僅顯示歷史資料;2:僅顯示登錄資料)

	public String getAddFile(String systemType) throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		String ststus = View.getLookupField("select status from Drg0001Db where id="+Common.getLong(getId()));
		String hql = " from Con0001Db where fileKind='DRG' and dbName='Drg0001Db' and upLoadId="+Common.getLong(getId());
		if("1".equals(systemType))
		    hql += " and systemType like 'DRG01%'";  //附件上傳
		else if ("2".equals(systemType))
			hql += " and systemType = 'DRG010002'";  //案件評估
        
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
				sb.append("<td  style='text-align:left'>");
				sb.append("<a href='#' class='text_link_b' onClick=\"downLoadFile('DRG','").append(attFile).append("')\"\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
				
				sb.append("<td style='text-align:center'>");
				if( "1".equals(systemType) || 
						("2".equals(systemType) && "41,42".indexOf(Common.get(ststus)) != -1)){		
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</td>\n");
			
				sb.append("</tr>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}	
	
	public void doAcceptedDrg0102() throws Exception
	{
		DRGIN0102F obj = this;
		Drg0001Db c = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(getId()));
		if(c != null){	
			ServiceGetter.getInstance().getTcbwService().getDrginDao().acceptedByDrgIN0102F(c,getUserID());
		}
		obj.setApplNo(c.getApplNo());
	}
	
	
	public void doUpdateDrg0102() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().updateByDrgIN0102F(this);
	}
	
	public void doBackPiecesDrg0102() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().backPiecesDrgByIN0102F(this);
	}
	
	public void doDismissalDrg0102() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().dismissalByDrgIN0102F(this);
	}
	
	public void doDeleteCon0003Db() throws Exception
	{
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(getId()));
		if(obj != null){		
			ServiceGetter.getInstance().getTcbwService().getDrginDao().doDeleteCon0003Db(obj);	
		}		
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
        		otherDescribeValue = View.getLookupField(" select otherDescribe from Drg0002Db where drg0001Db.id="+Common.getLong(id) +" and mainCode="+Common.sqlChar(Common.get(o[0])));
        		sb.append(o[1]).append("：");
        		sb.append("<input style=\"display:none\" class=\"" ).append( "field" ).append( "\" type=\"checkbox\" name=\"" ).append( mainCode ).append( "\" value=\"" ).append( o[0] ).append( "\"");
        		if (selectedCheckBox1!=null && selectedCheckBox1.length>0) {
        			for (j=0; j<selectedCheckBox1.length; j++) {
        				if(Common.get(o[0]).equals(selectedCheckBox1[j])) sb.append(" checked");
        			}
        		}
        		sb.append(">");
        		//sb.append("  onClick=\"updateSubCode(this);\" >").append(o[1]+"：").append(" ").append("<br>");
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
                		//若勾選"顏色異常"，需提示"若為雙/三腔軟袋於操作前發現顏色異常，請詳加描述隔膜及外袋之完整性"
        				if("0101".equals(Common.get(o2[0]))){
        					sb.append(" onclick=\"alertAbnormalColor(this);\"");
        				}        					
        				if (selectedCheckBox2!=null && selectedCheckBox2.length>0) {
                			for (l=0; l<selectedCheckBox2.length; l++) {
                				if(Common.get(o2[0]).equals(selectedCheckBox2[l])) sb.append("checked");
                			}
                		}
                		sb.append("  onClick=\"updateMainCode(this);\" ");
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
        		    sb.append("id=\"otherDescribe").append(o[0]).append("\"").append("value=\"" ).append(otherDescribeValue).append("\"").append("onChange=\"updateMainCode2(this);\"").append("><br>");
        		}else{
        			sb.append("<input class=\"" ).append( className ).append( "\" type=\"").append(otherDescribeType).append( "\" name=\"" ).append( otherDescribe ).append( "\"");
        			sb.append("id=\"otherDescribe").append(o[0]).append("\"").append("value=\"" ).append(otherDescribeValue).append("\"").append("onChange=\"updateSubCodeZZ(this);\"").append("><br>");
        		}
        	}    		
    	}
        return sb.toString();    
    } 
	
	
	public Object doQueryOneDrg0102() throws Exception {
		DRGIN0102F obj = this;
		obj.setIsEnabledUpdate("Y");
		
		Drg0001Db c = (Drg0001Db) View.getObject(" from Drg0001Db where id=" + Common.getInt(obj.getId()));
		
		System.out.println("[TCBW]-[DRGIN0102F]-[doQueryOne] : " + obj.getId());
		
		if (c!=null) {
			obj.setApplNo(Common.get(c.getApplNo()));
			obj.setStatus(Common.get(c.getStatus()));
			
			//基本資料------------------------------------------------------------------	
			obj.setNotifier(Common.get(obj.getNotifier()));
			obj.setOccurDate(Common.get(c.getOccurDate()));		
			obj.setNotifierRepDate(Common.get(c.getNotifierRepDate()));		
			obj.setNotifierRevDate(Common.get(c.getNotifierRevDate()));
			obj.setEnrolledDate(Common.get(c.getEnrolledDate()));
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
			obj.setApplNo(Common.get(c.getApplNo()));
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
			
			String hql2 = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.get(getId());
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql2+" order by id asc");
			String[] mainCode = new String[objList.size()];
	        String[] subCode = new String[100]; 
	        
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
		
		//案件分級(drg0106)------------------------------------------------------
		String drg03hql ="";
		if("".equals(obj.getDrg03id())){		
			drg03hql = " from Drg0003Db where applNo="+Common.sqlChar(obj.getApplNo())+" order by id desc";
		}else{
			drg03hql = " from Drg0003Db where id="+ Common.getInt(obj.getDrg03id());
		}
		Drg0003Db drg03 = (Drg0003Db) View.getObject(drg03hql);
		System.out.println("[TCBW]-[DRGIN0102F]-[doQueryOne_drg03] : " + drg03hql);
		if (drg03!=null) {
		     obj.setApplNo(Common.get(drg03.getApplNo()));
		     obj.setAssessMan(Common.get(drg03.getAssessMan()));
		     obj.setAssessDate(Common.get(drg03.getAssessDate()));
		     obj.setFirstResult1(Common.get(drg03.getFirstResult()));
		     obj.setNotifierAging(Common.get(drg03.getNotifierAging()));
		     obj.setNotifierQuality(Common.get(drg03.getNotifierQuality()));
		     obj.setIntervalDays(Common.get(drg03.getIntervalDays()));
		     obj.setRemark(Common.get(drg03.getRemark()));
             
		     String sql = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(obj.getId());				
		     java.util.List objList = ServiceGetter.getInstance().getCommonService().load(sql+" order by id desc");				
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
		     }
		     if(subCodeList.length() > 3) subCodeList = subCodeList.substring(0, subCodeList.length()-3);
		     
		     //歷年本藥品之通報件數
		     String hql1 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
		                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg03.getApplNo())+") ";
		     int hisData1 = ServiceGetter.getInstance().getCommonService().loadCount(hql1);
		     obj.setHisData1(Common.get(hisData1));
           
		     //歷年本藥品同此次瑕疵之通報件數
		     if(subCodeList!=null&&!"".equals(subCodeList)){
			     String hql2 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
			                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg03.getApplNo())+") "+
			                   " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
			     int hisData2 = ServiceGetter.getInstance().getCommonService().loadCount(hql2);
			     obj.setHisData2(Common.get(hisData2));
		     }
		     
		     //一年內本藥品之通報件數
		     String hql3 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
		                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg03.getApplNo())+") "+
		                   " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()));
            int hisData3 = ServiceGetter.getInstance().getCommonService().loadCount(hql3);
            obj.setHisData3(Common.get(hisData3));
		     
            
            //一年內本藥品同此次瑕疵之通報件數
            if(subCodeList!=null&&!"".equals(subCodeList)){
			     String hql4 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
			                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg03.getApplNo())+") "+
			                   " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
			                   " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
	             int hisData4 = ServiceGetter.getInstance().getCommonService().loadCount(hql4);
	             obj.setHisData4(Common.get(hisData4));
            }
            
            //一年內本藥品同此次瑕疵之高風險通報件數
            if(subCodeList!=null&&!"".equals(subCodeList)){
			     String hql5 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
			                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg03.getApplNo())+") "+
			                   " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))"+
	                           " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
	                           " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
	             int hisData5 = ServiceGetter.getInstance().getCommonService().loadCount(hql5);
	             obj.setHisData5(Common.get(hisData5));
            }
            
            //一年內本藥品同此次瑕疵案件
            String hql6 = " select manufactorNo,count(applNo) from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
                          " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg03.getApplNo())+") "+
                          " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
                          " group by manufactorNo";
            java.util.List hql6List = ServiceGetter.getInstance().getCommonService().load(hql6);
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
		     if(subCodeList!=null&&!"".equals(subCodeList)){
	             String hql7 = " select manufactorNo,count(applNo) from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                           " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg03.getApplNo())+") "+
	                           " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
	                           " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))"+
	                           " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))"+
	                           " group by manufactorNo"; 
	             java.util.List hql7List = ServiceGetter.getInstance().getCommonService().load(hql7);
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
		     }
		     
            
    		 //取得風險等級
    		 String drgLev = View.getLookupField("select drgLev from Drg0004Db where applNo="+Common.sqlChar(obj.getApplNo()));
            //B級案件
            if("04".equals(drgLev)){         	
            	if(subCodeList!=null&&!"".equals(subCodeList)){
	             	//一年內本藥品同此次瑕疵案件「同批號的各案件編號、不同批號的各案件編號」      
	             	String sql8 = " select manufactorNo,applNo from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+ 
	             	              " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(drg03.getApplNo())+") "+
	             	              " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+                   
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
            }            
		}
		
		//分級確認(drg0109)------------------------------------------------------------------
		String drg04hql = " from Drg0004Db where 1=1 ";
		if(null != obj.getApplNo() && !"".equals(obj.getApplNo())){
			drg04hql += "and applNo="+Common.sqlChar(obj.getApplNo());	
		}
		if(null != obj.getId() && !"".equals(obj.getId())){
			drg04hql += " and applNo in (select applNo from Drg0001Db where id="+Common.sqlChar(obj.getId())+")";	
		}
		Drg0004Db drg04 = (Drg0004Db) View.getObject(drg04hql);

		System.out.println("[TCBW]-[DRGIN0102F]-[doQueryOne_drg04] : " + drg04hql);
		
		if (drg04!=null) {		    
			obj.setApplNo(Common.get(drg04.getApplNo()));
		    obj.setDrgLev(Common.get(drg04.getDrgLev()));
		    obj.setGradeDate(Common.get(drg04.getGradeDate()));
		}
		
		//案件評估(drg0112)------------------------------------------------------------------
		String drg05hql = " from Drg0005Db where applNo="+Common.sqlChar(obj.getApplNo());	
		Drg0005Db drg05 = (Drg0005Db) View.getObject(drg05hql);
		System.out.println("[TCBW]-[DRGIN0102F]-[doQueryOne_drg05] : " + drg05hql);		
		if (drg05!=null) {
			obj.setLotNo05(Common.get(drg05.getLotNo()));
			obj.setReplyDate05(Common.get(drg05.getReplyDate()));
			obj.setBeforeOrLater05(Common.get(drg05.getBeforeOrLater()));
			obj.setCapaDate05(Common.get(drg05.getCapaDate()));
		}
		String drg06hql = " from Drg0006Db where applNo="+Common.sqlChar(obj.getApplNo());	
		Drg0006Db drg06 = (Drg0006Db) View.getObject(drg06hql);
		System.out.println("[TCBW]-[DRGIN0102F]-[doQueryOne_drg06] : " + drg06hql);
		if (drg06!=null) {
			obj.setMedicineType06(Common.get(drg06.getMedicineType())); //學名藥/原廠藥
			obj.setProduceType06(Common.get(drg06.getProduceType())); //國產/輸入
			obj.setLotType06(Common.get(drg06.getLotType())); //批號範圍
			obj.setDefect06(Common.get(drg06.getDefect())); //不良品缺陷
			obj.setDefectOther06(Common.get(drg06.getDefectOther())); //不良品缺陷(其他)
			obj.setSurvey06(Common.get(drg06.getSurvey())); //調查結果
			obj.setSurveyOther06(Common.get(drg06.getSurveyOther())); //調查結果(其他)
			obj.setPrecaution06(Common.get(drg06.getPrecaution())); //預防措施
			obj.setPrecautionOther06(Common.get(drg06.getPrecautionOther())); //預防措施(其他)			
		}
		String drg07hql ="";
		if("".equals(obj.getDrg07id())){		
			drg07hql = " from Drg0007Db where applNo="+Common.sqlChar(obj.getApplNo())+" order by id desc";
		}else{
			drg07hql = " from Drg0007Db where id="+ Common.getInt(obj.getDrg07id());
		}
		Drg0007Db drg07 = (Drg0007Db) View.getObject(drg07hql);
		System.out.println("[TCBW]-[DRGIN0102F]-[doQueryOne_drg07] : " + drg07hql);		
		if (drg07!=null) {
			obj.setCheckResult07(Common.get(drg07.getCheckResult())); //清查結果
			obj.setSurvey07(Common.get(drg07.getSurvey())); //調查結果
			obj.setPrecaution07(Common.get(drg07.getPrecaution())); //預防矯正措施及改善時程
		}
		String drg08hql = " from Drg0008Db where applNo="+Common.sqlChar(obj.getApplNo());	
		Drg0008Db drg08 = (Drg0008Db) View.getObject(drg08hql);
		System.out.println("[TCBW]-[DRGIN0102F]-[doQueryOne_drg08] : " + drg08hql);		
		if (drg08!=null) {		    
			obj.setApplNo(Common.get(drg08.getApplNo()));
			obj.setIsPostYn08(Common.get(drg08.getIsPostYn()));
			obj.setReason08(Common.get(drg08.getReason()));
			obj.setFdaPostNo08(Common.get(drg08.getFdaPostNo()));
			obj.setDelayDate08(Common.get(drg08.getDelayDate()));
			obj.setPayDate08(Common.get(drg08.getPayDate()));
			obj.setDrgLev08(Common.get(drg08.getDrgLev()));
			obj.setCapaDownDate08(Common.get(drg08.getCapaDownDate()));
			obj.setDrgReason08(Common.get(drg08.getDrgReason()));
			obj.setCheckResult08(!"".equals(Common.get(drg08.getCheckResult()))?Common.get(drg08.getCheckResult()):Common.get(drg07.getCheckResult()));
			obj.setSurvey08(!"".equals(Common.get(drg08.getSurvey()))?Common.get(drg08.getSurvey()):Common.get(drg07.getSurvey()));
			obj.setPrecaution08(!"".equals(Common.get(drg08.getPrecaution()))?Common.get(drg08.getPrecaution()):Common.get(drg07.getPrecaution()));
			String[] dealWith08 = null;
			if(drg08.getDealWith() != null && drg08.getDealWith().length()>0){				
				String[] dealWithList = drg08.getDealWith().split(",");
				dealWith08 = new String[dealWithList.length];
				for(int j=0; j< dealWithList.length; j++){
					if(Common.get(dealWithList[j]) != ""){
						dealWith08[j] = dealWithList[j];
					}
				}
			}
			obj.setDealWith08(dealWith08);
			obj.setAssessResult08(Common.get(drg08.getAssessResult()));
			obj.setAddDocDate08(Common.get(drg08.getAddDocDate()));
			obj.setReAssessDate08(Common.get(drg08.getReAssessDate()));
			obj.setReAssessMan08(Common.get(drg08.getReAssessMan()));
		}
		
		//案件分析(drg0114)--------------------------------------------------------------
		String drg09hql = " from Drg0009Db where applNo="+Common.sqlChar(obj.getApplNo());	
		Drg0009Db drg09 = (Drg0009Db) View.getObject(drg09hql);
		System.out.println("[TCBW]-[DRGIN0102F]-[doQueryOne_drg09] : " + drg09hql);		
		if (drg09!=null) {
			obj.setMedicineType09(Common.get(drg09.getMedicineType())); //學名藥/原廠藥
			obj.setProduceType09(Common.get(drg09.getProduceType())); //國產/輸入
			obj.setLotType09(Common.get(drg09.getLotType())); //批號範圍
			obj.setDefect09(Common.get(drg09.getDefect())); //不良品缺陷
			obj.setDefectOther09(Common.get(drg09.getDefectOther())); //不良品缺陷(其他)
			obj.setSurvey09(Common.get(drg09.getSurvey())); //調查結果
			obj.setSurveyOther09(Common.get(drg09.getSurveyOther())); //調查結果(其他)
			obj.setPrecaution09(Common.get(drg09.getPrecaution())); //預防措施
			obj.setPrecautionOther09(Common.get(drg09.getPrecautionOther())); //預防措施(其他)
		}
		
		this.setState("queryOneSuccess");
		return obj;
	}

	
	public void doDeleteDrg0102() throws Exception {
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
		}		
	}
	
	//檢查是否有分派權限
	public  String competenceDrg0102() throws Exception
	{
		
	  String hql="  from Con1015Db ";
             hql+=" where con1014Db.code = "+ Common.sqlChar("01");   //審核
             hql+=" and   con1014Db.con1007Db.formCode="+Common.sqlChar("DRG01");
             hql+=" and   competence like "+TCBWCommon.likeSqlChar("4");
             hql+=" and   commonUser.userId="+Common.sqlChar(getUserID());
    
        Con1015Db  c =(Con1015Db)View.getObject(hql);
             
        if(c!=null)
        {
        	return "Y";
        }	
        else
        {
        	return null;
        }	
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

	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}	
	public String getIsComplete() {return checkGet(isComplete);}
	public void setIsComplete(String isComplete) {this.isComplete = checkSet(isComplete);}	
	public String getDoType() {return checkGet(doType);}
	public void setDoType(String doType) {this.doType = checkSet(doType);}
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}	
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}	
	
	public String getUpdateType() {return checkGet(updateType);}
	public void setUpdateType(String updateType) {this.updateType = checkSet(updateType);}	
	public String getIsNeedBackQuery() {return checkGet(isNeedBackQuery);}
	public void setIsNeedBackQuery(String isNeedBackQuery) {this.isNeedBackQuery = checkSet(isNeedBackQuery);}	
	public String getIsEnabledUpdate() {return checkGet(isEnabledUpdate);}
	public void setIsEnabledUpdate(String isEnabledUpdate) {this.isEnabledUpdate = checkSet(isEnabledUpdate);}	
	public String getApplNo() {
		return checkGet(applNo);
	}
	public void setApplNo(String applNo) {	this.applNo = checkSet(applNo);	}
	public String getStatus() {	return checkGet(status);	}
	public void setStatus(String status) {	this.status = checkSet(status);	}
	public String getChargeMan() {	return checkGet(chargeMan);	}
	public void setChargeMan(String chargeMan) {	this.chargeMan = checkSet(chargeMan);	}
	public String getNotifierUserID() {	return checkGet(notifierUserID);	}
	public void setNotifierUserID(String notifierUserID) {	this.notifierUserID = checkSet(notifierUserID);	}
	public String getOccurDate() {	return checkGet(occurDate);	}
	public void setOccurDate(String occurDate) {	this.occurDate = checkSet(occurDate);	}
	public String getNotifierRevDate() {	return checkGet(notifierRevDate);	}
	public void setNotifierRevDate(String notifierRevDate) {	this.notifierRevDate = checkSet(notifierRevDate);	}
	public String getNotifierRepDate() {	return checkGet(notifierRepDate);	}
	public void setNotifierRepDate(String notifierRepDate) {	this.notifierRepDate = checkSet(notifierRepDate);	}	
	public String getNotifierSource() {	return checkGet(notifierSource);	}
	public void setNotifierSource(String notifierSource) {	this.notifierSource = checkSet(notifierSource);	}
	public String getNotifierName() {	return checkGet(notifierName);	}
	public void setNotifierName(String notifierName) {	this.notifierName = checkSet(notifierName);	}
	public String getNotifierDept() {	return checkGet(notifierDept);	}
	public void setNotifierDept(String notifierDept) {	this.notifierDept = checkSet(notifierDept);	}
	public String getNotifierTel() {	return checkGet(notifierTel);	}
	public void setNotifierTel(String notifierTel) {	this.notifierTel = checkSet(notifierTel);	}
	public String getNotifierAddress() {	return checkGet(notifierAddress);	}
	public void setNotifierAddress(String notifierAddress) {	this.notifierAddress = checkSet(notifierAddress);	}
	public String getNotifierEmail() {	return checkGet(notifierEmail);	}
	public void setNotifierEmail(String notifierEmail) {	this.notifierEmail = checkSet(notifierEmail);	}
	public String getNotifierType() {	return checkGet(notifierType);	}
	public void setNotifierType(String notifierType) {	this.notifierType = checkSet(notifierType);	}
	public String getNotifierTitle() {	return checkGet(notifierTitle);	}
	public void setNotifierTitle(String notifierTitle) {	this.notifierTitle = checkSet(notifierTitle);	}
	public String getPermitKey() {	return checkGet(permitKey);	}
	public void setPermitKey(String permitKey) {	this.permitKey = checkSet(permitKey);	}
	public String getPermitNo() {	return checkGet(permitNo);	}
	public void setPermitNo(String permitNo) {	this.permitNo = checkSet(permitNo);	}
	public String getChProduct() {	return checkGet(chProduct);	}
	public void setChProduct(String chProduct) {	this.chProduct = checkSet(chProduct);	}
	public String getEnProduct() {	return checkGet(enProduct);	}
	public void setEnProduct(String enProduct) {	this.enProduct = checkSet(enProduct);	}
	public String getIngredient() {	return checkGet(ingredient);	}
	public void setIngredient(String ingredient) {	this.ingredient = checkSet(ingredient);	}
	public String getContent() {	return checkGet(content);	}
	public void setContent(String content) {	this.content = checkSet(content);	}
	public String getMedModel() {	return checkGet(medModel);	}
	public void setMedModel(String medModel) {	this.medModel = checkSet(medModel);	}
	public String getMedModelOther() {	return checkGet(medModelOther);	}
	public void setMedModelOther(String medModelOther) {	this.medModelOther = checkSet(medModelOther);	}
	public String getMedPackage() {	return checkGet(medPackage);	}
	public void setMedPackage(String medPackage) {	this.medPackage = checkSet(medPackage);	}
	public String getMedPackageOther() {	return checkGet(medPackageOther);	}
	public void setMedPackageOther(String medPackageOther) {	this.medPackageOther = checkSet(medPackageOther);	}	
	public String getApplicationID() {	return checkGet(applicationID);	}
	public void setApplicationID(String applicationID) {	this.applicationID = checkSet(applicationID);	}
	public String getApplicationName() {	return checkGet(applicationName);	}
	public void setApplicationName(String applicationName) {	this.applicationName = checkSet(applicationName);	}
	public String getManufactorName() {	return checkGet(manufactorName);	}
	public void setManufactorName(String manufactorName) {	this.manufactorName = checkSet(manufactorName);	}
	public String getManufactorNo() {	return checkGet(manufactorNo);	}
	public void setManufactorNo(String manufactorNo) {	this.manufactorNo = checkSet(manufactorNo);	}
	public String getManufactorDate() {	return checkGet(manufactorDate);	}
	public void setManufactorDate(String manufactorDate) {	this.manufactorDate = checkSet(manufactorDate);	}
	public String getExpirationDate() {	return checkGet(expirationDate);	}
	public void setExpirationDate(String expirationDate) {	this.expirationDate = checkSet(expirationDate);	}
	public String getStorage() {	return checkGet(storage);	}
	public void setStorage(String storage) {	this.storage = checkSet(storage);	}
	public String getStorageOther() {	return checkGet(storageOther);	}
	public void setStorageOther(String storageOther) {	this.storageOther = checkSet(storageOther);	}
	public String getIsFindYn() {	return checkGet(isFindYn);	}
	public void setIsFindYn(String isFindYn) {	this.isFindYn = checkSet(isFindYn);	}
	public String getIsSingleYn() {	return checkGet(isSingleYn);	}	
	public void setIsSingleYn(String isSingleYn) {	this.isSingleYn = checkSet(isSingleYn);	}
	public String getSameNum() {	return checkGet(sameNum);	}
	public void setSameNum(String sameNum) {	this.sameNum = checkSet(sameNum);	}
	public String getDiffNum() {	return checkGet(diffNum);	}
	public void setDiffNum(String diffNum) {	this.diffNum = checkSet(diffNum);	}
	public String getIsHarmYn() {	return checkGet(isHarmYn);	}
	public void setIsHarmYn(String isHarmYn) {	this.isHarmYn = checkSet(isHarmYn);	}
	public String getEvenContactYn() {	return checkGet(evenContactYn);	}
	public void setEvenContactYn(String evenContactYn) {	this.evenContactYn = checkSet(evenContactYn);	}
	public String getDealWith() {	return checkGet(dealWith);	}
	public void setDealWith(String dealWith) {	this.dealWith = checkSet(dealWith);	}
	public String getIsContactYn() {	return checkGet(isContactYn);	}
	public void setIsContactYn(String isContactYn) {	this.isContactYn = checkSet(isContactYn);	}
	public String getFirstResult() {	return checkGet(firstResult);	}
	public void setFirstResult(String firstResult) {	this.firstResult = checkSet(firstResult);	}
	public String getFirstRemark() {	return checkGet(firstRemark);	}
	public void setFirstRemark(String firstRemark) {	this.firstRemark = checkSet(firstRemark);	}		
	public String[] getMainCode() {	return mainCode;	}
	public void setMainCode(String[] mainCode) {	this.mainCode = mainCode;	}
	public String[] getSubCode() {	return subCode;	}
	public void setSubCode(String[] subCode) {	this.subCode = subCode;	}
	public String[] getOtherDescribe() {	return otherDescribe;	}
	public void setOtherDescribe(String[] otherDescribe) {	this.otherDescribe = otherDescribe;	}
	public String getNotifierDeptID() {	return checkGet(notifierDeptID);	}
	public void setNotifierDeptID(String notifierDeptID) {	this.notifierDeptID = checkSet(notifierDeptID);	}
	public String getNotifierTelArea() {	return checkGet(notifierTelArea);	}
	public void setNotifierTelArea(String notifierTelArea) {	this.notifierTelArea = checkSet(notifierTelArea);	}
	public String getNotifierTelExt() {	return checkGet(notifierTelExt);	}
	public void setNotifierTelExt(String notifierTelExt) {	this.notifierTelExt = checkSet(notifierTelExt);	}
	public String getNotifierCounty() {	return checkGet(notifierCounty);	}
	public void setNotifierCounty(String notifierCounty) {	this.notifierCounty = checkSet(notifierCounty);	}
	public String getNotifierZipCode() {	return checkGet(notifierZipCode);	}
	public void setNotifierZipCode(String notifierZipCode) {	this.notifierZipCode = checkSet(notifierZipCode);	}
	public String getManufactorCountry() {	return checkGet(manufactorCountry);	}
	public void setManufactorCountry(String manufactorCountry) {	this.manufactorCountry = checkSet(manufactorCountry);	}
	public String getCommonUser() {	return checkGet(commonUser);	}
	public void setCommonUser(String commonUser) {	this.commonUser = checkSet(commonUser);	}
	public String getIsUsedYn() {	return checkGet(isUsedYn);	}
	public void setIsUsedYn(String isUsedYn) {	this.isUsedYn = checkSet(isUsedYn);	}
	public String getDefectDesc() {	return checkGet(defectDesc);	}
	public void setDefectDesc(String defectDesc) {	this.defectDesc = checkSet(defectDesc);	}
	public String getNotifier() {	return checkGet(notifier);	}
	public void setNotifier(String notifier) {	this.notifier = checkSet(notifier);	}
	public String getEnrolledDate() {	return checkGet(enrolledDate);	}
	public void setEnrolledDate(String enrolledDate) {	this.enrolledDate = checkSet(enrolledDate);	}
	
	public String getDrg03id() {return checkGet(drg03id);}
	public void setDrg03id(String drg03id) {this.drg03id = checkSet(drg03id);}
	public String getAssessDate() {return checkGet(assessDate);}
	public void setAssessDate(String assessDate) {this.assessDate = checkSet(assessDate);}
	public String getAssessMan() {return checkGet(assessMan);}
	public void setAssessMan(String assessMan) {this.assessMan = checkSet(assessMan);}
	public String getFirstResult1() {return checkGet(firstResult1);}
	public void setFirstResult1(String firstResult1) {this.firstResult1 = checkSet(firstResult1);}
	public String getNotifierAging() {return checkGet(notifierAging);}
	public void setNotifierAging(String notifierAging) {this.notifierAging = checkSet(notifierAging);}
	public String getNotifierQuality() {return checkGet(notifierQuality);}
	public void setNotifierQuality(String notifierQuality) {this.notifierQuality = checkSet(notifierQuality);}
	public String getIntervalDays() {return checkGet(intervalDays);}
	public void setIntervalDays(String intervalDays) {this.intervalDays = checkSet(intervalDays);}
	public String getRemark() {return checkGet(remark);}
	public void setRemark(String remark) {this.remark = checkSet(remark);}

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
	public String getHisApplNoY() {return checkGet(hisApplNoY);}
	public void setHisApplNoY(String hisApplNoY) {this.hisApplNoY = checkSet(hisApplNoY);}
	public String getHisApplNoN() {return checkGet(hisApplNoN);}
	public void setHisApplNoN(String hisApplNoN) {this.hisApplNoN = checkSet(hisApplNoN);}
	
	public String getDrgLevType() {return checkGet(drgLevType);}
	public void setDrgLevType(String drgLevType) {this.drgLevType = checkSet(drgLevType);}
	public String getGradeMan() {return checkGet(gradeMan);}
	public void setGradeMan(String gradeMan) {this.gradeMan = checkSet(gradeMan);}
	public String getGradeDate() {return checkGet(gradeDate);}
	public void setGradeDate(String gradeDate) {this.gradeDate = checkSet(gradeDate);}
	public String getDrgLev() {return checkGet(drgLev);}
	public void setDrgLev(String drgLev) {this.drgLev = checkSet(drgLev);}	
	public String getFileData() {return checkGet(fileData);}
	public void setFileData(String fileData) {this.fileData = checkSet(fileData);}
	public String[] getFds(){	return fds;		}
	public void setFds(String[] s){ 	fds = s;	}
	
	public String getIsPostYn08() {return checkGet(isPostYn08);}
	public void setIsPostYn08(String isPostYn08) {this.isPostYn08 = checkSet(isPostYn08);}
	public String getReason08() {return checkGet(reason08);}
	public void setReason08(String reason08) {this.reason08 = checkSet(reason08);}
	public String getFdaPostNo08() {return checkGet(fdaPostNo08);}
	public void setFdaPostNo08(String fdaPostNo08) {this.fdaPostNo08 = checkSet(fdaPostNo08);}
	public String getDelayDate08() {return checkGet(delayDate08);}
	public void setDelayDate08(String delayDate08) {this.delayDate08 = checkSet(delayDate08);}
	public String getPayDate08() {return checkGet(payDate08);}
	public void setPayDate08(String payDate08) {this.payDate08 = checkSet(payDate08);}
	public String getCheckResult08() {return checkGet(checkResult08);}
	public void setCheckResult08(String checkResult08) {this.checkResult08 = checkSet(checkResult08);}
	public String getSurvey08() {return checkGet(survey08);}
	public void setSurvey08(String survey08) {this.survey08 = checkSet(survey08);}
	public String getPrecaution08() {return checkGet(precaution08);}
	public void setPrecaution08(String precaution08) {this.precaution08 = checkSet(precaution08);}
	public String[] getDealWith08() {	return dealWith08;	}
	public void setDealWith08(String[] dealWith08) {	this.dealWith08 = dealWith08;	}
	public String getAssessResult08() {return checkGet(assessResult08);}
	public void setAssessResult08(String assessResult08) {this.assessResult08 = checkSet(assessResult08);}
	public String getAddDocDate08() {return checkGet(addDocDate08);}
	public void setAddDocDate08(String addDocDate08) {this.addDocDate08 = checkSet(addDocDate08);}	
	public String getDrgLev08() {return checkGet(drgLev08);}
	public void setDrgLev08(String drgLev08) {this.drgLev08 = checkSet(drgLev08);}	
	public String getCapaDownDate08() {	return checkGet(capaDownDate08);	}
	public void setCapaDownDate08(String capaDownDate08) {	this.capaDownDate08 = checkSet(capaDownDate08);	}
	public String getDrgReason08() {return checkGet(drgReason08);}
	public void setDrgReason08(String drgReason08) {this.drgReason08 = checkSet(drgReason08);}
	public String getReAssessMan08() {	return checkGet(reAssessMan08);	}
	public void setReAssessMan08(String reAssessMan08) {	this.reAssessMan08 = checkSet(reAssessMan08);	}
	public String getReAssessDate08() {	return checkGet(reAssessDate08);	}
	public void setReAssessDate08(String reAssessDate08) {	this.reAssessDate08 = checkSet(reAssessDate08);	}

	public String getLotNo05() {return checkGet(lotNo05);}
	public void setLotNo05(String lotNo05) {this.lotNo05 = checkSet(lotNo05);}
	public String getReplyDate05() {return checkGet(replyDate05);}
	public void setReplyDate05(String replyDate05) {this.replyDate05 = checkSet(replyDate05);}
	public String getBeforeOrLater05() {return checkGet(beforeOrLater05);}
	public void setBeforeOrLater05(String beforeOrLater05) {this.beforeOrLater05 = checkSet(beforeOrLater05);}
	public String getCapaDate05() {return checkGet(capaDate05);}
	public void setCapaDate05(String capaDate05) {this.capaDate05 = checkSet(capaDate05);}
	
	public String getAnalyDate06() {return checkGet(analyDate06);}
	public void setAnalyDate06(String analyDate06) {this.analyDate06 = checkSet(analyDate06);}
	public String getMedicineType06() {return checkGet(medicineType06);}
	public void setMedicineType06(String medicineType06) {this.medicineType06 = checkSet(medicineType06);}
	public String getProduceType06() {return checkGet(produceType06);}
	public void setProduceType06(String produceType06) {this.produceType06 = checkSet(produceType06);}
	public String getLotType06() {return checkGet(lotType06);}
	public void setLotType06(String lotType06) {this.lotType06 = checkSet(lotType06);}
	public String getDefect06() {return checkGet(defect06);}
	public void setDefect06(String defect06) {this.defect06 = checkSet(defect06);}
	public String getDefectOther06() {return checkGet(defectOther06);}
	public void setDefectOther06(String defectOther06) {this.defectOther06 = checkSet(defectOther06);}
	public String getSurvey06() {return checkGet(survey06);}
	public void setSurvey06(String survey06) {this.survey06 = checkSet(survey06);}
	public String getSurveyOther06() {return checkGet(surveyOther06);}
	public void setSurveyOther06(String surveyOther06) {this.surveyOther06 = checkSet(surveyOther06);}
	public String getPrecaution06() {return checkGet(precaution06);}
	public void setPrecaution06(String precaution06) {this.precaution06 = checkSet(precaution06);}
	public String getPrecautionOther06() {return checkGet(precautionOther06);}
	public void setPrecautionOther06(String precautionOther06) {this.precautionOther06 = checkSet(precautionOther06);}	
	public String getDrg07id() {return checkGet(drg07id);}
	public void setDrg07id(String drg07id) {this.drg07id = checkSet(drg07id);}
	public String getCheckResult07() {return checkGet(checkResult07);}
	public void setCheckResult07(String checkResult07) {this.checkResult07 = checkSet(checkResult07);}
	public String getSurvey07() {return checkGet(survey07);}
	public void setSurvey07(String survey07) {this.survey07 = checkSet(survey07);}
	public String getPrecaution07() {return checkGet(precaution07);}
	public void setPrecaution07(String precaution07) {this.precaution07 = checkSet(precaution07);}
	public String getCorrectionReason() { return checkGet(correctionReason);	}
	public void setCorrectionReason(String correctionReason) {	this.correctionReason = checkSet(correctionReason);	}
	
	public String getMedicineType09() {return checkGet(medicineType09);}
	public void setMedicineType09(String medicineType09) {this.medicineType09 = checkSet(medicineType09);}
	public String getProduceType09() {return checkGet(produceType09);}
	public void setProduceType09(String produceType09) {this.produceType09 = checkSet(produceType09);}
	public String getLotType09() {return checkGet(lotType09);}
	public void setLotType09(String lotType09) {this.lotType09 = checkSet(lotType09);}
	public String getDefect09() {return checkGet(defect09);}
	public void setDefect09(String defect09) {this.defect09 = checkSet(defect09);}
	public String getDefectOther09() {return checkGet(defectOther09);}
	public void setDefectOther09(String defectOther09) {this.defectOther09 = checkSet(defectOther09);}
	public String getSurvey09() {return checkGet(survey09);}
	public void setSurvey09(String survey09) {this.survey09 = checkSet(survey09);}
	public String getSurveyOther09() {return checkGet(surveyOther09);}
	public void setSurveyOther09(String surveyOther09) {this.surveyOther09 = checkSet(surveyOther09);}
	public String getPrecaution09() {return checkGet(precaution09);}
	public void setPrecaution09(String precaution09) {this.precaution09 = checkSet(precaution09);}
	public String getPrecautionOther09() {return checkGet(precautionOther09);}
	public void setPrecautionOther09(String precautionOther09) {this.precautionOther09 = checkSet(precautionOther09);}
	
	public String getQ_id() {	return checkGet(q_id);	}
	public void setQ_id(String q_id) {	this.q_id = checkSet(q_id);	}
	public String getQ_applNoS() {	return checkGet(q_applNoS);	}
	public void setQ_applNoS(String q_applNoS) {	this.q_applNoS = checkSet(q_applNoS);	}	
	public String getQ_applNoE() {	return checkGet(q_applNoE);	}
	public void setQ_applNoE(String q_applNoE) {	this.q_applNoE = checkSet(q_applNoE);	}
	public String getQ_occurDateS() {	return checkGet(q_occurDateS);	}
	public void setQ_occurDateS(String q_occurDateS) {	this.q_occurDateS = checkSet(q_occurDateS);	}	
	public String getQ_occurDateE() {	return checkGet(q_occurDateE);	}
	public void setQ_occurDateE(String q_occurDateE) {	this.q_occurDateE = checkSet(q_occurDateE);	}
	public String getQ_enrolledDateS() {	return checkGet(q_enrolledDateS);	}
	public void setQ_enrolledDateS(String q_enrolledDateS) {	this.q_enrolledDateS = checkSet(q_enrolledDateS);	}
	public String getQ_enrolledDateE() {	return checkGet(q_enrolledDateE);	}
	public void setQ_enrolledDateE(String q_enrolledDateE) {	this.q_enrolledDateE = checkSet(q_enrolledDateE);	}
	public String getQ_notifierRepDateS() {	return checkGet(q_notifierRepDateS);	}
	public void setQ_notifierRepDateS(String q_notifierRepDateS) {	this.q_notifierRepDateS = checkSet(q_notifierRepDateS);	}	
	public String getQ_notifierRepDateE() {	return checkGet(q_notifierRepDateE);	}
	public void setQ_notifierRepDateE(String q_notifierRepDateE) {	this.q_notifierRepDateE = checkSet(q_notifierRepDateE);	}
	public String getQ_notifierSource() {	return checkGet(q_notifierSource);	}
	public void setQ_notifierSource(String q_notifierSource) {	this.q_notifierSource = checkSet(q_notifierSource);	}
	public String getQ_notifierType() {	return checkGet(q_notifierType);	}
	public void setQ_notifierType(String qNotifierType) {	q_notifierType = checkSet(qNotifierType);	}
	public String getQ_permitNo() {	return checkGet(q_permitNo);	}
	public void setQ_permitNo(String q_permitNo) {	this.q_permitNo = checkSet(q_permitNo);	}
	public String getQ_chProduct() {	return checkGet(q_chProduct);	}
	public void setQ_chProduct(String q_chProduct) {	this.q_chProduct = checkSet(q_chProduct);	}
	public String getQ_ingredient() {	return checkGet(q_ingredient);	}
	public void setQ_ingredient(String q_ingredient) {	this.q_ingredient = checkSet(q_ingredient);	}
	public String getQ_applicationName() {	return checkGet(q_applicationName);	}
	public void setQ_applicationName(String q_applicationName) {	this.q_applicationName = checkSet(q_applicationName);	}
	public String getQ_manufactorName() {	return checkGet(q_manufactorName);	}
	public void setQ_manufactorName(String q_manufactorName) {	this.q_manufactorName = checkSet(q_manufactorName);	}
	public String getQ_status() {	return checkGet(q_status);	}
	public void setQ_status(String q_status) {	this.q_status = checkSet(q_status);	}
	public String[] getQ_drgLev() {	return q_drgLev;	}
	public void setQ_drgLev(String[] q_drgLev) {	this.q_drgLev = q_drgLev;	}	
	public String getQ_permitKey() {	return checkGet(q_permitKey);	}
	public void setQ_permitKey(String qPermitKey) {	q_permitKey = checkSet(qPermitKey);	}	
	public String getQ_notifierDept() {	return checkGet(q_notifierDept);	}
	public void setQ_notifierDept(String qNotifierDept) {	q_notifierDept = checkSet(qNotifierDept);	}	
	public String[] getQ_mainCode() {	return q_mainCode;	}
	public void setQ_mainCode(String[] qMainCode) {	q_mainCode = qMainCode;	}
	public String[] getQ_subCode() {	return q_subCode;	}
	public void setQ_subCode(String[] qSubCode) {	q_subCode = qSubCode;	}	
	public String getQ_isTrans() {	return checkGet(q_isTrans);	}
	public void setQ_isTrans(String q_isTrans) {	this.q_isTrans = checkSet(q_isTrans);	}	
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
