<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.pivot.PIVOT0901R">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
response.addHeader("Pragma", "No-cache");
response.addHeader("Cache-Control", "no-cache");

if ("queryAll".equals(obj.getState())) {	
	if ("false".equals(obj.getQueryAllFlag())){ obj.setQueryAllFlag("true"); }
}	
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>     
<html>
<head>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

function init()
{ 
  if("queryAllSuccess"!=form1.state.value){
	  document.getElementById("queryShow").style.display = 'none';
  }
}

function queryOne(){
	var arg = arguments.length;
	if(arguments[arg-1]!=-1){
		if(arg==1){
			queryOne1(arguments[0]);
		}else if(arg==3){
			queryOne2(arguments[0],arguments[1],arguments[2]);
		}
	}
}
function queryOne1(q1){	
	form1.queryId1.value = q1;
	subQuery();		
}
function queryOne2(q1,q2,q3){	
	form1.queryId1.value = q1;
	form1.queryId2.value = q2;
	form1.queryId3.value = q3;
	subQuery();	
}
function subQuery(){
	form1.action = "pivot0901r2.jsp";
	form1.state.value = "queryData";
	openPostWindow('健康食品及膠囊錠狀食品非預期反應通報統計資料',"1200","600");
	form1.target = "健康食品及膠囊錠狀食品非預期反應通報統計資料";
	beforeSubmit();
	form1.submit();	
	form1.action = "pivot0901r.jsp";
	form1.target = "_self";
}

</script>
</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" autocomplete="off" >
<br>
<table cellspacing="0" cellpadding="0" width="100%" class="bg">
<tr>
<td>
	<table border="1" width="100%" class="table_form">
	    <tr>
			<td colspan="2" style="text-align:center" class="td_form"></td>
		</tr>
		<tr>
			<td class="td_form" width="30%">資料年月：</td>
			<td class="td_form_white">
			    <input class="field_Q" name="q_dateS" type="text" size="5" maxlength="5" value="<%=obj.getQ_dateS()%>" />~
			    <input class="field_Q" name="q_dateE" type="text" size="5" maxlength="5" value="<%=obj.getQ_dateE()%>"/>
			    (請輸入民國年月，例如：10301~10312)
			</td>
		</tr>
	    <tr>
			<td class="td_form">許可證字號：</td>
			<td class="td_form_white">
				<select name="q_permitKey" class="field">
				<%=View.getOptionCodeKind("HFRPKID", obj.getQ_permitKey())%>
			</select>	
				<input class="field_Q" name="q_permitNoG" size="8" maxlength="8" value="<%=obj.getQ_permitNoG()%>">
			</td>
		</tr>	
		<tr>
			<td class="td_form">核備字號：</td>
			<td class="td_form_white">
				<input class="field_Q" name="q_permitNoN" size="25" maxlength="25" value="<%=obj.getQ_permitNoN()%>">
			</td>
		</tr>	
		<tr>
			<td class="td_form">品名：</td>
			<td class="td_form_white">
				<input class="field_Q" name="q_productName" size="25" maxlength="25" value="<%=obj.getQ_productName()%>">
			</td>
		</tr>
		<tr>
			<td class="td_form">統計類別：</td>
			<td class="td_form_white">
			    <select class="field" name="q_staType" id="q_staType">
			    <%=View.getTextOption("01;依通報來源;02;依通報者職稱;03;依食用者性別;04;依食用者年齡;05;依非預期反應結果;"+
			    		  "06;依通報案件縣市別;07;依通報品項;08;依食品類別;09;依非預期反應分類;10;依非預期反應原因;"+
			    		  "11;依證據性 ;12;依嚴重程度 ;13;依行政處置層級 ;",obj.getQ_staType(),1) %>
			    </select>
			</td>
		</tr>	
		<tr>
			<td class="queryTDInput" colspan="2" style="text-align:center;">
			    <input class="toolbar_default" type="button" name="query" id="query" value="查　詢" />
				<input class="toolbar_default" type="button" name="print" id="print" value="產製報表"  />
			</td>
		</tr>	
	</table>
	</td>
	</tr>
</table>

<!--List區============================================================-->
<br>
<table id="queryShow"  width="50%" class="bg" cellspacing="0" cellpadding="0" style="height:auto">
<tr><td class="bgList">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0" style="height:auto">
	<thead id="listTHEAD">
	<tr>
		<%
		   if("".equals(obj.getQ_staType())){
			    //do nothing
		   }else if("07".equals(obj.getQ_staType())){
			    out.write("<th class='listTH' style='text-align:center'>"+obj.getReports(obj.getQ_staType())[1]+"</th>");
			    out.write("<th class='listTH' style='text-align:center'>"+obj.getReports(obj.getQ_staType())[2]+"</th>");
				out.write("<th class='listTH' style='text-align:center'>"+obj.getReports(obj.getQ_staType())[3]+"</th>");
		   }else {
			 	out.write("<th class='listTH' style='text-align:center'>"+obj.getReports(obj.getQ_staType())[1]+"</th>");
				out.write("<th class='listTH' style='text-align:center'>"+obj.getReports(obj.getQ_staType())[2]+"</th>");
		   }
		%>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    if("".equals(obj.getQ_staType())){
	         //do nothing
        }else if("07".equals(obj.getQ_staType())){
	    	boolean primaryArray[] = {true,true,true,false,false,false};
	    	boolean displayArray[] = {false,false,false,true,true,true};
	    	String[] alignArray = {"center","center","center","center","center","center"};
	    	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),false));
        }else {
        	boolean primaryArray[] = {true,false,false};
        	boolean displayArray[] = {false,true,true};
        	String[] alignArray = {"center","center","center"};
        	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),false));
        }	
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>

<input type="hidden" name="state" value="<%=obj.getState()%>"/>
<input type="hidden" name="queryId1" id="queryId1"/>
<input type="hidden" name="queryId2" id="queryId2"/>
<input type="hidden" name="queryId3" id="queryId3"/>
</form>
  <script type="text/javascript">
	$(document).ready(function()
	{
		$("#print").click(function()
		{

			var alertStr = "";

			alertStr+=checkNumber(form1.q_dateS,"資料年月(起)");
			alertStr+=checkNumber(form1.q_dateE,"資料年月(迄)");
			
			if(alertStr.length!=0){ alert(alertStr); return false; }
			
			form1.action = "pivot0901p.jsp" ;
			form1.target = "_blank";
			beforeSubmit();
			form1.submit();
			form1.action = "pivot0901r.jsp";
			form1.target = "_self";
		});
		$("#query").click(function()
		{
			var alertStr = "";
			alertStr+=checkNumber(form1.q_dateS,"資料年月(起)");
			alertStr+=checkNumber(form1.q_dateE,"資料年月(迄)");
			alertStr += checkEmpty(form1.q_staType,"統計類別");
			
			if(alertStr.length!=0){ alert(alertStr); return false; }	
			form1.state.value="queryAll";
			beforeSubmit();
			form1.submit();
		});
	});
</script>
</body>
</html>