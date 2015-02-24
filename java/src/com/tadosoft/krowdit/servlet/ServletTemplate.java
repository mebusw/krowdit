package com.tadosoft.krowdit.servlet;

/**
 * All Krowdit Servlets are comform to this interface.
 * 
 * @author TadoSoft
 *
 */
public interface ServletTemplate {
	
	/**
	 * inject DAO by either applicatonContext.xml or servlet context
	 */
	void injectDAO();
	
	/**
	 * parse parameters from servlet request
	 */
	void parseReqParam();
	
	/**
	 * do the business logic, AOP, don't care DAO or parameters
	 */
	void processBusiness();
	
}
