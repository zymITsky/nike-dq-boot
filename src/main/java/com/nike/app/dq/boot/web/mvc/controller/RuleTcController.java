
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
import com.nike.app.dq.boot.common.model.DumyRcTcDTResult;
import com.nike.app.dq.boot.common.model.JsonCjLabelYModel;
import com.nike.app.dq.boot.common.model.JsonReturnResult;
import com.nike.app.dq.boot.data.entity.RuleTcCase;
import com.nike.app.dq.boot.data.entity.UserProfile;
import com.nike.app.dq.boot.data.service.RuleTcService;
import com.nike.app.dq.boot.web.mvc.bean.form.RtccForm;

@Controller
@RequestMapping("/rule/tc")
public class RuleTcController {

	@Autowired
	public RuleTcService ruleTcService = null;

	@RequestMapping(value="/list/form", method=RequestMethod.GET)
	@RequiresPermissions("suser:view")
	public String go2RuleTcListForm(Model model) {
		model.addAttribute(WebAppConstant.RULE_TC_STEP_ID, 1);
		return WebAppConstant.RULE_TC_LIST_FORM_PAGE;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list/form/api/ruletcs", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public <T> Map<String, List<T>> apiOfAllRuleTcs() {
		Map<String, List<T>> result = new HashMap<String, List<T>>();
		result.put("data", (List<T>)ruleTcService.getAllTcRuleCases());
		result.put("options", (List<T>)(new ArrayList<String>()));
		result.put("files", (List<T>)(new ArrayList<String>()));
		return result;
	}

	@RequestMapping(value="/list/form/api/ruletcs", method=RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public <T> Map<String, List<T>> apiOfAllRuleTcs(@RequestBody String requestBody) {
		ruleTcService.doDatatableActForRcTc(requestBody);
		return DumyRcTcDTResult.dumyEmptyData();
	}

	@RequestMapping(value="/list/form/api/run/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfRunRuleCase(@PathVariable int id) {
		ruleTcService.runRcTcById(id);
		return new JsonReturnResult();
	}

	@RequestMapping(value="/list/form/api/run_batch/{ids}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfRunBatch(@PathVariable String ids) {
		ruleTcService.runRcTcBatch(ids);
		return new JsonReturnResult();
	}

	@RequestMapping(value="/list/form/api/last_tc_rc_rh_chart", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public Map<String, List<JsonCjLabelYModel>> apiOfFetchLastTcRcRhChart() {
		return ruleTcService.loadLastTcRcRunHistoryForChart();
	}

	@RequestMapping(value="/list/form/api/tc_conn_used_chart", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public List<JsonCjLabelYModel> apiOfFetchTcConnUsedForChart() {
		return ruleTcService.loadTcConnUsedForChart();
	}

	@RequestMapping(value="/list/form/api/tc_created_by_chart", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public List<JsonCjLabelYModel> apiOfFetchTcCreatedByForChart() {
		return ruleTcService.loadTcCreatedByForChart();
	}

	@RequestMapping(value="/list/form/api/tc_case_severity_chart", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public List<JsonCjLabelYModel> apiOfFetchTcCaseSeverityForChart() {
		return ruleTcService.loadTcCaseSeverityForChart();
	}

	@RequestMapping(value="/new/form", method=RequestMethod.GET)
	@RequiresPermissions("suser:view")
	public String go2RuleTcNewForm(Model model) {
		model.addAttribute(WebAppConstant.RULE_TC_STEP_ID, 2);
		return WebAppConstant.RULE_TC_NEW_FORM_PAGE;
	}

	@RequestMapping(value="/new/form/api/is_name_duplicated/{name}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public JsonReturnResult apiOfIsNameDuplicated(@PathVariable String name) {
		JsonReturnResult result = new JsonReturnResult();
		if (ruleTcService.isRuleCaseNameDuplicate(name)) {
			result.setStatus(JsonReturnResult.FAILURE);
		}
		return result;
	}

	@RequestMapping(value="/new/form/api/is_table_exist/{table}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public JsonReturnResult apiOfIsTableExist(@PathVariable String table) {
		JsonReturnResult result = new JsonReturnResult();
		if (!ruleTcService.isTableExist(table)) {
			result.setStatus(JsonReturnResult.FAILURE);
		}
		return result;
	}

	@RequestMapping(value="/new/form/api/new_case", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfNewCaseRtcc(@RequestBody RtccForm rtccForm, HttpSession session) {
		JsonReturnResult result = new JsonReturnResult();
		UserProfile user = (UserProfile)session.getAttribute(WebAppConstant.USER_CONFIG);
		rtccForm.setCreatedBy(user.getUserName().toUpperCase());
		ruleTcService.addNewRtcc(rtccForm);
		return result;
	}

	@RequestMapping(value="/view/detail", method=RequestMethod.GET)
	@RequiresPermissions("suser:view")
	public String go2RuleTcViewDetails(Model model) {
		model.addAttribute(WebAppConstant.RULE_TC_STEP_ID, 3);
		RuleTcCase rtcc = ruleTcService.getAllTcRuleCases().get(0);
		model.addAttribute(WebAppConstant.INIT_TC_RULE_CASE_NAME, rtcc.getRuleCaseName());
		return WebAppConstant.RULE_TC_VIEW_DETAIL_PAGE;
	}

	@RequestMapping(value="/view/detail/api/avg_tc_rc_rh_chart", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public Map<String, List<JsonCjLabelYModel>> apiOfFetchAvgTcRcRhChart() {
		return ruleTcService.loadLastTcRcRunHistoryAvgForChart();
	}

	@RequestMapping(value="/view/detail/api/tc_his_fail_cnt_chart", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public List<JsonCjLabelYModel> apiOfFetchTcHistoryFailureCountForChart() {
		return ruleTcService.loadTcHistoryFailureCountForChart();
	}

	@RequestMapping(value="/view/detail/api/tc_rc_rh_all_chart/{name}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public Map<String, List<JsonCjLabelYModel>> apiOfFetchTcRcsRunHistoryAllForChart(@PathVariable String name) {
		return ruleTcService.loadTcRcsRunHistoryAllForChart(name);
	}
}