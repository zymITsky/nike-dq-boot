
var RtccViewModel = function () {
	var self = this;
	self.txtRuleCaseName = ko.observable().extend({ required: true, minLength: 1, maxLength: 300 });
	self.readyForFocused = false;
	self.txtRuleCaseName.focused = ko.observable();
	self.txtRuleCaseName.focused.subscribe(function (newValue) {
		if (!newValue) {
			if (!self.readyForFocused) {
				self.readyForFocused = true;
			} else {
				if (self.txtRuleCaseName() != null && self.txtRuleCaseName() != '') {
					$.ajax({
						url:  _contextRoot + '/rule/tc/new/form/api/is_name_duplicated/' + self.txtRuleCaseName(),
						type: 'GET',
						contentType: 'application/json;charset=utf-8',
						beforeSend: function () {
							$("#ajaxloader1").show();
						},
						success: function (data) {
							if (data.status != "Success") {
								alert("RuleCaseName already exist.");
								self.txtRuleCaseName("");
							}
							$("#ajaxloader1").hide();
						},
						error: function (e) {
							alert("error - " + error.status);
						}
					});
				}
			}
		}
	});
	self.txtRuleCaseDescription = ko.observable().extend({ required: true, minLength: 1, maxLength: 500 });
	self.txtRuleCaseTargetDb = ko.observable().extend({ required: true, minLength: 1, maxLength: 200 });
	self.txtRuleCaseTargetTable = ko.observable().extend({ required: true, minLength: 1, maxLength: 200 });
	self.txtRuleCaseTargetTable.focused = ko.observable();
	self.txtRuleCaseTargetTable.focused.subscribe(function (newValue) {
		if (!newValue) {
			if (!self.readyForFocused) {
				self.readyForFocused = true;
			} else {
				if (self.txtRuleCaseTargetTable() != null && self.txtRuleCaseTargetTable() != '') {
					$.ajax({
						url:  _contextRoot + '/rule/tc/new/form/api/is_table_exist/' + self.txtRuleCaseTargetTable(),
						type: 'GET',
						contentType: 'application/json;charset=utf-8',
						beforeSend: function () {
							$("#ajaxloader2").show();
						},
						success: function (data) {
							if (data.status != "Success") {
								alert("TagetTable not exist.");
								self.txtRuleCaseTargetTable("");
							}
							$("#ajaxloader2").hide();
						},
						error: function (e) {
							alert("error - " + error.status);
						}
					});
				}
			}
		}
	});
	self.selRuleCaseConnection = ko.observable();
	self.txtRuleCaseOrigTblSize = ko.observable(10).extend({ required: true });;
	self.txtRuleCaseRowGapGt = ko.observable('10.0').extend({ required: true });;
	self.txtRuleCaseRowGapLt = ko.observable('10.0').extend({ required: true });;
	self.selRuleCaseSeverity = ko.observable();
	self.connectionOptions = ko.observableArray();
	self.getConnections = function () {
		$.get(_contextRoot + "/ds/connection/api/all", function (data) {
			var observableData = ko.mapping.fromJS(data);
			var array = observableData();
			self.connectionOptions(array);
		});
	};
	self.errors = ko.validation.group(this);
	self.submit = function () {
		if (self.errors().length == 0) {
			$.ajax({
				url:  _contextRoot + '/rule/tc/new/form/api/new_case',
				type: 'POST',
				data: ko.toJSON(self),
				contentType: 'application/json;charset=utf-8',
				success: function (data) {
					if (data.status == "Success") {
						window.location.replace(_contextRoot + "/rule/tc/list/form");
					} else {
						alert("Error : " + data.message);
					}
				},
				error : function (e) {
					alert("error - " + error.status);
				}
			});
		} else {
			self.errors.showAllMessages();
		}
		return false;
	};
};

$(document).ready(function () {
	ko.validation.rules.pattern.message = 'Invalid.';
	ko.validation.configure({
		registerExtenders: true,
		messagesOnModified: true,
		insertMessages: true,
		parseInputAttributes: true,
		messageTemplate: null
	});
	ko.bindingHandlers.numeric = {
		init: function (element, valueAccessor) {
			$(element).on("keydown", function (event) {
				// Allow: backspace, delete, tab, escape, and enter
				if (event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 ||
					// Allow: Ctrl+A
					(event.keyCode == 65 && event.ctrlKey === true) ||
					// Allow: . ,
					(event.keyCode == 188 || event.keyCode == 190 || event.keyCode == 110) ||
					// Allow: home, end, left, right
					(event.keyCode >= 35 && event.keyCode <= 39)) {
					// let it happen, don't do anything
					return;
				} else {
					// Ensure that it is a number and stop the keypress
					if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105)) {
						event.preventDefault();
					}
				}
			});
		}
	};
	var vmRtcc = new RtccViewModel();
	ko.applyBindings(vmRtcc, $('#frmRtcc').get(0));
	vmRtcc.getConnections();
});