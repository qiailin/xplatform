/**
 * @namespace Ext.plugins.ComplexGridCombox
 */
Ext.ns('Ext.plugins.ComplexGridCombox');

/**
 * 在列表中加入科目选择列
 * 
 * @alias Ext.plugins.ComplexGridCombox
 * @author Tingjia Chen tingjia.chentj@alibaba-inc.com
 */
Ext.plugins.ComplexGridCombox = function(config){
	this.config = Ext.apply({}, config, {
		     width: 420,
		    height: 300,
		  pageSize: 10,
		   paginal: false,
		 creatable: true,
		 deletable: false,
		searchable: true,
		 multiable: false,
		stripeRows: true,
	   columnLines: true,
		  moreText: null,
	 triggerAction: null,		// 'power'
	     deleteURL: null,
	     submitURL: null,
	        params: null, // for datasource
	       dparams: null, // for delete
	       cparams: null, // for create
	        reload: true, // reload on expand
	     autoField: null,
	valueDataIndex: [],
		  loadMask: true
	});
};

Ext.extend(Ext.plugins.ComplexGridCombox, Ext.util.Observable, {
	
	/**
	 * @param combo /
	 *            grid
	 * @method
	 */
    init : function(combo){
    	if( combo.getColumnModel ) { // combo is a grid
	    	var cm = combo.getColumnModel();
	    	cm.getColumnsBy( function(c, i) {
	    		if( c.editor ) {
	    			c.editor.ignoreNoChange = true;
	    			c.editor.dataIndex = c.dataIndex;
	    			c.editor.valueDataIndex = [].concat(c.valueDataIndex || []);
	    			c.editor.startEdit = c.editor.startEdit.createInterceptor(function(el, value) {
	    				this.field.cellEl = Ext.get(el);
	    				this.field.editing = false;
	    				this.field.col = this.col;
	    				this.field.row = this.row;
						this.field.owerGrid = combo;
	    				this.field.record = this.record;
	    				this.field.dataIndex = this.dataIndex;
	    				this.field.valueDataIndex = this.valueDataIndex;
						this.field.validateOnBlur = false;
	    				return true;
	    			}, c.editor).createSequence(function(el, value) {
	    				this.field.editing = true;
	    			}, c.editor);
	    			
				    c.editor.completeEdit = function(remainVisible){
				        if(!this.editing){
				            return;
				        }
				        var v = this.getValue();
				        if(String(v) === String(this.startValue) && this.ignoreNoChange){
				            this.editing = false;
				            this.hide();
				            return;
				        }
				        if(this.revertInvalid !== false && !this.field.isValid()){
				            v = this.startValue;
				            this.cancelEdit(true);
				        }
				        if(this.fireEvent("beforecomplete", this, v, this.startValue) !== false){
				            this.editing = false;
				            if(this.updateEl && this.boundEl){
				                this.boundEl.update(v);
				            }
				            if(remainVisible !== true){
					            if(this.field.powerExpand){
									this.field.fireEvent("change",this.field);
					            }
				                this.hide();
				            }
				            this.fireEvent("complete", this, v, this.startValue);
				        }
				    };

	    		}
	    	});
	    	return;
    	}
    	
    	// combo is a combobox
    	combo.addEvents(
            /**
			 * @event beforeexpand
			 * @param {Ext.form.ComboBox}
			 *            combo This combo box
			 */
            'beforeexpand',
            /**
			 * @event afterexpand
			 * @param {Ext.form.ComboBox}
			 *            combo This combo box
			 */
            'afterexpand',
            'restrictHeight'
        );
        
		Ext.apply(combo, {
			initList: combo.initList.createSequence((function(config){
				return config.moreText ? function() {
					if(!this.more){
	                	this.more = this.list.createChild();
	                	this.morebar = new Ext.Toolbar({
	                		renderTo: this.more
	                	});
	                	this.morebar.add({
	                		text: config.moreText,
	                		handler: function() {
        						this.list.setHeight(10);
	                			this.powerExpand();
	                		},
	                		scope: this
	                	});
	                	this.assetHeight += this.more.getHeight();
					}
				} : Ext.emptyFn;
			})(this.config), combo).createInterceptor((function(config){
				// "修改"页面时初始化下拉框的值
				return function(){
					if(config.hiddenValue){
						this.store.on('load', function(){
							for( var key in config.hiddenValue ) {
								var i = this.store.findBy(function(r){
									return r.get(key) == config.hiddenValue[key];
								});
								if( i != -1 ) { // found
									var r = this.store.getAt(i);
									this.value = r.get(this.displayField);
									for( var j = 0, l = config.valueDataIndex.length; j < l; j++ ) {
										var k = config.valueDataIndex[j];
										if( config.hiddenField ) {
											var f = Ext.get(config.hiddenField[k]);
											if( f ) f.set({value:r.get(k)});
										} else {
											this.record.set(k, v); // TODO fix
																	// 可能this.record会不存在
										}
									}
									this.initValue();
									break;
								}
							}
						}, this, {single: true});
					}
					return true;
				}
			})(this.config), combo).createInterceptor((function(config){
				return function() {
					if(!this.list&&this.store){
						if(config.autocomplete){
							this.store.load();
							// 实现autocomplete的多字段支持
							this.store.createFilterFn = (function(scope, method, field) {
								return function() {
									var fn = [];
									for( var i = 0, n = field.length; i < n; i++ ) {
										fn[i] = (method.apply(scope, [field[i]].concat(Array.prototype.slice.call(arguments, 1))));
										if( fn[i] === false ) return false;
									}
									return function(r) {
										var result = false;
										for( var i = 0, n = fn.length; i < n; i++ ) {
											result = result || (fn[i] ? (fn[i])(r) : false);
										}
										return result;
									};
								};
							})(this.store, this.store.createFilterFn, [].concat(config.autoField));
						}else{
							return false;
						}
					}
					return true;
				};
			})(this.config), combo), // eo initList
			
			onRender: combo.onRender.createSequence(function() {
				if (!this.lazyInit) {
					this.initGrid();
				} else {
					this.on('focus', this.initGrid, this, {	single : true });
				}
			}, combo), // eo onRender
			
			restrictHeight: combo.restrictHeight.createSequence(function() {
				this.fireEvent('restrictHeight', this);
			}, combo), // eo restrictHeight
			
			getValue: combo.getValue.createInterceptor(function() {
				if(this.forceSelection){
		            this.doForce();
		        }
		        return true;
		    }, combo), // eo getValue
			
			expand: combo.expand.createSequence(function() {
			    this.list.getShim().setLeft(this.list.getShim().getLeft()-(Ext.isIE6 ? 10:0));
		    }, combo), // eo expand
			
			onLoad: combo.onLoad.createSequence(function() {
			    try {this.list.getShim().setLeft(this.list.getShim().getLeft()-(Ext.isIE6 ? 10:0));}
			    catch(ex){}
		    }, combo), // eo onLoad
			
			doQuery: combo.doQuery.createSequence((function(config) {
				return function(q) {
		        	if( this.el.dom.value == '' || this.el.dom.value === null ) {
						// 当无值时清除其它相关字段值
						if( (q === undefined || q === null || q == '') ) {
							this.clearValue();
							for( var j = 0, l = config.valueDataIndex.length; j < l; j++ ) {
								var k = config.valueDataIndex[j];
								if( config.hiddenField ) {
									var f = Ext.get(config.hiddenField[k]);
									if( f ) f.set({value: ''});
								} else {
									if (this.record) { // add
										if(this.record.get(k) != undefined) {
											// this.record.set(k, ''); //
											// set会重新render，导致cell失去焦点？
											this.record.data[k] = '';
										}
									}
								}
							}
						}
		        	}
				};
			})(this.config), combo), // eo doQuery
		    
			initEvents: combo.initEvents.createSequence(function() {
				if( this.keyNav ) {
					this.keyNav['esc'] = this.keyNav['esc'].createSequence(
						function() {
							this.powerCollapse();
						}
					, this);
					this.keyNav['doRelay'] = this.keyNav['doRelay'].createInterceptor(
						function(foo, bar, hname) {
			                if(hname == 'esc' || this.scope.isPowerExpanded()){
			                   return Ext.KeyNav.prototype.doRelay.apply(this, arguments);
			                }
			                return true;
						}
					, this.keyNav);
				}
			}, combo), // eo initEvents
			
			onTriggerClick: combo.onTriggerClick.createInterceptor((function(config){
				if(config.triggerAction == 'power'){
					return function() {	
						// show grid
				        if (!this.disabled) {
							if (this.isPowerExpanded()) {
								this.powerCollapse();
								this.el.focus();
							} else {
								this.onFocus({});
								this.powerExpand();
								if(this.grid.getSelectionModel().hasSelection()&&!config.multiable){
									this.grid.view.focusRow(this.grid.getSelectionModel().last);
								}
								this.grid.focus();
							}
						}
						return false;
					};
				}else if(config.triggerAction == 'tree'){
					return function() {	
						// show tree
				        if (!this.disabled) {
							if (this.isPowerExpanded()) {
								this.powerCollapse();
								this.el.focus();
							} else {
								this.onFocus({});
								this.powerExpand();
							}
						}
						return false;
					}; 
				}
			})(this.config), combo).createInterceptor(function() {
				if(this.isExpanded()){
					return true;
				} else {
					return this.fireEvent('beforeexpand', this);
				}
			}).createSequence(function() {
				if(this.isExpanded()){
					this.fireEvent('afterexpand', this, this.list);
				}else if (this.isPowerExpanded()) {
					this.fireEvent('afterexpand', this, this.power, this.grid);
				}
			}), // eo onTriggerClick
			
			onSelect: combo.onSelect.createInterceptor((function(config){
				return function(record, index) {
					if(this.fireEvent('beforeselect', this, record, index) !== false){
						// TODO 暂未考虑多选情况
						// 使用值列来保存选定的结果
						for( var j = 0, l = config.valueDataIndex.length; j < l; j++ ) {
							var v = (config.formatValue || function(r, field){
					        	if( r.length ) {
					        		var values = [];
					        		for( var i = 0, len = r.length; i < len; i++ ) {
					        			values.push(r[i].get(field));
					        		}
					        		return values.join(', ');
					        	} else {
					        		return r.get(field);
					        	}
					        })(record, config.valueDataIndex[j]);
							if( config.hiddenField ) {
								var f = Ext.get(config.hiddenField[config.valueDataIndex[j]]);
								if( f ) f.set({value:v});
							} else {
								// modify by xujiakun 2011-5-10
								// this.record.data[config.valueDataIndex[j]] =
								// v;
								if (this.displayField != config.valueDataIndex[j]) {
									if (store && store.getAt(rowIndex)) {
										store.getAt(rowIndex).data[config.valueDataIndex[j]] = v;
									}
								}
							}
							if(config.multiable&&this.displayField==config.valueDataIndex[j]){
								var f = Ext.get(this.el.dom.id);
								if( f ) f.set({value:v});
							}
						}
						this.on('change', function() {
							if( this.record ) {
								var old = this.record.get(config.valueDataIndex[0]);
								this.record.data[config.valueDataIndex[0]] = '';
								this.record.set(config.valueDataIndex[0], old);
							}
						}, this, {single: true});
						if(config.multiable){
							return false;
						}else{
							return true;
						}
					}else{
						return false;
					}
				}; // eo return
			})(this.config), combo)// eo onSelect
			
		},new Ext.ux.PowerSelectBase(this.config)); // eo apply
    }
});
