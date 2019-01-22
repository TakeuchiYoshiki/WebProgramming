<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<title>ユーザー一覧</title>
</head>
<body>


	<div style="text-align: right">
		<div class="p-3 mb-2 bg-secondary text-white">${userInfo.name}<a
				href="LogoutServlet"><p class="text-danger">ログアウト</p></a>
		</div>
	</div>
	<br>
	<div style="text-align: right">
		<a href="NewUserServlet">新規登録</a>
	</div>
	<br>

	<div style="text-align: center">
		<div class="p-3 mb-2 bg-dark text-white">
			<h1>ユーザ一覧</h1>
		</div>
	</div>

	<form action="UserListServlet" method="Post">


		<div style="text-align: center">
			ログインID：<input type="text" name="loginid"><br>
		</div>
		<br>
		<div style="text-align: center">
			ユーザー名：<input type="text" name="username"><br>
		</div>
		<br>


		<div style="text-align: center">
			生年月日：
			<div style="display: inline-flex">
				<input type="date" name="birthday"> ~ <input type="date"
					name="birthday2"> <br>
			</div>
		</div>
		<br>
		<div style="text-align: center">
			<input type="submit" value="検索">
		</div>



	</form>

	<br>
	<br>
	<br>


	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ログインID</th>
					<th>ユーザ名</th>
					<th>生年月日</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userList}">

					<tr>

						<td>${user.loginId}</td>
						<td>${user.name}</td>
						<td>${user.birthDate}</td>
						<!-- TODO 未実装；ログインボタンの表示制御を行う -->
						<td><a class="btn btn-primary"
							href="UserInfoServlet?id=${user.id}">詳細</a> <c:if
								test="${userInfo.loginId == 'admin'}">
								<a class="btn btn-success"
									href="UserUpdateServlet?id=${user.id}">更新</a>

								<a class="btn btn-danger" href="UserDeleteServlet?id=${user.id}">削除</a>

							</c:if> <c:if test="${userInfo.loginId == user.loginId}">

								<a class="btn btn-success"
									href="UserUpdateServlet?id=${user.id}">更新</a>
							</c:if></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>


