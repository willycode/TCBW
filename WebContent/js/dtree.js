/*--------------------------------------------------|
| dTree 2.05 | www.destroydrop.com/javascript/tree/ |
|---------------------------------------------------|
| Copyright (c) 2002-2003 Geir Landr?               |
|                                                   |
| This script can be used freely as long as all     |
| copyright messages are intact.                    |
|                                                   |
| Updated: 17.04.2003                               |
|--------------------------------------------------*/
/*updated by linheng for faster initialize @ 2006-08-02*/
//Modify by clive.chang Nov 15, 2006

function isObj(obj){
	return (!((obj==null)||(obj==undefined)));
}

// Node object

function Node(id, pid, name, url, title, target, icon, iconOpen, open) {
	this.id = id;
	this.pid = pid;
	this.name = name;
	this.url = url;
	this.title = title;
	this.target = target;
	this.icon = icon;
	this.iconOpen = iconOpen;
	this._io = open || false;
	this._is = false;
	this._ls = false;
	this._hc = false;
	this._ai = 0;
	this._p = null;
	
	// add by linheng for faster initialize
	this._fcp = -1;
	this._lcp = -1;
};

// Tree object
function dTree(objName, imagePath, functionPathObj) {
	this.config = {
		target			: null,
		folderLinks		: true,
		useSelection	: true,
		useCookies		: false,
		useLines		: true,
		useIcons		: true,
		useStatusText	: false,
		closeSameLevel	: false,
		inOrder			: false,
		objForm			: null,	
		checkboxPrefix	: '',		
		objPath			: null,		
		imagePath		: '../images/',		
		checkParent		: true,
		useEventLink	: false
	};

	if (isObj(imagePath) && imagePath.length>0) this.config.imagePath = imagePath;
	if (isObj(functionPathObj) && functionPathObj!=null) this.config.objPath = functionPathObj; 
	
	this.icon = {
		root		: this.config.imagePath + 'base.gif',
		folder		: this.config.imagePath + 'folder.gif',
		folderOpen	: this.config.imagePath + 'folderopen.gif',
		node		: this.config.imagePath + 'page.gif',
		empty		: this.config.imagePath + 'empty.gif',
		line		: this.config.imagePath + 'line.gif',
		join		: this.config.imagePath + 'join.gif',
		joinBottom	: this.config.imagePath + 'joinbottom.gif',
		plus		: this.config.imagePath + 'plus.gif',
		plusBottom	: this.config.imagePath + 'plusbottom.gif',
		minus		: this.config.imagePath + 'minus.gif',
		minusBottom	: this.config.imagePath + 'minusbottom.gif',
		nlPlus		: this.config.imagePath + 'nolines_plus.gif',
		nlMinus		: this.config.imagePath + 'nolines_minus.gif'
	};

	this.obj = objName;

	this.aNodes = [];

	this.aIndent = [];

	this.root = new Node(-1);

	this.selectedNode = null;

	this.selectedFound = false;

	this.completed = false;	

};



// Adds a new node to the node array
dTree.prototype.add = function(id, pid, name, url, title, target, icon, iconOpen, open) {
	this.aNodes[this.aNodes.length] = new Node(id, pid, name, url, title, target, icon, iconOpen, open);
};

// Open/close all nodes
dTree.prototype.openAll = function() {
	this.oAll(true);
};

dTree.prototype.closeAll = function() {

	this.oAll(false);

};



// Outputs the tree to the page

dTree.prototype.toString = function() {
	var sb = new StringBuffer();	
	sb.append('<div class="dtree">\n');
	if (document.getElementById) {
		if (this.config.useCookies) this.selectedNode = this.getSelected();
		this.initialize();
		sb.append(this.addNode(this.root));
	} else sb.append('Browser not supported.');

	sb.append('</div>');

	if (!this.selectedFound) this.selectedNode = null;
	this.completed = true;

	return sb.toString();

};

/*updated by linheng for faster initialize @ 2006-08-02*/
// initialize all nodes before output
dTree.prototype.initialize = function() {
	// get all open node id
	var aOpen = null;
	if(this.config.useCookies) {
	    aOpen = this.getCookie('cs' + this.obj).split('.');
	}
	var node, pNode;
    for(var n=0; n<this.aNodes.length; n++) {
	    pNode = null;
	    node = this.aNodes[n];
		node._ai = n;
		// is selected?
		if(this.config.useSelection && !this.selectedFound && node.id == this.selectedNode) {
			node._is = true;
			this.selectedNode = n;
			this.selectedFound = true;
		}
		// target
		if (!node.target && this.config.target) node.target = this.config.target;
		// find parent
		if(node.pid == this.root.id) {
		    pNode = this.root;
		} else {
		    // find in front
		    for(var m=n-1; m>=0; m--) {
			    if(this.aNodes[m].id == node.pid) {
				    pNode = this.aNodes[m];
					break;
				} else if(this.aNodes[m].pid == node.pid) {
				    pNode = this.aNodes[m]._p;
					break;
				}
			}
			if(!pNode) {
			    // find in behind
				for(var k=n+1; k<this.aNodes.length; k++) {
				    if(this.aNodes[k].id == node.pid) {
					    pNode = this.aNodes[k];
						break;
					}
				}
			}
		}
		// update parent status
		if(pNode) {
			if(pNode._hc) {
			    this.aNodes[pNode._lcp]._ls = false;
			} else {
			    pNode._hc = true;
				pNode._fcp = n;
				// folder link
			    if (!this.config.folderLinks) pNode.url = null;
	            // check if parent is open
		        if(pNode.id != this.root.id && this.config.useCookies && !pNode._io) {
		            for(var i=0; i<aOpen.length; i++) {
			            if(aOpen[i] == pNode.id) pNode._io = true;
			        }
		        }
			}
		    node._p = pNode;
			node._ls = true;
			pNode._lcp = n;
		}
	}
};

// Creates the tree structure

dTree.prototype.addNode = function(pNode) {
	var sb = new StringBuffer();

	var n=0;

	if (this.config.inOrder) n = pNode._ai;

	for (n; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == pNode.id) {

			var cn = this.aNodes[n];

			cn._p = pNode;

			cn._ai = n;

			this.setCS(cn);

			if (!cn.target && this.config.target) cn.target = this.config.target;

			if (cn._hc && !cn._io && this.config.useCookies) cn._io = this.isOpen(cn.id);

			if (!this.config.folderLinks && cn._hc) cn.url = null;

			if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) {

					cn._is = true;

					this.selectedNode = n;

					this.selectedFound = true;

			}

			sb.append(this.node(cn,n));
			
			if (cn._ls) break;

		}

	}
	return sb.toString();

};


// Creates the node icon, url and text

dTree.prototype.node = function(node, nodeId) {
	var sb = new StringBuffer().append('<div class="dTreeNode">').append(this.indent(node, nodeId));
	if (this.config.useIcons) {
		if (!node.icon) node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);
		if (!node.iconOpen) node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node;
		if (this.root.id == node.pid) {
			node.icon = this.icon.root;

			node.iconOpen = this.icon.root;

		}
		sb.append('<img id="i').append(this.obj).append(nodeId).append('" src="').append(((node._io) ? node.iconOpen : node.icon)).append('" alt="" />');

	}

	if (node.url) {
		if (node.title && this.config.useEventLink) sb.append(node.title);
		sb.append('<a id="s' ).append( this.obj ).append( nodeId ).append( '" class="' ).append( ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') ).append( '"');
		if (this.config.useEventLink)  sb.append(' href="#" ');
		else sb.append(' href="' ).append( node.url ).append( '&nodeId=' ).append( nodeId ).append( '"');
		if (node.title && this.config.useEventLink==false) sb.append(' title="').append(node.title).append('"');
		if (node.target) sb.append(' target="').append(node.target).append('"');
		if (this.config.useStatusText) sb.append(' onmouseover="window.status=\'' ).append( node.name ).append( '\';return true;" onmouseout="window.status=\'\';return true;" ');
		if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))
			sb.append(' onclick="javascript: ' ).append( this.obj ).append( '.s(' ).append( nodeId ).append( ');').append((this.config.useEventLink)?node.url.replaceAll('"','\'')+';':'').append('"');
		else if (this.config.useEventLink) sb.append(' onclick="javascript: ' ).append(node.url.replaceAll('"','\'')).append(');"');
		sb.append('>');
	} else if ((!this.config.folderLinks || !node.url) && node._hc && node.pid != this.root.id) {
		sb.append('<a href="javascript: ' ).append( this.obj ).append( '.o(' ).append( nodeId ).append( ');" class="node">');
	}
	sb.append(node.name);

	if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)) sb.append('</a>'); //str += '</a>';

	sb.append('</div>');

	if (node._hc) {
		sb.append('<div id="d' ).append( this.obj ).append( nodeId ).append( '" class="clip" style="display:' ).append( ((this.root.id == node.pid || node._io) ? 'block' : 'none') ).append( ';">');		
		sb.append(this.addNode(node));
		sb.append('</div>');
	}

	this.aIndent.pop();
	
	return sb.toString();

};



// Adds the empty and line icons

dTree.prototype.indent = function(node, nodeId) {

	var sb = new StringBuffer();

	if (this.root.id != node.pid) {

		for (var n=0; n<this.aIndent.length; n++)
			sb.append('<img src="' ).append( ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) ).append( '" alt="" />');

		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);

		if (node._hc) {
			sb.append('<a href="javascript: ' ).append( this.obj ).append( '.o(' ).append( nodeId ).append( ');"><img id="j' ).append( this.obj ).append( nodeId ).append( '" src="');

			if (!this.config.useLines) sb.append( (node._io) ? this.icon.nlMinus : this.icon.nlPlus);

			else  sb.append( (node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus ) );

			sb.append('" alt="" /></a>');

		} else sb.append('<img src="' ).append( ( (this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join ) : this.icon.empty) ).append( '" alt="" />');

	}
	return sb.toString();

};



// Checks if a node has any children and if it is the last sibling

dTree.prototype.setCS = function(node) {

	var lastId;

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id) node._hc = true;

		if (this.aNodes[n].pid == node.pid) lastId = this.aNodes[n].id;

	}

	if (lastId==node.id) node._ls = true;

};



// Returns the selected node

dTree.prototype.getSelected = function() {

	var sn = this.getCookie('cs' + this.obj);

	return (sn) ? sn : null;

};



// Highlights the selected node

dTree.prototype.s = function(id) {

	if (!this.config.useSelection) return;
	var cn = this.aNodes[id];
	if (cn._hc && !this.config.folderLinks) return;
	if (this.selectedNode != id) {
		if (this.selectedNode || this.selectedNode==0) {
			var eOld = document.getElementById("s" + this.obj + this.selectedNode);
			eOld.className = "node";
		}
		var eNew = document.getElementById("s" + this.obj + id);
		eNew.className = "nodeSel";
		this.selectedNode = id;
		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id);
	}
	if (this.config.objPath!=null) {
		var s = this.getAncestorsName(cn);
		this.config.objPath.innerHTML = s;
	}
};


// Toggle Open or close

dTree.prototype.o = function(id) {

	var cn = this.aNodes[id];

	this.nodeStatus(!cn._io, id, cn._ls);

	cn._io = !cn._io;

	if (this.config.closeSameLevel) this.closeLevel(cn);

	if (this.config.useCookies) this.updateCookie();

};



// Open or close all nodes

dTree.prototype.oAll = function(status) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) {

			this.nodeStatus(status, n, this.aNodes[n]._ls)

			this.aNodes[n]._io = status;

		}

	}

	if (this.config.useCookies) this.updateCookie();

};



// Opens the tree to a specific node

dTree.prototype.openTo = function(nId, bSelect, bFirst) {

	if (!bFirst) {

		for (var n=0; n<this.aNodes.length; n++) {

			if (this.aNodes[n].id == nId) {

				nId=n;

				break;

			}

		}

	}

	var cn=this.aNodes[nId];

	if (cn.pid==this.root.id || !cn._p) return;

	cn._io = true;

	cn._is = bSelect;

	if (this.completed && cn._hc) this.nodeStatus(true, cn._ai, cn._ls);

	if (this.completed && bSelect) this.s(cn._ai);

	else if (bSelect) this._sn=cn._ai;

	this.openTo(cn._p._ai, false, true);

};



// Closes all nodes on the same level as certain node

dTree.prototype.closeLevel = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc) {

			this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);

		}

	}

};



// Closes all children of a node

dTree.prototype.closeAllChildren = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) {

			if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);		

		}

	}

};


//Modify Start Nov 15, 2006
// get a node ancestors name
dTree.prototype.getAncestorsName = function(node) {
	var dummyId = node.id;	
	var ancestorsName = "";	
	do {
		for (var n=0; n<this.aNodes.length; n++) {
			if (this.aNodes[n].id == dummyId ) {
				if (ancestorsName=="") ancestorsName = this.aNodes[n].name;
				else ancestorsName = this.aNodes[n].name + " > > " + ancestorsName;
				dummyId = this.aNodes[n].pid;
			}
		}
	} while (dummyId!=-1);
	return ancestorsName;
};

dTree.prototype.hasElementChecked = function() {
	for (var n=0; n<this.aNodes.length; n++) {
		if (isObj(document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id))) {
			if (document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id).checked) return true;
		}
	}
	return false;
};

dTree.prototype.checkAllParent = function(node, flag) {
	var dummyId = node.pid;		
	do {
		for (var n=0; n<this.aNodes.length; n++) {
			if (this.aNodes[n].id == dummyId ) {
				if (isObj(document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id))) {
					document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id).checked = flag;
					flag = this.parseSameLevel(this.aNodes[n], true);
				}
				dummyId = this.aNodes[n].pid;
			}
		}	
	} while (dummyId!=-1)
};

dTree.prototype.checkAllChildren = function(node,flag) {
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n].pid == node.id) {		
			if (isObj(document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id))) {			
				document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id).checked = flag;	
			}
			this.checkAllChildren(this.aNodes[n], flag);		
		}
	}
};

dTree.prototype.parseSameLevel = function(node, bSelf) {
	for (var n=0; n<this.aNodes.length; n++) {
		if (bSelf) {
			if (this.aNodes[n].pid == node.pid) {
				if (isObj(document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id))) {
					if (document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id).checked) return true;
				}
			}			
		} else {
			if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id) {
				if (isObj(document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id))) {
					if (document.getElementById(this.config.checkboxPrefix+this.aNodes[n].id).checked) return true;
				}
			}
		}
	}
	return false;
};

dTree.prototype.checkBoxClick = function(obj,nName) {
	var node = null;
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n].id == obj.value) {
			node = this.aNodes[n];
			break;
		}
	}
	if (node._hc) this.checkAllChildren(node, obj.checked);	
	if (this.config.checkParent) {
		if (obj.checked) this.checkAllParent(node, true);
		else {
			obj.checked = false;
			this.checkAllParent(node, this.parseSameLevel(node));
		}
	}
};

/**
dTree.prototype.changeNodeValue = function(id) {
	try {
		if (!isNaN(parseInt(id))) {
			var cn = this.aNodes[id];
			cn.name = 'NodeNameChanged' + id;
		}
	} catch(e) { alert(e); }
};
**/
// End Modify


// Change the status of a node(open or closed)

dTree.prototype.nodeStatus = function(status, id, bottom) {

	eDiv	= document.getElementById('d' + this.obj + id);

	eJoin	= document.getElementById('j' + this.obj + id);

	if (this.config.useIcons) {

		eIcon	= document.getElementById('i' + this.obj + id);

		eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].icon;

	}

	eJoin.src = (this.config.useLines)?

	((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)):

	((status)?this.icon.nlMinus:this.icon.nlPlus);

	eDiv.style.display = (status) ? 'block': 'none';

};





// [Cookie] Clears a cookie

dTree.prototype.clearCookie = function() {

	var now = new Date();

	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);

	this.setCookie('co'+this.obj, 'cookieValue', yesterday);

	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);

};



// [Cookie] Sets value in a cookie

dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {

	document.cookie =

		escape(cookieName) + '=' + escape(cookieValue)

		+ (expires ? '; expires=' + expires.toGMTString() : '')

		+ (path ? '; path=' + path : '')

		+ (domain ? '; domain=' + domain : '')

		+ (secure ? '; secure' : '');

};



// [Cookie] Gets a value from a cookie

dTree.prototype.getCookie = function(cookieName) {

	var cookieValue = '';

	var posName = document.cookie.indexOf(escape(cookieName) + '=');

	if (posName != -1) {

		var posValue = posName + (escape(cookieName) + '=').length;

		var endPos = document.cookie.indexOf(';', posValue);

		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));

		else cookieValue = unescape(document.cookie.substring(posValue));

	}

	return (cookieValue);

};



// [Cookie] Returns ids of open nodes as a string

dTree.prototype.updateCookie = function() {

	var str = '';

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {

			if (str) str += '.';

			str += this.aNodes[n].id;

		}

	}

	this.setCookie('co' + this.obj, str);

};



// [Cookie] Checks if a node id is in a cookie

dTree.prototype.isOpen = function(id) {

	var aOpen = this.getCookie('co' + this.obj).split('.');

	for (var n=0; n<aOpen.length; n++)

		if (aOpen[n] == id) return true;

	return false;

};



// If Push and pop is not implemented by the browser

if (!Array.prototype.push) {

	Array.prototype.push = function array_push() {

		for(var i=0;i<arguments.length;i++)

			this[this.length]=arguments[i];

		return this.length;

	}

};

if (!Array.prototype.pop) {

	Array.prototype.pop = function array_pop() {

		lastElement = this[this.length-1];

		this.length = Math.max(this.length-1,0);

		return lastElement;

	}

};

function StringBuffer() { 
   this.buffer = []; 
}
StringBuffer.prototype.append = function append(string) { 
   this.buffer.push(string);
   return this; 
}; 
StringBuffer.prototype.toString = function toString() { 
	return this.buffer.join(""); 
};
String.prototype.replaceAll = function(s1,s2){return this.replace(new RegExp(s1,"gm"),s2);}