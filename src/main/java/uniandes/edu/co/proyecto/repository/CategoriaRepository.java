package uniandes.edu.co.proyecto.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.Categoria;



public interface CategoriaRepository extends MongoRepository<Categoria, String> {

   
    // Crear una nueva categoria
    @Query("{ $insert: { _id: ?0, nombre: ?1, descripcion: ?2, caracteristicas_almacenamiento: ?3, lista_Id_productos: ?4 } }")
    void insertarCategoria(String id, String nombre, String descripcion, String caracteristicas_almacenamiento, List<String> lista_Id_productos);

    // Consultar categoría por ID
    @Query("{'_id': ?0}")
    Optional<Categoria> buscarPorId(String id);

    // Consultar categoría por nombre
    @Query("{'nombre': ?0}")
    Optional<Categoria> buscarPorNombre(String nombre);





}
