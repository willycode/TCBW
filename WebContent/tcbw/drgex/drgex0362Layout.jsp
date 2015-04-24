<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
var arrDrg62Field = new Array("testDate","testItems","testNum");
var arrDrg62FieldName = new Array("檢驗日期","檢驗項目","檢驗數據");
function addDrg62Row(tblId,v1,v2,v3,v4){
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = row.uniqueID?row.uniqueID:randomUUID();
	
	var dateText = row.insertCell(0);
	dateText.align = 'center';
	dateText.innerHTML = '<input type=hidden name=drg62Row value=' + id + '>' +
	' <input type=text class=field_E name=testDate' + id + ' size=7 maxlength=7 value=\'' + (v1!=null?v1:'') + '\'>' +
	//' <input class="field_E" type="button" onclick="popCalendar(\'testDate' + id + '\')" value="..." title="萬年曆輔助視窗">';
    ' <button class="field_E" type="button" onclick="popCalendar(\'testDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	 

	var itemsText = row.insertCell(1);
	itemsText.align = 'center';
	itemsText.innerHTML = '<input type=text class=field name="testItems' + id + '" size=30 maxlength=30 value="' + (v2!=null?v2:'') + '">';

	var numText = row.insertCell(2);
	numText.align = 'center';
	numText.innerHTML = '<input type=text class=field name="testNum' + id + '" size=30 maxlength=30 value="' + (v3!=null?v3:'') + '">';
		
	var actionText = row.insertCell(3);
	actionText.align = 'center';
	var sb = new StringBuffer();
	sb.append('<input type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);">');
	sb.append(' <input type="button" class="field_btnAdd" value="" onclick="copyDrg62Row(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	sb.append('<input type=hidden name=drg62Id').append(id).append(' value=' ).append( v4!=null?v4:'' ).append('>');		
	actionText.innerHTML = sb.toString();
}

function copyDrg62Row(tblId,rid) {
	var arrFd = new Array(3);
	for (var i=0; i<arrFd.length; i++) {		
		if (isObj(document.all.item(arrDrg62Field[i]+rid))) arrFd[i] = document.all.item(arrDrg62Field[i]+rid).value;
	} 	
	addDrg62Row(tblId,arrFd[0],arrFd[1],arrFd[2]);
}

function clearDrg62Table(){
	var tbl = document.getElementById('drg62Table');
	if (isObj(opener))
	{
		if(null != opener.document.getElementById('drg62Table') && "" != opener.document.getElementById('drg62Table'))
		{
			tbl = opener.document.getElementById('drg62Table');
		}
	}
	var lastRow = tbl.rows.length;
	if (lastRow > 0) {
		while ( tbl.rows.length != 1 )
		{
			tbl.deleteRow(tbl.rows.length-1);
		}
	}
}

function validateDrg62Table() {
	var sb = new StringBuffer();
	var drg62Row=document.getElementsByName("drg62Row");	
	if (drg62Row!=null && drg62Row.length>0) {
		var sb = new StringBuffer();
		for (var i=0; i<drg62Row.length; i++) {
			var id = drg62Row[i].value;
			for (var j=0; j<arrDrg62Field.length; j++) {
				var fd	= document.all.item(arrDrg62FieldName[j]+id);
				if (isObj(fd) && arrDrg62FieldName[j]!='') {
					sb.append(checkEmpty(fd,arrDrg62FieldName[j]));
				}
			}
		}
		if (sb.toString().length>0) return sb.toString();
	}
	return "";
}

function getDrg62Table() {

	var x = getRemoteData('../../ajax/jsonDrg6002Dbs.jsp',getObjectValue("id"));
	var drg62Row=document.getElementsByName("drg62Row");	
	if (x!=null && x.length>0) {
		clearDrg62Table();
		var json = JSON.parse(x);
		for (var i=0; i<json.length; i++) {
			addDrg62Row("drg62Table",json[i].testDate,json[i].testItems,json[i].testNum, json[i].id);	
		}
	}
}


</script>
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="drg62Table" onmouseover="this.className='tableMouseover';" onmouseout="this.className='tableMouseout';">
	  <thead id="listTHEAD">
	  	<th class="listTH">檢驗日期</th>
	    <th class="listTH">檢驗項目</th>
	    <th class="listTH">檢驗數據</th>
	    <th class="listTH"><input type="button" class="field_btnAdd" onclick="addDrg62Row('drg62Table');" /></th>	    
	  </thead>
	  <tbody id="listTBODY">
	  </tbody>
	</table>	
