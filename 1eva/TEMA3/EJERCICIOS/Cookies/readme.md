# Conceptos fundamentales de Cookies con Java


Para crear una cookie y enviarla al cliente:

```
// Crear una cookie
Cookie cookie = new Cookie("usuario", "nombreDeUsuario");
// Establecer tiempo de expiración en 7 días
cookie.setMaxAge(7 * 24 * 60 * 60); 
response.addCookie(cookie);

```

Para leer la cookie, el servlet recibiría todas las cookies en el HttpServletRequest:

```
Cookie[] cookies = request.getCookies();
for (Cookie c : cookies) {
    if (c.getName().equals("usuario")) {
        String nombreUsuario = c.getValue();
        // Realizar alguna acción con el valor de la cookie
    }
}

```

# Ejercicio 1: sistema de login con cookies. Práctica guiada

Práctica guiada para mejorar el sistema de login (https://github.com/profeMelola/DWES-03-2024-25/blob/main/EJERCICIOS/Cabeceras/readme.md#ejercicio-52-vamos-a-crear-un-loginservlet-que-simula-el-login-de-un-usuario).

## Pasos:

1. Reutilizaremos el proyecto webapp-ut03
2. Crearemos una página html nueva llamada cookie.html:
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cabeceras Http</title>
</head>
<body>
<h3>Manejo de cookies Http</h3>
<ul>
    <li><a href="productos">mostrar productos html</a></li>
    <li><a href="login.html">login</a></li>
    <li><a href="logout">logout</a></li>
</ul>

</body>
</html>
```
3. Mantenemos la lógica de mostrar los productos en una tabla html, pero si el usuario está registrado, entonces no saldrá el precio. Algo similar a esto:

<img src="https://github.com/user-attachments/assets/05cdd245-9e25-4f44-a0e2-2e3c70700578" height="100px"/>

5. Si el usuario está registrado, entonces sí saldrá el precio en la tabla. Algo similar a esto:
   
   <img src="https://github.com/user-attachments/assets/5fde97bf-e6b5-4353-a564-c89ebccde5e4" height="100px"/>

7. Vamos a hacer dos controladores (servlets): LoginCookieServlet y LogoutServlet
8. Vamos a crear el servicio de Login: LoginService y LoginServiceImpl

Observa el siguiente código:

```
// Forma 1: API Stream
Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
return Arrays.stream(cookies)
             .filter(c -> "username".equals(c.getName()))
             .map(Cookie::getValue)
             .findAny();

```

```
// Forma 2:
Cookie[] cookies = request.getCookies();
String nombre = null;

if (cookies != null) {
    for (Cookie cookie : cookies) {
        if ("username".equals(cookie.getName())) {
            nombre = cookie.getValue();
            break;
        }
    }
}

```
