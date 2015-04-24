/*---------------------------------------------------|
| comboBoxControl beta								 |
|----------------------------------------------------|
| 													 |
| Please feel free to use, modify or redistribute	 |
| this script.										 |
| 													 |
| Clive Chang                                        |
| Sep 4, 2009			                             |
|---------------------------------------------------*/
function comboBoxControl(txtId,uri,hql,fdList,fdField,isFixOption) {
	this.textbox = document.getElementById(txtId);
	this.table = document.getElementById('table_'+txtId);	
	this.btn = document.getElementById('btn_'+txtId);	
	this.uri = uri;
	this.hql = hql;
	this.fdList = fdList;
	this.fdField = fdField;	
	this.isFixOption = isFixOption;
	this.layer = document.getElementById('layer_'+txtId);
    this.layer.className = 'comboBoxControl_layer';
    //this.layer.style.position = 'absolute';
    this.layer.style.display = 'none';    	
	this.initComboBoxControl();	
}

comboBoxControl.prototype.initComboBoxControl = function () {
    var oThis = this;
	oThis.textbox.onblur = oThis.textbox.onchange = function () {
		oThis.hideComboBoxControl();
	}; 
	oThis.layer.onblur = function() {
		oThis.hideComboBoxControl();
    };	
    oThis.layer.onchange = function() {
    	oThis.textbox.value = oThis.layer.options[oThis.layer.selectedIndex].text;
    	oThis.hideComboBoxControl();
    	oThis.textbox.focus();
    };	
    oThis.btn.onmouseover = function() {
    	oThis.btn.className = 'comboBoxControl_btn_mouseover';
    };
    oThis.btn.onmouseout = function() {
    	oThis.btn.className = 'comboBoxControl_btn';
    };    
    oThis.btn.onmousedown = function (evt) {
    	evt = evt || window.event;
        if (evt.type == "mousedown") oThis.bindComboOptions();
	};    
};

comboBoxControl.prototype.hideComboBoxControl = function () {
	this.layer.style.display = 'none';
	this.table.style.display = '';
};
comboBoxControl.prototype.showComboBoxControl = function () {	
	this.layer.style.width = this.textbox.offsetWidth+19;
	this.layer.style.display = '';	
	this.table.style.display = 'none';
};

comboBoxControl.prototype.bindComboOptions = function() {		
	var oThis = this;	
	if(oThis.layer.style.display=='none') {
		if (oThis.isFixOption) {
			oThis.showComboBoxControl();
		} else {
			oThis.layer.options.length=0;
		    var i = 0;
		    var q = new StringBuffer().append(oThis.hql);
		    if (oThis.fdList!=null && oThis.fdList.length>0 && oThis.fdField!=null && oThis.fdField.length>0) {
		    	var fds = oThis.fdList.split(",");
		    	var fdv = oThis.fdField.split(",");
		    	if (fds.length==fdv.length) {
			    	for (i=0; i<fds.length; i++) {
			    		q.append(" and ").append(fds[i]).append("='").append(document.all.item(fdv[i]).value).append("' ");
			    	}	    		
		    	}
		    }
			var x = getRemoteData(oThis.uri,q.toString());			
			if (x!=null && x.length>0) {
				var obj = oThis.layer.options;
				obj.add(new Option(oThis.textbox.value,-1));
				var json = eval('(' + x + ')');			
				for (i=0; i<json.length ; i++) {
					obj.add(new Option(json[i].obj0,i));		    	
				}   			
		        oThis.showComboBoxControl();
			}			
		}		
	} else {
		oThis.hideComboBoxControl();
	}
};