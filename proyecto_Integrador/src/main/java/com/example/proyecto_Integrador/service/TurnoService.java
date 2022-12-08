package com.example.proyecto_Integrador.service;

import com.example.proyecto_Integrador.DTO.TurnoDTO;
import com.example.proyecto_Integrador.entity.Turno;
import com.example.proyecto_Integrador.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    public TurnoDTO guardar(TurnoDTO turnoDTO){
        Turno turnoGuardado = turnoRepository.save(turnoDTOATurno(turnoDTO));
        return turnoATurnoDTO(turnoGuardado);
    }

    public Optional<TurnoDTO> buscar(Long id){
       Optional<Turno> turnoBuscado = turnoRepository.findById(id);
       if (turnoBuscado.isPresent()){
           return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
       }
       return Optional.empty();
    }

    public void actualizar(TurnoDTO turnoDTO){
        turnoRepository.save(turnoDTOATurno(turnoDTO));
    }

    public void eliminar(Long id){
        turnoRepository.deleteById(id);
    }

    public List<TurnoDTO> buscarTodos (){
        List<TurnoDTO> turnoDTOList = new ArrayList<>();
        List<Turno> turnos = turnoRepository.findAll();
        for (Turno turno : turnos) {
            turnoDTOList.add(turnoATurnoDTO(turno));
        }
        return turnoDTOList;
    }


    private TurnoDTO turnoATurnoDTO (Turno t){
        TurnoDTO turnoDTO = new TurnoDTO();

        turnoDTO.setId(t.getId());
        turnoDTO.setFecha(t.getFecha());
        turnoDTO.setIdPaciente(t.getPaciente().getId());
        turnoDTO.setIdOdontologo(t.getOdontologo().getId());

        return turnoDTO;
    }

    private Turno turnoDTOATurno (TurnoDTO t){
        Turno turno = new Turno();

        turno.setId(t.getId());
        turno.setFecha(t.getFecha());
        turno.setPaciente(pacienteService.buscar(t.getIdPaciente()).get());
        turno.setOdontologo(odontologoService.buscar(t.getIdOdontologo()).get());

        return turno;
    }
}
