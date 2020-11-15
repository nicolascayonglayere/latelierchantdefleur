import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { DataTablesResponse } from '../model/DataTablesResponse';
import { Materiau } from '../model/Materiau';

const rootUrl = 'http://localhost:8181/atelier-chant-de-fleur/materiaux';

@Injectable({
  providedIn: 'root'
})
export class MateriauService {

  allMateriauxSource = new BehaviorSubject([]);
  currentAllMateriaux = this.allMateriauxSource.asObservable();

  constructor(private httpClient: HttpClient) { }

  getAll(): void {
    this.httpClient.get<Materiau[]>(rootUrl + '/').subscribe(resp => {
      this.allMateriauxSource.next(resp);
    });
  }

  getById(id: number): Observable<Materiau>{
    return this.httpClient.get<Materiau>(rootUrl + '/' + id);
  }

  create(materiau: Materiau): Observable<Materiau> {
    return this.httpClient.post<Materiau>(rootUrl + '/' + materiau.id + '/edit', materiau);
  }

  update(materiau: Materiau): Observable<Materiau> {
    return this.httpClient.put<Materiau>(rootUrl + '/' + materiau.id + '/edit', materiau);
  }
}
