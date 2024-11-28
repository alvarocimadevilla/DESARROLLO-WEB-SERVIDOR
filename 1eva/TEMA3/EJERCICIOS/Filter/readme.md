# Ejemplo de un Filter

Ejemplo básico de un filtro de autenticación, que verifica un token antes de permitir el acceso:

```
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/api/protegido/*")
public class FiltroAutenticacion implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Código de inicialización si es necesario
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // Verificación simple de autenticación mediante un token
        String token = httpRequest.getHeader("Authorization");

        if (token != null && token.equals("token_valido")) {
            // Continuar con la cadena de filtros
            chain.doFilter(request, response);
        } else {
            // Bloquear acceso si no está autorizado
            response.getWriter().write("Acceso no autorizado");
        }
    }

    @Override
    public void destroy() {
        // Código de limpieza si es necesario
    }
}

```

El objetivo de este filtro es proteger las rutas que empiecen con /api/protegido/* y asegurar que solo los usuarios con un token válido puedan acceder a ellas.

La anotación **@WebFilter** especifica que esta clase es un filtro web y que debe aplicarse a todas las URLs que comiencen con /api/protegido/. Esto significa que cualquier solicitud HTTP dirigida a un endpoint en esta ruta será interceptada por este filtro.

La clase **FiltroAutenticacion** implementa la interfaz Filter, lo cual requiere que se definan tres métodos: init, doFilter y destroy. Estos métodos representan el ciclo de vida de un filtro en la especificación de servlets.

El filtro obtiene el token de autenticación desde el **encabezado Authorization** de la solicitud HTTP. En aplicaciones reales, este encabezado suele contener un token JWT o una clave de acceso.

La llamada a **chain.doFilter(request, response)** permite que la solicitud siga su curso hacia el siguiente filtro o recurso (como un servlet o controlador), ya que el usuario está autenticado, por ejemplo se ejecutaría un servlet mapeado a /api/protegido/listar-productos (causante en su llamada de la ejecución del filtro).

# Ejercicio 1: filtro que valida la sesión de usuario o login

Vamos a crear un nuevo paquete **filters**.

Creamos un nuevo filtro llamado **LoginFiltro**. Será una clase que implemente la interface Filter.

De los tres métodos de la interface, el init y destroy no es obligatorio implementarlos porque son default. Solo es obligatorio doFilter porque es el método abstracto. El ciclo de vida de un filtro es similar al de un servlet. Observa la documentación:

![image](https://github.com/user-attachments/assets/62e99323-5173-47b5-abfc-17ad5b6472e7)

Diseñamos el filtro para que se dispare ante las URL /carro/* /producto/*

```
@WebFilter({"/carro/*", "/productos/*"})
```

En el método doFilter hacemos la comprobación de si el usuario ha hecho login en nuestra aplicación:

```
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        if (username.isPresent()) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos no estas autorizado para ingresar a esta pagina!");
        }
    }
```

Haz las pruebas oportunas para comprobar que no podemos mostrar productos ni ver el carro de la compra si no hemos hecho login.

Podemos agregar todas las rutas (funcionalidades) de mi aplicación que requieren sesión.



