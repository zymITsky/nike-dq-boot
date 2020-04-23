
var R33mcViewModel = function () {
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
						url:  _contextRoot + '/rule/r33mc/new/form/api/is_name_duplicated/' + self.txtRuleCaseName(),
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
	self.txtRuleCaseOwner = ko.observable().extend({ required: true, minLength: 1, maxLength: 50 });
	self.txtRuleCaseBusinessFunction = ko.observable();
	self.bfOptions = ko.observableArray();
	self.getBfs = function () {
		$.get(_contextRoot + "/rule/api/bf/all", function (data) {
			var observableData = ko.mapping.fromJS(data);
			var array = observableData();
			self.bfOptions(array);
		});
	};
	self.txtRuleCaseDescription = ko.observable().extend({ required: true, minLength: 1, maxLength: 500 });
	self.connectionOptions = ko.observableArray();
	self.getConnections = function () {
		$.get(_contextRoot + "/ds/connection/api/all", function (data) {
			var observableData = ko.mapping.fromJS(data);
			var array = observableData();
			self.connectionOptions(array);
		});
	};
	self.selRuleCaseConnectionSrc = ko.observable();
	self.txtRuleCaseSourceTable = ko.observable().extend({ required: true, minLength: 1, maxLength: 200 });
	self.txtRuleCaseSourceField = ko.observable().extend({ required: true, minLength: 1, maxLength: 200 });
	self.txtRuleCaseSourceConditionField = ko.observable();
	self.selRuleCaseConnectionTgt = ko.observable();
	self.txtRuleCaseTargetTable = ko.observable().extend({ required: true, minLength: 1, maxLength: 200 });
	self.txtRuleCaseTargetField = ko.observable().extend({ required: true, minLength: 1, maxLength: 200 });
	self.txtRuleCaseTargetConditionField = ko.observable();
	self.errors = ko.validation.group(this);
	self.submit = function () {
		if (self.errors().length == 0) {
			$.ajax({
				url:  _contextRoot + '/rule/r33mc/new/form/api/new_case',
				type: 'POST',
				data: ko.toJSON(self),
				contentType: 'application/json;charset=utf-8',
				success: function (data) {
					if (data.status == "Success") {
						window.location.replace(_contextRoot + "/rule/r33mc/list/form");
					} else {
						$.prompt("Case created failed.<br>Please check details of connection, db, table, field and condition field.<br><br>" + data.message);
					}
				},
				error: function (e) {
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
	var vmR33mc = new R33mcViewModel();
	ko.applyBindings(vmR33mc, $('#frmR33mc').get(0));
	vmR33mc.getConnections();
	vmR33mc.getBfs();
});