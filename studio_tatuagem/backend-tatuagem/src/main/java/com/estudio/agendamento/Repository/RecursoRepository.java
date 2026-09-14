package com.estudio.agendamento.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.estudio.agendamento.Model.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {
}