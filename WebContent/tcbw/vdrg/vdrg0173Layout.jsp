<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
var arrDrg73Field = new Array("permitKey","permitNo","applicationId","applicationName");
var arrDrg73FieldName = new Array("許可證字","許可證號","國內許可證持有商","國內許可證持有商");
function addDrg73Row(tblId,v1,v2,v3,v4,v5){
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = row.uniqueID?row.uniqueID:randomUUID();
	
	var permitText = row.insertCell(0);
	permitText.align = 'center';
	permitText.innerHTML = '<input type=hidden name=drg73Row value=' + id + '>' +
	'<select class="field" type="select"  id="permitKey' + id + '" name="permitKey' + id + '" value="' + (v1!=null?v1:'') + '" onChange="getPermitData(\''+ id +'\')" >' + "<%=View.getOptionCodeKind("DRGPKID","").replaceAll("\n","")%>" + '</select>'+
	'第<input class="field" type="text" name="permitNo' + id + '" size=5 maxlength=6 value="' + (v2!=null?v2:'') + '" onChange="getPermitData(\''+ id +'\')">號 ';
	getSelectedValue(document.all.item('permitKey' + id) ,v1);

	var nameText = row.insertCell(1);
	nameText.align = 'center';
	nameText.innerHTML = '<input type=hidden class=field name="applicationId' + id + '" value="' + (v3!=null?v3:'') + '">'
					+ '<input type=text class="field_RO" name="applicationName' + id + '" size=25 maxlength=25 value="' + (v4!=null?v4:'') + '"> '
					+ '<input type="button" class="field" type="button" value="廠商資料" onclick="popCon1005(\''+id+'\');">';
		
	var actionText = row.insertCell(2);
	actionText.align = 'center';
	var sb = new StringBuffer();
	sb.append('<input type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);">');
	sb.append(' <input type="button" class="field_btnAdd" value="" onclick="copyDrg73Row(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	sb.append('<input type=hidden name=drg73Id').append(id).append(' value=' ).append( v5!=null?v5:'' ).append('>');
	
	actionText.innerHTML = sb.toString();
	//document.all.item('applicationName'+ id ).disabled = true;
	document.all.item('applicationName'+ id ).readOnly = true;
}

function copyDrg73Row(tblId,rid) {
	var arrFd = new Array(4);
	for (var i=0; i<arrFd.length; i++) {		
		if (isObj(document.all.item(arrDrg73Field[i]+rid))) arrFd[i] = document.all.item(arrDrg73Field[i]+rid).value;
	} 	
	addDrg73Row(tblId,arrFd[0],arrFd[1],arrFd[2],arrFd[3]);
}

function clearDrg73Table(){
	var tbl = document.getElementById('drg73Table');
	if (isObj(opener))
	{
		if(null != opener.document.getElementById('drg73Table') && "" != opener.document.getElementById('drg73Table'))
		{
			tbl = opener.document.getElementById('drg73Table');
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

function validateDrg73Table() {
	var sb = new StringBuffer();
	var drg73Row=document.getElementsByName("drg73Row");	
	if (drg73Row!=null && drg73Row.length>0) {
		for (var i=0; i<drg73Row.length; i++) {
			var id = drg73Row[i].value;
			var sb = new StringBuffer();
			for (var j=0; j<arrDrg73Field.length; j++) {
				var fd	= document.all.item(arrDrg73FieldName[j]+id);
				if (isObj(fd) && arrDrg73FieldName[j]!='') {
					sb.append(checkEmpty(fd,arrDrg73FieldName[j]));
				}
			}
		}
		if (sb.toString().length>0) return sb.toString();
	}
	return "";
}

function getPermitData(id) 
{
	
	var permitKey = getObjectValue("permitKey"+id);
	var permitNo = getObjectValue("permitNo"+id);

	if(null != permitKey && "" != permitKey && null != permitNo && "" != permitNo)
	{
		var x = getRemoteData(getVirtualPath() + 'ajax/jsonPermit.jsp', "&k="+permitKey+"&n="+permitNo);
	
		if (x!=null && x.length>0) 
		{
			var json = JSON.parse(x);
			
			setObjectValue("permitKey"+id, json.licekId);
			setObjectValue("permitNo"+id, json.licId1);
			setObjectValue("applicationId"+id, json.appunNo);
			setObjectValue("applicationName"+id, json.applName);
		}
		else
		{
			setObjectValue("permitKey"+id, "");
			setObjectValue("permitNo"+id, "");
			setObjectValue("applicationId"+id, "");
			setObjectValue("applicationName"+id, "");
		}
	}
}

function popPermitData()
{
	var removeKey = "";
	var drg73Row=document.getElementsByName("drg73Row");
	if (drg73Row!=null && drg73Row.length>0) {
		for (var i=0; i<drg73Row.length; i++) {
			var id = drg73Row[i].value;
			if(removeKey.length > 0) removeKey += ",";
			removeKey += "'"+getObjectValue("permitKey"+id) + getObjectValue("permitNo"+id)+"'";
		}
	}

	var param = "";		
	var prop="";
	var windowTop=(document.body.clientHeight-500)/2+100;
	var windowLeft=(document.body.clientWidth-500)/2+100;
	prop=prop+"width=1300,height=500,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	param += "q_removeKey="+removeKey 
		+ "&q_prodName1="+encodeURI(encodeURI(getObjectValue("chProduct")))
		+ "&q_ingrMa1="+encodeURI(encodeURI(getObjectValue("scientificName")))
		+ "&q_appName="+encodeURI(encodeURI(getObjectValue("druggist")))
		+ "&q_factName="+encodeURI(encodeURI(getObjectValue("manufactorName")))
		+ "&q_caseType=DRGPKID";
	returnWindow=window.open(getVirtualPath() + "home/popPermitData.jsp?"+param,"",prop);
}

function popCon1005(id){
	var jscript = "";		
	var prop="";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=650,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCon1005.jsp?drgId="+id,"",prop);
}


</script>
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="drg73Table" onmouseover="this.className='tableMouseover';" onmouseout="this.className='tableMouseout';">
	  <thead id="listTHEAD">
	  	<th class="listTH">國內許可證  <input type="button" class="field" type="button" value="批次帶入" onclick="popPermitData();"></th>
	    <th class="listTH">國內許可證擁有商</th>
	    <th class="listTH"><input type="button" class="field_btnAdd" onclick="addDrg73Row('drg73Table');" /></th>	    
	  </thead>
	  <tbody id="listTBODY">
	  </tbody>
	</table>	
