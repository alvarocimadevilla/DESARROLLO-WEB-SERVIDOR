# Ejemplos

## Ejemplo de un sencillo Listener de sesión

```
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MiSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("Nueva sesión creada: " + event.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Sesión destruida: " + event.getSession().getId());
    }
}

```

## Ejemplo de listener contexto de inicialización de Firebase 

clase de inicialización que carga el archivo de configuración de Firebase. 

Esta clase se puede inicializar al inicio de la aplicación usando un ServletContextListener.

```
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            FileInputStream serviceAccount = new FileInputStream("path/to/firebase-adminsdk.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Método opcional para limpiar recursos, si fuera necesario
    }
}

```

# Ejercicio 1: tracear en el fichero de log los métodos de Listener

Vamos a crear un **nuevo paquete listeners.**

Creamos la clase **AppListener** que implemente **ServletContextListener**, **ServletRequestListener** y **HttpSessionListener**, por tanto usaremos el mismo listener para esos tres ámbitos.

Vamos a copiar este código y ejecutarlo para ver como funciona el listener:

```
@WebListener
public class AppListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();

        servletContext.log("******** inicializando la aplicacion!");
        
        //servletContext.setAttribute("mensaje", "algun valor global de la app!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("******** destruyendo la aplicacion!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("******** inicializando el request!");
        //sre.getServletRequest().setAttribute("mensaje", "guardando algun valor para el request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("******** destruyendo el request!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("******** inicializando la sesion http");

        // En vez de crear el carro en el servlet AgregarCarro
        // Carro carro = new Carro();
        // HttpSession session = se.getSession();
        // session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("******** destruyendo la sesion http");
    }
}

```

El fichero de log del servidor de aplicaciones, en nuestro caso Wildfly integrado en Visual Studio Code, podemos encontrarlo editando la configuración del servidor:

![image](https://github.com/user-attachments/assets/bd0cd47e-021f-4e05-ba31-afa723ba4341)


# Ejercicio 2: ampliar el carrito de la compra con listeners

En AppListener vamos a descomentar el código que crea atributos de contexto y request y que al crear la sesión se inicializa el carro.

Modifica el código oportuno en AgregarCarroServlet para que usar la instancia del carro del listener.

Por otro lado, muestra en la vista de los productos del carro, la información de los dos atributos "mensaje":

<img src="https://github.com/user-attachments/assets/48400e12-0d24-489a-a888-757923071b90" height="200px"/>
