<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TSOJ -- login</title>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$("#login").click(function () {
			$.post("srv/login",{
				"uid"		:	$("#username").val(),
				"upasswd"	:	$("#password").val()
			}, function(response) {
				if (response === "OK") {
					location.href = "home";
				} else {
					alert("用户名或密码错误");
					$("#password").val("");
				}
			});
			return false;
		});
	});
</script>
<style type="text/css">
	input {
		font-size: 20px;
		margin: 10px;
	}
	form {
		margin: 40px;
	}
</style>
</head>

<body>
	<div>
		<form action="d">
			<input id="username" name="uid" type="text" placeholder="User name"/><br />
			<input id="password" name="upasswd" type="password" placeholder="Password"/><br />
			<input id="login" type="submit" onclick="javascript:void(0);"/>
		</form>
	</div>
</body>
</html>
