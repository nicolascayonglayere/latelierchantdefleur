import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { CompositionService } from './../../../services/composition.service';
import { CompositionDisplay } from 'src/app/model/CompositionDisplay';
import { Composition } from 'src/app/model/Composition';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { ImageComposition } from 'src/app/model/ImageComposition';
import { ImageCompositionService } from 'src/app/services/image-composition.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { SnackbarSuccessComponent } from 'src/app/layout/snackbar/snackbar-success/snackbar-success.component';

@Component({
  selector: 'app-composition-add-image',
  templateUrl: './composition-add-image.component.html',
  styleUrls: ['./composition-add-image.component.css']
})
export class CompositionAddImageComponent implements OnInit {
  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };
  faTrash = faTrash;

  compo: CompositionDisplay;
  selectedFiles: FileList;
  currentFile: File;
  progress = 0;

  fileInfos: ImageComposition[];

  nomImg: string;
  description: string;

  constructor(private imgService: ImageCompositionService, private compoService: CompositionService,
              private route: ActivatedRoute, private sanitizer: DomSanitizer, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(id => {
      this.compoService.getById(+id.get('id')).subscribe(resp => {
        this.compo = this.constructCompoDisplay(resp);
      });
      this.refreshListImageDipslay(+id.get('id'));
    });

  }

  selectFile(event): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    this.progress = 0;
    this.currentFile = this.selectedFiles.item(0);
    this.imgService.uploadImage(this.currentFile, this.compo.id, this.nomImg, this.description).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.fileInfos = event.body.images;
          this.fileInfos.forEach(f => {
            const objectUrl = 'data:image/jpeg;base64,' + f.content;
            f.imgDisplay = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
          });
          this.snackBar.openFromComponent(SnackbarSuccessComponent, {
            ...this.configSuccess,
            data: 'Enregistrement effectué !'
          });
          this.refreshListImageDipslay(this.compo.id);
          this.currentFile = undefined;
        }
      },
      err => {
        this.progress = 0;
        this.currentFile = undefined;
        this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configFailed,
          data: 'Erreur lors de la sauvegarde !'
        });
      });

    this.selectedFiles = undefined;
  }

  onClikDeleteImage(id: number): void{
    this.imgService.delete(this.compo.id, id).subscribe(resp => {
      this.refreshListImageDipslay(this.compo.id);
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
          ...this.configSuccess,
          data: 'Suppression effectuée !'
      });
    }, err => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configFailed,
        data: 'Erreur lors de la suppression !'
      });
    });
  }

  private refreshListImageDipslay(id: number): void{
    this.imgService.getByIdComposition(id).subscribe(data => {
      this.fileInfos = data;
      this.fileInfos.forEach(f => {
        const objectUrl = 'data:image/jpeg;base64,' + f.content;
        f.imgDisplay = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
      });
    });
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
