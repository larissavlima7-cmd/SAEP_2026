package com.estudio.agendamento.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.estudio.agendamento.Model.Agendamento;
import com.estudio.agendamento.Repository.AgendamentoRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping
    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Agendamento agendamento) {
        if (agendamento.getData() != null && agendamento.getData().isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não é possível realizar agendamentos para datas retroativas.");
        }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            if (!agendamentoRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("error", "Agendamento não encontrado."));
            }

            agendamentoRepository.deleteById(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Excluído com sucesso."));
        } catch (Exception e) {
            e.printStackTrace(); // Mostra o erro exato no terminal do Spring Boot
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}