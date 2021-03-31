import {BehaviorSubject} from 'rxjs';
import {Composition} from '../model/Composition';
import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class CompositionSelectedService {

  compositionSelected = new BehaviorSubject(new Composition());
  currentCompoSelected = this.compositionSelected.asObservable();

  constructor() {
  }

  recuperationCompoSelected(compo: Composition): void {
    this.compositionSelected.next(compo);
  }

  removeCompoSelected(): void {
    this.compositionSelected.unsubscribe();
    this.compositionSelected = new BehaviorSubject(new Composition());
    this.currentCompoSelected = this.compositionSelected.asObservable();
  }
}
