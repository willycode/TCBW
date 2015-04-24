<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	

StringBuilder sb = new StringBuilder();

String prodCountry= Common.get(request.getParameter("abroadCountry"));

 obj.setQueryAllFlag("true");
 
 objList = (java.util.ArrayList) obj.queryAll_prodCountry();

%>

<html>
<head>
<title>公告對象輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/cbToggle.js"></script>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript">

function init()
{
	var Str = "<%=prodCountry%>";
    var myArray = Str.split(",");    
    for (var i = 0; i < myArray.length; i++) 
    {
        for(var j=0; j < form1.fds.length ; j++)
    	{	     	
        	var b = form1.fds[i];
        	var countryCode = b.value.substring(0,2);
            if(countryCode == myArray[i])
            	b.checked=true;   
    	} 
    }
}


function setProdCountry()
{	
    var c = form1.fds.length;
    var abroadCountry="";
    var countryCName="";
    var j = 0;
    for(var i=0 ; i < c; i++)
    {	     	
	   var b = form1.fds[i];	   
	   if( b.checked )
       {
		   j++;
		   if("" != b.value && b.value != null){		  
			   abroadCountry += b.value.substring(0,2)+",";			  
			   countryCName+= b.value.substring(0,2)+"-"+b.value.substring(3);
			   if(j%4==0) countryCName +="\n";
			   else countryCName += ", ";
		   }		  		            
	   }	   
    }

    opener.document.all.item("abroadCountry").value = abroadCountry.substr(0,abroadCountry.length-1);    
    opener.document.all.item("abroadCountryName").value = countryCName;

	window.close();
}

</script>
</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" >
<table width="100%" cellspacing="0" cellpadding="0"> 
<!--Form區============================================================-->
<tr>
<td class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
	<tr>
		<td class="queryTDLable" width="15%" >國別代碼：</td>
		<td class="queryTDInput" width="85%">
		    <input class="field_Q" type="text" size="50" name="popCode" value="<%=obj.getPopCode()%>" />
		</td>
	</tr>
    <tr>
		<td class="queryTDLable">國別名稱：</td>
		<td class="queryTDInput">
		     <input class="field_Q" type="text" size="50" name="popCodeName" value="<%=obj.getPopCodeName()%>" />
		</td>
	</tr>
	<tr>
	    <td class="queryTDInput" style="text-align:center" colspan="3">
	        <input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="查　　詢" >
	        <input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
        </td>
    </tr>
	</table>
	</div>
</td>
</tr>

<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<br><br>
</td></tr>

<tr><td class="bg" style="text-align:left">
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="選 　　擇" onClick="setProdCountry()">
</td></tr>

<!--List區============================================================-->
<tr><td class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>	       
	    <th class="listTH"><input type="checkbox" name="cbAll"></th>
	    <th class="listTH"><a class="text_link_w" >序號</a></th>	 	    
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">國別代碼</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">國別名稱</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	  boolean primaryArray[] = {false,false,true,true};
	  boolean displayArray[] = {false,true,true,true};
	  String[] alignArray = {"center","center","center","center"};
	  boolean linkArray[] = {false,false,false,false};
	  out.write(View.getCheckboxQuerylist(primaryArray, displayArray, alignArray, objList, "true", "fds", linkArray, "_blank"));
	%>  
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
<script type="text/javascript">	
var cb = new cbToggle('cb',document.form1,form1.cbAll,'fds');
cb.config.cssTopLevel = true;
</script>
</body>
</html>
