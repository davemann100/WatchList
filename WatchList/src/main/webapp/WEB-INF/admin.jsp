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
<title>WatchList - Admin Panel</title>
</head>
<body>

	<div class="col-8 mx-auto mt-4 d-flex justify-content-between">
		<div>
			<h1>Admin Control Panel</h1>
			<p>Instructions on API config and Selenium testing below</p>
		</div>
		<div>
			<form action="/logout" method="get">
				<button type="submit" class="btn btn-warning mt-2 rounded-3 border border-dark border-2">Logout</button>
			</form>
			<form action="/dashboard" method="get">
				<button type="submit" class="btn btn-warning mt-2 rounded-3 border border-dark border-2">Dashboard</button>
			</form>
		</div>
	</div>
	<div class="container col-6 mx-auto mt-4">
		<div>
			<h4 class="text-center">Selenium Tests | API Config</h4>
		</div>
		<div class="card border border-3 rounded-3 p-4">
			<h5>Unit Tests</h5>
			<button type="submit" class="btn btn-info mt-2 rounded-3 border border-dark border-2">Login Test</button>
			<button type="submit" class="btn btn-info mt-2 rounded-3 border border-dark border-2">Registration Test</button>
		</div>
	</div>
</body>
</html>









