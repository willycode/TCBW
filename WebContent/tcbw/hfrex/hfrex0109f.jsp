<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
<%
String fileType = Common.get(request.getParameter("drugType")); 
%>
var arrFieldName<%=fileType%> = new Array( "<%=fileType%>ChProduct", "<%=fileType%>EnProduct", "<%=fileType%>Content", "<%=fileType%>MedModel", 
										   "<%=fileType%>DoseNum", "<%=fileType%>DoseDay", "<%=fileType%>FrequencyNum", "<%=fileType%>FrequencyMl",
										   "<%=fileType%>EdibleDateS", "<%=fileType%>EdibleDateE", "<%=fileType%>Brand", 
										   "<%=fileType%>permitNo" );
var arrFieldZhName<%=fileType%> = new Array("學名","商品名","","","劑型-次","劑量-日","劑量-顆","劑量-ML","使用日期-起","使用日期-迄","廠牌","");

function addDrugRow<%=fileType%>(tblId,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13){	
	$("#<%=fileType%>listTFOOT").remove();
	
	var type = "<%=fileType%>";
	idDRCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v13!=null?v13:(type + idDRCount);
	
    var strDrChProduct = row.insertCell(0);
    strDrChProduct.innerHTML = '<input type=hidden name=' + type + 'Row value=' + id + '><input type=text class=field name=' + type + 'ChProduct' + id + ' size=20 maxlength=90 style="font-size: 10pt" value="' + (v1!=null?v1:'') + '">' + 
    						   '<input type=text class=field name=' + type + 'EnProduct' + id + ' size=20 maxlength=90 style="font-size: 10pt" value="' + (v2!=null?v2:'') + '">';
    
	var strDrContent = row.insertCell(1);
	strDrContent.innerHTML = '<input type=text class=field name=' + type + 'Content' + id + ' size=15 maxlength=20 style="font-size: 10pt" value="' + (v3!=null?v3:'') + '">' + 
							 '<select style="font-size: 10pt" class="field" name='+ type + 'MedModel' + id + ' type="select"><%=View.getOption("select codeId,codeName from CommonCode where commonCodeKind.codeKindId='DRGFLN' and isStop<> 'Y' order by codeId","").replace("\n","").replace("'","\\'")%></select>';
	document.all.item(type+'MedModel'+id).value = (v4!=null?v4:'');						 

	var strDrFrequency = row.insertCell(2);
	strDrFrequency.innerHTML = '<input type=text class=field name=' + type + 'DoseNum' + id + ' size=2 maxlength=2 style="font-size: 10pt" value="' + (v5!=null?v5:'') + '">/' + 
							   '<input type=text class=field name=' + type + 'DoseDay' + id + ' size=2 maxlength=2 style="font-size: 10pt" value="' + (v6!=null?v6:'') + '"><font size="2">(次數/日)</font>' + '<br>' + 
							   '<input type=text class=field name=' + type + 'Frequency' + id + ' size=2 maxlength=3 style="font-size: 10pt" value="' + (v7!=null?v7:'') + '">' +
	        				   '<select name='+type+'FrequencyUnit'+id+' class=field style="font-size:10pt">'+"<%=View.getOptionCodeKind("DRG0305","")%>"+'</select>';
	$('select[name='+type+'FrequencyUnit'+id+']').val((v8!=null?v8:''));	

	var strDate = row.insertCell(3);
	strDate.innerHTML = '<font size="2">起</font><input type=text class=field name=' + type + 'EdibleDateS' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v9!=null?v9:'') + '"><button class=field style="font-size: 10px" type="button" name=btn_' + type + 'EdibleDateS' + id + '" onClick="popCalendar(\'' + type + 'EdibleDateS' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>' + 
						'<br>' + 
						'<font size="2">迄</font><input type=text class=field name=' + type + 'EdibleDateE' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v10!=null?v10:'') + '"><button class=field style="font-size: 10px" type="button" name=btn_' + type + 'EdibleDateE' + id + '" onClick="popCalendar(\'' + type + 'EdibleDateE' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
	var strDrBrand = row.insertCell(4);
	strDrBrand.innerHTML = '<input type=text class=field name=' + type + 'Brand' + id + ' size=15 maxlength=20 style="font-size: 10pt" value="' + (v11!=null?v11:'') + '">';
	
	var strDrFontLicense = row.insertCell(5);
	strDrFontLicense.innerHTML = '<input type=text class=field name=' + type + 'permitNo' + id + ' size=20 maxlength=20 style="font-size: 10pt" value="' + (v12!=null?v12:'') + '">';
	
	var actionText = row.insertCell(6);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);create' + type + 'TFOOT();">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyDrugRow' + type + '(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
}

function copyDrugRow<%=fileType%>(tblId, rid) {
	var arrFd = new Array(12);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrFieldName<%=fileType%>[i] + rid))){
			arrFd[i] = document.all.item(arrFieldName<%=fileType%>[i] + rid).value;
		}
	} 	
	addDrugRow<%=fileType%>(tblId, arrFd[0], arrFd[1], arrFd[2], arrFd[3], arrFd[4], arrFd[5], arrFd[6], arrFd[7], arrFd[8], arrFd[9], arrFd[10], arrFd[11]);
}

function validateDrugTable<%=fileType%>() {
	var drugArray = document.getElementsByName("<%=fileType%>Row");
	if(drugArray!=null && drugArray.length>0){	
		for(var i=0; i<drugArray.length; i++){
			var flag = false;
			var objS = null;
			var objE = null;
			var id = drugArray[i].value;
			var sb = new StringBuffer();
			for(var j=0; j<arrFieldName<%=fileType%>.length; j++){
				var fd	= document.all.item(arrFieldName<%=fileType%>[j] + id);
				if(isObj(fd) && arrFieldZhName<%=fileType%>[j]!="") {
					if(j==0 || j==1){
						if(fd.value != ""){
							flag = true;
						}
					}else if(j>=4 && j<=7){
						sb.append(checkNumber(fd, arrFieldZhName<%=fileType%>[j]));
					}else if(j==8 || j==9){
						if(j == 8){
							objS = fd;
						}else{
							objE = fd;
						}
						var tmpS = checkDate(fd, arrFieldZhName<%=fileType%>[j]);
						if(tmpS.length == 0){
							if(fd.value > yyymmdd){
								sb.append(arrFieldZhName<%=fileType%>[j] + msgS);
								fd.style.backgroundColor = errorbg;
							}else{
								fd.style.backgroundColor = "";
							}
						}else{
							sb.append(tmpS);
						}
					}else{
						sb.append(checkEmpty(fd, arrFieldZhName<%=fileType%>[j]));
					}
				}
			}
			if(!flag){
				sb.append("學名/商品名需輸入其中一項!\n");
			}
			sb.append(checkDateSE(objS, objE, "使用日期"));
			if (sb.toString().length>0) return sb.toString();
		}
	}
	
	return "";
}

function create<%=fileType%>TFOOT(){
	var tbl = $("#tabDrug<%=fileType%>").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='<%=fileType%>listTFOOT'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}

</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabDrug<%=fileType%>">
	<thead id="<%=fileType%>listTHEAD">
	<th class="listTH"><font size="2">學名/商品名</font></th>
	<th class="listTH"><font size="2">含量/劑型</font></th>
	<th class="listTH"><font size="2">劑量/頻率</font></th>
	<th class="listTH"><font size="2">使用起迄日期</font></th>
	<th class="listTH"><font size="2">廠牌</font></th>
	<th class="listTH"><font size="2">許可字號</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addDrugRow<%=fileType%>('tabDrug<%=fileType%>');" />
		</font>
	</th>
	</thead>
	
	<tbody id="<%=fileType%>listTBODY">
	</tbody>
	
	<tfoot id="<%=fileType%>listTFOOT">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>

<script type="text/javascript">
function check<%=fileType%>listTFOOT(){
	var tbl = $("#tabDrug<%=fileType%>").get(0);
	if(tbl.rows.length==2 && $("#<%=fileType%>listTFOOT").html()!=null){
		addDrugRow<%=fileType%>('tabDrug<%=fileType%>');
	}
}
</script>





