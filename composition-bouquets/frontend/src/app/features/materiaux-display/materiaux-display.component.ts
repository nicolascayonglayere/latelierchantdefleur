import {PageEvent} from '@angular/material/paginator';
import {CompositionService} from '../../services/composition.service';
import {ElementComposition} from '../../model/ElementComposition';
import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Materiau} from '../../model/Materiau';
import {MateriauService} from '../../services/materiau.service';
import {faEdit, faPlus} from '@fortawesome/free-solid-svg-icons';
import {NgbPaginationConfig} from '@ng-bootstrap/ng-bootstrap';
import {Composition} from '../../model/Composition';
import {CompositionSelectedService} from '../../services/composition-selected.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-materiaux-display',
  templateUrl: './materiaux-display.component.html',
  styleUrls: ['./materiaux-display.component.css'],
  providers: [NgbPaginationConfig]
})
export class MateriauxDisplayComponent implements OnInit {
  composition: Composition;

  faPlus = faPlus;
  faEdit = faEdit;

  tsMateriaux: Materiau[];
  materiaux: Materiau[];
  quantiteElt: number[] = [];

  pageIndex = 0;
  page: PageEvent;
  pageSize = 10;
  pageSizeOptions = [10, 25, 50];
  length: number;
  lowValue = 0;
  highValue = 10;

  editing: boolean[] = [];

  disableAjout: boolean;


  @Output() materiauAjoute = new EventEmitter<ElementComposition>();

  constructor(private materiauService: MateriauService,
              private compositionservice: CompositionService,
              private config: NgbPaginationConfig,
              private compoSelectedService: CompositionSelectedService,
              private router: Router) {
    // config.size = 'sm';
    config.boundaryLinks = true;
   }

  ngOnInit(): void {
    if (this.router.url === '/atelier-chant-de-fleur/materiaux'){
      this.disableAjout = true;
    }else{
      this.disableAjout = false;
    }
    this.materiauService.getAll();
    this.materiauService.currentAllMateriaux.subscribe(resp => {
      this.materiaux = resp;
      this.tsMateriaux = resp;
      this.length = this.tsMateriaux.length;
      for ( let x = 0, ln = this.length ; x < ln; x++){
        this.editing[x] = false;
        this.quantiteElt[x] = 1;
      }
    });
  }

  onClickAjoutComposition(materiau: Materiau, index: number): void {
    const elt: ElementComposition = new ElementComposition();
    elt.id = materiau.id;
    elt.nom = materiau.nom;
    elt.type = 'MATERIAU';
    elt.prixUnitaire = materiau.prixUnitaire;
    elt.quantite = this.quantiteElt[index];
    this.materiauAjoute.emit(elt);
    for ( let x = 0, ln = this.length ; x < ln; x++){
      this.editing[x] = false;
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
    for (let x = 0, ln = this.length ; x < ln; x++){
      this.editing[x] = true;
    }
    this.editing[index] = false;
  }
}
