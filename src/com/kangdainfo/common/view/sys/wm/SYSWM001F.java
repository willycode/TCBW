package com.kangdainfo.common.view.sys.wm;

import java.io.File;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonNews;
import com.kangdainfo.common.util.BeanUtil;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.query.params.QueryParam;
import com.kangdainfo.persistence.hibernate3.query.params.QueryParams;

/**
*<br>程式目的：最新消息維護
*<br>程式代號：syswm001f
*<br>程式日期：0960726
*<br>程式作者：clive.chang
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  SYSWM001F extends SuperBean{

String masterId;
String id;
String subject;
String content;
String startDate;
String endDate;
String isHTML;
String isOpen;
String itemPicture1;
String itemPicture2;
String itemPicture3;
String itemPicture4;
String itemPicture5;
String itemPicture6;
String itemPicture7;
String itemPicture8;
String itemPicture9;
String itemPicture10;
String filestoreLocation;

String q_masterId;
String q_subject;
String q_content;
String q_startDate;
String q_startDateE;
String q_endDate;
String q_endDateE;
String q_isHTML;
String q_isOpen;

public String getMasterId(){ return checkGet(masterId); }
public void setMasterId(String s){ masterId=checkSet(s); }
public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getSubject(){ return checkGet(subject); }
public void setSubject(String s){ subject=checkSet(s); }
public String getContent(){return checkGet(content);}
public void setContent(String s){content=checkSet(s);}
public String getStartDate(){ return checkGet(startDate); }
public void setStartDate(String s){ startDate=checkSet(s); }
public String getEndDate(){ return checkGet(endDate); }
public void setEndDate(String s){ endDate=checkSet(s); }
public String getIsHTML(){ return checkGet(isHTML); }
public void setIsHTML(String s){ isHTML=checkSet(s); }
public String getItemPicture1(){ return checkGet(itemPicture1); }
public void setItemPicture1(String s){ itemPicture1=checkSet(s); }
public String getItemPicture2(){ return checkGet(itemPicture2); }
public void setItemPicture2(String s){ itemPicture2=checkSet(s); }
public String getItemPicture3(){ return checkGet(itemPicture3); }
public void setItemPicture3(String s){ itemPicture3=checkSet(s); }
public String getItemPicture4(){ return checkGet(itemPicture4); }
public void setItemPicture4(String s){ itemPicture4=checkSet(s); }
public String getItemPicture5(){ return checkGet(itemPicture5); }
public void setItemPicture5(String s){ itemPicture5=checkSet(s); }
public String getItemPicture6(){ return checkGet(itemPicture6); }
public void setItemPicture6(String s){ itemPicture6=checkSet(s); }
public String getItemPicture7(){ return checkGet(itemPicture7); }
public void setItemPicture7(String s){ itemPicture7=checkSet(s); }
public String getItemPicture8(){ return checkGet(itemPicture8); }
public void setItemPicture8(String s){ itemPicture8=checkSet(s); }
public String getItemPicture9(){ return checkGet(itemPicture9); }
public void setItemPicture9(String s){ itemPicture9=checkSet(s); }
public String getItemPicture10(){ return checkGet(itemPicture10); }
public void setItemPicture10(String s){ itemPicture10=checkSet(s); }
public String getFilestoreLocation(){ return checkGet(filestoreLocation); }
public void setFilestoreLocation(String s){ filestoreLocation=checkSet(s); }
public String getIsOpen() { return checkGet(isOpen);}
public void setIsOpen(String isOpen) {this.isOpen = checkSet(isOpen);}


public String getQ_isOpen() {return checkGet(q_isOpen);}
public void setQ_isOpen(String open) {q_isOpen = checkSet(open);}
public String getQ_masterId(){ return checkGet(q_masterId); }
public void setQ_masterId(String s){ q_masterId=checkSet(s); }
public String getQ_subject(){ return checkGet(q_subject); }
public void setQ_subject(String s){ q_subject=checkSet(s); }
public String getQ_content(){ return checkGet(q_content); }
public void setQ_content(String s){ q_content=checkSet(s); }
public String getQ_startDate(){ return checkGet(q_startDate); }
public void setQ_startDate(String s){ q_startDate=checkSet(s); }
public String getQ_startDateE(){ return checkGet(q_startDateE); }
public void setQ_startDateE(String s){ q_startDateE=checkSet(s); }
public String getQ_endDate(){ return checkGet(q_endDate); }
public void setQ_endDate(String s){ q_endDate=checkSet(s); }
public String getQ_endDateE(){ return checkGet(q_endDateE); }
public void setQ_endDateE(String s){ q_endDateE=checkSet(s); }
public String getQ_isHTML(){ return checkGet(q_isHTML); }
public void setQ_isHTML(String s){ q_isHTML=checkSet(s); }

String q_id;
public String getQ_id(){ return checkGet(q_id); }
public void setQ_id(String s){ q_id=checkSet(s); }

String itemPictureHTML;
public void setItemPictureHTML(String s) { itemPictureHTML = s; }
public String getItemPictureHTML(){
	if (itemPictureHTML==null) return "";
	else return itemPictureHTML.trim();
}

boolean isFAQ = false;
public boolean isFAQ() {return isFAQ;}
public void setFAQ(boolean isFAQ) {this.isFAQ = isFAQ;}

final public String[] arrFileNames = new String[]{"itemPicture1","itemPicture2","itemPicture3","itemPicture4","itemPicture5","itemPicture6","itemPicture7","itemPicture8","itemPicture9","itemPicture10"};

private void genAttFileHTML(Object obj) {
	StringBuffer sbHTML = new StringBuffer(500).append("");			
	for (int i=0; i<arrFileNames.length; i++) {
		String[] arrFileName;
		String attFile;
		try {			
			attFile = Common.get(BeanUtil.getProperty(obj,arrFileNames[i]));
			arrFileName=attFile.split(":;:");
			if (arrFileName.length>1) {
				sbHTML.append("<tr>\n");
				sbHTML.append("<td nowrap bgcolor='E2F0CF' class='td_form'>附件").append((i+1)).append("：</td>");
				sbHTML.append("<td nowrap bgcolor='F6FCE9' class='td_form_white'  style='text-align:left'>");
				// onclick=\"../downloadFileSimple?fileID='").append(attFile).append("');\"
				sbHTML.append("<a class=\"text_link_b\" href=\"../downloadFileSimple?fileID=").append(attFile).append("\">");
				sbHTML.append(arrFileName[1]);
				sbHTML.append("</a></td>\n");
				sbHTML.append("</tr>\n");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	setItemPictureHTML(sbHTML.toString());	
}


@Override
public void doCreate() throws Exception {
	CommonNews obj = new CommonNews();
	
	CommonCode master = new CommonCode();
	master.setId(Integer.parseInt(masterId));
	obj.setCommonCode(master);
	obj.setSubject(subject);
	obj.setContent(content);
	obj.setStartDate(startDate);
	obj.setEndDate(endDate);
	obj.setIsHtml(isHTML);
	obj.setIsOpen(isOpen);
	obj.setItemPicture1(itemPicture1);
	obj.setItemPicture2(itemPicture2);
	obj.setItemPicture3(itemPicture3);
	obj.setItemPicture4(itemPicture4);
	obj.setItemPicture5(itemPicture5);
	obj.setItemPicture6(itemPicture6);
	obj.setItemPicture7(itemPicture7);
	obj.setItemPicture8(itemPicture8);
	obj.setItemPicture9(itemPicture9);
	obj.setItemPicture10(itemPicture10);
	obj.setEditId(getEditID());
	obj.setEditDate(getEditDate());
	obj.setEditTime(getEditTime());
	ServiceGetter.getInstance().getCommonService().getCommonNewsDao().saveCommonNews(obj);
	setId(obj.getId().toString());
}


@Override
public void doUpdate() throws Exception {
	CommonNews obj = (CommonNews) View.getObject("from CommonNews where id=" + Common.getInt(id));
	if (obj!=null) {
		//刪除檔案
		String[] arrFileName=null;	
		if (!"".equals(Common.get(obj.getItemPicture1())) && !Common.get(obj.getItemPicture1()).equals(Common.get(itemPicture1))) {
			arrFileName=obj.getItemPicture1().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}
		if (!"".equals(Common.get(obj.getItemPicture2())) && !Common.get(obj.getItemPicture2()).equals(Common.get(itemPicture2))) {
			arrFileName=obj.getItemPicture2().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}
		if (!"".equals(Common.get(obj.getItemPicture3())) && !Common.get(obj.getItemPicture3()).equals(Common.get(itemPicture3))) {
			arrFileName=obj.getItemPicture3().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}		
		if (!"".equals(Common.get(obj.getItemPicture4())) && !Common.get(obj.getItemPicture4()).equals(Common.get(itemPicture4))) {
			arrFileName=obj.getItemPicture4().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}
		if (!"".equals(Common.get(obj.getItemPicture5())) && !Common.get(obj.getItemPicture5()).equals(Common.get(itemPicture5))) {
			arrFileName=obj.getItemPicture5().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}
		if (!"".equals(Common.get(obj.getItemPicture6())) && !Common.get(obj.getItemPicture6()).equals(Common.get(itemPicture6))) {
			arrFileName=obj.getItemPicture6().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}
		if (!"".equals(Common.get(obj.getItemPicture7())) && !Common.get(obj.getItemPicture7()).equals(Common.get(itemPicture7))) {
			arrFileName=obj.getItemPicture7().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}
		if (!"".equals(Common.get(obj.getItemPicture8())) && !Common.get(obj.getItemPicture8()).equals(Common.get(itemPicture8))) {
			arrFileName=obj.getItemPicture8().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}
		if (!"".equals(Common.get(obj.getItemPicture9())) && !Common.get(obj.getItemPicture9()).equals(Common.get(itemPicture9))) {
			arrFileName=obj.getItemPicture9().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}		
		if (!"".equals(Common.get(obj.getItemPicture10())) && !Common.get(obj.getItemPicture10()).equals(Common.get(itemPicture10))) {
			arrFileName=obj.getItemPicture10().split(":;:");
			if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
		}			
		obj.setSubject(subject);
		obj.setContent(content);
		obj.setStartDate(startDate);
		obj.setEndDate(endDate);
		obj.setIsHtml(isHTML);
		obj.setIsOpen(isOpen);
		obj.setItemPicture1(itemPicture1);
		obj.setItemPicture2(itemPicture2);
		obj.setItemPicture3(itemPicture3);
		obj.setItemPicture4(itemPicture4);
		obj.setItemPicture5(itemPicture5);
		obj.setItemPicture6(itemPicture6);
		obj.setItemPicture7(itemPicture7);
		obj.setItemPicture8(itemPicture8);
		obj.setItemPicture9(itemPicture9);
		obj.setItemPicture10(itemPicture10);
		obj.setEditId(getEditID());
		obj.setEditDate(getEditDate());
		obj.setEditTime(getEditTime());
	    ServiceGetter.getInstance().getCommonService().getCommonNewsDao().updateCommonNews(obj);		
	}
	/**
	CommonCode master = new CommonCode();
	master.setId(Integer.parseInt(masterId));
	obj.setCommonCode(master);
	obj.setId(Integer.parseInt(id));
	**/
}


@Override
public void doDelete() throws Exception {
    ServiceGetter.getInstance().getCommonService().getCommonNewsDao().deleteCommonNews(Common.getInt(id));
    
	//刪除檔案
	String[] arrFileName=null;
	if (!"".equals(Common.get(itemPicture1))) {
		arrFileName=itemPicture1.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
	if (!"".equals(Common.get(itemPicture2))) {
		arrFileName=itemPicture2.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
	if (!"".equals(Common.get(itemPicture3))) {
		arrFileName=itemPicture3.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
	if (!"".equals(Common.get(itemPicture4))) {
		arrFileName=itemPicture4.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
	if (!"".equals(Common.get(itemPicture5))) {
		arrFileName=itemPicture5.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
	if (!"".equals(Common.get(itemPicture6))) {
		arrFileName=itemPicture6.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
	if (!"".equals(Common.get(itemPicture7))) {
		arrFileName=itemPicture7.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
	if (!"".equals(Common.get(itemPicture8))) {
		arrFileName=itemPicture8.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
	if (!"".equals(Common.get(itemPicture9))) {
		arrFileName=itemPicture9.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
	if (!"".equals(Common.get(itemPicture10))) {
		arrFileName=itemPicture10.split(":;:");
		if (arrFileName.length>1) Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]));
	}
}


/**
 * <br>
 * <br>目的：依主鍵查詢單一資料
 * <br>參數：無
 * <br>傳回：傳回本物件
*/

public SYSWM001F  doQueryOne() throws Exception {
	SYSWM001F obj = this;
	CommonNews c = (CommonNews)ServiceGetter.getInstance().getCommonService().getCommonNewsDao().load(CommonNews.class,Integer.parseInt(getId()));
	if (c!=null) {
        obj.setMasterId(c.getCommonCode().getId().toString());
        obj.setId(c.getId().toString());
        obj.setSubject(c.getSubject());
        obj.setContent(c.getContent());
        obj.setStartDate(c.getStartDate());
        obj.setEndDate(c.getEndDate());
        obj.setIsHTML(c.getIsHtml());
        obj.setIsOpen(c.getIsOpen());
        obj.setItemPicture1(c.getItemPicture1());
        obj.setItemPicture2(c.getItemPicture2());
        obj.setItemPicture3(c.getItemPicture3());
        obj.setItemPicture4(c.getItemPicture4());
        obj.setItemPicture5(c.getItemPicture5());
        obj.setItemPicture6(c.getItemPicture6());
        obj.setItemPicture7(c.getItemPicture7());
        obj.setItemPicture8(c.getItemPicture8());
        obj.setItemPicture9(c.getItemPicture9());
        obj.setItemPicture10(c.getItemPicture10());
        obj.setEditID(c.getEditId());
        obj.setEditDate(c.getEditDate());
        obj.setEditTime(c.getEditTime());
        genAttFileHTML(obj);
	} else throw new Exception("查無該筆資料！");
	return obj;
}


	/**
	 * <br>
	 * <br>目的：依查詢欄位查詢多筆資料
	 * <br>參數：無
	 * <br>傳回：傳回ArrayList
	*/
	public java.util.ArrayList<String[]> doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		if (!"".equals(getQuerySubmit())) setQ_id("");		
		QueryParams q = new QueryParams();
		if (!"".equals(getQ_id())) {
			q.add(new QueryParam("and","id","=",Common.getInt(getQ_id())));
		} else {
			if (!"".equals(getQ_masterId())) {
				q.add(new QueryParam("and","commonCode.id","=",Common.getInt(getQ_masterId())));
			} else {
				if (isFAQ()) {
					q.add(new QueryParam("and","commonCode.codeId","between",new String[]{"51","80"}));
				}
			}
			if (!"".equals(getQ_subject()))
				q.add(new QueryParam("and","subject","like",getQ_subject()));
			if (!"".equals(getQ_content()))
				q.add(new QueryParam("and","content","like",getQ_content()));
			if (!"".equals(getQ_startDate()))
				q.add(new QueryParam("and","startDate",">=",getQ_startDate()));
			if (!"".equals(getQ_startDateE()))
				q.add(new QueryParam("and","startDate","<=",getQ_startDateE()));		
			if (!"".equals(getQ_endDate()))
				q.add(new QueryParam("and","endDate",">=",getQ_endDate()));
			if (!"".equals(getQ_endDateE()))
				q.add(new QueryParam("and","endDate","<=",getQ_endDateE()));		
			if (!"".equals(getQ_isHTML()))
				q.add(new QueryParam("and","isHtml","=",getQ_isHTML()));
			if (!"".equals(getQ_isOpen()))
				q.add(new QueryParam("and","isOpen","=",getQ_isOpen()));				
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().getCommonNewsDao().loadObjectsCountByParams(CommonNews.class, q));
		
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getCommonNewsDao().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().getCommonNewsDao().loadObjectsByParams(CommonNews.class, q, this.getRecordStart(), this.getPageSize(), "startDate desc, id desc", false);			
			if (objList != null && objList.size() > 0) {
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) {
					CommonNews o = (CommonNews) it.next();
					String rowArray[] = new String[6];
					rowArray[0] = Common.get(o.getCommonCode().getCodeName());
					rowArray[1] = Common.get(o.getId());
					rowArray[2] = Common.get(o.getSubject());
					rowArray[3] = Common.get(o.getStartDate());
					rowArray[4] = Common.get(o.getEndDate());
					rowArray[5] = Common.get(o.getIsOpen());
					arrList.add(rowArray);	
				}
			}
		}
		return arrList;
		
		/**
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		if (!"".equals(getQuerySubmit())) setQ_id("");
		
		StringBuffer sb = new StringBuffer();
		sb.append("from CommonNews where 1=1 ");		
		if (!"".equals(getQ_id())) {
			sb.append(" and id=").append(getQ_id());
		} else {			
			if (!"".equals(getQ_masterId()))
				sb.append(" and commonCode.id=").append(getQ_masterId());
			if (!"".equals(getQ_subject()))
				sb.append(" and subject like ").append(Common.sqlChar("%"+getQ_subject()+"%"));
			if (!"".equals(getQ_content()))
				sb.append(" and content like ").append(Common.sqlChar("%"+getQ_content()+"%"));
			if (!"".equals(getQ_startDate()))
				sb.append(" and startDate >= ").append(Common.sqlChar(getQ_startDate()));
			if (!"".equals(getQ_startDateE()))
				sb.append(" and startDate <= ").append(Common.sqlChar(getQ_startDateE()));
			if (!"".equals(getQ_endDate()))
				sb.append(" and endDate >= ").append(Common.sqlChar(getQ_endDate()));
			if (!"".equals(getQ_endDateE()))
				sb.append(" and endDate <= ").append(Common.sqlChar(getQ_endDateE()));
			if (!"".equals(getQ_isHTML()))
				sb.append(" and isHtml = ").append(Common.sqlChar(getQ_isHTML()));
			if (!"".equals(getQ_isOpen()))
				sb.append(" and isOpen = ").append(Common.sqlChar(getQ_isOpen()));
		}		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().getCommonNewsDao().loadCount(sb.toString()));
		
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getCommonNewsDao().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().getCommonNewsDao().load(sb.toString()+" order by id desc", this.getRecordStart(), this.getPageSize(), "id", false);
			if (objList != null && objList.size() > 0) {
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) {
					CommonNews o = (CommonNews) it.next();
					String rowArray[] = new String[6];
					rowArray[0] = Common.get(o.getCommonCode().getCodeName());
					rowArray[1] = Common.get(o.getId());
					rowArray[2] = Common.get(o.getSubject());
					rowArray[3] = Common.get(o.getStartDate());
					rowArray[4] = Common.get(o.getEndDate());
					rowArray[5] = Common.get(o.getIsOpen());
					arrList.add(rowArray);	
				}
			}
		}
		return arrList;
		**/
	}

}


