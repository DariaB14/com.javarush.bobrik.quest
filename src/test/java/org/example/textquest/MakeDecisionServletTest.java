package org.example.textquest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

class MakeDecisionServletTest {

    private MakeDecisionServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        servlet = new MakeDecisionServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    void testForestDecision() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("decision")).thenReturn("forest");
        servlet.doPost(request, response);
        verify(response).sendRedirect("Forest");
    }

    @Test
    void testCityDecision() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("decision")).thenReturn("city");
        servlet.doPost(request, response);
        verify(response).sendRedirect("City");
    }

    @Test
    void testInvalidDecision() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("decision")).thenReturn("invalid");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        writer.flush();
        String result = stringWriter.toString();
        assert(result.contains("Запрос не отправлен!"));
        assert(result.contains("<a href='index.jsp'>Начать игру заново</a>"));
    }
}
