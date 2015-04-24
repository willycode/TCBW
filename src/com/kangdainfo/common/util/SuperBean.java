package com.kangdainfo.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.owasp.esapi.ESAPI;

import com.kangdainfo.common.util.report.ReportEnvironment;

/**
*<br>程式目的：所有前端網頁的bean都需繼承此類別
*<br>程式代號：SuperBean
*<br>撰寫日期：93/12/01
*<br>程式作者：Kang Da Information
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public abstract class SuperBean {
	int listLimit=500;
	public int getListLimit(){ return listLimit; }
	public void setListLimit(int i){ listLimit=i; }
	public String getListLimitError() { return "查詢資料超過"+getListLimit()+"筆, 請縮小查詢範圍！"; }

	String state="init";
	public String getState(){ return checkGet(state); }
	public void setState(String s){ state=checkSet(s); }
	
	String errorMsg;
	public String getErrorMsg(){ return checkGet(errorMsg); }
	public void setErrorMsg(String s){ errorMsg=checkSet(s); }	

	String queryAllFlag="false";
	public String getQueryAllFlag(){ return checkGet(queryAllFlag); }
	public void setQueryAllFlag(String s){ queryAllFlag=checkSet(s); }	
	
	String progID;
	public String getProgID() {return checkGet(progID);}
	public void setProgID(String s) {this.progID = checkSet(s);	}	

	String userID;
	public String getUserID(){ return checkGet(userID); }
	public void setUserID(String s){ userID=checkSet(s); }
	
	String editID;
	public String getEditID(){ return checkGet(editID); }
	public void setEditID(String s){ editID=checkSet(s); }
	
	String editDate;
	public String getEditDate(){ return checkGet(editDate); }
	public void setEditDate(String s){ editDate=checkSet(s); }
	
	String editTime;
	public String getEditTime(){ return checkGet(editTime); }
	public void setEditTime(String s){ editTime=checkSet(s); }	
	
	String nextInsert;
	public String getNextInsert(){ return checkGet(nextInsert); }
	public void setNextInsert(String s){ nextInsert=checkSet(s); }
	
	String listContainerActiveRowId;
	public String getListContainerActiveRowId(){ return checkGet(listContainerActiveRowId); }
	public void setListContainerActiveRowId(String s){ listContainerActiveRowId=checkSet(s); }	
	
	String pageNumberVar;
	String q_outputFormat;	
	String deptCode;	
	public String getPageNumberVar() { return checkGet(pageNumberVar); }
	public void setPageNumberVar(String s) { pageNumberVar = checkSet(s); }
	public String getQ_outputFormat() { return Common.get(q_outputFormat).equals("")?ReportEnvironment.VAL_FORMAT_PDF:checkGet(q_outputFormat); }
	public void setQ_outputFormat(String s) { q_outputFormat = checkSet(s); }
	public String getDeptCode() { return checkGet(deptCode); }
	public void setDeptCode(String s) { deptCode = checkSet(s); }	
	
	String querySubmit;
	public String getQuerySubmit() {return checkGet(querySubmit);	}
	public void setQuerySubmit(String querySubmit) {this.querySubmit = checkSet(querySubmit);}	

	//檢查資料隱碼之旗標
	String sqlInjectionFlag="false";		
	boolean isPOJO = false;
	public boolean isPOJO() {return isPOJO;}
	
	/*回傳各種狀態值*/
	public void setStateInsertSuccess()  {		
		setState("insertSuccess");
		if (!"".equals(getNextInsert())) {
			setState("insert");	
		}		
	}
	public void setStateInsertError()    { setState("insertError"); }
	public void setStateUpdateSuccess()  { setState("updateSuccess"); }
	public void setStateUpdateError()    { setState("updateError"); }
	public void setStateDeleteSuccess()  { setState("deleteSuccess"); }
	public void setStateDeleteError()    { setState("deleteError"); }
	public void setStateQueryOneSuccess(){ 
		setState("queryOneSuccess");
	}
	public void setStateQueryAllSuccess(){ 
		if ("queryAll".equals(getState())){
			setState("queryAllSuccess");			
		}
	}
	
  	/**
  	 * <br>
   	 * <br>目的：撰寫JavaBean get方法時所需套用的函數
   	 * <br>參數：資料字串
     * <br>傳回：檢查資料為null,是則傳回空字串
  	*/
	static public String get(String s){
		if(s==null){
			return "";
		}else{
			s = ESAPI.encoder().encodeForHTML(s);	//其實下方已經有編碼了，根本不需要，這是給機器用的
			s = ESAPI.encoder().decodeForHTML(s);	//所以回覆
			return s.trim();
		}
	}
  	/**
  	 * <br>
  	 * <br>目的：撰寫JavaBean set方法時所需套用的函數
  	 * <br>參數：資料字串
  	 * <br>傳回：將資料前後空白去除
  	*/
	static public String set(String s){
		if(s==null){
			return "";
		}else{
			return s.trim();
		}
	}	
	
  	/**
  	 * <br>
   	 * <br>目的：撰寫JavaBean chect get方法時所需套用的函數
   	 * <br>參數：資料字串
     * <br>傳回：檢查資料為null,是則傳回空字串
  	*/
	public String checkGet(String s){
//		if(s==null){
//			return "";
//		}else{
//			s = s.trim();	
//			s = s.replaceAll("&","&amp;");
//			s = s.replaceAll("<","&lt;");
//			s = s.replaceAll(">","&gt;");
//			s = s.replaceAll("\"","&quot;");
//			s = s.replaceAll("'","&#39;");
//			//s = s.replaceAll("(","&#40;");
//			//s = s.replaceAll(")","&#41;");
//			//s = org.apache.commons.lang.StringEscapeUtils.escapeHtml(s);
//			ESAPI.encoder().encodeForHTML(s);  //<= 問題有二 1.沒有塞回s  2.所有文字皆編碼，連中文、符號等，系統大亂
//			return s;
//			
//		}
		s = get(s);
		s = s.replaceAll("&","&amp;");
		s = s.replaceAll("<","&lt;");
		s = s.replaceAll(">","&gt;");
		s = s.replaceAll("\"","&quot;");
		s = s.replaceAll("'","&#39;");
		//s = s.replaceAll("(","&#40;");
		//s = s.replaceAll(")","&#41;");
		//s = org.apache.commons.lang.StringEscapeUtils.escapeHtml(s);
		return s;

	}
	public String[] checkGet(String[] s){
		if(s != null && s.length != 0){
			for(int i = 0 ; i < s.length ; i ++){
				s[i] = checkGet(s[i]);
			}
		}
		return s;
	}
  	/**
  	 * <br>
  	 * <br>目的：撰寫JavaBean check set方法時所需套用的函數
  	 * <br>參數：資料字串
  	 * <br>傳回：將資料前後空白去除
  	*/
	public String checkSet(String s){
		if(s==null){
			return s;
		}else{			
			/**
			//檢查資料隱碼
			if ("false".equals(sqlInjectionFlag)){
				if ((s.indexOf("'")>=0)||(s.indexOf("\"")>=0))	{
					sqlInjectionFlag="true";
				}
			}
			return s.trim().replaceAll("'", "''");
			**/
			s = s.replaceAll("&amp;","&");
			s = s.replaceAll("&lt;","<");
			s = s.replaceAll("&gt;",">");
			s = s.replaceAll("&quot;","\"");
			s = s.replaceAll("&#39;","'");
			return s.trim();
			
		}
	}	
	public String[] checkSet(String[] s){
		if(s != null && s.length != 0){
			for(int i = 0 ; i < s.length ; i ++){
				s[i] = checkSet(s[i]);
			}
		}
		return s;
	}
		
	//--------- Pagging Control Start -----------------------
    int pageSize = 10;
    int totalPage = 0;
    int currentPage = 1;
    int totalRecord = 0;
    int recordStart = 0;
    int recordEnd = 0;
            
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    /*public void setPageSize(String s) {
        try{
            this.pageSize = Integer.parseInt(s);
        }catch(Exception x){}        
        
    }*/
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPageNumber) {
        this.currentPage = currentPageNumber;
    }
    
    /*protected void setCurrentPage(String s) {
        try{
            this.currentPage = Integer.parseInt(s);
        }catch(Exception x){}
    }*/
    
    public int getRecordEnd() {
        return recordEnd;
    }
    protected void setRecordEnd(int recordEnd) {
        this.recordEnd = recordEnd;
    }
    public int getRecordStart() {
        return recordStart;
    }
    protected void setRecordStart(int recordStart) {
        this.recordStart = recordStart;
    }
    public int getTotalPage() {
        return totalPage;
    }
    protected void setTotalPage(int totalPageNumber) {
        this.totalPage = totalPageNumber;
    }
    public int getTotalRecord() {
        return totalRecord;
    }
    protected void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }    
    String historyPage;
    String historyPageSize;
    public String getHistoryPage(){ return checkGet(historyPage); }
    public void setHistoryPage(String s){ historyPage=checkSet(s); }    
    public String getHistoryPageSize(){ return checkGet(historyPageSize); }
    public void setHistoryPageSize(String s){ historyPageSize=checkSet(s); }
    
    
	public int initRecourdCount(String hql) throws Exception {
		isPOJO = true;
		Object x = com.kangdainfo.common.util.View.getLookupField("select count(*) "+hql);
		if (x!=null && !"".equals(get(x.toString()))) {
			int rc = Integer.parseInt(x.toString());			
			processCurrentPageAttribute(rc);
			return rc;
		} else {
			processCurrentPageAttribute(0);
		}
		return 0;
	}
	
    protected void processCurrentPageAttribute(java.sql.ResultSet rs) throws Exception{
        rs.last();
        totalRecord = rs.getRow();
        rs.first();

        if (totalRecord <= 0) {
            recordStart = 0;
            recordEnd = 0;            
            return ;
        }

        if (pageSize > 0) {
            totalPage = (totalRecord - 1) / pageSize + 1;
            if (currentPage < 1)
                currentPage = 1;
            if (currentPage > totalPage)
                currentPage = totalPage;

            recordStart = pageSize * (currentPage - 1) + 1;
            recordEnd = Math.min(recordStart + pageSize - 1, totalRecord);
        } else {
            recordStart = 0;
            recordEnd = totalRecord;
        }
        rs.absolute(recordStart);     
    }    
    
    protected void processCurrentPageAttribute(int t) throws Exception{
		isPOJO = true;    	
        totalRecord = t;
        if (totalRecord <= 0) {
            recordStart = 0;
            recordEnd = 0;            
            return ;
        }
        if (pageSize > 0) {
            totalPage = (totalRecord - 1) / pageSize + 1;
            if (currentPage < 1)
                currentPage = 1;
            if (currentPage > totalPage)
                currentPage = totalPage;

            //recordStart = pageSize * (currentPage - 1) + 1;
            recordStart = pageSize * (currentPage - 1);
            //recordEnd = Math.min(recordStart + pageSize - 1, totalRecord);
            recordEnd = Math.min(recordStart + pageSize, totalRecord);
        } else {
            recordStart = 0;
            recordEnd = totalRecord;
        }            
    }
    
    //------- End Of Pagging Control -------------
	/**
  	 * <br>
  	 * <br>目的：檢查是否有SQL Injection的威脅
  	 * <br>
  	*/
	public boolean isSQLInjection(){
		if ("true".equals(sqlInjectionFlag)) return true;
		else return false;
	}
	
	
	 //------- Start CRUD Control -------------
	
	//傳回Insert前之檢查SQL及message　
	protected String[][] getInsertCheckSQL(){String [][] rtnArray={{"","","",""}}; return rtnArray;}	
	//傳回Update前之檢查SQL及message　
	protected String[][] getUpdateCheckSQL(){String [][] rtnArray={{"","","",""}}; return rtnArray;}	
	//傳回Delete前之檢查SQL及message　
	protected String[][] getDeleteCheckSQL(){String [][] rtnArray={{"","","",""}}; return rtnArray;}
	
	/**
  	 * <br>
  	 * <br>目的：執行新增, 修改, 刪除前之邏輯檢查
  	 * <br>參數：傳入字串二維陣列, 允許多筆邏輯檢查
  	 * <br>　　　[0][0]:檢查之SQL語法, 查詢欄位只允許一個, 需加上as checkResult
  	 * <br>　　　[0][1]:七種條件式("EOF","=","!=",">",">=","<","<=")
  	 * <br>　　　[0][2]:條件值(需為數字字串,如條件式為EOF則不須輸入條件值)
  	 * <br>　　　[0][3]:錯誤訊息
  	 * <br>傳回：成功傳回true, 失敗傳回false
  	*/
	public boolean beforeExecCheck(String [][] checkSQLArray){
		boolean rtnBoolean=true;
		double checkResult=0;
		int condition=0;
		/**
		if ("true".equals(sqlInjectionFlag)){
			setErrorMsg("所有欄位均不允許輸入[單引號]及[雙引號]，請重新檢查！");
			if ("insert".equals(getState()) || "insertError".equals(getState())) {
				setStateInsertError();
			} else { 
				setStateUpdateError(); 
			}
			return false;
		}
		**/
		try {		
			for(int i=0; i<checkSQLArray.length; i++){
				if (checkSQLArray[i]!=null && !"".equals(checkSQLArray[i][0].toString())){
					Object o = View.getObject(checkSQLArray[i][0]);
					if (o!=null){
						try{
							checkResult=Common.getNumeric(o);
							condition=Integer.parseInt(checkSQLArray[i][2]);
						} catch (Exception e) {
							continue;
						}		
						if ("=".equals(checkSQLArray[i][1])){
							if (checkResult==condition)
								rtnBoolean=false;
						}else if ("!=".equals(checkSQLArray[i][1])){
							if (checkResult!=condition)
								rtnBoolean=false;						
						}else if (">".equals(checkSQLArray[i][1])){
							if (checkResult>condition)
								rtnBoolean=false;						
						}else if (">=".equals(checkSQLArray[i][1])){
							if (checkResult>=condition)
								rtnBoolean=false;						
						}else if ("<".equals(checkSQLArray[i][1])){
							if (checkResult<condition)
								rtnBoolean=false;						
						}else if ("<=".equals(checkSQLArray[i][1])){
							if (checkResult<=condition)
								rtnBoolean=false;						
						}						
					}else{
						if ("EOF".equals(checkSQLArray[i][1]))
							rtnBoolean=false;
					}
					if (!rtnBoolean){
						setErrorMsg(checkSQLArray[i][3]);
						break;
					}
				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnBoolean;
	}	
	
  	/**
  	 * <br>
  	 * <br>目的：新增儲存
  	 * <br>參數：無
  	 * <br>傳回：無
  	*/		
	public void insert(){
		try {
			if (!beforeExecCheck(getInsertCheckSQL())){
				setStateInsertError();
			}else{
				setEditDate(Datetime.getYYYMMDD());
				setEditTime(Datetime.getHHMMSS());
				doCreate();
				setStateInsertSuccess();
				setErrorMsg("新增完成");				
			}
		} catch (Exception e) {
			e.printStackTrace();
			setStateInsertError();
			if (e.getMessage()!=null && e.getMessage().length()<200) setErrorMsg(Common.escapeJavaScript(e.getMessage()));
			else setErrorMsg("新增失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");
		}
	}	
	
  	/**
  	 * <br>
  	 * <br>目的：更新
  	 * <br>參數：無
  	 * <br>傳回：無
  	*/		
	public void update(){
		try {
			if (!beforeExecCheck(getUpdateCheckSQL())){
				setStateUpdateError();
			}else{
				setEditDate(Datetime.getYYYMMDD());
				setEditTime(Datetime.getHHMMSS());			
				doUpdate();
				setStateUpdateSuccess();
				setErrorMsg("修改完成");					
			}			
		} catch (Exception e) {
			e.printStackTrace();
			setStateUpdateError();
			if (e.getMessage()!=null && e.getMessage().length()<200) setErrorMsg(StringEscapeUtils.escapeJavaScript(e.getMessage()));
			else setErrorMsg("發生未預期的錯誤,修改失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");			
		}
	}
	
  	/**
  	 * <br>
  	 * <br>目的：刪除
  	 * <br>參數：無
  	 * <br>傳回：無
  	*/		
	public void delete(){
		try {
			if (!beforeExecCheck(getDeleteCheckSQL())){
				setStateDeleteError();
			}else{
				setEditDate(Datetime.getYYYMMDD());
				setEditTime(Datetime.getHHMMSS());			
				doDelete();
				setStateDeleteSuccess();
				setErrorMsg("刪除完成");					
			}				
		} catch (Exception e) {
			e.printStackTrace();
			setStateDeleteError();
			if (e.getMessage()!=null && e.getMessage().length()<200) setErrorMsg(StringEscapeUtils.escapeJavaScript(e.getMessage()));
			else setErrorMsg("發生未預期的錯誤,刪除失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");			
		}
	}	
	
	
	public Object queryOne(){
		setStateQueryOneSuccess();
		try {
			return doQueryOne();
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage()!=null && e.getMessage().length()<300) setErrorMsg(Common.escapeJavaScript(e.getMessage()));
			else setErrorMsg("查詢失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");
			return null;
		}
	}	
	
	public Object queryAll(){
		setStateQueryAllSuccess();
		try {
			return doQueryAll();
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage()!=null && e.getMessage().length()<300) setErrorMsg(Common.escapeJavaScript(e.getMessage()));
			else setErrorMsg("查詢失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");
			return null;
		}
	}	
	
	HttpServletRequest request;
	public HttpServletRequest getRequest() {return request;}
	public void setRequest(HttpServletRequest request) {this.request = request;}
	
	
	public abstract Object doQueryOne() throws Exception;	
	public abstract Object doQueryAll() throws Exception;	
	public abstract void doCreate() throws Exception;	
	public abstract void doUpdate() throws Exception;	
	public abstract void doDelete() throws Exception;	
	//public abstract void doPrint(java.util.Map form) throws Exception;
    
}