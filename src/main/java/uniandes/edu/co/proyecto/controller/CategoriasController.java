package uniandes.edu.co.proyecto.controller;


import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;








@RestController
@RequestMapping("/bares")
public class CategoriasController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear un nuevo bar
    @PostMapping("/new/save")
    public ResponseEntity<String> crearBar(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.save(categoria);
            return new ResponseEntity<>("Categoria creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {;
            return new ResponseEntity<>("Error al crear el bar: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    
}