<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Use line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Use line below to utilize form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css" />
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>WatchList - Welcome</title>
</head>
<body class="bg-dark">
	<h1 class="text-center text-warning mt-4 text-decoration-underline">Welcome to WatchList</h1>
	<div class="container d-flex justify-content-around">
		<div
			class="card col-4 bg-light mt-5 p-4 rounded-4 border border-4 border-primary">
			<h1>Register</h1>
			<form:form action="/register" method="POST" modelAttribute="newUser">
				<div class="form-group">
					<form:label path="userName" class="form-label">Username:</form:label>
					<form:input path="userName" class="form-control" />
					<form:errors path="userName" class=" text-danger" />
				</div>
				<div class="form-group">
					<form:label path="email" class="form-label">Email:</form:label>
					<form:input path="email" class="form-control" />
					<form:errors path="email" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="password" class="form-label">Password:</form:label>
					<form:input path="password" class="form-control" type="password" />
					<form:errors path="password" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="confirm" class="form-label">Confirm Password:</form:label>
					<form:input path="confirm" class="form-control" type="password" />
					<form:errors path="confirm" class="text-danger" />
				</div>
				<button type="submit" class="btn btn-primary mt-2">Register</button>
			</form:form>

		</div>
		<div
			class="card col-4 bg-light mt-5 p-4 rounded-4 border border-4 border-success">
			<h1>Login</h1>
			<form:form action="/login" method="POST" modelAttribute="newLogin">
				<div class="form-group">
					<form:label path="email" class="form-label">Email:</form:label>
					<form:input path="email" class="form-control" />
					<form:errors path="email" class="text-danger" />
				</div>
				<div class="form-group">
					<form:label path="password" class="form-label">Password:</form:label>
					<form:input path="password" class="form-control" type="password" />
					<form:errors path="password" class="text-danger" />
				</div>
				<button type="submit" class="btn btn-success mt-2">Login</button>
			</form:form>
		</div>
	</div>
</body>
</html>