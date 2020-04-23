<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!-- jQuery -->
<script type="text/javascript" src="<c:url value="/resources/plugin/jquery-1.12.4.min.js"/>"></script>
<!-- Bootstrap Core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/plugin/bootstrap-3.3.7/js/bootstrap.min.js"/>"></script>
<!-- Bootstrap Multiselect JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/plugin/bootstrap-multiselect-0.9.13/js/bootstrap-multiselect.js"/>"></script>
<!-- jQuery plug-ins for Prompt -->
<script type="text/javascript" src="<c:url value="/resources/plugin/jquery.impromptu-6.2.2/js/jquery-impromptu.min.js"/>"></script>
<!-- MVVM Knockout -->
<script type="text/javascript" src="<c:url value="/resources/plugin/knockout-3.4.2/js/knockout.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/plugin/knockout.mapping-2.4.1/js/knockout.mapping.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/plugin/knockout.validation-1.0.1/js/knockout.validation.min.js"/>"></script>
<!-- APP Script -->
<script type="text/javascript" src="<c:url value="/resources/js/common/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/rule/rule_r33mc_02.js"/>"></script>
<script type="text/javascript">
	var _contextRoot = '<%=request.getContextPath() %>';
	doForLeftMenuBar("rule_case");
	$(document).ready(function () {
		$("#leftMenuBarSwitcher").click(function (event) {
			event.preventDefault();
			doForLeftMenuBar("rule_case");
		});
	});
</script>