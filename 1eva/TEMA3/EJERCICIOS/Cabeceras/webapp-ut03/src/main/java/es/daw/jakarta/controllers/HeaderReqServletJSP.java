package es.daw.jakarta.controllers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cabeceras-request-jsp")
public class HeaderReqServletJSP extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        // Obtiene el método HTTP utilizado en la solicitud (GET, POST, etc.).
        String metodoHttp = req.getMethod();
        req.setAttribute("metodoHttp", metodoHttp);
            
        // Obtiene la URI de la solicitud.
        String requestUri = req.getRequestURI();
        req.setAttribute("requestUri", requestUri);

        // Obtiene la URL completa de la solicitud.
        String requestUrl = req.getRequestURL().toString();
        req.setAttribute("requestUrl", requestUrl);

        // Obtiene el contexto de la aplicación donde se ejecuta el servlet.
        String contexPath = req.getContextPath();
        // pendiente meter el resto de cabeceras en el req como atributos....

        // Obtiene la ruta del servlet.
        String servletPath = req.getServletPath();

        // Obtiene la dirección IP del cliente que realiza la solicitud.
        String ipCliente = req.getRemoteAddr();

        // Obtiene la dirección IP local donde se ejecuta el servlet.
        String ip = req.getLocalAddr();

        // Obtiene el puerto local en el que está escuchando el servidor.
        int port = req.getLocalPort();

        // Obtiene el esquema de la solicitud (http o https).
        String scheme = req.getScheme();

        // Obtiene el valor de la cabecera "host" de la solicitud.
        String host = req.getHeader("host");

        // Construye la URL completa utilizando el esquema, el host, el contexto y la ruta del servlet.
        String url = scheme + "://" + host + contexPath + servletPath;

        // Construye una URL alternativa utilizando la IP local, el puerto, el contexto y la ruta del servlet.
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath;

        Enumeration<String> headerNames = req.getHeaderNames();

        Map<String,String> headers = new HashMap<>();

        // Modificar este código para cargar el map headers con cabecera-valor
        while (headerNames.hasMoreElements()) {
            String nombreCabecera = headerNames.nextElement();
            headers.put(nombreCabecera,req.getHeader(nombreCabecera));
        }

        req.setAttribute("headersMap", headers);

        //getServletContext().getRequestDispatcher("/informe.jsp").forward(req, resp);
        req.getRequestDispatcher("/informe.jsp").forward(req, resp);
        



    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: implement POST request handling
        doGet(request, response);
    }
}