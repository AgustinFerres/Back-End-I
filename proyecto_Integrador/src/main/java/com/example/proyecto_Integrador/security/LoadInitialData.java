package com.example.proyecto_Integrador.security;

import com.example.proyecto_Integrador.entity.Usuario;
import com.example.proyecto_Integrador.entity.UsuarioRole;
import com.example.proyecto_Integrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoadInitialData implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();

        String passwordCifrrada = cifrador.encode("1234");

        Usuario usuario = new Usuario("Gonza", "shaloo", "alvarezgonzalo00@gmail.com", passwordCifrrada, UsuarioRole.ROLE_USER);
        Usuario usuarioAdmin = new Usuario("Agus", "me", "admin@admin.com", passwordCifrrada, UsuarioRole.ROLE_ADMIN);

        usuarioRepository.save(usuario);
        usuarioRepository.save(usuarioAdmin);
    }
}
