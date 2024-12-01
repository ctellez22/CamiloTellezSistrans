package uniandes.edu.co.proyecto.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Producto;
import org.slf4j.Logger;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;
import uniandes.edu.co.proyecto.repository.FiltroRepository;
import uniandes.edu.co.proyecto.repository.ProductoRepository;
import uniandes.edu.co.proyecto.repository.ProductoRepositoryCustom;

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
    

    @Autowired
    private FiltroRepository filtroRepository;

    
    

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
        // Determinar si el valor proporcionado es un ObjectId válido
        Document productoConCategoria;
        if (ObjectId.isValid(idOrNombre)) {
            logger.info("El parámetro es un ObjectId válido. Buscando por _id.");
            productoConCategoria = productoRepositoryCustom.obtenerProductoConCategoriaPorId(new ObjectId(idOrNombre));
        } else {
            logger.info("El parámetro no es un ObjectId válido. Buscando por nombre.");
            productoConCategoria = productoRepositoryCustom.obtenerProductoConCategoriaPorNombre(idOrNombre);
        }

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




    @PutMapping("/update/{id}")
public ResponseEntity<String> actualizarProducto(@PathVariable String id, @RequestBody Map<String, Object> datos) {
    try {
        // Verificar si el producto existe en la base de datos
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (!productoExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto con ID " + id + " no encontrado.");
        }

        // Extraer datos del cuerpo de la solicitud
        String nombre = (String) datos.get("nombre");
        int costoBodega = (int) datos.get("costoBodega");
        int precioUnitario = (int) datos.get("precioUnitario");
        String presentación = (String) datos.get("presentación");
        int cantidad = (int) datos.get("cantidad");
        String unidadMedida = (String) datos.get("unidadMedida");
        double volumenEmpaque = (double) datos.get("volumenEmpaque");
        double pesoEmpaque = (double) datos.get("pesoEmpaque");
        Date fechaExpiración = new Date((long) datos.get("fechaExpiración")); // Convertir de timestamp a Date
        String codigoBarras = (String) datos.get("codigoBarras");
        String categoriaNombre = (String) datos.get("categoriaNombre");

        // Llamar al método del repositorio para actualizar el producto
        productoRepository.actualizarProducto(
                id, nombre, costoBodega, precioUnitario, presentación,
                cantidad, unidadMedida, volumenEmpaque, pesoEmpaque,
                fechaExpiración, codigoBarras, categoriaNombre
        );

        return ResponseEntity.ok("Producto actualizado exitosamente");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar el producto: " + e.getMessage());
    }
}




    @GetMapping("/filtro")
    public ResponseEntity<List<Document>> obtenerProductos(
            @RequestParam(required = false) Integer rangoPrecioMin,
            @RequestParam(required = false) Integer rangoPrecioMax,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaVencimiento,
            @RequestParam(required = false) Boolean vencimientoPosterior,
            @RequestParam(required = false) String categoria) {

        List<Document> productos = filtroRepository.obtenerProductosPorCaracteristicas(
                rangoPrecioMin, rangoPrecioMax, fechaVencimiento, vencimientoPosterior, categoria);

        return ResponseEntity.ok(productos);
    }



}

    



