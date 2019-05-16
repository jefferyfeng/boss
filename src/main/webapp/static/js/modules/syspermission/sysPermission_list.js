//获取隐藏于的值
var contextPath = document.getElementById('contextPath').value;

//记录当前node节点
var currentNode = null;

layui.use(['layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var index = layer.load(1);
    function configTree() {
        var setting = {
            async: {
                enable: true,
                url: contextPath + '/sysPermission/listPermissions',
                autoParam: ["id"],
            },
            view: {
                expandSpeed: "fast",//动画速度"slow", "normal", "fast"
                selectedMulti:false
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    currentNode = treeNode;
                    buildNodeInfo(currentNode);
                },
                onAsyncSuccess: function () {
                    layer.close(index);
                }
            }
        };
        return setting;
    }

    reloadTree();


    //构建右侧节点详细信息表格
    function buildNodeInfo(node) {
        var loading = layer.load(1);
        //清空子元素
        $('#nodeView').empty();
        //顶部描述
        var fieldset = $('<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;"><legend style="font-weight: bold">菜单详情</legend></fieldset>');
        $('#nodeView').append(fieldset);

        //构建表格
        var table = $('<table class="layui-table"></table>');

        //构建详细节点信息
        $.ajax({
            type : 'get',
            url : contextPath + '/sysPermission/queryOne/'+ node.id,
            dataType : 'json',
            async : false,
            success : function (result) {
                layer.close(loading);

                //隐藏当前菜单id
                $('#nodeView').append('<input id="menuId" type="hidden" value="'+result.id+'"/>');
                /*if(resSuccess(result)){
                    $('#nodeView').append(table);
                }else{
                    failMsg();
                }*/
                table.append('<tr><td width="20%">菜单名称</td><td>'+getData(result.permissionName)+'</td></tr>');
                table.append('<tr><td width="20%">菜单地址</td><td>'+getData(result.permissionUrl)+'</td></tr>');
                table.append('<tr><td width="20%">菜单次序</td><td>'+getData(result.sequence)+'</td></tr>');
                table.append('<tr><td width="20%">菜单图标</td><td>'+getData(result.permissionIcon)+'</td></tr>');
                table.append('<tr><td width="20%">创建时间</td><td>'+getData(result.createDate)+'</td></tr>');
                $('#nodeView').append(table);
            },
            error : function () {
                failMsg();
            }
        });
    }

    //添加下级
    $('#addChildMenu').on('click',function () {
        if($('#nodeView').find('#menuId').length <= 0){
            warnMsg('请先选择菜单!');
        }else{
            var parentId = $('#nodeView').find('#menuId').val();
            layerBox({
                title : '添加下级菜单',
                url : contextPath + "/sysPermission/toAddChildPermission?parentId=" + parentId
            });
        }
    });

    //添加同级
    $('#addMenu').on('click',function () {
        if($('#nodeView').find('#menuId').length <= 0){
            warnMsg('请先选择菜单!');
        }else{
            var brotherId = $('#nodeView').find('#menuId').val();
            layerBox({
                title : '添加同级菜单',
                url : contextPath + "/sysPermission/toAddBrotherPermission?brotherId=" + brotherId
            });
        }
    });

    //删除
    $('#btn_delete').on('click',function () {
        if($('#nodeView').find('#menuId').length <= 0){
            warnMsg('请先选择菜单!');
        }else{
            if(currentNode.isParent){
                layer.msg("菜单下有子菜单不能删除！",{
                    icon : 7,
                    time: 2000
                });
            }else{
                var loading = layer.load(1);
                var parentId = $('#nodeView').find('#menuId').val();
                $.ajax({
                    type : 'post',
                    url : contextPath + '/sysPermission/removePermission',
                    data : {
                        permissionId : parentId
                    },
                    dataType : 'json',
                    success : function (result) {
                        layer.close(loading);
                        if(resSuccess(result.code)){
                            succMsg('删除菜单成功！');
                            //刷新父节点
                            reloadParentNode(currentNode.id);
                        }else{
                            failMsg();
                        }
                    },
                    error : function () {
                        layer.close(loading);
                        failMsg();
                    }
                })
            }
        }
    });

    //修改
    $('#btn_edit').on('click',function () {
        if($('#nodeView').find('#menuId').length <= 0){
            warnMsg('请先选择菜单!');
        }else{
            var permissionId = $('#nodeView').find('#menuId').val();
            layerBox({
                title : '修改菜单',
                url : contextPath + "/sysPermission/toEditPermission?permissionId=" + permissionId
            });
        }
    });

    //刷新整个树
    function reloadTree() {
        $.fn.zTree.init($("#permissionsTree"), configTree());
        $('#nodeView').empty();
    }

    //刷新父节点
    function reloadParentNode(id){
        var treeObj = $.fn.zTree.getZTreeObj("permissionsTree");
        var nownode = treeObj.getNodesByParam("id", id, null);
        var parent=nownode[0].getParentNode();
        treeObj.reAsyncChildNodes(parent, "refresh",false);//加载后并打开
        $('#nodeView').empty();
    }

    //刷新当前节点
    function reloadNode(id){
        var treeObj = $.fn.zTree.getZTreeObj("permissionsTree");
        var nownode = treeObj.getNodesByParam("id", id, null);
        treeObj.reAsyncChildNodes(nownode[0], "refresh");
        $('#nodeView').empty();
    }

    
    //用于子iframe刷新ztree
    window.searchReload = function () {
        reloadTree();
    }

    window.realoadParentNode = function () {
        reloadParentNode(currentNode.id);
    }

    window.realoadCurrentNode = function () {
        reloadNode(currentNode.id);
    }
});