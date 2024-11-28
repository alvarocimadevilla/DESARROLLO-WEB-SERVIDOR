package es.daw.jakarta.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registro4")
public class ParamsFormServlet4 extends HttpServlet {
    // serialVersionUID se usa para verificar la versión de la clase en la serialización
    private static final long serialVersionUID = 1L;    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Lectura de parámetros del request
        // Recolectamos y limpiamos los parámetros recibidos del formulario
        String username = req.getParameter("username").replaceAll("\\s", ""); // Elimina espacios en blanco
        String password = req.getParameter("password");
        String email = req.getParameter("email").replaceAll("\\s", ""); // Elimina espacios en blanco
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma"); // nulo si no hay ninguno seleccionado
        String habilitar = req.getParameter("habilitar");
        boolean enabled = habilitar != null && habilitar.equals("on"); // Determina si está activado
        String secreto = req.getParameter("secreto");

        // 2. Validaciones de campos y generación de mensajes de error
        Map<String, String> errores = new HashMap<>();

        // Verifica si 'username' está vacío
        if (username.isEmpty()) {
            errores.put("username", "El username es obligatorio!");
        }

        // Verifica si 'password' está vacío
        if (password.isEmpty()) {
            errores.put("password", "El password no puede ser vacío!");
        }

        // Verifica si 'email' está vacío o no contiene '@'
        if (email.isEmpty() || !email.contains("@")) {
            errores.put("email", "El email es obligatorio y debe tener un formato correcto (una @)");
        }

        // Verifica si no se seleccionó ningún idioma
        if (idioma == null) {
            errores.put("idioma", "Debe seleccionar un idioma!");
        }

        // Verifica si no se seleccionó ningún rol
        if (roles == null || roles.length == 0) {
            errores.put("roles", "Debe seleccionar al menos un rol!");
        }

        // Verifica si no se seleccionó ningún lenguaje
        if (lenguajes == null || lenguajes.length == 0) {
            errores.put("lenguajes", "Debe seleccionar al menos un lenguaje!");
        }

        // 3. Generar respuesta HTML
        resp.setContentType("text/html");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ParamsFormServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Informe de datos recibidos del formulario</h1>");
            out.println("<ul>");

            // Si existen errores, se redirige a una página de error
            if (!errores.isEmpty()) {
                req.setAttribute("errores", errores);
                getServletContext().getRequestDispatcher("/index4.jsp").forward(req, resp);  
            } else {
                // Si no hay errores, se muestra la información recibida
                out.println("<li>Username: " + username + "</li>");

                // Muestra los valores restantes del formulario en la respuesta
                StringBuilder sb = new StringBuilder();
                sb.append("<li>Password: ").append(password).append("</li>")
                  .append("<li>Email: ").append(email).append("</li>")
                  .append("<li>Lenguajes: <ul>");

                // Lista de lenguajes seleccionados
                if (lenguajes != null) {
                    Arrays.asList(lenguajes).forEach(lenguaje -> sb.append("<li>" + lenguaje + "</li>"));
                }

                sb.append("</ul><li>Roles: <ul>");
                Arrays.asList(roles).forEach(role -> sb.append("<li>" + role + "</li>"));

                sb.append("</ul><li>Idioma: ").append(idioma).append("</li>")
                  .append("<li>Habilitado: ").append(enabled).append("</li>")
                  .append("<li>Secreto: ").append(secreto).append("</li>");

                out.println(sb.toString());              
            }

            out.println("</ul>");
            out.println("<p><a href=\"index.jsp\">Volver</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Llama a doGet para manejar la solicitud POST de la misma manera
        doGet(request, response);
    }
}
