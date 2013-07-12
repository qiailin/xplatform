Ext.onReady(function() {
			Ext.QuickTips.init();

			new Ext.form.TextField({
						id : "sql_monitor_title",
						allowBlank : false,
						width : 300,
						height : 20,
						applyTo : 'sql_monitor_title'
					});
			var freqStore = new Ext.data.SimpleStore({
						fields : ['itemId', 'itemName'],
						data : [['15', '15分钟一次'], ['30', '30分钟一次'],
								['60', '60分钟一次']]
					});

			var freq = new Ext.form.ComboBox({
						id : 'freq',
						store : freqStore,
						mode : 'local',
						displayField : 'itemName',
						valueField : 'itemId',
						hiddenName : 'sqlMonitor.freq',
						editable : false,
						triggerAction : 'all',
						applyTo : 'freq'
					});

			new Ext.form.NumberField({
						id : "threshold",
						allowBlank : false,
						applyTo : 'threshold'
					});
			var logStore = new Ext.data.SimpleStore({
						fields : ['itemId', 'itemName'],
						data : [['Y', '记录'], ['N', '不记录']]
					});

			var log = new Ext.form.ComboBox({
						id : 'log',
						store : logStore,
						mode : 'local',
						displayField : 'itemName',
						valueField : 'itemId',
						hiddenName : 'sqlMonitor.log',
						editable : false,
						triggerAction : 'all',
						applyTo : 'log'
					});

			new Ext.form.TextField({
						id : "sms_alarm",
						width : 300,
						applyTo : 'sms_alarm'
					});

			new Ext.form.TextField({
						id : "email_alarm",
						width : 300,
						applyTo : 'email_alarm'
					});

			new Ext.form.TextArea({
						id : "sql",
						allowBlank : false,
						width : 350,
						height : 100
					}).applyToMarkup('sql');

			new Ext.form.TextArea({
						id : "sql_detail",
						width : 350,
						height : 100
					}).applyToMarkup('sql_detail');
			freq.setValue('15');
			log.setValue('N');
			$('#hideFrame').bind('load', promgtMsg);

		});

function save() {
	var p = Ext.getCmp("sql_monitor_title").validate();
	var n = Ext.getCmp("threshold").validate();
	if (!(p && n)) {
		return;
	}
	createShadeDiv();

	var form = window.document.forms[0];
	form.action = "sqlMonitorAction!createSqlMonitor.htm";
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		Ext.Msg.show({
					title : '错误',
					msg : failResult,
					buttons : Ext.Msg.OK,
					fn : function(btn) {
						if (btn == 'ok') {
							closeShadeDiv();
						}
					},
					icon : Ext.Msg.ERROR
				});
	} else {
		Ext.Msg.show({
					title : '信息',
					msg : "操作成功！",
					buttons : Ext.Msg.OK,
					fn : function(btn) {
						if (btn == 'ok') {
							window.close();
							window.parent.opener.search();
						}
					},
					icon : Ext.Msg.INFO
				});
	}
}

function warn(msg) {
	Ext.Msg.show({
				title : '警告',
				msg : msg,
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.WARNING
			});
}