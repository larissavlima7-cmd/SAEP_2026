package com.estudio.agendamento.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudio.agendamento.Model.Cliente;

import java.util.List;

//interface de acesso com o CRUD
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Busca por nome ou documento
    List<Cliente> findByNomeContainingIgnoreCaseOrDocumentoContaining(String nome, String documento);
}