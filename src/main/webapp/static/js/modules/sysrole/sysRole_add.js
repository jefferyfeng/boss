//获取隐藏域的值
var contextPath = document.getElementById('contextPath').value;

layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //自定义验证规则
    form.verify({
        roleName: function(value){
            if(value.length < 1){
                return '角色名称不能为空';
            }
        },
        description: function (value) {
            if(value.length < 1){
                return '角色描述不能为空';
            }
        },
    });

    //提交添加
    form.on("submit(addRole)",function(data){
        var loading = layer.load(1);
        var role = data.field;
        var index = parent.layer.getFrameIndex(window.name);
        $.ajax({
            type : 'post',
            url : contextPath + '/sysRole/add',
            async : false,
            data : {
                roleName : role.roleName,
                description : role.description,
                status : role.status,
            },
            dataType : 'json',
            success : function (result) {
                layer.close(loading);
                if(resSuccess(result.code)){
                    succMsg('角色添加成功！',function () {
                        parent.searchReload(1);
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
        return false;
    });
})