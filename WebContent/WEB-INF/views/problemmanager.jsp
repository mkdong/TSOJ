<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		
		$("#submit").click(function () {
			$.post("srv/newproblem", {
				"ptitle"	:	$("#ptitle").val(),
				"pcontent"	:	$("#pcontent").val(),
				"pinput"	:	$("#pinput").val(),
				"poutput"	:	$("#poutput").val(),
				"psamplei"	:	$("#psamplei").val(),
				"psampleo"	:	$("#psampleo").val(),
				"ptime"		:	$("#ptime").val(),
				"pmemory"	:	$("#pmemory").val(),
				"plevel"	:	$("#plevel").val(),
				"pcategory"	:	$("#pcategory").val()
			}, function (response) {
				if (response === "OK") {
					alert("OK");
					location.href="problemset/0";
				} else {
					alert(response);
				}
			});
			
			return false;
		});
	});
</script>
<style type="text/css">
	#mid form table{
		max-width:800px;
		min-width:600px;
	}
	#mid form tr{
		margin-top:40px;
		padding-top:40px;
	}
	
</style>
</head>
<body>
	<div id="mid" class="mid">
		<form>
			<table>
			<tr>
				<td>题目标题</td>
				<td>
					<input type="text" id="ptitle" required="required"></input>
				</td>
			</tr>
			<tr>
				<td>题目内容</td>
				<td>
					<textarea id="pcontent" required="required"></textarea>
				</td>
			</tr>
			<tr>
				<td>输入</td>
				<td>
					<textarea id="pinput"></textarea>
				</td>
			</tr>
			<tr>
				<td>输出</td>
				<td>
					<textarea id="poutput"></textarea>
				</td>
			</tr>
			<tr>
				<td>
				样例输入
				</td>
				<td>
					<textarea id="psamplei"></textarea>
				</td>
			</tr>
			<tr>
				<td>
				样例输出</td>
				<td>
					<textarea id="psampleo"></textarea>
				</td>
			</tr>
			<tr>
				<td>
				时间限制</td>
				<td>
					<input type="number" id="ptime" value="0"></input>
				</td>
			</tr>
			<tr>
				<td>
				空间限制</td>
				<td>
					<input type="number" id="pmemory" value="0"></input>
				</td>
			</tr>
			<tr>
				<td>
				题目等级</td>
				<td>
					<input type="number" id="plevel" value="0"></input>
				</td>
			</tr>
			<tr>
				<td>
				题目分类</td>
				<td>
					<input type="text" id="pcategory"></input>
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<input type="submit" id="submit" value="submit"></input>
				</td>
			</tr>
			</table>
		</form>
	</div>

</body>
</html>
