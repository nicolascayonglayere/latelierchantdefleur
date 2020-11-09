import { FournisseurService } from './../../services/fournisseur.service';
import { Fournisseur } from './../../model/Fournisseur';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-fournisseur-edit',
  templateUrl: './fournisseur-edit.component.html',
  styleUrls: ['./fournisseur-edit.component.css']
})
export class FournisseurEditComponent implements OnInit {

  fournisseurForm: FormGroup;
  fournisseurEdit: Fournisseur;

  formTitle: string;

  constructor(private route: ActivatedRoute, private fournisseurService: FournisseurService, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(resp => {
      if (resp.get('id') === '0') {
        this.initFormCreate();
        this.fournisseurEdit = new Fournisseur();
        this.fournisseurEdit.id = 0;
        console.log('Creation');
      } else {
        this.fournisseurService.getById(+resp.get('id')).subscribe((t) => {
          this.fournisseurEdit = t;
          this.initFormUpdate();
          console.log('Update');
        });
      }
    });
  }

  initFormCreate(){
    this.formTitle = 'Creation d\'un nouveau fournisseur',
    // this.tigeForm = this.formBuilder.group({
    this.fournisseurForm = new FormGroup({
      'nom': new FormControl('', Validators.required),
      'siret': new FormControl(''),
      'adresse': new FormControl(''),
      'codePostal': new FormControl('', [Validators.maxLength(5), Validators.pattern('[0-9]+')]),
      'ville': new FormControl(''),
      'email': new FormControl('', Validators.email),
      'telephone': new FormControl('')
    });
  }

  initFormUpdate(){
    this.formTitle = 'Modification d\'un fournisseur existant : ' + this.fournisseurEdit.nom,
    // this.tigeForm = this.formBuilder.group({
    this.fournisseurForm = new FormGroup({
      'nom': new FormControl(this.fournisseurEdit.nom, Validators.required),
      'siret': new FormControl(this.fournisseurEdit.numeroSiret),
      'adresse': new FormControl(this.fournisseurEdit.adresse),
      'codePostal': new FormControl(this.fournisseurEdit.codePostal, [Validators.maxLength(5), Validators.pattern('[0-9]+')]),
      'ville': new FormControl(this.fournisseurEdit.ville),
      'email': new FormControl(this.fournisseurEdit.email, Validators.email),
      'telephone': new FormControl(this.fournisseurEdit.telephone)
    });
  }

  onSubmitForm(){
    const formValue = this.fournisseurForm.value;
    const newFournisseur = new Fournisseur();
    newFournisseur.id = this.fournisseurEdit.id;
    newFournisseur.nom = formValue['nom'];
    newFournisseur.numeroSiret = formValue['siret'];
    newFournisseur.adresse = formValue['adresse'];
    newFournisseur.codePostal = formValue['codePostal'];
    newFournisseur.ville = formValue['ville'];
    newFournisseur.email = formValue['email'];
    newFournisseur.telephone =  formValue['telephone']
    if(newFournisseur.id === 0){
      this.fournisseurService.create(newFournisseur);
    }else{
      this.fournisseurService.update(newFournisseur);
    }
    this.router.navigate(['/atelier-chant-de-fleur', 'fournisseurs']);
  }



}
