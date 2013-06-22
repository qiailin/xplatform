Ext.ns('Ext.ux.grid.PowerSearchGridPanel');
/**
 * @class Ext.ux.grid.PowerSearchGridPanel
 * @extends Ext.grid.GridPanel
 * 
 * @constructor
 * @param {Object}
 *            config The config object
 */
Ext.ux.grid.PowerSearchGridPanel = Ext.extend(Ext.grid.GridPanel, {
	/**
	 * @cfg {Boolean} multiable 是否支持多选
	 */
	multiable : true,

	/**
	 * @cfg {Boolean} searchable 是否支持查找
	 */
	searchable : true,

	/**
	 * @cfg {Boolean} creatable 是否支持快速新建
	 */
	creatable : true,

	/**
	 * @cfg {Boolean} paginal 是否支持分页显示
	 */
	paginal : true,

	// private
	initComponent : function() {
		if (this.deletable || this.updatable) {
			var actionConfig = [];
			if (this.updatable) {
				actionConfig.push({
							iconCls : 'icon-edit-record'
						});
			}
			if (this.deletable) {
				actionConfig.push({
							iconCls : 'icon-del-table'
						});
			}
			var da = new Ext.ux.grid.RowActions({
						actions : actionConfig
					});
			da.on('action', function(grid, r, action, row, col, e) {
						if (action == 'icon-del-table') {
							e.stopEvent();
							grid.fireEvent("rowdeleted", grid, r, row, col);
						}
						if (action == 'icon-edit-record') {
							e.stopEvent();
							grid.fireEvent("rowupdated", grid, r, row, col);
						}
					});
			this.plugins = this.plugins ? [da].concat(this.plugins) : [da];
			this.cm.setConfig([da].concat(this.cm.config), true);
		}
		if (this.multiable) {
			this.sm = new Ext.grid.CheckboxSelectionModel();
			Ext.apply(this.sm, {
						onMouseDown : function(e, t) {
							e.stopEvent();
							var row = e.getTarget('.x-grid3-row');
							if (row) {
								var index = row.rowIndex;
								if (this.isSelected(index)) {
									this.deselectRow(index);
								} else {
									this.selectRow(index, true);
								}
							}
						}
					});
			this.cm.setConfig([this.sm].concat(this.cm.config), true);
			if (this.store && this.store.reader) {
				this.plugins.push(new Ext.ux.plugins.CheckBoxMemory({
							idProperty : this.store.reader.meta.id
						}));
			}
		} else {
			this.sm = this.sm || new Ext.grid.RowSelectionModel();
		}

		this.tbar = [];
		if (this.searchable) {
			this.tbar.push('查找: ');
			this.searchField = new Ext.ux.grid.SearchField({
						pageSize : this.pageSize,
						paginal : this.paginal,
						store : this.store,
						paramName : this.searchParamName || 'search',
						emptyText : this.searchEmptyText || ''
					});
			this.tbar.push(this.searchField);
		}
		this.tbar.push('->');
		if (this.multiable) {
			this.tbar.push({
						text : '选定',
						iconCls : 'btn_icon_select',
						handler : function() {
							if (this.totalSelection.items.length > 0) {
								this.fireEvent("rowselected",
										[].concat(this.totalSelection.items));
							}
						},
						scope : this
					});
		}
		if (this.creatable && this.multiable) {
			this.tbar.push('-');
		}
		if (this.creatable) {
			this.tbar.push({
				text : '新建',
				iconCls : 'btn_icon_add',
				handler : function() {
					this.fireEvent("showadd", this);
				},
				scope : this
					// grid
				});
		}
		this.tbar.push({
			iconCls : 'btn_icon_close',
			handler : function() {
				this.fireEvent("close", this);
			},
			scope : this
				// grid
			});
		if (this.paginal) {
			this.bbar = this.bbar || new Ext.PagingToolbar({
						pageSize : this.pageSize,
						store : this.store,
						displayInfo : true,
						displayMsg : '共 {2} 条记录',
						emptyMsg : "没有找到记录！"
					});
		}

		Ext.ux.grid.PowerSearchGridPanel.superclass.initComponent.call(this);

		this.addEvents(
				/**
				 * @event rowselected Fires after row(s) be selected. <br />
				 * @param {Array/Object}
				 *            array of Record / Record
				 */
				'rowselected',
				/**
				 * @event showadd
				 * @param {GridPanel}
				 *            grid
				 */
				'showadd',
				/**
				 * @event rowdeleted Fires after row be deleted. <br />
				 * @param {Object}
				 *            Record
				 * @param {Number}
				 *            row
				 * @param {Number}
				 *            col
				 */
				'rowdeleted',
				/**
				 * @event close
				 * @param {GridPanel}
				 *            grid
				 */
				'close');
	},

	// private
	initEvents : function() {
		Ext.ux.grid.PowerSearchGridPanel.superclass.initEvents.call(this);
		// 单选时单击选中／多选时双击选中
		if (this.multiable) {
			this.on('rowdblclick', function(g, i, e) {
						if (this.totalSelection.items.length > 0) {
							this.fireEvent("rowselected", []
											.concat(this.totalSelection.items));
						}
					}, this);
		} else {
			this.on('rowclick', function(g, i, e) {
						if (this.getSelectionModel().hasSelection()) {
							this.fireEvent("rowselected", this
											.getSelectionModel().getSelected(),
									i);
						}
					}, this);
		}

		this.keyNav = new Ext.KeyNav(this.getGridEl(), {
					"enter" : function(e) {
						if (this.getSelectionModel().hasSelection()) {
							this.fireEvent("rowselected", this
											.getSelectionModel().getSelected(),
									this.getSelectionModel().last);
						}
					},
					scope : this,
					forceKeyDown : true
				});
	}
});
Ext.reg('powersearch', Ext.ux.grid.PowerSearchGridPanel);

Ext.ux.grid.SearchField = Ext.extend(Ext.form.TwinTriggerField, {
			initComponent : function() {
				if (!this.store.baseParams) {
					this.store.baseParams = {};
				}
				Ext.ux.grid.SearchField.superclass.initComponent.call(this);
				this.on('specialkey', function(f, e) {
							if (e.getKey() == e.ENTER) {
								this.onTrigger2Click();
							}
						}, this);
			},

			validationEvent : false,
			validateOnBlur : false,
			trigger1Class : 'x-form-clear-trigger',
			trigger2Class : 'x-form-search-trigger',
			hideTrigger1 : true,
			width : 180,
			hasSearch : false,
			paramName : 'search',

			// 清除查找条件并恢复列表到初始结果
			onTrigger1Click : function() {
				if (this.hasSearch) {
					delete this.store.baseParams[this.paramName];
					this.store.fireEvent("clearCheckBoxMemeory");
					this.store.load({
								params : this.paginal ? {
									start : 0,
									limit : this.pageSize
								} : {}
							});
					this.el.dom.value = '';
					this.triggers[0].hide();
					this.hasSearch = false;
					this.focus();
				}
			},

			// 查找按钮响应
			onTrigger2Click : function() {
				var v = this.getRawValue();
				if (v.length < 1) {
					this.onTrigger1Click();
					return;
				}
				this.store.baseParams[this.paramName] = encodeURIComponent(v);
				this.store.fireEvent("clearCheckBoxMemeory");
				this.store.load({
							params : this.paginal ? {
								start : 0,
								limit : this.pageSize
							} : {}
						});
				this.hasSearch = true;
				this.triggers[0].show();
				this.focus();
			}
		});