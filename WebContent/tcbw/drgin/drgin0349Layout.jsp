<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
var arrDrg49Field = new Array("ingredient");
var arrDrg49FieldName = new Array("成分");
function addDrg49Row(tblId,v1,v2){
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = row.uniqueID?row.uniqueID:randomUUID();
	
	var ingredientText = row.insertCell(0);
	ingredientText.innerHTML = "<input type=hidden name=drg49Row value=" + id + ">" +
	'<input type=text class="field" name="ingredient' + id + '" size=50 maxlength=50 value="' + (v1!=null?v1:'') + '">';

	var actionText = row.insertCell(1);
	actionText.align = 'center';
	var sb = new StringBuffer();
	sb.append('<input type="button" class="field_btnRemove" name="btn_drg49Remove' + id + '" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);">');
	sb.append('<input type=hidden name=drg49Id').append(id).append(' value=' ).append( v2!=null?v2:'' ).append('>');		
	actionText.innerHTML = sb.toString();
}

function clearDrg49Table(){
	var tbl = document.getElementById('drg49Table');
	if (isObj(opener))
	{
		if(null != opener.document.getElementById('drg49Table') && "" != opener.document.getElementById('drg49Table'))
		{
			tbl = opener.document.getElementById('drg49Table');
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

function validateDrg49Table() {
	var sb = new StringBuffer();
	var drg49Row=document.getElementsByName("drg49Row");	
	if (drg49Row!=null && drg49Row.length>0) {
		for (var i=0; i<drg49Row.length; i++) {
			var id = drg49Row[i].value;
			var sb = new StringBuffer();
			for (var j=0; j<arrDrg49Field.length; j++) {
				var fd	= document.all.item(arrDrg49Field[j]+id);
				if (isObj(fd) && arrDrg49FieldName[j]!='') {
					
					sb.append(checkEmpty(fd,arrDrg49FieldName[j]));
				}
				
			}
		}
		if (sb.toString().length>0) return sb.toString();
	}
	return "";
}

function setDrg49Readonly() {
	var sb = new StringBuffer();
	var drg49Row=document.getElementsByName("drg49Row");	
	if (drg49Row!=null && drg49Row.length>0) {
		for (var i=0; i<drg49Row.length; i++) {
			var id = drg49Row[i].value;
			var sb = new StringBuffer();
			for (var j=0; j<arrDrg49Field.length; j++) {
				document.all.item(arrDrg49Field[j]+id).className = "field_RO";
			}
			document.all.item("btn_drg49Remove"+id).style.display = "none";
		}
	}
	document.all.item("btn_drg49Add").style.display = "none";
}

</script>
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="drg49Table" onmouseover="this.className='tableMouseover';" onmouseout="this.className='tableMouseout';">
	  <thead id="listTHEAD">
	  	<th class="listTH">成份</th>
	    <th class="listTH" width="10%"><input type="button" class="field_btnAdd" name="btn_drg49Add" onclick="addDrg49Row('drg49Table');" /></th>	    
	  </thead>
	  <tbody id="listTBODY">
	  </tbody>
	</table>	
