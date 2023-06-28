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
<title>WatchList - Edit</title>
</head>
<body>

	<div class="container col-6 mx-auto mt-4">
		<h1>Edit <c:out value="${video.title}"/></h1>
	</div>
	<div
		class="card col-6 mx-auto mt-4 p-4 border border-dark border-3 rounded-4">
		<form:form action="/edit/${video.id}" method="put" modelAttribute="video"
			class="form">
			<!-- Path is the attribute from book model, value is the userId from session -->
			<form:hidden path="user_id" value="${userId}" />
			<div class="form-group">
				<form:label class="form-label" path="title">Primary Title:</form:label>
				<form:input class="form-control" path="title" />
				<form:errors class="text-danger" path="title" />
			</div>
			<div class="form-group">
				<form:label class="form-label" path="type">Media Type:</form:label>
				<form:select class="form-control" path="type">
					<form:option value="Movie"/>
					<form:option value="TV-Show"/>
					<form:option value="Other"/>
				</form:select>
				<form:errors class="text-danger" path="type" />
			</div>
			<div class="form-group">
				<form:label class="form-label" path="recommended">Recommended By: (optional)</form:label>
				<form:input type="input" class="form-control" path="recommended" />
				<form:errors class="text-danger" path="recommended" />
			</div>
			<input type="submit" value="Update"
				class="btn btn-success mt-2 border border-dark border-2" />
		</form:form>
		<form action="/delete/${video.id}" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<button type="submit" class="btn btn-danger mt-2 rounded-3 border border-dark border-2">Delete</button>
		</form>
		<form action="/dashboard" method="get">
			<button type="submit" class="btn btn-warning mt-2 rounded-3 border border-dark border-2">Cancel</button>
		</form>
	</div>


</body>
</html>