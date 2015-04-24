<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">

var idCOS0012DbCount = 0;
var arrCOS0012DbFieldName = new Array("postDate12","postNo12","postDept12","postMemo12");
var arrCOS0012DbFieldZhName = new Array("發文日期","","函轉單位","");
function addCOS0012DbRow(tblId,v1,v2,v3,v4,v5){
	$("#COS0012DbListTFOOT").remove();
	
	idCOS0012DbCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v5!=null?v5:("tmp" + idCOS0012DbCount);
	
    var strPostDate = row.insertCell(0);
    strPostDate.innerHTML = '<input type=hidden name=COS0012DbRow value=' + id + '><input type=text class=field name=postDate12' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v1!=null?v1:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_postDate12' + id + '" onClick="popCalendar(\'postDate12' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
    var strPostNo = row.insertCell(1);
	strPostNo.innerHTML = '<input type=text class=field name=postNo12' + id + ' size=15 maxlength=20 style="font-size: 10pt" value="' + (v2!=null?v2:'') + '">';
    
    
	var strPostDept = row.insertCell(2);
	strPostDept.innerHTML = '<select class="field_RO" style="font-size: 10pt" disabled name=postDept12' + id + ' type="select"><%=View.getOption("select id, unionName from Con1003Db where 1 = 1 order by id","").replace("\n","").replace("'","\\'")%></select>' +
							'<input type=button class="field" name=getDept12' + id + ' value="函轉單位" onclick="getDeptInfo(\'postDept12' + id + '\');">';

	if(v3==null || v3==''){
		if(isObj(document.all.item("con1003DbId"))){
			v3 = document.all.item("con1003DbId").value;
		}
	}
	document.all.item('postDept12'+id).value = (v3!=null?v3:'');
	
	var strPostMemo = row.insertCell(3);
	strPostMemo.innerHTML = '<input type=text class=field name=postMemo12' + id + ' size=10 maxlength=10 style="font-size: 10pt" value="' + (v4!=null?v4:'') + '">';
		
	var actionText = row.insertCell(4);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);createCOS0012DbListTFOOT();">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyCOS0012DbRow(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
}

function getDeptInfo(field){
	openCenterWindow(900, 600, "cosin0503f.jsp?field1=" + field);
}

// 因為引用另外一支程式，暫時不改
function openDeptMail(){
	
}

function copyCOS0012DbRow(tblId, rid) {
	var arrFd = new Array(4);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrCOS0012DbFieldName[i] + rid))){
			arrFd[i] = document.all.item(arrCOS0012DbFieldName[i] + rid).value;
		}
	} 	
	addCOS0012DbRow(tblId, arrFd[0], arrFd[1], arrFd[2], arrFd[3]);
}

function validateCOS0012DbTable() {
	var COS0012DbArray = document.getElementsByName("COS0012DbRow");	
	if(COS0012DbArray!=null && COS0012DbArray.length>0){	
	
		for(var i=0; i<COS0012DbArray.length; i++){
			var id = COS0012DbArray[i].value;
			
			var sb = new StringBuffer();
			for(var j=0; j<arrCOS0012DbFieldName.length; j++){
				var fd	= document.all.item(arrCOS0012DbFieldName[j] + id);
				if(isObj(fd) && arrCOS0012DbFieldZhName[j] != "") {
					sb.append(checkEmpty(fd, arrCOS0012DbFieldZhName[j]));
					if(j == 0){
						sb.append(checkDate(fd, arrCOS0012DbFieldZhName[j]));
					}
				}
			}
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}

function createCOS0012DbListTFOOT(){
	var tbl = $("#tabCOS0012Db").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='COS0012DbListTFOOT'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabCOS0012Db">
	<thead id="COS0012DbListTHEAD">
	<th class="listTH"><font size="2">發文日期</font></th>
	<th class="listTH"><font size="2">發文文號</font></th>
	<th class="listTH"><font size="2">函轉單位</font></th>
	<th class="listTH"><font size="2">附件內容</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addCOS0012DbRow('tabCOS0012Db');" />
		</font>
	</th>
	</thead>
	
	<tbody id="COS0012DbListTBODY">
	</tbody>
	
	<tfoot id="COS0012DbListTFOOT">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>
