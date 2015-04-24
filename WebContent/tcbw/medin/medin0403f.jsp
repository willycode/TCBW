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
<jsp:useBean id="obj0403" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0402F">
	<jsp:setProperty name="obj0403" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList0403" scope="page" class="java.util.ArrayList"/>
<%
     objList0403 = (java.util.ArrayList) obj0403.doQueryAll();
     obj0403 = (com.kangdainfo.tcbw.view.medin.MEDIN0402F)obj0403.queryOne();
%>
	<table id="Tab12" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="20%">通報品質</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getCommonRadioBoxOption("field","bulletinQuality","MEDNTQL",obj0403.getBulletinQuality(),"")%>
			</td>
		</tr>		
		<tr>
			<td nowrap class="td_form" width="20%">仿單</td>
			<td nowrap class="td_form_white"  width="30%">
				<%=View.getRadioBoxTextOption("field","instructionSheet","1;是;2;否;3;未取得仿單",obj0403.getInstructionSheet())%>
			</td>
			<td nowrap class="td_form" width="20%">文獻紀錄</td>
			<td nowrap class="td_form_white"  width="30%">
				<input type="radio" name="documentRecords" <%if("1".equals(obj0403.getDocumentRecords())) out.print("checked");  %>  value="1">PubMed，<input type="text" name="documentNum" value="<%=obj0403.getDocumentNum()%>" size="3" maxlength="3"/>件&nbsp;&nbsp;
				<input type="radio" name="documentRecords" <%if("2".equals(obj0403.getDocumentRecords())) out.print("checked");  %>  value="2">查無紀錄&nbsp;&nbsp;
			</td>			
		</tr>		
		<tr>
			<td nowrap class="td_form" width="20%">警訊查詢</td>
			<td nowrap class="td_form_white"  width="30%">
			<input class="field_RO" type="checkbox" name="policeQuery" value="fda" <%if(!"".equals(obj0403.getPoliceFdaNum())) out.write("checked");%>>FDA，<input type="text" name="policeFdaNum" value="<%=obj0403.getPoliceFdaNum()%>" size="3" maxlength="3" />件&nbsp;&nbsp;
			<input class="field_RO" type="checkbox" name="policeQuery" value="mara" <%if(!"".equals(obj0403.getPoliceMhraNum())) out.write("checked"); %>>MHRA，<input type="text" name="policeMhraNum" value="<%=obj0403.getPoliceMhraNum()%>" size="3" maxlength="3" />件&nbsp;&nbsp;<br>
			<input class="field_RO" type="checkbox" name="policeQuery" value="ecri" <%if(!"".equals(obj0403.getPoliceEcriNum())) out.write("checked"); %>>ECRI，<input type="text" name="policeEcriNum" value="<%=obj0403.getPoliceEcriNum()%>" size="3" maxlength="3" />件&nbsp;&nbsp;
			<input class="field_RO" type="checkbox" name="policeQuery" value="no" <%if("".equals(obj0403.getPoliceEcriNum()) && "".equals(obj0403.getPoliceMhraNum()) && "".equals(obj0403.getPoliceEcriNum())) out.write("checked"); %>>查無紀錄&nbsp;&nbsp;
			
			
			<!--  
				<input type="radio" name="policeQuery"  value="fda" <%if("fda".equals(obj0403.getPoliceQuery())) out.print("checked");  %> >FDA，<input type="text"    name="policeFdaNum" value="<%=obj0403.getPoliceFdaNum()%>" size="3" maxlength="3" />件&nbsp;&nbsp;
				<input type="radio" name="policeQuery"  value="mara" <%if("mara".equals(obj0403.getPoliceQuery())) out.print("checked");  %> >MHRA，<input type="text" name="policeMhraNum" value="<%=obj0403.getPoliceMhraNum()%>" size="3" maxlength="3" />件&nbsp;&nbsp;<br>
				<input type="radio" name="policeQuery"  value="ecri" <%if("ecri".equals(obj0403.getPoliceQuery())) out.print("checked");  %> >ECRI，<input type="text" name="policeEcriNum" value="<%=obj0403.getPoliceEcriNum()%>" size="3" maxlength="3" />件&nbsp;&nbsp;
				<input type="radio" name="policeQuery"  value="no" <%if("no".equals(obj0403.getPoliceQuery())) out.print("checked");  %> >查無紀錄&nbsp;&nbsp;
			-->
			</td>
			<td nowrap class="td_form" width="20%">不良事件通報</td>
			<td nowrap class="td_form_white"  width="30%">
			          統計區間 <%=View.getPopCalendar("field_RO","statDateS",obj0403.getStatDateS())%>~<%=View.getPopCalendar("field_RO","statDateE",obj0403.getStatDateE())%><br>
				國內醫材不良反應通報資料庫，<input class="field_RO" type="text" value="<%=obj0403.getStatEventKind1() %>" size="2" />件<br>
				國內不良品通報資料庫，<input class="field_RO" type="text" value="<%=obj0403.getStatEventKind2() %>" size="2" />件
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form_white"  colspan="2" width="100%">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="td_form" style="text-align:center" width="90%">病人問題代碼</th>
		         <th width="10%" class="td_form" style="text-align:center">
		         <input class="field" name="btnAdd" value="+" type="button" onClick="addMed1005Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed1005DbView">
			   </tbody>
               </table>
			</td>
			<td nowrap class="td_form_white"  colspan="2" width="100%">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="td_form" style="text-align:center" width="90%">醫材問題代碼</th>
		         <th width="10%" class="td_form" style="text-align:center">
		         <input class="field" name="btnAdd" value="+" type="button" onClick="addMed1006_0403Db()">
		         </th>
	           </tr>
	           </thead>
	           <tbody id="attMed1006_0403DbView">
			   </tbody>
               </table>
			</td>
		</tr>				
		<tr>
		    <td nowrap class="td_form">需再取得事件詳細說明</td>	
			<td nowrap class="td_form_white"  colspan="3">
				<input type="radio" name="eventDetails" value="Y" <%if("Y".equals(obj0403.getEventDetails())) out.print("checked");  %>>是
				<input type="radio" name="eventDetails" value="N" <%if("N".equals(obj0403.getEventDetails())) out.print("checked");  %>>否
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">可能性：評估不良反應，請回答下列問題，並勾選適當的答案</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<table width="100%" class="table_form" border="1" >
					<tr>
						<td nowrap width="70%" class="listTH" align="center">項目</td>
						<td nowrap width="10%" class="listTH">是</td>
						<td nowrap width="10%" class="listTH">否</td>
						<td nowrap width="10%" class="listTH">不知</td>
					</tr>
					    <%=obj0403.getMed1002Db(obj0403.getId2())%>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >總分:
						    <input class="field_RO" type="text"  name="adverseTotal" value="<%=obj0403.getAdverseTotal()%>">
						    <input type="hidden" class="field_RO" name="assessAdverseReactions" value="<%=obj0403.getAssessAdverseReactions()%>">
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >確定為下列何者:
							<%=View.getRadioBoxTextOption("field","adverseReactionsResult","1;≦2分：存疑;2;3-5分：可能相關;3;6-8分：極有可能相關;4;≧9：確定相關",obj0403.getAdverseReactionsResult())%>
						</td>
					</tr>
				</table>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">事件嚴重程度</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<input type="radio" name="eventSeverity" value="mild" <%if("mild".equals(obj0403.getEventSeverity())) out.print("checked");  %> >輕度 (無需治療、停止使用器材或技術即可)&nbsp;&nbsp;
				<input type="radio" name="eventSeverity" value="moderate" <%if("moderate".equals(obj0403.getEventSeverity())) out.print("checked");  %>>中度 (需治療、導致住院或延長住院時間至少一天以上)&nbsp;&nbsp;<br>
				<input type="radio" name="eventSeverity" value="serious" <%if("serious".equals(obj0403.getEventSeverity())) out.print("checked");  %>>重度 (導致死亡、危及生命、需加護病房治療或需七天以上才能復原、造成永久性殘疾或先天性畸形)&nbsp;&nbsp;
				<input type="radio" name="eventSeverity" value="unable" <%if("unable".equals(obj0403.getEventSeverity())) out.print("checked");  %>>無法評估&nbsp;&nbsp;
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">初評意見</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<input type="radio" name="commentOpinion" value="1" <%if("1".equals(obj0403.getCommentOpinion())) out.print("checked");  %>>先行結案，並持續受理通報。需注意觀察後續類似通報情形&nbsp;&nbsp;<br>
				<input type="radio" name="commentOpinion" value="2" <%if("2".equals(obj0403.getCommentOpinion())) out.print("checked");  %>>應聯繫原始通報者，取得事件詳細說明。如：<input type="text" name="commentExplanation1" value="<%=obj0403.getCommentExplanation1()%>" size="20" maxlength="50"/><br>
				<input type="radio" name="commentOpinion" value="3" <%if("3".equals(obj0403.getCommentOpinion())) out.print("checked");  %>>應聯繫該醫材許可證持有商，取得事件詳細說明。如：<input type="text" name="commentExplanation2" value="<%=obj0403.getCommentExplanation2()%>" size="20" maxlength="50"/>&nbsp;&nbsp;
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他意見</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<textarea name="otherOpinion" cols="60" rows="4"><%=obj0403.getOtherOpinion()%></textarea>
			</td>		
		</tr>
		
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
					<%=obj0403.getMed1003Db(obj0403.getId2())%>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >
						  小計:<input type="text" class="field_RO" name="ncarTotal" value="<%=obj0403.getNcarTotal()%>">
						<input type="hidden" class="field_RO" name="ncarOptions" value="<%=obj0403.getNcarOptions()%>">
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form_white" colspan="4" >
							NCAR通報結果：
							  <%=View.getRadioBoxTextOption("field","ncarResult","1;通報NCAR;2;不通報NCAR",obj0403.getNcarResult())%>
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
			<td nowrap class="td_form_left" colspan="4">結案通知</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white"  colspan="4">
				<input type="radio" name="resultsNotification" value="1" <%if("1".equals(obj0403.getResultsNotification())) out.print("checked");  %>>通知廠商本案資料留廠備查&nbsp;&nbsp;
				<input type="radio" name="resultsNotification" value="2" <%if("2".equals(obj0403.getResultsNotification())) out.print("checked");  %>>通知廠商來文至TFDA說明事件矯正措施
			</td>		
		</tr>
		<tr>
		<td nowrap class="td_form_white"  colspan="4" width="100%">
        <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
			<thead id="listTHEAD">   
	        <tr>
	        <td>
	            <span id="spanDoUpload">
		        <input class="toolbar_default" type="button" followPK="false" name="doUpload0403" value="附件上傳" onClick="upload('medin0403')">&nbsp;
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
			   <%=obj0403.getAddFile("MED010003")%>
			</tbody>
			</table>
			</td>
		</tr>
		<tr>
		<td>&nbsp;</td>
		<tr>
			<td nowrap class="td_form_left" colspan="4">評估歷程</td>
		</tr>
<!-- List 區   -->	
<tr><td nowrap class="bgPagging" colspan="4">		
<% request.setAttribute("QueryBean",obj0403);%>
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">醫材與不良反應關聯性</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">NCAR通報狀況</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true};
	out.write(View.getQuerylist(primaryArray,displayArray,objList0403,obj0403.getQueryAllFlag()));
	%>
	</tbody>
</table>

</div>
</td>
</tr>
</table>
	
