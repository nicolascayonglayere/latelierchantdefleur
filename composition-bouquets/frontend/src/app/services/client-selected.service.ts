import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Client} from '../model/Client';


@Injectable({
  providedIn: 'root'
})
export class ClientSelectedService {
  clientSelected = new BehaviorSubject(new Client());
  currentClientSelected = this.clientSelected.asObservable();

  constructor() { }

  recuperationClientSelected(client: Client): void{
    this.clientSelected.next(client);
  }


}
