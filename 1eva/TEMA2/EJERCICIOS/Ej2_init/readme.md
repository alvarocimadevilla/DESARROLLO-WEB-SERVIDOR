# Ciclo de vida: método init de un servlet

![image](https://github.com/user-attachments/assets/b922fd0a-9ed7-4701-9ccc-fcb7670c350d)


1. Utiliza el proyecto web del ejercicio anterior ParamsGetServlet y crea un servlet y siguiendo el siguiente código haz la prueba realizando diferentes peticiones para ver el valor de la variable *peticiones*.

2. Utiliza la siguiente anotación @WebServlet en vez de la sencilla @WebServlet("/init")

```
@WebServlet(
    urlPatterns = {"/init", "/init-servlet"},
    name = "inicio",
    initParams =
    {
        @WebInitParam(name = "peticiones", value = "5"),
        @WebInitParam(name = "saveDir", value = "D:/FileUpload"),
        @WebInitParam(name = "allowedTypes", value = "jpg,jpeg,gif,png")
    }
)
```
4. Mejora el código del método doGet utilizando *try con recursos.*

```
	//variable estara compartida por todas las peticiones
	private int peticiones;
   
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		//configurar nuestro servlet con un valor inicial de 5
		peticiones= Integer.parseInt(config.getInitParameter("inicio"));
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw= response.getWriter();
		pw.println(peticiones);
		peticiones++;
		pw.close();
	}
```

