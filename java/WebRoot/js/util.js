function getCookie(name) {
    var start = document.cookie.indexOf(name + "=");
    var len = start + name.length + 1;
    if ((!start) && (name != document.cookie.substring(0, name.length))) {
        return null;
    }
    if (start == -1)
        return null;
    var end = document.cookie.indexOf(';', len);
    if (end == -1)
        end = document.cookie.length;
    return unescape(document.cookie.substring(len, end));
}

function setCookie(name, value, expires, path, domain, secure) {
    var today = new Date();
    today.setTime(today.getTime());
    if (expires) {
        expires = expires * 1000 * 60 * 60 * 24;
    }
    var expires_date = new Date(today.getTime() + (expires));
    document.cookie = name + '=' + escape(value)
            + ((expires) ? ';expires=' + expires_date.toGMTString() : '')
            + // expires.toGMTString()
            ((path) ? ';path=' + path : '') + ((domain) ? ';domain=' + domain : '')
            + ((secure) ? ';secure' : '');
}

function deleteCookie(name, path, domain) {
    if (getCookie(name))
        document.cookie = name + '=' + ((path) ? ';path=' + path : '')
                + ((domain) ? ';domain=' + domain : '') + ';expires=Thu, 01-Jan-1970 00:00:01 GMT';
}

function jsDateDiff(dateStr) {
    /* dateStr must be MM/dd/yyyy to parse
     * in Mozilla, MM-dd-yyyy can't be parsed correctly 
     */
    //alert(dateStr);
    var d_minutes, d_hours, d_days;
    var publishTime = Date.parse(dateStr) / 1000;
    var timeNow = new Date().getTime() / 1000;
    var d;
    d = timeNow - publishTime;
    d_days = parseInt(d / 86400);
    d_hours = parseInt(d / 3600);
    d_minutes = parseInt(d / 60);
    if (d_days > 0 && d_days < 4) {
        return d_days + "天前";
    } else if (d_days <= 0 && d_hours > 0) {
        return d_hours + "小时前";
    } else if (d_hours <= 0 && d_minutes > 0) {
        return d_minutes + "分钟前";
    } else {
        var s = new Date(publishTime * 1000);
        // s.getFullYear()+"年";
        return (s.getMonth() + 1) + "月" + s.getDate() + "日";
    }
}

String.prototype.ToCharArray = function() {
    return this.split("");
}
String.prototype.Reverse = function() {
    return this.split("").reverse().join("");
}
String.prototype.IsContains = function(str) {
    return (this.indexOf(str) > -1);
} /* include or not ? */
String.prototype.Format = function() {
    var args = arguments;
    return this.replace(/\{(\d+)\}/g, function(m, i, o, n) {
        return args[i];
    });
};
// eg:alert('<a href="{clickurl}"
// target="_blank">{inner}</a>'.JsonFormat({clickurl:'http://www.baidu.com',inner:'baidu'}));
String.prototype.JsonFormat = function(config, reserve) {
    return this.replace(/\{([^}]*)\}/g, (typeof config == 'object') ? function(m, i) {
        var ret = config[i];
        return ret == null && reserve ? m : ret
    } : config);
}; /* 2010-1-3 */
String.prototype.ResetBlank = function() {
    return this.replace(/s+/g, "");
}
String.prototype.LTrim = function() {
    return this.replace(/^s+/g, "");
}
String.prototype.RTrim = function() {
    return this.replace(/s+$/g, "");
}
String.prototype.Trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.GetNum = function() {
    return this.replace(/[^d]/g, "");
} /* num only */
String.prototype.GetEn = function() {
    return this.replace(/[^A-Za-z]/g, "");
} /* english charctar only */
String.prototype.GetCn = function() {
    return this.replace(/[^\u4e00-\u9fa5\uf900-\ufa2d]/g, "");
} /* chinese charctar only */
String.prototype.ByteLength = function() {
    return this.replace(/[^\x00-\xff]/g, "aa").length;
} /* get Byte Length */
String.prototype.Left = function(n) {
    return this.slice(0, n);
}
String.prototype.Right = function(n) {
    return this.slice(this.length - n);
}
String.prototype.Insert = function(index, str) {
    return this.substring(0, index) + str + this.substr(index);
}
String.prototype.Copy = function() {
    if (IE)
        window.clipboardData.setData("text", this.toString());
}/* ie only */
String.prototype.HtmlEncode = function() {
    var i, e = {
        '&' : '&amp;',
        '<' : '&lt;',
        '>' : '&gt;',
        '"' : '&quot;'
    }, t = this;
    for (i in e)
        t = t.replace(new RegExp(i, 'g'), e[i]);
    return t
}
String.prototype.UrlEncode = function() {
    return encodeURIComponent(this);
}
String.prototype.Unicode = function() {
    var tmpArr = [];
    for ( var i = 0; i < this.length; i++)
        tmpArr.push("&#" + this.charCodeAt(i) + ";");
    return tmpArr.join("");
}
/* Validate */
String.prototype.IsEmpty = function() {
    return this == "";
}
String.prototype.IsEmail = function() {
    return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(this);
}
String.prototype.IsChinese = function() {
    return /^[\u0391-\uFFE5]+$/.test(this);
}
String.prototype.IsQQ = function() {
    return /^[0-9]{5,9}$/.test(this);
}
String.prototype.IsTel = function() {
    return /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/.test(this);
}
String.prototype.IsTelAll = function() {
    return /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/.test(this) || /^\d{11}$/.test(this);
} /* include cell phone */
String.prototype.IsNum = function() {
    return /^(\d+)$/.test(this);
}
/*-- Array Class Extendtions --*/
Array.prototype.add = function(itAdd) {
    var _index = this.indexOf(itAdd);
    if (_index < 0) {
        this.push(itAdd);
    }
};
Array.prototype.del = function(itDel) {
    var _index = this.indexOf(itDel);
    if (_index >= 0) {
        this.splice(_index, 1);
    }
};

var popUpWin = 0;
function popUpWindow(URLStr, left, top, width, height, newWin, scrollbars) {
    if (typeof (newWin) == "undefined")
        newWin = false;

    if (typeof (left) == "undefined")
        left = 100;

    if (typeof (top) == "undefined")
        top = 0;

    if (typeof (width) == "undefined")
        width = 800;

    if (typeof (height) == "undefined")
        height = 760;

    if (newWin) {
        open(URLStr, '', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars='
                + scrollbars + ',resizable=yes,copyhistory=yes,width=' + width + ',height='
                + height + ',left=' + left + ', top=' + top + ',screenX=' + left + ',screenY='
                + top + '');
        return;
    }

    if (typeof (scrollbars) == "undefined") {
        scrollbars = 0;
    }

    if (popUpWin) {
        if (!popUpWin.closed)
            popUpWin.close();
    }
    popUpWin = open(URLStr, 'popUpWin',
            'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=' + scrollbars
                    + ',resizable=yes,copyhistory=yes,width=' + width + ',height=' + height
                    + ',left=' + left + ', top=' + top + ',screenX=' + left + ',screenY=' + top
                    + '');
    popUpWin.focus();
}

function inviteFriendAjax() {
    $("a.invite").click( function() {
        var s = confirm('确定邀请?');
        if(s) {
            $.getJSON($(this).attr("href"), function(data) {
                switch (data.ec) {
                case 0:
                    alert("邀请成功");
                    break;
                default:
                    alert("邀请失败");
                }
            });
        } 
        return false; //!important to avoid <a> jumping

    });

}
