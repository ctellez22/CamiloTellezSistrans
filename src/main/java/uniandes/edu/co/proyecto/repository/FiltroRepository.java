package uniandes.edu.co.proyecto.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FiltroRepository {

    private final MongoTemplate mongoTemplate;

    public FiltroRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Obtiene los productos que cumplen con las características ingresadas.
     *
     * @param rangoPrecioMin Precio mínimo del rango (puede ser nulo).
     * @param rangoPrecioMax Precio máximo del rango (puede ser nulo).
     * @param fechaVencimiento Fecha de vencimiento para filtrar (puede ser nula).
     * @param vencimientoPosterior Si es true, filtra productos con fecha posterior; 
     *                             si es false, filtra productos con fecha anterior (aplica si fechaVencimiento no es nula).
     * @param categoria Nombre de la categoría para filtrar (puede ser nulo).
     * @return Lista de productos que cumplen con las características.
     */
    public List<Document> obtenerProductosPorCaracteristicas(
            Integer rangoPrecioMin, Integer rangoPrecioMax, 
            Date fechaVencimiento, Boolean vencimientoPosterior, 
            String categoria) {
        
        List<Document> pipeline = new ArrayList<>();

        // Filtro por rango de precio
        if (rangoPrecioMin != null || rangoPrecioMax != null) {
            Document rangoPrecio = new Document();
            if (rangoPrecioMin != null) {
                rangoPrecio.append("$gte", rangoPrecioMin);
            }
            if (rangoPrecioMax != null) {
                rangoPrecio.append("$lte", rangoPrecioMax);
            }
            pipeline.add(new Document("$match", new Document("precioUnitario", rangoPrecio)));
        }

        // Filtro por fecha de vencimiento
        if (fechaVencimiento != null) {
            Document filtroFecha = new Document();
            if (vencimientoPosterior != null && vencimientoPosterior) {
                filtroFecha.append("$gte", fechaVencimiento);
            } else {
                filtroFecha.append("$lte", fechaVencimiento);
            }
            pipeline.add(new Document("$match", new Document("fechaExpiración", filtroFecha)));
        }

        // Filtro por categoría
        if (categoria != null && !categoria.isEmpty()) {
            pipeline.add(new Document("$match", new Document("categoriaNombre", categoria)));
        }

        // Proyección opcional para devolver toda la información del producto
        pipeline.add(new Document("$project", new Document()
                .append("id", 1)
                .append("nombre", 1)
                .append("costoBodega", 1)
                .append("precioUnitario", 1)
                .append("presentación", 1)
                .append("cantidad", 1)
                .append("unidadMedida", 1)
                .append("volumenEmpaque", 1)
                .append("pesoEmpaque", 1)
                .append("fechaExpiración", 1)
                .append("codigoBarras", 1)
                .append("categoriaNombre", 1)));

        // Ejecutar el pipeline en la colección "productos"
        return mongoTemplate.getCollection("productos").aggregate(pipeline).into(new ArrayList<>());
    }
}
