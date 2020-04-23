<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-lg-6 col-lg-offset-3">
		<shiro:hasPermission name="suser:view">
		<form role="form">
			<div class="form-group input-group">
				<input type="text" class="form-control" placeholder="Searching from here ..." />
				<span class="input-group-btn"><button class="btn btn-default" type="button"><i class="fa fa-search"></i></button></span>
			</div>
		</form>
		</shiro:hasPermission>
	</div>
</div>
<!-- System Alert message -->
<div class="row">
<script id="warn-info-tmpl" type="text/html">
<div class="alert alert-danger alert-dismissable">
	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	<i class="fa fa-info-circle"></i>&nbsp;<strong data-content="content"></strong>&nbsp;Try to check with the&nbsp;<a href="#" class="alert-link">Technical Support</a>&nbsp;for IT Help!
</div>
</script>
	<div id="sys-info-prompt" class="col-lg-12">
	</div>
</div>
<div class="row">
	<div class="col-sm-2 col-sm-offset-2">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<div class="row">
					<a href="<c:url value="/ds/list/form"/>">
						<div class="col-xs-3"><i class="fa fa-database fa-3x"></i></div>
						<div class="col-xs-9 text-right">Quick Start for:<br/><h6>Data Source</h6></div>
					</a>
				</div>
			</div>
			<a href="<c:url value="/ds/list/form"/>">
				<div class="panel-footer">
					<span class="pull-left">View Details</span>&nbsp;<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-sm-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="row">
					<a href="<c:url value="/rule/r21fc/list/form"/>">
						<div class="col-xs-3"><i class="fa fa-table fa-3x"></i></div>
						<div class="col-xs-9 text-right">Quick Start for:<br/><h6>Rule: Rule Spec 2.1</h6></div>
					</a>
				</div>
			</div>
			<a href="<c:url value="/rule/r21fc/list/form"/>">
				<div class="panel-footer">
					<span class="pull-left">View Details</span>&nbsp;<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-sm-2">
		<div class="panel panel-warning">
			<div class="panel-heading">
				<div class="row">
					<a href="<c:url value="/schedule/list/form"/>">
						<div class="col-xs-3"><i class="fa fa-clock-o fa-3x"></i></div>
						<div class="col-xs-9 text-right">Quick Start for:<br/><h6>Schedule</h6></div>
					</a>
				</div>
			</div>
			<a href="<c:url value="/schedule/list/form"/>">
				<div class="panel-footer">
					<span class="pull-left">View Details</span>&nbsp;<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
	<div class="col-sm-2">
		<div class="panel panel-success">
			<div class="panel-heading">
				<div class="row">
					<a href="<c:url value="/dashboard/user/home"/>">
						<div class="col-xs-3"><i class="fa fa-dashboard fa-3x"></i></div>
						<div class="col-xs-9 text-right">Quick Start for:<br/><h6>Dashboard</h6></div>
					</a>
				</div>
			</div>
			<a href="<c:url value="/dashboard/user/home"/>">
				<div class="panel-footer">
					<span class="pull-left">View Details</span>&nbsp;<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					<div class="clearfix"></div>
				</div>
			</a>
		</div>
	</div>
</div>
<!-- optional panels -->
<div class="row" style="height:650px">
</div>