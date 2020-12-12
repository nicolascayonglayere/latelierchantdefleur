import { CompositionDelete } from './../../model/CompositionDelete';
import { EvenementService } from './../../services/evenement.service';
import { ElementComposition } from './../../model/ElementComposition';
import { ImageCompositionService } from './../../services/image-composition.service';
import { CompositionAddImageComponent } from './composition-add-image/composition-add-image.component';
import { CompositionDisplay } from 'src/app/model/CompositionDisplay';
import { PageEvent } from '@angular/material/paginator';
import { CompositionService } from './../../services/composition.service';
import { Component, OnInit } from '@angular/core';
import { Composition } from 'src/app/model/Composition';
import { faEdit, faUpload, faPlus, faInfoCircle, faTrash } from '@fortawesome/free-solid-svg-icons';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router, RouterLink } from '@angular/router';
import { SnackbarSuccessComponent } from 'src/app/layout/snackbar/snackbar-success/snackbar-success.component';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { CompositionDelDialogComponent } from '../composition-del-dialog/composition-del-dialog.component';


@Component({
  selector: 'app-compositions-display',
  templateUrl: './compositions-display.component.html',
  styleUrls: ['./compositions-display.component.css']
})
export class CompositionsDisplayComponent implements OnInit {
  faInfoCircle = faInfoCircle;
  faEdit = faEdit;
  faUpload = faUpload;
  faPlus = faPlus;
  faTrash = faTrash;

  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  ttesComposition: CompositionDisplay[] = [];
  compositionResearch: CompositionDisplay[];

  compoEvt: ElementComposition[] = [];

  pageIndex = 0;
page: PageEvent; // = 1;
pageSize = 10;
pageSizeOptions = [10, 25, 50];
length: number;
lowValue = 0;
highValue = 10;

dialogRef: MatDialogRef<CompositionAddImageComponent>;
dialogRefDel: MatDialogRef<CompositionDelDialogComponent>;

  constructor(private compositionService: CompositionService, config: NgbPaginationConfig,
              private snackBar: MatSnackBar, private imgService: ImageCompositionService,
              private routerLink: Router, private dialog: MatDialog, private evtService: EvenementService) {
    config.boundaryLinks = true;
  }

  ngOnInit(): void {
    this.compositionService.getAll();
    this.compositionService.currentAllCompositions.subscribe(resp => {
      this.ttesComposition = [];
      this.compositionResearch = [];
      resp.forEach(c => this.ttesComposition.push(this.constructCompoDisplay(c)));
      this.ttesComposition.forEach(c => this.compositionResearch.push(c));
      this.length = this.ttesComposition.length;
    });
  }

  onChangeResearch(e: any): void{
    this.compositionResearch = [];
    this.ttesComposition.forEach(t => {
      if (t.nom?.toLowerCase().startsWith(e.target.value.toLowerCase())){
        this.compositionResearch.push(t);
      }
    });
  }

  getPaginatorData(event: PageEvent): PageEvent{
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
}

onClickAddImageDialog(composition: CompositionDisplay): void{
  this.routerLink.navigate(['atelier-chant-de-fleur', 'compositions', 'images', composition.id]);
}

onClickAjoutComposition(composition: CompositionDisplay): void{
  const elt: ElementComposition = new ElementComposition();
  elt.id = composition.id;
  elt.nom = composition.nom;
  elt.type = 'COMPOSITION';
  elt.prixUnitaire = composition.prixUnitaire ;
  elt.quantite = 1;
  this.compoEvt.push(elt);
  this.compositionService.recuperationElements(elt);
}

onClikDeleteCompo(index: number): void{
  const idCompo = this.ttesComposition[index].id;
  this.compositionService.deleteById(idCompo).subscribe(resp => {
    this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Suppression effectuée !'
    });
    this.ngOnInit();
  }, err => {
    this.evtService.getByIdCompo(idCompo).subscribe(resp => {
      const compoDel = new CompositionDelete();
      compoDel.compo = this.compositionResearch[index];
      compoDel.evts = resp;
      this.dialogRefDel = this.dialog.open(
        CompositionDelDialogComponent,
        {
          disableClose: true,
          data: compoDel,
          minWidth: 500,
        });
      this.dialogRefDel.afterClosed().subscribe(result => {
        if (result === 'Cancel' || result === 'Close') {
            return;
          }
        this.compositionService.deleteFromEvt(idCompo).subscribe(resp1 => {
          this.snackBar.openFromComponent(SnackbarSuccessComponent, {
            ...this.configSuccess,
            data: 'Suppression effectuée !'
        });
          this.ngOnInit();
        }, err1 => {
          this.snackBar.openFromComponent(SnackbarSuccessComponent, {
            ...this.configFailed,
            data: 'Erreur lors de la suppression !'
          });
        });
      });
    });
  });
}

private constructCompoDisplay(compo: Composition): CompositionDisplay {
  const compoDisplay = new CompositionDisplay();
  compoDisplay.id = compo.id;
  if (compo.nom === null){
    compoDisplay.nom = 'Composition sans nom';
  }else{
    compoDisplay.nom = compo.nom;
  }
  compoDisplay.dateCreation = compo.dateCreation;
  compoDisplay.dureeCreation = compo.dureeCreation;
  compoDisplay.prixUnitaire = compo.prixUnitaire;
  compoDisplay.tiges = compo.elements.filter(e => e.type === 'TIGE');
  compoDisplay.materiaux = compo.elements.filter(e => e.type === 'MATERIAU');
  compoDisplay.qteImage = compo.images.length;
  compoDisplay.tva = compo.tva;
  return compoDisplay;
}

}




