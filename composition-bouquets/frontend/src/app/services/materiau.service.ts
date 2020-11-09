import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DataTablesResponse } from '../model/DataTablesResponse';
import { Materiau } from '../model/Materiau';

const rootUrl = 'http://localhost:8181/atelier-chant-de-fleur/materiaux';

@Injectable({
  providedIn: 'root'
})
export class MateriauService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Materiau[]> {
    return this.httpClient.get<Materiau[]>(rootUrl + '/');
  }

  getAllDataTablesMateriaux(dataTablesParameters: any): Observable<DataTablesResponse> {
    // const params: HttpParams;
    // params.set('')
    return this.httpClient.get<DataTablesResponse>(rootUrl + '/datatables');
  }

  getById(id: number): Observable<Materiau>{
    return this.httpClient.get<Materiau>(rootUrl + '/' + id);
  }

  create(materiau: Materiau): void {
    this.httpClient.post(rootUrl + '/' + materiau.id + '/edit', materiau).subscribe(resp => {
      console.log('create succes', resp);
    });
  }

  update(materiau: Materiau): void {
    this.httpClient.put(rootUrl + '/' + materiau.id + '/edit', materiau).subscribe(resp => {
      console.log('update succes', resp);
    });
  }
}
