Ext.onReady(function() {
	// tips
	Ext.QuickTips.init();

	var Item = Ext.data.Record.create([{
				name : 'responseStatsId',
				type : 'string'
			}, {
				name : 'methodName',
				type : 'string'
			}, {
				name : 'startTime',
				type : 'string'
			}, {
				name : 'endTime',
				type : 'string'
			}, {
				name : 'createDate',
				type : 'date'
			}]);

	store = new Ext.data.Store({
				url : appUrl
						+ '/openapi/responseAction!getResponseStatsJsonList.htm',
				reader : new Ext.data.SimpleJsonReader({
							id : 'responseStatsId'
						}, Item),
				remoteSort : true
			});

	var cm = new Ext.grid.ColumnModel([{
				header : "ID",
				dataIndex : 'responseStatsId',
				width : 50,
				sortable : true,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "Method Name",
				dataIndex : 'methodName',
				width : 120,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "Start Time",
				dataIndex : 'startTime',
				width : 80,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "End Time",
				dataIndex : 'endTime',
				width : 80,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "Cost Time",
				dataIndex : '',
				width : 80,
				align : 'center',
				renderer : function(value, cellmeta, record, rowIndex,
						columnIndex, store) {
					var startTime = parseFloat(record.get('startTime'));
					var endTime = parseFloat(record.get('endTime'));

					var str = endTime - startTime;

					cellmeta.attr = 'ext:qtip="' + str + '"';
					return str;
				}
			}, {
				header : "Create Date",
				dataIndex : 'createDate',
				width : 80,
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

	store.setDefaultSort('responseStatsId', 'DESC');

	search();

});

function search() {
	store.load();
}

function config() {
	var WWidth = 500;
	var WHeight = 250;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window.open(appUrl + "/monitor/cacheMonitorAction!configCachePrepare.htm",
			"config", "left=" + WLeft + ",top=" + WTop + ",width=" + WWidth
					+ ",height=" + WHeight
					+ ",toolbar=no,menubar=no,scrollbars=yes");
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