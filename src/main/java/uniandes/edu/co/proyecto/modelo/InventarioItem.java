package uniandes.edu.co.proyecto.modelo;

import java.util.Objects;

public class InventarioItem {
    // Atributos de la clase
    private String idProducto;
    private int cantidad;
    private double costoPromedio;

    // Constructor vacío (requerido por frameworks como Jackson para la deserialización)
    public InventarioItem() {}

    // Constructor completo
    public InventarioItem(String idProducto, int cantidad, double costoPromedio) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.costoPromedio = costoPromedio;
    }

    // Getters y setters
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(double costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    // Método toString para facilitar la depuración
    @Override
    public String toString() {
        return "InventarioItem{" +
                "idProducto='" + idProducto + '\'' +
                ", cantidad=" + cantidad +
                ", costoPromedio=" + costoPromedio +
                '}';
    }

    // Métodos equals y hashCode para comparar objetos de manera precisa
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventarioItem that = (InventarioItem) o;
        return cantidad == that.cantidad &&
                Double.compare(that.costoPromedio, costoPromedio) == 0 &&
                Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, cantidad, costoPromedio);
    }
}
