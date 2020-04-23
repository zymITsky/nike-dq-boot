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
					<table id="tbl-ds" class="display" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>Data source<br>number</th>
								<th>Data source<br>name</th>
								<th>Data source<br>owner</th>
								<th>Data source<br>description</th>
								<th>Data source<br>type</th>
								<th>Created<br>date</th>
								<th>Created<br>by</th>
								<th>Last modified<br>date</th>
								<th>Last modified<br>by</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>