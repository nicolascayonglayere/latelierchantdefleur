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

  ttesComposition: Composition[];
  compositionResearch : Composition[];

  page = 1;
  pageSize = 10;

  constructor(private compositionService: CompositionService, config: NgbPaginationConfig) {
    config.boundaryLinks = true;
  }

  ngOnInit(): void {
    this.compositionService.getAll().subscribe(resp =>{
      this.ttesComposition = resp;
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

}
