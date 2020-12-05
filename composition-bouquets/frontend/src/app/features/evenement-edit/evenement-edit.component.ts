import { Composition } from './../../model/Composition';
import { EvenementService } from './../../services/evenement.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Evenement } from 'src/app/model/Evenement';
import { faMinusCircle } from '@fortawesome/free-solid-svg-icons';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { SnackbarSuccessComponent } from 'src/app/layout/snackbar/snackbar-success/snackbar-success.component';

@Component({
  selector: 'app-evenement-edit',
  templateUrl: './evenement-edit.component.html',
  styleUrls: ['./evenement-edit.component.css']
})
export class EvenementEditComponent implements OnInit {
  faMinusCircle = faMinusCircle;

  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  formTitle: string;
  evtForm: FormGroup;
  evenement: Evenement;

  mntCompoTot: number = 0;
  mntCompoTva: number = 0;
  forfaitMo: number = 0;
  forfaitDplct: number = 0;

  compositionsEdit: Composition[] = [];

  constructor(private evtService: EvenementService, private route: ActivatedRoute,
    private formBuilder: FormBuilder, private snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(resp =>{
      this.evtService.getById(+resp.get('id')).subscribe(evt => {
        this.evenement = evt;
        this.initFormUpdate();
        this.evenement.compositions.forEach(c =>{
          this.mntCompoTot = this.mntCompoTot + c.prixUnitaire;
          this.mntCompoTva = this.mntCompoTva + c.prixUnitaire * (c.tva / 100);
          this.compositionsEdit.push(c);
        });
      });
    });


  }

  initFormUpdate(): void{
    this.formTitle = 'Modification de l\'évènement ' + this.evenement.nom + ' crée le ' + this.evenement.dateCreation;
    this.evtForm = this.formBuilder.group({
      nom: new FormControl(this.evenement.nom, Validators.required),
      datePrevue: new FormControl(this.evenement.datePrevue, Validators.required),
      forfaitDplct: new FormControl(this.evenement.forfaitDplct, Validators.min(0)),
      forfaitMo: new FormControl(this.evenement.forfaitMo,  Validators.min(0))
    });
  }

  onSubmitForm(){
    const formValue = this.evtForm.value;
    const newEvt = new Evenement();
    newEvt.id = this.evenement.id;
    newEvt.nom = formValue.nom;
    newEvt.dateCreation = this.evenement.dateCreation;
    newEvt.datePrevue = formValue.datePrevue;
    newEvt.forfaitDplct = formValue.forfaitDplct;
    newEvt.forfaitMo = formValue.forfaitMo;
    newEvt.compositions = this.compositionsEdit;
    this.evtService.update(newEvt).subscribe(resp =>{
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Enregistrement effectué !'
      });
      this.router.navigate(['/atelier-chant-de-fleur', 'evenements', newEvt.id]);
    }, error => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configFailed,
        data: 'Erreur lors de la sauvegarde !'
      });
    });
  }

  onChangeCalculMo(): void{
    this.mntCompoTot = this.mntCompoTot + this.forfaitMo;
  }

  onChangeCalculDplct(): void{
    this.mntCompoTot = this.mntCompoTot + this.forfaitDplct;
  }

  onClickRemoveElt(element: Composition): void{
    this.compositionsEdit = this.compositionsEdit.filter(c => c.id !== element.id);
    this.mntCompoTot = this.mntCompoTot - element.prixUnitaire;
    this.mntCompoTva = this.mntCompoTva - (element.prixUnitaire) * (element.tva / 100);
  }

}
