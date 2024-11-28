package es.daw.jakarta.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {
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

        Producto producto = mapper.readValue(jsonStream,Producto.class);

        System.out.println("********** PRODUCTO ************");
        System.out.println(producto);
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
            out.println("        <ul>");
            out.println("           <li>Id: "+producto.getId()+"</li>");
            out.println("           <li>Nombre: "+producto.getNombre()+"</li>");
            out.println("           <li>Tipo: "+producto.getTipo()+"</li>");
            out.println("           <li>Precio: "+producto.getPrecio()+"</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
        
    }
}
