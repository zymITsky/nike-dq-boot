
package com.nike.app.dq.boot.web.mvc.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nike.app.dq.boot.common.constant.WebAppConstant;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@RequestMapping(value="/list/form", method=RequestMethod.GET)
	@RequiresPermissions("suser:view")
	public String go2ScheduleListForm(Model model) {
		model.addAttribute(WebAppConstant.SCHEDULE_STEP_ID, 1);
		return WebAppConstant.SCHEDULE_LIST_FORM_PAGE;
	}
}