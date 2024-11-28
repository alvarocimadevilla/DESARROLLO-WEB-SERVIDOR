# EJERCICIO 1: Copia profunda y superficial de un arraylist

Revisad el proyecto webapp_ut03 trabajado en clase.

En la clase ProductServiceImpl os propuse un reto relacionado con copia profunda y copia superficial de un ArrayList: 

```
        // Esto devuelve una copia profunda o superficial? demostrármelo!!!!
        // Profundo implica que los objetos de la lista también son copias (clonados)
        return new ArrayList<>(productos);

```

# EJERCICIO 2: carga de objetos producto a través de un CSV

Cread un nuevo proyecto web.

Para ello, haced una copia del proyecto webapp_ut03 porque vais a modificar la lógica de la clase **ProductServiceImpl**.

En vez de crear a mano los objetos productos, debéis leer un archivo CSV y crear los objetos con los datos de dicho archivo.

Por ejemplo, el contenido de **productos.csv** sería:

```
1,notebook,informática,175000
2,mesa escritorio,oficina,10000000
3,teclado,informatica,40000
```

Tenéis que repasar cómo leer el contenido de un fichero de texto. Podemos hacerlo de diferentes formas. 

Lo importante es poder leer línea a línea el archivo y procesar cada línea para obtener los datos necesarios para posteriormente crear los objetos.

PISTA: 
- Guardad productos.csv en el directorio webapp del proyecto web.
- El código getServletContext().getRealPath("/productos.csv") nos dará la ruta abssoluta donde se cuentra dicho archivo para poderlo leer.







