<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN1001F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String tabId = Common.get(request.getParameter("tabId"));
%>
<script type="text/javascript">

$(function(){
	doShow1("<%=obj.getReactionLev()%>");
	
	$(":radio[name=isComplaintYn]").bind("click", function(){
		doShow2($(this).val());
	});
	doShow2("<%=obj.getIsComplaintYn()%>");
	
	$(":radio[name=dIsOtherDeptYn]").bind("click", function(){
		doShow3($(this).val());
	});
	doShow3("<%=obj.getdIsOtherDeptYn()%>");
	chPreResult("<%=obj.getPreResult()%>");
});

function doShow1(val){
	var s = "";
	if(val == "1"){
		$(":radio[name=adverseCondition]").each(function(){
			if($(this).attr("checked") && $(this).val()!="99"){
				s += $(this)[0].nextSibling.nodeValue;
			}
		});
		$("#show1").empty().show().append($("<font color='red'>" + s + "</font>"));
	}else if(val == "2"){
		$(":radio[name=adverseCondition]").each(function(){
			if($(this).attr("checked") && $(this).val()=="99"){
				s += $(this).next().val();
			}
		});
		$("#show1").empty().show().append($("<font color='red'>" + s + "</font>"));
	}else{
		$("#show1").empty();
		$("#show1").hide();
	}
}	

function doShow2(val){
	if(val == "Y"){
		var s = "";
		$(":radio[name=dealWith]").each(function(){
			if($(this).attr("checked")){
				s += $(this)[0].nextSibling.nodeValue;
			}
		});
		$("#show2").show().append($("<font color='red'>" + s + "</font>"));
	}else{
		$("#show2").empty();
		$("#show2").hide();
	}	
}

function doShow3(val){
	if(val == "Y"){
		var s = $(":text[name=otherDpetName]").val();
		$("#show3").show().append($("<font color='red'>" + s + "</font>"));
	}else{
		$("#show3").empty();
		$("#show3").hide();
	}
}

function chPreResult(val){
	if(val == "02"){
		$("#COSFR1").show();	
	}else{
		$("#COSFR1").hide();	
		$("[name=leftCaseReason]").val("");
	}
}

function queryCos0008DbOne(rowid, isNeedUpdateOther, classTR, id){
	$("tr[id*=listCos0008DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listCos0008DbContainerRow" + rowid)){
			this.className = "activeRow";
			this.onmouseover = "activeRow";
			this.onmouseout = "activeRow";
		}else{
			this.className = classTR;
			this.onmouseover = function(){
				this.className = "listTRMouseover";
			};
			this.onmouseout = function(){
				this.className = classTR;
			}
		}
	});

	showCOS0008Db(id);
}

function showCOS0008Db(id){
	openCenterWindow(900, 300, 'cosin1006f.jsp?cos0008DbId=' + id);
}

</script>

<table id="<%=tabId.equals("")?"Tab6":tabId%>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">初步判定結果</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="preResult" type="select" onChange="chPreResult(this.value)">
			<%=View.getOptionCodeKind("COSFR", obj.getPreResult()) %>
		</select>
		<span id="COSFR1">
			，留案備查理由
			<input class="field" type="text" name="leftCaseReason" size="60" maxlength="80" value="<%=obj.getLeftCaseReason()%>">
		</span>
		<input type="hidden" name="cos0008DbId" value="<%=obj.getCos0008DbId()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">通報資料完整性</td>
	<td nowrap class="td_form_white">
		<%=View.getYNRadioBoxTextOption("field", "isCompleteYn", obj.getIsCompleteYn()) %>
	</td>
	<td nowrap class="td_form">是否聯絡通報者取得完整資料</td>
	<td nowrap class="td_form_white">
		<%=View.getYNRadioBoxTextOption("field", "dIsContactYn", obj.getdIsContactYn()) %>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">不良反應嚴重程度</td>
	<td nowrap class="td_form_white" colspan="3">
		<select class="field" name="reactionLev" type="select" onChange="doShow1(this.value);">
			<%=View.getTextOption("1;嚴重不良反應;2;非嚴重不良反應", obj.getReactionLev()) %>	
		</select>
		&nbsp;&nbsp;
		<span id="show1"></span>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">時序性</td>
	<td nowrap class="td_form_white">
		<select class="field" name="timingLev" type="select">
			<%=View.getOptionCodeKind("COSTIM", obj.getTimingLev()) %>
		</select>
	</td>
	<td nowrap class="td_form">過往通報紀錄</td>
	<td nowrap class="td_form_white">
		<select class="field" name="previousNotify" type="select">
			<%=View.getTextOption("Y;有;N;無", obj.getPreviousNotify()) %>
		</select>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">是否連絡廠商進行客訴</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getYNRadioBoxTextOption("field", "isComplaintYn", obj.getIsComplaintYn()) %>
		
		&nbsp;&nbsp;
		<span id="show2"></span>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">是否送交相關單位處理</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getYNRadioBoxTextOption("field", "dIsOtherDeptYn", obj.getdIsOtherDeptYn()) %>
		
		&nbsp;&nbsp;
		<span id="show3"></span>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">化粧品不良品通報件數</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field_RO" type="text" name="num1" size="20" value="<%=obj.getNum1()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">化粧品不良反應通報件數</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field_RO" type="text" name="num2" size="20" value="<%=obj.getNum2()%>">
	</td>
</tr>
<%
if(obj.getCos0008DbList()!=null && obj.getCos0008DbList().size()>0){
%>
	<tr>
		<td nowrap class="td_form_left" colspan="4" style="font-size:12pt;text-align:center;background-color:yellow">分類歷史流程資料</td>
	</tr>
	
	<tr><td nowrap class="bgList" colspan="4">
	<div id="COS0008DbListContainer" style="height:auto">
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="COS0008DbListTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w">NO.</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">初步判定結果</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">通報資料完整性</a></th>
			<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">是否聯絡通報者取得完整資料</a></th>
		</tr>
		</thead>
		<tbody id="COS0008DbListTBODY">
		<%
		boolean primaryArray[] = {true, false, false, false};
		boolean displayArray[] = {false, true, true, true};
		out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getCos0008DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Cos0008Db"));   
		%>
		</tbody>
	</table>
	</div>
	</td></tr>

<%
}
%>
</table>