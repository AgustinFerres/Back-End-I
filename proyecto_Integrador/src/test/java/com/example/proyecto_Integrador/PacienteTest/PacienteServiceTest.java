package com.example.proyecto_Integrador.PacienteTest;

import com.example.proyecto_Integrador.entity.Domicilio;
import com.example.proyecto_Integrador.entity.Paciente;
import com.example.proyecto_Integrador.service.PacienteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente() {
        Domicilio domicilio = new Domicilio("salterain", 1231, "montevideo", "montevideo");
        Paciente paciente = new Paciente("Agustin", "Ferres", "5123141", LocalDate.of(2022,11,13), domicilio, "prueba@gmail.com");
        pacienteService.guardar(paciente);

        Paciente resultadoActual = pacienteService.buscar(1L).get();
        Assertions.assertEquals("5123141", resultadoActual.getDni());
    }
    @Test
    @Order(2)
    public void actualizarPaciente () {
        Domicilio domicilio = new Domicilio("salterain", 1231, "montevideo", "montevideo");
        Paciente paciente = new Paciente("Agustin", "Ferres", "4951032", LocalDate.of(2022,11,13), domicilio, "prueba@gmail.com");
        pacienteService.guardar(paciente);
        pacienteService.actualizar(paciente);

        List<Paciente> resultadoActual = pacienteService.buscarTodos();
        Assertions.assertEquals("4951032", resultadoActual.get(0).getDni());
    }
    @Test
    @Order(3)
    public void buscarXemailPaciente () {
        pacienteService.buscarXEmail("prueba@gmail.com");

        Paciente resultadoActual = pacienteService.buscarXEmail("prueba@gmail.com").get();

        Assertions.assertEquals("4951032",resultadoActual.getDni());
    }@Test
    @Order(4)
    public void borrarPaciente () {
        pacienteService.eliminar(1L);

        List<Paciente> resultadoActual = pacienteService.buscarTodos();

        Assertions.assertEquals(0,resultadoActual.size());
    }
}
