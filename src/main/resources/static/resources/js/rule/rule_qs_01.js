
var tableRuleQss = null;
var editorRuleQss = null;

$(document).ready(function () {

	editorRuleQss = new $.fn.dataTable.Editor({
		ajax : _contextRoot + "/rule/qs/list/form/api/ruleqss",
		table : "#tbl-rule-qs",
		fields : [
		    { label: "Rule Case Description", name: "ruleCaseDescription" }, 
		    { label: "Rule Case Sql", name: "ruleCaseSql" }, 
		    { label: "Rule Case Severity", name: "ruleCaseSeverity", type: "select", options: [ 'HIGH', 'MEDIUM', 'LOW' ] }
		]
	});

	$('#tbl-rule-qs').on('click', 'tbody td:not(:first-child,:nth-child(2),:nth-child(3),:nth-child(4),:nth-child(5),:nth-child(6),:nth-child(7),:nth-child(11),:nth-child(12),:nth-child(13),:last-child)', function (e) {
		editorRuleQss.inline(this);
	});

	tableRuleQss = $('#tbl-rule-qs').DataTable({
		scrollX: true,
		scrollY: "425px",
		searching: true,
		lengthChange: true,
		lengthMenu: [ [ 10, 50, 200, -1 ], [ 10, 50, 200, "all" ] ],
		pagingType: "full_numbers",
		select: { style: "multi" },
		ordering: true,
		display: "envelope",
		ajax: _contextRoot + "/rule/qs/list/form/api/ruleqss",
		columns: [
			{ data: "ruleCaseId", sortable: false }, 
			{ data: "connection.connectionName", sortable: true }, 
			{ data: "connection.connectionDescription", sortable: false }, 
			{ data: "connection.connectionServerType", sortable: false }, 
			{ data: "connection.connectionServerHost", sortable: false }, 
			{ data: "connection.connectionDbLink", sortable: false }, 
			{ data: "ruleCaseName", sortable: true }, 
			{ data: "ruleCaseDescription", sortable: false }, 
			{ data: "ruleCaseSql", sortable: true }, 
			{ data: "ruleCaseSeverity", sortable: false }, 
			{ data: "ruleCaseCreatedBy", sortable: true }, 
			{ data: "ruleCaseCreatedDateStr", sortable: true },
			{ data: null, sortable: false, render: function (data, type, row) {
				return '<center><a href="javascript:;" class="rc_qs_run">RUN</a></center>';
			}},
			{ data: null, defaultContent: '', sortable: false, className: 'select-checkbox' }
		],
		rowCallback: function (row, data, displayIndex, displayIndexFull) {
			$(row).children().each(function (index, td) {
				switch (index) {
					case 8:
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

	$('#tbl-rule-qs').on('click', 'a.rc_qs_run', function (e) {
		e.preventDefault();
		var rowdata = tableRuleQss.row($(this).closest('tr')).data();
		$.get(_contextRoot + "/rule/qs/list/form/api/run/" + rowdata.ruleCaseId, function (data) {
			if (data.status == "Success") {
				$.prompt("Rule case has been run successful.");
			} else {
				$.prompt("Rule case run failed.");
			}
		});
	});

	new $.fn.dataTable.Buttons(tableRuleQss, [
		{ extend: "edit", editor: editorRuleQss }, 
		{ extend: "remove", editor: editorRuleQss }
	]);
	tableRuleQss.buttons().container().prependTo($('div.fg-toolbar:eq(0)', tableRuleQss.table().container()));

	var postEditNum = 0;

	editorRuleQss.on('postEdit', function (e, json, data) {
		if (postEditNum == 0) {
			tableRuleQss.ajax.reload();
		}
		if (++postEditNum == json.data.length) {
			postEditNum = 0;
		}
	});

	var postRemoveNum = 0;

	editorRuleQss.on('postRemove', function (e, json, data) {
		if (postRemoveNum == 0) {
			tableRuleQss.ajax.reload();
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
				html: '<center>Are you sure to run all the cases for Rule#QueryString?</center>',
				buttons: { "Yes": true, "No": false },
				submit: function (e, v, m, f) {
					if (v) {
						$.get(_contextRoot + "/rule/qs/list/form/api/run_batch/all", function (data) {
							if (data.status == "Success") {
								$.prompt("All the rule cases run successful.");
							} else {
								$.prompt("Some rule cases run failed.");
							}
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
						var count = tableRuleQss.rows({ selected: true }).count();
						var rows = tableRuleQss.rows({ selected: true }).data();
						if (count != 0) {
							for (var i = 0; i < count; i++) {
								ids += (rows[i].ruleCaseId + "-");
							}
							$.get(_contextRoot + "/rule/qs/list/form/api/run_batch/" + ids, function (data) {
								if (data.status == "Success") {
									$.prompt("All the rule cases run successful.");
								} else {
									$.prompt("Some rule cases run failed.");
								}
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
});