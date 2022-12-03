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
import telran.java45.forum.ecouting.model.UserAccount;
import telran.java45.forum.ecouting.repository.UserAccountRepository;

@Component
@RequiredArgsConstructor
@Order(20)
public class AdminFilter implements Filter {
	

	final UserAccountRepository userAccountRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (checkEndPoint(request.getMethod(), request.getServletPath())) {
			UserAccount userAccount = userAccountRepository.findById(request.getUserPrincipal().getName()).get();
			if(!userAccount.getRoles().contains("ADMINISTRATOR")) {
				response.sendError(403);
				return;
			}
		}
		
		if (checkEndPointPut(request.getMethod(), request.getServletPath())) {
			UserAccount userAccount = userAccountRepository.findById(request.getUserPrincipal().getName()).get();
			if(userAccount.getRoles().contains("ADMINISTRATOR")) {
				response.sendError(403);
				return;
			}
		}
		
		
		chain.doFilter(request, response);
	}
	private boolean checkEndPoint(String method, String path) {
		return path.matches("/account/user/\\w+/role/\\w+/?");
	}
	
	private boolean checkEndPointPut(String method, String path) {
		return path.matches("/user/\\w+/?") && method.equals("PUT");
	}
	

}
