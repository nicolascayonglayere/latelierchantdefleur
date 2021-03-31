import {EvenementService} from '../../../services/evenement.service';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Evenement} from '../../../model/Evenement';
import {Component, Inject} from '@angular/core';

@Component({
  selector: 'app-evenement-add-dialog',
  templateUrl: './evenement-add-dialog.component.html',
  styleUrls: ['./evenement-add-dialog.component.css']
})
export class EvenementAddDialogComponent {

  evenements: Evenement[];
  // nouveau: boolean;
  nvelEvt: Evenement;
  evtExist: Evenement;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private evtService: EvenementService) {
    // console.log('DATA dialogBox', data);
    // this.nouveau = true;
    this.evtService.getAll();
    this.evtService.allEvenementSource.subscribe(resp => {
      this.evenements = resp;
    });
    this.nvelEvt = data.evenement;
    this.evtExist = new Evenement();
    this.evtExist.id = 0;
  }

  onChangeEvtExistant(id: number): void{
    this.data = this.evenements.find(e => e.id === +id);
    this.nvelEvt.compositions.forEach(e => {
      this.data.compositions.push(e);
    });
  }

  onClickNvelEvt(): void{
    this.data = this.nvelEvt;
    this.evtExist.id = 0;
  }

}
