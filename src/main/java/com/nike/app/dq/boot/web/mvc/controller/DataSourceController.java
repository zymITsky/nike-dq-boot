
package com.nike.app.dq.boot.web.mvc.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nike.app.dq.boot.common.constant.WebAppConstant;
import com.nike.app.dq.boot.data.service.ConnectionService;

@Controller
@RequestMapping("/ds")
public class DataSourceController {

	@Autowired
	public ConnectionService connectionService = null;

	@RequestMapping(value="/list/form", method=RequestMethod.GET)
	@RequiresPermissions("suser:view")
	public String go2DsListForm(Model model) {
		model.addAttribute(WebAppConstant.DATASOURCE_STEP_ID, 1);
		return WebAppConstant.DATASOURCE_LIST_FORM_PAGE;
	}

	@RequestMapping(value="/connection/api/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> getAllConnectionsAsStrings() {
		return connectionService.getAllConnectionsAsStrings();
	}
}