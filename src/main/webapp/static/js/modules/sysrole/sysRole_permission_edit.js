//获取隐藏于的值
var contextPath = document.getElementById('contextPath').value;
var roleId = document.getElementById('roleId').value;

layui.use(['form','layer'],function() {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var index = layer.load(1);
    function configTree() {
        var setting = {
            async: {
                enable: true,
                url: contextPath + '/sysRolePermission/listRolePermissions/'+roleId
            },
            check: {
                enable: true
            },
            view: {
                expandSpeed: "fast",//动画速度"slow", "normal", "fast"
                selectedMulti:true,
                dblClickExpand: true,
            },
            callback: {
                onAsyncSuccess: function () {
                    layer.close(index);
                }
            }
        };
        return setting;
    }

    reloadTree();


    //提交添加
    form.on("submit(editPermission)",function(data){
        var loading = layer.load(1);
        var zTreeObj = $.fn.zTree.getZTreeObj("permissionsTree");
        var checkedNodes = zTreeObj.getCheckedNodes(true);
        var uncheckedNodes = zTreeObj.getCheckedNodes(false);

        var checkedPermissionIds = '';
        var uncheckedPermissionIds = '';
        checkedNodes.forEach(function (permission) {
            checkedPermissionIds = checkedPermissionIds + permission.id + ',';
        });
        uncheckedNodes.forEach(function (permission) {
            uncheckedPermissionIds = uncheckedPermissionIds + permission.id + ',';
        });

        if(checkedPermissionIds !=null && checkedPermissionIds != ''){
            checkedPermissionIds = checkedPermissionIds.substring(0,checkedPermissionIds.length-1);
        }
        if(uncheckedPermissionIds !=null && uncheckedPermissionIds != ''){
            uncheckedPermissionIds = uncheckedPermissionIds.substring(0,uncheckedPermissionIds.length-1);
        }

        $.ajax({
            type : 'post',
            url : contextPath + '/sysRolePermission/editRolePermission',
            async : false,
            data : {
                checkedPermissionIds : checkedPermissionIds,
                uncheckedPermissionIds : uncheckedPermissionIds,
                roleId : roleId,
            },
            dataType : 'json',
            success : function (result) {
                layer.close(loading);
                if(resSuccess(result.code)){
                    var index = parent.layer.getFrameIndex(window.name);
                    succMsg('角色菜单配置成功！',function () {
                        parent.layer.close(index);
                    });
                }else{
                    failMsg(null,function () {
                        parent.layer.close(index);
                    });
                }
            },
            error: function () {
                layer.close(loading);
                failMsg(null,function () {
                    parent.layer.close(index);
                });
            }
        });
        return false;
    });

    //重置时刷新树
    $('#resetBtn').on('click',function () {
        reloadTree();
    });


    //刷新整个树
    function reloadTree() {
        $.fn.zTree.init($("#permissionsTree"), configTree());
    }
});