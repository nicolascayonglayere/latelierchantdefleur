import { SnackbarSuccessComponent } from './../../layout/snackbar/snackbar-success/snackbar-success.component';
import { Observable } from 'rxjs';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { FournisseurService } from './../../services/fournisseur.service';
import { Fournisseur } from './../../model/Fournisseur';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MateriauService } from './../../services/materiau.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Materiau } from 'src/app/model/Materiau';

@Component({
  selector: 'app-materiau-edit',
  templateUrl: './materiau-edit.component.html',
  styleUrls: ['./materiau-edit.component.css']
})
export class MateriauEditComponent implements OnInit {
  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  materiauEdit: Materiau;
  formTitle: string;
  fournisseurs: Fournisseur[];

  materiauForm: FormGroup;
  fournisseurCtrl: FormControl;

  constructor(
    private route: ActivatedRoute,
    private materiauService: MateriauService,
    private router: Router,
    private fournisseurService: FournisseurService,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    // this.fournisseurCtrl = new FormControl(this.fournisseurs, Validators.required);
    this.route.paramMap.subscribe((resp) => {
      if (resp.get('id') === '0') {
        this.initFormCreate();
        this.materiauEdit = new Materiau();
        this.materiauEdit.id = 0;
      } else {
        this.materiauService.getById(+resp.get('id')).subscribe((t) => {
          this.materiauEdit = t;
          this.initFormUpdate();
        });
      }
    });
    this.fournisseurService.getAll().subscribe(resp => {
      this.fournisseurs = resp;
    });
  }

  initFormCreate(): void{
    this.formTitle = 'Creation d\'un nouveau materiau',
    this.materiauForm = this.formBuilder.group({
      nom: new FormControl('', Validators.required),
      prixUnitaire: new FormControl(0, [Validators.required, Validators.min(0)]),
      fournisseurCtrl: new FormControl(this.fournisseurs, Validators.required)
    });
  }

  initFormUpdate(): void{
    this.formTitle = 'Modification d\'un materiau existant : ' + this.materiauEdit.nom,
    this.materiauForm = this.formBuilder.group({
      nom: new FormControl(this.materiauEdit.nom, Validators.required),
      prixUnitaire: new FormControl(this.materiauEdit.prixUnitaire, [Validators.required, Validators.min(0)]),
      fournisseurCtrl: new FormControl(this.fournisseurs, Validators.required)
    });
  }

  onSubmitForm(): void{
    const formValue = this.materiauForm.value;
    const newMateriau = new Materiau();
    newMateriau.id = this.materiauEdit.id;
    newMateriau.nom = formValue.nom;
    newMateriau.prixUnitaire = formValue.prixUnitaire;
    console.log(formValue.fournisseurCtrl);
    const fournisseur = this.fournisseurs.find(f => f.id === +formValue.fournisseurCtrl);
    newMateriau.fournisseurRest = fournisseur;
    let materiauObs: Observable<Materiau>;
    if (newMateriau.id === 0){
      materiauObs = this.materiauService.create(newMateriau);
    }else{
      materiauObs = this.materiauService.update(newMateriau);
    }
    materiauObs.subscribe(resp => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Enregistrement effectué !'
      });
      this.router.navigate(['/atelier-chant-de-fleur', 'materiaux']);
    }, error => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configFailed,
        data: 'Erreur lors de la sauvegarde !'
      });
    });
  }
}
