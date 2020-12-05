import { EvenementService } from './../../../services/evenement.service';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CompositionEvenement } from 'src/app/model/CompositionEvenement';
import { Evenement } from 'src/app/model/Evenement';

@Component({
  selector: 'app-composition-add-dialog',
  templateUrl: './composition-add-dialog.component.html',
  styleUrls: ['./composition-add-dialog.component.css'],
})
export class CompositionAddDialogComponent {

  evenements: Evenement[];

  constructor(@Inject(MAT_DIALOG_DATA) public data: CompositionEvenement, private evtService: EvenementService
  ) {
    this.evtService.getAll();
    this.evtService.allEvenementSource.subscribe(resp =>{
      this.evenements = resp;
    });
   }

}
