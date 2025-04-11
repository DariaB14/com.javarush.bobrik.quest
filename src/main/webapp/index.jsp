<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Апокалипсис</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    String playerName = (String) session.getAttribute("name");
    if (playerName == null) {
%>
<h1>Добро пожаловать в апокалиптический квест</h1>
<form action="StartServlet" method="POST">
    Ваше имя: <input name="playerName">
    <p><input type='submit' value='Начать игру'/>
</form>
<%
} else {
%>
<h1>Добро пожаловать обратно, <%= playerName %>!</h1>
<form action="game.jsp" method="POST">
    <p><input type='submit' value='Продолжить игру'/>
</form>
<%
    }
%>
</body>
</html>

