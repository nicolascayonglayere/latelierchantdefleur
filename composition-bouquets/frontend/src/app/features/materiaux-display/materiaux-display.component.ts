import { CompositionService } from './../../services/composition.service';
import { ElementComposition } from './../../model/ElementComposition';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Materiau } from '../../model/Materiau';
import { DataTablesResponse } from '../../model/DataTablesResponse';
import { MateriauService } from '../../services/materiau.service';
import { faPlus, faCheckCircle } from '@fortawesome/free-solid-svg-icons';
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
    config.size = 'sm';
    config.boundaryLinks = true;
   }

  ngOnInit(): void {
    this.materiauService.getAll().subscribe(resp => {
      this.materiaux = resp;
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
    console.log(this.materiauComposition);
    // this.compositionservice.recuperationElements(this.tigeComposition);
    this.compositionservice.recuperationElements(elt);
  }

  private constructionMateriaux(tsMateriaux: Materiau[], size: number): Materiau[]{
    for (let index = 0; index < size; index++) {
      this.materiaux.push(tsMateriaux[index]);
    }
    return this.materiaux;
  }
}
