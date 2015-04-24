package com.kangdainfo.tcbw.view.medin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0007Db;
import com.kangdainfo.tcbw.model.bo.Med0008Db;
import com.kangdainfo.tcbw.model.bo.Med0009Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN2003F extends MEDIN0101F
{
	
	private String id2;//序號	NUMERIC(19,0)
	private String q_notifierRevDateS;//通報日期	
	private String q_notifierRevDateE;//通報日期
	private String[] q_eventKind;
	private String[] q_badReactionResults;
	private String[] q_eventClass;
	private String q_notifyDept;
	private String changeTabValue;
	@Override
	public Object doQueryAll() throws Exception 
	{		

		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med0001Db med0001 left join med0001.med0007Dbs med0007 left join med0001.med0008Dbs med0008 left join med0001.med0009Dbs med0009 where 1=1 ";
		hql += " and (";
		hql += " (med0001.medPermit = ( select medPermit from  Med0001Db where ";
		hql += " id=" + getId() + " )";
		hql += " and med0001.medPermitNumber = ( select medPermitNumber from  Med0001Db where ";
		hql += " id=" + getId() + " )";
		hql += " and med0001.medPermit is not null and med0001.medPermitNumber is not null and med0001.medPermit <> '' and med0001.medPermitNumber <> '' )";
		//如果是字是Z0或Z5，則改比中文品名
		String medPermit = String.valueOf(View.getLookupField("select medPermit from Med0001Db where id = "+ Common.sqlChar(getId())));
		if("Z0".equals(medPermit)||"Z5".equals(medPermit))
			hql += " or (med0001.medCname = (select medCname from Med0001Db where id = " + Common.sqlChar(getId()) + "))";
		hql += ")";
		//不良事件類別
		if(!"".equals(Common.get(getQ_eventKind())))
		{
			hql +=" and (";
			for(int i=0;i<getQ_eventKind().length;i++)
			{
				if(i!=0) 
					hql += " or ";
				hql += " med0001.eventKind = " + Common.sqlChar(getQ_eventKind()[i]);					
			}
			hql += ")";
		} 
		//不良反應結果
		if(!"".equals(Common.get(getQ_badReactionResults())))
		{
			hql +=" and (";
			for(int i=0;i<getQ_badReactionResults().length;i++)
			{
				if(i!=0) 
					hql += " or ";
				hql += " med0001.badReactionResults = " + Common.sqlChar(getQ_badReactionResults()[i]);					
			}
			hql += ")";
		}
		//不良品事件等級
		if(getQ_eventClass() != null && getQ_eventClass().length > 0){
			hql += " and (med0008.eventClass in (";
			for(int i = 0 ; i < getQ_eventClass().length ; i ++)
				if(getQ_eventClass()[i] != null && !"".equals(getQ_eventClass()[i])){
					hql += Common.sqlChar(getQ_eventClass()[i]) ;
					hql += (i < getQ_eventClass().length -1)?",":"";
				}
			hql += ")";
			hql += " or med0009.eventClass in (";
			for(int i = 0 ; i < getQ_eventClass().length ; i ++)
				if(getQ_eventClass()[i] != null && !"".equals(getQ_eventClass()[i])){
					hql += Common.sqlChar(getQ_eventClass()[i]) ;
					hql += (i < getQ_eventClass().length -1)?",":"";
				}
			hql += ")";
			hql += ")";
		}
		//通報單位
		if(getQ_notifyDept() != null && !"".equals(getQ_notifyDept()))
			hql += " and med0001.notifierDept like " + Common.likeSqlChar(getQ_notifyDept());
		
		if(getId() != null && !"".equals(getId()))
			hql += " and med0001.notifierRevDate <= (select notifierRevDate from Med0001Db where id = "+getId()+")";
		
		//因為這幾支是共用page.jsp的模組，會造成衝突，故都將查出結果分頁設為最大
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load("select distinct med0001 " + hql + " order by med0001.id");
		//int count = ServiceGetter.getInstance().getTcbwService().loadCount(hql);
		int count = objList!=null?objList.size():0;
		this.setPageSize(count);
		this.processCurrentPageAttribute(count);
		
		System.out.println("[TCBW]-[MEDIN2003F]-[QUERYALL] : " + hql);
		
		if (getTotalRecord() > 0)
		{
			//???
			//if (getState().indexOf("query")<0) 
				//ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			if (objList != null && objList.size() > 0) 
			{
				//init codeName map
				Map MEDAD2Map = TCBWCommon.getCommonCodeMap("MEDAD2");
				Map NCTRResultMap = TCBWCommon.getCodeNameNCTRResult();
				Map MEDEVCMap = TCBWCommon.getCommonCodeMap("MEDEVC");
				for(Object dtlObj : objList) 
				{				
					Med0001Db dtl = (Med0001Db)dtlObj;
					String[] rowArray = new String[12];
					rowArray[0] = Common.get(dtl.getId());
					setId2(Common.get(dtl.getId()));
					rowArray[1] = Common.get(dtl.getNotifierRevDate());//通報日期
					rowArray[2] = Common.get(dtl.getApplNo());//案件編號
					
					if("1".equals(dtl.getEventKind()))
					  rowArray[3] = Common.get("不良反應");//不良事件類別
					else 
					  rowArray[3] = Common.get("不良品");//不良事件類別
					
					rowArray[4] = Common.get(dtl.getMedCname());//醫材品名
					rowArray[5] = Common.get(dtl.getMedFactory());//製造廠
					rowArray[6] = Common.get(dtl.getMedMainCategory());//醫材主類別
					rowArray[7] = Common.get(MEDAD2Map.get(dtl.getBadReactionResults()));//不良反應結果
					rowArray[8] = Common.get(NCTRResultMap.get(getNcarResult(dtl)));//NCAR通報狀況
					rowArray[9] = Common.get(MEDEVCMap.get(getEventClass(dtl)));//不良品事件等級
					rowArray[10] = Common.get(dtl.getNotifierDept());//通報單位
					rowArray[11] = Common.get("附件");//附件
	
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}

	public static String getQuerylist(boolean primaryArray[], boolean displayArray[], 
			String[] arrAlign,
    		ArrayList objList, String queryAllFlag, boolean withListNo, 
    		boolean linkArray[], String target, String noDataMsg, boolean checkHtml,
    		boolean defaultRow, boolean isNeedLogCookie, String listId) 
	{
    	
    	int i, counter = 0;
    	boolean trFlag = false, targetFlag = false, even = false;
    	String pk = "";
    	
    	StringBuilder sb = new StringBuilder();
    	if (objList!=null && objList.size()>0) {
			String rowArray[] = new String[primaryArray.length];
			java.util.Iterator it = objList.iterator();
			
			String defaultKey = "null";
			for(i=0; i<primaryArray.length; i++){
				if (primaryArray[i]) defaultKey = "";
			}
			
			int j = 0;
			while(it.hasNext()){
				j++;
				rowArray = (String[])it.next();
				counter++;				
				String classTR = "listTROdd", classTD = "listTDOdd";				
				if (even) {
					classTR = "listTREven";
					classTD = "listTDEven";
				}				
			
				pk = "";
				for(i=0; i<primaryArray.length; i++){			
					if(primaryArray[i]){
						pk += Common.escapeReturnChar(rowArray[i]);
					}
				}				
				
				StringBuilder v = new StringBuilder().append(defaultKey);
				for(i=0; i<primaryArray.length; i++){
					if(primaryArray[i]){
						if(trFlag){
							v.append(",'").append(Common.escapeReturnChar(rowArray[i])).append("'");
						}else{
							v.append("'").append(Common.escapeReturnChar(rowArray[i])).append("'");
							trFlag = true;
						}
					}
				}
				
				if(targetFlag==false && target!=null && !"".equals(Common.get(target))){
					v.append(",'").append(target).append("'");
					targetFlag = true;
				}					
				
				// 顯示TR
				if(linkArray != null){
					sb.append(" <tr class='highLight' >");
				}else{
					if(!"".equals(Common.get(listId))){							// FOR 同一頁面，引用多筆LIST使用，且定位不寫入COOKIE
						sb.append(" <tr id='").append("list").append(Common.get(listId)).append("ContainerRow").append(pk).append("'");
						sb.append(" class='").append(classTR).append("' onmouseover=\"this.className='listTRMouseover'\" onmouseout=\"this.className='").append(classTR).append("'\" onClick=\"query").append(listId).append("One('").append(pk).append("','Y','").append(classTR).append("',").append(v).append(");\" >\n");
					}else{
						if(isNeedLogCookie){
							sb.append(" <tr id=\"").append("listContainerRow").append(pk).append("\"");
							sb.append(" class='").append(classTR).append("' onmouseover=\"this.className='listTRMouseover'\" onmouseout=\"this.className='").append(classTR).append("'\" onClick=\"listContainerRowClick('").append(pk).append("');queryOne1(");
							sb.append(v);
							sb.append(");\" >\n");
						}else{
							sb.append(" <tr id='").append("listContainerRow").append(pk).append("'");
							sb.append(" class='").append(classTR).append("' onmouseover=\"this.className='listTRMouseover'\" onmouseout=\"this.className='").append(classTR).append("'\" onClick=\"queryOne1('").append(pk).append("','Y','").append(classTR).append("',").append(v).append(");\" >\n");
						}
					}
				}
				
				// 顯示TD
				if(withListNo){
					sb.append(" <td nowrap class='").append(classTD).append("' >").append(counter).append(".</td>\n");			
				}
				targetFlag = false;
				
				for(i=0; i<displayArray.length; i++){
					if(displayArray[i]){
						if(arrAlign!=null && arrAlign.length>0){
							sb.append(" <td nowrap class='").append(classTD).append("' style=\"text-align:").append(arrAlign[i]).append("\">"); //.append(Common.get(rowArray[i])).append("</td>\n");
						}else{
							sb.append(" <td nowrap class='").append(classTD).append("' >");
						}
						
						if(linkArray!=null && linkArray[i]){
							sb.append("<a href='#' class='sLink2' onClick=\"listContainerRowClick('").append(pk).append("');queryOne1(").append(v).append(",").append(i).append(")\"");
							sb.append(">").append(checkHtml?Common.checkGet(rowArray[i]):Common.get(rowArray[i])).append("</a>");
						}else{
							sb.append(checkHtml?Common.checkGet(rowArray[i]):Common.get(rowArray[i]));
						}
						
						// 預設選取欄位
						if(defaultRow){ 
							sb.append("<script type=\"text/javascript\">");
							sb.append("if(typeof queryOne1 == 'function') {"); 
							sb.append("	if (isObj(document.all.item('state')) && document.all.item('state').value=='queryAllSuccess') {");
							sb.append(" listContainerRowClick('").append(pk).append("');");
							sb.append("	queryOne1(").append(v).append(",-1);");
							sb.append("	}");
							sb.append("}");
							sb.append("</script>");							
							//sb.append("<input type='hidden' name='listContainerRowDefault' id='listContainerRowDefault' value=\"" ).append( v ).append( "\">");
							defaultRow = false;
						}
						sb.append("</td>\n");
					}
				}
				sb.append("</tr>\n");
				trFlag = false;
				even = !even;
			}
    	} else {
    		if ("true".equals(queryAllFlag)) sb.append(" <tr class='highLight' ><td nowrap class='listTD' colspan='100'>").append("".equals(Common.get(noDataMsg))?"查無資料，請您重新輸入查詢條件！":noDataMsg).append("</td></tr>");
    	}
		return sb.toString();
    }
	
	
	
	private String getEventClass(Med0001Db dtl) {
		String eventClass = "";
		if(dtl.getMed0008Dbs() != null && dtl.getMed0008Dbs().size() > 0){
			Object o = TCBWCommon.getMaxObjectById(dtl.getMed0008Dbs());
			Med0008Db med0008 = (Med0008Db)o;
			if(med0008 != null)eventClass = med0008.getEventClass();
		}
		if(dtl.getMed0009Dbs() != null && dtl.getMed0009Dbs().size() > 0){
			Object o = TCBWCommon.getMaxObjectById(dtl.getMed0009Dbs());
			Med0009Db med0009 = (Med0009Db)o;
			if(med0009 != null)eventClass = med0009.getEventClass();
		}
		return eventClass;
	}

	private String getNcarResult(Med0001Db dtl) {
		String ncarResult = "";
		if(dtl.getMed0007Dbs() != null && dtl.getMed0007Dbs().size() > 0){
			Object o = TCBWCommon.getMaxObjectById(dtl.getMed0007Dbs());
			Med0007Db med0007 = (Med0007Db)o;
			if(med0007 != null)ncarResult = med0007.getNcarResult();
		}
		if(dtl.getMed0008Dbs() != null && dtl.getMed0008Dbs().size() > 0){
			Object o = TCBWCommon.getMaxObjectById(dtl.getMed0008Dbs());
			Med0008Db med0008 = (Med0008Db)o;
			if(med0008 != null)ncarResult = med0008.getNcarResult();
		}
		if(dtl.getMed0009Dbs() != null && dtl.getMed0009Dbs().size() > 0){
			Object o = TCBWCommon.getMaxObjectById(dtl.getMed0009Dbs());
			Med0009Db med0009 = (Med0009Db)o;
			if(med0009 != null)ncarResult = med0009.getNcarResult();
		}
		return ncarResult;
	}

	@Override
	public Object doQueryOne() throws Exception 
	{

		
		MEDIN2003F obj = this;
		
		
		
		return obj;
	}

	@Override
	public void doDelete() throws Exception {
		
	}
	
	@Override
	public void doCreate() throws Exception 
	{
		
	}
	public List<Object[]> getTableModel() throws Exception{
		ArrayList arrList = (ArrayList)doQueryAll();
		if(arrList != null){
			for(int i = 0 ; i < arrList.size() ; i ++){				
				String[] strArr = (String[])arrList.get(i);
				strArr[0] = String.valueOf(i + 1);
			}
		}else
			arrList = new ArrayList();
		String[] header = new String[12];
		header[0] = "NO";
		header[1] = "通報日期";
		header[2] = "案件編號";
		header[3] = "不良事件類別";
		header[4] = "醫材品名";
		header[5] = "製造廠";
		header[6] = "醫材主類別";
		header[7] = "不良反應結果";
		header[8] = "NCAR通報狀況";
		header[9] = "不良事件等級";
		header[10] = "通報單位";
		header[11] = "附件";
		arrList.add(0, header);
		return arrList;
	}

	public String getId2() {
		return checkGet(id2);
	}

	public void setId2(String id2) {
		this.id2 = checkSet(id2);
	}

	public String getQ_notifierRevDateS() {
		return checkGet(q_notifierRevDateS);
	}

	public void setQ_notifierRevDateS(String qNotifierRevDateS) {
		q_notifierRevDateS = checkSet(qNotifierRevDateS);
	}

	public String getQ_notifierRevDateE() {
		return checkGet(q_notifierRevDateE);
	}

	public void setQ_notifierRevDateE(String qNotifierRevDateE) {
		q_notifierRevDateE = checkSet(qNotifierRevDateE);
	}

	public String[] getQ_eventKind() {
		return checkGet(q_eventKind);
	}

	public void setQ_eventKind(String[] qEventKind) {
		q_eventKind = checkSet(qEventKind);
	}

	public String[] getQ_badReactionResults() {
		return checkGet(q_badReactionResults);
	}

	public void setQ_badReactionResults(String[] qBadReactionResults) {
		q_badReactionResults = checkSet(qBadReactionResults);
	}

	public String[] getQ_eventClass() {
		return checkGet(q_eventClass);
	}

	public void setQ_eventClass(String[] q_eventClass) {
		this.q_eventClass = checkSet(q_eventClass);
	}

	public String getQ_notifyDept() {
		return checkGet(q_notifyDept);
	}

	public void setQ_notifyDept(String q_notifyDept) {
		this.q_notifyDept = checkSet(q_notifyDept);
	}

	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}

	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}

	

	
	

	

	


	

}
