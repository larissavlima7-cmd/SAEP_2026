package com.estudio.agendamento.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.estudio.agendamento.Model.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    // Checa se já existe agendamento no mesmo recurso, data e hora (RF06)
    boolean existsByRecursoIdAndDataAndHora(Long recursoId, LocalDate data, LocalTime hora);
}