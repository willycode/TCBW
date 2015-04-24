package com.kangdainfo.tcbw.view.medse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med1002Db;

public class MEDSE0001F extends SuperBean{

	private String id;
	private String matter;
	private String isYes;
	private String isYesType;
	private String isNo;
	private String isNoType;
	private String isUnknown;
	private String isUnknownType;
	private String seq;
	private String isStop;
	
	private String q_isQuery;
	private String q_id;
	private String q_matter;
	private String q_isStop;	


	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}
	
	public String getMatter() {
		return checkGet(matter);
	}

	public void setMatter(String matter) {
		this.matter = checkSet(matter);
	}

	public String getIsYes() {
		return checkGet(isYes);
	}

	public void setIsYes(String isYes) {
		this.isYes = checkSet(isYes);
	}

	public String getIsYesType() {
		return checkGet(isYesType);
	}

	public void setIsYesType(String isYesType) {
		this.isYesType = checkSet(isYesType);
	}

	public String getIsNo() {
		return checkGet(isNo);
	}

	public void setIsNo(String isNo) {
		this.isNo = checkSet(isNo);
	}

	public String getIsNoType() {
		return checkGet(isNoType);
	}

	public void setIsNoType(String isNoType) {
		this.isNoType = checkSet(isNoType);
	}

	public String getIsUnknown() {
		return checkGet(isUnknown);
	}

	public void setIsUnknown(String isUnknown) {
		this.isUnknown = checkSet(isUnknown);
	}

	public String getIsUnknownType() {
		return checkGet(isUnknownType);
	}

	public void setIsUnknownType(String isUnknownType) {
		this.isUnknownType = checkSet(isUnknownType);
	}

	public String getSeq() {
		return checkGet(seq);
	}

	public void setSeq(String seq) {
		this.seq = checkSet(seq);
	}

	public String getIsStop() {
		return checkGet(isStop);
	}

	public void setIsStop(String isStop) {
		this.isStop = checkSet(isStop);
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

	public String getQ_matter() {
		return checkGet(q_matter);
	}

	public void setQ_matter(String qMatter) {
		q_matter = checkSet(qMatter);
	}
	
	public String getQ_isStop() {
		return checkGet(q_isStop);
	}

	public void setQ_isStop(String qIsStop) {
		q_isStop = checkSet(qIsStop);
	}

	@Override
	public Object doQueryOne() throws Exception {
		MEDSE0001F obj = this;
		Med1002Db c = (Med1002Db)View.getObject("from Med1002Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setMatter(c.getMatter());
			obj.setIsYes(Common.get(c.getIsYes()));
			obj.setIsYesType(c.getIsYesType());
			obj.setIsNo(Common.get(c.getIsNo()));
			obj.setIsNoType(c.getIsNoType());
			obj.setIsUnknown(Common.get(c.getIsUnknown()));
			obj.setIsUnknownType(c.getIsUnknownType());
			obj.setSeq(Common.get(c.getSeq()));
			obj.setIsStop(c.getIsStop());
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
		String hql = " from Med1002Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_matter()))
				hql += " and matter like " + Common.sqlChar("%" + getQ_matter() + "%");
			if(!"".equals(getQ_isStop()))
				hql += " and isStop = " + Common.sqlChar(getQ_isStop());
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {				
					Med1002Db dtl = (Med1002Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());		
					rowArray[1] = Common.get(dtl.getMatter());	
					rowArray[2] = (Common.get(dtl.getIsYesType()).equals("0")?"+":Common.get(dtl.getIsYesType()).equals("1")?"-":"") + Common.get(dtl.getIsYes());	
					rowArray[3] = (Common.get(dtl.getIsNoType()).equals("0")?"+":Common.get(dtl.getIsNoType()).equals("1")?"-":"") + Common.get(dtl.getIsNo());	
					rowArray[4] = (Common.get(dtl.getIsUnknownType()).equals("0")?"+":Common.get(dtl.getIsUnknownType()).equals("1")?"-":"") + Common.get(dtl.getIsUnknown());	
					rowArray[5] = Common.get(dtl.getSeq());		
					rowArray[6] = Common.get(dtl.getIsStop()).equals("Y")?"是":Common.get(dtl.getIsStop()).equals("N")?"否":"";
					arrList.add(rowArray);
				}
				objList.clear();
			}		
		}
		return arrList;
	}
	
	

	@Override
	public void doCreate() throws Exception {
		Med1002Db obj = new Med1002Db();
		obj.setMatter(getMatter());
		obj.setIsYes(Integer.valueOf(getIsYes()));
		obj.setIsYesType(getIsYesType());
		obj.setIsNo(Integer.valueOf(getIsNo()));
		obj.setIsNoType(getIsNoType());
		obj.setIsUnknown(Integer.valueOf(getIsUnknown()));
		obj.setIsUnknownType(getIsUnknownType());
		obj.setSeq(Integer.valueOf(getSeq()));
	    obj.setIsStop(getIsStop());
	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
	@Override
	public void doUpdate() throws Exception {
		Med1002Db obj = (Med1002Db)View.getObject(" from Med1002Db where id = " + Common.getLong(getId()));
		if(obj != null){	
			obj.setMatter(getMatter());
			obj.setIsYes(Integer.valueOf(getIsYes()));
			obj.setIsYesType(getIsYesType());
			obj.setIsNo(Integer.valueOf(getIsNo()));
			obj.setIsNoType(getIsNoType());
			obj.setIsUnknown(Integer.valueOf(getIsUnknown()));
			obj.setIsUnknownType(getIsUnknownType());
			obj.setSeq(Integer.valueOf(getSeq()));
		    obj.setIsStop(getIsStop());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Med1002Db obj = (Med1002Db)View.getObject(" from Med1002Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
