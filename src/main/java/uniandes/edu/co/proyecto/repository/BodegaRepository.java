package uniandes.edu.co.proyecto.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.InventarioItem;

public interface BodegaRepository extends MongoRepository<Bodega, String> {

    // Crear una nueva bodega
    //@Query("{ $insertOne: { id: ?0, nombre: ?1, tamaño: ?2, capacidad: ?3, inventario: ?4 } }")
    //void insertarBodega(String id, String nombre, double tamaño, double capacidad, List<InventarioItem> inventario);

    


}
