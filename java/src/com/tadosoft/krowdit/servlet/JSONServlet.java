package com.tadosoft.krowdit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tadosoft.krowdit.util.DateFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class JSONServlet extends HttpServlet implements ServletTemplate {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected JSONObject json;

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.request = request;
		this.response = response;
		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		request.setCharacterEncoding("utf8");
		PrintWriter out = this.response.getWriter();

		injectDAO();
		parseReqParam();
		processBusiness();

		out.print(json.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void injectDAO() {

	}

	public void parseReqParam() {

	}

	public void processBusiness() {

	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	protected String parseStr(String paraName) {
		String temp = request.getParameter(paraName);
		return null == temp ? "" : temp;
	}

	protected int parseInt(String paraName) {
		String temp = request.getParameter(paraName);
		return null == temp ? 0 : Integer.parseInt(temp);
	}

	protected long parseLong(String paraName) {
		String temp = request.getParameter(paraName);
		return null == temp ? 0L : Long.parseLong(temp);
	}

	protected float parseFloat(String paraName) {
		String temp = request.getParameter(paraName);
		return null == temp ? 0F : Float.parseFloat(temp);
	}
	
	protected Date parseDate(String paraName) throws ParseException {
		String temp = request.getParameter(paraName);

		SimpleDateFormat sdf = DateFormatter.getInstance();

		try {
			return sdf.parse(temp);
		} catch (ParseException e) {
			throw e;
		}
	}
}
