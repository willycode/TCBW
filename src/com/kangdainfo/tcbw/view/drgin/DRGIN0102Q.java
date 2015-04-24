package com.kangdainfo.tcbw.view.drgin;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0004Db;
import com.kangdainfo.tcbw.model.bo.Drg0007Db;
import com.kangdainfo.tcbw.model.bo.Drg0008Db;
import com.kangdainfo.tcbw.model.bo.Drg0009Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGIN0102Q extends DRGIN0114F{
	
	
	private String isCloseUserInfo;		//是否遮蔽個資
	public String getIsCloseUserInfo() {return checkGet(isCloseUserInfo);}
	public void setIsCloseUserInfo(String isCloseUserInfo) {this.isCloseUserInfo = checkSet(isCloseUserInfo);}
	
	private String reAssessReason;    //再評估理由
	public String getReAssessReason() {	return checkGet(reAssessReason);	}
	public void setReAssessReason(String reAssessReason) {	this.reAssessReason = checkSet(reAssessReason);	}
	
	public void doAssessDrg0102Q() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrginDao().doReAssessByDrgIN0102Q(this);
	}

	//不良品通報表列印
	public DefaultTableModel getDefaultTableModel() throws Exception
	{
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[] 
    	        {"id","applNo","status","chargeMan","occurDate","notifierRevDate",
    			"notifierRepDate","notifierSource","notifierName","notifierDept","notifierTel","address","notifierEamil",
    			"notifierType","notifierTitle","permitNo","chProduct","enProduct","ingredient","content","medModel",
    			"medPackage","applicationName","manufactorName","manufactorNo","manufactorDate","expirationDate",
    			"storage","isFindYn","isSingleYn","isHarmYn","evenContactYn","dealWith",
    			"isContactYn","firstResult","defectDesc","isUsedYn","defectMemo"
    	        };
    	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = "from Drg0001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) {
			java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
			java.util.Map<String, String> titleMap = TCBWCommon.getCommonCodeMap("TITLE");
			for (int i=0; i<list.size(); i++) {
				Drg0001Db obj = (Drg0001Db) list.get(i);
				String rowArray[]=new String[cols.length];
				
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.get(obj.getChargeMan()); 
				rowArray[4]=Common.formatYYYMMDD(obj.getOccurDate(),2);
				rowArray[5]=Common.formatYYYMMDD(obj.getNotifierRevDate(),2);
				rowArray[6]=Common.formatYYYMMDD(obj.getNotifierRepDate(),2);
				rowArray[7]=getNotifierSource(Common.get(obj.getNotifierSource()));
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierName())))) {
					rowArray[8]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[8]=Common.get(obj.getNotifierName());
				} else {
					rowArray[8]=Common.get(obj.getNotifierName());
				}
				

				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierDept())))) {
					rowArray[9]="●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[9]=Common.get(obj.getNotifierDept());
				} else {
					rowArray[9]=Common.get(obj.getNotifierDept());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierTel())))) {
					rowArray[10]="●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[10]=Common.get(obj.getNotifierTel());
				} else {
					rowArray[10]=Common.get(obj.getNotifierTel());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierAddress())))) {
					rowArray[11]="●●●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[11]=Common.get(obj.getNotifierAddress());
				} else {
					rowArray[11]=Common.get(obj.getNotifierAddress());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierEmail())))) {
					rowArray[12]="●●●●●●●●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[12]=Common.get(obj.getNotifierEmail());
				} else {
					rowArray[12]=Common.get(obj.getNotifierEmail());
				}
				
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
					rowArray[28]="■ 是\n□ 否";
				} else if("N".equals(Common.get(obj.getIsFindYn()))) {
					rowArray[28]="□ 是\n■ 否";
				} else {
					rowArray[28]="□ 是\n□ 否";
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
				
				if(obj.getDrg0002Dbs()!=null && obj.getDrg0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getDrg0002Dbs().iterator();
					
					String[] maincode = null;
					String[] subcode = null;
					String main = "";
					String sub = "";
					
					while(it2.hasNext())
					{
						Drg0002Db drg0002Db = (Drg0002Db)it2.next();
						
						main += drg0002Db.getMainCode() + ",";
						sub += drg0002Db.getSubCode() + ",";
					}
					maincode = main.split(",");
					subcode = sub.split(",");
					
					rowArray[35]=getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId",maincode,subcode,Common.get(obj.getId()));
				} else {
					String[] maincode = null;
					String[] subcode = null;
					rowArray[35]=getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId",maincode,subcode,Common.get(obj.getId()));

				}
				
				if("Y".equals(Common.get(obj.getIsUsedYn()))) {
					rowArray[36]="■ 是　□ 否";
				} else if("N".equals(Common.get(obj.getIsUsedYn()))) {
					rowArray[36]="□ 是　■ 否";
				} else {
					rowArray[36]="□ 是　□ 否";
				}
				
				rowArray[37]=Common.get(obj.getDefectDesc());
				System.out.println("rowArray37: " + rowArray[35]);
				
				arrList.add(rowArray);
				
				
				
			}
			if(pkidMap!=null) pkidMap.clear();
		}
		if (arrList!=null && arrList.size()>0) {
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		return model;
		
	}
	
	//CAPA評估表列印
	public DefaultTableModel getCAPADefaultTableModel() throws Exception {
		String hqlCAPA = "from Drg0007Db where applNo = " +Common.sqlChar(getApplNo());
		java.util.List CAPAList = ServiceGetter.getInstance().getCommonService().load(hqlCAPA);
		if(CAPAList!=null && CAPAList.size() > 0) {
		
		//取得不良品缺陷資料
		String drg01id = View.getLookupField("select id from Drg0001Db where applNo="+Common.sqlChar(getApplNo()));
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
		if(subCodeList != null && subCodeList.length() > 3) subCodeList = subCodeList.substring(0, subCodeList.length() - 3);
//		System.out.println("subCodeList: " + subCodeList.length());
		
	    //歷年本藥品之通報件數
	    String sql1 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo());		     
	    int hisData1 = ServiceGetter.getInstance().getCommonService().loadCount(sql1);

       
	    //歷年本藥品同此次瑕疵之通報件數
	    int hisData2 = 0;
	    if(subCodeList != null && subCodeList.length() > 0) {
	    String sql2 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                   " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
	    hisData2 = ServiceGetter.getInstance().getCommonService().loadCount(sql2);
	    
	    }

	     
	    //一年內本藥品之通報件數
	    String sql3 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                   " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()));
        int hisData3 = ServiceGetter.getInstance().getCommonService().loadCount(sql3);

        
        //一年內本藥品同此次瑕疵之通報件數
        int hisData4 = 0;
        if(subCodeList != null && subCodeList.length() > 0) {
	    String sql4 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                   " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
	                   " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
        hisData4 = ServiceGetter.getInstance().getCommonService().loadCount(sql4);
        }

        
        //一年內本藥品同此次瑕疵之高風險通報件數
        int hisData5 = 0;
        if(subCodeList != null && subCodeList.length() > 0) {
	    String sql5 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                  " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))"+
                      " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
                      " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
        hisData5 = ServiceGetter.getInstance().getCommonService().loadCount(sql5);
        }

        
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
				hisData6 += "["+o[0]+"] : " + o[1] + "件 ,";
			}	    	 
	    }
	    if(hisData6 != "") hisData6 = hisData6.substring(0,hisData6.length()-1);
	    else hisData6 = "件"; 	     
	    
        //一年內本藥品同此次瑕疵之高風險案件
        String sql7 = " select manufactorNo,count(applNo) from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
                      " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
                      " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))";
                      if(subCodeList != null && subCodeList.length() > 0) {
                      sql7 += " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
                      }
                      sql7 += " group by manufactorNo"; 
                      System.out.println("sql7: " + sql7);
        java.util.List hql7List = ServiceGetter.getInstance().getCommonService().load(sql7);
        String hisData7 ="";
        if(hql7List!=null && hql7List.size()>0)
        {       	 
        	for (int i=0; i<hql7List.size(); i++) {       		
        		Object[] o = (Object[]) hql7List.get(i);       		
        		hisData7 += "["+o[0]+"] : "+o[1]+"件 ,";       	 
        	}
        }
        if(hisData7 != "") hisData7 = hisData7.substring(0,hisData7.length()-1);
        else hisData7 ="件";  		

		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		String[] cols = new String[] { "id", "applNo","assessDate","chProduct","enProduct","permitKey","permitNo","medModel", //0-7
				"druggist","manufactorName","lotNo","defectDesc","drgLev","caseReason","checkResult", //8-14
				"survey","precaution","ingredient","hisData","assessResult","dealWith","drgReason" //15-21
		};
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

		String hql = "from Drg0001Db a where 1=1 and a.applNo in (select applNo from Drg0007Db b where b.applNo = " + Common.sqlChar(getApplNo()) + ")";
		if (!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if (!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());

		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		
		if (list != null && list.size() > 0) {
			
			String drg0007dbhql = "from Drg0007Db where 1=1";
			String drg0008dbhql = "from Drg0008Db where 1=1";
			String drg0004dbhql = "from Drg0004Db where 1=1";
			
			if(!"".equals(getApplNo())) {
				drg0004dbhql += "and applNo = " + Common.sqlChar(getApplNo());
				drg0007dbhql += "and applNo = " + Common.sqlChar(getApplNo());
				drg0008dbhql += "and applNo = " + Common.sqlChar(getApplNo());
			}
			java.util.List drg0004dblist = ServiceGetter.getInstance().getCommonService().load(drg0004dbhql);
			java.util.List drg0007dblist = ServiceGetter.getInstance().getCommonService().load(drg0007dbhql);
			java.util.List drg0008dblist = ServiceGetter.getInstance().getCommonService().load(drg0008dbhql);

			if(drg0004dblist != null && drg0004dblist.size() > 0) {
			if(drg0007dblist != null && drg0007dblist.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				Drg0001Db obj = (Drg0001Db) list.get(i);
				
				Drg0004Db drg0004db = (Drg0004Db) drg0004dblist.get(i);
				Drg0007Db drg0007db = (Drg0007Db) drg0007dblist.get(i);
				
				String rowArray[] = new String[cols.length];
				
				rowArray[0] = Common.get(obj.getId());
				rowArray[1] = Common.get(obj.getApplNo());
				rowArray[2] = Common.get(drg0007db.getAssessDate());
				rowArray[3] = Common.get(obj.getChProduct());
				rowArray[4] = Common.get(obj.getEnProduct());
				rowArray[5] = getPermitKey(rowArray[5],Common.get(obj.getPermitKey()));
				rowArray[6] = Common.get(obj.getPermitNo());
				rowArray[7] = getMedModelCAPA(rowArray[7],Common.get(obj.getMedModel())); //劑型
				rowArray[8] = Common.get(obj.getApplicationName());
				rowArray[9] = Common.get(obj.getManufactorName());
				rowArray[10] = Common.get(obj.getManufactorNo());//批號
				
				if(obj.getDrg0002Dbs()!=null && obj.getDrg0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getDrg0002Dbs().iterator();
					
					String[] maincode = null;
					String[] subcode = null;
					String main = "";
					String sub = "";
					
					while(it2.hasNext())
					{
						Drg0002Db drg0002Db = (Drg0002Db)it2.next();
						
						main += drg0002Db.getMainCode() + ",";
						sub += drg0002Db.getSubCode() + ",";
					}
					maincode = main.split(",");
					subcode = sub.split(",");
					
					rowArray[11]=getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId",maincode,subcode,Common.get(obj.getId()));
				} else {
					String[] maincode = null;
					String[] subcode = null;
					rowArray[11]=getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId",maincode,subcode,Common.get(obj.getId()));
				}
				
				//rowArray[12] = getDrgLev(Common.get(drg0004db.getDrgLev()));//風險等級  (改抓drg08Db)
				rowArray[13] = "■通報系統　□廠商主動通報　□警訊監控　□品質監測";//本案原由
				rowArray[14] = Common.get(drg0007db.getCheckResult());
				rowArray[15] = Common.get(drg0007db.getSurvey());;
				rowArray[16] = Common.get(drg0007db.getPrecaution());
				rowArray[17] = Common.get(obj.getIngredient());
				rowArray[18] = "歷年本藥品之通報件數：" + hisData1 + "件\n" + "歷年本藥品同此次瑕疵之通報件數：" + hisData2 + "件\n" + 
							   "一年內本藥品之通報件數：" + hisData3 + "件\n" + "一年內本藥品同此次瑕疵之通報件數：" + hisData4 + "件\n" +
							   "一年內本藥品同此次瑕疵之高風險通報件數：" + hisData5 + "件\n" + "一年內本藥品同此次瑕疵案件：" + hisData6 + "\n" + 
							   "一年內本藥品同此次瑕疵之高風險案件：" + hisData7 + "\n";
				if(drg0008dblist != null && drg0008dblist.size() > 0) { 
					Drg0008Db drg0008db = (Drg0008Db) drg0008dblist.get(i);
					rowArray[12] = getDrgLev(drg0008db.getDrgLev());
					rowArray[19] = Common.get(drg0008db.getAssessResult());
					rowArray[20] = getDrg0008_DealWith(Common.get(drg0008db.getDealWith()));
					rowArray[21] = Common.get(drg0008db.getDrgReason());
				} else {
					rowArray[19] = "";
					rowArray[20] = getDrg0008_DealWith("");
					rowArray[21] = "";
				}
				
				
				arrList.add(rowArray);
			}
		}}
		}
		if (arrList != null && arrList.size() > 0) {
			String[][] rs = new String[0][0];
			rs = (String[][]) arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		return model;
		}
		else {
			DefaultTableModel model = new javax.swing.table.DefaultTableModel();
			model = null;
			return model;
		}
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
	        		otherDescribeValue = View.getLookupField(" select otherDescribe from Drg0002Db where drg0001Db.id="+Common.getLong(id) +" and mainCode="+Common.sqlChar(Common.get(o[0])));
	
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
	//CAPA評估表不良藥品劑型格式
	public String getMedModelCAPA(String NotifierSource,String MedModelOther) {
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
					checkbox = "■" + obj.getCodeName() + " ";
					if("10".equals(NotifierSource)) {
						checkbox += MedModelOther;
					}
				}
				else
				{
					checkbox =checkbox + obj.getCodeName() + " ";
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

	//許可證字
	public String getPermitKey(String rowArray2, String PermitKey) {
		rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRGPKID");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "";
				if (obj.getCodeId().equals(PermitKey)) {
					checkbox += obj.getCodeName();
				} 
				rowArray2 += checkbox;
			}
			
		}
		return rowArray2;
	}
	
	//風險等級
	public String getDrgLev(String DrgLev) {
		String rowArray2 = "";
		if("B1".equals(DrgLev)){
			rowArray2 = "■ 高通報(同批號n≧3)  □ 高通報(不同批號n≧5)  □ 高風險";
		}else if("B2".equals(DrgLev)){
			rowArray2 = "□ 高通報(同批號n≧3)  ■ 高通報(不同批號n≧5)  □ 高風險";
		}else if("A".equals(DrgLev)){
			rowArray2 = "□ 高通報(同批號n≧3)  □ 高通報(不同批號n≧5)  ■ 高風險";
		}else {
			rowArray2 = "□ 高通報(同批號n≧3)  □ 高通報(不同批號n≧5)  □ 高風險";
		}
		return rowArray2;
	}
	
	//擬辦事項
	public String getDrg0008_DealWith(String DealWith) {
		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRGDLW");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				if (obj.getCodeId().equals(DealWith)) {
					checkbox = "■";
					checkbox += obj.getCodeName();
				}
				else {
					checkbox += obj.getCodeName();
				}
				rowArray2 += checkbox + "　";
			}
			
		}
		return rowArray2;
	}
	
}
