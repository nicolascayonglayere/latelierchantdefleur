import { Tige } from './../model/Tige';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const rootUrl = 'http://localhost:8181/atelier-chant-de-fleur/tiges';

@Injectable({
  providedIn: 'root'
})
export class TigeService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Tige[]> {
    return this.httpClient.get<Tige[]>(rootUrl + '/');
  }

  getById(id: number): Observable<Tige> {
    return this.httpClient.get<Tige>(rootUrl + '/' + id);
  }

  create(tige: Tige): void {
    this.httpClient.post(rootUrl + '/' + tige.id + '/edit', tige).subscribe(resp =>{
      console.log('create succes', resp);
    });
  }

  update(tige: Tige): void {
    this.httpClient.put(rootUrl + '/' + tige.id + '/edit', tige).subscribe(resp =>{
      console.log('update success ', resp);
    });
  }
}
