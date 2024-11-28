# Objetos implícitos a usar con EL en JSPs

En JSP (JavaServer Pages), Expression Language (EL) permite acceder de manera sencilla a los objetos implícitos y los datos asociados.

Con EL, se pueden obtener varios tipos de objetos sin necesidad de escribir código Java explícito.

## Listado de objetos implícitos más comunes que puedes usar con EL en una JSP:

### 1. param
Representa los parámetros de la solicitud (request.getParameter()).

Acceso a un solo parámetro: ${param.nombreParametro}

**Ejemplo:** Si tienes un parámetro en la URL como ?id=123, puedes accederlo usando ${param.id}.


### 2. paramValues
Similar a param, pero se utiliza para acceder a los parámetros que tienen múltiples valores (como en un checkbox con el mismo nombre).

Acceso a un arreglo de valores: ${paramValues.nombreParametro}.

**Ejemplo:** Si el parámetro colors tiene varios valores, puedes accederlos así: ${paramValues.colors[0]}, ${paramValues.colors[1]}, etc.

### 3. header
Acceso a los encabezados HTTP de la solicitud (request.getHeader()).

**Ejemplo:** ${header["User-Agent"]}.

### 4. headerValues
Similar a header, pero para encabezados con múltiples valores (request.getHeaders()).

**Ejemplo:** ${headerValues.nombreEncabezado}.

### 5. cookie
Permite acceder a las cookies enviadas en la solicitud.

**Ejemplo:** ${cookie.nombreCookie.value}.

### 6. initParam
Permite acceder a los parámetros de inicialización definidos en la anotación @WebServlet (initParams).

**Ejemplo:** ${initParam.nombreParametro}.

### 7. sessionScope
Acceso a los atributos en el ámbito de la sesión (session.setAttribute()).

**Ejemplo:** ${sessionScope.nombreAtributo}.

### 8. session
Acceso directo al objeto sesión.

**Ejemplo:** ${session.id} para obtener el ID de la sesión.

![image](https://github.com/user-attachments/assets/0dad4b3a-46e1-44aa-a540-9ae6df411790)

### 9. requestScope
Acceso a los atributos definidos en el ámbito de la solicitud (request.setAttribute()).

**Ejemplo:** ${requestScope.nombreAtributo}.

### 10. request
Acceso directo al objeto HttpServletRequest.

**Ejemplo:** ${request.remoteAddr} para obtener la dirección IP del cliente.

### 11. applicationScope
Acceso a los atributos definidos en el ámbito de la aplicación (application.setAttribute()).

**Ejemplo:** ${applicationScope.nombreAtributo}.

### 12. application
Acceso al contexto de la aplicación (ServletContext).

**Ejemplo:** ${application.contextPath}.

### 13. response
Acceso directo al objeto HttpServletResponse, aunque raramente se usa en EL.

**Ejemplo:** ${response.contentType}.

### 14. exception
Acceso a la excepción que se lanzó en la página, útil en páginas de error.

**Ejemplo:** ${exception.message}.
