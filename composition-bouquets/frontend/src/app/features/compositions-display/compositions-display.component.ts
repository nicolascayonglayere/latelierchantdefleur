import { ImageCompositionService } from './../../services/image-composition.service';
import { CompositionAddImageComponent } from './composition-add-image/composition-add-image.component';
import { CompositionDisplay } from 'src/app/model/CompositionDisplay';
import { PageEvent } from '@angular/material/paginator';
import { CompositionService } from './../../services/composition.service';
import { Component, OnInit } from '@angular/core';
import { Composition } from 'src/app/model/Composition';
import { faEdit, faUpload } from '@fortawesome/free-solid-svg-icons';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-compositions-display',
  templateUrl: './compositions-display.component.html',
  styleUrls: ['./compositions-display.component.css']
})
export class CompositionsDisplayComponent implements OnInit {

  faEdit = faEdit;
  faUpload = faUpload;

  ttesComposition: CompositionDisplay[] = [];
  compositionResearch: CompositionDisplay[];

  pageIndex = 0;
page: PageEvent; // = 1;
pageSize = 10;
pageSizeOptions = [10, 25, 50];
length: number;
lowValue = 0;
highValue = 10;

dialogRef: MatDialogRef<CompositionAddImageComponent>;

  constructor(private compositionService: CompositionService, config: NgbPaginationConfig,
              private dialog: MatDialog, private imgService: ImageCompositionService,
              private routerLink: Router) {
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
    // console.log(event);
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
}

onClickAddImageDialog(composition: CompositionDisplay): void{
  this.routerLink.navigate(['atelier-chant-de-fleur', 'compositions', 'images', composition.id]);

  // this.dialogRef = this.dialog.open(
  //   CompositionAddImageComponent,
  //   {
  //     disableClose: true,
  //     data: composition,
  //     minWidth: 500,
  //   });

  // this.dialogRef.afterClosed().subscribe((result) => {
  //     if (result === 'Cancel' || result === 'Close') {
  //       return;
  //     }
  //     console.log(result);
  //     this.imgService.uploadImage(result, 1);
  //     // const compositionToSave = new Composition();
  //     // compositionToSave.id = 0;
  //     // compositionToSave.dateCreation = new Date();
  //     // compositionToSave.dureeCreation = this.tempsTravail;
  //     // compositionToSave.prixUnitaire = this.coutTva;
  //     // compositionToSave.elements = this.elementCompo;
  //     // this.compositionService.save(compositionToSave).subscribe(resp => {
  //     //   this.snackBar.openFromComponent(SnackbarSuccessComponent, {
  //     //     ...this.configSuccess,
  //     //     data: 'Enregistrement effectué !'
  //     //   });
  //     //   this.router.navigate(['/atelier-chant-de-fleur', 'compositions']);
  //     // },
  //     // error => {
  //     //   this.snackBar.openFromComponent(SnackbarSuccessComponent, {
  //     //     ...this.configFailed,
  //     //     data: 'Erreur lors de la sauvegarde !'
  //     //   });
  //     // });
  //   });
}

private constructCompoDisplay(compo: Composition): CompositionDisplay {
  const compoDisplay = new CompositionDisplay();
  compoDisplay.id = compo.id;
  compoDisplay.nom = compo.nom;
  compoDisplay.dateCreation = compo.dateCreation;
  compoDisplay.dureeCreation = compo.dureeCreation;
  compoDisplay.prixUnitaire = compo.prixUnitaire;
  compoDisplay.tiges = compo.elements.filter(e => e.type === 'TIGE');
  compoDisplay.materiaux = compo.elements.filter(e => e.type === 'MATERIAU');
  compoDisplay.qteImage = compo.images.length;
  return compoDisplay;
}

}


