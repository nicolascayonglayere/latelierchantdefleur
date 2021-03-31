import {Component, OnInit} from '@angular/core';
import {ElementComposition} from '../../model/ElementComposition';
import {Client} from '../../model/Client';
import {ClientService} from '../../services/client.service';
import {Composition} from '../../model/Composition';
import {CompositionSelectedService} from '../../services/composition-selected.service';
import {SnackbarSuccessComponent} from '../../layout/snackbar/snackbar-success/snackbar-success.component';
import {CompositionService} from '../../services/composition.service';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {CompositionEvenement} from '../../model/CompositionEvenement';

@Component({
  selector: 'app-creation-composition',
  templateUrl: './creation-composition.component.html',
  styleUrls: ['./creation-composition.component.css']
})
export class CreationCompositionComponent implements OnInit {
  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };
  clients: Client[] = [];
  compositionDetail: Composition;
  titre: string;


  constructor(private clientService: ClientService,
              private compoSelectedService: CompositionSelectedService,
              private compositionService: CompositionService,
              private snackBar: MatSnackBar,
              private router: Router) {  }

  ngOnInit(): void {
    this.compoSelectedService.currentCompoSelected.subscribe(resp => {
      if (resp.id){
        this.compositionDetail = resp;
        this.titre = 'Modification de la compostion ' + resp.nom;
      }else{
        this.resetComposition();
        this.titre = 'Création de la composition';
      }
    });
    this.clientService.getAll();
    this.clientService.currentClients.subscribe(resp => {
      this.clients = resp;
    });
  }

  selectClient(event: Client): void{
    // this.clientSelected = event;
  }

  ajouterElement(event: ElementComposition): void{
    this.compositionDetail.elements.push(event);
    // this.cdr.markForCheck();
  }

  // selectComposition(event: Composition): void {
  //   this.compositionDetail = event;
  // }
  saveComposition(event: Composition): void {
    console.log('SAVE --> ', event);
    this.compositionService.save(event).subscribe(resp => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configSuccess,
          data: 'Enregistrement effectué !'
        });
        this.router.navigate(['/atelier-chant-de-fleur', 'compositions']);
      },
      error => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configFailed,
          data: 'Erreur lors de la sauvegarde !'
        });
      });
  }

  saveCompositionInEvt(event: CompositionEvenement): void {
    console.log('SAVE IN EVT --> ', event);
    this.compositionService.saveInEvt(event.compo, event.idEvt).subscribe(resp => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configSuccess,
          data: 'Enregistrement effectué !'
        });
        this.router.navigate(['/atelier-chant-de-fleur', 'evenements', event.idEvt]);
      },
      error => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configFailed,
          data: 'Erreur lors de la sauvegarde !'
        });
      });
  }

  updateComposition(event: Composition): void {
    console.log('UPDATE --> ', event);
    this.compositionService.update(event).subscribe(resp => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configSuccess,
          data: 'Enregistrement effectué !'
        });
        this.router.navigate(['/atelier-chant-de-fleur', 'compositions']);
      },
      error => {
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configFailed,
          data: 'Erreur lors de la sauvegarde !'
        });
      });
  }

  resetCompo(): void {
    this.resetComposition();
  }

  private resetComposition(): void{
    this.compositionDetail = new Composition();
    this.compositionDetail.id = 0;
    this.compositionDetail.tva = 0;
    this.compositionDetail.marge = 0;
    this.compositionDetail.tauxHoraire = 0;
    this.compositionDetail.dureeCreation = 0;
    this.compositionDetail.elements = [];
    this.titre = 'Création de la composition';
  }


}
