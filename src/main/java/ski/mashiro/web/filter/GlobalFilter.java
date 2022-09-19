package ski.mashiro.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

/**
 * @author FeczIne
 */
@WebFilter(filterName = "SameSiteFilter", value = "/*")
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String[] urls = {"/login.html", "/register.html", "/user/getUser", "/user/saveUser"};
        for (String u : urls) {
            if (req.getRequestURI().contains(u)) {
                chain.doFilter(request, response);
                return;
            }
        }

        if (req.getSession().getAttribute("username") != null) {
            chain.doFilter(request, response);
            addSameSiteAttribute((HttpServletResponse) response);
        } else {
            req.getRequestDispatcher("/login.html").forward(request, response);
            addSameSiteAttribute((HttpServletResponse) response);
        }
    }
    private void addSameSiteAttribute(HttpServletResponse response) {
        Collection<String> headers = response.getHeaders("Set-Cookie");
        boolean firstHeader = true;
        for (String header : headers) {
            if (firstHeader) {
                response.setHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Strict"));
                firstHeader = false;
                continue;
            }
            response.addHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Strict"));
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void destroy() {
    }
}
