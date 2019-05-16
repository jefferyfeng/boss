<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加用户</title>
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

<body class="childrenBody">
<form class="layui-form" id="addUserForm" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="username" lay-verify="username" placeholder="请输入用户名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="password" class="layui-input" name="password" lay-verify="password" placeholder="请输入密码">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="1" title="男">
            <input type="radio" name="sex" value="2" title="女">
            <input type="radio" name="sex" value="" title="保密" checked>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="email" class="layui-input" name="email" lay-verify="email" placeholder="请输入邮箱">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="phone" lay-verify="phone" placeholder="请输入手机号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <select name="status" >
                <%--<option value="">请选择状态</option>--%>
                <option value="0" selected="selected">禁用</option>
                <option value="1">启用</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addUser">立即添加</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonTool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modules/sysuser/sysUser_add.js"></script>
</body>
</html>