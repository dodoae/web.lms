<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="<%=request.getContextPath()%>/css/bootstrap-min-simplex.css"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<nav class="navbar navbar-default">
<div class="container-fluid">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand"
			href="<%=request.getContextPath()%>/ex/cusListEx.jsp">LMS</a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">기초 등록<span
					class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li id="cus"><a
						href="<%=request.getContextPath()%>/ex/cusListEx.jsp">거래처 확인</a></li>
					<li><a href="<%=request.getContextPath()%>/html/warehousingList.html">창고 확인</a></li>
					<li><a href="<%=request.getContextPath()%>/html/produceList.html">품목 확인</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">생산/외주
					관리<span class="caret"></span>
			</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">발주서 내역</a></li>
					<li><a href="#">입고 내역</a></li>
					<li><a href="#">구매 내역</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">영업 관리<span
					class="caret"></span>
			</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">주문서 내역</a></li>
					<li><a href="#">출하 내역</a></li>
					<li><a href="#">판매 내역</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-expanded="false">시즌 아웃
					상품<span class="caret"></span>
			</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">시즌 아웃 상품 조회</a></li>
					<li><a href="#">창고 이동</a></li>
				</ul></li>
		</ul>
		<form class="navbar-form navbar-right" role="search">
			<button type="button" class="btn btn-primary" id="idLogout">로그아웃</button>
	</form>
	</div>
	
</div>
</nav>