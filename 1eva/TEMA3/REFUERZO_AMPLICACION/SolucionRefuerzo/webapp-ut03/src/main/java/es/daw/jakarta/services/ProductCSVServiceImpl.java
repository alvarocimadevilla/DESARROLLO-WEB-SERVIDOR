package es.daw.jakarta.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.daw.jakarta.models.Producto;

public class ProductCSVServiceImpl implements ProductService{


    private static List<Producto> productos;

    @Override
    public List<Producto> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }    
    @Override
    public List<Producto> listar(String pathCsv) throws IOException{

        List<Producto> productos = new ArrayList<>();

        // FORMA 1: con java.nio
        Path path = Paths.get(pathCsv);

        List<String> linea_producto = Files.readAllLines(path);

        linea_producto.forEach(linea ->{
            String[] datos = linea.split(",");
            productos.add(new Producto(Long.parseLong(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3])));
        });

        // FORMA 2: con java.nio y API Stream
        // productos = Files.lines(Paths.get(pathCsv))
        //         .map(linea -> {
        //             String[] datos = linea.split(",");
        //             return new Producto(Long.parseLong(datos[0]), datos[1], datos[2], Integer.parseInt(datos[3]));
        //         })
        //         .collect(Collectors.toList());        

        // ----------------------------------------
        // FORMA 3: con BufferedReader y java.nio
        /*
         * Files.readAllLines: Carga todo el contenido del archivo en memoria de una vez como una lista de líneas (List<String>), lo que puede ser costoso en términos de memoria, especialmente con archivos grandes.
         * Files.newBufferedReader: Lee el archivo línea por línea en lugar de cargarlo por completo en memoria. Esto es mucho más eficiente en términos de memoria y evita problemas de OutOfMemoryError al trabajar con archivos grandes.
         */
        // Path path = Paths.get(pathCsv);

        // try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
        //     String linea;
        //     while ((linea = br.readLine()) != null) {
        //         String[] datos = linea.split(",");
        //         productos.add(new Producto(Long.parseLong(datos[0]), datos[1], datos[2], Integer.parseInt(datos[3])));
        //     }
        // }

        // ----------------------------------------
        // FORMA 4: con BufferedReader y FileReader para leer línea a línea
        // try (BufferedReader br = new BufferedReader(new FileReader(pathCsv))) {
        //     String linea;
        //     while ((linea = br.readLine()) != null) {
        //         String[] datos = linea.split(",");
        //         productos.add(new Producto(Long.parseLong(datos[0]), datos[1], datos[2], Integer.parseInt(datos[3])));
        //     }
        // }

        // FORMA 5: con BufferReader e InputStreamReader (binario)
        // try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathCsv), "UTF-8"))) {
        //     String linea;
        //     while ((linea = br.readLine()) != null) {
        //         String[] datos = linea.split(",");
        //         productos.add(new Producto(Long.parseLong(datos[0]), datos[1], datos[2], Integer.parseInt(datos[3])));
        //     }
        // }        


        return productos;
        
    }

    @Override
    public Optional<Producto> buscarProducto(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarProducto'");
    }

    @Override
    public Producto buscarProducto2(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarProducto2'");
    }

    
}
