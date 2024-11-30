package uniandes.edu.co.proyecto.controller;

import java.util.HashMap;
import java.util.Optional;

import org.bson.Document;
import org.slf4j.LoggerFactory;
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
import uniandes.edu.co.proyecto.modelo.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;
import uniandes.edu.co.proyecto.repository.ProductoRepository;
import uniandes.edu.co.proyecto.repository.ProductoRepositoryCustom;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);


    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepositoryCustom productoRepositoryCustom;
    

    // Crear un nuevo producto
    @PostMapping("/new/save")
public ResponseEntity<String> insertarProducto(@RequestBody Producto producto) {

    try {
        // Validar que el nombre de la categoría no sea nulo
        if (producto.getCategoriaNombre() == null || producto.getCategoriaNombre().isEmpty()) {
            return new ResponseEntity<>("El nombre de la categoría no puede ser nulo o vacío.", 
                                        HttpStatus.BAD_REQUEST);
        }

        // Verificar si el nombre de la categoría existe
        Optional<Categoria> categoriaOpt = categoriaRepository.findByNombre(producto.getCategoriaNombre());
        if (!categoriaOpt.isPresent()) {
            return new ResponseEntity<>("La categoría con nombre " + producto.getCategoriaNombre() + " no existe.",
                                        HttpStatus.BAD_REQUEST);
        }

        // Guardar el producto
        productoRepository.save(producto);

        return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Error al crear el producto: " + e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @GetMapping("/producto")
    public ResponseEntity<?> obtenerProductoConCategoria(@RequestParam String idOrNombre) {
        logger.info("Solicitud recibida para obtener producto con ID o nombre: {}", idOrNombre);
        try {
            Document productoConCategoria = productoRepositoryCustom.obtenerProductoConCategoria(idOrNombre);

            if (productoConCategoria != null) {
                logger.info("Producto encontrado: {}", productoConCategoria.toJson());
                return ResponseEntity.ok(productoConCategoria);
            } else {
                logger.warn("Producto no encontrado para ID o nombre: {}", idOrNombre);
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Producto no encontrado");
                errorResponse.put("idOrNombre", idOrNombre);
                errorResponse.put("mensaje", "No se encontró un producto con el ID o nombre proporcionado.");
                return ResponseEntity.status(404).body(errorResponse);
            }
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud para ID o nombre: {}", idOrNombre, e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error interno del servidor");
            errorResponse.put("detalle", e.getMessage());
            errorResponse.put("idOrNombre", idOrNombre);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    }



