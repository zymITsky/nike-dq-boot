
package com.nike.app.dq.boot.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nike.app.dq.boot.common.constant.WebAppConstant;
import com.nike.app.dq.boot.util.log.SimpleLogger;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@RequestMapping(value="/user/home", method=RequestMethod.GET)
	public String redirectUserDashboardHome() {
		SimpleLogger.log(SimpleLogger.INFO, getClass(), "start to load home page (dashboard) ...");
		return WebAppConstant.USER_DASHBOARD_HOME_PAGE;
	}
}