# Entorno de trabajo

## Directorio principal

Ya que tenemos entornos de lo más variopinto, entre los pcs de clase, el portátil de cada uno, para unificar el entorno y así poder resolver de una forma más ágil los problemas de despliegue de nuestras aplicaciones web, vamos a trabajar todos con un directorio principal llamado **DWES** donde crearemos los diferentes proyectos web.

Solo los proyectos web.

Por ejemplo, deberíamos tener el proyecto con el servlet que gestiona los parámetros del request vía get y el proyecto para trabajar con los datos de un formulario vía post.

![image](https://github.com/user-attachments/assets/3f3ad204-f18b-43c6-a744-d2a042f9d4c1)

## Creación de un proyecto web

Aunque en clase hemos visto las diferentes formas de crear un proyecto Maven para trabajar con Jakarta EE, a partir de este momento todos vamos a crear los proyectos desde el Eclipse Starter https://start.jakarta.ee/

Por ejemplo:

![image](https://github.com/user-attachments/assets/ffa107a3-6d16-49eb-94e1-a21c9518ff45)


## Servidor de aplicaciones

Después de haber aprendido cómo usar Tomcat para aplicaciones web java basadas en Sevlets y JSPs, vamos a instalar el servidor de aplicaciones definitivo, concretamente Wildfly de Red Hat.

Para ello es necesario instalar en Visual Studio Code la siguiente extensión:

![image](https://github.com/user-attachments/assets/d16f719d-d1df-46d4-b7d0-3db4e1e74158)


Una vez instalada la extensión vamos a instalar el servidor de la siguiente manera:

![image](https://github.com/user-attachments/assets/9af7a800-bc24-46b5-9ecc-37a77aac3568)

![image](https://github.com/user-attachments/assets/f699633a-28aa-400d-a5b9-afdcf2914347)

![image](https://github.com/user-attachments/assets/ccaa6991-a3e1-4753-8855-05df75157954)


Puedo tener en VSC tantos servidores como quiera, teniendo en cuenta que si todos usan el puerto 8080 debo tener uno solo arrancado. También podría configurar cada servidor con un puerto diferente.

Nosotros vamos a parar todos los servidores y dejar únicamente funcionando Wildfly. Deberíamos tener algo similar a esto:

![image](https://github.com/user-attachments/assets/ce316532-40f5-4d01-83ac-64d83571b2e0)


