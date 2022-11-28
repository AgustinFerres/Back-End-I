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
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;


    @PostMapping
    public ResponseEntity<Paciente> guardar (@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Paciente>> buscar (@RequestParam("id") Long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(id);

        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/buscar/mail")
    public ResponseEntity<Optional<Paciente>> buscar (@RequestParam("email") String string) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarXEmail(string);

        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<String> actualizar (@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(paciente.getId());
        if (pacienteBuscado.isPresent()){
            pacienteService.actualizar(paciente);
            return ResponseEntity.ok().body("Se actualizo el paciente de apellido: " + paciente.getApellido());
        }
        return ResponseEntity.badRequest().body("No existe un paciente con id = " + paciente.getId());
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminar(id);
            return ResponseEntity.ok().body("Se actualizo el paciente de apellido: " + pacienteBuscado.get().getApellido());
        }
        return ResponseEntity.badRequest().body("No existe un paciente con id = " + id );
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos () {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
}
