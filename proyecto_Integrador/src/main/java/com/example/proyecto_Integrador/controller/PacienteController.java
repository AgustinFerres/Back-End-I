package com.example.proyecto_Integrador.controller;

import com.example.proyecto_Integrador.model.Paciente;
import com.example.proyecto_Integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Paciente> buscar (@RequestParam("id") Integer id) {
        Paciente pacienteBuscado = pacienteService.buscar(id);
        if (pacienteBuscado != null){
            return ResponseEntity.ok(pacienteBuscado);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/buscar/mail")
    public ResponseEntity<Paciente> buscar (@RequestParam("email") String string) {
        Paciente pacienteBuscado = pacienteService.buscarXEmail(string);
        if (pacienteBuscado != null){
            return ResponseEntity.ok(pacienteBuscado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<String> actualizar (@RequestBody Paciente paciente) {


        if (pacienteService.buscar(paciente.getId()) != null){
            pacienteService.actualizar(paciente);
            return ResponseEntity.ok().body("Se actualizo el paciente de apellido: " + paciente.getApellido());
        }
        return ResponseEntity.badRequest().body("El paciente de apellido " + paciente.getApellido() + " no se encuentra en la base de datos");

    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Integer id) {
        Paciente pacienteBuscado = pacienteService.buscar(id);
        if (pacienteBuscado != null){
            pacienteService.eliminar(id);
            return ResponseEntity.ok().body("Se elimino el paciente de apellido: " + pacienteBuscado.getApellido());
        }
        return ResponseEntity.badRequest().body("El paciente de apellido " + pacienteBuscado.getApellido() + " no se encuentra en la base de datos");
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos () {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
}
