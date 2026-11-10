package com.estudio.agendamento.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estudio.agendamento.Model.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;

//interface de acesso com o CRUD
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

// verifica se tem um agendamento com o mesmo recurso para a mesma data e horário
    boolean existsByRecursoIdAndDataAndHora(Long recursoId, LocalDate data, LocalTime hora);
}