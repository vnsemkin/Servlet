package controller.filter;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", value = "/*")
public class AuthorizationFilter implements Filter {

    public void init(FilterConfig config)  {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String defaultHttp = "/index";
        String url = httpRequest.getRequestURI();
        String userName = (String) httpRequest.getSession().getAttribute("name");
        if (userName != null || url.equals("/login") || url.equals("/signup") || url.equals("/index")) {
            httpRequest.getServletContext().getRequestDispatcher(url).forward(request, response);
        } else {
            httpResponse.sendRedirect(defaultHttp);
        }
    }
}
