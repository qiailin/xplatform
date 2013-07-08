Ext.onReady(function() {
			// tips
			Ext.QuickTips.init();

			var Item1 = Ext.data.Record.create([{
						name : 'columnId',
						type : 'long'
					}, {
						name : 'columnName',
						type : 'string'
					}]);

			columnStore = new Ext.data.Store({
						url : '',
						reader : new Ext.data.SimpleJsonReader({
									id : 'columnId'
								}, Item1),
						remoteSort : true
					});
			var has = Ext.getDom('listJson').value;
			if (has != '') {
				columnStore.loadData(Ext.util.JSON.decode(has), false);
			}

			var Item = Ext.data.Record.create([{
						name : 'value1',
						type : 'string'
					}, {
						name : 'value2',
						type : 'string'
					}, {
						name : 'value3',
						type : 'string'
					}, {
						name : 'value4',
						type : 'string'
					}, {
						name : 'value5',
						type : 'string'
					}, {
						name : 'value6',
						type : 'string'
					}, {
						name : 'value7',
						type : 'string'
					}, {
						name : 'value8',
						type : 'string'
					}, {
						name : 'value9',
						type : 'string'
					}, {
						name : 'value10',
						type : 'string'
					}, {
						name : 'value11',
						type : 'string'
					}, {
						name : 'value12',
						type : 'string'
					}, {
						name : 'value13',
						type : 'string'
					}, {
						name : 'value14',
						type : 'string'
					}, {
						name : 'value15',
						type : 'string'
					}, {
						name : 'value16',
						type : 'string'
					}, {
						name : 'value17',
						type : 'string'
					}, {
						name : 'value18',
						type : 'string'
					}, {
						name : 'value19',
						type : 'string'
					}, {
						name : 'value20',
						type : 'string'
					}, {
						name : 'value21',
						type : 'string'
					}, {
						name : 'value22',
						type : 'string'
					}, {
						name : 'value23',
						type : 'string'
					}, {
						name : 'value24',
						type : 'string'
					}, {
						name : 'value25',
						type : 'string'
					}, {
						name : 'value26',
						type : 'string'
					}, {
						name : 'value27',
						type : 'string'
					}, {
						name : 'value28',
						type : 'string'
					}, {
						name : 'value29',
						type : 'string'
					}, {
						name : 'value30',
						type : 'string'
					}]);

			store = new Ext.data.Store({
						url : appUrl
								+ '/data/dataManageAction!getDataPreviewJsonList.htm',
						reader : new Ext.data.SimpleJsonReader({
									id : 'value1'
								}, Item),
						remoteSort : true
					});

			var cm = new Ext.grid.ColumnModel([{
						header : "parameter1",
						dataIndex : 'value1',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter2",
						dataIndex : 'value2',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter3",
						dataIndex : 'value3',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter4",
						dataIndex : 'value4',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter5",
						dataIndex : 'value5',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter6",
						dataIndex : 'value6',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter7",
						dataIndex : 'value7',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter8",
						dataIndex : 'value8',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter9",
						dataIndex : 'value9',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter10",
						dataIndex : 'value10',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter11",
						dataIndex : 'value11',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter12",
						dataIndex : 'value12',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter13",
						dataIndex : 'value13',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter14",
						dataIndex : 'value14',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter15",
						dataIndex : 'value15',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter16",
						dataIndex : 'value16',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter17",
						dataIndex : 'value17',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter18",
						dataIndex : 'value18',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter19",
						dataIndex : 'value19',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter20",
						dataIndex : 'value20',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter21",
						dataIndex : 'value21',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter22",
						dataIndex : 'value22',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter23",
						dataIndex : 'value23',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter24",
						dataIndex : 'value24',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter25",
						dataIndex : 'value25',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter26",
						dataIndex : 'value26',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter27",
						dataIndex : 'value27',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter28",
						dataIndex : 'value28',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter29",
						dataIndex : 'value29',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "parameter30",
						dataIndex : 'value30',
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}]);

			var i = 0;
			columnStore.each(function(record) {
						cm.setColumnHeader((i++), record.get("columnName"));
					});

			for (var j = i; j < 30; j++) {
				cm.setHidden(j, true);
			}

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
									displayMsg : '共 {2} 条记录，当前显示 {0} - {1}',
									emptyMsg : "没有找到记录！"
								})
					});

			search();

			$('#hideFrame').bind('load', promgtMsg);

		});

function ok() {
	window.close();
}

function del() {
	Ext.Msg.confirm("提示", "确认删除本次导入的所有数据？", function(button) {
				if (button == 'yes') {
					createShadeDiv();
					var form = window.document.forms[0];
					form.action = appUrl
							+ "/data/dataManageAction!deleteDataInfo.htm";
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
					msg : "操作成功",
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

function search() {
	store.baseParams.dataLogTotalId = encodeURIComponent($('#dataLogTotalId')
			.val());

	store.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});
}