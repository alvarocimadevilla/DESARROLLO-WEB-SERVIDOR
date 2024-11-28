# FASE I: Recoger datos de un formulario (Servlet + salida html con PrintWriter)

Vamos a aprender a leer los parámetros enviados desde un formularios.

Usaremos nuestra primera página JSP que se proporciona en este repositorio.

Creamos nuestro proyecto desde **Eclipse Starter** y llamaremos a nuestro proyecto **webapp-form1**:

![image](https://github.com/user-attachments/assets/ffa107a3-6d16-49eb-94e1-a21c9518ff45)

Viendo la página index.jsp ¿Cómo debe llamarse el servlet?

La estructura del proyecto completa, con el jsp y servlet debería quedar así:

![image](https://github.com/user-attachments/assets/352ef533-6af8-4561-8883-eab8674cee27)

## Cliente

Se proporciona la página index.jsp

![image](https://github.com/user-attachments/assets/ed4c8243-d89f-42e0-a943-df594ad64ca2)


Para poder acceder a dicha página JSP a través del navegador necesito indicar en la url la ruta completa porque si no da el siguiente error:

![image](https://github.com/user-attachments/assets/c2901b64-dbc3-42a2-8e12-373d5d64fce6)

La ruta completa sería: http://localhost:8080/webapp-form1/index.jsp

Esto es debido, a que los proyectos generados con el Eclipse Starter tienen una directiva de bienvenida en el **archivo de configuración web.xml**

![image](https://github.com/user-attachments/assets/dd44f77c-5066-4f3f-aca6-767bfac31783)


En Jakarta EE 10, el uso del archivo web.xml no es obligatorio gracias a las anotaciones, pero sigue siendo una opción válida si prefieres centralizar configuraciones o si tienes necesidades específicas.


## Servidor

Debemos de realizar las siguientes validaciones en el servidor (no el el cliente vía javascript o html5):

- el username es requerido.
- el password no puede ser vacío.
- el email es requerido y debe tener un formato de correo.
- debe seleccionar un idioma.

Debemos devolver una página html para informar al usuario.

![image](https://github.com/user-attachments/assets/72b6f8b9-8d71-4bab-bfc1-5ae4e821d4c0)

Si se ha rellenado correctamente el formulario, el servlet debe devolver esta página html:

![image](https://github.com/user-attachments/assets/c5fa1216-f499-4fb5-9758-9494aa1b40a9)

# FASE II: Recoger datos de un formulario (Servlet + JSP) -> Práctica guiada

Vamos a mejorar la aplicación separando la creación del html de salida del código java del Servlet.

Java Server Pages (JSP) es una tecnología que se utiliza para desarrollar páginas web insertando Java código en las páginas HTML mediante la creación de etiquetas JSP especiales. 

Las etiquetas JSP que permiten java El código que se incluirá en él es <% —-java código—-%>

En el caso de que haya errores en el formulario, se redigirá la salida desde el servlet a una página JSP:

```
  req.setAttribute("errores", errores);
  getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
```

Utilizaremos en el Sevlet una colección Map para indicar el campo del formulario y la descripción del error. Por ejemplo:

```
  Map<String, String> errores = new HashMap<>();

  if (username == null || username.isBlank()) {
      errores.put("username", "el username es requerido!");
  }
```

Ese Map lo utilizaré en la página jsp para pintar los errores. Esta será la página html resultado:

![image](https://github.com/user-attachments/assets/0a67533c-6896-4ffb-88d1-3fd5f634376e)

Sigue las instrucciones del profesor para añadir las etiquetas JSP pertinentes (https://github.com/profeMelola/DWES-02-2024-25/blob/main/APOYO_TEORIA/jsp.md)

# ![image](https://github.com/user-attachments/assets/e9765248-ae09-4165-be31-a9bb59ad1f1b) FASE III: look & feel. Bootstrap 

![image](https://github.com/user-attachments/assets/597df347-5bf1-46a6-b57a-c6bd0e521383)

![image](https://github.com/user-attachments/assets/fe4e284c-8521-4492-bd04-e4db09fd2085)


## 1. Vamos a incluir Bootstrap de forma remota en nuestra página añadiendo en la cabecera esta etiqueta:

https://getbootstrap.com/docs/5.3/getting-started/introduction/


```
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
```

https://getbootstrap.com/docs/5.3/examples/

https://getbootstrap.com/docs/5.3/layout/grid/

https://getbootstrap.com/docs/5.3/components/buttons/

## 2. Ahora tenemos que dar estilos a nuestra página. Vamos a añadir estilos a los div de la página index.jsp

Fijaros en la página html resultado para ver los estilos a aplicar:

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
    <link href="/webapp-formBootstrap2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h3>Formulario de usuarios</h3>


<div class="px-5">
<form action="registro" method="post">

  <div class="row mb-3">
    <label for="username" class="col-form-label col-sm-2">Usuario</label>
    <div class="col-sm-4">
        <input type="text" name="username" id="username" class="form-control" value="">
    </div>

  </div>
      
  <div class="row mb-3">
    <label for="password" class="col-form-label col-sm-2">Password</label>
    <div class="col-sm-4"><input type="password" name="password" id="password" class="form-control"></div>

  </div>
  <div class="row mb-3">
    <label for="email" class="col-form-label col-sm-2">Email</label>
    <div class="col-sm-4"><input type="text" name="email" id="email" class="form-control" value=""></div>
    
  </div>
  <div class="row mb-3">
    <label for="pais" class="col-form-label col-sm-2">País</label>
    <div class="col-sm-4">
      <select name="pais" id="pais" class="form-select">
        <option value="">-- seleccionar --</option>
        <option value="ES" >España</option>
        <option value="MX" >México</option>
        <option value="CL" >Chile</option>
        <option value="AR" >Argentina</option>
        <option value="PE" >Perú</option>
        <option value="CO" >Colombia</option>
        <option value="VE" >Venezuela</option>
      </select>
    </div>
    
  </div>

  <div class="row mb-3">
    <label for="lenguajes" class="col-form-label col-sm-2">Lenguajes de programación</label>
    <div class="col-sm-4">
      <select name="lenguajes" id="lenguajes" multiple class="form-select">
        <option value="java" >Java SE</option>
        <option value="jakartaee" >Jakarta EE9</option>
        <option value="spring" >Spring Boot</option>
        <option value="js" >JavaScript</option>
        <option value="angular" >Angular</option>
        <option value="react" >React</option>
      </select>
    </div>
    
  </div>

  <div class="row mb-3">
    <label class="col-form-label col-sm-2">Roles</label>
    <div class="form-check col-sm-2">
      <input type="checkbox" name="roles" value="ROLE_ADMIN"
      
      class="form-check-input">
      <label class="form-check-label">Administrador</label>
    </div>
    <div class="form-check col-sm-2">
      <input type="checkbox" name="roles" value="ROLE_USER" class="form-check-input"
            >
      <label class="form-check-label">Usuario</label>
    </div>
    <div class="form-check col-sm-2">
      <input type="checkbox" name="roles" value="ROLE_MODERATOR" class="form-check-input"
            >
      <label class="form-check-label">Moderador</label>
    </div>
    
  </div>
  <div class="row mb-3">
    <label class="col-form-label col-sm-2">Idiomas</label>
    <div class="form-check col-sm-2">
      <input type="radio" name="idioma" value="es" class="form-check-input" >
      <label class="form-check-label">Español</label>
    </div>
    <div class="form-check col-sm-2">
      <input type="radio" name="idioma" value="en" class="form-check-input" >
      <label class="form-check-label">Inglés</label>
    </div>
    <div class="form-check col-sm-2">
      <input type="radio" name="idioma" value="fr" class="form-check-input" >
      <label class="form-check-label">Frances</label>
    </div>
    
  </div>
  <div class="row mb-3">
    <label for="habilitar" class="col-form-label col-sm-2">Habilitar</label>
    <div class="form-check col-sm-2">
      <input type="checkbox" name="habilitar" id="habilitar" checked class="form-check-input">
    </div>
  </div>
  <div class="row mb-3">
    <div>
      <input type="submit" value="Enviar" class="btn btn-primary">
    </div>
  </div>
  <input type="hidden" name="secreto" value="12345">
</form>
</div>
</body>
</html>
```

# FASE IV: usando Expression Language API

Cada vez que enviamos los datos y validamos, perdemos los datos del formulario.

Se pueden recuperar esos valores mediante el API EL (Expression Language API) es una tecnología que permite el acceso a los datos de beans, mapas, listas y otras estructuras de datos de manera sencilla y expresiva dentro de tecnologías como JSP (JavaServer Pages) y JSF (JavaServer Faces).





 

