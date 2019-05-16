//获取隐藏域的值
var contextPath = document.getElementById('contextPath').value;

var _tool = null;

layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //渲染数据表格用户列表
    var index = layer.load(1);
    var tableIns = table.render({
        elem: '#userList',
        url : contextPath + '/sysUser/listUsers',
        cellMinWidth : 100,
        page : true,
        /*height : "full-200",*/
        limits : [10,15,20,25],
        limit : 15,
        id : 'userListTable',
        method : 'get',
        request : {
            pageName : 'currentPage',
            limitName : 'pageSize'
        },
        response: {
            statusCode: 1 //重新规定成功的状态码为 1，table 组件默认为 0
        },
        done : function () {
            layer.close(index);
        },
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '用户id', width:100, align:"center"},
            {field: 'username', title: '用户名', width:150, align:"center"},
            {field: 'sex', title: '性别', width:80, align:"center",templet :function (data) { return formatSex(data.sex); }},
            {field: 'phone', title: '手机号', width:150, align:"center"},
            {field: 'roleNames', title: '角色名称', minWidth:100, align:"center",templet : function (data) {
                    var roleNames = '';
                    data.rolesList.forEach(function (role) {
                        roleNames = roleNames + role.roleName +',';
                    });
                    return (roleNames.length>0 ? roleNames.substring(0,roleNames.length-1) : '');
                }},
            /*{field: 'roleDescriptions', title: '角色描述', minWidth:100, align:"center",templet : function (data) {
                    var roleDescription = '';
                    data.rolesList.forEach(function (role) {
                        roleDescription = roleDescription + role.description +',';
                    });
                    return (roleDescription.length>0 ? roleDescription.substring(0,roleDescription.length-1) : '');
                }},*/
            {field: 'createDate', title: '创建时间', width:200, align:'center'},
            {field: 'email', title: '用户邮箱', width:200, align:'center',templet:function(data){ return formatEmail(data.email); }},
            {field: 'status', title: '用户状态', width:100,  align:'center',templet:function(data){ return formatStatus(data.status); }},
            {title: '操作', width:175, fixed:"right", templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    //顶部查询
    form.on('submit(searchBtn)', function(data){
        var index = layer.load(1);
        //重新渲染数据表格
        table.reload("userListTable",{
            page: {
                currentPage : 1
            },
            where: {
                id : data.field.userId.trim(),
                username : data.field.username.trim(),
                sex : data.field.sex.trim(),
                roleId : data.field.role.trim(),
                status : data.field.status.trim()
            },
            done : function () {
                layer.close(index);
            }
        });
        //禁止表单提交
        return false;
    });

    //添加用户
    $('#btn_add').on('click',function () {
        layerBox({
            title : '添加用户',
            url : contextPath + "/sysUser/toAddUser"
        })
    });

    //toolbar事件
    table.on('tool(userList)',function (obj) {
        var event = obj.event;
        var data = obj.data;
        if(event === 'edit'){//修改按钮
            layerBox({
                title : '修改用户',
                url : contextPath + "/sysUser/toEditUser?userId="+data.id
            });
        }else if(event === 'roleEdit'){//角色配置按钮
            layerBoxTable({
                title : '配置角色',
                url : contextPath + "/sysUser/toUserRoleEdit?userId="+data.id,
                area : ['900px','550px']
            })
        }
    });


    //批量启用
    $('#btn_start_all').on('click',function () {
        //获取选中行数据
        var datas = table.checkStatus('userListTable').data;
        if(datas.length <= 0){
            warnMsg('请先选择数据！');
        }else{
            layer.confirm('你确定启用选中的数据吗?', {icon: 3, title:'提示'}, function(index){
                var loading = layer.load(1);
                var ids = "";
                datas.forEach(function (data) {
                    ids = ids + data.id + ','
                });
                ids = ids.substring(0,ids.length-1);

                $.ajax({
                    type : 'post',
                    url : contextPath + '/sysUser/batchModifyStatus',
                    data : {
                        "ids[]": ids,
                        status : 1
                    },
                    dataType : 'json',
                    success : function (result) {
                        layer.close(index);
                        //关闭loading
                        layer.close(loading);
                        //重新渲染数据表格
                        reloadTable();
                        if(resSuccess(result.code)){
                            succMsg('批量启用成功！');
                        }else{
                            failMsg();
                        }
                    },
                    error : function () {
                        //关闭loading
                        layer.close(index);
                        layer.close(loading);
                        failMsg();
                    }
                })
            });
        }
    });

    //批量禁用
    $('#btn_stop_all').on('click',function () {
        var datas = table.checkStatus('userListTable').data;
        if(datas.length <= 0){
            warnMsg('请先选择数据！');
        }else{
            layer.confirm('你确定禁用选中的数据吗?', {icon: 3, title:'提示'}, function(index){
                var loading = layer.load(1);
                var ids = "";
                datas.forEach(function (data) {
                    ids = ids + data.id + ','
                });
                ids = ids.substring(0,ids.length-1);

                $.ajax({
                    type : 'post',
                    url : contextPath + '/sysUser/batchModifyStatus',
                    data : {
                        "ids[]": ids,
                        status : 0
                    },
                    dataType : 'json',
                    success : function (result) {
                        if(resSuccess(result.code)){
                            layer.close(index);
                            //关闭loading
                            layer.close(loading);
                            //重新渲染数据表格
                            reloadTable();

                            if(resSuccess(result.code)){
                                succMsg('批量禁用成功！');
                            }else{
                                failMsg();
                            }
                        }else{
                            layer.close(index);
                            //关闭loading
                            layer.close(loading);
                            failMsg();
                        }
                    },
                    error : function () {
                        layer.close(index);
                        //关闭loading
                        layer.close(loading);
                        failMsg();
                    }
                })
            });
        }
    });

    //批量删除
    $('#btn_delete_all').on('click',function () {
        var datas = table.checkStatus('userListTable').data;
        if(datas.length <= 0){
            warnMsg('请先选择数据！');
        }else{
            layer.confirm('你确定删除选中的数据吗?', {icon: 3, title:'提示'}, function(index){
                var loading = layer.load(1);
                var ids = "";
                datas.forEach(function (data) {
                    ids = ids + data.id + ','
                });
                ids = ids.substring(0,ids.length-1);

                $.ajax({
                    type : 'post',
                    url : contextPath + '/sysUser/batchRemoveUsers',
                    data : {
                        "ids[]": ids
                    },
                    dataType : 'json',
                    success : function (result) {
                        if(resSuccess(result.code)){
                            layer.close(loading);
                            layer.close(index);
                            //重新渲染数据表格
                            reloadTable(1);

                            succMsg('批量删除成功！');
                        }else{
                            layer.close(loading);
                            layer.close(index);
                            failMsg();
                        }
                    },
                    error : function () {
                        layer.close(loading);
                        layer.close(index);
                        failMsg();
                    }
                })
            });
        }
    });


    //重置密码
    $('#btn_reset_all').on('click',function () {
        var datas = table.checkStatus('userListTable').data;
        if(datas.length <= 0){
            warnMsg('请先选择数据！');
        }else{
            layer.confirm('你确定将密码重置为123456吗?', {icon: 3, title:'提示'}, function(index) {
                var loading = layer.load(1);
                var ids = "";
                datas.forEach(function (data) {
                    ids = ids + data.id + ','
                });
                ids = ids.substring(0, ids.length - 1);

                $.ajax({
                    type: 'post',
                    url: contextPath + '/sysUser/resetPassword',
                    data: {
                        "ids[]": ids
                    },
                    dataType: 'json',
                    success: function (result) {
                        if (resSuccess(result.code)) {
                            layer.close(loading);
                            layer.close(index);
                            succMsg('密码重置成功！');
                        } else {
                            layer.close(loading);
                            layer.close(index);
                            failMsg();
                        }
                    },
                    error: function () {
                        layer.close(loading);
                        layer.close(index);
                        failMsg();
                    }
                });
            });
        }
    });
    
    //刷新数据表格
    function reloadTable(currentPage) {
        var searchForm  = $('#searchForm').serializeArray();

        //重新渲染数据表格
        if(currentPage != null){
            table.reload("userListTable",{
                page: {
                    currentPage : currentPage
                },
                where: {
                    id : searchForm[0].value,
                    username : searchForm[1].value,
                    sex : searchForm[2].value,
                    roleId : searchForm[3].value,
                    status : searchForm[4].value
                },
                done : function () {
                    layer.close(index);
                }
            });
        }else{
            table.reload("userListTable",{
                where: {
                    id : searchForm[0].value,
                    username : searchForm[1].value,
                    sex : searchForm[2].value,
                    roleId : searchForm[3].value,
                    status : searchForm[4].value
                },
                done : function () {
                    layer.close(index);
                }
            });
        }
    }

    //用于子iframe刷新数据表格
    window.searchReload = function (currentPage) {
        reloadTable(currentPage);
    }
});
