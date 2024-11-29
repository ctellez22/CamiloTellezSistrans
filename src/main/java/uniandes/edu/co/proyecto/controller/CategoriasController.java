package uniandes.edu.co.proyecto.controller;



import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;



@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear un nueva categoria
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return new ResponseEntity<>("Categoria creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear la categoria: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Endpoint para buscar por ID o nombre
    @GetMapping("/search")
    public ResponseEntity<Categoria> obtenerCategoria(@RequestParam String idOrNombre) {
        Optional<Categoria> categoria = categoriaRepository.buscarPorId(idOrNombre);
        //Optional<Categoria> categoria = categoriaRepository.buscarPorId(idOrNombre) ;

        /// Si no se encuentra por ID, buscamos por nombre
        if (categoria.isEmpty()) {
            categoria = categoriaRepository.buscarPorNombre(idOrNombre);
        }

        // Si no se encuentra la categoría, devolvemos un 404
        if (categoria.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Si se encuentra, devolvemos un 200 con la categoría*/
        return ResponseEntity.ok(categoria.get());
    }








}