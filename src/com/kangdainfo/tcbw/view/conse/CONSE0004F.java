package com.kangdainfo.tcbw.view.conse;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.XlsUtil;
import com.kangdainfo.tcbw.model.bo.Con1002Db;

public class CONSE0004F extends SuperBean {
	
	private String q_filePath;
	public String getQ_filePath() {		return checkGet(q_filePath);	}
	public void setQ_filePath(String q_filePath) {		this.q_filePath = checkSet(q_filePath);	}
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void importZipCode() throws Exception {	
		if (!"".equals(getQ_filePath())) {
			String[] arrFileName = null;
			arrFileName = getQ_filePath().split(":;:");
			if(arrFileName!=null && arrFileName.length >1){
				String filestoreLocation = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("filestoreLocation");
				File f = new File(filestoreLocation + File.separator + arrFileName[0] + File.separator + arrFileName[1]);
				if (f.isFile() && f.exists() && f.canRead()) {
					XlsUtil xlsUtil = new XlsUtil();
					java.util.List<String[]> list = xlsUtil.getJExcelModel(f.getPath(), 0, false, 6);
					if(list!=null && list.size()>0){
						CommonUser currentUser = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
						String userId = currentUser!=null?currentUser.getUserId():"";
						String yyyMMdd = Datetime.getYYYMMDD();
						String hhMMss = Datetime.getHHMMSS();
						StringBuffer msg = new StringBuffer();
						
					//	java.util.Map<String, String> checkRepeatMap = new java.util.HashMap<String, String>();
						java.util.Map<String, Con1002Db> oCon1002DbMap = new java.util.HashMap<String, Con1002Db>();
						//將所有現有的郵遞區號取出
						java.util.List<Con1002Db> oCon1002DbList = (List<Con1002Db>) ServiceGetter.getInstance().getTcbwService().load(" from Con1002Db ");
						if(oCon1002DbList!=null && oCon1002DbList.size()>0){
							for(Con1002Db dtl : oCon1002DbList){
								if(!"".equals(Common.get(dtl.getZipcode()))){
									//Map (郵遞區號, 物件)
									oCon1002DbMap.put(Common.get(dtl.getZipcode()), dtl);
								}
							}
							oCon1002DbList.clear();
						}
						
						java.util.Map<String, String> cccMap = new java.util.HashMap<String, String>();
						java.util.Map<String, CommonCode> h = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeMap("CTY", null);	
						for (String key : h.keySet()) 
						{
							cccMap.put(Common.get(h.get(key).getCodeName()), key);			
						}
						
						java.util.List<Con1002Db> saveList = new java.util.ArrayList<Con1002Db>(); 
						java.util.List<Con1002Db> updateList = new java.util.ArrayList<Con1002Db>();						
						for(int i=0; i<list.size(); i++)
						{
							String[] rs = list.get(i);
							if("".equals(Common.get(rs[2]))){
								msg.append("第").append(i + 2).append("郵遞區號為空値，無法處理\n");
								continue;
							}
							if(Common.get(rs[1]).length() > 5){
								msg.append("郵遞區號[").append(Common.get(rs[1])).append("]字數大於五，無法處理\n");
								continue;
							}
							//String address = Common.get(rs[2]) + Common.get(rs[3]) + Common.get(rs[4]);
							//if("".equals(address)){
								//msg.append("郵遞區號[").append(Common.get(rs[1])).append("]部份地址為空値，無法處理\n");
								//continue;
							//}
							//if("".equals(Common.get(rs[5]))){
								//msg.append("郵遞區號[").append(Common.get(rs[1])).append("]投遞範圍為空値，無法處理\n");
								//continue;
							//}
							
							String key = Common.get(rs[1]).substring(0, 3);
							/*
							if(checkRepeatMap.containsKey(key)){
								msg.append("郵遞區號[").append(Common.get(rs[0])).append("]與投遞範圍[" + Common.get(rs[4]) + "]已存在相同値，無法處理<br/>");
								continue;
							}else{
								checkRepeatMap.put(key, "Y");
							}*/
							boolean isSave = false;
							Con1002Db con1002Db = oCon1002DbMap.get(key);
							if(con1002Db == null){
								con1002Db = new Con1002Db();
								con1002Db.setCreator(userId);
								con1002Db.setCreateDate(yyyMMdd);
								con1002Db.setCreateTime(hhMMss);
								isSave = true;
							}
							System.out.println(rs[1]);
							con1002Db.setCity(rs[0]);
							
							con1002Db.setZipcode(Common.get(rs[1]).substring(0, 3));
							con1002Db.setZipname(Common.get(rs[2]));
							con1002Db.setModifier(userId);
							con1002Db.setModifyDate(yyyMMdd);
							con1002Db.setModifyTime(hhMMss);
							if(isSave)
							{
								con1002Db.setIsStop("Y");
								saveList.add(con1002Db);
								oCon1002DbMap.put(Common.get(rs[1]).substring(0, 3), con1002Db);
							}
							else
							{
								updateList.add(con1002Db);
							}
						}
					//	checkRepeatMap.clear();
						oCon1002DbMap.clear();
						list.clear();
						
						if(saveList.size() > 0)
						{
							ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);
							saveList.clear();
						}
						if(updateList.size() > 0){
							ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
							updateList.clear();
						}
						if(msg.toString().length() > 0){
							logger.info("[TCBW]-[Conse0004] : \n" + msg.toString());
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

	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
