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

@WebServlet("/update")
public class UpdateUser extends HttpServlet {

	private static final String FORM_UPDATE_USER_VIEW = "/WEB-INF/modifierUtilisateur.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Utilisateur utilisateur = UtilisateurDao.get(Integer.parseInt(id));
		if (utilisateur == null) {
			response.sendRedirect("list?error=l'utilisateur avec l'id " + id + " n'existe pas");
		} else {
			request.setAttribute("utilisateur", utilisateur);
			getServletContext().getRequestDispatcher(FORM_UPDATE_USER_VIEW).forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		if (role == null || role.isBlank()) role = "USER";
		Utilisateur u = new Utilisateur(Integer.parseInt(id), nom, prenom, login, password, role);
		String message = "";
		boolean status = false;
		if (UtilisateurDao.modifier(u)) {
			message = "Mise à jour effectuée avec succès";
			status = true;
		} else {
			message = "Erreur lors de la mise à jour";
		}
		String url = String.format("list?message=%s&status=%s", URLEncoder.encode(message, "UTF-8"),
				status ? "success" : "error");
		response.sendRedirect(url);

	}

}
