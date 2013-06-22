/**
 * @namespace Ext.plugins.HeaderAligning
 */
Ext.ns('Ext.plugins.HeaderAligning');

/**
 * 列头对齐格式设置
 * 
 * @alias Ext.plugins.HeaderAligning
 * @author Tingjia Chen tingjia.chentj@alibaba-inc.com
 */
Ext.plugins.HeaderAligning = function(align) {
	this.align = align || 'center';
	// call parent
	Ext.plugins.HeaderAligning.superclass.constructor.call(this);
};

Ext.extend(Ext.plugins.HeaderAligning, Ext.util.Observable, {
			/**
			 * @method
			 */
			init : function(grid) {
				var view = grid.getView();
				var headerAlign = this.align;
				Ext.apply(view, {
							getColumnStyle : function(col, isHeader) {
								var style = !isHeader
										? (this.cm.config[col].css || '')
										: '';
								style += 'width:' + this.getColumnWidth(col)
										+ ';';
								if (this.cm.isHidden(col)) {
									style += 'display:none;';
								}
								/* added by chentingjia */
								if (isHeader) {
									style += 'text-align:' + headerAlign + ';';
									style += 'color: #5D6B82;';
									/* end */
								} else {
									var align = this.cm.config[col].align;
									if (align) {
										style += 'text-align:' + align + ';';
									}
								}
								return style;
							} // eo getColumnStyle
						});

			}
		});