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
			<h2>CRM - Patients Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save New Patient's Exam</h3>
		<form:form action="savePatientExam" modelAttribute="patientExam" method="POST">
			<!-- need to associate this data with patient ID, when UPDATE-->
			<form:hidden path="id" />
			<form:errors path="*" cssClass="errorblock" element="div" />
			<table>
				<tbody>
					<tr>
						<td><label>Temperature:</label><br> <form:errors
								path="temperature" cssClass="error" /></td>
						<td><form:input path="temperature" /></td>
					</tr>
					<tr>
						<td><label>Type of exam:</label></td>
						<td><form:select path="typeOfExamination">
								<form:option value="-" label="(please select:)" />
								<form:option value="MRI" label="MRI" />
								<form:option value="CT" label="CT" />
								<form:option value="USG" label="USG" />
								<form:option value="RTG" label="RTG" />
							</form:select></td>
					</tr>
					<tr>
						<td><label>Date:</label><br> <form:errors path="date"
								cssClass="error" /></td>
						<td><form:input type="date" path="date" pattern="yyyy-MM-dd" /><br></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><button type="submit" class="btn btn-primary">Save</button></td>
					</tr>

				</tbody>

			</table>


		</form:form>

		<p>
			<br> <a href="${pageContext.request.contextPath}/doctor/list"
				class="btn btn-primary" role="button" aria-pressed="true">Back</a>
		</p>


	</div>

</body>

</html>