import { CompositionDisplay } from 'src/app/model/CompositionDisplay';
import { PageEvent } from '@angular/material/paginator';
import { CompositionService } from './../../services/composition.service';
import { Component, OnInit } from '@angular/core';
import { Composition } from 'src/app/model/Composition';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-compositions-display',
  templateUrl: './compositions-display.component.html',
  styleUrls: ['./compositions-display.component.css']
})
export class CompositionsDisplayComponent implements OnInit {

  faEdit = faEdit;

  ttesComposition: CompositionDisplay[] = [];
  compositionResearch: Composition[];

  pageIndex:number = 0;
page: PageEvent;// = 1;
pageSize = 10;
pageSizeOptions = [10, 25, 50];
length: number;
lowValue:number = 0;
highValue:number = 10;

  constructor(private compositionService: CompositionService, config: NgbPaginationConfig) {
    config.boundaryLinks = true;
  }

  ngOnInit(): void {
    this.compositionService.getAll();
    this.compositionService.currentAllCompositions.subscribe(resp => {
      resp.forEach(c => this.ttesComposition.push(this.constructCompoDisplay(c)));
      this.length = this.ttesComposition.length;
    });
  }

  onChangeResearch(e: any): void{
    this.compositionResearch = [];
    // this.ttesComposition.forEach(t => {
    //   if (t.nom.toLowerCase().startsWith(e.target.value.toLowerCase())){
    //     this.compositionResearch.push(t);
    //   }
    // });
  }

  getPaginatorData(event: PageEvent): PageEvent{
    // console.log(event);
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
}

private constructCompoDisplay(compo: Composition): CompositionDisplay {
  const compoDisplay = new CompositionDisplay();
  compoDisplay.id = compo.id;
  compoDisplay.dateCreation = compo.dateCreation;
  compoDisplay.dureeCreation = compo.dureeCreation;
  compoDisplay.prixUnitaire = compo.prixUnitaire;
  compoDisplay.tiges = compo.elements.filter(e => e.type === 'TIGE');
  compoDisplay.materiaux = compo.elements.filter(e => e.type === 'MATERIAU');
  return compoDisplay;
}

}


