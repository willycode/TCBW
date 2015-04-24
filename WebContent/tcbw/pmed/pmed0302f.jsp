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
<jsp:useBean id="obj0301" scope="request" class="com.kangdainfo.tcbw.view.pmed.PMED0301F">
	<jsp:setProperty name="obj0301" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<% 
obj0301 = (com.kangdainfo.tcbw.view.pmed.PMED0301F) obj0301.doQueryOne();
objList = (java.util.ArrayList) obj0301.doQueryAll();
%>
<script type="text/javascript">

function result()
{
	var assessresult = form1.assessresult.value;
	if(assessresult == "90")
	{
		document.all.item("assessresultdesc").readOnly = false;
		document.all.item("assessresultdesc").className = "field";
	} else
	{
		form1.assessresultdesc.value = "";
		document.all.item("assessresultdesc").readOnly = true;
		document.all.item("assessresultdesc").className = "field_RO";
	}
		
}
</script>

<!--Form區============================================================-->
	<table id="Tab4" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">報告資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">期數</td>
			<td nowrap class="td_form_white"  width="35%">
				<input class="field_RO" type="text" name="reportIssueno" value="<%=obj0301.getReportIssueno()%>">
			</td>
			<td nowrap class="td_form" width="15%">報告類別</td>
			<td nowrap class="td_form_white" width="35%" >
				<input class="field_RO" type="hidden" name="reporttype1" value="<%=obj0301.getReporttype()%>">
				<%=View.getOneCodeName("MEDRPTYPE",obj0301.getReporttype()) %>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >繳交狀態</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getOneCodeName("MEDHANDTYPE",obj0301.getHandstatus()) %>	
			</td>		
		</tr>		
	<tr>
		<td nowrap class="td_form">評估日期</td>
		<td nowrap class="td_form_white" >
			<%=View.getPopCalendar("field","assessdate",obj0301.getAssessdate())%>
		</td>
		<td nowrap class="td_form">本次報告監控系統期間</td>
		<td nowrap class="td_form_white" >
			<%=View.getPopCalendar("field_RO","setMonitorSDate0301",obj0301.getMonitorSDate0301())%>
			~
			<%=View.getPopCalendar("field_RO","prehanddate0301",obj0301.getPrehanddate())%>
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">本次報告監控期間<br>國內不良反應通報系統件數</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_RO" type="text" name="inUnReacNum" value="<%=obj0301.getInUnReacNum()%>">
		</td>
		<td nowrap class="td_form" width="15%">本次報告監控期間<br>國內不良品通報系統件數</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_RO" type="text" name="inDefProNum" value="<%=obj0301.getInDefProNum()%>">
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">本次報告監控期間<br>國外警訊系統件數</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_RO" type="text" name="outVmedNum" value="<%=obj0301.getOutVmedNum()%>">
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">評估摘要</td>
		<td nowrap class="td_form_white" colspan="3">
			<textarea class="field" id="assesssummary" name="assesssummary" rows="2" cols="40"><%=obj0301.getAssesssummary()%></textarea>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >評估結果</td>
			<td nowrap class="td_form_white"  colspan="3">
				<select class="field" name="assessresult" onchange=result();>
					<%=View.getOptionCodeKind("MEDASREPORT", obj0301.getAssessresult())%>
			    </select>
				其他
				<input class="field_RO" type="text" name="assessresultdesc" value="<%=obj0301.getAssessresultdesc()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">評估備註</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="assessremark" size="50" maxlength="50" value="<%=obj0301.getAssessremark()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">附件(評估附件)</td>
		<td nowrap class="td_form_white" colspan="3">  
		<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	     <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%">檔案名稱</th>
		          <th class="listTH" width="50%">檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		             <input class="toolbar_default" type="button" followPK="false" name="doUpload5" value="附件上傳" onClick="upload('MED050005')">
		          </span>
		          </th>
	           </tr>
	     </thead>
	     <tbody>
		 	<%=obj0301.getAddFileMed050005()%>
		 </tbody>
       </table>    
	   </td>
	</tr>
	
	<tr>
<td class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">期數</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">報告類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">報告收文字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">報告摘要</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">預計繳交日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">實際繳交日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">繳交狀態</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">明細</a></th>
	
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,true};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,false};
	    boolean linkArray[] = {false,true,false,false,false,false,false,false,false};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center"};
		out.write(View.getQuerylistPlusDetailButton(primaryArray,displayArray,null,objList,obj0301.getQueryAllFlag(),true,linkArray,null,"",false, false));
		//out.write(View.getQueryDiscolorList(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", true,"pmed0201f.jsp","","",1));
	%>
	</tbody>
</table>
</div>
</td>
</tr>
	
</table>
