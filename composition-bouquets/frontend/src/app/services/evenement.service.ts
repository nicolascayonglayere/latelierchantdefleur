import { Observable, BehaviorSubject } from 'rxjs';
import { Evenement } from './../model/Evenement';
import { HttpClient, HttpParams, HttpResponse, } from '@angular/common/http';
import { Injectable } from '@angular/core';

const rootUrl = 'http://localhost:8181/atelier-chant-de-fleur/evenements';

@Injectable({
  providedIn: 'root'
})
export class EvenementService {

  allEvenementSource = new BehaviorSubject([]);
  currentAllEvenement = this.allEvenementSource.asObservable();

  constructor(private httpClient: HttpClient) { }

  save(evenement: Evenement): Observable<Evenement>{
    return this.httpClient.post<Evenement>(rootUrl, evenement);
  }

  update(evenement: Evenement): Observable<Evenement>{
    return this.httpClient.put<Evenement>(rootUrl, evenement);
  }

  getById(id: number): Observable<Evenement>{
    return this.httpClient.get<Evenement>(rootUrl + '/' + id);
  }

  getByIdCompo(id: number): Observable<Evenement[]>{
    let params = new HttpParams();
    params = params.append('id-compo', id.toString());
    return this.httpClient.get<Evenement[]>(rootUrl + '/', {params});
  }

  getAll(): void{
    this.httpClient.get<Evenement[]>(rootUrl + '/').subscribe(resp =>{
      this.allEvenementSource.next(resp);
    });
  }

  delete(id: number): Observable<string>{
    return this.httpClient.delete<string>(rootUrl + '/' + id);
  }

  downloadDevis(id: number): any {
    return this.httpClient.get(rootUrl + '/' + id + '/devis', {responseType: 'blob'});
  }
}
