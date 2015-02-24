package com.mvc.rest;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestController {
	@RequestMapping(value = { "/login/{user}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView myMethod(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("user") String user,
			ModelMap modelMap) throws Exception {
		modelMap.put("loginUser", user);
		return new ModelAndView("/login/hello", modelMap);
	}

	@RequestMapping(value = { "/welcome" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String registPost() {
		return "/welcome";
	}

	@RequestMapping(value = { "/krowd/{user}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String krowdGet(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("user") String user,
			ModelMap modelMap) throws Exception {
		PrintWriter out = response.getWriter();
		out.print("response of GET: " + user);

		return null;
	}

	@RequestMapping(value = { "/blog/{user}" }, method = { org.springframework.web.bind.annotation.RequestMethod.PUT })
	public String krowdPut(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("user") String user,
			ModelMap modelMap) throws Exception {
		PrintWriter out = response.getWriter();
		out.print("response of PUT: " + user);

		return null;
	}

	@RequestMapping(value = { "/krowd/{lid}/{kid}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String krowdPost(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("lid") int lid,
			@PathVariable("kid") int kid, ModelMap modelMap) throws Exception {
		PrintWriter out = response.getWriter();
		out.print("response of POST: " + lid + kid);

		return null;
	}

}