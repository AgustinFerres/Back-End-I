package com.example.proyecto_Integrador.controller;

import com.example.proyecto_Integrador.entity.Paciente;
import com.example.proyecto_Integrador.exception.ResourceNotFoundException;
import com.example.proyecto_Integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/admin")
    public ResponseEntity<Paciente> guardar (@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }
    @GetMapping("/admin/buscar")
    public ResponseEntity<Optional<Paciente>> buscar (@RequestParam("id") Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(id);

        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }
        throw new ResourceNotFoundException("No se encontro un paciente de id = " + id);
    }
    @GetMapping("/admin/buscar/mail")
    public ResponseEntity<Optional<Paciente>> buscar (@RequestParam("email") String email) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarXEmail(email);

        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado);
        }
        throw new ResourceNotFoundException("No se encontro un paciente de mail = " + email);
    }
    @PutMapping("/admin")
    public ResponseEntity<String> actualizar (@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(paciente.getId());
        if (pacienteBuscado.isPresent()){
            pacienteService.actualizar(paciente);
            return ResponseEntity.ok().body("Se actualizo el paciente de apellido: " + paciente.getApellido());
        }
        throw new ResourceNotFoundException("No se encontro un paciente de id = " + paciente.getId());
    }
    @DeleteMapping("/admin/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(id);
        if (pacienteBuscado.isPresent()){
            pacienteService.eliminar(id);
            return ResponseEntity.ok().body("Se actualizo el paciente de apellido: " + pacienteBuscado.get().getApellido());
        }
        throw new ResourceNotFoundException("No existe un paciente con id = " + id);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos () {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
}
