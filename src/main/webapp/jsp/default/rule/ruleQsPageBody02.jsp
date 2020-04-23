<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">Rule defined about Verifying Specified Query String <small>for creating new case</small></h3>
		<p>This module is designed and used for monitoring the various kinds of data sources on rules status and scheduling, and it also can provide the function to manipulate, configure and update the rule case.</p>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"><i class="fa fa-table fa-fw"></i>&nbsp;Create New Case for Rule#QueryString</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<script id="custMsgTmpl" type="text/html"><em class="errMsg" data-bind='validationMessage:field'></em></script>
						<div id="frmRqsc" data-bind='validationOptions:{messageTemplate:"custMsgTmpl"}'>
							<form>
								<div class="row">
									<div class="col-lg-3 col-lg-offset-3">
										<div class="panel panel-default">
											<div class="panel-heading"><small>Rule Case Name</small></div>
											<div class="panel-body">
												<div class="form-group">
													<div class="fake-input">
														<input id="txtRuleCaseName" name="txtRuleCaseName" type="text" class="form-control" placeholder="e.g., DQ_RULE_TC_CASE_RUN_HISTORY_TAB_START_DATE" data-bind="value:txtRuleCaseName,hasfocus:txtRuleCaseName.focused">
														<img id="ajaxloader1" style="display:none;" src="<c:url value="/resources/image/ajax_loader.gif"/>" width="25px" height="25px">
													</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Rule Case Description</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseDescription" name="txtRuleCaseDescription" type="text" class="form-control" placeholder="e.g., bala bala ..." data-bind="value:txtRuleCaseDescription">
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Rule Case Sql</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseSql" name="txtRuleCaseSql" type="text" class="form-control" placeholder="e.g., select max(1) from t where . have ." data-bind="value:txtRuleCaseSql">
												</div>
											</div>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="panel panel-default">
											<div class="panel-heading"><small>Rule Case Connection</small></div>
											<div class="panel-body">
												<div class="form-group">
													<select id="selRuleCaseConnection" name="selRuleCaseConnection" class="form-control" data-bind='value:selRuleCaseConnection,options:connectionOptions'></select>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Rule Case Severity</small></div>
											<div class="panel-body">
												<div class="form-group">
													<select id="selRuleCaseSeverity" name="selRuleCaseSeverity" class="form-control" data-bind="value:selRuleCaseSeverity">
														<option value="HIGH">HIGH</option>
														<option value="MEDIUM">MEDIUM</option>
														<option value="LOW">LOW</option>
													</select>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<p><div style="text-align:center"><button id="btnSubmitNew" data-bind="click:submit" type="submit" style="width:180px;" class="btn btn-primary btn-lg">Submit</button></div></p>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>