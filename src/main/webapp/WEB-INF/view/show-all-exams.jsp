<!-- Added support for JSTL Core tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!DOCTYPE html>
<html>

<head>
<title>List Patients</title>
<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- reference our style sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<!-- use proper app name ^ -->
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Patients Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<!-- add out html table here -->
			<table>
				<tr>
					<th>Temperature</th>
					<th>Type of Examination</th>
					<th>Date</th>
					<th>Doctor</th>
				</tr>

				<!-- loop over and print our patient -->
				<c:forEach var="tempPatientExam" items="${patientExams}">
					<!-- patients exams is taken from MVC model -->

					<tr>
						<td>${tempPatientExam.temperature}</td>
						<td>${tempPatientExam.typeOfExamination}</td>
						<td>${tempPatientExam.date}</td>
						<td>doctor xyz</td>
					</tr>

				</c:forEach>
			</table>
			<!-- put new button: Back -->
			<br>
			<security:authorize access="hasAnyRole('DOCTOR','ADMIN')">
				<a href="${pageContext.request.contextPath}/list/"
					class="btn btn-primary" role="button" aria-pressed="true">Back</a>
			</security:authorize>
			<security:authorize access="hasRole('PATIENT')">
				<a href="${pageContext.request.contextPath}/"
					class="btn btn-primary" role="button" aria-pressed="true">Back</a>
			</security:authorize>
			
		</div>
	</div>

</body>

</html>