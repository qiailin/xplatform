Ext.ns('Ext.plugins.Selector');

/**
 * factory
 * 
 * @param {}
 *            config
 */
Ext.plugins.FactorySelector = function(config) {
	var factory = Ext.data.Record.create([{
				name : 'factory_number',
				type : 'string'
			}, {
				name : 'factory_name',
				type : 'string'
			}, {
				name : 'factory_xx_name',
				type : 'string'
			}]);

	var p = [new Ext.plugins.ComplexGridCombox({
				searchEmptyText : '按工厂编号或名称查询',
				valueDataIndex : ['factory_number', 'factory_name',
						'factory_xx_name'],
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
										id : 'factory_number'
									}, factory),
							remoteSort : true
						}),
				cm : new Ext.grid.ColumnModel([{
							id : "factory_number",
							header : "工厂编号",
							dataIndex : 'factory_number',
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "工厂名称",
							dataIndex : 'factory_name',
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}]),
				viewConfig : {
					forceFit : true
				}
			})];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox({
		listWidth : config.listWidth || undefined,
		displayField : config.displayField || 'factory_number',
		triggerAction : 'all',
		mode : 'local',
		lazyInit : config.lazyInit || true,
		shadow : false,
		editable : config.autocomplete || false,
		tpl : '<tpl for="."><div class="x-combo-list-item"><span style="width:35%!important;">{factory_number:htmlEncode}</span><span style="width:60%!important;">{factory_name:htmlEncode}</span></div></tpl>',
		store : new Ext.data.Store({
					proxy : new Ext.data.MemoryProxy({}),
					reader : new Ext.data.SimpleJsonReader({
								id : 'factory_number'
							}, factory)
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

Ext.extend(Ext.plugins.FactorySelector, Ext.util.Observable, {
			init : function(grid) {
				// do nothing
			}
		});

/**
 * companyCode
 * 
 * @param {}
 *            config
 */
Ext.plugins.CompanyCodeSelector = function(config) {
	var companyCode = Ext.data.Record.create([{
				name : 'comp_cod',
				type : 'string'
			}, {
				name : 'comp_cod_name',
				type : 'string'
			}]);

	var p = [new Ext.plugins.ComplexGridCombox({
				searchEmptyText : '按公司代码编号或名称查询',
				valueDataIndex : ['comp_cod', 'comp_cod_name'],
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
										id : 'comp_cod'
									}, companyCode),
							remoteSort : true
						}),
				cm : new Ext.grid.ColumnModel([{
							id : "comp_cod",
							header : "公司代码编号",
							dataIndex : 'comp_cod',
							width : 150,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "公司名称",
							dataIndex : 'comp_cod_name',
							width : 245,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}]),
				viewConfig : {
					forceFit : true
				}
			})];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox({
		listWidth : config.listWidth || undefined,
		displayField : config.displayField || 'comp_cod_name',
		triggerAction : 'all',
		mode : 'local',
		lazyInit : config.lazyInit || true,
		shadow : false,
		editable : config.autocomplete || false,
		tpl : '<tpl for="."><div class="x-combo-list-item"><span style="width:35%!important;">{comp_cod:htmlEncode}</span><span style="width:60%!important;">{comp_cod_name:htmlEncode}</span></div></tpl>',
		store : new Ext.data.Store({
					proxy : new Ext.data.MemoryProxy({}),
					reader : new Ext.data.SimpleJsonReader({
								id : 'comp_cod'
							}, companyCode)
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

Ext.extend(Ext.plugins.CompanyCodeSelector, Ext.util.Observable, {
			init : function(grid) {
				// do nothing
			}
		});

/**
 * CompanyByOrgSelector
 * 
 * @param {}
 *            config
 */
Ext.plugins.CompanyByOrgSelector = function(config) {
	var companyByOrg = Ext.data.Record.create([{
				name : 'orgId',
				type : 'long'
			}, {
				name : 'orgName',
				type : 'string'
			}]);

	var p = [new Ext.plugins.ComplexGridCombox({
				searchEmptyText : '按公司名查询',
				valueDataIndex : ['orgId', 'orgName'],
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
										id : 'orgId'
									}, companyByOrg),
							remoteSort : true
						}),
				cm : new Ext.grid.ColumnModel([{
							id : "orgId",
							header : "公司编号",
							dataIndex : 'orgId',
							width : 100,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "公司名称",
							dataIndex : 'orgName',
							width : 200,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}]),
				viewConfig : {
					forceFit : true
				}
			})];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox({
		listWidth : config.listWidth || undefined,
		displayField : config.displayField || 'orgName',
		triggerAction : 'all',
		mode : 'local',
		lazyInit : config.lazyInit || true,
		shadow : false,
		editable : config.autocomplete || false,
		tpl : '<tpl for="."><div class="x-combo-list-item"><span style="width:15%!important;">{orgId:htmlEncode}</span><span style="width:50%!important;">{orgName:htmlEncode}</span></div></tpl>',
		store : new Ext.data.Store({
					proxy : new Ext.data.MemoryProxy({}),
					reader : new Ext.data.SimpleJsonReader({
								id : 'orgId'
							}, companyByOrg)
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

Ext.extend(Ext.plugins.CompanyByOrgSelector, Ext.util.Observable, {
			init : function(grid) {
				// do nothing
			}
		});

/**
 * RoleSelector
 * 
 * @param {}
 * 
 */
Ext.plugins.RoleSelector = function(config) {
	var role = Ext.data.Record.create([{
				name : 'roleId',
				type : 'string'
			}, {
				name : 'roleName',
				type : 'string'
			}, {
				name : 'descn',
				type : 'string'
			}]);

	var p = [new Ext.plugins.ComplexGridCombox({
				searchEmptyText : '按角色编号或角色名称或角色描述',
				valueDataIndex : ['roleId', 'roleName', 'descn'],
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
										id : 'roleId'
									}, role),
							remoteSort : true
						}),
				cm : new Ext.grid.ColumnModel([{
							header : "角色编号",
							dataIndex : 'roleId',
							width : 120,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "角色名称",
							dataIndex : 'roleName',
							width : 180,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "角色描述",
							dataIndex : 'descn',
							width : 100,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}])
			})];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox({
		listWidth : config.listWidth || undefined,
		displayField : config.displayField || 'roleId',
		triggerAction : 'all',
		mode : 'local',
		lazyInit : config.lazyInit || true,
		shadow : false,
		editable : config.autocomplete || false,
		tpl : '<tpl for="."><div class="x-combo-list-item"><span style="width:35%!important;">{roleId:htmlEncode}</span><span style="width:60%!important;">{roleName:htmlEncode}</span></div></tpl>',
		store : new Ext.data.Store({
					proxy : new Ext.data.MemoryProxy({}),
					reader : new Ext.data.SimpleJsonReader({
								id : 'roleId'
							}, role)
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

Ext.extend(Ext.plugins.RoleSelector, Ext.util.Observable, {
			init : function(grid) {
				// do nothing
			}
		});

/**
 * PositionTypeSelector
 * 
 * @param {}
 * 
 */
Ext.plugins.PositionTypeSelector = function(config) {
	var positionType = Ext.data.Record.create([{
				name : 'positionTypeId',
				type : 'long'
			}, {
				name : 'positionTypeName',
				type : 'string'
			}, {
				name : 'companyId',
				type : 'long'
			}]);

	var p = [new Ext.plugins.ComplexGridCombox({
				searchEmptyText : '按岗位名查询',
				valueDataIndex : ['positionTypeId', 'positionTypeName',
						'companyId'],
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
										id : 'positionTypeId'
									}, positionType),
							remoteSort : true
						}),
				cm : new Ext.grid.ColumnModel([{
							header : "岗位ID",
							dataIndex : 'positionTypeId',
							width : 120,
							sortable : false,
							align : 'center',
							renderer : function(v, p) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "岗位名称",
							dataIndex : 'positionTypeName',
							width : 180,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "公司Id",
							dataIndex : 'companyId',
							width : 70,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}]),
				viewConfig : {
					forceFit : true
				}
			})];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox({
		listWidth : config.listWidth || undefined,
		displayField : config.displayField || 'positionTypeId',
		triggerAction : 'all',
		mode : 'local',
		lazyInit : config.lazyInit || true,
		shadow : false,
		editable : config.autocomplete || false,
		tpl : '<tpl for="."><div class="x-combo-list-item"><span style="width:35%!important;">{positionTypeId:htmlEncode}</span><span style="width:60%!important;">{positionTypeName:htmlEncode}</span><span style="width:60%!important;">{companyId:htmlEncode}</span></div></tpl>',
		store : new Ext.data.Store({
					proxy : new Ext.data.MemoryProxy({}),
					reader : new Ext.data.SimpleJsonReader({
								id : 'positionTypeId'
							}, positionType)
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

Ext.extend(Ext.plugins.PositionTypeSelector, Ext.util.Observable, {
			init : function(grid) {
				// do nothing
			}
		});

/**
 * menu
 * 
 * @param {}
 *            config
 */
Ext.plugins.MenuSelector = function(config) {
	var menu = Ext.data.Record.create([{
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
			}]);

	var p = [new Ext.plugins.ComplexGridCombox({
				searchEmptyText : '按菜单编号或名称查询',
				valueDataIndex : ['pid', 'pname', 'id', 'name'],
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
				cm : new Ext.grid.ColumnModel([{
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
						}]),
				viewConfig : {
					forceFit : true
				}
			})];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox({
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
 * conpoint
 * 
 * @param {}
 *            config
 */
Ext.plugins.ConpointSelector = function(config) {
	var conpoint = Ext.data.Record.create([{
				name : 'conpointId',
				type : 'long'
			}, {
				name : 'conpointNum',
				type : 'string'
			}, {
				name : 'conpointName',
				type : 'string'
			}, {
				name : 'menuId',
				type : 'long'
			}, {
				name : 'menuName',
				type : 'string'
			}]);

	var p = [new Ext.plugins.ComplexGridCombox({
				searchEmptyText : '按权限点ID或编号或名称查询',
				valueDataIndex : ['conpointId', 'conpointNum', 'conpointName',
						'menuId', 'menuName'],
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
										id : 'conpointId'
									}, conpoint),
							remoteSort : true
						}),
				cm : new Ext.grid.ColumnModel([{
							header : "权限点ID",
							dataIndex : 'conpointId',
							width : 60,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "权限点编号",
							dataIndex : 'conpointNum',
							width : 70,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "权限点名称",
							dataIndex : 'conpointName',
							width : 80,
							sortable : false,
							align : 'left',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "菜单ID",
							dataIndex : 'menuId',
							width : 60,
							sortable : false,
							align : 'center',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "菜单名称",
							dataIndex : 'menuName',
							width : 80,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}]),
				viewConfig : {
					forceFit : true
				}
			})];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox({
		listWidth : config.listWidth || undefined,
		displayField : config.displayField || 'conpointId',
		triggerAction : 'all',
		mode : 'local',
		lazyInit : config.lazyInit || true,
		shadow : false,
		editable : config.autocomplete || false,
		tpl : '<tpl for="."><div class="x-combo-list-item"><span style="width:35%!important;">{conpointId:htmlEncode}</span><span style="width:60%!important;">{conpointNum:htmlEncode}</span></div></tpl>',
		store : new Ext.data.Store({
					proxy : new Ext.data.MemoryProxy({}),
					reader : new Ext.data.SimpleJsonReader({
								id : 'conpointId'
							}, conpoint)
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

Ext.extend(Ext.plugins.ConpointSelector, Ext.util.Observable, {
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
	var sqlMonitor = Ext.data.Record.create([{
				name : 'sqlMonitorId',
				type : 'long'
			}, {
				name : 'sqlMonitorTitle',
				type : 'string'
			}, {
				name : 'status',
				type : 'string'
			}]);

	var p = [new Ext.plugins.ComplexGridCombox({
				searchEmptyText : '按监控计划编号或监控标题查询',
				valueDataIndex : ['sqlMonitorId', 'sqlMonitorTitle', 'status'],
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
				cm : new Ext.grid.ColumnModel([{
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
									return "<p style='color:red'>停止运行</p>";
								}
							}
						}]),
				viewConfig : {
					forceFit : true
				}
			})];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox({
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
	var dbTable = Ext.data.Record.create([{
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
			}]);

	var p = [new Ext.plugins.ComplexGridCombox({
				searchEmptyText : '按编号或表名查询',
				valueDataIndex : ['itemId', 'itemValue', 'remark', 'itemState'],
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
				cm : new Ext.grid.ColumnModel([{
							header : "编号",
							dataIndex : 'itemId',
							width : 50,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "表名",
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
							header : "状态",
							dataIndex : 'itemState',
							width : 50,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								if (v == 'U') {
									return "<p style='color:green'>启用</p>";
								} else {
									return "<p style='color:red'>禁用</p>";
								}
							}
						}]),
				viewConfig : {
					forceFit : true
				}
			})];

	if (config.plugins) {
		if (Ext.isArray(config.plugins)) {
			p = p.concat(config.plugins);
		} else {
			p.push(config.plugins);
		}
	}

	this.editor = new Ext.form.ComboBox({
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