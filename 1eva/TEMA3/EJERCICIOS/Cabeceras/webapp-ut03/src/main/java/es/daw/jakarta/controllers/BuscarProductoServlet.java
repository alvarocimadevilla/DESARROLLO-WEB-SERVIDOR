package es.daw.jakarta.controllers;

// Importa las clases necesarias para manejar excepciones de entrada/salida y flujos de salida de texto
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;  // Importa Optional para manejar valores opcionales

// Importa las clases necesarias para trabajar con servlets en Jakarta EE
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importa la clase Producto y el servicio que buscará el producto
import es.daw.jakarta.models.Producto;
import es.daw.jakarta.services.*;

// Declara que esta clase es un servlet y especifica la URL en la que responderá
@WebServlet("/buscar-producto")
public class BuscarProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // ID de versión del servlet para serialización

    // Método doGet que respondería a solicitudes GET, actualmente vacío
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // No implementado; no realiza ninguna acción
    }

    // Método doPost para manejar solicitudes POST al buscar el producto
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recoge el parámetro "nombre_producto" de la solicitud (request)
        String nombre_producto = request.getParameter("nombre_producto");
        nombre_producto = nombre_producto.trim();  // Elimina espacios en blanco alrededor del nombre

        // 2. Lógica de validación:
        // Si el nombre del producto es nulo o está vacío después de eliminar los espacios
        if (nombre_producto == null || nombre_producto.isBlank()) {
            // Envía un error 404 si el nombre es inválido, con un mensaje de error específico
            response.sendError(HttpServletResponse.SC_NOT_FOUND, 
                "No se encuentra el producto porque no has puesto el nombre!!!!! \nNo se permiten espacios en blanco");
        }

        // 3. Buscar el producto:
        ProductService service = new ProductServiceImpl();  // Crea una instancia del servicio de producto
        Optional<Producto> encontrado = service.buscarProducto(nombre_producto);  // Busca el producto por nombre

        // Imprime en consola si el producto fue encontrado o no (para depuración)
        System.out.println("*******> optional empty:" + encontrado.isEmpty());
        System.out.println("*******> optional present:" + encontrado.isPresent());

        // 4. Genera la respuesta del servlet:
        response.setContentType("text/html;charset=UTF-8");  // Establece el tipo de contenido de la respuesta como HTML y UTF-8
        if (encontrado.isPresent()) {  // Si el producto fue encontrado
            try (PrintWriter out = response.getWriter()) {  // Obtiene el escritor para la respuesta
                // Escribe la respuesta HTML al cliente
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Producto encontrado</title>");  // Título de la página
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Producto encontrado:</h1>");  // Encabezado
                out.println("<ul>");  // Lista no ordenada para los detalles del producto
                out.println("<li>id :" + encontrado.get().getId() + "</li>");  // Muestra el ID del producto
                out.println("<li>nombre :" + encontrado.get().getNombre() + "</li>");  // Muestra el nombre del producto
                out.println("<li>tipo :" + encontrado.get().getTipo() + "</li>");  // Muestra el tipo de producto
                out.println("<li>precio :" + encontrado.get().getPrecio() + "</li>");  // Muestra el precio del producto
                out.println("</ul>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            // Si el producto no fue encontrado, envía un error 404 con un mensaje específico
            response.sendError(HttpServletResponse.SC_NOT_FOUND, 
                "No se encuentra el producto " + nombre_producto);
        }
    }
}
