import { Tige } from './../model/Tige';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';

const rootUrl = 'http://localhost:8181/atelier-chant-de-fleur/tiges';

@Injectable({
  providedIn: 'root'
})
export class TigeService {

  allTigesSource = new BehaviorSubject([]);
  currentAllTiges = this.allTigesSource.asObservable();

  constructor(private httpClient: HttpClient) { }

  getAll(): void{
    this.httpClient.get<Tige[]>(rootUrl + '/').subscribe(resp => {
      this.allTigesSource.next(resp);
    });
  }

  getById(id: number): Observable<Tige> {
    return this.httpClient.get<Tige>(rootUrl + '/' + id);
  }

  create(tige: Tige): Observable<Tige> {
    return this.httpClient.post<Tige>(rootUrl + '/' + tige.id + '/edit', tige);
  }

  update(tige: Tige): Observable<Tige> {
    return this.httpClient.put<Tige>(rootUrl + '/' + tige.id + '/edit', tige);
  }
}
