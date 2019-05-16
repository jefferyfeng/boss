<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>首页--Boss后台管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all" />
</head>

<!-- 页面隐藏域 -->
<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />
<input id="username" type="hidden" value="${user.username}" />


<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-bg-green">
		<div id="nowTime"></div>
	</blockquote>
	<div class="layui-row layui-col-space10">
		<div class="layui-col-lg6 layui-col-md12">
			<blockquote class="layui-elem-quote title">我的时间线</blockquote>
			<div class="layui-elem-quote layui-quote-nm history_box magb0">
				<ul class="layui-timeline">
					<li class="layui-timeline-item">
						<i class="layui-icon layui-timeline-axis">&#xe756;</i>
						<div class="layui-timeline-content layui-text">
							<div class="layui-timeline-title">
								<h3 class="layui-inline">新增core包内容　</h3>
								<span class="layui-badge-rim layui-red">2019-03-12</span>
							</div>
							<ul>
								<li>增加通用的result工具类</li>
								<li>增加pom依赖</li>
								<li>优化生成文件的目录</li>
							</ul>
						</div>
					</li>
					<li class="layui-timeline-item">
						<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<div class="layui-timeline-title">
								<h3 class="layui-inline">优化已知bug　</h3>
								<span class="layui-badge-rim layui-red">2019-01-20</span>
							</div>
							<ul>
								<li>优化mapper文件在idea中不被编译问题</li>
								<li>增加配置文件及一些基础配置，eg：日志</li>
								<li>优化springmvc日期序列化问题</li>
								<li>增加一些常用工具类</li>
							</ul>
						</div>
					</li>
					<li class="layui-timeline-item">
						<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<div class="layui-timeline-title">
								<h3 class="layui-inline">提供基础ssm环境构建　</h3>
								<span class="layui-badge-rim layui-red">2018-11-07</span>
							</div>
							<ul>
								<li>根据数据库（mysql）地址 辅助生成权限表</li>
								<li>开放自定义项目基础包名配置，项目名配置</li>
								<li>开放 model dao mapper service controller 包名自定义配置</li>
								<li>开放基础权限表 表名自定义配置</li>
							</ul>
						</div>
					</li>
					<li class="layui-timeline-item">
						<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<div class="layui-timeline-title">
								<h3 class="layui-inline">初始化AutoBuild项目　</h3>
								<span class="layui-badge-rim layui-red">2018-10-09</span>
							</div>
							<ul>
								<li>初始化项目</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>