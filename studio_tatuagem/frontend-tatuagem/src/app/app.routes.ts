import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { ClientesComponent } from './components/clientes/clientes';
import { AgendamentosComponent } from './components/agendamentos/agendamentos';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'clientes', component: ClientesComponent },
  { path: 'agendamentos', component: AgendamentosComponent }
];