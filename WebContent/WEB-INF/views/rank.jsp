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
		table-layout: fixed;
		width:800px;
	}
	.mid table, th, td, tr{
		border: 1px solid white;
	}
	.mid{
		margin-top:10px;
	}
	.mid nav a{
		margin: 20px;
	}

</style>
</head>

<body>
	<div id="mid" class="mid">
	
		<nav>
			<a href="${pageContext.request.contextPath}/rank/${first}">
				&lt;&lt;
			</a>
			<a href="${pageContext.request.contextPath}/rank/${prev}">
				&lt;
			</a>
			<a href="${pageContext.request.contextPath}/rank/${next}">
				&gt;
			</a>
		</nav>
		<table>
			<tr>
				<th>Rank</th>
				<th>UID</th>
				<th>AC</th>
				<th>WA</th>
				<th>Submits</th>
			</tr>
		<c:forEach items="${userRanks}" var="rank">
			<tr>
				<td>
					<c:out value="${rank.rank}" />
				</td>
				<td>
					<c:out value="${rank.uid}" />
				</td>
				<td>
					<c:out value="${rank.ac}" />
				</td>
				<td>
					<c:out value="${rank.wa}" />
				</td>
				<td>
					<c:out value="${rank.tot}" />
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>
