package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

@Document(collection = "proveedores") // Nombre de la colección en MongoDB
@ToString
public class Proveedor {

    @Id
    private String id; // ID del proveedor (puede ser un String o un ObjectId dependiendo del requerimiento)
    private String nit; // NIT del proveedor
    private String nombre; // Nombre del proveedor
    private String direccion; // Dirección del proveedor
    private String nombreContacto; // Nombre del contacto en el proveedor
    private String telefonoContacto; // Teléfono de contacto del proveedor

    // Constructor vacío (necesario para operaciones de Spring Data)
    public Proveedor() {}

    // Constructor con parámetros
    public Proveedor(String id, String nit, String nombre, String direccion, String nombreContacto, String telefonoContacto) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
}

