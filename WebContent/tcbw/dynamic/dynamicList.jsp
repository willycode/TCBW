<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.dynamic.DYNAMICLIST">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
request.setCharacterEncoding("UTF-8");

String kind = Common.get(request.getParameter("kind"));

obj.setQ_kind(kind);

if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}

objList = (java.util.ArrayList) obj.queryAll();

%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/cbToggle.js"></script>
<script type="text/javascript" src="../../js/toolbar.js"></script>
<script type="text/javascript">

function checkField(type,content,code)
{
	if(type == "confirm")
	{
		if (opener!=null) 
		{
			if (form1.id.value == "") 
			{
				alert("請先點選一筆資料!\n");
			}
			else
			{

				opener.setAllClearQ();
				
                var str="";
                
                if(code.length >0)
                	str=code.split(":");

                for(var i=0;i<str.length;i++)
                {
                	if (isObj(opener.document.all.item(str[i])))
                	  opener.document.all.item(str[i]).checked= true;
                }    

                var con="";
                
                if(content.length >0)
                	con=content.split(":");

                for(var i=0;i<con.length;i=i+3)
                {
                    if(con[i]=="text")
                    {    
                	   if (isObj(opener.document.all.item(con[i+1])))
                  	       opener.document.all.item(con[i+1]).value=con[i+2];
                    }
                    else  if(con[i]=="radio")
                    {
                       if (isObj(opener.document.all.item(con[i+1])))
                       {
                    	   var column=opener.document.all.item(con[i+1]);
                    	   for(var j=0;j<column.length;j++)
                   		   {
                   			 if(con[i+2]==column[j].value)
                   			 {
                   				column[j].checked= true;
                       		 }	 
                   		   }
                       }    
                    }
                    else  if(con[i]=="checkbox")
                    {

                       if (isObj(opener.document.all.item(con[i+1])))
                       {
                      
                    	  var chk="";
                    	  var column=opener.document.all.item(con[i+1]);
                    	  var columnValue=con[i+2];

                          if(columnValue.length >0)
                        	 chk=columnValue.split("、");

                    	  for(var x=0;x<chk.length;x++)
                          {
                    		  for(var j=0;j<column.length;j++)
                      		  {
                    			
                    			  
                      			 if(chk[x]==column[j].value)
                      			 {
                      				column[j].checked= true;
                          		 }	 
                      		  }
                          }
                          
                       }    
                    }

                    
                }
	
				window.close();
			}
		}
	}
	else
	{
		form1.state.value = type;
		var alertStr = "";
		if(alertStr.length!=0){ alert(alertStr); return false; }
		form1.submit();
		return true;
	}
}


function queryOne(id,a,b,c,content,code) 
{
	form1.id.value = id;
	checkField('confirm',content,code);
}

function init(){
	
}

</script>
</head>
<body onload="init();">
<form id="form1" name="form1" method="post" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0" style="width:auto;height:auto;">
	<tr>
		<td nowrap class="queryTDLable" width="30%">常用變式名稱：</td>
		<td nowrap class="queryTDInput" width="70%">
			<input class="field_Q" type="text" name="q_name" size="100"  value="<%=obj.getQ_name()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">常用變式名稱說明：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_explain" size="100"  value="<%=obj.getQ_explain()%>">
	    </td>
	</tr>
</table>	

<table width="100%" cellspacing="0" cellpadding="0">
<tr>
<td class="bg" align="left">
<span id="spanConfirm">
<input type="hidden" name="state" value="<%=obj.getState()%>">
<input type="hidden" name="id" value="<%=obj.getId()%>">
<input type="hidden" id="listContainerActiveRowId">
<input class="toolbar_default" type="button" followPK="false" name="queryAll" value="查　詢" onClick="checkField(this.name);">&nbsp;
<input class="toolbar_default" type="button" followPK="false" name="confirm" value="確　定" onClick="checkField(this.name);">&nbsp;
</span>
</td>
</tr>
<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
<tr><td class="bg">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="tbl">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">說明</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	   boolean primaryArray[] = {true,false,false,true,true};
	   boolean displayArray[] = {false,true,true,false,false};
	   out.write(View.getQuerylist(primaryArray,displayArray,null,objList,"",true,null,"","",false,false,false,"",false,false,0));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>