import {CompositionEvenement} from '../../model/CompositionEvenement';
import {EvenementService} from '../../services/evenement.service';
import {Evenement} from '../../model/Evenement';
import {ElementComposition} from 'src/app/model/ElementComposition';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {CompositionAddDialogComponent} from './composition-add-dialog/composition-add-dialog.component';
import {Composition} from '../../model/Composition';
import {AfterViewChecked, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CompositionService} from '../../services/composition.service';
import {
  faAngleDoubleDown,
  faAngleDoubleUp,
  faCheckCircle,
  faMinusCircle,
  faTrash
} from '@fortawesome/free-solid-svg-icons';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';
import {Client} from 'src/app/model/Client';
import {CompositionSelectedService} from '../../services/composition-selected.service';

@Component({
  selector: 'app-bandeau-bouquet',
  templateUrl: './bandeau-bouquet.component.html',
  styleUrls: ['./bandeau-bouquet.component.css']
})
export class BandeauBouquetComponent implements OnInit, AfterViewChecked {
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
  faTrash = faTrash;

  coutIntrant = 0;
  coutRevient = 0;
  coutMarge = 0;
  coutTva = 0;

  tempsTravail = 0;
  heureTravail = '00';
  minuteTravail = '00';
  tauxHoraire: number;
  margeActuelle: number;
  tvaActuelle: number;

  elementCompo: any[] = [];
  qteTigeTot = 0;

  isCollapsed = false;

  timePicker: FormGroup;

  dialogRef: MatDialogRef<CompositionAddDialogComponent>;

  // forfaitMo = 0;
  // forfaitDplct = 0;

  clients: Client[] = [];
  // clientEvt: Client;

  @Input() bouquet: boolean;
  @Input() compositionDetail: Composition;
  @Input() evtDetail: Evenement;
  @Input() titre: string;
  // @Output() clientSelected = new EventEmitter<Client>();
  @Output() compoModif = new EventEmitter<Composition>();
  @Output() compoSave = new EventEmitter<Composition>();
  @Output() compoSaveEvt = new EventEmitter<CompositionEvenement>();
  @Output() compoReset = new EventEmitter<Composition>();

  constructor(private compositionService: CompositionService,
              private modalService: NgbModal,
              private router: Router,
              private snackBar: MatSnackBar,
              private formBuilder: FormBuilder,
              private dialog: MatDialog,
              private evtService: EvenementService,
              private compoSelectedService: CompositionSelectedService){ }

  ngOnInit(): void {
    this.initTimePicker();
    this.tauxHoraire = this.compositionDetail?.tauxHoraire;
    this.tvaActuelle = this.compositionDetail?.tva;
    this.margeActuelle = this.compositionDetail?.marge;
    this.tempsTravail = this.compositionDetail?.dureeCreation;
  }

  ngAfterViewChecked(): void{
    this.qteTigeTot = 0;
    this.compositionDetail?.elements?.forEach(e => this.qteTigeTot = this.qteTigeTot + e.quantite);
    this.onChangeCalcul();
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

  onSubmitTempsTravailHeure(event: any): void {
    if ( this.compositionDetail.elements.length > 0){
      this.tempsTravail += +event;
      this.compositionDetail.dureeCreation = this.tempsTravail;
      this.onChangeCalcul();
    }
  }

  onSubmitTempsTravailMinute(event: any): void {
    if ( this.compositionDetail.elements.length > 0){
      this.tempsTravail += +event / 60;
      this.compositionDetail.dureeCreation = this.tempsTravail;
      this.onChangeCalcul();
    }
  }

  onChangeCalcul(): void {
    if (this.compositionDetail){
      this.compositionDetail.tva = this.tvaActuelle;
      this.compositionDetail.marge = this.margeActuelle;
      this.compositionDetail.tauxHoraire = this.tauxHoraire;
      console.log('TEMPS TRAVAIL ', this.tempsTravail);
      if ( this.compositionDetail.elements?.length > 0){
        this.coutIntrant = this.calculCoutIntrant(this.compositionDetail.elements);
        this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
        this.coutMarge = this.calculMarge(this.coutRevient);
        this.coutTva = this.calculTva(this.coutMarge);
      }
    }
  }

  onClickRemoveElt(element: ElementComposition): void{
    this.elementCompo = this.compositionDetail.elements.filter((e => e !== element));
    if (this.elementCompo.length > 0){
      if (element.type === 'TIGE'){
        this.qteTigeTot = this.qteTigeTot - element.quantite;
      }
      this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
      this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
      this.coutMarge = this.calculMarge(this.coutRevient);
      this.coutTva = this.calculTva(this.coutMarge);
      this.compositionDetail.elements = this.elementCompo;
    }else {
      this.coutIntrant = 0;
      this.coutRevient = 0;
      this.coutMarge = 0;
      this.coutTva = 0;
      this.tempsTravail = 0;
      this.qteTigeTot = 0;
      this.compositionDetail.elements = [];
      // this.compoReset.emit(this.compositionDetail);
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
    this.compoSelectedService.removeCompoSelected();
    this.compositionDetail = new Composition();
    this.initTimePicker();
    this.compoReset.emit(this.compositionDetail);
  }

  onClickSaveComposition(): void{
    this.dialogRef = this.dialog.open(
      CompositionAddDialogComponent,
      {
        disableClose: true,
        data: this.constructionCompoDisplay(),
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
        compositionToSave.dureeCreation = this.compositionDetail.dureeCreation;
        compositionToSave.prixUnitaire = this.compositionDetail.prixUnitaire;
        compositionToSave.elements = this.compositionDetail.elements;
        compositionToSave.tva = this.compositionDetail.tva;
        compositionToSave.marge = this.compositionDetail.marge;
        compositionToSave.tauxHoraire = this.compositionDetail.tauxHoraire;
        compositionToSave.prixUnitaire = this.coutTva;
        compositionToSave.elements = this.compositionDetail.elements;
        if (result.idEvt === 0){
          this.compoSave.emit(compositionToSave);
        }else{
          const compoEvtToSave = new CompositionEvenement();
          compoEvtToSave.compo = compositionToSave;
          compoEvtToSave.idEvt = result.idEvt;
          this.compoSaveEvt.emit(compoEvtToSave);
        }
      });
  }

  onClickUpdateComposition(): void {
    this.dialogRef = this.dialog.open(
      CompositionAddDialogComponent,
      {
        disableClose: true,
        data: this.constructionCompoDisplay(),
        minWidth: 500,
      });
    this.dialogRef.afterClosed().subscribe((result) => {
      if (result === 'Cancel' || result === 'Close') {
        return;
      }
      const compositionToSave = new Composition();
      compositionToSave.id = this.compositionDetail.id;
      compositionToSave.nom = result.compo.nom;
      compositionToSave.dateCreation = new Date();
      compositionToSave.dureeCreation = this.compositionDetail.dureeCreation;
      compositionToSave.prixUnitaire = this.compositionDetail.prixUnitaire;
      compositionToSave.elements = this.compositionDetail.elements;
      compositionToSave.tva = this.compositionDetail.tva;
      compositionToSave.marge = this.compositionDetail.marge;
      compositionToSave.tauxHoraire = this.compositionDetail.tauxHoraire;
      compositionToSave.prixUnitaire = this.coutTva;
      compositionToSave.elements = this.compositionDetail.elements;
      this.compoModif.emit(compositionToSave);
    });
  }

  initTimePicker(): void {
    let heureValeur = 0;
    let minuteValeur = 0;
    if (this.compositionDetail?.dureeCreation){
      this.heureTravail = this.compositionDetail.dureeCreation.toString().split('.')[0];
      heureValeur = parseInt(this.compositionDetail.dureeCreation.toString().split('.')[0], 0);
      minuteValeur = (this.compositionDetail.dureeCreation * 60) - (heureValeur * 60);
      this.minuteTravail = minuteValeur.toString();
    } else {
      this.heureTravail = '00';
      this.minuteTravail = '00';
    }
  }

  private constructionCompoDisplay(): CompositionEvenement{
    const eltsCompoCopy = [];
    this.elementCompo.forEach(e => eltsCompoCopy.push(e));
    const compoDisplay = new CompositionEvenement();
    compoDisplay.compo = new Composition();
    compoDisplay.compo.id = this.compositionDetail ? this.compositionDetail.id : 0;
    compoDisplay.compo.nom = this.compositionDetail.nom ? this.compositionDetail.nom : '';
    compoDisplay.compo.dateCreation = new Date();
    compoDisplay.compo.dureeCreation = this.tempsTravail;
    compoDisplay.compo.prixUnitaire = this.coutTva;
    compoDisplay.compo.tva = this.tvaActuelle;
    compoDisplay.compo.marge = this.margeActuelle;
    compoDisplay.compo.tauxHoraire = this.tauxHoraire;
    compoDisplay.compo.elements = eltsCompoCopy;
    compoDisplay.idEvt = 0;
    return compoDisplay;
  }
}
