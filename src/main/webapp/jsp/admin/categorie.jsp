<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="ma.fstt.entities.Categorie"%>
<%@page import="java.util.List"%>

<%
List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
%>

<%
Categorie categorie = (Categorie) request.getAttribute("categorie");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Categories</title>
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
			<li><a href="/jpaJsf/BackServlet?action=categorie" class="bg-secondary">Categorie</a></li>
			<li><a href="/jpaJsf/BackServlet?action=user">User</a></li>
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
				<div class="col-lg-2 mt-4"></div>
				<c:if test="${empty categorie}">
					<div class="col-lg-4 mt-4">
						<form method="post"
							action="/jpaJsf/BackServlet?action=AjouterCategorie" class="card"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="nom">Nom Categorie</label> <input type="text"
									name="nomCat" class="form-control" id="nom"
									placeholder="Entrer le nom du categorie">
							</div>
												

							<input type="submit" class="btn btn-primary mb-2 mt-3"
								value="Ajouter">
						</form>
					</div>
				</c:if>
				<c:if test="${not empty categorie}">
					<div class="col-lg-4 mt-4">
						<form method="post"
							action="/jpaJsf/BackServlet?action=EditCategorie&codeCat=${categorie.getCodeCat()}" class="card"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="nom">Nom Categorie</label> <input type="text"
									name="nomCat" class="form-control" id="nom"
									placeholder="Entrer le nom du categorie"
									value="${categorie.getNomCat()}">
							</div>
							  
						 <input type="submit"
								class="btn btn-warning mb-2 mt-3" value="Update">
						</form>
					</div>
				</c:if>

				<div class="col-lg-6 mt-4">
					<table class="table" class="card">
						<thead class="card-header">
							<tr>
								<th scope="col">NOM CATEGORIE</th>
								<th scope="col">action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${categories}" var="categorie">
								<tr>
									<td>${categorie.getNomCat()}</td>
									<td><a
										href="/jpaJsf/BackServlet?action=DeleteCategorie&codeCat=${categorie.getCodeCat()}"
										class="btn btn-danger">Delete</a><a
										href="/jpaJsf/BackServlet?action=UpdateCategorie&codeCat=${categorie.getCodeCat()}"
										class="btn btn-warning">update</a></td>
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