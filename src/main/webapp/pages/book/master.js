function load_event_monitor(root) {
    var re = /a_(\w+)/, fns = {};
    $(".j", root).each(function (i) {
        var m = re.exec(this.className);
        if (m) {
            var actionName = m[1], f = fns[actionName];
            f || (f = eval("Douban.init_" + actionName), fns[actionName] = f), f && f(this)
        }
    })
}



!function () {
    var t = window.white_site_list || new RegExp(["^https?://([\\w]+\\.douban\\.com", "|web[0-9]?\\.qq\\.com", "|hao\\.qq\\.com", "|(hao\\.)*360\\.cn", "|so\\.com", "|www\\.soso\\.com", "|(www\\.)?growingio\\.com", ")(\\:[\\d]+)?/"].join(""));
    self !== top && document.referrer.search(t) === -1 && (top.location = self.location)
}(), Do = "undefined" == typeof Do ? function (t) {
    setTimeout(t, 0)
} : Do, Douban = {}, function () {
    function t() {
        var t = {done: [], fail: []}, e = {
            done: function (n) {
                return t.done.push(n), e
            }, fail: function (n) {
                return t.fail.push(n), e
            }
        };
        return {
            resolve: function () {
                for (var e, n = 0; e = t.done[n++];) e.apply(this, arguments)
            }, reject: function () {
                for (var e, n = 0; e = t.fail[n++];) e.apply(this, arguments)
            }, promise: e
        }
    }

    };

Douban.init_donate = function () {

    $("body").delegate(".btn-donate", "click", function (t) {
        var e, n, i, o, a = $(t.currentTarget), r = a.attr("href").split("?"), c = {elm: a}, l = {is_first: 1};
        if (t.preventDefault(), !a.hasClass(s)) {
            if (a.addClass(s), r[1]) for (e = r[1].split("&"), i = 0, o = e.length; i < o; i++) n = e[i].split("="), l[n[0]] = n[1] || "";
            c.args = l, c.url = r[0], $.dataPoster(r[0], l, $.proxy(h, c), "post", "json")
        }
    })
}, $(function () {
    load_event_monitor(document)
}), Douban.init_stars = function (t) {
    var e, n = function () {
            var t = "(-webkit-min-device-pixel-ratio: 1.5), (min--moz-device-pixel-ratio: 1.5), (-o-min-device-pixel-ratio: 3/2), (min-resolution: 1.5dppx)";
            return window.devicePixelRatio > 1 || !(!window.matchMedia || !window.matchMedia(t).matches)
        }, i = {1: "很差", 2: "较差", 3: "还行", 4: "推荐", 5: "力荐"}, o = $("#n_rating", t), a = $("#stars img", t),
        r = $("#stars"), s = n() && r.data("hollow-2x") ? r.data("hollow-2x") : r.data("hollow"),
        c = n() && r.data("solid-2x") ? r.data("solid-2x") : r.data("solid"), l = function (e) {
            var n = o.val() || 0;
            e ? ($("#rateword", t).text(i[e]), a.each(function (t) {
                this.src = t < e ? c : s
            })) : ($("#rateword", t).text(n ? i[n] : ""), a.each(function (t) {
                this.src = t < n ? c : s
            }))
        };
    a.hover(function () {
        l(this.id.charAt(4))
    }, function () {
        l(e || 0)
    }), o.attr("name") && a.click(function () {
        e = this.id.charAt(4), o.val(e), l(e)
    }), l()
}, $(function () {
    $("[name=my_followings]").bind("change", function () {
        $(this).prop("checked") ? location.search = "?show_followings=on" : location.search = ""
    })
}), Douban.init_show_login = function (t) {
    var e = function () {
            var t = "", e = location.hostname.match(/(.*)\.douban\.com/);
            e && (t = e[1]);
            for (var n = [{name: "fm", reg: /douban\.fm/}, {name: "sns", reg: /www\.douban\.com/}, {
                name: "group",
                reg: /douban\.com\/group/
            }], i = 0, o = n.length; i++; i < o) {
                var a = n[i];
                location.href.match(a.reg) && (t = a.name)
            }
            return t || "sns"
        }, n = e(), i = "https://accounts.douban.com/popup/login?source=" + n,
        o = "https://s.doubanio.com/dae/cdnlib/libs/components/accounts/popup.min.css",
        a = "https://s.doubanio.com/dae/cdnlib/libs/jquery-overlay/overlay.min.js",
        r = location.protocol + "//" + location.hostname,
        s = '\n<iframe src="javascript:;" frameborder="0" scrolling="no" width="478" height="382" name="' + r + '">\n</iframe>',
        c = function () {
            $.overlay.open(s, function () {
                $.overlay.body.find("iframe").attr("src", i)
            })
        }, l = $(t);
    l.click(function (t) {
        t.preventDefault(), $.overlay ? c() : Douban.loader.batch([o, a]).done(c)
    }), $(window).bind("message", function (t) {
        "https://accounts.douban.com" === t.originalEvent.origin && $.overlay.body.find("iframe").css("height", t.originalEvent.data)
    })
};
