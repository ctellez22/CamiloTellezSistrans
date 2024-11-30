package uniandes.edu.co.proyecto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Proveedor;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {

    // Crear un nuevo proveedor
    @Query("{ $insert: { 'id': ?0, 'nit': ?1, 'nombre': ?2, 'direccion': ?3, 'nombreContacto': ?4, 'telefonoContacto': ?5 } }")
    void insertarProveedor(String id, String nit, String nombre, String direccion, String nombreContacto, String telefonoContacto);

    
    // Si necesitas una consulta para actualizar, puedes usar:
    @Query("{ '_id': ?0 }")
    @Update("{ $set: { 'nit': ?1, 'nombre': ?2, 'direccion': ?3, 'nombreContacto': ?4, 'telefonoContacto': ?5 } }")
    void actualizarProveedor(String id, String nit, String nombre, String direccion, String nombreContacto, String telefonoContacto);
}
