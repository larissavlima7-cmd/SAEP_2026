package com.estudio.agendamento.controller;

import com.estudio.agendamento.model.Recurso;
import com.estudio.agendamento.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
@CrossOrigin(origins = "*")
public class RecursoController {

    @Autowired
    private RecursoRepository recursoRepository;

    @GetMapping
    public List<Recurso> listar() {
        return recursoRepository.findAll();
    }
}