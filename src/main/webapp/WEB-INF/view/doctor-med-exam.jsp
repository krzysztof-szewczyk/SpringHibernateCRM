<html>
<head>
<title>Ozii Clinic for PATIENT</title>
<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
	<h2>Doctor Zone</h2>
	<hr>
	<p>
		There you will be able to create and save patient's medical
		examinations. xD <br> Coming soon... :)
	</p>
	<hr>
	<a href="${pageContext.request.contextPath}/doctor/list" class="btn btn-primary"
		role="button" aria-pressed="true">List</a> 
	<a href="${pageContext.request.contextPath}/doctor/showRegistrationForm"
			class="btn btn-primary" role="button" aria-pressed="true">Register New Patient</a>
	<hr>
	<a href="${pageContext.request.contextPath}/" class="btn btn-primary"
		role="button" aria-pressed="true">Back</a>
</body>
</html>