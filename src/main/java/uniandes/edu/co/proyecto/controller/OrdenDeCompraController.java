package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.*;
import uniandes.edu.co.proyecto.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenes-de-compra")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Crear una nueva orden de compra
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarOrdenDeCompra(@RequestParam String idSucursal, 
                                                         @RequestParam String idProveedor, 
                                                         @RequestParam Date fechaEsperadaEntrega,
                                                         @RequestBody List<InventarioItem> inventarioItems) {
        try {
            // Obtener los objetos Sucursal y Proveedor por sus respectivos IDs
            Sucursal sucursal = sucursalRepository.findById(idSucursal).orElse(null);
            Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);

            // Verificar si la sucursal y proveedor existen
            if (sucursal == null) {
                return new ResponseEntity<>("Sucursal no encontrada con el ID: " + idSucursal, HttpStatus.NOT_FOUND);
            }
            if (proveedor == null) {
                return new ResponseEntity<>("Proveedor no encontrado con el ID: " + idProveedor, HttpStatus.NOT_FOUND);
            }

            // Crear una nueva orden de compra con los datos proporcionados
            OrdenDeCompra nuevaOrden = new OrdenDeCompra(sucursal, proveedor, fechaEsperadaEntrega, "vigente", inventarioItems);

            // Guardar la orden de compra en la base de datos
            ordenDeCompraRepository.save(nuevaOrden);

            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/search")
    public ResponseEntity<OrdenDeCompra> obtenerOrdenDeCompra(@RequestParam String id) {
        try {
            Optional<OrdenDeCompra> ordenDeCompra = ordenDeCompraRepository.findById(id);

            // Si no se encuentra la orden, devolvemos un 404
            if (ordenDeCompra.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Si se encuentra, devolvemos un 200 con la orden
            return ResponseEntity.ok(ordenDeCompra.get());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
