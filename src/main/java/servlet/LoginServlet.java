package servlet;

import dao.DaoFactory;
import dao.LoginDao;
import entity.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name="LoginServlet",urlPatterns="/loginservlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String Loginname = request.getParameter("u");
        String Password = request.getParameter("p");
        LoginBean login = this.isLogin(Loginname,Password);
        if (login != null){
            HttpSession session = request.getSession();//通过请求对象获取会话
            session.setAttribute("Loginname",Loginname);
            System.out.println(session.getMaxInactiveInterval());//查看失效时长
            System.out.println(new  java.sql.Timestamp(session.getCreationTime()).toLocaleString());//查看会话创建时间
            System.out.println( session.getId());//查看会话id

            session.setMaxInactiveInterval(-1);//设置失效时间
            //session.invalidate();//使会话对象立即失效

            if (request.getParameter("chkRememberLoginName") != null){
                Cookie cookie = new Cookie("Loginname",Loginname);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(cookie);//使用响应对象将cookie信息带给浏览器
            }
            else {
                //没有移除cookie的方法，创建同名cookie赋空值
                Cookie cookie = new Cookie("Loginnname","");
                cookie.setMaxAge(1);
                response.addCookie(cookie);
            }
            //自动登录
            if (request.getParameter("chkAutoLogin") != null){
                Cookie cookie = new Cookie("autoLogin",Loginname);
                cookie.setMaxAge(7*24*60*60);
                response.addCookie(cookie);
            }
            //登录类型判断
            if (login.getLoginType().compareTo("admin") == 0){
                response.sendRedirect("adminindexservlet");
            }else if (login.getLoginType().compareTo("teacher") == 0){
                response.sendRedirect("teacherindexservlet");
            }else if (login.getLoginType().compareTo("student") == 0){
                response.sendRedirect("studentindexservlet");
            }
            //response.sendRedirect("indexservlet");//响应重定向
        }
        else {
            request.setAttribute("ErrorMessage","用户名或密码错误");
            request.getRequestDispatcher("Login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().compareTo("Loginname") == 0){
                    request.setAttribute("Loginname",cookie.getValue());
                }
                if (cookie.getName().compareTo("autoLogin") == 0){
                    //自动登录
                    HttpSession session = request.getSession();
                    session.setAttribute("Loginname",cookie.getValue());
                    response.sendRedirect("indexservlet");
                    return;
                }
            }
        }
        request.getRequestDispatcher("Login.jsp").forward(request,response);
    }
    //业务逻辑不能在数据访问层实现
    private LoginBean isLogin(String loginName, String password){
        LoginDao dao = DaoFactory.getLoginDao();
        LoginBean login = dao.select(loginName);

        if (login != null && login.getPassword().compareTo(password) == 0){
            //login != null && login.getPassword().compareTo(password) == 0
            return login;
        }
        return null;
    }
}