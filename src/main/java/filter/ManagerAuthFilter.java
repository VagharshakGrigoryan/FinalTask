package filter;

import model.User;
import model.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/managerHome", "/adTask", "/userRegister"})
public class ManagerAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUserType() != UserType.MANAGER) {
            HttpServletResponse responses = (HttpServletResponse) response;
            responses.sendRedirect("/index.jsp");
        } else {
                chain.doFilter(request, response);
            }
        }


    @Override
    public void destroy() {

    }
}
