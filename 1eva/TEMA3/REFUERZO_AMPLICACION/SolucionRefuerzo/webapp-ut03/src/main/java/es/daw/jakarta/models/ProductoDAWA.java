package es.daw.jakarta.models;

public class ProductoDAWA implements Comparable<ProductoDAWA>{
    private Long id;
    private String nombre;
    private String tipo;
    private int precio;

    public ProductoDAWA(){

    }

    public ProductoDAWA(Long id, String nombre, String tipo, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + "]";
    }

    @Override
    public int compareTo(ProductoDAWA arg0) {
        //return nombre.compareTo(arg0.nombre);
        //return id.compareTo(arg0.id);
        return Integer.compare(precio, arg0.precio);
    }




    
    
}
