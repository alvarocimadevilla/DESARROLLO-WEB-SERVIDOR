package es.daw.jakarta.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import es.daw.jakarta.services.ProductServiceImpl;
import es.daw.jakarta.models.Producto;
import es.daw.jakarta.services.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/copia-lista-profunda"})
public class CopiaListaProfundaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Leer parámetros del request
        String nuevoNombre = request.getParameter("nombre");
        int posicion = Integer.parseInt(request.getParameter("posicion"));
        
        // Obtener un objeto del servicio
        ProductService service = new ProductServiceImpl();

        // Obtener la lista de productos
        List<Producto> productos = service.listar();

        // Realizo una copia profunda de la lista
        // Tengo que copiar cada uno de los objetos que contiene la lista original
        List<Producto> productosDeep = new ArrayList<>();
        productos.forEach(p -> productosDeep.add(new Producto(p.getId(), p.getNombre(), p.getTipo(), p.getPrecio())));
        
        // Modifico el nombre del producto en la posición especificada
        productosDeep.get(posicion).setNombre(nuevoNombre);
        System.out.println("**************************");
        System.out.println("Modificado producto con posición "+posicion+" con el nuevo nombre "+nuevoNombre);
        System.out.println(productos.get(posicion));

        System.out.println("**************************");
        productosDeep.forEach(System.out::println);
        System.out.println("**************************");

        // Página respuest: generar tabla HTML a partir de una JSP
        request.setAttribute("productos", productosDeep);
        request.getRequestDispatcher("/productos.jsp").forward(request, response);
                
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
