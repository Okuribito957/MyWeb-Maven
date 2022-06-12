<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/29
  Time: 下午 1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ch">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <!-- Title and other stuffs -->
    <title>学校信息管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="">
    <!-- Stylesheets -->
    <link href="style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="style/font-awesome.css">
    <link href="style/style.css" rel="stylesheet">
    <link href="style/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 Support for IE -->
    <!--[if lt IE 9]>
    <script src="js/html5shim.js"></script>
    <![endif]-->

    <!-- Favicon -->
    <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body>

<!-- Form area -->
<div class="admin-form">
    <div class="container">

        <div class="row">
            <div class="col-md-12">
                <!-- Widget starts -->
                <div class="widget worange">
                    <!-- Widget head -->
                    <div class="widget-head">
                        <i class="icon-lock"></i>登录 <span class="label label-danger" id="spanmessage">${requestScope.ErrorMessage}</span>
                    </div>
                    <div class="widget-content">
                        <div class="padd">
                            <!-- Login form -->
                            <form class="form-horizontal" action="loginservlet" method="post" onsubmit="return checkInput();">
                                <!-- Email -->
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="txtLoginName" >用户名</label>
                                    <div class="col-lg-9">
                                        <input name="u" type="text" class="form-control" id="txtLoginName" placeholder="用户名" value="${requestScope.Loginname}">
                                        <span class="label label-danger" id="spanLoginName"></span>
                                    </div>
                                </div>
                                <!-- Password -->
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="txtPassword">密码</label>
                                    <div class="col-lg-9">
                                        <input name="p" type="password" class="form-control" id="txtPassword" placeholder="密码">
                                        <span class="label label-danger" id="spanPassword"></span>
                                    </div>
                                </div>
                                <!-- Remember me checkbox and sign in button -->
                                <div class="form-group">
                                    <div class="col-lg-9 col-lg-offset-3">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="chkRememberLoginName"> 记住我 <br>
                                            </label>
                                            <label>
                                                <input type="checkbox" name="chkAutoLogin"> 自动登录
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9 col-lg-offset-2">
                                    <button type="submit" class="btn btn-danger">登录</button>
                                    <button type="reset" class="btn btn-default" onclick="CleanMessage()">重置</button>
                                </div>
                                <br/>
                            </form>

                        </div>
                    </div>
                    <div class="widget-foot">
                        没有用户？ <a href="registerservlet">点此注册</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- JS -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript">
    function checkInput() {
        var flag = true;
        var txtLoginName = document.getElementById("txtLoginName");
        var txtPassword = document.getElementById("txtPassword");
        var spanLoginName = document.getElementById("spanLoginName");
        var spanPassword = document.getElementById("spanPassword");

        if (txtLoginName.value.length == 0){
            spanLoginName.innerText = "登录名不能为空";
            flag = false;
        }
        else if (txtLoginName.value.length < 5){
            spanLoginName.innerText = "登录名不能少于5";
            flag = false;
        }
        else {
            spanLoginName.innerText = "";
        }

        if (txtPassword.value.length == 0){
            spanPassword.innerText = "密码不能为空";
            flag = false;
        }
        else if (txtLoginName.value.length < 5){
            spanPassword.innerText = "密码不能小于5位";
            flag = false;
        }
        else{
            spanPassword.innerText = "";
        }
        return flag;
    }

    function CleanMessage() {
        var spanLoginName = document.getElementById("spanLoginName");
        var spanPassword = document.getElementById("spanPassword");
        var spanmessage = document.getElementById("spanmessage")
        spanLoginName.innerText = "";
        spanPassword.innerText = "";
        spanmessage.innerText = "";
    }
</script>
</body>
</html>
