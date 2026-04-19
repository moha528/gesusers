<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.Utilisateur" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Modifier un utilisateur</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<jsp:include page="_navbar.jsp" />

<main class="container">
  <% Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur"); %>

  <h1>Modifier l'utilisateur</h1>

  <form action="update" method="post">
    <input type="hidden" name="id" value="<%= utilisateur.getId() %>">

    <label for="nom">Nom</label>
    <input type="text" name="nom" id="nom" required
           value="<%= utilisateur.getNom() == null ? "" : utilisateur.getNom().trim() %>">

    <label for="prenom">Pr&eacute;nom</label>
    <input type="text" name="prenom" id="prenom" required
           value="<%= utilisateur.getPrenom() == null ? "" : utilisateur.getPrenom().trim() %>">

    <label for="login">Login</label>
    <input type="text" name="login" id="login" required
           value="<%= utilisateur.getLogin() == null ? "" : utilisateur.getLogin().trim() %>">

    <label for="password">Password</label>
    <input type="password" name="password" id="password" required
           value="<%= utilisateur.getPassword() %>">

    <label for="role">R&ocirc;le</label>
    <select name="role" id="role">
      <option value="USER"  <%= "USER".equals(utilisateur.getRole())  ? "selected" : "" %>>USER</option>
      <option value="ADMIN" <%= "ADMIN".equals(utilisateur.getRole()) ? "selected" : "" %>>ADMIN</option>
    </select>

    <button type="submit">Enregistrer</button>
    <a class="btn btn-cancel" href="<%= request.getContextPath() %>/list">Annuler</a>
  </form>
</main>

</body>
</html>
