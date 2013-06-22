function showDetailMsg() {
	var flag = document.getElementById("detailMsgFlag").value;
	if (flag != "0") {
		document.getElementById("detailMsg").style.display = "block";
	}
}

function closeDiv() {
	document.getElementById("detailMsg").style.display = "none";
}

function gotoProcessEvent() {
	var form = window.document.forms[0];
	// form.action = appUrl + "/wfe/eventAction!searchProcessEvent.htm";
	form.target = "mainRight";
	form.method = "post";
	form.submit();
}
