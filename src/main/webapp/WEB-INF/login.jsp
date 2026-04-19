<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body class="login-body">

<div class="login-card">
  <h1>Connexion</h1>

  <% String error = (String) request.getAttribute("error");
     if (error != null) { %>
    <div class="message error"><%= error %></div>
  <% } %>

  <form method="post">
    <label for="login">Login</label>
    <input type="text" name="login" id="login" required autofocus>

    <label for="password">Mot de passe</label>
    <input type="password" name="password" id="password" required>

    <button type="submit">Se connecter</button>
  </form>
</div>

</body>
</html>
