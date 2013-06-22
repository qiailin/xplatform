var __last_login_passport__ = "__last_login_passport__";

function setPassport() {
	var lastuser = $.cookie(__last_login_passport__);

	if (lastuser != "" && lastuser != null) {
		document.forms[0].passport.value = lastuser;
		document.forms[0].password.focus();
	} else {
		document.forms[0].passport.focus();
		document.forms[0].passport.select();
	}
}

function setPassportCookies() {
	$.cookie(__last_login_passport__, null);

	var passport = document.forms[0].passport.value;

	$.cookie(__last_login_passport__, passport, {
				expires : 30,
				path : '/' + appName + '/',
				domain : domain
			});

	_gaq.push(['_trackEvent', 'Home', 'Login', passport]);
}

function reg() {
	window.document.forms[0].action = "";
	window.document.forms[0].submit();

	_gaq.push(['_trackEvent', 'Home', 'Register']);
}

function forgetPasswd() {
	var passport = document.forms[0].passport.value;

	var WWidth = 600;
	var WHeight = 300;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	window.open(appUrl
					+ "/login/forgetPasswd.htm"
					+ ((passport == '')
							? ''
							: ('?passport=' + encodeURIComponent(passport))),
			"winSub", "left=" + WLeft + ",top=" + WTop + ",width=" + WWidth
					+ ",height=" + WHeight
					+ ",toolbar=no,menubar=no,scrollbars=yes");
}
