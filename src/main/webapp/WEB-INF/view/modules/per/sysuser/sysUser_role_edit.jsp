<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mylayui.css" media="all" />
    <style>
        .layui-input-block{
            width : 20%;
            min-width: 160px;
            margin: 15px auto;
        }
    </style>
</head>

<!-- 页面隐藏域 -->
<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />
<input id="userId" type="hidden" value="${userId}" />


<body class="childrenBody">
<!-- 操作栏及数据表格 -->
<form class="layui-form">
    <!-- 数据表格 -->
    <table id="roleList" lay-filter="roleList"></table>
    <div class="layui-form-item" style="width: 100%">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="editUserRole">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonTool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modules/sysuser/sysUser_role_edit.js"></script>
</body>
</html>