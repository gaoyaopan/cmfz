<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">
    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>
    <script src="${path}/kindeditor/kindeditor-all.js"></script>
    <script src="${path}/kindeditor/kindeditor-all-min.js"></script>
</head>
<script>
    $(function () {
        //创建表单
        $("#anTable").jqGrid({
            url :"${path}/user/selectByPage",
            editurl: "${path}/user/edit",
            datatype : "json",
            rowNum : 3,
            rowList : [3, 6, 9],
            pager : '#anPage',
            viewrecords : true,//是否展示总条数
            styleUI:"Bootstrap",
            height:"auto",
            autowidth:true,
            colNames : [ 'id', '手机号', '密码', '状态','姓名','法名','性别','位置','注册时间' ],
            colModel : [
                {name : 'id',index : 'id',width : 55,align : "center"},
                {name : 'phone',editable:true,index : 'id',width : 55,align : "center"},
                {name : 'password',editable:true,index : 'id',width : 55,align : "center"},
                {
                    name: 'status',
                    align: "center",
                    formatter: function (data) {
                        if (data == "1") {
                            return "展示";
                        } else return "冻结";
                    },
                    editable: true,
                    editrules: {required: true},
                    edittype: "select",
                    editoptions: {value: "1:展示;2:冻结"}
                },
                {name : 'name',editable:true ,index : 'name asc',width : 80,align : "center"},
                {name : 'nickName',editable:true ,index : 'name asc',width : 80,align : "center"},
                {name : 'sex',editable:true ,align : "center",
                    formatter: function (data) {
                        if (data == "1") {
                            return "女";
                        } else return "男";
                    },
                    editable: true,
                    editrules: {required: true},
                    edittype: "select",
                    editoptions: {value: "1:女;0:男"}
                },
                {name : 'location',editable:true,index : 'note',width : 150,sortable : false,align : "center"},
                {name : 'rigestDate',width : 150,sortable : false,align:"center",editable:true,edittype:"date"}
            ],

        });
        //增删改查操作
        $("#anTable").jqGrid('navGrid', '#anPage', {add : true,del : true,addtext:"添加"},
            {
                closeAfterAdd:true, //关闭添加框
                //提交之后操作
                afterSubmit: function (data) {

                    //文件上传
                    $.ajaxFileUpload({
                        url: "${path}/user/upload",
                        type: "post",
                        dataType: "json",
                        data: {id: data.responseText},
                        success: function () {
                            //刷新表单
                            $("#anTable").trigger("reloadGrid");
                        }
                    });
                    return "cjh";
                }
            },   //修改之后的额外操作
            {
                closeAfterDel:true, //关闭添加框

            }
        );
        //导出数据
        $("#btn1").click(function(){

            $.post("${path}/user/export",function (data) {
                alert(data.message);
            })
        });
    });


    function poiExe(){
        $.ajax({
            url:"${path}/user/imageUpload",
            type:"post",
            datatype: "json",
            success:function (){
                $("#anTable").trigger("reloadGrid");
            }
        })
    }

    function lead(){
        $.ajax({
            url:"${path}/user/leadExcel",
            type:"post",
            datatype: "json",
            success:function (){
                $("#anTable").trigger("reloadGrid");
            }
        })
    }

</script>
<ul class="nav nav-tabs">
    <li><a>用户信息</a></li>
    <li><a onclick="poiExe()">导出</a></li>
    <li><a onclick="lead()">导入</a></li>
</ul>


<%--初始化面板--%>
<div class="panel panel-info">

    <%--面板标题--%>
    <div class="panel panel-heading">
        <h3>用户管理</h3>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs">
        <li class="active"><a >用户管理</a></li>
    </ul>
    <%--初始化表单--%>
    <table id="anTable"/>

    <%--分页工具栏--%>
    <div id="anPage" />

</div>