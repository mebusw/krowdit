package org.developerworks.comet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EventSourceListener implements ServletContextListener {
	  public void contextInitialized(ServletContextEvent arg0)
	  {   
		  EventSource.getInstance().start();
	  } 
	  
	  public void contextDestroyed(ServletContextEvent arg0)
	  {
	     // TODO Auto-generated method stub
	  } 
}
