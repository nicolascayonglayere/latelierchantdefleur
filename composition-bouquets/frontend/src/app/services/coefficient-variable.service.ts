import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CoefficientVariable } from '../model/CoefficientVariable';

const rootUrl = 'http://localhost:8181/atelier-chant-de-fleur/coefficients';

@Injectable({
  providedIn: 'root'
})
export class CoefficientVariableService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<CoefficientVariable> {
    return this.httpClient.get<CoefficientVariable>(rootUrl);
  }
}
