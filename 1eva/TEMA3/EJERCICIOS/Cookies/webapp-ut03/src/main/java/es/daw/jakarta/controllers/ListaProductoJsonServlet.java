package es.daw.jakarta.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.daw.jakarta.services.ProductServiceImpl;
import es.daw.jakarta.models.Producto;
import es.daw.jakarta.services.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/lista-productos.json")
public class ListaProductoJsonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductService service = new ProductServiceImpl();
        List<Producto> productos = service.listar();

        // Jackson. Necesito el objeto que mapee objetos a json
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(productos);

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(json);
                
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletInputStream jsonStream = request.getInputStream();

        ObjectMapper mapper = new ObjectMapper();

        //Producto producto = mapper.readValue(jsonStream,Producto.class);
        List<Producto> productos = mapper.readValue(jsonStream, new TypeReference<List<Producto>>() {});

        System.out.println("********** PRODUCTO ************");
        productos.forEach(System.out::println);
        System.out.println("********************************");


        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Json product</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Detalle de producto desde Json!</h1>");
            productos.forEach( p -> {
                out.println("<h2>Producto:</h2>");
                out.println("        <ul>");
                out.println("           <li>Id: "+p.getId()+"</li>");
                out.println("           <li>Nombre: "+p.getNombre()+"</li>");
                out.println("           <li>Tipo: "+p.getTipo()+"</li>");
                out.println("           <li>Precio: "+p.getPrecio()+"</li>");
                out.println("        </ul>");
            });
            out.println("    </body>");
            out.println("</html>");
        }
        
    }
}
