<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>角色菜单配置</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/ztree/css/metroStyle.css" rel="stylesheet" type="text/css"/>
</head>

<!-- 页面隐藏域 -->
<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />
<input id="roleId" type="hidden" value="${roleId}" />

<body class="childrenBody">
<form class="layui-form" id="addRoleForm" style="width:80%;">
    <div class="layui-input-block">
        <ul class="tree_ul ztree" style="min-height: 200px" id="permissionsTree"></ul>
    </div>
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit="" lay-filter="editPermission">立即修改</button>
        <button id="resetBtn" type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonTool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modules/sysrole/sysRole_permission_edit.js"></script>

</body>
</html>