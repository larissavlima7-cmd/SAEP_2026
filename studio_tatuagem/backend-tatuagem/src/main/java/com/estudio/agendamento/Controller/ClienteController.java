package com.estudio.agendamento.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.estudio.agendamento.Model.Cliente;
import com.estudio.agendamento.Repository.ClienteRepository;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    //Lista os clientes, e permite fazer uma busca pelo nome ou documento
    @GetMapping
    public List<Cliente> listar(@RequestParam(required = false) String busca) {
        if (busca != null && !busca.isEmpty()) {
            return clienteRepository.findByNomeContainingIgnoreCaseOrDocumentoContaining(busca, busca);
        }
        return clienteRepository.findAll(); // Retorna todos os registros caso não haja busca
    }

    @PostMapping
    //para cadastar um novo cliente
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //para editar os dados do cliente com base no ID
    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    //remove o cliente pelo id
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}