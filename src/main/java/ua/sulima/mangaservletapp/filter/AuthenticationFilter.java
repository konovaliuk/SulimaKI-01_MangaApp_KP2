package ua.sulima.mangaservletapp.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.sulima.mangaservletapp.controller.GetEndpoint;
import ua.sulima.mangaservletapp.entity.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AuthenticationFilter implements Filter {
    private Set<String> notAuthenticatedOnlyEndpoints = new HashSet<>();

    private Set<String> authenticatedOnlyEndpoints = new HashSet<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initNotAuthenticatedOnlyEndpoints();
        initAuthenticatedOnlyEndpoints();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String path = httpServletRequest.getRequestURI();
        Optional<User> maybeLoginedUser =
                Optional.ofNullable((User)httpServletRequest.getSession().getAttribute("user"));
        if(maybeLoginedUser.isEmpty()){
            httpServletRequest.setAttribute("login_status", Boolean.FALSE);
            if(!authenticatedOnlyEndpoints.contains(path)){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }else{
                httpServletResponse.sendRedirect(GetEndpoint.INDEX_PAGE);
            }
        }else{
            httpServletRequest.setAttribute("login_status", Boolean.TRUE);
            if(!notAuthenticatedOnlyEndpoints.contains(path)){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }else{
                httpServletResponse.sendRedirect(GetEndpoint.INDEX_PAGE);
            }
        }

    }
    private void initNotAuthenticatedOnlyEndpoints(){
        notAuthenticatedOnlyEndpoints.add(GetEndpoint.LOGIN_PAGE);
        notAuthenticatedOnlyEndpoints.add(GetEndpoint.REGISTER_PAGE);
    }
    private void initAuthenticatedOnlyEndpoints(){
        authenticatedOnlyEndpoints.add(GetEndpoint.LOGOUT);
        authenticatedOnlyEndpoints.add(GetEndpoint.MANGA_CATALOG_PAGE);
    }
}
