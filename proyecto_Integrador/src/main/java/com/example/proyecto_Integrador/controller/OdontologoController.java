package com.example.proyecto_Integrador.controller;

import com.example.proyecto_Integrador.entity.Odontologo;
import com.example.proyecto_Integrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<Odontologo> guardar (@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Odontologo>> buscar (@RequestParam("id") Integer id) {
            return ResponseEntity.ok(odontologoService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<String> actualizar (@RequestBody Odontologo odontologo) {
            odontologoService.actualizar(odontologo);
            return ResponseEntity.ok().body("Se actualizo el paciente de apellido: " + odontologo.getApellido());
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Integer id) {
            odontologoService.eliminar(id);
            return ResponseEntity.ok().body("Se elimino el odontologo de id: " + id);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos () {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}
