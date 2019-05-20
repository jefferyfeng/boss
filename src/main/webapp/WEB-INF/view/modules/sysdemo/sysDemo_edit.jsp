<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改页面</title>
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
<form class="layui-form" id="editSysDemoForm" style="width:80%;">
    <input type="text" hidden="hidden" name="id" value="${sysDemo.id}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">测试名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="demoName" value="${sysDemo.demoName}"  lay-verify="demoName" placeholder="请输入测试名称"/>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">测试时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="demoDate" value="<fmt:formatDate value="${sysDemo.demoDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>"  lay-verify="demoDate" placeholder="请输入测试时间" id="demoDate" readonly />
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">测试状态</label>
        <div class="layui-input-block">
            <select name="status" >
                <option value="" <c:if test="${sysDemo.status == null}">selected="selected"</c:if>>请选择状态</option>
                <option value="0" <c:if test="${sysDemo.status == 0}">selected="selected"</c:if>>禁用</option>
                <option value="1" <c:if test="${sysDemo.status == 1}">selected="selected"</c:if>>启用</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="editSysDemo">立即修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonTool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modules/sysdemo/sysDemo_edit.js"></script>
</body>
</html>