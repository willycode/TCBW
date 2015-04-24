<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
var idGCount = 0;
var arrGFoodFieldName = new Array("gPermitKey","gPermitNo","gChProduct","gEnProduct","gBuySource","gIngredient", "gDoseDay", "gDoseNum", "gFrequencyNum", "gFrequencyMl", "gEdibleDateS", "gEdibleDateE");
var arrGFoodFieldZhName = new Array("許可證字","許可證號","","","購買來源","","食用方式-日","食用方式-次數","食用方式-顆","食用方式-ML","食用日期-起","食用日期-迄");

function addGFoodRow(tblId,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13){
	$("#GFOODlistTFOOT").remove();
	
	if(v13!=null && v13!=""){
		if(v13.indexOf("gTmp") != -1){
			idGCount = parseInt(v13.substring(4, v13.length));
		}
	}else{
		idGCount += 1;
	}
	
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v13!=null?v13:("gTmp" + idGCount);
	
    var strGFontLicense = row.insertCell(0);
    strGFontLicense.innerHTML = '<input type=hidden name=GFoodRow value=' + id + '><select class="field_RO" style="font-size: 10pt" disabled name=gPermitKey' + id + ' type="select"><%=View.getOption("select codeId,codeName from CommonCode where commonCodeKind.codeKindId='HFRPKID' and isStop<> 'Y' order by codeId","").replace("\n","").replace("'","\\'")%></select>' + 
    							'<input type=text class="field_RO" readonly name=gPermitNo' + id + ' size=16 maxlength=20 style="font-size: 10pt" value="' + (v2!=null?v2:'') + '">';
	strGFontLicense.innerHTML += '<input type=button class="field" name=getPermit' + id + ' value="許可證字號" onclick="getPermitKey(\'gPermitKey' + id + '\',\'gPermitNo' + id + '\',\'gChProduct' + id + '\',\'gEnProduct' + id + '\',\'gIngredient' + id + '\');">';
	document.all.item('gPermitKey'+id).value = (v1!=null?v1:'');
	
	var strGChProduct = row.insertCell(1);
	strGChProduct.innerHTML = '<input type=text class=field name=gChProduct' + id + ' size=20 maxlength=150 style="font-size: 10pt" value="' + (v3!=null?v3:'') + '">';

	var strGEnProduct = row.insertCell(2);
	strGEnProduct.innerHTML = '<input type=text class=field name=gEnProduct' + id + ' size=20 maxlength=150 style="font-size: 10pt" value="' + (v4!=null?v4:'') + '">';
	
	var strGBuySource = row.insertCell(3);
	strGBuySource.innerHTML = '<select style="font-size: 10pt" class="field" name=gBuySource' + id + ' type="select"><%=View.getOption("select codeId,codeName from CommonCode where commonCodeKind.codeKindId='HFRBYS' and isStop<> 'Y' order by codeId","").replace("\n","").replace("'","\\'")%></select>';
	document.all.item('gBuySource'+id).value = (v5!=null?v5:'');	
		
	var strGIngredient = row.insertCell(4);
	strGIngredient.innerHTML = '<input type=text class=field name=gIngredient' + id + ' size=25 maxlength=25 style="font-size: 10pt" value="' + (v6!=null?v6:'') + '">';
	
	var strGFoodType = row.insertCell(5);
	strGFoodType.innerHTML = '<input type=text class=field name=gDoseNum' + id + ' size=2 maxlength=2 style="font-size: 10pt" value="' + (v8!=null?v8:'') + '">' + 
							 '/<input type=text class=field name=gDoseDay' + id + ' size=2 maxlength=2 style="font-size: 10pt" value="' + (v7!=null?v7:'') + '"><font size="2">(次數/日)</font>' + 
							 '<br><input type=text class=field name=gFrequency' + id + ' size=2 maxlength=2 style="font-size: 10pt" value="' + (v9!=null?v9:'') + '">' + 
							 '<select name=gFrequencyUnit'+id+' class=field style="font-size:10pt">'+"<%=View.getOptionCodeKind("DRG0305","")%>"+'</select>';
	$('select[name=gFrequencyUnit'+id+']').val((v10!=null?v10:''));					 
	
	var strGEdibleDate = row.insertCell(6);
	strGEdibleDate.innerHTML = '<font size="2">起</font><input type=text class=field name=gEdibleDateS' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v11!=null?v11:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_gEdibleDateS' + id + '" onClick="popCalendar(\'gEdibleDateS' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>' + 
							   '<br>' + 
						       '<font size="2">迄</font><input type=text class=field name=gEdibleDateE' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v12!=null?v12:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_gEdibleDateE' + id + '" onClick="popCalendar(\'gEdibleDateE' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>'; 
	var actionText = row.insertCell(7);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);createGTFOOT();">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyGFoodRow(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
}

function getPermitKey(fieldK, fieldN, fieldC, fieldE, fieldI){
	openCenterWindow(900, 600, "hfrex0112f.jsp?field1=" + fieldK + "&field2=" + fieldN + "&field3=" + fieldC + "&field4=" + fieldE + "&field5=" + fieldI);
}

function copyGFoodRow(tblId, rid) {
	var arrFd = new Array(12);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrGFoodFieldName[i] + rid))){
			arrFd[i] = document.all.item(arrGFoodFieldName[i] + rid).value;
		}
	} 	
	addGFoodRow(tblId, null, null, arrFd[2], arrFd[3], arrFd[4], arrFd[5], arrFd[6], arrFd[7], arrFd[8], arrFd[9], arrFd[10], arrFd[11]);
}

function clearGFoodTable(){
	var tbl = document.getElementById('gFood');
	var lastRow = tbl.rows.length;
	if(lastRow > 1){
		for(var i=1; i<lastRow; i++){		
			tbl.deleteRow(lastRow-i);
		}
	}
}

function createGTFOOT(){
	var tbl = $("#gFood").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='GFOODlistTFOOT'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}

function validateGFoodTable() {
	var GFArray = document.getElementsByName("GFoodRow");	
	if(GFArray!=null && GFArray.length>0){	
		for(var i=0; i<GFArray.length; i++){
			var id = GFArray[i].value;
			
			var objS = null;
			var objE = null;
			var sb = new StringBuffer();
			for(var j=0; j<arrGFoodFieldName.length; j++){
				var fd	= document.all.item(arrGFoodFieldName[j] + id);
				if(isObj(fd) && arrGFoodFieldZhName[j]!="") {
					if(j>=6 && j<=9){
						sb.append(checkNumber(fd, arrGFoodFieldZhName[j]));
					}else if(j >= 10){
						if(j == 10){
							objS = fd;
						}else{
							objE = fd;
						}
						var tmpS = checkDate(fd, arrGFoodFieldZhName[j]);
						if(tmpS.length == 0){
							if(fd.value > yyymmdd){
								sb.append(arrGFoodFieldZhName[j] + msgS);
								fd.style.backgroundColor = errorbg;
							}else{
								fd.style.backgroundColor = "";
							}
						}else{
							sb.append(tmpS);
						}
					}else{
						sb.append(checkEmpty(fd, arrGFoodFieldZhName[j]));
					}
				}
			}
			sb.append(checkDateSE(objS, objE, "食用日期"));
			if (sb.toString().length>0) return sb.toString();
		}
	}
	
	return "";
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="gFood">
	<thead id="GFOODlistTHEAD">
	<th class="listTH"><font size="2">許可證號</font></th>
	<th class="listTH"><font size="2">產品中文名稱</font></th>
	<th class="listTH"><font size="2">產品英文名稱</font></th>
	<th class="listTH"><font size="2">購買來源</font></th>
	<th class="listTH"><font size="2">成份</font></th>
	<th class="listTH"><font size="2">食用方式</font></th>
	<th class="listTH"><font size="2">食用日期</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addGFoodRow('gFood');" />
		</font>
	</th>
	</thead>
	<tbody id="GFOODlistTBODY">
		
	</tbody>
	<tfoot id="GFOODlistTFOOT">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>

<script type="text/javascript">
function checkGFOODlistTFOOT(){
	var tbl = $("#gFood").get(0);
	if(tbl.rows.length==2 && $("#GFOODlistTFOOT").html()!=null){
		addGFoodRow('gFood');
	}
}
</script>


