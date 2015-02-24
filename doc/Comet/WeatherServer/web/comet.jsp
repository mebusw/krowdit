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
	<input type="button" onclick="loadXMLDoc()" value="Go!"></input>
	<div id="content"></div>
</body>
<SCRIPT TYPE="text/javascript">
	// global flag
	var isIE = false;

	// global request and XML document objects
	var req;
	
	// retrieve XML document (reusable generic function);
	// parameter is URL string (relative or complete) to
	// an .xml file whose Content-Type is a valid XML
	// type, such as text/xml; XML source must be from
	// same domain as HTML file
	function loadXMLDoc() {
		// The request URL
		var url = "http://10.224.173.117:9000/cs/cometSample";

		// branch for native XMLHttpRequest object
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
			req.onreadystatechange = processReqChange;
			req.open("GET", url, true);
			req.send(null);
		// branch for IE/Windows ActiveX version
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
			if (req) {
				req.onreadystatechange = processReqChange;
				req.open("GET", url, true);
				req.send();
			}
		}
	}
	
	// handle onreadystatechange event of req object
	function processReqChange() {
		// only if req shows "loaded"
		if (req.readyState == 3) {
			// only if "OK"
			if (req.status == 200) {
				//alert('received');
				document.getElementById("content").innerHTML=req.responseText;
			 } else {
				alert("There was a problem retrieving the XML data:\n" +
					req.statusText);
			 }
		}
	}

	//Display the data from the server
	function write(i){
			document.writeln(i);
	}

</SCRIPT>
</html>
