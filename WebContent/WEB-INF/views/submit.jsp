<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

</style>
</head>
<body>
	<div id="mid" class="mid">
		<p class="pid">
			PID: <c:out value="${problem.pid}"></c:out>
		</p>
		<p class="ptitle">
			<c:out value="${problem.ptitle}"></c:out>
		</p>
		<form class="extra">
		</div>
	</div>
</body>
</html>
