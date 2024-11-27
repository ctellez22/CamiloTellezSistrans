package uniandes.edu.co.proyecto.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Producto;



public interface CategoriaRepository extends MongoRepository<Categoria, Integer> {

   
    // Crear una nueva categoria
    @Query("{ $insert: { _id: ?0, nombre: ?1, descripcion: ?2, caracteristicas_almacenamiento: ?3, lista_productos: ?4 } }")
    void insertarCategoria(int id, String nombre, String descripcion, String caracteristicas_almacenamiento, List<Producto> lista_productos);

    
}
