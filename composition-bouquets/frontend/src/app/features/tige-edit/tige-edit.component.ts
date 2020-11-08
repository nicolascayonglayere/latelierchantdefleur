import { Fournisseur } from './../../model/Fournisseur';
import { FournisseurService } from './../../services/fournisseur.service';
import { TigeService } from './../../services/tige.service';
import { Tige } from './../../model/Tige';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tige-edit',
  templateUrl: './tige-edit.component.html',
  styleUrls: ['./tige-edit.component.css'],
})
export class TigeEditComponent implements OnInit {
  // @Input() id: Tige;
  id: number;
  tigeEdit: Tige;
  tigeForm: FormGroup;
  formTitle: string;
  fournisseurs: Fournisseur[];

  constructor(
    private route: ActivatedRoute,
    private tigeService: TigeService,
    private formBuilder: FormBuilder,
    private router: Router,
    private fournisseurService: FournisseurService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((resp) => {
      console.log(resp.get('id'));
      if (resp.get('id') === '0') {
        this.initFormCreate();
        this.tigeEdit = new Tige();
        this.tigeEdit.id = 0;
        console.log('Creation');
      } else {
        this.tigeService.getById(+resp.get('id')).subscribe((t) => {
          this.tigeEdit = t;
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
    this.formTitle = 'Creation d\'une nouvelle tige',
    // this.tigeForm = this.formBuilder.group({
    this.tigeForm = new FormGroup({
      // 'id': new FormControl(0),
      'nom': new FormControl(''),
      'nomLatin': new FormControl(''),
      'prixUnitaire': new FormControl(0),
      'fournisseur': new FormControl(this.fournisseurs)
    });
  }

  initFormUpdate(){
    this.formTitle = 'Modification d\'une tige existante : ' + this.tigeEdit.nom,
    // this.tigeForm = this.formBuilder.group({
    this.tigeForm = new FormGroup({
      // 'id': new FormControl(this.tigeEdit.id),
      'nom': new FormControl(this.tigeEdit.nom),
      'nomLatin': new FormControl(this.tigeEdit.nomLatin),
      'prixUnitaire': new FormControl(this.tigeEdit.prixUnitaire),
      'fournisseur': new FormControl(this.tigeEdit.fournisseurRest.nom)
    });
  }

  onSubmitForm(){
    const formValue = this.tigeForm.value;
    const newTige = new Tige();
    newTige.id = this.tigeEdit.id;
    newTige.nom = formValue['nom'];
    newTige.nomLatin = formValue['nomLatin'];
    newTige.prixUnitaire = formValue['prixUnitaire'];
    const fournisseur = this.fournisseurs.find(f => f.id === +formValue['fournisseur']);
    newTige.fournisseurRest = fournisseur;
    if(newTige.id === 0){
      this.tigeService.create(newTige);
    }else{
      this.tigeService.update(newTige);
    }
    this.router.navigate(['/atelier-chant-de-fleur', 'tiges']);
  }

}
