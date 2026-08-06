package com.saep.backend.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saep.backend.Model.Movimentacao;
import com.saep.backend.Repository.MovimentacaoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {

    private MovimentacaoRepository movimentacaoRepository;

    MovimentacaoController(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    } 
    
    //metodo para buscar as movimentações
    @GetMapping()
    public List<Movimentacao> buscarPorNome() {
        return movimentacaoRepository.findAll();
    }
    
    @PostMapping
    public Movimentacao registarMovimentacao(@RequestBody Movimentacao movimentacao){
        return movimentacaoRepository.save(movimentacao);
    }

    //outro meio de ser feito o POST, exibindo mensagem de erro
    // @PostMapping()
    // public ResponseEntity<?> registrar2(@RequestBody Movimentacao movimentacao) {
    //     //TODO: process POST request
    //     try {
    //         movimentacaoRepository.save(movimentacao);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    //     return null;
    // }


}
