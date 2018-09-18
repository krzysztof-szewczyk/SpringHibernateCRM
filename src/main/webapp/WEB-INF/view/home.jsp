<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<html>

<head>
<title>Ozii Company Home Page</title>
</head>

<body>
	<h2>Ozii Company Home Page</h2>
	<hr>

	<p>Welcome to the Clinical website</p>

	<hr>

	<!-- display user name and role -->

	<p>
		Access for:
		<security:authentication property="principal.username" />
		<br> <br> Your role(s):
		<security:authentication property="principal.authorities" />

	</p>


	<hr>

	<security:authorize access="hasRole('PATIENT')">
		<p>
			<a href="${pageContext.request.contextPath}/patient/"
				class="btn btn-primary" role="button" aria-pressed="true"> Go to
				Patient Zone </a> (read medical examinations)
		</p>
	

		<hr>
	</security:authorize>

	<!--  Add a link to a point to /leaders... this id for the managers -->

	<security:authorize access="hasAnyRole('DOCTOR','ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/list/"
				class="btn btn-primary" role="button" aria-pressed="true"> Go to
				Doctor Zone </a> (create medical examinations)
		</p>

		<hr>
	</security:authorize>


	<security:authorize access="hasAnyRole('ADMIN', 'MODERATOR')">

		<p>
			<a href="${pageContext.request.contextPath}/admin/"
				class="btn btn-primary" role="button" aria-pressed="true"> Go to
				Admin/Mod Zone </a> (delete medical examination) - Only for the Boss
		</p>


	</security:authorize>

	<hr> 
	<security:authorize access="hasAnyRole('DOCTOR', 'ADMIN', 'MODERATOR')">

		<p>
			<a href="${pageContext.request.contextPath}/registration/"
				class="btn btn-primary" role="button" aria-pressed="true">
				Register new user </a>
		</p>


	</security:authorize>
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout"  class="btn btn-success"/>
	</form:form>

</body>

</html>