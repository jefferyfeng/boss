<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加页面</title>
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
<form class="layui-form" id="addSysLogForm" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">操作用户id</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="userId" lay-verify="userId" placeholder="请输入操作用户id"  />
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">操作用户名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="username" lay-verify="username" placeholder="请输入操作用户名"  />
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">操作者角色</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="userRoles" lay-verify="userRoles" placeholder="请输入操作者角色"  />
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">操作模块</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="logModule" lay-verify="logModule" placeholder="请输入操作模块"  />
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">操作内容</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="operation" lay-verify="operation" placeholder="请输入操作内容"  />
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">操作结果</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="result" lay-verify="result" placeholder="请输入操作结果"  />
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addSysLog">立即添加</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonTool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modules/syslog/sysLog_add.js"></script>
</body>
</html>