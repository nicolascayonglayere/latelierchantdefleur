import { CompositionEvenement } from '../../model/CompositionEvenement';
import { EvenementService } from '../../services/evenement.service';
import { EvenementAddDialogComponent } from './evenement-add-dialog/evenement-add-dialog.component';
import { Evenement } from '../../model/Evenement';
import { ElementComposition } from 'src/app/model/ElementComposition';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CompositionAddDialogComponent } from './composition-add-dialog/composition-add-dialog.component';
import { SnackbarSuccessComponent } from '../../layout/snackbar/snackbar-success/snackbar-success.component';
import { Composition } from '../../model/Composition';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CompositionService } from '../../services/composition.service';
import {faCheckCircle, faMinusCircle, faCaretDown, faTrash, faCaretUp, faAngleDoubleDown, faAngleDoubleUp } from '@fortawesome/free-solid-svg-icons';
import { FormBuilder, NgForm, Validators, FormGroup, FormControl } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { ClientService } from 'src/app/services/client.service';
import { Client } from 'src/app/model/Client';
import {CompositionCommande} from '../../model/CompositionCommande';

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
  faAngleDoubleDown = faAngleDoubleDown;
  faAngleDoubleUp = faAngleDoubleUp;
  faCaretDown = faCaretDown;
  faCaretUp = faCaretUp;
  faTrash = faTrash;

  coutIntrant = 0;
  coutRevient = 0;
  coutMarge = 0;
  coutTva = 0;

  tempsTravail = 0;
  heureTravail = '00';
  minuteTravail = 0;
  tauxHoraire: number;
  margeActuelle: number;
  tvaActuelle: number;

  elementCompo: any[] = [];
  qteTigeTot = 0;

  messageModifOk = false;
  isCollapsed = false;
  isCollapsedTot = false;

  timePicker: FormGroup;
  forfaitForm: FormGroup;

  dialogRef: MatDialogRef<CompositionAddDialogComponent>;
  dialogRefEvt: MatDialogRef<EvenementAddDialogComponent>;

  bouquet = false;

  forfaitMo = 0;
  forfaitDplct = 0;

  clients: Client[] = [];
  clientEvt: Client;

  @Output() clientSelected = new EventEmitter<Client>();

  constructor(private compositionService: CompositionService,
              private modalService: NgbModal,
              private router: Router,
              private snackBar: MatSnackBar,
              private formBuilder: FormBuilder,
              private dialog: MatDialog,
              private evtService: EvenementService,
              private clientService: ClientService) { }

  ngOnInit(): void {
    this.initTimePicker();
    this.tvaActuelle = 0;
    this.margeActuelle = 0;
    this.tauxHoraire = 0;
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
    this.clientService.getAll();
    this.clientService.currentClients.subscribe(resp => {
      this.clients = resp;
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
    return (coutRevient * (this.margeActuelle / 100)) + coutRevient;
  }

  calculTva(marge: number): number {
    return (marge * (this.tvaActuelle / 100)) + marge;
  }

  onSubmitTempsTravail(): void {
    if (this.elementCompo.length > 0){
      const formValue = this.timePicker.value;
      const heure = formValue.heure === undefined ? 0 : formValue.heure;
      const minute = formValue.minute;
      this.minuteTravail = +minute;
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
    this.tauxHoraire = form.value.tauxHoraire;
    if (this.elementCompo.length > 0){
      this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
      this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
      this.coutMarge = this.calculMarge(this.coutRevient);
      this.coutTva = this.calculTva(this.coutMarge);
    }
    this.messageModifOk = true;
  }

  onSubmitMarge(form: NgForm): void {
    this.margeActuelle = form.value.marge;
    if (this.elementCompo.length > 0){
      this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
      this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
      this.coutMarge = this.calculMarge(this.coutRevient);
      this.coutTva = this.calculTva(this.coutMarge);
    }
    this.messageModifOk = true;
  }

  onSubmitTva(form: NgForm): void {
    this.tvaActuelle = form.value.tva;
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
    const eltsCompoCopy = [];
    this.elementCompo.forEach(e => eltsCompoCopy.push(e));
    const compoDisplay = new CompositionEvenement();
    compoDisplay.compo = new Composition();
    compoDisplay.compo.id = 0;
    compoDisplay.compo.dateCreation = new Date();
    compoDisplay.compo.dureeCreation = this.tempsTravail;
    compoDisplay.compo.prixUnitaire = this.coutTva;
    compoDisplay.compo.tva = this.tvaActuelle;
    compoDisplay.compo.marge = this.margeActuelle;
    compoDisplay.compo.tauxHoraire = this.tauxHoraire;
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
    compoDisplay.compo.elements = eltList;
    compoDisplay.idEvt = 0;
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
        compositionToSave.nom = result.compo.nom;
        compositionToSave.dateCreation = new Date();
        compositionToSave.dureeCreation = this.tempsTravail;
        compositionToSave.prixUnitaire = this.coutTva;
        compositionToSave.elements = this.elementCompo;
        compositionToSave.tva = this.tvaActuelle;
        compositionToSave.marge = this.margeActuelle;
        compositionToSave.tauxHoraire = this.tauxHoraire;
        if (result.idEvt === 0){
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
        }else{
          this.compositionService.saveInEvt(compositionToSave, result.idEvt).subscribe(resp => {
            this.snackBar.openFromComponent(SnackbarSuccessComponent, {
              ...this.configSuccess,
              data: 'Enregistrement effectué !'
            });
            this.router.navigate(['/atelier-chant-de-fleur', 'evenements', result.idEvt]);
          },
          error => {
            this.snackBar.openFromComponent(SnackbarSuccessComponent, {
              ...this.configFailed,
              data: 'Erreur lors de la sauvegarde !'
            });
          });
        }
      });
  }

  onClickSaveEvenement(): void{
    const eltsCompoCopy = [];
    this.compositionService.currentAllCompositions.subscribe(c => {
      this.elementCompo.forEach(e => {
        const compoCom = new CompositionCommande();
        compoCom.id = 0;
        if (eltsCompoCopy.find(compo => compo.composition.id === e.id) === undefined){
          compoCom.composition = c.find(compo => compo.id === e.id);
          compoCom.quantite = this.calculQte(this.elementCompo, e.id);
          console.log(compoCom);
          eltsCompoCopy.push(compoCom);
        }
        // else {
        //   compoCom.composition = c.find(compo => compo.id === e.id);
        //   compoCom.quantite = 1;
        // }

      });
    });

    const evenementToSave = new Evenement();
    evenementToSave.id = 0;
    evenementToSave.dateCreation = new Date();
    evenementToSave.compositions = eltsCompoCopy;
    evenementToSave.clientRest = this.clientEvt;
    console.log(evenementToSave);
    this.dialogRefEvt = this.dialog.open(
      EvenementAddDialogComponent,
      {
        disableClose: true,
        data: evenementToSave,
        minWidth: 500,
      });
    this.dialogRefEvt.afterClosed().subscribe((result) => {
        if (result === 'Cancel' || result === 'Close') {
          return;
        }

        evenementToSave.datePrevue = result.datePrevue;
        evenementToSave.nom = result.nom;
        evenementToSave.budget = result.budget;
        if (result.id === 0){
          evenementToSave.forfaitMo = this.forfaitMo;
          evenementToSave.forfaitDplct = this.forfaitDplct;
          this.evtService.save(evenementToSave).subscribe(resp => {
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
        }else{
          evenementToSave.id = result.id;
          evenementToSave.dateCreation = result.dateCreation;
          evenementToSave.compositions = result.compositions;
          evenementToSave.forfaitMo = result.forfaitMo;
          evenementToSave.forfaitDplct = result.forfaitDplct;
          this.evtService.update(evenementToSave).subscribe(resp => {
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
      });
  }

  initTimePicker(): void {
    this.timePicker = this.formBuilder.group({
      heure: new FormControl(0, [Validators.min(0), Validators.required]),
      minute: new FormControl(0, [Validators.min(0), Validators.max(59), Validators.required, Validators.maxLength(2)])
    });
  }

  onChangeCalculMo(): void{
    this.coutTva = this.coutTva + this.forfaitMo;
  }

  onChangeCalculDplct(): void{
    this.coutTva = this.coutTva + this.forfaitDplct;
  }

  onChangeSelectClient(id: any): void{
    const clientSelected = this.clients.find(c => c.id === +id.value);
    this.clientEvt = clientSelected;
    this.clientSelected.emit(clientSelected);
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


