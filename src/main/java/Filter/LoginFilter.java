package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns = {"/indexservlet"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // chain.doFilter(req, resp);

        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse){
            HttpServletRequest request = (HttpServletRequest)req;
            HttpServletResponse response = (HttpServletResponse)resp;

            HttpSession session = request.getSession();
            if (session == null || session.getAttribute("Loginname") == null || session.getAttribute("Loginname").toString().length() == 0){
                response.sendRedirect("loginservlet");
            }
            else {
                chain.doFilter(request,response);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
