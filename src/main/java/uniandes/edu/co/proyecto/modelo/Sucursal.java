package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "sucursales") // Nombre de la colección en MongoDB
@ToString
public class Sucursal {
    
    @Id
    private String id; // ID de la sucursal
    private String nombre; // Nombre de la sucursal
    private String tamaño; // Tamaño de la sucursal (Ej: "Pequeña", "Mediana", "Grande")
    private String direccion; // Dirección de la sucursal
    private String telefono; // Teléfono de la sucursal
    private String ciudadId; // ID de la ciudad donde está ubicada la sucursal
    private List<String> listaIdsBodegas; // Lista de IDs de bodegas asociadas a la sucursal

    // Constructor vacío (necesario para operaciones de Spring Data)
    public Sucursal() {
    }

    // Constructor con parámetros
    public Sucursal(String id, String nombre, String tamaño, String direccion, String telefono, String ciudadId, List<String> listaIdsBodegas) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudadId = ciudadId;
        this.listaIdsBodegas = listaIdsBodegas;
    }

    // Getters y Setters
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

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(String ciudadId) {
        this.ciudadId = ciudadId;
    }

    public List<String> getListaIdsBodegas() {
        return listaIdsBodegas;
    }

    public void setListaIdsBodegas(List<String> listaIdsBodegas) {
        this.listaIdsBodegas = listaIdsBodegas;
    }
}
