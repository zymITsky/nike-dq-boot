
var RqscViewModel = function () {
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
						url:  _contextRoot + '/rule/qs/new/form/api/is_name_duplicated/' + self.txtRuleCaseName(),
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
	self.txtRuleCaseSql = ko.observable().extend({ required: true, minLength: 1, maxLength: 2000 });
	self.selRuleCaseConnection = ko.observable();
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
				url:  _contextRoot + '/rule/qs/new/form/api/new_case',
				type: 'POST',
				data: ko.toJSON(self),
				contentType: 'application/json;charset=utf-8',
				success: function (data) {
					if (data.status == "Success") {
						window.location.replace(_contextRoot + "/rule/qs/list/form");
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
	var vmRqsc = new RqscViewModel();
	ko.applyBindings(vmRqsc, $('#frmRqsc').get(0));
	vmRqsc.getConnections();
});