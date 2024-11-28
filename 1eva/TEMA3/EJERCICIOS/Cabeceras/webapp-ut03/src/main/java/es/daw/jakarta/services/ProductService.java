package es.daw.jakarta.services;

import java.util.List;
import java.util.Optional;

import es.daw.jakarta.models.Producto;

public interface ProductService {
    
    // Método abstracto. No es necesario poner la palabra reservada abstract
    public List<Producto> listar();

    public Optional<Producto> buscarProducto(String name);

    public Producto buscarProducto2(String name);

}
