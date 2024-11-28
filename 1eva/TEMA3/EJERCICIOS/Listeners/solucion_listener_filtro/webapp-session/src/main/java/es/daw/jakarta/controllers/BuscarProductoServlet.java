package es.daw.jakarta.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import es.daw.jakarta.models.Producto;
import es.daw.jakarta.services.*;

@WebServlet("/buscar-producto")
public class BuscarProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: implement POST request handling
        // 1. recoger los parámetros del request
        String nombre_producto = request.getParameter("nombre_producto");
        nombre_producto = nombre_producto.trim();

        // 2. lógica...
        if (nombre_producto == null || nombre_producto.isBlank())
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encuentra el producto porque no has puesto el nombre!!!!! \nNo se permiten espacios en blanco");

        // 3. Buscar el producto....
        ProductService service = new ProductServiceImpl();
        Optional<Producto> encontrado = service.buscarProducto(nombre_producto);

        System.out.println("*******> optional empty:" + encontrado.isEmpty());
        System.out.println("*******> optional present:" + encontrado.isPresent());

        // 4. Respuesta del servlet
        response.setContentType("text/html;charset=UTF-8");
        if (encontrado.isPresent()) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Producto encontrado</title>");
                out.println("</title>");
                out.println("   <body>");
                out.println("       <h1>Producto econtrado:</h1>");
                out.println("<ul>");
                out.println("<li>id :" + encontrado.get().getId() + "</li>");
                out.println("<li>nombre :" + encontrado.get().getNombre() + "</li>");
                out.println("<li>tipo :" + encontrado.get().getTipo() + "</li>");
                out.println("<li>precio :" + encontrado.get().getPrecio() + "</li>");
                out.println("</ul>");
                out.println("   </body>");
                out.println("</html>");
            }
        }
        else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encuentra el producto "+nombre_producto);
        }
        
    }
}