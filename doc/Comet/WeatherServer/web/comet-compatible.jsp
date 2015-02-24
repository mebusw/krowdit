<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comet Sample</title>
    </head>
<body>
	<h1>This is the sample for Comet</h1>
	
	<div id="content"></div>
</body>
 <script type="text/javascript" src="prototype.js"></script>  
<script type="text/javascript">

var comet = {
  connection   : false,
  iframediv    : false,
  initialize: function() {
  var userAgent = navigator.userAgent.toLowerCase();
      //alert(userAgent)
  if (/msie/.test(userAgent) && !/opera/.test(userAgent)) {
      // For IE browsers
	  alert('ie');
      comet.connection = new ActiveXObject("htmlfile");
      comet.connection.open();
      comet.connection.write("<html>");
      comet.connection.write("<script>document.domain = '"+document.domain+"';<\/script> ");
      comet.connection.write("</html>");
      comet.connection.close();
      comet.iframediv = comet.connection.createElement("div");
      comet.connection.appendChild(comet.iframediv);
      comet.connection.parentWindow.comet = comet;
      comet.iframediv.innerHTML = "<iframe id='comet_iframe' src='http://10.224.173.117:9000/cs/cometSample'></iframe>";
   }

   if( /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent) ) {
      // For Firefox browser
	  alert('firefox');
      comet.connection = document.createElement('iframe');
      comet.connection.setAttribute('id','comet_iframe');
      with (comet.connection.style) {
        left       = top   = "-100px";
        height     = width = "1px";
        visibility = "hidden";
        display    = 'none';
      }

      comet.iframediv = document.createElement('iframe');
      comet.iframediv.setAttribute('src', 'http://10.224.173.117:9000/cs/cometSample');
      comet.connection.appendChild(comet.iframediv);
      document.body.appendChild(comet.connection);
    }

    if (/webkit/.test(userAgent) || /opera/.test(userAgent)) {
      // for other browsers
      comet.connection = document.createElement('iframe');
      comet.connection.setAttribute('id',     'comet_iframe');
      comet.connection.setAttribute('src',    './Demo');
      with (comet.connection.style) {
        position   = "absolute";
        left       = top   = "-100px";
        height     = width = "1px";
        visibility = "hidden";
      }
      document.body.appendChild(comet.connection);
    }
  },

  // this function will be called from backend.jsp
  cometResponseFromServer: function (time) {
	  //alert(time);
    $('content').innerHTML = time;
  },
 
  onUnload: function() {
    if (comet.connection) {
      comet.connection = false; // release the iframe to prevent problems with IE when reloading the page
    }
  }
}

Event.observe(window,'load',comet.initialize);

Event.observe(window, 'unload', comet.onUnload);

</script>
</html>
