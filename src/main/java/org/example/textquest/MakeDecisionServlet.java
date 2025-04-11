package org.example.textquest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "makeDecisionServlet", value = "/MakeDecisionServlet")
public class MakeDecisionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String playerName = (String) session.getAttribute("name");
        String decision = request.getParameter("decision");

        if ("forest".equals(decision)) {
            response.sendRedirect("Forest");
        } else if ("city".equals(decision)) {
            response.sendRedirect("City");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("Запрос не отправлен!");
            out.println("<p><a href='index.jsp'>Начать игру заново</a>");
            out.println("</body></html>");
        }
    }
}

