package uniandes.edu.co.proyecto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.InventarioItem;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.modelo.Sucursal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrdenDeCompraRepository extends MongoRepository<OrdenDeCompra, String> {

    // Crear una nueva orden de compra
    @Query("{ $insert: { '_id': ?0, 'fechaCreacion': ?1, 'sucursal': ?2, 'proveedor': ?3, 'fechaEsperadaEntrega': ?4, 'estado': ?5, 'inventarioItems': ?6 } }")
    void insertarOrdenDeCompra(String id, Date fechaCreacion, Sucursal sucursal, Proveedor proveedor, Date fechaEsperadaEntrega, String estado, List<InventarioItem> inventarioItems);

    @Query("{ '_id': ?0 }")
    Optional<OrdenDeCompra> findById(String id);

    
}
