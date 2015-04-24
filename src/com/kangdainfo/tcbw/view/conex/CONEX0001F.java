package com.kangdainfo.tcbw.view.conex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1003Db;
import com.kangdainfo.tcbw.model.bo.Con1005Db;
import com.kangdainfo.tcbw.model.bo.Con1009Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONEX0001F extends SuperBean{

	private String id;
	private String type;
	private String isNonNeedGetBack;
	private String formdName;
    private String q_medAgencyCode;
	private String q_medAgencyName;
	private String q_engageKind;
	private String q_unionName;
	private String q_compegno;
	private String q_name;
	private String q_county;
	private String q_zipCode;
	private String isFirst;

	public String getQ_county() {
		return checkGet(q_county);
	}

	public void setQ_county(String qCounty) {
		q_county = checkSet(qCounty);
	}
	public String getQ_zipCode() {
		return checkGet(q_zipCode);
	}

	public void setQ_zipCode(String qZipCode) {
		q_zipCode = checkSet(qZipCode);
	}

	public String getId() {
		return checkGet(id);
	}

	
	public String getIsNonNeedGetBack() {
		return checkGet(isNonNeedGetBack);
	}

	public void setIsNonNeedGetBack(String isNonNeedGetBack) {
		this.isNonNeedGetBack = checkSet(isNonNeedGetBack);
	}

	public String getFormdName() {return checkGet(formdName);	}
	public void setFormdName(String formdName) {this.formdName = checkSet(formdName);	}
	public void setId(String id) {this.id = checkSet(id);}	
	public String getType() {return checkGet(type);}
	public void setType(String type) {this.type = checkSet(type);}
	public String getQ_medAgencyCode() {return checkGet(q_medAgencyCode);}
	public void setQ_medAgencyCode(String qMedAgencyCode) {q_medAgencyCode = checkSet(qMedAgencyCode);}
	public String getQ_medAgencyName() {return checkGet(q_medAgencyName);}
	public void setQ_medAgencyName(String qMedAgencyName) {q_medAgencyName = checkSet(qMedAgencyName);}	
	public String getQ_engageKind() {return checkGet(q_engageKind);}
	public void setQ_engageKind(String qengageKind) {q_engageKind = checkSet(qengageKind);}	
	public String getQ_unionName() {return checkGet(q_unionName);}
	public void setQ_unionName(String qUnionName) {q_unionName = checkSet(qUnionName);}
	public String getQ_compegno() {return checkGet(q_compegno);}
	public void setQ_compegno(String qcompegno) {q_compegno = checkSet(qcompegno);}
	public String getQ_name() {return checkGet(q_name);}
	public void setQ_name(String qname) {q_name = checkSet(qname);}
	
	public String getIsFirst() {return checkGet(isFirst);}
	public void setIsFirst(String isFirst) {isFirst = checkSet(isFirst);}

	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		java.util.Map<String, String> cityMap = TCBWCommon.getCommonCodeMap("CTY");
		java.util.Map<String, String> zipMap = View.getZipCode();
		String hql = "";
		if("02".equals(getType())){
			hql = " from Con1005Db where 1 = 1 ";
			if(!"".equals(getQ_compegno()))
				hql += " and compegno = " + Common.sqlChar(getQ_compegno());
			if(!"".equals(getQ_name()))
				hql += " and name like " + Common.sqlChar("%" + getQ_name() + "%");
			if(!"".equals(getQ_county()))
				hql += " and county = " + Common.sqlChar(getQ_county());
			if(!"".equals(getQ_zipCode()))
				hql += " and zipcode = " + Common.sqlChar(getQ_zipCode());
		}
		else if("03".equals(getType())){
			hql = " from Con1009Db where 1 = 1 ";
			if(!"".equals(getQ_medAgencyCode()))
				hql += " and medAgencyCode = " + Common.sqlChar(getQ_medAgencyCode());
			if(!"".equals(getQ_medAgencyName()))
				hql += " and medAgencyName like " + Common.sqlChar("%" + getQ_medAgencyName() + "%");
			if(!"".equals(getQ_engageKind()))
				hql += " and engageKind = " + Common.sqlChar(getQ_engageKind());
			if(!"".equals(getQ_county()))
				hql += " and agencyAddress like " + Common.sqlChar("%" + cityMap.get(getQ_county()) + "%");
			if(!"".equals(getQ_zipCode()))
				hql += " and agencyAddress like " + Common.sqlChar("%" + zipMap.get(getQ_zipCode()) + "%");				
		}else{
			hql = " from Con1003Db where 1 = 1 ";
			if(!"".equals(getQ_unionName()))
				hql += " and unionName like " + Common.sqlChar("%" + getQ_unionName() + "%");
			if(!"".equals(getQ_county()))
				hql += " and county = " + Common.sqlChar(getQ_county());
			if(!"".equals(getQ_zipCode()))
				hql += " and zipcode = " + Common.sqlChar(getQ_zipCode());
		}
		
		System.out.println("hql=="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {
					if("02".equals(getType())){					
						Con1005Db dtl = (Con1005Db)dtlObj;					
						String[] rowArray = new String[7];					
						rowArray[0] = Common.get(dtl.getId());				
						rowArray[1] = Common.get(dtl.getName());
						rowArray[2] = Common.get(dtl.getCompegno());
						rowArray[3] = Common.get(cityMap.get(dtl.getCounty())) + Common.get(zipMap.get(dtl.getZipcode()));
						rowArray[4] = Common.get(dtl.getAddress());
						rowArray[5] = Common.get(dtl.getCounty());
						rowArray[6] = Common.get(dtl.getZipcode());
						arrList.add(rowArray);
					}
					else if("03".equals(getType()))
					{					
						Con1009Db dtl = (Con1009Db)dtlObj;					
						String[] rowArray = new String[7];					
						rowArray[0] = Common.get(dtl.getId());				
						rowArray[1] = Common.get(dtl.getMedAgencyName());
						rowArray[2] = Common.get(dtl.getEngageKind());
                        
						String  str=dtl.getAgencyAddress();
						
						if(str.length()>0)
						{
						   str=str.substring(0,3);
						}
	
						String county=View.getLookupField("select codeId from CommonCode where codeKindId='42'  and codeName="+Common.sqlChar(str));
						
						int c=0;
						String  str1=dtl.getAgencyAddress();
						
						if(str1.length()>0)
						{
						  c=str1.indexOf("區");
						}
						
						if(c!=-1)
						{
							str1=str1.substring(3,c+1);
						}	
						
						String Zip=View.getLookupField("select zipcode from Con1002Db where zipname="+Common.sqlChar(str1));
						
						rowArray[3] = Common.get(cityMap.get(county))+Common.get(zipMap.get(Zip));
						rowArray[4] = Common.get(dtl.getAgencyAddress());
						rowArray[5] = Common.get(county);
						rowArray[6] = Common.get(Zip);
						arrList.add(rowArray);
					}
					else
					{
						Con1003Db dtl = (Con1003Db)dtlObj;					
						String[] rowArray = new String[7];					
						rowArray[0] = Common.get(dtl.getId());				
						rowArray[1] = Common.get(dtl.getUnionName());
						rowArray[2] = Common.get("");
						rowArray[3] = Common.get(cityMap.get(dtl.getCounty())) + Common.get(zipMap.get(dtl.getZipcode()));
						rowArray[4] = Common.get(dtl.getAddress());
						rowArray[5] = Common.get(dtl.getCounty());
						rowArray[6] = Common.get(dtl.getZipcode());
						arrList.add(rowArray);
					}
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
	public void doUpdate() throws Exception {

	}

	@Override
	public void doDelete() throws Exception {

	}

}
