package com.estudio.agendamento.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudio.agendamento.Model.Agendamento;

import java.time.LocalDate;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByRecursoIdAndDataAndHora(Long recursoId, LocalDate data, Object hora);
}