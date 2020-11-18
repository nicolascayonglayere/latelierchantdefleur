import { SnackbarSuccessComponent } from './../../layout/snackbar/snackbar-success/snackbar-success.component';
import { Observable } from 'rxjs';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { Fournisseur } from './../../model/Fournisseur';
import { FournisseurService } from './../../services/fournisseur.service';
import { TigeService } from './../../services/tige.service';
import { Tige } from './../../model/Tige';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tige-edit',
  templateUrl: './tige-edit.component.html',
  styleUrls: ['./tige-edit.component.css'],
})
export class TigeEditComponent implements OnInit {
  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  id: number;
  tigeEdit: Tige;
  tigeForm: FormGroup;
  formTitle: string;
  fournisseurs: Fournisseur[];

  // prixUnitaire = new FormControl(0, [Validators.required, Validators.min(0)]);
  // fournisseurCtrl: FormControl;

  constructor(
    private route: ActivatedRoute,
    private tigeService: TigeService,
    private router: Router,
    private fournisseurService: FournisseurService,
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder
  ) {
    // this.route.paramMap.subscribe((resp) => {
    //   if (resp.get('id') === '0') {

    //     this.formTitle = 'Creation d\'une nouvelle tige',
    //     this.tigeForm = this.formBuilder.group({
    //       nom: new FormControl('', Validators.required),
    //       nomLatin: new FormControl(''),
    //       prixUnitaire: new FormControl(0, [Validators.required, Validators.min(0)]),
    //       fournisseurCtrl: new FormControl(this.fournisseurs, Validators.required)
    //     });
    //     this.tigeEdit = new Tige();
    //     this.tigeEdit.id = 0;
    //   } else {
    //     this.tigeService.getById(+resp.get('id')).subscribe((t) => {
    //       this.tigeEdit = t;
    //       this.formTitle = 'Creation d\'une nouvelle tige',
    //       this.tigeForm = this.formBuilder.group({
    //         nom: new FormControl('', Validators.required),
    //         nomLatin: new FormControl(''),
    //         prixUnitaire: new FormControl(0, [Validators.required, Validators.min(0)]),
    //         fournisseurCtrl: new FormControl(this.fournisseurs, Validators.required)
    //       });
    //     });
    //   }
    // });
    // this.fournisseurService.getAll();
    // this.fournisseurService.currentAllFournisseur.subscribe(resp => {
    //   this.fournisseurs = resp;
    // });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((resp) => {
      if (resp.get('id') === '0') {
        this.initFormCreate();
        this.tigeEdit = new Tige();
        this.tigeEdit.id = 0;
      } else {
        this.tigeService.getById(+resp.get('id')).subscribe((t) => {
          this.tigeEdit = t;
          this.initFormUpdate();
        });
      }
    });
    this.fournisseurService.getAll();
    this.fournisseurService.currentAllFournisseur.subscribe(resp => {
      this.fournisseurs = resp;
    });
  }

  initFormCreate(): void{
    // this.fournisseurCtrl = new FormControl(this.fournisseurs, Validators.required);
    this.formTitle = 'Creation d\'une nouvelle tige',
    this.tigeForm = this.formBuilder.group({
      nom: new FormControl('', Validators.required),
      nomLatin: new FormControl(''),
      prixUnitaire: new FormControl(0, [Validators.required, Validators.min(0)]),
      fournisseurCtrl: new FormControl(this.fournisseurs, Validators.required)
    });
  }

  initFormUpdate(): void{
    // this.fournisseurCtrl = new FormControl(this.tigeEdit.fournisseurRest, Validators.required);
    this.formTitle = 'Modification d\'une tige existante : ' + this.tigeEdit.nom,
    this.tigeForm = this.formBuilder.group({
      nom: new FormControl(this.tigeEdit.nom, Validators.required),
      nomLatin: new FormControl(this.tigeEdit.nomLatin),
      prixUnitaire: new FormControl(this.tigeEdit.prixUnitaire, [Validators.required, Validators.min(0)]),
      fournisseurCtrl: new FormControl(this.tigeEdit.fournisseurRest, Validators.required)
    });
    console.log(this.tigeForm.value.fournisseurCtrl);
  }

  onSubmitForm(): void{
    const formValue = this.tigeForm.value;
    const newTige = new Tige();
    newTige.id = this.tigeEdit.id;
    newTige.nom = formValue.nom;
    newTige.nomLatin = formValue.nomLatin;
    newTige.prixUnitaire = formValue.prixUnitaire;
    const fournisseur = this.fournisseurs.find(f => f.id === +formValue.fournisseurCtrl);
    newTige.fournisseurRest = fournisseur;
    let tigeObs: Observable<Tige>;
    if ( newTige.id === 0 ) {
      tigeObs = this.tigeService.create(newTige);
    }else{
      tigeObs = this.tigeService.update(newTige);
    }
    tigeObs.subscribe(resp => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Enregistrement effectué !'
      });
      this.router.navigate(['/atelier-chant-de-fleur', 'tiges']);
    }, error => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configFailed,
        data: 'Erreur lors de la sauvegarde !'
      });
    });
  }

}
