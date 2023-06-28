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
<body>

	<div class="col-8 mx-auto mt-4 d-flex justify-content-between">
		<div>
			<h1>Welcome, <c:out value="${userName}" /></h1>
			<p>Easily add video suggestions by clicking 'Add+'
		</div>
		<div class="">
			<form action="/logout" method="get">
				<button type="submit" class="btn btn-warning mt-2 rounded-3 border border-dark border-2">Logout</button>
			</form>
			<form action="/admin" method="get">
				<button type="submit" class="btn btn-warning mt-2 rounded-3 border border-dark border-2">Admin</button>
			</form>
		</div>
	</div>
	<div class="container col-6 mx-auto mt-4">
		<div class="mt-4">
			<div class="d-flex justify-content-between">
				<h4 class="align-items-center">Your WatchList:</h4>
				<form action="/create" method="get">
					<button type="submit" class="btn btn-warning btn-sm mt-2 rounded-3 border border-dark border-2">Add+</button>
				</form>			
			</div>
			
			<table class="table table-striped table-hover col-6 mx-auto m-2 border border-3 border-dark bg-light">
				<thead class="border border-2 border-dark">
					<tr>
						<th>Title</th>
						<th>Media Type</th>
						<th>Recommended By</th>
						<th>Action(s)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="eachVideo" items="${videoList}">
						<tr>
							<td><c:out value="${eachVideo.title}"/></td>
							<td><c:out value="${eachVideo.type}"/></td>
							<td><c:out value="${eachVideo.recommended}"/></td>
							<td>
								<div class="d-flex justify-content-around">
									<form action="/video/${eachVideo.id}" method="get">
										<button type="submit" class="bg-info rounded-3">View</button>
									</form>
									<form action="/edit/${eachVideo.id}" method="get">
										<button type="submit" class="bg-info rounded-3">Edit</button>
									</form>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>