<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>列表页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mylayui.css" media="all" />
</head>

<!-- 页面隐藏域 -->
<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<body class="childrenBody">
<form class="layui-form layui-form-pane" id="searchForm">
    <div class="layui-input-inline">
        <lable class="layui-form-label">id: </lable>
        <div class="layui-input-block">
            <input type="text"  name="id" autocomplete="off" placeholder="id" class="layui-input" />
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">操作用户id: </lable>
        <div class="layui-input-block">
            <input type="text"  name="userId" autocomplete="off" placeholder="操作用户id" class="layui-input" />
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">操作用户名: </lable>
        <div class="layui-input-block">
            <input type="text"  name="username" autocomplete="off" placeholder="操作用户名" class="layui-input" />
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">操作者角色: </lable>
        <div class="layui-input-block">
            <input type="text"  name="userRoles" autocomplete="off" placeholder="操作者角色" class="layui-input" />
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">操作模块: </lable>
        <div class="layui-input-block">
            <input type="text"  name="logModule" autocomplete="off" placeholder="操作模块" class="layui-input" />
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">操作内容: </lable>
        <div class="layui-input-block">
            <input type="text"  name="operation" autocomplete="off" placeholder="操作内容" class="layui-input" />
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">操作结果: </lable>
        <div class="layui-input-block">
            <input type="text"  name="result" autocomplete="off" placeholder="操作结果" class="layui-input" />
        </div>
    </div>
    <div class="layui-input-inline">
        <button type="button" id="searchBtn" class="layui-btn mgl-20" lay-submit="" lay-filter="searchBtn">查询</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>

<!-- 操作栏及数据表格 -->
<form class="layui-form">
    <!-- 操作栏 -->
    <blockquote class="layui-elem-quote quoteBox">
        <a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-sm" id="btn_add">
            <i class="layui-icon">&#xe61f;</i> 添加
        </a>
        <a href="#" class="layui-btn layui-btn-danger layui-btn-sm" id="btn_delete_all">
            <i class="layui-icon" >&#xe640;</i> 批量删除
        </a>
        <a href="#" class="layui-btn layui-btn-cyan layui-btn-sm" id="btn_other_all">
            <i class="layui-icon" >&#xe614;</i> 其他
        </a>
    </blockquote>
    <!-- 数据表格 -->
    <table id="sysLogList" lay-filter="sysLogList"></table>
    <!--数据表格中的操作栏-->
    <script type="text/html" id="sysLogListBar">
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">修改</a>
        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="other">其他</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
    </script>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonTool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modules/syslog/sysLog_list.js"></script>
</body>
</html>
