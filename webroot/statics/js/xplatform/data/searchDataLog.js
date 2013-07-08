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
						header : "日志编号",
						dataIndex : 'dataLogTotalId',
						width : 70,
						sortable : true,
						align : 'center'
					}, {
						header : "配置编号",
						dataIndex : 'dataConfigId',
						width : 70,
						sortable : false,
						align : 'center'
					}, {
						header : "表名",
						dataIndex : 'tableName',
						width : 150,
						sortable : false,
						align : 'left',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "状态",
						dataIndex : 'flag',
						width : 50,
						sortable : false,
						align : 'center',
						renderer : function(v, p) {
							if ('U' == v) {
								return "<p style='color:green'>有效</p>";
							} else {
								return "<p style='color:red'>删除</p>";
							}
						}
					}, {
						header : "数据总数",
						dataIndex : 'total',
						width : 70,
						sortable : false,
						align : 'center',
						renderer : function(v, p) {
							p.attr = 'ext:qtip="' + v + '"';
							return Ext.util.Format.htmlEncode(v);
						}
					}, {
						header : "创建时间",
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
						header : "修改时间",
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
						header : "操作",
						width : 70,
						sortable : false,
						align : 'center',
						renderer : function(value, cellmeta, record, rowIndex,
								columnIndex, store) {
							if (record.get('flag') == 'D') {
								return "无数据预览";
							} else {
								return "<a href=javascript:searchDataPreview('"
										+ record.get('dataLogTotalId')
										+ "') >数据预览 </a>";
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
									displayMsg : '共 {2} 条记录，当前显示 {0} - {1}',
									emptyMsg : "没有找到记录！"
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
		warn('请选择查询表名');
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
		warn('请选择导入表名');
		return;
	}

	var form = window.document.forms[0];
	form.action = "dataManageAction!exportDataTemplate.htm";
	form.target = "hideFrame";
	form.submit();
}

function importData() {
	if ($('#itemId').val() == '') {
		warn('请选择查询表名');
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
					emptyText : '请选择excel文件',
					fieldLabel : '文件',
					name : 'upload',
					buttonText : '浏览'
				}],
		buttons : [{
			text : '保存',
			handler : function() {
				Ext.Msg.confirm("提示", "确定提交？", function(button) {
					if (button == 'yes') {
						if (fp.getForm().isValid()) {
							fp.getForm().submit({
										url : 'dataManageAction!importData.htm',
										params : {
											dataConfigId : $('#itemId').val()
										},
										waitMsg : '正在提交文件...',
										success : function(form, action) {
											try {
												Ext.Msg.show({
															title : '信息',
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
														title : '信息',
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
			text : '重置',
			handler : function() {
				fp.getForm().reset();
			}
		}, {
			text : '取消',
			handler : function() {
				win.close();
				Ext.getBody().unmask();
			}
		}]
	});

	var win = new Ext.Window({
				title : "文件上传",
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
							window.opener.search();
						}
					},
					icon : Ext.Msg.INFO
				});
	}
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