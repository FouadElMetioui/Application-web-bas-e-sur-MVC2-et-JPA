<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="ma.fstt.entities.Article"%>
<%@page import="java.util.List"%>

<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
%>

<%
Article article = (Article) request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Articles</title>
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
			<li><a href="/jpaJsf/BackServlet?action=article" class="bg-secondary">Article</a></li>
			<li><a href="/jpaJsf/BackServlet?action=categorie">Categorie</a></li>
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
				<c:if test="${empty article}">
					<div class="col-lg-4 mt-4">
						<form method="post"
							action="/jpaJsf/BackServlet?action=AjouterArticle" class="card"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="nom">Nom article</label> <input type="text"
									name="nomArt" class="form-control" id="nom"
									placeholder="Entrer le nom du article">
							</div>
							<div class="form-group">
								<label for="prenom">Prix Unitaire</label> <input type="text"
									name="pu" class="form-control" id="prenom"
									placeholder="Entrer le prix unitaire">
							</div>
							<div class="form-group">
								<label for="adress">Quantite en Stock</label> <input
									type="number" name="qteStock" class="form-control" id="adress"
									placeholder="Entrer la quantite en stock">
							</div>

							<div class="form-group">
								<label for="prenom">Choisir la categorie</label> <select
									class="form-select" aria-label="Default select example"
									name="Categorielist">
									<c:forEach items="${categories}" var="categorie">
										<option value="${categorie.getCodeCat()}">${categorie.getNomCat()}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label for="adress">telecharger photo article</label> <input
									type="file" name="photo" class="form-control" id="adress"
									placeholder="Entrer la photo d'article">
							</div>

							<input type="submit" class="btn btn-primary mb-2 mt-3"
								value="Ajouter">
						</form>
					</div>
				</c:if>
				<c:if test="${not empty article}">
					<div class="col-lg-4 mt-4">
						<form method="post"
							action="/jpaJsf/BackServlet?action=EditArticle" class="card"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="nom">Nom article</label> <input type="text"
									name="nomArt" class="form-control" id="nom"
									placeholder="Entrer le nom du client"
									value="${article.getNomArt()}">
							</div>
							<div class="form-group">
								<label for="prenom">Prix Unitaire</label> <input type="text"
									name="pu" class="form-control" id="prenom"
									placeholder="Entrer le prenom du client"
									value="${article.getPu()}">
							</div>
							<div class="form-group">
								<label for="adress">Quantite en Stock</label> <input type="text"
									name="qteStock" class="form-control" id="adress"
									placeholder="Entrer l'adress du client"
									value="${article.getQteStock()}">
							</div>


							<div class="form-group">
								<label for="prenom">Choisir la categorie</label> <select
									class="form-select" aria-label="Default select example"
									name="Categorielist">
									<c:forEach items="${categories}" var="categorie">

										<c:if test="${categorie.getCodeCat() == article.getCodeCat()}">
											<option value="${categorie.getCodeCat()}" selected>${categorie.getNomCat()}</option>
										</c:if>
										<c:if test="${categorie.getCodeCat() != article.getCodeCat()}">
											<option value="${categorie.getCodeCat()}">${categorie.getNomCat()}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
   
							<div class="form-group">
								<label for="adress">telecharger photo article</label> <input
									type="file" name="photo" class="form-control" id="adress"
									value="images/${article.getPath()}"
									placeholder="Entrer la photo d'article">
							</div>

							<input type="hidden" id="id" name="codeArt"
								value="${ article.getCodeArt()}"> <input type="submit"
								class="btn btn-warning mb-2 mt-3" value="Update">
						</form>
					</div>
				</c:if>

				<div class="col-lg-6 mt-4">
					<table class="table" class="card">
						<thead class="card-header">
							<tr>
								<th scope="col">NOM ARTICLE</th>
								<th scope="col">PRIX UNITAIRE</th>
								<th scope="col">QUANTITE EN STOCK</th>
								<th scope="col">CATEGORIE</th>
								<th scope="col">image</th>
								<th scope="col">action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${articles}" var="article">
								<tr>
									<td>${article.getNomArt()}</td>
									<td>${article.getPu()}</td>
									<td>${article.getQteStock()}</td>
									<td>${article.getCategorie().nomCat}</td>

									<td><a href="#"
										class="cat-img position-relative overflow-hidden mb-3"> <img
											class="img-fluid" src="images/${article.getPath()}"
											alt="faild" />
									</a></td>
									<td><a
										href="/jpaJsf/BackServlet?action=DeleteArticle&codeArt=${article.getCodeArt()}"
										class="btn btn-danger">Delete</a><a
										href="/jpaJsf/BackServlet?action=UpdateArticle&codeArt=${article.getCodeArt()}"
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