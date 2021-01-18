import { SnackbarSuccessComponent } from './../../layout/snackbar/snackbar-success/snackbar-success.component';
import { Observable } from 'rxjs';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { FournisseurService } from './../../services/fournisseur.service';
import { Fournisseur } from './../../model/Fournisseur';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-fournisseur-edit',
  templateUrl: './fournisseur-edit.component.html',
  styleUrls: ['./fournisseur-edit.component.css']
})
export class FournisseurEditComponent implements OnInit {
  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  fournisseurForm: FormGroup;
  fournisseurEdit: Fournisseur;

  formTitle: string;

  constructor(private route: ActivatedRoute,
              private fournisseurService: FournisseurService,
              private router: Router,
              private snackBar: MatSnackBar,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(resp => {
      if (resp.get('id') === '0') {
        this.initFormCreate();
        this.fournisseurEdit = new Fournisseur();
        this.fournisseurEdit.id = 0;
      } else {
        this.fournisseurService.getById(+resp.get('id')).subscribe((t) => {
          this.fournisseurEdit = t;
          this.initFormUpdate();
        });
      }
    });
  }

  initFormCreate(): void{
    this.formTitle = 'Creation d\'un nouveau fournisseur';
    this.fournisseurForm = this.formBuilder.group({
      nom: new FormControl('', Validators.required),
      siret: new FormControl(''),
      adresse: new FormControl(''),
      codePostal: new FormControl('', [Validators.maxLength(5), Validators.pattern('[0-9]+')]),
      ville: new FormControl(''),
      email: new FormControl('', Validators.email),
      telephone: new FormControl('')
    });
  }

  initFormUpdate(): void{
    this.formTitle = 'Modification d\'un fournisseur existant : ' + this.fournisseurEdit.nom;
    this.fournisseurForm = this.formBuilder.group({
      nom: new FormControl(this.fournisseurEdit.nom, Validators.required),
      siret: new FormControl(this.fournisseurEdit.numeroSiret),
      adresse: new FormControl(this.fournisseurEdit.adresse),
      codePostal: new FormControl(this.fournisseurEdit.codePostal, [Validators.maxLength(5), Validators.pattern('[0-9]+')]),
      ville: new FormControl(this.fournisseurEdit.ville),
      email: new FormControl(this.fournisseurEdit.email, Validators.email),
      telephone: new FormControl(this.fournisseurEdit.telephone)
    });
  }

  onSubmitForm(): void{
    const formValue = this.fournisseurForm.value;
    const newFournisseur = new Fournisseur();
    newFournisseur.id = this.fournisseurEdit.id;
    newFournisseur.nom = formValue.nom;
    newFournisseur.numeroSiret = formValue.siret;
    newFournisseur.adresse = formValue.adresse;
    newFournisseur.codePostal = formValue.codePostal;
    newFournisseur.ville = formValue.ville;
    newFournisseur.email = formValue.email;
    newFournisseur.telephone =  formValue.telephone;
    let fournisseurObs: Observable<Fournisseur>;
    if (newFournisseur.id === 0){
      fournisseurObs = this.fournisseurService.create(newFournisseur);
    }else{
      fournisseurObs = this.fournisseurService.update(newFournisseur);
    }
    fournisseurObs.subscribe(resp => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Enregistrement effectué !'
      });
      this.router.navigate(['/atelier-chant-de-fleur', 'fournisseurs']);
    }, error => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configFailed,
        data: 'Erreur lors de la sauvegarde !'
      });
    });

  }



}
