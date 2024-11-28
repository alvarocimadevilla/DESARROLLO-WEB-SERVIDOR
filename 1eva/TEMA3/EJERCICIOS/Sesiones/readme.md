# Conceptos fundamentales de HttpSession con Java

## Servlet que introduce datos en una sesión

```
@WebServlet("/CrearSession")
public class CrearSession extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession misession = request.getSession(true);
        Producto miproducto = new Producto(1, "telefono", 300);
        misession.setAttribute("producto", miproducto);
        PrintWriter pw = response.getWriter();
        pw.println("<html><body>Producto en session</body></html>");
        pw.close();
    }
}
```

## Servlet que lee datos de la sesión

```
@WebServlet("/VerSession")
public class VerSession extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession misession = request.getSession();
        Producto miproducto = (Producto) misession.getAttribute("producto");
        PrintWriter pw = response.getWriter();
        pw.println("<html><body>" + miproducto.getId() + "," + miproducto.getConcepto() + "," + miproducto.getImporte() + "</body></html>");
        pw.close();
    }
}
```

El primer Servlet (CreaSession) se encarga de crear la session y almacenar un producto en ella. Mientras el segundo Servlet se encargará de acceder a los datos y mostrarlos por pantalla.

![image](https://github.com/user-attachments/assets/55a467ea-c5b8-4d09-a5e7-099728c10beb)

Hemos compartido información perteneciente al mismo usuario entre dos Servlets. 

No se debe abusar del almacenamiento de objetos en Session ya que al ser por cada usuario si tenemos muchos usuarios concurrentes estaremos obligando al servidor a utilizar mucha memoria para almacenarlos.

<i> Referencia: https://www.arquitecturajava.com/usando-java-session-en-aplicaciones-web/ </i>

# Ejercicio 1: sistema de login

## Pasos:
- Crear un nuevo proyecto para tener un entorno limpio llamado **webapp-session.**
    - Para ello vamos a copiar el proyecto webapp-ut03 y limpiamos todo menos lo relativo al ejercicio de login con cookies.
- Reutilizaremos los servlets **LoginCookieServlet y LogoutServlet** en los que cambiaremos el uso de cookies por HttpSession. Por tanto creamos **LoginSessionServlet y LogoutSessionServlet.**
    - En el **doPost de LoginSessionServlet** implementaremos el código para añadir a la sesión el username (login):
      
          ```
      
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
      
          ```
      
    - En el **doGet de LoginSessionServlet** implementaremos el código para devolver una página como la siguiente:

      <img src="https://github.com/user-attachments/assets/0c613f20-9036-4b0b-898a-82df3203a3e1" height="150px"/>

    - En el **doGet de LogoutSessionServlet** implementaremos el código para invalidar la sesión.

- Añadimos una implementación del servicio de login **LoginServiceSessionImpl**




# Ejercicio 2: carro de la compra

Vamos a partir de una página index.html muy sencilla con un menú para manejar mi "tienda":

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de la compra</title>
</head>
<body>
<h3>Manejo de cookies Http</h3>
<ul>
    <li><a href="productos">Mostrar productos html</a></li>
    <li><a href="login">Login</a></li>
    <li><a href="logout">Logout</a></li>
    <li><a href="carro/ver">ver carro</a></li>
</ul>

</body>
</html>
```

Para poder comprar debemos haber hecho login (iniciado sesión). 

Si no hemos hecho login, saldrá el listado de productos sin el precio.

Si has hecho login, te saldrá el listado de productos y una nueva opción de agrerar el producto al carro:

<img src="https://github.com/user-attachments/assets/a264de82-0341-4461-a207-ba8314c6212d" height="200px"/>

Si compramos nos saldrá el carro con el producto comprado y el total:

<img src="https://github.com/user-attachments/assets/848d9984-2b7b-40f9-972c-61a29a9c2048" height="200px"/>


## MODELO DE DATOS

### Clase ItemCarro
Debemos añadir la clase ItemCarro para gestionar la cantidad de productos de un producto concreto.

Esta clase estará compuesta de:

```
    private int cantidad;
    private Producto producto;
```

Crea el constructor, métodos getters and setters y añade el método de comportamiento para obtener el importe de la línea o item del producto:

```
    public int getImporte() {
        return cantidad * producto.getPrecio();
    }
```

### Clase Carro

Esta clase contendrá una lista de ItemCarro.

En el constructor se inicializa la lista y solo tendrá el método get para obtener la lista.

También tendrá los siguientes métodos de comportamiento:

#### addItemCarro

```
public void addItemCarro(ItemCarro itemCarro)
```

Hay que comprobar que solo puede haber un item del carro, porque lo que se modifica es la cantidad del mismo producto, no se añade otro item.

Vamos a partir de este código para meter la funcionalidad:

```
        if (items.contains(itemCarro)) {

                // ...
        } else {
            this.items.add(itemCarro);
        }
```

Tengo que controlar que el producto no esté repetido y voy a tener en cuenta el id y nombre como criterio para saber si dos productos son iguales.

#### getTotal

Para obtener el importe total del carro:

```
    public int getTotal() {
        // ....
    }
```
#### getItems()

Implementamos ese método getter para obtener la lista de items del carro.

## SERVICIOS: Añadir al ProductoService nuevos métodos

### buscarProductoPorId

```
public Optional<Producto> buscarPorId(Long id);
```

## NUEVOS CONTROLADORES (servlets)

### 1. Renombrado LoginSessionServlet por LoginServlet

Por tanto hay que modificar el action de la página login.html.

Este servlet en el metodo post gestionará el login y pwd del usuario.

En el método get comprobará si el usuario ha hecho sesión. Si es así mostrará una página de bienvenido y si no, devolverá la página login.html para que se registre.

![image](https://github.com/user-attachments/assets/78d626d5-6ff7-4534-9402-6002f5e19cf2)


### 2. Creamos AgregarCarroServlet

Con este url-mapping:
```
@WebServlet("/carro/agregar")
```

En el método doGet recogeremos el parámetro id.

Buscaremos el producto por id para que si existe agregar el item del carro.

Controlaremos si ya está el "carro" en la sesión.

Finalmente redirigimos al servlet encargado de ver el carro.

### 3. Renombrado ProductoXslServlet por ProductoServlet

Limpiamos todo la lógica de exportación a XSL.

Este servlet se va a dedicar a generar la tabla HTML con la lista de productos.

Debemos añadir una nueva columna en la tabla, la relativa a agregar el producto al carro.

Observamos el código y para ello, vemos que es necesario editar la página **productos.jsp**.

### 4. Ver carro compra: creamos VerCarroServlet

Es un servlet muy sencillo que "redirige" a una página JSP que mostrará el carro de la compra:

```
@WebServlet("/carro/ver")
public class VerCarroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/carro.jsp").forward(request, response);

    }


}

```

# Ejercicio 3: ampliar carro de la compra con actualizar y eliminar items del carro de compras

Como funcionalidad extra del carro de compras se pide que se pueda actualizar la cantidad y eliminar los ítems:

![image](https://github.com/user-attachments/assets/3bc246cc-5db2-4114-a43f-6c8715dd9c01)


Actualizar la cantidad mediante un campo cantidad en cada linea del carro y eliminar mediante checkboxes seleccionables por cada ítems del carro.

**Para eso se necesita:**
- Un formulario en la vista carro.jsp.
- Un nuevo servlet llamado ActualizarCarroServlet para actualizar los ítems del carro (/carro/actualizar).
- Un par de métodos en la clase Carro para eliminar y actualizar productos.

