package br.com.mystudies.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter( urlPatterns={"/backlog", "/kanban", "/sprints", "/statistics", "/theme"})
public class SecurityFilter implements Filter {


    public SecurityFilter() {
    }




	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		if(userLoged(request)){
			chain.doFilter(request, response);
		}else{
			redirectToLogin(response);
		}
	}





















	private boolean userLoged(ServletRequest request) {
		return ((HttpServletRequest) request).getSession().getAttribute("user") != null;
	}



	private void redirectToLogin(ServletResponse response) throws IOException {
		((HttpServletResponse) response).sendRedirect("index.html");
	}

	public void destroy() {}

	public void init(FilterConfig fConfig) throws ServletException {}

}
