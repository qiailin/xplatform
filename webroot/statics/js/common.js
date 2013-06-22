/**
 * �õ��ַ�������ʵ���ȣ�˫�ֽ��ַ��纺�ֻ�����������ֽ��ַ���.
 */
String.prototype.len = (function() {
	var re = /[^\x00-\xff]/g, xx = 'xx';

	return function() {
		return this.replace(re, xx).length;
	};
})();
String.prototype.getLength = String.prototype.len;

/**
 * ��ʽ���ַ����еı��ʽ��{\d}
 */
String.prototype.format = (function() {
	var re = /\{(?:\d+)\}/g;

	return function() {
		if (arguments.length == 0) {
			return this;
		}

		return this.replace(re, function(m, i) {
					return arguments[i] != null ? arguments[i] : m;
				});
	};
})();

/**
 * ȥ���ַ���ǰ��ո�.
 */
String.prototype.trim = (function() {
	var re = /^\s+|\s+$/g, s = '';

	return function() {
		return this.replace(re, s);
	};
})();

/**
 * ȥ���ַ�����߿ո�.
 */
String.prototype.trimLeft = (function() {
	var re = /^\s+/g, s = '';

	return function() {
		return this.replace(re, s);
	};
})();

/**
 * ȥ���ַ����ұ߿ո�.
 */
String.prototype.trimRight = (function() {
	var re = /\s+$/g, s = '';

	return function() {
		return this.replace(re, s);
	};
})();

/**
 * ȥ���ַ����е�html��ǩ.
 */
String.prototype.stripHTML = (function() {
	var re = /<(?:.|\s)*?>/g, s = '';

	return function() {
		return this.replace(re, s);
	};
})();

/**
 * ��ʽ������(#,###.##).
 * 
 * @param v
 * @param iScale
 *            ����λ��
 * @param showPreScale
 *            �Ƿ����Ĭ�ϵľ����ַ������������֣���ӡ�.00��
 */
Number.format = function(v, iScale, showPreScale) {
	if (v == null || v == '') {
		return '';
	}

	if (showPreScale == null || typeof(showPreScale) != 'boolean') {
		showPreScale = true;
	}

	if (typeof(iScale) == 'number') {
		if (iScale < 0)
			iScale = 0;
		if (iScale > 8)
			iScale = 8;

		v = parseFloat(v).toFixed(iScale);
	} else {
		v = parseFloat(v).toFixed(2);
	}

	var arrs = String(v).split('.');

	var zs = arrs[0], // ��������
	xs = arrs[1], // С������
	ret = [], c = 0;

	var i = zs.length - 1;
	for (; i >= 0; i--) {
		if (c++ >= 3) {
			c = 1;
			ret.push(',');
		}
		ret.push(zs.charAt(i));
	}

	ret.reverse();

	if (xs != null) {
		var tmp = parseFloat('0.' + xs);
		if (tmp != 0) {
			ret.push(String(tmp).substring(1));
		} else if (iScale != null && showPreScale) {
			ret.push(iScale < 2 ? '.0' : '.00');
		}
	}

	return ret.join('');
};

/**
 * 1. ȡ���ַ���. 2. ���ַ��� + ... + title��ʾ
 * 
 * @param is
 *            ��ʼλ�ã��±��0��ʼ��
 * @param ie
 *            ����λ��
 * @param showTitle
 *            �Ƿ���ʾtitle��ʾ��true����ʾ��false������ʾ��Ĭ��false
 */
String.prototype.substring2 = function(is, ie, showTitle) {
	if (arguments.length == 0) {
		return '';
	}

	if (arguments.length == 1) {
		ie = this.length;
	}

	if (typeof(is) != 'number' || typeof(ie) != 'number') {
		return '';
	}

	if (typeof(showTitle) == 'undefined' || typeof(showTitle) != 'boolean') {
		showTitle = false;
	}

	if (is < 0)
		is = 0;
	if (is > ie)
		is = ie;

	if (this.len() <= ie) {
		return this;
	}

	var sSource = this.substring(is);

	var str = '', c = '', len = 0;
	var reg = /[^\x00-\xff]/;

	for (var i = 0; c = sSource.charAt(i); i++) {
		str += c;
		len += (c.match(reg) != null ? 2 : 1);

		if (len >= ie)
			break;
	}

	if (showTitle) {
		return '<span title="' + this + '">' + str + '...<span>';
	}

	return str;
};

function createShadeDiv() {
	var exist = document.getElementById("shadeDiv") != null;
	var shadeDiv;
	if (exist) {
		shadeDiv = document.getElementById("shadeDiv");
	} else {
		shadeDiv = document.createElement("div");
	}

	var bh = document.body.scrollHeight;
	var dh = document.documentElement.scrollHeight;

	var bw = document.body.scrollWidth;
	var dw = document.documentElement.scrollWidth;
	var sheight = bh > dh ? bh : dh;
	var swidth = bw > dw ? bw : dw;

	if (exist) {
		var iframe = document.getElementById('shadeIframe');
		iframe.width = swidth;
		iframe.height = sheight;
		window.document.body.focus();
		return;
	}
	shadeDiv.id = "shadeDiv";
	shadeDiv.style.position = "absolute";
	shadeDiv.style.left = 0;
	shadeDiv.style.top = 0;
	shadeDiv.style.zIndex = 100;
	shadeDiv.style.filter = "alpha(opacity=30)";
	shadeDiv.style.backgroundColor = "rgb(69,73,78)";
	shadeDiv.style.width = swidth;
	shadeDiv.style.height = sheight;

	var sb = [];
	sb
			.push('<iframe scrolling="no" width="100%" height="100%" frameborder="0"');
	sb.push('id="shadeIframe" name="shadeIframe" ');
	sb
			.push('style="z-index:99;position:absolute; top:0px;left:0px;filter:alpha(opacity=0);"');
	sb.push('></iframe>');

	shadeDiv.innerHTML = sb.join("");
	document.body.appendChild(shadeDiv);

	document.body.focus();
}

function closeShadeDiv() {
	var loadProceedImg = document.getElementById("loadProceedImg");
	if (loadProceedImg != undefined)
		loadProceedImg.removeNode(true);
	var loadProceedDiv = document.getElementById("loadProceedDiv");
	if (loadProceedDiv != undefined) {
		loadProceedDiv.removeNode(true);
		loadProceedDiv.innerHTML = "";
	}
	var deleteIframe = document.getElementById("shadeIframe");
	if (deleteIframe != undefined)
		deleteIframe.removeNode(true);
	var deleteDiv = document.getElementById("shadeDiv");
	if (deleteDiv != undefined) {
		deleteDiv.removeNode(true);
		deleteDiv.innerHTML = "";
	}
}
