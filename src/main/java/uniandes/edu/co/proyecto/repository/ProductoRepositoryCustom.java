package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public ProductoRepositoryCustom(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Obtiene un producto por su ID o nombre con la categoría embebida.
     *
     * @param idOrNombre ID o nombre del producto.
     * @return Documento que contiene el producto y la categoría.
     */
    public Document obtenerProductoConCategoriaPorNombre(String idOrNombre) {
        List<Document> pipeline = List.of(
                // Filtrar por ID o nombre del producto
                new Document("$match", new Document(
                        new Document("nombre", idOrNombre))),
                // Realizar el lookup para traer la categoría asociada
                new Document("$lookup", new Document()
                        .append("from", "categorias") // Nombre de la colección de categorías
                        .append("localField", "categoriaNombre") // Campo en el producto que se une con el ID de la
                                                                 // categoría
                        .append("foreignField", "_id") // Campo en la categoría que coincide con el localField
                        .append("as", "categorias") // Nombre del campo que contendrá la categoría embebida
                ),
                // Descomponer el array de categoría (opcional, si se espera un único resultado)
                new Document("$unwind",
                        new Document("path", "$categorias").append("preserveNullAndEmptyArrays", true)));

        // Ejecutar la consulta en la colección "productos" y devolver el primer
        // resultado
        List<Document> resultado = mongoTemplate.getCollection("productos").aggregate(pipeline)
                .into(new java.util.ArrayList<>());

        // Retornar el primer documento encontrado o null si no existe
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    public Document obtenerProductoConCategoriaPorId(ObjectId id) {
        List<Document> pipeline = List.of(
                // Filtrar por el _id del producto
                new Document("$match", new Document("_id", id)),
                // Realizar el lookup para traer la categoría asociada
                new Document("$lookup", new Document()
                        .append("from", "categorias") // Nombre de la colección de categorías
                        .append("localField", "categoriaNombre") // Campo en el producto que se une con el ID de la
                                                                 // categoría
                        .append("foreignField", "_id") // Campo en la categoría que coincide con el localField
                        .append("as", "categorias") // Nombre del campo que contendrá la categoría embebida
                ),
                // Descomponer el array de categoría (opcional, si se espera un único resultado)
                new Document("$unwind",
                        new Document("path", "$categorias").append("preserveNullAndEmptyArrays", true)));

        // Ejecutar la consulta en la colección "productos" y devolver el primer
        // resultado
        List<Document> resultado = mongoTemplate.getCollection("productos").aggregate(pipeline)
                .into(new java.util.ArrayList<>());

        // Retornar el primer documento encontrado o null si no existe
        return resultado.isEmpty() ? null : resultado.get(0);
    }

}
