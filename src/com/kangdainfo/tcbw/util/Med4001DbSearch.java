package com.kangdainfo.tcbw.util;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Drg5001Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med5001Db;

public class Med4001DbSearch extends SuperBean{

	private String applNo;
	private String med0001Id;
	


	public String getMed0001Id() {
		return checkGet(med0001Id);
	}

	public void setMed0001Id(String med0001Id) {
		this.med0001Id = checkSet(med0001Id);
	}

	public String getApplNo() {
		return checkGet(applNo);
	}

	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}


	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Med4001Db where med0001ID = " + Common.getLong(getMed0001Id());
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {
					Med4001Db dtl = (Med4001Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());	
					rowArray[2] = Common.get(dtl.getRevision());			
					rowArray[3] = Common.formatYYYMMDD(Common.get(dtl.getCreateDate()),4);
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
	
	public String getQuerylist( boolean primaryArray[], boolean displayArray[], String[] arrAlign, java.util.ArrayList objList, String queryAllFlag, boolean withListNo, String noDataMsg, boolean checkHtml) {
    	int counter = 0;
    	boolean even = false;	
    	StringBuilder sb = new StringBuilder();
    	if(objList!=null && objList.size()>0){
			String rowArray[] = new String[primaryArray.length];
			
			java.util.Iterator it = objList.iterator();
			while(it.hasNext()){
				rowArray = (String[])it.next();
				counter++;				
				String classTR = "listTROdd";
				String classTD = "listTDOdd";				
				if(even){
					classTR = "listTREven";
					classTD = "listTDEven";
				}				
			
				String pk = "";
				for(int i=0; i<primaryArray.length; i++){			
					if(primaryArray[i]){
						pk += Common.escapeReturnChar(rowArray[i]);
					}
				}
				boolean trFlag = false;
				StringBuilder v = new StringBuilder();
				for(int i=0; i<primaryArray.length; i++){
					if (primaryArray[i]) {
						if(trFlag){
							v.append(",'").append(Common.escapeReturnChar(rowArray[i])).append("'");
						}else{
							v.append("'").append(Common.escapeReturnChar(rowArray[i])).append("'");
							trFlag = true;
						}
					}
				}
				sb.append(" <tr id='").append("listContainerRow").append(pk).append("'");
				sb.append(" class='").append(classTR).append("' onmouseover=\"this.className='listTRMouseover'\" onmouseout=\"this.className='").append(classTR).append("'\" onClick=\"listContainerRowClick('").append(pk).append("');\" >\n");
				
				// 顯示TD
				if(withListNo){
					sb.append(" <td nowrap class='").append(classTD).append("' >").append(counter).append(".</td>\n");			
				}
				for(int i=0; i<displayArray.length; i++){
					if(displayArray[i]){
						if(arrAlign!=null && arrAlign.length>0){
							sb.append(" <td nowrap class='").append(classTD).append("' style=\"text-align:").append(arrAlign[i]).append("\">"); 
						}else{
							sb.append(" <td nowrap class='").append(classTD).append("' >");
						}
						
						sb.append(checkHtml?Common.checkGet(rowArray[i]):Common.get(rowArray[i]));
						sb.append("</td>\n");
					}
				}
				
				// BUTTON
				sb.append(" <td nowrap class='").append(classTD).append("'>");
				sb.append(" <input type='button' class='toolbar_default' name='btn" + counter + "' value='" + "明　細"  + "' ");
			//	if(Common.getInt(rowArray[isDisabled]) > 90){
			//		sb.append("disabled");
			//	}
				sb.append(" onClick=\"").append("listContainerRowClick('").append(pk).append("');")
				  .append("updateData(").append(v).append(");\"> ");
				sb.append("</td>\n");
				sb.append("</tr>\n");
				even = !even;
			}
    	} else {
    		if ("true".equals(queryAllFlag)) sb.append(" <tr class='highLight' ><td nowrap class='listTD' colspan='100'>").append("".equals(Common.get(noDataMsg))?"查無資料，請您重新輸入查詢條件！":noDataMsg).append("</td></tr>");
    	}
		return sb.toString();
    }
	
}


