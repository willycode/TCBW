package com.kangdainfo.tcbw.view.medin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Med1001Db;

public class MEDIN0001F extends SuperBean{

	private String id;
	private String applNo;
	private String subject;
	private String summary;
	private String isStop;
	private String fileData;

	private String q_isQuery;
	private String q_id;
	private String q_applNo;
	private String q_subject;
	private String q_summary;
	private String q_isStop;	

	

	@Override
	public Object doQueryOne() throws Exception {
		MEDIN0001F obj = this;
		Med1001Db c = (Med1001Db)View.getObject("from Med1001Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setApplNo(c.getApplNo());
			obj.setSubject(c.getSubject());
			obj.setSummary(c.getSummary());
			obj.setIsStop(c.getIsStop());
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());	
			List<Con0001Db> con0001 = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'MEDIN0001F' and upLoadId = " + Common.getLong(getId()) + " order by id");
			if(con0001 != null && con0001.size()>0){
				String tempFilePath = "";
				for(Con0001Db o : con0001){
					tempFilePath += o.getFileRoute() + "|";
				}
				fileData = tempFilePath.substring(0, tempFilePath.length()-1);
			}else{
				fileData = "";
			}
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Med1001Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_applNo()))
				hql += " and applNo like " + Common.sqlChar("%" + getQ_applNo() + "%");
			if(!"".equals(getQ_summary()))
				hql += " and summary like " + Common.sqlChar("%" + getQ_summary() + "%");
			if(!"".equals(getQ_subject()))
				hql += " and subject like " + Common.sqlChar("%" + getQ_subject() + "%");
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
					Med1001Db dtl = (Med1001Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());		
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getSubject());
					rowArray[3] = Common.get(dtl.getSummary());
					rowArray[4] = Common.get(dtl.getIsStop()).equals("Y")?"是":Common.get(dtl.getIsStop()).equals("N")?"否":"";
					arrList.add(rowArray);
				}
				objList.clear();
			}		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Med1001Db obj = new Med1001Db();
		obj.setApplNo(getApplNo());
		obj.setSubject(getSubject());
		obj.setSummary(getSummary());
	    obj.setIsStop(getIsStop());
	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		if(!"".equals(getFileData())){
			String[] tempFilePath = getFileData().substring(0, getFileData().length()-1).split("\\|");
			for(String o: tempFilePath){
				String f = ".\\upload\\MED\\MEDIN0001F\\" + o.replaceAll(":","_").replaceAll("_;_","\\\\");
				File uploadFile = new File(f);
				if(uploadFile.exists()){
					Con0001Db con0001 = new Con0001Db();
					con0001.setFileKind("MEDIN0001F");
					con0001.setUpLoadId(obj.getId());
					con0001.setFileRoute(o);
					con0001.setFileName(uploadFile.getName());
					//con0001.setFileType(uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".") + 1));
					//con0001.setFileSize(String.valueOf(uploadFile.length()));
					con0001.setCreator(getEditID());
					con0001.setCreateDate(getEditDate());
					con0001.setCreateTime(getEditTime());
					ServiceGetter.getInstance().getTcbwService().save(con0001);
				}
			}
		}
		setId(Common.get(obj.getId()));
	}

	@Override
	public void doUpdate() throws Exception {
		Med1001Db obj = (Med1001Db)View.getObject(" from Med1001Db where id = " + Common.getLong(getId()));
		if(obj != null){	
			obj.setApplNo(getApplNo());
			obj.setSubject(getSubject());
			obj.setSummary(getSummary());
		    obj.setIsStop(getIsStop());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			java.util.List<Con0001Db> SaveorDelList = new java.util.ArrayList<Con0001Db>(); 
			java.util.Map<String, Con0001Db> oCon0001DbMap = new java.util.HashMap<String, Con0001Db>();
			java.util.List<Con0001Db> oCon0001DbList = (List<Con0001Db>) ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'MEDIN0101F' and upLoadId = " + Common.getLong(getId()));
			if(oCon0001DbList!=null && oCon0001DbList.size()>0){
				for(Con0001Db dtl : oCon0001DbList){
					oCon0001DbMap.put(Common.get(dtl.getFileRoute()), dtl);
				}
			}
			if(!"".equals(getFileData())){				
				String[] tempFilePath = getFileData().substring(0, getFileData().length()-1).split("\\|");
				for(String o: tempFilePath){
					//insert
					Con0001Db con0001 = oCon0001DbMap.get(o);
					if(con0001 != null){
						oCon0001DbMap.remove(o);
					}else{
						String f = ".\\upload\\MED\\MEDIN0001F\\" + o.replaceAll(":","_").replaceAll("_;_","\\\\");
						File uploadFile = new File(f);
						if(uploadFile.exists()){
							con0001 = new Con0001Db();
							con0001.setFileKind("MEDIN0001F");
							con0001.setUpLoadId(obj.getId());
							con0001.setFileRoute(o);
							con0001.setFileName(uploadFile.getName());
							//con0001.setFileType(uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".") + 1));
							//con0001.setFileSize(String.valueOf(uploadFile.length()));
							con0001.setCreator(getEditID());
							con0001.setCreateDate(getEditDate());
							con0001.setCreateTime(getEditTime());
							SaveorDelList.add(con0001);
						}
					}
				}
				ServiceGetter.getInstance().getTcbwService().saveBatch(SaveorDelList);
				SaveorDelList.clear();
				
				//delete
				Set del = oCon0001DbMap.keySet();
				for(Object o: del){
					Con0001Db con0001 = oCon0001DbMap.get(o);
					if(con0001 != null)
						SaveorDelList.add(con0001);
				}
				ServiceGetter.getInstance().getTcbwService().deleteBatch(SaveorDelList);
			}else{
				Set del = oCon0001DbMap.keySet();
				for(Object o: del){
					Con0001Db con0001 = oCon0001DbMap.get(o);
					if(con0001 != null)
						SaveorDelList.add(con0001);
				}
				ServiceGetter.getInstance().getTcbwService().deleteBatch(SaveorDelList);
			}
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Med1001Db obj = (Med1001Db)View.getObject(" from Med1001Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			java.util.ArrayList<Con0001Db> con0001 = (ArrayList<Con0001Db>)ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'MEDIN0101F' and upLoadId = " + Common.getLong(getId()));
			for(Con0001Db o : con0001){
				ServiceGetter.getInstance().getTcbwService().delete(o);
			}
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}
	
	public String getApplNo() {
		return checkGet(applNo);
	}

	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}

	public String getSubject() {
		return checkGet(subject);
	}

	public void setSubject(String subject) {
		this.subject = checkSet(subject);
	}

	public String getSummary() {
		return checkGet(summary);
	}

	public void setSummary(String summary) {
		this.summary = checkSet(summary);
	}

	public String getIsStop() {
		return checkGet(isStop);
	}

	public void setIsStop(String isStop) {
		this.isStop = checkSet(isStop);
	}
	
	public String getFileData() {
		return checkGet(fileData);
	}

	public void setFileData(String fileData) {
		this.fileData = checkSet(fileData);
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

	public String getQ_applNo() {
		return checkGet(q_applNo);
	}

	public void setQ_applNo(String qApplNo) {
		q_applNo = checkSet(qApplNo);
	}

	public String getQ_subject() {
		return checkGet(q_subject);
	}

	public void setQ_subject(String qSubject) {
		q_subject = checkSet(qSubject);
	}

	public String getQ_summary() {
		return checkGet(q_summary);
	}

	public void setQ_summary(String qSummary) {
		q_summary = checkSet(qSummary);
	}
	
	public String getQ_isStop() {
		return checkGet(q_isStop);
	}

	public void setQ_isStop(String qIsStop) {
		q_isStop = checkSet(qIsStop);
	}
	
}
