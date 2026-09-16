package com.estudio.agendamento.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.estudio.agendamento.Model.Usuario;
import com.estudio.agendamento.Repository.UsuarioRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        //busca o usuário no banco pelo email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(usuario.getEmail());

        //vai conferir se o usuario e a senha existem no bd
        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok(usuarioOpt.get());
        }

        //se a senha ou email forem incorretos mostra o erro
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
}