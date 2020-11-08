import { CompositionService } from './../../services/composition.service';
import { ElementComposition } from './../../model/ElementComposition';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Materiau } from '../../model/Materiau';
import { DataTablesResponse } from '../../model/DataTablesResponse';
import { MateriauService } from '../../services/materiau.service';
import { faPlus, faCheckCircle, faEdit } from '@fortawesome/free-solid-svg-icons';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-materiaux-display',
  templateUrl: './materiaux-display.component.html',
  styleUrls: ['./materiaux-display.component.css'],
  providers: [NgbPaginationConfig]
})
export class MateriauxDisplayComponent implements OnInit {
  faCheckCircle = faCheckCircle;
  faPlus = faPlus;
  faEdit = faEdit;

  tsMateriaux: Materiau[];
  materiaux: Materiau[];
  dtOptions: DataTables.Settings = {};
  dataTablesData: DataTablesResponse;
  materiauComposition: ElementComposition[] = [];
  eltSelected: ElementComposition;
  quantiteElt: number = 1;
  page = 1;
  pageSize = 10;

  constructor(private materiauService: MateriauService, private compositionservice: CompositionService, config: NgbPaginationConfig) {
    // config.size = 'sm';
    config.boundaryLinks = true;
   }

  ngOnInit(): void {
    this.materiauService.getAll().subscribe(resp => {
      this.materiaux = resp;
      this.tsMateriaux = resp;
    });
    this.compositionservice.currentElements.subscribe(resp => {
      this.eltSelected = new ElementComposition();
      this.eltSelected = resp;
    });
  }

  onSubmitQuantite(form: NgForm): void{
    this.quantiteElt = form.value['quantite'];
  }

  onClickAjoutComposition(materiau: Materiau): void {
    const elt: ElementComposition = new ElementComposition();
    elt.id = materiau.id;
    elt.nom = materiau.nom;
    elt.type = 'MATERIAU';
    elt.prixUnitaire = materiau.prixUnitaire / 1000;
    elt.quantite = this.quantiteElt;
    this.materiauComposition.push(elt);
    this.compositionservice.recuperationElements(elt);
  }

  onChangeResearch(e: any): void{
    this.materiaux = [];
    this.tsMateriaux.forEach(t => {
      if (t.nom.toLowerCase().startsWith(e.target.value.toLowerCase())){
        this.materiaux.push(t);
      }
    });
  }
}
