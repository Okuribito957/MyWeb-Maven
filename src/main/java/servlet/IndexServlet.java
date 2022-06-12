package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "IndexServlet",urlPatterns = "/indexservlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        //初始化页面信息
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("Loginname") == null || session.getAttribute("Loginname").toString().length() == 0){
            response.sendRedirect("loginservlet");
        }
        else{
            request.getRequestDispatcher("AdminIndex.jsp").forward(request,response);
        }
    }
}
