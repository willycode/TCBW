<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="VCOS0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vcos.VCOS0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String formType = Common.get(request.getParameter("formType"));
obj.setFormType(formType);
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.vcos.VCOS0301F)obj.queryOne();	
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
}
obj.setQueryAllFlag("true"); 

if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
function init() {
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";	
}
function checkField(){
	var alertStr = "";
	form1.state.value="queryAll";

	if(form1.state.value == "queryAll")
	{
		alertStr += checkQuery();		
	}	
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}
function queryOne(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";
	form1.action = "vcos0100f.jsp";
	beforeSubmit();
	form1.submit();
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
	<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
		<table width="100%" cellspacing="0" cellpadding="0">
			<!--Toolbar區============================================================-->
			<tr><td nowrap class="bgToolbar" style="text-align:left">
			    <input type="hidden" name="id" value="<%=obj.getQ_id()%>">	
				<input type="hidden" name="state" value="<%=obj.getState()%>">
				<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
				<input type="hidden" name="userID" value="<%=User.getUserId()%>">
				<input type="hidden" name="userName" value="<%=User.getUserName()%>">
				<input type="hidden" name="formType" value="<%=obj.getFormType()%>">
				<input class="toolbar_default" type="submit" followPK="false" name="drgin0101q_queryAll" value="查　詢">&nbsp;
				<span id="spanQueryClear">
					<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
				</span>
				<span id="spanListToExcel">
			        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
			    </span>
			    <jsp:include page="../../home/toolbar.jsp" />
			</td></tr>
			<tr>
				<td nowrap class="bg">
					<div id="formContainer" style="height:auto">
						<!--Form區============================================================-->
							<table class="table_form queryTable" width="100%" style="height:auto">
								<tr>
									<td nowrap width="15%" nowrap class="td_form" >案件編號：</td>
									<td class="td_form_white"  colspan="3">
										<input class="field_Q" type="text" name="q_applNoS" id="q_applNoS" size="12" maxlength="12" value="<%=obj.getQ_applNoS()%>">~		
										<input class="field_Q" type="text" name="q_applNoE" id="q_applNoE" size="12" maxlength="12" value="<%=obj.getQ_applNoE()%>">
									</td>			
								</tr>
								<tr>
									<td nowrap width="15%" nowrap class="td_form">品名：</td>
									<td nowrap class="td_form_white" colspan="3">
										<input class="field_Q" type="text"  name="q_chProduct" id="q_chProduct" size="12" maxlength="12"  value="<%=obj.getQ_chProduct()%>">
									</td>			
								</tr>		  
								<tr>			
									<td nowrap width="15%" nowrap class="td_form">資料收集日期：</td>
									<td nowrap nowrap class="td_form_white"> 
									<%=View.getPopCalendar("field_Q","q_dataRevDateS",obj.getQ_dataRevDateS())%>~
									<%=View.getPopCalendar("field_Q","q_dataRevDateE",obj.getQ_dataRevDateE())%>				
									</td>
									<td nowrap class="td_form">發佈單位：</td>
									<td nowrap class="td_form_white" >
										<%=View.getPopCode("field_Q","q_publDept",obj.getQ_publDept(),obj.getQ_publDeptName(),"","CONPUBLDEPT","q_publDeptCodeId",obj.getQ_publDeptCodeId())%>
									</td>
								</tr>
								<tr>
								   <td nowrap class="td_form">化粧品項目：</td>
									<td nowrap class="td_form_white" >
						                <select class="field_Q" name="q_ingredient" id="q_ingredient">
											<%=View.getOptionCodeKind("CCI", obj.getQ_ingredient())%>
										</select>
									</td>
									<td nowrap class="td_form">發佈日期：</td>
									<td nowrap nowrap class="td_form_white"> 
									<%=View.getPopCalendar("field_Q","q_publDateS",obj.getQ_publDateS())%>~
									<%=View.getPopCalendar("field_Q","q_publDateE",obj.getQ_publDateE())%>				
									</td>			
								</tr>
								<tr>
									<td nowrap class="td_form">產品是否進口：</td>
									<td nowrap class="td_form_white">
									<%=View.getRadioBoxTextOption("field_Q","q_isImport","Y;是;N;否",obj.getQ_isImport())%>
									</td>				
									<td nowrap width="15%" nowrap class="td_form">燈號：</td>
									<td nowrap class="td_form_white" colspan="3">
											<%=View.getCommonCodeKindBoxOption("field_Q","q_lamp","CONLAMP",obj.getQ_lamp())%>				
									</td>			
								</tr>	
						</table>							
					</div>
				</td></tr>
						<!-- List區================================================================-->
							<tr><td nowrap class="bgPagging">
								<% request.setAttribute("QueryBean",obj);%>
						<!--<jsp:include page="../../home/page.jsp" />-->
							</td></tr>
							<tr>
									<td nowrap class="bgList">										
										<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
											<thead id="listTHEAD">
												<tr>
														<th class="listTH"><a class="text_link_w" >NO.</a></th>
														<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
														<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">品名</a></th>
														<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">資料收集日期</a></th>
														<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">化粧品項目</a></th>
														<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">狀況</a></th>
											            <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">發佈日期</a></th>
											            <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">訊息主題</a></th>  
											            <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">明細</a></th>           
												</tr>
											</thead>
														<tbody id="listTBODY">
														<%
														boolean primaryArray[] = {true,false,false,false,false,false,false,false};
														boolean displayArray[] = {false,true,true,true,true,true,true,true};
														String[] alignArray  =  {"center","center","left","center","center","center","center","center"};
														out.write(View.getQueryDiscolorList(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", true,"vcos0100f.jsp","&formType=vcos0301","",1));														
														%>
														</tbody>
										</table>										
							</td></tr>
				</table>		
		</form>						
</body>
</html>