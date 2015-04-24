package com.kangdainfo.tcbw.view.vcos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonGroup;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Cos7001Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7002Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.web.listener.MyServletContext;
public class VCOS0100F extends SuperBean
{
	private String id;
    private String applNo;
    private String prodtype;
    private String chProduct;
    private String dataRevDate;
    private String publDeptCodeId;
    private String publDept;
    private String publDeptName;
    private String publCountry;
    private String manufactorCountry;
    private String ingredient;
    private String brand;
    private String situation;    
    private String publDate;
    private String subjecttype;
    private String subject;
    private String contextsummary;
    private String lotNo;
    private String datasourWebSite;
    private String estimateDate;
    private String isImport;
    private String ispublnews;
    private String ispublconsumer;
    private String publconsumerDate;
    private String lamp;
    private String searchdept;
    private String remark;
    private String aftereffect;
    private String status;
    private String statusCh;
    private String actionType;
    private String formType;
    private String tabId;

	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}		
	public String getApplNo() {return checkGet(applNo);}
	public void setApplNo(String applNo) {this.applNo = checkSet(applNo);}
	public String getProdtype() {return checkGet(prodtype);}
	public void setProdtype(String prodtype) {this.prodtype = checkSet(prodtype);}
	public String getChProduct() {return checkGet(chProduct);}
	public void setChProduct(String chProduct) {this.chProduct = checkSet(chProduct);}
	public String getDataRevDate() {return checkGet(dataRevDate);}
	public void setDataRevDate(String dataRevDate) {this.dataRevDate = checkSet(dataRevDate);}
	public String getPublDeptCodeId() {return checkGet(publDeptCodeId);}
	public void setPublDeptCodeId(String publDeptCodeId) {this.publDeptCodeId = checkSet(publDeptCodeId);}
	public String getPublDept() {return checkGet(publDept);}
	public void setPublDept(String publDept) {this.publDept = checkSet(publDept);}
	public String getPublDeptName() {return checkGet(publDeptName);}
	public void setPublDeptName(String publDeptName) {this.publDeptName = checkSet(publDeptName);}
	public String getPublCountry() {return checkGet(publCountry);}
	public void setPublCountry(String publCountry) {this.publCountry = checkSet(publCountry);}
	public String getManufactorCountry() {return checkGet(manufactorCountry);}
	public void setManufactorCountry(String manufactorCountry) {this.manufactorCountry = checkSet(manufactorCountry);}
	public String getIngredient() {return checkGet(ingredient);}
	public void setIngredient(String ingredient) {this.ingredient = checkSet(ingredient);}
	public String getBrand() {return checkGet(brand);}
	public void setBrand(String brand) {this.brand = checkSet(brand);}
	public String getSituation() {return checkGet(situation);}
	public void setSituation(String situation) {this.situation = checkSet(situation);}
	public String getPublDate() {return checkGet(publDate);}
	public void setPublDate(String publDate) {this.publDate = checkSet(publDate);}
	public String getSubjecttype() {return checkGet(subjecttype);}
	public void setSubjecttype(String subjecttype) {this.subjecttype = checkSet(subjecttype);}
	public String getSubject() {return checkGet(subject);}
	public void setSubject(String subject) {this.subject = checkSet(subject);}
	public String getContextsummary() {return checkGet(contextsummary);}
	public void setContextsummary(String contextsummary) {this.contextsummary = checkSet(contextsummary);}
	public String getLotNo() {return checkGet(lotNo);}
	public void setLotNo(String lotNo) {this.lotNo = checkSet(lotNo);}
	public String getDatasourWebSite() {return datasourWebSite;}
	public void setDatasourWebSite(String datasourWebSite) {this.datasourWebSite =datasourWebSite;}
	public String getEstimateDate() {return checkGet(estimateDate);}
	public void setEstimateDate(String estimateDate) {this.estimateDate = checkSet(estimateDate);}
	public String getIsImport() {return checkGet(isImport);}
	public void setIsImport(String isImport) {this.isImport = checkSet(isImport);}
	public String getIspublnews() {return checkGet(ispublnews);}
	public void setIspublnews(String ispublnews) {this.ispublnews = checkSet(ispublnews);}
	public String getIspublconsumer() {return checkGet(ispublconsumer);}
	public void setIspublconsumer(String ispublconsumer) {this.ispublconsumer = checkSet(ispublconsumer);}
	public String getPublconsumerDate() {return checkGet(publconsumerDate);}
	public void setPublconsumerDate(String publconsumerDate) {this.publconsumerDate = checkSet(publconsumerDate);}
	public String getLamp() {return checkGet(lamp);}
	public void setLamp(String lamp) {this.lamp = checkSet(lamp);}
	public String getSearchdept() {return checkGet(searchdept);}
	public void setSearchdept(String searchdept) {this.searchdept = checkSet(searchdept);}
	public String getRemark() {return checkGet(remark);}
	public void setRemark(String remark) {this.remark = checkSet(remark);}
	public String getAftereffect() {return checkGet(aftereffect);}
	public void setAftereffect(String aftereffect) {this.aftereffect = checkSet(aftereffect);}
	public String getStatus() {return checkGet(status);}
	public void setStatus(String status) {this.status = checkSet(status);}
	public String getStatusCh() {return checkGet(statusCh);}
	public void setStatusCh(String statusCh) {this.statusCh = checkSet(statusCh);}
	public String getActionType() {return checkGet(actionType);}
	public void setActionType(String actionType) {this.actionType =  checkSet(actionType);}
	public String getFormType() {return checkGet(formType);}
	public void setFormType(String formType) {this.formType =  checkSet(formType);}
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}	
	/** default constructor */
    public VCOS0100F() {
    }

    @Override
	public void doCreate() throws Exception {
		Cos7001Db obj = new Cos7001Db();
		obj.setId(Common.getLong(id));
		obj.setApplNo(Common.get(getApplNo()));
		obj.setStatus(Common.get(getStatus()));
		obj.setProdtype("C01");		
		obj.setChProduct(Common.get(getChProduct()));
		obj.setDataRevDate(Common.get(getDataRevDate()));
		obj.setPublDept(Common.get(getPublDeptCodeId()));
		obj.setPublCountry(Common.get(getPublCountry()));
		obj.setManufactorCountry(Common.get(getManufactorCountry()));
		obj.setIngredient(Common.get(getIngredient()));
		obj.setBrand(Common.get(getBrand()));
		obj.setSituation(Common.get(getSituation()));
		obj.setPublDate(Common.get(getPublDate()));
		obj.setSubjecttype(Common.get(getSubjecttype()));
		obj.setContextsummary(Common.get(getContextsummary()));
		obj.setLotNo(Common.get(getLotNo()));
		obj.setDatasourWebSite(getDatasourWebSite());
		obj.setEstimateDate(Common.get(getEstimateDate()));
		obj.setIsImport(Common.get(getIsImport()));
		obj.setIspublnews(Common.get(getIspublnews()));
		obj.setIspublconsumer(Common.get(getIspublconsumer()));
		obj.setPublconsumerDate(Common.get(getPublconsumerDate()));
		obj.setLamp(Common.get(getLamp()));
		obj.setSearchdept("06");
		obj.setRemark(Common.get(getRemark()));
		obj.setAftereffect(Common.get(getAftereffect()));
		obj.setModifyDate(Datetime.getYYYMMDD());
		obj.setModifyTime(Datetime.getHHMM());
		obj.setModifier(Common.get(getUserID()));
		ServiceGetter.getInstance().getCommonService().save(obj);
		setId(Common.get(obj.getId()));
	}
    
	@Override
	public void doUpdate() throws Exception {
		String procDesc ="";
		Cos7001Db obj= (Cos7001Db)ServiceGetter.getInstance().getCommonService().getObject(" from Cos7001Db where  1=1 and id = "+Common.getInt(getId()));
		if(null != obj ){
			obj.setId(Common.getLong(id));
			obj.setApplNo(Common.get(getApplNo()));
			obj.setStatus(Common.get(getStatus()));
			obj.setProdtype("C01");			
			obj.setChProduct(Common.get(getChProduct()));
			obj.setDataRevDate(Common.get(getDataRevDate()));
			obj.setPublDept(getPublDeptCodeId());
			obj.setPublCountry(Common.get(getPublCountry()));
			obj.setManufactorCountry(Common.get(getManufactorCountry()));
			obj.setIngredient(Common.get(getIngredient()));
			obj.setBrand(Common.get(getBrand()));
			obj.setSituation(Common.get(getSituation()));
			obj.setPublDate(Common.get(getPublDate()));
			obj.setSubjecttype(Common.get(getSubjecttype()));
			obj.setContextsummary(Common.get(getContextsummary()));
			obj.setLotNo(Common.get(getLotNo()));
			obj.setDatasourWebSite(getDatasourWebSite());
			obj.setEstimateDate(Common.get(getEstimateDate()));
			if("10".equals(getStatus())){
				obj.setEstimateDate(Datetime.getYYYMMDD());
			}
			obj.setIsImport(Common.get(getIsImport()));
			obj.setIspublnews(Common.get(getIspublnews()));
			obj.setIspublconsumer(Common.get(getIspublconsumer()));
			obj.setPublconsumerDate(Common.get(getPublconsumerDate()));
			obj.setLamp(Common.get(getLamp()));
			obj.setSearchdept("06");
			obj.setRemark(Common.get(getRemark()));
			obj.setAftereffect(Common.get(getAftereffect()));
			obj.setModifyDate(Datetime.getYYYMMDD());
			obj.setModifyTime(Datetime.getHHMM());
			obj.setModifier(Common.get(getUserID()));
			
			//送出button
			if("doSend".equals(getActionType())){
				String newApplNo = TCBWCommon.getApplNo("COS04","C01",Datetime.getYYY());
				if(null != newApplNo && !"".equals(newApplNo)){
						obj.setApplNo(newApplNo);
						obj.setStatus("10");
						procDesc ="案件評估者-警訊評估中";
					}			
			}
			if("assesscomp".equals(getActionType())){				
				obj.setEstimateDate(Datetime.getYYYMMDD());
				obj.setStatus("90");
				procDesc ="結案";
			}
			
			
			ServiceGetter.getInstance().getCommonService().update(obj);			
			
			if(null != procDesc && !"".equals(procDesc)){
				//歷程紀錄
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("COS03",obj.getId(), obj.getApplNo(), obj.getStatus(), procDesc, TCBWCommon.getCurrentUserId());
			}
		}else throw new Exception("查無該筆資料！");			
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		VCOS0100F obj= this;
		Cos7001Db a =(Cos7001Db)ServiceGetter.getInstance().getCommonService().getObject("from Cos7001Db where id="+Common.getInt(getId()));
		if(null != a){	
			java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("COSQTSTATUS");

			
			obj.setId(Common.get(a.getId()));
			obj.setApplNo(Common.get(a.getApplNo()));
			obj.setStatus(Common.get(a.getStatus()));
			obj.setStatusCh(statusMap.get(Common.get(a.getStatus())));
			obj.setProdtype("化粧品");	
			obj.setChProduct(Common.get(a.getChProduct()));
			obj.setDataRevDate(Common.get(a.getDataRevDate()));
	        obj.setPublDept(Common.get(a.getPublDept()));
	        obj.setPublDeptCodeId(Common.get(a.getPublDept()));
	        obj.setPublDeptName(View.getCommonCodeName("CONPUBLDEPT", a.getPublDept()));
			obj.setPublCountry(Common.get(a.getPublCountry()));
			obj.setManufactorCountry(Common.get(a.getManufactorCountry()));
			obj.setIngredient(Common.get(a.getIngredient()));
			obj.setBrand(Common.get(a.getBrand()));
			obj.setSituation(Common.get(a.getSituation()));
			obj.setPublDate(Common.get(a.getPublDate()));
			obj.setSubjecttype(Common.get(a.getSubjecttype()));
			obj.setContextsummary(Common.get(a.getContextsummary()));
			obj.setLotNo(Common.get(a.getLotNo()));
			obj.setDatasourWebSite(a.getDatasourWebSite());
			obj.setEstimateDate(Common.get(a.getEstimateDate()));
			obj.setIsImport(Common.get(a.getIsImport()));
			obj.setIspublnews(Common.get(a.getIspublnews()));
			obj.setIspublconsumer(Common.get(a.getIspublconsumer()));
			obj.setPublconsumerDate(Common.get(a.getPublconsumerDate()));
			obj.setLamp(Common.get(a.getLamp()));
			obj.setSearchdept(View.getOneCodeName(Common.get(a.getSearchdept())));
			obj.setRemark(Common.get(a.getRemark()));
			obj.setAftereffect(Common.get(a.getAftereffect()));
			obj.setConJSBuilder(this.genCon0001DbSet(a.getId()));
		}	
		return obj;
	}
	
	//附件(監控附件)
	public String getAddFileVcos0100() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus = View.getLookupField("select status from Cos7001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='COS' and systemType like 'COS040001%' and dbName='COS7001DB' and upLoadId="+Common.getLong(getId());
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
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=COS&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus) <= 10){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	String[] conRow;

	public String[] getConRow() {
		return conRow;
	}

	public void setConRow(String[] conRow) {
		this.conRow = conRow;
	}

	public final String[] arrConFieldName = { "fileName", "fileExplan","fileRoute" };
	String conJSBuilder;

	public String getConJSBuilder() {
		if (conJSBuilder != null) return conJSBuilder;
		else return "";
	}

	public void setConJSBuilder(String conJSBuilder) {
		this.conJSBuilder = conJSBuilder;
	}

	public String genCon0001DbSet(Long cos4001Id) throws Exception {
		String hql = " from Con0001Db where fileKind='COS' and systemType = 'COS040001' and dbName='Cos7001Db' and upLoadId="+ cos4001Id;
		List<Con0001Db> conList = ServiceGetter.getInstance().getCommonService().load(hql + " order by id asc");
		if (null != conList && !conList.isEmpty()) {
			StringBuilder sb = new StringBuilder(1024);
			for (Con0001Db dtl : conList) {
				sb.append("addConRow('conTable'");
				for (int j = 0; j < arrConFieldName.length; j++) {
					if (j == 2) {
						String attFile = Common.get(dtl.getFileRoute()) + ":;:"+ Common.get(dtl.getFileName());
						sb.append(",").append(Common.sqlChar(attFile));
					} else {
						sb.append(",").append(Common.sqlChar(Common.escapeReturnChar(checkGet(BeanUtils.getProperty(dtl,arrConFieldName[j])), true)));
					}
				}
				sb.append(",'").append(dtl.getId() != null ? dtl.getId() : "").append("'");
				sb.append(");\n");
			}
			this.setConJSBuilder(sb.toString());
			return sb.toString();
		}
		return "";
	}
	
	@Override
	public java.util.ArrayList<String[]> doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		return arrList;
	}

	@Override
	public void doDelete() throws Exception {
		
	}	
	
	
	
	
}
