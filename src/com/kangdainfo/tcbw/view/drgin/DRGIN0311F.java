package com.kangdainfo.tcbw.view.drgin;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections.set.ListOrderedSet;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg4007Db;
import com.kangdainfo.tcbw.model.bo.Drg4011Db;


public class DRGIN0311F extends SuperBean{
	
	private String id;
	private String assessDesc;
	private String isClose;
	private String actionType;
	
	private String q_ingredient;
	private String q_drg4005Id;
	
	public String getId() {return checkGet(id);}
	public void setId(String s) {this.id = checkSet(s);}
	public String getAssessDesc() {return checkGet(assessDesc);}
	public void setAssessDesc(String s) {this.assessDesc = checkSet(s);}
	public String getIsClose() {return checkGet(isClose);}
	public void setIsClose(String s) {this.isClose = checkSet(s);}
	public String getActionType() {return checkGet(actionType);}
	public void setActionType(String s) {this.actionType = checkSet(s);}
	public String getQ_ingredient() {return checkGet(q_ingredient);}
	public void setQ_ingredient(String s) {this.q_ingredient = checkSet(s);}
	public String getQ_drg4005Id() {return checkGet(q_drg4005Id);}
	public void setQ_drg4005Id(String s) {this.q_drg4005Id = checkSet(s);}
	
	
	@Override
	public Object doQueryOne() throws Exception {
		DRGIN0311F obj = this;
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " 	select replyDate, ingredient, con1009Db.id,"
				+ "	sum(case when isEffectChange='Y' then 1 else 0 end) ,"
				+ "	sum(case when isBrandChange='Y' then 1 else 0 end) ,"
				+ "	sum(case when noBrandChange='Y' then 1 else 0 end) "
				+ " from Drg4009Db where 1=1  and (replyDate is not null or replyDate <> '')";
		if(null != getQ_ingredient() && !"".equals(getQ_ingredient())){
			hql += " and ingredient = " + Common.sqlChar(getQ_ingredient());
		}
		if(null != getQ_drg4005Id() && !"".equals(getQ_drg4005Id())){
			hql += " and drg4005Db.id = " + getQ_drg4005Id();
		}
		hql += " group by replyDate, ingredient, con1009Db.id ";
		hql += " order by replyDate, ingredient, con1009Db.id ";
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			if(objList!=null && objList.size()>0)
			{
				int medCount = 0;
				int isEffectChangeSum = 0;
				int isBrandChangeSum = 0;
				int noBrandChangeSum = 0;
				String[] rowArray = new String[7];
				for(Object dtlObj : objList)
				{
					Object[] o = (Object[])dtlObj;
					rowArray = new String[9];
					rowArray[0] = Common.get(o[1]);
					rowArray[1] = Common.get(o[0]);
					rowArray[2] = Common.get(View.getLookupField(" select medAgencyName from Con1009Db where id = "+o[2]));
					rowArray[3] = Common.get(o[3])+"件";
					rowArray[4] = Common.get(o[4])+"件";
					rowArray[5] = Common.get(o[5])+"件";
					rowArray[6] = Common.get(View.getLookupField(" select beforeBrand from Drg4009Db where con1009Db.id = "+o[2]+" and ingredient = "+Common.sqlChar(Common.get(o[1]))));
					rowArray[7] = Common.get(View.getLookupField(" select afterBrand from Drg4009Db where con1009Db.id = "+o[2]+" and ingredient = "+Common.sqlChar(Common.get(o[1]))));
					rowArray[8] = Common.get(View.getLookupField(" select comment from Drg4009Db where con1009Db.id = "+o[2]+" and ingredient = "+Common.sqlChar(Common.get(o[1]))));
					arrList.add(rowArray);
					medCount ++;
					isEffectChangeSum += Common.getInt(o[3]);
					isBrandChangeSum += Common.getInt(o[4]);
					noBrandChangeSum += Common.getInt(o[5]);
				}
				rowArray = new String[9];
				rowArray[0] = Common.get("");
				rowArray[1] = Common.get("共計");
				rowArray[2] = "總共"+Common.get(medCount)+"家";
				rowArray[3] = Common.get(isEffectChangeSum)+"件";
				rowArray[4] = Common.get(isBrandChangeSum)+"件";
				rowArray[5] = Common.get(noBrandChangeSum)+"件";
				rowArray[6] = "";
				rowArray[7] = "";
				rowArray[8] = "";
				arrList.add(rowArray);
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
	
	public DefaultTableModel getTableModel() throws Exception {
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		String[] columns = new String[] { "ingredient", "replyDate", "medAgencyName",
				"isEffectChange", "isBrandChange", "noBrandChange","beforeBrand","afterBrand","comment"};

		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		arrList = (ArrayList<String[]>) doQueryAll();
		if (null != arrList && arrList.size() > 0) {
			Object[][] rs = new Object[0][0];
			rs = (Object[][]) arrList.toArray(rs);
			model.setDataVector(rs, columns);

		} else {
			model = null;// 查詢無資料
		}
		return model;
	}
}
