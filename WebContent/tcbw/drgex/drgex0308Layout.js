var arrDrg49Field   = new Array("ingredient","isEffectChange","isBrandChange","noBrandChange","beforeBrand","afterBrand","comment");
var arrDrg49FieldName = new Array("","有療效不等狀況" ,"有更換廠牌無療效不等狀況" ,"未更換廠牌" ,"轉換前廠牌","轉換後廠牌","");
function addDrg49Row(tblId,v1,v2,v3,v4,v5,v6,v7,v8){
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = row.uniqueID?row.uniqueID:randomUUID();

	var drgContent = row.insertCell(0);
	var sb = new StringBuffer();
	sb.append('<table class="bgList" width="100%" cellspacing="0" cellpadding="0" >');
	sb.append('<tr>');
	sb.append('		<td>藥品成分：<input name="ingredient').append(id).append('" value="').append(v1!=null?v1:'').append('" type="text" class="field_RO" size="25" maxlength="50" />');
	sb.append('			<input type="hidden" name="drg49Row" value="').append(id).append('">');
	sb.append('		</td>');
	sb.append('</tr>');
	sb.append('<tr>');
	sb.append('  <td>有療效不等狀況：');
	sb.append('		<input class="field" type="radio" id="isEffectChange').append(id).append('" name="isEffectChange').append(id).append('" value="N"').append(v2=='N'?'checked':'').append('>否');
	sb.append('		<input class="field" type="radio" id="isEffectChange').append(id).append('" name="isEffectChange').append(id).append('" value="Y"').append(v2=='Y'?'checked':'').append('>是');
	sb.append('	 </td>');
	sb.append('</tr>');
	sb.append('<tr>');
	sb.append('  <td>有更換廠牌無療效不等狀況：');
	sb.append('		<input class="field" type="radio" id="isBrandChange').append(id).append('" name="isBrandChange').append(id).append('" value="N" ').append(v3=='N'?'checked':'').append(' >否');
	sb.append('		<input class="field" type="radio" id="isBrandChange').append(id).append('" name="isBrandChange').append(id).append('" value="Y" ').append(v3=='Y'?'checked':'').append(' >是');
	sb.append('	 </td>');
	sb.append('</tr>');
	sb.append('  <td>未更換廠牌：');
	sb.append('		<input class="field" type="radio" id="isBrandChange').append(id).append('" name="noBrandChange').append(id).append('" value="N" ').append(v4=='N'?'checked':'').append(' >否');
	sb.append('		<input class="field" type="radio" id="isBrandChange').append(id).append('" name="noBrandChange').append(id).append('" value="Y" ').append(v4=='Y'?'checked':'').append(' >是');
	sb.append('	 </td>');
	sb.append('</tr>');
	sb.append('<tr>');
	sb.append('		<td>轉換前廠牌：<input name="beforeBrand').append(id).append('" value="').append(v5!=null?v5:'').append('" type="text" class="field" size="25" maxlength="50" />').append('</td>');
	sb.append('</tr>');
	sb.append('<tr>');
	sb.append('		<td>轉換後廠牌：<input name="afterBrand').append(id).append('" value="').append(v6!=null?v6:'').append('" type="text" class="field" size="25" maxlength="50" />').append('</td>');
	sb.append('</tr>');
	sb.append('<tr>');
	sb.append('		<td>補充說明：<textarea class="field" name="comment').append(id).append('" cols="50" rows="2">').append(v7!=null?v7:'').append('</textarea>').append('</td>');
	sb.append('</tr>');
	sb.append('</table>');
	drgContent.innerHTML = sb.toString();
	
	var actionText = row.insertCell(1);
	sb = new StringBuffer();
	sb.append('<a href="#toolbarAnchor">top</a>');
	sb.append('<input type="hidden" name="drg49Id').append(id).append('" value="').append(v8!=null?v8:'').append('">');		
	actionText.innerHTML = sb.toString();
}

function validateDrg49Table() {
	var sb = new StringBuffer();
	var drg49Rows=document.getElementsByName("drg49Row");	
	if (drg49Rows!=null && drg49Rows.length>0) {
		for (var i=0; i<drg49Rows.length; i++) {
			var id = drg49Rows[i].value;
			var sb = new StringBuffer();
			for (var j=0; j<arrDrg49Field.length; j++) {
				var fd	= document.all.item(arrDrg49Field[j]+id);
				if (isObj(fd) && arrDrg49FieldName[j]!='') {
					if(j==1 || j==2 || j==3){
						sb.append(checkRadioButton(fd,arrDrg49FieldName[j]));
					}else{
						sb.append(checkEmpty(fd,arrDrg49FieldName[j]));
					}
				}
			}
		}
		if (sb.toString().length>0) return sb.toString();
	}
	return "";
}
