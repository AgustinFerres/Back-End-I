package com.example.proyecto_Integrador.controller;

import com.example.proyecto_Integrador.model.Odontologo;
import com.example.proyecto_Integrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Odontologo> buscar (@RequestParam("id") Integer id) {
        Odontologo odontologoBuscado = odontologoService.buscar(id);
        if (odontologoBuscado != null){
            return ResponseEntity.ok(odontologoBuscado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<String> actualizar (@RequestBody Odontologo odontologo) {


        if (odontologoService.buscar(odontologo.getId()) != null){
            odontologoService.actualizar(odontologo);
            return ResponseEntity.ok().body("Se actualizo el paciente de apellido: " + odontologo.getApellido());
        }
        return ResponseEntity.badRequest().body("El odontologo de apellido " + odontologo.getApellido() + " no se encuentra en la base de datos");

    }

    @DeleteMapping("/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Integer id) {
        Odontologo odontologoBuscado = odontologoService.buscar(id);
        if (odontologoBuscado != null){
            odontologoService.eliminar(id);
            return ResponseEntity.ok().body("Se elimino el odontologo de apellido: " + odontologoBuscado.getApellido());
        }
        return ResponseEntity.badRequest().body("El odontologo de apellido " + odontologoBuscado.getApellido() + " no se encuentra en la base de datos");
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos () {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}
