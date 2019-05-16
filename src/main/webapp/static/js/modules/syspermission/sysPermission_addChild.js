//获取隐藏域的值
var contextPath = document.getElementById('contextPath').value;

layui.use(['form','layer','jquery'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //自定义验证规则
    form.verify({
        permissionName: function(value){
            if(value.length < 1){
                return '菜单名称不能为空';
            }
        },
    });

    //提交添加
    form.on("submit(addChildPermission)",function(data){
        var permission = data.field;
        console.log(permission);
        $.ajax({
            type : 'post',
            url : contextPath + '/sysPermission/add',
            async : false,
            data : {
                permissionName : permission.permissionName,
                parentId : permission.parentPermissionId,
                permissionUrl : permission.permissionUrl,
                sequence : permission.sequence,
                permissionIcon : permission.permissionIcon,
            },
            dataType : 'json',
            success : function (result) {
                if(resSuccess(result.code)){
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.realoadParentNode();
                    parent.layer.close(index);
                    succMsg('菜单添加成功！');
                }else{
                    failMsg();
                }
            },
            error : function () {
                failMsg();
            }
        });
        return false;
    });
})