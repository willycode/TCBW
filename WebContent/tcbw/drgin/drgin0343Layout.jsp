<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
<%
String type = Common.get(request.getParameter("type"));
%>
var arrDrg43Field = new Array("medType","permitKey","permitNo","scientificName","productName","applicationName","manufactorName","manufactorNo","content","medModel","medPath","dosage","frequency","startDare","endDate","indication","manufactorID","medModelOther","medPathOther","frequencyOther");
var arrDrg43FieldName = new Array("","許可證字","許可證號","學名","商品名","申請商","製造廠","批號","含量","劑型","給藥途徑","劑量","頻率","起始日期","結束日期","適應症","製造廠","","","");
function addDrg43Row<%=type%>(tblId,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16,v17,v18,v19,v20,v21){
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = row.uniqueID?row.uniqueID:randomUUID();

	var typeText = row.insertCell(0);
	typeText.innerHTML = "<input type=hidden name=drg43Row value=" + id + ">" +
	"<input type=hidden class=field name='medType" + id + "' value='" + ((v1!=null && v1 != '')?v1:<%=type%>) + "'>" +
	"<select class='field' type='select'  id='permitKey" + id + "' name='permitKey" + id + "' value='" + (v2!=null?v2:'') + "' onChange='permitProdData(\""+ id +"\")' >" + "<%=View.getOptionCodeKind("DRGPKID","").replaceAll("\n","")%>" + "</select>"+
	"<br>第<input class='field' type='text' name='permitNo" + id + "' size=5 maxlength=6 value='" + (v3!=null?v3:'') + "' onChange='permitProdData(\""+ id +"\")'>號"+
	"<input class='field' type='button' name='btnQryExpense' onClick='permitDataQ(\"" + id + "\");' value='查詢' width='120px' >";
	getSelectedValue(document.all.item('permitKey' + id) ,v2);

	var scientifText = row.insertCell(1);
	scientifText.innerHTML = '<input type=text class=field name="scientificName' + id + '" size=50 maxlength=50 value="' + (v4!=null?v4:'') + '">';

	var productText = row.insertCell(2);
	productText.innerHTML = '<input type=text class=field name="productName' + id + '" size=50 maxlength=50 value="' + (v5!=null?v5:'') + '">';

	var applicationText = row.insertCell(3);
	applicationText.innerHTML = '<input type=text class=field name="applicationName' + id + '" size=50 maxlength=50 value="' + (v6!=null?v6:'') + '">';

	var manuNameText = row.insertCell(4);
	manuNameText.innerHTML = '<input type=hidden class=field name="manufactorID' + id + '" size=10 maxlength=10 value="' + (v17!=null?v17:'') + '">'
						+ '<input type=text class=field name="manufactorName' + id + '" size=50 maxlength=50 value="' + (v7!=null?v7:'') + '">';

	var manuNoText = row.insertCell(5);
	manuNoText.innerHTML = '<input type=text class=field name="manufactorNo' + id + '" size=3 maxlength=20 value="' + (v8!=null?v8:'') + '">';

	var contentText = row.insertCell(6);
	contentText.innerHTML = '<input type=text class=field name="content' + id + '" size=3 maxlength=10 value="' + (v9!=null?v9:'') + '">';

	var medText = row.insertCell(7);
	medText.innerHTML = "<select class='field' type='select'  id='medModel" + id + "' name='medModel" + id + "' value='" + (v10!=null?v10:'') + "'>" + "<%=View.getOptionCodeKind("DRGFLN","").replaceAll("\n","")%>" + "</select><br>"
	+ '<input type=text class=field name="medModelOther' + id + '" size=10 maxlength=10 value="' + (v18!=null?v18:'') + '">';
	getSelectedValue(document.all.item('medModel' + id) ,v10);

	var pathText = row.insertCell(8);
	pathText.innerHTML = "<select class='field' type='select'  id='medPath" + id + "' name='medPath" + id + "' value='" + (v11!=null?v11:'') + "'>" + "<%=View.getOptionCodeKind("DRG0304","").replaceAll("\n","")%>" + "</select><br>"
	+ '<input type=text class=field name="medPathOther' + id + '" size=10 maxlength=10 value="' + (v19!=null?v19:'') + '">';
	getSelectedValue(document.all.item('medPath' + id) ,v11);

	var doText = row.insertCell(9);
	doText.innerHTML = "<input type=text class='field' name='dosage" + id + "' size=5 maxlength=5 value='" + (v12!=null?v12:'') + "'>";

	var freText = row.insertCell(10);
	freText.innerHTML = "<select class='field' type='select'  id='frequency" + id + "' name='frequency" + id + "' value='" + (v13!=null?v13:'') + "'>" + "<%=View.getOptionCodeKind("DRG0306","").replaceAll("\n","")%>" + "</select><br>"
	+ '<input type=text class=field name="frequencyOther' + id + '" size=10 maxlength=10 value="' + (v20!=null?v20:'') + '">';
	getSelectedValue(document.all.item('frequency' + id),v13);

	var dateSText = row.insertCell(11);
	dateSText.innerHTML = ' <input type=text class=field_E name=startDare' + id + ' size=7 maxlength=7 value=\'' + (v14!=null?v14:'') + '\'>' +
	' <button class="field_E" type="button" onclick="popCalendar(\'startDare' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';

	var dateEText = row.insertCell(12);
	dateEText.innerHTML = ' <input type=text class=field_E name=endDate' + id + ' size=7 maxlength=7 value=\'' + (v15!=null?v15:'') + '\'>' +
	' <button class="field_E" type="button" onclick="popCalendar(\'endDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';

	var inText = row.insertCell(13);
	inText.innerHTML = '<input type=text class=field name="indication' + id + '" size=5 maxlength=25 value="' + (v16!=null?v16:'') + '">';
	
	var actionText = row.insertCell(14);
	actionText.align = 'center';
	var sb = new StringBuffer();
	if("03" == <%=type%>){
		sb.append('<input type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);">');
		sb.append(' <input type="button" class="field_btnAdd" value="" onclick="copyDrg43Row(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	}
	sb.append('<input type=hidden name=drg43Id').append(id).append(' value=' ).append( v21!=null?v21:'' ).append('>');		
	actionText.innerHTML = sb.toString();
}

function copyDrg43Row(tblId,rid) {
	var arrFd = new Array(20);
	for (var i=0; i<arrFd.length; i++) {		
		if (isObj(document.all.item(arrDrg43Field[i]+rid))) arrFd[i] = document.all.item(arrDrg43Field[i]+rid).value;
	} 	
	addDrg43Row<%=type%>(tblId,arrFd[0],arrFd[1],arrFd[2],arrFd[3],arrFd[4],arrFd[5],arrFd[6],arrFd[7],arrFd[8],arrFd[9],arrFd[10],arrFd[11],arrFd[12],arrFd[13],arrFd[14],arrFd[15],arrFd[16],arrFd[17],arrFd[18],arrFd[19],arrFd[20]);
}

function clearDrg43Table(){
	var tbl = document.getElementById('drg43Table<%=type%>');
	if (isObj(opener))
	{
		if(null != opener.document.getElementById('drg43Table<%=type%>') && "" != opener.document.getElementById('drg43Table<%=type%>'))
		{
			tbl = opener.document.getElementById('drg43Table<%=type%>');
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

function validateDrg43Table() {
	var sb = new StringBuffer();
	var drg43Row=document.getElementsByName("drg43Row");
	if (drg43Row!=null && drg43Row.length>0) {
		for (var i=0; i<drg43Row.length; i++) {
			var id = drg43Row[i].value;
			var sb = new StringBuffer();
			if(i < 2) {
				var medTypeDesc = "";
				if(i==0) medTypeDesc ="事件前使用藥品";
				else if(i==1) medTypeDesc ="懷疑療效不等藥品";
				
				for (var j=0; j<arrDrg43Field.length; j++) {
					var fd	= document.all.item(arrDrg43Field[j]+id);
					if (isObj(fd) && arrDrg43FieldName[j]!='') {
						sb.append(checkEmpty(fd,medTypeDesc+arrDrg43FieldName[j]));
					}
				}				
			}
			
			if("10" == getObjectValue("medModel"+id)){
				sb.append(checkEmpty(document.all.item("medModelOther"+id),"其他劑型"));
			}else{
				sb.append(checkMustEmpty(document.all.item("medModelOther"+id),"其他劑型"));
			}
			if("Z0" == getObjectValue("medPath"+id)){
				sb.append(checkEmpty(document.all.item("medPathOther"+id),"其他給藥途徑"));
			}else{
				sb.append(checkMustEmpty(document.all.item("medPathOther"+id),"其他給藥途徑"));
			}
			if("Q0" == getObjectValue("frequency"+id)){
				sb.append(checkEmpty(document.all.item("frequencyOther"+id),"其他頻率"));
			}else{
				sb.append(checkMustEmpty(document.all.item("frequencyOther"+id),"其他頻率"));
			}
		}
		if (sb.toString().length>0) return sb.toString();
	}
	return "";
}

function permitDataQ(id){
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var permitKey = getObjectValue("permitKey"+id);
	var permitNo = getObjectValue("permitNo"+id);
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	window.open("../drgex/drgex0104f.jsp?permitKey="+permitKey+"&permitNo="+permitNo+"&keyId="+id,'popWinE',prop);		
}

function setPermitData(permitStr, keyId){
	var permitKey = permitStr.substring(0,2);
	var permitNo = permitStr.substring(2,8);
	setObjectValue("permitKey"+keyId,permitKey);
	setObjectValue("permitNo"+keyId,permitNo);
	permitProdData(keyId);
}

function permitProdData(id){
	var permitKey = getObjectValue("permitKey"+id);
	var permitNo = getObjectValue("permitNo"+id);
	var x = getRemoteData("../../ajax/jsonDrgObjects.jsp", "&permitKey="+permitKey+"&permitNo="+permitNo );
	if(x!=null && x.length>0){
		var json1 = JSON.parse(x);
		setObjectValue("productName"+id, json1.obj1);
		setObjectValue("applicationName"+id, json1.obj2);
		setObjectValue("manufactorName"+id, json1.obj3);
		checkedRadio(document.all.item("medModel"+id), json1.obj4);
		setObjectValue("scientificName"+id, json1.obj6);
	}
}

</script>
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="drg43Table<%=type%>" onmouseover="this.className='tableMouseover';" onmouseout="this.className='tableMouseout';">
	  <thead id="listTHEAD">
	  	<tr>
	  	    <th class="listTH" colspan="14" style="text-align:left"><font color="#ffff00"><b><%=View.getCommonCodeName("DRG0307",type) %>(請往右拉填寫通報資料)</b></font></th>
		  	<th class="listTH" rowspan="2"><%if("03".equals(type)){%><input type="button" class="field_btnAdd" onclick="addDrg43Row<%=type%>('drg43Table<%=type%>');"/><%}%> </th>
		</tr>
	  	<tr>
		  	<th class="listTH">許可證字號</th>
			<th class="listTH">學名</th>
			<th class="listTH">商品名</th>
			<th class="listTH">申請商</th>
			<th class="listTH">製造廠</th>
			<th class="listTH">批號</th>
			<th class="listTH">含量</th>
			<th class="listTH">劑型</th>
			<th class="listTH">給藥途徑</th>
			<th class="listTH">劑量</th>
			<th class="listTH">頻率</th>
			<th class="listTH">起始日期</th>
			<th class="listTH">結束日期</th>
			<th class="listTH">適應症</th>
	  	</tr>    
	  </thead>
	  <tbody id="listTBODY" class="td_form_white">
	  </tbody>
	</table>	
