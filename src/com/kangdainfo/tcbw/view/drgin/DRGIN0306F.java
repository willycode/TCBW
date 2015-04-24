package com.kangdainfo.tcbw.view.drgin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1009Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4003Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg4005Db;
import com.kangdainfo.tcbw.model.bo.Drg4006Db;
import com.kangdainfo.tcbw.model.bo.Drg4007Db;
import com.kangdainfo.tcbw.model.bo.Drg4008Db;
import com.kangdainfo.tcbw.model.bo.Drg4009Db;
import com.kangdainfo.tcbw.model.bo.Drg4010Db;
import com.kangdainfo.tcbw.model.bo.Drg4011Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;


public class DRGIN0306F extends SuperBean{
	
	private String id;
	private String assessDesc;
	private String[] assessResult;
	private String isMedSend;
	private String tabId;
	private String actionType;
	private String isClose;
	
	private String q_permitKey;
	private String q_permitNo;
	
	public String getId() {return checkGet(id);}
	public void setId(String s) {this.id = checkSet(s);}
	public String getAssessDesc() {return checkGet(assessDesc);}
	public void setAssessDesc(String s) {this.assessDesc = checkSet(s);}
	public String[] getAssessResult() {return assessResult;}
	public void setAssessResult(String[] assessResult) {this.assessResult = assessResult;}
	public String getIsMedSend() {return checkGet(isMedSend);}
	public void setIsMedSend(String s) {this.isMedSend = checkSet(s);}
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String s) {this.tabId = checkSet(s);}
	public String getActionType() {return checkGet(actionType);}
	public void setActionType(String s) {this.actionType = checkSet(s);}
	public String getIsClose() {return checkGet(isClose);}
	public void setIsClose(String s) {this.isClose = checkSet(s);}
	
	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String s) {this.q_permitKey = checkSet(s);}
	public String getQ_permitNo() {return checkGet(q_permitNo);}
	public void setQ_permitNo(String s) {this.q_permitNo = checkSet(s);}
	
	String[] ids;
	public String[] getIds() {return ids;}
	public void setIds(String[] ids) {this.ids = ids;}
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	//==========  調查成份 =================
	String[] drg49Row;
	public String[] getDrg49Row() {return drg49Row;}
	public void setDrg49Row(String[] drg49Row) {this.drg49Row = drg49Row;}
	public final String[] arrDrg49FieldName = {"ingredient"};
	String drg49JSBuilder;
	public String getDrg49JSBuilder() {
		if (drg49JSBuilder!=null) return drg49JSBuilder;
		else return "";
	}
	public void setDrg49JSBuilder(String drg49JSBuilder) {this.drg49JSBuilder = drg49JSBuilder;}
	public String genDrg4009DbSet(java.util.Set dtlSet) throws Exception {
	    if (dtlSet!=null && dtlSet.size()>0) {
	    	Map<String, String> drg49Map = new HashMap<String, String>();	//成份, 醫院ids
	    	for (Object dtlObj : dtlSet) {
	    		Drg4009Db dtl = (Drg4009Db) dtlObj;
	    		String drg49Ids = Common.get(drg49Map.get(dtl.getIngredient()));
	    		if(null != drg49Ids && !"".equals(drg49Ids)){
	    			drg49Ids += ",";
	    		}
	    		drg49Ids += dtl.getId();
	    		drg49Map.put(dtl.getIngredient(), drg49Ids);
	    	}

	    	StringBuilder sb = new StringBuilder(1024);  
	    	if(null != drg49Map && !drg49Map.isEmpty()){
	    		for(String ingredient : drg49Map.keySet()){
	    			sb.append("addDrg49Row('drg49Table'");
	    			sb.append(",").append(Common.sqlChar(checkGet(Common.escapeJavaScript(ingredient))));       
					sb.append(",'").append(Common.get(drg49Map.get(ingredient))).append("'");
					sb.append(");\n");
	    		}
	    	}
	    	this.setDrg49JSBuilder(sb.toString());
	    	return sb.toString();    	
	    } else if (httpRequest!=null && this.getDrg49Row()!=null && this.getDrg49Row().length>0) {
			String v = "";
			StringBuilder sb = new StringBuilder(1024);
			for (int i=0; i<getDrg49Row().length; i++) {
				String rid = getDrg49Row()[i];
				sb.append("addDrg49Row('drg49Table'");
				for (int j=0; j<arrDrg49FieldName.length; j++) {				
					v = Common.escapeReturnChar(checkGet(httpRequest.getParameter(arrDrg49FieldName[j] + rid)),true);				
					sb.append(",").append(Common.sqlChar(v));				
				}
				sb.append(",'").append(checkGet(httpRequest.getParameter("drg49Id" + rid))).append("'");
				sb.append(");\n");
				
			}
			this.setDrg49JSBuilder(sb.toString());
			return sb.toString();
		}
	    return "";
	}
	
	//==========  調查醫院 =================
	String[] con19Row;
	public String[] getCon19Row() {return con19Row;}
	public void setCon19Row(String[] con19Row) {this.con19Row = con19Row;}
	public final String[] arrCon19FieldName = {"medAgencyName", "engageKind", "medAgencyKind"};
	String con19JSBuilder;
	public String getCon19JSBuilder() {
		if (con19JSBuilder!=null) return con19JSBuilder;
		else return "";
	}
	public void setCon19JSBuilder(String con19JSBuilder) {this.con19JSBuilder = con19JSBuilder;}
	public String genCon1009DbSet(java.util.Set dtlSet) throws Exception {
	    if (dtlSet!=null && dtlSet.size()>0) {
	    	StringBuilder sb = new StringBuilder(1024); 
	    	Map<Long,Con1009Db> con19Map = new HashMap<Long,Con1009Db>();
	    	for (Object dtlObj : dtlSet) {
	    		Drg4009Db drg49 = (Drg4009Db) dtlObj;
	    		if(null != drg49.getCon1009Db()){
	    			con19Map.put(drg49.getCon1009Db().getId(), drg49.getCon1009Db());
	    		}
	    	}
	    	if(null != con19Map && !con19Map.isEmpty()){
	    		for(Long con19Id : con19Map.keySet()){
	    			Con1009Db con19 = con19Map.get(con19Id);
	    			sb.append("addCon19Row('con19Table'");
		    		for (int j=0; j<arrCon19FieldName.length; j++) {
		    			if(j == 1){
		    				sb.append(",").append(Common.sqlChar(View.getCommonCodeName("MEDENG", con19.getEngageKind())));
		    			}else if(j == 2){
		    				sb.append(",").append(Common.sqlChar(View.getCommonCodeName("MEDKIND", con19.getMedAgencyKind())));
		    			}else{
		    				sb.append(",").append(Common.sqlChar(checkGet(Common.escapeJavaScript(BeanUtils.getProperty(con19, arrCon19FieldName[j])))));
		    			}
		    		}
		    		sb.append(",'").append(con19.getId()!=null?con19.getId():"").append("'");
					sb.append(");\n");
	    		}
	    	}
	    	this.setCon19JSBuilder(sb.toString());
	    	return sb.toString();    	
	    } else if (httpRequest!=null && this.getCon19Row()!=null && this.getCon19Row().length>0) {
			String v = "";
			StringBuilder sb = new StringBuilder(1024);
			for (int i=0; i<getCon19Row().length; i++) {
				String rid = getCon19Row()[i];
				sb.append("addCon19Row('con19Table'");
				for (int j=0; j<arrCon19FieldName.length; j++) {				
					v = Common.escapeReturnChar(checkGet(httpRequest.getParameter(arrCon19FieldName[j] + rid)),true);				
					sb.append(",").append(Common.sqlChar(v));				
				}
				sb.append(",'").append(checkGet(httpRequest.getParameter("con19Id" + rid))).append("'");
				sb.append(");\n");
				
			}
			this.setCon19JSBuilder(sb.toString());
			return sb.toString();
		}
	    return "";
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		DRGIN0306F obj = this;
		if(null != getId() && !"".equals(getId())){
			Drg4005Db drg45 = (Drg4005Db) ServiceGetter.getInstance().getTcbwService().getObject(" from Drg4005Db where id = " + getId());
			if(null != drg45){
				obj.setAssessDesc(drg45.getAssessDesc());
				obj.setIsMedSend(drg45.getIsMedSend());
				obj.setIsClose(drg45.getIsClose());
				if(null != drg45.getAssessResult() && !"".equals(drg45.getAssessResult())){
					obj.setAssessResult(drg45.getAssessResult().split(","));
				}
				obj.setDrg49JSBuilder(this.genDrg4009DbSet(drg45.getDrg4009Dbs())); 
				obj.setCon19JSBuilder(this.genCon1009DbSet(drg45.getDrg4009Dbs()));
			}
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		Map<Long,Drg4008Db> drg48Map = new HashMap<Long,Drg4008Db>();
		
		String hql = " from Drg4001Db where 1 = 1 ";
		if(null != getQ_permitKey() && !"".equals(getQ_permitKey()) && null != getQ_permitNo() && !"".equals(getQ_permitNo())){
			hql += " and id in (select drg4001Db.id from Drg4003Db where medType ='02' and permitKey = " + Common.sqlChar(getQ_permitKey())
				+ " and permitNo = " + Common.sqlChar(getQ_permitNo())+")";
		}
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id ");
		if(objList!=null && objList.size()>0)
		{
			//查詢已選取的案件資料
			if(null != getId() && !"".equals(getId())){
				List<Drg4008Db> drg48List = ServiceGetter.getInstance().getTcbwService().load(" from Drg4008Db where drg4005Db.id = "+ getId());
				if(null != drg48List && !drg48List.isEmpty()){
					for(Drg4008Db drg48:drg48List){
						drg48Map.put(drg48.getDrg4001Db().getId(), drg48);
					}
				}
			}
			String befYearDate = Datetime.getDateSubtraction("y", 1,Datetime.getYYYMMDD());
			for(Object dtlObj : objList)
			{
				Drg4001Db dtl = (Drg4001Db) dtlObj;
				if((null != drg48Map && null != drg48Map.get(dtl.getId())) 
						|| ("30".equals(dtl.getStatus()) 
						|| ("90".equals(dtl.getStatus()) && Common.getInt(dtl.getNotifierRepDate()) >= Common.getInt(befYearDate)))){
					
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = "N";
					if(null != drg48Map && null != drg48Map.get(dtl.getId())){
						rowArray[1] = "Y";
					}
					rowArray[2] = Common.get(dtl.getApplNo());
					rowArray[3] = Common.get(dtl.getOccurDate());
					rowArray[4] = "";
					if(null != dtl.getDrg4003Dbs() && !dtl.getDrg4003Dbs().isEmpty()){
						for(Object drg43Obj:dtl.getDrg4003Dbs()){
							Drg4003Db drg43 = (Drg4003Db)drg43Obj;
							if("02".equals(drg43.getMedType())){
								rowArray[4] = drg43.getProductName();
							}
						}
					}
					rowArray[5] = "";
					rowArray[6] = "";
					rowArray[7] = "";
					Drg4004Db drg04 = (Drg4004Db) View.getObject(" from Drg4004Db where applNo=" + Common.sqlChar(dtl.getApplNo()));
					if(null != drg04){
						if(StringUtils.contains(drg04.getConSequence(), "1")) {
							if("1".equals(drg04.getEffectChangeDesc())){
								if(rowArray[5].length() > 0) rowArray[5] += "、";
								rowArray[5] += "藥效增強";
							}else if("2".equals(drg04.getEffectChangeDesc())){
								if(rowArray[5].length() > 0) rowArray[5] += "、";
								rowArray[5] += "藥效減弱";
							}
						}
						if(StringUtils.contains(drg04.getConSequence(), "2")) {
							if(null != drg04.getBadReactionLev() && !"".equals(drg04.getBadReactionLev())){
								if(rowArray[5].length() > 0) rowArray[5] += "、";
								rowArray[5] += View.getCommonCodeName("DRG0308", drg04.getBadReactionLev());
							}
							if(null != drg04.getBadReactionDesc() && !"".equals(drg04.getBadReactionDesc())){
								if(rowArray[5].length() > 0) rowArray[5] += "、";
								rowArray[5] += drg04.getBadReactionDesc();
							}
							if(null != drg04.getBadReactionDra() && !"".equals(drg04.getBadReactionDra())){
								if(rowArray[5].length() > 0) rowArray[5] += "、";
								rowArray[5] += drg04.getBadReactionDra();
							}
							rowArray[6] =  Common.get(View.getCommonCodeName("DRG0308", drg04.getBadReactionLev()));
						}
						rowArray[7] = Common.get(View.getCommonCodeName("DRG2RKL", drg04.getAssessResult()));
					}
					arrList.add(rowArray);
				}
			}
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		List<Object> delList = new ArrayList<Object>();
		List<Object> saveList = new ArrayList<Object>();
		List<Object> updList = new ArrayList<Object>();
		boolean isSaveCon2001 = false;
		Drg4005Db drg45 = null;
		Drg4006Db drg46 = null;
		Drg4007Db drg47 = null;
		if(null != getId() && !"".equals(getId())){
			drg45 = (Drg4005Db) ServiceGetter.getInstance().getTcbwService().getObject(" from Drg4005Db where id = " + getId());
		}
		if(null == drg45){
			drg45 = new Drg4005Db();
			drg45.setCreateDate(Datetime.getYYYMMDD());
			drg45.setCreateTime(Datetime.getHHMMSS());
			drg45.setCreator(getUserID());
		}
		drg45.setAssessDate(Datetime.getYYYMMDD());
		drg45.setAssessMan(getUserID());
		drg45.setAssessDesc(getAssessDesc());
		drg45.setModifier(getUserID());
		drg45.setModifyDate(Datetime.getYYYMMDD());
		drg45.setModifyTime(Datetime.getHHMMSS());
		
		if(null != getAssessResult() && !"".equals(getAssessResult())){
			String resultStr = "";
			for(String result:getAssessResult()){
				if(resultStr.length() > 0) resultStr += ",";
				resultStr += result;
			}
			drg45.setAssessResult(resultStr);
		}
		if(null != ids && ids.length > 0){
			java.util.Set dtlSet = new ListOrderedSet();
			StringBuilder dtlKey = new StringBuilder("-2,-1");
			for(int i=0;i<ids.length;i++){
				Drg4008Db dtl = (Drg4008Db) View.getObject("from Drg4008Db where drg4005Db.id ="+Common.getLong(drg45.getId())+" and drg4001Db.id="+Common.getLong(ids[i]));
				if (dtl==null) {
					dtl = new Drg4008Db();
					dtl.setDrg4005Db(drg45);
					Drg4001Db drg41 = (Drg4001Db) View.getObject(" from Drg4001Db where id = "+Common.getLong(ids[i]));
					dtl.setDrg4001Db(drg41);
					if(null != drg41 && null != drg41.getDrg4003Dbs() && !drg41.getDrg4003Dbs().isEmpty()){
						for(Object dtlObj:drg41.getDrg4003Dbs()){
							Drg4003Db drg43 = (Drg4003Db)dtlObj;
							if("02".equals(drg43.getMedType())){
								drg45.setPermitKey2(drg43.getPermitKey());
								drg45.setPermitNo2(drg43.getPermitNo());
								drg45.setProductName2(drg43.getProductName());
							}
						}
					}
					dtl.setCreateDate(Datetime.getYYYMMDD());
					dtl.setCreateTime(Datetime.getHHMMSS());
					dtl.setCreator(getUserID());
				}
				//評估完成
				if("2".equals(getActionType())){
					drg45.setIsClose("Y");
					//選擇啟動品質調查
					if(StringUtils.contains(drg45.getAssessResult(),"01")){
						if(null != dtl.getDrg4001Db()){
							dtl.getDrg4001Db().setStatus("40");
							Drg4003Db drg43 = null;
							if(null != dtl.getDrg4001Db().getDrg4003Dbs() && !dtl.getDrg4001Db().getDrg4003Dbs().isEmpty()){
								for(Object dtlObj:dtl.getDrg4001Db().getDrg4003Dbs()){
									Drg4003Db drg43Tmp = (Drg4003Db)dtlObj;
									if("02".equals(drg43Tmp.getMedType())){
										drg43 = drg43Tmp;
									}
								}
							}
							if(null != drg43){
								//1.使用藥證資料查詢是否有相同廠商回覆資料
								if(null == drg46){
									drg46 = (Drg4006Db) View.getObject(" from Drg4006Db where (isClose is null or isClose ='' or isClose ='N') and permitKey2 = "+Common.sqlChar(drg43.getPermitKey())+" and permitNo2 = "+Common.sqlChar(drg43.getPermitNo()));
									if(null == drg46){
										drg46 = new Drg4006Db();
										drg46.setManufactorID(drg43.getManufactorID());
										drg46.setPermitKey2(drg43.getPermitKey());
										drg46.setPermitNo2(drg43.getPermitNo());
										drg46.setProductName2(drg43.getProductName());
										drg46.setCreator(getUserID());
										drg46.setCreateDate(Datetime.getYYYMMDD());        
										drg46.setCreateTime(Datetime.getHHMMSS());
										drg46.setModifier(getUserID());
										drg46.setModifyDate(Datetime.getYYYMMDD());
										drg46.setModifyTime(Datetime.getHHMMSS());
									}
								}
								java.util.Set dtl10Set = new ListOrderedSet();
								Drg4010Db drg410 = new Drg4010Db();
								drg410.setApplNo(dtl.getDrg4001Db().getApplNo());
								drg410.setDrg4001Db(dtl.getDrg4001Db());
								drg410.setDrg4006Db(drg46);
								drg410.setCreator(getUserID());
								drg410.setCreateDate(Datetime.getYYYMMDD());        
								drg410.setCreateTime(Datetime.getHHMMSS());
								drg410.setModifier(getUserID());
								drg410.setModifyDate(Datetime.getYYYMMDD());
								drg410.setModifyTime(Datetime.getHHMMSS());
								dtl10Set.add(drg410);								
								drg46.setDrg4010Dbs(dtl10Set);
								dtl.getDrg4001Db().getDrg4010Dbs().add(drg410);
								
								//2.使用藥證資料查詢是否有相同品質調查資料
								if(null == drg47){
									drg47 = (Drg4007Db) View.getObject(" from Drg4007Db where (isClose is null or isClose ='' or isClose ='N') and permitKey2 = "+Common.sqlChar(drg43.getPermitKey())+" and permitNo2 = "+Common.sqlChar(drg43.getPermitNo()));
									if(null == drg47){
										drg47 = new Drg4007Db();
										drg47.setPermitKey2(drg43.getPermitKey());
										drg47.setPermitNo2(drg43.getPermitNo());
										drg47.setProductName2(drg43.getProductName());
										drg47.setCreator(getUserID());
										drg47.setCreateDate(Datetime.getYYYMMDD());        
										drg47.setCreateTime(Datetime.getHHMMSS());
										drg47.setModifier(getUserID());
										drg47.setModifyDate(Datetime.getYYYMMDD());
										drg47.setModifyTime(Datetime.getHHMMSS());
									}
								}
								
								
								java.util.Set dtl11Set = new ListOrderedSet();
								Drg4011Db drg411 = new Drg4011Db();
								drg411.setApplNo(dtl.getDrg4001Db().getApplNo());
								drg411.setDrg4001Db(dtl.getDrg4001Db());
								drg411.setDrg4007Db(drg47);
								drg411.setCreator(getUserID());
								drg411.setCreateDate(Datetime.getYYYMMDD());        
								drg411.setCreateTime(Datetime.getHHMMSS());
								drg411.setModifier(getUserID());
								drg411.setModifyDate(Datetime.getYYYMMDD());
								drg411.setModifyTime(Datetime.getHHMMSS());
								dtl11Set.add(drg411);								
								drg47.setDrg4011Dbs(dtl11Set);
								if(null == drg47.getId() || drg47.getId() == 0){
									saveList.add(drg47);
								}
								dtl.getDrg4001Db().getDrg4011Dbs().add(drg411);
								
								isSaveCon2001 = true;
							}
						}
					}else{
						if(null != dtl.getDrg4001Db()){
							dtl.getDrg4001Db().setStatus("90");
							dtl.getDrg4001Db().setChargeMan(null);
							dtl.getDrg4001Db().setModifier(getUserID());
							dtl.getDrg4001Db().setModifyDate(Datetime.getYYYMMDD());
							dtl.getDrg4001Db().setModifyTime(Datetime.getHHMMSS());
							isSaveCon2001 = true;
						}
					}
					if(null != dtl.getDrg4001Db()){
						//同步更新外部案件狀態
						Drg6001Db drg61 = (Drg6001Db)ServiceGetter.getInstance().getTcbwService().getObject(" from Drg6001Db where drg4001Id = " + dtl.getDrg4001Db().getId()+" order by revision desc ");
						if(null != drg61){
							drg61.setStatus(dtl.getDrg4001Db().getStatus());
							updList.add(drg61);
						}
					}
				}
				dtl.setModifier(getUserID());
				dtl.setModifyDate(Datetime.getYYYMMDD());
				dtl.setModifyTime(Datetime.getHHMMSS());
				dtlSet.add(dtl);
				dtlKey.append(",").append(dtl.getId());
			}
			if(null != drg46 && (null == drg46.getId() || drg46.getId() == 0)){
				saveList.add(drg46);
			}
			drg45.setDrg4008Dbs(dtlSet);
			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg4008Db where drg4005Db.id=" + Common.getLong(drg45.getId()) + " and id not in (" + dtlKey.toString() + ")"));
		}
		
		if(true){
			java.util.Set dtlSet = new ListOrderedSet();
			Map<String,String[]> drg49Map = new HashMap<String,String[]>();	//ingredient, Drg4009Db.id
			if (getHttpRequest()!=null && getDrg49Row()!=null && getDrg49Row().length>0) {
				for(int i=0; i<getDrg49Row().length; i++) {
					String rid = getDrg49Row()[i];
					drg49Map.put(Common.get(getHttpRequest().getParameter("ingredient" + rid)), Common.get(getHttpRequest().getParameter("drg49Id" + rid)).split(","));
				}
			}
			if (getHttpRequest()!=null && getCon19Row()!=null && getCon19Row().length>0) {
				StringBuilder dtlKey = new StringBuilder("-2,-1");		
    			for (int i=0; i<getCon19Row().length; i++) {
    				String rid = getCon19Row()[i];
    				String con19Id = Common.get(getHttpRequest().getParameter("con19Id" + rid));
    				for(String ingredient:drg49Map.keySet()){
    					Drg4009Db dtl = null;
    					for(String drg49Id : drg49Map.get(ingredient)){
    						Drg4009Db dtlTmp = (Drg4009Db) View.getObject("from Drg4009Db where id="+Common.getLong(drg49Id)+" and con1009Db.id = " +con19Id);
    						if(null != dtlTmp){
    							dtl = dtlTmp;
    						}
    					}
    					if(null == dtl){
    						dtl = new Drg4009Db();
    						dtl.setDrg4005Db(drg45);
    						dtl.setCon1009Db((Con1009Db)View.getObject(" from Con1009Db where id = " + con19Id));
    						dtl.setCreateDate(Datetime.getYYYMMDD());
    						dtl.setCreateTime(Datetime.getHHMMSS());
    						dtl.setCreator(getUserID());
    					}
    					dtl.setMedAgencyName(dtl.getCon1009Db().getMedAgencyName());
    					dtl.setIngredient(ingredient);
    					dtl.setModifier(getUserID());
    					dtl.setModifyDate(Datetime.getYYYMMDD());
    					dtl.setModifyTime(Datetime.getHHMMSS());
    					dtlSet.add(dtl);
    					dtlKey.append(",").append(dtl.getId());
    				}
    			}
    			delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg4009Db where drg4005Db.id=" + Common.getLong(drg45.getId()) + " and id not in (" + dtlKey.toString() + ")"));
    			drg45.setDrg4009Dbs(dtlSet);
			}else{
				delList.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg4009Db where drg4005Db.id=" + Common.getLong(drg45.getId())));
			}
		}
		
		if(null != saveList && !saveList.isEmpty()){
			ServiceGetter.getInstance().getCommonService().saveBatch(saveList);
		}
		if(null != updList && !updList.isEmpty()){
			ServiceGetter.getInstance().getCommonService().updateBatch(updList);
		}
		if(null != drg45.getId() && drg45.getId() > 0 ){
			ServiceGetter.getInstance().getCommonService().update(drg45);
			this.setErrorMsg("修改完成");
		}else{
			ServiceGetter.getInstance().getCommonService().save(drg45);
			this.setErrorMsg("新增完成");
		}

		if(null != delList && !delList.isEmpty()){
			ServiceGetter.getInstance().getCommonService().deleteBatch(delList);
		}
		
		if(isSaveCon2001){
			if(null != drg45 && null != drg45.getDrg4008Dbs() && !drg45.getDrg4008Dbs().isEmpty()){
				for(Object dtlObj : drg45.getDrg4008Dbs()){
					Drg4008Db drg48 = (Drg4008Db)dtlObj;
					if(null != drg48.getDrg4001Db()){
						//歷程紀錄
						ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG2",drg48.getDrg4001Db().getId(), drg48.getDrg4001Db().getApplNo(),drg48.getDrg4001Db().getStatus(), "評估作業 - "+("90".equals(drg48.getDrg4001Db().getStatus())?"結案":"廠商回覆"), getUserID());
					}
				}
			}
		}
		
	}

	@Override
	public void doDelete() throws Exception {}
}
