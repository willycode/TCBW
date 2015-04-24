package com.kangdainfo.tcbw.view.drgse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg1001Db;
import com.kangdainfo.tcbw.model.bo.Drg1002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DRGSE0001F extends SuperBean{

	private String id;
	private String level;
	
	//A,A+(02,03)
	private String formulation1;
	private String defectiveProduct1;
	private String isStop1;
	
	//B(04)
	private String permitNo2;
	private String permitKey2;
	private String lotNum2;
	private String num2;
	private String year2;
	private String isStop2;	
	
	
	//專案(01)
	private String permitNo3;
	private String permitKey3;
	private String defectiveProduct3;	
	private String isStop3;
	
	private String q_isQuery;
	private String q_id;
	private String q_level;	
	private String q_formulation;
	private String q_defectiveProduct;	
	private String q_defectiveProductPrj;
	private String q_num;
	private String q_year;
	private String q_isStop;
	private String q_permitNo;
	private String q_permitKey;
	private String q_lotNum;
	
	public String getId() {
		return checkGet(id);
	}
	public void setId(String id) {
		this.id = checkSet(id);
	}
	public String getLevel() {
		return checkGet(level);
	}
	public void setLevel(String level) {
		this.level = checkSet(level);
	}
	public String getFormulation1() {
		return checkGet(formulation1);
	}
	public void setFormulation1(String formulation1) {
		this.formulation1 = checkSet(formulation1);
	}
	public String getDefectiveProduct1() {
		return checkGet(defectiveProduct1);
	}
	public void setDefectiveProduct1(String defectiveProduct1) {
		this.defectiveProduct1 = checkSet(defectiveProduct1);
	}
	public String getIsStop1() {
		return checkGet(isStop1);
	}
	public void setIsStop1(String isStop1) {
		this.isStop1 = checkSet(isStop1);
	}
	public String getPermitNo2() {
		return checkGet(permitNo2);
	}
	public void setPermitNo2(String permitNo2) {
		this.permitNo2 = checkSet(permitNo2);
	}
	public String getPermitKey2() {
		return checkGet(permitKey2);
	}
	public void setPermitKey2(String permitKey2) {
		this.permitKey2 = checkSet(permitKey2);
	}
	public String getLotNum2() {
		return checkGet(lotNum2);
	}
	public void setLotNum2(String lotNum2) {
		this.lotNum2 = checkSet(lotNum2);
	}
	public String getNum2() {
		return checkGet(num2);
	}
	public void setNum2(String num2) {
		this.num2 = checkSet(num2);
	}
	public String getYear2() {
		return checkGet(year2);
	}
	public void setYear2(String year2) {
		this.year2 = checkSet(year2);
	}
	public String getIsStop2() {
		return checkGet(isStop2);
	}
	public void setIsStop2(String isStop2) {
		this.isStop2 = checkSet(isStop2);
	}
	public String getPermitNo3() {
		return checkGet(permitNo3);
	}
	public void setPermitNo3(String permitNo3) {
		this.permitNo3 = checkSet(permitNo3);
	}
	public String getPermitKey3() {
		return checkGet(permitKey3);
	}
	public void setPermitKey3(String permitKey3) {
		this.permitKey3 = checkSet(permitKey3);
	}
	public String getDefectiveProduct3() {
		return checkGet(defectiveProduct3);
	}
	public void setDefectiveProduct3(String defectiveProduct3) {
		this.defectiveProduct3 = checkSet(defectiveProduct3);
	}
	public String getIsStop3() {
		return checkGet(isStop3);
	}
	public void setIsStop3(String isStop3) {
		this.isStop3 = checkSet(isStop3);
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
	public String getQ_level() {
		return checkGet(q_level);
	}
	public void setQ_level(String qLevel) {
		q_level = checkSet(qLevel);
	}
	public String getQ_formulation() {
		return checkGet(q_formulation);
	}
	public void setQ_formulation(String qFormulation) {
		q_formulation = checkSet(qFormulation);
	}
	public String getQ_defectiveProduct() {
		return checkGet(q_defectiveProduct);
	}
	public void setQ_defectiveProduct(String qDefectiveProduct) {
		q_defectiveProduct = checkSet(qDefectiveProduct);
	}
	public String getQ_defectiveProductPrj() {
		return checkGet(q_defectiveProductPrj);
	}
	public void setQ_defectiveProductPrj(String qDefectiveProductPrj) {
		q_defectiveProductPrj = checkSet(qDefectiveProductPrj);
	}
	public String getQ_num() {
		return checkGet(q_num);
	}
	public void setQ_num(String qNum) {
		q_num = checkSet(qNum);
	}
	public String getQ_year() {
		return checkGet(q_year);
	}
	public void setQ_year(String qYear) {
		q_year = checkSet(qYear);
	}
	public String getQ_isStop() {
		return checkGet(q_isStop);
	}
	public void setQ_isStop(String qIsStop) {
		q_isStop = checkSet(qIsStop);
	}
	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}
	public void setQ_permitNo(String qPermitNo) {
		q_permitNo = checkSet(qPermitNo);
	}
	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}
	public void setQ_permitKey(String qPermitKey) {
		q_permitKey = checkSet(qPermitKey);
	}
	public String getQ_lotNum() {
		return checkGet(q_lotNum);
	}
	public void setQ_lotNum(String qLotNum) {
		q_lotNum = checkSet(qLotNum);
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		DRGSE0001F obj = this;
		
		Drg1002Db c = (Drg1002Db)View.getObject("from Drg1002Db where id = " + Common.getLong(getId()));		
		if(c != null){			
			obj.setId(Common.get(c.getId()));
			obj.setLevel(c.getLevel());
			
			if((obj.getLevel().equals("02")) || (obj.getLevel().equals("03"))) {	    
		    	obj.setDefectiveProduct1(c.getDefectiveProduct());
		    	obj.setFormulation1(c.getFormulation());
		    	obj.setIsStop1(c.getIsStop());		    	
		    }
			else if((obj.getLevel().equals("04"))){
				obj.setPermitKey2(c.getPermitKey());
				obj.setPermitNo2(c.getPermitNo());
				obj.setLotNum2(c.getLotNum());
				obj.setNum2(c.getNum());
				obj.setYear2(c.getYear());
                obj.setIsStop2(c.getIsStop());		    	
		    }else{
		    	obj.setPermitKey3(c.getPermitKey());
		    	obj.setPermitNo3(c.getPermitNo());
		    	obj.setDefectiveProduct3(c.getDefectiveProduct());
		    	obj.setIsStop3(c.getIsStop());
		    }			
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg1002Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_level()))
				hql += " and level = " + Common.sqlChar(getQ_level());
			
			if(!"".equals(getQ_formulation()))
				hql += " and formulation = " + Common.sqlChar(getQ_formulation());
			
			if(!"".equals(getQ_defectiveProduct()))
				hql += " and defectiveProduct = " + Common.sqlChar(getQ_defectiveProduct());
			
			if((getQ_level().equals("04")) && (!"".equals(getQ_defectiveProductPrj())))
				hql += " and defectiveProduct = " + Common.sqlChar(getQ_defectiveProductPrj());
			
			if(!"".equals(getQ_isStop()))
				hql += " and isStop = " + Common.sqlChar(getQ_isStop());
			
			if(!"".equals(getQ_lotNum()))
				hql += " and lotNum = " + Common.sqlChar(getQ_lotNum());
			
			if(!"".equals(getQ_num()))
				hql += " and num = " + Common.sqlChar(getQ_num());
			
			if(!"".equals(getQ_year()))
				hql += " and year = " + Common.sqlChar(getQ_year());
			
			if(!"".equals(getQ_permitNo()))
				hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
			
			if(!"".equals(getQ_permitKey()))
				hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
			System.out.println("hql: " + hql);			
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來				
				java.util.Map<String, String> formulationMap = TCBWCommon.getCommonCodeMap("DRGFLN");
				java.util.Map<String, String> drgpkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
				java.util.Map<String, String> drgrkldMap = TCBWCommon.getCommonCodeMap("DRGRKL");
				java.util.List<Drg1001Db> drg1001Dbs = ServiceGetter.getInstance().getTcbwService().load("from Drg1001Db");
				java.util.Map<String, String> subCodeMap = new java.util.HashMap<String, String>(); //不良品缺陷子代碼
				if(drg1001Dbs!=null && drg1001Dbs.size()>0){
					for(Drg1001Db drg1001Db : drg1001Dbs){
						subCodeMap.put(drg1001Db.getDpdKind(), drg1001Db.getDpdKindName());
					}
					drg1001Dbs.clear();
				}
				
				for(Object dtlObj : objList) {				
					Drg1002Db dtl = (Drg1002Db)dtlObj;
					String[] rowArray = new String[9];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getLevel()!=null?drgrkldMap.get(dtl.getLevel()):"");
					rowArray[2] = Common.get(formulationMap.get(dtl.getFormulation()));
					rowArray[3] = Common.get(dtl.getDefectiveProduct()!=null?subCodeMap.get(dtl.getDefectiveProduct()):"");
					rowArray[4] = Common.get(dtl.getLotNum()).equals("Y")?"同批號":Common.get(dtl.getLotNum()).equals("N")?"不同批號":"";
					rowArray[5] = Common.get(dtl.getNum());
					rowArray[6] = Common.get(dtl.getYear());
					rowArray[7] = Common.get(dtl.getIsStop()).equals("Y")?"是":Common.get(dtl.getIsStop()).equals("N")?"否":"";					
					rowArray[8] = Common.get(dtl.getPermitKey()!=null?(drgpkidMap.get(dtl.getPermitKey())+"-"+dtl.getPermitNo()):"");
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}
	
//	protected String[][] getInsertCheckSQL(){	
//		String[][] checkSQLArray = new String[1][4];
//	 	checkSQLArray[0][0] = "select count(*) from Drg1002Db where code = " + Common.sqlChar(getCode());
//		checkSQLArray[0][1] = ">";
//		checkSQLArray[0][2] = "0";
//		checkSQLArray[0][3] = "該流程角色代碼已存在，請重新輸入！";
//		return checkSQLArray;
//	}

	@Override
	public void doCreate() throws Exception {
		
		Drg1002Db obj = new Drg1002Db();
		
	    obj.setLevel(getLevel());
	    
		if((getLevel().equals("02")) || (getLevel().equals("03"))) {	 //A,A+  
	    	obj.setDefectiveProduct(getDefectiveProduct1());
	    	obj.setFormulation(getFormulation1());
	    	obj.setIsStop(getIsStop1());		    	
	    }
		else if((getLevel().equals("04"))){     //B
			obj.setLotNum(getLotNum2());
			obj.setNum(getNum2());
			obj.setYear(getYear2());
            obj.setIsStop(getIsStop2());		    	
	    }else{
	    	obj.setPermitKey(getPermitKey3());
	    	obj.setPermitNo(getPermitNo3());
	    	obj.setDefectiveProduct(getDefectiveProduct3());
	    	obj.setIsStop(getIsStop3());
	    }   

	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());

		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
//	protected String[][] getUpdateCheckSQL(){	
//		String[][] checkSQLArray = new String[1][4];	 	
//		checkSQLArray[0][0] = "select count(*) from Drg1002Db where code = " + Common.sqlChar(getCode()) + " and id != " + Common.getLong(getId()); 	
//	 	checkSQLArray[0][1] = ">";
//		checkSQLArray[0][2] = "0";
//		checkSQLArray[0][3] = "該流程角色代碼已存在，請重新輸入！";	
//		return checkSQLArray;		
//	}

	@Override
	public void doUpdate() throws Exception {
		Drg1002Db obj = (Drg1002Db)View.getObject(" from Drg1002Db where id = " + Common.getLong(getId()));
		if(obj != null){
			setId(Common.get(obj.getId()));
		    
			obj.setLevel(getLevel());
		    
			if((getLevel().equals("02")) || (getLevel().equals("03"))) {	//A,A+  
		    	obj.setDefectiveProduct(getDefectiveProduct1());
		    	obj.setFormulation(getFormulation1());
		    	obj.setIsStop(getIsStop1());		    	
		    }
			else if(getLevel().equals("04")){  //B
				obj.setLotNum(getLotNum2());
				obj.setNum(getNum2());
				obj.setYear(getYear2());
	            obj.setIsStop(getIsStop2());		    	
		    }else{
		    	obj.setPermitKey(getPermitKey3());
		    	obj.setPermitNo(getPermitNo3());
		    	obj.setDefectiveProduct(getDefectiveProduct3());
		    	obj.setIsStop(getIsStop3());
		    }			

		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
		}
	}

	@Override
	public void doDelete() throws Exception {
		Drg1002Db obj = (Drg1002Db)View.getObject(" from Drg1002Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
