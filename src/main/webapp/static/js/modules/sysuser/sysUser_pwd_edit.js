//获取隐藏域的值
var contextPath = document.getElementById('contextPath').value;

layui.use(['form','layer'],function() {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //添加验证规则
    form.verify({
        oldPwd: function (value, item) {

        },
        newPwd: function (value, item) {
            if (value.length < 6) {
                return "密码长度不能小于6位";
            }
        },
        confirmPwd: function (value, item) {
            if (!new RegExp($("#oldPwd").val()).test(value)) {
                return "两次输入密码不一致，请重新输入！";
            }
        }
    });

    form.on("submit(changePwd)",function(data){
        var loading = layer.load(1);
        var user = data.field;
        $.ajax({
            type : 'post',
            url : contextPath + '/sysUser/changePwd',
            data : {
                userId : user.userId,
                oldPwd : user.oldPwd,
                newPwd : user.newPwd
            },
            dataType : 'json',
            success : function (result) {
                layer.close(loading);
                if(resSuccess(result.code)){
                    succMsg("密码修改成功！",function () {
                        
                    })
                }else{
                    failMsg(result.msg);
                }
            },
            error : function () {
                layer.close(loading);
                failMsg();
            }
        });
        return false;
    });
});