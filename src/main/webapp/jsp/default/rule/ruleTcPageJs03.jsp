<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!-- jQuery -->
<script type="text/javascript" src="<c:url value="/resources/plugin/jquery-1.12.4.min.js"/>"></script>
<!-- Bootstrap Core JavaScript -->
<script type="text/javascript" src="<c:url value="/resources/plugin/bootstrap-3.3.7/js/bootstrap.min.js"/>"></script>
<!-- jQuery plug-ins for Prompt -->
<script type="text/javascript" src="<c:url value="/resources/plugin/jquery.impromptu-6.2.2/js/jquery-impromptu.min.js"/>"></script>
<!-- CanvasJS Charts -->
<script type="text/javascript" src="<c:url value="/resources/plugin/canvasjs-1.9.10/js/canvasjs.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/plugin/canvasjs-1.9.10/js/jquery.canvasjs.min.js"/>"></script>
<!-- APP Script -->
<script type="text/javascript" src="<c:url value="/resources/js/common/common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/rule/rule_tc_03.js"/>"></script>
<script type="text/javascript">
	var _contextRoot = '<%=request.getContextPath() %>';
	doForLeftMenuBar("rule_case");
	$(document).ready(function () {
		$("#leftMenuBarSwitcher").click(function (event) {
			event.preventDefault();
			doForLeftMenuBar("rule_case");
		});
	});
	var _initTcRuleCaseName = '${INIT_TC_RULE_CASE_NAME}';
</script>