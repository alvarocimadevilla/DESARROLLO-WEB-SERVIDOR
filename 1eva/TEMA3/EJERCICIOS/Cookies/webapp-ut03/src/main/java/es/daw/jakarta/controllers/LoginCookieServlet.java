package es.daw.jakarta.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login-cookie")
public class LoginCookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1.leer datos del request
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");

        // 2. Procesarlos

        response.setContentType("text/html;charset=UTF-8");

        if (USERNAME.equals(login) && PASSWORD.equals(pwd)){

            // Crear cookie
            Cookie loginCookie = new Cookie("username",login);
            response.addCookie(loginCookie);

            response.sendRedirect("cookie.html");

        }
        else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

        }
 
    }
}