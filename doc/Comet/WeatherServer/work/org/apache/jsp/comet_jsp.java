package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class comet_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("   \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Comet Sample</title>\n");
      out.write("    </head>\n");
      out.write("<body>\n");
      out.write("\t<h1>This is the sample for Comet</h1>\n");
      out.write("\t<input type=\"button\" onclick=\"loadXMLDoc()\" value=\"Go!\"></input>\n");
      out.write("\t<div id=\"content\"></div>\n");
      out.write("</body>\n");
      out.write("<SCRIPT TYPE=\"text/javascript\">\n");
      out.write("\t// global flag\n");
      out.write("\tvar isIE = false;\n");
      out.write("\n");
      out.write("\t// global request and XML document objects\n");
      out.write("\tvar req;\n");
      out.write("\t\n");
      out.write("\t// retrieve XML document (reusable generic function);\n");
      out.write("\t// parameter is URL string (relative or complete) to\n");
      out.write("\t// an .xml file whose Content-Type is a valid XML\n");
      out.write("\t// type, such as text/xml; XML source must be from\n");
      out.write("\t// same domain as HTML file\n");
      out.write("\tfunction loadXMLDoc() {\n");
      out.write("\t\t// The request URL\n");
      out.write("\t\tvar url = \"http://10.224.173.117:9000/cs/cometSample\";\n");
      out.write("\n");
      out.write("\t\t// branch for native XMLHttpRequest object\n");
      out.write("\t\tif (window.XMLHttpRequest) {\n");
      out.write("\t\t\treq = new XMLHttpRequest();\n");
      out.write("\t\t\treq.onreadystatechange = processReqChange;\n");
      out.write("\t\t\treq.open(\"GET\", url, true);\n");
      out.write("\t\t\treq.send(null);\n");
      out.write("\t\t// branch for IE/Windows ActiveX version\n");
      out.write("\t\t} else if (window.ActiveXObject) {\n");
      out.write("\t\t\tisIE = true;\n");
      out.write("\t\t\treq = new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("\t\t\tif (req) {\n");
      out.write("\t\t\t\treq.onreadystatechange = processReqChange;\n");
      out.write("\t\t\t\treq.open(\"GET\", url, true);\n");
      out.write("\t\t\t\treq.send();\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\t// handle onreadystatechange event of req object\n");
      out.write("\tfunction processReqChange() {\n");
      out.write("\t\t// only if req shows \"loaded\"\n");
      out.write("\t\tif (req.readyState == 3) {\n");
      out.write("\t\t\t// only if \"OK\"\n");
      out.write("\t\t\tif (req.status == 200) {\n");
      out.write("\t\t\t\t//alert('received');\n");
      out.write("\t\t\t\tdocument.getElementById(\"content\").innerHTML=req.responseText;\n");
      out.write("\t\t\t } else {\n");
      out.write("\t\t\t\talert(\"There was a problem retrieving the XML data:\\n\" +\n");
      out.write("\t\t\t\t\treq.statusText);\n");
      out.write("\t\t\t }\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("\t//Display the data from the server\n");
      out.write("\tfunction write(i){\n");
      out.write("\t\t\tdocument.writeln(i);\n");
      out.write("\t}\n");
      out.write("\n");
      out.write("</SCRIPT>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
