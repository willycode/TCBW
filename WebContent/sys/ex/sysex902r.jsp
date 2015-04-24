<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.common.view.sys.ex.SYSEX900R">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
      
<html>
<head>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript">
//*********************************************
//函數功能：下拉式機關選單
//傳 回 值：無
//*********************************************
function initDeptLevel0(deptLevel0,deptLevel1,deptLevel2,selectValue){
	//清除相關資料
	var obj0 = null;
  if (isObj(document.all.item(deptLevel0))) {
		obj0 = document.all.item(deptLevel0);
		obj0.options.length=0;
		obj0.options.add(new Option("請選擇",""));
		
		if (isObj(document.all.item(deptLevel1))) {
			var obj1 = document.all.item(deptLevel1);
			obj1.options.length=0;
			obj1.options.add(new Option("請選擇",""));				
		}
		
		if (isObj(document.all.item(deptLevel2))) {
			var obj2 = document.all.item(deptLevel2);
			obj2.options.length=0;
			obj2.options.add(new Option("請選擇",""));				
		}
		var hql = "select id, fullName from CommonDepartment where level=0 order by fullCode";
		var x = getRemoteData(getVirtualPath() + "ajax/jsonObjects.jsp", q);
		if (x!=null && x.length>0) {
			var json = eval('(' + x + ')'); 
			var i = 0;
			for (i=0; i<json.length; i++) {
				var astId =  json[i].obj0;			
				var oOption = new Option(json[i].obj1,astId);
				obj0.options.add(oOption);
		    	if(astId == selectValue) oOption.selected=true;			
			}
		}
  }
}

//*********************************************
//函數功能：下拉式機關選單, 改變第0層
//傳 回 值：無
//*********************************************
function changeDeptLevel0(deptLevel0,deptLevel1,deptLevel2,selectValue){
	var q=document.all.item(deptLevel0).value;	
	//清除相關資料
	if (isObj(document.all.item(deptLevel1))) {		
		var obj1 = document.all.item(deptLevel1);
		obj1.options.length=0;
		obj1.options.add(new Option("請選擇",""));
		
		if (isObj(document.all.item(deptLevel2))) {
			var obj2 = document.all.item(deptLevel2);
			obj2.options.length=0;
			obj2.options.add(new Option("請選擇",""));				
		}			
		if (q!=""){	
			var x = getRemoteData(getVirtualPath() + "ajax/jsonDept.jsp?pid="+q, "");
			if (x!=null && x.length>0) {
				var json = eval('(' + x + ')'); 
				var i = 0;
				for (i=0; i<json.length; i++) {
					var astId =  json[i].Id;			
					var oOption = new Option(json[i].fullName,astId);
					obj1.options.add(oOption);
			    	if(astId == selectValue) oOption.selected=true;			
				}
			}
		}		
	}
}

//*********************************************
//函數功能：機關選單, 改變第1層
//傳 回 值：無
//*********************************************
function changeDeptLevel1(deptLevel0,deptLevel1,deptLevel2,selectValue){
	var q=document.all.item(deptLevel1).value;	
	//清除相關資料
	if (isObj(document.all.item(deptLevel2))) {		
		var obj1 = document.all.item(deptLevel2);
		obj1.options.length=0;
		obj1.options.add(new Option("請選擇",""));
		
		if (q!=""){	
			var x = getRemoteData(getVirtualPath() + "ajax/jsonDept.jsp?pid="+q, "");
			if (x!=null && x.length>0) {
				var json = eval('(' + x + ')'); 
				var i = 0;
				for (i=0; i<json.length; i++) {
					var astId =  json[i].Id;			
					var oOption = new Option(json[i].fullName,astId);
					obj1.options.add(oOption);
			    	if(astId == selectValue) oOption.selected=true;			
				}
			}
		}		
	}
}

</script>

</head>
<body>
<form id="form1" name="form1" method="post" autocomplete="off">

	<table class="table_form" border="1">
	<tr>
	  <td nowrap class="td_form">機關層級0：</td>
	  <td nowrap class="td_form_white"><select name="deptLevel0" id="deptLevel0" class="field_P" onChange="changeDeptLevel0('deptLevel0','deptLevel1','deptLevel2',this.value);">
			<%=View.getOption("select id, fullName from CommonDepartment where level=0 order by fullCode","") %>
		</select>

	  </td>
	  </tr>
	<tr>
	  <td nowrap class="td_form">機關層級1：</td>
	  <td nowrap class="td_form_white">
	    <select name="deptLevel1" id="deptLevel1" class="field_P" onChange="changeDeptLevel1('deptLevel0','deptLevel1','deptLevel2',this.value);">
	    		      
	      </select>

	  </td>
	  </tr>	  
	<tr>
	  <td nowrap class="td_form">機關層級2：</td>
	  <td nowrap class="td_form_white">
	    <select name="deptLevel2" id="deptLevel2" class="field_P">
	      	
	      </select>

	  </td>
	  </tr>		  
	</table>
	
<input type="hidden" name="state" value="<%=obj.getState()%>"/>
</body>
</html>