Ext.onReady(function() {
	// tips
	Ext.QuickTips.init();
  	$('#hideFrame').bind('load', promgtMsg);
}
);


function dictTypeCreateSave(){
	if($("#dictTypeName").val()==""){
		warn("����д������� .");
		return;
	}
	createShadeDiv();
	var form = window.document.forms[0];
	form.action = appUrl + "/dict/dictAction!CreateDictType.htm";
	form.target = "hideFrame";
	form.submit();
}


function dictCreateSave(){
	if($("#dictTypeId").val()==""){
		warn("����ѡ���ֵ����Ͳ�������鿴�ֵ���Ŀ��.");
		return;
	}
	createShadeDiv();
	var form = window.document.forms[0];
	form.action = appUrl + "/dict/dictAction!CreateDict.htm";
	form.target = "hideFrame";
	form.submit();
}



function dictTypeUpdateSave(){
	if($("#dictTypeName").val()==""){
		warn("����д������� .");
		return;
	}
	createShadeDiv();
	var form = window.document.forms[0];
	form.action = appUrl + "/dict/dictAction!UpdateDictType.htm";
	form.target = "hideFrame";
	form.submit();
}


function dictUpdateSave(){
	if($("#itemName").val()==""){
		warn("����д�ֵ���.");
		return;
	}
	if($("#itemValue").val()==""){
		warn("����д�ֵ�ֵ.");
		return;
	}
	createShadeDiv();
	var form = window.document.forms[0];
	form.action = appUrl + "/dict/dictAction!UpdateDict.htm";
	form.target = "hideFrame";
	form.submit();
}





function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	//	loadM.hide();
	if (failResult != "") {
		
		Ext.Msg.show( {
			title : '����',
			msg : failResult,
			buttons : Ext.Msg.OK,
			fn : function(btn) {
				if (btn == 'ok') {
					closeShadeDiv();
				}
			},
			icon : Ext.Msg.ERROR
		});
	} else {
		Ext.Msg.show( {
			title : '��Ϣ',
			msg : successResult,
			buttons : Ext.Msg.OK,
			fn : function(btn) {
				if (btn == 'ok') {
					closeShadeDiv();
					window.opener.search();
					window.opener.searchDict($("#dictTypeId").val());
				}
			},
			icon : Ext.Msg.INFO
		});
	}
}


function warn(msg) {
	Ext.Msg.show({
				title : '����',
				msg : msg,
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.WARNING
			});
}