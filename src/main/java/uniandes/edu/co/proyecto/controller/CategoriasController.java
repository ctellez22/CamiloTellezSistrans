package uniandes.edu.co.proyecto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/new/save")
public ResponseEntity<String> insertarCategoria(@RequestBody Categoria categoria) {
    try {
        // Verificar si el ID de la categoría ya existe
        if (categoriaRepository.findById(categoria.getNombre()).isPresent()) {
            return new ResponseEntity<>("Error: Ya existe una categoría con el ID proporcionado", HttpStatus.CONFLICT);
        }
        
        // Guardar la nueva categoría
        categoriaRepository.save(categoria);
        return new ResponseEntity<>("Categoría creada exitosamente", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Error al crear la categoría: " + e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


    // 
    @GetMapping("/search")
    public ResponseEntity<Categoria> obtenerCategoria(@RequestParam String idOrNombre) {
        Optional<Categoria> categoria = categoriaRepository.buscarPorNombre(idOrNombre);
        // Optional<Categoria> categoria = categoriaRepository.buscarPorId(idOrNombre) ;

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