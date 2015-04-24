var _replaceContext = false;		// replace the system context menu?
var _mouseOverContext = false;		// is the mouse over the context menu?
var _noContext = false;				// disable the context menu?
var _divContext = $('divContext');	// makes my life easier

InitContext();

function InitContext()
{
	_divContext.onmouseover = function() { _mouseOverContext = true; };
	_divContext.onmouseout = function() { _mouseOverContext = false; };
	
	//$('aDisable').onclick = DisableContext;
	//$('aEnable').onclick = EnableContext;
	
	$('body').onmousedown = ContextMouseDown;
	$('body').oncontextmenu = ContextShow;
}

function getURLParameter( url, name ) {
  var regexS = "[\\?&]"+name+"=([^&#]*)";
  var regex = new RegExp( regexS );
  var results = regex.exec( url );
  if( results == null )
    return "";
  else
    return results[1];
}

// call from the onMouseDown event, passing the event if standards compliant
function ContextMouseDown(event)
{
	if (_noContext || _mouseOverContext)
		return;

	// IE is evil and doesn't pass the event object
	if (event == null)
		event = window.event;
		
	// we assume we have a standards compliant browser, but check if we have IE
	var target = event.target != null ? event.target : event.srcElement;
		
	// only show the context menu if the right mouse button is pressed
	//   and a hyperlink has been clicked (the code can be made more selective)
	if (event.button == 2 && target.tagName.toLowerCase() == 'a') {	
		_replaceContext = true;
	}
	else if (!_mouseOverContext)
		_divContext.style.display = 'none';
}

function CloseContext()
{
	_mouseOverContext;
	_divContext.style.display = 'none';
}

// call from the onContextMenu event, passing the event
// if this function returns false, the browser's context menu will not show up
function ContextShow(event)
{
	if (_noContext || _mouseOverContext)
		return;

	// IE is evil and doesn't pass the event object
	if (event == null)
		event = window.event;
		
	// we assume we have a standards compliant browser, but check if we have IE
	var target = event.target != null ? event.target : event.srcElement;
	
	if (_replaceContext) 
	{
		/**
		var params = $('aInsertNav').href;		
		params = params.substring(params.lastIndexOf("/")+1);		
		$('aInsertNav').href = target.href + "&state=insert";		
		$('aUpdateNav').href = target.href + "&state=update";		
		$('aDeleteNav').href = target.href + "&state=delete";
		**/
		var aDisplay = 'block';
		$('aInsertNav').style.display = '';
		$('aUpdateNav').style.display = '';	
		$('aDeleteNav').style.display = '';				
		var aNav = getURLParameter(target.href,'aNav');
		if (aNav!=null && aNav.length>0) {
			if (aNav!='-1') {
				if (aNav.indexOf('1')!=-1) $('aInsertNav').href = "javascript:popFormFolders('"+target.href + "&exeKind=insert');CloseContext();";	
				else $('aInsertNav').style.display = 'none';
				
				if (aNav.indexOf('2')!=-1) $('aUpdateNav').href = "javascript:popFormFolders('"+target.href + "&exeKind=update');CloseContext();";
				else $('aUpdateNav').style.display = 'none';
				
				if (aNav.indexOf('3')!=-1) $('aDeleteNav').href = "javascript:popFormFolders('"+target.href + "&exeKind=delete');CloseContext();";
				else $('aDeleteNav').style.display = 'none';
			} else {
				aDisplay = 'none';
			}
		} else {
			$('aInsertNav').href = "javascript:popFormFolders('"+target.href + "&exeKind=insert');CloseContext();";		
			$('aUpdateNav').href = "javascript:popFormFolders('"+target.href + "&exeKind=update');CloseContext();";		
			$('aDeleteNav').href = "javascript:popFormFolders('"+target.href + "&exeKind=delete');CloseContext();";		
		}
			
		/**
		$('aAddWebmark').href = 'http://luke.breuer.com/webmark/?addurl=' +
			encodeURIComponent(target.href) + '&title=' +
			encodeURIComponent(target.innerHTML);
		**/
		
		// document.body.scrollTop does not work in IE
		var scrollTop = document.body.scrollTop ? document.body.scrollTop : 
			document.documentElement.scrollTop;
		var scrollLeft = document.body.scrollLeft ? document.body.scrollLeft : 
			document.documentElement.scrollLeft;
			
		// hide the menu first to avoid an "up-then-over" visual effect
		_divContext.style.display = 'none';
		_divContext.style.left = event.clientX + scrollLeft + 'px';
		_divContext.style.top = event.clientY + scrollTop + 'px';
		_divContext.style.display = aDisplay;
		
		_replaceContext = false;
		
		return false;
	}
}

function DisableContext()
{
	_noContext = true;
	CloseContext();
	$('aEnable').style.display = '';
	
	return false;
}

function EnableContext()
{
	_noContext = false;
	_mouseOverContext = false; // this gets left enabled when "disable menus" is chosen
	$('aEnable').style.display = 'none';
	
	return false;
}

// comes from prototype.js; this is simply easier on the eyes and fingers
function $(id)
{
	return document.getElementById(id);
}

