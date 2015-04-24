package com.kangdainfo.tcbw.view.drgse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg1003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGSE0003F extends SuperBean{

	private String id;
	private String nti;
	private String phyyear;
	private String phynum;
	private String advyear;
	private String advnum;
	private String lotNum;

	private String advLevel;
	private String category;
	
	
	private String q_isQuery;
	private String q_id;
	private String q_nti;	
	private String q_phyyear;
	private String q_phynum;
	private String q_advyear;
	private String q_advnum;
	private String q_lotNum;

	private String q_advLevel;
	private String q_category;

	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getNti() {
		return checkGet(nti);
	}

	public void setNti(String nti) {
		this.nti = checkSet(nti);
	}

	public String getLotNum() {
		return checkGet(lotNum);
	}

	public void setLotNum(String lotNum) {
		this.lotNum = checkSet(lotNum);
	}
	
	public String getAdvLevel() {
		return checkGet(advLevel);
	}

	public void setAdvLevel(String advLevel) {
		this.advLevel = checkSet(advLevel);
	}

	public String getCategory() {
		return checkGet(category);
	}

	public void setCategory(String category) {
		this.category = checkSet(category);
	}
	
	public String getPhynum() {
		return checkGet(phynum);
	}

	public void setPhynum(String phynum) {
		this.phynum = checkSet(phynum);
	}
	
	public String getPhyyear() {
		return checkGet(phyyear);
	}

	public void setPhyyear(String phyyear) {
		this.phyyear = checkSet(phyyear);
	}
	
	
	
	public String getAdvnum() {
		return checkGet(advnum);
	}

	public void setAdvnum(String advnum) {
		this.advnum = checkSet(advnum);
	}
	
	public String getAdvyear() {
		return checkGet(advyear);
	}

	public void setAdvyear(String advyear) {
		this.advyear = checkSet(advyear);
	}
	

	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}
	
	public String getQ_nti() {
		return checkGet(q_nti);
	}

	public void setQ_nti(String qNti) {
		q_nti = checkSet(qNti);
	}
	
	public String getQ_lotNum() {
		return checkGet(q_lotNum);
	}

	public void setQ_lotNum(String qLotNum) {
		q_lotNum = checkSet(qLotNum);
	}
	
	public String getQ_advLevel() {
		return checkGet(q_advLevel);
	}

	public void setQ_advLevel(String qAdvLevel) {
		q_advLevel = checkSet(qAdvLevel);
	}
	
	
	public String getQ_category() {
		return checkGet(q_category);
	}

	public void setQ_category(String qCategory) {
		q_category = checkSet(qCategory);
	}
	
	public String getQ_phynum() {
		return checkGet(q_phynum);
	}

	public void setQ_phynum(String qPhynum) {
		q_phynum = checkSet(qPhynum);
	}
	
	public String getQ_phyyear() {
		return checkGet(q_phyyear);
	}

	public void setQ_phyyear(String qPhyyear) {
		q_phyyear = checkSet(qPhyyear);
	}

	public String getQ_advnum() {
		return checkGet(q_advnum);
	}

	public void setQ_advnum(String qAdvnum) {
		q_advnum = checkSet(qAdvnum);
	}
	
	public String getQ_advyear() {
		return checkGet(q_advyear);
	}

	public void setQ_advyear(String qAdvnum) {
		q_advyear = checkSet(qAdvnum);
	}
	
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		DRGSE0003F obj = this;
		
		Drg1003Db c = (Drg1003Db)View.getObject("from Drg1003Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setNti(c.getNti());
			obj.setCategory(c.getCategory());
			
			if(c.getCategory().equals("PHY")) {
				obj.setPhyyear(c.getYear());
				obj.setPhynum(c.getNum());
			}
			
			if(c.getCategory().equals("ADV")) {
				obj.setAdvyear(c.getYear());
				obj.setAdvnum(c.getNum());
			}
			

			obj.setLotNum(c.getLotNum());

			obj.setAdvLevel(c.getAdvLevel());
			
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg1003Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_nti()))
				hql += " and nti = " + Common.sqlChar(getQ_nti());
			if(!"".equals(getQ_category()))
				hql += " and category = " + Common.sqlChar(getQ_category());
			
			
			
			if(getQ_category().equals("PHY")) {
				if(!"".equals(getQ_phyyear()))
					hql += " and year = " + Common.sqlChar(getQ_phyyear());
				if(!"".equals(getQ_phynum()))
					hql += " and num = " + Common.sqlChar(getQ_phynum());
			}
			
			if(getQ_category().equals("ADV")) {
				if(!"".equals(getQ_advyear()))
					hql += " and year = " + Common.sqlChar(getQ_advyear());
				if(!"".equals(getQ_advnum()))
					hql += " and num = " + Common.sqlChar(getQ_advnum());
			}
			
			
			if(!"".equals(getQ_lotNum()))
				hql += " and lotNum = " + Common.sqlChar(getQ_lotNum());

			if(!"".equals(getQ_advLevel()))
				hql += " and advLevel = " + Common.sqlChar(getQ_advLevel());	
			
			


			
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> levelMap = TCBWCommon.getCommonCodeMap("DRG0308");
				
				
				for(Object dtlObj : objList) {				
					Drg1003Db dtl = (Drg1003Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());	
					rowArray[1] = Common.get(dtl.getCategory().equals("PHY")?"藥效改變(同產品)":dtl.getCategory().equals("ADV")?"不良反應(同產品)":"");
					rowArray[2] = Common.get(dtl.getNti()).equals("Y")?"是":Common.get(dtl.getLotNum()).equals("N")?"否":"";
					rowArray[3] = Common.get(dtl.getYear());
					rowArray[4] = Common.get(dtl.getLotNum()).equals("Y")?"同批號":Common.get(dtl.getLotNum()).equals("N")?"不同批號":"";
					rowArray[5] = Common.get(dtl.getNum());
					rowArray[6] = Common.get(dtl.getAdvLevel()!=null?levelMap.get(dtl.getAdvLevel()):"");
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}
	
//	protected String[][] getInsertCheckSQL(){	
//		String[][] checkSQLArray = new String[1][4];
//	 	checkSQLArray[0][0] = "select count(*) from Drg1003Db where code = " + Common.sqlChar(getCode());
//		checkSQLArray[0][1] = ">";
//		checkSQLArray[0][2] = "0";
//		checkSQLArray[0][3] = "該流程角色代碼已存在，請重新輸入！";
//		return checkSQLArray;
//	}

	@Override
	public void doCreate() throws Exception {
		Drg1003Db obj = new Drg1003Db();
		
		
		obj.setCategory(getCategory());
		obj.setNti(getNti());
		
		
		
		
		if(getCategory().equals("PHY")) {
			obj.setYear(getPhyyear());
			obj.setNum(getPhynum());
		}
		
		if(getCategory().equals("ADV")) {
			obj.setYear(getAdvyear());
			obj.setNum(getAdvnum());
		}
		
		obj.setLotNum(getLotNum());
		obj.setAdvLevel(getAdvLevel());
		
	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
//	protected String[][] getUpdateCheckSQL(){	
//		String[][] checkSQLArray = new String[1][4];	 	
//		checkSQLArray[0][0] = "select count(*) from Drg1003Db where code = " + Common.sqlChar(getCode()) + " and id != " + Common.getLong(getId()); 	
//	 	checkSQLArray[0][1] = ">";
//		checkSQLArray[0][2] = "0";
//		checkSQLArray[0][3] = "該流程角色代碼已存在，請重新輸入！";	
//		return checkSQLArray;		
//	}

	@Override
	public void doUpdate() throws Exception {
		Drg1003Db obj = (Drg1003Db)View.getObject(" from Drg1003Db where id = " + Common.getLong(getId()));
		if(obj != null){
			setId(Common.get(obj.getId()));
			obj.setNti(getNti());
			obj.setCategory(getCategory());
			
			if(getCategory().equals("PHY")) {
				obj.setYear(getPhyyear());
				obj.setNum(getPhynum());
			}
			
			if(getCategory().equals("ADV")) {
				obj.setYear(getAdvyear());
				obj.setNum(getAdvnum());
			}
			
			obj.setLotNum(getLotNum());
			obj.setAdvLevel(getAdvLevel());

		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}
	}

	@Override
	public void doDelete() throws Exception {
		Drg1003Db obj = (Drg1003Db)View.getObject(" from Drg1003Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
