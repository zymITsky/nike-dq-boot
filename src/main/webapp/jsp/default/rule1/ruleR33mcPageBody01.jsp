<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">To check data consistency by comparing target data set calculated result with baseline (source data set)</h4>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-2">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Source Connection Name</h3>
							</div>
							<div class="panel-body">
								<select id="selSrcConnName" class="form-control r33mc-filter" multiple="multiple">
								</select>
							</div>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Target Connection Name</h3>
							</div>
							<div class="panel-body">
								<select id="selTgtConnName" class="form-control r33mc-filter" multiple="multiple">
								</select>
							</div>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Business Function</h3>
							</div>
							<div class="panel-body">
								<select id="selBizFunc" class="form-control r33mc-filter" multiple="multiple">
								</select>
							</div>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Case Owner</h3>
							</div>
							<div class="panel-body">
								<select id="selCaseOwner" class="form-control r33mc-filter" multiple="multiple">
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="table-responsive">
					<table id="tbl-rule-r33mc" class="display" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>Case<br>number</th>
								<th>Case<br>name</th>
								<th>Case<br>owner</th>
								<th>Business<br>function</th>
								<th>Case<br>description</th>
								<th>Source<br>connection name</th>
								<th>Source<br>table</th>
								<th>Source<br>field</th>
								<th>Source<br>condition field</th>
								<th>Target<br>connection name</th>
								<th>Target<br>table</th>
								<th>Target<br>field</th>
								<th>Target<br>condition field</th>
								<th>Last modified<br>by</th>
								<th>Last modified<br>date</th>
								<th>Check<br>history</th>
								<th>Last<br>Run<br>result</th>
							</tr>
						</thead>
					</table>
				</div>
				<div id="frmRunHistoryModel" class="modal" style="display: none;">
					<div class="modal-dialog" style="width:800px;">
						<div class="modal-content" >
							<div class="modal-header">
								<button data-dismiss="modal" class="close">&times;</button>
								<h3 class="modal-title">Rule case's run history list</h3>
							</div>
							<div class="modal-body">
								<div style="height:550px;">
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-default">
												<div class="panel-body">
													<div class="row">
														<div class="col-lg-12">
															<div class="table-responsive">
																<table id="tbl-rc-rh" class="display nowrap" style="width:100%">
																	<thead>
																		<tr>
																			<th>Run number</th>
																			<th>Run start time</th>
																			<th>Run end time</th>
																			<th>Run result</th>
																			<th>Source measure</th>
																			<th>Target measure</th>
																			<th>Different Percent</th>
																			<th>Tolerance</th>
																		</tr>
																	</thead>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>