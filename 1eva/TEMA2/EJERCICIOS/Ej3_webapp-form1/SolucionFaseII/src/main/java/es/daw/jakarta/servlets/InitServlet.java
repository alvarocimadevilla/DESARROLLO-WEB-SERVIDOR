package es.daw.jakarta.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    urlPatterns = {"/init", "/init-servlet"},
    name = "inicio",
    initParams =
    {
        @WebInitParam(name = "peticiones", value = "5"),
        @WebInitParam(name = "saveDir", value = "D:/FileUpload"),
        @WebInitParam(name = "allowedTypes", value = "jpg,jpeg,gif,png")
    }
)
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Declaración global
    private int peticiones;

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        //super.init();

        // Valor inicial a 5
        //peticiones = 5;
        String peticiones_param = getInitParameter("peticiones");
        
        // // Elimina todos los espacios, pero no los tabuladores ni retornos
        // peticiones_param = peticiones_param.replace(" ",""));        
        // Elimina espacios, tabuladores, retornos
        peticiones_param = peticiones_param.replaceAll("\\s","");


        if (peticiones_param != null && !peticiones_param.isEmpty())
            peticiones = Integer.parseInt(peticiones_param);

        System.out.println("***********************************");
        System.out.println("[InitServlet] peticiones inicializado a "+peticiones);
        System.out.println("***********************************");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       peticiones++;
       System.out.println("nueva petición...");

       response.setContentType("text/html");
       try(PrintWriter out = response.getWriter()){
           out.println("<!DOCTYPE html>");
           out.println("<html>");
           out.println("<head>");
           out.println("<title>Init Servlet</title>");
           out.println("</title>");
           out.println("   <body>");
           out.println("       <h1> peticiones:"+peticiones+"</h1>");
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

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        //super.destroy();
        System.out.println("ME MUEROOOOOOOOOOOOO!");
    }    


}
