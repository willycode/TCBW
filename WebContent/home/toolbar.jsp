<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="head.jsp" %>
<%
String progID = Common.get(request.getParameter("progID"));
String authBit = "";
if (User==null || UserRole==null) {
	return;
} else {
	if (UserRole.isUserLevel()) {		
		int auth = 1;		
		if ("".equals(progID)){
			auth = Common.getInt(session.getAttribute("PAU"));
			if (auth==0) auth = 1;
		} else {
			if (User.getPermissionMap()!=null && User.getPermissionMap().size()>0 && Common.getLong(progID)>0) {	
				CommonDtree dt = ServiceGetter.getInstance().getMyServletContext().getFunctionMap().get(progID);
				if (dt==null) {
					dt = (CommonDtree) ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getNode(Common.getLong(progID));
					if (dt!=null) {
						ServiceGetter.getInstance().getMyServletContext().getFunctionMap().put(dt.getId().toString(), dt);
					}
				}
				if (dt!=null && !"".equals(Common.get(dt.getBtnRead()))) {
					Object[] o = User.getPermissionMap().get(Common.get(dt.getBtnRead()));
					if (o!=null) {
						auth = Common.getInt(o[0]);
					}
				}
			}			
		}		
		/**
		if ("".equals(progID)){
			auth = Common.getInt(session.getAttribute("PAU"));
			if (auth==0) auth = 1;
		} else if (User.getCommonAuth()!=null) {		
			for (int i=0; i<User.getCommonAuth().length; i++) {
				CommonAuth authObj = User.getCommonAuth()[i];
				if (Common.get(authObj.getCommonDtree().getId()).equals(progID)) {
					auth = authObj.getAuth();
					session.setAttribute("PAU",auth);
					break;
				}
			}
			//CommonAuth authObj = (CommonAuth) ServiceGetter.getInstance().getCommonService().getCommonAuthDao().getCommonAuth(UserRole.getCommonGroup().getId().toString(), progID);
			//CommonAuth authObj = (CommonAuth) ServiceGetter.getInstance().getCommonService().getCommonAuthDao().getCommonAuth(UserRole, progID);
			//if (authObj!=null) auth = authObj.getAuth();			
			//else auth = 1;						
		}	
		**/
		authBit = String.valueOf(auth);
	}
}
%>
<script type="text/javascript">
var readbg="#FFFFFF";
var editbg="#FFFFFF";
var hasBeenConfirmed = false;
var clearFormFieldFlag = true;
var listToHTMLReportFlag = true;
var listHiddenFlag = false;

var localButtonFireListener = new Object();
localButtonFireListener.whatButtonFireEvent = function(buttonName){
    // do nothing,for override.
};

localButtonFireListener.beforeWhatButtonFireEvent = function(buttonName){
    return true;
    // do nothing,for override.
};

function getFocusField() {	
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (!like(obj.className,"field_Q") && !like(obj.className,"RO") && like(obj.className,"field")){
			if ((obj.value=="" && obj.type!="hidden") && (obj.readOnly==false||obj.disabled==false)) {
				try { obj.focus(); } catch(e) {}
				break;
			}				
		}
		/**
		if (obj.className=="field"||obj.className=="field_P"||obj.className=="field_N"||obj.className=="field_C"||obj.className=="field_E") {		
			if ((obj.value=="" && obj.type!="hidden") && (obj.readOnly==false||obj.disabled==false)) {
				try { obj.focus(); } catch(e) {}
				break;
			}				
		}
		**/
	}	
}

/*全部欄位都設為readonly,  除query欄位及所有資料*/
function setAllReadonly(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"field_Q")){	
			if (like(obj.className,"RO")){	
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){									
	    	    	obj.readOnly = true;	
				}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					
					obj.disabled = true;
			   	}
			 }
		}else{			
			if (like(obj.className,"RO")){			
				//obj.readOnly = true;	
											
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){
								
    	    		obj.readOnly = true;	
				}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
   			  		obj.disabled = true;
   	  			}				
			}else{
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){						
								
    	    		obj.readOnly = true;	
				}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
   			  		obj.disabled = true;
   	  			}
   	  		}
   	  	}
	}
}

/*全部欄位都設為readonly並清空, 除query欄位外所有資料*/
function setAllReadonlyClear(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"field_Q")){	
			if (like(obj.className,"RO")){	
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){									
	    	    	obj.readOnly = true;	
				}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					
					obj.disabled = true;
			   	}
			 }
		}else{	
			if (like(obj.className,"RO")){
				if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){		   	  	
   			  		obj.disabled = true;
   	  			}				
					
				obj.readOnly = true;
				obj.value="";	
			}else{	
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){						
								
        			obj.readOnly = true;	
        			obj.value="";
				}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")) {
															
					obj.disabled = true;
					obj.value = "";
				}else if((obj.type=="checkbox")||(obj.type=="radio")){
					obj.disabled = true;
					obj.checked  = false;
		   	  	}else if((obj.type=="button")&&(obj.className!="toolbar_default")){
   			  		obj.disabled = true;
   	  			}
   	  		}
      	}
	}
}

/*全部欄位都設為open*/
function setAllOpen(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"RO")){
			
			if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				obj.disabled = true;
	   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
  				obj.disabled = true;
  	  		} else {
					
				obj.readOnly = true;
			}
		}else{		
			if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){					
							
	        	obj.readOnly = false;			
			}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				
				obj.disabled = false;
    	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
	    	  	obj.disabled = false;
    	  	}
   	  	}
	}
}

/*全部欄位都設為open並清空, 除query欄位外所有資料*/
function setAllOpenClear(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (!like(obj.className,"field_Q")){			
			if (like(obj.className,"RO")){			
				
				if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
	  				obj.disabled = true;
	  	  		} else {
						
					obj.readOnly = true;
				}
    	    	obj.value="";
			}else{		
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){					
								
		        	obj.readOnly = false;			
		        	obj.value="";
				}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")){
					
					obj.disabled = false;
					obj.value="";
				}else if((obj.type=="checkbox")||(obj.type=="radio")){
					obj.disabled = false;
					obj.checked  = false;
	   		  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
	    		  	obj.disabled = false;
	    		//為了使用單位與非機關使用單位二擇一時清除hidden欄位值
				}else if((obj.type=="hidden")&&(obj.className=="field")){
					obj.value="";
				}
			}
      	}
	}
}

/*除PK欄位外全部欄位都設為open*/
function setUnpkOpen(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"RO")){
						
			if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				obj.disabled = true;
	   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
  				obj.disabled = true;
  	  		} else {	
					
				obj.readOnly = true;
			}
		}else{		
			if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){
				if (like(obj.className,"field_P") || like(obj.className,"field_EP")){
								
	   	    		obj.readOnly = true;
				}else{
								
	    	    	obj.readOnly = false;
				}			
			}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				if (like(obj.className,"field_P") || like(obj.className,"field_RO") || like(obj.className,"field_QRO")){
					
					obj.disabled = true;			
				}else{
					
					obj.disabled = false;	
				}	
	      	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
				if (like(obj.className,"field_P")){
					obj.disabled = true;			
				}else{
					obj.disabled = false;	
				}    	  	
      		}
		}
	}
}

/*submit之前將select打開*/
function beforeSubmit(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")) {		
			obj.disabled = false;
		} else if(obj.type=="text") {
			if ((like(obj.className,"field_N") || like(obj.className,"field_QN"))) deleteCommas(obj);
			if ((like(obj.className,"field_C") || like(obj.className,"field_QC"))) deleteChr(obj,'.');
			if ((like(obj.className,"field_Q") || like(obj.className,"field_X"))) obj.disabled = false;
		}
	}
	document.all.item("confirm").disabled = true;
}
/*顯示查詢視窗*/
function queryShow(queryName){
	var queryObj=document.all.item(queryName);		
	var objHeight= queryObj.style.height;
	var objWidth= queryObj.style.width;	
	objHeight= objHeight.substring(0,objHeight.length-2);
	objWidth= objWidth.substring(0,objWidth.length-2);
	/**
  	document.all.item("insert").disabled = true;   
  	document.all.item("queryAll").disabled = true;
  	if (!document.all.item("update").disabled){
   		document.all.item("update").disabled = true;   
  		document.all.item("delete").disabled = true; 	
  	}
  	**/
	queryObj.style.top=(document.body.clientHeight-Number(objHeight)-80)/2;
	queryObj.style.left=(document.body.clientWidth-Number(objWidth))/2;	
	queryObj.style.display="block";  	
}
/*隱藏查詢視窗*/
function queryHidden(queryName){
	var queryObj=document.all.item(queryName);		
	queryObj.style.display="none";
	/**
  	document.all.item("insert").disabled = false;   
  	document.all.item("queryAll").disabled = false; 	
  	btnFollowPK();
  	**/
}

/*按鈕隨著PK值顯示*/
function btnFollowPK(){  	
  	var PKValueflag = false;
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (obj.className=="field_P" && obj.value!="" && !PKValueflag && obj.type!="button"){
			PKValueflag = true;
			break;
		}			
   	}
   	
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if ((obj.type=="button")&&(obj.className=="toolbar_default" )){	
			if (PKValueflag){
				if (obj.followPK=="true" || obj.getAttribute('followPK')=='true') {obj.disabled = false; }				
			}else{
				if (obj.followPK=="true" || obj.getAttribute('followPK')=='true'){ obj.disabled = true; }
			}
		}					
   	}   	
   	
}

/*顯示新增,查詢按鈕*/
function btnIQ(){
	if (isObj(document.all.item("btnDuplicate"))) document.all.item("btnDuplicate").disabled = false;
	if (isObj(document.all.item("insert"))) document.all.item("insert").disabled = false;  	 
	if (isObj(document.all.item("queryAll"))) document.all.item("queryAll").disabled = false; 
	if (isObj(document.all.item("update"))) document.all.item("update").disabled = true; 
	if (isObj(document.all.item("delete"))) document.all.item("delete").disabled = true; 
	if (isObj(document.all.item("clear"))) document.all.item("clear").disabled = true;  
	if (isObj(document.all.item("confirm"))) document.all.item("confirm").disabled = true;	
  	if (isObj(document.all.item("listPrint"))) document.all.item("listPrint").disabled = false;
  	if (isObj(document.all.item("listHidden"))) document.all.item("listHidden").disabled = false; 	  	
  	/**
  	if (isObj(document.all.item("nextInsert"))) document.all.item("nextInsert").disabled = true;  	
  	document.all.item("insert").className = "toolbar_image_insert";    	 
  	document.all.item("queryAll").className = "toolbar_image_query";
  	document.all.item("update").className = "toolbar_image_update_disable";
  	document.all.item("delete").className = "toolbar_image_delete_disable";
  	document.all.item("clear").className = "toolbar_image_cancel_disable";  
  	document.all.item("confirm").className = "toolbar_image_confirm_disable";  	
  	if (isObj(document.all.item("nextInsert"))) document.all.item("nextInsert").className = "toolbar_image_nextInsert_disable";
  	**/
}
/*顯示取消,確定按鈕*/
function btnCC(){
	if (isObj(document.all.item("btnDuplicate"))) document.all.item("btnDuplicate").disabled = true;
	if (isObj(document.all.item("insert"))) document.all.item("insert").disabled = true; 
	if (isObj(document.all.item("queryAll"))) document.all.item("queryAll").disabled = true; 
	if (isObj(document.all.item("update"))) document.all.item("update").disabled = true; 
	if (isObj(document.all.item("delete"))) document.all.item("delete").disabled = true; 
	if (isObj(document.all.item("clear"))) document.all.item("clear").disabled = false;  
	if (isObj(document.all.item("confirm"))) document.all.item("confirm").disabled = false;	
  	if (isObj(document.all.item("listPrint"))) document.all.item("listPrint").disabled = false;
  	if (isObj(document.all.item("listHidden"))) document.all.item("listHidden").disabled = false;
  	
  	/**
  	if (isObj(document.all.item("nextInsert"))) document.all.item("nextInsert").disabled = false;
  	document.all.item("insert").className = "toolbar_image_insert_disable";    	 
  	document.all.item("queryAll").className = "toolbar_image_query_disable";
  	document.all.item("update").className = "toolbar_image_update_disable";
  	document.all.item("delete").className = "toolbar_image_delete_disable";
  	document.all.item("clear").className = "toolbar_image_cancel";  
  	document.all.item("confirm").className = "toolbar_image_confirm";  	
  	if (isObj(document.all.item("nextInsert"))) document.all.item("nextInsert").className = "toolbar_image_nextInsert";
  	**/
}
/*顯示新增,查詢,修改,刪除按鈕*/
function btnIQUD(){
	if (isObj(document.all.item("btnDuplicate"))) document.all.item("btnDuplicate").disabled = false;
	if (isObj(document.all.item("insert"))) document.all.item("insert").disabled = false;   
	if (isObj(document.all.item("queryAll"))) document.all.item("queryAll").disabled = false; 
	if (isObj(document.all.item("update"))) document.all.item("update").disabled = false; 
	if (isObj(document.all.item("delete"))) document.all.item("delete").disabled = false; 
	if (isObj(document.all.item("clear"))) document.all.item("clear").disabled = true;  
	if (isObj(document.all.item("confirm"))) document.all.item("confirm").disabled = true;
  	if (isObj(document.all.item("listPrint"))) document.all.item("listPrint").disabled = false;
  	if (isObj(document.all.item("listHidden"))) document.all.item("listHidden").disabled = false;  
  	
  	/**
  	if (isObj(document.all.item("nextInsert")))document.all.item("nextInsert").disabled = true;
  	document.all.item("insert").className = "toolbar_image_insert";    	 
  	document.all.item("queryAll").className = "toolbar_image_query";
  	document.all.item("update").className = "toolbar_image_update";
  	document.all.item("delete").className = "toolbar_image_delete";
  	document.all.item("clear").className = "toolbar_image_cancel_disable";  
  	document.all.item("confirm").className = "toolbar_image_confirm_disable";  	
  	if (isObj(document.all.item("nextInsert"))) document.all.item("nextInsert").className = "toolbar_image_nextInsert";  	
  	**/
}

/*觸發不同Button之相對應動作*/
function whatButtonFireEvent(buttonName){
	var oldState = "";

	if(!localButtonFireListener.beforeWhatButtonFireEvent(buttonName)){
	   return;
	}
	oldState = document.all("state").value;
	document.all("state").value=buttonName;	
	switch (buttonName)	{
		case "init":
			setAllReadonly();
			btnFollowPK();
			btnIQ();
			break;
		case "insert":
			if (clearFormFieldFlag) setAllOpenClear();
			else setAllOpen();
			if (isObj(insertDefault)){
				for(var i=0;i<insertDefault.length;i++){
					if (isObj(document.all.item(insertDefault[i][0])) && isObj(document.all.item(insertDefault[i][0]).type)){					
						if((document.all.item(insertDefault[i][0]).type=="checkbox")){
							if (insertDefault[i][1]==document.all.item(insertDefault[i][0]).value)
								document.all.item(insertDefault[i][0]).checked  = true;
	   		  			}else{
			    		  	document.all.item(insertDefault[i][0]).value=insertDefault[i][1];
				   	  	}					
					} else if (isObj(document.all.item(insertDefault[i][0]))) { //radio box
						for (var j = 0; j < document.all.item(insertDefault[i][0]).length; j++) {
							if (document.all.item(insertDefault[i][0])[j].value==insertDefault[i][1]) document.all.item(insertDefault[i][0])[j].checked = true;
						}
					}					
				}
			}				
			if (isObj(form1.editID)) form1.editID.value = "<%=User.getUserId()%>";
			if (isObj(form1.editDate)) form1.editDate.value = getToday();
			btnFollowPK();
			btnCC();
			getFocusField();		
			break;
		case "insertSuccess":
			setAllReadonly();
			btnFollowPK();
			btnIQUD();
			break;
		case "insertError":
			setAllOpen();
			//setUnpkOpen();
			btnFollowPK();
			btnCC();
			break;
		case "update":
			if (isObj(form1.editID)) form1.editID.value = "<%=User.getUserId()%>";
			if (isObj(form1.editDate)) form1.editDate.value = getToday();
			setUnpkOpen();
			btnFollowPK();
			btnCC();
			break;
		case "updateSuccess":
			setAllReadonly();
			btnFollowPK();
			btnIQUD();
			break;
		case "updateError":
			setUnpkOpen();
			btnFollowPK();
			btnCC();
			break;		
		case "delete":
			if (hasBeenConfirmed) {
				beforeSubmit();	
				//genJSONString();
				document.forms[0].submit();
			} else {
				if(confirm("確定刪除這筆資料?")){
					beforeSubmit();	
					//genJSONString();
					document.forms[0].submit();
				}
			}
			break;
		case "deleteSuccess":
			setAllReadonlyClear();
			btnFollowPK();
			btnIQ();
			break;
		case "deleteError":
			setAllReadonly();
			btnFollowPK();
			btnIQUD();
			break;					
		case "queryAll":
			queryShow('queryContainer');
			break;
		case "queryCannel":
			queryHidden('queryContainer');
			break;	
		case "queryAllSuccess":
			setAllReadonlyClear();
			btnFollowPK();
			btnIQ();
			break;		
		case "queryOneSuccess":
			setAllReadonly();
			btnFollowPK();
			btnIQUD();
			break;
		case "clear":
			if (oldState=="update" || oldState=="updateError") {
				document.all("state").value="queryOne";
				beforeSubmit();				
				document.forms[0].submit();				
				//setAllReadonly();
			} else {				
				setAllReadonlyClear();
			}
			btnFollowPK();
			btnIQ();
			break;	
		default:
			break;
	}
	localButtonFireListener.whatButtonFireEvent(buttonName); 	
}

/*模仿sql like功能*/
function like(buttonName,likestr){
	return (buttonName.indexOf(likestr)>=0);
}
/*檢查查詢欄位至少輸入一個*/
function checkQuery(){
	var errorflag=true;
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){
		obj = arrObj[i];	
		if (like(obj.className,"field_Q")){
			if((obj.type=="checkbox")||(obj.type=="radio")){
				if (obj.checked){
					errorflag=false;
					break;				
				}
			}else{
				if ((parse(obj.value)!="") && (obj.type!="button")){
					errorflag=false;
					break;
				}
			}
		}	
	}
	if (errorflag){
		return "查詢欄位至少輸入一個!\n";
	}else{
		return "";
	}
}


/*列表隱藏*/
function listToHidden(btnObj,formName,listName){
	var formObj=document.all.item(formName);
	var listObj=document.all.item(listName);
	if (btnObj.value=="列表隱藏"){
		formObj.style.height="auto";
		listObj.style.display="none";
		btnObj.value="列表顯示";
	}else{
		formObj.style.height="220px";
		listObj.style.display="block";
		btnObj.value="列表隱藏";	
	}
}

function listToHTMLReportWithColor(listHeadName,listBodyName) {
	window.open('../../home/listReport.htm');
	listToHTMLReportFlag = true;
}


function listToExcelReport(listHeadName,listBodyName) 
{ 
	var i=0, j=0;
	if (listToHTMLReportFlag) 
	{
		var sb = new StringBuffer();
		sb.append("<table border='0' cellpadding='0' cellspacing='0' bgcolor='#000000' width='100%'><tr><td nowrap><table width='100%' border='0' cellpadding='1' cellspacing='1'>");

        //寫入Thead資料
		var listHead = document.getElementById(listHeadName);	
		for(i=0; i<listHead.rows.length; i++)
		{
			sb.append("<tr bgcolor='#CCCCCC' align='center'>");
			for(j=0; j<listHead.rows[i].cells.length; j++)
			{			
				if (isObj(listHead.rows[i].cells[j].childNodes[0].childNodes[0]))
				{
					sb.append("<td nowrap>").append(listHead.rows[i].cells[j].childNodes[0].childNodes[0].nodeValue).append("</td>");
				}
				else
				{
					sb.append("<td nowrap>&nbsp;</td>");
				}
			}
			sb.append("</tr>");
		}
		
		//寫入Tbody資料	
		var listBody = document.getElementById(listBodyName);	

		for(i=0; i<listBody.rows.length; i++)
		{
			sb.append("<tr bgcolor='#FFFFFF' align='center'>");	

			for(j=0; j<listBody.rows[i].cells.length; j++)
			{
				textAlign = "";

				if (isObj(listBody.rows[i].cells[j].childNodes[0]))
				{
					try 
					{			
						textAlign = listBody.rows[i].cells[j].style.getAttribute('textAlign');
					} catch(e) {}					

					if (textAlign!=null && textAlign!="") 
						sb.append("<td nowrap style=\"text-align:").append(textAlign).append("\">").append(listBody.rows[i].cells[j].innerText).append("</td>");
					else 
						sb.append("<td nowrap>").append(listBody.rows[i].cells[j].innerText).append("</td>");
				}
				else
				{
					sb.append("<td nowrap>&nbsp;</td>");
				}
			}
			sb.append("</tr>");
		}
		sb.append("</table></td></tr></table>");

		form1.excelValue.value=sb.toString();
		
		var url="";
		url+="../../home/reportXLS.jsp";
		form1.target = "_blank";
		form1.action =url;
		beforeSubmit();
		form1.submit();
		form1.target = "_self";
		form1.action = ""

	}
}
	 


/*列表檔案*/
function listToHTMLReport(listHeadName,listBodyName){
	var i=0, j=0;
	if (listToHTMLReportFlag) {
		var sb = new StringBuffer();
		sb.append("<table border='0' cellpadding='0' cellspacing='0' bgcolor='#000000' width='100%'><tr><td nowrap><table width='100%' border='0' cellpadding='1' cellspacing='1'>");
		//寫入Thead資料
		var listHead = document.getElementById(listHeadName);	
		for(i=0; i<listHead.rows.length; i++){
			sb.append("<tr bgcolor='#CCCCCC' align='center'>");
			for(j=0; j<listHead.rows[i].cells.length; j++){			
				if (isObj(listHead.rows[i].cells[j].childNodes[0].childNodes[0])){
					sb.append("<td nowrap>").append(listHead.rows[i].cells[j].childNodes[0].childNodes[0].nodeValue).append("</td>");
				}else{
					sb.append("<td nowrap>&nbsp;</td>");
				}
			}
			sb.append("</tr>");
		}
		
		//寫入Tbody資料	
		var listBody = document.getElementById(listBodyName);	
		for(i=0; i<listBody.rows.length; i++){
			sb.append("<tr bgcolor='#FFFFFF' align='center'>");	
			for(j=0; j<listBody.rows[i].cells.length; j++){
				textAlign = "";
				if (isObj(listBody.rows[i].cells[j].childNodes[0])){
					try {			
						textAlign = listBody.rows[i].cells[j].style.getAttribute('textAlign');
					} catch(e) {}					
					if (textAlign!=null && textAlign!="") sb.append("<td nowrap style=\"text-align:").append(textAlign).append("\">").append(listBody.rows[i].cells[j].childNodes[0].nodeValue).append("</td>");
					else sb.append("<td nowrap>").append(listBody.rows[i].cells[j].childNodes[0].nodeValue).append("</td>");
				}else{
					sb.append("<td nowrap>&nbsp;</td>");
				}
			}
			sb.append("</tr>");
		}
		sb.append("</table></td></tr></table>");
		var ie = window.open();
		ie.document.write(sb.toString());
		listToHTMLReportFlag = false;
	} else listToHTMLReportWithColor(listHeadName,listBodyName);
}

function btnQueryAllOnly() {
	setDisplayItem('spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
}

var leavePageConfirm = true;
function setBeforePageUnload(on){
    window.onbeforeunload = (on) ? confirmLeavePage : null;
    //window.onunload = (on) ? confirmLeavePage : null;
}

function confirmLeavePage(e) {
	if (isObj(document.all.item("state"))) {
		var s = document.all.item("state").value;
		if (s=="insert" || s=="insertError" || s=="update" || s=="updateError") {
			return "資料尚未儲存，確定要離開?";
		}
	}
}
/*開啟只有class=field_U的欄位*/
function setUpdateOnlyOpen(){
	setAllReadonly();
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"field_U")){
			obj.disabled = false;
			obj.removeAttribute('readonly',0);
		}
	}
}




setBeforePageUnload(true);
//window.onbeforeunload = confirmLeavePage
</script>
<input type='hidden' name='permissionField' value='<%=authBit%>'>
<input type="hidden" name="excelValue">
<span id="spanToolbar">
<span id="spanInsert">
<input class="toolbar_default" type="button" followPK="false" name="insert"  value="新　增" onClick="whatButtonFireEvent(this.name)">&nbsp;
</span>
<% if("Y".equalsIgnoreCase(Common.get(request.getParameter("btnCopy")))){ %>
<span id="spanDuplicate">
<input class="toolbar_default" type="button" followPK="false" name="btnDuplicate" value="複製新增" onClick="clearFormFieldFlag=false;whatButtonFireEvent('insert');">&nbsp;
</span><%}%>
<span id="spanQueryAll">
<input class="toolbar_default" type="button" followPK="false" name="queryAll" value="查　詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
</span>
<span id="spanUpdate">
<input class="toolbar_default" type="button" followPK="true"  name="update" value="修　改" onClick="whatButtonFireEvent(this.name)">&nbsp;
</span>
<span id="spanDelete">
<input class="toolbar_default" type="button" followPK="true"  name="delete" value="刪　除" onClick="whatButtonFireEvent(this.name)">&nbsp;
</span>
<span id="spanClear">
<input class="toolbar_default" type="button" followPK="false" name="clear" value="取　消" onClick="whatButtonFireEvent(this.name)">&nbsp;
</span>
<span id="spanConfirm">
<input class="toolbar_default" type="submit" followPK="false" name="confirm" value="確　定" onClick="setBeforePageUnload(false);">&nbsp;
</span>
<span id="spanListPrint" Style="display:none;">
<input class="toolbar_default" type="button" followPK="false" name="listPrint" value="列表檔案" onClick="listToHTMLReport('listTHEAD','listTBODY')">&nbsp;
</span>
<span id="spanListHidden">
<input class="toolbar_default" type="button" followPK="false" name="listHidden" value="列表隱藏" onClick="listToHidden(this,'formContainer','listContainer')">&nbsp;
</span>
</span>