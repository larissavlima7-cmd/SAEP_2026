import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { ClientesComponent } from './components/clientes/clientes';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'clientes', component: ClientesComponent }
];