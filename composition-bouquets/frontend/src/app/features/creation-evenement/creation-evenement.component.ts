import {Component, OnInit} from '@angular/core';
import {Evenement} from '../../model/Evenement';
import {CompositionCommande} from '../../model/CompositionCommande';
import {SnackbarSuccessComponent} from '../../layout/snackbar/snackbar-success/snackbar-success.component';
import {EvenementService} from '../../services/evenement.service';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {EvenementSelectedService} from '../../services/evenement-selected.service';
import { Client } from 'src/app/model/Client';
import {ClientService} from '../../services/client.service';

@Component({
  selector: 'app-creation-evenement',
  templateUrl: './creation-evenement.component.html',
  styleUrls: ['./creation-evenement.component.css']
})
export class CreationEvenementComponent implements OnInit {
  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };


  evtDetail: Evenement;
  clientDetail: Client;
  clients: Client[];
  titre: string;

  constructor(private evtService: EvenementService,
              private snackBar: MatSnackBar,
              private router: Router,
              private evtSelectedService: EvenementSelectedService,
              private clientService: ClientService) { }

  ngOnInit(): void {
    this.evtSelectedService.currentEvtSelected.subscribe(e => {
      if (e.id){
        this.evtDetail = e;
        // this.clientDetail = e.clientRest;
        this.titre = 'Modification de l\'évènement ' + e.nom;
      }else{
        this.resetEvt();
      }
    });
    this.clientService.getAll();
    this.clientService.currentClients.subscribe(resp => {
      this.clients = resp;
    });
  }

  ajouterComposition(event: CompositionCommande): void {
    this.evtDetail.compositions.push(event);
  }

  resetEvt(): void {
    this.resetEvenement();
  }

  saveEvt(event: Evenement): void {
    console.log('SAVE EVT -->', event);
    this.evtService.save(event).subscribe(resp => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configSuccess,
          data: 'Enregistrement effectué !'
        });
        this.router.navigate(['/atelier-chant-de-fleur', 'evenements', resp.id]);
      },
      error => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configFailed,
          data: 'Erreur lors de la sauvegarde !'
        });
      });
  }

  update(event: Evenement): void {
    console.log('UPDATE EVT -->', event);
    this.evtService.update(event).subscribe(resp => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configSuccess,
          data: 'Enregistrement effectué !'
        });
        this.router.navigate(['/atelier-chant-de-fleur', 'evenements', resp.id]);
      },
      error => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configFailed,
          data: 'Erreur lors de la sauvegarde !'
        });
      });
  }

  selectClient(event: Client): void {
    this.clientDetail = event;
  }

  private resetEvenement(): void{
    this.evtDetail = new Evenement();
    this.evtDetail.id = 0;
    this.evtDetail.compositions = [];
    this.titre = 'Création de l\'évènement';
  }



}
