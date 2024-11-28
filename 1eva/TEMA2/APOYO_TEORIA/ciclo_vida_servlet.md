# Servlet

Un Servlet es un programa que se ejecuta en un servidor web y maneja peticiones que vienen de los clientes (normalmente navegadores) y envía respuestas. Es como un intermediario entre el usuario y la lógica de una aplicación web.

Un servlet es un componente alojado en un contenedor web, su código es ejecutado cuando el contenedor recibe una petición HTTP.

Los servlets generan páginas web de forma dinámica como consecuencia de las peticiones HTTP recibidas.

![image](https://github.com/user-attachments/assets/761c327d-0748-4fb5-893f-3a3a2035f165)


## Ciclo de Vida de un Servlet
1. **Creación:** Cuando el servidor recibe una petición (por ejemplo, cuando alguien visita una página web que usa servlets), el contenedor de servlets (un programa que gestiona estos servlets, como Tomcat, Jetty, Payara, Glassfish, Wildfly...) primero verifica si el servlet ya está en memoria.
   - Si el servlet no existe en memoria, el contenedor lo crea llamando al constructor del servlet.
3. **Inicialización:** Después de crear el servlet, el contenedor llama al método init(), que inicializa los recursos que el servlet puede necesitar. Esto también ocurre solo una vez, la primera vez que el servlet es creado.
4. **Manejo de peticiones:** Aquí es donde entra la programación concurrente:
    - Cuando llegan múltiples peticiones de los clientes, el contenedor crea un hilo separado por cada una. Cada hilo ejecuta el método service(), que a su vez puede llamar a métodos como doGet() o doPost() (dependiendo del tipo de petición: GET o POST).
    - Importante: El servlet no se vuelve a crear para cada petición. En su lugar, múltiples hilos manejan las peticiones concurrentes, usando la misma instancia del servlet.
5. **Destrucción:** Cuando el servidor se apaga o el servlet ya no se necesita, el contenedor llama al método destroy(), que libera cualquier recurso que el servlet estaba usando. Esto ocurre solo una vez, cuando el servlet ya no se usará más.

## Relación con la programación concurrente

En programación concurrente, varios hilos pueden estar ejecutándose al mismo tiempo, y en el caso de los servlets, cada hilo maneja una petición del cliente. Aquí hay algunos conceptos clave de la programación concurrente aplicados a los servlets:

- **Hilos (Threads):** Por cada petición que llega al servidor, se crea un hilo para manejarla. Todos estos hilos usan la misma instancia del servlet para responder, lo que permite manejar múltiples usuarios al mismo tiempo de manera eficiente.

- **Problemas de concurrencia:** Como varios hilos pueden estar accediendo a la misma instancia del servlet al mismo tiempo, es importante asegurarse de que no se accedan ni modifiquen variables o recursos compartidos sin control. Si no se maneja bien, puede haber condiciones de carrera (cuando dos o más hilos acceden simultáneamente a un recurso compartido y los resultados dependen del orden de ejecución).

- **Sincronización:** Para evitar problemas de concurrencia, en algunos casos es necesario utilizar mecanismos de sincronización para controlar el acceso a datos compartidos.

## Ejemplo simple

Imagina que tienes un sitio web que muestra información sobre productos. 

Cuando múltiples usuarios visitan la página al mismo tiempo, el contenedor de servlets crea varios hilos que manejan cada visita (petición) de manera independiente y paralela, usando la misma instancia del servlet. 

Los hilos permiten que cada usuario obtenga su respuesta sin necesidad de esperar a que otro termine.

Sin esta concurrencia, el servidor tendría que manejar las peticiones una por una, lo que haría que la página web fuera mucho más lenta cuando hay muchos usuarios.
