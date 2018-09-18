<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

	<div>

		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">Register New User</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<!-- Registration Form -->
					<form:form
						action="${pageContext.request.contextPath}/registration/processRegistrationForm"
						modelAttribute="customer" class="form-horizontal">

						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col-xs-15">
								<div>

									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">

										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError}</div>

									</c:if>

								</div>
							</div>
						</div>

						<!-- User name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>

							<form:input path="userName" placeholder="username"
								class="form-control" />
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>

							<form:password path="password" placeholder="password"
								class="form-control" />
						</div>

						<!-- Authorities -->
						<security:authorize access="hasAnyRole('ADMIN', 'MODERATOR')">
								Roles: 
								<br>
							<form:checkbox path="authorities" value="ROLE_DOCTOR" /> Doctor <br>
							<security:authorize access="hasRole('ADMIN')">
								<form:checkbox path="authorities" value="ROLE_MODERATOR" /> Moderator<br>
								<form:checkbox path="authorities" value="ROLE_ADMIN" /> Administrator (has all roles) <br>
							</security:authorize>
						</security:authorize>

						<br>
							Patient role is added automatically.
							<br>
						<br>


						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-6 controls">
								<button type="submit"  class="btn btn-success"">Register</button>
							</div>
						</div>

						<a href="${pageContext.request.contextPath}/"
							class="btn btn-primary" role="button" aria-pressed="true">Back</a>
					</form:form>

				</div>

			</div>

		</div>

	</div>

</body>
</html>