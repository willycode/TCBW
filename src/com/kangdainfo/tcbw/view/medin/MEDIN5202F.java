package com.kangdainfo.tcbw.view.medin;



import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.model.bo.Med2002Db;
import com.kangdainfo.tcbw.model.bo.Med2003Db;
import com.kangdainfo.tcbw.model.bo.Med2004Db;
import com.kangdainfo.tcbw.model.bo.Med2005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDIN5202F extends MEDIN5101F
{
	private String usernameid; 
	private String commonUser;
	
	private String id;
	private String status;
	
	private String isCloseUserInfo;		//是否遮蔽個資
	
	private String changeTabValue;
	
	//受理
	public void doAccepted() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin2Dao().updateByMedIN5202F(this);
		
		Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where mailID='MED030001'");	
		
		java.util.List<javax.mail.internet.InternetAddress> mailList = new java.util.ArrayList<javax.mail.internet.InternetAddress>();
		
		String mailBody="",title="",mail="";
		
		if(c!=null)
		{
			mailBody=c.getMailBody();
			
			title=c.getTitle();
			
			Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(getId()));
			
			if(obj!=null)
			{
				title=title.replace("field1",obj.getApplNo());
				mail=obj.getNotifierEamil();
				mailBody=mailBody.replace("field1", Datetime.getYYYMMDD());
				mailBody=mailBody.replace("field2", "1");
				mailBody=mailBody.replace("field3", obj.getApplNo());
				
			
			    String[] mailAddress = mail.split(",");
			
			    if(mailAddress!=null && mailAddress.length>0)
			    {
				  for(String s : mailAddress)
				  {
					javax.mail.internet.InternetAddress p = new javax.mail.internet.InternetAddress();
				    p.setAddress(s);
				    mailList.add(p);
				  }
			    }
			   ServiceGetter.getInstance().getTcbwService().sendEmail(title,mailBody, mailList, null, true,null, null, null);
			   TCBWCommon.setMailbackup("MED03",Common.get(obj.getId()),title,mailBody,obj.getApplNo(),"20",obj.getWorkers(),"");	
		    }	
		}

	}
	
	//退件
	public void doBackPieces() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin2Dao().updateByBackPiecesMedIN5202F(this);
	}
	
	//撤案
	public void doDismissal() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin2Dao().updateByDismissalMedIN5202F(this);
	}
	
	//校正
	public void doCorrection() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin2Dao().updateByCorrectionMedIN5202F(this);
	}
	
	//補件 - 寄發補件通知
	public void doAddocuments() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin2Dao().updateByAddocumentsMedIN5202F(this);
	}
	
	public void doDeleteCon0003Db() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin2Dao().updateByDeleteCon0003DbMedIN5202F(this);	
	}
	
	//檢查是否有分派權限
	public  String competence() throws Exception
	{
		
	  String hql="  from Con1015Db ";
             hql+=" where con1014Db.code = "+ Common.sqlChar("01");   
             hql+=" and   con1014Db.con1007Db.formCode="+Common.sqlChar("MED03");
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
	
	
	
	
	
	
	public DefaultTableModel getDefaultTableModel() throws Exception
	{		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[] 
    	        {"id","applNo","status","occurDate","notifierRevDate","notifierRepDate","notifierName",
    			"notifierTitleDept","notifierAreaCode","notifierTel","notifierAddress","notifierEamil",
    			"notifierType","caseSource","reportKind","testName","fdaNum","fdaOptions","approvedUnits",
    			"firmTestNo","patientId","patientSex","patientBirth","patientAge","patientWeight",
    			"patientHeigth","badReactionResults","medTestMedical","medType","medFactory","medSupplier",
    			"medModel","medNo","medLotNum","medManufactureDate","medOperator","medPurchaseDate",
    			"medUseDate","medUseReason","medUseIsYn","medOnceUseMed","medStopMedMitigate",
    			"onceSameReaction","sameTimeUse","medSea","procedureSea","noticeSponsor","noticeSponsorWritten",
    			"noticeIRB","noticeIRBWritten","noticeApprovedUnits","noticeApprovedUnitsWritten","isAdverseEvents",
    			"obj1","obj2","otherDesc","obj3","obj4"
    			};		
    	java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();

		String hql = "from Med2001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());

		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				Med2001Db obj = (Med2001Db) list.get(i);
				Object rowArray[]=new Object[cols.length];
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.formatYYYMMDD(obj.getOccurDate(),2); 
				rowArray[4]=Common.formatYYYMMDD(obj.getNotifierRevDate(),2); 
				rowArray[5]=Common.formatYYYMMDD(obj.getNotifierRepDate(),2);
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierName())))) {
					rowArray[6]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[6]=Common.get(obj.getNotifierName());
				} else {
					rowArray[6]=Common.get(obj.getNotifierName());
				}
				
				//rowArray[7]=Common.get(obj.getNotifierTitleDept());
				rowArray[7]="";//通報者服務機構??
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierAreaCode())))) {
					rowArray[8]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[8]=Common.get(obj.getNotifierAreaCode());
				} else {
					rowArray[8]=Common.get(obj.getNotifierAreaCode());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierTel())))) {
					rowArray[9]="●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[9]=Common.get(obj.getNotifierTel());
				} else {
					rowArray[9]=Common.get(obj.getNotifierTel());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierAddress())))) {
					rowArray[10]="●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[10]=Common.get(obj.getNotifierAddress());
				} else {
					rowArray[10]=Common.get(obj.getNotifierAddress());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierEamil())))) {
					rowArray[11]="●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[11]=Common.get(obj.getNotifierEamil());
				} else {
					rowArray[11]=Common.get(obj.getNotifierEamil());
				}
				

				if("1".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="■醫療人員  職稱：" + Common.get(obj.getNotifierTitle()) + "\n　　　□廠商";
				} else if("2".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="□醫療人員  職稱：_______\n　　　■廠商";
				} else {
					rowArray[12]="□醫療人員  職稱：_______\n　　　□廠商";
				}
				if("out".equals(Common.get(obj.getCaseSource()))) {
					rowArray[13]="■ 國外," + Common.get(obj.getCaseSourceOutCountry()) + "(國家)\n" + "□ 國內，試驗醫院：________\n　　　　 試驗醫師：________";
				} else if("in".equals(Common.get(obj.getCaseSource()))) {
					rowArray[13]="□ 國外，________(國家)\n" + "■ 國內，試驗醫院：" + Common.get(obj.getCaseSourceInHospital()) + "\n" + "　　　　 試驗醫師：" + Common.get(obj.getCaseSourceInDoctor());  
				}
				
				if("1".equals(Common.get(obj.getReportKind()))) {
					rowArray[14]="■ " + "初始報告\n" + "□ 追蹤報告，第______次";
				} else if("2".equals(Common.get(obj.getReportKind()))) {
					rowArray[14]="□ " + "初始報告\n" + "■ 追蹤報告，第" + Common.get(obj.getTrackingNum()) + "次";
				} else {
					rowArray[14]="□ " + "初始報告\n" + "□ 追蹤報告，第______次";
				}
				rowArray[15]=Common.get(obj.getTestName());
				rowArray[16]=Common.get(obj.getFdaNum());
				
				if("1".equals(Common.get(obj.getFdaOptions()))) {
					rowArray[17]="■查驗登記用　□學術研究用";
				} else if("2".equals(Common.get(obj.getFdaOptions()))) {
					rowArray[17]="□查驗登記用　■學術研究用";
				} else {
					rowArray[17]="□查驗登記用　□學術研究用";
				}
				
				if("1".equals(Common.get(obj.getApprovedUnits()))) {
					rowArray[18]="■ 醫事司　□ 食品藥物管理署　□ 其他：________";
				} else if("2".equals(Common.get(obj.getApprovedUnits()))) {
					rowArray[18]="□ 醫事司　■ 食品藥物管理署　□ 其他：________";
				} else if("3".equals(Common.get(obj.getApprovedUnits()))) {
					rowArray[18]="□ 醫事司　□ 食品藥物管理署　■ 其他：" + Common.get(obj.getApprovedUnitsOther());
				} else {
					rowArray[18]="□ 醫事司　□ 食品藥物管理署　□ 其他：________";
				}
				
				rowArray[19]=Common.get(obj.getFirmTestNo());
				rowArray[20]=Common.get(obj.getPatientId());
				if("M".equals(Common.get(obj.getPatientSex()))) {
					rowArray[21]="■ 男　□女";
				}else if("F".equals(Common.get(obj.getPatientSex()))) {
					rowArray[21]="□ 男　■女";
				}else {
					rowArray[21]="□ 男　□女";
				}
				rowArray[22]=Common.get(obj.getPatientBirth());
				rowArray[23]=Common.get(obj.getPatientAge());
				rowArray[24]=Common.get(obj.getPatientWeight());
				rowArray[25]=Common.get(obj.getPatientHeigth());
				String DeathDateAndReason = "，日期：" + Common.formatYYYMMDD(obj.getBadReactionResultsDeathDate(),2) + "，死亡原因：" + Common.get(obj.getBadReactionResultsDeathReason()); //死亡日期與原因
				String BadReactionResultsOther = Common.get(obj.getBadReactionResultsOther());
				rowArray[26]=getCommonCodeKindOfBadReactionResults(Common.get(obj.getBadReactionResults()),DeathDateAndReason,BadReactionResultsOther);
				rowArray[27]=Common.get(obj.getMedTestMedical());
				rowArray[28]=Common.get(obj.getMedType());
				rowArray[29]=Common.get(obj.getMedFactory());
				rowArray[30]=Common.get(obj.getMedSupplier());
				rowArray[31]=Common.get(obj.getMedModel());
				rowArray[32]=Common.get(obj.getMedNo());
				rowArray[33]=Common.get(obj.getMedLotNum());
				rowArray[34]=Common.formatYYYMMDD(obj.getMedManufactureDate(),2);
			
				rowArray[35]=getMedOperator(Common.get(obj.getMedOperator()));

				rowArray[36]=Common.formatYYYMMDD(obj.getMedUseDate(),2);
				rowArray[37]=Common.formatYYYMMDD(obj.getMedStopDate(),2);
				rowArray[38]=Common.get(obj.getMedUseReason());
				if("Y".equals(Common.get(obj.getMedUseIsYn()))) {
					rowArray[39]="■ 是 取得來源" + Common.get(obj.getMedYesSoruce()) + "\n□ 否" + "　□ 已於____年____月____日 退還給廠商";
				} else if("N".equals(Common.get(obj.getMedUseIsYn()))) {
					rowArray[39]="□ 是 取得來源____________\n" + "■ 否" + "　□ 已於____年____月____日 退還給廠商";
				} else if("O".equals(Common.get(obj.getMedUseIsYn()))) {
					rowArray[39]="□ 是 取得來源____________\n" + "□ 否" + "　■ 已於" + Common.formatYYYMMDD(obj.getMedNoReturnDate()) + " 退還給廠商";
				} else {
					rowArray[39]="□ 是 取得來源____________\n" + "□ 否" + "　□ 已於____年____月____日 退還給廠商";

				}
				
				if("Y".equals(Common.get(obj.getMedOnceUseMed()))) {
					rowArray[40]="■ 是　醫材：" + Common.get(obj.getMedOnceUseMedName()) + "不良反應：" + Common.get(obj.getMedOnceUseBadReaction()) + "　□ 否 □ 無法得知";
				} else if("N".equals(Common.get(obj.getMedOnceUseMed()))) {
					rowArray[40]="□ 是　醫材： ________不良反應：________" + "　■ 否 □ 無法得知";
				} else if("O".equals(Common.get(obj.getMedOnceUseMed()))) {
					rowArray[40]="□ 是　醫材： ________不良反應：________" + "　□ 否 ■ 無法得知";
				} else {
					rowArray[40]="□ 是　醫材： ________不良反應：________" + "　□ 否 □ 無法得知";
				}
				
				rowArray[41]=getMedTFO(Common.get(obj.getMedStopMedMitigate()));			
				rowArray[42]=getMedTFO(Common.get(obj.getOnceSameReaction()));
				rowArray[43]=getSameTimeUse(Common.get(obj.getSameTimeUse()),Common.get(obj.getSameTimeUseOther()));
				
				if("1".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="■確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("2".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) ■很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("3".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) ■可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("4".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) ■不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("5".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n■不相關(unrelated) □無法評估(unknow)";
				} else if("6".equals(Common.get(obj.getMedSea()))) {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) ■無法評估(unknow)";
				}
				else {
					rowArray[44]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				}
				
				if("1".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="■確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("2".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) ■很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("3".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) ■可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("4".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) ■不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				} else if("5".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n■不相關(unrelated) □無法評估(unknow)";
				} else if("6".equals(Common.get(obj.getProcedureSea()))) {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) ■無法評估(unknow)";
				}
				else {
					rowArray[45]="□確定相關(certain) □很可能相關(probable/likely) □可能相關(possible) □不太可能相關(unlikely)\n□不相關(unrelated) □無法評估(unknow)";
				}
				
				if("Y".equals(Common.get(obj.getNoticeSponsor()))) {
					rowArray[46]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeSponsor()))) {
					rowArray[46]="□ 是　■　否";
				} else {
					rowArray[46]="□ 是　□　否";
				}

				if("Y".equals(Common.get(obj.getNoticeSponsorWritten()))) {
					rowArray[47]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeSponsorWritten()))) {
					rowArray[47]="□ 是　■　否";
				} else {
					rowArray[47]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getNoticeIRB()))) {
					rowArray[48]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeIRB()))) {
					rowArray[48]="□ 是　■　否";
				} else {
					rowArray[48]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getNoticeIRBWritten()))) {
					rowArray[49]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeIRBWritten()))) {
					rowArray[49]="□ 是　■　否";
				} else {
					rowArray[49]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getNoticeApprovedUnits()))) {
					rowArray[50]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeApprovedUnits()))) {
					rowArray[50]="□ 是　■　否";
				} else {
					rowArray[50]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getNoticeApprovedUnitsWritten()))) {
					rowArray[51]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getNoticeApprovedUnitsWritten()))) {
					rowArray[51]="□ 是　■　否";
				} else {
					rowArray[51]="□ 是　□　否";
				}
				
				if("Y".equals(Common.get(obj.getIsAdverseEvents()))) {
					rowArray[52]="■是　□否";
				} else if("N".equals(Common.get(obj.getIsAdverseEvents()))) {
					rowArray[52]="□是　■否";
				}else {
					rowArray[52]="□是　□否";
				}
				
				if(obj.getMed2002Dbs()!=null && obj.getMed2002Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed2002Dbs().iterator();
					rowArray[53] = new JRTableModelDataSource(getSubModel01(it2));
				} else {
					rowArray[53]=null;
				}
				
				if(obj.getMed2003Dbs()!=null && obj.getMed2003Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed2003Dbs().iterator();
					rowArray[54] = new JRTableModelDataSource(getSubModel02(it2));
				} else {
					rowArray[54]=null;
				}
				
				rowArray[55]=Common.get(obj.getOtherDesc());
				
				if(obj.getMed2004Dbs()!=null && obj.getMed2004Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed2004Dbs().iterator();
					
					rowArray[56] = new JRTableModelDataSource(getSubModel03(it2));
				}
				
				if(obj.getMed2005Dbs()!=null && obj.getMed2005Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed2005Dbs().iterator();
					rowArray[57] = new JRTableModelDataSource(getSubModel04(it2));
					
				}
				arrList.add(rowArray);
			}
		}
		if (arrList!=null && arrList.size()>0) {
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		
		return model;
		
	}
	
	//設定子報表路徑
	 public void setParameter(java.util.HashMap<String, Object> params)
		{
			//傳給報表的查詢條件參數,在此設定
			String subreport0FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN5101r_subreport0.jasper");
			String subreport1FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN5101r_subreport1.jasper");
			String subreport2FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN5101r_subreport2.jasper");
			String subreport3FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN5101r_subreport3.jasper");
	
			params.put("subreport0", subreport0FilePath);			
			params.put("subreport1", subreport1FilePath);	
			params.put("subreport2", subreport2FilePath);			
			params.put("subreport3", subreport3FilePath);
		}
	
	//子報表-不良事件描述資訊
	public DefaultTableModel getSubModel01(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"bulletinDate","position","symptom","severity","dealWith"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Med2002Db med2002Db = (Med2002Db)it2.next();
			String[] rowArray= new String[col02.length];
			rowArray[0]=Common.formatYYYMMDD(med2002Db.getBulletinDate(),2);
			rowArray[1]=Common.get(med2002Db.getPosition());
			rowArray[2]=Common.get(med2002Db.getSymptom());
			rowArray[3]=Common.get(med2002Db.getSeverity());
			rowArray[4]=Common.get(med2002Db.getDealWith());
			
			arrList.add(rowArray);

		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	
	//子報表-相關檢查,檢驗數據及其他資料
	public DefaultTableModel getSubModel02(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"testDate","testItems","testNum"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Med2003Db med2003Db = (Med2003Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.formatYYYMMDD(med2003Db.getTestDate(),2);
			rowArray[1]=Common.get(med2003Db.getTestItems());
			rowArray[2]=Common.get(med2003Db.getTestNum());
			
			arrList.add(rowArray);

		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	
	//子報表-併用醫材
	public DefaultTableModel getSubModel03(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"cName","permitNumber","permitFirm","model",
				"mainCategory","useDate","useReason"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		
		while(it2.hasNext())
		{
			Med2004Db med2004db = (Med2004Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.get(med2004db.getcName());
			rowArray[1]=Common.get(med2004db.getPermitNumber());
			rowArray[2]=Common.get(med2004db.getPermitFirm());
			rowArray[3]=Common.get(med2004db.getModel());
			rowArray[4]=Common.get(med2004db.getMainCategory());
			rowArray[5]=Common.formatYYYMMDD(med2004db.getUseDate(),2);
			rowArray[6]=Common.get(med2004db.getUseReason());
			arrList.add(rowArray);

		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
	        System.out.println("併用醫材： " + model.getDataVector());
		}
        return model;
	}	
	
	//子報表-併用藥品
	public DefaultTableModel getSubModel04(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"cName","content","formulation","drgApproach","dose",
    			"frequency","sDate","eDate","medCauses"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Med2005Db med2005db = (Med2005Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.get(med2005db.getcName());
			rowArray[1]=Common.get(med2005db.getContent());
			rowArray[2]=Common.get(med2005db.getFormulation());
			rowArray[3]=Common.get(med2005db.getDrgApproach());
			rowArray[4]=Common.get(med2005db.getDose());
			rowArray[5]=Common.get(med2005db.getFrequency());
			rowArray[6]=Common.formatYYYMMDD(med2005db.getsDate(),2)+"~";
			rowArray[7]=Common.formatYYYMMDD(med2005db.geteDate(),2);
			rowArray[8]=Common.get(med2005db.getMedCauses());
			arrList.add(rowArray);

		}

		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        System.out.println("併用藥品： " + model.getDataVector());

        return model;
	}	
	//取回common_code內容
	public String getCommonCodeKindHQL(String codeKindId) {
		String HQL = "from CommonCode where 1=1 and codeKindId ='" + codeKindId + "'";
		return HQL;
	}
	
	//是、否、無法得知
	public String getMedTFO(String MedUse) {
		String hql = getCommonCodeKindHQL("16");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(MedUse)) 
				{
					checkbox = "■" + obj.getCodeName();

				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	
	
	
	
	//醫療器材操作者
	public String getMedOperator(String MedOperator) {
		String hql = getCommonCodeKindHQL("8");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(MedOperator)) 
				{
					checkbox = "■" + obj.getCodeName();

				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				rowArray2 += checkbox +"\n";
			}
		}
		return rowArray2;
	}
	
	//是否同時使用
	public String getSameTimeUse(String SameTimeUse,String SameTimeUseOther) {
		String hql = getCommonCodeKindHQL("17");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(SameTimeUse)) 
				{
					checkbox = "■" + obj.getCodeName();
					if("4".equals(SameTimeUse)) {
						checkbox += SameTimeUseOther;
					}
				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
					if(3 == i) {
						checkbox += "________";
					}
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	//不良反應結果
	public String getCommonCodeKindOfBadReactionResults(String BadReactionResults,String DeathDateAndReason,String BadReactionResultsOther) {
		String hql =getCommonCodeKindHQL("21");
		String rowArray2 = "";
		String[] BadReactionResultsList = BadReactionResults.split(",");
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				
				String check = "□";
				String check2 = "□";
				for(int j=0; j<BadReactionResultsList.length; j++) 
				{
					if(obj.getCodeId().equals(BadReactionResultsList[j]))
					{
						check = "■";
						if("01".equals(BadReactionResultsList[j]))
						{
							check = check + obj.getCodeName() + DeathDateAndReason; //不良反應結果為死亡時帶入日期與死亡原因
						} else if("06".equals(BadReactionResultsList[j]))
						{
							check2 = "■";
							check2 = check2 + obj.getCodeName() + BadReactionResultsOther;
						}
					}
					
				}
				if(i == 0) 
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check + "\n";
					} else {
						rowArray2 += check + obj.getCodeName() +  "，日期________　死亡原因：________\n";
					}
					
				} else if(i == 5) 
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check2 + "\n";
					} else {
						rowArray2 += check + obj.getCodeName() +  "________\n";
					}
					
				}
				else 
				{
					rowArray2 += check + obj.getCodeName() + "\n";
				}
			}
		}
		return rowArray2;
	}

	
	
	
	public String getUsernameid() {
		return checkGet(usernameid);
	}

	public void setUsernameid(String usernameid) {
		this.usernameid = checkSet(usernameid);
	}

	public String getCommonUser() {
		return checkGet(commonUser);
	}

	public void setCommonUser(String commonUser) {
		this.commonUser = checkSet(commonUser);
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getStatus() {
		return checkGet(status);
	}
	
	public void setStatus(String status) {
		this.status = checkSet(status);
	}
	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}

	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}
	public String getIsCloseUserInfo() {return checkGet(isCloseUserInfo);}
	public void setIsCloseUserInfo(String isCloseUserInfo) {this.isCloseUserInfo = checkSet(isCloseUserInfo);}
}
