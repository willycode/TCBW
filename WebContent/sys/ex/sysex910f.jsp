<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<html>
<head>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript">
function isObj(obj){
	return (!((obj==null)||(obj==undefined)));
}

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/, ''); };

function isIE() {
	var nav = navigator.userAgent.toLowerCase();
	if ((nav.indexOf("msie") != -1)) return true;
	else return false;
}

function getRemoteData(uri,q,asyn)  {
	var x ;
	if(window.XMLHttpRequest) x = new XMLHttpRequest();
	else x = new ActiveXObject("Microsoft.XMLHTTP"); 
	if (isObj(asyn) && asyn != null && asyn == true) {
		x.open('Post',uri,true); 		
	} else 	x.open('Post',uri,false); 
	x.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	x.setRequestHeader( "If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT" );
	x.send(encodeURI('q='+q));
	return x.responseText.trim();
}


function getCodeName(codeObj, nameObj) {
	if (codeObj.value!='' ) {
		var url = '../../ajax/jsonCode.jsp';
		var q = codeObj.value+"&codeKind=TIT";
		var x = getRemoteData(url,q);
		if (x!=null && x.length>0) {
			var json = eval ('(' + x + ')');
			nameObj.value = json.codeName;		
		}
	} else {
		nameObj.value = '';
	}
}

function getSubCodeList(srcObj, subCodeObj) {
	if (srcObj.value!='' ) {
		var url = '../../ajax/jsonCodeKind.jsp';
		var q = srcObj.value;
		var x = getRemoteData(url,q);
		if (x!=null && x.length>0) {
			var json = JSON.parse(x); //eval ('(' + x + ')');
			form1.codeId.value = json.id;
			form1.codeName.value = json.codeKindName;
			if (json.commonCodes!=null && json.commonCodes.length>0) {
				var code = json.commonCodes;
				subCodeObj.options.length = 0;
				for (var i=0; i<code.length; i++) {
					var op = new Option(code[i].codeId+'-'+code[i].codeName, code[i].id);
					subCodeObj.add(op);
				}
				
			}
		}
	}
}
</script>
</head>
<body>
<form name="form1" id="form1" autocomplete="off">
代碼:<input type="text" name="codeId" onChange="getCodeName(this,document.all.item('codeName'));">
名稱:<input type="text" name="codeName">

<br>
<select name="codeKindId" onChange="getSubCodeList(this, form1.subCodeList);">
	<option value=""></option>
	<option value="TIT">TIT</option>
	<option value="MSG">MSG</option>
</select>
<select name="subCodeList">
</select>
<input type="submit">
</form>
</body>
</html>