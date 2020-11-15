import { HttpClient } from '@angular/common/http';
import { Composition } from './../model/Composition';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ElementComposition } from '../model/ElementComposition';

const rootUrl = 'http://localhost:8181/atelier-chant-de-fleur/compositions';
@Injectable({
  providedIn: 'root'
})
export class CompositionService {

  elementsSource = new BehaviorSubject(new ElementComposition());
  currentElements = this.elementsSource.asObservable();

  allCompositionsSource = new BehaviorSubject([]);
  currentAllCompositions = this.allCompositionsSource.asObservable();

  constructor(private httpClient: HttpClient) { }

  recuperationElements(element: ElementComposition): void{
    this.elementsSource.next(element);
  }

  save(composition: Composition): Observable<Composition>{
    return this.httpClient.post<Composition>(rootUrl + '/' + '0/edit', composition);
  }

  getAll(): void{
    this.httpClient.get<Composition[]>(rootUrl + '/').subscribe(resp => {
      this.allCompositionsSource.next(resp);
    });
  }
}
