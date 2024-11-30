package uniandes.edu.co.proyecto.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.Categoria;



public interface CategoriaRepository extends MongoRepository<Categoria, String> {

   
    // Crear una nueva categoria
    @Query("{ $insert: {nombre: ?0, descripcion: ?1, caracteristicas_almacenamiento: ?2, lista_Id_productos: ?3 } }")
    void insertarCategoria(String nombre, String descripcion, String caracteristicas_almacenamiento, List<String> lista_Id_productos);

    // Consultar categoría por ID
    @Query("{'nombre': ?0}")
    Optional<Categoria> buscarPorNombre(String nombre);
    
    @Query("{ 'nombre': ?0 }")
    Optional<Categoria> findByNombre(String nombre);

    





}
