<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SYSDS805" />
</jsp:include>
<%
com.kangdainfo.common.view.sys.ds.SYSDS801F obj = new com.kangdainfo.common.view.sys.ds.SYSDS801F();
obj.doQueryProcess();
%>

<html>
<head>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/toolbar.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<style type="text/css">
<!--
	.text_link_b { 
		color: #000000; 
		text-decoration: none; 
	}
	
	.text_link_b:hover { 		 		
		color: #FF0000; 
		color: #001993;		
		text-decoration: none; 
	}
	
	.table_form {
		border: 0px solid white;
  		border-collapse: collapse;	
		background-color:#FFFFFF;
	}
		
	.td_lable  {
		text-align:center;
		background-color:#ffffd2;
		padding: 2px 5px 2px 2px;
		border-left: 1px solid silver;
		border-bottom: 1px solid silver;
		border-right: 1px solid silver;
		border-top: 1px solid silver;		
	}

	.td_lable_white  {
		text-align:center;
		background-color:#ffffff;
		padding: 2px 5px 2px 2px;
		border-left: 1px solid silver;
		border-bottom: 1px solid silver;
		border-right: 1px solid silver;
		border-top: 1px solid silver;		
	}	
-->
</style>
</head>

<body topmargin="5">
<form id="form1" name="form1" method="post">
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  
  <tr>
  <td nowrap>　上線人數總覽</td>
</tr>
  <tr>
  <td nowrap><table width="100%" align="center" class="table_form">
  <TR>  
  <td nowrap width="25%" align="center" class="td_lable">本日上線人次</TD>
  <td nowrap width="25%" class="td_lable_white"><%=obj.getVDayCount()%></TD>
  </TR>  
  <TR>  
  <td nowrap align="center" class="td_lable">本月上線人次</TD>
  <td nowrap class="td_lable_white"><%=obj.getVMonthCount()%></TD>
  </TR>  
  <TR>  
  <td nowrap align="center" class="td_lable">年度上線人次</TD>
  <td nowrap class="td_lable_white"><%=obj.getVYearCount() %></TD>
  </TR>  
  <TR>  
  <td nowrap align="center" class="td_lable">累積上線總人次</TD>
  <td nowrap class="td_lable_white"><%=obj.getVTotalCount() %></TD>
  </TR>
  <TR>  
  <td nowrap align="center" class="td_lable">最大日上線人次/日期</TD>
  <td nowrap class="td_lable_white"><%=obj.getVMaxDayCount() + " / " + obj.getVMaxDay()%></TD>
  </TR>
  <TR>  
  <td nowrap align="center" class="td_lable">最大月上線人次/月份</TD>
  <td nowrap class="td_lable_white"><%=obj.getVMaxMonthCount() + " / " + obj.getVMaxMonth()%></TD>
  </TR>
  <TR>  
  <td nowrap align="center" class="td_lable">最大年上線人次/年份</TD>
  <td nowrap class="td_lable_white"><%=obj.getVMaxYearCount() + " / " + obj.getVMaxYear()%></TD>
  </TR>
</table></td>
</tr>
  <tr>
  <td nowrap>&nbsp;</td>
</tr>
  <tr>
  <td nowrap>&nbsp;</td>
</tr>
  <tr>
  <td nowrap>　月份上線人數總覽</td>
</tr>
  <tr>
  <td nowrap>
  <table width="100%" align="center" class="table_form">
<thead id="listTHEAD">  
  <TR>
  <td nowrap width="30%" align="center" class="td_lable"><a class="text_link_b" onClick="return sortTable('listTBODY',0,false);" href="#">月份</a></TD>
  <td nowrap width="40%" class="td_lable"><a class="text_link_b" onClick="return sortTable('listTBODY',1,false);" href="#">上線人次</a></TD>
  <td nowrap width="30%" class="td_lable"><a class="text_link_b" onClick="return sortTable('listTBODY',2,false);" href="#">百分比</a></TD>
</TR>
</thead>
<tbody id="listTBODY">
<%=obj.getReportByMonth()%>
</tbody>
</table></td>
</tr>
</table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</form>
</body>
</html>



