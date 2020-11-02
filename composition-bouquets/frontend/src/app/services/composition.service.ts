import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ElementComposition } from '../model/ElementComposition';

@Injectable({
  providedIn: 'root'
})
export class CompositionService {

  private elementsSource = new BehaviorSubject(new ElementComposition);
  currentElements = this.elementsSource.asObservable();

  constructor() { }

  recuperationElements(element: ElementComposition){
    this.elementsSource.next(element);
  }
}
