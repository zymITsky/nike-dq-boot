<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">Rule defined about Verifying Size of Table Rows <small>for creating new case</small></h3>
		<p>This module is designed and used for monitoring the various kinds of data sources on rules status and scheduling, and it also can provide the function to manipulate, configure and update the rule case.</p>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"><i class="fa fa-table fa-fw"></i>&nbsp;Create New Case for Rule#TableCount</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<script id="custMsgTmpl" type="text/html"><em class="errMsg" data-bind='validationMessage:field'></em></script>
						<div id="frmRtcc" data-bind='validationOptions:{messageTemplate:"custMsgTmpl"}'>
							<form>
								<div class="row">
									<div class="col-lg-3 col-lg-offset-3">
										<div class="panel panel-default">
											<div class="panel-heading"><small>Rule Case Name</small></div>
											<div class="panel-body">
												<div class="form-group">
													<div class="fake-input">
														<input id="txtRuleCaseName" name="txtRuleCaseName" type="text" class="form-control" placeholder="e.g., USER_PROFILE_SIZE" data-bind="value:txtRuleCaseName,hasfocus:txtRuleCaseName.focused">
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
											<div class="panel-heading"><small>Rule Case Target DB</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseTargetDb" name="txtRuleCaseTargetDb" type="text" class="form-control" placeholder="e.g., NIKEDQ" data-bind="value:txtRuleCaseTargetDb">
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Rule Case Target Table</small></div>
											<div class="panel-body">
												<div class="form-group">
													<div class="fake-input">
														<input id="txtRuleCaseTargetTable" name="txtRuleCaseTargetTable" type="text" class="form-control" placeholder="e.g., USER_PROFILE_TAB" data-bind="value:txtRuleCaseTargetTable,hasfocus:txtRuleCaseTargetTable.focused">
														<img id="ajaxloader2" style="display:none;" src="<c:url value="/resources/image/ajax_loader.gif"/>" width="25px" height="25px">
													</div>
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
											<div class="panel-heading"><small>Rule Case Original Table Size</small></div>
											<div class="panel-body">
												<div class="form-group input-group">
													<span class="input-group-addon"><i class="fa fa-terminal"></i></span><input id="txtRuleCaseOrigTblSize" name="txtRuleCaseOrigTblSize" type="text" class="form-control" placeholder="e.g., 100" data-bind="numeric,value:txtRuleCaseOrigTblSize">
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Rule Case Deviant Range</small></div>
											<div class="panel-body">
												<div class="form-group input-group">
													<span class="input-group-addon">>=</span><input id="txtRuleCaseRowGapGt" name="txtRuleCaseRowGapGt" type="text" class="form-control" placeholder="10.0" data-bind="numeric,value:txtRuleCaseRowGapGt"><span class="input-group-addon">%</span><span class="input-group-addon"><=</span><input id="txtRuleCaseRowGapLt" name="txtRuleCaseRowGapLt" type="text" class="form-control" placeholder="10.0" data-bind="numeric,value:txtRuleCaseRowGapLt"><span class="input-group-addon">%</span>
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