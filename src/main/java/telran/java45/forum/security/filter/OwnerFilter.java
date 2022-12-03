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
@Order(30)
public class OwnerFilter implements Filter {

	final UserAccountRepository userAccountRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		UserAccount userAccount = userAccountRepository.findById(request.getUserPrincipal().getName()).get();
		String userName = request.getUserPrincipal().getName();

		if (checkEndPoint(request.getMethod(), request.getServletPath())) {
			if (!userName.matches(getSubject(request))) {
				response.sendError(403);
				return;
			}
		}

		if (checkEndPointDelete(request.getMethod(), request.getServletPath())) {
			if (!(userAccount.getRoles().equals("MODERATOR") || userName.matches(getSubject(request)))) {
				response.sendError(403);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private String getSubject(HttpServletRequest request) {
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

	private boolean checkEndPoint(String method, String path) {
		return (path.matches("/user/\\w+/?") && method.equals("PUT"))
				|| (path.matches("/post/\\w+/?") && (method.equals("DEL") || method.equals("PUT")));
	}

	private boolean checkEndPointDelete(String method, String path) {
		return (path.matches("/user/\\w+/?") && method.equals("DEL"));
	}
}
