<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/problemset/${prev}">
						Prev
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/problemset/${next}">
						Next
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/newproblem">
						New Problem
					</a>
				</td>
			</tr>
			<tr>
				<td>ID</td>
				<td>title</td>
				<td>level</td>
				<td>categor</td>
			</tr>
		<c:forEach items="${problemset}" var="problem">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/problem/${problem.pid}">
					<c:out value="${problem.pid}" />
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/problem/${problem.pid}">
					<c:out value="${problem.ptitle}" />
					</a>
				</td>
				<td>
					<c:out value="${problem.plevel}" />
				</td>
				<td>
					<c:out value="${problem.pcategory}" />
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>
