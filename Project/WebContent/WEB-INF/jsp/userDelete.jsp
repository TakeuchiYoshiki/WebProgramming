<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


<title>ユーザー削除確認</title>
</head>
<body>
<div style="text-align : right"><div class="p-3 mb-2 bg-secondary text-white">${u.name} さん <a href="LogoutServlet"><p class="text-danger">ログアウト</p></a></div>
</div>
<br>
<div style="text-align : center"><h1>ユーザー削除確認</h1></div>
<br>
<br>
<div style="text-align : center">
ログインID：${u.name}を本当に削除してよろしいでしょうか。
</div>
<br>
<br>
<br>
<div style="text-align : center">

<a href="UserListServlet"><input type="submit" value="キャンセル" style="WIDTH: 200px; HEIGHT: 50px"></a>

<form action="UserDeleteServlet" method="Post">


<input type="hidden" name="id" value="${u.id}" >
<input type="submit" value="OK" style="WIDTH: 200px; HEIGHT: 50px">
</form>



</div>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>