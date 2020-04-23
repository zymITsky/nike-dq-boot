<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title><tiles:getAsString name="PageTitle" ignore="true" /></title>
	<link rel="shortcut icon" href="<c:url value="/resources/nike.ico"/>" />

	<tiles:insertAttribute name="PageCss" />

</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<div id="toggleMenubar"></div>
				<a id="leftMenuBarSwitcher" href="#" class="navbar-brand"><b>Data Quality Platform</b></a>
			</div>
			<!-- Top Menu Bar -->
			<tiles:insertAttribute name="TopMenuBar" />
			<!-- Navigation Menu Bar - These collapse to the responsive navigation menu on small screens -->
			<div id="leftMenuBar" class="collapse navbar-collapse navbar-mainmenu-collapse"></div>
		</nav>
		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<tiles:insertAttribute name="PageHeader" />
				</div>
				<!-- Page Level navigation and panel bar -->
				<div class="row">
					<tiles:insertAttribute name="WorkflowTabPanel" />
				</div>
				<tiles:insertAttribute name="PageBody" />
				<a id="back-to-top" href="#" class="btn btn-primary btn-lg back-to-top" role="button" title="Click to return on the top page" data-toggle="tooltip" data-placement="left"><span class="glyphicon glyphicon-chevron-up"></span></a>
			</div>
		</div>
	</div>
	<div id="footer">
		<p>&copy;ED&A&nbsp;(NIKE.COM)&nbsp;2019.03&nbsp;&nbsp;ALL COPYRIGHTS RESERVED&nbsp;(by Skymarlio)</p>
	</div>
<script id="tmplleftMenuContent" type="text/html">
<c:set var="uname" value="${sessionScope.USER_CONFIG.userName}" />
<ul class="nav navbar-nav side-nav">
	<li id="lmi_data_source"><a href="<c:url value="/ds/list/form"/>"><i class="fa fa-fw fa-database"></i>&nbsp;Data Source</a></li>
	<li id="lmi_rule_case">
		<a href="javascript:;" data-toggle="collapse" data-target="#menuLv2Case"><i class="fa fa-fw fa-table"></i>&nbsp;Case&nbsp;<i class="fa fa-fw fa-caret-down"></i></a>
		<ul id="menuLv2Case" class="collapse">
			<li><a href="<c:url value="/rule/r21fc/list/form"/>">Rule#2.1 Freshness Check against current system date</a></li>
			<li><a href="<c:url value="/rule/r33mc/list/form"/>">Rule#3.3 Measure comparison between source and target check</a></li>
		</ul>
	</li>
	<li id="lmi_schedule"><a href="<c:url value="/schedule/list/form"/>"><i class="fa fa-fw fa-clock-o"></i>&nbsp;Schedule</a></li>
	<li id="lmi_dashboard"><a href="<c:url value="/dashboard/user/home"/>"><i class="fa fa-fw fa-dashboard"></i>&nbsp;Dashboard</a></li>
</ul>
</script>
</body>

<tiles:insertAttribute name="PageJs" />

</html>
