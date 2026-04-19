package filters;

import java.io.IOException;
import java.util.Set;

import beans.Utilisateur;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

	private static final Set<String> PUBLIC_PATHS = Set.of("/login", "/logout");
	private static final Set<String> ADMIN_PATHS = Set.of("/list", "/add", "/update", "/delete");

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String ctx = request.getContextPath();
		String uri = request.getRequestURI();
		String path = uri.substring(ctx.length());

		// Laisser passer les ressources statiques (CSS, images, etc.)
		if (path.startsWith("/css/") || path.startsWith("/img/") || path.startsWith("/js/")) {
			chain.doFilter(req, res);
			return;
		}

		// Pages publiques
		if (PUBLIC_PATHS.contains(path)) {
			chain.doFilter(req, res);
			return;
		}

		HttpSession session = request.getSession(false);
		Utilisateur u = (session == null) ? null : (Utilisateur) session.getAttribute("utilisateur");

		if (u == null) {
			response.sendRedirect(ctx + "/login");
			return;
		}

		// Pages réservées aux admins
		if (ADMIN_PATHS.contains(path) && !u.isAdmin()) {
			response.sendRedirect(ctx + "/home");
			return;
		}

		chain.doFilter(req, res);
	}
}
