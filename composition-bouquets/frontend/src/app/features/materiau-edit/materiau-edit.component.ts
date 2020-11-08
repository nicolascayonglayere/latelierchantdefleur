import { FournisseurService } from './../../services/fournisseur.service';
import { Fournisseur } from './../../model/Fournisseur';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
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

  materiauEdit: Materiau;
  materiauForm: FormGroup;
  formTitle: string;
  fournisseurs: Fournisseur[];

  constructor(
    private route: ActivatedRoute,
    private materiauService: MateriauService,
    private formBuilder: FormBuilder,
    private router: Router,
    private fournisseurService: FournisseurService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((resp) => {
      console.log(resp.get('id'));
      if (resp.get('id') === '0') {
        this.initFormCreate();
        this.materiauEdit = new Materiau();
        this.materiauEdit.id = 0;
        console.log('Creation');
      } else {
        this.materiauService.getById(+resp.get('id')).subscribe((t) => {
          this.materiauEdit = t;
          this.initFormUpdate();
          console.log('Update');
        });
      }
    });
    this.fournisseurService.getAll().subscribe(resp =>{
      this.fournisseurs = resp;
    });
  }

  initFormCreate(){
    this.formTitle = 'Creation d\'un nouveau materiau',
    // this.tigeForm = this.formBuilder.group({
    this.materiauForm = new FormGroup({
      'nom': new FormControl(''),
      'prixUnitaire': new FormControl(0),
      'fournisseur': new FormControl(this.fournisseurs)
    });
  }

  initFormUpdate(){
    this.formTitle = 'Modification d\'un materiau existant : ' + this.materiauEdit.nom,
    // this.tigeForm = this.formBuilder.group({
    this.materiauForm = new FormGroup({
      'nom': new FormControl(this.materiauEdit.nom),
      'prixUnitaire': new FormControl(this.materiauEdit.prixUnitaire),
      'fournisseur': new FormControl(this.materiauEdit.fournisseurRest)
    });
  }

  onSubmitForm(){
    const formValue = this.materiauForm.value;
    const newMateriau = new Materiau();
    newMateriau.id = this.materiauEdit.id;
    newMateriau.nom = formValue['nom'];
    newMateriau.prixUnitaire = formValue['prixUnitaire'];
    const fournisseur = this.fournisseurs.find(f => f.id === +formValue['fournisseur']);
    newMateriau.fournisseurRest = fournisseur;
    if(newMateriau.id === 0){
      this.materiauService.create(newMateriau);
    }else{
      this.materiauService.update(newMateriau);
    }
    this.router.navigate(['/atelier-chant-de-fleur', 'materiaux']);
  }

}
