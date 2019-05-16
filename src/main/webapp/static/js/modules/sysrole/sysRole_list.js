//获取隐藏域的值
var contextPath = document.getElementById('contextPath').value;

layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //渲染数据表格角色列表
    var index = layer.load(1);
    var tableIns = table.render({
        elem: '#roleList',
        url : contextPath + '/sysRole/listRoles',
        cellMinWidth : 95,
        page : true,
        /*height : "full-200",*/
        limits : [10,15,20,25],
        limit : 20,
        id : "roleListTable",
        request : {
            pageName : 'currentPage',
            limitName : 'pageSize'
        },
        done : function () {
            layer.close(index);
        },
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '角色id', minWidth:50, align:"center"},
            {field: 'roleName', title: '角色名称', minWidth:100, align:"center"},
            {field: 'description', title: '角色描述', minWidth:100, align:"center"},
            {field: 'createDate', title: '创建时间', minWidth:200, align:'center'},
            {field: 'status', title: '角色状态',  align:'center',templet:function(data){ return formatStatus(data.status); }},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    //顶部查询
    form.on('submit(searchBtn)', function(data){
        var index = layer.load(1);
        //重新渲染数据表格
        table.reload("roleListTable",{
            page: {
                currentPage : 1
            },
            where: {
                id : data.field.id.trim(),
                roleName : data.field.roleName.trim(),
                description : data.field.description.trim(),
                status : data.field.status.trim()
            },
            done : function () {
                layer.close(index);
            }
        });
        //禁止表单提交
        return false;
    });

    //添加角色
    $('#btn_add').on('click',function () {
        layerBox({
            title : '添加角色',
            url : contextPath + "/sysRole/toAddRole"
        })
    });

    //toolbar事件
    table.on('tool(roleList)',function (obj) {
        var event = obj.event;
        var data = obj.data;
        if(event === 'edit'){//修改按钮
            layerBox({
                title : '修改角色',
                url : contextPath + "/sysRole/toEditRole?roleId="+data.id
            });
        }else if(event === 'permissionEdit'){//配置按钮
            layerBox({
                title : '菜单配置',
                url : contextPath + "/sysRole/toEditRolePermission?roleId="+data.id
            });
        }
    });


    //批量启用
    $('#btn_start_all').on('click',function () {
        //获取选中行数据
        var datas = table.checkStatus('roleListTable').data;
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
                    url : contextPath + '/sysRole/batchModifyStatus',
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
        var datas = table.checkStatus('roleListTable').data;
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
                    url : contextPath + '/sysRole/batchModifyStatus',
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
        var datas = table.checkStatus('roleListTable').data;
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
                    url : contextPath + '/sysRole/batchRemoveRoles',
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

    //刷新数据表格
    function reloadTable(currentPage) {
        var searchForm  = $('#searchForm').serializeArray();

        //重新渲染数据表格
        if(currentPage != null){
            table.reload("roleListTable",{
                page: {
                    currentPage : currentPage
                },
                where: {
                    id : searchForm[0].value,
                    roleName : searchForm[1].value,
                    description : searchForm[2].value,
                    status : searchForm[3].value
                },
                done : function () {
                    layer.close(index);
                }
            });
        }else{
            table.reload("roleListTable",{
                where: {
                    id : searchForm[0].value,
                    roleName : searchForm[1].value,
                    description : searchForm[2].value,
                    status : searchForm[3].value
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