<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="col-lg-12">
	<ul class="nav nav-tabs">
		<li<c:if test="${RULE_QS_STEP_ID == 1}"> class="active"</c:if>>
			<a href="<c:url value="/rule/qs/list/form"/>"><i class="fa fa-fw fa-arrow-down"></i>&nbsp;<b>LIST ALL</b></a>
		</li>
		<li<c:if test="${RULE_QS_STEP_ID == 2}"> class="active"</c:if>>
			<a href="<c:url value="/rule/qs/new/form"/>"><i class="fa fa-fw fa-arrow-down"></i>&nbsp;<b>NEW CASE</b></a>
		</li>
	</ul>
</div>