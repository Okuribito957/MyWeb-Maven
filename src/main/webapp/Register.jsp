<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/4/5
  Time: 下午 1:58
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
    <title>注册</title>
    <!-- Stylesheets -->
    <link href="style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="style/font-awesome.css">
    <link href="style/style.css" rel="stylesheet">
    <link href="../webapp/style/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 Support for IE -->
    <!--[if lt IE 9]>
    <script src="js/html5shim.js"></script>
    <![endif]-->

    <!-- Favicon -->
    <link rel="shortcut icon" href="../webapp/img/favicon/favicon.png">
</head>
<body>
    <div class="admin-form">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <!-- Widget starts -->
                    <div class="widget wred">
                        <div class="widget-head">
                            <i class="icon-lock"></i> 注册
                        </div>
                        <div class="widget-content">
                            <div class="padd">
                                <!-- Register from -->
                                <form class="form-horizontal" action="registerservlet" method="post" onsubmit="return checkInput();">
                                    <!-- Name -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3" for="txtLoginName">登录名:</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" id="txtLoginName" name="txtloginname" placeholder="不少于5位的字母数字组合">
                                            <span class="label label-danger" id="spanLoginName"></span>
                                        </div>
                                    </div>
                                    <!-- Password -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3" for="txtPassword">密码:</label>
                                        <div class="col-lg-9">
                                            <input type="password" class="form-control" id="txtPassword" name="txtpassword" placeholder="不少于6位的包括字母数字组合">
                                            <span class="label label-danger" id="spanPassword"></span>
                                        </div>
                                    </div>
                                    <!-- VPassword -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3" for="txtVPassword">确认密码:</label>
                                        <div class="col-lg-9">
                                            <input type="password" class="form-control" id="txtVPassword" name="txtvpassword" placeholder="再次输入密码">
                                            <span class="label label-danger" id="spanVPassword">test</span>
                                        </div>
                                    </div>
                                    <!-- Email -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3" for="txtEmail">邮箱:</label>
                                        <div class="col-lg-9">
                                            <input type="email" class="form-control" id="txtEmail" name="txtemail" placeholder="只支持境内邮箱">
                                            <span class="label label-danger" id="spanEmail">test</span>
                                        </div>
                                    </div>
                                    <!-- Username -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3" for="txtName">姓名:</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" id="txtName" name="txtname" placeholder="请输入中文姓名">
                                            <span class="label label-danger" id="spanName">test</span>
                                        </div>
                                    </div>
                                    <!-- SEX -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3" for="txtFemale">性别:</label>
                                        <div class="col-lg-9">
                                            <input type="radio" id="txtFemale" name="radSex" checked="checked">男 &nbsp&nbsp
                                            <input type="radio" id="txtMale" name="radSex" >女
                                        </div>
                                    </div>
                                    <!-- Data -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3" for="data">出生日期:</label>
                                        <div class="col-lg-9">
                                            <input type="date" class="form-control" id="data" name="data" checked>
                                            <span class="label label-danger" id="spanData">test</span>
                                        </div>
                                    </div>
                                    <!-- HOBBY -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3">爱好</label>
                                        <div class="col-lg-9">
                                            <input type="checkbox" name="chkHobby" value="山地速降">山地速降 &nbsp&nbsp
                                            <input type="checkbox" name="chkHobby" value="翼装飞行">翼装飞行 &nbsp&nbsp
                                            <input type="checkbox" name="chkHobby" value="高山滑雪">高山滑雪 &nbsp&nbsp
                                            <input type="checkbox" name="chkHobby" value="洞穴潜水">洞穴潜水 &nbsp&nbsp
                                        </div>
                                    </div>
                                    <!-- Select box -->
                                    <div class="form-group">
                                        <label class="control-label col-lg-3">登录类型</label>
                                        <div class="col-lg-9">
                                            <select class="form-control">
                                                <option>&nbsp;</option>
                                                <option value="学生">学生</option>
                                                <option value="教师">教师</option>
                                                <option value="管理员">管理员</option>
                                            </select>
                                        </div>
                                    </div>

                                    <!-- Accept box and button s-->
                                    <div class="form-group">
                                        <div class="col-lg-9 col-lg-offset-3">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="true" value="true"> 同意用户协议
                                                </label>
                                            </div>
                                            <br />
                                            <button type="submit" class="btn btn-danger">注册</button>
                                            <button type="reset" class="btn btn-success" onclick="CleanMessage()">重置</button>
                                        </div>
                                    </div>
                                    <br />
                                </form>

                            </div>
                        </div>
                        <div class="widget-foot">
                            已有账号？ <a href="loginservlet">点此登录</a>
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
            var spanLoginName = document.getElementById("spanLoginName");
            if (txtLoginName.value.length == 0){
                spanLoginName.innerText = "登录名不能为空";
                flag = false;
            } else if (txtLoginName.value.length < 5){
                spanLoginName.innerText = "登录名不能少于5位";
                flag = false;
            } else {
                spanLoginName.innerText = "";
            }

            var txtPassword = document.getElementById("txtpassword");
            var spanPassword = document.getElementById("spanPassword");
            var reg = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$)/);
            if (txtPassword.value.length == 0){
                spanPassword.innerText = "密码不能为空";
                flag = false;
            } else if (txtPassword.value.length < 6){
                spanPassword.innerText = "密码不能少于8位";
                flag = false;
            }
            // else if (reg.test(txtPassword.value)){
            //     spanPassword.innerText = "密码中至少含有一位数字或字母"
            //     flag = false;
            // }
            else {
                spanPassword.innerText = "";
            }

            // var txtVPassword = document.getElementsById("txtvpassword");
            // var spanVPassword = document.getElementById("spanVPassword");
            // if (txtVPassword.value != txtPassword.value){
            //     spanVPassword.innerText = "两次密码不相同";
            //     flag = false;
            // } else {
            //     spanVPassword.innerText = "";
            // }
            //
            // var txtEmail = document.getElementsById("txtemail");
            // var spanEmail = document.getElementById("spanEmail");
            // if (txtEmail.value.length.match(/^\w+@\w+\.\w+$/i)){
            //     spanEmail.innerText = "请输入正确的邮箱地址";
            //     flag = false;
            // } else {
            //     spanEmail.innerText = "";
            // }
            //
            // var txtName = document.getElementsById("txtname");
            // var spanName= document.getElementById("spanName");
            // if (txtName.value.replace(/[^\u4e00-\u9fa5\w]/g,''){
            //     spanName.innerText = "只能输入中文";
            //     flag = false;
            // }else {
            //     spanName.innerText = "";
            // }
            //
            // var Data = document.getElementsById("data");
            // var spanDate = document.getElementById("spanDate")
            // if (spanDate.value.length == 0){
            //     spanDate.innerText = "请选择日期";
            //     flag = false;
            // }else {
            //     spanDate.innerText = "";
            // }

            return flag;
        }
        function CleanMessage() {
            var spanLoginName = document.getElementById("spanLoginName");
            var spanPassword = document.getElementById("spanPassword");
            var spanVPassword = document.getElementById("spanVPassword");
            var spanEmail = document.getElementById("spanEmail");
            var spanData = document.getElementById("spanData");
            var spanName= document.getElementById("spanName");
            spanLoginName.innerText = "";
            spanPassword.innerText = "";
            spanVPassword.innerText = "";
            spanEmail.innerText = "";
            spanData.innerText = "";
            spanName.innerText = "";
        }
    </script>
</body>
</html>
