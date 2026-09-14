package com.estudio.agendamento.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudio.agendamento.Model.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Busca por nome ou documento para atender ao RF04
    List<Cliente> findByNomeContainingIgnoreCaseOrDocumentoContaining(String nome, String documento);
}