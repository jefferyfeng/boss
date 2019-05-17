//获取隐藏域的值
var contextPath = document.getElementById('contextPath').value;
var userId = document.getElementById('userId').value;

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
        url : contextPath + '/sysUserRole/listUserRoles?userId='+ userId,
        cellMinWidth : 50,
        page : true,
        /*height : "full-200",*/
        limits : [10,15,20,25],
        limit : 10,
        id : "roleListTable",
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
            {type: "checkbox", fixed:"left", width:50,},
            {field: 'id', title: '角色id', minWidth:50, align:"center"},
            {field: 'roleName', title: '角色名称', minWidth:100, align:"center"},
            {field: 'description', title: '角色描述', minWidth:100, align:"center"},
            /*{field: 'createDate', title: '创建时间', minWidth:200, align:'center'},*/
            {field: 'status', title: '角色状态', minWidth:100, align:'center',templet:function(data){ return formatStatus(data.status); }},
        ]]
    });



    //提交修改
    form.on("submit(editUserRole)",function(data){
        //获取当前iframe
        var index = parent.layer.getFrameIndex(window.name);
        //获取当前页所有数据
        var roleList = table.cache.roleListTable;
        if(roleList.length <= 0){
            warnMsg('您没有配置任何角色！',function () {
                parent.layer.close(index);
            });
        }else{
            var loading = layer.load(1);
            //处理选中、未选中角色
            var checkedIds = "";//选中的ids
            var uncheckedIds = "";//未选中的ids
            roleList.forEach(function (role) {
                if(role.LAY_CHECKED){
                    checkedIds = checkedIds + role.id + ',';
                }else{
                    uncheckedIds = uncheckedIds + role.id + ',';
                }
            });

            if(checkedIds != ''){
                checkedIds = checkedIds.substring(0,checkedIds.length-1);
            }
            if(uncheckedIds != ''){
                uncheckedIds = uncheckedIds.substring(0,uncheckedIds.length-1);
            }

            $.ajax({
                type : 'post',
                url : contextPath + '/sysUserRole/editUserRole',
                data : {
                    "checkedIds" : checkedIds,
                    "uncheckedIds" : uncheckedIds,
                    userId : userId
                },
                async : false,
                dataType : 'json',
                success : function (result) {
                    layer.close(loading);
                    if(resSuccess(result.code)){
                        succMsg('角色配置成功！',function () {
                            parent.searchReload();
                            parent.layer.close(index);
                        });
                    }else{
                        failMsg();
                    }
                },
                error : function () {
                    layer.close(loading);
                    failMsg();
                }
            });
        }
        return false;
    });
});