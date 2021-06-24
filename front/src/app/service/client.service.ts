import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Cliente } from '../client';
import { Clientvo } from '../clientvo';
@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private urlBase = "http://localhost:9090/client/";
  constructor(private http: HttpClient) { }

  

   getAll() {
     return this.http.get<Cliente[]>(this.urlBase + "listar");
   }

  

  createClient(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.urlBase + "guardar", cliente);
  }

  delete(id: number) {
    let url = this.urlBase + "eliminar/" + id;
    return this.http.delete(url);
  }

  editClient(cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(this.urlBase + "editar", cliente);
  }

  getbyid(id: number) {
    return this.http.get<Cliente>(this.urlBase + "buscarid/" + id);
  }

  getShareKey(value: String) {
    return this.http.get<Cliente[]>(this.urlBase + "buscarsharekey/" + value);
  }

  searchAdvance(clientevo: Clientvo): Observable<Cliente> {
    return this.http.post<Cliente>(this.urlBase + "advance", clientevo);
  }

  pagelista() {    
    return this.http.get<Cliente[]>(this.urlBase + "paginas/0/1").subscribe(res=><Cliente[]>res);  
  }

}




