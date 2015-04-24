<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
var pageListener = new Object();
pageListener.beforeGotoPage = function(){
    // do nothing,for override.
}

function gotoPage(iPageNo)
{
    pageListener.beforeGotoPage();
    if (isObj(form1.state)) form1.state.value = "queryAll";
	form1.currentPage.value = iPageNo;
	form1.submit();
}
</script>
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
   	}
%>
	<input type="hidden" name="currentPageSize" value="<%=pageSize%>">
	<input type="hidden" name="currentPage" value="<%=currentPage%>"> 
	<input type="hidden" name="listContainerActiveRowId" value="<%=queryBean.getListContainerActiveRowId()%>">
	<%
	if((pageSize<=0) || (totalRecord<=0) || (totalPage<=1)) {
		return;	
	}
	%>	
    <table width="99%" border="0" align="center">
      <tr>
        <td nowrap class="querytext" valign="middle"><span class="pagetext" ><input type="hidden" name="pageSize" value="<%=pageSize%>" autocomplete="off" onchange="gotoPage(<%=currentPage%>);" style="font-size: 12px;">共<%=totalRecord%>筆 第<%=recordStart%>到<%=recordEnd%>筆</span></td>
        
        <td nowrap class="querytext" valign="middle">
        <div align="right">
				   [ <a class="pagetext" href="javascript: gotoPage(1)">第一筆</a>
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
				<a class="pagetext" href="javascript: gotoPage(<%=totalPage%>)">最後一筆</a> ][
<%				
                if(currentPage>1){
%>
                <a class="pagetext" href="javascript: gotoPage(<%=currentPage-1%>)">上一筆</a>
<%
                }
				if(currentPage<totalPage){
%>
                <a class="pagetext" href="javascript: gotoPage(<%=currentPage+1%>)">下一筆</a>
<%			    
				}  
%>        		
                ]
	 	</div>
	 	</td>
      </tr>
    </table>
<%
    }catch(Exception x){
        x.printStackTrace();
    }

%>
