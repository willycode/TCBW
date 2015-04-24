package com.kangdainfo.tcbw.view.vdrg;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.*;
import com.kangdainfo.common.util.*;
import com.kangdainfo.tcbw.model.bo.*;
import com.kangdainfo.tcbw.util.TCBWCommon;

/**
*<br>程式目的：國內外藥品品質警訊警訊登錄作業
*<br>程式代號：vdrg0101q
*<br>程式日期：1030424
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class VDRG0501F extends VDRG0100F{

public String getHQL(){
	String hql = " from Drg7001Db where 1=1 ";
	if (!"".equals(getQ_applNoS()))
		hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
	if (!"".equals(getQ_applNoE()))
		hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
	if (!"".equals(getQ_dataRevDateS()))
		hql += " and dataRevDate >= " + Common.sqlChar(getQ_dataRevDateS());
	if (!"".equals(getQ_dataRevDateE()))
		hql += " and dataRevDate <= " + Common.sqlChar(getQ_dataRevDateE());
	if (null != getQ_status() && getQ_status().length > 0){
		String q_statusStr="";
		for(String status:getQ_status()){
			if(q_statusStr.length()>0)q_statusStr+=",";
			q_statusStr += Common.sqlChar(status);
		}
		hql += " and status in (" + q_statusStr + ")";
	}
		
	if (!"".equals(getQ_publDateS()))
		hql += " and publDate >= " + Common.sqlChar(getQ_publDateS());
	if (!"".equals(getQ_publDateE()))
		hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());
	if (!"".equals(getQ_publDept()))
		hql += " and publDept=" + Common.sqlChar(getQ_publDeptCodeId());
	if (!"".equals(getQ_istransfer()))
		hql += " and istransfer=" + Common.sqlChar(getQ_istransfer());
	if (null != getQ_situation() && !"".equals(getQ_situation())){
		String q_situationStr = "";
		for(String q_situation : getQ_situation()){
			if(q_situationStr.length() > 0) q_situationStr += ",";
			q_situationStr += Common.sqlChar(q_situation);
		}
		hql += " and situation in (" + q_situationStr + ")";
	}
	if (!"".equals(getQ_chProduct()))
		hql += " and chProduct like " + Common.likeSqlChar(getQ_chProduct());
	if (!"".equals(getQ_scientificName()))
		hql += " and scientificName like " + Common.likeSqlChar(getQ_scientificName());
	if (!"".equals(getQ_druggist()))
		hql += " and druggist like " + Common.likeSqlChar(getQ_druggist());
	if (!"".equals(getQ_permitKey()))
		hql += " and id in (select drg7001Db.id from Drg7003Db where permitKey=" + Common.sqlChar(getQ_permitKey())+")";
	if (!"".equals(getQ_permitNo()))
		hql += " and id in (select drg7001Db.id from Drg7003Db where permitNo=" + Common.sqlChar(getQ_permitNo())+")";
	if (!"".equals(getQ_manufactorName()))
		hql += " and manufactorName like " + Common.likeSqlChar(getQ_manufactorName());
	if (!"".equals(getQ_applicationName()))
		hql += " and id in (select drg7001Db.id from Drg7003Db where applicationName like " + Common.likeSqlChar(getQ_applicationName())+")";
	if (!"".equals(getQ_lotNo()))
		hql += " and lotNo=" + Common.sqlChar(getQ_lotNo());
	
	
	//if (!"".equals(getQ_qualitywarningtype()))
		//hql += " and qualitywarningtype=" + Common.sqlChar(getQ_qualitywarningtype());
	
	if(!"".equals(Common.get(getQ_qualitywarningtype())))
	{
		hql +=" and (";
		for(int i=0;i<getQ_qualitywarningtype().length;i++)
		{
			if(i!=0) 
				hql += " or ";
			hql += " qualitywarningtype like " + Common.likeSqlChar(getQ_qualitywarningtype()[i]);					
		}
		hql += ")";
	}
	
	if (!"".equals(getQ_isImport()))
		hql += " and id in (select drg7001Db.id from Drg7002Db where isImport=" + Common.sqlChar(getQ_isImport())+")";
	if (null != getQ_recycleType() && !"".equals(getQ_recycleType())){
		String q_recycleTypeStr = "";
		for(String q_recycleType : getQ_recycleType()){
			if(q_recycleTypeStr.length() > 0) q_recycleTypeStr += ",";
			q_recycleTypeStr += Common.sqlChar(q_recycleType);
		}
		hql += " and recycleType in (" + q_recycleTypeStr + ")";
	}
	if (null != getQ_lamp() && !"".equals(getQ_lamp())){
		String q_lampStr = "";
		for(String q_lamp : getQ_lamp()){
			if(q_lampStr.length() > 0) q_lampStr += ",";
			q_lampStr += Common.sqlChar(q_lamp);
		}
		hql += " and lamp in (" + q_lampStr + ")";
	}
	if (!"".equals(getQ_aftereffect()))
		hql += " and aftereffect=" + Common.sqlChar(getQ_aftereffect());
	if (!"".equals(getQ_iswarning()))
		hql += " and iswarning=" + Common.sqlChar(getQ_iswarning());
	if(null != getQ_isReply() && !"".equals(getQ_isReply())) {
		String q_isReplyStr = "";
		boolean isreplyY = false;
		boolean isreplyN = false;
		for(String q_isReplyType : getQ_isReply()) {
			if("Y".equals(q_isReplyType)) {
				isreplyY = true;
			}
			if("N".equals(q_isReplyType)) {
				isreplyN = true;
			}
		}
		if(isreplyN && isreplyY == false) {
			hql += " and id in (select drg7001Db.id from Drg7002Db where isnull(replyDate,'') = '') and applNo is not null";
		} else if(isreplyY && isreplyN == false) {
			hql += " and id not in (select drg7001Db.id from Drg7002Db where isnull(replyDate,'') = '') and applNo is not null";
		}
	}
	if(!"".equals(getQ_isTrans())){
		if("Y".equals(getQ_isTrans())) hql += " and isTransferData='Y' ";
		else if ("N".equals(getQ_isTrans())) hql += " and ( isTransferData is null or isTransferData='' ) ";
	}
	System.out.println("[TCBW]-[VDRG0501F]-[queryAll]： " + hql);
	return hql;
}
@Override
public Object doQueryOne() throws Exception {
	VDRG0501F obj = this;
	return obj;
}
@Override
public Object doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

	
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(getHQL()));
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(getHQL()+" order by id desc", this.getRecordStart(), this.getPageSize());
		if (objList != null && objList.size() > 0) {
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) {
				Drg7001Db o = (Drg7001Db) it.next();
				String rowArray[] = new String[8];
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = Common.get(o.getApplNo());
				rowArray[2] = View.getCommonCodeName("CONPUBLDEPT", Common.get(o.getPublDept()));
				rowArray[3] = View.getCommonCodeName("CONWARNING", Common.get(o.getSituation()));
				rowArray[4] = Common.get(o.getDataRevDate());
				rowArray[5] = Common.get(o.getChProduct());
				rowArray[6] = Common.get(o.getEventDesc());
				rowArray[7] = View.getCommonCodeName("DRGRECTP", Common.get(o.getRecycleType()));
				arrList.add(rowArray);					
			}
		}
	}
	return arrList;
}

@Override
public void doCreate() throws Exception {}

@Override
public void doUpdate() throws Exception {}

@Override
public void doDelete() throws Exception {}


public DefaultTableModel getDefaultTableModel() throws Exception
{		
	DefaultTableModel model = new javax.swing.table.DefaultTableModel();
	StringBuffer sb = new StringBuffer();

	String[] columns = new String[]{
		"dataRevDate"		,		"publDept"			,
		"situation"			,		"publDate"			,
		"manufactorData"	,		"productData"		,
		"lotNo"				,		"datasourWebSite"	,
		"isImport"			,		"ispublnews"		,
		"ispublconsumer"	,		"publconsumerDate"	,
		"lamp"				,		"estimateremark"	,
		"aftereffect"
	};
	
	java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
	List DataList = ServiceGetter.getInstance().getCommonService().load(getHQL()+" order by id desc");
	if(DataList!=null && DataList.size() > 0){	
		Iterator it = DataList.iterator();
		while(it.hasNext()){
			Drg7001Db o = (Drg7001Db) it.next();
			String rowArray[] = new String[15];
			rowArray[0] = Common.formatYYYMMDD(Common.get(o.getDataRevDate()),1);
			rowArray[1] = View.getCommonCodeName("CONPUBLDEPT", o.getPublDept());
			rowArray[2] = View.getCommonCodeName("CONWARNING", o.getSituation());
			rowArray[3] = Common.formatYYYMMDD(Common.get(o.getPublDate()),1);
			rowArray[4] = "廠商"+Common.get(o.getDruggist())+View.getCommonCodeName("DRGRECTP", o.getRecycleType())+Common.get(o.getChProduct())
							+ "/" + Common.get(o.getScientificName())+"。"+Common.get(o.getEventDesc());
			rowArray[5] = "廠商"+Common.get(o.getDruggist())+View.getCommonCodeName("DRGRECTP", o.getRecycleType())+Common.get(o.getChProduct())
							+ "/" + Common.get(o.getScientificName())+"。"+Common.get(o.getEventDesc())+"。產品批號"+Common.get(o.getLotNo());
			rowArray[6] = Common.get(o.getLotNo());
			rowArray[7] = Common.get(o.getDatasourWebSite());
			rowArray[8] = "--";
			if(null != o.getDrg7002Dbs() && !o.getDrg7002Dbs().isEmpty()){
				for(Object dtlObj:o.getDrg7002Dbs()){
					Drg7002Db drg72 = (Drg7002Db)dtlObj;
					if("Y".equals(drg72.getIsImport())){
						rowArray[8] = "YES";
					}else if("N".equals(drg72.getIsImport())){
						rowArray[8] = "NO";
					}
				}
			}
			rowArray[9] = "--";
			if("Y".equals(o.getIspublnews())){
				rowArray[9] = "YES";
			}else if("N".equals(o.getIspublnews())){
				rowArray[9] = "NO";
			}
			rowArray[10] = "--";
			if("Y".equals(o.getIspublconsumer())){
				rowArray[10] = "YES";
			}else if("N".equals(o.getIspublconsumer())){
				rowArray[10] = "NO";
			}
			rowArray[11] = Common.formatYYYMMDD(Common.get(o.getPublconsumerDate()),1);
			rowArray[12] = View.getCommonCodeName("CONLAMP", o.getLamp());
			rowArray[13] = Common.get(o.getEstimateremark());
			if(null != o.getDrg7003Dbs() && !o.getDrg7003Dbs().isEmpty()){
				for(Object dtlObj:o.getDrg7003Dbs()){
					Drg7003Db drg73 = (Drg7003Db)dtlObj;
					if(rowArray[13].length() > 0) rowArray[13] += "、";
					rowArray[13] += Common.get(View.getCommonCodeName("DRGPKID", drg73.getPermitKey()))+"第"+Common.get(drg73.getPermitNo())+"號";
				}
			}
			rowArray[14] = View.getCommonCodeName("DRGEFFECT", o.getAftereffect());
			arrList.add(rowArray);					
		}
	}
	if(null != arrList && arrList.size() >0){
		Object[][] rs = new Object[0][0];
        rs = (Object[][])arrList.toArray(rs);
        model.setDataVector(rs, columns);
		
	}else{
		model=null;//查詢無資料
	}
	return model;	
}

public Object[] getDefaultTableModelForWord() throws Exception
{
	Object[] rowArray = null;		//word 替換欄位數
	List list = ServiceGetter.getInstance().getCommonService().load(getHQL()+" order by id desc");
	if(list!=null && list.size() > 0){	
		rowArray = new Object[5];		//word 替換欄位數
		rowArray[0] = Common.formatYYYMMDD(getQ_dataRevDateS()) + "~" + Common.formatYYYMMDD(getQ_dataRevDateE());		//##1##
		rowArray[1] = Common.get(list.size());						//##2##
		rowArray[2] = "0";
		rowArray[3] = "0";
		java.util.List<Object[]> situationList = ServiceGetter.getInstance().getCommonService().load("select situation,count(id) as count "+getHQL()+" group by situation");
		if(null != situationList && !situationList.isEmpty()){
			for(Object[] o : situationList){
				if("01".equals(Common.get(o[0]))){			//警訊
					rowArray[3] = Common.get(o[1]);
				}else if("02".equals(Common.get(o[0]))){	//回收
					rowArray[2] = Common.get(o[1]);
				}
			}
		}
		rowArray[4] = getTableModelForWordSub(list);
	}
	return rowArray;
}

public java.util.List<Object[]> getTableModelForWordSub(List list) throws Exception
{
	java.util.List<Object[]> arrList = new java.util.ArrayList<Object[]>();
	if(list!=null && list.size() > 0)
	{	
		for(int i=0; i<list.size(); i++)
		{
			Drg7001Db o = (Drg7001Db) list.get(i);
			Object[] rowArray = new Object[10];		//word 替換欄位數
			rowArray[0] = View.getCommonCodeName("CONWARNING", o.getSituation());	//##1##
			rowArray[1] = Common.get(i+1);											//##2##
			rowArray[2] = View.getCommonCodeName("CONPUBLDEPT", o.getPublDept());	//##3##
			rowArray[3] = Common.get(o.getDatasourWebSite());						//##4##
			rowArray[4] = Common.get(o.getChProduct());								//##5##
			rowArray[5] = Common.get(o.getDruggist());								//##6##
			rowArray[6] = Common.get(o.getManufactorName());						//##7##
			rowArray[7] = Common.get(o.getLotNo());									//##8##
			rowArray[8] = Common.get(o.getEventDesc());								//##9##
			
			String permitStr = "";
			String applname = "";
			
			if(null != o.getDrg7003Dbs() && !o.getDrg7003Dbs().isEmpty()) 
			{
			
				for(Object dtlObj:o.getDrg7003Dbs())
				{
					Drg7003Db drg73 = (Drg7003Db)dtlObj;
					
					if(permitStr.length() > 0) permitStr +=";\n";
					
					permitStr += Common.get(View.getCommonCodeName("DRGPKID", drg73.getPermitKey()))+"字第"+Common.get(drg73.getPermitNo())+"號";
					permitStr += "，"+Common.get(drg73.getApplicationName());
					
				}
			}
			if(!"".equals(o.getWarningremark()))
			  rowArray[9] = Common.get(o.getWarningremark()) + permitStr ;							//##10##
			else
			  rowArray[9] = Common.get(o.getWarningremark()) + permitStr ;
			
			arrList.add(rowArray);
		}
	}
	return arrList;
}

}


