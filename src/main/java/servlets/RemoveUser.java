package servlets;

import java.io.IOException;
import java.net.URLEncoder;

import dao.UtilisateurDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class RemoveUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String message = "Erreur lors de la suppression";
		boolean status = false;
		if (UtilisateurDao.supprimer(Integer.parseInt(id))) {
			message = "Suppression effectuée avec succès";
			status = true;
		}
		String url = String.format("list?message=%s&status=%s", URLEncoder.encode(message, "UTF-8"),
				status ? "success" : "error");
		response.sendRedirect(url);

	}

}
