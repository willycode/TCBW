package com.kangdainfo.tcbw.view.drgin;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg0004Db;
import com.kangdainfo.tcbw.model.bo.Drg0007Db;
import com.kangdainfo.tcbw.model.bo.Drg1001Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4002Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.DrgView;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGIN0001F extends SuperBean{
	
	private String id;//序號	NUMERIC(19,0)
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	
	private String printType; //療效不等匯出格式
	public String getPrintType() {return checkGet(printType);}
	public void setPrintType(String printType) {this.printType = checkSet(printType);}
	
	private String[] q_drgType;	
	private String q_applNoS;
	private String q_applNoE;
	private String q_permitKey;
	private String q_permitNo;
	private String q_notifierRepDateS;
	private String q_notifierRepDateE;
	private String q_enrolledDateS;
	private String q_enrolledDateE;
	private String q_occurDateS;
	private String q_occurDateE;	
	private String q_Product;
	private String q_notifierDept;
	private String q_ingredient;
	private String q_applicationName;
	private String q_manufactorName;
	private String q_manufactorNo;
	
	private String[] q_drgLev;
	private String q_survey;
	private String[] q_mainCode;
	private String[] q_subCode;
	private String q_status1;
	
	private String q_medNti;
	private String q_medAtcCode;
	private String[] q_conSequence4;
	private String q_effectChangeDesc4;
	private String q_badReactionLev4;
	private String q_badReactionDesc4;
	private String q_badReactionDra;
	private String q_assessResult;
	private String q_status2;
	
	
	public String[] getQ_drgType() {return q_drgType;}
	public void setQ_drgType(String[] qDrgType) {q_drgType = qDrgType;}
	public String getQ_applNoS() {return checkGet(q_applNoS);}
	public void setQ_applNoS(String qApplNoS) {q_applNoS = checkSet(qApplNoS);}
	public String getQ_applNoE() {return checkGet(q_applNoE);}
	public void setQ_applNoE(String qApplNoE) {q_applNoE = checkSet(qApplNoE);}	
	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String qPermitKey) {q_permitKey = checkSet(qPermitKey);}
	public String getQ_permitNo() {return checkGet(q_permitNo);}
	public void setQ_permitNo(String qPermitNo) {q_permitNo = checkSet(qPermitNo);}
	public String getQ_occurDateS() {return checkGet(q_occurDateS);}
	public void setQ_occurDateS(String qOccurDateS) {q_occurDateS = checkSet(qOccurDateS);}
	public String getQ_occurDateE() {return checkGet(q_occurDateE);}
	public void setQ_occurDateE(String qOccurDateE) {q_occurDateE = checkSet(qOccurDateE);}
	public String getQ_status1() {return checkGet(q_status1);}
	public void setQ_status1(String qStatus1) {q_status1 = checkSet(qStatus1);}
	public String getQ_Product() {return checkGet(q_Product);}
	public void setQ_Product(String qProduct) {q_Product = checkSet(qProduct);}

	

	public String getQ_enrolledDateS() {return checkGet(q_enrolledDateS);	}
	public void setQ_enrolledDateS(String q_enrolledDateS) {this.q_enrolledDateS = checkSet(q_enrolledDateS);	}
	public String getQ_enrolledDateE() {return checkGet(q_enrolledDateE);	}
	public void setQ_enrolledDateE(String q_enrolledDateE) {this.q_enrolledDateE = checkSet(q_enrolledDateE);	}
	public String getQ_notifierRepDateS() {return checkGet(q_notifierRepDateS);}
	public void setQ_notifierRepDateS(String qNotifierRepDateS) {q_notifierRepDateS = checkSet(qNotifierRepDateS);}
	public String getQ_notifierRepDateE() {return checkGet(q_notifierRepDateE);}
	public void setQ_notifierRepDateE(String qNotifierRepDateE) {q_notifierRepDateE = checkSet(qNotifierRepDateE);}
	public String getQ_notifierDept() {return checkGet(q_notifierDept);}
	public void setQ_notifierDept(String qNotifierDept) {q_notifierDept = checkSet(qNotifierDept);}
	public String getQ_ingredient() {return checkGet(q_ingredient);}
	public void setQ_ingredient(String qIngredient) {q_ingredient = checkSet(qIngredient);}
	public String getQ_applicationName() {return checkGet(q_applicationName);}
	public void setQ_applicationName(String qApplicationName) {q_applicationName = checkSet(qApplicationName);}
	public String getQ_manufactorName() {return checkGet(q_manufactorName);}
	public void setQ_manufactorName(String qManufactorName) {q_manufactorName = checkSet(qManufactorName);}
	public String getQ_manufactorNo() {return checkGet(q_manufactorNo);}
	public void setQ_manufactorNo(String qManufactorNo) {q_manufactorNo = checkSet(qManufactorNo);}
	public String[] getQ_drgLev() {return q_drgLev;}
	public void setQ_drgLev(String[] qdrgLev) {q_drgLev = qdrgLev;}
	public String getQ_survey() {return checkGet(q_survey);}
	public void setQ_survey(String qSurvey) {q_survey = checkSet(qSurvey);}	
	public String[] getQ_mainCode() {return q_mainCode;}
	public void setQ_mainCode(String[] qMainCode) {q_mainCode = qMainCode;}
	public String[] getQ_subCode() {return q_subCode;}
	public void setQ_subCode(String[] qSubCode) {q_subCode = qSubCode;}
	
	public String getQ_medNti() {return checkGet(q_medNti);}
	public void setQ_medNti(String qMedNti) {q_medNti = checkSet(qMedNti);}
	public String getQ_medAtcCode() {return checkGet(q_medAtcCode);}
	public void setQ_medAtcCode(String qMedAtcCode) {q_medAtcCode = checkSet(qMedAtcCode);}
	public String[] getQ_conSequence4() {return checkGet(q_conSequence4);}
	public void setQ_conSequence4(String[] qConSequence4) {q_conSequence4 = checkSet(qConSequence4);}
	public String getQ_effectChangeDesc4() {return checkGet(q_effectChangeDesc4);}
	public void setQ_effectChangeDesc4(String qEffectChangeDesc4) {q_effectChangeDesc4 = checkSet(qEffectChangeDesc4);}
	public String getQ_badReactionLev4() {return checkGet(q_badReactionLev4);}
	public void setQ_badReactionLev4(String qBadReactionLev4) {q_badReactionLev4 = checkSet(qBadReactionLev4);}
	public String getQ_badReactionDesc4() {return checkGet(q_badReactionDesc4);}
	public void setQ_badReactionDesc4(String qBadReactionDesc4) {q_badReactionDesc4 = checkSet(qBadReactionDesc4);}
	public String getQ_badReactionDra() {return checkGet(q_badReactionDra);}
	public void setQ_badReactionDra(String qBadReactionDra) {q_badReactionDra = checkSet(qBadReactionDra);}
	public String getQ_assessResult() {return checkGet(q_assessResult);}
	public void setQ_assessResult(String qAssessResult) {q_assessResult = checkSet(qAssessResult);}
	public String getQ_status2() {return checkGet(q_status2);}
	public void setQ_status2(String qStatus2) {q_status2 = checkSet(qStatus2);}
	
	
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
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}
	
	@Override
	public Object doQueryAll() throws Exception {		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		StringBuilder sb = new StringBuilder();
		sb.append(" from DrgView where 1=1 ");
		if(!"".equals(Common.get(getQ_drgType()))){
			String q_drgTypes = "";
			for(String o: getQ_drgType()){
				if(q_drgTypes.length() > 0)	q_drgTypes += ",";
				q_drgTypes += Common.sqlChar(o);
			}
			sb.append(" and caseType in (" + q_drgTypes + ")");
		}
		
		if(!"".equals(getQ_applNoS()) && !"".equals(getQ_applNoE())) 
			sb.append(" and applNo >= " +  Common.sqlChar(getQ_applNoS())+ " and applNo <= " + Common.sqlChar(getQ_applNoE()));
		else if(!"".equals(getQ_applNoS()) && "".equals(getQ_applNoE()))
			sb.append(" and applNo like " + Common.sqlChar("%"+getQ_applNoS()+"%"));
		else if("".equals(getQ_applNoS()) && !"".equals(getQ_applNoE()))
			sb.append(" and applNo like " + Common.sqlChar("%"+getQ_applNoE()+"%"));		
		
		if(!"".equals(getQ_occurDateS()))			
			sb.append(" and occurDate >= " + Common.sqlChar(getQ_occurDateS()));		
		if(!"".equals(getQ_occurDateE()))			
			sb.append(" and occurDate <= " + Common.sqlChar(getQ_occurDateE()));		
		if(!"".equals(getQ_permitKey()))			
			sb.append(" and permitKey = " + Common.sqlChar(getQ_permitKey()));		
		if(!"".equals(getQ_permitNo()))			
			sb.append(" and permitNo = " + Common.sqlChar(getQ_permitNo()));		
		if(!"".equals(getQ_Product()))			
			sb.append(" and (chProduct like " + Common.sqlChar("%"+getQ_Product()+"%")+" or enProduct like "+ Common.sqlChar("%"+getQ_Product()+"%")+" )");
		if(!"".equals(getQ_enrolledDateS()))
			sb.append(" and enrolledDate >= " + Common.sqlChar(getQ_enrolledDateS()));
		if(!"".equals(getQ_enrolledDateE()))
			sb.append(" and enrolledDate <= " + Common.sqlChar(getQ_enrolledDateE()));
		if(!"".equals(getQ_notifierRepDateS()))
			sb.append(" and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS()));
		if(!"".equals(getQ_notifierRepDateE()))
			sb.append(" and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE()));
		if(!"".equals(getQ_notifierDept()))
			sb.append(" and notifierDept like " + Common.sqlChar("%"+getQ_notifierDept()+"%"));
		if(!"".equals(getQ_ingredient()))
			sb.append(" and ingredient like " + Common.sqlChar("%"+getQ_ingredient()+"%"));
		if(!"".equals(getQ_applicationName()))
			sb.append(" and applicationName like " + Common.sqlChar("%"+getQ_applicationName()+"%"));
		if(!"".equals(getQ_manufactorName()))
			sb.append(" and manufactorName like " + Common.sqlChar("%"+getQ_manufactorName()+"%"));
		if(!"".equals(getQ_manufactorNo()))
			sb.append(" and manufactorNo = " + Common.sqlChar(getQ_manufactorNo()));			
		if(!"".equals(Common.get(getQ_drgLev()))){				
			String drgLevList ="";
			for(String o: getQ_drgLev()){
				drgLevList += Common.sqlChar(o)+",";
			}
			if(drgLevList !="") drgLevList = drgLevList.substring(0, drgLevList.length()-1);
			sb.append(" and ((caseType = '01' and drgLev in ("+drgLevList+")) or (caseType = '02' and  drgLev is null or drgLev = '' )) ");
		}
		if(!"".equals(getQ_survey())){
			sb.append(" and ((caseType = '01' and survey ="+Common.sqlChar(getQ_survey())).append(") or (caseType = '02' and  survey is null or survey = '' ))");
		}
		if(!"".equals(Common.get(getQ_subCode()))){
			sb.append(" and ((caseType = '01' and ( ");
			for(int i=0;i<getQ_subCode().length;i++){
				if(i > 0)sb.append(" or ");
				sb.append(" subCode like " ).append(Common.likeSqlChar(getQ_subCode()[i]));
			}
			sb.append(")) or (caseType = '02' and  subCode is null or subCode = '')) ");
		}
		if(!"".equals(getQ_status1())){
	    	sb.append(" and ((caseType = '01' and status = " + Common.sqlChar(getQ_status1())).append(") or (caseType ='02' and status is not null and status <> ''))");
	    }
		if(!"".equals(getQ_medNti())){
			sb.append(" and ((caseType ='02' and medNti = " + Common.sqlChar(getQ_medNti())).append(") or (caseType ='01' and medNti is null or medNti = '')) ");
		}
		if(!"".equals(getQ_medAtcCode())){
			sb.append(" and ((caseType ='02' and medAtcCode like " + Common.likeSqlChar(getQ_medAtcCode())).append(") or (caseType ='01' and medAtcCode is null or medAtcCode = '')) ");
		}
		if(null != getQ_conSequence4() && !"".equals(getQ_conSequence4())){	
			
			sb.append(" and ((caseType = '02' and ( ");
			for(int i=0;i<getQ_conSequence4().length;i++){
				if(i > 0)sb.append(" or ");
				sb.append(" conSequence like " ).append(Common.likeSqlChar(getQ_conSequence4()[i]));
			}
			sb.append(" )) or (caseType ='01' and conSequence is null or conSequence = '')) ");
			/**
			if("1".equals(getQ_conSequence4())){			
				if(!"".equals(getQ_effectChangeDesc4())){				
					sb.append(" and ((caseType ='02' and effectChangeDesc = " + Common.sqlChar(getQ_effectChangeDesc4())).append(") or (caseType ='01' and effectChangeDesc is null or effectChangeDesc = '')) ");
				}
			}else{
				if(!"".equals(getQ_badReactionLev4())){
					sb.append(" and ((caseType ='02' and badReactionLev = " + Common.sqlChar(getQ_badReactionLev4())).append(") or (caseType ='01' and badReactionLev is null or badReactionLev = '')) ");				
				}
				if(!"".equals(getQ_badReactionDesc4())){
					sb.append(" and ((caseType ='02' and badReactionDesc like " + Common.likeSqlChar(getQ_badReactionDesc4())).append(") or (caseType ='01' and badReactionDesc is null or badReactionDesc = '')) ");
				}
				if(!"".equals(getQ_badReactionDra())){
					sb.append(" and ((caseType ='02' and badReactionDra = " + Common.sqlChar(getQ_badReactionDra())).append(") or (caseType ='01' and badReactionDra is null or badReactionDra = '')) ");	
				}
			}
			**/
		}	
		if(!"".equals(getQ_assessResult())){
			sb.append(" and ((caseType ='02' and assessResult = " + Common.sqlChar(getQ_badReactionLev4())).append(") or (caseType ='01' and assessResult is null or assessResult = '')) ");	
		}
		
	    if(!"".equals(getQ_status2())){
	    	sb.append(" and ((caseType = '02' and status = " + Common.sqlChar(getQ_status2())).append(") or (caseType ='01' and status is not null and status <> '')) ");
	    }
	    System.out.println(sb.toString());
		java.util.List<DrgView> objList = ServiceGetter.getInstance().getTcbwService().load(sb.toString());
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 	ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			objList = ServiceGetter.getInstance().getTcbwService().load( sb.toString() + " order by id ", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> status01Map = TCBWCommon.getCommonCodeMap("DRGST1");
				java.util.Map<String, String> status02Map = TCBWCommon.getCommonCodeMap("DRG0310");
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID"); //許可證字
				java.util.Map<String, String> mainCodeMap = TCBWCommon.getCommonCodeMap("DRG0105"); //不良品主代碼
				java.util.Map<String, String> subCodeMap = new java.util.HashMap<String, String>(); //不良品缺陷子代碼
				
				for(DrgView dtl:objList){
					String[] rowArray = new String[12];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getCaseType());
					rowArray[2] = "";
					if("01".equals(dtl.getCaseType())){
						rowArray[2] = "藥品不良品";
					}else if("02".equals(dtl.getCaseType())){
						rowArray[2] = "藥品療效不等";
					}
					rowArray[3] = Common.get(dtl.getNotifierRepDate());
					rowArray[4] = Common.get(dtl.getApplNo());
					rowArray[5] = Common.get(pkidMap.get(dtl.getPermitKey()))+"-"+Common.get(dtl.getPermitNo());
					rowArray[6] = Common.get(dtl.getChProduct());
					rowArray[7] = Common.get(dtl.getApplicationName());
					rowArray[8] = Common.get(dtl.getManufactorName());
					rowArray[9] = "";
					if("01".equals(dtl.getCaseType())){
						List<Drg0002Db> drg02List = ServiceGetter.getInstance().getCommonService().load(" from Drg0002Db where drg0001Db.id ="+dtl.getId());
						if(null != drg02List && !drg02List.isEmpty()){
							String mainCode02="",subCode02="";
							for(Drg0002Db drg02:drg02List){
								if(mainCode02.length() > 0)	mainCode02 += ",";
								mainCode02 +=  mainCodeMap.get(drg02.getMainCode());
								String[] subList = drg02.getSubCode().split(",");
								for(int j=0; j<subList.length; j++){
									if(Common.get(subList[j]) != "" && !"".equals(Common.get(subCodeMap.get(subList[j])))){
										if(subCode02.length() > 0)	subCode02 += ",";
										subCode02 += subCodeMap.get(subList[j]);
									}
								}
							}
							rowArray[9] = Common.get(mainCode02+" "+subCode02);
						}
					}else if("02".equals(dtl.getCaseType())){
						if(StringUtils.contains(dtl.getConSequence(), "1")) {
							if(Common.get(rowArray[9]).length() > 0) rowArray[9] += ",";
							rowArray[9] += "藥效改變";
						}
						if(StringUtils.contains(dtl.getConSequence(), "2")) {
							if(Common.get(rowArray[9]).length() > 0) rowArray[9] += ",";
							rowArray[9] += "不良反應發生、強度增強或頻率增加";
						}
					}
					
					rowArray[10] = Common.get(dtl.getManufactorNo());
					rowArray[11] = "";
					if("01".equals(dtl.getCaseType())){
						rowArray[11] = Common.get(status01Map.get(dtl.getStatus()));
					}else if("02".equals(dtl.getCaseType())){
						rowArray[11] = Common.get(status02Map.get(dtl.getStatus()));
					}
					arrList.add(rowArray);
				}
				if(mainCodeMap != null) mainCodeMap.clear();
				if(pkidMap != null) pkidMap.clear();
				if(subCodeMap != null) subCodeMap.clear();
				if(status01Map != null) status01Map.clear();
				if(status02Map != null) status02Map.clear();
			}
		}
		return arrList;
	}	

	// 不良品查詢條件
	public String getDrg01Hql() throws Exception{
		StringBuilder sb = new StringBuilder();
				
		if(!"".equals(getQ_applNoS()) && !"".equals(getQ_applNoE())) 
			sb.append(" and a.applNo >= " +  Common.sqlChar(getQ_applNoS())+ " and a.applNo <= " + Common.sqlChar(getQ_applNoE()));
		else if(!"".equals(getQ_applNoS()) && "".equals(getQ_applNoE()))
			sb.append(" and a.applNo like " + Common.sqlChar("%"+getQ_applNoS()+"%"));
		else if("".equals(getQ_applNoS()) && !"".equals(getQ_applNoE()))
			sb.append(" and a.applNo like " + Common.sqlChar("%"+getQ_applNoE()+"%"));
		
		if(!"".equals(getQ_occurDateS()))			
			sb.append(" and a.occurDate >= " + Common.sqlChar(getQ_occurDateS()));		
		if(!"".equals(getQ_occurDateE()))			
			sb.append(" and a.occurDate <= " + Common.sqlChar(getQ_occurDateE()));		
		if(!"".equals(getQ_permitKey()))			
			sb.append(" and a.permitKey = " + Common.sqlChar(getQ_permitKey()));		
		if(!"".equals(getQ_permitNo()))			
			sb.append(" and a.permitNo = " + Common.sqlChar(getQ_permitNo()));		
		if(!"".equals(getQ_Product()))			
			sb.append(" and ( a.chProduct like " + Common.sqlChar("%"+getQ_Product()+"%")+		           
			       " or a.enProduct like "+ Common.sqlChar("%"+getQ_Product()+"%")+" )");
		if(!"".equals(getQ_enrolledDateS()))
			sb.append(" and a.enrolledDate >= " + Common.sqlChar(getQ_enrolledDateS()));
		if(!"".equals(getQ_enrolledDateE()))
			sb.append(" and a.enrolledDate <= " + Common.sqlChar(getQ_enrolledDateE()));
		if(!"".equals(getQ_notifierRepDateS()))
			sb.append(" and a.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS()));
		if(!"".equals(getQ_notifierRepDateE()))
			sb.append(" and a.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE()));
		if(!"".equals(getQ_notifierDept()))
			sb.append(" and a.notifierDept like " + Common.sqlChar("%"+getQ_notifierDept()+"%"));
		if(!"".equals(getQ_ingredient()))
			sb.append(" and a.ingredient like " + Common.sqlChar("%"+getQ_ingredient()+"%"));
		if(!"".equals(getQ_applicationName()))
			sb.append(" and a.applicationName like " + Common.sqlChar("%"+getQ_applicationName()+"%"));
		if(!"".equals(getQ_manufactorName()))
			sb.append(" and a.manufactorName like " + Common.sqlChar("%"+getQ_manufactorName()+"%"));
		if(!"".equals(getQ_manufactorNo()))
			sb.append(" and a.manufactorNo = " + Common.sqlChar(getQ_manufactorNo()));			
		if(!"".equals(Common.get(getQ_drgLev()))){				
			String drgLevList ="";
			for(String o: getQ_drgLev()){
				drgLevList += Common.sqlChar(o)+",";
			}
			if(drgLevList !="") drgLevList = drgLevList.substring(0, drgLevList.length()-1);
			sb.append(" and a.applNo in ( select c.applNo from Drg0004Db c where c.drgLev in ("+drgLevList+") )");
		}
		if(!"".equals(getQ_survey()))
			sb.append(" and a.applNo in ( select d.applNo from Drg0006Db d where d.survey ="+Common.sqlChar(getQ_survey())+" )");
		if(!"".equals(Common.get(getQ_mainCode()))){
			sb.append(" and a.id in ( select b.drg0001Db.id from Drg0002Db b where ");
			for(int i=0;i<getQ_mainCode().length;i++){
				if(i!=0) sb.append(" or ");
				sb.append(" ( b.mainCode ="+Common.sqlChar(getQ_mainCode()[i]));					
				String subCodeList ="";
				if(!"".equals(Common.get(getQ_subCode()))){
					for(int j=0;j<getQ_subCode().length;j++)							
					{							
						//抓取第二層資料(前2碼與第一層相同)								
						if(Common.get(getQ_mainCode()[i]).equals(Common.get(getQ_subCode()[j]).substring(0,2))){								
							subCodeList += Common.sqlChar(getQ_subCode()[j])+",";								
						}							
					}
					if(subCodeList != ""){
						subCodeList = subCodeList.substring(0,subCodeList.length()-1);
						sb.append(" and b.subCode in ("+subCodeList+")");
					}
				}
				sb.append(")");
			}
			sb.append(")");
		}
	    if(!"".equals(getQ_status1()))
	    	sb.append(" and a.status = " + Common.sqlChar(getQ_status1()));
		return sb.toString();
	}
	
	// 療效不等查詢條件
	public String getDrg02Hql() throws Exception{
		StringBuilder sb = new StringBuilder();
		
		if(!"".equals(getQ_applNoS()) && !"".equals(getQ_applNoE())) 
			sb.append(" and a.applNo >= " +  Common.sqlChar(getQ_applNoS())+ " and a.applNo <= " + Common.sqlChar(getQ_applNoE()));
		else if(!"".equals(getQ_applNoS()) && "".equals(getQ_applNoE()))
			sb.append(" and a.applNo like " + Common.sqlChar("%"+getQ_applNoS()+"%"));
		else if("".equals(getQ_applNoS()) && !"".equals(getQ_applNoE()))
			sb.append(" and a.applNo like " + Common.sqlChar("%"+getQ_applNoE()+"%"));
		
		if(!"".equals(getQ_occurDateS()))			
			sb.append(" and a.occurDate >= " + Common.sqlChar(getQ_occurDateS()));		
		if(!"".equals(getQ_occurDateE()))			
			sb.append(" and a.occurDate <= " + Common.sqlChar(getQ_occurDateE()));
		if(!"".equals(getQ_enrolledDateS()))
			sb.append(" and a.enrolledDate >= " + Common.sqlChar(getQ_enrolledDateS()));
		if(!"".equals(getQ_enrolledDateE()))
			sb.append(" and a.enrolledDate <= " + Common.sqlChar(getQ_enrolledDateE()));
		if(!"".equals(getQ_notifierRepDateS()))
			sb.append(" and a.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS()));
		if(!"".equals(getQ_notifierRepDateE()))
			sb.append(" and a.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE()));
		if(!"".equals(getQ_notifierDept()))
			sb.append(" and a.notifierDept like " + Common.sqlChar("%"+getQ_notifierDept()+"%"));
		
		if(!"".equals(getQ_permitKey()))			
			sb.append(" and a.id in ( select c.drg4001Db.id from Drg4003Db c where medType='02' and c.permitKey = " + Common.sqlChar(getQ_permitKey())+" )");		
		if(!"".equals(getQ_permitNo()))			
			sb.append(" and a.id in ( select c.drg4001Db.id from Drg4003Db c where medType='02' and c.permitNo = " + Common.sqlChar(getQ_permitNo())+" )");	
		if(!"".equals(getQ_Product()))			
			sb.append(" and a.id in ( select c.drg4001Db.id from Drg4003Db c where medType='02' and c.productName like " + Common.sqlChar("%"+getQ_Product()+"%")+" )");
		if(!"".equals(getQ_applicationName()))
			sb.append(" and a.id in ( select c.drg4001Db.id from Drg4003Db c where medType='02' and c.applicationName like " + Common.sqlChar("%"+getQ_applicationName()+"%")+" )");
		if(!"".equals(getQ_manufactorName()))
			sb.append(" and a.id in ( select c.drg4001Db.id from Drg4003Db c where medType='02' and c.manufactorName like " + Common.sqlChar("%"+getQ_manufactorName()+"%")+" )");
		if(!"".equals(getQ_manufactorNo()))
			sb.append(" and a.id in ( select c.drg4001Db.id from Drg4003Db c where medType='02' and c.manufactorNo = " + Common.sqlChar(getQ_manufactorNo())+" )");
		if(!"".equals(getQ_ingredient()))
			sb.append(" and a.id in ( select c.drg4001Db.id from Drg4003Db c where medType='02' and c.scientificName like " + Common.sqlChar("%"+getQ_ingredient()+"%")+" )");
		
		if(!"".equals(getQ_medNti()))
		    sb.append(" and a.applNo in ( select d.applNo from Drg4004Db d where d.medNti = " + Common.sqlChar(getQ_manufactorNo())+" )");
		if(!"".equals(getQ_medAtcCode()))
			sb.append(" and a.applNo in ( select d.applNo from Drg4004Db d where d.medAtcCode = " + Common.sqlChar(getQ_medAtcCode())+" )");
		
		if(!"".equals(Common.get(getQ_conSequence4()))){
			String conSequenceStr ="";
			for(int i=0;i<getQ_conSequence4().length;i++){
				if(i > 0) conSequenceStr+= " or ";
				conSequenceStr +=" d.conSequence like " +Common.likeSqlChar(getQ_conSequence4()[i]);
			}
			sb.append(" and a.applNo in ( select d.applNo from Drg4004Db d where " + Common.get(conSequenceStr)+" )");
			/**
			if("1".equals(getQ_conSequence4())){		
				if(!"".equals(getQ_effectChangeDesc4())){				
					sb.append(" and a.applNo in ( select d.applNo from Drg4004Db d where d.effectChangeDesc = " + Common.sqlChar(getQ_effectChangeDesc4())+" )");
				}
			}else{
				if(!"".equals(getQ_badReactionLev4())){
					sb.append(" and a.applNo in ( select d.applNo from Drg4004Db d where d.badReactionLev = " + Common.sqlChar(getQ_badReactionLev4())+" )");					
				}
				if(!"".equals(getQ_badReactionDesc4())){
					sb.append(" and a.applNo in ( select d.applNo from Drg4004Db d where d.badReactionDesc like " + Common.sqlChar("%"+getQ_badReactionDesc4()+"%")+" )");
				}
				if(!"".equals(getQ_badReactionDra())){
					sb.append(" and a.applNo in ( select d.applNo from Drg4004Db d where d.badReactionDra = " + Common.sqlChar(getQ_badReactionDra())+" )");
				}
			}
			**/
		}	
		if(!"".equals(getQ_assessResult()))
			sb.append(" and a.applNo in ( select d.drg4008Db.applNo from Drg4005Db d where d.badReactionLev = " + Common.sqlChar(getQ_badReactionLev4())+" )");		
		
	    if(!"".equals(getQ_status2()))
	    	sb.append(" and a.status = " + Common.sqlChar(getQ_status2()));
	    
		return sb.toString();
	}
	
	public DefaultTableModel getTableModel() throws Exception{
		DefaultTableModel model = null;
		String[] cols = new String[]{ "applNo","occurDate","notifierSource","notifierRevDate","notifierRepDate",
									  "notifierDept","notifierType","chProduct","enProduct","permitKey",
									  "permitNo","ingredient","content","medModel","medPackage",
									  "applicationName","manufactorName","manufactorCountry","manufactorNo","mainCode02",
									  "subCode02","otherDescribe02","firstResult","drgLev04","notifierAging03",
									  "notifierQuality03","historyData","checkResult07","survey07","precaution07",
									  "beforeOrLater05","survey09" };
		
		java.util.List<String[]> arrList = new java.util.ArrayList<String[]>();		

		String hql = " from Drg0001Db a where 1=1 ";		
	           hql += getDrg01Hql();		
        System.out.println("[TCBW]-[DRGIN0001F]-[不良品-QUERY_FOR_EXCEL] : " + hql);
		
		java.util.List lists = ServiceGetter.getInstance().getCommonService().load(hql);
		if (lists!=null && lists.size()>0) {
			
			java.util.List<Drg1001Db> drg1001Dbs = ServiceGetter.getInstance().getTcbwService().load("from Drg1001Db");
			java.util.Map<String, String> subCodeMap = new java.util.HashMap<String, String>(); //不良品缺陷子代碼
			if(drg1001Dbs!=null && drg1001Dbs.size()>0){
				for(Drg1001Db drg1001Db : drg1001Dbs){
					subCodeMap.put(drg1001Db.getDpdKind(), drg1001Db.getDpdKindName());
				}
				drg1001Dbs.clear();
			}
			java.util.Map<String, String> notifierTypeCodeMap = TCBWCommon.getCommonCodeMap("CONNFT1"); //通報者屬性
			java.util.Map<String, String> mainCodeMap = TCBWCommon.getCommonCodeMap("DRG0105"); //不良品主代碼
			java.util.Map<String, String> nfsMap = TCBWCommon.getCommonCodeMap("DRGNFS"); //風險評估結果
			java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID"); //許可證字
			java.util.Map<String, String> flnMap = TCBWCommon.getCommonCodeMap("DRGFLN"); //劑型
			java.util.Map<String, String> drg0102Map = TCBWCommon.getCommonCodeMap("DRG0102"); //包裝形式
			java.util.Map<String, String> drg0106Map = TCBWCommon.getCommonCodeMap("DRG0106"); //不良品原因初評
			java.util.Map<String, String> rklMap = TCBWCommon.getCommonCodeMap("DRGRKL"); //風險評估結果
			

			
			for (Object drg01Obj : lists){
				Drg0001Db obj = (Drg0001Db) drg01Obj;
				
				//抓取2檔不良品缺陷
				String mainCode02="",subCode02="",otherDescribe02 ="",subCodeList ="";;			
				if (obj.getDrg0002Dbs()!=null && obj.getDrg0002Dbs().size()>0){
					for (Object drg02Obj : obj.getDrg0002Dbs()) {
						Drg0002Db obj02 = (Drg0002Db) drg02Obj;
						mainCode02 +=  mainCodeMap.get(obj02.getMainCode())+",";
						otherDescribe02 += Common.get(obj02.getOtherDescribe());
						String[] subList = obj02.getSubCode().split(",");
						for(int j=0; j<subList.length; j++){
							if(!"".equals(Common.get(subList[j]))){
								subCode02 += subCodeMap.get(subList[j])+",";
								subCodeList += "subCode like ('%"+subList[j]+"%') or ";
							}
						}
					}
				}
				if(subCodeList.length()>3) subCodeList = subCodeList.substring(0, subCodeList.length()-3);
				if(mainCode02 != "") mainCode02 = mainCode02.substring(0, mainCode02.length()-1);
				if(subCode02 != "") subCode02 = subCode02.substring(0, subCode02.length()-1);				
				if(otherDescribe02.length()>0) otherDescribe02 = otherDescribe02.substring(0, otherDescribe02.length()-1);
				
				Drg0003Db obj03 = (Drg0003Db)View.getObject(" from Drg0003Db where applNo="+Common.sqlChar(obj.getApplNo())+" order by id desc");
				String notifierAging="",notifierQuality="";
				if(obj03 != null){
					//通報時效: 1.時效佳            2.時效待加強
					notifierAging = !"".equals(Common.get(obj03.getNotifierAging()))?(obj03.getNotifierAging().equals("1")?"時效佳":obj03.getNotifierAging().equals("2")?"時效待加強":""):"";
					//通報品質: 1.Excellent 2.Good 3.Fair
					notifierQuality =  !"".equals(Common.get(obj03.getNotifierQuality()))?(
						              obj03.getNotifierQuality().equals("1")?"Excellent":
						              obj03.getNotifierQuality().equals("2")?"Good":
						              obj03.getNotifierQuality().equals("3")?"Fair":""):"";
				}
				String drgLev = View.getLookupField("select drgLev from Drg0004Db where applNo="+Common.sqlChar(obj.getApplNo()));
				Drg0007Db obj07 = (Drg0007Db)View.getObject(" from Drg0007Db where applNo="+Common.sqlChar(obj.getApplNo())+" order by id desc");
				String beforeOrLater = View.getLookupField("select beforeOrLater from Drg0005Db where applNo="+Common.sqlChar(obj.getApplNo()));
				if(beforeOrLater!=null) beforeOrLater = beforeOrLater.equals("1")?"前次CAPA執行前製造":beforeOrLater.equals("2")?"前次CAPA執行後製造":"";
				String survey = View.getLookupField("select survey from Drg0009Db where applNo="+Common.sqlChar(obj.getApplNo()));
				String historyData ="";
				if(!"".equals(Common.get(obj.getPermitKey())) && !"".equals(Common.get(obj.getPermitNo()))){
				StringBuilder sb = new StringBuilder(); 
			    //歷年本藥品之通報件數
			    String hql1 = " from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo());		     
			    int hisData1 = ServiceGetter.getInstance().getCommonService().loadCount(hql1);			    
			    sb.append("歷年本藥品之通報件數："+Common.get(hisData1)+"\n");
	            
			    //歷年本藥品同此次瑕疵之通報件數
			    int hisData2 = 0;
			    if(subCodeList.length()>0){			    
			    	String hql2 = " from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+
			                      " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
			        hisData2 = ServiceGetter.getInstance().getCommonService().loadCount(hql2);
			    }
			    sb.append("歷年本藥品同此次瑕疵之通報件數："+Common.get(hisData2)+"\n");
			     
			    //一年內本藥品之通報件數
			    String hql3 = " from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+
			                  " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()));
	            int hisData3 = ServiceGetter.getInstance().getCommonService().loadCount(hql3);
	            sb.append("一年內本藥品之通報件數："+Common.get(hisData3)+"\n");
	             
	            //一年內本藥品同此次瑕疵之通報件數
	            int hisData4 = 0;
	            if(subCodeList.length()>0){			    
	            	String hql4 = " from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+
			                      " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
			                      " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";	            
	            	hisData4 = ServiceGetter.getInstance().getCommonService().loadCount(hql4);
	            }
	            sb.append("一年內本藥品同此次瑕疵之通報件數："+Common.get(hisData4)+"\n");
	             
	            //一年內本藥品同此次瑕疵之高風險通報件數
	            int hisData5 = 0;
	            if(subCodeList.length()>0){				    
	            	String hql5 = " from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+
			                      " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))"+
	                              " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
	                              " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";			    
	            	hisData5 = ServiceGetter.getInstance().getCommonService().loadCount(hql5);
	            }
	            sb.append("一年內本藥品同此次瑕疵之高風險通報件數："+Common.get(hisData5)+"\n");

	            //一年內本藥品同此次瑕疵案件
	            String hql6 = " select manufactorNo,count(applNo) from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+
	                          " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
	                          " group by manufactorNo";
	            java.util.List hql6List = ServiceGetter.getInstance().getCommonService().load(hql6);
	            String hisData6 ="";
			    if(hql6List!=null && hql6List.size()>0)				
			    {
				    for (int i=0; i<hql6List.size(); i++) {
						Object[] o = (Object[]) hql6List.get(i);
						hisData6 += "["+o[0]+"] : "+o[1]+"件 ,";
					}	    	 
			    }
			    if(hisData6 != "") hisData6 = hisData6.substring(0,hisData6.length()-1);
			    else hisData6 = "0 件";
			    sb.append("一年內本藥品同此次瑕疵案件："+Common.get(hisData6)+"\n");
			    
	            //一年內本藥品同此次瑕疵之高風險案件
			    java.util.List hql7List = null;
			    String hisData7 ="";
			    if(subCodeList.length()>0){	            
			    	String hql7 = " select manufactorNo,count(applNo) from Drg0001Db where permitKey="+Common.sqlChar(obj.getPermitKey())+" and permitNo="+Common.sqlChar(obj.getPermitNo())+
	                              " and notifierRepDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
	                              " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))"+
	                              " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))"+
	                              " group by manufactorNo"; 	            
			    	hql7List = ServiceGetter.getInstance().getCommonService().load(hql7);	            
			    }
	            if(hql7List!=null && hql7List.size()>0)
	            {	
	            	for (int i=0; i<hql7List.size(); i++) {		
	            		Object[] o = (Object[]) hql7List.get(i);	
	            		hisData7 += "["+o[0]+"] : "+o[1]+"件 ,";
	            	}
	            }
	            if(hisData7 != "") hisData7 = hisData7.substring(0,hisData7.length()-1);
	            else hisData7 = "0 件";
	            sb.append("一年內本藥品同此次瑕疵之高風險案件："+Common.get(hisData7)+"\n");
				
	            historyData = Common.get(sb.toString());
				}
				String row[] = new String[cols.length];
				row[0] = Common.get(obj.getApplNo());  //案件編號
				row[1] = Common.formatYYYMMDD(obj.getOccurDate(),4);  //發生日期
				row[2] = Common.get(nfsMap.get(obj.getNotifierSource()));  //通報來源
				row[3] = Common.formatYYYMMDD(obj.getNotifierRepDate(),4);  //接獲通報日期
				row[4] = Common.formatYYYMMDD(obj.getEnrolledDate(),4);  //收案日期
				row[5] = Common.get(obj.getNotifierDept());  //服務機構
				row[6] = Common.get(notifierTypeCodeMap.get(obj.getNotifierType()));  //醫療機構類別
				row[7] = Common.get(obj.getChProduct());  //中文品名
				row[8] = Common.get(obj.getEnProduct());  //英文品名
				row[9] = Common.get(pkidMap.get(obj.getPermitKey()));  //許可證字
				row[10] = Common.get(obj.getPermitNo());  //許可證號
				row[11] = Common.get(obj.getIngredient());  //有效成分名稱
				row[12] = Common.get(obj.getContent());  //有效成分單位含量
				row[13] = Common.get(flnMap.get(obj.getMedModel()));  //劑型
				row[14] = Common.get(drg0102Map.get(obj.getMedPackage()));  //包裝形式
				row[15] = Common.get(obj.getApplicationName());  //申請商
				row[16] = Common.get(obj.getManufactorName());  //製造商
				row[17] = Common.get("");                       //製造廠國別
				row[18] = Common.get(obj.getManufactorNo());  //製造批號
				row[19] = Common.get(mainCode02);  //不良品缺陷(粗分)
				row[20] = Common.get(subCode02);  //不良品缺陷(細分)
				row[21] = Common.get(otherDescribe02);  //不良品缺陷描述
				row[22] = Common.get(drg0106Map.get(obj.getFirstResult()));  //不良品原因初評
				row[23] = Common.get(rklMap.get(drgLev));  //風險評估結果
				row[24] = Common.get(notifierAging);  //通報時效
				row[25] = Common.get(notifierQuality);  //通報品質
				row[26] = Common.get(historyData);  //歷史通報資料摘要
				row[27] = Common.get(obj07!=null?obj07.getCheckResult():"");  //清查結果
				row[28] = Common.get(obj07!=null?obj07.getSurvey():"");  //調查結果
				row[29] = Common.get(obj07!=null?obj07.getPrecaution():"");  //預防矯正措施及改善時程
				row[30] = Common.get(beforeOrLater);  //不良品改進狀況(CAPA前/後)
				row[31] = Common.get(survey);  //不良品原因分析(Final)
				
				arrList.add(row);
				
			}
			if(mainCodeMap != null) mainCodeMap.clear();
			if(subCodeMap != null) subCodeMap.clear();
			if(pkidMap != null) pkidMap.clear();
			if(flnMap != null) flnMap.clear();
			if(drg0102Map != null) drg0102Map.clear();
			if(drg0106Map != null) drg0106Map.clear();
			if(rklMap != null) rklMap.clear();
			if(nfsMap != null) nfsMap.clear();
		}		
		if(null != arrList && arrList.size() >0){
			String[][] rs = new String[0][0];
			rs = arrList.toArray(rs);
			model = new DefaultTableModel();
			model.setDataVector(rs, cols);
		}
		return model;
	}
	
	
	public DefaultTableModel getTableModel2() throws Exception{
		DefaultTableModel model2 = null;
		String[] cols =null;
		if("1".equals(getPrintType())){	//匯出格式(1)	
			cols = new String[]{ "applNo","notifierRepDate", "conSequence","scientificName","medNti",
					  "productName1","permitKey1","permitNo1","productName2","permitKey2",
					  "permitNo2","dealWith","badReactionDesc04","badReactionDra04","engageKind",
					  "notifierSource","assessResult04"};
		}else if("2".equals(getPrintType())){  //匯出格式(2)
			cols = new String[]{ "applNo","notifierRepDate", "conSequence","scientificName","medNti",
					  "medAtcCode","productName1","permitKey1","permitNo1","dosage1",
					  "frequency1","productName2","permitKey2","permitNo2","dosage2",
					  "frequency2","dealWith","badReactionDesc04","badReactionDra04","engageKind",
					  "notifierSource","assessResult04"};
		}else{  //匯出格式(全)
			cols = new String[]{ "applNo","patientId","notifierDept","notifierRevDate","notifierRepDate",
					  "occurDate","conSequence","scientificName","medNti","medAtcCode",
					  "beforeDesc","changeDesc","occurDesc","afterDesc","productName1",
					  "permitKey1","permitNo1","startDare1","endDate1","medPath1",
					  "dosage1","frequency1","indication1","applicationName1","manufactorName1",
					  "manufactorCountry1","manufactorNo1","medModel1","productName2","permitKey2",
					  "permitNo2","startDare2","endDate2","medPath2","dosage2",
					  "frequency2","indication2","applicationName2","manufactorName2","manufactorCountry2",
					  "manufactorNo2","medModel2","productName3","permitNo3","dealWith",
					  "isImproveYn","dressingAttitude","obedienceLev","conSequence04","badReactionLev04",
					  "badReactionDesc04","testDate","testItems","testNum","otherDesc",
					  "badReactionDra04","engageKind","notifierSource","assessResult04","notifierAging04","notifierQuality04"};
		}

        java.util.List<String[]> arrList = new java.util.ArrayList<String[]>();	
		
        String hql = " from Drg4001Db a where 1=1 ";		
	           hql += getDrg02Hql();		
        System.out.println("[TCBW]-[DRGIN0001F]-[療效不等-QUERYALL] : " + hql);
        java.util.List lists = ServiceGetter.getInstance().getCommonService().load(hql);
		
        if (lists!=null && lists.size()>0) {
        	java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID"); //許可證字
			java.util.Map<String, String> flnMap = TCBWCommon.getCommonCodeMap("DRGFLN"); //劑型
			java.util.Map<String, String> rkl2Map = TCBWCommon.getCommonCodeMap("DRG2RKL"); //風險評估結果
			java.util.Map<String, String> drg0301Map = TCBWCommon.getCommonCodeMap("DRG0301"); //發生事件後之處置
			java.util.Map<String, String> drg0302Map = TCBWCommon.getCommonCodeMap("DRG0302"); //醫師對換藥的態度
			java.util.Map<String, String> drg0303Map = TCBWCommon.getCommonCodeMap("DRG0303"); //病人服藥順從性
			java.util.Map<String, String> drg0304Map = TCBWCommon.getCommonCodeMap("DRG0304"); //給藥途徑
			java.util.Map<String, String> drg0305Map = TCBWCommon.getCommonCodeMap("DRG0305"); //劑量
			java.util.Map<String, String> drg0306Map = TCBWCommon.getCommonCodeMap("DRG0306"); //頻率
			java.util.Map<String, String> drg0308Map = TCBWCommon.getCommonCodeMap("DRG0308"); //不良反應等級
			java.util.Map<String, String> drgnfsMap = TCBWCommon.getCommonCodeMap("DRGNFS"); //不良反應等級

			
			
			        	
        	for (Object drg01Obj : lists){
        		Drg4001Db obj = (Drg4001Db) drg01Obj;
        		
        		
        		java.util.List<Drg4002Db> drg4002Dbs = ServiceGetter.getInstance().getTcbwService().load(" from Drg4002Db where drg4001Db.id="+Common.getLong(obj.getId()));
        		String testDate="",testItems="",testNum ="";
    			if(drg4002Dbs!=null && drg4002Dbs.size()>0){
    				for(Drg4002Db drg4002Db : drg4002Dbs){
    					testDate += Common.formatYYYMMDD(drg4002Db.getTestDate(),4)+",";
    					testItems += Common.get(drg4002Db.getTestItems())+",";
    					testNum += Common.get(drg4002Db.getTestNum())+",";
    				}
    				drg4002Dbs.clear();
    			}
        		if(testDate.length()>0) testDate = testDate.substring(0,testDate.length()-1);
        		if(testItems.length()>0) testItems = testItems.substring(0,testItems.length()-1);
        		if(testNum.length()>0) testNum = testNum.substring(0,testNum.length()-1);
        		
        		java.util.List<Drg4003Db> drg4003Dbs = ServiceGetter.getInstance().getTcbwService().load(" from Drg4003Db where medType='03' and drg4001Db.id="+Common.getLong(obj.getId()));
        		String productName3="",permitNo3="";
    			if(drg4003Dbs!=null && drg4003Dbs.size()>0){
    				for(Drg4003Db drg4003Db : drg4003Dbs){
    					productName3 += Common.get(drg4003Db.getProductName())+",";
    					permitNo3 += pkidMap.get(drg4003Db.getPermitKey())+Common.get(drg4003Db.getPermitNo())+",";
    				}
    				drg4002Dbs.clear();
    			}
        		if(productName3.length()>0) productName3 = productName3.substring(0,productName3.length()-1);
        		if(permitNo3.length()>0) permitNo3 = permitNo3.substring(0,permitNo3.length()-1);               
        		//懷疑療效
        		Drg4003Db obj02 = (Drg4003Db)View.getObject(" from Drg4003Db where medType='02' and drg4001Db.id="+Common.getLong(obj.getId()));
        		//事件前使用藥品
        		Drg4003Db obj03 = (Drg4003Db)View.getObject(" from Drg4003Db where medType='01' and drg4001Db.id="+Common.getLong(obj.getId()));
        		//分級資料檔
        		Drg4004Db obj04 = (Drg4004Db)View.getObject(" from Drg4004Db where applNo="+Common.sqlChar(obj.getApplNo()));
        		String notifierAging="",notifierQuality="";
        		if(obj04 != null){
        			//通報時效: 1.時效佳            2.時效待加強
					notifierAging = !"".equals(Common.get(obj04.getNotifierAging()))?(obj04.getNotifierAging().equals("1")?"時效佳":obj04.getNotifierAging().equals("2")?"時效待加強":""):"";
					//通報品質: 1.Excellent 2.Good 3.Fair
					notifierQuality =  !"".equals(Common.get(obj04.getNotifierQuality()))?(
						              obj04.getNotifierQuality().equals("1")?"Excellent":
						              obj04.getNotifierQuality().equals("2")?"Good":
						              obj04.getNotifierQuality().equals("3")?"Fair":""):"";
				}
        		
        		if("1".equals(getPrintType())){	//匯出格式(1)	
        			String row[] = new String[cols.length];	
        			
        			row[0] = Common.get(obj.getApplNo());  //案件編號        							
        			row[1] = Common.formatYYYMMDD(obj.getEnrolledDate(),4);  //收案日期 
        			if("1".equals(obj.getConSequence())) {
        				row[2] = "藥效改變";  //通報事件後果
        			} else if("2".equals(obj.getConSequence())) {
        				row[2] = "不良反應發生、強度增強或頻率增加";  //通報事件後果
        			} else if("1,2".equals(obj.getConSequence())) {
        				row[2] = "藥效改變；不良反應發生、強度增強或頻率增加";	//通報事件後果
        			} else {
        				row[2] = Common.get(obj.getConSequence());  //通報事件後果
        			}
        			row[3] = Common.get(obj02.getScientificName());  //學名
        			if("Y".equals(obj04.getMedNti())) {
        				row[4] = "是";	//NTI Drugs	
        			} else {
        				row[4] = "";	//NTI Drugs	
        			}
//        			row[4] = Common.get(obj04!=null?obj04.getMedNti():"");  //NTI Drugs			
        			row[5] = Common.get(obj03!=null?obj03.getProductName():"");  //事件前使用藥品-商品名				
        			row[6] = Common.get(obj03!=null?pkidMap.get(obj03.getPermitKey()):"");  //事件前使用藥品-許可證字				
        			row[7] = Common.get(obj03!=null?obj03.getPermitNo():"");  //事件前使用藥品-許可證號				
        			row[8] = Common.get(obj02!=null?obj02.getProductName():"");  //懷疑效不等藥品-商品名				
        			row[9] = Common.get(obj02!=null?pkidMap.get(obj02.getPermitKey()):"");  //懷疑效不等藥品-許可證字				
        			row[10] = Common.get(obj02!=null?obj02.getPermitNo():"");  //懷疑效不等藥品-許可證號		
        			row[11] = Common.get(drg0301Map.get(obj.getDealWith()));  //發生事件後之處置				
        			row[12] = Common.get(obj04!=null?obj04.getBadReactionDesc():"");  //不良反應狀況(初評表)				
        			row[13] = Common.get(obj04!=null?obj04.getBadReactionDra():"");  //MedDRA代碼				
        			row[14] = Common.get("");  //醫療機構類別				
        			row[15] = Common.get(drgnfsMap.get(obj.getNotifierSource()));  //通報來源				
        			row[16] = Common.get(obj04!=null?rkl2Map.get(obj04.getAssessResult()):"");  //療效不等評估結果				
        			
        			arrList.add(row);
        			
        		}else if("2".equals(getPrintType())){  //匯出格式(2)
        			String row[] = new String[cols.length];				
        			row[0] = Common.get(obj.getApplNo());  //案件編號        							
        			row[1] = Common.formatYYYMMDD(obj.getEnrolledDate(),4);  //收案日期        						
        			if("1".equals(obj.getConSequence())) {
        				row[2] = "藥效改變";  //通報事件後果
        			} else if("2".equals(obj.getConSequence())) {
        				row[2] = "不良反應發生、強度增強或頻率增加";  //通報事件後果
        			} else if("1,2".equals(obj.getConSequence())) {
        				row[2] = "藥效改變；不良反應發生、強度增強或頻率增加";	//通報事件後果
        			} else {
        				row[2] = Common.get(obj.getConSequence());  //通報事件後果
        			}				
        			row[3] = Common.get(obj02.getScientificName());  //學名		
        			if("Y".equals(obj04.getMedNti())) {
        				row[4] = "是";	//NTI Drugs	
        			} else {
        				row[4] = "";	//NTI Drugs	
        			}
//        			row[4] = Common.get(obj04!=null?obj04.getMedNti():"");  //NTI Drugs				
        			row[5] = Common.get(obj04!=null?obj04.getMedAtcCode():"");  //藥理治療分類			
        			row[6] = Common.get(obj03!=null?obj03.getProductName():"");  //事件前使用藥品-商品名				
        			row[7] = Common.get(obj03!=null?pkidMap.get(obj03.getPermitKey()):"");  //事件前使用藥品-許可證字				
        			row[8] = Common.get(obj03!=null?obj03.getPermitNo():"");  //事件前使用藥品-許可證號				
        			row[9] = Common.get(obj03!=null?drg0305Map.get(obj03.getDosage()):"");  //事件前使用藥品-劑量				
        			row[10] = Common.get(obj03!=null?drg0306Map.get(obj03.getFrequency()):"");  //事件前使用藥品-頻率					
        			row[11] = Common.get(obj02!=null?obj02.getProductName():"");  //懷疑效不等藥品-商品名				
        			row[12] = Common.get(obj02!=null?pkidMap.get(obj02.getPermitKey()):"");  //懷疑效不等藥品-許可證字				
        			row[13] = Common.get(obj02!=null?obj02.getPermitNo():"");  //懷疑效不等藥品-許可證號				
        			row[14] = Common.get(obj02!=null?drg0305Map.get(obj02.getDosage()):"");  //懷疑效不等藥品-劑量				
        			row[15] = Common.get(obj02!=null?drg0306Map.get(obj02.getFrequency()):"");  //懷疑效不等藥品-頻率			
        			row[16] = Common.get(drg0301Map.get(obj.getDealWith()));  //發生事件後之處置				
        			row[17] = Common.get(obj04!=null?obj04.getBadReactionDesc():"");  //不良反應狀況(初評表)				
        			row[18] = Common.get(obj04!=null?obj04.getBadReactionDra():"");  //MedDRA代碼				
        			row[19] = Common.get("");  //醫療機構類別				
        			row[20] = Common.get(drgnfsMap.get(obj.getNotifierSource()));  //通報來源				
        			row[21] = Common.get(obj04!=null?rkl2Map.get(obj04.getAssessResult()):"");  //療效不等評估結果				
        			
        			arrList.add(row);
        			
        		}else{  //匯出格式(全)				
        			String row[] = new String[cols.length];				
        			row[0] = Common.get(obj.getApplNo());  //案件編號				
        			row[1] = Common.get(obj.getPatientId());  //識別代號				
        			row[2] = Common.get(obj.getNotifierDept());  //服務機構				
        			row[3] = Common.formatYYYMMDD(obj.getNotifierRepDate(),4);  //通報日期				
        			row[4] = Common.formatYYYMMDD(obj.getEnrolledDate(),4);  //收案日期				
        			row[5] = Common.formatYYYMMDD(obj.getOccurDate(),4);  //發生日期				
        			if("1".equals(obj.getConSequence())) {
        				row[6] = "藥效改變";  //通報事件後果
        			} else if("2".equals(obj.getConSequence())) {
        				row[6] = "不良反應發生、強度增強或頻率增加";  //通報事件後果
        			} else if("1,2".equals(obj.getConSequence())) {
        				row[6] = "藥效改變；不良反應發生、強度增強或頻率增加";	//通報事件後果
        			} else {
        				row[6] = Common.get(obj.getConSequence());  //通報事件後果
        			}				
        			row[7] = Common.get(obj02!=null?obj02.getScientificName():"");  //學名
        			if("Y".equals(obj04.getMedNti())) {
        				row[8] = "是";	//NTI Drugs	
        			} else {
        				row[8] = "";	//NTI Drugs	
        			}
//        			row[8] = Common.get(obj04!=null?obj04.getMedNti():"");  //NTI Drugs				
        			row[9] = Common.get(obj04!=null?obj04.getMedAtcCode():"");  //藥理治療分類				
        			row[10] = Common.get(obj.getBeforeDesc());  //通報事件描述-事件前				
        			row[11] = Common.get(obj.getChangeDesc());  //通報事件描述-藥品轉換				
        			row[12] = Common.get(obj.getOccurDesc());  //通報事件描述-發生事件				
        			row[13] = Common.get(obj.getAfterDesc());  //通報事件描述-事件後				
        			row[14] = Common.get(obj03!=null?obj03.getProductName():"");  //事件前使用藥品-商品名				
        			row[15] = Common.get(obj03!=null?pkidMap.get(obj03.getPermitKey()):"");  //事件前使用藥品-許可證字				
        			row[16] = Common.get(obj03!=null?obj03.getPermitNo():"");  //事件前使用藥品-許可證號				
        			row[17] = obj03!=null?Common.formatYYYMMDD(obj03.getStartDare(),4):""; //事件前使用藥品-使用期間(起)				
        			row[18] = obj03!=null?Common.formatYYYMMDD(obj03.getEndDate(),4):"";  //事件前使用藥品-使用期間(訖)				
        			row[19] = Common.get(obj03!=null?drg0304Map.get(obj03.getMedPath()):"");  //事件前使用藥品-給藥途徑				
        			row[20] = Common.get(obj03!=null?drg0305Map.get(obj03.getDosage()):"");  //事件前使用藥品-劑量				
        			row[21] = Common.get(obj03!=null?drg0306Map.get(obj03.getFrequency()):"");  //事件前使用藥品-頻率				
        			row[22] = Common.get(obj03!=null?obj03.getIndication():"");  //事件前使用藥品-適應症				
        			row[23] = Common.get(obj03!=null?obj03.getApplicationName():"");  //事件前使用藥品-申請商				
        			row[24] = Common.get(obj03!=null?obj03.getManufactorName():"");  //事件前使用藥品-製造廠				
        			row[25] = Common.get("");  //事件前使用藥品-製造廠國別				
        			row[26] = Common.get(obj03!=null?obj03.getManufactorNo():"");  //事件前使用藥品-批號				
        			row[27] = Common.get(obj03!=null?flnMap.get(obj03.getMedModel()):"");  //事件前使用藥品-劑型				
        			row[28] = Common.get(obj02!=null?obj02.getProductName():"");  //懷疑效不等藥品-商品名				
        			row[29] = Common.get(obj02!=null?pkidMap.get(obj02.getPermitKey()):"");  //懷疑效不等藥品-許可證字				
        			row[30] = Common.get(obj02!=null?obj02.getPermitNo():"");  //懷疑效不等藥品-許可證號				
        			row[31] = obj02!=null?Common.formatYYYMMDD(obj02.getStartDare(),4):"";  //懷疑效不等藥品-使用期間(起)				
        			row[32] = obj02!=null?Common.formatYYYMMDD(obj02.getEndDate(),4):"";  //懷疑效不等藥品-使用期間(訖)				
        			row[33] = Common.get(obj02!=null?drg0304Map.get(obj02.getMedPath()):"");  //懷疑效不等藥品-給藥途徑				
        			row[34] = Common.get(obj02!=null?drg0305Map.get(obj02.getDosage()):"");  //懷疑效不等藥品-劑量				
        			row[35] = Common.get(obj02!=null?drg0306Map.get(obj02.getFrequency()):"");  //懷疑效不等藥品-頻率				
        			row[36] = Common.get(obj02!=null?obj02.getIndication():"");  //懷疑效不等藥品-適應症				
        			row[37] = Common.get(obj02!=null?obj02.getApplicationName():"");  //懷疑效不等藥品-申請商				
        			row[38] = Common.get(obj02!=null?obj02.getManufactorName():"");  //懷疑效不等藥品-製造廠				
        			row[39] = Common.get("");  //懷疑效不等藥品-製造廠國別				
        			row[40] = Common.get(obj02!=null?obj02.getManufactorNo():"");  //懷疑效不等藥品-批號				
        			row[41] = Common.get(obj02!=null?flnMap.get(obj02.getMedModel()):"");  //懷疑效不等藥品-劑型				
        			row[42] = Common.get(productName3);  //併用藥品-商品名				
        			row[43] = Common.get(permitNo3);  //併用藥品-許可證字號				
        			row[44] = Common.get(drg0301Map.get(obj.getDealWith()));  //發生事件後之處置
        			if("Y".equals(obj.getIsImproveYn())) {
        				row[45] = "是";	//病人恢復原藥或轉換同成分藥品其症狀是否改善	
        			} else if("N".equals(obj.getIsImproveYn())) {
        				row[45] = "否";	//病人恢復原藥或轉換同成分藥品其症狀是否改善	
        			} else if("0".equals(obj.getIsImproveYn())) {
        				row[45] = "未知";	//病人恢復原藥或轉換同成分藥品其症狀是否改善	
        			}
        			row[46] = Common.get(drg0302Map.get(obj.getDressingAttitude()));  //醫師對換藥的態度				
        			row[47] = Common.get(drg0303Map.get(obj.getObedienceLev()));  //病人服藥順從性
        			if("1".equals(obj.getConSequence())) {
        				row[48] = "藥效改變";  //通報事件後果(初評表)
        			} else if("2".equals(obj.getConSequence())) {
        				row[48] = "不良反應發生、強度增強或頻率增加";  //通報事件後果(初評表)
        			} else if("1,2".equals(obj.getConSequence())) {
        				row[48] = "藥效改變；不良反應發生、強度增強或頻率增加";	//通報事件後果(初評表)
        			} else {
        				row[48] = Common.get(obj.getConSequence());  //通報事件後果(初評表)
        			}		
//        			row[48] = Common.get(obj04!=null?obj04.getConSequence():"");  //通報事件後果(初評表)				
        			row[49] = Common.get(obj04!=null?drg0308Map.get(obj04.getBadReactionLev()):"");  //改變狀況/不良反應等級(初評表)				
        			row[50] = Common.get(obj04!=null?obj04.getBadReactionDesc():"");  //不良反應狀況(初評表)				
        			row[51] = Common.get(testDate);  //相關檢查及檢驗數據資訊-檢驗日期				
        			row[52] = Common.get(testItems);  //相關檢查及檢驗數據資訊-檢驗項目				
        			row[53] = Common.get(testNum);  //相關檢查及檢驗數據資訊-檢驗數據				
        			row[54] = Common.get(obj.getOtherDesc());  //其他相關資料				
        			row[55] = Common.get(obj04!=null?obj04.getBadReactionDra():"");  //MedDRA代碼				
        			row[56] = Common.get("");  //醫療機構類別				
        			row[57] = Common.get(drgnfsMap.get(obj.getNotifierSource()));  //通報來源				
        			row[58] = Common.get(obj04!=null?rkl2Map.get(obj04.getAssessResult()):"");  //療效不等評估結果				
        			row[59] = Common.get(notifierAging);  //通報時效				
        			row[60] = Common.get(notifierQuality);  //通報品質				
				
        			arrList.add(row);			
        		}
        	}
			if(pkidMap != null) pkidMap.clear();
			if(flnMap != null) flnMap.clear();
			if(rkl2Map != null) rkl2Map.clear();
			if(drg0301Map != null) drg0301Map.clear();
			if(drg0302Map != null) drg0302Map.clear();
			if(drg0303Map != null) drg0303Map.clear();
			if(drg0304Map != null) drg0304Map.clear();
			if(drg0305Map != null) drg0305Map.clear();
			if(drg0306Map != null) drg0306Map.clear();
		}
		if(null != arrList && arrList.size() >0){
			String[][] rs = new String[0][0];
			rs = arrList.toArray(rs);
			model2 = new DefaultTableModel();
			model2.setDataVector(rs, cols);
		}
		return model2;
	}
	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}

	
}
