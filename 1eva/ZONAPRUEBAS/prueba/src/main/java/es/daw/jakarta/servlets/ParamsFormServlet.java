package es.daw.jakarta.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/registro")
public class ParamsFormServlet extends HttpServlet {
    /*
     * El campo serialVersionUID es una identificación única para la clase serializable.
     * Evita conflictos.
     */
    private static final long serialVersionUID = 1L;    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("**********> doGet!!!!");

        // 1. LECTURA DE PARÁMETROS DEL REQUEST.
        /*
         * \s ==> cualquier carácter de espacio en blanco incluyendo (\t, \n, \r)
         * Si ponemos \s simplemente, dará error por secuencia de escape inválida. 
         * En java la barra \ se usa para \n \t ....
         * Por eso hay que escapar \s
         * 
         */
        // Si no va relleno llegará cadena vacía
        // Cuidadín con los espacios en blanco!!!!
        String username = req.getParameter("username").replaceAll("\\s", ""); 
        //String username2 = req.getParameter("username").replace(" ", "");

        System.out.println("[ParamsFormServlet] username <"+username+">");
        String password = req.getParameter("password");
        String email = req.getParameter("email").replaceAll("\\s", "");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        if (lenguajes != null){
            System.out.println("[ParamsFormServlet] lenguajes 1:"+ Arrays.toString(lenguajes));
            System.out.println("[ParamsFormServlet] lenguajes 2:"+ lenguajes.toString());
            System.out.println("[ParamsFormServlet] lenguajes 3:"+ lenguajes);
        }
        String[] roles = req.getParameterValues("roles");

        String idioma = req.getParameter("idioma"); // si no hay ningún radio marcado, vendrá como nulo

        String habilitar = req.getParameter("habilitar");

        boolean enabled = habilitar != null && habilitar.equals("on");

        System.out.println("[ParamsFormServlet] habilitar:"+ habilitar);

        String secreto = req.getParameter("secreto");
        System.out.println("[ParamsFormServlet] secreto:"+ secreto);


        // DECLARACIONES DE VARIABLES...
        /*
         * Generalmente una mejor práctica. Principios de diseño orientado a interfaces
         * Tu código dependerá de la interfaz (List) en lugar de la implementación concreta (ArrayList). 
         * Esto facilita cambiar la implementación a otra clase que implemente la interfaz List 
         * (como LinkedList, CopyOnWriteArrayList, etc.) sin necesidad de modificar mucho el código.
         * Solo puedes utilizar los métodos definidos en la interfaz List. 
         * Si necesitas acceder a métodos específicos de ArrayList, tendrías que hacer un casting.
         */
        /*
         * ArrayList si necesitas acceso rápido por índice o si tienes pocas modificaciones en la lista.
         * LinkedList si haces muchas inserciones o eliminaciones en el medio de la lista o en los extremos, o si tu lista cambia de tamaño con mucha frecuencia.
         */
        ArrayList<String> errores = new ArrayList<>();
        //List<String> errores2 = new ArrayList<>();
        //List<String> errores3 = new List<>(); // Error no puedo crear objetos de una interface

        // 2. COMPROBACIONES PARA GENERAR ERRORES
        if (username.isEmpty())
            errores.add("El username es obligatorio!");
        if (password.isEmpty())
            errores.add("El password no puede ser vacío!");
        // Comprobamos simplemente que el email tenga @
        if (email.isEmpty() || !email.contains("@"))
            errores.add("El email es obligatorio y debe tener un formato correcto (una @)");
        if (idioma == null)
            errores.add("Debe seleccionar un idioma!");
        

        // 3. GENERAR LA PÁGINA HTML RESPUESTA
        //response.getWriter().append("Served at: ").append(request.getContextPath());

        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ParamsFormServlet</title>");
            out.println("</title>");
            out.println("   <body>");
            out.println("       <h1>Informe de datos recibidos del form</h1>");
            out.println("       <ul>");

            if (!errores.isEmpty()){
                //FASE I
                // En el caso de que haya errores, pinto los errores en la página html resultado
                //    for (String error : errores) {
                //         out.printf("<li> %s </li>\n", error);
                //         //out.println("<li>"+error+"</li>");
                //    }
                
                errores.forEach( error ->  out.printf("<li> %s </li>\n", error));


                // errores.forEach( (error) -> {
                //     out.printf("<li> %s </li>\n", error);
                //     System.out.println("error:"+error);
                // } );


                // FASE II ------------ PARA EL LUNES 30 SEPTIEMBRE!!!!
                // req.setAttribute("errores", errores);
                // getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);  

                
                
            }else{
                // Informe de todos datos del formulario
                out.println("<li>Username:"+username+"</li>");

                StringBuilder sb = new StringBuilder();
                sb.append("<li>Password:").append(password).append("</li>")
                .append("<li>Email:").append(email).append("</li>")
                .append("<li>Lenguajes: <ul>");

                // for (String lenguaje : lenguajes) {
                //     sb.append("<li>"+lenguaje+"</li>");
                // }
                if (lenguajes != null)
                    Arrays.asList(lenguajes).forEach( lenguaje -> sb.append("<li>"+lenguaje+"</li>"));

                sb.append("</ul><li>Roles: <ul>");
                Arrays.asList(roles).forEach( role -> sb.append("<li>"+role+"</li>"));
                
                sb.append("</ul><li>Idioma:").append(idioma).append("</li>")
                .append("<li>Habilitado:").append(enabled)
                .append("<li>Secreto:").append(secreto).append("</li>");


                out.println(sb.toString());              


            }

            out.println("       </ul>");
            out.println("       <p><a href=\"index.jsp\">volver</a></p>");

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