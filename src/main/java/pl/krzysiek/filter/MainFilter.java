package pl.krzysiek.filter;

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

import pl.krzysiek.model.User;

@WebFilter(value="/main/*")
public class MainFilter implements Filter{
	public int a = 0;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		a++;
		
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
//		if(a%2==0 && a%4==0)
//			request.getSession().setAttribute("user", new User("Krzys"));
//		else
//			request.getSession().setAttribute("user", new User("Adam"));
		if(request.getSession().getAttribute("user")==null){
			response.sendRedirect("/auth/login");
			return;
		}else{
			chain.doFilter(req, resp);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
