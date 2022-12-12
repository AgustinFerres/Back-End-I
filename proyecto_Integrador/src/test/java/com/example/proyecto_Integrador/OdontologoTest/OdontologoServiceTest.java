package com.example.proyecto_Integrador.OdontologoTest;

import com.example.proyecto_Integrador.entity.Odontologo;
import com.example.proyecto_Integrador.service.OdontologoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo() {
        Odontologo odontologo = new Odontologo(1234,"Agustin", "Ferres");
        odontologoService.guardar(odontologo);

        Odontologo resultadoActual = odontologoService.buscar(1L).get();
        Assertions.assertEquals(1234, resultadoActual.getMatricula());
    }
    @Test
    @Order(2)
    public void actualizarOdontologo () {
        Odontologo odontologo = new Odontologo(1L,4341,"Agustin", "Ferres");
        odontologoService.actualizar(odontologo);

        List<Odontologo> resultadoActual = odontologoService.buscarTodos();
        Assertions.assertEquals(4341, resultadoActual.get(0).getMatricula());
    }
    @Test
    @Order(3)
    public void buscarXnombreOdontologo () {

        Odontologo resultadoActual = odontologoService.buscarXNombreYApellido("Agustin", "Ferres").get();

        Assertions.assertEquals(4341,resultadoActual.getMatricula());
    }@Test
    @Order(4)
    public void borrarOdontologo () {
        odontologoService.eliminar(1L);

        List<Odontologo> resultadoActual = odontologoService.buscarTodos();

        Assertions.assertEquals(0,resultadoActual.size());
    }


}
