Os recomiendo ver el siguiente video ¿Qué es la programación funcional?

[![Alt text](https://img.youtube.com/vi/W3k3IEXJ2oQ/0.jpg)](https://www.youtube.com/watch?v=W3k3IEXJ2oQ)



# Java Streams

![image](https://github.com/user-attachments/assets/349c5bb6-903c-422e-be19-63db2f66ff77)

<pre>https://medium.com/@berrachdim/mastering-data-processing-with-java-streams-a-comprehensive-guide-581f2a64052a</pre>

## Descripción

- Los Streams fueron introducidos a Java en la versión 8.
- Permiten procesar secuencias de elementos con operaciones previamente definidas en la API.
- La API de Streams contiene clases para procesar secuencias de elementos y pueden ser creados desde componentes existentes en Java como las Colecciones.
- El código es declarativo, definimos el que y no el cómo.
- Menos complejidad y más legibilidad del código.
- Los Streams son autoiterables (no tenemos que definir explícitamente la forma en que se van a iterar).
- Integración con la API funcional (interfaces funcionales, expresiones lambda, etc). Esto me permite reutilizar funciones.
- Uso del paralelismo, nos permite ejecutar operaciones paralelas de una forma fácil.

## Tipos de operaciones

En la API de Streams existen dos tipos de operaciones, las operaciones Intermedias y las operaciones Terminales. En ningún caso las operaciones cambian la fuente, es decir que promueven la inmutabilidad.

La forma en que se ejecutan las operaciones es conocida como Lazyness, es decir que las operaciones se ejecutan al último momento o lo más tarde posible.

Las operaciones terminales son las que producen la ejecución de las operaciones intermedias.

### Operaciones intermedias

Son operaciones que retornan un Stream. Retornan una nueva versión del Stream del tipo Stream<T>. Estas operaciones pueden ser encadenadas para formar un “Pipeline” de operaciones. 

Algunas de las operaciones intermedias son:

- map()
- filter()
- distinct()
- sorted()
- limit()
- peek()
- skip()

### Operaciones terminales

Son operaciones que no retornan un Stream, sino que retornan un valor primitivo, una colección o un objeto. 

Las operaciones terminales no pueden ser encadenadas. 

Algunas de las operaciones terminales son:

- forEach()
- toArray()
- reduce()
- collect()
- min()
- max()
- count()
- anyMatch()
- allMatch()
- noneMatch()
- findFirst()
- findAny()


https://barcochrist.medium.com/streams-en-java-5712989fc62b


## Ejemplos

```
ArrayList<String> names =
   Lists.newArrayList(
       "John", "John", "Mariam", "Alex", "Mohammado", "Mohammado", "Vincent", "Alex", "Alex");

// FILTER
names.stream().filter(name-> name.contains("o")).forEach(System.out::println);

// MAP
names.stream()
     .map(name -> name.length())
     .collect(Collectors.toList());

// SORTED
names.stream()
   .sorted(Comparator.naturalOrder())
   .forEach(System.out::println);

names.stream()
   .sorted(Comparator.comparing(String::length))
   .forEach(System.out::println);

names.stream()
   .sorted( Comparator
           .comparing(String::length)
           .thenComparing(Comparator.naturalOrder()) )
   .forEach(System.out::println);

names.stream()
 .distinct()
 .forEach(System.out::println);

names.stream()
   .limit(5)
   .forEach(System.out::println);

// REDUCE
String reduce = names.stream().reduce("", String::concat);

// COUNT
int cantidad = names.stream().limit(5).count();

// COLLECT
List<String> collect = names.stream()
   .filter(s -> s.contains("o"))
   .collect(Collectors.toList());

```

# Programación funcional. Interfaz funcional

Una interfaz funcional es una interfaz que contiene exactamente un único método abstracto. 

Este tipo de interfaces está diseñado para ser usado en programación funcional, donde las expresiones lambda y las referencias a métodos pueden implementarlas de forma concisa.

Desde Java 8, las interfaces funcionales son fundamentales para utilizar las expresiones lambda y la programación funcional en Java. 

Estas interfaces permiten simplificar el código y hacer que sea más expresivo al enfocarse en la funcionalidad sin la necesidad de clases anónimas.

## Características de una Interfaz Funcional

- **Un único método abstracto:** Una interfaz funcional tiene un solo método abstracto que define la operación que la interfaz representa.
- **Anotación @FunctionalInterface:** Aunque no es obligatoria, esta anotación ayuda a marcar la interfaz como funcional. También le indica al compilador que genere un error si la interfaz tiene más de un método abstracto, asegurando que se mantiene la naturaleza funcional de la interfaz.

```
@FunctionalInterface
public interface Operacion {
    int ejecutar(int a, int b);
}

```

- **Métodos default o static:** Pueden contener métodos default o static, ya que estos no cuentan como métodos abstractos y no afectan el requisito de un solo método abstracto. Estos métodos pueden proporcionar comportamientos adicionales sin interferir con la funcionalidad principal de la interfaz.

## Ejemplo de Uso de una Interfaz Funcional

Supongamos que queremos definir una operación matemática que acepta dos enteros y devuelve un resultado entero:

```
@FunctionalInterface
public interface Operacion {
    int aplicar(int a, int b);
}
```

Podemos implementar esta interfaz con una expresión lambda para hacer sumas, restas o cualquier otra operación:

```
public class EjemploInterfazFuncional {
    public static void main(String[] args) {
        Operacion suma = (a, b) -> a + b;
        Operacion resta = (a, b) -> a - b;

        System.out.println("Suma: " + suma.aplicar(5, 3));  // Imprime: Suma: 8
        System.out.println("Resta: " + resta.aplicar(5, 3)); // Imprime: Resta: 2
    }
}
```

## Ventajas de las Interfaces Funcionales
- **Simplificación del código:** Las expresiones lambda y las interfaces funcionales permiten que el código sea más compacto y legible.
- **Uso en programación funcional:** Son la base de la programación funcional en Java, especialmente en combinación con Stream API, donde se utilizan en métodos como filter, map, y reduce.
- **Compatibilidad con lambdas:** Las interfaces funcionales permiten usar expresiones lambda, que son útiles para implementar operaciones de una manera concisa y directa.
 
## Interfaces Funcionales Comunes en Java

Java proporciona varias interfaces funcionales en el paquete java.util.function. Algunas de las más comunes incluyen:

- **Predicate<T>:** Define una condición que acepta un argumento de tipo T y devuelve un boolean.
- **Function<T, R>:** Acepta un argumento de tipo T y devuelve un resultado de tipo R.
- **Consumer<T>:** Realiza una operación sobre un solo argumento de tipo T sin devolver resultado.
- **Supplier<T>:** Proporciona un resultado de tipo T sin aceptar ningún argumento.
  
### Ejemplo de uso de una interfaz funcional existente: Predicate<T>

```
Predicate<Integer> esPar = x -> x % 2 == 0;
System.out.println(esPar.test(4));  // Imprime: true
System.out.println(esPar.test(5));  // Imprime: false
```

Aquí, Predicate<Integer> es una interfaz funcional que tiene un único método abstracto, test, y se utiliza para verificar si un número es par.

![image](https://github.com/user-attachments/assets/6e6e4577-9fba-465d-8946-5c0ab679122b)


