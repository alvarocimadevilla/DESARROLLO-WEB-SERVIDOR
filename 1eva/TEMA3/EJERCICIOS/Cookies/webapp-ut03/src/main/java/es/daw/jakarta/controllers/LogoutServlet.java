package es.daw.jakarta.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import es.daw.jakarta.services.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import es.daw.jakarta.services.*;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Borrar la cookie

        LoginService auth = new LoginServiceImpl();
        Optional<String> username = auth.getUserName(request);

        if (username.isPresent()){
            Cookie usernameCookie = new Cookie("username","");
            usernameCookie.setMaxAge(0);
            response.addCookie(usernameCookie);
        }

       response.setContentType("text/html;charset=UTF-8");
       try(PrintWriter out = response.getWriter()){
           out.println("<!DOCTYPE html>");
           out.println("<html>");
           out.println("<head>");
           out.println("<title>Logout</title>");
           out.println("</title>");
           out.println("   <body>");
           out.println("       <h1>Te has deslogado (busca en la RAE)</h1>");
           out.println("        <a href='cookie.html'>Volver</a>");
           out.println("   </body>");
           out.println("</html>");
       }

       //response.sendRedirect("cookie.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //doGet(request, response);
    }
}