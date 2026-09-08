package com.estudio.agendamento.controller;

import com.estudio.agendamento.model.Agendamento;
import com.estudio.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping
    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Agendamento agendamento) {
        // Validação da Regra Anti-Double-Booking (RF06) colocada direto no controller
        boolean conflito = agendamentoRepository.existsByRecursoIdAndDataAndHora(
                agendamento.getRecurso().getId(),
                agendamento.getData(),
                agendamento.getHora()
        );

        if (conflito) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("O recurso selecionado já possui um agendamento para esta data e hora.");
        }

        Agendamento salvo = agendamentoRepository.save(agendamento);
        return ResponseEntity.ok(salvo);
    }
}