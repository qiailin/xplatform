Ext.onReady(function() {
	// tips
	Ext.QuickTips.init();

	var menu = new Ext.plugins.SqlMonitorSelector({
				url : appUrl
						+ '/monitor/sqlMonitorAction!getSqlMonitorJsonList.htm',
				triggerAction : 'power',
				searchable : true,
				displayField : 'sqlMonitorTitle',
				hiddenField : {
					sqlMonitorId : 'monitorId'
				}
			});

	menu.editor.applyToMarkup('title');

	var Item = Ext.data.Record.create([{
				name : 'monitorLogId',
				type : 'long'
			}, {
				name : 'sqlMonitorTitle',
				type : 'string'
			}, {
				name : 'createDate',
				type : 'date'
			}, {
				name : 'monitorResult',
				type : 'int'
			}, {
				name : 'threshold',
				type : 'int'
			}]);

	store = new Ext.data.Store({
				url : appUrl
						+ '/monitor/monitorLogAction!getMonitorLogJsonList.htm',
				reader : new Ext.data.SimpleJsonReader({
							id : 'monitorLogId'
						}, Item),
				remoteSort : true
			});

	var cm = new Ext.grid.ColumnModel([{
				header : "监控日志编号",
				dataIndex : 'monitorLogId',
				width : 40,
				sortable : true,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "监控标题",
				dataIndex : 'sqlMonitorTitle',
				sortable : false,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "监控结果",
				dataIndex : 'monitorResult',
				width : 40,
				sortable : false,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "实际阈值",
				dataIndex : 'threshold',
				width : 30,
				sortable : false,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
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

	store.setDefaultSort('monitorLogId', 'DESC');

	// search();

	$('#hideFrame').bind('load', promgtMsg);

});

function search() {
	var monitorId = document.getElementById("monitorId").value;

	if (monitorId == "") {
		warn("请选择监控计划！");
		return;
	}

	store.baseParams.monitorId = encodeURIComponent(monitorId);
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
	var WWidth = 860;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window
			.open(
					appUrl
							+ "/monitor/sqlMonitorAction!updateSqlMonitorPrepare.htm?sqlMonitorId4del="
							+ sqlMonitorId, 'updateSqlMonitorPrepare', "left="
							+ WLeft + ",width=" + WWidth
							+ ",toolbar=no,rolebar=no,scrollbars=yes");
}

function create() {
	var WWidth = 860;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl
					+ "/monitor/sqlMonitorAction!createSqlMonitorPrepare.htm",
			'updateSqlMonitorPrepare', "left=" + WLeft + ",width=" + WWidth
					+ ",toolbar=no,rolebar=no,scrollbars=yes");
}

function warn(msg) {
	Ext.Msg.show({
				title : '警告',
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