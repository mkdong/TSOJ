<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TSOJ - Header</title>
</head>
<body>
	<div id="header" class="header">
		<img src="#"/>
		<nav>
			<a href="${pageContext.request.contextPath}/home">TSOJ</a>
			<a href="${pageContext.request.contextPath}/problemset">Problem Set</a>
			<a href="${pageContext.request.contextPath}/contest">Contest</a>
			<a href="${pageContext.request.contextPath}/statu">Status</a>
			<a href="${pageContext.request.contextPath}/rank">Rank</a>
			<a href="${pageContext.request.contextPath}/about">About</a>
			<span>
				<c:choose>
					<c:when test="${sessionScope.current_user == null}">
						<a href="${pageContext.request.contextPath}/login">Login</a>
						<a href="${pageContext.request.contextPath}/register">Register</a>
					</c:when>
					<c:otherwise>
						Hi, ${sessionScope.current_user.uid}
						<a href="${pageContext.request.contextPath}/logout">Logout</a>
					</c:otherwise>
				</c:choose>
					
			</span>
		</nav>
	</div>
</body>
</html>
