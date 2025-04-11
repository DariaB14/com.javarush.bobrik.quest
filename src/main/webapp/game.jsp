<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  String playerName = (String) session.getAttribute("name");

  if (playerName == null) {
    response.sendRedirect("index.jsp");
    return;
  }

  String decision = request.getParameter("decision");
  String action = request.getParameter("action");

  Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
  if (gamesPlayed == null) {
    gamesPlayed = 0;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Апокалипсис</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="session">Имя: <%= playerName %></div>

<%
  if (decision == null && action == null) {
%>
<h1>Ты очнулся в разрушенном мире...</h1>
<img src="images/start.jpg" alt="Разрушенный мир" />
<form action="game.jsp" method="POST">
  <h3>Ты видишь два пути. Куда пойдешь?</h3>
  <input type="radio" name="decision" value="forest" checked/>Лес <br>
  <input type="radio" name="decision" value="city"/>Город <br>
  <p><input type='submit' value='Выбрать'/>
</form>
<%
} else if (decision != null && action == null) {
  if ("forest".equals(decision)) {
%>
<h1>Ты в лесу и видишь горящую хижину.</h1>
<img src="images/start.jpg" alt="Горящая хижина" />
<form action="game.jsp" method="POST">
  <h3>Что ты будешь делать?</h3>
  <input type="hidden" name="decision" value="forest" />
  <input type="radio" name="action" value="help" checked/>Помочь людям <br>
  <input type="radio" name="action" value="ignore"/>Проигнорировать <br>
  <p><input type='submit' value='Выбрать'/>
</form>
<%
} else if ("city".equals(decision)) {
%>
<h1>Ты в городе и видишь группу выживших.</h1>

<form action="game.jsp" method="POST">
  <h3>Что ты будешь делать?</h3>
  <input type="hidden" name="decision" value="city" />
  <input type="radio" name="action" value="join" checked/>Присоединиться к ним <br>
  <input type="radio" name="action" value="avoid"/>Избежать их <br>
  <p><input type='submit' value='Выбрать'/>
</form>
<%
  }
} else if (action != null) {
  gamesPlayed++;
  session.setAttribute("gamesPlayed", gamesPlayed);

  if ("forest".equals(request.getParameter("decision"))) {
    if ("help".equals(action)) {
%>
<h1>Ты помог людям и нашел новых союзников.</h1>
<h3>Победа</h3>
<p>Ты и твои новые друзья смогли выжить и найти безопасное место.</p>
<%
} else if ("ignore".equals(action)) {
%>
<h2>Ты проигнорировал их и потерял шанс на помощь.</h2>
<h4>Поражение</h4>
<p>Без союзников ты не смог выжить в лесу.</p>
<%
  }
} else if ("city".equals(request.getParameter("decision"))) {
  if ("join".equals(action)) {
%>
<h1>Ты присоединился к группе и нашел убежище.</h1>
<h3>Победа</h3>
<p>Ты смог наладить жизнь в городе с новой группой выживших.</p>
<%
} else if ("avoid".equals(action)) {
%>
<h2>Ты избежал их, но остался один и уязвим.</h2>
<h4>Поражение</h4>
<p>Без поддержки ты не смог выжить в городе.</p>
<%
    }
  }
%>
<p>Количество сыгранных игр: <%= gamesPlayed %></p>
<p><a href='index.jsp'>Начать игру заново</a></p>
<%
  }
%>

</body>
</html>