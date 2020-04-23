
function TblColFilter(dataTable, fltedCols, selColFlt_id, colFllr_class, fByTitle_id, frmColFltModel_id, btnColFlt_id, btnColFltCls_id) {
	var _this = this;
	_this.cols_filter_arr = new Map();
	var fltColIdx = 0;
	var fltColTitle = '';
	var fltColData = [];
	var cols_filter_sel_arr = new Map();

	var init = function () {
		$.each(fltedCols, function (key, value) {
			_this.cols_filter_arr.set(value.id, { id: value.id, title: value.title, data: [] });
			cols_filter_sel_arr.set(value.id, { id: value.id, title: value.title, data: [] });
		});
	};

	init();

	$("#" + selColFlt_id).multiselect({
		maxHeight: 450,
		buttonWidth: '100%',
		includeSelectAllOption: true,
		enableFiltering: true,
		enableCaseInsensitiveFiltering: true
	});

	$("." + colFllr_class).click(function (e) {
		e.preventDefault();
		fltColIdx = parseInt(this.id.split('_')[1]);
		fltColTitle = _this.cols_filter_arr.get(fltColIdx).title;
		fltColData = _this.cols_filter_arr.get(fltColIdx).data;
		var selectedArr = cols_filter_sel_arr.get(fltColIdx).data;
		$("#" + selColFlt_id).empty();
		$("#" + selColFlt_id).append(function () {
			var output = '';
			$.each(fltColData, function (key, value) {
				output += (selectedArr.includes(value)) ? ('<option selected="true" value="' + value + '">' + value + '</option>') : ('<option value="' + value + '">' + value + '</option>');
			});
			return output;
		});
		$("#" + selColFlt_id).multiselect('rebuild');
		$("#" + fByTitle_id).html(fltColTitle);
		$("#" + frmColFltModel_id).modal();
	});

	$("#" + btnColFlt_id).click(function (e) {
		e.preventDefault();
		var seltedColFltVals = $("#" + selColFlt_id).val();
		var allSelected = $("#" + selColFlt_id + " option:not(:selected)").length == 0;
		var colCn = dataTable.column(fltColIdx);
		var searchOptions = '';
		var arr_sel = [];
		if (seltedColFltVals != null && !allSelected) {
			$.each(seltedColFltVals, function (index, item) {
				arr_sel.push(item);
				searchOptions += ('^' + item + '$|');
			});
			searchOptions = (searchOptions != '') ? searchOptions.substring(0, searchOptions.length - 1) : searchOptions;
		} else {
			searchOptions = '';
			if (allSelected) {
				arr_sel = _this.cols_filter_arr.get(fltColIdx).data;
			}
		}
		cols_filter_sel_arr.get(fltColIdx).data = arr_sel;
		colCn.search(searchOptions, true, false).draw();
		$("#" + frmColFltModel_id).modal('hide');
	});

	$("#" + btnColFltCls_id).click(function (e) {
		e.preventDefault();
		$("#" + selColFlt_id + " option:selected").prop("selected", false);
		var colCn = dataTable.column(fltColIdx);
		cols_filter_sel_arr.get(fltColIdx).data = [];
		colCn.search('', true, false).draw();
		$("#" + frmColFltModel_id).modal('hide');
	});
}