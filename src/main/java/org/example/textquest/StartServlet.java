package org.example.textquest;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "startServlet", value = "/StartServlet")
public class StartServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String playerName = request.getParameter("playerName");

        if (playerName == null || playerName.trim().isEmpty()) {
            response.sendRedirect("index.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(120);
            session.setAttribute("name", playerName);
            Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
            if (gamesPlayed == null) {
                gamesPlayed = 0;
            }
            session.setAttribute("gamesPlayed", gamesPlayed);

            request.getRequestDispatcher("game.jsp").forward(request, response);
        }
    }
}