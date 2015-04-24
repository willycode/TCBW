<!--
程式目的：
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="PROCOND0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.prcond.PROCOND0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) 
{
	obj = (com.kangdainfo.tcbw.view.prcond.PROCOND0101F)obj.queryOne();	
}
else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) 
{
	obj.insert();
	if ("insertSuccess".equals(obj.getState())) 
	{
		obj.setQueryAllFlag("true");
	}
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) 
{
	obj.update();
	if ("updateSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
	}
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
	if("deleteError".equals(obj.getState())){
		obj = (com.kangdainfo.tcbw.view.prcond.PROCOND0101F)obj.queryOne();
	}
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript">
var insertDefault = new Array
(
	new Array("isclose","N"),
	    Array("projType","drg")
);

function checkField()
{
	var alertStr = "";
	if(form1.state.value == "queryAll")
	{
		alertStr += checkQuery();
	}
	else if(form1.state.value=="insert" || form1.state.value=="insertError"||
			form1.state.value=="update" || form1.state.value=="updateError")
	{
		if(form1.state.value=="insert" || form1.state.value=="insertError")
		{
		   if(getUserInsert()!="")
		   {	   
		     alert(getUserInsert());
		     return false;
		   }
		}

		alertStr += checkEmpty(form1.projType,"專案類別");
		alertStr += checkEmpty(form1.projName,"專案名稱");
		alertStr += checkEmpty(form1.isclose,"是否結案");

		if(form1.projType.value=="drg")
		{
             if(form1.permitKeyDrg.value=="" && form1.applicationId.value=="" )
             {
            	 alertStr +="許可證字號與許可證持有商至少需填寫一個!";
             }
             
             if(form1.permitKeyDrg.value!="" && form1.applicationId.value!="" )
             {
            	 alertStr +="許可證字號與許可證持有商不得同時填寫!";
             } 
		}	
		else if(form1.projType.value=="med")
		{
			 if(form1.permitKeyMed.value=="" && form1.medSecCategory.value=="" )
             {
            	 alertStr +="許可證字號與醫材次類別至少需填寫一個!";
             }  

			 if(form1.permitKeyMed.value!="" && form1.medSecCategory.value!="" )
             {
            	 alertStr +="許可證字號與醫材次類別不得同時填寫!";
             }   
		}	
		
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}


function queryOne(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function init() 
{

	var hql = "Select id,subSystem from  CommonUser  where userId='"+"<%=User.getUserId()%>"+"'";

	var x = getRemoteData('../../ajax/jsonObjects.jsp',hql);

	if(x!=null && x.length>0)
	{
		var json = JSON.parse(x);
		var subSystem=json.obj1;

		if(subSystem=="")
		  form1.permissionField.value=1;
	}
	
	document.all.item("drg").style.display="none";
	$("#formContainer").find('.toolbar_default').attr('disabled',true);
	$("#formContainer").find('.toolbar_default').attr('readOnly',true);
	changKind();
	form1.btn_application.disabled=true;
	form1.btn_applicationClear.disabled=true;
}


function changKind()
{
	
  var kind=form1.projType.value;

  if(kind=="drg")
  {
	  form1.medSecCategory.disabled=true;
	  form1.medSecCategoryCode.disabled=true;
	  form1.medSecCategoryCodeName.disabled=true;
	        
	  form1.btn_medSecCategoryCode.disabled=true;

	  form1.applicationName.disabled=false;
	  form1.applicationId.disabled=false;
	  
	  form1.btn_application.disabled=false;
	  form1.btn_applicationClear.disabled=false;
	  
	  document.all.item("med").style.display="none";
	  document.all.item("drg").style.display="";
  }
  else if(kind=="med")
  {
	  form1.applicationName.disabled=true;
	  form1.applicationId.disabled=true;
	  
	  form1.btn_application.disabled=true;
	  form1.btn_applicationClear.disabled=true;
	  
	  form1.medSecCategory.disabled=false;
	  form1.medSecCategoryCode.disabled=false;
	  form1.medSecCategoryCodeName.disabled=false;
	  form1.btn_medSecCategoryCode.disabled=false;

	  document.all.item("drg").style.display="none";
	  document.all.item("med").style.display="";
  }	  	  

  
}

function q_changKind()
{
	
  var kind=form1.q_projType.value;

  if(kind=="drg")
  {
	  document.all.item("q_med").style.display="none";
	  document.all.item("q_drg").style.display="";
	  document.all.item("q_permitKeyMed").value="";
	  document.all.item("q_permitNoMed").value="";
  }
  else if(kind=="med")
  {
	  document.all.item("q_drg").style.display="none";
	  document.all.item("q_med").style.display="";
	  
	  document.all.item("q_permitKeyDrg").value="";
	  document.all.item("q_permitNoDrg").value="";
  }	  	  

  
}

function popCon1005(type)
{
	var jscript = "";		
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=650,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	if(type=='1')
	{	
	   returnWindow=window.open(getVirtualPath() + "home/popCon1005.jsp","",prop);
	}
	else if(type=='2')
	{
		form1.applicationId.value="";
		form1.applicationName.value="";
	}
}



function getUserUpdate()
{

	var hql = "Select id,subSystem from  CommonUser  where userId='"+"<%=User.getUserId()%>"+"'";

	var x = getRemoteData('../../ajax/jsonObjects.jsp',hql);

	if(x!=null && x.length>0)
	{
		
		var json = JSON.parse(x);
		var subSystem=json.obj1;
		var projType=form1.projType.value;

		if(subSystem.indexOf(projType.toUpperCase())==-1)
		{
			alert("您無法修改其他專案類別!");
			setAllReadonly();
			btnFollowPK();
			btnIQUD();
			return false;
		}	
		
	}

}

function getUserInsert()
{

	var hql = "Select id,subSystem from  CommonUser  where userId='"+"<%=User.getUserId()%>"+"'";

	var x = getRemoteData('../../ajax/jsonObjects.jsp',hql);

	if(x!=null && x.length>0)
	{
		var json = JSON.parse(x);
		var subSystem=json.obj1;
		var projType=form1.projType.value;

        if(subSystem=="")
          return "您無維護權限!";
		
		if(subSystem.indexOf(projType.toUpperCase())==-1)
		{
			return "您無法新增其他專案類別!";
		}	
		else
		{
			return "";
		}	
	}
	else
	{
		return "您無維護權限!";
    }	

}

var popWinName;

function permitDataQ()
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var medPermit = "";
	var medPermitNumber = "";

	medPermit = form1.permitKeyMed.value;
	medPermitNumber = form1.permitNoMed.value;

	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../medex/medex0107f.jsp?medPermit="+medPermit+"&medPermitNumber="+medPermitNumber,'popWinE',prop);		
}


function permitData2(id)
{
	
	var medPermit = id.substring(0,2);
	var medPermitNumber = id.substring(2,8);

	if(form1.projType.value=="med")
	{	
	   form1.permitKeyMed.value = medPermit;
	   form1.permitNoMed.value = medPermitNumber;

	   var q = "&medPermit="+medPermit;
           q += "&medPermitNumber="+medPermitNumber;
	
	   if(medPermit!="" && medPermitNumber!="")
	   {
	      x = getRemoteData("../../ajax/jsonMedPermitObjects.jsp",q );
	      if(x!=null && x.length>0)
	      {
		    var json1 = JSON.parse(x);
		    form1.chProduct.value = json1.obj0;  //中文品名 CHNAME
	      }
	       else
	      {
		    form1.permitKeyMed.value = "";
		    form1.permitNoMed.value = "";
		    form1.chProduct.value = "";
	      }	  
	    }
	}
	else
	{
		form1.permitKeyDrg.value = medPermit;
		form1.permitNoDrg.value = medPermitNumber;

		var q = "&permitKey="+medPermit;
            q += "&permitNo="+medPermitNumber;
		   
		x = getRemoteData("../../ajax/jsonDrgObjects.jsp", q );
		
		if(x!=null && x.length>0)
	    {
		    var json1 = JSON.parse(x);
		    form1.chProduct.value = json1.obj0;  //中文品名 CHNAME
	    }
	    else
	    {
		    form1.permitKeyDrg.value = "";
		    form1.permitNoDrg.value = "";
		    form1.chProduct.value = "";
	    }	  
	}	
}



function getChProduct()
{
    if(form1.projType.value=="med")
	{
    	var permitKeyMed=form1.permitKeyMed.value;
        var permitNoMed=form1.permitNoMed.value;
        
    	var q = "&medPermit="+permitKeyMed;
            q += "&medPermitNumber="+permitNoMed;

        form1.chProduct.value = "";
        	
	    if(permitKeyMed!="" && permitNoMed!="")
	    {
	       x = getRemoteData("../../ajax/jsonMedPermitObjects.jsp",q );
	       if(x!=null && x.length>0)
	       {
		     var json1 = JSON.parse(x);
		     form1.chProduct.value = json1.obj0;  //中文品名 CHNAME
	       }
	       else
	       {
		     form1.permitKeyMed.value = "";
		     form1.permitNoMed.value = "";
		     form1.chProduct.value = "";
	       }	 
	     }
	}
    else
    {
  
        form1.chProduct.value = "";
        
    	var permitKeyDrg=form1.permitKeyDrg.value;
        var permitNoDrg=form1.permitNoDrg.value;
        
    	var q = "&permitKey="+permitKeyDrg;
            q += "&permitNo="+permitNoDrg;

        if(permitKeyDrg!="" && permitNoDrg!="")
    	{
	       x = getRemoteData("../../ajax/jsonDrgObjects.jsp", q );
	
	       if(x!=null && x.length>0)
           {
	         var json1 = JSON.parse(x);
	         form1.chProduct.value = json1.obj0;  //中文品名 CHNAME
           }
           else
           {
	         form1.permitKeyDrg.value = "";
	         form1.permitNoDrg.value = "";
	         form1.chProduct.value = "";
           }	  
    	}
    }    
    
	
}

function permitDataDrgQ()
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	
	var permitKey = form1.permitKeyDrg.value;
	var permitNo = form1.permitNoDrg.value;
	
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../drgex/drgex0104f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',prop);		
}

var popWin = null;

function popReportForm(k)
{
	
	var maintainman=form1.maintainman.value;

	if(maintainman=="" &&  k==2)
	{
		alert("無可刪除維護人員!");
	    return false;
	}
	
	var params = 'width=800,height=600,resizable=1,menubar=no,scrollbars=yes';

	if (popWin!=null) popWin.close();
	
	popWin = window.open("prcond0102f.jsp?type="+k+"&userId="+maintainman,'popChefWin', params);
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:500px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td nowrap width="15%" class="queryTDLable">專案類別：</td>
		<td class="queryTDInput">
			<select class="field_Q" name="q_projType" onChange="q_changKind()">
			 <%=View.getTextOption("drg;藥品;med;醫療器材",obj.getQ_projType(),0)%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" class="queryTDLable">專案名稱：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" size="30" name="q_projName" value="<%=obj.getQ_projName()%>" />
		</td>
	</tr> 
	
	<tr id="q_med">
		<td nowrap class="queryTDLable">許可證字號：</td>
		<td nowrap class="queryTDInput" >
			<select name="q_permitKeyMed" class="field_Q">
			  <%=View.getOptionCodeKind("MEDPKID",obj.getQ_permitKeyMed())%>
			</select>
			字第<input class="field_Q" type="text" name="q_permitNoMed" size="10"  value="<%=obj.getQ_permitNoMed()%>">號
		</td>
	</tr>
	
	<tr id="q_drg">
		<td nowrap class="queryTDLable">許可證字號：</td>
		<td nowrap class="queryTDInput" >
			<select name="q_permitKeyDrg" class="field_Q">
			  <%=View.getOptionCodeKind("DRGPKID",obj.getQ_permitKeyDrg())%>
			</select>
			字第<input class="field_Q" type="text" name="q_permitNoDrg" size="10"  value="<%=obj.getQ_permitNoDrg()%>">號
		</td>
	</tr>
	
	<tr>
		<td nowrap class="queryTDLable">中文品名：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" size="30" name="q_chProduct" value="<%=obj.getQ_chProduct()%>" />
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">是否結案：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name="q_isclose">
			  <%=View.getYNOption(obj.getQ_isclose())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">維護人員：</td>
		<td nowrap class="queryTDInput" >
			<input class="field_Q" type="text" name="q_maintainman" value="<%=obj.getQ_maintainman()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="q_isQuery">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" onClick="form1.q_isQuery.value='Y'">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
	  <tr>
		<td nowrap width="25%" class="td_form"><font color="red">*</font>專案類別：</td>
		<td class="td_form_white" width="75%"  >
			<select class="field" name="projType" onChange="changKind();">
			 <%=View.getTextOption("drg;藥品;med;醫療器材" ,obj.getProjType(),0)%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>專案名稱：</td>
		<td class="td_form_white">
			<input class="field" type="text" size="50" maxlength="50" name="projName" value="<%=obj.getProjName()%>" />
		</td>
	</tr>

	<tr id="drg">
		<td nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white">
		    <select name="permitKeyDrg" class="field" onChange="getChProduct();">
				<%=View.getOptionCodeKind("DRGPKID",obj.getPermitKeyDrg())%>
			</select>
			字第<input class="field" type="text" name="permitNoDrg" onChange="getChProduct();" size="6" maxlength="6" value="<%=obj.getPermitNoDrg()%>">號
		    <input type="button" name="btnQryExpense" onClick="permitDataDrgQ();" value="查詢" width="120px" class="field" >
		</td>
	</tr>

	<tr id="med">
		<td nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white">
		    <select name="permitKeyMed" class="field" onChange="getChProduct();">
				<%=View.getOptionCodeKind("MEDPKID",obj.getPermitKeyMed())%>
			</select>
			字第<input class="field" type="text" onChange="getChProduct();" name="permitNoMed" size="6" maxlength="6" value="<%=obj.getPermitNoMed()%>">號
		      <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field" >
		</td>
	</tr>
	
	<tr>
		<td nowrap class="td_form">中文品名：</td>
		<td nowrap class="td_form_white">
			<input class="field_RO" type="text" size="50" maxlength="50" name="chProduct" value="<%=obj.getChProduct()%>" />
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證持有商：</td>
		<td nowrap class="td_form_white">
		    <input class="field_RO" type="text" size="10" name="applicationId" value="<%=obj.getApplicationId()%>" />
		    <input class="field_RO" type="text" size="40" maxlength="50" name="applicationName" value="<%=obj.getApplicationName()%>" />
		    <input name="btn_application" class="field" type="button" value="廠商資料" onclick="popCon1005('1');">
		    <input name="btn_applicationClear" class="field" type="button" value="清除廠商資料" onclick="popCon1005('2');">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">醫材次類別：</td>
		<td nowrap class="td_form_white">
		   <%=View.getPopCode("field","medSecCategoryCode","",Common.get(View.getOneCodeName("MEDSCT",Common.get(obj.getMedSecCategory()))),"","MEDSCT","medSecCategory",obj.getMedSecCategory())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>是否結案：</td>
		<td nowrap class="td_form_white">
			<select class="field" name="isclose">
				<%=View.getYNOption(obj.getIsclose())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>維護人員：</td>
		<td nowrap class="td_form_white">
		   <textarea class="field_RO" name="maintainmanName" cols="60" rows="4"><%=obj.getMaintainmanName()%></textarea>
          <input class="field" type="hidden" name="maintainman" size="70" value="<%=obj.getMaintainman()%>">	
          <br>
          <input class="toolbar_default" type="button" followPK="false" name="btnQuery" value="選擇" style="width:80px" onClick="popReportForm('1');">
          <input class="toolbar_default" type="button" followPK="false" name="btnQuery" value="刪除" style="width:80px" onClick="popReportForm('2');">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">異動人員/日期：</td>
		<td nowrap class="td_form_white" colspan="3">
		  	[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">/
		  	<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">]
		</td>
	</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="q_id" value="<%=obj.getQ_id()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">專案類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">專案名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">許可證持有商</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">醫材次類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">是否結案</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center", "center"};
	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td>
</tr>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)	
	{
	  case "insert":
		    $("#formContainer").find('.toolbar_default').attr('disabled',false);
			$("#formContainer").find('.toolbar_default').attr('readOnly',false);
		    changKind();
			break;
	  case "update":
		    getUserUpdate();
		    $("#formContainer").find('.toolbar_default').attr('disabled',false);
			$("#formContainer").find('.toolbar_default').attr('readOnly',false);
		    changKind();
			break;
	  case "queryAll":
			q_changKind();
			break;
	}
}


</script>
</body>
</html>