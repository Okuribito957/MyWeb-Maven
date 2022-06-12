<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/4/18
  Time: 下午 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Sidebar -->
<div class="sidebar">
    <div class="sidebar-dropdown"><a href="#">导航</a></div>

    <!--- Sidebar navigation -->
    <!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
    <ul id="nav">
        <!-- Main menu with font awesome icon -->
        <li><a href="indexservlet" class="open"><i class="icon-home"></i> 首页</a></li>

        <li class="has_sub"><a href="#"><i class="icon-list-alt"></i> 系统信息管理  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="manageclazzesservlet">班级信息管理</a></li>
                <li><a href="managestudentsservlet">学生信息管理</a></li>
                <li><a href="manageteachersservlet">教师信息管理</a></li>
                <li><a href="managecoursesservlet">课程信息管理</a></li>
                <li><a href="managestudycoursesservlet">选课管理</a></li>
                <li><a href="manageteachcoursesservlet">授课管理</a></li>
            </ul>
        </li>
        <li class="has_sub"><a href="#"><i class="icon-file-alt"></i> 日志管理 <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
            <ul>
                <li><a href="ModifyScoreLogsServlet">成绩修改日志管理</a></li>
                <li><a href="ModifyTeachCourseLogsServlet">开课修改日志管理</a></li>
            </ul>
        </li>
    </ul>
</div>
<!-- Sidebar ends -->

