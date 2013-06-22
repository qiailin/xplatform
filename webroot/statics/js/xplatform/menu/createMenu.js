Ext.onReady(function() {
			// tips
			Ext.QuickTips.init();

			new Ext.form.NumberField({
						id : "pid",
						allowBlank : false,
						applyTo : 'pid'
					});

			new Ext.form.TextField({
						id : "name",
						allowBlank : false,
						applyTo : 'name'
					});

			new Ext.form.TextField({
						id : "url",
						width : 300,
						applyTo : 'url'
					});

			new Ext.form.NumberField({
						id : "orderBy",
						allowBlank : false,
						applyTo : 'orderBy'
					});

			new Ext.form.ComboBox({
						id : 'target',
						store : ['mainRight', 'NA', '_blank'],
						allowBlank : false,
						editable : false,
						triggerAction : 'all',
						applyTo : 'target'
					});

			$('#hideFrame').bind('load', promgtMsg);

		});

function save() {

	var p = Ext.getCmp("pid").validate();
	var n = Ext.getCmp("name").validate();
	var o = Ext.getCmp("orderBy").validate();
	var t = Ext.getCmp("target").validate();

	if (!(p && n && o && t)) {
		return;
	}

	createShadeDiv();

	var form = window.document.forms[0];
	form.action = "menuAction!createMenu.htm";
	// form.target = "hideFrame";
	form.submit();

}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		Ext.Msg.show({
					title : '����',
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
					title : '��Ϣ',
					msg : "�����ɹ���",
					buttons : Ext.Msg.OK,
					fn : function(btn) {
						if (btn == 'ok') {
							closeShadeDiv();
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
				title : '����',
				msg : msg,
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.WARNING
			});
}
