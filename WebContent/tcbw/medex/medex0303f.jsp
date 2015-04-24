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
<jsp:include page="../../home/secure.jsp"/>
<jsp:useBean id="objMedex0303" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX0303F">
	<jsp:setProperty name="objMedex0303" property="*"/>
</jsp:useBean>
<%
objMedex0303 = (com.kangdainfo.tcbw.view.medex.MEDEX0303F)objMedex0303.queryOne();
%>

<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript">
function uploadForReplayContent(){
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();

     var url="../../home/popManyUploadSimple.jsp?";
         url+="fileKind=MED";
         url+="&systemType=MED010002";
         url+="&uploadId="+form1.med0010id.value;
         url+="&dbName=Med0010DB";
         
	 returnWindow = window.open(url,'上傳檔案',prop);
}
</script>
<table id="Tab10" width="100%" align="center" class="table_form" style="overflow: hidden;">
	<%if(objMedex0303.getMed0010id() != null && !"".equals(objMedex0303.getMed0010id())){ %>
	<thead id="listTHEAD">   
	<tr>
		<td>
		<span id="spanDoUploadForReplayContent">
			<input type="hidden" name="med0010id" value="<%=objMedex0303.getMed0010id()%>"/>
			<input class="toolbar_default" type="button" followPK="false" name="doUploadForReplayContent" value="附件上傳" onClick="uploadForReplayContent()">&nbsp; 
		</span>
		</td>
	</tr>
	<tr>
		<th class="listTH" width="10%">No.</th>
		<th class="listTH" width="30%">檔案名稱</th>
		<th class="listTH" width="50%">檔案說明</th>
		<th class="listTH" width="10%">刪除</th>
	</tr>
	</thead>
	<tbody>
		<%=objMedex0303.getAddFile()%>
	</tbody>
	<%} %>
	<tr>
		<td nowrap class="td_form">回覆內容</td>
		<td nowrap class="td_form_white" colspan="3">
			<textarea class="field" name="replyContent" cols="80" rows="5"><%=objMedex0303.getReplyContent()%></textarea>
		</td>
	</tr>
</table>
