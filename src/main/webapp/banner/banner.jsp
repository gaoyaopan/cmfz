<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        //创建表单
        $("#bnTable").jqGrid({
            url :"${path}/banner/queryByPage",
            editurl:"${path}/banner/edit",
            datatype : "json",
            rowNum : 3,
            rowList : [ 3, 6, 9 ],
            pager : '#bnPage',
            viewrecords : true,//是否展示总条数
            styleUI:"Bootstrap",
            height:"auto",
            autowidth:true,
            colNames : [ 'id', '标题', '图片', '描述', '状态', '上传时间' ],
            colModel : [
                {name : 'id',align:"center",hidden: true},
                {name : 'title',index : 'invdate',width : 90,align:"center",editable: true},
                {name : 'url',editable:true ,edittype:"file" , formatter:function(cellvalue, options, rowObject){
                        return "<img src='${path}/upload/photo/"+cellvalue+"'style='width:180px;height:80px'/>";
                    }
                },
                {name : 'description',index : 'invdate',width : 90,align:"center",editable: true},
                {name : 'status',align:"center",formatter:function (data) {
                        if (data=="1"){
                            return "展示";
                        } else return "冻结";
                    },editable:true,editrules:{required:true},edittype:"select",editoptions: {value:"1:展示;2:冻结"}
                },
                {name : 'createDate',index : 'note',width : 150,sortable : false,align:"center",editable:true,edittype:"date"}
            ],

        });
        //增删改查操作
        $("#bnTable").jqGrid('navGrid', '#bnPage', {edit : true,add : true,del : true},
            {
                closeAfterEdit:true,
                //提交之后做得事情
                afterSubmit:function (data) {
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/banner/uploadBanner",
                        type:"post",
                        dataType:"json",
                        data:{id:data.responseText},
                        fileElementId:"url",
                        success:function () {
                            //刷新表单
                            $("#bnTable").trigger("reloadGrid");
                        }
                    });
                    return "kjt";//必须要有返回值，可随便写
                }
            },//修改之后得操作
            {
                closeAfterAdd:true,//添加之后得操作
                //提交之后做得事情
                afterSubmit:function (data) {
                    //文件上传
                    $.ajaxFileUpload({
                        url:"${path}/banner/uploadBanner",
                        type:"post",
                        dataType:"json",
                        data:{id:data.responseText},
                        fileElementId:"url",
                        success:function () {
                            //刷新表单
                            $("#bnTable").trigger("reloadGrid");
                        }
                    });
                    return "kjt";//必须要有返回值，可随便写
                }
            },
            { closeAfterDel:true,} //删除之后得操作
        );

    })

</script>
<%--初始化面板--%>
<div class="panel panel-info">

    <%--面板标题--%>
    <div class="panel panel-heading">
        <h3>轮播图管理</h3>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a >轮播图管理</a></li>
    </ul>

    <%--初始化表单--%>
    <table id="bnTable"/>

    <%--分页工具栏--%>
    <div id="bnPage" />

</div>
