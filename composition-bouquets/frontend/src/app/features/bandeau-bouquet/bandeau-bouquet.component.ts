import { ElementComposition } from 'src/app/model/ElementComposition';
// import { CompositionDisplay } from 'src/app/model/CompositionDisplay';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CompositionAddDialogComponent } from './composition-add-dialog/composition-add-dialog.component';
import { SnackbarSuccessComponent } from './../../layout/snackbar/snackbar-success/snackbar-success.component';
import { Composition } from './../../model/Composition';
import { Component, Input, OnInit } from '@angular/core';
import { CoefficientVariableService } from '../../services/coefficient-variable.service';
import { CompositionService } from '../../services/composition.service';
import {faCheckCircle, faMinusCircle, faCaretDown, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FormBuilder, NgForm, Validators, FormGroup, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { elementEventFullName } from '@angular/compiler/src/view_compiler/view_compiler';

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
  heureTravail = '00';
  minuteTravail = 0;
  tauxHoraire: number;
  margeActuelle: number;
  tvaActuelle: number;

  elementCompo: any[] = [];
  qteTigeTot: number = 0;

  messageModifOk: boolean = false;
  isCollapsed = false;

  timePicker: FormGroup;

  dialogRef: MatDialogRef<CompositionAddDialogComponent>;

  constructor(private coefficientVariableService: CoefficientVariableService,
              private compositionService: CompositionService,
              private modalService: NgbModal,
              private router: Router,
              private snackBar: MatSnackBar,
              private formBuilder: FormBuilder,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.initTimePicker();
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

  onSubmitTempsTravail(): void {
    // console.log(form.value['tempsTravail']);
    // this.tempsTravail = form.value['tempsTravail'];
    if (this.elementCompo.length > 0){
      const formValue = this.timePicker.value;
      const heure = formValue['heure'];
      const minute = formValue['minute'];
      this.minuteTravail = minute;
      this.tempsTravail = +minute / 60 + +heure;
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
  this.elementCompo = this.elementCompo.filter(e => e !== element);
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
  console.log(this.elementCompo);
  const eltsCompoCopy = [];
  this.elementCompo.forEach(e => eltsCompoCopy.push(e));
  const compoDisplay = new Composition();
  compoDisplay.id = 0;
  compoDisplay.dateCreation = new Date();
  compoDisplay.dureeCreation = this.tempsTravail;
  compoDisplay.prixUnitaire = this.coutTva;
  const eltList: ElementComposition[] = [];
  eltsCompoCopy.forEach(t => {
      const elt = new ElementComposition();
      elt.id = t.id;
      elt.nom = t.nom;
      elt.prixUnitaire = t.prixUnitaire;
      elt.type = t.type;
      elt.quantite = this.calculQte(eltsCompoCopy, elt.id);
      eltList.push(elt);
      eltsCompoCopy.forEach((tig, index) => {
        if (tig.id === elt.id && tig.nom === elt.nom){
          delete eltsCompoCopy[index];
        }
      });
    });
  compoDisplay.elements = eltList;
  console.log(this.elementCompo);
  this.dialogRef = this.dialog.open(
    CompositionAddDialogComponent,
    {
      disableClose: true,
      data: compoDisplay,
      minWidth: 500,
    });

  this.dialogRef.afterClosed().subscribe((result) => {
      if (result === 'Cancel' || result === 'Close') {
        return;
      }
      const compositionToSave = new Composition();
      compositionToSave.id = 0;
      compositionToSave.dateCreation = new Date();
      compositionToSave.dureeCreation = this.tempsTravail;
      compositionToSave.prixUnitaire = this.coutTva;
      compositionToSave.elements = this.elementCompo;
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
    });
}

initTimePicker(): void {
  this.timePicker = this.formBuilder.group({
    heure: new FormControl(0, [Validators.min(0), Validators.required]),
    minute: new FormControl(0, [Validators.min(0), Validators.max(59), Validators.required, Validators.maxLength(2)])
  });
}

  private calculQte(list: any, id: number): number{
    let qte = 0;
    list.forEach((element, i) => {
      if (element.id === id){
        qte = list[i].quantite + qte;
      }
    });
    return qte;
  }

}
