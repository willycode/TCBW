<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">


function changeTab(tabId) 
{
	
	if (isObj(document.all.item("t1")))
	{	
	  document.getElementById("t1").className = "tab_border2";
	  document.getElementById("aTab1").className = "";
	  document.getElementById("Tab1").style.display = 'none';
	}
	
	if (isObj(document.all.item("t2")))
	{
	  document.getElementById("t2").className = "tab_border2";
	  document.getElementById("aTab2").className = "";
	  document.getElementById("Tab2").style.display = 'none';
	}

	if (isObj(document.all.item("t3")))
	{
	  document.getElementById("t3").className = "tab_border2";
	  document.getElementById("aTab3").className = "";
	  document.getElementById("Tab3").style.display = 'none';
	}

	if (isObj(document.all.item("t4")))
	{
	  document.getElementById("t4").className = "tab_border2";
	  document.getElementById("aTab4").className = "";
	  document.getElementById("Tab4").style.display = 'none';
	}


	if(tabId == 2)
	{
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";		
	}
	else if(tabId == 3)
	{
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";		
	}
	else if(tabId == 4)
	{
		document.getElementById("t4").className = "tab_border1";	
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";		
	}
	else
	{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}


</script>

