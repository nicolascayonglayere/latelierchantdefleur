import {ChangeDetectorRef, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TigeService} from '../../services/tige.service';
import {Tige} from '../../model/Tige';
import {CompositionService} from '../../services/composition.service';
import {ElementComposition} from 'src/app/model/ElementComposition';
import {faEdit, faPlus} from '@fortawesome/free-solid-svg-icons';
import {NgbPaginationConfig} from '@ng-bootstrap/ng-bootstrap';
import {PageEvent} from '@angular/material/paginator';
import {Client} from '../../model/Client';
import {Router} from '@angular/router';

@Component({
  selector: 'app-tiges-display',
  templateUrl: './tiges-display.component.html',
  styleUrls: ['./tiges-display.component.css'],
  providers: [NgbPaginationConfig]
})
export class TigesDisplayComponent implements OnInit {


  faPlus = faPlus;
  faEdit = faEdit;

tiges: Tige[];

quantiteElt: number[] = [];

page: PageEvent;
pageSize = 10;
pageSizeOptions = [10, 25, 50];
length: number;
lowValue = 0;
highValue = 10;

tigesTotal: Tige[];

editing: boolean[] = [];

  clientSelected: Client;

  disableAjout: boolean;

  @Output() tigesAjoutees = new EventEmitter<ElementComposition>();

  constructor(private tigeService: TigeService,
              private compositionservice: CompositionService,
              private config: NgbPaginationConfig,
              private router: Router) {
                // config.size = 'sm';
                config.boundaryLinks = true;
               }

  ngOnInit(): void {
    if (this.router.url === '/atelier-chant-de-fleur/tiges'){
      this.disableAjout = true;
    }else{
      this.disableAjout = false;
    }
    this.tigeService.getAll();
    this.tigeService.currentAllTiges.subscribe(tiges => {
      this.tiges = tiges;
      this.tigesTotal = tiges;
      this.length = this.tigesTotal.length;
      for ( let x = 0, ln = this.length ; x < ln; x++){
        this.editing[x] = false;
        this.quantiteElt[x] = 1;
      }
    });
  }

  onClickAjoutComposition(tige: Tige, index: number): void {
    const elt: ElementComposition = new ElementComposition();
    elt.id = tige.id;
    elt.nom = tige.nom;
    elt.type = 'TIGE';
    elt.prixUnitaire = tige.prixUnitaire;
    elt.quantite = this.quantiteElt[index];
    for ( let x = 0, ln = this.length ; x < ln; x++){
      this.editing[x] = false;
    }
    this.tigesAjoutees.emit(elt);
  }

  onChangeResearch(e: any): void{
    this.tiges = [];
    this.tigesTotal.forEach(t => {
      if (t.nom.toLowerCase().startsWith(e.target.value.toLowerCase())){
        this.tiges.push(t);
      }
    });
  }

  getPaginatorData(event: PageEvent): PageEvent{
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

  selectClient(event: Client): void{
    this.clientSelected = event;
  }
}
