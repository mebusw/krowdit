package org.developerworks.comet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.CometEvent;
import org.apache.catalina.CometProcessor;

public class CometSample extends HttpServlet implements CometProcessor {
	  public void event(final CometEvent event) throws IOException, ServletException {
	        HttpServletRequest request = event.getHttpServletRequest();
	        HttpServletResponse response = event.getHttpServletResponse();
	       // response.setContentType("text/html;");
        	response.setStatus(HttpServletResponse.SC_OK);
	        if (event.getEventType() == CometEvent.EventType.BEGIN) {
	        	PrintWriter out = response.getWriter();
	        	out.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");  
	        	out.println("<html><head><script type=\"text/javascript\">var comet = window.parent.comet;</script></head><body>");  
	        	out.print("<script type=\"text/javascript\">");  
	        	out.println("comet.cometResponseFromServer('The initialized content.');");
	        	out.print("</script>");  
	        	EventSource.getInstance().addConnection(response);
	        	System.out.println("Event begin method invoked");
	        }else if (event.getEventType() == CometEvent.EventType.ERROR) {
	            log("Error for session: " + request.getSession(true).getId());
	            event.close();
	        } else if (event.getEventType() == CometEvent.EventType.END) {
	            log("End for session: " + request.getSession(true).getId());
	            event.close();
	        } else if (event.getEventType() == CometEvent.EventType.READ) {
	            throw new UnsupportedOperationException("This servlet does not accept data");
	        }
	  }
}
