package telran.java45.forum.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java45.forum.ecouting.repository.UserAccountRepository;
@Component
@RequiredArgsConstructor
@Order(11)
public class AutorFilter implements Filter {
	
	final UserAccountRepository userAccountRepository;


	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String userName = request.getUserPrincipal().getName();
		
		if (checkEndPointAutor(request.getMethod(), request.getServletPath())) {
			if (!userName.equals(getAutor(request))) {
				response.sendError(403);
				return;
			}
		}
		
		
		

		chain.doFilter(request, response);
	}
	
	private boolean checkEndPointAutor(String method, String servletPath) {	
		return ("POST".equalsIgnoreCase(method) && servletPath.matches("/post/\\w+/?"))
				|| "PUT".equalsIgnoreCase(method) && servletPath.matches("/post/\\w+/commenr/\\w+/?");
	}
	
	private String getAutor(HttpServletRequest request) {
		String pathString = request.getServletPath();
		String[] path = pathString.split("/");
		String subject;
		if (pathString.matches("/user/\\w+/")) {
			subject = path[path.length - 2];
		} else {
			subject = path[path.length - 1];
		}
		return subject;
	}

}
