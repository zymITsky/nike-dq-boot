
package com.nike.app.dq.boot.web.mvc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nike.app.dq.boot.common.constant.WebAppConstant;
import com.nike.app.dq.boot.common.model.JsonReturnResult;
import com.nike.app.dq.boot.data.entity.RuleR21fcCase;
import com.nike.app.dq.boot.data.entity.RuleR33mcCase;
import com.nike.app.dq.boot.data.service.ConnectionService;
import com.nike.app.dq.boot.data.service.RuleR21fcService;
import com.nike.app.dq.boot.data.service.RuleR33mcService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/rule/api")
public class RuleApiController {

	@Autowired
	public RuleR21fcService ruleR21fcService = null;

	@Autowired
	public RuleR33mcService ruleR33mcService = null;

	@Autowired
	public ConnectionService connectionService = null;

	@CrossOrigin
	@RequestMapping(value="/r21c/ids/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="get_all_r21_ids", notes="Get all the cases' ids of Rule 2.1")
	public List<Integer> restApiOfAllRuleR21fcs() {
		List<RuleR21fcCase> list = ruleR21fcService.getAllR21fcRuleCases();
		return list.stream().map((rc) -> rc.getRuleCaseId()).collect(Collectors.toList());
	}

	@CrossOrigin
	@RequestMapping(value="/r21c/run/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="run_r21_case_by_id", notes="Run the case of Rule 2.1 by Id")
	public JsonReturnResult restApiOfRunR21Case(@ApiParam(value="Case Id of Rule 2.1", required=true) @PathVariable int id) {
		ruleR21fcService.runRcR21fcById(id);
		return new JsonReturnResult();
	}

	@CrossOrigin
	@RequestMapping(value="/r33c/ids/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="get_all_r33_ids", notes="Get all the cases' ids of Rule 3.3")
	public List<Integer> restApiOfAllRuleR33mcs() {
		List<RuleR33mcCase> list = ruleR33mcService.getAllRuleR33mcCases();
		return list.stream().map((rc) -> rc.getRuleCaseId()).collect(Collectors.toList());
	}

	@CrossOrigin
	@RequestMapping(value="/r33c/run/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="run_r33_case_by_id", notes="Run the case of Rule 3.3 by Id")
	public JsonReturnResult restApiOfRunR33Case(@PathVariable int id) {
		ruleR33mcService.runRcR33mcById(id);
		return new JsonReturnResult();
	}

	@CrossOrigin
	@RequestMapping(value="/bf/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="business_all", notes="List all the business functions")
	public List<String> restApiOfAllBf() {
		return WebAppConstant.GBL_ALL_BUSINESS_FUNCTION;
	}

	@CrossOrigin
	@RequestMapping(value="/connection/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="connection_all", notes="List all the connections")
	public List<String> restApiOfAllConnections() {
		return connectionService.getAllConnectionsAsStrings();
	}
}