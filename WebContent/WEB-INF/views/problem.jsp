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
					题目序号:<c:out value="${problem.pid}"></c:out>
				</td>
			</tr>
			<tr>
				<td>
					题目标题:<c:out value="${problem.ptitle}"></c:out>
				</td>
			</tr>
			<tr>
				<td>
					题目内容:<c:out value="${problem.pcontent}"></c:out>
				</td>
			</tr>
			<tr>
				<td>
				输入:<c:out value="${problem.pinput}"></c:out>
				</td>
			</tr>
			<tr>
				<td>
				输出:<c:out value="${problem.poutput}">n/a</c:out>
				</td>
			</tr>
			<tr>
				<td>
				样例输入:<c:out value="${problem.psamplei}"></c:out>
				</td>
			</tr>
			<tr>
				<td>
				样例输出:<c:out value="${problem.psampleo}"></c:out>
				</td>
			</tr>
			<tr>
				<td>
				时间限制:<c:out value="${problem.ptime}"></c:out>
				</td>
			</tr>
			<tr>
				<td>
				空间限制:<c:out value="${problem.pmemory}"></c:out>
				</td>
			</tr>
			<tr>
				<td>
				题目等级:<c:out value="${problem.plevel}"></c:out>
				</td>
			</tr>
			<tr>
				<td>
				题目分类:<c:out value="${problem.pcategory}"></c:out>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
