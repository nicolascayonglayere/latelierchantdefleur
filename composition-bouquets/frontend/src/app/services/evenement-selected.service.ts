import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Evenement} from '../model/Evenement';

@Injectable({
  providedIn: 'root'
})
export class EvenementSelectedService {

  evenementSelected = new BehaviorSubject(new Evenement());
  currentEvtSelected = this.evenementSelected.asObservable();

  constructor() { }

  recuperationEvenementSelected(evt: Evenement): void{
    this.evenementSelected.next(evt);
  }

  removeEvenementSelectd(): void{
    this.evenementSelected.unsubscribe();
    this.evenementSelected = new BehaviorSubject(new Evenement());
    this.currentEvtSelected = this.evenementSelected.asObservable();
  }
}
