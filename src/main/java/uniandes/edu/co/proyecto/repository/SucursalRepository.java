package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.Sucursal;

public interface SucursalRepository extends MongoRepository<Sucursal, String> {

    // Crear una nueva sucursal
    @Query("{ $insert: { '_id': ?0, 'nombre': ?1, 'tamaño': ?2, 'direccion': ?3, 'telefono': ?4, 'ciudadId': ?5, 'listaIdsBodegas': ?6 } }")
    void insertarSucursal(String id, String nombre, String tamaño, String direccion, String telefono, String ciudadId, List<String> listaIdsBodegas);
}
