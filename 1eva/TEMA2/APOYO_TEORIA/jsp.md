# Jakarta Sever Pages. JSP

![image](https://github.com/user-attachments/assets/fd2fe72d-70bc-471f-91c2-828f78814445)

https://jakarta.ee/specifications/pages/

Las páginas JSP son archivos con la extensión jsp que contienen etiquetas HTML y XML junto con código Java incrustado.

Los archivos JSP se crean dentro de la carpeta webapp. 

Cuando el contenedor web recibe una petición HTTP hacia un JSP utiliza un motor JSP para convertir internamente el JSP a servlet y procesar la petición.

Las etiquetas JSP permiten utilizar código Java:

<img src="https://github.com/user-attachments/assets/df72882e-4714-4138-a8be-13c909901161" height="200px"/>



## 1. Directivas
Las directivas proporcionan información sobre el contenido de la página JSP y afectan el procesamiento de la página.

### Page Directive: Define atributos que afectan el comportamiento de la página completa.

```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
```

- language: El lenguaje utilizado, típicamente "java".
- contentType: El tipo de contenido de la respuesta, por ejemplo, "text/html".
- pageEncoding: La codificación de caracteres usada en la página.

### Include Directive: Incluye el contenido de un archivo en la página actual en tiempo de compilación.

```
<%@ include file="header.jsp" %>
```

### Taglib Directive: Declara bibliotecas de etiquetas personalizadas.

```
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

## 2. Scripting Elements
Son fragmentos de código Java que se insertan en una página JSP.

### Declarations: Definen variables y métodos que pueden ser usados en toda la página.

<%! 
  int add(int a, int b) {
    return a + b;
  }
%>

### Scriptlets: Código Java que se ejecuta en tiempo de solicitud.

```
<% 
  String name = "Mundo"; 
  out.println("Hola, " + name);
%>
```

No puedes declarar un método dentro de un scriptlet.

Los scriptlets contienen bloques de código Java que se ejecutan dentro del método service() de la página JSP, por lo que cualquier declaración dentro de ellos es local al cuerpo de ese método y no puedes definir un método de esa manera.

### Expressions: Expresiones Java que se evalúan y se insertan directamente en el flujo de salida.

```
<%= new java.util.Date() %>
```

## 3. Declaración de Objetos Implicitos: JSP proporciona objetos implícitos para interactuar con la solicitud, respuesta, sesión y contexto de aplicación:

Al igual que en los servlets desde JSP también es posible acceder a la petición request y otros objetos implícitos.

- **request:** Representa la solicitud del cliente.
- **response:** Representa la respuesta al cliente.
- **session:** Representa la sesión del usuario.
- **application:** Representa el contexto de la aplicación.
- **out:** Representa el objeto de escritura de la respuesta.
- **config:** Representa la configuración del servlet.
- **pageContext:** Proporciona un contexto de página más amplio.

## 4. Uso de JavaBeans

Un objeto JavaBean o bean es un objeto instanciado cuya clase cumple:
- Implementa la interfaz Serializable
- Tiene constructor vacío 
- Métodos getter y setter 

Se utilizan para conectar los controles de los formularios a los datos del bean.

![image](https://github.com/user-attachments/assets/c6cea6cb-656e-482c-a078-e5f2ea59c6b6)

### Malas prácticas con JavaBeans

```
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ejemplo.Usuario" %>
<html>
<head>
    <title>Ejemplo de JavaBean en JSP</title>
</head>
<body>

<% 
    // Crear una instancia del JavaBean
    Usuario usuario = new Usuario();
    
    // Establecer propiedades
    usuario.setNombre("Juan");
    usuario.setEdad(30);
    
    // Obtener propiedades
    String nombre = usuario.getNombre();
    int edad = usuario.getEdad();
%>

<h1>Detalles del Usuario</h1>
<p>Nombre: <%= nombre %></p>
<p>Edad: <%= edad %></p>

</body>
</html>

```

### Buenas prácticas con JavaBeans

```
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ejemplo.Usuario" %>
<html>
<head>
    <title>Ejemplo de JavaBean en JSP</title>
</head>
<body>

<jsp:useBean id="usuario" class="com.ejemplo.Usuario" scope="session">
    <jsp:setProperty name="usuario" property="nombre" value="Juan"/>
    <jsp:setProperty name="usuario" property="edad" value="30"/>
</jsp:useBean>

<h1>Detalles del Usuario</h1>
<p>Nombre: <jsp:getProperty name="usuario" property="nombre"/></p>
<p>Edad: <jsp:getProperty name="usuario" property="edad"/></p>

</body>
</html>

```
