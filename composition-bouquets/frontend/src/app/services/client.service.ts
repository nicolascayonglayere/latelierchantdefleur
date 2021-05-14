import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Client } from '../model/Client';

const rootUrl = 'http://127.0.0.1:8181/atelier-chant-de-fleur/clients';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  allClientsSource = new BehaviorSubject([]);
  currentClients = this.allClientsSource.asObservable();

  constructor(private httpClient: HttpClient) { }

  recuperationClients(client: Client[]): void{
    this.allClientsSource.next(client);
  }

  getAll(): void{
    this.httpClient.get<Client[]>(rootUrl + '/').subscribe(resp => {
      this.allClientsSource.next(resp);
    });
  }

  getById(id: number): Observable<Client>{
    return this.httpClient.get<Client>(rootUrl + '/' + id);
  }

  create(client: Client): Observable<Client> {
    return this.httpClient.post<Client>(rootUrl + '/' + client.id + '/edit', client);
  }

  update(client: Client): Observable<Client> {
    return this.httpClient.put<Client>(rootUrl + '/' + client.id + '/edit', client);
  }
}
