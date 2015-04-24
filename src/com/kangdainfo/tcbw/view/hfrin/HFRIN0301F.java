package com.kangdainfo.tcbw.view.hfrin;

import java.io.File;
import java.util.Map;
import org.apache.log4j.Logger;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr1002Db;
import com.kangdainfo.tcbw.model.bo.Hfr1003Db;
import com.kangdainfo.tcbw.model.bo.Hfr1004Db;
import com.kangdainfo.tcbw.model.bo.Hfr1005Db;

public class HFRIN0301F extends SuperBean{
	
	private String id;
	private String meetingYear;
	private String meetingSeason;
	private String meetingDate;
	private String meetingStime;
	private String meetingStimeH;
	private String meetingStimeM;
	private String meetingEtime;
	private String meetingEtimeH;
	private String meetingEtimeM;
	private String meetingFile;
	private String fileMsg;
	
	private String q_isQuery;
	private String q_id;
	private String q_meetingYear;
	private String q_meetingSeason;
	private String q_meetingDateS;
	private String q_meetingDateE;
	private String q_meetingStime;
	private String q_meetingStimeH;
	private String q_meetingStimeM;
	private String q_meetingEtime;
	private String q_meetingEtimeH;
	private String q_meetingEtimeM;
	
	private String q_filePath;
	public String getQ_filePath() {		return checkGet(q_filePath);	}
	public void setQ_filePath(String q_filePath) {		this.q_filePath = checkSet(q_filePath);	}
	protected Logger logger = Logger.getLogger(this.getClass());

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getMeetingYear() {
		return checkGet(meetingYear);
	}

	public void setMeetingYear(String meetingYear) {
		this.meetingYear = checkSet(meetingYear);
	}

	public String getMeetingSeason() {
		return checkGet(meetingSeason);
	}

	public void setMeetingSeason(String meetingSeason) {
		this.meetingSeason = checkSet(meetingSeason);
	}

	public String getMeetingDate() {
		return checkGet(meetingDate);
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = checkSet(meetingDate);
	}

	public String getMeetingStime() {
		return checkGet(meetingStime);
	}

	public void setMeetingStime(String meetingStime) {
		this.meetingStime = checkSet(meetingStime);
	}

	public String getMeetingEtime() {
		return checkGet(meetingEtime);
	}

	public void setMeetingEtime(String meetingEtime) {
		this.meetingEtime = checkSet(meetingEtime);
	}
	
	public String getMeetingStimeH() {
		return checkGet(meetingStimeH);
	}

	public void setMeetingStimeH(String meetingStimeH) {
		this.meetingStimeH = checkSet(meetingStimeH);
	}

	public String getMeetingStimeM() {
		return checkGet(meetingStimeM);
	}

	public void setMeetingStimeM(String meetingStimeM) {
		this.meetingStimeM = checkSet(meetingStimeM);
	}

	public String getMeetingEtimeH() {
		return checkGet(meetingEtimeH);
	}

	public void setMeetingEtimeH(String meetingEtimeH) {
		this.meetingEtimeH = checkSet(meetingEtimeH);
	}

	public String getMeetingEtimeM() {
		return checkGet(meetingEtimeM);
	}

	public void setMeetingEtimeM(String meetingEtimeM) {
		this.meetingEtimeM = checkSet(meetingEtimeM);
	}
	
	public String getMeetingFile() {
		return checkGet(meetingFile);
	}

	public void setMeetingFile(String meetingFile) {
		this.meetingFile = checkSet(meetingFile);
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

	public String getQ_meetingYear() {
		return checkGet(q_meetingYear);
	}

	public void setQ_meetingYear(String qMeetingYear) {
		q_meetingYear = checkSet(qMeetingYear);
	}

	public String getQ_meetingSeason() {
		return checkGet(q_meetingSeason);
	}

	public void setQ_meetingSeason(String qMeetingSeason) {
		q_meetingSeason = checkSet(qMeetingSeason);
	}

	public String getQ_meetingDateS() {
		return checkGet(q_meetingDateS);
	}

	public void setQ_meetingDateS(String qMeetingDateS) {
		q_meetingDateS = checkSet(qMeetingDateS);
	}

	public String getQ_meetingDateE() {
		return checkGet(q_meetingDateE);
	}

	public void setQ_meetingDateE(String qMeetingDateE) {
		q_meetingDateE = checkSet(qMeetingDateE);
	}

	public String getQ_meetingStime() {
		return checkGet(q_meetingStime);
	}

	public void setQ_meetingStime(String qMeetingStime) {
		q_meetingStime = checkSet(qMeetingStime);
	}

	public String getQ_meetingEtime() {
		return checkGet(q_meetingEtime);
	}

	public void setQ_meetingEtime(String qMeetingEtime) {
		q_meetingEtime = checkSet(qMeetingEtime);
	}

	public String getQ_meetingStimeH() {
		return checkGet(q_meetingStimeH);
	}

	public void setQ_meetingStimeH(String qMeetingStimeH) {
		q_meetingStimeH = checkSet(qMeetingStimeH);
	}

	public String getQ_meetingStimeM() {
		return checkGet(q_meetingStimeM);
	}

	public void setQ_meetingStimeM(String qMeetingStimeM) {
		q_meetingStimeM = checkSet(qMeetingStimeM);
	}

	public String getQ_meetingEtimeH() {
		return checkGet(q_meetingEtimeH);
	}

	public void setQ_meetingEtimeH(String qMeetingEtimeH) {
		q_meetingEtimeH = checkSet(qMeetingEtimeH);
	}

	public String getQ_meetingEtimeM() {
		return checkGet(q_meetingEtimeM);
	}

	public void setQ_meetingEtimeM(String qMeetingEtimeM) {
		q_meetingEtimeM = checkSet(qMeetingEtimeM);
	}
	
	public String getFileMsg() {
		return checkGet(fileMsg);
	}
	public void setFileMsg(String fileMsg) {
		this.fileMsg = checkSet(fileMsg);
	}

	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0301F obj = this;
		Hfr1003Db c = (Hfr1003Db)View.getObject("from Hfr1003Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setMeetingYear(c.getMeetingYear());
			obj.setMeetingSeason(c.getMeetingSeason());
			obj.setMeetingDate(c.getMeetingDate());
			obj.setMeetingStimeH(c.getMeetingStime().substring(0, 2));
			obj.setMeetingStimeM(c.getMeetingStime().substring(2));
			obj.setMeetingEtimeH(c.getMeetingEtime().substring(0, 2));
			obj.setMeetingEtimeM(c.getMeetingEtime().substring(2));
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());		
			Con0001Db con0001 = (Con0001Db)View.getObject(" from Con0001Db where fileKind = 'HFRIN0301F' and upLoadId = " + Common.getLong(getId()));
			if(con0001 != null){
				obj.setMeetingFile(con0001.getFileRoute());
			}else{
				obj.setMeetingFile("");
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
		String hql = " from Hfr1003Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_meetingYear()))
				hql += " and meetingYear = " + Common.sqlChar(getQ_meetingYear());
			if(!"".equals(getQ_meetingSeason()))
				hql += " and meetingSeason = " + Common.sqlChar(getQ_meetingSeason());
			if(!"".equals(getQ_meetingDateS()))
				hql += " and meetingDate >= " + Common.sqlChar(getQ_meetingDateS());
			if(!"".equals(getQ_meetingDateE()))
				hql += " and meetingDate <= " + Common.sqlChar(getQ_meetingDateE());
			if(!"".equals(getQ_meetingStime()))
				hql += " and meetingStime >= " + Common.sqlChar(addZero(getQ_meetingStime()) + "00");
			if(!"".equals(getQ_meetingEtime()))
				hql += " and meetingEtime <= " + Common.sqlChar(addZero(getQ_meetingEtime()) + "00");
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {				
					Hfr1003Db dtl = (Hfr1003Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getMeetingYear()) + "-" + Common.get(dtl.getMeetingSeason());						
					rowArray[2] = Common.formatYYYMMDD(dtl.getMeetingDate(), 4);
					rowArray[3] = Common.get(formatTime(dtl.getMeetingStime(),dtl.getMeetingEtime()));
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Hfr1003Db obj = new Hfr1003Db();
		obj.setMeetingYear(getMeetingYear());
		obj.setMeetingSeason(getMeetingSeason());
		obj.setMeetingDate(getMeetingDate());
		obj.setMeetingStime(addZero(getMeetingStimeH()) + addZero(getMeetingStimeM()));
		obj.setMeetingEtime(addZero(getMeetingEtimeH()) + addZero(getMeetingEtimeM()));
		obj.setCreator(getEditID());
		obj.setCreateDate(getEditDate());
		obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		if(!"".equals(getMeetingFile())){
			String f = "."+File.separator+"upload"+File.separator+"HFRIN0301F"+File.separator + getMeetingFile().replaceAll(":","_").replaceAll("_;_","\\\\");
			File uploadFile = new File(f);
			Con0001Db con0001 = new Con0001Db();
			con0001.setFileKind("HFRIN0301F");
			con0001.setUpLoadId(obj.getId());
			con0001.setFileRoute(getMeetingFile());
			con0001.setFileName(uploadFile.getName());
			//con0001.setFileType(uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".") + 1));
			//con0001.setFileSize(String.valueOf(uploadFile.length()));
			con0001.setCreator(getEditID());
			con0001.setCreateDate(getEditDate());
			con0001.setCreateTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().save(con0001);
		}
		setId(Common.get(obj.getId()));
	}

	@Override
	public void doUpdate() throws Exception {
		Hfr1003Db obj = (Hfr1003Db)View.getObject(" from Hfr1003Db where id = " + Common.getLong(getId()));
		if(obj != null){		
			obj.setMeetingYear(getMeetingYear());
			obj.setMeetingSeason(getMeetingSeason());
			obj.setMeetingDate(getMeetingDate());
			obj.setMeetingStime(addZero(getMeetingStimeH()) + addZero(getMeetingStimeM()));
			obj.setMeetingEtime(addZero(getMeetingEtimeH()) + addZero(getMeetingEtimeM()));
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			if(!"".equals(getMeetingFile())){
				String f = "."+File.separator+"upload"+File.separator+"HFRIN0301F"+File.separator+ getMeetingFile().replaceAll(":","_").replaceAll("_;_","\\\\");
				File uploadFile = new File(f);
				Con0001Db con0001 = (Con0001Db)View.getObject(" from Con0001Db where fileKind = 'HFRIN0301F' and upLoadId = " + Common.getLong(getId()));
				if(con0001 != null){
					if(!getMeetingFile().equals(con0001.getFileRoute())){
						con0001.setFileRoute(getMeetingFile());
						con0001.setFileName(uploadFile.getName());
						//con0001.setFileType(uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".") + 1));
						//con0001.setFileSize(String.valueOf(uploadFile.length()));
					    obj.setModifier(getEditID());
					    obj.setModifyDate(getEditDate());
					    obj.setModifyTime(getEditTime());
					    ServiceGetter.getInstance().getTcbwService().update(con0001);
					}
				}else{
					con0001 = new Con0001Db();
					con0001.setFileKind("HFRIN0301F");
					con0001.setUpLoadId(Long.valueOf(getId()));
					con0001.setFileRoute(getMeetingFile());
					con0001.setFileName(uploadFile.getName());
					//con0001.setFileType(uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".") + 1));
					//con0001.setFileSize(String.valueOf(uploadFile.length()));
					con0001.setCreator(getEditID());
					con0001.setCreateDate(getEditDate());
					con0001.setCreateTime(getEditTime());
					ServiceGetter.getInstance().getTcbwService().save(con0001);
				}
			}
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Hfr1003Db obj = (Hfr1003Db)View.getObject(" from Hfr1003Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			Con0001Db con0001 = (Con0001Db)View.getObject(" from Con0001Db where fileKind = 'HFRIN0301F' and upLoadId = " + Common.getLong(getId()));
			if(con0001 != null){
				ServiceGetter.getInstance().getTcbwService().delete(con0001);
			}
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
	
	public String addZero(String number)throws Exception {
		if(number.length() == 1)
			number = "0" + number;
		return number;
	}
	
	public String formatTime(String sdate, String edate)throws Exception {
		StringBuffer sb1 = new StringBuffer(); 
		sb1.append(sdate.substring(0, 2)+":"+sdate.substring(2)+"~");
		sb1.append(edate.substring(0, 2)+":"+edate.substring(2));
		return sb1.toString();
	}
	
	public void importHfrin() throws Exception {
		this.setFileMsg("");
		if (!"".equals(getQ_filePath())) {
			String[] arrFileName = null;
			arrFileName = getQ_filePath().split(":;:");
			if(arrFileName!=null && arrFileName.length >1){
				String filestoreLocation = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("filestoreLocation");
				File f = new File(filestoreLocation + File.separator + arrFileName[0] + File.separator + arrFileName[1]);
				if (f.isFile() && f.exists() && f.canRead()) {
					com.kangdainfo.common.util.XlsUtil xUtil = new com.kangdainfo.common.util.XlsUtil();
					Map<String,java.util.List<Map<String,String>>> fileMap = xUtil.getMasterDetailExcel(f.getPath());
					if(fileMap!=null && fileMap.size()>0){
						CommonUser currentUser = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
						String userId = currentUser!=null?currentUser.getUserId():"";
						String yyyMMdd = Datetime.getYYYMMDD();
						String hhMMss = Datetime.getHHMMSS();
						StringBuffer msg = new StringBuffer();
						java.util.List master = fileMap.get("master");
						java.util.List detail1 = fileMap.get("detail1");
						java.util.List detail2 = fileMap.get("detail2");
						if(master!=null && master.size()>0){
							//通報案件
							for(Object m : master){
								boolean detailSave=true;
								java.util.List<Hfr1004Db> hfr1004saveList = new java.util.ArrayList<Hfr1004Db>();
								java.util.List<Hfr1005Db> hfr1005saveList = new java.util.ArrayList<Hfr1005Db>();
								Map<String,String> masterMap = (Map<String, String>) m;
								if(checkEmptyMap(masterMap, "master")){
									msg.append("通報案件資料不完整，NO ").append(masterMap.get("NO")).append("\n");
									continue;
								}
								String no = masterMap.get("NO");
								Hfr1003Db hfr1003 = new Hfr1003Db();
								hfr1003.setMeetingYear(masterMap.get("年度"));
								hfr1003.setMeetingSeason(masterMap.get("期別"));
								hfr1003.setMeetingDate(masterMap.get("召開日期").replaceAll("/", ""));
								hfr1003.setMeetingStime(masterMap.get("召開時間(起)").replaceAll(":", ""));
								hfr1003.setMeetingEtime(masterMap.get("召開時間(迄)").replaceAll(":", ""));
								hfr1003.setCreator(userId);
								hfr1003.setCreateDate(yyyMMdd);
								hfr1003.setCreateTime(hhMMss);
								hfr1003.setModifier(userId);
								hfr1003.setModifyDate(yyyMMdd);
								hfr1003.setModifyTime(hhMMss);
							    
								//評估委員
								for(int i=0; i<detail1.size(); i++){
									Map<String,String> detailMap = (Map<String, String>)detail1.get(i);
									if(no.equals(detailMap.get("NO"))){
										if(checkEmptyMap(detailMap, "detail")){
											msg.append("委員編號資料不完整，NO ").append(masterMap.get("NO")).append("\n");
											detailSave = false;
											break;
										}
										Hfr1002Db hfr1002 = (Hfr1002Db)View.getObject(
												" from Hfr1002Db where hfr1001Db.committeeNum = " + Common.sqlChar(detailMap.get("委員編號")) +
												" and termSdate <= " + Common.sqlChar(hfr1003.getMeetingDate()) + " and termEdate >= " + Common.sqlChar(hfr1003.getMeetingDate()));
										if(hfr1002 == null){
											msg.append("無任期內的委員，NO ").append(masterMap.get("NO"));
											msg.append("，委員編號 ").append(detailMap.get("委員編號")).append("\n");
											detailSave = false;
											break;
										}	
										Hfr1004Db newHfr1004 = new Hfr1004Db();
										newHfr1004.setHfr1002Db(hfr1002);
										newHfr1004.setCreator(userId);
										newHfr1004.setCreateDate(yyyMMdd);
										newHfr1004.setCreateTime(hhMMss);
										newHfr1004.setModifier(userId);
										newHfr1004.setModifyDate(yyyMMdd);
										newHfr1004.setModifyTime(hhMMss);
										hfr1004saveList.add(newHfr1004);
										//detail1.remove(i);
									}
								}
								if(!detailSave)
									continue;

								//評估案件
								for(int i=0; i<detail2.size(); i++){
									Map<String,String> detailMap = (Map<String, String>)detail2.get(i);
									if(no.equals(detailMap.get("NO"))){
										if(checkEmptyMap(detailMap, "detail")){
											msg.append("評估案件資料不完整，NO ").append(masterMap.get("NO")).append("\n");
											detailSave = false;
											break;
										}
										Hfr0001Db hfr0001 = (Hfr0001Db)View.getObject(" from Hfr0001Db where applno = " + Common.sqlChar(detailMap.get("案件編號")));
										if(hfr0001 == null){
											msg.append("案件編號不存在，NO ").append(masterMap.get("NO"));
											msg.append("，案件編號 ").append(detailMap.get("案件編號")).append("\n");
											//detailSave = false;
											//break;
										}
										Hfr1005Db newHfr1005 = new Hfr1005Db();
										newHfr1005.setCaseno(detailMap.get("案件編號"));
										newHfr1005.setCreator(userId);
										newHfr1005.setCreateDate(yyyMMdd);
										newHfr1005.setCreateTime(hhMMss);
										newHfr1005.setModifier(userId);
										newHfr1005.setModifyDate(yyyMMdd);
										newHfr1005.setModifyTime(hhMMss);
										hfr1005saveList.add(newHfr1005);
										//detail2.remove(i);
									}
								}
								if(!detailSave)
									continue;

								if(detailSave){
									ServiceGetter.getInstance().getTcbwService().save(hfr1003);
									if(hfr1004saveList.size() > 0){
										java.util.List<Hfr1004Db> saveList = new java.util.ArrayList<Hfr1004Db>();
										for(Hfr1004Db o: hfr1004saveList){
											o.setHfr1003Db(hfr1003);
											saveList.add(o);
										}
										hfr1004saveList.clear();
										ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);	
									}
									if(hfr1005saveList.size() > 0){
										java.util.List<Hfr1005Db> saveList = new java.util.ArrayList<Hfr1005Db>();
										for(Hfr1005Db o: hfr1005saveList){
											o.setHfr1003Db(hfr1003);
											saveList.add(o);
										}
										hfr1005saveList.clear();
										ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);
									}
								}
							}
							if(msg.toString().length() > 0){
								logger.info("[TCBW]-[HFRIN0301F]-[委員會會期匯入] : \n" + msg.toString());
								this.setFileMsg(msg.toString());
							}
						}
					}else{
						setErrorMsg("匯入資料無法解析，請重新操作 !");
					}
				}else{
					setErrorMsg("檔案路徑名無法取得的匯入資料，請重新操作 !");
				}
			}else{
				setErrorMsg("檔案路徑名稱錯誤，無法匯入，請重新操作 !");
			}
		}else{
			setErrorMsg("未有檔案路徑名稱，無法匯入，請重新操作 !");
		}
	}
	
	public boolean checkEmptyMap(Map<String,String> map, String type){		
		java.util.Iterator it = map.entrySet().iterator();
		//detail只須檢核NO, 委員編號, 案件編號
		while (it.hasNext()) { 
		    Map.Entry entry = (Map.Entry) it.next(); 
		    if("detail".equals(type)){
			    if("NO".equals(Common.get(entry.getKey()))){
			    	if("".equals(Common.get(entry.getValue())))
			    		return true;
			    }else if("委員編號".equals(Common.get(entry.getKey()))){
			    	if("".equals(Common.get(entry.getValue())))
			    		return true;
			    }else if("案件編號".equals(Common.get(entry.getKey()))){
			    	if("".equals(Common.get(entry.getValue())))
			    		return true;
			    }
		    }else{
			    if("".equals(Common.get(entry.getValue())))
			    	return true;
		    }
		} 
		return false;
	}
}
