<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

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
		<br> <br> 
		Your role(s):
		<security:authentication property="principal.authorities" />

	</p>


	<hr>
	<!--  Add a link to a point to /leaders... this id for the managers -->

	<security:authorize access="hasRole('PATIENT')">
	<p>
		<a href="${pageContext.request.contextPath}/patient">
		Go to Patient Zone
		</a>
			(read medical examinations)
	</p>

	<hr>
	</security:authorize>
	
	<!--  Add a link to a point to /leaders... this id for the managers -->

	<security:authorize access="hasRole('DOCTOR')">
	<p>
		<a href="${pageContext.request.contextPath}/doctor/">
		Go to Doctor Zone
		</a>
			(create medical examinations)
	</p>

	<hr>
	</security:authorize>
	
	<security:authorize access="hasRole('MODERATOR')">
	<p>
		<a href="${pageContext.request.contextPath}/moderator">
		Go to Moderator Zone
		</a>
		(edit medical examination if doctor asked)
	</p>

	<hr>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">


		<p>
			<a href="${pageContext.request.contextPath}/admin">
			Go to Admin Zone
			</a> (delete medical examination) - Only for the Boss
		</p>

		
	</security:authorize>
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>

</body>

</html>