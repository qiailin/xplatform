Ext.onReady(function() {
			// tips
			Ext.QuickTips.init();

			var dbTable = new Ext.plugins.DBTableSelector({
						url : appUrl
								+ '/data/dataLogAction!getDBTableJsonList.htm',
						triggerAction : 'power',
						searchable : true,
						displayField : 'itemValue',
						hiddenField : {
							itemId : 'itemId'
						}
					});

			dbTable.editor.applyToMarkup('itemValue');

			var Item = Ext.data.Record.create([{
						name : 'dataLogTotalId',
						type : 'long'
					}, {
						name : 'dataConfigId',
						type : 'long'
					}, {
						name : 'createDate',
						type : 'date'
					}, {
						name : 'tableName',
						type : 'string'
					}, {
						name : 'flag',
						type : 'string'
					}, {
						name : 'modifyDate',
						type : 'date'
					}, {
						name : 'total',
						type : 'int'
					}]);

			store = new Ext.data.Store({
						url : appUrl
								+ '/data/dataLogAction!getDataLogJsonList.htm',
						reader : new Ext.data.SimpleJsonReader({
									id : 'dataLogId'
								}, Item),
						remoteSort : true
					});

			var cm = new Ext.grid.ColumnModel([{
						header : "��־���",
						dataIndex : 'dataLogTotalId',
						width : 70,
						sortable : true,
						align : 'center'
					}, {
						header : "���ñ��",
						dataIndex : 'dataConfigId',
						width : 70,
						sortable : false,
						align : 'center'
					}, {
						header : "����",
						dataIndex : 'tableName',
						width : 150,
						sortable : false,
						align : 'left',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "״̬",
						dataIndex : 'flag',
						width : 50,
						sortable : false,
						align : 'center',
						renderer : function(v, p) {
							if ('U' == v) {
								return "<p style='color:green'>��Ч</p>";
							} else {
								return "<p style='color:red'>ɾ��</p>";
							}
						}
					}, {
						header : "�������",
						dataIndex : 'total',
						width : 70,
						sortable : false,
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "����ʱ��",
						dataIndex : 'createDate',
						width : 100,
						sortable : false,
						align : 'center',
						renderer : function(v, p) {
							v = Ext.util.Format.date(v.toUTC(), 'Y-m-d H:i:s');
							p.attr = 'ext:qtip="' + v + '"';
							return v;
						}
					}, {
						header : "�޸�ʱ��",
						dataIndex : 'modifyDate',
						width : 100,
						sortable : false,
						align : 'center',
						renderer : function(v, p) {
							v = Ext.util.Format.date(v.toUTC(), 'Y-m-d H:i:s');
							p.attr = 'ext:qtip="' + v + '"';
							return v;
						}
					}, {
						header : "����",
						width : 70,
						sortable : false,
						align : 'center',
						renderer : function(value, cellmeta, record, rowIndex,
								columnIndex, store) {
							if (record.get('flag') == 'D') {
								return "�����Ԥ��";
							} else {
								return "<a href=javascript:searchDataPreview('"
										+ record.get('dataLogTotalId')
										+ "') >���Ԥ�� </a>";
							}
						}
					}]);

			grid = new Ext.grid.EditorGridPanel({
						store : store,
						cm : cm,
						renderTo : 'gridList',
						height : height,
						loadMask : true,
						enableHdMenu : false,
						viewConfig : {
							forceFit : true
						},
						stripeRows : true,
						columnLines : true,
						plugins : [new Ext.plugins.HeaderAligning()],
						bbar : new Ext.PagingToolbar({
									pageSize : pageSize,
									store : store,
									displayInfo : true,
									displayMsg : '�� {2} ����¼����ǰ��ʾ {0} - {1}',
									emptyMsg : "û���ҵ���¼��"
								})
					});

			store.setDefaultSort('dataLogTotalId', 'DESC');

			$('#hideFrame').bind('load', promgtMsg);
		});

function searchDataPreview(id) {
	var id = encodeURIComponent(id);
	var WWidth = 800;
	var WHeight = 420;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window
			.open(
					appUrl
							+ "/data/dataManageAction!searchDataPreview.htm?dataLogTotalId="
							+ id, "winSub", "left=" + WLeft + ",top=" + WTop
							+ ",width=" + WWidth + ",height=" + WHeight
							+ ",toolbar=no,rolebar=no,scrollbars=yes");
}

function search() {
	if ($('#itemId').val() == '') {
		warn('��ѡ���ѯ����');
		return;
	}

	store.baseParams.dataConfigId = encodeURIComponent($('#itemId').val());

	store.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});
}

function exportDataTemplate() {
	if ($('#itemId').val() == '') {
		warn('��ѡ�������');
		return;
	}

	var form = window.document.forms[0];
	form.action = "dataManageAction!exportDataTemplate.htm";
	form.target = "hideFrame";
	form.submit();
}

function importData() {
	if ($('#itemId').val() == '') {
		warn('��ѡ���ѯ����');
		return;
	}

	Ext.getBody().mask();

	fp = new Ext.FormPanel({
		fileUpload : true,
		width : 280,
		frame : true,
		autoHeight : true,
		bodyStyle : 'padding: 10px 10px 0 10px;',
		labelWidth : 35,
		defaults : {
			anchor : '95%',
			allowBlank : false,
			msgTarget : 'side'
		},
		items : [{
					xtype : 'fileuploadfield',
					id : 'form-file',
					emptyText : '��ѡ��excel�ļ�',
					fieldLabel : '�ļ�',
					name : 'upload',
					buttonText : '���'
				}],
		buttons : [{
			text : '����',
			handler : function() {
				Ext.Msg.confirm("��ʾ", "ȷ���ύ��", function(button) {
					if (button == 'yes') {
						if (fp.getForm().isValid()) {
							fp.getForm().submit({
										url : 'dataManageAction!importData.htm',
										params : {
											dataConfigId : $('#itemId').val()
										},
										waitMsg : '�����ύ�ļ�...',
										success : function(form, action) {
											try {
												Ext.Msg.show({
															title : '��Ϣ',
															msg : action.result.msg,
															buttons : Ext.Msg.OK,
															fn : function(btn) {
																if (btn == 'ok') {
																	search();
																}
															},
															icon : Ext.Msg.INFO
														});
												win.close();
												Ext.getBody().unmask();
											} catch (err) {
											}
										},
										failure : function(form, action) {
											Ext.Msg.show({
														title : '��Ϣ',
														msg : action.result.msg,
														buttons : Ext.Msg.OK,
														icon : Ext.Msg.ERROR
													});
										}
									});
						}
					}
				});
			}
		}, {
			text : '����',
			handler : function() {
				fp.getForm().reset();
			}
		}, {
			text : 'ȡ��',
			handler : function() {
				win.close();
				Ext.getBody().unmask();
			}
		}]
	});

	var win = new Ext.Window({
				title : "�ļ��ϴ�",
				width : 300,
				items : [fp],
				listeners : {
					"close" : function() {
						Ext.getBody().unmask();
					}
				}

			});

	win.show();
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
					msg : successResult,
					buttons : Ext.Msg.OK,
					fn : function(btn) {
						if (btn == 'ok') {
							closeShadeDiv();
							window.opener.search();
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

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
}