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

  page = 1;
  pageSize = 10;

  tsFournisseurs: Fournisseur[];
  fournisseursResearch: Fournisseur[];

  constructor(private fournisseurService: FournisseurService, config: NgbPaginationConfig) {
    config.boundaryLinks = true;
   }

  ngOnInit(): void {
    this.fournisseursResearch = [];
    this.fournisseurService.getAll().subscribe(resp =>{
      this.tsFournisseurs = resp;
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

}
