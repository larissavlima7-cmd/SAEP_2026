import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClienteService } from '../../services/cliente';

@Component({
  selector: 'app-clientes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './clientes.html',
  styleUrl: './clientes.css' 
})
export class ClientesComponent implements OnInit {
  clientes: any[] = [];
  clienteAtual: any = { nome: '', documento: '', telefone: '' };
  termoBusca: string = '';

  constructor(private clienteService: ClienteService) {}

  ngOnInit() {
    this.carregarClientes();
  }

  carregarClientes() {
    this.clienteService.listar(this.termoBusca).subscribe({
      next: (dados) => this.clientes = dados,
      error: (err) => console.error('Erro ao carregar', err)
    });
  }

  salvarCliente() {
    this.clienteService.salvar(this.clienteAtual).subscribe({
      next: () => {
        this.carregarClientes();
        this.clienteAtual = { nome: '', documento: '', telefone: '' }; // Limpa o formulário
      }
    });
  }

  editarCliente(cliente: any) {
    this.clienteAtual = { ...cliente }; // Copia os dados para o formulário
  }

  excluirCliente(id: number) {
    if (confirm('Deseja realmente excluir este cliente?')) {
      this.clienteService.deletar(id).subscribe({
        next: () => this.carregarClientes()
      });
    }
  }
}