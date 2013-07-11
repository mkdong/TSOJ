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
		$("#update_password").click(function () {
			if ($("#password").val() !== $("#password_confirm").val()) {
				alert("两次输入的密码不一致");
				return false;
			}
			$.post("srv/password",{
				"upasswd"	:	$("#password").val()
			}, function(response) {
				if (response === "OK") {
					alert(response);
					$("#password").val("");
					$("#password_confirm").val("");
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
	img {
		width: 200px;
		height: 200px;
	}
</style>
</head>
<body>
	<div>
		<form>
			<input id="username" name="username" type="text" value="${sessionScope.current_user.uid}" readonly="readonly"/>
		</form>
		<hr />
		<form>
			<input id="password" name="password" type="password" placeholder="Password"/><br />
			<input id="password_confirm" type="password" placeholder="Confirm password"/><br />
			<input id="update_password" type="submit" value="update password"/>
		</form>
		<hr />
		<form action="${pageContext.request.contextPath}/avatar" method="post" enctype="multipart/form-data" target="">
			<input type="file" name="avatar"/>
			<input id="update_avatar" type="submit" value="update avatar"/>
		</form>
		<img src="avatar" />
		
	</div>
</body> 
</html>
