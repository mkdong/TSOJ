<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.mid{
		text-align:center;
	}
	.mid .ptitle{
		font-size:30px;
		color:blue;
	}
	.mid .pid{
		font-size:20px;
		text-align:left;
	}
	.mid .extra{
		margin-left:auto;
		margin-right:auto;
		display:inline-block;
		text-align:center;
	}
	.mid .extra table{
		font-size:15px;
		text-align:center;
	}
	.mid .content p{
		font-size:25px;
		color:blue;
		text-align:left;
	}
	.mid .content .pcontent{
		font-size:15px;
		color:black;
	}
	.mid nav{
		font-size:20px;
		text-decoration:none;
	}
	.mid nav a{
		margin-left:20px;
	}
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
		<div class="extra">
			<table>
				<tr>
					<td>
						Time Limits: <c:out value="${problem.ptime}"></c:out> MS
					</td>
					<td>
						Memory Limits: <c:out value="${problem.pmemory}"></c:out> KB
					</td>
					<td>
						Level: <c:out value="${problem.plevel}"></c:out>
					</td>
				</tr>
				<tr>
					<td colspan=3>
						Category: <c:out value="${problem.pcategory}"></c:out>
					</td>
				</tr>
			</table>
		</div>
		<div class="content">
			<p>Description</p>
			<p class="pcontent">
				<c:out value="${problem.pcontent}"></c:out>
			</p>
			<p>Input</p>
			<p class="pcontent">
				<c:out value="${problem.pinput}"></c:out>
			</p>
			<p>Output</p>
			<p class="pcontent">
				<c:out value="${problem.poutput}">n/a</c:out>
			</p>
			<p>Sample Input</p>
			<p class="pcontent">
				<c:out value="${problem.psamplei}"></c:out>
			</p>
			<p>Sample Output</p>
			<p class="pcontent">
				<c:out value="${problem.psampleo}"></c:out>
			</p>			
		</div>
		<nav>
			<a href="${pageContext.request.contextPath}/submit/<c:out value="${problem.pid}"></c:out>">Submit</a>
			<a href="${pageContext.request.contextPath}/problemset/<c:out value="${psid}"></c:out>">Go Back</a>
			<a href="${pageContext.request.contextPath}/status/<c:out value="${problem.pid}"></c:out>">Status</a>
			<a href="${pageContext.request.contextPath}/comments/<c:out value="${problem.pid}"></c:out>">Discuss</a>
		</nav>
	</div>
</body>
</html>
