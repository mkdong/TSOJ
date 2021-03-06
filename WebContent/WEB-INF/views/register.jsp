<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TSOJ -- Register</title>

<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$("#register").click(function () {
			if ($("#password").val() !== $("#password_confirm").val()) {
				alert("两次输入的密码不一致");
				return false;
			}
			$.post("srv/register",{
				"uid"		:	$("#username").val(),
				"upasswd"	:	$("#password").val()
			}, function(response) {
				if (response === "OK") {
					location.href = "login";
				} else {
					alert(response);
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
		<form>
			<input id="username" name="username" type="text" placeholder="User name"/><br />
			<input id="password" name="password" type="password" placeholder="Password"/><br />
			<input id="password_confirm" type="password" placeholder="Confirm password"/><br />
			<input id="register" type="submit" />
		</form>
	</div>
</body> 
</html>
