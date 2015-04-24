var readbg="#FFFFFF";
var editbg="#FFFFFF";

/*除PK欄位外全部欄位都設為open*/
function setUnpkOpen(){
  	for(var i=0; i<document.forms[0].elements.length; i++){
		obj = document.forms[0].elements[i];
		if (like(obj.className,"RO")){
			obj.style.backgroundColor=readbg;			
			if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				obj.disabled = true;
	   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
  				obj.disabled = true;
  	  		} else {	
				obj.style.backgroundColor=editbg;	
				obj.readOnly = true;
			}
		}else{		
			if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){
				if (like(obj.className,"field_P")){
					obj.style.backgroundColor=readbg;			
	   	    		obj.readOnly = true;
				}else{
					obj.style.backgroundColor=editbg;			
	    	    	obj.readOnly = false;
				}			
			}else if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
				if (like(obj.className,"field_P") || like(obj.className,"field_RO") || like(obj.className,"field_QRO")){
					obj.style.backgroundColor=readbg;
					obj.disabled = true;			
				}else{
					obj.style.backgroundColor=editbg;
					obj.disabled = false;	
				}	
	      	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
				if (like(obj.className,"field_P")){
					obj.disabled = true;			
				}else{
					obj.disabled = false;	
				}    	  	
      		}
		}
	}
}

function setAllReadonly(){
	for(var i=0; i<document.forms[0].elements.length; i++){  			
		obj = document.forms[0].elements[i];
		if (like(obj.className,"field_Q")){	
			if (like(obj.className,"RO")){	
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){									
	    	    	obj.readOnly = true;	
				}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.style.backgroundColor=readbg;
					obj.disabled = true;
			   	}
			 }
		}else{			
			if (like(obj.className,"RO")){			
				//obj.readOnly = true;	
				obj.style.backgroundColor=readbg;							
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){
					obj.style.backgroundColor=editbg;			
    	    		obj.readOnly = true;	
				}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
   			  		obj.disabled = true;
   	  			}				
			}else{
				if((obj.type=="text")||(obj.type=="textarea")||(obj.type=="password")){						
					obj.style.backgroundColor=readbg;			
    	    		obj.readOnly = true;	
				}else if((obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")){
					obj.style.backgroundColor=readbg;
					obj.disabled = true;
		   	  	}else if ((obj.type=="button")&&(obj.className!="toolbar_default")){
   			  		obj.disabled = true;
   	  			}
   	  		}
   	  	}
	}
}

/*submit之前將select打開*/
function beforeSubmit(){
	for(var i=0; i<document.forms[0].elements.length; i++){  			
		obj = document.forms[0].elements[i];
		if((obj.type=="select-one")||(obj.type=="select-multiple")||(obj.type=="select")||(obj.type=="checkbox")||(obj.type=="radio")) {		
			obj.disabled = false;
		}
	}
}

/*模仿sql like功能*/
function like(buttonName,likestr){
	return (buttonName.indexOf(likestr)>=0);
}