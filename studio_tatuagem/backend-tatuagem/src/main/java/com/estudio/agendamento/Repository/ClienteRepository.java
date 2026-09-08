package com.estudio.agendamento.repository;

import com.estudio.agendamento.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Busca por nome ou documento para atender ao RF04
    List<Cliente> findByNomeContainingIgnoreCaseOrDocumentoContaining(String nome, String documento);
}