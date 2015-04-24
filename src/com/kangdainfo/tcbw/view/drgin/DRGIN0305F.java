package com.kangdainfo.tcbw.view.drgin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg4005Db;


public class DRGIN0305F extends SuperBean{
	
	private String id;
	
	private String q_permitKey;
	private String q_permitNo;
	
	
	public String getId() {return checkGet(id);}
	public void setId(String s) {this.id = checkSet(s);}

	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String s) {this.q_permitKey = checkSet(s);}
	public String getQ_permitNo() {return checkGet(q_permitNo);}
	public void setQ_permitNo(String s) {this.q_permitNo = checkSet(s);}

	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg4005Db where 1=1 ";
		if(null != getQ_permitKey() && !"".equals(getQ_permitKey())){
			hql += " and permitKey2 = " + Common.sqlChar(getQ_permitKey());
		}
		if(null != getQ_permitNo() && !"".equals(getQ_permitNo())){
			hql += " and permitNo2 = " + Common.sqlChar(getQ_permitNo());
		}
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id ", this.getRecordStart(), this.getPageSize());
			if(objList!=null && objList.size()>0)
			{
				for(Object dtlObj : objList)
				{
					Drg4005Db dtl = (Drg4005Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());
					if(null != dtl.getPermitKey2() && !"".equals(dtl.getPermitKey2()) && null != dtl.getPermitNo2() && !"".equals(dtl.getPermitNo2())){
						rowArray[1] =  Common.get(View.getCommonCodeName("DRGPKID", dtl.getPermitKey2()))+"第"+Common.get(dtl.getPermitNo2())+"號";
					}
					rowArray[2] = Common.get(dtl.getProductName2());
					rowArray[3] = "";
					if(null != dtl.getAssessResult() && !"".equals(dtl.getAssessResult())){
						StringBuffer s = new StringBuffer();
						for(String result : dtl.getAssessResult().split(",")){
							if(s.toString().length() > 0)	s.append(",");
							s.append(View.getCommonCodeName("DRG0308", result));
						}
						if(s.toString().length() > 0){
							rowArray[3] = s.toString();
						}
					}
					rowArray[4] = Common.get(dtl.getAssessDesc());
					rowArray[5] = "Y".equals(Common.get(dtl.getIsClose()))?"是":"否";
					rowArray[6] =  "<input type='button' class='toolbar_default' name='btn_Data' value='明　細' onClick=\"listContainerRowClick('"+dtl.getId()+"');queryData('" + dtl.getId() + "');\"> ";
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {}

	@Override
	public void doDelete() throws Exception {}
}
