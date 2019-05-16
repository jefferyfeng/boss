<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
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
        <lable class="layui-form-label">用户id：</lable>
        <div class="layui-input-block">
            <input type="text"  name="userId" autocomplete="off" placeholder="id" class="layui-input" />
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">用户名：</lable>
        <div class="layui-input-block">
            <input type="text"  name="username" autocomplete="off" placeholder="用户名" class="layui-input" />
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">性别：</lable>
        <div class="layui-input-block">
            <select name="sex" >
                <option value="" selected="selected">请选择性别</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">角色：</lable>
        <div class="layui-input-block">
            <select name="role">
                <option value="" selected="selected">请选择角色</option>
                <c:forEach items="${rolesList}" var="role">
                    <option value="${role.id}">${role.roleName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="layui-input-inline">
        <lable class="layui-form-label">状态：</lable>
        <div class="layui-input-block">
            <select name="status" >
                <option value="" selected="selected">请选择状态</option>
                <option value="0">禁用</option>
                <option value="1">启用</option>
            </select>
        </div>
    </div>

    <div class="layui-input-inline">
        <button type="button" id="searchBtn" class="layui-btn mgl-20" lay-submit="" lay-filter="searchBtn">查询</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>

<!-- 操作栏及数据表格 -->
<form class="layui-form">
    <!-- 表头toolbar -->
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
            <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
            <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        </div>
    </script>

    <!-- 操作栏 -->
    <blockquote class="layui-elem-quote quoteBox">
        <a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-sm" id="btn_add">
            <i class="layui-icon">&#xe61f;</i> 添加
        </a>
        <a href="#" class="layui-btn layui-bg-green layui-btn-sm" id="btn_start_all">
            <i class="layui-icon" >&#xe605;</i> 批量启用
        </a>
        <a href="javascript:;" class="layui-btn layui-btn-warm layui-btn-sm" id="btn_stop_all">
            <i class="layui-icon">&#x1006;</i> 批量禁用
        </a>
        <a href="#" class="layui-btn layui-btn-danger layui-btn-sm" id="btn_delete_all">
            <i class="layui-icon" >&#xe640;</i> 批量删除
        </a>
        <a href="#" class="layui-btn layui-btn-cyan layui-btn-sm" id="btn_reset_all">
            <i class="layui-icon" >&#xe614;</i> 重置密码
        </a>
    </blockquote>

    <!-- 数据表格 -->
    <table id="userList" lay-filter="userList"></table>

    <!--数据表格中的操作栏-->
    <script type="text/html" id="userListBar">
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">修改</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="roleEdit">角色配置</a>
    </script>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonTool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modules/sysuser/sysUser_list.js"></script>
</body>
</html>