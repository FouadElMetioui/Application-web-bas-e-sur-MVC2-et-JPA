<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="ma.fstt.entities.Article"%>
<%@page import="ma.fstt.entities.User"%>
<%@page import="ma.fstt.entities.Categorie"%>
<%@page import="java.util.List"%>
<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<%
User user = (User)request.getAttribute("user");
%>

<%
List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evitrine Onligne</title>


<!-- Favicon -->
<!-- <link href="img/favicon.ico" rel="icon"> -->

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link
	href="<%=request.getContextPath()%>/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet">


</head>
<body>

	<!-- Topbar Start -->

	<div class="row align-items-center py-3 px-xl-5">
		<div class="col-lg-3 d-none d-lg-block">
			<a href="/jpaJsf/ControllerServlet?action=index"
				class="text-decoration-none">
				<h1 class="m-0 display-5 font-weight-semi-bold">Evitrine</h1>
			</a>
		</div>
		<div class="col-lg-6 col-6 text-left">
			<form action="">
				<div class="input-group">
					<input type="text" class="form-control"
						placeholder="Search for products">
					<div class="input-group-append">
						<span class="input-group-text bg-transparent text-primary">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
			</form>
		</div>
		<div class="col-lg-3 col-6 text-right">
			<a href="/jpaJsf/ControllerServlet?action=panier" class="btn border">
				<i class="fas fa-shopping-cart text-primary"></i> <span
				class="badge"></span>
			</a>
		</div>
	</div>
	</div>
	<!-- Topbar End -->


	<!-- Navbar Start -->
	<div class="container-fluid mb-5">
		<div class="row border-top px-xl-5">
			<div class="col-lg-3 d-none d-lg-block">
				<a
					class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100"
					data-toggle="collapse" href="#navbar-vertical"
					style="height: 65px; margin-top: -1px; padding: 0 30px;">
					<h6 class="m-0">Categories</h6> <i
					class="fa fa-angle-down text-dark"></i>
				</a>
				<nav
					class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0"
					id="navbar-vertical">
					<div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
						<c:forEach items="${categories}" var="categorie">
							<a href="" class="nav-item nav-link">${categorie.getNomCat()}</a>
						</c:forEach>
					</div>
				</nav>
			</div>
			<div class="col-lg-9">
				<nav
					class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
					<a href="" class="text-decoration-none d-block d-lg-none">
						<h1 class="m-0 display-5 font-weight-semi-bold">
							<span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper
						</h1>
					</a>
					<button type="button" class="navbar-toggler" data-toggle="collapse"
						data-target="#navbarCollapse">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-between"
						id="navbarCollapse">
						<div class="navbar-nav mr-auto py-0">
							<a href="/jpaJsf/ControllerServlet?action=index"
								class="nav-item nav-link active">Home</a>
						</div>
						<div class="navbar-nav ml-auto py-0">
							
							<c:if test="${not empty user}">
								<a href="#"
								class="nav-item nav-link">${user.getNom()}</a>
								<a href="/jpaJsf/ControllerServlet?action=signout"
								class="nav-item nav-link">signout</a>
							</c:if>
							<c:if test="${empty user}">
								<a href="/jpaJsf/ControllerServlet?action=loginform"
								class="nav-item nav-link">Login</a> <a
								href="/jpaJsf/ControllerServlet?action=logupform"
								class="nav-item nav-link">Register</a>
							</c:if>
						</div>

					</div>
				</nav>
				<div id="header-carousel" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">

						<div class="carousel-item active" style="height: 410px;">

							<div
								class="carousel-caption d-flex flex-column align-items-center justify-content-center">
								<div class="p-3" style="max-width: 700px;">
									<h4 class="text-light text-uppercase font-weight-medium mb-3">Don't
										miss the opportunity</h4>
									<h3 class="display-4 text-white font-weight-semi-bold mb-4">Reasonable
										Price</h3>
									<a href="" class="btn btn-light py-2 px-3">Shop Now</a>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- Navbar End -->



	<!-- Categories Start -->
	<div class="container-fluid pt-5 d-flex">
		<div class="row">
			<c:forEach items="${articles}" var="article">

				<div class="col-lg-4 col-md-6 pb-1">
					<div class="cat-item d-flex flex-column border mb-4"
						style="padding: 30px;">
						<p class="text-right">last ${article.getQteStock()} Products</p>
						<a
							href="/jpaJsf/ControllerServlet?action=onearticle&codeArt=${article.getCodeArt()}"
							class="cat-img position-relative overflow-hidden mb-3"> <img
							class="img-fluid" src="images/${article.getPath()}" alt="faild">
						</a>
						<div class="d-flex justify-content-between">
							<h5 class="font-weight-semi-bold m-0">${article.getNomArt()}</h5>
							<a
								href="/jpaJsf/ControllerServlet?action=ajouterPanier&codeArt=${article.getCodeArt()}"
								class="btn"> <i class="fas fa-shopping-cart text-primary"></i>
								<span class="badge"></span>
							</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>
	</div>
	<!-- Categories End -->






	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Template Javascript -->
	<script src="<%=request.getContextPath()%>/js/main.js"></script>
</body>
</html>