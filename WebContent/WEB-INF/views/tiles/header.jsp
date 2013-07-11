<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TSOJ - Header</title>
<style type="text/css">
	body{
		text-align:center;
		background-color:#F1F1FD;
	}
	.header{
		top:0px;
		background-color:#6589D1;
		height:50px;
		text-align:right;
		font-size:20px;
		color:white;
	}
	.header>img{
		float:left;
		width:50px;
		height:50px;
		padding-left:50px;
	}
	.header>nav{
		padding-top:15px;
	}
	.header>nav .nav{
		float:left;
	}
	.header>nav .account{
		float:right;
		padding-right:50px;
	}
	.header a{
		text-decoration:none;
		color:white;
		padding-left:30px;
	}
	.header a:link{
		color:white;
	}
	.header a:visited{
		color:white;
	}
	.header a:active{
		color:white;
	}
	.header a:hover{
		color:white;
	}
	.mid{
		margin-left:auto;
		margin-right:auto;
		display:inline-block;
		width:900px;
	}
</style>
</head>
<body>
	<div id="header" class="header">
		<img src="#"/>
		<nav>
			<div class="nav">
			<a href="${pageContext.request.contextPath}/home">TSOJ</a>
			<a href="${pageContext.request.contextPath}/problemset">Problem Set</a>
			<!-- <a href="${pageContext.request.contextPath}/contest">Contest</a> -->
			<a href="${pageContext.request.contextPath}/status/0">Status</a>
			<a href="${pageContext.request.contextPath}/rank/0">Rank</a>
			<a href="${pageContext.request.contextPath}/about">About</a>
			</div>
			<div class="account">
			<span>
				<c:choose>
					<c:when test="${sessionScope.current_user == null}">
						<a href="${pageContext.request.contextPath}/login">Login</a>
						<a href="${pageContext.request.contextPath}/register">Register</a>
					</c:when>
					<c:otherwise>
						Hi, <a href="${pageContext.request.contextPath}/info">${sessionScope.current_user.uid}</a>
						<a href="${pageContext.request.contextPath}/logout">Logout</a>
					</c:otherwise>
				</c:choose>
					
			</span>
			</div>
		</nav>
	</div>
</body>
</html>
