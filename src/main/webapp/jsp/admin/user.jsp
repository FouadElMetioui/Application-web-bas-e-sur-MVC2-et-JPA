<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="ma.fstt.entities.User"%>
<%@page import="java.util.List"%>

<%
List<User> users = (List<User>) request.getAttribute("users");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/astyle.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>

<body>

	<!-- Sidebar -->
	<div id="sidebar-wrapper">
		<ul class="sidebar-nav">
			<li class="sidebar-brand"><a
				href="/jpaJsf/BackServlet?action=article"> Dashboard </a></li>
			<li><a href="/jpaJsf/BackServlet?action=article">Article</a></li>
			<li><a href="/jpaJsf/BackServlet?action=categorie">Categorie</a></li>
			<li><a href="/jpaJsf/BackServlet?action=user"
				class="bg-secondary">User</a></li>
			<li><a href="/jpaJsf/ControllerServlet?action=index">Preview</a></li>

		</ul>
	</div>
	<!-- /#sidebar-wrapper -->

	<!-- Page Content -->
	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="#"> Dashboard </a>
	</nav>
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-4 mt-4"></div>
				<div class="col-lg-6 mt-4">
					<table class="table" class="card">
						<thead class="card-header">
							<tr>
								<th scope="col">NOM USER</th>
								<th scope="col">ADRESS USER</th>
								<th scope="col">TEL USER</th>
								<th scope="col">VILLE USER</th>
								<th scope="col">EMAIL USER</th>
								<th scope="col">PROFILE USER</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<td>${user.getNom()}</td>
									<td>${user.getAddr()}</td>
									<td>${user.getTel()}</td>
									<td>${user.getVille()}</td>
									<td>${user.getEmail()}</td>
									<c:if test="${user.getRole() == 0}">
										<td><span class="badge bg-primary">Internaute</span></td>
									</c:if>
									<c:if test="${user.getRole() == 1}">
										<td><span class="badge bg-primary">Admin</span></td>
									</c:if>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>


		</div>
	</div>
	<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->


</body>
</html>