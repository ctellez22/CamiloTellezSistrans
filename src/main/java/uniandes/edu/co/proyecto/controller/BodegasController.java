package uniandes.edu.co.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.InventarioItem;
import uniandes.edu.co.proyecto.repository.BodegaRepository;
import uniandes.edu.co.proyecto.repository.ProductoRepository;

@RestController
@RequestMapping("/bodegas")
public class BodegasController {

    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Endpoint para insertar una nueva bodega
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarBodega(@RequestBody Bodega bodega) {
        try {
            // Verificar que todos los idProducto en el inventario existan
            List<InventarioItem> inventario = bodega.getInventario();
            for (InventarioItem item : inventario) {
                String idProducto = item.getIdProducto();
                // Verificar que el producto exista en la base de datos
                if (!productoRepository.existsById(idProducto)) {
                    return new ResponseEntity<>(
                            "Error: El producto con ID " + idProducto + " no existe.",
                            HttpStatus.BAD_REQUEST
                    );
                }
            }

            // Usamos save() para insertar la bodega
            bodegaRepository.save(bodega);

            return new ResponseEntity<>("Bodega creada exitosamente", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la bodega: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
