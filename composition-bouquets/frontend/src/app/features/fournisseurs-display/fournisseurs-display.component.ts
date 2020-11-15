import { PageEvent } from '@angular/material/paginator';
import { FournisseurService } from './../../services/fournisseur.service';
import { Fournisseur } from './../../model/Fournisseur';
import { Component, OnInit } from '@angular/core';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-fournisseurs-display',
  templateUrl: './fournisseurs-display.component.html',
  styleUrls: ['./fournisseurs-display.component.css']
})
export class FournisseursDisplayComponent implements OnInit {

  faEdit = faEdit;

  pageIndex:number = 0;
page: PageEvent;// = 1;
pageSize = 10;
pageSizeOptions = [10, 25, 50];
length: number;
lowValue:number = 0;
highValue:number = 10;


  tsFournisseurs: Fournisseur[];
  fournisseursResearch: Fournisseur[];

  constructor(private fournisseurService: FournisseurService, config: NgbPaginationConfig) {
    config.boundaryLinks = true;
   }

  ngOnInit(): void {
    this.fournisseursResearch = [];
    this.fournisseurService.getAll();
    this.fournisseurService.currentAllFournisseur.subscribe(resp =>{
      this.tsFournisseurs = resp;
      this.length = this.tsFournisseurs.length;
    });
  }

  onChangeResearch(e: any): void{
    this.fournisseursResearch = [];
    this.tsFournisseurs.forEach(t => {
      if (t.nom.toLowerCase().startsWith(e.target.value.toLowerCase())){
        this.fournisseursResearch.push(t);
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
