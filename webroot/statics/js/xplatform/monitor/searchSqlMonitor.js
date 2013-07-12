Ext.onReady(function() {
	// tips
	Ext.QuickTips.init();

	sqlMonitorId = new Ext.form.NumberField({
				id : "sqlMonitorId",
				applyTo : 'sqlMonitorId'
			});

	sqlMonitorTitle = new Ext.form.TextField({
				id : "sqlMonitorTitle",
				applyTo : 'sqlMonitorTitle'
			});

	var statusStore = new Ext.data.SimpleStore({
				fields : ['itemId', 'itemName'],
				data : [['', '所有状态'], ['Y', '正常运行'], ['N', '停止运行']]
			});

	var status = new Ext.form.ComboBox({
				id : 'status',
				store : statusStore,
				mode : 'local',
				displayField : 'itemName',
				valueField : 'itemId',
				editable : false,
				triggerAction : 'all',
				applyTo : 'status'
			});

	var logStore = new Ext.data.SimpleStore({
				fields : ['itemId', 'itemName'],
				data : [['', '所有状态'], ['Y', '记录'], ['N', '不记录']]
			});

	var log = new Ext.form.ComboBox({
				id : 'log',
				store : logStore,
				mode : 'local',
				displayField : 'itemName',
				valueField : 'itemId',
				editable : false,
				triggerAction : 'all',
				applyTo : 'log'
			});

	status.setValue('');
	log.setValue('');

	var Item = Ext.data.Record.create([{
				name : 'sqlMonitorId',
				type : 'long'
			}, {
				name : 'sqlMonitorTitle',
				type : 'string'
			}, {
				name : 'createDate',
				type : 'date'
			}, {
				name : 'freq',
				type : 'int'
			}, {
				name : 'threshold',
				type : 'int'
			}, {
				name : 'log',
				type : 'String'
			}, {
				name : 'status',
				type : 'string'
			}]);

	store = new Ext.data.Store({
				url : appUrl
						+ '/monitor/sqlMonitorAction!getSqlMonitorJsonList.htm',
				reader : new Ext.data.SimpleJsonReader({
							id : 'sqlMonitorId'
						}, Item),
				remoteSort : true
			});

	var cm = new Ext.grid.ColumnModel([{
				header : "监控计划编号",
				dataIndex : 'sqlMonitorId',
				width : 50,
				sortable : true,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "监控标题",
				dataIndex : 'sqlMonitorTitle',
				width : 100,
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "监控计划状态",
				dataIndex : 'status',
				width : 50,
				sortable : false,
				align : 'center',
				renderer : function(v, p) {
					if (v == 'Y') {
						return "<p style='color:green'>正常运行</p>";
					}
					if (v == 'N') {
						return "<p style='color:red'>停止运行</p>";
					}
				}
			}, {
				header : "创建时间",
				dataIndex : 'createDate',
				width : 70,
				sortable : false,
				align : 'center',
				renderer : function(v, p) {
					v = Ext.util.Format.date(v.toUTC(), 'Y-m-d H:i:s');
					p.attr = 'ext:qtip="' + v + '"';
					return v;
				}
			}, {
				header : "监控频率(分)",
				dataIndex : 'freq',
				width : 40,
				sortable : false,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "阈值",
				dataIndex : 'threshold',
				width : 25,
				sortable : false,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "是否记录日志",
				dataIndex : 'log',
				width : 50,
				sortable : false,
				align : 'center',
				renderer : function(v, p) {
					if (v == 'Y') {
						return "记录";
					}
					if (v == 'N') {
						return "不记录";
					}
				}
			}, {
				header : "操作",
				width : 40,
				sortable : false,
				align : 'center',
				renderer : function(value, cellmeta, record, rowIndex,
						columnIndex, store) {
					var strReturn = "<a href=javascript:searchSqlMonitorDetail('"
							+ record.get('sqlMonitorId') + "') > 查看/修改</a>";

					return strReturn;
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
							displayMsg : '共 {2} 条记录，当前显示 {0} - {1}',
							emptyMsg : "没有找到记录！"
						})
			});

	store.setDefaultSort('sqlMonitorId', 'DESC');

	search();

	$('#hideFrame').bind('load', promgtMsg);

});

function search() {
	store.baseParams.sqlMonitorId = encodeURIComponent(sqlMonitorId.getValue());
	store.baseParams.sqlMonitorTitle = encodeURIComponent(sqlMonitorTitle
			.getValue());
	store.baseParams.status = encodeURIComponent(Ext.getCmp("status")
			.getValue());
	store.baseParams.log = encodeURIComponent(Ext.getCmp("log").getValue());
	store.load({
				params : {
					start : 0,
					limit : pageSize
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

function searchSqlMonitorDetail(sqlMonitorId) {
	var WWidth = 800;
	var WHeight = 500;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window
			.open(
					appUrl
							+ "/monitor/sqlMonitorAction!updateSqlMonitorPrepare.htm?sqlMonitorId4del="
							+ sqlMonitorId, 'searchSqlMonitorDetail', "left="
							+ WLeft + ",top=" + WTop + ",width=" + WWidth
							+ ",height=" + WHeight
							+ ",toolbar=no,rolebar=no,scrollbars=yes");
}

function create() {
	var WWidth = 800;
	var WHeight = 465;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window.open(appUrl
					+ "/monitor/sqlMonitorAction!createSqlMonitorPrepare.htm",
			'create', "left=" + WLeft + ",top=" + WTop + ",width=" + WWidth
					+ ",height=" + WHeight
					+ ",toolbar=no,rolebar=no,scrollbars=yes");
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