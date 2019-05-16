//弹出框 添加 修改
function layerBox(config){
    layer.open({
        title : config.title,
        type : 2,//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
        area: ['550px','550px'],
        content : config.url,
        maxmin : true,
    });
}

//用于表格的layer
function layerBoxTable(config){
    layer.open({
        title : config.title,
        type : 2,//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
        area: config.area,
        content : config.url,
        maxmin : true,
    });
}

//处理null
function getData(data) {
    return data == null ? '' :data ;
}

//成功消息
function succMsg(msg,callback) {
    layer.msg(msg,{
        icon : 1,
        time: 2000
    },callback);
}

//警告消息
function warnMsg(msg,callback) {
    layer.msg(msg,{
        icon : 7,
        time: 2000
    },callback);
}

//异常消息
function failMsg(msg,callback) {
    if(msg == null || msg === ''){
        msg = '网络繁忙，请稍后再试！';
    }
    layer.msg(msg,{
        icon : 7,
        time: 2000
    },callback);
}

//处理响应是否成功
function resSuccess(code) {
    if(code === 1 || code === '1') return true;
    return false;
}

//通用的ajax请求 默认异步 post提交
/*
function ajaxSubmit(config) {
    var loading = layer.load(1);
    $.ajax({
        type : 'post',
        url : config.url,
        data : config.data,
        dataType : 'json',
        success : config.func,
    });
}
*/



//格式化性别
function formatSex(data){
    if(data == null || data === ''){
        return '';
    }else if (data === '1' || data === 1 ){
        return '男';
    }else if(data === '2' || data === 2){
        return '女';
    }else{
        return '';
    }
}

//格式化邮箱
function formatEmail(data){
    if(data == null || data == ''){
        return '';
    }else{
        return '<a class="layui-blue" href="mailto:'+data+'">'+data+'</a>';
    }
}

//格式化状态
function formatStatus(data) {
    if(data == null || data === ''){
        return '';
    }else if (data === '0' || data === 0 ){
        return '<span style="color: red">禁用</span>';
    }else if (data === '1' || data === 1 ){
        return '<span style="color: green">启用</span>';
    }else{
        return '';
    }
}