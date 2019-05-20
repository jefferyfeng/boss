//获取隐藏域的值
var contextPath = document.getElementById('contextPath').value;

var $,tab,dataStr,layer;
layui.config({
	base : contextPath + "/static/js/"
}).extend({
	"bodyTab" : "bodyTab"
})
layui.use(['bodyTab','form','element','layer','jquery'],function(){

	var form = layui.form,
		element = layui.element;
		$ = layui.$;
    	layer = parent.layer === undefined ? layui.layer : top.layer;


		tab = layui.bodyTab({
			openTabNum : "50",  //最大可打开窗口数量
            url : contextPath + "/sysPermission/listAllLayuiNavs" //获取菜单json地址
		});

	//通过顶部菜单获取左侧二三级菜单
	function getData(parentId){
		$.ajax({
			type : 'get',
			url : tab.tabConfig.url + '/' + parentId,
            dataType : 'json',
			success : function (result) {
				if(result.code == 1){
                    dataStr = result.body.navsList;
                    //重新渲染左侧菜单
                    tab.render();
				}else{
                    layer.msg('网络繁忙请稍后再试！', {
                        icon: 2,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        //do something
                    });
				}
            },
			error : function () {
                layer.msg('网络繁忙请稍后再试！', {
                    icon: 2,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    //do something
                });
            }
		});
	}


    //预加载第一个菜单的左侧权限菜单
	var preNavs = $('.topLevelMenus li').first().data("menu");
    getData(preNavs);

	//页面加载时判断左侧菜单是否显示
	//通过顶部菜单获取左侧菜单
	$(".topLevelMenus li,.mobileTopLevelMenus dd").click(function(){
		if($(this).parents(".mobileTopLevelMenus").length != "0"){
			$(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
		}else{
			$(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
		}
		$(".layui-layout-admin").removeClass("showMenu");
		$("body").addClass("site-mobile");
		//根据父id拉取权限菜单
		getData($(this).data("menu"));
		//渲染顶部窗口
		tab.tabMove();
	})

	//隐藏左侧导航
	$(".hideMenu").click(function(){
		if($(".topLevelMenus li.layui-this a").data("url")){
			layer.msg("此栏目状态下左侧菜单不可展开");  //主要为了避免左侧显示的内容与顶部菜单不匹配
			return false;
		}
		$(".layui-layout-admin").toggleClass("showMenu");
		//渲染顶部窗口
		tab.tabMove();
	})


	//手机设备的简单适配
    $('.site-tree-mobile').on('click', function(){
		$('body').addClass('site-mobile');
	});
    $('.site-mobile-shade').on('click', function(){
		$('body').removeClass('site-mobile');
	});

	// 添加新窗口
	$("body").on("click",".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')",function(){
		//如果不存在子级
		if($(this).siblings().length == 0){
			addTab($(this));
			$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
		}
		$(this).parent("li").siblings().removeClass("layui-nav-itemed");
	});

	//清除缓存
	$(".clearCache").click(function(){
		window.sessionStorage.clear();
        window.localStorage.clear();
        var index = layer.msg('清除缓存中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("缓存清除成功！");
        },1000);
    });

	//刷新后还原打开的窗口
    if(cacheStr == "true") {
        if (window.sessionStorage.getItem("menu") != null) {
            menu = JSON.parse(window.sessionStorage.getItem("menu"));
            curmenu = window.sessionStorage.getItem("curmenu");
            var openTitle = '';
            for (var i = 0; i < menu.length; i++) {
                openTitle = '';
                if (menu[i].icon) {
                    if (menu[i].icon.split("-")[0] == 'icon') {
                        openTitle += '<i class="seraph ' + menu[i].icon + '"></i>';
                    } else {
                        openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
                    }
                }
                openTitle += '<cite>' + menu[i].title + '</cite>';
                openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
                element.tabAdd("bodyTab", {
                    title: openTitle,
                    content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
                    id: menu[i].layId
                })
                //定位到刷新前的窗口
                if (curmenu != "undefined") {
                    if (curmenu == '' || curmenu == "null") {  //定位到后台首页
                        element.tabChange("bodyTab", '');
                    } else if (JSON.parse(curmenu).title == menu[i].title) {  //定位到刷新前的页面
                        element.tabChange("bodyTab", menu[i].layId);
                    }
                } else {
                    element.tabChange("bodyTab", menu[menu.length - 1].layId);
                }
            }
            //渲染顶部窗口
            tab.tabMove();
        }
    }else{
		window.sessionStorage.removeItem("menu");
		window.sessionStorage.removeItem("curmenu");
	}

	//搜索菜单 页面级
    if(!!window.ActiveXObject){
        document.getElementById("searchPermission").onpropertychange = searchPermission;
    }else{
        document.getElementById("searchPermission").addEventListener("input",searchPermission,false);
    }

    //捐赠按钮
    $('#donation').on('click',function () {
        donation();
    })
})

//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}

//捐赠弹窗
function donation(){
	layer.tab({
		area : ['260px', '367px'],
		tab : [{
			title : "微信",
			content : "<div style='padding:10px;overflow:hidden;background:#d2d0d0;'><img width='220px' src='"+contextPath+"/static/images/wechat.jpg'></div>"
		},{
			title : "支付宝",
			content : "<div style='padding:10px;overflow:hidden;background:#d2d0d0;'><img width='220px' src='"+contextPath+"/static/images/alipay.jpg'></div>"
		}]
	})
}

//图片管理弹窗
function showImg(){
    $.getJSON('json/images.json', function(json){
        var res = json;
        layer.photos({
            photos: res,
            anim: 5
        });
    });
}

//搜索菜单 TODO
function searchPermission() {
    var input = $('#searchPermission').val();
    var permissions = $('.navBar').find('.layui-nav-item');
    permissions.each(function (index,permission) {
        if(index != 0){
            //菜单名称串
            var permissionNames = $(permission).context.textContent + '';
            if(permissionNames.indexOf(input) >= 0){
                $(permission).addClass("layui-nav-itemed").siblings().removeClass("layui-nav-itemed");
            }
        }
    })
}