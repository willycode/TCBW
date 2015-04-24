package com.kangdainfo.tcbw.util;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0002Db;

public class Con0002DbSearch extends SuperBean{
	
	private String id;
	private String formID;
	private String applNo;
	private String systemType;
	private String mailState;
	private String title;
	private String mailBody;
	private String sysType;
	
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
		String hql = " from Con0001Db where dbName='Con0002Db' and upLoadId="+ id;
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

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}
	
	public String getFormID() {
		return checkGet(formID);
	}

	public void setFormID(String formID) {
		this.formID = checkSet(formID);
	}

	public String getApplNo() {
		return checkGet(applNo);
	}

	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}
	
	public String getSystemType() {
		return checkGet(systemType);
	}

	public void setSystemType(String systemType) {
		this.systemType = checkSet(systemType);
	}

	public String getMailState() {
		return checkGet(mailState);
	}

	public void setMailState(String mailState) {
		this.mailState = checkSet(mailState);
	}

	public String getTitle() {
		return checkGet(title);
	}

	public void setTitle(String title) {
		this.title = checkSet(title);
	}

	public String getMailBody() {
		return get(mailBody);
	}

	public void setMailBody(String mailBody) {
		this.mailBody = checkSet(mailBody);
	}
	
	public String getSysType() {
		return checkGet(sysType);
	}

	public void setSysType(String sysType) {
		this.sysType = checkSet(sysType);
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		Con0002DbSearch obj = this;
		Con0002Db c = (Con0002Db)View.getObject("from Con0002Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setSystemType(c.getSystemType());
			obj.setFormID(c.getFormID());
			obj.setTitle(c.getTitle());
			obj.setMailBody(c.getMailBody().replaceAll("<p>", "").replaceAll("</p>", ""));
			obj.setConJSBuilder(this.genCon0001DbSet(c.getId()));
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con0002Db where systemType = " + Common.sqlChar(getSysType());
		
		if(!"".equals(Common.get(getApplNo())))
		{
			hql += " and applNo = " + Common.sqlChar(Common.get(getApplNo()));
		}	
		else
		{
			hql += " and formID = " + Common.sqlChar(getFormID());
		}	
			
		
		
		System.out.println(hql);
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRGST1");
				for(Object dtlObj : objList) {
					Con0002Db dtl = (Con0002Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());	
					rowArray[2] = Common.formatYYYMMDD(Common.get(dtl.getCreateDate()),4);			
					rowArray[3] = Common.get(dtl.getTitle()).replaceAll("<p>", "").replaceAll("</p>", "");
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


