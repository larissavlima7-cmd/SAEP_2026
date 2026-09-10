import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private apiUrl = 'http://localhost:8080/api/clientes';

  constructor(private http: HttpClient) {}

  listar(busca: string = ''): Observable<any> {
    const url = busca ? `${this.apiUrl}?busca=${busca}` : this.apiUrl;
    return this.http.get(url);
  }

  salvar(cliente: any): Observable<any> {
    if (cliente.id) {
      return this.http.put(`${this.apiUrl}/${cliente.id}`, cliente);
    }
    return this.http.post(this.apiUrl, cliente);
  }

  deletar(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}