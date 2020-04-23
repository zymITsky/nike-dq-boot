
package com.nike.app.dq.boot.web.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nike.app.dq.boot.common.constant.WebAppConstant;
import com.nike.app.dq.boot.common.model.DumyRcQsDTResult;
import com.nike.app.dq.boot.common.model.JsonReturnResult;
import com.nike.app.dq.boot.data.entity.UserProfile;
import com.nike.app.dq.boot.data.service.RuleQsService;
import com.nike.app.dq.boot.web.mvc.bean.form.RqscForm;

@Controller
@RequestMapping("/rule/qs")
public class RuleQsController {

	@Autowired
	public RuleQsService ruleQsService = null;

	@RequestMapping(value="/list/form", method=RequestMethod.GET)
	@RequiresPermissions("suser:view")
	public String go2RuleQsListForm(Model model) {
		model.addAttribute(WebAppConstant.RULE_QS_STEP_ID, 1);
		return WebAppConstant.RULE_QS_LIST_FORM_PAGE;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list/form/api/ruleqss", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public <T> Map<String, List<T>> apiOfAllRuleQss() {
		Map<String, List<T>> result = new HashMap<String, List<T>>();
		result.put("data", (List<T>)ruleQsService.getAllTcRuleCases());
		result.put("options", (List<T>)(new ArrayList<String>()));
		result.put("files", (List<T>)(new ArrayList<String>()));
		return result;
	}

	@RequestMapping(value="/list/form/api/ruleqss", method=RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public <T> Map<String, List<T>> apiOfAllRuleQss(@RequestBody String requestBody) {
		ruleQsService.doDatatableActForRcQs(requestBody);
		return DumyRcQsDTResult.dumyEmptyData();
	}

	@RequestMapping(value="/list/form/api/run/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfRunRuleCase(@PathVariable int id) {
		ruleQsService.runRcQsById(id);
		return new JsonReturnResult();
	}

	@RequestMapping(value="/list/form/api/run_batch/{ids}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfRunBatch(@PathVariable String ids) {
		ruleQsService.runRcQsBatch(ids);
		return new JsonReturnResult();
	}

	@RequestMapping(value="/new/form", method=RequestMethod.GET)
	@RequiresPermissions("suser:view")
	public String go2RuleQsNewForm(Model model) {
		model.addAttribute(WebAppConstant.RULE_QS_STEP_ID, 2);
		return WebAppConstant.RULE_QS_NEW_FORM_PAGE;
	}

	@RequestMapping(value="/new/form/api/is_name_duplicated/{name}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public JsonReturnResult apiOfIsNameDuplicated(@PathVariable String name) {
		JsonReturnResult result = new JsonReturnResult();
		if (ruleQsService.isRuleCaseNameDuplicate(name)) {
			result.setStatus(JsonReturnResult.FAILURE);
		}
		return result;
	}

	@RequestMapping(value="/new/form/api/new_case", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfNewCaseRqsc(@RequestBody RqscForm rqscForm, HttpSession session) {
		JsonReturnResult result = new JsonReturnResult();
		UserProfile user = (UserProfile)session.getAttribute(WebAppConstant.USER_CONFIG);
		rqscForm.setCreatedBy(user.getUserName().toUpperCase());
		ruleQsService.addNewRqsc(rqscForm);
		return result;
	}
}