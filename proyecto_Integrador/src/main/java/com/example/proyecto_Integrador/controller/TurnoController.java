package com.example.proyecto_Integrador.controller;

import com.example.proyecto_Integrador.entity.Turno;
import com.example.proyecto_Integrador.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;


    @PostMapping
    public ResponseEntity<Turno> guardar (@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Optional<Turno>> buscar (@RequestParam("id") Integer id) {
            return ResponseEntity.ok(turnoService.buscar(id));
    }

    @PutMapping
    public ResponseEntity<String> actualizar (@RequestBody Turno turno) {
            turnoService.actualizar(turno);
            return ResponseEntity.ok().body("Se actualizo el paciente de id: " + turno.getId());
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Integer id) {
            turnoService.eliminar(id);
            return ResponseEntity.ok().body("Se elimino el turno de id: " + id);
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos () {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
}
