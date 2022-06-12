package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="TestServlet2",urlPatterns="/error")
public class TestServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//
//        PrintWriter out = response.getWriter();
//
//        out.println("登录名：" + request.getParameter("u") + "<br>");
//        out.println("密码：" + request.getParameter("p"));
//
//        String Loginname = request.getParameter("u");
//        String Password = request.getParameter("p");
//        if (Loginname.compareTo("admin") == 0 && Password.compareTo("123456") == 0){
//            response.sendRedirect("indexservlet");//响应重定向
//        }
//        else {
//            request.setAttribute("ErrorMessage","用户名或密码错误");
//            //response.sendRedirect("Login.jsp");
//            //请求转发，原请求和响应对象依然有效
//            request.getRequestDispatcher("indexservlet").forward(request,response);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//
//        request.getRequestDispatcher("Login.jsp").forward(request,response);
    }
}
