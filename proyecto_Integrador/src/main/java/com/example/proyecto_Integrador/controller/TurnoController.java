package com.example.proyecto_Integrador.controller;

import com.example.proyecto_Integrador.DTO.TurnoDTO;
import com.example.proyecto_Integrador.entity.Turno;
import com.example.proyecto_Integrador.exception.BadRequestException;
import com.example.proyecto_Integrador.exception.ResourceNotFoundException;
import com.example.proyecto_Integrador.service.OdontologoService;
import com.example.proyecto_Integrador.service.PacienteService;
import com.example.proyecto_Integrador.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping("/admin")
    public ResponseEntity<TurnoDTO> guardar (@RequestBody TurnoDTO turnoDTO) throws BadRequestException {
        if (pacienteService.buscar(turnoDTO.getIdPaciente()).isPresent() && odontologoService.buscar(turnoDTO.getIdOdontologo()).isPresent()){
            return ResponseEntity.ok(turnoService.guardar(turnoDTO));
        }
        throw new BadRequestException("No se puede registrar un turno cuando no existe un odontologo y/o paciente");
    }
    @GetMapping("/admin/buscar")
    public ResponseEntity<Optional<TurnoDTO>> buscar (@RequestParam("id") Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscar(id);
        if (turnoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoService.buscar(id));
        }
        throw new ResourceNotFoundException("No existe turno de id = " + id + " en la base de datos");
    }
    @PutMapping("/admin")
    public ResponseEntity<String> actualizar (@RequestBody TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscar(turnoDTO.getId());
        if (turnoBuscado.isPresent()){
            if (pacienteService.buscar(turnoDTO.getIdPaciente()).isPresent() && odontologoService.buscar(turnoDTO.getIdOdontologo()).isPresent()){
                turnoService.actualizar(turnoDTO);
                return ResponseEntity.ok().body("Se actualizo el turno de id: " + turnoDTO.getId());
            }
            throw new BadRequestException("No se puede actualizar un turno cuando no existe un odontologo y/o paciente");
        }
        throw new ResourceNotFoundException("No existe turno de id = " + turnoDTO.getId() + " en la base de datos");
    }
    @DeleteMapping("/admin/borrar")
    public ResponseEntity<String> eliminar (@RequestParam("id") Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscar(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminar(id);
            return ResponseEntity.ok().body("Se elimino el turno de id: " + id);

        }
        throw new ResourceNotFoundException("No existe turno de id = " + id + " en la base de datos");
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos () {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
}
