package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "TestServlet",urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("客户端IP:" + request.getRemoteAddr() + "<br>");
        out.println("服务器IP：" + request.getLocalAddr() + "<br>");
        out.println("服务器名：" + request.getServerName() + "<br>");
        out.println("客户端端口号：" + request.getRemotePort() + "<br>");
        out.println("服务器端口号：" + request.getLocalPort() + "<br>");
        out.println("协议名：" + request.getScheme() + "<br>");
        out.println("协议版本：" + request.getProtocol() + "<br>");
        out.println("请求方法：" + request.getMethod() + "<br>");
        out.println("请求URL：" + request.getRequestURL() + "<br>");
        out.println("项目路径：" + request.getContextPath() + "<br>");
        out.println("Servlet路径" + request.getServletPath() + "<br>");
        out.println("----------------------------------------------------------------------");
        //http头信息
        Enumeration<String> headeNames =request.getHeaderNames();
        while (headeNames.hasMoreElements()){
            String headerName = headeNames.nextElement();
            out.print(headerName +":"+request.getHeader(headerName) + "<br>");
        }

    }
}
