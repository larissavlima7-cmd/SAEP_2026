package com.saep.backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saep.backend.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    //Método customizado para verificar o usuario e a senha {RF02}
    Optional<Usuario> findByLoginAndSenha(String login, String senha);

}