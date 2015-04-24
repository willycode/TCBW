<!--
程式目的：藥品不良品案件分級設定作業
程式代號：drgse0001f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGSE0001" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgse.DRGSE0001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.drgse.DRGSE0001F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.drgse.DRGSE0001F)obj.queryOne();
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
		alertStr += checkEmpty(form1.level, "不良品風險等級");
		if((form1.level.value == "02") || (form1.level.value == "03")){
			alertStr += checkEmpty(form1.formulation1, "劑型");
			alertStr += checkEmpty(form1.defectiveProduct1, "不良品缺陷之描述")
			alertStr += checkEmpty(form1.isStop1, "是否停用");
		}
		if((form1.level.value == "04")) {
			alertStr += checkEmpty(form1.year2, "時間區間");
			alertStr += checkNumber(form1.num2, "件數");
			}
		if((form1.level.value == "01")) {
			alertStr += checkEmpty(form1.defectiveProduct3, "不良品缺陷之描述");
			alertStr += checkEmpty(form1.permitKey3,"許可證字");
			alertStr += checkEmpty(form1.permitNo3, "許可證號");
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
	if(a.value == "02" || a.value == "03"){
		phyType.style.display="block";
		advType.style.display="none";
		prjType.style.display="none"
	}else if(a.value == "04"){
		phyType.style.display="none";
		advType.style.display="block";
		prjType.style.display="none"
	}else if(a.value == "01"){
		phyType.style.display="none";
		advType.style.display="none";
		prjType.style.display="block"
	}else{
		phyType.style.display="none";
		advType.style.display="none";
		prjType.style.display="none"
	}
}

function changeQuery(a){
	if(a.value == "02" || a.value == "03"){
		Q_phyTpye.style.display="block";
		Q_advType.style.display="none";
		Q_prjType.style.display="none";
	}else if(a.value == "04"){
		Q_phyTpye.style.display="none";
		Q_advType.style.display="block";
		Q_prjType.style.display="none";
	}else if(a.value == "01") {
		Q_phyTpye.style.display="none";
		Q_advType.style.display="none";
		Q_prjType.style.display="block";
	}else{
		Q_phyTpye.style.display="none";
		Q_advType.style.display="none";
		Q_prjType.style.display="none";
	}
}

function init() {
	if(('<%=obj.getLevel()%>' == "02") || ('<%=obj.getLevel()%>' == "03")){
		phyType.style.display="block";
	}else if ('<%=obj.getLevel()%>' == "04"){
		advType.style.display="block";
	}else if ('<%=obj.getLevel()%>' == "01") {
		prjType.style.display="block"
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
	<table class="queryTable" border="1" style="height:50px;">
	<tr>
		<td nowrap class="queryTDLable">不良品風險等級：</td>
		<td nowrap class="queryTDInput">
			<select name="q_level" class="field_Q" type="select" onchange="changeQuery(this);">
	    		<%=View.getOptionCodeKind("DRGRKL", obj.getQ_level())%>
	    	</select>
	    </td>
	</tr>
	</table>
	<div id="Q_phyTpye" style="display:none">
	<table class="queryTable" style="height:150px;">
		<tr>
		<td nowrap class="queryTDLable">劑型：</td>
		<td nowrap class="queryTDInput">
			<select name="q_formulation" class="field_Q" type="select">
	    		<%=View.getOptionCodeKind("DRGFLN", obj.getQ_formulation())%>
	    	</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">不良品缺陷之描述：</td>
		<td nowrap class="queryTDInput">
			<select name="q_defectiveProduct" class="field_Q" type="select">
				<%=View.getOption("select dpdKind,dpdKindName from Drg1001Db",obj.getQ_defectiveProduct(),false,1)%>
	    	</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">是否停用：</td>
		<td nowrap class="queryTDInput">
			<select name="q_isStop" class="field_Q" type="select">
	    		<%=View.getTextOption("Y;是;N;否;", obj.getQ_isStop(), 1)%>
	    	</select>	
		</td>
	</tr>
	</table>
	</div>
	<div id="Q_advType" style="display:none">
		<table class="queryTable" style="height:150px;">
	        <tr>
	            <td nowrap class="queryTDLable"><font color="red">*</font>批號：</td>
			    <td nowrap class="queryTDInput">
				<select name = "q_lotNum" class="field_Q" type="select">
					<%=View.getTextOption("Y;同批號;N;不同批號;", obj.getQ_lotNum(), 1)%>
				</select>
			</td>
			<tr>
		        <td nowrap class="queryTDLable">時間區間：</td>
				<td nowrap class="queryTDInput">
					<input class="field_Q" type="text" name="q_year" size="2" maxlength="2" value="<%=obj.getQ_year()%>">年
				</td>
			</tr>	
			<tr>
		        <td nowrap class="queryTDLable">件數：</td>
				<td nowrap class="queryTDInput">
					<input class="field_Q" type="text" name="q_num" size="6" maxlength="6" value="<%=obj.getQ_num()%>">件
				</td>
			</tr>				
		</table>
	</div>
		<div id="Q_prjType" style="display:none">
		<table class="queryTable" style="height:150px;">
			<tr>
		        <td nowrap class="queryTDLable">許可證字號：</td>
				<td nowrap class="queryTDInput">
					<select class="field_Q" name="q_permitKey" class="field">
				        <%=View.getOptionCodeKind("DRGPKID", obj.getQ_permitKey())%>
			        </select>			        
					<input class="field_Q" type="text" name="q_permitNo" size="14" maxlength="14" value="<%=obj.getQ_permitNo()%>">
				</td>
			</tr>
	<tr>
		<td nowrap class="queryTDLable">不良品缺陷之描述：</td>
		<td nowrap class="queryTDInput">
			<select name="q_defectiveProductPrj" class="field_Q" type="select">
				<%=View.getOption("select dpdKind,dpdKindName from Drg1001Db",obj.getQ_defectiveProductPrj(),false,1)%>
	    	</select>
		</td>
	</tr>	
		</table>
	</div>
	
	<table class="queryTable" style="height:100px;">
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
        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>不良品風險等級：</td>
		<td nowrap class="td_form_white"  width="50%" >
			<select name = "level" class="field" type="select" onchange="changeForm(this);"> 
				<%=View.getOptionCodeKind("DRGRKL", obj.getLevel())%>
			</select>
		</td>
	</tr>
	</table>
	<div id="phyType" style="display:none">
		<table class="table_form" width="99%" height="100%">
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>劑型：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name="formulation1" class="field">
						<%=View.getOptionCodeKind("DRGFLN", obj.getFormulation1())%>
					</select>
				</td>
			</tr>			
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>不良品缺陷之描述：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name="defectiveProduct1" class="field">
						<%=View.getOption("select dpdKind,dpdKindName from Drg1001Db", obj.getDefectiveProduct1())%>
					</select>
				</td>
			</tr>								
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>是否停用：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name = "isStop1">
						<%=View.getTextOption("Y;是;N;否;", obj.getIsStop1(), 1)%>
					</select>
				</td>
			</tr>
		</table>
	</div>
	<div id="advType" style="display:none">
		<table class="table_form" width="99%" height="100%">
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>批號：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name = "lotNum2">
						<%=View.getTextOption("Y;同批號;N;不同批號;", obj.getLotNum2(), 1)%>
					</select>
				</td>
			</tr>
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>時間區間：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<input type="text" name="year2" size="2" maxlength="2" value="<%=obj.getYear2()%>">年
				</td>
			</tr>	
			<tr>
		        <td nowrap class="td_form"  width="50%"><font color="red">*</font>件數：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<input type="text" name="num2" size="6" maxlength="6" value="<%=obj.getNum2()%>">件
				</td>
			</tr>
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>是否停用：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name = "isStop2">
						<%=View.getTextOption("Y;是;N;否;", obj.getIsStop2(), 1)%>
					</select>
				</td>
			</tr>		
		</table>
	</div>
		<div id="prjType" style="display:none">
		<table class="table_form" width="99%" height="100%">
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>許可證字號：</td>
				<td nowrap class="td_form_white"  width="50%" >					
					<select class="field" name="permitKey3" class="field">
				        <%=View.getOptionCodeKind("DRGPKID", obj.getPermitKey3())%>
			        </select>			 
			        <input class="field" type="text" name="permitNo3" size="6" maxlength="6" value="<%=obj.getPermitNo3()%>" />
				</td>
			</tr>
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>不良品缺陷之描述：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name = "defectiveProduct3">
						<%=View.getOption("select dpdKind,dpdKindName from Drg1001Db",obj.getDefectiveProduct3(),false,1)%>
					</select>
				</td>
			</tr>
			<tr>
		        <td nowrap class="td_form"  width="50%"  ><font color="red">*</font>是否停用：</td>
				<td nowrap class="td_form_white"  width="50%" >
					<select name = "isStop3">
						<%=View.getTextOption("Y;是;N;否;", obj.getIsStop3(), 1)%>
					</select>
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">風險等級</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">劑型</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">不良品缺陷之描述</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">批號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">件數</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">時間區間</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',7,false);" href="#">是否停用</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',8,false);" href="#">許可證字號</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center", "center", "center", "center"};
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