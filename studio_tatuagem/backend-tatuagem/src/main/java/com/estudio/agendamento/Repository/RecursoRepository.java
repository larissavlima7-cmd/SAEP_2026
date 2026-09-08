package com.estudio.agendamento.repository;

import com.estudio.agendamento.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {
}