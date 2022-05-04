<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Register</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6" style="border: solid 2px; margin-top: 50px;">
				<h1 class="text-center">Register</h1>
				<br>
				<form class="form-group" action="RegistrationServlet" method="post">
					<label class="form-label" for="username">Enter Your
						UserName</label> <input type="text" name="username"
						class="form-control mb-3"> <label class="form-label"
						for="email">Enter Your Email</label> <input type="email"
						name="email" class="form-control mb-3"> <label
						class="form-label" for="password">Enter Your Password</label> <input
						type="password" name="password" class="form-control mb-3">

					<button class="btn btn-primary mb-4" type="submit">Register</button>
					<button class="btn btn-danger mb-4" type="reset">Cancel</button>
				</form>
				<p>
					Already Registered ? <a href="login.jsp">Login</a>
				</p>
			</div>
		</div>
</body>
</html>