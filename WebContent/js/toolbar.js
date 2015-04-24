var readbg="#FFFFFF";
var editbg="#FFFFFF";
var hasBeenConfirmed = false;
var listToHTMLReportFlag = true;

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
		if (obj.className=="field"||obj.className=="field_P") {		
			if ((obj.value=="" && obj.type!="hidden") && (obj.readOnly==false||obj.disabled==false)) {
				obj.focus();
				break;
			}				
		}
	}

}

function setAllReadonly(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"field_Q")){	
			if (like(obj.className,"RO")){	
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){									
	    	    	obj.readOnly = true;	
				}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.style.backgroundColor=readbg;
					obj.disabled = true;
			   	}
			 }
		}else{			
			if (like(obj.className,"RO")){			
				//obj.readOnly = true;	
				obj.style.backgroundColor=readbg;							
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){
					obj.style.backgroundColor=editbg;			
    	    		obj.readOnly = true;	
				}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
   			  		obj.disabled = true;
   	  			}				
			}else{
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){						
					obj.style.backgroundColor=readbg;			
    	    		obj.readOnly = true;	
				}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.style.backgroundColor=readbg;
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
   			  		obj.disabled = true;
   	  			}
   	  		}
   	  	}
	}
}


function setAllReadonlyClear(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"field_Q")){	
			if (like(obj.className,"RO")){	
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){									
	    	    	obj.readOnly = true;	
				}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.style.backgroundColor=readbg;
					obj.disabled = true;
			   	}
			 }
		}else{	
			if (like(obj.className,"RO")){
				if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.style.backgroundColor=readbg;
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){		   	  	
   			  		obj.disabled = true;
   	  			}				
				obj.style.backgroundColor=editbg;	
				obj.readOnly = true;
				obj.value="";	
			}else{	
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){						
					obj.style.backgroundColor=readbg;			
        			obj.readOnly = true;	
        			obj.value="";
				}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.style.backgroundColor=readbg;
					obj.disabled = true;
					obj.checked  = false;
					if(obj.type=="select") obj.value="";				
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
   			  		obj.disabled = true;
   	  			}
   	  		}
      	}
	}
}


function setAllOpen(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"RO")){
			obj.style.backgroundColor=readbg;
			if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				obj.disabled = true;
	   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
  				obj.disabled = true;
  	  		} else {
				obj.style.backgroundColor=editbg;	
				obj.readOnly = true;
			}
		}else{		
			if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){					
				obj.style.backgroundColor=editbg;			
	        	obj.readOnly = false;			
			}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				obj.style.backgroundColor=editbg;
				obj.disabled = false;
    	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
	    	  	obj.disabled = false;
    	  	}
   	  	}
	}
}


function setAllOpenClear(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (!like(obj.className,"field_Q")){			
			if (like(obj.className,"RO")){			
				obj.style.backgroundColor=readbg;
				if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
	  				obj.disabled = true;
	  	  		} else {
					obj.style.backgroundColor=editbg;	
					obj.readOnly = true;
				}
    	    	obj.value="";
			}else{		
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){					
					obj.style.backgroundColor=editbg;			
		        	obj.readOnly = false;			
		        	obj.value="";
				}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.style.backgroundColor=editbg;
					obj.disabled = false;
					obj.checked  = false;
					if(obj.type=="select") obj.value="";				
	   		  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
	    		  	obj.disabled = false;
	    		
				}else if((obj.type=="hidden")&&(obj.className=="field")){
					obj.value="";
				}
			}
      	}
	}
}


function setUnpkOpen(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (like(obj.className,"RO")){
			obj.style.backgroundColor=readbg;			
			if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				obj.disabled = true;
	   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
  				obj.disabled = true;
  	  		} else {	
				obj.style.backgroundColor=editbg;	
				obj.readOnly = true;
			}
		}else{		
			if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){
				if (like(obj.className,"field_P")){
					obj.style.backgroundColor=readbg;			
	   	    		obj.readOnly = true;
				}else{
					obj.style.backgroundColor=editbg;			
	    	    	obj.readOnly = false;
				}			
			}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				if (like(obj.className,"field_P") || like(obj.className,"field_RO") || like(obj.className,"field_QRO")){
					obj.style.backgroundColor=readbg;
					obj.disabled = true;			
				}else{
					obj.style.backgroundColor=editbg;
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

function beforeSubmit(){
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")) {		
			obj.disabled = false;
		} else if(obj.type=="text") {
			if ((like(obj.className,"field_N") || like(obj.className,"field_QN"))) deleteCommas(obj);
		}
	}	
}


function queryShow(queryName){
	if (isObj(document.all.item(queryName))) {
		var queryObj=document.all.item(queryName);		
		var objHeight= queryObj.style.height;
		var objWidth= queryObj.style.width;	
		objHeight= objHeight.substring(0,objHeight.length-2);
		objWidth= objWidth.substring(0,objWidth.length-2);
		queryObj.style.top=(document.body.clientHeight-Number(objHeight)-80)/2;
		queryObj.style.left=(document.body.clientWidth-Number(objWidth))/2;	
		queryObj.style.display="block"; 		
	} 	
}

function queryHidden(queryName){
	if (isObj(document.all.item(queryName))) {
		var queryObj=document.all.item(queryName);		
		queryObj.style.display="none";		
	}
}


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
				if (obj.followPK=="true" || obj.getAttribute('followPK')=='true'){obj.disabled = false; }				
			}else{
				if (obj.followPK=="true" || obj.getAttribute('followPK')=='true'){ obj.disabled = true; }
			}
		}					
   	}   	
   	
}


function btnIQ(){
	if (isObj(document.all.item("insert"))) document.all.item("insert").disabled = false;  	 
	if (isObj(document.all.item("queryAll"))) document.all.item("queryAll").disabled = false; 
	if (isObj(document.all.item("update"))) document.all.item("update").disabled = true; 
	if (isObj(document.all.item("delete"))) document.all.item("delete").disabled = true; 
	if (isObj(document.all.item("clear"))) document.all.item("clear").disabled = true;  
	if (isObj(document.all.item("confirm"))) document.all.item("confirm").disabled = true;

  	if (isObj(document.all.item("listPrint"))) document.all.item("listPrint").disabled = false;
  	if (isObj(document.all.item("listHidden"))) document.all.item("listHidden").disabled = false;  	

	if (isObj(document.all.item("preview"))) document.all.item("preview").disabled = true;
	if (isObj(document.all.item("cancel"))) document.all.item("cancel").disabled = true;
}


function btnCC(){
	if (isObj(document.all.item("insert"))) document.all.item("insert").disabled = true; 
	if (isObj(document.all.item("queryAll"))) document.all.item("queryAll").disabled = true; 
	if (isObj(document.all.item("update"))) document.all.item("update").disabled = true; 
	if (isObj(document.all.item("delete"))) document.all.item("delete").disabled = true; 
	if (isObj(document.all.item("clear"))) document.all.item("clear").disabled = false;  
	if (isObj(document.all.item("confirm"))) document.all.item("confirm").disabled = false;

  	if (isObj(document.all.item("listPrint"))) document.all.item("listPrint").disabled = false;
  	if (isObj(document.all.item("listHidden"))) document.all.item("listHidden").disabled = false;

  	if (isObj(document.all.item("preview"))) document.all.item("preview").disabled = false;
  	if (isObj(document.all.item("cancel"))) document.all.item("cancel").disabled = false;
}

function btnIQUD(){
	if (isObj(document.all.item("insert")))			document.all.item("insert").disabled = false;   
	if (isObj(document.all.item("queryAll")))		document.all.item("queryAll").disabled = false; 
	if (isObj(document.all.item("update")))			document.all.item("update").disabled = false; 
	if (isObj(document.all.item("delete")))			document.all.item("delete").disabled = false; 
	if (isObj(document.all.item("clear")))			document.all.item("clear").disabled = true;  
	if (isObj(document.all.item("confirm")))		document.all.item("confirm").disabled = true;
	
  	if (isObj(document.all.item("listPrint")))		document.all.item("listPrint").disabled = false;
  	if (isObj(document.all.item("listHidden")))		document.all.item("listHidden").disabled = false;  	

  	if (isObj(document.all.item("preview")))		document.all.item("preview").disabled = false;
  	if (isObj(document.all.item("cancel")))			document.all.item("cancel").disabled = false;
}

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
			setAllOpenClear();
			if (isObj(insertDefault)){
				for(var i=0;i<insertDefault.length;i++){
					if (isObj(document.all.item(insertDefault[i][0]))){					
						if((document.all.item(insertDefault[i][0]).type=="checkbox")||(document.all.item(insertDefault[i][0]).type=="radio")){
							document.all.item(insertDefault[i][0]).checked  = insertDefault[i][1];
	   		  			}else{
			    		  	document.all.item(insertDefault[i][0]).value=insertDefault[i][1];
				   	  	}					
					}					
				}
			}			
			if (isObj(form1.editID)) form1.editID.value = "";
			if (isObj(form1.editDate)) form1.editDate.value = "";
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
			if (isObj(form1.editID)) form1.editID.value = "";
			if (isObj(form1.editDate)) form1.editDate.value = "";
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
				document.forms[0].submit();
			} else {
				if(confirm("確定刪除這筆資料?")){
					beforeSubmit();	
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
			if (oldState=="update") setAllReadonly();
			else setAllReadonlyClear();
			btnFollowPK();
			btnIQ();
			break;	
		default:
			break;
	}
	localButtonFireListener.whatButtonFireEvent(buttonName); 	
}


function like(buttonName,likestr){
	return (buttonName.indexOf(likestr)>=0);
}

function checkQuery(){
	var errorflag=true;
	var arrObj = document.forms[0].elements;
	var arrLen = arrObj.length;		
	for(var i=0; i<arrLen; i++){  			
		var obj = arrObj[i];
		if (obj.className=="field_PQ" || obj.className=="field_Q" || obj.className=="field_QE" || obj.className=="field_QRO"){	
			if((obj.type=="checkbox")||(obj.type=="radio")){
				if (obj.checked){
					errorflag=false;
					break;				
				}
			}else{
				if ((obj.value!="") && (obj.type!="button")){
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

function listToHidden(btnObj,formName,listName){
	var formObj=document.all.item(formName);
	var listObj=document.all.item(listName);
	if (btnObj.value=="列表隱藏"){
		formObj.style.height="405px";
		listObj.style.display="none";
		btnObj.value="列表顯示";
	}else{
		formObj.style.height="220px";
		listObj.style.display="block";
		btnObj.value="列表隱藏";	
	}
}

function listToHTMLReportWithColor(listHeadName,listBodyName) {
	window.open("home/listReport.htm");
	listToHTMLReportFlag = true;
}

function listToHTMLReport(listHeadName,listBodyName){
	if (listToHTMLReportFlag) {
		var i=0, j=0;
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
			sb.append("<tr bgcolor='#FFFFFF'>");	
			for(j=0; j<listBody.rows[i].cells.length; j++){
				if (isObj(listBody.rows[i].cells[j].childNodes[0])){
					sb.append("<td nowrap>").append(listBody.rows[i].cells[j].childNodes[0].nodeValue).append("</td>");
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

function setBeforePageUnload(on){
	//Do Nothing..
}