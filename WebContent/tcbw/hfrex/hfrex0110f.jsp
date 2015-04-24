<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript" src="../../js/jquery.js"></script>
<%
String fileType = Common.get(request.getParameter("fileType")); 
%>
<script type="text/javascript">
// 引用頁面，記得宣告idFileCount

function addFile<%=fileType%>(tblId,v1,v2,v3,v4){
	$("#listTFOOT<%=fileType%>").remove();
	
	var fType = "<%=fileType%>";
	idFileCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v4!=null?v4:('<%=fileType%>' + idFileCount);
	
	var strFileName = row.insertCell(0);
    strFileName.innerHTML = '<input type=hidden name=' + fType + 'FileRow value=' + id + '>' + 
    						'<input type=text readonly class="field_RO" name=' + fType + 'ShowFileName' + id + ' size=30 maxlength=300 value=\'' + (v1!=null?v1:'') + '\'>' +
    						'<input type=hidden name=' + fType + 'FileName' + id + ' value=\'' + (v1!=null?v1:'') + '\'>' +
    						'<input type=hidden name=' + fType + 'FileNameRoute' + id + ' value=\'' + (v2!=null?v2:'') + '\'>' +  
    						'<input type=button class="field" name=btn_' + fType + 'FileName' + id + ' onclick="openUploadWindow' + fType + '(\'' + fType + 'FileName' + id + '\',\'' + fType + 'FileNameRoute'+ id + '\',\'' + fType + 'ShowFileName' + id + '\');" value=\'上傳檔案\'>' + 
    						'<input type=button class="field" name=btn_' + fType + 'FileName' + id + 'Download onclick="openDownloadWindow' + fType + '(form1.' + fType + 'FileName' + id + '.value, form1.'+ fType + 'FileNameRoute' + id + '.value);" value=\'下載檔案\'>';
							
	var strFileExplain = row.insertCell(1);
	strFileExplain.innerHTML = '<input type=text class="field" name=' + fType + 'FileExplan' + id + ' size=30 maxlength=50 value=\'' + (v3!=null?v3:'') + '\'>';
    							
	var actionText = row.insertCell(2);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowInTable' + fType + '(\'' ).append(tblId).append( '\',this.parentNode.parentNode.rowIndex, form1.' + fType + 'FileName' + id + '.value, form1.' + fType + 'FileNameRoute' + id +'.value,\'' + id + '\');create' + fType + 'FileTFOOT();">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyFile' + fType + '(\'').append(tblId).append('\');">');
	actionText.innerHTML = sb.toString();
}

function openUploadWindow<%=fileType%>(popFieldFileName, popFieldFileRoute, fileShowName){
	if (isObj(eval("form1." + popFieldFileName))) {
	    var prop = '';
	    var w = (screen.width-450)/2;
		var h = (screen.height-160)/2;
	    prop = prop + 'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes';
	    prop = prop + ',width=450,';
	    prop = prop + ',height=160';
	    prop = prop + ',left=' + w;
	    prop = prop + ',top=' + h;
	    if(win != null) win.close();
	    win = window.open('../../home/uploadFileHiddenRoute.jsp?popFieldFileName=' + popFieldFileName + '&popFieldFileRoute=' + popFieldFileRoute + "&popFileType=HFR&fileShowName=" + fileShowName,'上傳檔案',prop);
	} else {
		alert("欄位不存在，請檢查!");
		return ;
	}
}

function openDownloadWindow<%=fileType%>(fileName, fileRoute){
    var prop='';
    prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=yes,scrollbars=yes,resizable=yes,';
    prop=prop+'width=400,';
    prop=prop+'height=400';
	if (fileName!=null && fileName.length>0) {
	    var url = getVirtualPath() + "downloadFileSimple?fileID=" + fileRoute + ':;:' + fileName + "&fileType=HFR";
	    if(win != null) win.close();
		win = window.open(url,'popDownload',prop);		
	} else {
		alert("目前沒有任何檔案可供下載!");	
	}
}

function copyFile<%=fileType%>(tblId){
	addFile<%=fileType%>(tblId);
}

function removeRowInTable<%=fileType%>(tblId, rid, fileName, fileRoute, id){
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	if (lastRow > 0) {
		if (rid!=null) tbl.deleteRow(rid);
		else tbl.deleteRow(lastRow - 1);
	}
	
	// 判斷是否刪除位儲存的檔案
	if(id.indexOf("HFR")!=-1 || id.indexOf("food")!=-1){
		$.ajax({
			url: '../../ajax/jsonDeleteTmpFile.jsp',
			type: 'POST',
			data: 'fileID=' + fileRoute + ':;:' + fileName +"&fileType=HFR",
			dataType: 'json',
			success: function(data){
				
			},
			error: function(){
				
			}
		});
	}
}

function create<%=fileType%>FileTFOOT(){
	var tbl = $("#tabFile<%=fileType%>").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='listTFOOT<%=fileType%>'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}

function validateTabFile<%=fileType%>(){
	var FArray = document.getElementsByName("<%=fileType%>FileRow");	
	if(FArray!=null && FArray.length>0){	
		for(var i=0; i<FArray.length; i++){
			var id = FArray[i].value;
			
			var sb = new StringBuffer();
			var fd	= document.all.item("<%=fileType%>ShowFileName" + id);
			sb.append(checkEmpty(fd, "檔案名稱"));
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabFile<%=fileType%>">
	<thead id="listTHEAD<%=fileType%>">
	<th class="listTH"><font size="2">檔案名稱</font></th>
	<th class="listTH"><font size="2">檔案說明</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addFile<%=fileType%>('tabFile<%=fileType%>');" />
		</font>
	</th>
	</thead>
	<tbody id="listTBODY<%=fileType%>">
	</tbody>
	<tfoot id="listTFOOT<%=fileType%>">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>

<script type="text/javascript">
function checklistTFOOT<%=fileType%>(){
	var tbl = $("#tabFile<%=fileType%>").get(0);
	if(tbl.rows.length==2 && $("#listTFOOT<%=fileType%>").html()!=null){
		addFile<%=fileType%>('tabFile<%=fileType%>');
	}
}
</script>



