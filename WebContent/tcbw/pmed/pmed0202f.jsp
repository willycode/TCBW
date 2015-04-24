<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj0201" scope="request" class="com.kangdainfo.tcbw.view.pmed.PMED0201F">
	<jsp:setProperty name="obj0201" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<% 
obj0201 = (com.kangdainfo.tcbw.view.pmed.PMED0201F) obj0201.doQueryOne();
objList = (java.util.ArrayList) obj0201.doQueryAll();
%>
<script type="text/javascript">

</script>
<!--Form區============================================================-->
	<table id="Tab3" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="15%">期數</td>
			<td nowrap class="td_form_white"  width="35%">
				<input class="field_RO" type="text" name="reportIssueno" value="<%=obj0201.getReportIssueno()%>">
			</td>
			<td nowrap class="td_form" width="15%">報告類別</td>
			<td nowrap class="td_form_white" width="35%" >
				<input class="field_RO" type="hidden" name="reporttype" value="<%=obj0201.getReporttype()%>">
				<%=View.getOneCodeName("MEDRPTYPE",obj0201.getReporttype()) %>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >繳交狀態</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field_RO" type="hidden" name="handstatus" value="<%=obj0201.getHandstatus()%>">
				<%=View.getOneCodeName("MEDHANDTYPE",obj0201.getHandstatus()) %>	
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >預計繳交日期</td>
			<td nowrap class="td_form_white" >
			<%=View.getPopCalendar("field_RO","prehanddate",obj0201.getPrehanddate())%>
			</td>
			<td nowrap class="td_form" >實際繳交日期</td>
			<td nowrap class="td_form_white" >
			<input class="field_RO" type="hidden" name="systemTime0202" value="<%=Datetime.getYYYMMDD()%>">
			<%=View.getPopCalendar("field","handdate",obj0201.getHanddate())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >本次報告監控期間(起/迄)</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","monitorsdateR",obj0201.getMonitorsdateR())%>
			~
				<%=View.getPopCalendar("field","monitoredateR",obj0201.getMonitoredateR())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >報告收文字號</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" name="reportreceiveno" value="<%=obj0201.getReportreceiveno()%>">
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >報告摘要</td>
			<td nowrap class="td_form_white"  colspan="3">
			    <textarea class="field" id="reportsummary" name="reportsummary" rows="2" cols="40"><%=obj0201.getReportsummary()%></textarea>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">國內嚴重不良反應案件件數</td>
			<td nowrap class="td_form_white"  width="35%">
				<input class="field" type="text" name="inDefCaseNum" value="<%=obj0201.getInDefCaseNum()%>">
			</td>
			<td nowrap class="td_form" width="15%">國內非嚴重不良反應案件件數</td>
			<td nowrap class="td_form_white" width="35%" >
				<input class="field" type="text" name="inUnDefCaseNum" value="<%=obj0201.getInUnDefCaseNum()%>">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">國外嚴重不良反應案件件數</td>
			<td nowrap class="td_form_white"  width="35%">
				<input class="field" type="text" name="abDefCaseNum" value="<%=obj0201.getAbDefCaseNum()%>">
			</td>
			<td nowrap class="td_form" width="15%">國外非嚴重不良反應案件件數</td>
			<td nowrap class="td_form_white" width="35%" >
				<input class="field" type="text" name="abUnDefCaseNum" value="<%=obj0201.getAbUnDefCaseNum()%>">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >國內外學術期刊文獻及研討會報告件數</td>
			<td nowrap class="td_form_white" colspan="3" >
				<input class="field" type="text" name="scienceNum" value="<%=obj0201.getScienceNum()%>">
			</td>		
		</tr>		
		<tr>
			<td nowrap class="td_form" >報告備註</td>
			<td nowrap class="td_form_white" colspan="3" >
				<input class="field" type="text" name="reportremark" size="50" maxlength="50" value="<%=obj0201.getReportremark()%>">
			</td>		
		</tr>
		<tr>
		       <td nowrap class="td_form">附件(報告附件)</td>
		       <td nowrap class="td_form_white" colspan="3">  
		         <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	             <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%">檔案名稱</th>
		          <th class="listTH" width="50%">檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		             <input class="toolbar_default" type="button" followPK="false" name="doUpload2" value="附件上傳" onClick="upload('MED050002')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj0201.getAddFileMed050002()%>
			   </tbody>
               </table>    
		       </td>
		</tr>
		<%if(!"01".equals(obj0201.getHandstatus())) { %>
		<tr>
			<td nowrap class="td_form" >通知補件日期</td>
			<td nowrap class="td_form_white" >
				<%=View.getPopCalendar("field","noticereupdatedate",obj0201.getNoticereupdatedate())%>
			</td>
			<td nowrap class="td_form">通知補件文號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="noticereupdateNo" value="<%=obj0201.getNoticereupdateNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通知補件摘要</td>
			<td nowrap class="td_form_white"  colspan="3">
				<textarea class="field" id="noticereupdatesummary" name="noticereupdatesummary" rows="2" cols="40"><%=obj0201.getNoticereupdatesummary()%></textarea>
			</td>	
		</tr>
		<tr>
		       <td nowrap class="td_form">附件(通知補件附件)</td>
			   <td nowrap class="td_form_white" colspan="3">  
		         <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	             <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%">檔案名稱</th>
		          <th class="listTH" width="50%">檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		             <input class="toolbar_default" type="button" followPK="false" name="doUpload3" value="附件上傳" onClick="upload('MED050003')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj0201.getAddFileMed050003()%>
			   </tbody>
               </table>    
		       </td>
		</tr>
		<tr>
			<td nowrap class="td_form" >補件日期</td>
			<td nowrap class="td_form_white"  >
				<%=View.getPopCalendar("field","reupdatedate",obj0201.getReupdatedate())%>
			</td>
			<td nowrap class="td_form" >補件文號</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="reupdateNo" value="<%=obj0201.getReupdateNo()%>">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >補件摘要</td>
			<td nowrap class="td_form_white"  colspan="3">
				<textarea class="field" id="reupdatesummary" name="reupdatesummary" rows="2" cols="40"><%=obj0201.getReupdatesummary()%></textarea>
			</td>		
		</tr>
		<tr>
		       <td nowrap class="td_form">附件(補件附件)</td>
		       			   <td nowrap class="td_form_white" colspan="3">  
		         <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	             <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%">檔案名稱</th>
		          <th class="listTH" width="50%">檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		             <input class="toolbar_default" type="button" followPK="false" name="doUpload4" value="附件上傳" onClick="upload('MED050004')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj0201.getAddFileMed050004()%>
			   </tbody>
               </table>    
		       </td>
		</tr>
		<%} %>
		<tr>
			<td nowrap class="td_form" >修改日期</td>
			<td nowrap class="td_form_white"  >
				<%=View.getPopCalendar("field_RO","reportupdatedate",obj0201.getReportupdatedate())%>
			</td>
			<td nowrap class="td_form" >修改人員</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="reportupdateman" value="<%=obj0201.getReportupdateman()%>">
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
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true};
	    boolean linkArray[] = {false,true,false,false,false,false,false,false};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center"};
		out.write(View.getQuerylistPlusDetailButton(primaryArray,displayArray,null,objList,obj0201.getQueryAllFlag(),true,linkArray,null,"",false, false));
		//out.write(View.getQueryDiscolorList(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", true,"pmed0201f.jsp","","",1));
	%>
	</tbody>
</table>
</div>
</td></tr>
		
</table>

