Ext.onReady(function() {
			// tips
			Ext.QuickTips.init();

			new Ext.form.NumberField({
						id : "id",
						allowBlank : false,
						readOnly : true,
						applyTo : 'id'
					});

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
						value : targetValue,
						triggerAction : 'all',
						applyTo : 'target'
					});

			$('#hideFrame').bind('load', promgtMsg);

		});

function save() {

	var i = Ext.getCmp("id").validate();
	var p = Ext.getCmp("pid").validate();
	var n = Ext.getCmp("name").validate();
	var o = Ext.getCmp("orderBy").validate();
	var t = Ext.getCmp("target").validate();

	if (!(i && p && n && o && t)) {
		return;
	}

	createShadeDiv();

	var form = window.document.forms[0];
	form.action = "menuAction!updateMenu.htm";
	// form.target = "hideFrame";
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
				title : '警告',
				msg : msg,
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.WARNING
			});
}