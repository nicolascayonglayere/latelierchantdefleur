import { Observable } from 'rxjs';
import { Client } from './../../model/Client';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from 'src/app/services/client.service';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { SnackbarSuccessComponent } from 'src/app/layout/snackbar/snackbar-success/snackbar-success.component';

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.css']
})
export class ClientEditComponent implements OnInit {
  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  formTitle: string;
  clientForm: FormGroup;

  clientEdit: Client;

  constructor(private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private clientService: ClientService,
    private snackBar: MatSnackBar,
    private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(resp => {
      if (resp.get('id') === '0'){
        this.initFormCreate();
        this.clientEdit = new Client();
        this.clientEdit.id = 0;
      } else {
        this.clientService.getById(+resp.get('id')).subscribe(c => {
          this.clientEdit = c;
          this.initFormUpdate();
        });
      }
    });
  }

  initFormCreate(): void{
    this.formTitle = 'Creation d\'un nouveau client';
    this.clientForm = this.formBuilder.group({
      nom1: new FormControl('', Validators.required),
      prenom1: new FormControl(''),
      nom2: new FormControl(''),
      prenom2: new FormControl(''),
      adresse: new FormControl(''),
      codePostal: new FormControl('', [Validators.maxLength(5), Validators.pattern('[0-9]+')]),
      ville: new FormControl(''),
      email: new FormControl('', [Validators.email, Validators.required]),
      telephone: new FormControl('')
    });
  }

  initFormUpdate(): void{
    this.formTitle = 'Modification du client '+this.clientEdit.nom1;
    this.clientForm = this.formBuilder.group({
      nom1: new FormControl(this.clientEdit.nom1, Validators.required),
      prenom1: new FormControl(this.clientEdit.prenom1),
      nom2: new FormControl(this.clientEdit.nom2),
      prenom2: new FormControl(this.clientEdit.prenom2),
      adresse: new FormControl(this.clientEdit.adresse),
      codePostal: new FormControl(this.clientEdit.codePostal, [Validators.maxLength(5), Validators.pattern('[0-9]+')]),
      ville: new FormControl(this.clientEdit.ville),
      email: new FormControl(this.clientEdit.email, [Validators.email, Validators.required]),
      telephone: new FormControl(this.clientEdit.telephone)
    });
  }

  onSubmitForm(): void{
    const formValue = this.clientForm.value;
    const newClient = new Client();
    newClient.id = this.clientEdit.id;
    newClient.nom1 = formValue.nom1;
    newClient.prenom1 = formValue.prenom1;
    newClient.nom2 = formValue.nom2;
    newClient.prenom2 = formValue.prenom2;
    newClient.adresse = formValue.adresse;
    newClient.codePostal = formValue.codePostal;
    newClient.ville = formValue.ville;
    newClient.email = formValue.email;
    newClient.telephone =  formValue.telephone;
    let clientObs: Observable<Client>;
    if(newClient.id === 0){
      clientObs = this.clientService.create(newClient);
    } else {
      clientObs = this.clientService.update(newClient);
    }
    clientObs.subscribe(resp => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Enregistrement effectué !'
      });
      this.router.navigate(['/atelier-chant-de-fleur', 'clients']);
    }, error => {
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configFailed,
        data: 'Erreur lors de la sauvegarde !'
      });
    });
  }

}
