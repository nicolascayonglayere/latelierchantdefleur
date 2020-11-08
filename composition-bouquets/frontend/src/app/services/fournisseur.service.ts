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
}
