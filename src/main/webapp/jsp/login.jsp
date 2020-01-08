<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>cmfz Login</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="${path}/jsp/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="${path}/jsp/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="${path}/jsp/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${path}/jsp/assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/jsp/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${path}/jsp/assets/js/jquery.backstretch.min.js"></script>
    <script src="${path}/jsp/assets/js/scripts.js"></script>
    <script src="${path}/jsp/assets/js/jquery.validate.min.js"></script>
    <script>
        function login() {
            $.ajax({
                url:"${path}/admin/login",
                type:"post",
                datatype:"json",
                // 传递表单数据时,通常使用选择器选中该表单,使用serialize()序列化方法,将表单数据全部提交
                data:$("#loginForm").serialize(),
                success:function (data) {
                    if (data.msg=="ok"){
                        location.href="${path}/jsp/main.jsp";
                    }else {
                        alert(data.msg);
                    }
                }
            })
        }
        //点击切换验证码
        $("#captchaImage").click(function () {
            $("#captchaImage").prop("src", "${path}/admin/yzm?" + new Date().getTime());
        })

    </script>

</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showAll</h3>
                            <p>Enter your username and password to log on:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" action="" method="post" class="login-form" id="loginForm">
                            <span id="msgDiv"></span>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">username</label>
                                <input type="text" minlength="3" name="username" placeholder="请输入用户名..."
                                       class="form-username form-control" required id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">password</label>
                                <input type="password" minlength="3" name="password" placeholder="请输入密码..."
                                       class="form-password form-control" required id="form-password">
                            </div>
                            <div class="form-group">
                                <%--<label class="sr-only" for="form-code">Code</label>--%>
                                <img id="captchaImage" style="height: 48px" class="captchaImage"
                                     src="${path}/admin/yzm">
                                <input style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       minlength="4" required type="test" name="code" id="form-code">
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary form-control" id="log" onclick="login()">登录</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" title="网站模板">网站模板</a></div>


<!-- Javascript -->

<!--[if lt IE 10]>
<script src="assets/js/placeholder.js"></script>
<![endif]-->

</body>

</html>