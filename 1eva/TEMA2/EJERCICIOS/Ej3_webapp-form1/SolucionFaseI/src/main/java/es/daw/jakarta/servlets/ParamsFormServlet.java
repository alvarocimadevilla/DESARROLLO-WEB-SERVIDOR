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

@WebServlet("/registro") // Define la ruta para acceder al servlet.
public class ParamsFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // ID único para el servlet.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. LECTURA DE PARÁMETROS DEL REQUEST
        String username = req.getParameter("username").replaceAll("\\s", ""); // Lee el 'username' y elimina espacios en blanco.
        String password = req.getParameter("password"); // Lee el 'password'.
        String email = req.getParameter("email").replaceAll("\\s", ""); // Lee el 'email' y elimina espacios en blanco.
        String pais = req.getParameter("pais"); // Lee el 'pais'.
        String[] lenguajes = req.getParameterValues("lenguajes"); // Obtiene los lenguajes seleccionados.
        String[] roles = req.getParameterValues("roles"); // Obtiene los roles seleccionados.
        String idioma = req.getParameter("idioma"); // Lee el idioma.
        String habilitar = req.getParameter("habilitar"); // Lee el valor de la casilla de verificación 'habilitar'.

        boolean enabled = habilitar != null && habilitar.equals("on"); // Verifica si 'habilitar' está marcado.
        String secreto = req.getParameter("secreto"); // Lee el parámetro oculto 'secreto'.

        // Lista de errores de validación.
        ArrayList<String> errores = new ArrayList<>();

        // 2. VALIDACIÓN DE PARÁMETROS
        if (username.isEmpty()) errores.add("El username es obligatorio!");
        if (password.isEmpty()) errores.add("El password no puede ser vacío!");
        if (email.isEmpty() || !email.contains("@")) errores.add("El email es obligatorio y debe tener un formato correcto (una @)");
        if (idioma == null) errores.add("Debe seleccionar un idioma!");

        // 3. GENERACIÓN DE RESPUESTA HTML
        response.setContentType("text/html"); // Define el tipo de contenido de la respuesta como HTML.

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ParamsFormServlet</title>");
            out.println("</title>");
            out.println("<body>");
            out.println("<h1>Informe de datos recibidos del form</h1>");
            out.println("<ul>");

            if (!errores.isEmpty()) {
                // Muestra los errores de validación.
                errores.forEach(error -> out.printf("<li> %s </li>\n", error));
            } else {
                // Muestra los datos del formulario.
                out.println("<li>Username:" + username + "</li>");

                StringBuilder sb = new StringBuilder();
                sb.append("<li>Password:").append(password).append("</li>")
                  .append("<li>Email:").append(email).append("</li>")
                  .append("<li>Lenguajes: <ul>");

                // Añade cada lenguaje seleccionado.
                if (lenguajes != null)
                    Arrays.asList(lenguajes).forEach(lenguaje -> sb.append("<li>" + lenguaje + "</li>"));

                sb.append("</ul><li>Roles: <ul>");
                
                // Añade cada rol seleccionado.
                Arrays.asList(roles).forEach(role -> sb.append("<li>" + role + "</li>"));

                sb.append("</ul><li>Idioma:").append(idioma).append("</li>")
                  .append("<li>Habilitado:").append(enabled).append("</li>")
                  .append("<li>Secreto:").append(secreto).append("</li>");

                out.println(sb.toString()); // Añade el HTML construido.
            }

            out.println("</ul>");
            out.println("<p><a href=\"index.jsp\">volver</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige las peticiones POST a doGet.
        doGet(request, response);
    }
}
