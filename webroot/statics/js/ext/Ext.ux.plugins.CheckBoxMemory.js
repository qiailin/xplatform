Ext.ns('Ext.ux.plugins.CheckBoxMemory');
/**
 * 为CheckboxSelectionModel增加保持状态的功能
 * 
 * @alias Ext.plugins.GridHotKey
 * @author Zhijun Yuan zhijun.yuanzj@alibaba-inc.com
 */
Ext.ux.plugins.CheckBoxMemory = Ext.extend(Object, {
			constructor : function(config) {
				if (!config)
					config = {};

				this.prefix = 'id_';
				this.items = {};
				this.idProperty = config.idProperty || 'id';
			},

			init : function(grid) {
				this.grid = grid;
				this.view = grid.getView();
				this.store = null;
				this.sm = grid.getSelectionModel();
				this.sm.on('rowselect', this.onSelect, this);
				this.sm.on('rowdeselect', this.onDeselect, this);
				this.view.on('refresh', this.reConfigure, this);
				this.grid.totalSelection = new Ext.util.MixedCollection(false,
						function(o) {
							return o.id;
						});
			},

			reConfigure : function() {
				this.store = this.grid.getStore();
				this.store.on('clear', this.onClear, this);
				this.store.on('datachanged', this.restoreState, this);
			},

			onSelect : function(sm, idx, rec) {
				this.items[this.getId(rec)] = true;
				if (this.grid.totalSelection) {
					this.grid.totalSelection.add(rec);
				}
			},

			onDeselect : function(sm, idx, rec) {
				delete this.items[this.getId(rec)];
				if (this.grid.totalSelection) {
					this.grid.totalSelection.remove(rec);
				}
			},

			restoreState : function() {
				if (this.store != null) {
					var i = 0;
					var sel = [];
					var isAllSelected = true;
					var els = Ext.select("*[class=x-grid3-hd-checker]");
					var hd;
					if (els) {
						hd = Ext.fly(els.first().dom.parentNode);
					}
					this.store.each(function(rec) {
								var id = this.getId(rec);
								if (this.items[id] === true) {
									sel.push(i);
								} else {
									isAllSelected = false;
								}
								++i;
							}, this);
					if (hd) {
						if (isAllSelected) {
							hd.addClass('x-grid3-hd-checker-on');
						} else {
							hd.removeClass('x-grid3-hd-checker-on');
						}
					}
					if (sel.length > 0) {
						this.sm.selectRows(sel);
					}
				}
			},

			onClear : function() {
				var sel = [];
				this.items = {};
			},

			getId : function(rec) {
				return rec.get(this.idProperty);
			}
		});
