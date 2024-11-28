# Ejemplo Servlet con pasos de parámetros por Get

## Página index.html

![image](https://github.com/user-attachments/assets/77665391-86b5-4522-a3f2-2e78ca8da99b)

## Diferentes páginas html resultado

**Si el código es superior a 100:**

![image](https://github.com/user-attachments/assets/1656e969-404e-46dc-9932-d23f4e52a41f)

**Si el código no es superior a 100:**

![image](https://github.com/user-attachments/assets/6cd252ef-1a1a-4b6d-9a51-df2a244eb56d)


## Código de ayuda:

**En el servlet:**

```
  // Entrada
  String param = request.getParameter("saludo");

  // Salida
  out.println("       <h2>El saludo enviado es:"+param+"</h2>");
```

**En la página html**

```
    <h1>Mi primera app web</h1>
    <p><a href="url-get?saludo=Hola caracola">Ejecutar el ParamsGetServlet (url-get?saludo=Hola caracola)</a></p>
    <p><a href="url-get?saludo=Hola&nombre=melola&codigo=666">Ejecutar el ParamsGetServlet con 3 params(url-get?saludo=Hola&nombre=melola&codigo=666)</a></p>
    <p><a href="url-get?saludo=Hola&nombre=melola&codigo=66">Ejecutar el ParamsGetServlet con 3 params(url-get?saludo=Hola&nombre=melola&codigo=66)</a></p>
```

## Ampliación

Corrige el error que sale al hacer clic en el primer enlace de la página index.html.

![image](https://github.com/user-attachments/assets/6e7c680b-a336-4d27-9727-e9611f18818d)


En vez de que salga el error, que el servlet devuelva un mensaje indicando que el código no puede ser nulo.

