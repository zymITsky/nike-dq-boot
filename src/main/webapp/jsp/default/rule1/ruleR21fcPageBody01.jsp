<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h4 class="page-header">To check data freshness by comparing target data set with current system date</h4>
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
								<h3 class="panel-title">Target Connection Name</h3>
							</div>
							<div class="panel-body">
								<select id="selTgtConnName" class="form-control r21fc-filter" multiple="multiple">
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
								<select id="selBizFunc" class="form-control r21fc-filter" multiple="multiple">
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
								<select id="selCaseOwner" class="form-control r21fc-filter" multiple="multiple">
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="table-responsive">
					<table id="tbl-rule-r21fc" class="display" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>Case<br>number</th>
								<th>Case<br>name</th>
								<th>Case<br>owner <a id="f_2" href="javascript:;" class="col-fllr"><i class="fa fa-filter"></i></a></th>
								<th>Business<br>function <a id="f_3" href="javascript:;" class="col-fllr"><i class="fa fa-filter"></i></a></th>
								<th>Case<br>description</th>
								<th>Target<br>connection name <a id="f_5" href="javascript:;" class="col-fllr"><i class="fa fa-filter"></i></a></th>
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
																			<th>Target measure</th>
																			<th>Tolerance [day(s)]</th>
																			<th>Difference [day(s)]</th>
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
				<div id="frmColFltModel" class="modal" style="display: none;">
					<div class="modal-dialog" style="width:320px;">
						<div class="modal-content" >
							<div class="modal-header">
								<button data-dismiss="modal" class="close">&times;</button>
								<h3 class="modal-title">Filter rows by column</h3>
							</div>
							<div class="modal-body">
								<div style="height:150px;">
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h3 class="panel-title">For values of <b id="fByTitle"></b></h3>
												</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-lg-12">
															<div class="form-group">
																<select id="selColFlt" class="form-control" multiple="multiple">
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<input id="btnColFltCls" class="btn btn-md btn-primary btn-block" type="button" value="Clear" />
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<input id="btnColFlt" class="btn btn-md btn-success btn-block" type="button" value="Start to filter" />
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