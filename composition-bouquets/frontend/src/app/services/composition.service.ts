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

  private elementsSource = new BehaviorSubject(new ElementComposition);
  currentElements = this.elementsSource.asObservable();

  constructor(private httpClient: HttpClient) { }

  recuperationElements(element: ElementComposition){
    this.elementsSource.next(element);
  }

  save(composition: Composition){
    this.httpClient.post(rootUrl + '/' + '0/edit', composition).subscribe(resp => console.log('Creation success ', resp));
  }

  getAll(): Observable<Composition[]>{
    return this.httpClient.get<Composition[]>(rootUrl + '/');
  }
}
