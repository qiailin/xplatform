Ext.onReady(function() {
	// tips
	Ext.QuickTips.init();

	var Item = Ext.data.Record.create([{
				name : 'logMonitorId',
				type : 'long'
			}, {
				name : 'className',
				type : 'string'
			}, {
				name : 'methodName',
				type : 'string'
			}, {
				name : 'lineNumber',
				type : 'string'
			}, {
				name : 'message',
				type : 'string'
			}, {
				name : 'e',
				type : 'string'
			}, {
				name : 'logDate',
				type : 'string'
			}, {
				name : 'createDate',
				type : 'date'
			}]);

	store = new Ext.data.Store({
				url : appUrl
						+ '/monitor/logMonitorAction!getLogMonitorJsonList.htm',
				reader : new Ext.data.SimpleJsonReader({
							id : 'logMonitorId'
						}, Item),
				remoteSort : true
			});

	var cm = new Ext.grid.ColumnModel([{
				header : "ID",
				dataIndex : 'logMonitorId',
				width : 50,
				sortable : true,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "Log Date",
				dataIndex : 'logDate',
				width : 80,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "class Name",
				dataIndex : 'className',
				width : 100,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'style="white-space:normal;"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "Method Name",
				dataIndex : 'methodName',
				width : 100,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'style="white-space:normal;"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "Line Number",
				dataIndex : 'lineNumber',
				width : 50,
				align : 'center',
				renderer : function(v, p) {
					p.attr = 'ext:qtip="' + v + '"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "Message",
				dataIndex : 'message',
				width : 300,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'style="white-space:normal;"';
					return Ext.util.Format.htmlEncode(v);
				}
			}, {
				header : "e",
				dataIndex : 'e',
				width : 1000,
				align : 'left',
				renderer : function(v, p) {
					p.attr = 'style="white-space:normal;"';
					return Ext.util.Format.htmlEncode(v);
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
				autoScroll : true,
				enableHdMenu : false,
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

	store.setDefaultSort('logMonitorId', 'DESC');

	search();

});

function search() {
	store.load();
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