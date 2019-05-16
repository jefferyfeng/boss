//获取隐藏域的值
var contextPath = document.getElementById('contextPath').value;

layui.use(['form','layer','jquery'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //自定义验证规则
    form.verify({
        username: function(value){
            if(value.length < 1){
                return '用户名不能为空';
            }
        },
        password: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ],
        email: [
            /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/
            ,'邮箱格式不正确'
        ],
        phone:[
              /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,1,2,5-9])|(177))\d{8}$/,
            ,'手机号格式不正确'
        ],
    });

    //提交添加
    form.on("submit(addUser)",function(data){
        var user = data.field;
        $.ajax({
            type : 'post',
            url : contextPath + '/sysUser/addUser',
            async : false,
            data : {
                username : user.username,
                sex : user.sex,
                email : user.email,
                password : user.password,
                phone : user.phone,
                status : user.status,
            },
            dataType : 'json',
            success : function (result) {
                if(result.code === 1 || result.code === '1'){
                    layer.msg('添加成功！',{
                        icon : 6,
                        time: 2000
                    },function () {
                        //关闭当前iframe
                        var index = parent.layer.getFrameIndex(window.name);
                        window.parent.searchReload();
                        parent.layer.close(index);
                    });
                }else{
                    layer.msg('网络繁忙，请稍后再试！',{
                        icon : 5,
                        time: 2000
                    },function () {
                        //回调
                    });
                }
            }
        });
        return false;
    });
})