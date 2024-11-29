package uniandes.edu.co.proyecto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;
import uniandes.edu.co.proyecto.repository.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear un nuevo producto
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarProducto(@RequestBody Producto producto) {
        try {
            // Verificar si el categoriaId existe
            Optional<Categoria> categoriaOpt = categoriaRepository.findById(producto.getCategoria().getId());
            if (!categoriaOpt.isPresent()) {
                return new ResponseEntity<>("La categoría con ID " + producto.getCategoria().getId() + " no existe.", HttpStatus.BAD_REQUEST);
            }

            // Asignar la categoría existente al producto
            producto.setCategoria(categoriaOpt.get());
            productoRepository.save(producto);

            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el producto: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
