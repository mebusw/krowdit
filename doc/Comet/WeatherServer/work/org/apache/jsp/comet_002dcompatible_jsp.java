package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class comet_002dcompatible_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t\n");
      out.write("\t<div id=\"content\"></div>\n");
      out.write("</body>\n");
      out.write(" <script type=\"text/javascript\" src=\"prototype.js\"></script>  \n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("var comet = {\n");
      out.write("  connection   : false,\n");
      out.write("  iframediv    : false,\n");
      out.write("  initialize: function() {\n");
      out.write("  var userAgent = navigator.userAgent.toLowerCase();\n");
      out.write("      //alert(userAgent)\n");
      out.write("  if (/msie/.test(userAgent) && !/opera/.test(userAgent)) {\n");
      out.write("      // For IE browsers\n");
      out.write("\t  alert('ie');\n");
      out.write("      comet.connection = new ActiveXObject(\"htmlfile\");\n");
      out.write("      comet.connection.open();\n");
      out.write("      comet.connection.write(\"<html>\");\n");
      out.write("      comet.connection.write(\"<script>document.domain = '\"+document.domain+\"';<\\/script> \");\n");
      out.write("      comet.connection.write(\"</html>\");\n");
      out.write("      comet.connection.close();\n");
      out.write("      comet.iframediv = comet.connection.createElement(\"div\");\n");
      out.write("      comet.connection.appendChild(comet.iframediv);\n");
      out.write("      comet.connection.parentWindow.comet = comet;\n");
      out.write("      comet.iframediv.innerHTML = \"<iframe id='comet_iframe' src='http://10.224.173.117:9000/cs/cometSample'></iframe>\";\n");
      out.write("   }\n");
      out.write("\n");
      out.write("   if( /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent) ) {\n");
      out.write("      // For Firefox browser\n");
      out.write("\t  alert('firefox');\n");
      out.write("      comet.connection = document.createElement('iframe');\n");
      out.write("      comet.connection.setAttribute('id','comet_iframe');\n");
      out.write("      with (comet.connection.style) {\n");
      out.write("        left       = top   = \"-100px\";\n");
      out.write("        height     = width = \"1px\";\n");
      out.write("        visibility = \"hidden\";\n");
      out.write("        display    = 'none';\n");
      out.write("      }\n");
      out.write("\n");
      out.write("      comet.iframediv = document.createElement('iframe');\n");
      out.write("      comet.iframediv.setAttribute('src', 'http://10.224.173.117:9000/cs/cometSample');\n");
      out.write("      comet.connection.appendChild(comet.iframediv);\n");
      out.write("      document.body.appendChild(comet.connection);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    if (/webkit/.test(userAgent) || /opera/.test(userAgent)) {\n");
      out.write("      // for other browsers\n");
      out.write("      comet.connection = document.createElement('iframe');\n");
      out.write("      comet.connection.setAttribute('id',     'comet_iframe');\n");
      out.write("      comet.connection.setAttribute('src',    './Demo');\n");
      out.write("      with (comet.connection.style) {\n");
      out.write("        position   = \"absolute\";\n");
      out.write("        left       = top   = \"-100px\";\n");
      out.write("        height     = width = \"1px\";\n");
      out.write("        visibility = \"hidden\";\n");
      out.write("      }\n");
      out.write("      document.body.appendChild(comet.connection);\n");
      out.write("    }\n");
      out.write("  },\n");
      out.write("\n");
      out.write("  // this function will be called from backend.jsp\n");
      out.write("  cometResponseFromServer: function (time) {\n");
      out.write("\t  //alert(time);\n");
      out.write("    $('content').innerHTML = time;\n");
      out.write("  },\n");
      out.write(" \n");
      out.write("  onUnload: function() {\n");
      out.write("    if (comet.connection) {\n");
      out.write("      comet.connection = false; // release the iframe to prevent problems with IE when reloading the page\n");
      out.write("    }\n");
      out.write("  }\n");
      out.write("}\n");
      out.write("\n");
      out.write("Event.observe(window,'load',comet.initialize);\n");
      out.write("\n");
      out.write("Event.observe(window, 'unload', comet.onUnload);\n");
      out.write("\n");
      out.write("</script>\n");
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
