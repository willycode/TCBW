package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0009Db;
import com.kangdainfo.tcbw.model.bo.Cos0010Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Sys0001Db;

public class COSIN0701F extends COSIN0601F {
	
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " select distinct a from Cos0001Db a, ";
		if("1B".equals(getQ_type())){
			hql += " Cos0009Db b ";
		}else{
			hql += " Cos0010Db b ";
		}
		hql += " where a.applNo = b.applNo and a.status = '50' "; 
		if("1B".equals(getQ_type())){
			hql += " and a.cosType = '1' ";
		}else{
			hql += " and a.cosType = '2' ";
		}
		if(!"".equals(getQ_applNo()))
			hql += " and a.applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and a.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and a.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_permitKey()))
			hql += " and a.permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and a.permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_chProduct()))
			hql += " and a.chProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%");
		if(!"".equals(getQ_manufactorName()))
			hql += " and a.manufactorName like " + Common.sqlChar("%" + getQ_manufactorName() + "%");
		
		// 不良品
		if("1B".equals(getQ_type())){
			if(getQ_subCode()!=null && getQ_subCode().length>0){
				StringBuffer sb = new StringBuffer();
				sb.append("(");
				
				boolean flag = true;
				for(String rid : getQ_subCode()){	
					if(flag){
						sb.append(" subCode like " + Common.sqlChar("%" + rid + "%"));
						flag = false;
					}else{
						sb.append(" or subCode like " + Common.sqlChar("%" + rid + "%"));
					}
				}
				sb.append(")");
				hql += " and a.id in (select distinct cos0001Db.id from Cos0003Db where " + sb.toString() + ") ";
			}
		}else{
			if(!"".equals(getQ_adverseCondition())){
				hql += " and id in (select distinct cos0001Db.id from Cos0002Db where adverseCondition = " + Common.sqlChar(getQ_adverseCondition()) + ")";
			}
		}
		System.out.println("[TCBW]-[COSIN0701F]-[QUERYALL] : " + hql + " order by a.id ");
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by a.id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> CPTMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CPT", null);
				java.util.Map<String, String> CACMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CAC", null);
				
				java.util.Map<String, String> subCodeNameMap = new java.util.HashMap<String, String>();
				if("1B".equals(getQ_type())){
					java.util.List<Cos1001Db> cos1001DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N'");
					if(cos1001DbList!=null && cos1001DbList.size()>0){
						for(Cos1001Db cos1001Db : cos1001DbList){
							subCodeNameMap.put(Common.get(cos1001Db.getDpdKind()), Common.get(cos1001Db.getDpdKindName()));
						}
						cos1001DbList.clear();
					}
				}
				Sys0001Db sys0001Db = (Sys0001Db)View.getObject(" from Sys0001Db where id = 1 ");
				if(sys0001Db == null){
					sys0001Db = new Sys0001Db();
					sys0001Db.setField1("10");						// 預設 10 天
					sys0001Db.setField2("3");						// 預設 3 個月
				}
				
				for(Object dtlObj : objList) {				
					Cos0001Db dtl = (Cos0001Db)dtlObj;
					
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
					rowArray[3] = "";
					if(!"".equals(Common.get(dtl.getPermitKey())) || !"".equals(Common.get(dtl.getPermitNo()))){
						rowArray[3] = (Common.get(CPTMap.get(Common.get(dtl.getPermitKey()))).equals("")?Common.get(dtl.getPermitKey()):Common.get(CPTMap.get(Common.get(dtl.getPermitKey())))) + 
									  " 字第 " + Common.get(dtl.getPermitNo()) + " 號";
					}
					rowArray[4] = Common.get(dtl.getChProduct());
					rowArray[5] = Common.get(dtl.getEnProduct());
					
					
					rowArray[6] = "";
					if("1B".equals(getQ_type())){
						if(dtl.getCos0003Dbs()!=null && dtl.getCos0003Dbs().size()>0){
							StringBuffer sb = new StringBuffer();
							for(Object cos0003DbObj : dtl.getCos0003Dbs()){
								Cos0003Db cos0003Db = (Cos0003Db)cos0003DbObj;
								if(sb.toString().length() > 0){
									sb.append("、");
								}
								sb.append(Common.get(subCodeNameMap.get(Common.get(cos0003Db.getSubCode()))));
							}
							rowArray[6] = sb.toString();
						}
					}else{
						if(dtl.getCos0002Dbs()!=null && dtl.getCos0002Dbs().size()>0){
							StringBuffer sb = new StringBuffer();
							for(Object cos0002DbObj : dtl.getCos0002Dbs()){
								Cos0002Db cos0002Db = (Cos0002Db)cos0002DbObj;
								if(sb.toString().length() > 0){
									sb.append("、");
								}
								sb.append(Common.get(CACMap.get(Common.get(cos0002Db.getAdverseCondition()))).equals("")?Common.get(cos0002Db.getAdverseCondition()):Common.get(CACMap.get(Common.get(cos0002Db.getAdverseCondition()))));
							}
							rowArray[6] = sb.toString();
						}
					}
					
					// 燈號處理
					String isNeedLight = "";
					if("1B".equals(getQ_type())){
						Cos0009Db cos0009Db = (Cos0009Db)View.getObject(" from Cos0009Db where applNo = " + Common.sqlChar(dtl.getApplNo()) + " order by id desc ");
						if(cos0009Db != null){
							String tDay = "0";
							if("Y".equals(Common.get(cos0009Db.getIsClose()))){					
								tDay = Datetime.getDateDiff("d", Common.get(cos0009Db.getNotifyDate()), Common.get(cos0009Db.getReplyDate()));
							}else{
								tDay = Datetime.getDateDiff("d", Common.get(cos0009Db.getNotifyDate()), Datetime.getYYYMMDD());
							}
							if(Common.getInt(tDay) > Common.getInt(sys0001Db.getField1())){
								isNeedLight = "red";
							}
						}else{
							System.out.println("[TCBW]-[COSIN0701F]-[不良品追蹤作業]-[無廠商回覆資料檔，無法判別燈號]");
						}
					}else{
						Cos0010Db cos0010Db = (Cos0010Db)View.getObject(" from Cos0010Db where applNo = " + Common.sqlChar(dtl.getApplNo()) + " order by id desc ");
						if(cos0010Db != null){
							String tDay = "0";
							if("Y".equals(Common.get(cos0010Db.getIsClose()))){					
								tDay = Datetime.getDateDiff("d", Common.get(cos0010Db.getNotifyDate()), Common.get(cos0010Db.getReplyDate()));
							}else{
								tDay = Datetime.getDateDiff("d", Common.get(cos0010Db.getNotifyDate()), Datetime.getYYYMMDD());
							}
							if(Common.getInt(tDay) > Common.getInt(sys0001Db.getField1())){
								isNeedLight = "red";
							}
						}else{
							System.out.println("[TCBW]-[COSIN0701F]-[不良反應追蹤作業]-[無廠商回覆資料檔，無法判別燈號]");
						}
					}
					if("red".equals(isNeedLight)){
						rowArray[7] = "<img width='30px' src='../../images/ballRed.png'>";
					}else{
						rowArray[7] = "";
					}
					arrList.add(rowArray);
				}
				objList.clear();
				
				CPTMap.clear();
				CACMap.clear();
				subCodeNameMap.clear();
			}
		
		}
		return arrList;
	}
	
	
	
	
	

}
