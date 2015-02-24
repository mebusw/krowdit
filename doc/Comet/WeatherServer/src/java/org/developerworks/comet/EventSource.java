package org.developerworks.comet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

public class EventSource extends Thread{
	
	public volatile ArrayList<HttpServletResponse> connections = new ArrayList(100);
	static EventSource enEventSource;
	
	public synchronized static EventSource getInstance(){
		if(enEventSource==null){
			enEventSource = new EventSource();
		}
		
		return enEventSource;
	}
	
	public void addConnection(HttpServletResponse response){
		connections.add(response);
	}
	
	public void run(){
		
		while(true){
			for (int i = 0; i < connections.size(); i++) {
				try {
					HttpServletResponse response = connections.get(i);
					PrintWriter out = response.getWriter();
					
					System.out.println(response+" buffer size====="+response.getBufferSize());
					//out.println("<script language=\"JavaScript\">write('"+new Random().nextInt()+"');</script>");
					out.print("<script type=\"text/javascript\">");  
					out.println("comet.cometResponseFromServer('"+new Random().nextInt()+"');");
					out.print("</script>");  
					out.flush();
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					connections.remove(i);
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

