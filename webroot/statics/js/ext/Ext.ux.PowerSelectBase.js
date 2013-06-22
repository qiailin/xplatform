Ext.ux.PowerSelectBase = function(config){
	Ext.useShims = true;
	this.canCollapse = true;
	this.config = Ext.apply({}, config, {
		     width: 700,
		    height: 350,
		  pageSize: 10,
		   paginal: false,
		 creatable: true,
		 deletable: false,
		 updatable: false,
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
	valueDataIndex: []
	});
	return {		
		powerExpand : (function(config){
			return function(preventLoad){
				if(config.beforeExpand){
					if(typeof config.beforeExpand == 'function'){
						if(!(config.beforeExpand)()){
							return
						}
					}
				}
		        if(this.isPowerExpanded() ){
		            return;
		        }
		        if(!preventLoad && config.triggerAction == 'power'){
					this.loadPowerStore();
		        }
	        	this.power.alignTo(config.trigger?config.trigger:this.wrap, "tl-bl?");
	        	if(this.grid&&!this.grid.isVisible()){
	        		this.grid.show(false);
	        	}
	        	this.power.show(false);
		        this.power.getShim().setLeft(this.power.getShim().getLeft()-(Ext.isIE6 ? 10:0));
		        Ext.getDoc().on('mousewheel', this.powerCollapseIf, this);
		        Ext.getDoc().on('mousedown', this.powerCollapseIf, this);
		        if(!config.trigger){
		        	this.fireEvent('expand', this, true);
		        }
			};
		})(this.config), // eo powerExpand
		
		loadPowerStore: (function(config){
			return function() {
		        var store = this.grid.getStore();
				store.fireEvent("clearCheckBoxMemeory");
		        if( !( store.data && store.data.getCount() > 0) || config.reload ) {
		        	var params = config.params ? ( typeof config.params == 'object' ?
							   	config.params : typeof config.params == 'function' ?
							   	(config.params)(/* no params */) : {} ) : {};
					for( key in params ) {
			        	store.baseParams[key] = params[key];
					}
		        	if( this.grid.paginal ) {
		        		store.load( {params:{ start: 0, limit: this.grid.pageSize }} );
		        	} else {
		        		store.load();
		        	}
		        }
			};
		})(this.config), // eo loadPowerStore
		
		// modify by xujiakun 2011-5-11
		setConfigParams : (function(config) {
			return function(params) {
				config.params = params;
				config.reload = true;
			};
		})(this.config),
		
		// private method
		powerCollapse : function(){
	        if( !this.isPowerExpanded() ){
	            return;
	        }
	        if( this.cw ) this.cw.hide();
	        if( this.uw ) this.uw.hide();
	        this.power.hide();
	        Ext.getDoc().un('mousewheel', this.powerCollapseIf, this);
	        Ext.getDoc().un('mousedown', this.powerCollapseIf, this);
	        if(!config.trigger){
	        	this.fireEvent('collapse', this, true);
	        }
		}, // eo powerCollapse
		
		// private method
		isPowerExpanded : function(){
			return this.power && this.power.isVisible();
		}, // eo isPowerExpanded
	
	    // private
	    powerCollapseIf : function(e){
	    	if(config.trigger){
		        if(!e.within(this.power)&&(this.canCollapse==true||this.canCollapse==undefined)){
		            this.powerCollapse();
		        }
	    	}else{
	    		if(!e.within(this.wrap) && !e.within(this.power)&&(this.canCollapse==true||this.canCollapse==undefined)){
		            this.powerCollapse();
		        }
	    	}
	    },
	    
		// private method
		initGrid: (function(config){
			return function() {
	        	if(!this.power){
		            this.power = new Ext.Layer({ shadow: false, constrain: true, shim: true });
	        		if(config.triggerAction != 'tree'){
		            this.grid  = new Ext.ux.grid.PowerSearchGridPanel({
							region: 'center',
						viewConfig: config.viewConfig,
						   paginal: config.paginal,
						 creatable: config.creatable,
						 updatable: config.updatable,
						 deletable: config.deletable,
						searchable: config.searchable,
						 multiable: config.multiable,
				             width: config.multiable?config.width+20:config.width,
				            height: config.height,
						stripeRows: config.stripeRows,
					   columnLines: config.columnLines,
						     store: config.store,
							    cm: config.cm,
					      pageSize: config.pageSize,
				  searchParamName : config.searchParamName,
				  searchEmptyText : config.searchEmptyText,
					       plugins: [new Ext.plugins.HeaderAligning()],
					  enableHdMenu: false,
					  	  loadMask: config.loadMask
					});
					
					if(config.hasNag){
						config.grid = this.grid;
						this.powerInner = new Ext.ux.PowerSearchPanel(config);
						this.powerInner.render(this.power);
		        		this.power.appendChild(this.powerInner.getEl());
					}else{
						this.grid.render(this.power);
		        		this.power.appendChild(this.grid.getEl());
					}
					
		        	this.power.swallowEvent('mousedown');
		            this.power.setWidth(config.width);
		            this.power.setHeight(config.height);
		            this.power.hide();
		            this.grid.hide();
		        	if( config.creatable ) {
						this.grid.on('showadd', function(gd) {
							this.initCreateWindow();
							// this.cw.setPosition((gd.width * (1-0.618)),
							// (gd.height * (1-0.618)));
	    					this.cw.show();
						}, this);
		        	}
		        	if( config.deletable ) {
						this.grid.on('rowdeleted', function(grid, r, row, col) {
							var promptMsg;
							if(config.deletePromptMsg){
								if(typeof config.deletePromptMsg == 'function'){
									promptMsg = (config.deletePromptMsg)(grid, r, row, col);
								}else{
									promptMsg = config.deletePromptMsg;
								}
							}else{
								promptMsg = "确定删掉此记录吗？";
							}
							if(window.confirm(promptMsg)){
								Ext.Ajax.request({
								   url: config.deleteURL,
								   scope: this,
								   success: function() {
										this.reloadGrid();
								   },
								   failure: Ext.emptyFn,
								   params: config.dparams ? ( typeof config.dparams == 'object' ?
								   	config.dparams : typeof config.dparams == 'function' ?
								   	(config.dparams)(grid,r,row,col) : {} ) : {}
								});
							}
// Ext.MessageBox.confirm('确认删除', 'Are you sure you want to do that?',
// function(btn){
// if(btn=='ok'){
// Ext.Ajax.request({
// url: config.deleteURL,
// scope: this,
// success: function() {
// this.reloadGrid();
// },
// failure: Ext.emptyFn,
// params: config.dparams ? ( typeof config.dparams == 'object' ?
// config.dparams : typeof config.dparams == 'function' ?
// (config.dparams)(grid,r,row,col) : {} ) : {}
// });
// }
// },this);
						}, this);
		        	}
		        	if( config.updatable ) {
						this.grid.on('rowupdated', function(grid, r, row, col) {
							this.initUpdateWindow(r);
	    					this.uw.show();
						}, this);
		        	}
					
					this.grid.on('rowselected', function(r,i) {
						/*
						 * if( this.store ) { var add = []; for( var i = 0, tmp =
						 * [].concat(r), n = tmp.length; i < n; i++ ) { if (
						 * this.store.getById(tmp[i].id) ) { continue; }
						 * add.push(tmp[i]); } this.store.add(add); }
						 */
						this.onSelect(r,i);
			            this.powerCollapse();
			            if(!config.trigger){
			            	this.focus();
			            }
					}, this);
					
					this.grid.on('close', function() {
			            this.powerCollapse();
					}, this);
					
					// "修改"页面时初始化下拉框的值
					this.grid.store.on('load', function(){
						for( var key in config.hiddenValue ) {
							var i = this.grid.store.findBy(function(r){
								return r.get(key) == config.hiddenValue[key];
							});
							if( i != -1 ) { // found
								var r = this.grid.store.getAt(i);
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
					
					// modify by xujiakun 2011-5-16
					// this.loadPowerStore();
	        		}
	        		
	        		if(config.triggerAction == 'tree'){
	        			this.tree = new Ext.ux.tree.PowerTreePanel({
								region: 'center',
							 creatable: config.creatable,
							 updatable: config.updatable,
							 deletable: config.deletable,
							searchable: config.searchable,
							 multiable: config.multiable,
					             width: config.multiable?config.width+20:config.width,
					            height: config.height,
					            titleText: config.titleText,
					            autoScroll : config.autoScroll,
								autoHeight : config.autoHeight||false,
								autoWidth : config.autoWidth||false,
						        loader: new Ext.tree.TreeLoader({
						            dataUrl:config.url
						        }),
						        root : new Ext.tree.AsyncTreeNode({
							        text: config.rootText||'',
							        draggable:false,
							        id:config.rootId
							    }),
        					rootVisible:config.rootVisible
						});
						this.tree.render(this.power);
		        		this.power.appendChild(this.tree.getEl());
	        			this.power.swallowEvent('mousedown');
			            this.power.setWidth(config.width);
			            this.power.setHeight(config.height);
			            this.power.hide();
			            this.tree.on('close', function() {
				            this.powerCollapse();
						}, this);
						
						this.tree.on('click', function(n){
					    	var sn = this.selModel.selNode || {}; 		
							if( config.hiddenField ) {
								var f1 = Ext.get(config.hiddenField);
								if( f1 ) f1.set({value:n.id});
							} 
							if(config.displayField){
								var f2 = Ext.get(config.displayField);
								if( f2 ) f2.set({value:n.text});									
							}
			            	this.fireEvent("nodeselected", n);
						});
						
						this.tree.on('nodeselected', function(n){
				            this.powerCollapse();
						},this);
	        		}
				}
			}; 
		})(this.config),
		
		reloadGrid: (function(config){
			return function() {
				if(this.grid.searchField){
					this.grid.searchField.onTrigger1Click();
				}
				this.grid.getStore().reload( {
					params: config.paginal ? { start: 0, limit: config.pageSize } : {},
					callback: function() {
						// this.getSelectionModel().selectFirstRow();
					},
					scope: this.grid	
				});
			}
		})(this.config),
		
		// private method
	    initCreateWindow: (function(config){
			return function() {
				if(this.cw){
					return
				}
				var tpl = '<span style="color:red;padding-left:15px;">{0}</span>';
				var form = Ext.apply({}, config.createForm, {
					xtype : 'form',
					border: false,
					defaultType : 'textfield',
					defaults:{
						width : 200,
						labelSeparator : '',
						labelStyle:'text-align:right',
						validationEvent: 'change',
						validateOnBlur: false,
						stripCharsRe: /^\s+|\s+$/g
					}
				});
				this.cw  = new Ext.Window({
					title : '新建',
					width : config.formWidth||400,
					shadow: false,
					hideParent: true,
					resizable: false,
					closeAction: 'hide',
					defaults: { bodyStyle: 'padding:15px' },
					items : [form, {xtype:'tbtext', text: String.format(tpl, '&#160;')}],
					buttons: [{
						text: '保存',
						scope: this,	// combo
						handler: function(btn, e){
							var fm = this.cw.items.itemAt(0).getForm();
							var tip = this.cw.items.itemAt(1).getEl();
							tip.innerHTML = String.format(tpl, '&#160;');
							if( fm.isValid() ){
								btn.setDisabled(true);
								var params = config.cparams;
								params = Ext.apply(params ? ( typeof params == 'object' ?
					   				params : typeof params == 'function' ?
					   				(params)() : {} ) : {}, fm.getValues());
					   			for( key in params ) {
					   				params[key] = encodeURIComponent(params[key]);
					   			}
								Ext.Ajax.request({
								   	url: config.submitURL,
									method: 'post',
								   	failure: function(){btn.setDisabled(false);},
								   	params: params,
									scope: this,
								   	success: function(r, p) {
								   		var result = Ext.decode(r.responseText);
										if( result) {
											// 保存成功
											if(result.type == '1' ){
												this.reloadGrid();
												this.cw.hide();
												tip.innerHTML = String.format(tpl, '&#160;');
											}else{
												var msg = result && result.message || '保存失败!';
												tip.innerHTML = String.format(tpl, msg);											
											}
										} else {
											var msg = result && result.message || '保存失败!';
											tip.innerHTML = String.format(tpl, msg);
										}
										btn.setDisabled(false);
								   	}
								});
							}
						}}, {
						text: '关闭',
						scope: this,	// combo
						handler: function(){
							this.cw.hide();
						}
					}],
					buttonAlign: 'center'
				});
				this.cw.on('hide', function(){
					this.itemAt(0).getForm().reset();
					var tip = this.itemAt(1).getEl();
					tip.innerHTML = String.format(tpl, '&#160;');
				}, this.cw.items);
				this.cw.on('show', function(){
					this.canCollapse = false;
				}, this);
				this.cw.on('hide', function(){
					this.canCollapse = true;					
				}, this);
				this.cw.render(this.power);
				this.cw.body.setStyle('background-color','white');
			};
		})(this.config) ,
		
		// private method
	    initUpdateWindow: (function(config){
			return function(record) {
				var tpl = '<span style="color:red;padding-left:15px;">{0}</span>';
				var form = Ext.apply({}, (config.updateForm)(record), {
					xtype : 'form',
					border: false,
					defaultType : 'textfield',
					defaults:{
						width : 200,
						allowBlank : false,
						labelSeparator : '',
						labelStyle:'text-align:right',
						validationEvent: 'change',
						validateOnBlur: false,
						stripCharsRe: /^\s+|\s+$/g
					}
				});
				this.uw  = new Ext.Window({
					title : '新建',
					width : config.formWidth||this.grid.width,
					shadow: false,
					hideParent: true,
					resizable: false,
					closeAction: 'hide',
					defaults: { bodyStyle: 'padding:15px' },
					items : [form, {xtype:'tbtext', text: String.format(tpl, '&#160;')}],
					buttons: [{
						text: '保存',
						scope: this,	// combo
						handler: function(btn, e){
							var fm = this.uw.items.itemAt(0).getForm();
							var tip = this.uw.items.itemAt(1).getEl();
							tip.innerHTML = String.format(tpl, '&#160;');
							if( fm.isValid() ){
								btn.setDisabled(true);
								var params = config.uparams;
								params = Ext.apply(params ? ( typeof params == 'object' ?
					   				params : typeof params == 'function' ?
					   				(params)(record) : {} ) : {}, fm.getValues());
					   			for( key in params ) {
					   				params[key] = encodeURIComponent(params[key]);
					   			}
								Ext.Ajax.request({
								   	url: config.updateURL,
									method: 'post',
								   	failure: function(){btn.setDisabled(false);},
								   	params: params,
									scope: this,
								   	success: function(r, p) {
								   		var result = Ext.decode(r.responseText);
										if( result) {
											// 保存成功
											if(result.type == '1' ){
												this.reloadGrid();
												this.uw.hide();
												tip.innerHTML = String.format(tpl, '&#160;');
											}else{
												var msg = result && result.message || '保存失败!';
												tip.innerHTML = String.format(tpl, msg);											
											}
										} else {
											var msg = result && result.message || '保存失败!';
											tip.innerHTML = String.format(tpl, msg);
										}
										btn.setDisabled(false);
								   	}
								});
							}
						}}, {
						text: '关闭',
						scope: this,	// combo
						handler: function(){
							this.uw.hide();
						}
					}],
					buttonAlign: 'center'
				});
				this.uw.on('hide', function(){
					this.itemAt(0).getForm().reset();
					var tip = this.itemAt(1).getEl();
					tip.innerHTML = String.format(tpl, '&#160;');
				}, this.uw.items);
				this.uw.on('show', function(){
					this.canCollapse = false;
				}, this);
				this.uw.on('hide', function(){
					this.canCollapse = true;					
				}, this);
				this.uw.render(this.power);
				this.uw.body.setStyle('background-color','white');
			};
		})(this.config) ,
		
		onSelect : (function(config){
			return function(record, index) {
				if(typeof config.onSelect == 'function'){
// setTimeout(function(){
				        Ext.MessageBox.show({
				           title: 'Please wait',
				           msg: 'Loading items...',
				           progressText: 'Initializing...',
				           width:300,
				           progress:true,
				           closable:false
				       });
						(config.onSelect)(record, index);
						Ext.MessageBox.hide();
// },0);
				}
			}; 
		})(this.config)
	}
};