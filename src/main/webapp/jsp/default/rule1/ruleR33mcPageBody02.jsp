<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header"><small>Rule defined about Verifying Measure comparison between source and target check for creating new case</small></h3>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"><i class="fa fa-table fa-fw"></i>&nbsp;Create New Case</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<script id="custMsgTmpl" type="text/html"><em class="errMsg" data-bind='validationMessage:field'></em></script>
						<div id="frmR33mc" data-bind='validationOptions:{messageTemplate:"custMsgTmpl"}'>
							<form>
								<div class="row">
									<div class="col-lg-2 col-lg-offset-3">
										<div class="panel panel-default">
											<div class="panel-heading"><small>Case Name</small></div>
											<div class="panel-body">
												<div class="form-group">
													<div class="fake-input">
														<input id="txtRuleCaseName" name="txtRuleCaseName" type="text" class="form-control" placeholder="e.g., MAX_TRAN_DT-SALES_DAY_LEVEL_FACT" data-bind="value:txtRuleCaseName,hasfocus:txtRuleCaseName.focused">
														<img id="ajaxloader1" style="display:none;" src="<c:url value="/resources/image/ajax_loader.gif"/>" width="25px" height="25px">
													</div>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Case Owner</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseOwner" name="txtRuleCaseOwner" type="text" class="form-control" placeholder="e.g., bala bala ..." data-bind="value:txtRuleCaseOwner">
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Business Function</small></div>
											<div class="panel-body">
												<div class="form-group">
													<select id="txtRuleCaseBusinessFunction" name="txtRuleCaseBusinessFunction" class="form-control" data-bind="value:txtRuleCaseBusinessFunction,options:bfOptions">
													</select>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Case Description</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseDescription" name="txtRuleCaseDescription" type="text" class="form-control" placeholder="e.g., bala bala ..." data-bind="value:txtRuleCaseDescription">
												</div>
											</div>
										</div>
									</div>
									<div class="col-lg-2">
										<div class="panel panel-default">
											<div class="panel-heading"><small>Source Connection Name</small></div>
											<div class="panel-body">
												<div class="form-group">
													<select id="selRuleCaseConnectionSrc" name="selRuleCaseConnectionSrc" class="form-control" data-bind='value:selRuleCaseConnectionSrc,options:connectionOptions'>
													</select>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Source Table</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseSourceTable" name="txtRuleCaseSourceTable" type="text" class="form-control" placeholder="e.g., SALES_DAY_LEVEL_FACT" data-bind="value:txtRuleCaseSourceTable">
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Source Field</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseSourceField" name="txtRuleCaseSourceField" type="text" class="form-control" placeholder="e.g., TRAFFIC_COUNT" data-bind="value:txtRuleCaseSourceField">
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Source Condition Field (Optional)</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseSourceConditionField" name="txtRuleCaseSourceConditionField" type="text" class="form-control" placeholder="e.g., 1=1" data-bind="value:txtRuleCaseSourceConditionField">
												</div>
											</div>
										</div>
									</div>
									<div class="col-lg-2">
										<div class="panel panel-default">
											<div class="panel-heading"><small>Target Connection Name</small></div>
											<div class="panel-body">
												<div class="form-group">
													<select id="selRuleCaseConnectionTgt" name="selRuleCaseConnectionTgt" class="form-control" data-bind='value:selRuleCaseConnectionTgt,options:connectionOptions'>
													</select>
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Target Table</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseTargetTable" name="txtRuleCaseTargetTable" type="text" class="form-control" placeholder="e.g., SALES_DAY_LEVEL_FACT" data-bind="value:txtRuleCaseTargetTable">
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Target Field</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseTargetField" name="txtRuleCaseTargetField" type="text" class="form-control" placeholder="e.g., TRAFFIC_COUNT" data-bind="value:txtRuleCaseTargetField">
												</div>
											</div>
										</div>
										<div class="panel panel-default">
											<div class="panel-heading"><small>Target Condition Field (Optional)</small></div>
											<div class="panel-body">
												<div class="form-group">
													<input id="txtRuleCaseTargetConditionField" name="txtRuleCaseTargetConditionField" type="text" class="form-control" placeholder="e.g., 1=1" data-bind="value:txtRuleCaseTargetConditionField">
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