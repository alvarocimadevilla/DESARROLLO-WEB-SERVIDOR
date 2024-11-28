package es.daw.jakarta.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/url-get")
public class ParametersGetServlet extends HttpServlet {
    
    //????
    private static final long serialVersionUID = 1L;

    private static final int LIMIT = 100;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // 1. LEER LOS PARÁMETROS DEL REQUEST
        String saludo = request.getParameter("saludo"); //no tiene que llamarse la variable igual que el parámetro
        String nombre = request.getParameter("nombre");
        String codigo = request.getParameter("codigo");

        // 2. CREO VARIABLES...
        String message;

        // 3. LÓGICA ...
        // Si no control el null me dará una excepción
        // if (Integer.parseInt(codigo) > LIMIT)
        //         message = "Has superado el límte establecido a "+LIMIT;

        // FORMA 1: PARA CONTROLAR EL ERROR NUMBER FORMAT EXCEPCION
        // operador ternario
        // if (codigo != null)
        //     message = (Integer.parseInt(codigo) > LIMIT)? "Has superado el límte establecido a "+LIMIT:"";
        // else
        //     message = "(forma 1) El código no puede ser nulo";

        // FORMA 1: PARA CONTROLAR EL ERROR NUMBER FORMAT EXCEPCION
        try {
            message = (Integer.parseInt(codigo) > LIMIT)? "Has superado el límte establecido a "+LIMIT:"";    
        } catch (NumberFormatException e) {
            message = "(forma 2) El código no puede ser nulo";
        }
        


        // 4. RESPUESTA
        // Respuesta no html
        //response.getWriter().append("Served at: ").append(request.getContextPath());

        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ParamsGetServlet</title>");
            out.println("</title>");
            out.println("   <body>");
            out.println("       <h1>ParamsGetServlet!!!!!!!</h1>");

            if (!message.isEmpty()){
            //if (!message.equals("")){
                out.println("<h2>"+message+"</h2>");
            }else{
                // forma 1
                //out.println("<h2>El saludo enviado es:"+saludo+" "+nombre+" con código "+codigo+"</h2>");

                // forma 2
                message = "<h2>El saludo enviado es: %s %s con código %s</h2>";
                //String.format(message, saludo, nombre,codigo);
                out.format(message, saludo, nombre,codigo);

                // forma 3
                //StringBuilder sb = new StringBuilder();
                //sb.append("<h2>El saludo enviado es: ").append(saludo).append(" ").append(nombre)....
            }

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