import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Fournisseur } from '../model/Fournisseur';

const rootUrl = 'http://127.0.0.1:8181/atelier-chant-de-fleur/fournisseurs';

@Injectable({
  providedIn: 'root'
})
export class FournisseurService {
  allFournisseursSource = new BehaviorSubject([]);
  currentAllFournisseur = this.allFournisseursSource.asObservable();

  constructor(private httpClient: HttpClient) { }

  getAll(): void{
    this.httpClient.get<Fournisseur[]>(rootUrl + '/').subscribe(resp =>{
      this.allFournisseursSource.next(resp);
    });
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
