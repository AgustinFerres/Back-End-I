package com.example.proyecto_Integrador.TurnoTest;

import com.example.proyecto_Integrador.DTO.TurnoDTO;
import com.example.proyecto_Integrador.entity.Domicilio;
import com.example.proyecto_Integrador.entity.Odontologo;
import com.example.proyecto_Integrador.entity.Paciente;
import com.example.proyecto_Integrador.entity.Turno;
import com.example.proyecto_Integrador.repository.PacienteRepository;
import com.example.proyecto_Integrador.service.OdontologoService;
import com.example.proyecto_Integrador.service.PacienteService;
import com.example.proyecto_Integrador.service.TurnoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    public void guardarTurno() {

        Domicilio domicilio = new Domicilio("salterain", 1231, "montevideo", "montevideo");
        Paciente paciente = new Paciente("Agustin", "Ferres", "5123141", LocalDate.of(2022,11,13), domicilio, "prueba@gmail.com");
        pacienteService.guardar(paciente);

        Odontologo odontologo = new Odontologo(1234,"Gonzalo", "Alvarez");
        odontologoService.guardar(odontologo);

        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setIdOdontologo(odontologo.getId());
        turnoDTO.setIdPaciente(paciente.getId());
        turnoDTO.setFecha(LocalDate.of(2022,11,13));
        turnoService.guardar(turnoDTO);

        TurnoDTO resultadoActual = turnoService.buscar(1L).get();
        Assertions.assertEquals(LocalDate.of(2022,11,13), resultadoActual.getFecha());
    }
    @Test
    @Order(2)
    public void actualizarTurno () {
        Domicilio domicilio = new Domicilio("salterain", 1231, "montevideo", "montevideo");
        Paciente paciente = new Paciente("Agustin", "Ferres", "5123141", LocalDate.of(2022,11,13), domicilio, "prueba@gmail.com");
        pacienteService.guardar(paciente);

        Odontologo odontologo = new Odontologo(1234,"Gonzalo", "Alvarez");
        odontologoService.guardar(odontologo);

        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(1L);
        turnoDTO.setIdOdontologo(odontologo.getId());
        turnoDTO.setIdPaciente(paciente.getId());
        turnoDTO.setFecha(LocalDate.of(2022,11,14));
        turnoService.actualizar(turnoDTO);

        List<TurnoDTO> resultadoActual = turnoService.buscarTodos();
        Assertions.assertEquals(LocalDate.of(2022,11,14), resultadoActual.get(0).getFecha());
    }
    @Test
    @Order(3)
    public void borrarTurno () {
        turnoService.eliminar(1L);

        List<TurnoDTO> resultadoActual = turnoService.buscarTodos();

        Assertions.assertEquals(0,resultadoActual.size());
    }
}
