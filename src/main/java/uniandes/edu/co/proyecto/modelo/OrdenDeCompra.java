package uniandes.edu.co.proyecto.modelo;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "ordenes_de_compra") // Nombre de la colección en MongoDB
@ToString
public class OrdenDeCompra {

    @Id
    private String id; // ID de la orden de compra (puede ser generado automáticamente)
    private Date fechaCreacion; // Fecha de creación de la orden (automática)
    private Sucursal sucursal; // ID de la sucursal asociada a la orden
    private Proveedor proveedor; // ID del proveedor asociado
    private Date fechaEsperadaEntrega; // Fecha esperada de entrega de los productos
    private String estado; // Estado de la orden (Ej: "Pendiente", "Enviado", "Entregado")
    private List<InventarioItem> inventarioItems; // Lista de los productos (InventarioItems) asociados a la orden

    // Constructor vacío (necesario para operaciones de Spring Data)
    public OrdenDeCompra() {
        this.fechaCreacion = new Date(); // Se asigna automáticamente la fecha actual
    }

    // Constructor con parámetros
    public OrdenDeCompra(Sucursal sucursal, Proveedor proveedor, Date fechaEsperadaEntrega, String estado, List<InventarioItem> inventarioItems) {
        this.fechaCreacion = new Date(); // Se asigna automáticamente la fecha actual
        this.sucursal = sucursal;
        this.proveedor = proveedor;
        this.fechaEsperadaEntrega = fechaEsperadaEntrega;
        this.estado = estado;
        this.inventarioItems = inventarioItems;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setIdSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Proveedor getIdProveedor() {
        return proveedor;
    }

    public void setIdProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaEsperadaEntrega() {
        return fechaEsperadaEntrega;
    }

    public void setFechaEsperadaEntrega(Date fechaEsperadaEntrega) {
        this.fechaEsperadaEntrega = fechaEsperadaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<InventarioItem> getInventarioItems() {
        return inventarioItems;
    }

    public void setInventarioItems(List<InventarioItem> inventarioItems) {
        this.inventarioItems = inventarioItems;
    }
}
