package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categorias") // Nombre de la colección en MongoDB
public class Categoria {

    @Id
    private Integer id; // Identificador único de la categoría
    private String nombre; // Nombre de la categoría
    private String descripcion; // Descripción de la categoría
    private String caracteristicasAlmacenamiento; // Características específicas para el almacenamiento
    private List<Producto> productos; // Lista de productos asociados a esta categoría

    // Constructor vacío (necesario para operaciones de Spring Data)
    public Categoria() {}

    // Constructor con parámetros
    public Categoria(Integer id, String nombre, String descripcion, String caracteristicasAlmacenamiento, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicasAlmacenamiento = caracteristicasAlmacenamiento;
        this.productos = productos;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicasAlmacenamiento() {
        return caracteristicasAlmacenamiento;
    }

    public void setCaracteristicasAlmacenamiento(String caracteristicasAlmacenamiento) {
        this.caracteristicasAlmacenamiento = caracteristicasAlmacenamiento;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", caracteristicasAlmacenamiento=" + caracteristicasAlmacenamiento + ", productos=" + productos + "]";
    }
}
