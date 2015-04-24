<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

<%
String isNeedShowDocNo = Common.get(request.getParameter("isNeedShowDocNo"));
%>

<script type="text/javascript">
var idNCount = 0;
var arrNFoodFieldName = new Array("nPermitKey","nPermitNo","nChProduct","nEnProduct","nBuySource","nIngredient", "nDoseDay", "nDoseNum", "nFrequencyNum", "nFrequencyMl", "nEdibleDateS", "nEdibleDateE");
var arrNFoodFieldZhName = new Array("","","","","購買來源","","食用方式-日","食用方式-次數","食用方式-顆","食用方式-ML","食用日期-起","食用日期-迄");

function addNFoodRow(tblId,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13){
	$("#NFOODlistTFOOT").remove();
	
	var isNeedShow = "<%=isNeedShowDocNo%>";
	
	if(v13!=null && v13!=""){
		if(v13.indexOf("nTmp") != -1){
			idNCount = parseInt(v13.substring(4, v13.length));
		}
	}else{
		idNCount += 1;
	}
	
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v13!=null?v13:("nTmp" + idNCount);
	
    var strNFontLicense = row.insertCell(0);
    strNFontLicense.innerHTML = '<input type=hidden name=NFoodRow value=' + id + '><input type=text class=field name=nPermitNo' + id + ' size=20 maxlength=25 style="font-size: 10pt" value="' + (v2!=null?v2:'') + '">';
    strNFontLicense.innerHTML += '<input type=button class="field" name=getNonPermit' + id + ' value="核備字號" onclick="getNonPermitKey(\'nPermitNo' + id + '\',\'nChProduct' + id + '\',\'nEnProduct' + id + '\');">'; 
    	
    if(isNeedShow == "Y"){
    	strNFontLicense.innerHTML += '<input type=button class="field" name=showNonPermit' + id + ' value="檢視食品資料" onclick="showNonPermitKey(form1.nPermitNo' + id + '.value);">';
    }
    
	var strNChProduct = row.insertCell(1);
	strNChProduct.innerHTML = '<input type=text class=field name=nChProduct' + id + ' size=20 maxlength=150 style="font-size: 10pt" value="' + (v3!=null?v3:'') + '">';

	var strNEnProduct = row.insertCell(2);
	strNEnProduct.innerHTML = '<input type=text class=field name=nEnProduct' + id + ' size=20 maxlength=150 style="font-size: 10pt" value="' + (v4!=null?v4:'') + '">';
	
	var strNBuySource = row.insertCell(3);
	strNBuySource.innerHTML = '<select style="font-size: 10pt" class="field" name=nBuySource' + id + ' type="select"><%=View.getOption("select codeId,codeName from CommonCode where commonCodeKind.codeKindId='HFRBYS' and isStop<> 'Y' order by codeId","").replace("\n","").replace("'","\\'")%></select>';
	document.all.item('nBuySource'+id).value = (v5!=null?v5:'');
	
	var strNIngredient = row.insertCell(4);
	strNIngredient.innerHTML = '<input type=text class=field name=nIngredient' + id + ' size=25 maxlength=40 style="font-size: 10pt" value="' + (v6!=null?v6:'') + '">';
	
	var strNFoodType = row.insertCell(5);
	strNFoodType.innerHTML = '<input type=text class=field name=nDoseNum' + id + ' size=2 maxlength=2 style="font-size: 10pt" value="' + (v8!=null?v8:'') + '">' + 
								 '/<input type=text class=field name=nDoseDay' + id + ' size=2 maxlength=2 style="font-size: 10pt" value="' + (v7!=null?v7:'') + '"><font size="2">(次數/日)</font>' + 
								 '<br><input type=text class=field name=nFrequency' + id + ' size=2 maxlength=2 style="font-size: 10pt" value="' + (v9!=null?v9:'') + '">' + 
								 '<select name=nFrequencyUnit'+id+' class=field style="font-size:10pt">'+"<%=View.getOptionCodeKind("DRG0305","")%>"+'</select>';
	$('select[name=nFrequencyUnit'+id+']').val((v10!=null?v10:''));
	 
	var strNEdibleDate = row.insertCell(6);
	strNEdibleDate.innerHTML = '<font size="2">起</font><input type=text class=field name=nEdibleDateS' + id + ' onblur="setDateDiff(\'nEdibleDate\',\''+id+'\');" size=7 maxlength=7 style="font-size: 10pt" value="' + (v11!=null?v11:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_nEdibleDateS' + id + '" onClick="popCalendar(\'nEdibleDateS' + id + '&js=setDateDiff(\\\'nEdibleDate\\\',\\\''+id+'\\\')\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>' +
							   '<br>' + 
							   '<font size="2">迄</font><input type=text class=field name=nEdibleDateE' + id + ' onblur="setDateDiff(\'nEdibleDate\',\''+id+'\');" size=7 maxlength=7 style="font-size: 10pt" value="' + (v12!=null?v12:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_nEdibleDateE' + id + '" onClick="popCalendar(\'nEdibleDateE' + id + '&js=setDateDiff(\\\'nEdibleDate\\\',\\\''+id+'\\\')\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>' +
							   "<br/><div id='nEdibleDate"+id+"'><div/>";
						   		
	var actionText = row.insertCell(7);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);createNTFOOT();">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyNFoodRow(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
	
	setDateDiff('nEdibleDate',id);
}

function showNonPermitKey(pNo){
	if(pNo != ""){
		openCenterWindow(900, 600, "hfrin0413f.jsp?permitNo=" + pNo);
	}else{
		alert("核備字號不完整，請重新輸入");
	}
}

function getNonPermitKey(fieldN, fieldC, fieldE){
	openCenterWindow(900, 600, "../hfrex/hfrex0113f.jsp?field1=" + fieldN + "&field2=" + fieldC + "&field3=" + fieldE);
}

function copyNFoodRow(tblId, rid) {
	var arrFd = new Array(12);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrNFoodFieldName[i] + rid))){
			arrFd[i] = document.all.item(arrNFoodFieldName[i] + rid).value;
		}
	} 	
	addNFoodRow(tblId, arrFd[0], arrFd[1], arrFd[2], arrFd[3], arrFd[4], arrFd[5], arrFd[6], arrFd[7], arrFd[8], arrFd[9], arrFd[10], arrFd[10]);
}

function clearNFoodTable(){
	var tbl = document.getElementById('nFood');
	var lastRow = tbl.rows.length;
	if(lastRow > 1){
		for(var i=1; i<lastRow; i++){		
			tbl.deleteRow(lastRow-i);
		}
	}
}

function validateNFoodTable(){
	var NFArray = document.getElementsByName("NFoodRow");	
	if(NFArray!=null && NFArray.length>0){	
		for(var i=0; i<NFArray.length; i++){
			var id = NFArray[i].value;
			
			var objS = null;
			var objE = null;
			var sb = new StringBuffer();
			for(var j=0; j<arrNFoodFieldName.length; j++){
				var fd	= document.all.item(arrNFoodFieldName[j] + id);
				if(isObj(fd) && arrNFoodFieldZhName[j]!="") {
					if(j>=6 && j<=9){
						sb.append(checkNumber(fd, arrNFoodFieldZhName[j]));
					}else if(j >= 10){
						if(j == 10){
							objS = fd;
						}else{
							objE = fd;
						}
						var tmpS = checkDate(fd, arrNFoodFieldZhName[j]);
						if(tmpS.length == 0){
							if(fd.value > yyymmdd){
								sb.append(arrNFoodFieldZhName[j] + msgS);
								fd.style.backgroundColor = errorbg;
							}else{
								fd.style.backgroundColor = "";
							}
						}else{
							sb.append(tmpS);
						}
					}else{
						sb.append(checkEmpty(fd, arrNFoodFieldZhName[j]));
					}
				}
			}
			sb.append(checkDateSE(objS, objE, "食用日期"));
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}

function createNTFOOT(){
	var tbl = $("#nFood").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='NFOODlistTFOOT'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="nFood">
	<thead id="NFOODlistTHEAD">
	<th class="listTH"><font size="2">核備字號</font></th>
	<th class="listTH"><font size="2">產品中文名稱</font></th>
	<th class="listTH"><font size="2">產品英文名稱</font></th>
	<th class="listTH"><font size="2">購買來源</font></th>
	<th class="listTH"><font size="2">成份</font></th>
	<th class="listTH"><font size="2">食用方式</font></th>
	<th class="listTH"><font size="2">食用日期</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addNFoodRow('nFood');" />
		</font>
	</th>
	</thead>
	
	<tbody id="NFOODlistTBODY">
	</tbody>
	<tfoot id="NFOODlistTFOOT">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>

<script type="text/javascript">
function checkNFOODlistTFOOT(){
	var tbl = $("#nFood").get(0);
	if(tbl.rows.length==2 && $("#NFOODlistTFOOT").html()!=null){
		addNFoodRow('nFood');
	}
}
</script>
