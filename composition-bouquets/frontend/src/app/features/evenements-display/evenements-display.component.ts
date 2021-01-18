import { EvenementService } from './../../services/evenement.service';
import { Component, OnInit } from '@angular/core';
import { Evenement } from 'src/app/model/Evenement';
import { PageEvent } from '@angular/material/paginator';
import { faInfoCircle, faTrash } from '@fortawesome/free-solid-svg-icons';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { SnackbarSuccessComponent } from 'src/app/layout/snackbar/snackbar-success/snackbar-success.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-evenements-display',
  templateUrl: './evenements-display.component.html',
  styleUrls: ['./evenements-display.component.css']
})
export class EvenementsDisplayComponent implements OnInit {
  faInfoCircle = faInfoCircle;
  faTrash = faTrash;

  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  tsEvt: Evenement[] = [];
  evtResearch: Evenement[] = [];

  pageIndex = 0;
  page: PageEvent; // = 1;
  pageSize = 10;
  pageSizeOptions = [10, 25, 50];
  length: number;
  lowValue = 0;
  highValue = 10;

  constructor(private evtService: EvenementService, private snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.evtService.getAll();
    this.evtService.currentAllEvenement.subscribe(resp => {
      this.tsEvt = resp;
      this.length = this.tsEvt.length;
      this.evtResearch = this.tsEvt;
    });
  }

  onChangeResearch(e: any): void{
    this.evtResearch = [];
    this.tsEvt.forEach(t => {
      if (t.nom?.toLowerCase().startsWith(e.target.value.toLowerCase())){
        this.evtResearch.push(t);
      }
    });
  }

  getPaginatorData(event: PageEvent): PageEvent{
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
}

onClikDeleteEvt(id: number): void {
  this.evtService.delete(id).subscribe(resp => {
    this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Suppression effectuée !'
    });
    this.evtService.getAll();
    this.evtService.currentAllEvenement.subscribe(evt => {
      this.tsEvt = evt;
      this.length = this.tsEvt.length;
      this.evtResearch = this.tsEvt;
    });
  }, err => {
    this.snackBar.openFromComponent(SnackbarSuccessComponent, {
      ...this.configFailed,
      data: 'Erreur lors de la suppression !'
    });
  });
}

}
