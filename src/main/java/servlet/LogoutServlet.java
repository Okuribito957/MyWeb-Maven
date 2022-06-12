package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet",urlPatterns = "/logoutservlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("Loginname");
        session.invalidate();

        //清除登录信息
        Cookie cookie = new Cookie("autoLogin","");
        cookie.setMaxAge(1);
        response.addCookie(cookie);

        response.sendRedirect("loginservlet");
    }
}
