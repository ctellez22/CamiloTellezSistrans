package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.repository.ProveedorRepository;

import java.util.Map;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Crear un nuevo proveedor
    @PostMapping("/new/save")
    public ResponseEntity<String> insertarProveedor(@RequestBody Proveedor proveedor) {
        try {
            

            // Insertar el nuevo proveedor
            proveedorRepository.insert(proveedor); // Usando save() o insert() para guardar el proveedor
            return new ResponseEntity<>("Proveedor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el proveedor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un proveedor existente por su ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> actualizarProveedor(@PathVariable String id, @RequestBody Map<String, Object> datos) {
        try {
            // Verificar si el proveedor existe
            if (!proveedorRepository.existsById(id)) {
                return new ResponseEntity<>("Proveedor no encontrado con el ID: " + id, HttpStatus.NOT_FOUND);
            }

            // Extraer los parámetros del cuerpo de la solicitud
            String nit = (String) datos.get("nit");
            String nombre = (String) datos.get("nombre");
            String direccion = (String) datos.get("direccion");
            String nombreContacto = (String) datos.get("nombreContacto");
            String telefonoContacto = (String) datos.get("telefonoContacto");

            // Llamar al método para actualizar el proveedor
            proveedorRepository.actualizarProveedor(id, nit, nombre, direccion, nombreContacto, telefonoContacto);

            return new ResponseEntity<>("Proveedor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el proveedor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
