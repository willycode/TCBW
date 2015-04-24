<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.prcond.PROCOND0102F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	

StringBuilder sb = new StringBuilder();

String userId=Common.get(request.getParameter("userId"));
String type=Common.get(request.getParameter("type"));

obj.setUserId(userId);
obj.setType(type);


if (!"".equals(obj.getUserName())) 
{	
	sb.append("if isObj(opener.document.all.item(\"").append(obj.getUserName()).append("\")){\n");
	sb.append("\topener.document.all.item(\"").append(obj.getUserName()).append("\").value=name;\n");		
	sb.append("}\n");
}

if (!"".equals(obj.getUserId())) 
{	
	sb.append("if isObj(opener.document.all.item(\"").append(obj.getUserId()).append("\")){\n");
	sb.append("\topener.document.all.item(\"").append(obj.getUserId()).append("\").value=id;\n");		
	sb.append("}\n");
}

if (!"".equals(obj.getJs())) sb.append("\nopener." ).append( obj.getJs() ).append( ";\n");

 obj.setQueryAllFlag("true");
 
 objList = (java.util.ArrayList) obj.queryAll();

%>

<html>
<head>
<title>公告對象輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript">





function init()
{

}


function setShortCode()
{	
    var c=form1.fds.length;

    var userId="";
    var userName="";
 

    for(var i=1; i<=c; i++)
    {	     	
	   var b=document.getElementById("fds["+i+"]");
	   
	   if( b.checked )
       {	  
		  userId+= document.getElementById("fds["+i+"]").value+",";

		  var hql= "select userName from CommonUser where userId='"+ document.getElementById("fds["+i+"]").value+"'";

	      var x = getRemoteData("../../ajax/jsonObjects.jsp", hql);

          if (x!=null && x.length>0) 
	      {
			  var json = JSON.parse(x);	
			  userName+= json.obj0+"\n"; 
		  }    
      
	   }
    }
   
    
    if('<%=type%>'=="1")
    {
       if(opener.document.all.item("maintainman").value!="")
         opener.document.all.item("maintainman").value+=userId.substr(0,userId.length-1)+",";
       else
         opener.document.all.item("maintainman").value+=userId;
    }
    else if('<%=type%>'=="2")
    {
       opener.document.all.item("maintainman").value=userId;
    }	
    
    
    if('<%=type%>'=="1")
    {
      if(opener.document.all.item("maintainmanName").value!="")  
	    opener.document.all.item("maintainmanName").value+=userName;	
	  else
        opener.document.all.item("maintainmanName").value+=userName;
    }
    else if('<%=type%>'=="2")
    {
      opener.document.all.item("maintainmanName").value=userName;
    }	
	
	
	window.close();
}

function checkField()
{
	
	beforeSubmit();
	return true;
}

</script>
</head>
<body >
<form id="form1" name="form1" method="post" onSubmit="return checkField()">

<input type="hidden" name="popId" value="<%=obj.getPopId()%>">
<input type="hidden" name="popCode" value="<%=obj.getPopCode()%>">
<input type="hidden" name="popCodeName" value="<%=obj.getPopCodeName()%>">
<input type="hidden" name="js" value="<%=obj.getJs()%>">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->

<%if("1".equals(type)){ %>
<tr>
<td class="bg" >
	<div id="formContainer" style="height:60px">
	<table class="table_form" width="100%" height="100%">	
	<tr>
		<td class="queryTDLable" width="15%" >帳號：</td>
		<td class="queryTDInput" width="85%">
		    <input class="field_Q" type="text" size="50" name="q_userId" value="<%=obj.getQ_userId()%>" />
		</td>
	</tr>
    <tr>
		<td class="queryTDLable">姓名：</td>
		<td class="queryTDInput">
		     <input class="field_Q" type="text" size="50" name="q_userName" value="<%=obj.getQ_userName()%>" />
		</td>
	</tr>
	</table>
	</div>
</td>
</tr>

<!--Toolbar區============================================================-->

<tr><td class="bg" style="text-align:center">
	<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
</td></tr>
 
<%}%>

<tr><td>
<% request.setAttribute("QueryBean",obj);%>
</td></tr>

<tr><td class="bg" style="text-align:left">
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="選 　　擇" onClick="setShortCode()">
</td></tr>

<!--List區============================================================-->
<tr><td class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
	    <th class="listTH" ><a class="text_link_w" >序號</a></th>
	    <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">勾選</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">帳號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">姓名</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	  boolean primaryArray[] = {false,true,false};
	  boolean displayArray[] = {false,true,true};
	  String[] alignArray = {"center","left","left"};
	  boolean linkArray[] = {false,false,false,false};
	  out.write(View.getCheckboxQuerylist_no(primaryArray,displayArray,alignArray,objList,"true","fds", linkArray, "_blank","",-1,false,false,true,type));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>
