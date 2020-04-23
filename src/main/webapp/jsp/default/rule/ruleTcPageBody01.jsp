<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">Rule defined about Verifying Size of Table Rows <small>for showing all the cases</small></h3>
		<p>This module is designed and used for monitoring the various kinds of data sources on rules status and scheduling, and it also can provide the function to manipulate, configure and update the rule case.</p>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"><i class="fa fa-table fa-fw"></i>&nbsp;Template for TableCount</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div id="statusBarPlanResult" class="col-lg-12">
						<ol class="breadcrumb">
							<li><i class="fa fa-usd"></i>&nbsp;Total: <span style="color:green;font-weight:bold;">100</span>%</li>
							<li><i class="fa fa-tags"></i>&nbsp;Planned: <span style="color:blue;font-weight:bold;">70</span>%</li>
							<li><i class="fa fa-share"></i>&nbsp;Remained: <span style="color:brown;font-weight:bold;">30</span>%</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div class="panel panel-red">
							<div class="panel-body"><div id="cj-tc-last-history"></div></div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="panel panel-primary">
							<div class="panel-body"><div id="cj-tc-connection"></div></div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="panel panel-green">
							<div class="panel-body"><div id="cj-tc-createdby"></div></div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="panel panel-yellow">
							<div class="panel-body"><div id="cj-tc-severity"></div></div>
						</div>
					</div>
				</div>
				<div class="table-responsive">
					<table id="tbl-rule-tc" class="display" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th rowspan="2">CASE<br>ID</th>
								<th rowspan="1" colspan="5">CONNECTION</th>
								<th rowspan="2">CASE<br>NAME</th>
								<th rowspan="2">CASE<br>DESCRIPTION</th>
								<th rowspan="1" colspan="5">TABLE<br>COUNT<br>RULE</th>
								<th rowspan="2">CASE<br>SEVERITY</th>
								<th rowspan="2">CREATED<br>BY</th>
								<th rowspan="2">CREATED<br>DATETIME</th>
								<th rowspan="2">ACTION</th>
								<th rowspan="2">MULTI<br>ToRUN</th>
							</tr>
							<tr>
								<th rowspan="1" colspan="1">NAME</th>
								<th rowspan="1" colspan="1">DESCRIPTION</th>
								<th rowspan="1" colspan="1">SERVER<br>TYPE</th>
								<th rowspan="1" colspan="1">SERVER<br>HOST</th>
								<th rowspan="1" colspan="1">DB<br>LINK</th>
								<th rowspan="1" colspan="1">CASE<br>TARGET<br>DB</th>
								<th rowspan="1" colspan="1">CASE<br>TARGET<br>TABLE</th>
								<th rowspan="1" colspan="1">CASE<br>ORIGINAL<br>ROWS<br>SIZE</th>
								<th rowspan="1" colspan="1">>= %</th>
								<th rowspan="1" colspan="1"><= %</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="text-right">
					<a href="javascript:;">Exported as MS OFFICE EXCEL (97-2003)&nbsp;<i class="fa fa-download"></i></a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<p><div style="text-align:center"><button id="btnRunAll" type="button" style="width:180px;" class="btn btn-primary btn-lg">Run All</button>&nbsp;<button id="btnRunSelected" type="button" style="width:180px;" class="btn btn-primary btn-lg">Run Selected</button>&nbsp;<a id="btnNewCase" href='<c:url value="/rule/tc/new/form"/>' style="width:180px;" class="btn btn-primary btn-lg">New Case&nbsp;&nbsp;&raquo;</a></div></p>
	</div>
</div>