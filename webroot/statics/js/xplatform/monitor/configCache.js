Ext.onReady(function() {
			Ext.QuickTips.init();

			$('#hideFrame').bind('load', promgtMsg);

		});

function config(operate) {

	createShadeDiv();

	$('#operate').val(operate);
	$('#key').val($('#' + operate + 'Key').val());

	var form = window.document.forms[0];
	form.action = "cacheMonitorAction!configCache.htm";
	form.target = "hideFrame";
	form.submit();

}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var successResult = hideFrame.contentWindow.successResult;
	closeShadeDiv();
	$('#' + $('#operate').val() + 'Result').val(successResult);

}