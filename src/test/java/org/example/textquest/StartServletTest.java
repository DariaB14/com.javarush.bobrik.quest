
package org.example.textquest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

class StartServletTest {
    private StartServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    @Test
    void setUp() {
        servlet = new StartServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    void testPlayerName() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("playerName")).thenReturn("Player1");
        when(request.getRequestDispatcher("game.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("name", "Player1");
        verify(session).setAttribute("gamesPlayed", 0);
        verify(dispatcher).forward(request, response);
    }
}

