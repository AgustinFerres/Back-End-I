package com.example.proyecto_Integrador.service;

import com.example.proyecto_Integrador.entity.Usuario;
import com.example.proyecto_Integrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBusacado = usuarioRepository.findByEmail(username);
        if (usuarioBusacado.isPresent()){
            return usuarioBusacado.get();
        }
        throw new UsernameNotFoundException("Nombre de usuario no encontrado en la base de datos");
    }
}
