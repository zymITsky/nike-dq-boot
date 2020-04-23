
var tableRuleR21fcs = null;
var editorRuleR21fcs = null;

$(document).ready(function () {

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

	$(".r21fc-filter").multiselect({
		maxHeight: 300,
		buttonWidth: '100%',
		includeSelectAllOption: true,
		onDropdownHide: function (event) {
			refreshDtRuleR21fcsByFilters();
		}
	});

	function refreshDtRuleR21fcsByFilters() {
		var tgtConnName = $("#selTgtConnName").val();
		var bizFunc = $("#selBizFunc").val();
		var caseOwner = $("#selCaseOwner").val();
		var allSelectedCN = $("#selTgtConnName option:not(:selected)").length == 0;
		var allSelectedBF = $("#selBizFunc option:not(:selected)").length == 0;
		var allSelectedCO = $("#selCaseOwner option:not(:selected)").length == 0;
		if ((tgtConnName == null || allSelectedCN) && (bizFunc == null || allSelectedBF) && (caseOwner == null || allSelectedCO)) {
			tableRuleR21fcs.ajax.url(_contextRoot + "/rule/r21fc/list/form/api/ruler21fcs").load();
		} else {
			if (tgtConnName == null || allSelectedCN) {
				tgtConnName = "-";
			}
			if (bizFunc == null || allSelectedBF) {
				bizFunc = "-";
			}
			if (caseOwner == null || allSelectedCO) {
				caseOwner = "-";
			}
			tableRuleR21fcs.ajax.url(_contextRoot + "/rule/r21fc/list/form/api/ruler21fcs_filter?tgtConnName=" + tgtConnName + "&bizFunc=" + bizFunc + "&caseOwner=" + caseOwner).load();
		}
	}

	function updateCaseOwnersAndFiltersFresh() {
		$.get(_contextRoot + "/rule/r21fc/caseowner/api/all", function (data) {
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
			refreshDtRuleR21fcsByFilters();
		});
	}

	editorRuleR21fcs = new $.fn.dataTable.Editor({
		ajax: _contextRoot + "/rule/r21fc/list/form/api/ruler21fcs",
		table: "#tbl-rule-r21fc",
		fields: [
			{ label: "Case name", name: "ruleCaseName" }, 
		    { label: "Case owner", name: "ruleCaseOwner" }, 
		    { label: "Business function", name: "ruleCaseBusinessFunction", type: "select", options: listOfBizFunc }, 
		    { label: "Case description", name: "ruleCaseDescription" }, 
		    { label: "Target connection name", name: "ruleCaseTargetConnectionName", type: "select", options: listOfDataSource }, 
		    { label: "Target table", name: "ruleCaseTargetTable" }, 
		    { label: "Target field", name: "ruleCaseTargetField" },
		    { label: "Target condition field", name: "ruleCaseTargetConditionField" }
		]
	});

	tableRuleR21fcs = $('#tbl-rule-r21fc').DataTable({
		scrollX: true,
		scrollY: "850px",
		searching: true,
		lengthChange: false,
		select: { style: "single" },
		ordering: false,
		display: "envelope",
		ajax: _contextRoot + "/rule/r21fc/list/form/api/ruler21fcs",
		columns: [
			{ data: "ruleCaseId" }, 
			{ data: "ruleCaseName" }, 
			{ data: "ruleCaseOwner" }, 
			{ data: "ruleCaseBusinessFunction" }, 
			{ data: null, render: function (data, type, row) {
				return (data.ruleCaseDescription.length > 50) ? (data.ruleCaseDescription.substr(0, 50) + " ...") : data.ruleCaseDescription;
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
				return '<center><a href="javascript:;" class="rc_r21fc_history">HISTORY</a></center>';
			}}, 
			{ data: "lastRunResult" }
		],
		rowCallback : function (row, data, displayIndex, displayIndexFull) {
			$(row).children().each(function (index, td) {
				switch (index) {
					case 12:
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
		, initComplete: function () {
			this.api().columns().every(function (i) {
				var column = this;
				column.data().unique().sort().each(function (d, j) {
					if (r21TCF.cols_filter_arr.get(i) !== undefined) {
						r21TCF.cols_filter_arr.get(i).data.push(d);
					}
				});
			});
		}
	});

	var r21TCF = new TblColFilter(tableRuleR21fcs,
			[{id: 2, title: 'Case owner'}, {id: 3, title: 'Business function'}, {id: 5, title: 'Target connection name'}],
			"selColFlt", "col-fllr", "fByTitle", "frmColFltModel", "btnColFlt", "btnColFltCls");

	new $.fn.dataTable.Buttons(tableRuleR21fcs, [
		{ extend: "edit", editor: editorRuleR21fcs },
		{ extend: "remove", editor: editorRuleR21fcs },
		{ text: 'Run all', action: function (e, dt, node, config) {
			$.prompt({
				state0: {
					title: "Warning:",
					html: '<center>Are you sure to run all the cases?</center>',
					buttons: { "Yes": true, "No": false },
					submit: function (e, v, m, f) {
						if (v) {
							$.get(_contextRoot + "/rule/r21fc/list/form/api/run_batch/all", function (data) {
								if (data.status == "Success") {
									tableRuleR21fcs.ajax.reload();
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
							var count = tableRuleR21fcs.rows({ selected: true }).count();
							var rows = tableRuleR21fcs.rows({ selected: true }).data();
							if (count != 0) {
								for (var i = 0; i < count; i++) {
									ids += (rows[i].ruleCaseId + "-");
								}
								$.get(_contextRoot + "/rule/r21fc/list/form/api/run_batch/" + ids, function (data) {
									if (data.status == "Success") {
										tableRuleR21fcs.ajax.reload();
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
							var count = tableRuleR21fcs.rows({ selected: true }).count();
							var rows = tableRuleR21fcs.rows({ selected: true }).data();
							if (count != 0) {
								var rd = rows[0];
								$.prompt({
									state0: {
										title: "Warning:",
										html: '<center>Are you sure to create a new case like this one?</center>',
										buttons: { "Yes": true, "No": false },
										submit: function (e, v, m, f) {
											if (v) {
												$.get(_contextRoot + "/rule/r21fc/list/form/api/copy_case/" + rd.ruleCaseId, function (data) {
													if (data.status == "Success") {
														tableRuleR21fcs.ajax.reload();
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
			window.open(_contextRoot + '/rule/r21fc/download/excel', '_blank', 'location=yes,height=360,width=480,scrollbars=yes,status=yes');
		}}
	]);
	tableRuleR21fcs.buttons().container().prependTo($('div.fg-toolbar:eq(0)', tableRuleR21fcs.table().container()));

	editorRuleR21fcs.on('preSubmit', function (e, o, action) {
		if (action == 'edit') {
			var ruleCaseName = this.field('ruleCaseName');
			var ruleCaseOwner = this.field('ruleCaseOwner');
			var ruleCaseDescription = this.field('ruleCaseDescription');
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
			// If any error was reported, cancel the submission so it can be corrected
//			return (this.inError()) ? false : true;
			return true;
		}
	});

	editorRuleR21fcs.on('postSubmit', function (e, json, data, action) {
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

	$('#tbl-rule-r21fc').on('click', 'a.rc_r21fc_history', function (e) {
		rowdata = tableRuleR21fcs.row($(this).closest('tr')).data();
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
			{ data: "ruleCaseTargetMeasureValue" },
			{ data: "ruleCaseTolerance" },
			{ data: "ruleCaseDifference" }
		],
		rowCallback: function (row, data, displayIndex, displayIndexFull) {
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

	$("#frmRunHistoryModel").on("shown.bs.modal", function (e) {
		tableRcRh.clear();
		tableRcRh.rows.add(rowdata.runHistories);
		tableRcRh.draw();
	});

//	$("#frmRunHistoryModel").on("hidden.bs.modal", function () {
//		$(this).removeData("bs.modal");
//		console.log("frmRunHistoryModel's removeData is called ...");
//	});

});