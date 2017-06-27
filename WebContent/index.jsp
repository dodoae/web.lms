<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*
	String memID = (String) session.getAttribute("MEMBERID");
	boolean login = memID == null ? false : true;
	System.out.println("login :: " + memID);
	if (login) {
		response.sendRedirect("ex/cusListEx.jsp");
	} else {
	*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logistic Management System</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="css/bootstrap-min-simplex.css" rel="stylesheet" type="text/css">
<link href="css/sb-admin-2.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign In</h3>
					</div>
					<div class="panel-body">
						<form method="POST" role="form">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="id" id="adID" name="adID"
										type="text" value="1" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="adPass" id="adPass" type="password" value="1">
								</div>
								<div class="checkbox">
									<label> <input name="remember" type="checkbox"
										value="Remember Me">Remember Me
									</label>
								</div>
								<input type="button" id="adminLogin" class="btn btn-lg btn-success btn-block" value="Login">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<%
	/*
	}
	*/
%>