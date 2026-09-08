package com.estudio.agendamento.controller;

import com.estudio.agendamento.model.Usuario;
import com.estudio.agendamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok(usuarioOpt.get());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
}