<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.Utilisateur" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<jsp:include page="navbar.jsp" />

<main class="container">
  <% Utilisateur u = (Utilisateur) session.getAttribute("utilisateur"); %>

  <h1>Bienvenue, <%= u.getPrenom() %> !</h1>

  <div class="card">
    <h2>Mes informations</h2>
    <dl class="info-list">
      <dt>Identifiant</dt><dd><%= u.getId() %></dd>
      <dt>Nom</dt><dd><%= u.getNom() %></dd>
      <dt>Pr&eacute;nom</dt><dd><%= u.getPrenom() %></dd>
      <dt>Login</dt><dd><%= u.getLogin() %></dd>
      <dt>R&ocirc;le</dt><dd><%= u.getRole() %></dd>
    </dl>
  </div>

  <% if (u.isAdmin()) { %>
    <div class="card">
      <h2>Administration</h2>
      <p>Vous avez acc&egrave;s aux fonctions d'administration :</p>
      <p>
        <a class="btn" href="<%= request.getContextPath() %>/list">G&eacute;rer les utilisateurs</a>
        <a class="btn" href="<%= request.getContextPath() %>/add">Ajouter un utilisateur</a>
      </p>
    </div>
  <% } %>
</main>

</body>
</html>
