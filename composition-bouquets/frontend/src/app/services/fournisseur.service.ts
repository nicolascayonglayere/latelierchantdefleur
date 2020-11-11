import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Fournisseur } from '../model/Fournisseur';

const rootUrl = 'http://localhost:8181/atelier-chant-de-fleur/fournisseurs';

@Injectable({
  providedIn: 'root'
})
export class FournisseurService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Fournisseur[]>{
    return this.httpClient.get<Fournisseur[]>(rootUrl + '/');
  }

  getById(id: number): Observable<Fournisseur>{
    return this.httpClient.get<Fournisseur>(rootUrl + '/' + id);
  }

  create(fournisseur: Fournisseur): Observable<Fournisseur>{
    return this.httpClient.post<Fournisseur>(rootUrl + '/' + fournisseur.id + '/edit', fournisseur);
  }

  update(fournisseur: Fournisseur): Observable<Fournisseur>{
    return this.httpClient.put<Fournisseur>(rootUrl + '/' + fournisseur.id + '/edit', fournisseur);
  }
}
