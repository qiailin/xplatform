Ext.onReady(function() {

	Ext.QuickTips.init();

	var tree = new Ext.tree.TreePanel({
				region : 'west',
				split : true,
				width : 210,
				useArrows : true,
				autoScroll : true,
				animate : true,
				enableDD : true,
				containerScroll : true,
				border : false,
				dataUrl : appUrl
						+ '/menu/menuTreeAjax!getMenuTreeListByAjax.htm',
				root : {
					nodeType : 'async',
					text : 'IMS',
					draggable : false,
					id : '1'
				}
			});

	var src = custType == 'X' ? "" : (custType == 'O' ? appUrl
			+ "/news/newsAction!newsIndex.htm" : imgUrl
			+ "/image/login/homeCopy.jpg");

	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [{
			region : "north",
			border : false,
			height : 89,
			html : '<iframe id="headFrame" name="headFrame" frameborder="no" width="100%" scrolling="no" height="100%" src="loginAction!headMenu.htm" />'
		}, tree, {
			region : 'center',
			border : false,
			autoScroll : true,
			html : '<iframe id="mainRight" name="mainRight" frameborder="no" width="100%" height="100%" src='
					+ src + ' />'
		}]
	});

	tree.getRootNode().expand();

	tree.on('click', function(node) {
				var href = node.attributes.href;
				if (undefined != href) {
					// Google Analytics
					_gaq.push(['_trackPageview', href]);
				}
			});
});