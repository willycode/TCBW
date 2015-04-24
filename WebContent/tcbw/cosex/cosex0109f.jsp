<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

<script type="text/javascript">
var idATTFileCount = 0;
var win = null;
var fType = "ATT";
function addFileATT(tblId,v1,v2,v3){
	idATTFileCount += 1;
	
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v3!=null?v3:(fType + idATTFileCount);
	
    var strFileName = row.insertCell(0);
    strFileName.innerHTML = '<input type=hidden name=ATTFileRow value=' + id + '>' + 
    						'<input type=text readonly class="field_RO" name=ATTFileName' + id + ' size=30 maxlength=300 value=\'' + (v1!=null?v1:'') + '\'>' +
    						'<input type=hidden name=ATTFileNameRoute' + id + ' value=\'' + (v2!=null?v2:'') + '\'>' +  
    						'<input type=button class="field" name=btn_ATTFileName' + id + ' onclick="openUploadWindowATT(\'ATTFileName' + id + '\',\'ATTFileNameRoute'+ id + '\');" value=\'上傳檔案\'>' + 
    						'<input type=button class="field" name=btn_ATTFileName' + id + 'Download onclick="openDownloadWindowATT(form1.ATTFileName' + id + '.value, form1.ATTFileNameRoute' + id + '.value);" value=\'下載檔案\'>';
								
	var actionText = row.insertCell(1);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowInTable' + fType + '(\'' ).append(tblId).append( '\',this.parentNode.parentNode.rowIndex, form1.' + fType + 'FileName' + id + '.value, form1.' + fType + 'FileNameRoute' + id +'.value,\'' + id + '\');">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyFile' + fType + '(\'').append(tblId).append('\');">');
	actionText.innerHTML = sb.toString();
}

function openUploadWindowATT(popFieldFileName, popFieldFileRoute){
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
	    win = window.open('../../home/uploadFileHiddenRoute.jsp?popFieldFileName=' + popFieldFileName + '&popFieldFileRoute=' + popFieldFileRoute + "&popFileType=COS",'上傳檔案',prop);
	} else {
		alert("欄位不存在，請檢查!");
		return ;
	}
}

function openDownloadWindowATT(fileName, fileRoute){
    var prop='';
    prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=yes,scrollbars=yes,resizable=yes,';
    prop=prop+'width=400,';
    prop=prop+'height=400';
	if (fileName!=null && fileName.length>0) {
	    var url = getVirtualPath() + "downloadFileSimple?fileID=" + fileRoute + ':;:' + fileName + "&fileType=COS";
	    if(win != null) win.close();
		win = window.open(url,'popDownload',prop);		
	} else {
		alert("目前沒有任何檔案可供下載!");	
	}
}

function copyFileATT(tblId){
	addFileATT(tblId);
}

function removeRowInTableATT(tblId, rid, fileName, fileRoute, id){
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	if (lastRow > 0) {
		if (rid!=null) tbl.deleteRow(rid);
		else tbl.deleteRow(lastRow - 1);
	}
	
	// 判斷是否刪除位儲存的檔案
	if(id.indexOf("ATT") != -1){
		$.ajax({
			url: '../../ajax/jsonDeleteTmpFile.jsp',
			type: 'POST',
			data: 'fileID=' + fileRoute + ':;:' + fileName +"&fileType=COS",
			dataType: 'json',
			success: function(data){
				
			},
			error: function(){
				
			}
		});
	}
}

function validateATTFileTable(){
	var arrArray = document.getElementsByName("ATTFileRow");	
	if(arrArray!=null && arrArray.length>0){	
		for(var i=0; i<arrArray.length; i++){
			var id = arrArray[i].value;
			
			var sb = new StringBuffer();
			var fd = document.all.item("ATTFileName" + id);
			if(isObj(fd)){
				sb.append(checkEmpty(fd, "檔案名稱"));
			}
			if (sb.toString().length>0){
				return sb.toString();
			}
		}
	}
	return "";
}

</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabFileATT">
	<thead id="listTHEAD">
	<th class="listTH"><font size="2">檔案名稱</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addFileATT('tabFileATT');" />
		</font>
	</th>
	</thead>
	<tbody id="listTBODYATT">
	</tbody>
</table>
