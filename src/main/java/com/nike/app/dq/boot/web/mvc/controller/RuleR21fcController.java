
package com.nike.app.dq.boot.web.mvc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nike.app.dq.boot.common.constant.WebAppConstant;
import com.nike.app.dq.boot.common.model.DumyRcR21fcDTResult;
import com.nike.app.dq.boot.common.model.JsonReturnResult;
import com.nike.app.dq.boot.data.service.ConnectionService;
import com.nike.app.dq.boot.data.service.RuleR21fcService;
import com.nike.app.dq.boot.util.format.SimpleFormater;
import com.nike.app.dq.boot.util.log.SimpleLogger;
import com.nike.app.dq.boot.web.mvc.bean.form.R21fcForm;

@Controller
@RequestMapping("/rule/r21fc")
public class RuleR21fcController {

	@Autowired
	public RuleR21fcService ruleR21fcService = null;

	@Autowired
	public ConnectionService connectionService = null;

	@RequestMapping(value="/list/form", method=RequestMethod.GET)
	@RequiresPermissions("suser:view")
	public String go2RuleR21fcListForm(Model model) {
		SimpleLogger.log(SimpleLogger.DEBUG, this.getClass(), "start to request for go2RuleR21fcListForm (/rule/r21fc/list/form)");
		model.addAttribute(WebAppConstant.RULE_R21FC_STEP_ID, 1);
		ObjectMapper objMapper = new ObjectMapper();
		try {
			model.addAttribute(WebAppConstant.LIST_OF_DATA_SOURCE, objMapper.writeValueAsString(connectionService.getAllConnectionsAsStrings()));
			model.addAttribute(WebAppConstant.LIST_OF_BIZ_FUNC, objMapper.writeValueAsString(WebAppConstant.GBL_ALL_BUSINESS_FUNCTION));
			model.addAttribute(WebAppConstant.LIST_OF_CASE_OWNER, objMapper.writeValueAsString(ruleR21fcService.getAllCaseOwnersAsStrings()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		SimpleLogger.log(SimpleLogger.DEBUG, this.getClass(), "finish the request");
		return WebAppConstant.RULE_R21FC_LIST_FORM_PAGE;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list/form/api/ruler21fcs", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public <T> Map<String, List<T>> apiOfAllRuleR21fcs() {
		Map<String, List<T>> result = new HashMap<String, List<T>>();
		result.put("data", (List<T>)ruleR21fcService.getAllR21fcRuleCases());
		result.put("options", (List<T>)(new ArrayList<String>()));
		result.put("files", (List<T>)(new ArrayList<String>()));
		return result;
	}

	@RequestMapping(value="/list/form/api/ruler21fcs", method=RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public <T> Map<String, List<T>> apiOfAllRuleR21fcs(@RequestBody String requestBody) {
		try {
			ruleR21fcService.doDatatableActForRcR21fc(requestBody);
		} catch (Exception e) {
			return DumyRcR21fcDTResult.dumyEmptyFailureData(e.getMessage());
		}
		return DumyRcR21fcDTResult.dumyEmptySuccessData();
	}

	@RequestMapping(value="/list/form/api/run/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfRunRuleCase(@PathVariable int id) {
		ruleR21fcService.runRcR21fcById(id);
		return new JsonReturnResult();
	}

	@RequestMapping(value="/list/form/api/run_batch/{ids}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfRunBatch(@PathVariable String ids) {
		ruleR21fcService.runRcR21fcBatch(ids);
		return new JsonReturnResult();
	}

	@RequestMapping(value="/list/form/api/copy_case/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfCopyRuleCase(@PathVariable int id) {
		ruleR21fcService.copyNewR21fc(id);
		return new JsonReturnResult();
	}

	@RequestMapping(value="/new/form", method=RequestMethod.GET)
	@RequiresPermissions("suser:view")
	public String go2RuleR21fcNewForm(Model model) {
		model.addAttribute(WebAppConstant.RULE_R21FC_STEP_ID, 2);
		return WebAppConstant.RULE_R21FC_NEW_FORM_PAGE;
	}

	@RequestMapping(value="/new/form/api/is_name_duplicated/{name}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public JsonReturnResult apiOfIsNameDuplicated(@PathVariable String name) {
		JsonReturnResult result = new JsonReturnResult();
		if (ruleR21fcService.isRuleCaseNameDuplicate(name)) {
			result.setStatus(JsonReturnResult.FAILURE);
		}
		return result;
	}

	@RequestMapping(value="/new/form/api/new_case", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:write")
	public JsonReturnResult apiOfNewCaseR21fc(@RequestBody R21fcForm form) {
		JsonReturnResult result = new JsonReturnResult();
		try {
			ruleR21fcService.addNewR21fc(form);
		} catch (Exception e) {
			result.setStatus(JsonReturnResult.FAILURE);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list/form/api/ruler21fcs_filter", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresPermissions("suser:read")
	public <T> Map<String, List<T>> apiOfAllRuleR21fcsFilter(@RequestParam(required=true, name="tgtConnName") String tgtConnName, @RequestParam(required=true, name="bizFunc") String bizFunc, @RequestParam(required=true, name="caseOwner") String caseOwner) {
		Map<String, List<T>> result = new HashMap<String, List<T>>();
		result.put("data", (List<T>)ruleR21fcService.filterForR21fcRuleCases(tgtConnName, bizFunc, caseOwner));
		result.put("options", (List<T>)(new ArrayList<String>()));
		result.put("files", (List<T>)(new ArrayList<String>()));
		return result;
	}

	@RequestMapping(value="/download/excel", method=RequestMethod.GET)
	@RequiresPermissions("suser:read")
    public void downloadAsExcel(HttpSession session, HttpServletResponse response) throws IOException {
		// set MIME type for forcing download
		response.setContentType("application/force-download");
		// set content length (-1) any
		response.setContentLength(-1);
		// set content transfer encode as 'binary'
		response.setHeader("Content-Transfer-Encoding", "binary");
		// attachment download's name
		response.setHeader("Content-Disposition", "attachment; filename=\"Rule21_" + SimpleFormater.simpleDateTimeFormate(new Date()) + ".xls\"");
		OutputStream os = response.getOutputStream();
		ruleR21fcService.downloadRule21AsExcel(os);
	}

	@RequestMapping(value="/caseowner/api/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> getAllCaseOwnersAsStrings() {
		return ruleR21fcService.getAllCaseOwnersAsStrings();
	}
}