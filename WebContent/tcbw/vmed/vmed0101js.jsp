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
	
	if (isObj(document.all.item("t5")))
	{
	  document.getElementById("t5").className = "tab_border2";
	  document.getElementById("aTab5").className = "";
	  document.getElementById("Tab5").style.display = 'none';	
	}
	
	if (isObj(document.all.item("t6")))
	{
	  document.getElementById("t6").className = "tab_border2";
	  document.getElementById("aTab6").className = "";
	  document.getElementById("Tab6").style.display = 'none';	
	}
	
	if (isObj(document.all.item("t7")))
	{
	  document.getElementById("t7").className = "tab_border2";
	  document.getElementById("aTab7").className = "";
	  document.getElementById("Tab7").style.display = 'none';	
	}
	
	if (isObj(document.all.item("t8")))
	{
	  document.getElementById("t8").className = "tab_border2";
	  document.getElementById("aTab8").className = "";
	  document.getElementById("Tab8").style.display = 'none';	
	}
	
	if (isObj(document.all.item("t9")))
	{
	  document.getElementById("t9").className = "tab_border2";
	  document.getElementById("aTab9").className = "";
	  document.getElementById("Tab9").style.display = 'none';	
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
	else if(tabId == 5)
	{
		document.getElementById("t5").className = "tab_border1";
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";
	}
	else if(tabId == 6)
	{
		document.getElementById("t6").className = "tab_border1";
		document.getElementById("Tab6").style.display = '';
		document.getElementById("aTab6").className = "text_w";
	}
	else if(tabId == 7)
	{
		document.getElementById("t7").className = "tab_border1";
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";
	}
	else if(tabId == 8)
	{
		document.getElementById("t8").className = "tab_border1";
		document.getElementById("Tab8").style.display = '';
		document.getElementById("aTab8").className = "text_w";
	}
	else if(tabId == 9)
	{
		document.getElementById("t9").className = "tab_border1";
		document.getElementById("Tab9").style.display = '';
		document.getElementById("aTab9").className = "text_w";
	}
	else
	{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}



</script>

