package com.estudio.agendamento.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudio.agendamento.Model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}