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
    urlPatterns = {"/init", "/init-servlet"}, // Define las rutas (URL) para acceder al servlet.
    name = "inicio", // Nombre del servlet, que se puede usar para identificarlo.
    initParams = {
        @WebInitParam(name = "peticiones", value = "5"), // Parámetro de inicialización: cantidad inicial de peticiones.
        @WebInitParam(name = "saveDir", value = "D:/FileUpload"), // Parámetro: ubicación de guardado de archivos.
        @WebInitParam(name = "allowedTypes", value = "jpg,jpeg,gif,png") // Parámetro: tipos de archivos permitidos.
    }
)
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // ID único para la versión del servlet.

    private int peticiones; // Variable para almacenar el número de peticiones.

    @Override
    public void init() throws ServletException {
        // Este método se ejecuta una vez cuando el servlet se inicializa.

        String peticiones_param = getInitParameter("peticiones"); // Obtiene el valor del parámetro de inicialización 'peticiones'.
        peticiones_param = peticiones_param.replaceAll("\\s", ""); // Elimina espacios en blanco para asegurar que solo contiene números.

        if (peticiones_param != null && !peticiones_param.isEmpty()) {
            peticiones = Integer.parseInt(peticiones_param); // Convierte el parámetro a entero y lo asigna a 'peticiones'.
        }

        // Imprime el valor inicial de 'peticiones' en la consola.
        System.out.println("***********************************");
        System.out.println("[InitServlet] peticiones inicializado a " + peticiones);
        System.out.println("***********************************");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        peticiones++; // Incrementa el contador de peticiones.
        System.out.println("nueva petición..."); // Imprime en consola cada vez que se realiza una petición.

        response.setContentType("text/html"); // Establece el tipo de contenido de la respuesta como HTML.

        try (PrintWriter out = response.getWriter()) {
            // Genera la respuesta en formato HTML y muestra el número de peticiones.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Init Servlet</title>");
            out.println("</title>");
            out.println("<body>");
            out.println("<h1> peticiones:" + peticiones + "</h1>"); // Muestra el contador de peticiones.
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Llama a doGet para que las peticiones POST se manejen igual que las GET.
        doGet(request, response);
    }

    @Override
    public void destroy() {
        // Este método se ejecuta cuando el servlet se destruye. Aquí solo imprime un mensaje en la consola.
        System.out.println("ME MUEROOOOOOOOOOOOO!");
    }
}
