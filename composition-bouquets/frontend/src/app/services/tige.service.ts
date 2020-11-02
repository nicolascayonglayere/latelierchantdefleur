import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tige } from '../model/Tige';

const rootUrl = 'http://localhost:8181/atelier-chant-de-fleur/tiges';

@Injectable({
  providedIn: 'root'
})
export class TigeService {

  constructor(private httpClient: HttpClient) { }

  getAll() : Observable<Tige[]> {
    return this.httpClient.get<Tige[]>(rootUrl+'/');
  }
}
