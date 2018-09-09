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
			<!-- put new button: Add patient -->
			<a href="${pageContext.request.contextPath}/doctor/showFormForAdd"
				class="btn btn-primary" role="button" aria-pressed="true">Add
				patient</a>
			<div id="search">
				<!--  add a search box -->
				<form:form action="search" method="POST">
                Search patient:
                <input type="text" name="theSearchName" />
					<!-- put new button: Search -->
					<button type="submit" class="btn btn-primary">Search</button>
				</form:form>
			</div>


			<!-- add out html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Gender</th>
					<th>Pesel</th>
					<th>Temperature</th>
					<th>Type of Exam</th>
					<th>Date</th>
					<th>Action</th>
				</tr>

				<!-- loop over and print our patient -->
				<c:forEach var="tempPatient" items="${patients}">
					<!-- patients is taken from MVC model -->

					<!-- construct an "update" link with patient id -->
					<c:url var="updateLink" value="/doctor/showFormForUpdate">
						<c:param name="patientID" value="${tempPatient.id}" />
					</c:url>

					<!-- construct an "delete" link with patient id -->
					<c:url var="deleteLink" value="/doctor/delete">
						<c:param name="patientID" value="${tempPatient.id}" />
					</c:url>

					<tr>
						<td>${tempPatient.firstName}</td>
						<td>${tempPatient.lastName}</td>
						<td>${tempPatient.gender}</td>
						<td>${tempPatient.pesel}</td>
						<td>${tempPatient.temperature}</td>
						<td>${tempPatient.typeOfExamination}</td>
						<td>${tempPatient.date}</td>
						<td><a href="${updateLink}">Update</a>
						<security:authorize access="hasRole('ADMIN')">
						| <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure that you want to delete this patient?'))) return false">Delete</a>
						</security:authorize>
						</td>
					</tr>

				</c:forEach>
			</table>
			<!-- put new button: Back -->
			<br> <a href="${pageContext.request.contextPath}/doctor/"
				class="btn btn-primary" role="button" aria-pressed="true">Back</a>
		</div>
	</div>

</body>

</html>