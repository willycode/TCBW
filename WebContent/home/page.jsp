<%@ page contentType="text/html;charset=UTF-8" %>
<%
	try{
	com.kangdainfo.common.util.SuperBean queryBean = (com.kangdainfo.common.util.SuperBean)request.getAttribute("QueryBean");
   	int pageSize = queryBean.getPageSize();
   	int totalPage = queryBean.getTotalPage();
   	int currentPage = queryBean.getCurrentPage();   	
   	int totalRecord = queryBean.getTotalRecord();   	
   	int recordStart = queryBean.getRecordStart();
   	int recordEnd = queryBean.getRecordEnd();   	
   	if (queryBean.isPOJO()) {
   		recordStart = recordStart + 1;
   		//recordEnd = recordEnd + 1;   	
   	}   	 	
	/**
	if(((pageSize<=0) || (totalRecord<=0) || (totalPage<=1))&&totalRecord<=10) {
		StringBuilder sb = new StringBuilder();
    	sb.append("<input type='hidden' name='pageSize' value='" ).append( pageSize ).append("'>");
       	sb.append("<input type='hidden' name='currentPage' value='" + currentPage + "'>");
       	sb.append("<input type='hidden' name='listContainerActiveRowId' value='" ).append( queryBean.getListContainerActiveRowId() ).append("'>");       	
    	return;
    }
	**/
%>
<script type="text/javascript">
var pageListener = new Object();
pageListener.beforeGotoPage = function(){
    // do nothing,for override.
};

function gotoPage(iPageNo)
{
	if(isNaN(form1.pageSize.value)){		
		alert(" 請輸入數值格式的頁碼!!");
		form1.pageSize.value = "<%=pageSize%>";
		return false;
	}
    pageListener.beforeGotoPage();
    if (isObj(form1.state)) form1.state.value = "queryAll";
   	form1.currentPage.value = iPageNo;
   	form1.submit();
}
</script>

	<input type="hidden" name="currentPageSize" value="<%=pageSize%>">
	<input type="hidden" name="currentPage" value="<%=currentPage%>"> 
	<input type="hidden" name="listContainerActiveRowId" value="<%=queryBean.getListContainerActiveRowId()%>">	
	<%	
	if(((pageSize<=0) || (totalRecord<=0) || (totalPage<=1))&&totalRecord<=10) {
		return;	
	}
	%>	
    <table width="99%" border="0" align="center">
      <tr>
        <td nowrap class="querytext" valign="middle"><span class="pagetext" >一頁<input type="text" class="field_Q" name="pageSize" maxlength="4" size="4" value="<%=pageSize%>" autocomplete="off" onchange="gotoPage(<%=currentPage%>);" style="font-size: 12px;">筆, 共<%=totalRecord%>筆 第<%=recordStart%>到<%=recordEnd%>筆</span></td>
 	<%
 	if(totalPage>1){
 	%>       
        <td nowrap class="querytext" valign="middle">
        <div align="right"><span class="pagetext" >
        			  共<%=totalPage%>頁</span>
				   [ <a class="pagetext" href="javascript: gotoPage(1)">第一頁</a>
				   <%
					for(int i=0;i<totalPage;i++)
					{
						if(i==currentPage-1)
						{
							out.println("<span class=\"pagetext\" style=\"color:red\">" + Integer.toString(i+1) + "</span>");

						}
						else if((currentPage-6<=i) && (i<=currentPage+5))
						{
							out.println("<a class=\"pagetext\" href=\"javascript:javascript: gotoPage("+(i+1)+")\">" + (i+1)+"</a>");
						}

					}
    
				%>
				<a class="pagetext" href="javascript: gotoPage(<%=totalPage%>)">末頁</a> ]
<%				
                if(currentPage>1){
%>
                [<a class="pagetext" href="javascript: gotoPage(<%=currentPage-1%>)">上一頁</a>]
<%
                }
				if(currentPage<totalPage){
%>
                [<a class="pagetext" href="javascript: gotoPage(<%=currentPage+1%>)">下一頁</a>]
<%			    
				}  
 	}
%>        		
                
	 	</div>
	 	</td>
      </tr>
    </table>
<%
    }catch(Exception x){
        x.printStackTrace();
    }

%>
