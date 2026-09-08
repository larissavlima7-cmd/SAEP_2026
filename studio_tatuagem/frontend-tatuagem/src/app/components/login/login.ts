import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
  usuario = { email: '', senha: '' };
  mensagemErro = '';

  constructor(private authService: AuthService, private router: Router) {}

  fazerLogin() {
    this.authService.login(this.usuario).subscribe({
      next: (res) => {
        this.authService.salvarUsuario(res);
        this.router.navigate(['/agendamentos']);
      },
      error: () => {
        this.mensagemErro = 'E-mail ou senha inválidos!';
      }
    });
  }
}