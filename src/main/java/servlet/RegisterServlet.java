package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "RegisterServlet",urlPatterns = "/registerservlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            String name =names.nextElement();
            out.println(name + ":" + request.getParameter(name) + "<br>");
        }
        String hobbies[] =request.getParameterValues("chkHobby");
        for (String hobby : hobbies){
            out.print(hobby +" ");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("Register.jsp").forward(request,response);
    }
}
