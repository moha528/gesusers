package servlets;

import java.io.IOException;
import java.net.URLEncoder;

import beans.Utilisateur;
import dao.UtilisateurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddUser extends HttpServlet {

	private static final String FORM_ADD_USER_VIEW = "/WEB-INF/ajouterUtilisateur.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher(FORM_ADD_USER_VIEW).forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		if (role == null || role.isBlank()) role = "USER";
		Utilisateur u = new Utilisateur(nom, prenom, login, password, role);
		String message = "Erreur lors de l'ajout";
		boolean status = false;
		if (UtilisateurDao.ajouter(u)) {
			message = "Ajout effectué avec succès";
			status = true;
		}
		String url = String.format("list?message=%s&status=%s", URLEncoder.encode(message, "UTF-8"),
				status ? "success" : "error");
		response.sendRedirect(url);
	}

}
