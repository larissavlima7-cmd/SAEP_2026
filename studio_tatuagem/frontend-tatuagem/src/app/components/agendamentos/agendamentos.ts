import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AgendamentoService } from '../../services/agendamento';
import { ClienteService } from '../../services/cliente';
import { RecursoService } from '../../services/recurso';

@Component({
  selector: 'app-agendamentos',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './agendamentos.html',
  styleUrl: './agendamentos.css'
})
export class AgendamentosComponent implements OnInit {
  agendamentos: any[] = [];
  clientes: any[] = [];
  recursos: any[] = [];

  agendamentoAtual: any = {
    cliente: { id: null },
    recurso: { id: null },
    data: '',
    hora: '',
    observacao: ''
  };

  mensagemErro: string = '';
  mensagemSucesso: string = '';

  constructor(
    private agendamentoService: AgendamentoService,
    private clienteService: ClienteService,
    private recursoService: RecursoService
  ) {}

  ngOnInit() {
    this.carregarAgendamentos();
    this.carregarClientes();
    this.carregarRecursos();
  }

  carregarAgendamentos() {
    this.agendamentoService.listar().subscribe({
      next: (dados) => this.agendamentos = dados
    });
  }

  carregarClientes() {
    this.clienteService.listar().subscribe({
      next: (dados) => this.clientes = dados
    });
  }

  carregarRecursos() {
    this.recursoService.listar().subscribe({
      next: (dados) => this.recursos = dados
    });
  }

  salvarAgendamento() {
    this.mensagemErro = '';
    this.mensagemSucesso = '';

    this.agendamentoService.salvar(this.agendamentoAtual).subscribe({
      next: () => {
        this.mensagemSucesso = 'Agendamento realizado com sucesso!';
        this.carregarAgendamentos();
        this.agendamentoAtual = {
          cliente: { id: null },
          recurso: { id: null },
          data: '',
          hora: '',
          observacao: ''
        };
      },
      error: (err) => {
        if (err.status === 409) {
          this.mensagemErro = err.error || 'Este recurso/profissional já possui agendamento neste horário!';
        } else {
          this.mensagemErro = 'Erro ao salvar o agendamento.';
        }
      }
    });
  }
}