<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Ajouter un utilisateur</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<jsp:include page="_navbar.jsp" />

<main class="container">
  <h1>Ajouter un utilisateur</h1>

  <form method="post">
    <label for="nom">Nom</label>
    <input type="text" name="nom" id="nom" required>

    <label for="prenom">Pr&eacute;nom</label>
    <input type="text" name="prenom" id="prenom" required>

    <label for="login">Login</label>
    <input type="text" name="login" id="login" required>

    <label for="password">Password</label>
    <input type="password" name="password" id="password" required>

    <label for="role">R&ocirc;le</label>
    <select name="role" id="role">
      <option value="USER">USER</option>
      <option value="ADMIN">ADMIN</option>
    </select>

    <button type="submit">Ajouter</button>
    <a class="btn btn-cancel" href="<%= request.getContextPath() %>/list">Annuler</a>
  </form>
</main>

</body>
</html>
