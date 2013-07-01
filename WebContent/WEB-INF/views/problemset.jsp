<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.mid table{
		border-collapse:collapse;
		font-size:20px;
		background-color:#DAE6FF;
	}
	.mid table, th, td, tr{
		border: 1px solid white;
	}
	.mid{
		margin-top:10px;
	}
	.mid nav a{
		padding-left:20px;
		text-decoration:none;
		font-size:20px;
		text-align:right;
	}

</style>
</head>

<body>
	<div id="mid" class="mid">
		<nav>
			<a href="${pageContext.request.contextPath}/problemset/${first}">
				&lt;&lt;
			</a>
			<a href="${pageContext.request.contextPath}/problemset/${prev}">
				&lt;
			</a>
			<a href="${pageContext.request.contextPath}/problemset/${next}">
				&gt;
			</a>
			<a href="${pageContext.request.contextPath}/problemset/${last}">
				&gt;&gt;
			</a>
			<a href="${pageContext.request.contextPath}/newproblem">
				New Problem
			</a>
		</nav>
		<table>
			<tr>
				<th>ID</th>
				<th>title</th>
				<th>level</th>
				<th>category</th>
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
		<nav>
			<a href="${pageContext.request.contextPath}/problemset/${first}">
				&lt;&lt;
			</a>
			<a href="${pageContext.request.contextPath}/problemset/${prev}">
				&lt;
			</a>
			<a href="${pageContext.request.contextPath}/problemset/${next}">
				&gt;
			</a>
			<a href="${pageContext.request.contextPath}/problemset/${last}">
				&gt;&gt;
			</a>
			<a href="${pageContext.request.contextPath}/newproblem">
				New Problem
			</a>
		</nav>
	</div>
</body>
</html>
