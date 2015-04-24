<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
var arrConField = new Array("fileName","fileExplan","fileRoute");
var arrConFieldName = new Array("檔案名稱","檔案說明","");
function addConRow(tblId,v1,v2,v3,v4){
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = row.uniqueID?row.uniqueID:randomUUID();
	
	var conItem = row.insertCell(0);
	conItem.innerHTML = '<input type=hidden name=conRow value=' + id + '>'+
	'<input class="field_RO" type="text" name="fileName' + id + '" value=\'' + (v1!=null?v1:'') + '\'>';
	
	
	var docWord = row.insertCell(1);
	docWord.innerHTML = '<input type=text class=field_RO name=fileExplan' + id + ' size=20 maxlength=20 value=\'' + (v2!=null?v2:'') + '\'>';

	var actionText = row.insertCell(2);
	actionText.align = 'center';
	sb = new StringBuffer();
	sb.append(' <input class="field_Q" type="button" name="btn_fileDownload" onclick="openDownload(\'').append(v3).append('\');" value="下載檔案">');	
	sb.append('<input type="button" class="field" value="刪除" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);">');
	sb.append('<input type=hidden name=conId').append(id).append(' value=' ).append( v4!=null?v4:'' ).append('>');		
	actionText.innerHTML = sb.toString();
}

function openDownload(fileRoute){
	if (null != fileRoute && "" != fileRoute) {
	    var prop='';
	    prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	    prop=prop+'width=450,';
	    prop=prop+'height=160';
	    closeReturnWindow();
	    returnWindow = window.open('../../downloadFileSimple?fileType=DRG&fileID='+encodeURI(encodeURI(fileRoute)),'上傳檔案',prop);
	} else {
		alert("欄位不存在,請檢查!");
		return ;
	}
}
</script>
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="conTable">
	  <thead id="listTHEAD">
	  	<th class="listTH">檔案名稱</th>
	    <th class="listTH">檔案說明</th>
	    <th class="listTH"></th>	    
	  </thead>
	  <tbody id="listTBODY">
	  </tbody>
	</table>	
