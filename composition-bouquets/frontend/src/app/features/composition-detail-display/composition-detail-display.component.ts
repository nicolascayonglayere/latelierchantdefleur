import {DomSanitizer, SafeUrl} from '@angular/platform-browser';
import {ImageComposition} from '../../model/ImageComposition';
import {CompositionService} from '../../services/composition.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Composition} from 'src/app/model/Composition';
import {CompositionDisplay} from 'src/app/model/CompositionDisplay';
import {faPlus, faUpload, faEdit, faTrash} from '@fortawesome/free-solid-svg-icons';
import {ElementComposition} from 'src/app/model/ElementComposition';
import {Client} from '../../model/Client';
import {CompositionSelectedService} from '../../services/composition-selected.service';
import {SnackbarSuccessComponent} from '../../layout/snackbar/snackbar-success/snackbar-success.component';
import {CompositionDelete} from '../../model/CompositionDelete';
import {CompositionDelDialogComponent} from '../composition-del-dialog/composition-del-dialog.component';
import {MatSnackBar, MatSnackBarConfig} from '@angular/material/snack-bar';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {EvenementService} from '../../services/evenement.service';

@Component({
  selector: 'app-composition-detail-display',
  templateUrl: './composition-detail-display.component.html',
  styleUrls: ['./composition-detail-display.component.css']
})
export class CompositionDetailDisplayComponent implements OnInit {
  faUpload = faUpload;
  faPlus = faPlus;
  faEdit = faEdit;
  faTrash = faTrash;
  composition: Composition;
  compoDisplay: CompositionDisplay;
  fileInfos: ImageComposition[];
  imgUrl: SafeUrl[] = [];
  compoEvt: ElementComposition[] = [];
  clientSelected: Client;

  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  dialogRefDel: MatDialogRef<CompositionDelDialogComponent>;

  constructor(private compositionService: CompositionService,
              private route: ActivatedRoute,
              private compoService: CompositionService,
              private sanitizer: DomSanitizer,
              private routerLink: Router,
              private compoSelectedService: CompositionSelectedService,
              private snackBar: MatSnackBar,
              private dialog: MatDialog,
              private evtService: EvenementService){ }

  ngOnInit(): void {
    this.route.paramMap.subscribe(resp => {
      this.compoService.getById(+resp.get('id')).subscribe(comp => {
        this.composition = comp;
        this.compoDisplay = this.constructCompoDisplay(this.composition);
        this.refreshListImageDipslay();
      });
    });
  }

  onClikDeleteCompo(id: number): void{
    this.compositionService.deleteById(id).subscribe(resp => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Suppression effectuée !'
      });
      this.routerLink.navigate(['atelier-chant-de-fleur', 'compositions']);
    }, err => {
      this.evtService.getByIdCompo(id).subscribe(resp => {
        const compoDel = new CompositionDelete();
        compoDel.compo = this.compoDisplay;
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
          this.compositionService.deleteFromEvt(id).subscribe(resp1 => {
            this.snackBar.openFromComponent(SnackbarSuccessComponent, {
              ...this.configSuccess,
              data: 'Suppression effectuée !'
            });
            this.routerLink.navigate(['atelier-chant-de-fleur', 'compositions']);
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

  onClickAddImageDialog(composition: CompositionDisplay): void{
    this.routerLink.navigate(['atelier-chant-de-fleur', 'compositions', 'images', composition.id]);
  }

  onClickSelectComposition(id: number): void {
    this.compositionService.currentAllCompositions.subscribe(resp => {
      const composition = resp.find(c => c.id === id);
      this.compoSelectedService.recuperationCompoSelected(composition);
    });
    this.routerLink.navigate(['atelier-chant-de-fleur', 'creation-composition']);
  }

  private refreshListImageDipslay(): void{
    this.composition.images.forEach(data => {
      const objectUrl = 'data:image/jpeg;base64,' + data.content;
      data.imgDisplay = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
      // data.imgDisplay = this.sanitizer.sanitize(SecurityContext.HTML, this.sanitizer.bypassSecurityTrustUrl(objectUrl));
      this.imgUrl.push(this.sanitizer.bypassSecurityTrustUrl(objectUrl));
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
    return compoDisplay;
  }

}
