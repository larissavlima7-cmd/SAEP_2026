import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
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

  dataMinima: string = new Date().toISOString().split('T')[0];

  agendamentoAtual: any = {
    cliente: { id: null },
    recursoId: null,
    macaId: null,
    data: '',
    hora: '',
    observacao: ''
  };

  mensagemErro: string = '';
  mensagemSucesso: string = '';

  constructor(
    private agendamentoService: AgendamentoService,
    private clienteService: ClienteService,
    private recursoService: RecursoService,
    private router: Router
  ) {}

  ngOnInit() {
    this.carregarAgendamentos();
    this.carregarClientes();
    this.carregarRecursos();
  }

  carregarAgendamentos() {
    this.agendamentoService.listar().subscribe({
      next: (dados) => (this.agendamentos = dados)
    });
  }

  carregarClientes() {
    this.clienteService.listar().subscribe({
      next: (dados) => (this.clientes = dados)
    });
  }

  carregarRecursos() {
    this.recursoService.listar().subscribe({
      next: (dados) => (this.recursos = dados)
    });
  }

  get profissionais() {
    return this.recursos.filter(
      (r) => r.tipo !== 'Maca' && r.tipo !== 'Equipamento'
    );
  }

  get macas() {
    return this.recursos.filter(
      (r) => r.tipo === 'Maca' || r.tipo === 'Equipamento'
    );
  }

  salvarAgendamento() {
    this.mensagemErro = '';
    this.mensagemSucesso = '';

    if (
      !this.agendamentoAtual.cliente?.id ||
      !this.agendamentoAtual.recursoId ||
      !this.agendamentoAtual.data ||
      !this.agendamentoAtual.hora
    ) {
      this.mensagemErro = 'Preencha o cliente, profissional, data e horário!';
      return;
    }

    if (this.agendamentoAtual.data < this.dataMinima) {
      this.mensagemErro = 'Não é possível realizar agendamentos para datas retroativas!';
      return;
    }

    const agendamentoProfissional = {
      cliente: { id: this.agendamentoAtual.cliente.id },
      recurso: { id: this.agendamentoAtual.recursoId },
      data: this.agendamentoAtual.data,
      hora: this.agendamentoAtual.hora,
      observacao: this.agendamentoAtual.observacao
    };

    this.agendamentoService.salvar(agendamentoProfissional).subscribe({
      next: () => {
        if (this.agendamentoAtual.macaId) {
          const agendamentoMaca = {
            cliente: { id: this.agendamentoAtual.cliente.id },
            recurso: { id: this.agendamentoAtual.macaId },
            data: this.agendamentoAtual.data,
            hora: this.agendamentoAtual.hora,
            observacao: `[Maca] ${this.agendamentoAtual.observacao || 'Reserva de espaço'}`
          };

          this.agendamentoService.salvar(agendamentoMaca).subscribe({
            next: () =>
              this.finalizarSucesso(
                'Agendamento do profissional e da maca realizados com sucesso!'
              ),
            error: (err) => {
              if (err.status === 409) {
                this.mensagemErro =
                  'Profissional agendado, mas a Maca selecionada já está ocupada neste horário!';
              } else {
                this.mensagemErro = 'Erro ao reservar a maca.';
              }
              this.carregarAgendamentos();
            }
          });
        } else {
          this.finalizarSucesso('Agendamento realizado com sucesso!');
        }
      },
      error: (err) => {
        if (err.status === 409) {
          this.mensagemErro =
            typeof err.error === 'string'
              ? err.error
              : 'Este profissional já possui agendamento neste horário!';
        } else if (err.status === 400) {
          this.mensagemErro = err.error || 'Data inválida para agendamento.';
        } else {
          this.mensagemErro = 'Erro ao processar o agendamento.';
        }
      }
    });
  }

  excluirAgendamento(id: number) {
  if (!id) {
    this.mensagemErro = 'ID do agendamento inválido!';
    return;
  }

  if (confirm('Deseja realmente excluir este agendamento?')) {
    this.mensagemErro = '';
    this.mensagemSucesso = '';

    this.agendamentoService.excluir(id).subscribe({
      next: () => {
        this.mensagemSucesso = 'Agendamento excluído com sucesso!';
        this.carregarAgendamentos();
      },
      error: (err) => {
        console.error('Erro na exclusão:', err);
        this.mensagemErro = 'Erro ao excluir o agendamento.';
      }
    });
  }
}

  private finalizarSucesso(msg: string) {
    this.mensagemSucesso = msg;
    this.carregarAgendamentos();
    this.agendamentoAtual = {
      cliente: { id: null },
      recursoId: null,
      macaId: null,
      data: '',
      hora: '',
      observacao: ''
    };
  }

  logout() {
    localStorage.removeItem('usuarioLogado');
    this.router.navigate(['/login']);
  }
}