package com.kangdainfo.tcbw.view.prcond;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con3001Db;
import com.kangdainfo.tcbw.model.bo.Con3002Db;


public class PROCOND0202F extends PROCOND0201F
{

	private String id;
	private String t_id;
	private String docdate;//文件日期
	private String docsubject;//文件主旨
	private String docsummary;//文件摘要
	private String isclose;//是否結案
	private javax.servlet.ServletRequest httpRequest;
	
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	private String[] prcondFileRow;

	public String[] getPrcondFileRow() {return checkGet(prcondFileRow);}
	public void setPrcondFileRow(String[] prcondFileRow) {this.prcondFileRow = checkSet(prcondFileRow);}

	private String CFileRowSBuilder;
	public String getCFileRowSBuilder() {		return get(CFileRowSBuilder);	}
	public void setCFileRowSBuilder(String cFileRowSBuilder) {		CFileRowSBuilder = checkSet(cFileRowSBuilder);	}
	
	public final String[] arrCOSIDFileFieldName = {"prcondFileName", "prcondFileNameRoute", "prcondFileExplan"};
	
	@Override
	public Object doQueryOne() throws Exception 
	{
		PROCOND0202F obj = this;
		
		Con3002Db c = (Con3002Db)View.getObject("from Con3002Db where id = " + Common.getLong(getT_id()));		
		
		if(c != null)
		{
			obj.setDocdate(c.getDocdate());//文件日期
			obj.setDocsubject(c.getDocsubject());//文件主旨
			obj.setDocsummary(c.getDocsummary());//文件摘要
			obj.setIsclose(c.getIsclose());//是否結案
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());	
			obj.setEditTime(c.getModifyTime());
			obj.setT_id(Common.get(c.getId()));
			
			// 相關資料頁籤
			java.util.List<Con0001Db> CFileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'PROCOND' and dbName = 'Con3002Db' and upLoadId = " + c.getId());
			this.genFileRowItemSet(CFileList, "prcond");
			if(CFileList!=null && CFileList.size()>0)
			{
				CFileList.clear();
			}
		}
		
		return obj;
	}
	
	public String genFileRowItemSet(java.util.List<Con0001Db> dtlList, String fileType) throws Exception
	{
		if(dtlList!=null && dtlList.size()>0)
		{
			
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Con0001Db dtl : dtlList)
	    	{
	    		sb.append("addFile").append(fileType).append("('tabFile" + fileType + "'");
	    		sb.append(",").append(Common.sqlChar(dtl.getFileName()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFileRoute()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFileExplan()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setCFileRowSBuilder(sb.toString());
System.out.println(sb.toString());
	    }
		return "";
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql =  " from Con3002Db ";
		       hql += " where con3001Db.id="+Common.getLong(getId());
		       
		       
		System.out.println("hql==="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{

				for(Object dtlObj : objList) 
				{				
					Con3002Db dtl = (Con3002Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getDocdate());//文件日期	
					rowArray[2] = Common.get(dtl.getDocsubject());//文件主旨
					rowArray[3] = Common.get(dtl.getIsclose());//是否結案
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	


	@Override
	public void doCreate() throws Exception 
	{
		Con3002Db obj = new Con3002Db();
		
		Con3001Db master = new Con3001Db();
		
		master.setId(Common.getLong(getId()));
		
		obj.setCon3001Db(master);
		
		obj.setDocdate(getDocdate());//文件日期
		obj.setDocsubject(getDocsubject());//文件主旨
		obj.setDocsummary(getDocsummary());//文件摘要
		obj.setIsclose(getIsclose());//是否結案

        obj.setCreateDate(getEditDate());
        obj.setCreateTime(getEditTime());
        obj.setCreator(getEditID());
        obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
	    
	    ServiceGetter.getInstance().getTcbwService().save(obj);
		
		setT_id(Common.get(obj.getId()));
		
		String yyymmdd = Datetime.getYYYMMDD();
	    String hhmmss = Datetime.getHHMMSS();
		try
		{
			ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(getHttpRequest(), Common.getLong(getT_id()), "PROCOND", "PROCOND",getPrcondFileRow(), "Con3002Db",arrCOSIDFileFieldName, getUserID(), yyymmdd, hhmmss);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	

	@Override
	public void doUpdate() throws Exception 
	{
		Con3002Db obj = (Con3002Db)View.getObject(" from Con3002Db where id = " + Common.getLong(getT_id()));
		
		if(obj != null)
		{		
		 
			obj.setDocdate(getDocdate());//文件日期
			obj.setDocsubject(getDocsubject());//文件主旨
			obj.setDocsummary(getDocsummary());//文件摘要
			obj.setIsclose(getIsclose());//是否結案
			
			obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
		    
		    String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();
			
			try
			{
				ServiceGetter.getInstance().getTcbwService().getCosexDao().updateCon0001Db(getHttpRequest(), Common.getLong(getT_id()), "PROCOND", "PROCOND",getPrcondFileRow(), "Con3002Db",arrCOSIDFileFieldName, getUserID(), yyymmdd, hhmmss);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		    
			ServiceGetter.getInstance().getTcbwService().update(obj);
			
			setT_id(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception
	{
		Con3002Db obj = (Con3002Db)View.getObject(" from Con3002Db where id = " + Common.getLong(getT_id()));
	
		if(obj != null)
		{
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setT_id("");
		}
		else
		{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
	
	
	//附件
	public String getAddFile() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
	
		String hql =" from Con0001Db where fileKind='PROCOND' ";
		       hql +=" and systemType like 'PRCOND0202f' ";
		       hql +=" and dbName='Con3002Db' ";
		       hql +=" and upLoadId="+Common.getLong(getT_id());
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
				sb.append("<td style='text-align:center'>");
							
				sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				
				sb.append("</td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}

	
	
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getT_id() {
		return checkGet(t_id);
	}

	public void setT_id(String tId) {
		t_id = checkSet(tId);
	}

	public String getDocdate() {
		return checkGet(docdate);
	}

	public void setDocdate(String docdate) {
		this.docdate = checkSet(docdate);
	}

	public String getDocsubject() {
		return checkGet(docsubject);
	}

	public void setDocsubject(String docsubject) {
		this.docsubject = checkSet(docsubject);
	}

	public String getDocsummary() {
		return checkGet(docsummary);
	}

	public void setDocsummary(String docsummary) {
		this.docsummary = checkSet(docsummary);
	}

	public String getIsclose() {
		return checkGet(isclose);
	}

	public void setIsclose(String isclose) {
		this.isclose = checkSet(isclose);
	}


	
	

}
