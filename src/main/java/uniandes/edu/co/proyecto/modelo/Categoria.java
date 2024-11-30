package uniandes.edu.co.proyecto.modelo;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.ToString;

@Document(collection = "categorias") // Nombre de la colección en MongoDB
@ToString
public class Categoria {

    @Id
    private String nombre; // Nombre de la categoría
    private String descripcion; // Descripción de la categoría
    private String caracteristicasAlmacenamiento; // Características específicas para el almacenamiento

    // Constructor vacío (necesario para operaciones de Spring Data)
    public Categoria() {}

    // Constructor con parámetros
    public Categoria(String nombre, String descripcion, String caracteristicasAlmacenamiento) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicasAlmacenamiento = caracteristicasAlmacenamiento;
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

    
}
