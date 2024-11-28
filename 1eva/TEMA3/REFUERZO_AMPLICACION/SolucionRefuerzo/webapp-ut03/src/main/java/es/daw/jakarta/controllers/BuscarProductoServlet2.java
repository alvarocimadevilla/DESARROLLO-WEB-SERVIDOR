package es.daw.jakarta.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import es.daw.jakarta.models.Producto;
import es.daw.jakarta.services.*;

@WebServlet("/buscar-producto2")
public class BuscarProductoServlet2 extends HttpServlet {
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
        nombre_producto = nombre_producto.trim(); // si no viaja el nombre_producto será null y dará nullpointerexception

        // 2. lógica...
        if (nombre_producto == null || nombre_producto.isBlank())
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encuentra el producto porque no has puesto el nombre!!!!! \nNo se permiten espacios en blanco");

        // 3. Buscar el producto....
        ProductService service = new ProductServiceImpl();
        //Optional<Producto> encontrado = service.buscarProducto(nombre_producto);
        Producto producto = service.buscarProducto2(nombre_producto);

        // 4. Respuesta del servlet
        response.setContentType("text/html;charset=UTF-8");
        //if (encontrado.isPresent()) {
        if (producto != null) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Producto encontrado</title>");
                out.println("</title>");
                out.println("   <body>");
                out.println("       <h1>Producto econtrado:</h1>");
                out.println("<ul>");
                out.println("<li>id :" + producto.getId() + "</li>");
                out.println("<li>nombre :" + producto.getNombre() + "</li>");
                out.println("<li>tipo :" + producto.getTipo() + "</li>");
                out.println("<li>precio :" + producto.getPrecio() + "</li>");
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