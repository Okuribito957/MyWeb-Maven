<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/4/15
  Time: 上午 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <!-- Title and other stuffs -->
    <title>学生信息管理系统-管理员首页</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="">
    <!-- Stylesheets -->
    <link href="style/bootstrap.css" rel="stylesheet">
    <!-- Font awesome icon -->
    <link rel="stylesheet" href="style/font-awesome.css">
    <!-- jQuery UI -->
    <link rel="stylesheet" href="style/jquery-ui.css">
    <!-- Calendar -->
    <link rel="stylesheet" href="style/fullcalendar.css">
    <!-- prettyPhoto -->
    <link rel="stylesheet" href="style/prettyPhoto.css">
    <!-- Star rating -->
    <link rel="stylesheet" href="style/rateit.css">
    <!-- Date picker -->
    <link rel="stylesheet" href="style/bootstrap-datetimepicker.min.css">
    <!-- CLEditor -->
    <link rel="stylesheet" href="style/jquery.cleditor.css">
    <!-- Uniform -->
    <link rel="stylesheet" href="style/uniform.default.css">
    <!-- Bootstrap toggle -->
    <link rel="stylesheet" href="style/bootstrap-switch.css">
    <!-- Main stylesheet -->
    <link href="style/style.css" rel="stylesheet">
    <!-- Widgets stylesheet -->
    <link href="style/widgets.css" rel="stylesheet">

    <!-- HTML5 Support for IE -->
    <!--[if lt IE 9]>
    <script src="js/html5shim.js"></script>
    <![endif]-->

    <!-- Favicon -->
    <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body>

<%@ include file="Navbar.jsp" %>

<%@ include file="Header.jsp" %>

<!-- Main content starts -->
<div class="content">

    <%@ include file="AdminSideBar.jsp" %>

    <!-- Main bar -->
    <div class="mainbar">

        <!-- Page heading -->
        <div class="page-head">
            <h2 class="pull-left"><i class="icon-home"></i> 班级信息管理</h2>

            <!-- Breadcrumb -->
            <div class="bread-crumb pull-right">
                <a href="indexservlet"><i class="icon-home"></i> 首页</a>
                <!-- Divider -->
                <span class="divider">/</span>
                <a href="#" class="bread-current">控制台</a>
            </div>

            <div class="clearfix"></div>

        </div>
        <!-- Page heading ends -->

        <!-- Matter -->
            <div class="matter">
                <div class="container">
                    <form class="form-horizontal" role="form" action="manageclazzesservlet" method="post" id="formMain">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="widget wgreen">
                                <div class="widget-head">
                                    <div class="pull-left">
                                        <c:if test="${ empty modifyClazz }">班级信息添加</c:if>
                                        <c:if test="${ not empty modifyClazz }">班级信息修改</c:if>
                                        <span class="label label-danger" id="spanMessage">${requestScope.message}</span>
                                    </div>
                                    <div class="widget-icons pull-right">
                                        <c:if test="${ empty modifyClazz }"><a href="#" class="wminimize"><i class="icon-chevron-down"></i></a></c:if>
                                        <c:if test="${ not  empty modifyClazz }"><a href="#" class="wminimize"><i class="icon-chevron-up"></i></a></c:if>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>

                                <div class="widget-content"  <c:if test="${ empty modifyClazz }">style="display: none"</c:if>>
                                    <div class="padd">

                                            <input type="hidden" id="hidOperationType" name="hidOperationType">
                                            <input type="hidden" id="hidClazzId" name="hidClazzId" value="${ modifyClazz.clazzId }">
                                            <input type="hidden" id="hidCurrentPageIndex" name="hidCurrentPageIndex" value="${currentPageIndex}">
                                            <input type="hidden" id="hidFieldName" name="hidFieldName" value="${fieldName}">
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">班级编号:</label>
                                                <div class="col-lg-4">
                                                    <input type="text" class="form-control" name="txtNumber" id="txtNumber" placeholder="ClassId" value="${ modifyClazz.number }">
                                                    <span class="label label-danger" id="spanNumber"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">班级名称:</label>
                                                <div class="col-lg-4">
                                                    <input type="text" class="form-control" name="txtName" id="txtName" placeholder="ClassName" value="${ modifyClazz.name }">
                                                    <span class="label label-danger" id="spanName"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">开班日期:</label>
                                                <div class="col-lg-4">
                                                    <input type="date" class="form-control" name="txtBeginDate" id="txtBeginDate" placeholder="Input Box" value="${ modifyClazz.beginDate }">
                                                    <span class="label label-danger" id="spanBeginDate"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">毕业日期:</label>
                                                <div class="col-lg-4">
                                                    <input type="date" class="form-control" name="txtEndDate" id="txtEndDate" placeholder="Input Box" value="${ modifyClazz.endDate }">
                                                    <span class="label label-danger" id="spanEndDate"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-4 control-label">班级人数:</label>
                                                <div class="col-lg-4">
                                                    <input type="text" class="form-control"  name="txtStudentCount" id="txtStudentCount" placeholder="StudentCount" value="${ modifyClazz.studentCount }">
                                                    <span class="label label-danger" id="spanStudentCount"></span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-offset-1 col-lg-9">
                                                    <c:if test="${ empty modifyClazz }">
                                                    <button type="button" class="btn btn-danger" onclick="add()">添加</button>
                                                    <button type="reset" class="btn btn-default" onclick="cleanMessage()">取消</button>
                                                    </c:if>
                                                    <c:if test="${ not empty modifyClazz }">
                                                        <button type="button" class="btn btn-danger" onclick="save()">保存</button>
                                                        <button type="reset" class="btn btn-default" onclick="cancelSave()">取消</button>
                                                    </c:if>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                            <div class="widget-foot"></div>
                        </div>
                    </div>


                <div class="row">
                    <div class="col-md-12">
                        <div class="widget wgreen">
                            <div class="widget-head">
                                <div class="pull-left">班级信息查询</div>
                                    <div class="widget-icons pull-right">
                                        <a href="#" class="wminimize"><i class="icon-chevron-down"></i></a>
                                        <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                    </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="widget-content">
                                <div class="padd">
                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">班级编号:</label>
                                        <div class="col-lg-4">
                                            <input type="text" class="form-control" name="txtSearchNumber" id="txtSearchNumber" value="${ searchNumber }">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">班级名称:</label>
                                        <div class="col-lg-4">
                                            <input type="text" class="form-control" name="txtSearchName" id="txtSearchName" value="${ searchName }">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-offset-1 col-lg-9">
                                            <button type="button" class="btn btn-danger" onclick="search()">查询</button>
                                            <button type="reset" class="btn btn-default" onclick="cancelSearch()">取消查询</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="widget-foot"></div>
                    </div>
                </div>
                    </form></div>

        </div>

        <div class="container">
                <div class="row">

                    <div class="col-md-12">

                        <div class="widget">

                            <div class="widget-head">
                                <div class="pull-left">班级信息表
                                    <span class="label label-danger" id="spanMessage2">${requestScope.message2}</span>
                                </div>
                                <div class="widget-icons pull-right">
                                    <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                    <a href="#" class="wclose"><i class="icon-remove"></i></a>
                                </div>
                                <div class="clearfix"></div>
                            </div>

                            <div class="widget-content">

                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th><a href="javascript: sorting('ClazzId')">序号</a></th>
                                        <th><a href="javascript: sorting('Number')">班级编号</a></th>
                                        <th><a href="javascript: sorting('Name')">班级名称</a></th>
                                        <th><a href="javascript: sorting('BeginDate')">开班日期</a></th>
                                        <th><a href="javascript: sorting('EndDate')">毕业日期</a></th>
                                        <th><a href="javascript: sorting('StudentCount')">学生人数</a></th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.clazzes}" var="clazz" begin="${ begin - 1 }" end = "${ end - 1 }">
                                    <tr>
                                        <td>${ clazz.clazzId }</td>
                                        <td>${ clazz.number }</td>
                                        <td>${ clazz.name }</td>
                                        <td>${ clazz.beginDate }</td>
                                        <td>${ clazz.endDate}</td>
                                        <td>${ clazz.studentCount }</td>
                                        <td>
                                            <button class="btn btn-xs btn-warning" onclick="modify(${ clazz.clazzId })"><i class="icon-pencil"></i> </button>
                                            <button class="btn btn-xs btn-danger" onclick="remove(${ clazz.clazzId })"><i class="icon-remove"></i> </button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="widget-foot">
                                    <ul class="pagination pull-left">
                                        <li><a href="#">共${ rowCount }条数据</a></li>
                                        <li><a href="#">第${ currentPageIndex }页</a></li>
                                        <li><a href="#">共${ pageCount }页</a></li>
                                    </ul>
                                    <ul class="pagination pull-right">
                                        <li><a href="javascript: paging(1)">首页</a></li>
                                        <li><a href="javascript: paging(${currentPageIndex - 1})">上一页</a></li>
                                        <li><a href="javascript: paging(${currentPageIndex + 1})">下一页</a></li>
                                        <li><a href="javascript: paging(${pageCount})">尾页</a></li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
        </div>
    </div>
        <!-- Matter ends -->

</div>
    <!-- Mainbar ends -->

    <div class="clearfix"></div>

</div>
<!-- Content ends -->

<%@ include file="Footer.jsp" %>

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>

<!-- JS -->
<script src="js/jquery.js"></script> <!-- jQuery -->
<script src="js/bootstrap.js"></script> <!-- Bootstrap -->
<script src="js/jquery-ui-1.9.2.custom.min.js"></script> <!-- jQuery UI -->
<script src="js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
<script src="js/jquery.rateit.min.js"></script> <!-- RateIt - Star rating -->
<script src="js/jquery.prettyPhoto.js"></script> <!-- prettyPhoto -->

<!-- jQuery Flot -->
<script src="js/excanvas.min.js"></script>
<script src="js/jquery.flot.js"></script>
<script src="js/jquery.flot.resize.js"></script>
<script src="js/jquery.flot.pie.js"></script>
<script src="js/jquery.flot.stack.js"></script>

<!-- jQuery Notification - Noty -->
<script src="js/jquery.noty.js"></script> <!-- jQuery Notify -->
<script src="js/themes/default.js"></script> <!-- jQuery Notify -->
<script src="js/layouts/bottom.js"></script> <!-- jQuery Notify -->
<script src="js/layouts/topRight.js"></script> <!-- jQuery Notify -->
<script src="js/layouts/top.js"></script> <!-- jQuery Notify -->
<!-- jQuery Notification ends -->

<script src="js/sparklines.js"></script> <!-- Sparklines -->
<script src="js/jquery.cleditor.min.js"></script> <!-- CLEditor -->
<script src="js/bootstrap-datetimepicker.min.js"></script> <!-- Date picker -->
<script src="js/jquery.uniform.min.js"></script> <!-- jQuery Uniform -->
<script src="js/bootstrap-switch.min.js"></script> <!-- Bootstrap Toggle -->
<script src="js/filter.js"></script> <!-- Filter for support page -->
<script src="js/custom.js"></script> <!-- Custom codes -->
<script src="js/charts.js"></script> <!-- Charts & Graphs -->

<script type="text/javascript">
    function checkInput() {
        var flag = true;
        var txtNumber = document.getElementById("txtNumber");
        var txtName = document.getElementById("txtName");
        var txtBeginDate = document.getElementById("txtBeginDate");
        var txtEndDate = document.getElementById("txtEndDate");
        var txtStudentCount = document.getElementById("txtStudentCount");

        var spanNumber = document.getElementById("spanNumber");
        var spanName = document.getElementById("spanName");
        var spanBeginDate = document.getElementById("spanBeginDate");
        var spanEndDate = document.getElementById("spanEndDate");
        var spanStudentCount = document.getElementById("spanStudentCount");

        if (txtNumber.value.length == 0){
            spanNumber.innerText = "班级编号不能为空";
            flag = false;
        }else {
            spanNumber.innerText = "";
        }
        if (txtName.value.length == 0){
            spanName.innerText = "班级名称不能为空"
            flag = false;
        }else {
            spanName.innerText = ""
        }
        if (txtBeginDate.value.length == 0){
            spanBeginDate.innerText = "开学日期不能为空";
            flag = false;
        }else {
            spanBeginDate.innerText = "";
        }
        if (txtEndDate.value.length == 0){
            spanEndDate.innerText = "毕业日期不能为空";
            flag = false;
        }else {
            spanEndDate.innerText = "";
        }
        if (txtStudentCount.value.length == 0){
            spanStudentCount.innerText = "学生人数不能为空";
            flag = false;
        }else if (isNaN(txtStudentCount.value)){
            spanStudentCount.innerText("数字格式有误")
            flag = false;
        }else {
            spanStudentCount.innerText = "";
        }
        return flag;
    }
    function cleanMessage() {
        var spanNumber = document.getElementById("spanNumber");
        var spanName = document.getElementById("spanName");
        var spanBeginDate = document.getElementById("spanBeginDate");
        var spanEndDate = document.getElementById("spanEndDate");
        var spanStudentCount = document.getElementById("spanStudentCount");
        var spanMessage = document.getElementById("spanMessage");
        var spanMessage2 = document.getElementById("spanMessage2");
        spanNumber.innerText = "";
        spanName.innerText = "";
        spanBeginDate.innerText = "";
        spanEndDate.innerText = "";
        spanStudentCount.innerText = "";
        spanMessage.innerText = "";
        spanMessage2.innerText = "";
    }
    function remove(clazzId) {
        if (confirm("确定删除序号" + clazzId + "的班级信息?")){
            var formMain = document.getElementById("formMain");
            var hidOperationType = document.getElementById("hidOperationType");
            var hidClazzId = document.getElementById("hidClazzId");
            hidClazzId.value = clazzId;
            hidOperationType.value = "remove"
            formMain.submit();
        }
    }
    function add(){
        if (checkInput()){
            var formMain = document.getElementById("formMain");
            var hidOperationType = document.getElementById("hidOperationType");
            hidOperationType.value = "add";
            formMain.submit();
        }
    }
    function modify(clazzId) {
        var formMain = document.getElementById("formMain");
        var hidOperationType = document.getElementById("hidOperationType");
        var hidClazzId = document.getElementById("hidClazzId");
        hidClazzId.value = clazzId;
        hidOperationType.value = "modify"
        formMain.submit();

    }
    function cancelSave() {
        var formMain = document.getElementById("formMain");
        var hidOperationType = document.getElementById("hidOperationType");
        hidOperationType.value = "cancelSave";
        formMain.submit();
    }
    function save() {
        if (checkInput()){
            var formMain = document.getElementById("formMain");
            var hidOperationType = document.getElementById("hidOperationType");
            hidOperationType.value = "save";
            formMain.submit();
        }
    }
    function search() {
        var formMain = document.getElementById("formMain");
        var hidOperationType = document.getElementById("hidOperationType");
        hidOperationType.value = "search";
        formMain.submit();
    }
    function cancelSearch() {
        var txtSearchNumber = document.getElementById("txtSearchNumber");
        var txtSearchName = document.getElementById("txtSearchName");
        txtSearchNumber.value = "";
        txtSearchName.value = "";
    }
    function paging(currentPageIndex) {
        var formMain = document.getElementById("formMain");
        var hidOperationType = document.getElementById("hidOperationType");
        var hidCurrentPageIndex = document.getElementById("hidCurrentPageIndex");

        hidCurrentPageIndex.value = currentPageIndex;
        hidOperationType.value = "paging";
        formMain.submit();
    }
    function sorting(fieldName) {
        var formMain = document.getElementById("formMain");
        var hidOperationType = document.getElementById("hidOperationType");
        var hidFieldName = document.getElementById("hidFieldName");

        hidFieldName.value = fieldName;
        hidOperationType.value = "sorting";
        formMain.submit();
    }
</script>

<!-- Script for this page -->
<script type="text/javascript">
    $(function () {

        /* Bar Chart starts */

        var d1 = [];
        for (var i = 0; i <= 20; i += 1)
            d1.push([i, parseInt(Math.random() * 30)]);

        var d2 = [];
        for (var i = 0; i <= 20; i += 1)
            d2.push([i, parseInt(Math.random() * 30)]);


        var stack = 0, bars = true, lines = false, steps = false;

        function plotWithOptions() {
            $.plot($("#bar-chart"), [ d1, d2 ], {
                series: {
                    stack: stack,
                    lines: { show: lines, fill: true, steps: steps },
                    bars: { show: bars, barWidth: 0.8 }
                },
                grid: {
                    borderWidth: 0, hoverable: true, color: "#777"
                },
                colors: ["#ff6c24", "#ff2424"],
                bars: {
                    show: true,
                    lineWidth: 0,
                    fill: true,
                    fillColor: { colors: [ { opacity: 0.9 }, { opacity: 0.8 } ] }
                }
            });
        }

        plotWithOptions();

        $(".stackControls input").click(function (e) {
            e.preventDefault();
            stack = $(this).val() == "With stacking" ? true : null;
            plotWithOptions();
        });
        $(".graphControls input").click(function (e) {
            e.preventDefault();
            bars = $(this).val().indexOf("Bars") != -1;
            lines = $(this).val().indexOf("Lines") != -1;
            steps = $(this).val().indexOf("steps") != -1;
            plotWithOptions();
        });

        /* Bar chart ends */

    });


    /* Curve chart starts */

    $(function () {
        var sin = [], cos = [];
        for (var i = 0; i < 14; i += 0.5) {
            sin.push([i, Math.sin(i)]);
            cos.push([i, Math.cos(i)]);
        }

        var plot = $.plot($("#curve-chart"),
            [ { data: sin, label: "sin(x)"}, { data: cos, label: "cos(x)" } ], {
                series: {
                    lines: { show: true, fill: true},
                    points: { show: true }
                },
                grid: { hoverable: true, clickable: true, borderWidth:0 },
                yaxis: { min: -1.2, max: 1.2 },
                colors: ["#1eafed", "#1eafed"]
            });

        function showTooltip(x, y, contents) {
            $('<div id="tooltip">' + contents + '</div>').css( {
                position: 'absolute',
                display: 'none',
                top: y + 5,
                left: x + 5,
                border: '1px solid #000',
                padding: '2px 8px',
                color: '#ccc',
                'background-color': '#000',
                opacity: 0.9
            }).appendTo("body").fadeIn(200);
        }

        var previousPoint = null;
        $("#curve-chart").bind("plothover", function (event, pos, item) {
            $("#x").text(pos.x.toFixed(2));
            $("#y").text(pos.y.toFixed(2));

            if ($("#enableTooltip:checked").length > 0) {
                if (item) {
                    if (previousPoint != item.dataIndex) {
                        previousPoint = item.dataIndex;

                        $("#tooltip").remove();
                        var x = item.datapoint[0].toFixed(2),
                            y = item.datapoint[1].toFixed(2);

                        showTooltip(item.pageX, item.pageY,
                            item.series.label + " of " + x + " = " + y);
                    }
                }
                else {
                    $("#tooltip").remove();
                    previousPoint = null;
                }
            }
        });

        $("#curve-chart").bind("plotclick", function (event, pos, item) {
            if (item) {
                $("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
                plot.highlight(item.series, item.datapoint);
            }
        });

    });

    /* Curve chart ends */
</script>

</body>
</html>