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
		max-width:800px;
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
	
	.mid table td{
		word-break: break-all;
		word-wrap:break-word;
	}
	.mid table th,
	.mid talbe td:nth-child(1),
	.mid table td:nth-child(2) {
		word-break:keep-all;
		white-space:nowrap;
	}

</style>
</head>

<body>
	<div id="mid" class="mid">
	
		<nav>
			<a href="${pageContext.request.contextPath}/status/<c:if test="${!empty pid}"><c:out value="${pid}/"></c:out></c:if>${first}">
				&lt;&lt;
			</a>
			<a href="${pageContext.request.contextPath}/status/<c:if test="${!empty pid}"><c:out value="${pid}/"></c:out></c:if>${prev}">
				&lt;
			</a>
			<a href="${pageContext.request.contextPath}/status/<c:if test="${!empty pid}"><c:out value="${pid}/"></c:out></c:if>${next}">
				&gt;
			</a>
		</nav>
		<table>
			<tr>
				<th>PID</th>
				<th>UID</th>
				<th>Language</th>
				<th>Result</th>
				<th>Time</th>
				<th>Memory</th>
				<th>Judged at</th>
			</tr>
		<c:forEach items="${solutions}" var="solution">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/problem/${solution.pid}">
						<c:out value="${solution.pid}" />
					</a>
				</td>
				<td>
					<c:out value="${solution.uid}" />
				</td>
				<td>
					<c:out value="${solution.slang}" />
				</td>
				<td>
					<c:out value="${solution.sresult}" />
				</td>
				<td>
					<c:out value="${solution.stime}" />
				</td>
				<td>
					<c:out value="${solution.smemory}" />
				</td>
				<td>
					<c:out value="${solution.stesttime}" />
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>
