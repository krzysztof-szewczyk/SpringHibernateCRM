<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Add support for Spring MVC Form Tags -->
<!DOCTYPE html>
<html>
<head>
<title>Save patient</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-patient-style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>Patient's form</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save New Patient</h3>
		<form:form action="savePatient" modelAttribute="patient" method="POST">

			<!-- need to associate this data with patient ID, when UPDATE-->
			<form:hidden path="id" />
			<form:errors path="*" cssClass="errorblock" element="div" />
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label><br> <form:errors
								path="firstName" cssClass="error" /></td>
						<td><form:input type="text" path="firstName" /></td>
					</tr>
					<tr>
						<td><label>Last name:</label><br> <form:errors
								path="lastName" cssClass="error" /></td>
						<td><form:input path="lastName" /></td>
					</tr>
					<tr>
						<td><label>Gender:</label><br> <form:errors
								path="gender" cssClass="error" /></td>
						<td>
							<fieldset>

								<form:radiobutton path="gender" value="M" />
								Male<br>
								<form:radiobutton path="gender" value="F" />
								Female

							</fieldset>


						</td>
					</tr>
					<tr>
						<td><label>Pesel:</label><br> <form:errors path="pesel"
								cssClass="error" /></td>
						<td><form:input type="text" path="pesel" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><button type="submit" class="btn btn-primary">Save</button></td>	
					</tr>

				</tbody>

			</table>


		</form:form>

		<p>
			<br> <a href="${pageContext.request.contextPath}/list/"
				class="btn btn-primary" role="button" aria-pressed="true">Back</a>
		</p>


	</div>

</body>

</html>