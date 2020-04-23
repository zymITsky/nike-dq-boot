
$(document).ready(function () {
	loadLastTcRcsRunHistoryAvgForChart();
	loadTcHistoryFailureCountForChart();
	loadTcRcsRunHistoryAllForChart(_initTcRuleCaseName);

	function loadLastTcRcsRunHistoryAvgForChart() {
		$.get(_contextRoot + "/rule/tc/view/detail/api/avg_tc_rc_rh_chart", function (data) {
			var chart = new CanvasJS.Chart("cj-tc-avg-trend", {
				animationEnabled: true,
				title: { text: "Average Rows Run History", fontSize: 12 },
				toolTip: { shared : true },
				axisY: { title: "# for rows" },
				data: [
					{
						type: "column", name: "Original Rows", click: function (e) { loadTcRcsRunHistoryAllForChart(e.dataPoint.label); }, dataPoints: data.origList 
					}, {
						type: "column", name: "Current Rows", click: function (e) { loadTcRcsRunHistoryAllForChart(e.dataPoint.label); }, dataPoints: data.currAvgList 
					}, {
						type: "line", name: "Expected Rows", click: function (e) { loadTcRcsRunHistoryAllForChart(e.dataPoint.label); }, dataPoints: data.expcList 
					}
				]
			});
			chart.render();
		});
	}

	function loadTcHistoryFailureCountForChart() {
		$.get(_contextRoot + "/rule/tc/view/detail/api/tc_his_fail_cnt_chart", function (data) {
			var chart = new CanvasJS.Chart("cj-tc-fail-cnt", {
				animationEnabled: true,
				title: { text: "Run History Failure Count", fontSize: 12 },
				legend: { verticalAlign: "bottom", horizontalAlign: "center" },
				data: [{ type: "pie", dataPoints: data }]
			});
			chart.render();
		});
	}

	function loadTcRcsRunHistoryAllForChart(name) {
		$.get(_contextRoot + "/rule/tc/view/detail/api/tc_rc_rh_all_chart/" + name, function (data) {
			var chart = new CanvasJS.Chart("cj-tc-history-sum", {
				animationEnabled: true, 
				title: { text: "Rule Case Run All History Result", fontSize: 12 },
				axisX: { interval: 1 }, 
				axisY: { title: "Rows" }, 
				legend: { verticalAlign: "bottom", horizontalAlign: "center" }, 
				toolTip: { shared: true, content: "<span style='\"'color: {color};'\"'><strong>{name}</strong></span> {y}" }, 
				data: [{
					name: "Actual Run Rows", 
					type: "spline", 
					markerSize: 0,
					dataPoints: data.actList 
				}, {
					name: "Expected Rows", 
					type: "spline", 
					markerSize: 0, 
					dataPoints: data.expList 
				}]
			});
			chart.render();
		});
	}
});