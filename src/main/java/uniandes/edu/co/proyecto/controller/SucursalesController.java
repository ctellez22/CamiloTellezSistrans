package uniandes.edu.co.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repository.SucursalRepository;
import uniandes.edu.co.proyecto.repository.BodegaRepository; // Repositorio para validar bodegas

@RestController
@RequestMapping("/sucursales")
public class SucursalesController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private BodegaRepository bodegaRepository; // Repositorio para verificar existencia de bodegas

    // Endpoint para insertar una nueva sucursal
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarSucursal(@RequestBody Sucursal sucursal) {
        try {
            // Verificar que todos los ids de bodegas en listaIdsBodegas existan
            List<String> listaIdsBodegas = sucursal.getListaIdsBodegas();
            for (String idBodega : listaIdsBodegas) {
                Optional<Bodega> bodega = bodegaRepository.findById(idBodega);
                if (!bodega.isPresent()) {
                    return new ResponseEntity<>(
                            "Error: La bodega con ID " + idBodega + " no existe.",
                            HttpStatus.BAD_REQUEST
                    );
                }
            }

            // Guardar la nueva sucursal
            sucursalRepository.save(sucursal);
            return new ResponseEntity<>("Sucursal creada exitosamente", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la sucursal: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
