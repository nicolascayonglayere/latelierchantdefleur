import { Component, Input, OnInit } from '@angular/core';
import { ElementComposition } from 'src/app/model/ElementComposition';
import { Materiau } from 'src/app/model/Materiau';
import { Tige } from 'src/app/model/Tige';
import { CoefficientVariableService } from '../../services/coefficient-variable.service';
import { CompositionService } from '../../services/composition.service';
import {faCheckCircle, faMinusCircle, faCaretDown, faTrash } from '@fortawesome/free-solid-svg-icons';
import { NgForm } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-bandeau-bouquet',
  templateUrl: './bandeau-bouquet.component.html',
  styleUrls: ['./bandeau-bouquet.component.css']
})
export class BandeauBouquetComponent implements OnInit {
  faCheckCircle= faCheckCircle;
  faMinusCircle = faMinusCircle;
  faCaretDown =faCaretDown;
  faTrash = faTrash;
  

  coutIntrant: number = 0;
  coutRevient: number = 0;
  coutMarge: number = 0;
  coutTva: number = 0;
  tempsTravail: number = 0;
  tauxHoraire: number;
  margeActuelle: number;
  tvaActuelle: number;
  tiges: Tige[];
  materiaux: Materiau[] = [];

  tigesCompo: Tige[] = [];
  elementCompo: any[] = [];

  messageModifOk: boolean = false;
  isCollapsed = false;

  constructor(private coefficientVariableService : CoefficientVariableService,
              private compositionService: CompositionService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.coefficientVariableService.getAll().subscribe(resp =>{
      this.tauxHoraire = resp.tauxHoraire/10;
      this.margeActuelle = resp.marge/10;
      this.tvaActuelle = resp.tva/10;
    });
    this.compositionService.currentElements.subscribe(resp =>{
      this.elementCompo.push(resp);
      if(this.elementCompo.length > 0){
        this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
        this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
        this.coutMarge = this.calculMarge(this.coutRevient);
        this.coutTva = this.calculTva(this.coutMarge);
      }
    });
  }

  calculCoutIntrant(elt: ElementComposition[]) : number{
    let coutIntrantCalcul = 0;
    elt.forEach(function (e){
      coutIntrantCalcul = coutIntrantCalcul + e.prixUnitaire;
    });
    return coutIntrantCalcul;
  }

  calculCoutRevient(coutIntrant: number, tempTravail: number) : number{
    return coutIntrant + (tempTravail * this.tauxHoraire);
  }

  calculMarge(coutRevient: number): number{
    return coutRevient*this.margeActuelle;
  }

  calculTva(marge: number): number {
    return marge*this.tvaActuelle;
  }

  onSubmitTempsTravail(form: NgForm) {
    this.tempsTravail = form.value['tempsTravail'];
    if(this.elementCompo.length > 0){
      this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
      this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
      this.coutMarge = this.calculMarge(this.coutRevient);
      this.coutTva = this.calculTva(this.coutMarge);
    }    
}

openVerticallyCentered(content) {
  this.messageModifOk = false;
  this.modalService.open(content, { centered: true });
}

onSubmitTauxHoraire(form: NgForm) {
  this.tauxHoraire = form.value['tauxHoraire'];
  if(this.elementCompo.length > 0){
    this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
    this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
    this.coutMarge = this.calculMarge(this.coutRevient);
    this.coutTva = this.calculTva(this.coutMarge);
  } 
  this.messageModifOk = true;
}

onSubmitMarge(form: NgForm) {
  this.margeActuelle = form.value['marge'];
  if(this.elementCompo.length > 0){
    this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
    this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
    this.coutMarge = this.calculMarge(this.coutRevient);
    this.coutTva = this.calculTva(this.coutMarge);
  } 
  this.messageModifOk = true;  
}

onSubmitTva(form: NgForm) {
  this.tvaActuelle = form.value['tva'];
  if(this.elementCompo.length > 0){
    this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
    this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
    this.coutMarge = this.calculMarge(this.coutRevient);
    this.coutTva = this.calculTva(this.coutMarge);
  } 
  this.messageModifOk = true;
}

onClickRemoveElt(id: number){
  console.log(id);
  this.elementCompo = this.elementCompo.filter(e => e.id !== id);
  console.log(this.elementCompo);
  if(this.elementCompo.length > 0){
    this.coutIntrant = this.calculCoutIntrant(this.elementCompo);
    this.coutRevient = this.calculCoutRevient(this.coutIntrant, this.tempsTravail);
    this.coutMarge = this.calculMarge(this.coutRevient);
    this.coutTva = this.calculTva(this.coutMarge);
  }else {
    this.coutIntrant = 0;
    this.coutRevient = 0;
    this.coutMarge = 0;
    this.coutTva = 0;
    this.tempsTravail = 0;
  }
}

onClikResetCompo(){
  this.elementCompo = [];
  this.coutIntrant = 0;
  this.coutRevient = 0;
  this.coutMarge = 0;
  this.coutTva = 0;
  this.tempsTravail = 0;
}

}
