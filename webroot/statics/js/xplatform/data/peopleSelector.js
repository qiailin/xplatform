Ext.onReady(function() {
			// tips
			Ext.QuickTips.init();

			var Item = Ext.data.Record.create([{
						name : 'userId',
						type : 'long'
					}, {
						name : 'userName',
						type : 'string'
					}]);

			store = new Ext.data.Store({
						url : '',
						reader : new Ext.data.SimpleJsonReader({
									id : 'userId'
								}, Item),
						remoteSort : true
					});
			var has = Ext.getDom('listJson').value;
			if (has != '') {
				store.loadData(Ext.util.JSON.decode(has), false);
			}

			authorizestore = new Ext.data.Store({
						url : '',
						reader : new Ext.data.SimpleJsonReader({
									id : 'userId'
								}, Item),
						remoteSort : true
					});

			Ext.form.Field.prototype.msgTarget = 'side';

			isForm = new Ext.form.FormPanel({
						border : false,
						height : 400,
						labelAlign : 'left',
						bodyBorder : false,
						labelWidth : 50,
						buttonAlign : 'center',
						bodyStyle : 'padding-top:10px; padding-right:50px',
						renderTo : 'itemselector',
						items : [{
									id : 'myit',
									xtype : 'itemselector',
									name : 'itemselector',
									multiselects : [{
												width : 100,
												height : 350,
												store : store,
												displayField : 'userName',
												valueField : 'userId',
												legend : 'δ��Ȩ'

											}, {
												width : 100,
												height : 350,
												store : authorizestore,
												displayField : 'userName',
												valueField : 'userId',
												legend : '����Ȩ'

											}]
								}],
						buttons : [{
							text : '����',
							handler : function() {
								if (isForm.getForm().isValid()) {
									Ext.Msg.confirm("���", "ȷ����Ȩ��",
											function(bn) {
												if (bn == 'yes') {
													createAuthorize();
												}
											});
								}
							}
						}]

					});

			$('#hideFrame').bind('load', promgtMsg);

		});

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
							window.parent.window.close();
							window.parent.opener.search();
						}
					},
					icon : Ext.Msg.INFO
				});
	}
}

function createAuthorize() {
	// isForm.getEl().mask();
	var ids = '';
	isForm.getForm().findField('itemselector').toMultiselect.store.each(
			function(record) {
				ids += record.get('userId') + ',';
			});
	var form = window.document.forms[0];
	$('#userIds').val(ids);
	form.action = appUrl + '/data/dataConfigAction!createAuthorize.htm';
	form.target = "hideFrame";
	form.submit();
}
