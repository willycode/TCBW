package com.kangdainfo.tcbw.util;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0004Db;

public class Con0004DbSearch extends SuperBean{
	
	private String id;
	private String applNo;
	private String notifyDate;
	private String notifyBody;
	private String replyDate;
	private String replyBody;
	private String fileType;

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
	
	public String getNotifyDate() {
		return checkGet(notifyDate);
	}

	public void setNotifyDate(String notifyDate) {
		this.notifyDate = checkSet(notifyDate);
	}

	public String getNotifyBody() {
		return checkGet(notifyBody);
	}

	public void setNotifyBody(String notifyBody) {
		this.notifyBody = checkSet(notifyBody);
	}

	public String getReplyDate() {
		return checkGet(replyDate);
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = checkSet(replyDate);
	}

	public String getReplyBody() {
		return checkGet(replyBody);
	}

	public void setReplyBody(String replyBody) {
		this.replyBody = checkSet(replyBody);
	}
	
	public String getFileType() {
		return checkGet(fileType);
	}

	public void setFileType(String fileType) {
		this.fileType = checkSet(fileType);
	}

	// ========== 附件上傳 =================
	String[] conRow;
	public String[] getConRow() {return conRow;}
	public void setConRow(String[] conRow) {this.conRow = conRow;}

	public final String[] arrConFieldName = { "fileName", "fileExplan","fileRoute" };
	String conJSBuilder;

	public String getConJSBuilder() {
		if (conJSBuilder != null) return get(conJSBuilder);
		else return "";
	}
	public void setConJSBuilder(String conJSBuilder) {this.conJSBuilder = conJSBuilder;}
	public String genCon0001DbSet(Long id) throws Exception {
		String hql = " from Con0001Db where dbName='Con0004Db' and upLoadId="+ id;
		List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(hql + " order by id asc");
		if (null != conList && !conList.isEmpty()) {
			StringBuilder sb = new StringBuilder(1024);
			for (Con0001Db dtl : conList) {
				sb.append("addConRow('conTable'");
				for (int j = 0; j < arrConFieldName.length; j++) {
					if (j == 2) {
						String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
						sb.append(",").append(Common.sqlChar(attFile));
					} else {
						sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConFieldName[j])), true)));
					}
				}
				sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
				sb.append(");\n");
			}
			this.setConJSBuilder(sb.toString());
			return sb.toString();
		}
		return "";
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		Con0004DbSearch obj = this;
		Con0004Db c = (Con0004Db)View.getObject("from Con0004Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setApplNo(c.getApplNo());
			obj.setNotifyDate(c.getNotifyDate());
			obj.setNotifyBody(c.getNotifyBody().replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("<h2>","").replaceAll("</h2>",""));
			obj.setReplyDate(c.getReplyDate());
			obj.setReplyBody(c.getReplyBody());
			obj.setConJSBuilder(this.genCon0001DbSet(c.getId()));
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con0004Db where applNo = " + Common.sqlChar(getApplNo());
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {
					Con0004Db dtl = (Con0004Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.formatYYYMMDD(Common.get(dtl.getNotifyDate()),4);	
					
					String notifyBody = "";
					if(null != dtl.getNotifyBody() && !"".equals(dtl.getNotifyBody())){
						notifyBody = Common.get(dtl.getNotifyBody()).replaceAll("<p>", "").replaceAll("</p>", "")
							.replaceAll("<h2>","").replaceAll("</h2>","")
							.replaceAll("<h3>","").replaceAll("</h3>","")
							.replaceAll("&nbsp;", "").replaceAll("<br />", "");
					}
					rowArray[2] = notifyBody.length()>10?notifyBody.substring(0, 10):notifyBody;	//先replace再subString	
					rowArray[3] = Common.formatYYYMMDD(Common.get(dtl.getReplyDate()),4);
					String replyBody = "";
					if(null != dtl.getReplyBody() && !"".equals(dtl.getReplyBody())){
						replyBody = Common.get(dtl.getReplyBody()).replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("<h2>","").replaceAll("</h2>","").replaceAll("<h3>","").replaceAll("</h3>","");
					}
					rowArray[4] = replyBody.length()>10?replyBody.substring(0, 10):replyBody;
					//==null?"":obj.getMailBody()
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception{}

	@Override
	public void doUpdate() throws Exception{}

	@Override
	public void doDelete() throws Exception{}
	
}


