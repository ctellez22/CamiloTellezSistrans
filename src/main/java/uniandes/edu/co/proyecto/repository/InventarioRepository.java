package uniandes.edu.co.proyecto.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventarioRepository {

    private final MongoTemplate mongoTemplate;

    public InventarioRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Añade el logger
    private static final Logger logger = LoggerFactory.getLogger(InventarioRepository.class);

    /**
     * Obtiene el inventario de productos para cada bodega de una sucursal específica.
     *
     * @param sucursalId El ID de la sucursal para la cual se desea el inventario.
     * @return Lista de documentos con la información del inventario por bodega.
     */
    public List<Document> obtenerInventarioPorSucursal(String sucursalId) {
        try {
            logger.info("Consultando inventario para la sucursal con ID: {}", sucursalId);

            List<Document> pipeline = new ArrayList<>();

            // Paso 1: Filtrar por la sucursal
            pipeline.add(new Document("$match", new Document("_id", new ObjectId(sucursalId))));
            logger.info("Pipeline después del match sucursal: {}", pipeline);

            // Paso 2: Convertir los IDs de las bodegas a ObjectId
            pipeline.add(new Document("$addFields", new Document("listaIdsBodegas", 
                new Document("$map", new Document()
                    .append("input", "$listaIdsBodegas")
                    .append("as", "id")
                    .append("in", new Document("$toObjectId", "$$id")) // Convertir cada id a ObjectId
                )
            )));
            logger.info("Pipeline después de convertir listaIdsBodegas a ObjectId: {}", pipeline);

            // Paso 3: Desnormalizar las bodegas
            pipeline.add(new Document("$lookup", new Document()
                    .append("from", "bodegas")
                    .append("localField", "listaIdsBodegas")
                    .append("foreignField", "_id")
                    .append("as", "bodegas")));
            logger.info("Pipeline después del lookup bodegas: {}", pipeline);

            // Paso 4: Convertir los IDs de los productos en inventario a ObjectId
            pipeline.add(new Document("$unwind", "$bodegas"));
            pipeline.add(new Document("$unwind", "$bodegas.inventario"));

            pipeline.add(new Document("$addFields", new Document("bodegas.inventario.idProducto", 
                new Document("$toObjectId", "$bodegas.inventario.idProducto")))); // Convertir el idProducto a ObjectId
            logger.info("Pipeline después de convertir idProducto a ObjectId: {}", pipeline);

            // Paso 5: Desnormalizar productos
            pipeline.add(new Document("$lookup", new Document()
                    .append("from", "productos")
                    .append("localField", "bodegas.inventario.idProducto")
                    .append("foreignField", "_id")
                    .append("as", "productoDetalle")));
            logger.info("Pipeline después del lookup productos: {}", pipeline);

            // Paso 6: Agrupar por bodega y juntar los productos en un solo documento
            pipeline.add(new Document("$group", new Document()
                    .append("_id", "$bodegas._id")
                    .append("bodegaNombre", new Document("$first", "$bodegas.nombre"))
                    .append("productos", new Document("$push", new Document()
                            .append("productoNombre", "$productoDetalle.nombre")
                            .append("cantidadDisponible", "$bodegas.inventario.cantidad")
                            .append("costoPromedio", "$bodegas.inventario.costoPromedio")
                    ))
            ));
            logger.info("Pipeline después del group por bodega: {}", pipeline);

            // Paso 7: Proyección final (opcional)
            pipeline.add(new Document("$project", new Document()
                    .append("_id", 0)
                    .append("bodegaId", "$_id")
                    .append("bodegaNombre", 1)
                    .append("productos", 1)
            ));
            logger.info("Pipeline después de la proyección: {}", pipeline);

            // Ejecución del pipeline
            List<Document> result = mongoTemplate.getCollection("sucursales")
                    .aggregate(pipeline)
                    .into(new ArrayList<>());

            logger.info("Resultado del pipeline: {}", result);

            return result;
        } catch (Exception e) {
            logger.error("Error al ejecutar el pipeline de inventario", e);
            return new ArrayList<>();
        }
    }
}
