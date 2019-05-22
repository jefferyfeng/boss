//获取隐藏域的值
var contextPath = document.getElementById('contextPath').value;

var _tool = null;

layui.use(['form','layer','table','laytpl','laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        laydate = layui.laydate,
        table = layui.table;

    var index = layer.load(1);

    
    //渲染数据表格列表
    var tableIns = table.render({
        elem: '#demoShardingList',
        url : contextPath + '/demoSharding/listDemoShardings',
        cellMinWidth : 150,
        page : true,
        /*height : "full-200",*/
        limits : [10,15,20,25],
        limit : 10,
        id : 'demoShardingListTable',
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
            {field: 'id', title: '主键id', width:200, align:"center", },
            {field: 'username', title: '用户名', width:200, align:"center", },
            {field: 'score', title: '成绩', width:200, align:"center", },
            {title: '操作', width:220, fixed:"right", templet:'#demoShardingListBar',align:"center"}
        ]]
    });

    //顶部查询
    form.on('submit(searchBtn)', function(data){
        var index = layer.load(1);
        //重新渲染数据表格
        table.reload("demoShardingListTable",{
            page: {
                curr : 1 //layui此处有点bug 前一步已经把curr改为currentPage了，但此处仍为curr
            },
            where: data.field,
            done : function () {
                layer.close(index);
            }
        });
        //禁止表单提交
        return false;
    });

    //toolbar事件
    table.on('tool(demoShardingList)',function (obj) {
        var event = obj.event;
        var data = obj.data;
        if(event === 'edit'){//修改按钮
            layerBox({
                title : '修改',
                url : contextPath + "/demoSharding/toEditDemoSharding?id="+data.id
            });
        }else if(event === 'delete'){//删除按钮
            layer.confirm('你确定删除吗?', {icon: 3, title:'提示'}, function(index){
                var loading = layer.load(1);
                $.ajax({
                    type : 'post',
                    url : contextPath + '/demoSharding/remove',
                    data : {
                        "id": data.id
                    },
                    dataType : 'json',
                    success : function (result) {
                        if(resSuccess(result.code)){
                            layer.close(loading);
                            layer.close(index);
                            //重新渲染数据表格
                            reloadTable(1);

                            succMsg('删除成功！');
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
        }else if(event === 'other'){//其他按钮
            layer.msg("无其他事件~~~");
        }
    });

    //添加
    $('#btn_add').on('click',function () {
        layerBox({
            title : '添加',
            url : contextPath + "/demoSharding/toAddDemoSharding"
        })
    });



    //批量删除
    $('#btn_delete_all').on('click',function () {
        var datas = table.checkStatus('demoShardingListTable').data;
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
                    url : contextPath + '/demoSharding/batchRemove',
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

    //平均值
    $('#btn_avg_all').on('click',function () {
        //layer.msg("其他");
        $.ajax({
            type : "post",
            url : contextPath + "/demoSharding/avg",
            dataType : 'json',
            success : function (result) {
                layer.msg(result)
            }
        })
    });

    //分组值
    $('#btn_groupBy_all').on('click',function () {
        //layer.msg("其他");
        $.ajax({
            type : "post",
            url : contextPath + "/demoSharding/groupby",
            dataType : 'json',
            success : function (result) {
                layer.msg(JSON.stringify(result))
            }
        })
    });


    //distinct
    $('#btn_distinct_all').on('click',function () {
        //layer.msg("其他");
        $.ajax({
            type : "post",
            url : contextPath + "/demoSharding/distinct",
            dataType : 'json',
            success : function (result) {
                layer.msg(JSON.stringify(result));
            }
        })
    });
    
    //刷新数据表格
    function reloadTable(currentPage) {
        var searchForm  = $('#searchForm').serializeArray();

        //重新渲染数据表格
        if(currentPage != null){
            table.reload("demoShardingListTable",{
                page: {
                    currentPage : currentPage
                },
                where: {
                    id : searchForm[0].value,
                    username : searchForm[1].value,
                    score : searchForm[2].value,
                    time : new Date().getTime()
                },
                done : function () {
                    layer.close(index);
                }
            });
        }else{
            table.reload("demoShardingListTable",{
                where: {
                    id : searchForm[0].value,
                    username : searchForm[1].value,
                    score : searchForm[2].value,
                    time : new Date().getTime()
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
