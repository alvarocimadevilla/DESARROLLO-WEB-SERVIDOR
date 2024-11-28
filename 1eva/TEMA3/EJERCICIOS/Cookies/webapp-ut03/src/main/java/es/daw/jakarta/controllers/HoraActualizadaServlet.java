package es.daw.jakarta.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hora-actualizada")
public class HoraActualizadaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setHeader("refresh", "1");

        LocalTime hora = LocalTime.now();

        System.out.println("****>>>>>>>>>> "+hora);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");

       response.setContentType("text/html;charset=UTF-8");
       try(PrintWriter out = response.getWriter()){
           out.println("<!DOCTYPE html>");
           out.println("<html>");
           out.println("<head>");
           out.println("<title>RELOJ</title>");
           out.println("</title>");
           out.println("   <body>");
           out.println("       <h1>Hora actualizada!</h1>");
        
           out.println("<h2>Hora sin formatear:"+hora+"</h2>");

           out.println("<h2>Hora formateada:"+hora.format(df)+"</h2>");

           out.println("   </body>");
           out.println("</html>");
       }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: implement POST request handling
        doGet(request, response);
    }
}
