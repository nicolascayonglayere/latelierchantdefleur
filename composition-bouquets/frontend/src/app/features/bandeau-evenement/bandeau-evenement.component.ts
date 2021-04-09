import {AfterViewChecked, Component, Input, OnInit, Output, EventEmitter} from '@angular/core';
import {faAngleDoubleDown, faAngleDoubleUp, faMinusCircle, faTrash} from '@fortawesome/free-solid-svg-icons';
import {Evenement} from '../../model/Evenement';
import {CompositionCommande} from '../../model/CompositionCommande';
import {EvenementAddDialogComponent} from '../bandeau-bouquet/evenement-add-dialog/evenement-add-dialog.component';
import {SnackbarSuccessComponent} from '../../layout/snackbar/snackbar-success/snackbar-success.component';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';
import {CompositionService} from '../../services/composition.service';
import {EvenementService} from '../../services/evenement.service';
import { Client } from 'src/app/model/Client';

@Component({
  selector: 'app-bandeau-evenement',
  templateUrl: './bandeau-evenement.component.html',
  styleUrls: ['./bandeau-evenement.component.css']
})
export class BandeauEvenementComponent implements OnInit, AfterViewChecked {
  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  faMinusCircle = faMinusCircle;
  faAngleDoubleDown = faAngleDoubleDown;
  faAngleDoubleUp = faAngleDoubleUp;
  faTrash = faTrash;

  isCollapsed = false;

  elementCompo: CompositionCommande[];
  coutIntrant = 0;
  coutTva = 0;
  forfaitMo = 0;
  forfaitDplct = 0;

  dialogRefEvt: MatDialogRef<EvenementAddDialogComponent>;

  clientEvt: Client;

  @Input() bouquet: boolean;
  @Input() evtDetail: Evenement;
  @Input() clients: Client[];
  @Input() titre: string;
  @Output() evtReset = new EventEmitter<Evenement>();
  @Output() evtSave = new EventEmitter<Evenement>();
  @Output() evtUpdate = new EventEmitter<Evenement>();
  @Output() clientSelected = new EventEmitter<number>();

  constructor(private dialog: MatDialog,
              private router: Router,
              private snackBar: MatSnackBar,
              private compositionService: CompositionService,
              private evtService: EvenementService) { }

  ngOnInit(): void {
    this.coutTva = 0;

    if (this.evtDetail){
      console.log('EVENEMENT ', this.evtDetail);
      this.forfaitDplct = this.evtDetail.forfaitDplct;
      this.forfaitMo = this.evtDetail.forfaitMo;
      if(!this.clientEvt){
        this.clientEvt = new Client();
      }
      this.clientEvt.id = this.evtDetail.idClientRest;
      // this.clientSelected.emit(this.evtDetail.idClientRest);
    }
  }

  ngAfterViewChecked(): void{
    this.coutTva = 0;
    this.elementCompo = [];
    this.evtDetail?.compositions?.forEach(e => this.elementCompo.push(e));
    // this.clientEvt =  this.clientEvt;
    this.onChangeCalcul();
  }

  onClikResetCompo(): void{
    this.coutTva = 0;
    this.coutIntrant = 0;
    this.elementCompo = [];
    this.forfaitMo = 0;
    this.forfaitDplct = 0;
    this.evtReset.emit(new Evenement());
  }

  onClickRemoveElt(element: CompositionCommande): void{
    this.elementCompo = this.evtDetail.compositions.filter((e => e !== element));
    if (this.elementCompo.length > 0){
      this.evtDetail.compositions = this.elementCompo;
      this.onChangeCalcul();
    }else {
      this.onClikResetCompo();
    }
  }

  onChangeCalculMo(): void{
    this.coutTva = this.coutTva + this.forfaitMo;
  }

  onChangeCalculDplct(): void{
    this.coutTva = this.coutTva + this.forfaitDplct;
  }

  onChangeCalcul(): void {
    if (this.evtDetail){
        this.evtDetail.compositions.forEach(e => this.coutTva = this.coutTva + e.quantite * e.composition.prixUnitaire);
        this.coutIntrant = this.coutTva;
        this.onChangeCalculMo();
        this.onChangeCalculDplct();
    }
  }

  onClickSaveEvenement(): void{
    // const eltsCompoCopy = [];
    // this.compositionService.currentAllCompositions.subscribe(c => {
    //   this.elementCompo.forEach(e => {
    //     const compoCom = new CompositionCommande();
    //     compoCom.id = 0;
    //     if (eltsCompoCopy.find(compo => compo.composition.id === e.id) === undefined){
    //       compoCom.composition = c.find(compo => compo.id === e.id);
    //       compoCom.quantite = e.quantite;
    //       eltsCompoCopy.push(compoCom);
    //     }
    //   });
    // });
    console.log('CTRL -- ', this.forfaitDplct, this.forfaitMo);
    const evenementToSave = new Evenement();
    evenementToSave.dateCreation = new Date();
    evenementToSave.compositions = this.evtDetail.compositions;
    evenementToSave.idClientRest = this.clientEvt.id;
    // evenementToSave.forfaitMo = this.forfaitMo;
    // evenementToSave.forfaitDplct = this.forfaitDplct;
    if (this.evtDetail?.id){
      evenementToSave.id = this.evtDetail.id;
      evenementToSave.datePrevue = this.evtDetail.datePrevue;
      evenementToSave.nom = this.evtDetail.nom;
      evenementToSave.budget = this.evtDetail.budget;
    }else{
      evenementToSave.id = 0;
    }

    this.dialogRefEvt = this.dialog.open(
      EvenementAddDialogComponent,
      {
        disableClose: true,
        data: {evenement: evenementToSave,
                titre: 'Titre injecte'},
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
        this.evtSave.emit(evenementToSave);
      }else{
        evenementToSave.id = result.id;
        evenementToSave.dateCreation = result.dateCreation;
        evenementToSave.compositions = result.compositions;
        evenementToSave.forfaitMo = this.forfaitMo;
        evenementToSave.forfaitDplct = this.forfaitDplct;
        this.evtUpdate.emit(evenementToSave);
      }
    });
  }
  onChangeSelectClient(client: any): void{
    console.log('select client dans bandeau evt', client);
    const clientSelected = this.clients.find(c => c.id === +client.value);
    this.clientEvt = clientSelected;
    this.clientSelected.emit(+client.value);
  }

  onClickUpdateEvenement(): void {
    const evenementToSave = new Evenement();
    evenementToSave.dateCreation = new Date();
    evenementToSave.compositions = this.evtDetail.compositions;
    evenementToSave.idClientRest = this.clientEvt.id;
    evenementToSave.id = this.evtDetail.id;
    evenementToSave.datePrevue = this.evtDetail.datePrevue;
    evenementToSave.nom = this.evtDetail.nom;
    evenementToSave.budget = this.evtDetail.budget;

    this.dialogRefEvt = this.dialog.open(
      EvenementAddDialogComponent,
      {
        disableClose: true,
        data: {evenement: evenementToSave,
          titre: 'Confirmer la modification de l\'évènement ' + evenementToSave.nom},
        minWidth: 500,
      });
    this.dialogRefEvt.afterClosed().subscribe((result) => {
      if (result === 'Cancel' || result === 'Close') {
        return;
      }
      evenementToSave.datePrevue = result.datePrevue;
      evenementToSave.nom = result.nom;
      evenementToSave.budget = result.budget;
      evenementToSave.dateCreation = result.dateCreation;
      evenementToSave.compositions = result.compositions;
      evenementToSave.forfaitMo = this.forfaitMo;
      evenementToSave.forfaitDplct = this.forfaitDplct;
      this.evtUpdate.emit(evenementToSave);

    });
  }
}
