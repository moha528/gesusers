<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.Utilisateur" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Liste des utilisateurs</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<jsp:include page="navbar.jsp" />

<main class="container">
  <h1>Liste des utilisateurs</h1>

  <%
    String message = request.getParameter("message");
    String status  = request.getParameter("status");
    if (message != null && status != null) {
  %>
    <div class="message <%= status %>"><%= message %></div>
  <% } %>

  <p><a class="btn" href="<%= request.getContextPath() %>/add">Ajouter un utilisateur</a></p>

  <%
    List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("utilisateurs");
    if (utilisateurs == null || utilisateurs.isEmpty()) {
  %>
    <p>Aucun utilisateur enregistr&eacute;.</p>
  <%
    } else {
  %>
    <table>
      <tr>
        <th>Id</th>
        <th>Nom</th>
        <th>Pr&eacute;nom</th>
        <th>Login</th>
        <th>Password</th>
        <th>R&ocirc;le</th>
        <th>Actions</th>
      </tr>
      <% for (Utilisateur u : utilisateurs) { %>
        <tr>
          <td><%= u.getId() %></td>
          <td><%= u.getNom() %></td>
          <td><%= u.getPrenom() %></td>
          <td><%= u.getLogin() %></td>
          <td><%= u.getPassword() %></td>
          <td><span class="badge badge-<%= u.getRole().toLowerCase() %>"><%= u.getRole() %></span></td>
          <td class="actions">
            <a href="<%= request.getContextPath() %>/update?id=<%= u.getId() %>">Modifier</a>
            <a class="delete" href="<%= request.getContextPath() %>/delete?id=<%= u.getId() %>"
               onclick="return confirm('Supprimer cet utilisateur ?');">Supprimer</a>
          </td>
        </tr>
      <% } %>
    </table>
  <% } %>
</main>

</body>
</html>
