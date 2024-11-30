package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;
import java.util.List;

@Document(collection = "bodegas") // Nombre de la colección en MongoDB
@ToString
public class Bodega {

    @Id
    private String id; // ID de la bodega
    private String nombre; // Nombre de la bodega
    private double tamaño; // Tamaño de la bodega (en metros cuadrados, por ejemplo)
    private double capacidad; // Capacidad total de la bodega (en unidades)
    private List<InventarioItem> inventario; // Lista de listas para idProducto, Cantidad, Costo_Promedio

    // Constructor vacío (necesario para operaciones de Spring Data)
    public Bodega() {}

    // Constructor con parámetros
    public Bodega(String id, String nombre, double tamaño, double capacidad, List<InventarioItem> inventario) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.capacidad = capacidad;
        this.inventario = inventario;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public List<InventarioItem> getInventario() {
        return inventario;
    }

    public void setInventario(List<InventarioItem> inventario) {
        this.inventario = inventario;
    }

}
