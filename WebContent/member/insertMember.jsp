<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logistic Management System</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="../css/bootstrap-min-simplex.css" rel="stylesheet"
	type="text/css">
<link href="../css/sb-admin-2.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="../js/script.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">회원 가입</h3>
					</div>
					<div class="panel-body">
						<form method="POST">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="ID를 입력해주세요" id="memID"
										name="mId" type="text" autofocus>
								</div>

								<div class="form-group" align="right">
									<input type="checkbox" id="idcheckbox"> <input
										type="button" id="idCheck" value="중복체크"> <input
										type="hidden" id="idcheck" value="0">
								</div>
								<!-- 
										<td id="idCheckId">
										<input type="button" id="idCheck" value="중복체크">
										<input type="checkbox" id="idcheckbox">
										<input type="hidden" id="idcheck" value="0">
									 -->
								<div class="form-group">
									<input type="password" class="form-control" name="pwd"
										id="memPwd" placeholder="비밀번호를 입력해주세요">
								</div>
								<div class="form-group">
									<input type="text" class="form-control" name="name"
										id="memName" placeholder="이름을 입력해주세요">
								</div>
								<div class="form-group">
									<input type="text" class="form-control" name="phone"
										id="memPhone" placeholder="핸드폰 번호를 입력해주세요">
								</div>
								<input type="button" class="btn btn-info btn-block" id="join"
									value="가입하기"> <br>
								<a href="../index.jsp"> <input type="button" class="btn btn-default btn-block" value="홈으로"></a>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>