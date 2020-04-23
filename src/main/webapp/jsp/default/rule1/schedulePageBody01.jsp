<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<h3 class="page-header"><small>Rule / Rule group defined about checking data quality for scheduling to be triggered to by daily / monthly / yearly.</small></h3>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="table-responsive">
					<table id="tbl-schedule" class="display" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>Job Id</th>
								<th>Job name</th>
								<th>Job description</th>
								<th>Rule detail</th>
								<th>Cron expression</th>
								<th>Schedule detail</th>
								<th>Launched</th>
								<th>Status</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>