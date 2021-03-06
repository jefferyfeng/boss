<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Boss后台管理系统</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="${pageContext.request.contextPath}/static/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css" media="all" />
</head>

<!-- 页面隐藏域 -->
<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<body class="main_body">
	<div class="layui-layout layui-layout-admin">
		<!-- 顶部 -->
		<div class="layui-header header">
			<div class="layui-main mag0">
				<a href="#" class="logo">Boss管理系统</a>
				<!-- 显示/隐藏菜单 -->
				<a href="javascript:;" class="seraph hideMenu icon-caidan"></a>
				<!-- 顶级菜单 -->
				<!-- 手机端 -->
				<ul class="layui-nav mobileTopLevelMenus" mobile>
					<li class="layui-nav-item" data-menu="contentManagement">
						<a href="javascript:;"><i class="seraph icon-caidan"></i><cite>layuiCMS</cite></a>
						<dl class="layui-nav-child" id="menuTop">
							<c:forEach items="${topMenus}" var="item" varStatus="status">
								<dd class="layui-this" data-menu="${item.id}">
									<a href="javascript:;"><i class="layui-icon" data-icon="&#xe63c;">&#xe63c;</i><cite>${item.title}</cite></a>
								</dd>
							</c:forEach>
						</dl>
					</li>
				</ul>
				<!-- pc端 -->
				<ul class="layui-nav topLevelMenus" pc>
					<c:forEach items="${topMenus}" var="item" varStatus="status">
						<li class="layui-nav-item layui-this" data-menu="${item.id}" pc>
							<a href="javascript:;"><i class="layui-icon" data-icon="&#xe63c;">&#xe63c;</i><cite>${item.title}</cite></a>
						</li>
					</c:forEach>
				</ul>
			    <!-- 顶部右侧菜单 -->
			    <ul class="layui-nav top_menu">
					<li class="layui-nav-item" pc>
						<a href="javascript:;" class="clearCache"><i class="layui-icon" data-icon="&#xe640;">&#xe640;</i><cite>清除缓存</cite><span class="layui-badge-dot"></span></a>
					</li>
					<li class="layui-nav-item lockcms" pc>
						<a href="javascript:;"><i class="seraph icon-lock"></i><cite>锁屏</cite></a>
					</li>
					<li class="layui-nav-item" id="userInfo">
						<a href="javascript:;"><img src="${pageContext.request.contextPath}/static/images/photo.jpg" class="layui-nav-img userAvatar" width="35" height="35"><cite class="adminName">${user.username}</cite></a>
						<dl class="layui-nav-child">
							<dd>
                                <a href="javascript:;" data-url="${pageContext.request.contextPath}/sysUser/toUserInfo">
                                    <i class="seraph icon-ziliao" data-icon="icon-ziliao"></i><cite>个人资料</cite>
                                </a>
                            </dd>
							<dd>
                                <a href="javascript:;" data-url="${pageContext.request.contextPath}/sysUser/toChangePwd">
                                    <i class="seraph icon-xiugai" data-icon="icon-xiugai"></i><cite>修改密码</cite>
                                </a>
                            </dd>
							<dd>
                                <a href="javascript:;" class="showNotice">
                                    <i class="layui-icon">&#xe645;</i><cite>系统公告</cite><span class="layui-badge-dot"></span>
                                </a>
                            </dd>
							<dd pc>
                                <a href="javascript:;" class="functionSetting">
                                    <i class="layui-icon">&#xe620;</i><cite>功能设定</cite><span class="layui-badge-dot"></span>
                                </a>
                            </dd>
							<dd pc>
                                <a href="javascript:;" class="changeSkin">
                                    <i class="layui-icon">&#xe61b;</i><cite>更换皮肤</cite>
                                </a>
                            </dd>
							<dd>
                                <a href="${pageContext.request.contextPath}/static/page/login/login.jsp" class="signOut">
                                    <i class="seraph icon-tuichu"></i><cite>退出</cite>
                                </a>
                            </dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			<div class="user-photo">
				<a class="img" title="我的头像" ><img src="${pageContext.request.contextPath}/static/images/photo.jpg" class="userAvatar"></a>
				<p>你好！<span class="userName">${user.username}</span>, 欢迎登录</p>
			</div>
			<!-- 搜索 -->
			<div class="layui-form component">
				<input type="text" class="layui-input" placeholder="请输入要搜索的菜单" id="searchPermission">
				<i class="layui-icon">&#xe615;</i>
			</div>
			<div class="navBar layui-side-scroll" id="navBar">
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item layui-this">
						<a href="javascript:;" data-url="${pageContext.request.contextPath}/per/toMain"><i class="layui-icon" data-icon=""></i><cite>后台首页</cite></a>
					</li>
				</ul>
			</div>
		</div>
		<!-- 右侧内容 -->
		<div class="layui-body layui-form">
			<div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
				<ul class="layui-tab-title top_tab" id="top_tabs">
					<li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite>后台首页</cite></li>
				</ul>
				<ul class="layui-nav refreshTab">
					<li>
						<a href="javascript:;" class="refresh refreshThis"><i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop">&#x1002;</i> 刷新</a>
					</li>
				</ul>
				<ul class="layui-nav closeBox">
					<li class="layui-nav-item">
						<a href="javascript:;"><i class="layui-icon caozuo">&#xe643;</i> 页面操作</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i> 刷新当前</a></dd>
							<dd><a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a></dd>
							<dd><a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a></dd>
						</dl>
					</li>
				</ul>
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
						<iframe src="${pageContext.request.contextPath}/per/toMain"></iframe>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<div class="layui-footer footer">
			<p><span>copyright @2018 jeffery</span>　　<a id="donation" class="layui-btn layui-btn-danger layui-btn-sm">捐赠作者</a></p>
		</div>
	</div>

	<!-- 移动导航 -->
	<div class="site-tree-mobile"><i class="layui-icon">&#xe602;</i></div>
	<div class="site-mobile-shade"></div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/cache.js"></script>
</body>
</html>