<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header">Rule defined about Verifying Size of Table Rows <small>for viewing simple report</small></h3>
		<p>This module is designed and used for monitoring the various kinds of data sources on rules status and scheduling, and it also can provide the function to manipulate, configure and update the rule case.</p>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"><i class="fa fa-table fa-fw"></i>&nbsp;View Simple Report for Rule#TableCount</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-9">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<h3 class="panel-title"><i class="fa fa-bar-chart fa-fw"></i>&nbsp;#TC-AVG-TREND</h3>
							</div>
							<div class="panel-body">
								<div id="cj-tc-avg-trend"></div>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title"><i class="fa fa-pie-chart fa-fw"></i>&nbsp;#TC-AVG-TREND</h3>
							</div>
							<div class="panel-body">
								<div id="cj-tc-fail-cnt"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-green">
							<div class="panel-heading">
								<h3 class="panel-title"><i class="fa fa-line-chart fa-fw"></i>&nbsp;#TC-AVG-TREND</h3>
							</div>
							<div class="panel-body">
								<div id="cj-tc-history-sum"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>