Ext.onReady(function() {
	// tips
	Ext.QuickTips.init();

	loadM = new Ext.LoadMask(gridList, {
		msg : '���ڲ�����̨��ݣ����Ժ�...',
		removeMask : true
			// ��ɺ��Ƴ�
		});
	// -----dictType
	var Item = Ext.data.Record.create([{
				name : 'dictTypeId',
				type : 'long'
			}, {
				name : 'parentDictTypeId',
				type : 'long'
			}, {
				name : 'dictTypeName',
				type : 'string'
			}, {
				name : 'dictTypeValue',
				type : 'string'
			}, {
				name : 'remark',
				type : 'string'
			}, {
				name : 'dictTypeState',
				type : 'string'
			}, {
				name : 'lastModify',
				type : 'Date'
			}]);

	store = new Ext.data.Store({
				url : appUrl + '/dict/dictAction!getCmsTbDictTypeJsonList.htm',
				reader : new Ext.data.SimpleJsonReader({
							id : 'dictTypeId'
						}, Item),
				remoteSort : true
			});
	var sm = new Ext.grid.CheckboxSelectionModel();

	var cm = new Ext.grid.ColumnModel([sm, {
				id : "dictTypeId",
				header : "���",
				dataIndex : 'dictTypeId',
				width : 40,
				sortable : true,
				align : 'center'
			}, {
				header : "�������",
				dataIndex : 'dictTypeName',
				width : 90,
				sortable : true,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "ֵ",
				dataIndex : 'dictTypeValue',
				width : 150,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "��ע",
				dataIndex : 'remark',
				width : 150,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}

			}, {
				header : "����޸�ʱ��",
				dataIndex : 'lastModify',
				width : 100,
				sortable : false,
				align : 'left',
				renderer : function(v) {
					return Ext.util.Format.date(v.toUTC(), 'Y-m-d');
				}
			}, {
				header : "����",
				sortable : false,
				align : 'center',
				width : 80,
				renderer : function(value, cellmeta, record, rowIndex,
						columnIndex, store) {
					var dictTypeId = record.get("dictTypeId");
					return "<a href=javascript:searchDict(" + dictTypeId
							+ ");>" + Ext.util.Format.htmlEncode('�鿴�ֵ���Ŀ')
							+ "</a>";
				}
			}, {
				header : "����",
				sortable : false,
				align : 'center',
				width : 60,
				renderer : function(value, cellmeta, record, rowIndex,
						columnIndex, store) {
					var dictTypeId = record.get("dictTypeId");
					return "<a href=javascript:modifyDictType(" + dictTypeId
							+ ");>" + Ext.util.Format.htmlEncode('�޸�') + "</a>";
				}
			}, {
				header : "����",
				sortable : false,
				align : 'center',
				width : 60,
				renderer : function(value, cellmeta, record, rowIndex,
						columnIndex, store) {
					var dictTypeId = record.get("dictTypeId");
					return "<a href=javascript:deleteDictType(" + dictTypeId
							+ ");>" + Ext.util.Format.htmlEncode('ɾ��') + "</a>";

				}
			}]);

	grid = new Ext.grid.EditorGridPanel({
				store : store,
				cm : cm,
				sm : sm,
				renderTo : 'gridList',
				height : 200,
				loadMask : true,
				enableHdMenu : false,
				stripeRows : true,
				columnLines : true,
				viewConfig : {
					forceFit : true
				},
				plugins : [new Ext.plugins.HeaderAligning()],
				bbar : new Ext.PagingToolbar({
							pageSize : pageSize,
							store : store,
							displayInfo : true,
							displayMsg : '�� {2} ����¼����ǰ��ʾ {0} - {1}',
							emptyMsg : "û���ҵ���¼��"
						})
			});

	store.setDefaultSort('dictTypeId', 'DESC');

	// -----dict

	var dictItem = Ext.data.Record.create([{
				name : 'itemId',
				type : 'long'
			}, {
				name : 'itemName',
				type : 'string'
			}, {
				name : 'itemValue',
				type : 'string'
			}, {
				name : 'parentItemId',
				type : 'long'
			}, {
				name : 'itemDescription',
				type : 'string'
			}, {
				name : 'remark',
				type : 'string'
			}, {
				name : 'lastModify',
				type : 'Date'
			}, {
				name : 'dictTypeId',
				type : 'long'
			}]);

	dictStore = new Ext.data.Store({
				url : appUrl + '/dict/dictAction!getCmsTbDictJsonList.htm',
				reader : new Ext.data.SimpleJsonReader({
							id : 'itemId'
						}, dictItem),
				remoteSort : true
			});

	var dictsm = new Ext.grid.CheckboxSelectionModel();
	var dictcm = new Ext.grid.ColumnModel([{
				id : "itemId",
				header : "���",
				dataIndex : 'itemId',
				width : 80,
				sortable : true,
				align : 'center'
			}, {
				header : "���",
				dataIndex : 'itemName',
				width : 100,
				sortable : true,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "ֵ",
				dataIndex : 'itemValue',
				width : 150,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {

				header : "����Id",
				dataIndex : 'dictTypeId',
				width : 80,
				hidden : true,
				sortable : true,
				align : 'center'
			}, {
				header : "����",
				dataIndex : 'parentItemId',
				width : 40,
				sortable : false,
				hidden : true,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}

			}, {
				header : "����",
				dataIndex : 'itemDescription',
				width : 150,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "��ע",
				dataIndex : 'remark',
				width : 150,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			},

			{
				header : "����޸�ʱ��",
				dataIndex : 'lastModify',
				width : 100,
				sortable : false,
				align : 'left',
				renderer : function(v) {
					return Ext.util.Format.date(v.toUTC(), 'Y-m-d');
				}
			}, {
				header : "����",
				sortable : false,
				align : 'center',
				width : 40,
				renderer : function(value, cellmeta, record, rowIndex,
						columnIndex, store) {
					var itemId = record.get("itemId");
					return "<a href=javascript:modifyDict(" + itemId + ");>"
							+ Ext.util.Format.htmlEncode('�޸�') + "</a>";
				}
			}, {
				header : "����",
				sortable : false,
				align : 'center',
				width : 40,
				renderer : function(value, cellmeta, record, rowIndex,
						columnIndex, store) {
					var itemId = record.get("itemId");
					return "<a href=javascript:deleteDict(" + itemId + ");>"
							+ Ext.util.Format.htmlEncode('ɾ��') + "</a>";

				}
			}]);

	dictGrid = new Ext.grid.EditorGridPanel({
				store : dictStore,
				cm : dictcm,
				sm : dictsm,
				renderTo : 'dictGridList',
				height : 200,
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
							store : dictStore,
							displayInfo : true,
							displayMsg : '�� {2} ����¼����ǰ��ʾ {0} - {1}',
							emptyMsg : "û���ҵ���¼��"
						})
			});
	dictStore.setDefaultSort('itemId', 'DESC');

	$('#hideFrame').bind('load', promgtMsg);

});

function search() {
	store.baseParams.dictTypeValue = encodeURIComponent($("#dictTypeValue")
			.val());
	store.baseParams.dictTypeName = encodeURIComponent($("#dictTypeName").val());
	store.baseParams.remark = encodeURIComponent($("#remark").val());
	store.load();
}

function searchDict(z) {
	dictStore.baseParams.dictTypeId = z;
	document.getElementById("dictTypeIdForDict").value = z;
	dictStore.load();
}

function deleteDictType(a) {
	Ext.Msg.confirm("��ʾ", "ȷ��ɾ��", function(button) {
				if (button == 'yes') {
					createShadeDiv();
					jQuery.getJSON(appUrl
									+ '/dict/dictAction!DeleteDictType.htm', {
								dictTypeId : a,
								dateTime : new Date().getTime()
							}, function(json) {
								// loadM.hide();
								if (json.result == "T") {
									Ext.Msg.show({
												title : '��ʾ',
												msg : 'ɾ��ɹ�',
												buttons : Ext.Msg.OK,
												icon : Ext.Msg.INFO
											});

									closeShadeDiv();
									search();
									searchDict('0');
								} else {
									Ext.Msg.show({
												title : '��ʾ',
												msg : json.code,
												buttons : Ext.Msg.OK,
												icon : Ext.Msg.ERROR
											});

									closeShadeDiv();
									search();
								}
							});
				}
			})
}
function deleteDict(a) {

	Ext.Msg.confirm("��ʾ", "ȷ��ɾ��", function(button) {
				if (button == 'yes') {
					createShadeDiv();
					jQuery.getJSON(appUrl + '/dict/dictAction!DeleteDict.htm',
							{
								itemId : a,
								dateTime : new Date().getTime()
							}, function(json) {
								// loadM.hide();
								if (json.result == "T") {
									Ext.Msg.show({
												title : '��ʾ',
												msg : 'ɾ��ɹ�',
												buttons : Ext.Msg.OK,
												icon : Ext.Msg.INFO
											});
									closeShadeDiv();
									searchDict($("#dictTypeIdForDict").val());
								} else {
									Ext.Msg.show({
												title : '��ʾ',
												msg : json.code,
												buttons : Ext.Msg.OK,
												icon : Ext.Msg.ERROR
											});
									closeShadeDiv();
								}
							});
				}
			})
}

function modifyDictType(a) {
	createShadeDiv();
	var form = window.document.forms[0];
	var WWidth = 400;
	var WHeight = 200;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window.open(appUrl + "/dict/dictAction!toUpdateDictType.htm?dictTypeId="
					+ a + "", "win", "left=" + WLeft + ",top=" + WTop
					+ ",width=" + WWidth + ",height=" + WHeight
					+ ",toolbar=no,menubar=no,scrollbars=yes");
}

function modifyDict(a) {
	createShadeDiv();
	var form = window.document.forms[0];
	var WWidth = 400;
	var WHeight = 200;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window.open(appUrl + "/dict/dictAction!toUpdateDict.htm?itemId=" + a + "",
			"win", "left=" + WLeft + ",top=" + WTop + ",width=" + WWidth
					+ ",height=" + WHeight
					+ ",toolbar=no,menubar=no,scrollbars=yes");
}

function createDictType() {
	createShadeDiv();
	var form = window.document.forms[0];
	var WWidth = 400;
	var WHeight = 200;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window.open(appUrl + "/dict/dictAction!toCreateDictType.htm?", "win",
			"left=" + WLeft + ",top=" + WTop + ",width=" + WWidth + ",height="
					+ WHeight + ",toolbar=no,menubar=no,scrollbars=yes");
}

function createDict() {

	var dictTypeIdForDict = $("#dictTypeIdForDict").val();
	if (dictTypeIdForDict == "" || dictTypeIdForDict == "0") {
		warn("����ѡ���ֵ����Ͳ�������鿴�ֵ���Ŀ��.");
		return;
	}

	createShadeDiv();
	var form = window.document.forms[0];
	var WWidth = 400;
	var WHeight = 200;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window.open(appUrl + "/dict/dictAction!toCreateDict.htm?dictTypeId="
					+ dictTypeIdForDict + "", "win", "left=" + WLeft + ",top="
					+ WTop + ",width=" + WWidth + ",height=" + WHeight
					+ ",toolbar=no,menubar=no,scrollbars=yes");
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	// loadM.hide();
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