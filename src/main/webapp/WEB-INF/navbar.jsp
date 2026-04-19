<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.Utilisateur" %>
<%
  Utilisateur navUser = (Utilisateur) session.getAttribute("utilisateur");
  String ctx = request.getContextPath();
%>
<nav class="navbar">
  <div class="navbar-brand">gesusers</div>
  <ul class="navbar-menu">
    <li><a href="<%= ctx %>/home">Accueil</a></li>
    <% if (navUser != null && navUser.isAdmin()) { %>
      <li><a href="<%= ctx %>/list">Utilisateurs</a></li>
      <li><a href="<%= ctx %>/add">Ajouter</a></li>
    <% } %>
  </ul>
  <div class="navbar-user">
    <% if (navUser != null) { %>
      <span><%= navUser.getPrenom() %> <%= navUser.getNom() %>
        <small>(<%= navUser.getRole() %>)</small></span>
      <a class="btn btn-logout" href="<%= ctx %>/logout">D&eacute;connexion</a>
    <% } %>
  </div>
</nav>
