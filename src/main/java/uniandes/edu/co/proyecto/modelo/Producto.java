package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "productos") // Nombre de la colección en MongoDB
@ToString

public class Producto {
    @Id
    private String id;
    private String nombre;
    private int costoBodega;
    private int precioUnitario;
    private String presentación; // Ejemplo: "Botella", "Bolsa"
    private int cantidad; // Cantidad de unidades
    private String unidadMedida; // Ejemplo: "Kg", "Litros", "Unidades"
    private double volumenEmpaque; // Volumen del empaque en cm³ o m³
    private double pesoEmpaque; // Peso del empaque en kg o g
    private Date fechaExpiración; // Fecha de expiración del producto
    private String codigoBarras; // Código de barras único del producto
    private String categoriaNombre; // ID de la categoría asociada

    // Constructor vacío (necesario para Spring Data)
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(String id, String nombre, int costoBodega, int precioUnitario, String presentación, int cantidad,
            String unidadMedida, double volumenEmpaque, double pesoEmpaque, Date fechaExpiración,
            String codigoBarras, String categoriaNombre) {
        this.id = id;
        this.nombre = nombre;
        this.costoBodega = costoBodega;
        this.precioUnitario = precioUnitario;
        this.presentación = presentación;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.volumenEmpaque = volumenEmpaque;
        this.pesoEmpaque = pesoEmpaque;
        this.fechaExpiración = fechaExpiración;
        this.codigoBarras = codigoBarras;
        this.categoriaNombre = categoriaNombre;
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

    public int getCostoBodega() {
        return costoBodega;
    }

    public void setCostoBodega(int costoBodega) {
        this.costoBodega = costoBodega;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getPresentación() {
        return presentación;
    }

    public void setPresentación(String presentación) {
        this.presentación = presentación;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getVolumenEmpaque() {
        return volumenEmpaque;
    }

    public void setVolumenEmpaque(double volumenEmpaque) {
        this.volumenEmpaque = volumenEmpaque;
    }

    public double getPesoEmpaque() {
        return pesoEmpaque;
    }

    public void setPesoEmpaque(double pesoEmpaque) {
        this.pesoEmpaque = pesoEmpaque;
    }

    public Date getFechaExpiración() {
        return fechaExpiración;
    }

    public void setFechaExpiración(Date fechaExpiración) {
        this.fechaExpiración = fechaExpiración;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    
}
