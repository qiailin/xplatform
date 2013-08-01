Ext.ns('Ext.plugins.Selector');

/**
 * menu
 * 
 * @param {}
 *            config
 */
Ext.plugins.MenuSelector = function(config) {
	var menu = Ext.data.Record.create([ {
		name : 'pid',
		type : 'long'
	}, {
		name : 'pname',
		type : 'string'
	}, {
		name : 'id',
		type : 'long'
	}, {
		name : 'name',
		type : 'string'
	} ]);

	var p = [ new Ext.plugins.ComplexGridCombox({
		searchEmptyText : '按菜单编号或名称查询',
		valueDataIndex : [ 'pid', 'pname', 'id', 'name' ],
		hiddenValue : config.hiddenValue,
		hiddenField : config.hiddenField,
		searchable : config.searchable || false,
		creatable : config.creatable || false,
		multiable : config.multiable || false,
		reload : config.reload || false,
		paginal : true,
		width : 405,
		height : 360,
		submitURL : config.submitURL,
		params : config.params || {},
		triggerAction : config.triggerAction || null,
		store : new Ext.data.Store({
			url : config.url,
			reader : new Ext.data.SimpleJsonReader({
				id : 'id'
			}, menu),
			remoteSort : true
		}),
		cm : new Ext.grid.ColumnModel([ {
			header : "父级菜单编号",
			dataIndex : 'pid',
			width : 70,
			sortable : false,
			align : 'center',
			renderer : function(v) {
				return Ext.util.Format.htmlEncode(v);
			}
		}, {
			header : "父级菜单名称",
			dataIndex : 'pname',
			width : 130,
			sortable : false,
			align : 'left',
			renderer : function(v, p) {
				p.attr = 'ext:qtip="' + v + '"';
				return Ext.util.Format.htmlEncode(v);
			}
		}, {
			header : "菜单编号",
			dataIndex : 'id',
			width : 70,
			sortable : false,
			align : 'center',
			renderer : function(v) {
				return Ext.util.Format.htmlEncode(v);
			}
		}, {
			header : "菜单名称",
			dataIndex : 'name',
			width : 130,
			sortable : false,
			align : 'left',
			renderer : function(v, p) {
				p.attr = 'ext:qtip="' + v + '"';
				return Ext.util.Format.htmlEncode(v);
			}
		} ]),
		viewConfig : {
			forceFit : true
		}
	}) ];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox(
			{
				listWidth : config.listWidth || undefined,
				displayField : config.displayField || 'id',
				triggerAction : 'all',
				mode : 'local',
				lazyInit : config.lazyInit || true,
				shadow : false,
				editable : config.autocomplete || false,
				tpl : '<tpl for="."><div class="x-combo-list-item"><span style="width:35%!important;">{id:htmlEncode}</span><span style="width:60%!important;">{name:htmlEncode}</span></div></tpl>',
				store : new Ext.data.Store({
					proxy : new Ext.data.MemoryProxy({}),
					reader : new Ext.data.SimpleJsonReader({
						id : 'id'
					}, menu)
				}),
				plugins : p,
				style : config.style
			});

	if (config.onselect) {
		this.editor.on('select', config.onselect);
	}

	if (config.beforeexpand) {
		this.editor.on('beforeexpand', config.beforeexpand);
	}

};

Ext.extend(Ext.plugins.MenuSelector, Ext.util.Observable, {
	init : function(grid) {
		// do nothing
	}
});

/**
 * sqlMonitor
 * 
 * @param {}
 *            config
 */
Ext.plugins.SqlMonitorSelector = function(config) {
	var sqlMonitor = Ext.data.Record.create([ {
		name : 'sqlMonitorId',
		type : 'long'
	}, {
		name : 'sqlMonitorTitle',
		type : 'string'
	}, {
		name : 'status',
		type : 'string'
	} ]);

	var p = [ new Ext.plugins.ComplexGridCombox({
		searchEmptyText : '按监控计划编号或监控标题查询',
		valueDataIndex : [ 'sqlMonitorId', 'sqlMonitorTitle', 'status' ],
		hiddenValue : config.hiddenValue,
		hiddenField : config.hiddenField,
		searchable : config.searchable || false,
		creatable : config.creatable || false,
		multiable : config.multiable || false,
		reload : config.reload || false,
		paginal : true,
		width : 405,
		height : 360,
		submitURL : config.submitURL,
		params : config.params || {},
		triggerAction : config.triggerAction || null,
		store : new Ext.data.Store({
			url : config.url,
			reader : new Ext.data.SimpleJsonReader({
				id : 'sqlMonitorId'
			}, sqlMonitor),
			remoteSort : true
		}),
		cm : new Ext.grid.ColumnModel([ {
			header : "监控计划编号",
			dataIndex : 'sqlMonitorId',
			width : 50,
			sortable : false,
			align : 'center',
			renderer : function(v) {
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
			renderer : function(v) {
				if (v == 'Y') {
					return "<p style='color:green'>正常运行</p>";
				}
				if (v == 'N') {
					return "<p style='color:red'>ֹ停止运行</p>";
				}
			}
		} ]),
		viewConfig : {
			forceFit : true
		}
	}) ];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox(
			{
				listWidth : config.listWidth || undefined,
				displayField : config.displayField || 'sqlMonitorId',
				triggerAction : 'all',
				mode : 'local',
				lazyInit : config.lazyInit || true,
				shadow : false,
				editable : config.autocomplete || false,
				tpl : '<tpl for="."><div class="x-combo-list-item"><span style="width:35%!important;">{sqlMonitorId:htmlEncode}</span><span style="width:60%!important;">{sqlMonitorTitle:htmlEncode}</span></div></tpl>',
				store : new Ext.data.Store({
					proxy : new Ext.data.MemoryProxy({}),
					reader : new Ext.data.SimpleJsonReader({
						id : 'sqlMonitorId'
					}, sqlMonitor)
				}),
				plugins : p,
				style : config.style
			});

	if (config.onselect) {
		this.editor.on('select', config.onselect);
	}

	if (config.beforeexpand) {
		this.editor.on('beforeexpand', config.beforeexpand);
	}

};

Ext.extend(Ext.plugins.SqlMonitorSelector, Ext.util.Observable, {
	init : function(grid) {
		// do nothing
	}
});

/**
 * dbTable
 * 
 * @param {}
 *            config
 */
Ext.plugins.DBTableSelector = function(config) {
	var dbTable = Ext.data.Record.create([ {
		name : 'itemId',
		type : 'long'
	}, {
		name : 'itemValue',
		type : 'string'
	}, {
		name : 'remark',
		type : 'string'
	}, {
		name : 'itemState',
		type : 'string'
	} ]);

	var p = [ new Ext.plugins.ComplexGridCombox({
		searchEmptyText : '����Ż�����ѯ',
		valueDataIndex : [ 'itemId', 'itemValue', 'remark', 'itemState' ],
		hiddenValue : config.hiddenValue,
		hiddenField : config.hiddenField,
		searchable : config.searchable || false,
		creatable : config.creatable || false,
		multiable : config.multiable || false,
		reload : config.reload || false,
		paginal : true,
		width : 405,
		height : 360,
		submitURL : config.submitURL,
		params : config.params || {},
		triggerAction : config.triggerAction || null,
		store : new Ext.data.Store({
			url : config.url,
			reader : new Ext.data.SimpleJsonReader({
				id : 'itemId'
			}, dbTable),
			remoteSort : true
		}),
		cm : new Ext.grid.ColumnModel([ {
			header : "���",
			dataIndex : 'itemId',
			width : 50,
			sortable : false,
			align : 'center',
			renderer : function(v) {
				return Ext.util.Format.htmlEncode(v);
			}
		}, {
			header : "����",
			dataIndex : 'itemValue',
			width : 150,
			sortable : false,
			align : 'left',
			renderer : function(v, p) {
				p.attr = 'ext:qtip="' + v + '"';
				return Ext.util.Format.htmlEncode(v);
			}
		}, {
			header : "sequence",
			dataIndex : 'remark',
			width : 100,
			sortable : false,
			align : 'left',
			renderer : function(v, p) {
				p.attr = 'ext:qtip="' + v + '"';
				return Ext.util.Format.htmlEncode(v);
			}
		}, {
			header : "״̬",
			dataIndex : 'itemState',
			width : 50,
			sortable : false,
			align : 'center',
			renderer : function(v) {
				if (v == 'U') {
					return "<p style='color:green'>����</p>";
				} else {
					return "<p style='color:red'>����</p>";
				}
			}
		} ]),
		viewConfig : {
			forceFit : true
		}
	}) ];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox(
			{
				listWidth : config.listWidth || undefined,
				displayField : config.displayField || 'itemId',
				triggerAction : 'all',
				mode : 'local',
				lazyInit : config.lazyInit || true,
				shadow : false,
				editable : config.autocomplete || false,
				tpl : '<tpl for="."><div class="x-combo-list-item"><span style="width:35%!important;">{itemId:htmlEncode}</span><span style="width:60%!important;">{itemValue:htmlEncode}</span></div></tpl>',
				store : new Ext.data.Store({
					proxy : new Ext.data.MemoryProxy({}),
					reader : new Ext.data.SimpleJsonReader({
						id : 'itemId'
					}, dbTable)
				}),
				plugins : p,
				style : config.style
			});

	if (config.onselect) {
		this.editor.on('select', config.onselect);
	}

	if (config.beforeexpand) {
		this.editor.on('beforeexpand', config.beforeexpand);
	}

};

Ext.extend(Ext.plugins.DBTableSelector, Ext.util.Observable, {
	init : function(grid) {
		// do nothing
	}
});