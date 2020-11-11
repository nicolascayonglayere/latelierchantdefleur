import { SnackbarSuccessComponent } from './../../layout/snackbar/snackbar-success/snackbar-success.component';
import { Composition } from './../../model/Composition';
import { ElementComposition } from './../../model/ElementComposition';
import { Component, Input, OnInit } from '@angular/core';
import { CoefficientVariableService } from '../../services/coefficient-variable.service';
import { CompositionService } from '../../services/composition.service';
import {faCheckCircle, faMinusCircle, faCaretDown, faTrash } from '@fortawesome/free-solid-svg-icons';
import { NgForm } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'app-bandeau-bouquet',
  templateUrl: './bandeau-bouquet.component.html',
  styleUrls: ['./bandeau-bouquet.component.css']
})
export class BandeauBouquetComponent implements OnInit {
  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  faCheckCircle = faCheckCircle;
  faMinusCircle = faMinusCircle;
  faCaretDown = faCaretDown;
  faTrash = faTrash;

  coutIntrant: number = 0;
  coutRevient: number = 0;
  coutMarge: number = 0;
  coutTva: number = 0;
  tempsTravail: number = 0;
  tauxHoraire: number;
  margeActuelle: number;
  tvaActuelle: number;

  elementCompo: any[] = [];
  qteTigeTot: number = 0;

  messageModifOk: boolean = false;
  isCollapsed = false;

  constructor(private coefficientVariableService: CoefficientVariableService,
              private compositionService: CompositionService,
              private modalService: NgbModal,
              private router: Router,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.coefficientVariableService.getAll().subscribe(resp => {
      this.tauxHoraire = resp.tauxHoraire / 10;
      this.margeActuelle = resp.marge / 10;
      this.tvaActuelle = resp.tva / 10;
    });
    this.compositionService.currentElements.subscribe(resp => {
      if (resp.id){
        this.elementCompo.push(resp);
        if (resp.type === 'TIGE'){
          this.qteTigeTot = this.qteTigeTot + resp.quantite;
        }
        this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
        this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
        this.coutMarge = this.calculMarge(this.coutRevient);
        this.coutTva = this.calculTva(this.coutMarge);
      }
    });
  }

  calculCoutIntrant(elt: ElementComposition[]): number{
    let coutIntrantCalcul = 0;
    elt.forEach(e => {
      coutIntrantCalcul = coutIntrantCalcul + (e.prixUnitaire * e.quantite);
    });
    return coutIntrantCalcul;
  }

  calculCoutRevient(coutIntrant: number, tempTravail: number): number{
    return coutIntrant + (tempTravail * this.tauxHoraire);
  }

  calculMarge(coutRevient: number): number{
    return coutRevient * this.margeActuelle;
  }

  calculTva(marge: number): number {
    return marge * this.tvaActuelle;
  }

  onSubmitTempsTravail(form: NgForm): void {
    this.tempsTravail = form.value['tempsTravail'];
    if (this.elementCompo.length > 0){
      this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
      this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
      this.coutMarge = this.calculMarge(this.coutRevient);
      this.coutTva = this.calculTva(this.coutMarge);
    }
}

openVerticallyCentered(content): void {
  this.messageModifOk = false;
  this.modalService.open(content, { centered: true });
}

onSubmitTauxHoraire(form: NgForm): void {
  this.tauxHoraire = form.value['tauxHoraire'];
  if (this.elementCompo.length > 0){
    this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
    this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
    this.coutMarge = this.calculMarge(this.coutRevient);
    this.coutTva = this.calculTva(this.coutMarge);
  }
  this.messageModifOk = true;
}

onSubmitMarge(form: NgForm): void {
  this.margeActuelle = form.value['marge'];
  if (this.elementCompo.length > 0){
    this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
    this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
    this.coutMarge = this.calculMarge(this.coutRevient);
    this.coutTva = this.calculTva(this.coutMarge);
  }
  this.messageModifOk = true;
}

onSubmitTva(form: NgForm): void {
  this.tvaActuelle = form.value['tva'];
  if (this.elementCompo.length > 0){
    this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
    this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
    this.coutMarge = this.calculMarge(this.coutRevient);
    this.coutTva = this.calculTva(this.coutMarge);
  }
  this.messageModifOk = true;
}

onClickRemoveElt(element: ElementComposition): void{
  console.log(element);
  this.elementCompo = this.elementCompo.filter(e => e !== element);
  console.log(this.elementCompo);
  if (this.elementCompo.length > 0){
    if (element.type === 'TIGE'){
      this.qteTigeTot = this.qteTigeTot - element.quantite;
    }
    this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
    this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
    this.coutMarge = this.calculMarge(this.coutRevient);
    this.coutTva = this.calculTva(this.coutMarge);
  }else {
    this.coutIntrant = 0;
    this.coutRevient = 0;
    this.coutMarge = 0;
    this.coutTva = 0;
    this.tempsTravail = 0;
    this.qteTigeTot = 0;
  }
}

onClikResetCompo(): void{
  this.elementCompo = [];
  this.coutIntrant = 0;
  this.coutRevient = 0;
  this.coutMarge = 0;
  this.coutTva = 0;
  this.tempsTravail = 0;
  this.qteTigeTot = 0;
}

onClickSaveComposition(): void{
  const compositionToSave = new Composition();
  compositionToSave.id = 0;
  compositionToSave.dateCreation = new Date();
  compositionToSave.dureeCreation = this.tempsTravail;
  compositionToSave.prixUnitaire = this.coutTva;
  compositionToSave.tiges = this.elementCompo.filter(e => e.type === 'TIGE');
  compositionToSave.materiaux = this.elementCompo.filter(e => e.type === 'MATERIAU');
  this.compositionService.save(compositionToSave).subscribe(resp => {
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

}
