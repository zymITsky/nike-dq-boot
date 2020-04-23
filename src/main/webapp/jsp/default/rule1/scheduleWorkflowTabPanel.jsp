<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="col-lg-12">
	<ul class="nav nav-tabs">
		<li<c:if test="${SCHEDULE_STEP_ID == 1}"> class="active"</c:if>>
			<a href="javascript:;"><i class="fa fa-fw fa-arrow-down"></i>&nbsp;<b>LIST ALL</b></a>
		</li>
	</ul>
</div>