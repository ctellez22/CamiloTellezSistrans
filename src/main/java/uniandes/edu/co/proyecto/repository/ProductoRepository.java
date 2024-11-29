package uniandes.edu.co.proyecto.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {

    // Crear un nuevo producto
    @Query("{ $insert: { _id: ?0, nombre: ?1, costoBodega: ?2, precioUnitario: ?3, presentación: ?4, " +
           "cantidad: ?5, unidadMedida: ?6, volumenEmpaque: ?7, pesoEmpaque: ?8, fechaExpiración: ?9, " +
           "codigoBarras: ?10, categoriaId: ?11 } }")
    void insertarProducto(String id, String nombre, int costoBodega, int precioUnitario, String presentación, 
                          int cantidad, String unidadMedida, double volumenEmpaque, double pesoEmpaque, 
                          Date fechaExpiración, String codigoBarras, String categoriaId);

    // Consultar categoría por ID
    @Query("{'_id': ?0}")
    Optional<Producto> buscarPorId(String id);

    // Consultar categoría por nombre
    @Query("{'nombre': ?0}")
    Optional<Producto> buscarPorNombre(String nombre);

    
}
