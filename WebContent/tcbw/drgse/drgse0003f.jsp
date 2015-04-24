<!--
程式目的：藥品療效不等案件分級設定作業
程式代號：drgse0003f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGSE0003" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgse.DRGSE0003F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.drgse.DRGSE0003F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
	if ("insertSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
		obj.setQ_id(obj.getId());
	}
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	if ("updateSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
	}
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
	if("deleteError".equals(obj.getState())){
		obj = (com.kangdainfo.tcbw.view.drgse.DRGSE0003F)obj.queryOne();
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
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField(){
	var alertStr = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.category, "類別");
		if((form1.category.value == "PHY")){
			alertStr += checkEmpty(form1.nti, "NTI");
			alertStr += checkEmpty(form1.phyyear, "時間區間")
			alertStr += checkEmpty(form1.lotNum, "批號");
			alertStr += checkEmpty(form1.phynum, "件數");
		}
		if((form1.category.value == "ADV")){
			alertStr += checkEmpty(form1.advLevel, "反應等級");
			alertStr += checkEmpty(form1.advyear, "時間區間")
			alertStr += checkEmpty(form1.advnum, "件數");
		}
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(id){
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}
function changeForm(a){
	if(a.value == "PHY"){
		phyType.style.display="block";
		advType.style.display="none";
	}else if(a.value == "ADV"){
		phyType.style.display="none";
		advType.style.display="block";
	}else{
		phyType.style.display="none";
		advType.style.display="none";
	}
}

function changeQuery(a){
	if(a.value == "PHY"){
		Q_phyType.style.display="block";
		Q_advType.style.display="none";
	}else if(a.value == "ADV"){
		Q_phyType.style.display="none";
		Q_advType.style.display="block";
	}else{
		Q_phyType.style.display="none";
		Q_advType.style.display="none";
	}
}

function init() {
	if('<%=obj.getCategory()%>' == "PHY"){
		phyType.style.display="block";
	}else if ('<%=obj.getCategory()%>' == "ADV"){
		advType.style.display="block";
	}
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:500px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗 
	<table class="queryTable" border="1" style="height:50px">
	<tr>
		<td nowrap class="queryTDLable">類別：</td>
		<td nowrap class="queryTDInput">
			<select name = "q_category" class="field_Q" type="select" onchange="changeQuery(this);"> 
					<%=View.getTextOption("PHY;藥效改變(同產品);ADV;不良反應(同產品);", obj.getQ_category(), 1)%>
			</select>
	    </td>
	</tr>
	</table>
	<div id="Q_phyType" style="display:none">
	<table class="queryTable">
		<tr>
	        <td nowrap class="queryTDLable"><font color="red">*</font>NTI：</td>
			<td nowrap class="queryTDInput">
				<select name="q_nti" class="field_Q">
					<%=View.getTextOption("Y;是;N;否;", obj.getQ_nti(), 1)%>
				</select>
			</td>
		</tr>	
		
		<tr>
	        <td nowrap class="queryTDLable"><font color="red">*</font>時間區間：</td>
			<td nowrap class="queryTDInput">
				<input type="text" name="q_phyyear" class="field_Q" size="2" maxlength="2" value="<%=obj.getQ_phyyear()%>">年
			</td>
		</tr>	
		
		<tr>
	        <td nowrap class="queryTDLable"><font color="red">*</font>批號：</td>
			<td nowrap class="queryTDInput">
				<select name = "q_lotNum" class="field_Q" type="select">
					<%=View.getTextOption("Y;同批號;N;不同批號;", obj.getQ_lotNum(), 1)%>
				</select>
			</td>
			</tr>
		<tr>
	        <td nowrap class="queryTDLable"><font color="red">*</font>件數：</td>
			<td nowrap class="queryTDInput">
				<input type="text" name="q_phynum"  class="field_Q" size="6" maxlength="6" value="<%=obj.getQ_phynum()%>">件
			</td>
		</tr>
	</table>
	</div>
	<div id="Q_advType" style="display:none">
		<table class="queryTable">
		<tr>
	        <td nowrap class="queryTDLable"><font color="red">*</font>不良反應：</td>
			<td nowrap class="queryTDInput">
				<select name="q_advLevel" class="field_Q" type="select">
    				<%=View.getOptionCodeKind("DRG0308", obj.getQ_advLevel())%>
    			</select>
			</td>
		</tr>
		<tr>
	        <td nowrap class="queryTDLable"><font color="red">*</font>時間區間：</td>
			<td nowrap class="queryTDInput">
				<input type="text" name="q_advyear" class="field_Q" size="2" maxlength="2" value="<%=obj.getQ_advyear()%>">年
			</td>
		</tr>
		<tr>
	        <td nowrap class="queryTDLable"><font color="red">*</font>件數：</td>
			<td nowrap class="queryTDInput">
				<input type="text" name="q_advnum" class="field_Q" size="6" maxlength="6" value="<%=obj.getQ_advnum()%>">件
			</td>
		</tr>				
	</table>
	</div>

	<table class="queryTable">
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="q_id" value="<%=obj.getQ_id()%>">
			<input type="hidden" name="q_isQuery">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" onClick="form1.q_isQuery.value='Y'">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
	</div>
</div>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="99%" height="100%">
	<tr>
        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>類別：</td>
		<td nowrap class="td_form_white"  width="50%" >
			<select name = "category" onchange="changeForm(this);"> 
					<%=View.getTextOption("PHY;藥效改變(同產品);ADV;不良反應(同產品);", obj.getCategory(), 1)%>
			</select>
		</td>
	</tr>
	</table>
	<div id="phyType" style="display:none">
		<table class="table_form" width="99%" height="100%">
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>NTI：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name="nti" class="field">
						<%=View.getTextOption("Y;是;N;否;", obj.getNti(), 1)%>
					</select>
				</td>
			</tr>	
			
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>時間區間：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<input type="text" name="phyyear" size="60" maxlength="50" value="<%=obj.getPhyyear()%>">年
				</td>
			</tr>	
			
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>批號：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name = "lotNum">
						<%=View.getTextOption("Y;同批號;N;不同批號;", obj.getLotNum(), 1)%>
					</select>
				</td>
			</tr>
			<tr>
		        <td nowrap class="td_form"  width="50%"><font color="red">*</font>件數：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<input type="text" name="phynum" size="6" maxlength="6" value="<%=obj.getPhynum()%>">件
				</td>
			</tr>
		</table>
	</div>
	<div id="advType" style="display:none">
		<table class="table_form" width="99%" height="100%">
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>不良反應：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name="advLevel" class="field_Q" type="select">
	    				<%=View.getOptionCodeKind("DRG0308", obj.getAdvLevel())%>
	    			</select>
				</td>
			</tr>
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>時間區間：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<input type="text" name="advyear" size="60" maxlength="50" value="<%=obj.getAdvyear()%>">年
				</td>
			</tr>
			<tr>
		        <td nowrap class="td_form"  width="50%"><font color="red">*</font>件數：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<input type="text" name="advnum" size="6" maxlength="6" value="<%=obj.getAdvnum()%>">件
				</td>
			</tr>	
		
		</table>
	</div>
	<table class="table_form" width="99%" height="100%">
		<tr>
			<td nowrap class="td_form"  width="50%">異動人員/日期：</td>
			<td nowrap class="td_form_white"  width="50%">
			  	[<input class="field_RO" type="text" name="editID" size="10" value="<%=User.getUserId()%>">/
			  	<input class="field_RO" type="text" name="editDate" size="7" value="<%=Datetime.getYYYMMDD()%>">]
			</td>
		</tr>		
	</table>
	</div>
</td></tr>
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">NTI</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">時間區間</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">批號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">件數</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">反應等級</a></th>
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
</td></tr>
</table>
</form>
</body>
</html>