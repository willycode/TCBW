package com.kangdainfo.tcbw.view.medex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Med0010Db;


/**
*<br>程式目的：藥品療效不等-廠商回覆
*<br>程式代號：drgex0306f
*<br>程式日期：1021126
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  MEDEX0303F extends MEDEX0301F
{
	private String med0010id;
	private String actionType;
	private String replyDate;
	private String replyContent;
	private String noticeDate;
    
    
    @Override
    public Object doQueryOne() throws Exception 
    {
	  MEDEX0303F obj = this;
	  if(getId() != null && !"".equals(getId())){
		  Med0010Db med0010 = (Med0010Db) View.getObject(" from Med0010Db where med0001Db.id=" + Common.getInt(getId()) + "and id in (select max(id) from Med0010Db)");
		  if(med0010 != null){
			  obj.setMed0010id(Common.get(med0010.getId()));
			  obj.setReplyContent(med0010.getReplyContent());
		  }
	  }
	  return obj;
	}

    @Override
    public Object doQueryAll() throws Exception 
    {
	  // TODO Auto-generated method stub
	  return null;
    }

    @Override
    public void doCreate() throws Exception 
    {
	  // TODO Auto-generated method stub
	
    }

    @Override
    public void doUpdate() throws Exception 
    {
    }

    @Override
    public void doDelete() throws Exception 
    {
	// TODO Auto-generated method stub
	
    }
    public String getAddFile() throws Exception 
	  {
		  StringBuilder sb = new StringBuilder(1024); 
			
		  String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED%' and dbName = 'Med0010DB' and upLoadId="+getMed0010id();

		  java.util.List objList = null;
		  if(getMed0010id() != null && !"".equals(getMed0010id()))
			  objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		  if(objList!=null && objList.size()>0)
		  {
				java.util.Iterator it = objList.iterator();
				int i=0;
				while (it.hasNext()) 
				{
					Con0001Db o = (Con0001Db) it.next();
					String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
					
					sb.append("<tr>\n");
					sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
					sb.append("<td  style='text-align:left'>");
					sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=MED&fileID=").append(attFile).append("\">");
					sb.append(o.getFileName());
					sb.append("</a></td>\n");				
					sb.append("<td >").append(o.getFileExplan()).append("</td>");
				
					
					sb.append("<td style='text-align:center'>");
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");
					sb.append("</td>\n");
				
					sb.append("</tr>\n");
					
					i++;
				}
		 }
		 return sb.toString(); 
	}

public String getActionType() {return checkGet(actionType);}
public void setActionType(String s) {this.actionType = checkSet(s);}

public String getReplyDate() {
	return checkGet(replyDate);
}
public void setReplyDate(String replyDate) {
	this.replyDate = checkSet(replyDate);
}
public String getReplyContent() {
	return checkGet(replyContent);
}
public void setReplyContent(String replyContent) {
	this.replyContent = checkSet(replyContent);
}
public String getNoticeDate() {
	return checkGet(noticeDate);
}
public void setNoticeDate(String noticeDate) {
	this.noticeDate = checkSet(noticeDate);
}


public String getMed0010id() {
	return checkGet(med0010id);
}


public void setMed0010id(String med0010id) {
	this.med0010id = checkSet(med0010id);
}

}


