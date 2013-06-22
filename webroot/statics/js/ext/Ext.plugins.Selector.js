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
				searchEmptyText : '��������Ż����Ʋ�ѯ',
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
							header : "�������",
							dataIndex : 'factory_number',
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "��������",
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
				searchEmptyText : '����˾�����Ż����Ʋ�ѯ',
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
							header : "��˾������",
							dataIndex : 'comp_cod',
							width : 150,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "��˾����",
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
				searchEmptyText : '����˾����ѯ',
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
							header : "��˾���",
							dataIndex : 'orgId',
							width : 100,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "��˾����",
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
				searchEmptyText : '����ɫ��Ż��ɫ���ƻ��ɫ����',
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
							header : "��ɫ���",
							dataIndex : 'roleId',
							width : 120,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "��ɫ����",
							dataIndex : 'roleName',
							width : 180,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "��ɫ����",
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
				searchEmptyText : '����λ����ѯ',
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
							header : "��λID",
							dataIndex : 'positionTypeId',
							width : 120,
							sortable : false,
							align : 'center',
							renderer : function(v, p) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "��λ����",
							dataIndex : 'positionTypeName',
							width : 180,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "��˾Id",
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
				searchEmptyText : '���˵���Ż����Ʋ�ѯ',
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
							header : "�����˵����",
							dataIndex : 'pid',
							width : 70,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "�����˵�����",
							dataIndex : 'pname',
							width : 130,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "�˵����",
							dataIndex : 'id',
							width : 70,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "�˵�����",
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
				searchEmptyText : '��Ȩ�޵�ID���Ż����Ʋ�ѯ',
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
							header : "Ȩ�޵�ID",
							dataIndex : 'conpointId',
							width : 60,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "Ȩ�޵���",
							dataIndex : 'conpointNum',
							width : 70,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "Ȩ�޵�����",
							dataIndex : 'conpointName',
							width : 80,
							sortable : false,
							align : 'left',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "�˵�ID",
							dataIndex : 'menuId',
							width : 60,
							sortable : false,
							align : 'center',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "�˵�����",
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
				searchEmptyText : '����ؼƻ���Ż��ر����ѯ',
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
							header : "��ؼƻ����",
							dataIndex : 'sqlMonitorId',
							width : 50,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "��ر���",
							dataIndex : 'sqlMonitorTitle',
							width : 100,
							sortable : false,
							align : 'left',
							renderer : function(v, p) {
								p.attr = 'ext:qtip="' + v + '"';
								return Ext.util.Format.htmlEncode(v);
							}
						}, {
							header : "��ؼƻ�״̬",
							dataIndex : 'status',
							width : 50,
							sortable : false,
							align : 'center',
							renderer : function(v) {
								if (v == 'Y') {
									return "<p style='color:green'>��������</p>";
								}
								if (v == 'N') {
									return "<p style='color:red'>ֹͣ����</p>";
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
				searchEmptyText : '����Ż������ѯ',
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