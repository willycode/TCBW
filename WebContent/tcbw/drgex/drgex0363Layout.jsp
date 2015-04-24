<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
<%
	String type = Common.get(request.getParameter("type"));
%>
var idDCount = 0;
var arrDrg63Field = new Array("medType","permitKey","permitNo","scientificName","productName","applicationName","manufactorName","manufactorNo","content","medModel","medPath","dosage","frequency","startDare","endDate","indication","manufactorID","medModelOther","medPathOther","frequencyOther");
var arrDrg63FieldName = new Array("","許可證字","許可證號","學名","商品名","申請商","製造廠","批號","含量","劑型","給藥途徑","劑量","頻率","起始日期","結束日期","適應症","製造廠","","","");
function addDrg63Row<%=type%>(tblId,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16,v17,v18,v19,v20,v21){
	idDCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);	
	var id = "dTmp_" + idDCount;
	var typeText = row.insertCell(0);
	typeText.innerHTML = "<input type=hidden name=drg63Row value=" + id + ">" +
	"<input type=hidden class=field name='medType" + id + "' value='" + ((v1!=null && v1 != '')?v1:'<%=type%>') + "'>" +
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
	manuNameText.innerHTML = '<input type=hidden class=field name="manufactorID' + id + '" value="' + (v17!=null?v17:'') + '">'
						+'<input type=text class=field name="manufactorName' + id + '" size=50 maxlength=50 value="' + (v7!=null?v7:'') + '">';

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
		sb.append(' <input type="button" class="field_btnAdd" value="" onclick="copyDrg63Row(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
		
	}
	sb.append('<input type=hidden name=drg63Id').append(id).append(' value=' ).append( v21!=null?v21:'' ).append('>');		
	actionText.innerHTML = sb.toString();
}

function copyDrg63Row(tblId,rid) {
	var arrFd = new Array(20);
	for (var i=0; i<arrFd.length; i++) {		
		if (isObj(document.all.item(arrDrg63Field[i]+rid))) arrFd[i] = document.all.item(arrDrg63Field[i]+rid).value;
	} 	
	addDrg63Row<%=type%>(tblId,arrFd[0],arrFd[1],arrFd[2],arrFd[3],arrFd[4],arrFd[5],arrFd[6],arrFd[7],arrFd[8],arrFd[9],arrFd[10],arrFd[11],arrFd[12],arrFd[13],arrFd[14],arrFd[15],arrFd[16],arrFd[17],arrFd[18],arrFd[19],arrFd[20]);
}

function clearDrg63Table(type){
	if(null == type || "" == type){
		type = "<%=type%>";
	}
	var tbl = document.getElementById('drg63Table'+type);
	if (isObj(opener))
	{
		if(null != opener.document.getElementById('drg63Table'+type) && "" != opener.document.getElementById('drg63Table'+type))
		{
			tbl = opener.document.getElementById('drg63Table'+type);
		}
	}
	var lastRow = tbl.rows.length;
	if (lastRow > 1) {
		while ( tbl.rows.length != 2 )
		{
			tbl.deleteRow(tbl.rows.length-1);
		}
	}
}

function validateDrg63Table() {
	var sb = new StringBuffer();
	var drg63Row=document.getElementsByName("drg63Row");
	if (drg63Row!=null && drg63Row.length>0) {
		var sb = new StringBuffer();
		for (var i=0; i<drg63Row.length; i++) {
			var id = drg63Row[i].value;			
			if(i < 2) {
				var medTypeDesc = "";
				if(i==0) medTypeDesc ="事件前使用藥品";
				else if(i==1) medTypeDesc ="懷疑療效不等藥品";
				
				for (var j=0; j<arrDrg63Field.length; j++) {
					var fd	= document.all.item(arrDrg63Field[j]+id);
					if (isObj(fd) && arrDrg63FieldName[j]!='') {
						sb.append(checkEmpty(fd,medTypeDesc+arrDrg63FieldName[j]));
					}
				}				
			}		
			
			if("10" == getObjectValue("medModel"+id)){
				sb.append(checkEmpty(document.all.item("medModelOther"+id),"其他劑型"));
			}
			if("Z0" == getObjectValue("medPath"+id)){				
				sb.append(checkEmpty(document.all.item("medPathOther"+id),"其他給藥途徑"));
			}
			if("Q0" == getObjectValue("frequency"+id)){
				sb.append(checkEmpty(document.all.item("frequencyOther"+id),"其他頻率"));
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
	window.open("drgex0104f.jsp?permitKey="+permitKey+"&permitNo="+permitNo+"&keyId="+id,'popWinE',prop);		
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
	if(null != permitKey && "" != permitKey && null != permitNo && "" != permitNo){
		var x = getRemoteData("../../ajax/jsonDrgObjects.jsp", "&permitKey="+permitKey+"&permitNo="+permitNo );
		if(x!=null && x.length>0){
			var json1 = JSON.parse(x);
			setObjectValue("productName"+id, json1.obj1);
			setObjectValue("applicationName"+id, json1.obj2);
			setObjectValue("manufactorName"+id, json1.obj3);
			checkedRadio(document.all.item("medModel"+id), json1.obj4);
			setObjectValue("scientificName"+id, json1.obj6);
			setObjectValue("manufactorID"+id, json1.obj4);
		}
	}
	
}

function getDrg63Table() {
	var medTypeStr = "01,02,03";
	var x = getRemoteData('../../ajax/jsonDrg6003Dbs.jsp',getObjectValue("id"));
	var drg63Row=document.getElementsByName("drg63Row");
	if (x!=null && x.length>0) {
		if (drg63Row!=null && drg63Row.length>0) {
			var medTypes = medTypeStr.split(",");
			for(var i = 0; i<medTypes.length; i++){
				clearDrg63Table(medTypes[i]);
			}
		}
		var json = JSON.parse(x);
		for (var i=0; i<json.length; i++) {
			if("01" == json[i].medType){
				addDrg63Row01("drg63Table01",json[i].medType,json[i].permitKey,json[i].permitNo,json[i].scientificName,json[i].productName,json[i].applicationName,json[i].manufactorName,json[i].manufactorNo,json[i].content,json[i].medModel,json[i].medPath,json[i].dosage,json[i].frequency,json[i].startDare,json[i].endDate,json[i].indication,json[i].manufactorID,json[i].medModelOther,json[i].medPathOther,json[i].frequencyOther, json[i].id);
			}else if("02" == json[i].medType){
				addDrg63Row02("drg63Table02",json[i].medType,json[i].permitKey,json[i].permitNo,json[i].scientificName,json[i].productName,json[i].applicationName,json[i].manufactorName,json[i].manufactorNo,json[i].content,json[i].medModel,json[i].medPath,json[i].dosage,json[i].frequency,json[i].startDare,json[i].endDate,json[i].indication,json[i].manufactorID,json[i].medModelOther,json[i].medPathOther,json[i].frequencyOther, json[i].id);
			}else if("03" == json[i].medType){
				addDrg63Row03("drg63Table03",json[i].medType,json[i].permitKey,json[i].permitNo,json[i].scientificName,json[i].productName,json[i].applicationName,json[i].manufactorName,json[i].manufactorNo,json[i].content,json[i].medModel,json[i].medPath,json[i].dosage,json[i].frequency,json[i].startDare,json[i].endDate,json[i].indication,json[i].manufactorID,json[i].medModelOther,json[i].medPathOther,json[i].frequencyOther, json[i].id);
			}
		}
	}
}

</script>
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="drg63Table<%=type%>" onmouseover="this.className='tableMouseover';" onmouseout="this.className='tableMouseout';">
	  <thead id="listTHEAD">
	  	<tr>
		  	<th class="listTH" colspan="14" style="text-align:left"><font color="#ffff00"><b><%=View.getCommonCodeName("DRG0307",type) %>(請往右拉填寫通報資料)</b></font></th>
		  	<th class="listTH" rowspan="2"><%if("03".equals(type)){%><input type="button" class="field_btnAdd" onclick="addDrg63Row<%=type%>('drg63Table<%=type%>');"/><%}%> </th>
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
