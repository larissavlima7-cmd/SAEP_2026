package com.estudio.agendamento.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.estudio.agendamento.Model.Recurso;
import com.estudio.agendamento.Repository.RecursoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
@CrossOrigin(origins = "*")
public class RecursoController {

    @Autowired
    private RecursoRepository recursoRepository;
//para mostarr todos os recursos cadastrados
    @GetMapping
    public List<Recurso> listar() {
        return recursoRepository.findAll();
    }
}