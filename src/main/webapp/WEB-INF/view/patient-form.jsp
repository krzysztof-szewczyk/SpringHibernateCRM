<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!-- Add support for Spring MVC Form Tags -->
<!DOCTYPE html>
<html>
<head>
	<title>
		Save patient
	</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-patient-style.css">
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Patients Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Patient</h3>
		
		<form:form action="savePatient" modelAttribute="patient" method="POST">
		
		<!-- need to associate this data with patient ID, when UPDATE-->
			<form:hidden path="id" />
				
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="firstName" /></td>
					</tr>
					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="lastName" /></td>
					</tr>
					<tr>
						<td><label>Gender:</label></td>
						<td><form:input path="gender" /></td>
					</tr>
					<tr>
						<td><label>Pesel:</label></td>
						<td><form:input path="pesel" /></td>
					</tr>
					<tr>
						<td><label>Temperature:</label></td>
						<td><form:input path="temperature" /></td>
					</tr>
					<tr>
						<td><label>Type of exam:</label></td>
						<td><form:input path="typeOfExamination" /></td>
					</tr>
					<tr>
						<td><label>Date:</label></td>
						<td><form:input path="Date" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				
				</tbody>
			
			</table>
		
		
		</form:form>
		
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/patients/list">Back to List</a>
		</p>
	
		
	</div>
	
</body>

</html>