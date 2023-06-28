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
<title>WatchList - View</title>
</head>
<body>

	<div class="col-8 mx-auto mt-4 d-flex justify-content-between">
		<div class="mt-4">
			<h1>Detailed View:</h1>
		</div>
		<div>
			<form action="/logout" method="get">
				<button type="submit" class="btn btn-warning mt-2 rounded-3 border border-dark border-2">Logout</button>
			</form>
			<form action="/dashboard" method="get">
				<button type="submit" class="btn btn-warning mt-2 rounded-3 border border-dark border-2">Back</button>
			</form>
		</div>
	</div>
	<div class="card col-6 mx-auto mt-4 p-4 border border-dark border-3 rounded-4">
		<h3>Title: <c:out value="${oneVideo.title}"/></h3>
		<h3>Media Type: <c:out value="${oneVideo.type}"/></h3>
		<h3>Recommended By: <c:out value="${oneVideo.recommended}"/></h3>
		<h3>Created: <c:out value="${oneVideo.createdAt}"/></h3>
		<hr>
		<p>API integration and stats displayed here</p>
		<p>Criteria to pull from API: ratings/reviews/genre or category</p>
	</div>


</body>
</html>