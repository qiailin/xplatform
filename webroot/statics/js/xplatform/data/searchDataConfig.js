Ext.onReady(function() {
	// tips
	Ext.QuickTips.init();

	var flagStore = new Ext.data.SimpleStore({
				fields : ['itemId', 'itemName'],
				data : [['', '����״̬'], ['U', '����'], ['D', '����']]
			});

	flag = new Ext.form.ComboBox({
				id : 'flag',
				store : flagStore,
				mode : 'local',
				displayField : 'itemName',
				valueField : 'itemId',
				editable : false,
				triggerAction : 'all',
				applyTo : 'flag'
			});

	flag.setValue('');

	var dbTable = new Ext.plugins.DBTableSelector({
		url : appUrl
				+ '/dict/dictAction!getDictJsonList.htm?dictTypeValue=db_table',
		triggerAction : 'power',
		searchable : true,
		displayField : 'itemValue',
		hiddenField : {
			itemId : 'itemId'
		}
	});

	dbTable.editor.applyToMarkup('itemValue');

	var Item = Ext.data.Record.create([{
				name : 'dataConfigId',
				type : 'long'
			}, {
				name : 'flag',
				type : 'string'
			}, {
				name : 'createDate',
				type : 'date'
			}, {
				name : 'modifyDate',
				type : 'date'
			}, {
				name : 'userName',
				type : 'string'
			}, {
				name : 'tableName',
				type : 'string'
			}, {
				name : 'sequenceValue',
				type : 'string'
			}, {
				name : 'primaryKey',
				type : 'string'
			}]);

	store = new Ext.data.Store({
				url : appUrl
						+ '/data/dataConfigAction!getDataConfigJsonList.htm',
				reader : new Ext.data.SimpleJsonReader({
							id : 'dataConfigId'
						}, Item),
				remoteSort : true
			});

	var sm = new Ext.grid.CheckboxSelectionModel();

	var cm = new Ext.grid.ColumnModel([sm, {
				header : "���ñ��",
				dataIndex : 'dataConfigId',
				width : 70,
				sortable : true,
				align : 'center'
			}, {
				header : "����",
				dataIndex : 'tableName',
				width : 120,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "primaryKey",
				dataIndex : 'primaryKey',
				width : 120,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "sequence",
				dataIndex : 'sequenceValue',
				width : 120,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "������",
				dataIndex : 'userName',
				width : 80,
				sortable : false,
				align : 'center',
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
						return "<p style='color:green'>����</p>";
					} else {
						return "<p style='color:red'>����</p>";
					}
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
			}]);

	grid = new Ext.grid.EditorGridPanel({
				store : store,
				sm : sm,
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

	store.setDefaultSort('dataConfigId', 'DESC');

	search();

	$('#hideFrame').bind('load', promgtMsg);
});

function search() {
	store.baseParams.itemId = encodeURIComponent($('#itemId').val());
	store.baseParams.flag = encodeURIComponent(flag.getValue());

	store.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});
}

function add() {
	if ($('#itemId').val() == '') {
		warn('��ѡ��������Ա��Ӧ����');
		return;
	}

	var itemId = $('#itemId').val();
	var WWidth = 660;
	var WHeight = 430;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window
			.open(
					appUrl
							+ "/data/dataConfigAction!createAuthorizePrepare.htm?itemId="
							+ itemId, "winSub2", "left=" + WLeft + ",top="
							+ WTop + ",width=" + WWidth + ",height=" + WHeight
							+ ",toolbar=no,rolebar=no,scrollbars=yes");

}

function del() {
	Ext.Msg.confirm("��ʾ", "ȷ������ȡ����Ȩ��", function(button) {
				if (button == 'yes') {
					createShadeDiv();
					var rows = grid.getSelectionModel().getSelections();
					if (rows == "") {
						Ext.Msg.show({
									title : '��ʾ',
									msg : '�빴ѡҪɾ������',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								});
						closeShadeDiv();
						return;
					}
					var params = [];
					Ext.each(rows, function(value, index, a) {
								params.push({
											"dataConfigId" : value
													.get("dataConfigId")
										});
							});
					document.getElementById("dataConfigList").value = Ext.util.JSON
							.encode(params);
					var form = window.document.forms[0];
					form.action = appUrl
							+ "/data/dataConfigAction!cancelAuthorize.htm";
					form.target = "hideFrame";
					form.submit();
				}
			});
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
							search();
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