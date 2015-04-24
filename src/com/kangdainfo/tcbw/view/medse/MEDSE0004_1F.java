package com.kangdainfo.tcbw.view.medse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med1006Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDSE0004_1F extends MEDSE0004F{

	private String code;	//代碼
	private String id2;
	private String level2code;	//代碼
	private String name2;	//名稱
	private String term2;
	private String definition2;
	private String fdaCode2;		//FDA代碼
	private String fdaName2;		//FDA名稱
	private String id;
	

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getCode() {
		return checkGet(code);
	}

	public void setCode(String code) {
		this.code = checkSet(code);
	}

	
	public String getId2() {
		return checkGet(id2);
	}

	public void setId2(String id2) {
		this.id2 = checkSet(id2);
	}

	public String getLevel2code() {
		return checkGet(level2code);
	}

	public void setLevel2code(String level2code) {
		this.level2code = checkSet(level2code);
	}
	
	public String getName2() {
		return checkGet(name2);
	}

	public void setName2(String name2) {
		this.name2 = checkSet(name2);
	}

	public String getTerm2() {
		return checkGet(term2);
	}

	public void setTerm2(String term2) {
		this.term2 = checkSet(term2);
	}
	
	public String getDefinition2() {
		return checkGet(definition2);
	}

	public void setDefinition2(String definition2) {
		this.definition2 = checkSet(definition2);
	}
	public String getFdaCode2() {
		return checkGet(fdaCode2);
	}

	public void setFdaCode2(String fdaCode2) {
		this.fdaCode2 = checkSet(fdaCode2);
	}
	public String getFdaName2() {
		return checkGet(fdaName2);
	}

	public void setFdaName2(String fdaName2) {
		this.fdaName2 = checkSet(fdaName2);
	}
	
	@Override
	public Object doQueryOne() throws Exception 
	{
		MEDSE0004_1F obj = this;
		Med1006Db m = (Med1006Db)View.getObject("from Med1006Db where id = " + Common.getLong(getId2()));		
		if(m != null)
		{
			obj.setCode(m.getLevel1code());		//取出Level1的code
			obj.setId2(Common.get(m.getId()));
			obj.setLevel2code(m.getCode());
			obj.setName2(m.getName());
			obj.setTerm2(m.getTerm());
			obj.setDefinition2(m.getDefinition());
			obj.setFdaCode2(m.getFdacode());
			obj.setFdaName2(m.getFdaname());
			obj.setEditID(m.getModifier());
			obj.setEditDate(m.getModifydate());		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med1006Db where 1=1 ";
			   hql += " and level1Code = " + Common.sqlChar(getCode());
			   hql += " and codelevel = '2'";
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
//				java.util.Map<String, String> sysMap = TCBWCommon.getCommonCodeMap("SYS");

				for(Object dtlObj : objList) {	
					Med1006Db dtl = (Med1006Db)dtlObj;
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getCode());
					rowArray[2] = Common.get(dtl.getName());
					rowArray[3] = Common.get(dtl.getTerm());
					if(dtl.getDefinition().length() > 100) {
						rowArray[4] = Common.get(dtl.getDefinition().substring(0,100));
					} else {
						rowArray[4] = Common.get(dtl.getDefinition());
					}
					rowArray[5] = Common.get(dtl.getFdacode());
					rowArray[6] = Common.get(dtl.getFdaname());
					rowArray[7] = Common.get(dtl.getLevel1code());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Med1006Db obj = new Med1006Db();
		obj.setCode(getLevel2code());
		obj.setName(getName2());
		obj.setTerm(getTerm2());
		obj.setDefinition(getDefinition2());
		obj.setLevel1code(getCode());
		obj.setFdacode(getFdaCode2());
		obj.setFdaname(getFdaName2());
	    obj.setCreator(getEditID());
	    obj.setCreatedate(getEditDate());
	    obj.setCreatetime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifydate(getEditDate());
	    obj.setModifytime(getEditTime());
	    obj.setCodelevel("2");
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId2(Common.get(obj.getId()));
	}

	@Override
	public void doUpdate() throws Exception {
		Med1006Db obj = (Med1006Db)View.getObject(" from Med1006Db where id = " + Common.getLong(getId2()));
		if(obj != null){		
			obj.setCode(getLevel2code());
			obj.setName(getName2());
			obj.setTerm(getTerm2());
			obj.setDefinition(getDefinition2());
			obj.setLevel1code(getCode());
			obj.setFdacode(getFdaCode2());
			obj.setFdaname(getFdaName2());
		    obj.setModifier(getEditID());
		    obj.setModifydate(getEditDate());
		    obj.setModifytime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId2(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Med1006Db obj = (Med1006Db)View.getObject(" from Med1006Db where id = " + Common.getLong(getId2()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId2("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
}
