<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改菜单</title>
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
<form class="layui-form" id="addRoleForm" style="width:80%;">
    <input type="hidden" class="layui-input" name="permissionId" value="${permission.id}" />
    <%--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">上级菜单</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="parentPermissionName" disabled="" value="${parentPermission.permissionName}" placeholder="请输入上级菜单名称">
        </div>
    </div>--%>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="permissionName" lay-verify="permissionName" value="${permission.permissionName}" placeholder="请输入菜单名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-block">
            <i id="icon_edit" class="layui-icon" style="font-size: 25px;">&#xe642;</i>
            <input type="hidden" class="layui-input" name="permissionIcon" lay-verify="permissionIcon" value="${permission.permissionIcon}" placeholder="请输入图标名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="permissionUrl" lay-verify="permissionUrl" value="${permission.permissionUrl}" placeholder="请输入菜单地址"><br/>
            <span style="color: gray">提示：菜单地址应为</span>&nbsp;
            <span style="color: red">/demo/demo</span>&nbsp;
            <span style="color: gray">格式</span>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单次序</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="sequence" lay-verify="sequence" value="${permission.sequence}" placeholder="请输入菜单次序"><br/>
            <span style="color: gray">提示：上次菜单次序为</span>&nbsp;<span style="color: red">${sequence}</span>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="editPermission">立即修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonTool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modules/syspermission/sysPermission_edit.js"></script>
<script>

</script>
</body>
</html>