<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0201F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<% 
obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0201F) obj.doQueryOne0201();
%>
<script type="text/javascript">
//主管衛生局查詢
function popHealthData(type){
	var prop="";
	var windowTop=120;
	var windowLeft=120;
	var v = "04";
	var p = "SDRG0201_"+type;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open(getVirtualPath() +"/home/popUserJob.jsp?v="+v+"&p="+p,'popWinE',prop);	
}

//訊息來源控制:
//選擇"不良品通報回收"，需再輸入"通報編號"
//選擇"國外警訊回收"，需再輸入"警訊編號"
function checkPlnoInput(){
	var len = form1.msgsource.length;
	for(i=0; i<len; i++){
		if(form1.msgsource[i].checked){
			if("01"==form1.msgsource[i].value){
				document.getElementById("cureapplno").style.display = '';
				document.getElementById("quaapplno").style.display = 'none';
				form1.quaapplno.value="";
				return;
			}else if ("03"==form1.msgsource[i].value){
				document.getElementById("cureapplno").style.display = 'none';
				document.getElementById("quaapplno").style.display = '';
				form1.cureapplno.value="";
				return;
			}else{
				document.getElementById("cureapplno").style.display = 'none';
				document.getElementById("quaapplno").style.display = 'none';
				form1.cureapplno.value="";
				form1.quaapplno.value="";
			}
		}
	}
}

//以通報編號查詢藥品通報資料
function showDrg01Data(){
	var cureapplno = form1.cureapplno.value;	
	if(cureapplno != ''){
		var v = cureapplno;
		x = getRemoteData(getVirtualPath() + "/ajax/jsonDrg0001Db.jsp?v="+v,"");
		if(x!=null && x.length>0){
			var json1 = JSON.parse(x);
			var drg01ID = json1.id;  //id		
			var isPop = "A";	
			var prop = "";
			var windowTop=(document.body.clientHeight-400)/2+100;
			var windowLeft=(document.body.clientWidth-400)/2+100;
			prop=prop+"width=1250,height=720,";
			prop=prop+"top="+windowTop+",";
			prop=prop+"left="+windowLeft+",";
			prop=prop+"scrollbars=yes,resizable=yes";
			closeReturnWindow();
			returnWindow=window.open(getVirtualPath() + "tcbw/drgin/drgin0102q.jsp?isPop="+isPop+"&id="+drg01ID,"",prop);
		}else{
			alert("此通報編號:"+cureapplno+"查無資料!");
		}
	}else{
		alert("請先輸入通報編號!");
	}	
}

//以警訊編號查詢國內外藥品品質警訊資料
function showDrg03Data(){
	var quaapplno = form1.quaapplno.value;	
	if(quaapplno != ''){
		var v = quaapplno;
		x = getRemoteData(getVirtualPath() + "/ajax/jsonDrg7001Db.jsp?v="+v,"");
		if(x!=null && x.length>0){
			var json1 = JSON.parse(x);
			var drg03ID = json1.id;  //id		
			var formType = "sdrg0202";	
			var prop = "";
			var windowTop=(document.body.clientHeight-400)/2+100;
			var windowLeft=(document.body.clientWidth-400)/2+100;
			prop=prop+"width=1250,height=720,";
			prop=prop+"top="+windowTop+",";
			prop=prop+"left="+windowLeft+",";
			prop=prop+"scrollbars=yes,resizable=yes";
			closeReturnWindow();
			returnWindow=window.open(getVirtualPath() + "tcbw/vdrg/vdrg0100f.jsp?formType="+formType+"&id="+drg03ID,"",prop);
		}else{
			alert("此警訊編號:"+quaapplno+"查無資料!");
		}
	}else{
		alert("請先輸入通報編號!");
	}	
}
</script>
   <table id="Tab2" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="20%">發文日期</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field","postDate",obj.getPostDate())%>               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >回收文號</td>
			<td nowrap class="td_form_white" colspan="3">
			   <input class="field" type="text" name="postNo" size="30" maxlength="50" value="<%=obj.getPostNo()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >回收分級</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getCommonCodeRadioBoxOption("field", "recycleclass", "DRGRECCLAS", obj.getRecycleclass(), null, "", "") %>           
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >訊息來源</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getCommonCodeRadioBoxOption("field", "msgsource", "DRGRECSR", obj.getMsgsource(), null, "", "","checkPlnoInput();") %> 
			   <input class="field" type="text" name="msgsourcedesc" size="30" maxlength="50" value="<%=obj.getMsgsourcedesc()%>">            
			</td>
		</tr>	
		<tr id="cureapplno" style="display:none">
			<td nowrap class="td_form" >通報編號</td>
			<td nowrap class="td_form_white" colspan="3">
			   <input class="field" type="text" name="cureapplno" size="20" maxlength="14" value="<%=obj.getCureapplno()%>">
			   <input class="field_Q" type="button" name="btnPlno" onClick="showDrg01Data();" value="檢視通報資料"  >	             
			</td>		
		</tr>	
		<tr id="quaapplno" style="display:none">
			<td nowrap class="td_form" >警訊編號</td>
			<td nowrap class="td_form_white" colspan="3"> 
			   <input class="field" type="text" name="quaapplno" size="20" maxlength="14" value="<%=obj.getQuaapplno()%>">
			   <input class="field_Q" type="button" name="btnPlno2" onClick="showDrg03Data();" value="檢視警訊資料"  >               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >回收期限</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field","recycledeadline",obj.getRecycledeadline())%>                
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >主管衛生局1</td>
			<td nowrap class="td_form_white" colspan="3">			  
			   <input class="field" type="hidden" name="healthbureau1" size="10" value="<%=obj.getHealthbureau1()%>" />
	  	       <input class="field_RO" type="text" name="healthbureau1Name" size="30" maxlength="50"  value="<%=obj.getHealthbureau1Name()%>" />
			   <input class="field" type="button" name="btnQryExpense" onClick="popHealthData(1);" value="查詢" width="120px" class="field" >                
			</td>			
		</tr>
		<tr>
		    <td nowrap class="td_form" >主管衛生局2</td>
			<td nowrap class="td_form_white" colspan="3">			  
			   <input class="field" type="hidden" name="healthbureau2" size="10" value="<%=obj.getHealthbureau2()%>" />
	  	       <input class="field_RO" type="text" name="healthbureau2Name" size="30" maxlength="50"  value="<%=obj.getHealthbureau2Name()%>" />
			   <input class="field" type="button" name="btnQryExpense" onClick="popHealthData(2);" value="查詢" width="120px" class="field" >                
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">展延資訊單</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >申請展延日期</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field_RO","q_extendate",obj.getQ_extendate())%>                
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >展延理由</td>
			<td nowrap class="td_form_white" colspan="3">
			   <input class="field_RO" type="text" name="q_extenreason" size="30" maxlength="50"  value="<%=obj.getQ_extenreason()%>" />               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >展延後回收期限</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field_RO","q_extendeadline",obj.getQ_extendeadline())%>                 
			</td>
		</tr>				
	</table>	


