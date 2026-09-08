package com.estudio.agendamento.controller;

import com.estudio.agendamento.model.Cliente;
import com.estudio.agendamento.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar(@RequestParam(required = false) String busca) {
        if (busca != null && !busca.isEmpty()) {
            return clienteRepository.findByNomeContainingIgnoreCaseOrDocumentoContaining(busca, busca);
        }
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}