<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0106F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
objList = (java.util.ArrayList) obj.doQueryAllDrg0106();
//obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0106F) obj.doQueryOneDrg0106();
%>

<table id="Tab3" width="100%" align="center" class="table_form">
	    <tr>
		    <td nowrap class="td_form_left" colspan="4">通報訊息		         
	             <input type="hidden" name="drg03id" value="<%=obj.getDrg03id()%>">
	        </td>
	    </tr>
		<tr>
			<td nowrap class="td_form" >評估日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field_RO","assessDate",obj.getAssessDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >不良品風險評估結果</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<%=View.getRadioBoxOption("field", "firstResult1", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRKL' order by codeShortName", obj.getFirstResult1())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報時效</td>
			<td nowrap class="td_form_white" width="80%">
				間隔天數：<input type="text" name="intervalDays" size="3" value="<%=obj.getIntervalDays()%>">天&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getRadioBoxTextOption("field","notifierAging","1;時效佳;2;時效待加強;",obj.getNotifierAging())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報品質</td>
			<td nowrap class="td_form_white" width="80%">
                 <%=View.getRadioBoxTextOption("field", "notifierQuality", "1;Excellent;2;Good;3;Fair;",obj.getNotifierQuality())%>			
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >歷史通報資料摘要</td>
			<td nowrap class="td_form_white" width="80%">
				歷年本藥品之通報件數：<input class="field_RO" type="text" size="3"  value="<%=obj.getHisData1()%>">件
				<br>歷年本藥品同此次瑕疵之通報件數：<input class="field_RO"  type="text" size="3" value="<%=obj.getHisData2()%>">件
				<br>一年內本藥品之通報件數：<input class="field_RO" type="text" size="3" value="<%=obj.getHisData3()%>">件
				<br>一年內本藥品同此次瑕疵之通報件數：<input class="field_RO" type="text" size="3" value="<%=obj.getHisData4()%>">件
				<br>一年內本藥品同此次瑕疵之高風險通報件數：<input class="field_RO" type="text" size="3" value="<%=obj.getHisData5()%>">件
				<br>一年內本藥品同此次瑕疵案件：<%=obj.getHisData6()%>
				<br>一年內本藥品同此次瑕疵之高風險案件：<%=obj.getHisData7()%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >備註</td>
			<td nowrap class="td_form_white" width="80%">
				<textarea class="field"  name="remark" cols="120" rows="2" ><%=obj.getRemark()%></textarea>
			</td>
		</tr>

<!-- List 區   -->	
<tr><td nowrap class="bgPagging" colspan="4">		
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>


<tr>
<td nowrap class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">初評日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">初評人員</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">不良品風險評估結果</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">通報時效</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">通報品質</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',7,false);" href="#">備註</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center"};
	    out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
