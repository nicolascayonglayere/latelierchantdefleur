import { PageEvent } from '@angular/material/paginator';
import { CompositionService } from './../../services/composition.service';
import { ElementComposition } from './../../model/ElementComposition';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Materiau } from '../../model/Materiau';
import { DataTablesResponse } from '../../model/DataTablesResponse';
import { MateriauService } from '../../services/materiau.service';
import { faPlus, faEdit } from '@fortawesome/free-solid-svg-icons';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-materiaux-display',
  templateUrl: './materiaux-display.component.html',
  styleUrls: ['./materiaux-display.component.css'],
  providers: [NgbPaginationConfig]
})
export class MateriauxDisplayComponent implements OnInit {
  faPlus = faPlus;
  faEdit = faEdit;

  tsMateriaux: Materiau[];
  materiaux: Materiau[];
  dtOptions: DataTables.Settings = {};
  dataTablesData: DataTablesResponse;
  materiauComposition: ElementComposition[] = [];
  eltSelected: ElementComposition;
  quantiteElt: number[] = [];

  pageIndex:number = 0;
page: PageEvent;// = 1;
pageSize = 10;
pageSizeOptions = [10, 25, 50];
length: number;
lowValue:number = 0;
highValue:number = 10;

editing: boolean[] = [];

  constructor(private materiauService: MateriauService, private compositionservice: CompositionService, config: NgbPaginationConfig) {
    // config.size = 'sm';
    config.boundaryLinks = true;
   }

  ngOnInit(): void {
    this.materiauService.getAll()
    this.materiauService.currentAllMateriaux.subscribe(resp => {
      this.materiaux = resp;
      this.tsMateriaux = resp;
      this.length = this.tsMateriaux.length;
      for ( let x = 0, ln = this.length ; x < ln; x++){
        this.editing[x] = false;
        this.quantiteElt[x] = 1;
      }
    });
    this.compositionservice.currentElements.subscribe(resp => {
      this.eltSelected = new ElementComposition();
      this.eltSelected = resp;
    });
  }

  onClickAjoutComposition(materiau: Materiau, index: number): void {
    const elt: ElementComposition = new ElementComposition();
    elt.id = materiau.id;
    elt.nom = materiau.nom;
    elt.type = 'MATERIAU';
    elt.prixUnitaire = materiau.prixUnitaire / 1000;
    elt.quantite = this.quantiteElt[index];
    console.log(elt);
    this.materiauComposition.push(elt);
    this.compositionservice.recuperationElements(elt);
    for ( let x = 0, ln = this.length ; x < ln; x++){
      this.editing[x] = false;
      // this.quantiteElt[x] = 1;
    }
  }

  onChangeResearch(e: any): void{
    this.materiaux = [];
    this.tsMateriaux.forEach(t => {
      if (t.nom.toLowerCase().startsWith(e.target.value.toLowerCase())){
        this.materiaux.push(t);
      }
    });
  }

  getPaginatorData(event: PageEvent): PageEvent{
    console.log(event);
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
}

onClickDisabledOthers(index: number): void{
  for(let x = 0, ln = this.length ; x < ln; x++){
    this.editing[x] = true;
  }
  this.editing[index] = false;

}
}
