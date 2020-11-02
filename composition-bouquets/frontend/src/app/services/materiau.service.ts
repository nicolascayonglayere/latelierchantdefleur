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
    return this.httpClient.get<Materiau[]>(rootUrl+'/');
  }

  getAllDataTablesMateriaux(dataTablesParameters: any): Observable<DataTablesResponse> {
    // const params: HttpParams;
    // params.set('')
    return this.httpClient.get<DataTablesResponse>(rootUrl+'/datatables');
  }
}
