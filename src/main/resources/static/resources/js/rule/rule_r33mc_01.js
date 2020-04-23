
var tableRuleR33mcs = null;
var editorRuleR33mcs = null;

$(document).ready(function () {

	$("#selSrcConnName").append(function () {
		var output = '';
		$.each(listOfDataSource, function (key, value) {
			output += '<option value="' + value + '">' + value + '</option>';
		});
		return output;
	});

	$("#selTgtConnName").append(function () {
		var output = '';
		$.each(listOfDataSource, function (key, value) {
			output += '<option value="' + value + '">' + value + '</option>';
		});
		return output;
	});

	$("#selBizFunc").append(function () {
		var output = '';
		$.each(listOfBizFunc, function (key, value) {
			output += '<option value="' + value + '">' + value + '</option>';
		});
		return output;
	});

	$("#selCaseOwner").append(function () {
		var output = '';
		$.each(listOfCaseOwner, function (key, value) {
			output += '<option value="' + value + '">' + value + '</option>';
		});
		return output;
	});

	$(".r33mc-filter").multiselect({
		maxHeight: 300,
		buttonWidth: '100%',
		includeSelectAllOption: true,
		onDropdownHide: function (event) {
			refreshDtRuleR33mcsByFilters();
		}
	});

	function refreshDtRuleR33mcsByFilters() {
		var srcConnName = $("#selSrcConnName").val();
		var tgtConnName = $("#selTgtConnName").val();
		var bizFunc = $("#selBizFunc").val();
		var caseOwner = $("#selCaseOwner").val();
		var allSelectedCN_Src = $("#selSrcConnName option:not(:selected)").length == 0;
		var allSelectedCN_Tgt = $("#selTgtConnName option:not(:selected)").length == 0;
		var allSelectedBF = $("#selBizFunc option:not(:selected)").length == 0;
		var allSelectedCO = $("#selCaseOwner option:not(:selected)").length == 0;
		if ((srcConnName == null || allSelectedCN_Src) && (tgtConnName == null || allSelectedCN_Tgt) && (bizFunc == null || allSelectedBF) && (caseOwner == null || allSelectedCO)) {
			tableRuleR33mcs.ajax.url(_contextRoot + "/rule/r33mc/list/form/api/ruler33mcs").load();
		} else {
			if (srcConnName == null || allSelectedCN_Src) {
				srcConnName = "-";
			}
			if (tgtConnName == null || allSelectedCN_Tgt) {
				tgtConnName = "-";
			}
			if (bizFunc == null || allSelectedBF) {
				bizFunc = "-";
			}
			if (caseOwner == null || allSelectedCO) {
				caseOwner = "-";
			}
			tableRuleR33mcs.ajax.url(_contextRoot + "/rule/r33mc/list/form/api/ruler33mcs_filter?srcConnName=" + srcConnName + "&tgtConnName=" + tgtConnName + "&bizFunc=" + bizFunc + "&caseOwner=" + caseOwner).load();
		}
	}

	function updateCaseOwnersAndFiltersFresh() {
		$.get(_contextRoot + "/rule/r33mc/caseowner/api/all", function (data) {
			var oldSelected = $("#selCaseOwner").val();
			var oldAllSelected = $("#selCaseOwner option:not(:selected)").length == 0;
			$("#selCaseOwner").empty();
			$("#selCaseOwner").append(function () {
				var output = '';
				$.each(data, function (key, value) {
					if (oldSelected != null && !oldAllSelected) {
						if (oldSelected.includes(value)) {
							output += '<option selected="true" value="' + value + '">' + value + '</option>';
						} else {
							output += '<option value="' + value + '">' + value + '</option>';
						}
					} else {
						output += '<option value="' + value + '">' + value + '</option>';
					}
				});
				return output;
			});
			$("#selCaseOwner").multiselect('rebuild');
			refreshDtRuleR33mcsByFilters();
		});
	}

	editorRuleR33mcs = new $.fn.dataTable.Editor({
		ajax: _contextRoot + "/rule/r33mc/list/form/api/ruler33mcs",
		table: "#tbl-rule-r33mc",
		fields: [
			{ label: "Case name", name: "ruleCaseName" }, 
		    { label: "Case owner", name: "ruleCaseOwner" }, 
		    { label: "Business function", name: "ruleCaseBusinessFunction", type: "select", options: listOfBizFunc }, 
		    { label: "Case description", name: "ruleCaseDescription" }, 
		    { label: "Source connection name", name: "ruleCaseSourceConnectionName", type: "select", options: listOfDataSource }, 
		    { label: "Source table", name: "ruleCaseSourceTable" }, 
		    { label: "Source field", name: "ruleCaseSourceField" }, 
		    { label: "Source condition field", name: "ruleCaseSourceConditionField" }, 
		    { label: "Target connection name", name: "ruleCaseTargetConnectionName", type: "select", options: listOfDataSource }, 
		    { label: "Target table", name: "ruleCaseTargetTable" }, 
		    { label: "Target field", name: "ruleCaseTargetField" }, 
		    { label: "Target condition field", name: "ruleCaseTargetConditionField" }
		]
	});

	tableRuleR33mcs = $('#tbl-rule-r33mc').DataTable({
		scrollX: true,
		scrollY: "850px",
		searching: true,
		lengthChange: false,
		select: { style: "single" },
		ordering: false,
		display: "envelope",
		ajax: _contextRoot + "/rule/r33mc/list/form/api/ruler33mcs",
		columns: [
			{ data: "ruleCaseId" }, 
			{ data: "ruleCaseName" }, 
			{ data: "ruleCaseOwner" }, 
			{ data: "ruleCaseBusinessFunction" }, 
			{ data: null, render: function (data, type, row) {
				return (data.ruleCaseDescription.length > 50) ? (data.ruleCaseDescription.substr(0, 50) + " ...") : data.ruleCaseDescription;
			}}, 
			{ data: "ruleCaseSourceConnectionName" }, 
			{ data: "ruleCaseSourceTable" }, 
			{ data: "ruleCaseSourceField" }, 
			{ data: null, render: function (data, type, row) {
				return (data.ruleCaseSourceConditionField.length > 50) ? (data.ruleCaseSourceConditionField.substr(0, 50) + " ...") : data.ruleCaseSourceConditionField;
			}}, 
			{ data: "ruleCaseTargetConnectionName" }, 
			{ data: "ruleCaseTargetTable" }, 
			{ data: "ruleCaseTargetField" }, 
			{ data: null, render: function (data, type, row) {
				return (data.ruleCaseTargetConditionField.length > 50) ? (data.ruleCaseTargetConditionField.substr(0, 50) + " ...") : data.ruleCaseTargetConditionField;
			}}, 
			{ data: "ruleCaseLastModifiedBy" }, 
			{ data: "ruleCaseLastModifiedDateStr" }, 
			{ data: null, render: function (data, type, row) {
				return '<center><a href="javascript:;" class="rc_r33mc_history">HISTORY</a></center>';
			}}, 
			{ data: "lastRunResult" }
		],
		rowCallback : function (row, data, displayIndex, displayIndexFull) {
			$(row).children().each(function (index, td) {
				switch (index) {
					case 16:
						if (data.lastRunResult == "PASSED") {
							$(td).html("<font color='blue'><b>" + $(td).html() + "</font></b>");
						} else {
							$(td).html("<font color='red'><b>" + $(td).html() + "</font></b>");
						}
						break;
					default:
						break;
				}
			});
			return row;
		}
	});

	new $.fn.dataTable.Buttons(tableRuleR33mcs, [
		{ extend: "edit", editor: editorRuleR33mcs },
		{ extend: "remove", editor: editorRuleR33mcs },
		{ text: 'Run all', action: function (e, dt, node, config) {
			$.prompt({
				state0: {
					title: "Warning:",
					html: '<center>Are you sure to run all the cases?</center>',
					buttons: { "Yes": true, "No": false },
					submit: function (e, v, m, f) {
						if (v) {
							$.get(_contextRoot + "/rule/r33mc/list/form/api/run_batch/all", function (data) {
								if (data.status == "Success") {
									tableRuleR33mcs.ajax.reload();
									$.prompt("All the rule cases run successful.");
								} else {
									$.prompt("Some rule cases run failed.");
								}
							});
						}
					}
				}
			});
		}},
		{ text: 'Run selected', action: function (e, dt, node, config) {
			$.prompt({
				state0: {
					title: "Warning:",
					html: '<center>Are you sure to run the selected cases?</center>',
					buttons: { "Yes": true, "No": false },
					submit: function (e, v, m, f) {
						if (v) {
							var ids = "";
							var count = tableRuleR33mcs.rows({ selected: true }).count();
							var rows = tableRuleR33mcs.rows({ selected: true }).data();
							if (count != 0) {
								for (var i = 0; i < count; i++) {
									ids += (rows[i].ruleCaseId + "-");
								}
								$.get(_contextRoot + "/rule/r33mc/list/form/api/run_batch/" + ids, function (data) {
									if (data.status == "Success") {
										tableRuleR33mcs.ajax.reload();
										$.prompt("All the selected rule cases run successful.");
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
		}},
		{ text: 'Copy', action: function (e, dt, node, config) {
			$.prompt({
				state0: {
					title: "Warning:",
					html: '<center>Are you sure to run the selected cases?</center>',
					buttons: { "Yes": true, "No": false },
					submit: function (e, v, m, f) {
						if (v) {
							var ids = "";
							var count = tableRuleR33mcs.rows({ selected: true }).count();
							var rows = tableRuleR33mcs.rows({ selected: true }).data();
							if (count != 0) {
								var rd = rows[0];
								$.prompt({
									state0: {
										title: "Warning:",
										html: '<center>Are you sure to create a new case like this one?</center>',
										buttons: { "Yes": true, "No": false },
										submit: function (e, v, m, f) {
											if (v) {
												$.get(_contextRoot + "/rule/r33mc/list/form/api/copy_case/" + rd.ruleCaseId, function (data) {
													if (data.status == "Success") {
														tableRuleR33mcs.ajax.reload();
														$.prompt("Copyed case created successful.");
													} else {
														$.prompt("Copying case failed.");
													}
												});
											}
										}
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
		}},
		{ text: 'Export', action: function (e, dt, node, config) {
			window.open(_contextRoot + '/rule/r33mc/download/excel', '_blank', 'location=yes,height=360,width=480,scrollbars=yes,status=yes');
		}}
	]);
	tableRuleR33mcs.buttons().container().prependTo($('div.fg-toolbar:eq(0)', tableRuleR33mcs.table().container()));

	editorRuleR33mcs.on('preSubmit', function (e, o, action) {
		if (action == 'edit') {
			var ruleCaseName = this.field('ruleCaseName');
			var ruleCaseOwner = this.field('ruleCaseOwner');
			var ruleCaseDescription = this.field('ruleCaseDescription');
			var ruleCaseSourceTable = this.field('ruleCaseSourceTable');
			var ruleCaseSourceField = this.field('ruleCaseSourceField');
			var ruleCaseTargetTable = this.field('ruleCaseTargetTable');
			var ruleCaseTargetField = this.field('ruleCaseTargetField');
			// Only validate user input values - different values indicate that the end user has not entered a value
			if (!ruleCaseName.isMultiValue()) {
				if (!ruleCaseName.val()) {
					ruleCaseName.error('Case name is required.');
					return false;
				}
				if (ruleCaseName.val().length >= 300) {
					ruleCaseName.error('Length of case name required less than 300 chars.');
					return false;
				}
			}
			if (!ruleCaseOwner.isMultiValue()) {
				if (!ruleCaseOwner.val()) {
					ruleCaseOwner.error('Case owner is required.');
					return false;
				}
				if (ruleCaseOwner.val().length >= 50) {
					ruleCaseOwner.error('Length of case owner required less than 50 chars.');
					return false;
				}
			}
			if (!ruleCaseDescription.isMultiValue()) {
				if (!ruleCaseDescription.val()) {
					ruleCaseDescription.error('Case description is required.');
					return false;
				}
				if (ruleCaseDescription.val().length >= 500) {
					ruleCaseDescription.error('Length of case description required less than 500 chars.');
					return false;
				}
			}
			if (!ruleCaseSourceTable.isMultiValue()) {
				if (!ruleCaseSourceTable.val()) {
					ruleCaseSourceTable.error('Source table is required.');
					return false;
				}
				if (ruleCaseSourceTable.val().length >= 200) {
					ruleCaseSourceTable.error('Length of source table required less than 200 chars.');
					return false;
				}
			}
			if (!ruleCaseSourceField.isMultiValue()) {
				if (!ruleCaseSourceField.val()) {
					ruleCaseSourceField.error('Source field is required.');
					return false;
				}
				if (ruleCaseSourceField.val().length >= 200) {
					ruleCaseSourceField.error('Length of source field required less than 200 chars.');
					return false;
				}
			}
			if (!ruleCaseTargetTable.isMultiValue()) {
				if (!ruleCaseTargetTable.val()) {
					ruleCaseTargetTable.error('Target table is required.');
					return false;
				}
				if (ruleCaseTargetTable.val().length >= 200) {
					ruleCaseTargetTable.error('Length of target table required less than 200 chars.');
					return false;
				}
			}
			if (!ruleCaseTargetField.isMultiValue()) {
				if (!ruleCaseTargetField.val()) {
					ruleCaseTargetField.error('Target field is required.');
					return false;
				}
				if (ruleCaseTargetField.val().length >= 200) {
					ruleCaseTargetField.error('Length of target field required less than 200 chars.');
					return false;
				}
			}
			return true;
		}
	});

	editorRuleR33mcs.on('postSubmit', function (e, json, data, action) {
		if (json.error) {
			alert('[system error] ::=> ' + json.error);	
		} else {
			var flag = (json.data[0].ruleCaseId == 0);
			var errMsg = json.data[0].ruleCaseName;
			if (flag) {
				if (action === 'edit' || action === 'remove') {
					updateCaseOwnersAndFiltersFresh();
				}
			} else {
				updateCaseOwnersAndFiltersFresh();
				$.prompt("Case updated failed.<br>Please check details of connection, db, table and field.<br><br>" + errMsg);
			}
		}
	});

	var rowdata = null;

	$('#tbl-rule-r33mc').on('click', 'a.rc_r33mc_history', function (e) {
		rowdata = tableRuleR33mcs.row($(this).closest('tr')).data();
		$('#frmRunHistoryModel').modal();
	});

	var tableRcRh = $('#tbl-rc-rh').DataTable({
		scrollX: true,
		scrollY: "350px",
		searching: false,
		lengthChange: true,
		lengthMenu: [ [ -1 ], [ "all" ] ],
		pagingType: "full_numbers",
		select: { style: "single" },
		ordering: false,
		columns: [
			{ data: "ruleCaseRunId" }, 
			{ data: "ruleCaseRunStartDateStr" }, 
			{ data: "ruleCaseRunEndDateStr" }, 
			{ data: "ruleCaseRunResult" }, 
			{ data: "ruleCaseSourceMeasureValue" }, 
			{ data: "ruleCaseTargetMeasureValue" }, 
			{ data: "ruleCaseDiffPcnt", render: $.fn.dataTable.render.number(',', '.', 2, '', '%') }, 
			{ data: "ruleCaseTolerance", render: $.fn.dataTable.render.number(',', '.', 2, '', '%') }
		],
		rowCallback : function (row, data, displayIndex, displayIndexFull) {
			$(row).children().each(function (index, td) {
				switch (index) {
					case 3:
						if (data.ruleCaseRunResult == "PASSED") {
							$(td).html("<font color='blue'><b>" + $(td).html() + "</font></b>");
						} else {
							$(td).html("<font color='red'><b>" + $(td).html() + "</font></b>");
						}
						break;
					default:
						break;
				}
			});
			return row;
		}
	});

	$("#frmRunHistoryModel").on("shown.bs.modal", function () {
		tableRcRh.clear();
		tableRcRh.rows.add(rowdata.runHistories);
		tableRcRh.draw();
	});
});