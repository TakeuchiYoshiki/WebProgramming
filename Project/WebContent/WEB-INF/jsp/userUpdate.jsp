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

<meta charset="UTF-8">
<title>ユーザー情報更新</title>
</head>
<body>
<div style="text-align : right"><div class="p-3 mb-2 bg-secondary text-white">${userInfo.name} さん <a href="LogoutServlet"><p class="text-danger">ログアウト</p></a></div>
</div>
<br>



<div style="text-align : center"><h1>ユーザ情報更新</h1></div>
<br>
<div style="text-align : center"> <span class="text-danger">${errMsg1}</span></div>
<div style="text-align : center"> <span class="text-danger">${errMsg2}</span></div>

<br>

<div  style="text-align : center">ログインID：${u.loginId}</div>
 <br>
 <form action="UserUpdateServlet" method="Post">
<div style="text-align : center">パスワード：<input type="text" name ="pass"></div>
 <br>
<div style="text-align : center">パスワード(確認)：<input type="text" name ="pass2"></div>
 <br>
<div style="text-align : center">ユーザー名：<input type="text" name ="name" value = "${u.name}"></div>
 <br>
<div style="text-align : center">生年月日：<input type="date" name ="birthday" value = "${u.birthDate}"></div>
 <br>
 <input type="hidden" name="id" value="${u.id}" >
 <input type="hidden" name="pass0" value="${u.password}" >
<div style="text-align : center"><input type="submit" value="更新"></div>
<br>
</form>
<br>
<div style="text-align : left"><a href="UserListServlet">戻る</a></div>









<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


</body>
</html>