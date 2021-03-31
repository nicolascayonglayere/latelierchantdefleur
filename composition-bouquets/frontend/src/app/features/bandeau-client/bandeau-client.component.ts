import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import { Client } from 'src/app/model/Client';
import {Evenement} from '../../model/Evenement';
import {EvenementService} from '../../services/evenement.service';

@Component({
  selector: 'app-bandeau-client',
  templateUrl: './bandeau-client.component.html',
  styleUrls: ['./bandeau-client.component.css']
})
export class BandeauClientComponent implements OnInit, OnChanges {

  @Input() clientSelected: Client;

  totTtesCommandes = 0;
  derniereCommandeEnCours = true;
  derniereCommandeAcquittee: Evenement;
  montantDerniereCommandeAcquittee = 0;
  montantDerniereCommandeEnCours = 0;

  constructor(private evtService: EvenementService) { }

  ngOnInit(): void {
    // console.log('INIT', this.clientSelected);
    // this.derniereCommandeAcquittee = new Evenement();
    // if(this.clientSelected.evenementsRest.length > 0){
    //   this.calculMontantToutesCommandes(this.clientSelected.evenementsRest);
    //   this.derniereCommandeEnCours = this.isDerniereCommandeEnCours(this.clientSelected.evenementsRest[0]);
    //   const derniereCommandeAcquitteeVide = this.clientSelected.evenementsRest.filter(e => this.isCommandeAcquittee(e))[0];
    //   this.calculMontantDerniereCommandeAcquittee(derniereCommandeAcquitteeVide);
    // }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['clientSelected']){
      console.log('CHANGES', this.clientSelected);
      this.init();
    }
  }

  private init(): void {
    console.log('INIT', this.clientSelected);
    this.derniereCommandeAcquittee = new Evenement();
    this.totTtesCommandes = 0;
    this.montantDerniereCommandeAcquittee = 0;
    this.montantDerniereCommandeEnCours = 0;
    if (this.clientSelected?.evenementsRest.length > 0){
      this.calculMontantToutesCommandes(this.clientSelected.evenementsRest);
      this.derniereCommandeEnCours = this.isDerniereCommandeEnCours(this.clientSelected.evenementsRest[0]);
      if (this.derniereCommandeEnCours){
        this.calculMontantDerniereCommandeEnCours(this.clientSelected.evenementsRest[0]);
      }
      const derniereCommandeAcquitteeVide = this.clientSelected.evenementsRest.filter(e => this.isCommandeAcquittee(e))[0];
      console.log(derniereCommandeAcquitteeVide);
      if (derniereCommandeAcquitteeVide){
        this.calculMontantDerniereCommandeAcquittee(derniereCommandeAcquitteeVide);
      }
    }
  }

  private calculMontantToutesCommandes(evts: Evenement[]): void{
    evts.forEach(e => {
      this.evtService.getById(e.id).subscribe(resp => {
        resp.compositions.forEach(c => {
          // const montantCompoHt = c.composition.prixUnitaire * (c.composition.tva / 100) + c.composition.prixUnitaire;
          // const montantCompoTxHoraire = montantCompoHt + c.composition.tauxHoraire * c.composition.dureeCreation;
          // const montantCompoMarge = montantCompoTxHoraire + montantCompoTxHoraire * (c.composition.marge / 100);
          const montantCompo = c.quantite * c.composition.prixUnitaire;//montantCompoMarge;
          this.totTtesCommandes = this.totTtesCommandes + montantCompo;
        });
        this.totTtesCommandes = this.totTtesCommandes  + resp.forfaitDplct + resp.forfaitMo;
      });
    });
  }

  private calculMontantDerniereCommandeAcquittee(commande: Evenement): void{
    this.evtService.getById(commande.id).subscribe(resp => {
      this.derniereCommandeAcquittee = resp;
      resp.compositions.forEach(c => {
        // const montantCompoHt = c.composition.prixUnitaire * (c.composition.tva / 100) + c.composition.prixUnitaire;
        // const montantCompoTxHoraire = montantCompoHt + c.composition.tauxHoraire * c.composition.dureeCreation;
        // const montantCompoMarge = montantCompoTxHoraire + montantCompoTxHoraire * (c.composition.marge / 100);
        const montantCompo = c.quantite * c.composition.prixUnitaire;//montantCompoMarge;
        this.montantDerniereCommandeAcquittee = this.montantDerniereCommandeAcquittee + montantCompo;
      });
      this.montantDerniereCommandeAcquittee = this.montantDerniereCommandeAcquittee + resp.forfaitDplct + resp.forfaitMo;
    });
  }

  private calculMontantDerniereCommandeEnCours(commande: Evenement): void{
    this.evtService.getById(commande.id).subscribe(resp => {
      this.derniereCommandeAcquittee = resp;
      resp.compositions.forEach(c => {
        // const montantCompoHt = c.composition.prixUnitaire * (c.composition.tva / 100) + c.composition.prixUnitaire;
        // const montantCompoTxHoraire = montantCompoHt + c.composition.tauxHoraire * c.composition.dureeCreation;
        // const montantCompoMarge = montantCompoTxHoraire + montantCompoTxHoraire * (c.composition.marge / 100);
        const montantCompo = c.quantite * c.composition.prixUnitaire; //montantCompoMarge;
        this.montantDerniereCommandeEnCours = this.montantDerniereCommandeEnCours + montantCompo;
      });
      this.montantDerniereCommandeEnCours = this.montantDerniereCommandeEnCours + resp.forfaitDplct + resp.forfaitMo;
    });
  }

  private isDerniereCommandeEnCours(commande: Evenement): boolean{
    return new Date(commande.datePrevue) > new Date();
  }

  private isCommandeAcquittee(commande: Evenement): boolean{
    console.log(new Date(commande.datePrevue) < new Date());
    return new Date(commande.datePrevue) < new Date();
  }

}
