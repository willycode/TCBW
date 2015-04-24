var arrMedFieldName   = new Array("permitKey","permitNo","chProduct","enProduct","medapprovedate","medEffectiveDate","applicationId","applicationName","manufactorName","manufactorCountry","medclass","medMainCategory","medSecCategory");
var arrMedFieldZhName = new Array("許可證字","許可證號","中文品名","英文品名","許可證核准日期","許可證有效日期","許可證持有商","許可證持有商","許可證製造商","醫材級數","醫材主類別","醫材次類別");
function addMedRow(tblId,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16){	
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = row.uniqueID?row.uniqueID:randomUUID();

	var content = row.insertCell(0);
	var sb = new StringBuffer();
	sb.append('<table width="100%" class="bgList">');
	sb.append('<tr><td nowrap class="td_form" >許可證字：</td><td nowrap class="td_form_white" colspan="3">');
	sb.append('		<input type="hidden" name="medRow" value="').append(id).append('">');
	sb.append('		<select class="field" id="permitKey').append(id).append('" name="permitKey').append(id).append('" value="').append(v1!=null?v1:'').append('" ></select>');
	sb.append('		第<input class="field" type="text" size="14" maxlength="14" id="permitNo').append(id).append('" name="permitNo').append(id).append('" value="').append(v2!=null?v2:'').append('" onChange="permitProdData(\'').append(id).append('\');">號');
	sb.append('</td></tr>');
	sb.append('<tr>');
	sb.append('	<td nowrap class="td_form">中文品名：</td><td nowrap class="td_form_white">');
	sb.append('		<input type="text" class="field" size="50" maxlength="50" id="chProduct').append(id).append('" name="chProduct').append(id).append('" value="').append(v3!=null?v3:'').append('"/>');
	sb.append('	</td>');
	sb.append('	<td nowrap class="td_form">英文品名：</td><td nowrap class="td_form_white">');
	sb.append('		<input type="text" class="field" size="50" maxlength="100" id="enProduct').append(id).append('" name="enProduct').append(id).append('" value="').append(v4!=null?v4:'').append('"/>');
	sb.append('	</td>');
	sb.append('</tr>');
	sb.append('<tr>');
	sb.append('	<td nowrap class="td_form">許可證核准日期：</td><td nowrap class="td_form_white">');
	sb.append('		<input class="field" type="text" size="7" maxlength="7" style="ime-mode: disabled;" name="medapprovedate').append(id).append('" value="').append(v5!=null?v5:'').append('">' );
	sb.append('		<input class="field" type="button" name="btn_medapprovedate').append(id).append('" onclick="popCalendar(\'medapprovedate').append(id).append('&js=\')\" value="..." title="萬年曆輔助視窗"> ');
	sb.append('	</td>');
	sb.append('	<td nowrap class="td_form">許可證有效日期：</td><td nowrap class="td_form_white">');
	sb.append('		<input class="field" type="text" size="7" maxlength="7" style="ime-mode: disabled;" name="medEffectiveDate').append(id).append('" value="').append(v6!=null?v6:'').append('">' );
	sb.append('		<input class="field" type="button" name="btn_medEffectiveDate').append(id).append('" onclick="popCalendar(\'medEffectiveDate').append(id).append('&js=\')\" value="..." title="萬年曆輔助視窗"> ');
	sb.append('	</td>');
	sb.append('</tr>');
	sb.append('<tr>');
	sb.append('	<td nowrap class="td_form">許可證持有商：</td><td nowrap class="td_form_white">');
	sb.append('		<input type="hidden" class="field" id="applicationId').append(id).append('" name="applicationId').append(id).append('" value="').append(v7!=null?v7:'').append('"/>');
	sb.append('		<input type="text" class="field" size="50" maxlength="50" id="applicationName').append(id).append('" name="applicationName').append(id).append('" value="').append(v8!=null?v8:'').append('"/>');
	sb.append('	</td>');
	sb.append('	<td nowrap class="td_form">許可證製造商：</td><td nowrap class="td_form_white">');
	sb.append('		<input type="text" class="field" size="50" maxlength="500" id="manufactorName').append(id).append('" name="manufactorName').append(id).append('" value="').append(v9!=null?v9:'').append('"/>');
	sb.append('	</td>');
	sb.append('</tr>');
	sb.append('<tr>');
	sb.append('	<td nowrap class="td_form">製造廠國別：</td><td nowrap class="td_form_white">');
	sb.append('		<input type="text" class="field" size="25" maxlength="25" id="manufactorCountry').append(id).append('" name="manufactorCountry').append(id).append('" value="').append(v10!=null?v10:'').append('"/>');
	sb.append('	</td>');
	sb.append('	<td nowrap class="td_form">醫材級數：</td><td nowrap class="td_form_white">');
	sb.append('		<input type="text" class="field" size="1" maxlength="1" id="medclass').append(id).append('" name="medclass').append(id).append('" value="').append(v11!=null?v11:'').append('"/>');
	sb.append('	</td>');
	sb.append('</tr>');
	
	/*
	sb.append('<tr>');
	sb.append('	<td nowrap class="td_form">醫材主類別：</td><td nowrap class="td_form_white">');
	sb.append('		<select class="field" id="medMainCategory').append(id).append('" name="medMainCategory').append(id).append('" value="').append(v12!=null?v12:'').append('" onChange="addMedSecCategoryOptions(\'').append(id).append('\')" ></select>');
	sb.append('	</td>');
	sb.append('	<td nowrap class="td_form">醫材次類別：</td><td nowrap class="td_form_white">');
	sb.append('		<select class="field" id="medSecCategory').append(id).append('" name="medSecCategory').append(id).append('" value="').append(v13!=null?v13:'').append('" ></select>');
	sb.append('	</td>');
	sb.append('</tr>');
*/
	
	sb.append('<tr>');
	sb.append('<td nowrap class="td_form">醫材主類別</td>');
	sb.append('<td nowrap class="td_form_white">');
	sb.append('<input class="field" type="hidden" id="medMainCategoryCodePop" name="medMainCategoryCode" >');
	sb.append('<input class="field" type="text" id="medMainCategoryPop" name="medMainCategory').append(id).append('" size="5" maxlength="10" value="').append(v12!=null?v12:'').append('" onchange="getCodeName(\'medMainCategoryCodePop\',\'\',\'MEDMCT\',this,\'medMainCategoryCodeNamePop\',\'\');">');
	sb.append('<input class="field_RO" type="text" id="medMainCategoryCodeNamePop" name="medMainCategoryCodeName" size="20" maxlength="50" value="').append(v15!=null?v15:'').append('">');
	sb.append('<input class="field" type="button" id="btn_medMainCategoryCode" name="btn_medMainCategoryCode" value="..." title="代碼輸入輔助視窗"   onclick="popCode(\'medMainCategoryCodePop\',\'\',\'MEDMCT\',\'medMainCategoryPop\',\'medMainCategoryCodeNamePop\',\'\',\'\');" >');
	sb.append('</td>');
	sb.append('<td nowrap class="td_form">醫材次類別</td>');
	sb.append('<td nowrap class="td_form_white">');
	sb.append('<input class="field" type="hidden" id="medSecCategoryCodePop" name="medSecCategoryCode" ><input class="field" type="text" style="ime-mode: disabled;" id="medSecCategoryPop"  name="medSecCategory').append(id).append('" size="5" maxlength="10" value="').append(v13!=null?v13:'').append('" onchange="getCodeName(\'medSecCategoryCodePop\',\'medMainCategoryPop\',\'MEDSCT\',this,\'medSecCategoryCodeNamePop\',\'\');">');
	sb.append('<input class="field_RO" type="text" id="medSecCategoryCodeNamePop" name="medSecCategoryCodeName" size="20" maxlength="50" value="').append(v16!=null?v16:'').append('"><input class="field" type="button" id="btn_medSecCategoryCode" value="..." title="代碼輸入輔助視窗" name="btn_medSecCategoryCodePop" onclick="popCode(\'medSecCategoryCodePop\',\'medMainCategoryPop\',\'MEDSCT\',\'medSecCategoryPop\',\'medSecCategoryCodeNamePop\',\'\',\'\');" >');
	sb.append('</td>');
	sb.append('</tr>');
	
	
	
	sb.append('</table>');
	sb.append('<br>');
	content.innerHTML = sb.toString();
	
	var actionText = row.insertCell(1);
	sb = new StringBuffer();
	sb.append('<input type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);"><br><br>');
	sb.append('<input type="button" class="field_btnAdd" value="" onclick="popPermitView();"><br><br>');
	sb.append('<input type="hidden" name="medId').append(id).append('" value="').append(v14!=null?v14:'').append('">');	
	actionText.innerHTML = sb.toString();
	
	addPermitKeyOptions(v1);
	//addMedMainCategoryOptions(v12);
}


function addMedNewRow(tblId,permitKey,permitNo,applicationId,applicationName) {
	addMedRow("medTable",permitKey,permitNo,"","","","",applicationId,applicationName,"","","","","");
}

function clearMedTable(){
	var tbl = document.getElementById('medTable');
	var lastRow = tbl.rows.length;
	if (lastRow > 0) {
		while ( tbl.rows.length != 0 )
		{
			tbl.deleteRow(tbl.rows.length-1);
		}
	}
}

function addPermitKeyOptions(permitKey) {
	var medRows=document.getElementsByName("medRow");	
	if (medRows!=null && medRows.length>0) {
		for (var i=0; i<medRows.length; i++) {
			var id = medRows[i].value;
			var permitKeyOpt = document.all.item("permitKey"+id);
			document.all.item("permitKey"+id).options.length = 0;
			document.all.item("permitKey"+id).options.add(new Option("請選擇" , ""));
			if (null != permitKeyOpt && "" != permitKeyOpt) {	
				var x = getRemoteData('../../ajax/jsonCommonCodes.jsp?','&codeKindId=MEDPKID');
				if (x!=null && x.length>0) {
					var json = JSON.parse(x);
					for (var j=0; j<json.length; j++) {
						permitKeyOpt.options.add(new Option(json[j].codeName , json[j].codeId));
						if(json[j].codeId == permitKey){
							permitKeyOpt.options[permitKeyOpt.options.length-1].selected = true;
						}
					}
				}
			}
		}
	}
}

function popPermitView(id)
{
	
	var removeKey = "";
	var med7002Row=document.getElementsByName("medRow");
	
	if (med7002Row!=null && med7002Row.length>0) 
	{
		for (var i=0; i<med7002Row.length; i++) 
		{
			var id = med7002Row[i].value;
			if(removeKey.length > 0) removeKey += ",";
			removeKey += getObjectValue("permitKey"+id) + getObjectValue("permitNo"+id);
		}
	}
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	prop=prop+"width=1050,height=620,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	window.open("../../home/popPermitViewByVMed.jsp?&rowId="+id+"&q_removeKey="+removeKey,'popWinE',prop);		
}

function permitProdData(id){
	var permitKey = getObjectValue("permitKey"+id);
	var permitNo = getObjectValue("permitNo"+id);
	var x = getRemoteData("../../ajax/jsonDrgObjects.jsp", "&permitKey="+permitKey+"&permitNo="+permitNo );
	if(x!=null && x.length>0){
		var json = JSON.parse(x);
		setObjectValue("applicationId"+id,json.obj4);
		setObjectValue("applicationName"+id,json.obj2);
	}else{
		setObjectValue("permitKey"+id,"");
		setObjectValue("permitNo"+id,"");
		setObjectValue("applicationId"+id,"");
		setObjectValue("applicationName"+id,"");
		alert("查無藥證資料!!");
	}
}

function addMedMainCategoryOptions(medMainCategory) {
	var medRows=document.getElementsByName("medRow");	
	if (medRows!=null && medRows.length>0) {
		for (var i=0; i<medRows.length; i++) {
			var id = medRows[i].value;
			var medMainCategoryOpt = document.all.item("medMainCategory"+id);
			medMainCategoryOpt.options.length = 0;
			medMainCategoryOpt.options.add(new Option("請選擇" , ""));
			if (null != medMainCategoryOpt && "" != medMainCategoryOpt) {	
				var x = getRemoteData('../../ajax/jsonCommonCodes.jsp?','&codeKindId=MEDMCT');
				if (x!=null && x.length>0) {
					var json = JSON.parse(x);
					for (var j=0; j<json.length; j++) {
						medMainCategoryOpt.options.add(new Option(json[j].codeName , json[j].codeId));
						if(json[j].codeId == medMainCategory){
							medMainCategoryOpt.options[medMainCategoryOpt.options.length-1].selected = true;
						}
					}
				}
			}
		}
	}
}

function addMedSecCategoryOptions(id) {
	var medMainCategory = document.all.item("medMainCategory"+id).value;
	if(null != medMainCategory && "" != medMainCategory){
		var medSecCategory = document.all.item("medSecCategory"+id).value;
		var medSecCategoryOpt = document.all.item("medSecCategory"+id);
		medSecCategoryOpt.options.length = 0;
		medSecCategoryOpt.options.add(new Option("請選擇" , ""));
		if (null != medSecCategoryOpt && "" != medSecCategoryOpt) {	
			var x = getRemoteData('../../ajax/jsonCommonCodes.jsp?','&codeKindId=MEDSCT&codeId='+medMainCategory);
			if (x!=null && x.length>0) {
				var json = JSON.parse(x);
				for (var j=0; j<json.length; j++) {
					medSecCategoryOpt.options.add(new Option(json[j].codeName , json[j].codeId));
					if(json[j].codeId == medSecCategory){
						medSecCategoryOpt.options[medSecCategoryOpt.options.length-1].selected = true;
					}
				}
			}
		}
	}
}

