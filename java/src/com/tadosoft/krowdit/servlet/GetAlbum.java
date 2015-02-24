package com.tadosoft.krowdit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.po.TableAlbumDAO;
import com.tadosoft.krowdit.type.EC;

public class GetAlbum extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(GetAlbum.class);

	/**
	 * Constructor of the object.
	 */
	public GetAlbum() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf8");
		request.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();

		JSONObject json = new JSONObject();

		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		TableAlbumDAO dao = (TableAlbumDAO) ctx
				.getBean("TableAlbumDAO");

		int uid = Integer.parseInt(request.getParameter("uid"));
		log.info(String.format("uid=%d", uid));

		try {
			dao.findById(0L);
			json.put("error", EC.SUCCESS);

		} catch (IndexOutOfBoundsException e) {
			json.put("error", EC.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);
		}

		out.print(json.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
