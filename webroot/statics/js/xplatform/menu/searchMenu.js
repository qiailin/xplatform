Ext.onReady(function() {
	// tips
	Ext.QuickTips.init();

	idField = new Ext.form.NumberField({
				id : "id",
				applyTo : 'id'
			});

	pidField = new Ext.form.NumberField({
				id : "pid",
				applyTo : 'pid'
			});

	nameField = new Ext.form.TextField({
				id : "name",
				applyTo : 'name'
			});

	pnameField = new Ext.form.TextField({
				id : "pname",
				applyTo : 'pname'
			});

	var Item = Ext.data.Record.create([{
				name : 'id',
				type : 'long'
			}, {
				name : 'pid',
				type : 'long'
			}, {
				name : 'name',
				type : 'string'
			}, {
				name : 'url',
				type : 'string'
			}, {
				name : 'target',
				type : 'string'
			}, {
				name : 'redirectUrl',
				type : 'string'
			}]);

	store = new Ext.data.Store({
				url : appUrl + '/menu/menuAction!getMenuJsonList.htm',
				reader : new Ext.data.SimpleJsonReader({
							id : 'id'
						}, Item),
				remoteSort : true
			});

	var sm = new Ext.grid.CheckboxSelectionModel();

	var cm = new Ext.grid.ColumnModel([sm, {
				header : "�˵����",
				dataIndex : 'id',
				width : 70,
				sortable : true,
				align : 'center'
			}, {
				header : "�ϼ��˵����",
				dataIndex : 'pid',
				width : 90,
				sortable : true,
				align : 'center'
			}, {
				header : "�˵����",
				dataIndex : 'name',
				width : 150,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "�˵���ַ",
				dataIndex : 'url',
				width : 220,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "��project��ת��ַ",
				dataIndex : 'redirectUrl',
				width : 150,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "�˵�Ŀ��",
				dataIndex : 'target',
				width : 70,
				sortable : false,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				xtype : 'actioncolumn',
				header : "����",
				width : 60,
				align : 'center',
				items : [{
							tooltip : '�޸�',
							icon : imgUrl + '/image/actions/icon_edit.gif',
							handler : function(grid, rowIndex, colIndex) {
								createShadeDiv();
								var rec = store.getAt(rowIndex);

								updateMenu(rec.get("id"));
							}
						}, '', '', '', '', '', {
							tooltip : '�鿴��ɫ',
							icon : imgUrl + '/image/actions/icon_note.gif',
							handler : function(grid, rowIndex, colIndex) {
								var rec = store.getAt(rowIndex);
								var WWidth = 600;
								var WHeight = 480;
								var WLeft = Math
										.ceil((window.screen.width - WWidth)
												/ 2);
								var WTop = Math
										.ceil((window.screen.height - WHeight)
												/ 2);

								var url = appUrl
										+ "/role/roleAction!searchRole4Config.htm?menuId="
										+ rec.get('id');
								window
										.open(
												url,
												"win",
												"left="
														+ WLeft
														+ ",top="
														+ WTop
														+ ",width="
														+ WWidth
														+ ",height="
														+ WHeight
														+ ",toolbar=no,menubar=no,scrollbars=no");
							}

						}]
			}]);

	grid = new Ext.grid.EditorGridPanel({
				store : store,
				cm : cm,
				sm : sm,
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

	store.setDefaultSort('id', 'DESC');
	search();

	$('#hideFrame').bind('load', promgtMsg);

});

function search() {
	store.baseParams.id = encodeURIComponent(idField.getValue());
	store.baseParams.pid = encodeURIComponent(pidField.getValue());
	store.baseParams.name = encodeURIComponent(nameField.getValue());
	store.baseParams.pname = encodeURIComponent(pnameField.getValue());

	store.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

}

function createMenu() {
	var WWidth = 800;
	var WHeight = 400;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window.open(appUrl + "/menu/menuAction!createMenuPrepare.htm", "winSub",
			"left=" + WLeft + ",top=" + WTop + ",width=" + WWidth + ",height="
					+ WHeight + ",toolbar=no,menubar=no,scrollbars=yes");
}

function updateMenu(id) {
	createShadeDiv();
	var WWidth = 800;
	var WHeight = 400;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window.open(appUrl + "/menu/menuAction!updateMenuPrepare.htm?id=" + id,
			"winSub", "left=" + WLeft + ",top=" + WTop + ",width=" + WWidth
					+ ",height=" + WHeight
					+ ",toolbar=no,menubar=no,scrollbars=yes");

}

function deleteMenu() {

	Ext.Msg.confirm("��ʾ", "ȷ������ɾ��˵���", function(button) {
				if (button == 'yes') {
					createShadeDiv();
					var rows = grid.getSelectionModel().getSelections();

					if (rows == "") {
						Ext.Msg.show({
									title : '��ʾ',
									msg : '�빴ѡҪɾ��˵������',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.ERROR
								});
						closeShadeDiv();
						return;
					}

					var params = [];
					Ext.each(rows, function(value, index, a) {
								params.push({
											"id" : value.get("id")
										});
							});

					document.getElementById("menuList").value = Ext.util.JSON
							.encode(params);

					var form = window.document.forms[0];
					form.action = appUrl + "/menu/menuAction!deleteMenu.htm";
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

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
}
