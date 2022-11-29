<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/astyle.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>

<body>


	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-4"></div>
				<div class="col-lg-4 mt-5">
					<form method="post" action="/jpaJsf/ControllerServlet?action=login"
						class="card">
						<div class="form-group mx-2">
							<label for="nom">Email</label> <input type="email" name="email"
								class="form-control" id="nom" placeholder="Entrer votre Email">
						</div>
						<div class="form-group mx-2">
							<label for="prenom">Password</label> <input type="password"
								name="password" class="form-control" id="prenom"
								placeholder="Entrer votre password">
						</div>

						<input type="submit" class="btn btn-primary mb-2 mt-3"
							value="log in">
					</form>
					 <a	href="/jpaJsf/ControllerServlet?action=logupform">Vous n'avez pas un Compte</a>
				</div>
</body>
</html>