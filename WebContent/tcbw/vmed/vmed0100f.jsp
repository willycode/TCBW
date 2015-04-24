<!--
程式目的：國內外藥品品質警訊警訊登錄作業
程式代號：vmed0100f
程式日期：1030512
程式作者：yuwen
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vmed.VMED0100F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="objList0301" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="objList0701" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="objList1001" scope="page" class="java.util.ArrayList"/>
<%
String okMsg = "";

if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) 
{
	obj.setHttpRequest(pageContext.getRequest());
	obj.insert();
	if ("insertSuccess".equals(obj.getState()))
	{
		if("maintain0".equals(obj.getActionType()))
		{
			okMsg = "暫存完成";
		}
		if("maintain1".equals(obj.getActionType()))
		{
			okMsg = "送件完成";
		}
	}
}
else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) 
{
	obj.setHttpRequest(pageContext.getRequest());
	obj.update();
	if ("updateSuccess".equals(obj.getState()))
	{
		if("maintain0".equals(obj.getActionType()))
		{
			okMsg = "暫存完成";
		}
		else if("maintain1".equals(obj.getActionType()))
		{
			okMsg = "送件完成";
		}
		else if("maintain3".equals(obj.getActionType()))
		{
			okMsg = "廠商確認完成";
		}
		else if("maintain6".equals(obj.getActionType()))
		{
			okMsg = "廠商回覆完成";
		}
		else if("maintain7".equals(obj.getActionType()))
		{
			okMsg = "摘譯完成";
		}
		else if("maintain8".equals(obj.getActionType()))
		{
			okMsg = "摘譯複審完成";
		}
		else if("maintain9".equals(obj.getActionType()))
		{
			okMsg = "廠商摘譯確認完成";
		}
		else if("maintain10".equals(obj.getActionType()))
		{
			okMsg = "廠商摘譯確認完成";
		}
		else if("maintain11".equals(obj.getActionType()))
		{
			okMsg = "公告完成";
		}
		else if("maintain14".equals(obj.getActionType()))
		{
			okMsg = "改版完成";
		}
		else if("maintain15".equals(obj.getActionType()))
		{
			okMsg = "複審完成";
		}
	}
}
else if ("upload".equals(obj.getState())) 
{
	obj.update();
}
else
{
	obj.setQueryAllFlag("true");
}

if(null != okMsg && !"".equals(okMsg))
{
	obj.setErrorMsg(okMsg);
}
else
{
	obj.setState("queryOne");
	obj.setQueryAllFlag("true");
}


if ("true".equals(obj.getQueryAllFlag()))
{
	  obj.setUserID(User.getUserId());
	  
	  obj.setId(Common.get(request.getParameter("id")));
	  
	  obj = (com.kangdainfo.tcbw.view.vmed.VMED0100F)obj.queryOne();
	  
	  objList0301 = obj.doQueryAll0301(obj.getId());
	  objList0701 = obj.doQueryAll0701(obj.getId());
	  objList1001 = obj.doQueryAll1001(obj.getId());
}


%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="med7002Layout.js"></script>
<script type="text/javascript">
var insertDefault = new Array(
		new Array("status","00"),Array("dataRevDate",'<%=Datetime.getYYYMMDD()%>')
);
function checkField()
{
	var alertStr="";
	if(form1.state.value=="queryAll")
	{
		alertStr += checkQuery();
	}
	else if(form1.state.value=="insert"||form1.state.value=="insertError"
		||form1.state.value=="update"||form1.state.value=="updateError")
	{
		
		if("0101" == form1.formType.value)
		{
			alertStr += checkEmpty(form1.publDept,"發佈單位");
			alertStr += checkRadioButton(form1.situation,"警訊類別");
		}

		if("0301" == form1.formType.value)
		{
			alertStr += checkRadioButton(form1.iswarning7002,"本許可證是否為警訊內容產品");

			var b=form1.iswarning7002;

			if (b!=null && b.length>0) 
			{
				for (j=0; j<b.length; j++) 
				{
					if(b[j].checked && b[j].value=='O')
					  alertStr += checkEmpty(form1.iswarningOther7002,"其他");

					if(b[j].checked && b[j].value=='Y')
					  alertStr += checkRadioButton(form1.iseffectinternal7002,"是否國內有受影響產品");
				}
			}
		}
		
		if("0401" == form1.formType.value)
		{
			alertStr += checkRadioButton(form1.isrecycle,"是否涉及回收");
			alertStr += checkRadioButton(form1.istranslate,"是否摘譯");
			
			var a=form1.istranslate;
			if (a!=null && a.length>0) 
			{
				for (j=0; j<a.length; j++) 
				{
					if(a[j].checked && a[j].value=='Y')
					  alertStr += checkEmpty(form1.translateman,"摘譯人員");
				}
			}

			var b=form1.iswarning7002;
			if (b!=null && b.length>0) 
			{
				for (j=0; j< b.length; j++) 
				{
					if(b[j].checked && b[j].value=='O')
					  alertStr += checkEmpty(form1.iswarningOther7002,"其他");

					if(b[j].checked && b[j].value=='Y')
					  alertStr += checkRadioButton(form1.iseffectinternal7002,"是否國內有受影響產品");
				}
			}


			
		}

		if("0801" == form1.formType.value)
		{
			alertStr += checkEmpty(form1.completecheckdate7005,"完成確認日期");
		}

		if("0901" == form1.formType.value)
		{
			alertStr += checkEmpty(form1.publdate7005,"警訊公告");
		}
		

	}

	if(alertStr.length!=0){ alert(alertStr); return false; }

	beforeSubmit();
	form1.submit();
	return true;
}

function init()
{

    var tbID=document.all.item("changeTabValue").value;

    if(tbID=="")
      changeTab(form1.tabId.value);
    else
	  changeTab(tbID);

	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden,spanConfirm','H');

	setDisplayItem('spanMaintain1,spanMaintain3,spanMaintain4,spanMaintain5,spanMaintain6,spanMaintain7,spanMaintain8,spanMaintain9,spanMaintain10,spanMaintain11,spanMaintain12,spanMaintain13,spanMaintain14,spanMaintain15,spanMaintain20,spanMaintain21,spanMaintain22,spanMaintain23','H');

	$("#Tab1").find('.toolbar_default').attr('disabled',true);
	$("#Tab1").find('.toolbar_default').attr('readOnly',true);
	$("#Tab3").find('.toolbar_default').attr('disabled',true);
	$("#Tab3").find('.toolbar_default').attr('readOnly',true);
	$("#Tab5").find('.toolbar_default').attr('disabled',true);
	$("#Tab5").find('.toolbar_default').attr('readOnly',true);
	$("#Tab6").find('.toolbar_default').attr('disabled',true);
	$("#Tab6").find('.toolbar_default').attr('readOnly',true);
	$("#Tab7").find('.toolbar_default').attr('disabled',true);
	$("#Tab7").find('.toolbar_default').attr('readOnly',true);
	$("#Tab9").find('.toolbar_default').attr('disabled',true);
	$("#Tab9").find('.toolbar_default').attr('readOnly',true);
	 
	if("0101" == form1.formType.value)
	{
		 
        if("" != form1.status.value &&  "00" != form1.status.value && "01" != form1.status.value)      
		   setDisplayItem('spanUpdate,spanClear,spanMaintain0,spanMaintain1','H');
        else
           setDisplayItem('spanMaintain1','S');
            
        if(isObj(document.getElementById("t2")))
			document.getElementById("t2").style.display = 'none';
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t4")))
			document.getElementById("t4").style.display = 'none';
		if(isObj(document.getElementById("t5")))
			document.getElementById("t5").style.display = 'none';
		if(isObj(document.getElementById("t6")))
			document.getElementById("t6").style.display = 'none';
		if(isObj(document.getElementById("t7")))
			document.getElementById("t7").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
		
	}
	else if("0201" == form1.formType.value)
	{
		setDisplayItem('spanMaintain3,spanMaintain4,spanMaintain5','S');
		
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t4")))
			document.getElementById("t4").style.display = 'none';
		if(isObj(document.getElementById("t5")))
			document.getElementById("t5").style.display = 'none';
		if(isObj(document.getElementById("t6")))
			document.getElementById("t6").style.display = 'none';
		if(isObj(document.getElementById("t7")))
			document.getElementById("t7").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
	}
	else if("0301" == form1.formType.value) //廠商回覆(外部)
	{
		
		setDisplayItem('spanMaintain20','S');

		if(isObj(document.getElementById("t2")))
			document.getElementById("t2").style.display = 'none';
		if(isObj(document.getElementById("t4")))
			document.getElementById("t4").style.display = 'none';
		if(isObj(document.getElementById("Tab4")))
			document.getElementById("Tab4").style.display = 'none';
		if(isObj(document.getElementById("t5")))
			document.getElementById("t5").style.display = 'none';
		if(isObj(document.getElementById("t6")))
			document.getElementById("t6").style.display = 'none';
		if(isObj(document.getElementById("t7")))
			document.getElementById("t7").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';

		if(form1.replydate7002.value!="")
		{
			setDisplayItem("spanUpdate,spanClear,spanMaintain0,spanMaintain1,maintain20","H");
		}	
		
	}
	else if("0401" == form1.formType.value)// 廠商回覆清單
	{
		setDisplayItem('spanMaintain6','S');

		if(isObj(document.getElementById("t2")))
			document.getElementById("t2").style.display = 'none';
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t5")))
			document.getElementById("t5").style.display = 'none';
		if(isObj(document.getElementById("t6")))
			document.getElementById("t6").style.display = 'none';
		if(isObj(document.getElementById("t7")))
			document.getElementById("t7").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
	}
	else if("0501" == form1.formType.value)//警訊摘譯 > > 摘譯中
	{
		setDisplayItem('spanMaintain7,spanMaintain23','S');
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t6")))
			document.getElementById("t6").style.display = 'none';
		if(isObj(document.getElementById("t7")))
			document.getElementById("t7").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
	}
	else if("0601" == form1.formType.value)// 警訊摘譯 > > 複審中
	{
		setDisplayItem('spanMaintain8','S');
		
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t6")))
			document.getElementById("t6").style.display = 'none';
		if(isObj(document.getElementById("t7")))
			document.getElementById("t7").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
	}
	else if("0701" == form1.formType.value)//摘譯確認中(外部)
	{
		setDisplayItem('spanMaintain9','S');
	
		if(isObj(document.getElementById("t2")))
			document.getElementById("t2").style.display = 'none';
		
		if(isObj(document.getElementById("t4")))
			document.getElementById("t4").style.display = 'none';

		if(isObj(document.getElementById("Tab4")))
			document.getElementById("Tab4").style.display = 'none';
		
		if(isObj(document.getElementById("t7")))
			document.getElementById("t7").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
		
		form1.checktranslatedate7004.disabled=true;
		
		if(isObj(document.getElementById("trCompletecheckdate")))
			document.getElementById("trCompletecheckdate").style.display = 'none';

		if(isObj(document.getElementById("trCompletecheckdate1")))
			document.getElementById("trCompletecheckdate1").style.display = 'none';
		
		if("" !=form1.checktranslatedate7004.value)
	    {    
			setDisplayItem("spanUpdate,spanClear,spanMaintain0,spanMaintain1,maintain9","H");
	    }
		
		
	}
	else if("0801" == form1.formType.value)//摘譯確認中
	{
		setDisplayItem('spanMaintain10','S');
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t7")))
			document.getElementById("t7").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
	}
	else if("0901" == form1.formType.value)//警訊公告
	{
		setDisplayItem('spanMaintain11','S');
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
	}
	else if("1001" == form1.formType.value)//公告改版
	{
		setDisplayItem('spanMaintain14','S');
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
	}
	else if("1101" == form1.formType.value)//公告改版
	{
		setDisplayItem('spanMaintain15','S');
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
	}
	else if("1201" == form1.formType.value)//警訊回收
	{
		//setDisplayItem('spanMaintain15','S');
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';

		if("05"!='<%=Dept.getShortCode()%>')
		{
		  if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
		}
	}
	else if("1301" == form1.formType.value)
	{
		setDisplayItem('update,clear,spanMaintain0,spanMaintain1,spanMaintain3','H');
		
		setDisplayItem('spanMaintain12,spanMaintain13,spanMaintain21,spanMaintain22','S');

		if(isObj(document.getElementById("t2")))
			document.getElementById("t2").style.display = 'none';
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';

		if("05"!='<%=Dept.getShortCode()%>')
		{
		  if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
		}
	}
	else if("1401" == form1.formType.value)
	{

		setDisplayItem('insert,update,clear,spanMaintain0,spanMaintain1,spanMaintain3,maintain2','H');

		changeTab(4);
		
		if(isObj(document.getElementById("t1")))
			document.getElementById("t1").style.display = 'none';
		if(isObj(document.getElementById("t2")))
			document.getElementById("t2").style.display = 'none';
		if(isObj(document.getElementById("t3")))
			document.getElementById("t3").style.display = 'none';
		if(isObj(document.getElementById("t5")))
			document.getElementById("t5").style.display = 'none';
		if(isObj(document.getElementById("t6")))
			document.getElementById("t6").style.display = 'none';
		if(isObj(document.getElementById("t7")))
			document.getElementById("t7").style.display = 'none';
		if(isObj(document.getElementById("t8")))
			document.getElementById("t8").style.display = 'none';
		if(isObj(document.getElementById("t9")))
			document.getElementById("t9").style.display = 'none';
	}
	
	setFormItem("maintain0,doUploadMed71,addNewMed72s","R");
	
	if(null == form1.status.value || "" == form1.status.value)
	{
		setDisplayItem('spanInsert','S');
		whatButtonFireEvent("insert");
	}

	if("maintain1" == "<%=obj.getActionType()%>" || "maintain3" == "<%=obj.getActionType()%>"
		    || "maintain6" == "<%=obj.getActionType()%>" || "maintain7" == "<%=obj.getActionType()%>"
			|| "maintain8" == "<%=obj.getActionType()%>" || "maintain9" == "<%=obj.getActionType()%>" 
			|| "maintain10" == "<%=obj.getActionType()%>" || "maintain11" == "<%=obj.getActionType()%>" 
			|| "maintain14" == "<%=obj.getActionType()%>" || "maintain15" == "<%=obj.getActionType()%>" 
			|| "maintain20" == "<%=obj.getActionType()%>"  || "maintain12" == "<%=obj.getActionType()%>" 
			|| "maintain13" == "<%=obj.getActionType()%>" 
		)
	{
		var action = "vmed"+form1.formType.value+"f.jsp";

        if('<%=obj.getErrorMsg()%>'=='本許可證是否為警訊內容產品欄位需填寫!')
        {
           return false;
        }
        else
        {    
	       form1.action = action;
		   form1.actionType.value = "";
		   form1.state.value = "queryAll";
		   form1.submit();
        }

        window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}

	
}

function beforeInit() {
	<%=obj.getConJSBuilder()%>
	<%=obj.getConMED060002JSBuilder()%>
	<%=obj.getConMED060003JSBuilder()%>
	<%=obj.getConMED060004JSBuilder()%>
	<%=obj.getConMED060005JSBuilder()%>
	<%=obj.getConMED060006JSBuilder()%>
	<%=obj.getConMED060007JSBuilder()%>
	<%=obj.getMedJSBuilder()%>
}

function changeTab(tabId) 
{
	form1.tabId.value = tabId;
	form1.changeTabValue.value = tabId;
	
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("aTab1").className = "";
	document.getElementById("Tab1").style.display = 'none';

	if(isObj(document.getElementById("t2")))
	{
		document.getElementById("t2").className = "tab_border2";
	}
	
	if(isObj(document.getElementById("t3")))
	{
		document.getElementById("t3").className = "tab_border2";
	}

	if(isObj(document.getElementById("t4")))
	{
		document.getElementById("t4").className = "tab_border2";
	}

	if(isObj(document.getElementById("t5")))
	{
		document.getElementById("t5").className = "tab_border2";
	}
	if(isObj(document.getElementById("t6")))
	{
		document.getElementById("t6").className = "tab_border2";
	}

	if(isObj(document.getElementById("t7")))
	{
		document.getElementById("t7").className = "tab_border2";
	}
	
	if(isObj(document.getElementById("t8")))
	{
		document.getElementById("t8").className = "tab_border2";
	}

	if(isObj(document.getElementById("t9")))
	{
		document.getElementById("t9").className = "tab_border2";
	}
	
	if(isObj(document.getElementById("aTab2")))
	{
		document.getElementById("aTab2").className = "";
	}
	
	if(isObj(document.getElementById("aTab3")))
	{
		document.getElementById("aTab3").className = "";
	}

	if(isObj(document.getElementById("aTab4")))
	{
		document.getElementById("aTab4").className = "";
	}

	if(isObj(document.getElementById("aTab5")))
	{
		document.getElementById("aTab5").className = "";
	}

	if(isObj(document.getElementById("aTab6")))
	{
		document.getElementById("aTab6").className = "";
	}

	if(isObj(document.getElementById("aTab7")))
	{
		document.getElementById("aTab7").className = "";
	}

	if(isObj(document.getElementById("aTab8")))
	{
		document.getElementById("aTab8").className = "";
	}

	if(isObj(document.getElementById("aTab9")))
	{
		document.getElementById("aTab9").className = "";
	}
	
	if(isObj(document.getElementById("Tab2")))
	{
		document.getElementById("Tab2").style.display = 'none';
	}
	
	if(isObj(document.getElementById("Tab3")))
	{
		document.getElementById("Tab3").style.display = 'none';
	}

	if(isObj(document.getElementById("Tab4")))
	{	
	   document.getElementById("Tab4").style.display = 'none';
	}

	if(isObj(document.getElementById("Tab5")))
	{	
	   document.getElementById("Tab5").style.display = 'none';
	}

	if(isObj(document.getElementById("Tab6")))
	{	
	   document.getElementById("Tab6").style.display = 'none';
	}
	
	if(isObj(document.getElementById("Tab7")))
	{	
	   document.getElementById("Tab7").style.display = 'none';
	}

	if(isObj(document.getElementById("Tab8")))
	{	
	   document.getElementById("Tab8").style.display = 'none';
	}

	if(isObj(document.getElementById("Tab9")))
	{	
	   document.getElementById("Tab9").style.display = 'none';
	}
	
	if(tabId == 2)
	{
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";		
	}
	else if (tabId == 3)
	{
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";	
	}
	else if (tabId == 4)
	{
		document.getElementById("t4").className = "tab_border1";
		document.getElementById("aTab4").className = "text_w";		
		document.getElementById("Tab3").style.display = '';
		document.getElementById("Tab4").style.display = '';
	}	
	else if (tabId == 5)
	{
		document.getElementById("t5").className = "tab_border1";	
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";	
	}
	else if (tabId == 6)
	{
		document.getElementById("t6").className = "tab_border1";	
		document.getElementById("Tab6").style.display = '';
		document.getElementById("aTab6").className = "text_w";	
	}
	else if (tabId == 7)
	{
		document.getElementById("t7").className = "tab_border1";	
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";	
	}
	else if (tabId == 8)
	{
		document.getElementById("t8").className = "tab_border1";	
		document.getElementById("Tab8").style.display = '';
		document.getElementById("aTab8").className = "text_w";	
	}
	else if (tabId == 9)
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

function submitBefore(buttonName,mailID,isJS,isAdd,isApplicationId)
{
	var isSendMail = false;

	if(null != mailID && "" != mailID)
	{
		isSendMail = true;
		if("MED060001" == mailID)
		{
			var medRows=document.getElementsByName("medRow");	
			
			if("20" != form1.publDeptCodeId.value && "21" != form1.publDeptCodeId.value && medRows!=null && medRows.length>0)
			{
				if("01" == form1.recycleclass.value)
				{
					mailID = "MED060002";
				}
			}
			else
			{
				isSendMail = false;
			}

		}

	}

	if(isSendMail)
	{
		var parm = "isAdd="+isAdd+"&applNo="+form1.applNo.value+"&isJS="+isJS+"&mailID="+mailID+"&id="+form1.id.value+"&systemType=MED";
		var prop = "";
		var windowTop=(document.body.clientHeight-400)/2+100;
		var windowLeft=(document.body.clientWidth-400)/2+100;
		prop=prop+"width=1200,height=600,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes,resizable=yes";
		closeReturnWindow();
		if(isApplicationId)
		{
			window.open(getVirtualPath() + "home/popSendMail.jsp?"+parm,"",prop);
		}
		else
		{
			window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?"+parm,"",prop);
		}
		
	}
	else
	{
		whatButtonFireEvent(buttonName);
	}

}

function choesePermitKeyNo(queryType)
{
	queryShow('queryContainer');
	document.all.item('spanMlms').style.display="none";
	document.all.item('spanMed01').style.display="none";
	if("1" == queryType)
	{
		document.all.item('spanMlms').style.display="block";
	}
	else if("2" == queryType)
	{
		document.all.item('spanMed01').style.display="block";
	}
}

function toShowMLMS()
{
	if(null != form1.q_permitKeyNo.value && "" != form1.q_permitKeyNo.value){
		var permitKey = form1.q_permitKeyNo.value.substring(0,2);
		var permitNo = form1.q_permitKeyNo.value.substring(2);	
		var params = 'width=980,height=640,resizable=1,menubar=no,scrollbars=yes';
		closeReturnWindow();
		window.open(getVirtualPath() + "tcbw/medin/medin0204f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',params);	
		whatButtonFireEvent("queryCannel");
		
	}else{
		alert("請選擇一筆藥證資料!!");
	}
}

function toShowMed01()
{
	if(null != form1.q_permitKeyNo.value && "" != form1.q_permitKeyNo.value)
	{
		var permitKey = form1.q_permitKeyNo.value.substring(0,2);
		var permitNo = form1.q_permitKeyNo.value.substring(2);	
		var params = 'width=980,height=640,resizable=1,menubar=no,scrollbars=yes';
		closeReturnWindow();
		window.open(getVirtualPath() + "tcbw/medin/medin2001q.jsp?isPop=Y&q_medPermit="+permitKey+"&q_medPermitNumber="+permitNo,'popWinE',params);	
		whatButtonFireEvent("queryCannel");
		
	}
	else
	{
		alert("請選擇一筆藥證資料!!");
	}
}

function queryOne(id)
{
	if("3" == form1.tabId.value)
	{	
      form1.id7002.value=id;
	  form1.state.value = "queryOne";
	}
	else if("4" == form1.tabId.value)
	{	
      form1.id7002.value=id;
	  form1.state.value = "queryOne";
	}
	else if("6" == form1.tabId.value)
	{	
      form1.id7004.value=id;
	  form1.state.value = "queryOne";
	}
	else if("8" == form1.tabId.value)
	{	
      form1.id7003.value=id;
	  form1.state.value = "queryOne";
	}
	
	beforeSubmit();
	form1.submit();
}

//重新啟案
function rePlaycase(id) 
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	var id = id;
	var applNo = applNo;
	var eventKind = eventKind;
	//帶案件id
	var q = "id=" + id;

	var a=form1.istranslate;
	var istranslateStr="";
	if (a!=null && a.length>0) 
	{
		for (j=0; j<a.length; j++) 
		{
			if(a[j].checked && a[j].value=='N')
				istranslateStr="N";
		}
	}
	
    if ( form1.status.value=='90' && ( istranslateStr=='N' || '<%=objList0301.size()%>'=='0'))
    {    
    	prop=prop+"width=800,height=300,";
    	prop=prop+"top="+windowTop+",";
    	prop=prop+"left="+windowLeft+",";
    	prop=prop+"scrollbars=yes,resizable=yes";
    	closeReturnWindow();
    	returnWindow=window.open(getVirtualPath() + "tcbw/vmed/vmed1302f.jsp?" + q,"",prop);
    }
    else
    {
    	alert("此案件無法重新啟案!");
        return false;
    }    
	
}

//公告改版
function reNoticerevision(id) 
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	var id = id;
	var applNo = applNo;
	var eventKind = eventKind;

	//帶案件id
	var q = "id=" + id;

	var a=form1.istranslate;
	var istranslateStr="";
	
	if (a!=null && a.length>0) 
	{
		for (j=0; j<a.length; j++) 
		{
			if(a[j].checked && a[j].value=='Y')
				istranslateStr="Y";
		}
	}
	
    if ( form1.status.value=='90' &&  istranslateStr=='Y' )
    {    
    	prop=prop+"width=800,height=300,";
    	prop=prop+"top="+windowTop+",";
    	prop=prop+"left="+windowLeft+",";
    	prop=prop+"scrollbars=yes,resizable=yes";
    	closeReturnWindow();
    	returnWindow=window.open(getVirtualPath() + "tcbw/vmed/vmed1303f.jsp?" + q,"",prop);
    }
    else
    {
    	alert("此案件無法公告改版!");
        return false;
    } 
      
   
	
}

function doBack()
{
	alert("公告改版完成!!");
	form1.action = "vmed1301f.jsp";
	form1.state.value = "queryAll";
	form1.submit();
	window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
}

var win = null;
function openUploadWindow(popFieldFileName, popFieldFileRoute, fileShowName)
{
  
    if(form1.fileName.value=="")
    {
    	alert("請先產生Word檔案!");
		return ;
    }

	if (isObj(eval("form1." + popFieldFileName))) 
	{
	    var prop = '';
	    var w = (screen.width-450)/2;
		var h = (screen.height-160)/2;
	    prop = prop + 'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes';
	    prop = prop + ',width=450,';
	    prop = prop + ',height=160';
	    prop = prop + ',left=' + w;
	    prop = prop + ',top=' + h;
	    if(win != null) win.close();
	    win = window.open('../../home/uploadFileHiddenRoute.jsp?popFieldFileName=' + popFieldFileName + '&popFieldFileRoute=' + popFieldFileRoute + "&popFileType=MED&allowExt=1&systemType=MED060004&fileId=word"+form1.id.value+"&fileShowName=" + fileShowName,'上傳檔案',prop);
	} 
	else 
	{
		alert("欄位不存在，請檢查!");
		return ;
	}
}

function openDownloadWindow(fileName, fileRoute, fileShowName)
{
    var prop='';
    prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=yes,scrollbars=yes,resizable=yes,';
    prop=prop+'width=400,';
    prop=prop+'height=400';
	if (fileName!=null && fileName.length>0) 
	{
	    var url = getVirtualPath() + "downloadFileSimple?fileID=" + fileRoute + ':;:' + fileName + "&fileType=MED";
	    if(win != null) win.close();
		win = window.open(url,'popDownload',prop);		
	} 
	else 
	{
		alert("目前沒有任何檔案可供下載!");	
	}
}

function toShowVmed0401()
{
	var params = 'width=1200,height=640,resizable=1,menubar=no,scrollbars=yes';
	closeReturnWindow();
	window.open(getVirtualPath() + "tcbw/vmed/vmed0100f.jsp?formType=1401&id="+form1.id.value,'popWinE',params);	
}


</script>
</head>

<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<iframe name="saveWordFrame" width="0" height="0" frameborder="0">
</iframe>

<!--Query區============================================================-->
<div id="queryContainer" style="width:300px;height:100px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" width="100%">
	<tr>
		<td class="td_form">許可證字號：</td>
		<td class="td_form_white">
			<select name="q_permitKeyNo" class="field_Q">
				<%=View.getOption("select med.permitKey+med.permitNo, c.codeName+med.permitNo from Med7002Db med , CommonCode c where med.permitKey = c.codeId and c.commonCodeKind.codeKindId = 'MEDPKID' and med.med7001Db.id = "+Common.getLong(obj.getId()),"") %>
			</select>	
		</td>
	</tr> 
	<tr id="spanMlms" style="display:none">
		<td class="queryTDInput" colspan="2" style="text-align:center;">
			<input class="toolbar_default" followPK="false" type="button" name="queryMLMS" value="確　　定" onClick="toShowMLMS()">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	<tr id="spanMed01" style="display:none">
		<td class="queryTDInput" colspan="2" style="text-align:center;">
			<input class="toolbar_default" followPK="false" type="button" name="queryMed01" value="確　　定" onClick="toShowMed01()">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
	<tr><td class="bgToolbar" style="text-align:left">
		<input type="hidden" name="id" value="<%=obj.getId()%>">
		<input type="hidden" name="id7002" value="<%=obj.getId7002()%>">
		<input type="hidden" name="id7003" value="<%=obj.getId7003()%>">
		<input type="hidden" name="id7004" value="<%=obj.getId7004()%>">
		<input type="hidden" name="id7005" value="<%=obj.getId7005()%>">
		<input type="hidden" name="changeMaxVersion7003" value="<%=obj.getChangeMaxVersion7003()%>">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
		<input type="hidden" name="userID" value="<%=User.getId()%>">
		<input type="hidden" name="editID" value="<%=obj.getEditID()%>">
		<input type="hidden" name="editDate" value="<%=obj.getEditDate()%>">
		<input type="hidden" name="tabId" value ="<%=obj.getTabId()%>">
		<input type="hidden" name="actionType" value="<%=obj.getActionType()%>">
		<input type="hidden" name="formType" value="<%=obj.getFormType()%>">
		<jsp:include page="../../home/toolbar.jsp" />
		<span id="spanMaintain0">
			<input class="toolbar_default" type="button" followPK="false" name="maintain0" value="暫　存" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain1">
			<input class="toolbar_default" type="button" followPK="false" name="maintain1" value="送　出" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain3">
			<input class="toolbar_default" type="button" followPK="false" name="maintain3" value="確 認 完 成" onClick="submitBefore(this.name,'MED060001','window.opener.whatButtonFireEvent(\''+this.name+'\');','N',1);">&nbsp;
		</span>
		<span id="spanMaintain4">
			<input class="toolbar_default" type="button" followPK="false" name="maintain4" value="檢視藥證資料" onClick="choesePermitKeyNo(1);">&nbsp;
		</span>
		<span id="spanMaintain5">
			<input class="toolbar_default" type="button" followPK="false" name="maintain5" value="檢視通報案件資料" onClick="choesePermitKeyNo(2);">&nbsp;
		</span>
		<span id="spanMaintain6">
			<input class="toolbar_default" type="button" followPK="false" name="maintain6" value="回覆完成" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain7">
			<input class="toolbar_default" type="button" followPK="false" name="maintain7" value="摘譯完成" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain8">
			<input class="toolbar_default" type="button" followPK="false" name="maintain8" value="複審完成" onClick="submitBefore(this.name,'MED060004','window.opener.whatButtonFireEvent(\''+this.name+'\');','N',1);">&nbsp;
		</span>
		<span id="spanMaintain9">
			<input class="toolbar_default" type="button" followPK="false" name="maintain9" value="確認完成" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain10">
			<input class="toolbar_default" type="button" followPK="false" name="maintain10" value="確認完成" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain11">
			<input class="toolbar_default" type="button" followPK="false" name="maintain11" value="公告完成" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain12">
			<input class="toolbar_default" type="button" followPK="false" name="maintain12" value="重新啟案" onClick="rePlaycase(form1.id.value);">&nbsp;
		</span>
		<span id="spanMaintain13">
			<input class="toolbar_default" type="button" followPK="false" name="maintain13" value="公告改版" onClick="reNoticerevision(form1.id.value);">&nbsp;
		</span>
		<span id="spanMaintain14">
			<input class="toolbar_default" type="button" followPK="false" name="maintain14" value="改版完成" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain15">
			<input class="toolbar_default" type="button" followPK="false" name="maintain15" value="複審完成" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain20">
			<input class="toolbar_default" type="button" followPK="false" name="maintain20" value="回覆完成" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		<span id="spanMaintain21">
		    <input class="toolbar_default" type="button" followPK="false" name="maintain21" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	    </span>
	    <span id="spanMaintain22">
	        <input class="toolbar_default" type="button" followPK="false" name="maintain22" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">	
		</span>
		<span id="spanMaintain23">
	        <input class="toolbar_default" type="button" followPK="false" name="maintain23" value="查看廠商回覆" onClick="toShowVmed0401();">	
		</span>
		<span id="spanMaintain2">
			<input class="toolbar_default" type="button" followPK="false" name="maintain2" value="返 回 查 詢" onClick="submitBefore(this.name,null,null,null,null);">&nbsp;
		</span>
		
	</td></tr>
</table>
<table width="100%" cellspacing="0" cellpadding="0">
	<tr><td>
	<TABLE CELLPADDING=0 CELLSPACING=0>
		<TR>
			<td nowrap ID="t1" CLASS="tab_border1" ><a id="aTab1" href="#" onClick="changeTab(1);">登錄</a></td>
			<td nowrap ID="t2" CLASS="tab_border2" ><a id="aTab2" href="#" onClick="changeTab(2);">廠商確認</a></td>
			<td nowrap ID="t3" CLASS="tab_border2" ><a id="aTab3" href="#" onClick="changeTab(3);">廠商回覆</a></td>
			<td nowrap ID="t4" CLASS="tab_border2" ><a id="aTab4" href="#" onClick="changeTab(4);">廠商回覆</a></td>
			<td nowrap ID="t5" CLASS="tab_border2" ><a id="aTab5" href="#" onClick="changeTab(5);">警訊摘譯</a></td>
		    <td nowrap ID="t6" CLASS="tab_border2" ><a id="aTab6" href="#" onClick="changeTab(6);">摘譯確認</a></td>
		    <td nowrap ID="t7" CLASS="tab_border2" ><a id="aTab7" href="#" onClick="changeTab(7);">警訊公告</a></td>
		    <td nowrap ID="t8" CLASS="tab_border2" ><a id="aTab8" href="#" onClick="changeTab(8);">公告改版</a></td>
		    <td nowrap ID="t9" CLASS="tab_border2" ><a id="aTab9" href="#" onClick="changeTab(9);">警訊回收</a></td>
		</TR>
	</TABLE>
	</td></tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" id="Tab1" width="100%" align="center">
	<tr>
		<td nowrap class="td_form" width="15%">案件編號：</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_RO" type="text" name="applNo" size="11" maxlength="11" value="<%=obj.getApplNo()%>">
		</td>
		<td nowrap class="td_form" width="15%">案件狀態：</td>
		<td nowrap class="td_form_white" width="35%">
			<select class="field_RO" name="status">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDQTSTATUS' order by codeId", obj.getStatus())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">發佈單位：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field","publDept",obj.getPublDept(),obj.getPublDeptName(),"","CONPUBLDEPT","publDeptCodeId",obj.getPublDeptCodeId())%>
		</td>
		<td nowrap class="td_form">警訊類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field","situation","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONWARNING' order by codeId",obj.getSituation())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">醫材主類別：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getPopCode("field","medMainCategory",obj.getMedMainCategory(),obj.getMedMainCategoryName(),"","MEDMCT","medMainCategoryCodeId",obj.getMedMainCategoryCodeId())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">產品名稱：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="chProduct" size="50" maxlength="50" value="<%=obj.getChProduct()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">國外回收等級：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field","recycleclass","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDRCCLASS' order by codeId",obj.getRecycleclass())%>
		</td>
		<td nowrap class="td_form">資料收集日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalndar("field","dataRevDate",obj.getDataRevDate())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">發佈日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalndar("field","publDate",obj.getPublDate())%>
		</td>
		<td nowrap class="td_form">製造廠：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="manufactorName" size="50" maxlength="100" value="<%=obj.getManufactorName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">警訊摘要：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="contextsummary" size="125" maxlength="125" value="<%=obj.getContextsummary()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">產品型號及批號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="productlotNo" size="50" maxlength="50" value="<%=obj.getProductlotNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">廠商行動：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="applicationaction" size="100" maxlength="100" value="<%=obj.getApplicationaction()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">影響地區：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="effectarea" size="50" maxlength="50" value="<%=obj.getEffectarea()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">影響數量：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="effectnum" size="50" maxlength="50" value="<%=obj.getEffectnum()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">原始網頁：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="datasourWebSite" size="150" maxlength="150" value="<%=obj.getDatasourWebSite()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">附件：</td>
		<td nowrap class="td_form_white" colspan="3">
			<table width="100%" align="center" class="table_form">
			   <tr>
				    <td class="bgToolbar">
						<span id="spanDoUpload">
							<input class="toolbar_default" type="button" followPK="false" name="doUploadMed71" value="附件上傳" onClick="upload('Med7001Db','MED060001',form1.id.value)">&nbsp;
						</span>
					</td>
				</tr>
				<tr>
				    <td class="bg">
					<jsp:include page="conmed01Layout.jsp">
						<jsp:param name="filetype" value="MED060001" />
					</jsp:include>
				    </td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
	
	<input class="field_Q" type="hidden" name="changeTabValue" value="<%=obj.getChangeTabValue()%>">
	
	<table class="table_form" id="Tab2" width="100%" align="center">
		<tr>
			<td>
				<input class="toolbar_default" type="button" followPK="false" name="addNewMed72s" value="批次新增許可證" onClick="popPermitView();">
			</td>
		</tr>
		<tr>
	 		<td>
	 			<table width="100%" cellspacing="0" cellpadding="0" id="medTable"></table>
	 		</td>
	 	</tr>
	</table>
	
	<table class="table_form" id="Tab4" width="100%" align="center">
	<tr>
		<td nowrap class="td_form_left" colspan="4">中心回覆</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">是否涉及國內回收：</td>
		<td nowrap class="td_form_white" width="35%">
		    <%=View.getRadioBoxTextOption("field","isrecycle","Y;是;N;否",obj.getIsrecycle())%>
	    </td>
	    <td nowrap class="td_form" width="15%">是否摘譯：</td>
		<td nowrap class="td_form_white" width="35%">
		     <%=View.getRadioBoxTextOption("field","istranslate","Y;是;N;否",obj.getIstranslate())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form" >摘譯人員：</td>
	    <td nowrap class="td_form_white"  colspan="3">
	        <input type="text" class="field" size="50" maxlength="25" name="translateman" value="<%=obj.getTranslateman()%>"/>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form" >國內受影響醫材型號：</td>
	    <td nowrap class="td_form_white"  colspan="3">
	        <input type="text" class="field" size="50" maxlength="50" name="medModel" value="<%=obj.getMedModel()%>"/>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">國內受影響醫材批號：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <input type="text" class="field" size="50" maxlength="50" name="lotNo" value="<%=obj.getLotNo()%>"/>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">國內情形：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="incountrysituation" rows="3" cols="50"><%=obj.getIncountrysituation()%></textarea>
	    </td>
	</tr>
	</table>
	
	<table class="table_form" id="Tab3" width="100%" align="center">
	<tr>
		<td nowrap class="td_form_left" colspan="4">廠商回覆</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >許可證字：</td>
		<td nowrap class="td_form_white" colspan="3">
		    <select name="permitKey7002" class="field_RO">
	           <%=View.getOptionCodeKind("MEDPKID",obj.getPermitKey7002())%>
	        </select>
	                      第
	        <input class="field_RO" type="text" size="14" name="permitNo7002" value="<%=obj.getPermitNo7002()%>">
	       
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form" >中文品名：</td>
	    <td nowrap class="td_form_white"  colspan="3">
	        <input type="text" class="field_RO" size="50" name="chProduct7002" value="<%=obj.getChProduct7002()%>"/>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form" >英文品名：</td>
	    <td nowrap class="td_form_white"  colspan="3">
	        <input type="text" class="field_RO" size="50" name="enProduct7002" value="<%=obj.getEnProduct7002()%>"/>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form" width="15%">許可證核准日期：</td>
	    <td nowrap class="td_form_white" width="35%">
	        <input class="field_RO" type="text" size="7"  name="medapprovedate7002" value="<%=obj.getMedapprovedate7002()%>">
	    </td>
	    <td nowrap class="td_form" width="15%">許可證有效日期：</td>
	    <td nowrap class="td_form_white"  width="35%">
	        <input class="field_RO" type="text" size="7" name="medEffectiveDate7002" value="<%=obj.getMedEffectiveDate7002()%>">
	      
		</td>
	</tr>
	<tr>
	    <td nowrap class="td_form">許可證持有商：</td>
	    <td nowrap class="td_form_white" colspan="3">
		    <input type="hidden" class="field" name="applicationId"/>
			<input type="text" class="field_RO" size="50" name="applicationName7002" value="<%=obj.getApplicationName7002()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">製造廠：</td>
		<td nowrap class="td_form_white" colspan="3">
		    <input type="text" class="field_RO" size="50"  name="manufactorName7002" value="<%=obj.getManufactorName7002()%>"/>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">製造廠國別：</td>
	    <td nowrap class="td_form_white">
		    <input type="text" class="field_RO" size="25"  name="manufactorCountry7002" value="<%=obj.getManufactorCountry7002()%>"/>
		</td>
		<td nowrap class="td_form">醫材級數：</td>
		<td nowrap class="td_form_white">
			<input type="text" class="field_RO" size="1"  name="medclass7002" value="<%=obj.getMedclass7002()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">醫材主類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_RO","medMainCategoryName7002","",Common.get(View.getOneCodeName("MEDMCT",Common.get(obj.getMedMainCategory7002()))),"","MEDMCT","medMainCategory7002",obj.getMedMainCategory7002())%>
		</td>
		<td nowrap class="td_form">醫材次類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_RO","medSecCategoryName7002","",Common.get(View.getOneCodeName("MEDSCT",Common.get(obj.getMedSecCategory7002()))),"","MEDSCT","medSecCategory7002",obj.getMedSecCategory7002())%>
		</td>
	</tr>
	
	<%if("0301".equals(obj.getFormType())){ %>
	<tr>
	    <td nowrap class="td_form">回覆日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field_RO","replydate7002",obj.getReplydate7002())%>
	    </td>
	</tr>
	<%}else{%>
	<tr>
	    <td nowrap class="td_form">回覆日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field","replydate7002",obj.getReplydate7002())%>
	    </td>
	</tr>
	<%}%>
	
	<tr>
	    <td nowrap class="td_form">本許可證是否為<br>警訊內容產品：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getRadioBoxTextOption("field","iswarning7002","Y;是;N;否;W;本公司未登記此產品;O;其他",obj.getIswarning7002())%>
	        <input type="text" class="field" size="50" maxlength="50" name="iswarningOther7002" value="<%=obj.getIswarningOther7002()%>"/>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">是否國內有受影響產品：</td>
	    <td nowrap class="td_form_white" colspan="3">
	         <%=View.getRadioBoxTextOption("field","iseffectinternal7002","Y;是;N;否",obj.getIseffectinternal7002())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">回覆摘要：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="replysummary7002" rows="3" cols="60"><%=obj.getReplysummary7002()%></textarea>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">施行對象：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="applyObject7002" rows="3" cols="60"><%=obj.getApplyObject7002()%></textarea>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">建議行動：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="proposedAction7002" rows="3" cols="60"><%=obj.getProposedAction7002()%></textarea>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">摘譯初稿：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="excerptDraft7002" rows="3" cols="60"><%=obj.getExcerptDraft7002()%></textarea>
	    </td>
	</tr>
	
	<tr>
	    <td nowrap class="td_form">醫材型號：</td>
	    <td nowrap class="td_form_white">
		    <input type="text" class="field" size="30" maxlength="25" name="medModel7002" value="<%=obj.getMedModel7002()%>"/>
		</td>
		<td nowrap class="td_form">醫材批號：</td>
		<td nowrap class="td_form_white">
			<input type="text" class="field" size="30" maxlength="25"  name="lotNo7002" value="<%=obj.getLotNo7002()%>"/>
		</td>
	</tr>
	<tr>
	    <td nowrap class="td_form">受影響數量：</td>
	    <td nowrap class="td_form_white">
		    <input type="text" class="field" size="30" maxlength="25" name="effectnum7002" value="<%=obj.getEffectnum7002()%>"/>
		</td>
		<td nowrap class="td_form">聯絡窗口：</td>
		<td nowrap class="td_form_white">
			<input type="text" class="field" size="30" maxlength="25"  name="checkcontactman7002" value="<%=obj.getCheckcontactman7002()%>"/>
		</td>
	</tr>
	<tr>
	    <td nowrap class="td_form">聯絡電話：</td>
	    <td nowrap class="td_form_white">
		    <input type="text" class="field" size="20" maxlength="10" name="checkcontacttel7002" value="<%=obj.getCheckcontacttel7002()%>"/>
		</td>
		<td nowrap class="td_form">聯絡信箱：</td>
		<td nowrap class="td_form_white">
			<input type="text" class="field" size="40" maxlength="50"  name="checkcontactemail7002" value="<%=obj.getCheckcontactemail7002()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">附件：</td>
		<td nowrap class="td_form_white" colspan="3">
			<table width="100%" align="center" class="table_form">
			    <tr>
				    <td class="bgToolbar">
					<span id="spanDoUpload">
						<input class="toolbar_default" type="button" followPK="false" name="doUploadMed72" value="附件上傳" onClick="upload('Med7002Db','MED060002',form1.id7002.value)">&nbsp;
					</span>
					</td>
				</tr>
				<tr>
				    <td class="bg">
					<jsp:include page="conmed01Layout.jsp">
						<jsp:param name="filetype" value="MED060002" />
					</jsp:include>
				</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td nowrap class="bgList" colspan="5">
		<div id="listContainer" style="height:auto">
		<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="listTHEAD">
			<tr>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">回覆日期</a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">許可證字</a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">中文品名</a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">許可證持有商</a></th>
			    <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">是否為警訊內容產品</a></th>
			    <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">是否國內有受影響產品</a></th>
			</tr>
			</thead>
			<tbody id="listTBODY">
			<%
				boolean primaryArray[] = {true,false,false,false,false,false,false};
			    boolean displayArray[] = {false,true,true,true,true,true,true};
				out.write(View.getQuerylist(primaryArray,displayArray,null,objList0301,obj.getQueryAllFlag(),true,null,null,"",false));
			%>
			</tbody>
			</table>
			</div>
			</td>
	</tr>
	</table>
	
	<table class="table_form" id="Tab5" width="100%" align="center">
	<tr>
	    <td nowrap class="td_form" width="15%">摘譯日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field","translatedate7005",obj.getTranslatedate7005())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">複審日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field_RO","recheckdate7005",obj.getRecheckdate7005())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">摘譯內容：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="translatecontext7005" rows="8" cols="80"><%=obj.getTranslatecontext7005()%></textarea>
	    </td>
	</tr>
    <tr>
       <td nowrap class="td_form_white" colspan="4">
			<input class="toolbar_default" type="button" followPK="false" name="maintain17" value="產生WORD" onClick="whatButtonFireEvent(this.name);">&nbsp;
	   </td>
	</tr>
	<tr>
       <td nowrap class="td_form_white" colspan="4">
           <input type="text" readonly class="field_RO" name="showFileName"  size="80" maxlength="300" value="<%=obj.getShowFileName()%>">
           <input type="hidden" name="fileName" value="<%=obj.getFileName()%>">
           <input type="hidden" name="fileNameRoute" value="<%=obj.getFileNameRoute()%>"> 
           <input type="button" class="field" name="btn_FileName" onclick="openUploadWindow('fileName','fileNameRoute','showFileName');" value="上傳檔案">
    	   <input type="button" class="field_Q" name="btn_FileNameDownload" onclick="openDownloadWindow(form1.fileName.value,form1.fileNameRoute.value,form1.showFileName.value);" value="下載檔案">
	    </td>
	</tr>
	</table>
	<table class="table_form" id="Tab6" width="100%" align="center">
	<tr id="trCompletecheckdate1">
		<td nowrap class="td_form_left" colspan="4">中心摘譯確認</td>
	</tr>
	<tr id="trCompletecheckdate">
	    <td nowrap class="td_form" width="15%">中心完成確認日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field","completecheckdate7005",obj.getCompletecheckdate7005())%>
	    </td>
	</tr>
	<tr>
		<td nowrap class="td_form_left" colspan="4">廠商摘譯確認</td>
	</tr>
	<tr>
	    <td nowrap class="td_form" width="15%">廠商完成確認日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field_RO","checktranslatedate7004",obj.getChecktranslatedate7004())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">許可證持有商：</td>
	    <td nowrap class="td_form_white" colspan="3">
            <input class="field_RO" type="text" size="10" name="applicationID7004" value="<%=obj.getApplicationID7004()%>">
            <input class="field_RO" type="text" size="50" name="applicationName7004" value="<%=obj.getApplicationName7004()%>">
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">廠商通知日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field_RO","noticetranslatedate7004",obj.getNoticetranslatedate7004())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">廠商確認摘要：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="checkcontextdesc7004" rows="8" cols="80"><%=obj.getCheckcontextdesc7004()%></textarea>
	    </td>
	</tr>
	<tr>
		<td nowrap class="td_form">附件：</td>
		<td nowrap class="td_form_white" colspan="3">
			<table width="100%" align="center" class="table_form">
				<tr>
				    <td class="bgToolbar">
					<span id="spanDoUpload">
						<input class="toolbar_default" type="button" followPK="false" name="doUploadMed72" value="附件上傳" onClick="upload('Med7004Db','MED060003',form1.id7004.value)">&nbsp;
					</span>
					</td>
				</tr>
				<tr>
				    <td class="bg">
					<jsp:include page="conmed01Layout.jsp">
						<jsp:param name="filetype" value="MED060003" />
					</jsp:include>
				</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td nowrap class="bgList" colspan="5">
		<div id="listContainer" style="height:auto">
		<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="listTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">許可證持有商統編</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">許可證持有商</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">廠商通知日期</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">廠商確認日期</a></th>
		</tr>
		</thead>
		<tbody id="listTBODY">
		<%
			boolean primaryArray1[] = {true,false,false,false,false};
			boolean displayArray1[] = {false,true,true,true,true};
		    out.write(View.getQuerylist(primaryArray1,displayArray1,null,objList0701,obj.getQueryAllFlag(),true,null,null,"",false));
		%>
		</tbody>
		</table>
		</div>
		</td>
	</tr>
	</table>
	
	<table class="table_form" id="Tab7" width="100%" align="center">
	<tr>
	    <td nowrap class="td_form">公告日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field","publdate7005",obj.getPubldate7005())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">標題：</td>
	    <td nowrap class="td_form_white" colspan="3">
            <input class="field" type="text" size="80" maxlength="50"  name="subject7005" value="<%=obj.getSubject7005()%>">
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">內容：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="context7005" rows="8" cols="80"><%=obj.getContext7005()%></textarea>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">備註：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="remark7005" rows="8" cols="80"><%=obj.getRemark7005()%></textarea>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">資料來源：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <input class="field" type="text" size="80" maxlength="75" name="datasource7005" value="<%=obj.getDatasource7005()%>">
	    </td>
	</tr>
	<tr>
		<td nowrap class="td_form">附件：</td>
		<td nowrap class="td_form_white" colspan="3">
			<table width="100%" align="center" class="table_form">
				<tr>
				    <td class="bgToolbar">
					<span id="spanDoUpload">
						<input class="toolbar_default" type="button" followPK="false" name="doUploadMed72" value="附件上傳" onClick="upload('Med7005Db','MED060004',form1.id7005.value)">&nbsp;
					</span>
					</td>
				</tr>
			    <tr>
				    <td class="bg">
					<jsp:include page="conmed01Layout.jsp">
						<jsp:param name="filetype" value="MED060004" />
					</jsp:include>
				    </td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否發佈新聞稿：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxTextOption("field","ispublnews7005","Y;是;N;否",obj.getIspublnews7005())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">是否發佈消費者知識服務網：</td>
		<td nowrap class="td_form_white" width="35%">
		   <%=View.getRadioBoxTextOption("field","ispublconsumer7005","Y;是;N;否",obj.getIspublconsumer7005())%>
		</td>
		<td nowrap class="td_form" width="15%">消費者知識服務網上架日期：</td>
		<td nowrap class="td_form_white"  width="35%">
		   <%=View.getPopCalendar("field","publconsumerDate7005",obj.getPublconsumerDate7005())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否發佈署網：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field","isfdaweb7005","Y;是;N;否",obj.getIsfdaweb7005())%>
		</td>
		<td nowrap class="td_form">燈號：</td>
		<td nowrap class="td_form_white" >
		   <%=View.getRadioBoxOption("field", "lamp7005", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONLAMP' order by codeId", obj.getLamp7005())%>
		</td>
	</tr>
	</table>
	<table class="table_form" id="Tab8" width="100%" align="center">
	<tr>
	    <td nowrap class="td_form" width="15%">改版版本：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <input class="field_RO" type="text" size="3"  name="changeVersion7003" value="<%=obj.getChangeVersion7003()%>">
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form" width="15%">改版日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field","changedate7003",obj.getChangedate7003())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">複審日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field_RO","changerecheckdate7003",obj.getChangerecheckdate7003())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">改版原因：</td>
	    <td nowrap class="td_form_white" colspan="3">
            <input class="field" type="text" size="80" maxlength="50"  name="changereason7003" value="<%=obj.getChangereason7003()%>">
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">改版標題：</td>
	    <td nowrap class="td_form_white" colspan="3">
            <input class="field" type="text" size="80" maxlength="50"  name="changesubject7003" value="<%=obj.getChangesubject7003()%>">
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">改版內容：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="changecontext7003" rows="8" cols="80"><%=obj.getChangecontext7003()%></textarea>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">改版備註：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="changeremark7003" rows="8" cols="80"><%=obj.getChangeremark7003()%></textarea>
	    </td>
	</tr>
	<tr>
		<td nowrap class="bgList" colspan="5">
		<div id="listContainer" style="height:auto">
		<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="listTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">改版版本</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">公告/改版日期</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">改版原因</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">改版標題</a></th>
		</tr>
		</thead>
		<tbody id="listTBODY">
		<%
			boolean primaryArray2[] = {true,false,false,false,false};
			boolean displayArray2[] = {false,true,true,true,true};
		    out.write(View.getQuerylist(primaryArray2,displayArray2,null,objList1001,obj.getQueryAllFlag(),true,null,null,"",false));
		%>
		</tbody>
		</table>
		</div>
		</td>
	</tr>
	</table>
	
	<table class="table_form" id="Tab9" width="100%" align="center">
	<tr>
	    <td nowrap class="td_form"  width="15%">FDA收文日期：</td>
	    <td nowrap class="td_form_white"  width="35%">
	        <%=View.getPopCalendar("field","fdareceiveDate",obj.getFdareceiveDate())%>
	    </td>
	    <td nowrap class="td_form"  width="15%">FDA收文字號：</td>
	    <td nowrap class="td_form_white"  width="35%">
	        <input class="field" type="text" size="20" maxlength="20"  name="fdareceiveNo" value="<%=obj.getFdareceiveNo()%>">
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">收文摘要：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="receivesummary" rows="8" cols="80"><%=obj.getReceivesummary()%></textarea>
	    </td>
	</tr>
	<tr>
		<td nowrap class="td_form">收文附件：</td>
		<td nowrap class="td_form_white" colspan="3">
			<table width="100%" align="center" class="table_form">
				<tr>
				    <td class="bgToolbar">
					<span id="spanDoUpload">
						<input class="toolbar_default" type="button" followPK="false" name="doUploadMed72" value="附件上傳" onClick="upload('Med7001Db','MED060005',form1.id.value)">&nbsp;
					</span>
					</td>
				</tr>
				<tr>
				    <td class="bg">
					<jsp:include page="conmed01Layout.jsp">
						<jsp:param name="filetype" value="MED060005" />
					</jsp:include>
				    </td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
	    <td nowrap class="td_form">FDA回文日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field","postdate",obj.getPostdate())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">回文摘要：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="posesummary" rows="8" cols="80"><%=obj.getPosesummary()%></textarea>
	    </td>
	</tr>
	<tr>
		<td nowrap class="td_form">回文附件：</td>
		<td nowrap class="td_form_white" colspan="3">
			<table width="100%" align="center" class="table_form">
				<tr>
				<td class="bgToolbar" colspan="3">
					<span id="spanDoUpload">
						<input class="toolbar_default" type="button" followPK="false" name="doUploadMed72" value="附件上傳" onClick="upload('Med7001Db','MED060006',form1.id.value)">&nbsp;
					</span>
					</td>
				</tr>
				<tr>
				    <td class="bg" colspan="3">
					<jsp:include page="conmed01Layout.jsp">
						<jsp:param name="filetype" value="MED060006" />
					</jsp:include>
				</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
	    <td nowrap class="td_form">廠商回收完成日期：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <%=View.getPopCalendar("field","completerecycledate",obj.getCompleterecycledate())%>
	    </td>
	</tr>
	<tr>
	    <td nowrap class="td_form">廠商回收摘要：</td>
	    <td nowrap class="td_form_white" colspan="3">
	        <textarea class="field"  name="completesummary" rows="8" cols="80"><%=obj.getCompletesummary()%></textarea>
	    </td>
	</tr>
	<tr>
		<td nowrap class="td_form">廠商回收狀況附件：</td>
		<td nowrap class="td_form_white" colspan="3">
			<table width="100%" align="center" class="table_form">
				<tr>
				    <td class="bgToolbar" colspan="3">
					<span id="spanDoUpload">
						<input class="toolbar_default" type="button" followPK="false" name="doUploadMed72" value="附件上傳" onClick="upload('Med7001Db','MED060007',form1.id.value)">&nbsp;
					</span>
					</td>
				</tr>
				<tr>
				    <td class="bg" colspan="3">
					<jsp:include page="conmed01Layout.jsp">
						<jsp:param name="filetype" value="MED060007" />
					</jsp:include>
				</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
	
	
	</div>
</td></tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	form1.actionType.value = buttonName;
	switch (buttonName)	
	{
		case "insert":
			setFormItem("maintain0","O");
			break;
		case "update":
			if("0101" == form1.formType.value)
			{	
			  setFormItem("maintain0,doUploadMed71,addNewMed72s","O");
			}
			else if("0201" == form1.formType.value)//廠商確認
			{	
			  setAllReadonly();
			  $("#Tab2").find('.field_btnRemove').attr('disabled',false);
			  $("#Tab2").find('.field_btnRemove').attr('readOnly',false);
			  $("#Tab2").find('.field_btnAdd').attr('disabled',false);
			  $("#Tab2").find('.field_btnAdd').attr('readOnly',false);
			  $("#Tab2").find('.field').attr('disabled',false);
			  $("#Tab2").find('.field').attr('readOnly',false);
			  setFormItem("maintain0,addNewMed72s","O");
			  setFormItem("maintain3","H");
			}
			else if("0301" == form1.formType.value)
			{
					
				  setAllReadonly();
				  setFormItem("maintain0","O");
				  $("#Tab3").find('.field').attr('disabled',false);
				  $("#Tab3").find('.field').attr('readOnly',false);
				  $("#Tab3").find('.toolbar_default').attr('disabled',false);
				  $("#Tab3").find('.toolbar_default').attr('readOnly',false);
			
			}	
			else if("0401" == form1.formType.value)
			{
				  setAllReadonly();
				  setFormItem("maintain0","O");
				  
				  $("#Tab3").find('.field').attr('disabled',false);
				  $("#Tab3").find('.field').attr('readOnly',false);
				  $("#Tab3").find('.toolbar_default').attr('disabled',false);
				  $("#Tab3").find('.toolbar_default').attr('readOnly',false);
				  $("#Tab4").find('.field').attr('disabled',false);
				  $("#Tab4").find('.field').attr('readOnly',false);
				  
				  //setFormItem("replydate7002","O");
			}	
			else if("0501" == form1.formType.value)
			{
				 setAllReadonly();
				 setFormItem("maintain0","O");
				 $("#Tab5").find('.field').attr('disabled',false);
				 $("#Tab5").find('.field').attr('readOnly',false);
				
			}	
			else if("0601" == form1.formType.value)
			{
				 setAllReadonly();
				 setFormItem("maintain0","O");
				 $("#Tab5").find('.field').attr('disabled',false);
				 $("#Tab5").find('.field').attr('readOnly',false);
				 $("#Tab5").find('.toolbar_default').attr('disabled',false);
				 $("#Tab5").find('.toolbar_default').attr('readOnly',false);
			}
			else if("0701" == form1.formType.value)
			{
				 
				 setAllReadonly();
				 setFormItem("maintain0","O");
				 $("#Tab6").find('.field').attr('disabled',false);
				 $("#Tab6").find('.field').attr('readOnly',false);
				 $("#Tab6").find('.toolbar_default').attr('disabled',false);
				 $("#Tab6").find('.toolbar_default').attr('readOnly',false);
				 form1.checktranslatedate7004.disabled=true;
				 form1.btn_checktranslatedate7004.disabled=true;

			    
 
			}
			else if("0801" == form1.formType.value)//警訊摘譯 > > 摘譯確認中
			{
				 setAllReadonly();
				 setFormItem("maintain0","O");
				 $("#Tab6").find('.field').attr('disabled',false);
				 $("#Tab6").find('.field').attr('readOnly',false);
				 $("#Tab6").find('.toolbar_default').attr('disabled',false);
				 $("#Tab6").find('.toolbar_default').attr('readOnly',false);
			}
			else if("0901" == form1.formType.value)//警訊公告
			{
				 setAllReadonly();
				 setFormItem("maintain0","O");
				 $("#Tab7").find('.field').attr('disabled',false);
				 $("#Tab7").find('.field').attr('readOnly',false);
				 $("#Tab7").find('.toolbar_default').attr('disabled',false);
				 $("#Tab7").find('.toolbar_default').attr('readOnly',false);
			}
			else if("1001" == form1.formType.value)//警訊公告改版 > > 改版中
			{
				 setAllReadonly();
				 setFormItem("maintain0","O");
				 $("#Tab8").find('.field').attr('disabled',false);
				 $("#Tab8").find('.field').attr('readOnly',false);
				 form1.changerecheckdate7003.disabled=true;
				 form1.btn_changerecheckdate7003.disabled=true;
				 if(form1.changeMaxVersion7003.value!=form1.id7003.value)
		         {
			         alert("僅可修改最新版本資料!");
					 whatButtonFireEvent("clear");
		         }
			}
			else if("1101" == form1.formType.value)//警訊公告改版 > > 複審中
			{
				 setAllReadonly();
				 setFormItem("maintain0","O");
				 $("#Tab8").find('.field').attr('disabled',false);
				 $("#Tab8").find('.field').attr('readOnly',false);
				 form1.changerecheckdate7003.disabled=true;
				 form1.btn_changerecheckdate7003.disabled=true;
				 form1.changedate7003.disabled=true;
				 form1.btn_changedate7003.disabled=true;
				 if(form1.changeMaxVersion7003.value!=form1.id7003.value)
		         {
			         alert("僅可修改最新版本資料!");
					 whatButtonFireEvent("clear");
		         } 
			}
			else if("1201" == form1.formType.value)//醫療器材回收
			{
				 setAllReadonly();
				 setFormItem("maintain0","O");
				 $("#Tab9").find('.field').attr('disabled',false);
				 $("#Tab9").find('.field').attr('readOnly',false);
				 $("#Tab9").find('.toolbar_default').attr('disabled',false);
				 $("#Tab9").find('.toolbar_default').attr('readOnly',false);
			}
			break;
		case "clear":
			setFormItem("maintain0,doUploadMed71,addNewMed72s","R");
			break;
		case "maintain0": //暫　存
			form1.state.value = "insert";
			if(null != form1.id.value && "" != form1.id.value)
			{
				form1.state.value = "update";
			}
			setBeforePageUnload(false);
			beforeSubmit();
			form1.submit();
			break;
		case "maintain1": //送　出
			form1.state.value = "update";
			if("" == form1.id.value)
			{
				form1.state.value = "insert";
			}
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain2"://返 回 查 詢
			var action = "vmed"+form1.formType.value+"f.jsp";
			form1.action = action;
			form1.state.value = "queryAll";
			form1.submit();
			break;
		case "maintain3"://確 認 完 成 --廠商確認
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain6"://回覆完成---廠商回覆
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain7"://摘譯完成-- 摘譯中
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain8"://複審完成--複審中
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain9"://確認完成--摘譯確認中(外部)
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain10"://確認完成--摘譯確認中
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain11"://公告完成--警訊公告
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain14"://改版完成--公告改版
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain15"://複審完成--公告改版
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain16"://風管組
			form1.action = "vmed1304p.jsp" ;
			form1.target = "_blank";
			beforeSubmit();
			form1.submit();
			break;
		case "maintain17"://產生word
			form1.action = "vmed0602f.jsp?id="+form1.id.value+"&applNo="+form1.applNo.value;
			form1.target = "saveWordFrame";
			beforeSubmit();
			form1.submit();
			form1.action = "vmed0100f.jsp";
			form1.target = "_self";
			alert("檔案產生成功!");
			form1.state.value = "upload";
			//setBeforePageUnload(false);
			checkField();
			break;
		case "maintain20"://回覆完成---廠商回覆
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain21":
			popCon0002Med('MED','<%=obj.getApplNo()%>');
			break;
		case "maintain22":
			popCon2001('MED06','<%=obj.getId()%>');
			break;
	}
}
</script>
</body>
</html>
