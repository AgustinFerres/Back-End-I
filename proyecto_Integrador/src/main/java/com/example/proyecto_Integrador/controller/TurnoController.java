package com.example.proyecto_Integrador.controller;

import com.example.proyecto_Integrador.model.Turno;
import com.example.proyecto_Integrador.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Turno> buscar (@RequestParam("id") Integer id) {
        Turno turnoBuscado = turnoService.buscar(id);
        if (turnoBuscado != null){
            return ResponseEntity.ok(turnoBuscado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<String> actualizar (@RequestBody Turno turno) {


        if (turnoService.buscar(turno.getId()) != null){
            turnoService.actualizar(turno);
            return ResponseEntity.ok().body("Se actualizo el paciente de id: " + turno.getId());
        }
        return ResponseEntity.badRequest().body("El turno de id " + turno.getId() + " no se encuentra en la base de datos");

    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Integer id) {
        Turno turnoBuscado = turnoService.buscar(id);
        if (turnoBuscado != null){
            turnoService.eliminar(id);
            return ResponseEntity.ok().body("Se elimino el turno de id: " + turnoBuscado.getId());
        }
        return ResponseEntity.badRequest().body("El turno de id " + turnoBuscado.getId() + " no se encuentra en la base de datos");
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos () {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
}
