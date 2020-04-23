
var tableRuleTcs = null;
var editorRuleTcs = null;

$(document).ready(function () {

	loadLastTcRcsRunHistoryForChart();
	loadConnUsedStatusForChart();
	loadTcCreatedByForChart();
	loadTcCaseSeverityForChart();

	editorRuleTcs = new $.fn.dataTable.Editor({
		ajax : _contextRoot + "/rule/tc/list/form/api/ruletcs",
		table : "#tbl-rule-tc",
		fields : [
		    { label: "Rule Case Description", name: "ruleCaseDescription" }, 
		    { label: "Rule Case Original Rows", name: "ruleCaseOriginalTableSize" }, 
		    { label: ">= %", name: "ruleCaseRowsGapGt" }, 
		    { label: "<= %", name: "ruleCaseRowsGapLt" }, 
		    { label: "Rule Case Severity", name: "ruleCaseSeverity", type: "select", options: [ 'HIGH', 'MEDIUM', 'LOW' ] }
		]
	});

	$('#tbl-rule-tc').on('click', 'tbody td:not(:first-child,:nth-child(2),:nth-child(3),:nth-child(4),:nth-child(5),:nth-child(6),:nth-child(7),:nth-child(9),:nth-child(10),:nth-child(15),:last-child)', function (e) {
		editorRuleTcs.inline(this);
	});

	tableRuleTcs = $('#tbl-rule-tc').DataTable({
		scrollX: true,
		scrollY: "425px",
		searching: true,
		lengthChange: true,
		lengthMenu: [ [ 10, 50, 200, -1 ], [ 10, 50, 200, "all" ] ],
		pagingType: "full_numbers",
		select: { style: "multi" },
		ordering: true,
		display: "envelope",
		ajax: _contextRoot + "/rule/tc/list/form/api/ruletcs",
		columns: [
			{ data: "ruleCaseId", sortable: false }, 
			{ data: "connection.connectionName", sortable: true }, 
			{ data: "connection.connectionDescription", sortable: false }, 
			{ data: "connection.connectionServerType", sortable: false }, 
			{ data: "connection.connectionServerHost", sortable: false }, 
			{ data: "connection.connectionDbLink", sortable: false }, 
			{ data: "ruleCaseName", sortable: true }, 
			{ data: "ruleCaseDescription", sortable: false }, 
			{ data: "ruleCaseTargetDb", sortable: true }, 
			{ data: "ruleCaseTargetTable", sortable: true }, 
			{ data: "ruleCaseOriginalTableSize", sortable: true }, 
			{ data: "ruleCaseRowsGapGt", sortable: false, render: $.fn.dataTable.render.number(',', '.', 2, '', '%') }, 
			{ data: "ruleCaseRowsGapLt", sortable: false, render: $.fn.dataTable.render.number(',', '.', 2, '', '%') }, 
			{ data: "ruleCaseSeverity", sortable: false }, 
			{ data: "ruleCaseCreatedBy", sortable: true }, 
			{ data: "ruleCaseCreatedDateStr", sortable: true },
			{ data: null, sortable: false, render: function (data, type, row) {
				return '<center><a href="javascript:;" class="rc_tc_run">RUN</a></center>';
			}},
			{ data: null, defaultContent: '', sortable: false, className: 'select-checkbox' }
		],
		rowCallback: function (row, data, displayIndex, displayIndexFull) {
			$(row).children().each(function (index, td) {
				switch (index) {
					case 10: case 11: case 12:
						$(td).css("background-color", "#FFFFCC");
						$(td).html("<b>" + $(td).html() + "</b>");
						break;
					default:
						break;
				}
			});
			return row;
		}
	});

	$('#tbl-rule-tc').on('click', 'a.rc_tc_run', function (e) {
		e.preventDefault();
		var rowdata = tableRuleTcs.row($(this).closest('tr')).data();
		$.get(_contextRoot + "/rule/tc/list/form/api/run/" + rowdata.ruleCaseId, function (data) {
			if (data.status == "Success") {
				loadLastTcRcsRunHistoryForChart();
				loadConnUsedStatusForChart();
				loadTcCreatedByForChart();
				loadTcCaseSeverityForChart();
			}
		});
	});

	new $.fn.dataTable.Buttons(tableRuleTcs, [
		{ extend: "edit", editor: editorRuleTcs }, 
		{ extend: "remove", editor: editorRuleTcs }
	]);
	tableRuleTcs.buttons().container().prependTo($('div.fg-toolbar:eq(0)', tableRuleTcs.table().container()));

	var postEditNum = 0;

	editorRuleTcs.on('postEdit', function (e, json, data) {
		if (postEditNum == 0) {
			tableRuleTcs.ajax.reload();
			loadLastTcRcsRunHistoryForChart();
			loadConnUsedStatusForChart();
			loadTcCreatedByForChart();
			loadTcCaseSeverityForChart();
		}
		if (++postEditNum == json.data.length) {
			postEditNum = 0;
		}
	});

	editorRuleTcs.on("preSubmit", function (e, o, action) {
		if (action == "edit") {
			for (var key in o.data) {
				var rowId = key;
				var field = o.data[rowId];
				for (var fieldname in field) {
					var objField = this.field(fieldname);
					if (!objField.isMultiValue()) {
						if (!objField.val()) {
							objField.error('Field required.');
						}
						if (fieldname != 'ruleCaseDescription' && fieldname != 'ruleCaseSeverity' && isNaN(objField.val())) {
							objField.error('Number Type required.');
						}
						if (this.inError()) {
							return false;
						}
					}
				}
			}
		}
	});

	var postRemoveNum = 0;

	editorRuleTcs.on('postRemove', function (e, json, data) {
		if (postRemoveNum == 0) {
			tableRuleTcs.ajax.reload();
			loadLastTcRcsRunHistoryForChart();
			loadConnUsedStatusForChart();
			loadTcCreatedByForChart();
			loadTcCaseSeverityForChart();
		}
		if (++postRemoveNum == json.data.length) {
			postRemoveNum = 0;
		}
	});

	$("#btnRunAll").click(function (e) {
		e.preventDefault();
		$.prompt({
			state0: {
				title: "Warning:",
				html: '<center>Are you sure to run all the cases for Rule#TableCount?</center>',
				buttons: { "Yes": true, "No": false },
				submit: function (e, v, m, f) {
					if (v) {
						$.get(_contextRoot + "/rule/tc/list/form/api/run_batch/all", function (data) {
							window.location.replace(_contextRoot + "/rule/tc/list/form");
						});
					}
				}
			}
		});
	});

	$("#btnRunSelected").click(function (e) {
		e.preventDefault();
		$.prompt({
			state0: {
				title: "Warning:",
				html: '<center>Are you sure to run the selected cases for Rule#TableCount?</center>',
				buttons: { "Yes": true, "No": false },
				submit: function (e, v, m, f) {
					if (v) {
						var ids = "";
						var count = tableRuleTcs.rows({ selected: true }).count();
						var rows = tableRuleTcs.rows({ selected: true }).data();
						if (count != 0) {
							for (var i = 0; i < count; i++) {
								ids += (rows[i].ruleCaseId + "-");
							}
							$.get(_contextRoot + "/rule/tc/list/form/api/run_batch/" + ids, function (data) {
								window.location.replace(_contextRoot + "/rule/tc/list/form");
							});
						} else {
							alert("No case selected.");
							e.preventDefault();
							$.prompt.close();
						}
					}
				}
			}
		});
	});

	function highlightDtRow(e) {
		$.each(tableRuleTcs.rows().data(), function (index, value) {
			if (value.ruleCaseName == e.dataPoint.label) {
				tableRuleTcs.$("tr.selected").removeClass("selected");
				tableRuleTcs.row(index).select();
				return false;
			}
    	});
	}

	function loadLastTcRcsRunHistoryForChart() {
		$.get(_contextRoot + "/rule/tc/list/form/api/last_tc_rc_rh_chart", function (data) {
			var chart = new CanvasJS.Chart("cj-tc-last-history", {
				animationEnabled: true,
				title: { text: "Last Run History", fontSize: 12 },
				toolTip: { shared : true },
				axisY: { title: "# for rows" },
				data: [
					{
						type: "column", name: "Original Rows", click: function (e) { highlightDtRow(e); }, dataPoints: data.origList 
					}, {
						type: "column", name: "Current Rows", click: function (e) { highlightDtRow(e); }, dataPoints: data.currList 
					}, {
						type: "line", name: "Expected Rows", click: function (e) { highlightDtRow(e); }, dataPoints: data.expcList 
					}
				]
			});
			chart.render();
		});
	}

	function loadConnUsedStatusForChart() {
		$.get(_contextRoot + "/rule/tc/list/form/api/tc_conn_used_chart", function (data) {
			var chart = new CanvasJS.Chart("cj-tc-connection", {
				animationEnabled: true,
				title: { text: "Connection Used Status", fontSize: 12 },
				legend: { verticalAlign: "bottom", horizontalAlign: "center" },
				data: [{ type: "pie", dataPoints: data }]
			});
			chart.render();
		});
	}

	function loadTcCreatedByForChart() {
		$.get(_contextRoot + "/rule/tc/list/form/api/tc_created_by_chart", function (data) {
			var chart = new CanvasJS.Chart("cj-tc-createdby", {
				animationEnabled: true, 
				theme: "light2", 
				title: { text: "Case Created By", fontSize: 12 },
				axisY: { interval: 10 },
				toolTip: { shared: true },
				data: [{
					type: "stackedBar100", showInLegend: true, name: "Created cases", dataPoints: data 
				}]
			});
			chart.render();
		});
	}

	function loadTcCaseSeverityForChart() {
		$.get(_contextRoot + "/rule/tc/list/form/api/tc_case_severity_chart", function (data) {
			var chart = new CanvasJS.Chart("cj-tc-severity", {
				animationEnabled: true,
				title: { text: "Case Severity", fontSize: 12 },
				legend: { verticalAlign: "bottom", horizontalAlign: "center" },
				data: [{ type: "pie", dataPoints: data }]
			});
			chart.render();
		});
	}
});