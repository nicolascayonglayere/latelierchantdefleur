import { NgForm } from '@angular/forms';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TigeService } from '../../services/tige.service';
import { Tige } from '../../model/Tige';
import { CompositionService} from '../../services/composition.service';
import { ElementComposition } from 'src/app/model/ElementComposition';
import { faPlus, faCheckCircle, faEdit } from '@fortawesome/free-solid-svg-icons';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-tiges-display',
  templateUrl: './tiges-display.component.html',
  styleUrls: ['./tiges-display.component.css'],
  providers: [NgbPaginationConfig]
})
export class TigesDisplayComponent implements OnInit {
  faPlus = faPlus;
  faCheckCircle = faCheckCircle;
  faEdit = faEdit;

tiges: Tige[];
tigeComposition: ElementComposition[] = [];
eltSelected: ElementComposition;
quantiteElt: number = 1;

pageIndex:number = 0;
page: PageEvent;// = 1;
pageSize = 10;
pageSizeOptions = [10, 25, 50];
length: number;
lowValue:number = 0;
highValue:number = 50;

tigesTotal: Tige[];

  constructor(private tigeService: TigeService,
              private compositionservice: CompositionService,
              config: NgbPaginationConfig) {
                config.size = 'sm';
                config.boundaryLinks = true;
               }

  ngOnInit(): void {
    this.tigeService.getAll().subscribe(tiges => {
      console.log(tiges);
      this.tiges = tiges;
      this.tigesTotal = tiges;
      this.length = this.tigesTotal.length;
    });
    this.compositionservice.currentElements.subscribe(resp => {
      this.eltSelected = new ElementComposition();
      this.eltSelected = resp;
    });
  }

  onClickAjoutComposition(tige: Tige): void {
    const elt: ElementComposition = new ElementComposition();
    elt.id = tige.id;
    elt.nom = tige.nom;
    elt.type = 'TIGE';
    elt.prixUnitaire = tige.prixUnitaire / 100;
    elt.quantite = this.quantiteElt;
    this.tigeComposition.push(elt);
    this.compositionservice.recuperationElements(elt);
  }

  onSubmitQuantite(form: NgForm): void{
    this.quantiteElt = form.value['quantite'];
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
    console.log(event);
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
}
}
