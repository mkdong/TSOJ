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
	
	.mid table td:nth-child(2){
		word-break: break-all;
		word-wrap:break-word;
	}
	.mid table th{
		word-break:keep-all;
		white-space:nowrap;
	}

</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#comment").click(function() {
			$.post("${pageContext.request.contextPath}/srv/newcomment", {
				"cmcontent"	:	$("#cmcontent").val(),
				"pid"		:	"${problem.pid}"
			}, function(res) {
				if (res === "OK") {
					location.reload();
				} else {
					alert(res);
				}
			});
			return false;
		});
	});
</script>
</head>

<body>
	<div id="mid" class="mid">
	
		<nav>
			<a href="${pageContext.request.contextPath}/comment/${problem.pid}/${first}">
				&lt;&lt;
			</a>
			<a href="${pageContext.request.contextPath}/comment/${problem.pid}/${prev}">
				&lt;
			</a>
			<a href="${pageContext.request.contextPath}/comment/${problem.pid}/${next}">
				&gt;
			</a>
			<a href="${pageContext.request.contextPath}/comment/${problem.pid}/${last}">
				&gt;&gt;
			</a>
			<a href="${pageContext.request.contextPath}/problem/${problem.pid}">
				Back
			</a>
		</nav>
		<c:if test="${not guest}">
			<form>
				<input id="cmcontent" type="text" name="cmcontent" placeholder="Your comment here"></input>
				<input id="comment" type="submit" value="Post"></input>
			</form>
		</c:if>
		<table>
			<tr>
				<th>UID</th>
				<th>Content</th>
				<th>Comment Time</th>
			</tr>
		<c:forEach items="${comments}" var="comment">
			<tr>
				<td>
					<c:out value="${comment.uid}" />
				</td>
				<td>
					<c:out value="${comment.cmcontent}" />
				</td>
				<td>
					<c:out value="${comment.cmtime}" />
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>
