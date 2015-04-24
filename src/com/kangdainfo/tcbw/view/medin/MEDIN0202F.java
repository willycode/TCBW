package com.kangdainfo.tcbw.view.medin;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0003Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0002Db;
import com.kangdainfo.tcbw.model.bo.Med0003Db;
import com.kangdainfo.tcbw.model.bo.Med0004Db;
import com.kangdainfo.tcbw.model.bo.Med0005Db;



public class MEDIN0202F extends MEDIN0101F
{
	private String usernameid; 
	private String commonUser;
	
	private String isCloseUserInfo;		//是否遮蔽個資
	
	private String changeTabValue;
	
	//受理
	public void doAccepted() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateByMedIN0202F(this);
	}
	
	//退件
	public void doBackPieces() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateByBackPiecesMedIN0202F(this);
	}
	
	//撤案完成
	public void doDismissal() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateByDismissalMedIN0202F(this);
	}
	
	//校正完成
	public void doCorrection() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateByCorrectionMedIN0202F(this);
	}
	
	//補件
	public void doAddocuments() throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateByAddocumentsMedIN0202F(this);
	}
	
	//刪除lock
	public void doDeleteCon0003Db() throws Exception
	{
		 String hql = "from Con0003Db where systemType='MED' ";
                hql +=" and formCode='MED01' ";
                hql +=" and stateus= "+ Common.sqlChar(getStatus());
                hql +=" and dbID=" + Common.sqlChar(getId());

         Con0003Db con0003Db = (Con0003Db)View.getObject(hql);
         
         System.out.println("hql=="+hql);
         System.out.println("con0003Db=="+con0003Db);
         
         if (con0003Db != null) 
         { 
	         ServiceGetter.getInstance().getTcbwService().delete(con0003Db);
         }
	}
	
	public DefaultTableModel getDefaultTableModel() throws Exception
	{
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[] 
    	        {"id","applNo","status","occurDate","notifierRevDate","notifierRepDate","notifierName",//0~6
    			"notifierAreaCode","notifierTel","notifierAddress","notifierEamil","notifierType","notifierStaffHospital",//7~12
    			"notifierStaffTitle","notifierDept","isContactYn","drugEventsSources","medicalStaff",//13~17
    			"healthUnits","caseSource","reportKind","trackingNum","correctiveAction","attachment","attachmentYnum",//18~24
    			"drugSafetyMonitoring","medCname","medPermit","medPermitNumber","medPermitFirm","medMainCategory",//25~30
    			"medSecCategory","medCountry","medFactory","medModel","medNo","medLotNum","medSoftwareVersion",//31~37
    			"medManufactureDate","medEffectiveDate","medPurchaseDate","medUseDate","medUseReason","eventKind",//38~43
    			"badReactionPatientCode","badReactionSex","badReactionBirthday","badReactionAge","badReactionWeight",//44~48
    			"badReactionHeight","badReactionResults","obj1","isprint1","detail1"//49~51
    	        };
    	java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		
		String hql = "from Med0001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		
		int dt1 = 0;
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				Med0001Db obj = (Med0001Db) list.get(i);
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
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierAreaCode())))) {
					rowArray[7]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[7]=Common.get(obj.getNotifierAreaCode());
				} else {
					rowArray[7]=Common.get(obj.getNotifierAreaCode());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierTel())))) {
					rowArray[8]="●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[8]=Common.get(obj.getNotifierTel());
				} else {
					rowArray[8]=Common.get(obj.getNotifierTel());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierAddress())))) {
					rowArray[9]="●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[9]=Common.get(obj.getNotifierAddress());
				} else {
					rowArray[9]=Common.get(obj.getNotifierAddress());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierEamil())))) {
					rowArray[10]="●●●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[10]=Common.get(obj.getNotifierEamil());
				} else {
					rowArray[10]=Common.get(obj.getNotifierEamil());
				}
				
				if("03".equals(Common.get(obj.getNotifierType()))) {
					rowArray[11]="■ 醫療人員\n\n□ 廠商\n□ 民眾\n□ 衛生單位";
					rowArray[12]=Common.get(obj.getNotifierStaffHospital());
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				} else if("02".equals(Common.get(obj.getNotifierType()))) {
					rowArray[11]="□ 醫療人員\n\n■ 廠商\n□ 民眾\n□ 衛生單位";
					rowArray[12]="__________";
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				} else if("01".equals(Common.get(obj.getNotifierType()))) {
					rowArray[11]="□ 醫療人員\n\n□ 廠商\n■ 民眾\n□ 衛生單位";
					rowArray[12]="__________";
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				} else if("04".equals(Common.get(obj.getNotifierType()))) {
					rowArray[11]="□ 醫療人員\n\n□ 廠商\n□ 民眾\n■ 衛生單位";
					rowArray[12]="__________";
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				} else {
					rowArray[11]="□ 醫療人員\n\n□ 廠商\n□ 民眾\n□ 衛生單位";
					rowArray[12]="__________";
					rowArray[13]=getNotifierStaffTitle(Common.get(obj.getNotifierStaffTitle()),Common.get(obj.getNotifierStaffTitleOther()));
				}
				
				rowArray[14]=Common.get(obj.getNotifierDept());
				
				if("Y".equals(Common.get(obj.getIsContactYn()))) {
					rowArray[15]="■願意　　□不願意";
				} else {
					rowArray[15]="□願意　　■不願意";
				}
				
				if("1".equals(Common.get(obj.getDrugEventsSources()))) {
					rowArray[16]="■由醫療人員轉知\n□由衛生單位得知\n□廠商\n□由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					rowArray[18]="□衛生局(所)　□其他________";
				} else if("2".equals(Common.get(obj.getDrugEventsSources()))) {
					rowArray[16]="□由醫療人員轉知\n■由衛生單位得知\n□廠商\n□由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					if("1".equals(Common.get(obj.getHealthUnits()))) {
						rowArray[18]="■衛生局(所)　□其他________";
					} else {
						rowArray[18]="□衛生局(所)　■其他" + Common.get(obj.getHealthUnitsOther());
					}
				} else if("3".equals(Common.get(obj.getDrugEventsSources()))) {
					rowArray[16]="□由醫療人員轉知\n□由衛生單位得知\n■廠商\n□由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					rowArray[18]="□衛生局(所)　□其他________";
				} else if("4".equals(Common.get(obj.getDrugEventsSources()))) {
					rowArray[16]="□由醫療人員轉知\n□由衛生單位得知\n□廠商\n■由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					rowArray[18]="□衛生局(所)　□其他________";
				} else {
					rowArray[16]="□由醫療人員轉知\n□由衛生單位得知\n□廠商\n□由民眾主重告知";
					rowArray[17]=getNotifierStaffTitle(Common.get(obj.getMedicalStaff()),Common.get(obj.getMedicalStaffOther()));
					rowArray[18]="□衛生局(所)　□其他________";
				}
				
				if("in".equals(Common.get(obj.getCaseSource()))) {
					rowArray[19]="■國內，或　□國外，";
				} else {
					rowArray[19]="□國內，或　■國外，" + Common.get(obj.getCaseSourceOutCountry());
				}
				
				if("1".equals(Common.get(obj.getReportKind()))) {
					rowArray[20]="■初始報告\n□追蹤報告";
					rowArray[21]="________";
				} else if("2".equals(Common.get(obj.getReportKind()))) {
					rowArray[20]="□初始報告\n■追蹤報告";
					rowArray[21]=Common.get(obj.getTrackingNum());
				} else {
					rowArray[20]="□初始報告\n□追蹤報告";
					rowArray[21]="________";
				}
				
				rowArray[22]=getMedTFO(Common.get(obj.getCorrectiveAction()));
				if("N".equals(Common.get(obj.getAttachment()))) {
					rowArray[23]="■無\n□有";
					rowArray[24]="______";
				} else if("Y".equals(Common.get(obj.getAttachment()))) {
					rowArray[23]="□無\n■有";
					rowArray[24]=Common.get(obj.getAttachmentYnum());
				}else {
					rowArray[23]="□無\n□有";
					rowArray[24]="______";
				}
				
				rowArray[25]=getMedTFO(Common.get(obj.getDrugSafetyMonitoring()));
				rowArray[26]=Common.get(obj.getMedCname());
				rowArray[27]=getPermitKey(Common.get(obj.getMedPermit()));
				rowArray[28]=Common.get(obj.getMedPermitNumber());
				rowArray[29]=Common.get(obj.getMedPermitFirm());
				rowArray[30]=Common.get(obj.getMedMainCategory());
				rowArray[31]=Common.get(obj.getMedSecCategory());
				rowArray[32]=Common.get(obj.getMedCountry());
				rowArray[33]=Common.get(obj.getMedFactory());
				rowArray[34]=Common.get(obj.getMedModel());
				rowArray[35]=Common.get(obj.getMedNo());
				rowArray[36]=Common.get(obj.getMedLotNum());
				rowArray[37]=Common.get(obj.getMedSoftwareVersion());
				rowArray[38]=Common.formatYYYMMDD(obj.getMedManufactureDate(),2);
				rowArray[39]=Common.formatYYYMMDD(obj.getMedEffectiveDate(),2);
				rowArray[40]=Common.formatYYYMMDD(obj.getMedPurchaseDate(),2);
				rowArray[41]=Common.formatYYYMMDD(obj.getMedUseDate(),2);
				rowArray[42]=Common.get(obj.getMedUseReason());
				
				if("1".equals(Common.get(obj.getEventKind()))) {
					rowArray[43]="■不良反應(請續填 Ⅱ-a)　　　□不良品(請續填Ⅱ-b)";
				} else if("2".equals(Common.get(obj.getEventKind()))) {
					rowArray[43]="□不良反應(請續填 Ⅱ-a)　　　■不良品(請續填Ⅱ-b)";
				} else if("1".equals(Common.get(obj.getEventKind())) && "2".equals(Common.get(obj.getEventKind()))) {
					rowArray[43]="■不良反應(請續填 Ⅱ-a)　　　■不良品(請續填Ⅱ-b)";
				}
				
				rowArray[44]=Common.get(obj.getBadReactionPatientCode());
				
				
				if("M".equals(Common.get(obj.getEventKind()))) {
					rowArray[45]="■男　　□女";
				} else if("F".equals(Common.get(obj.getEventKind()))) {
					rowArray[45]="□男　　■女";
				} else {
					rowArray[45]="□男　　□女";
				}
				
				rowArray[46]=Common.formatYYYMMDD(obj.getBadReactionBirthday(),2);
				rowArray[47]=Common.get(obj.getBadReactionAge());
				rowArray[48]=Common.get(obj.getBadReactionWeight());
				rowArray[49]=Common.get(obj.getBadReactionHeight());
				
				String DeathDateAndReason = "，日期：" + Common.formatYYYMMDD(obj.getBadReactionResultsDeathDate(),2) + "，死亡原因：" + Common.get(obj.getBadReactionResultsDeathReason()); //死亡日期與原因
				String BadReactionResultsOther = Common.get(obj.getBadReactionResultsOther());
				rowArray[50]=getCommonCodeKindOfBadReactionResults(Common.get(obj.getBadReactionResults()),DeathDateAndReason,BadReactionResultsOther);

				if(obj.getMed0002Dbs()!=null && obj.getMed0002Dbs().size() > 0) {
					java.util.Iterator it2 = obj.getMed0002Dbs().iterator();
					rowArray[51] = new JRTableModelDataSource(getSubModel01(it2));
				} else {
					rowArray[51]=null;
				}
				rowArray[52] = "tmp" + dt1;
				rowArray[53] = new JRTableModelDataSource(getDetail1Model(obj));
				
				
				
		
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
		 String detail1FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_detail1.jasper");
			String subreport0FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_subreport0.jasper");
			String subreport1FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_subreport1.jasper");
			String subreport2FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_subreport2.jasper");
			String subreport3FilePath = 
				ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/med/MEDIN0201r_subreport3.jasper");
	
			params.put("detail1", detail1FilePath);
			params.put("subreport0", subreport0FilePath);			
			params.put("subreport1", subreport1FilePath);	
			params.put("subreport2", subreport2FilePath);			
			params.put("subreport3", subreport3FilePath);
		}
	 
	 
	 //第二頁子報表
	 public DefaultTableModel getDetail1Model(Med0001Db obj) throws Exception
		{
			DefaultTableModel model = new javax.swing.table.DefaultTableModel();
			java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
			String[] cols = new String[]{"medOperator","medDisposalStatus","medUse","onceUseMed",//0~3
	    			"stopMedMitigate","sameReaction","otherRelatedData","productProblemKind1",//4~7
	    			"productProblemKind2","productProblemKind3","productProblemKind4",//8~10
	    			"productProblemKindOther","defProductDescription","obj2","obj3","obj4",//11~15
	    			"defProductOtherDescription"//16
	    			};

			Object[] rowArray = new Object[cols.length];
			
			rowArray[0]=getMedOperator(Common.get(obj.getMedOperator()));
			
			if("1".equals(Common.get(obj.getMedDisposalStatus()))) {
				rowArray[1]="■ 已銷毀　□ 尚在調查中　□ 當植於病患體內　□於___年___月___日";
			} else if("2".equals(Common.get(obj.getMedDisposalStatus()))) {
				rowArray[1]="□ 已銷毀　■ 尚在調查中　□ 當植於病患體內　□於___年___月___日";
			} else if("3".equals(Common.get(obj.getMedDisposalStatus()))) {
				rowArray[1]="□ 已銷毀　□ 尚在調查中　■ 當植於病患體內　□於___年___月___日";
			} else if("4".equals(Common.get(obj.getMedDisposalStatus()))) {
				rowArray[1]="□ 已銷毀　□ 尚在調查中　□ 當植於病患體內　■於" + Common.formatYYYMMDD(obj.getMedDisposalStatusDate(),2);
			} else {
				rowArray[1]="□ 已銷毀　□ 尚在調查中　□ 當植於病患體內　□於___年___月___日";
			}
			
			String MedUseOther = Common.get(obj.getMedUseOther());
			rowArray[2]=getMedUse(Common.get(obj.getMedUse()),MedUseOther);
			
			if("Y".equals(Common.get(obj.getOnceUseMed()))) {
				rowArray[3]="■是，醫材名稱：" + Common.get(obj.getOnceUseMedName()) + " 若發生不良反應請描述：" + Common.get(obj.getOnceUseMedName()) + "\n□否　□無法得知";
			} else if("N".equals(Common.get(obj.getOnceUseMed()))) {
				rowArray[3]="□是，醫材名稱：________ 若發生不良反應請描述：________\n■否　□無法得知";
			} else if("O".equals(Common.get(obj.getOnceUseMed()))) {
				rowArray[3]="□是，醫材名稱：________ 若發生不良反應請描述：________\n□否　■無法得知";
			} else {
				rowArray[3]="□是，醫材名稱：________ 若發生不良反應請描述：________\n□否　□無法得知";
			}

			rowArray[4]=getMedTFO(Common.get(obj.getStopMedMitigate()));
			rowArray[5]=getMedTFO(Common.get(obj.getSameReaction()));
			rowArray[6]=Common.get(obj.getOtherRelatedData());
			rowArray[7]=getProductProblemKind1(Common.get(obj.getProductProblemKind1()));
			rowArray[8]=getProductProblemKind2(Common.get(obj.getProductProblemKind2()));
			rowArray[9]=getProductProblemKind3(Common.get(obj.getProductProblemKind3()));
			rowArray[10]=getProductProblemKind4(Common.get(obj.getProductProblemKind4()));
			if(!"".equals(Common.get(obj.getProductProblemKindOther()))) {
				rowArray[11]=Common.get(obj.getProductProblemKindOther());
			} else {
				rowArray[11]="_______";

			}
			rowArray[12]=Common.get(obj.getDefProductDescription());


			if(obj.getMed0003Dbs()!=null && obj.getMed0003Dbs().size() > 0) {
				java.util.Iterator it2 = obj.getMed0003Dbs().iterator();
				rowArray[13] = new JRTableModelDataSource(getSubModel02(it2));
			} else {
				rowArray[13]=null;
			}
			
			if(obj.getMed0004Dbs()!=null && obj.getMed0004Dbs().size() > 0) {
				java.util.Iterator it2 = obj.getMed0004Dbs().iterator();
				
				rowArray[14] = new JRTableModelDataSource(getSubModel03(it2));
			}
			
			if(obj.getMed0005Dbs()!=null && obj.getMed0005Dbs().size() > 0) {
				java.util.Iterator it2 = obj.getMed0005Dbs().iterator();
				rowArray[15] = new JRTableModelDataSource(getSubModel04(it2));
			}
			rowArray[16]=Common.get(obj.getDefProductOtherDescription());
			
			//-----------------------
			arrList.add(rowArray);
			if(null != arrList && arrList.size() >0)
			{
				Object[][] rs = new Object[0][0];
		        rs = (Object[][])arrList.toArray(rs);
		        model.setDataVector(rs, cols);
			}
			return model;
		}
	
	//子報表-不良事件描述資訊
	public DefaultTableModel getSubModel01(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"bulletinDate","position","symptom","severity","dealWith"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Med0002Db med0002Db = (Med0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			rowArray[0]=Common.formatYYYMMDD(med0002Db.getOccurDate(),2);
			rowArray[1]=Common.get(med0002Db.getPosition());
			rowArray[2]=Common.get(med0002Db.getSymptom());
			rowArray[3]=Common.get(med0002Db.getSeverity());
			rowArray[4]=Common.get(med0002Db.getDealWith());
			
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
			Med0003Db med0003Db = (Med0003Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.formatYYYMMDD(med0003Db.getTestDate(),2);
			rowArray[1]=Common.get(med0003Db.getTestItems());
			rowArray[2]=Common.get(med0003Db.getTestNum());
			
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
			Med0004Db med0004db = (Med0004Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(med0004db.getcName());
			if(getPermitKey(Common.get(med0004db.getPermit())).length() > 0) {
				rowArray[1]=getPermitKey(Common.get(med0004db.getPermit()))+"字";
				if(Common.get(med0004db.getPermitNumber()).length() > 0) {
					rowArray[1] += "第"+Common.get(med0004db.getPermitNumber())+"號";
				}
			}
			else {
				rowArray[1] = "";
			}
//			rowArray[1]=getPermitKey(Common.get(med0004db.getPermit()))+"字"+"第"+Common.get(med0004db.getPermitNumber())+"號";
			rowArray[2]=Common.get(med0004db.getPermitFirm());
			rowArray[3]=Common.get(med0004db.getModel());
			rowArray[4]=Common.get(med0004db.getMainCategory());
			rowArray[5]=Common.formatYYYMMDD(med0004db.getUseDate(),2);
			rowArray[6]=Common.get(med0004db.getUseReason());
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
	
	//子報表-併用藥品
	public DefaultTableModel getSubModel04(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"cName","content","formulation","drgApproach","dose",
    			"frequency","sDate","eDate","medCauses"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Med0005Db med0005db = (Med0005Db)it2.next();
			String[] rowArray= new String[col02.length];

			rowArray[0]=Common.get(med0005db.getcName());
			rowArray[1]=Common.get(med0005db.getContent());
			rowArray[2]=Common.get(med0005db.getFormulation());
			rowArray[3]=Common.get(med0005db.getDrgApproach());
			rowArray[4]=Common.get(med0005db.getDose());
			rowArray[5]=Common.get(med0005db.getFrequency());
			rowArray[6]=Common.formatYYYMMDD(med0005db.getsDate(),2);
			rowArray[7]=Common.formatYYYMMDD(med0005db.geteDate(),2);
			rowArray[8]=Common.get(med0005db.getMedCauses());
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
	//Common_CodeKind查詢條件
	public String getCommonCodeKindHQL(String codeKindId) 
	{
		String HQL = "from CommonCode where 1=1 and codeKindId ='" + codeKindId + "'";
		return HQL;
	}
	//許可證字
	public String getPermitKey(String Permitkey) {
		String hql = getCommonCodeKindHQL("79");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "";
				if (obj.getCodeId().equals(Permitkey)) {
					checkbox = obj.getCodeName();
				}
				rowArray2 += checkbox;
			}
		}
//		System.out.println("PK: " + rowArray2);
		return rowArray2;
	}
	
	//是、否、無法得知
	public String getMedTFO(String MedUse) 
	{
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
	public String getMedOperator(String MedOperator) 
	{
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
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
	}
	
	
	
	//職稱
	public String getNotifierStaffTitle(String NotifierStaffTitle,
			String NotifierStaffTitleOther) 
	{
		String hql = getCommonCodeKindHQL("6");

		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(NotifierStaffTitle)) 
				{
					checkbox = "■" + obj.getCodeName();
					if("5".equals(NotifierStaffTitle))
					{
						checkbox += NotifierStaffTitleOther;
					} 

				}
				else if("5".equals(obj.getCodeId()))
				{
					
					checkbox += obj.getCodeName() + "_____";
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
	
	//不良反應結果
	public String getCommonCodeKindOfBadReactionResults
	(String BadReactionResults,String DeathDateAndReason,
			String BadReactionResultsOther) 
	{
		String hql = getCommonCodeKindHQL("20");
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
						} else if("07".equals(BadReactionResultsList[j]))
						{
							check2 = "■";
							check2 = check2 + obj.getCodeName() + BadReactionResultsOther;
						}
					}
					
				}
				if(i == 0) 
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check + "\n";//不良反應結果為死亡時
					} else {
						rowArray2 += check + obj.getCodeName() +  "，日期________　死亡原因：________\n";//非死亡時
					}
					
				} else if(i == 6)
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check2 + "\n";//不良反應結果為非嚴重不良反應時
					} else {
						rowArray2 += check + obj.getCodeName() +  "________\n";//非嚴重不良反應時
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
	
	//器材使用
	public String getMedUse(String MedUse,String MedUseOther) {
		String hql = getCommonCodeKindHQL("9");
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
					if("5".equals(MedUse))
					{
						checkbox += MedUseOther;
					}

				}
				else if("5".equals(obj.getCodeId()))
				{
					
					checkbox += obj.getCodeName() + "__________";
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
	
	//產品問題分類-器材操作
	public String getProductProblemKind1(String ProductProblemKind1) {
		String hql = getCommonCodeKindHQL("10");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(ProductProblemKind1)) 
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
	
	//產品問題分類-器材操作
	public String getProductProblemKind2(String ProductProblemKind2) {
		String hql = getCommonCodeKindHQL("11");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(ProductProblemKind2)) 
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
	
	//產品問題分類-器材操作
	public String getProductProblemKind3(String ProductProblemKind3) {
		String hql = getCommonCodeKindHQL("12");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(ProductProblemKind3)) 
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
	
	//產品問題分類-器材操作
	public String getProductProblemKind4(String ProductProblemKind4) {
		String hql = getCommonCodeKindHQL("13");
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(ProductProblemKind4)) 
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
	
	public String getIsCloseUserInfo() {return checkGet(isCloseUserInfo);}
	public void setIsCloseUserInfo(String isCloseUserInfo) {this.isCloseUserInfo = checkSet(isCloseUserInfo);}
	
	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}

	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}
}
