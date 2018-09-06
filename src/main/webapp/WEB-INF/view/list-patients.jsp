<!-- Added support for JSTL Core tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>List Patients</title>
	
	<!-- reference our style sheet -->
	<link type="text/css"
		rel="stylesheet"
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
			<input type="button" value="Add patient" onclick="window.location.href='showFormForAdd'; return false;" class="add-button"/>
															<!-- call our Spring Controller mapping 					CSS Style-->
															
			<!--  add a search box -->
            <form:form action="search" method="POST">
                Search patient:
                <input type="text" name="theSearchName" />
                <input type="submit" value="Search" class="add-button" />
            </form:form>
															
			<!-- add out html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our patient -->
				<c:forEach var="tempPatient" items="${patients}">
				<!-- patients is taken from MVC model -->
				
				<!-- construct an "update" link with patient id -->
				<c:url var="updateLink" value="/patients/showFormForUpdate">
					<c:param name="patientID" value="${tempPatient.id}" />
				</c:url>
				
				<!-- construct an "delete" link with patient id -->
				<c:url var="deleteLink" value="/patients/delete">
					<c:param name="patientID" value="${tempPatient.id}" />
				</c:url>
				
					<tr>
						<td> ${tempPatient.firstName}</td>
						<td> ${tempPatient.lastName}</td>
						<td> ${tempPatient.email}</td>	
						<td><a href="${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if (!(confirm('Are you sure that you want to delete this patient?'))) return false">Delete</a></td>			
					</tr>
					
				</c:forEach>
			</table>
		</div>
	</div>
	
</body>

</html>