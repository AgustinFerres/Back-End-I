package com.example.proyecto_Integrador.controller;

import com.example.proyecto_Integrador.entity.Paciente;
import com.example.proyecto_Integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;


    @PostMapping
    public ResponseEntity<Paciente> guardar (@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Paciente>> buscar (@RequestParam("id") Integer id) {
            return ResponseEntity.ok(pacienteService.buscar(id));
    }
    @GetMapping("/buscar/mail")
    public ResponseEntity<Optional<Paciente>> buscar (@RequestParam("email") String string) {
            return ResponseEntity.ok(pacienteService.buscarXEmail(string));
    }

    @PutMapping
    public ResponseEntity<String> actualizar (@RequestBody Paciente paciente) {
            pacienteService.actualizar(paciente);
            return ResponseEntity.ok().body("Se actualizo el paciente de apellido: " + paciente.getApellido());
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Integer id) {
            pacienteService.eliminar(id);
            return ResponseEntity.ok().body("Se elimino el paciente de id: " + id);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos () {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
}
