package servlets;

import java.io.IOException;

import beans.Utilisateur;
import dao.UtilisateurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final String LOGIN_VIEW = "/WEB-INF/login.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		Utilisateur u = UtilisateurDao.authentifier(login, password);
		if (u == null) {
			request.setAttribute("error", "Login ou mot de passe incorrect.");
			getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
			return;
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("utilisateur", u);
		response.sendRedirect(request.getContextPath() + "/home");
	}
}
