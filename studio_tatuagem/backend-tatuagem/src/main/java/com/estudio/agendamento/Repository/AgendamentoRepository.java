package com.estudio.agendamento.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estudio.agendamento.Model.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Substituído 'Object hora' por 'LocalTime hora'
    boolean existsByRecursoIdAndDataAndHora(Long recursoId, LocalDate data, LocalTime hora);
}