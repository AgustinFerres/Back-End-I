package com.example.proyecto_Integrador.controller;

import com.example.proyecto_Integrador.entity.Odontologo;
import com.example.proyecto_Integrador.exception.ResourceNotFoundException;
import com.example.proyecto_Integrador.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;
    private Logger logger = Logger.getLogger(OdontologoController.class);

    @PostMapping("/admin")
    public ResponseEntity<Odontologo> guardar (@RequestBody Odontologo odontologo) {

        logger.info("Se inicio una peticion para agregar un odontologo");
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping("/admin/buscar")
    public ResponseEntity<Optional<Odontologo>> buscar (@RequestParam("id") Long id) throws ResourceNotFoundException {

        logger.info("Se inicio una peticion de busqueda de un odontologo");

        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(id);

        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado);
        }
        throw new ResourceNotFoundException("No se encontro un odontologo de id = " + id);
    }
    @PutMapping("/admin")
    public ResponseEntity<String> actualizar (@RequestBody Odontologo odontologo) throws ResourceNotFoundException {

        logger.info("Se inicio una peticion para actualizar un odontologo");
        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            odontologoService.actualizar(odontologo);
            return ResponseEntity.ok().body("Se actualizo el odontologo de apellido: " + odontologo.getApellido());
        }
        logger.error("No se encontro un odontologo");
        throw new ResourceNotFoundException("No se encontro un odontologo de id = " + odontologo.getId());
    }

    @DeleteMapping("/admin/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Long id) throws ResourceNotFoundException {

        logger.info("Se inicio una peticion para borrar un odontologo");
        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminar(id);
            return ResponseEntity.ok().body("Se elimino el odontologo de apellido: " + odontologoBuscado.get().getApellido());
        }
        logger.error("No se encontro un odontologo");
        throw new ResourceNotFoundException("No existe un odontologo con id = " + id);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos () {

        logger.info("Se inicio una peticion para listar todos los odontologos");
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/admin/buscarXnombre")
    public ResponseEntity<Optional<Odontologo>> buscar (@RequestParam("nombreCompleto") String nombreCompleto) throws ResourceNotFoundException {

        logger.info("Se inicio una peticion de busqueda de un odontologo");
        List<String> nombre = Arrays.stream(nombreCompleto.split(" ")).toList();
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarXNombreYApellido(nombre.get(0), nombre.get(1));

        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoBuscado);
        }

        logger.error("No se encontro un odontologo");
        throw new ResourceNotFoundException("No se encontro un odontologo de nombre = " + nombre.get(0) + " " + nombre.get(1));
    }
}
