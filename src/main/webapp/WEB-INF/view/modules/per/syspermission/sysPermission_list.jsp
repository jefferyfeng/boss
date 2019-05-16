<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>菜单管理</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/static/images/favicon.ico"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/ztree/css/metroStyle.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mylayui.css" media="all" />
    <style type="text/css">
        .layui-elem-quote{
            margin-top :  10px;
            padding-top : 10px;
            padding-bottom : 10px;
        }
    </style>
</head>

<!-- 页面隐藏域 -->
<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}"/>

<body class="body">
    <div class="container">
        <div id="main">
            <!-- 操作栏 -->
            <blockquote class="layui-elem-quote">
                <a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-sm" id="addChildMenu">
                    <i class="layui-icon">&#xe61f;</i> 添加下级
                </a>
                <a href="javascript:;" class="layui-btn layui-btn-warm layui-btn-sm" id="addMenu">
                    <i class="layui-icon">&#xe61f;</i> 添加同级
                </a>
                <a href="javascript:;" class="layui-btn layui-btn-danger layui-btn-sm" id="btn_delete">
                    <i class="layui-icon"> &#xe640;</i>删除
                </a>
                <a href="javascript:;" class="layui-btn layui-btn-sm" id="btn_edit">
                    <i class="layui-icon"> &#xe642;</i>修改
                </a>
            </blockquote>
            <div class="layui-row">
                <table class="layui-table" height="750px">
                    <colgroup>
                        <col width="150">
                        <col>
                    </colgroup>
                    <tr>
                        <!--左侧菜单目录树-->
                        <td class="treeTd" style="text-align: left;" width="15%" valign="top">
                            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                                <legend style="font-weight: bold">菜单管理</legend>
                            </fieldset>
                            <ul class="tree_ul ztree" style="min-height: 200px" id="permissionsTree"></ul>
                        </td>
                        <!-- 右侧菜单详情 -->
                        <td class="org_Table" style="text-align: left;" valign="top" id="nodeView">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <!-- 页面js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/ztree/js/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/commonTool.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modules/syspermission/sysPermission_list.js"></script>
</body>
</html>