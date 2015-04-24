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
<jsp:useBean id="obj0803" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0802F">
	<jsp:setProperty name="obj0803" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList0803" scope="page" class="java.util.ArrayList"/>
<%
     objList0803 = (java.util.ArrayList) obj0803.doQueryAll();
     obj0803 = (com.kangdainfo.tcbw.view.medin.MEDIN0802F)obj0803.queryOne();
%>
	<table id="Tab7" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="20%">事件等級</td>
			<td nowrap class="td_form_white"  colspan="3">
			   <%=View.getCommonRadioBoxOption("field_U","eventClass0803","MEDEVC",obj0803.getEventClass0803(),"")%>
			</td>
		</tr>		
		<tr>
			<td nowrap class="td_form_white"  colspan="2" width="50%">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="td_form" style="text-align:center" width="90%">醫材問題代碼</th>
		         <th width="10%" class="td_form" style="text-align:center">
		         <input class="field" name="btnAddRe" value="+" type="button" onClick="addMed1007Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed1007DbView">
			   </tbody>
               </table>
			</td>
			<td nowrap class="td_form" width="20%">不良事件通報</td>
			<td nowrap class="td_form_white"  width="30%">
			          統計區間 <%=View.getPopCalendar("field_RO","statDateS",obj0803.getStatDateS())%>~<%=View.getPopCalendar("field_RO","statDateE",obj0803.getStatDateE())%><br>
				國內醫材不良反應通報資料庫，<input class="field_RO" type="text" value="<%=obj0803.getStatEventKind1() %>" size="2" />件<br>
				國內不良品通報資料庫，<input class="field_RO" type="text" value="<%=obj0803.getStatEventKind2() %>" size="2" />件
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4" class="field_U">評估建議</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<textarea name="assessProposal0803" cols="60" rows="4" class="field_U"><%=obj0803.getAssessProposal0803()%></textarea>
			</td>		
		</tr>
		<!--
		<tr>
		    <td nowrap class="td_form" width="20%">需再取得事件詳細說明</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input type="radio" name="eventDetails" value="Y" <%if("Y".equals(obj0803.getEventDetails())) out.print("checked");  %>>是
				<input type="radio" name="eventDetails" value="N" <%if("N".equals(obj0803.getEventDetails())) out.print("checked");  %>>否
			</td>		
		</tr>
		  -->
		<tr>
			<td nowrap class="td_form_left" colspan="4">NCAR通報篩選</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<table width="100%" class="table_form" border="1">
					<tr>
						<td nowrap width="80%" class="listTH">項目</td>
						<td nowrap width="10%" class="listTH">是</td>
						<td nowrap width="10%" class="listTH">否</td>
					</tr>
					<%=obj0803.getMed1003Db(obj0803.getId2())%>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >小計:
						    <input type="text" class="field_RO" name="ncarTotal" value="<%=obj0803.getNcarTotal()%>">
							<input type="hidden" class="field_RO" name="ncarOptions0803" value="<%=obj0803.getNcarOptions0803()%>">
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >
							NCAR通報結果： <%=View.getRadioBoxTextOption("field_U","ncarResult0803","1;通報NCAR;2;不通報NCAR",obj0803.getNcarResult0803())%>
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >
							說明：<br>
							(1)	A、B選項需同時成立，方可進行本表C項問答。<br>
							(2)	合計分數≧5分，建議進行NCAR通報。<br>
							(3)	合計分數< 5分，建議無須進行NCAR通報。
						</td>
					</tr>
				</table>
			</td>		
		</tr>
		<tr>
		<td nowrap class="td_form_white"  colspan="4" width="100%">
        <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
			<thead id="listTHEAD">   
	        <tr>
	        <td>
	        	<span id="spanDoUpload">
		        <input class="toolbar_default" type="button" followPK="false" name="doUpload0803" value="附件上傳" onClick="upload('medin0803')">&nbsp;
		        </span>
	        </td>
	        </tr>        
	        <tr>
		        <th class="listTH" width="10%" >No.</th>
		        <th class="listTH" width="30%">檔案名稱</th>
		        <th class="listTH" width="50%">檔案說明</th>
		        <th class="listTH" width="10%">刪除</th>
	        </tr>
	        </thead>
	        <tbody>
			   <%=obj0803.getAddFile("MED010005")%>
			</tbody>
			</table>
			</td>
		</tr>	
		<tr>
		<td>&nbsp;</td>
		<tr>
		<tr>
		<td nowrap class="td_form_left" colspan="4">評估歷程</td>
		</tr>
<!-- List 區   -->	
<tr><td nowrap class="bgPagging" colspan="4">		
<% request.setAttribute("QueryBean",obj0803);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>
<tr>
<td nowrap class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">評估日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">通報品質</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">事件等級</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">NCAR通報狀況</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true};
	out.write(View.getQuerylist(primaryArray,displayArray,objList0803,obj0803.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td>
</tr>
</table>
	
