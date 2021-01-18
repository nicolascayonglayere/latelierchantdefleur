import { Component, Input, OnInit } from '@angular/core';
import { Client } from 'src/app/model/Client';
import {Evenement} from '../../model/Evenement';
import {EvenementService} from '../../services/evenement.service';

@Component({
  selector: 'app-bandeau-client',
  templateUrl: './bandeau-client.component.html',
  styleUrls: ['./bandeau-client.component.css']
})
export class BandeauClientComponent implements OnInit {

  @Input() clientSelected: Client;

  totTtesCommandes = 0;
  derniereCommandeEnCours = true;
  derniereCommandeAcquittee: Evenement;
  montantDerniereCommandeAcquittee = 0;

  constructor(private evtService: EvenementService) { }

  ngOnInit(): void {
    console.log('INIT', this.clientSelected);
    this.calculMontantToutesCommandes(this.clientSelected.evenementsRest);
    this.derniereCommandeEnCours = this.isDerniereCommandeEnCours(this.clientSelected.evenementsRest[0]);
    this.derniereCommandeAcquittee = new Evenement();
    const derniereCommandeAcquitteeVide = this.clientSelected.evenementsRest.filter(e => this.isCommandeAcquittee(e))[0];

    this.calculMontantDerniereCommandeAcquittee(derniereCommandeAcquitteeVide);
  }

  private calculMontantToutesCommandes(evts: Evenement[]): void{
    evts.forEach(e => {
      this.evtService.getById(e.id).subscribe(resp => {
        resp.compositions.forEach(c => {
          const montantCompoHt = c.composition.prixUnitaire * (c.composition.tva / 100) + c.composition.prixUnitaire;
          const montantCompoTxHoraire = montantCompoHt + montantCompoHt * c.composition.tauxHoraire;
          const montantCompoMarge = montantCompoTxHoraire + montantCompoTxHoraire * (c.composition.tauxHoraire / 100);
          const montantCompo = c.quantite * montantCompoMarge;
          this.totTtesCommandes = this.totTtesCommandes + montantCompo + resp.forfaitDplct + resp.forfaitMo;
        });
      });
    });
  }

  private calculMontantDerniereCommandeAcquittee(commande: Evenement): void{
    console.log(commande);
    this.evtService.getById(commande.id).subscribe(resp => {
      console.log('Commande by id',resp);
      this.derniereCommandeAcquittee = resp;
      resp.compositions.forEach(c => {
        const montantCompoHt = c.composition.prixUnitaire * (c.composition.tva / 100) + c.composition.prixUnitaire;
        const montantCompoTxHoraire = montantCompoHt + montantCompoHt * c.composition.tauxHoraire;
        const montantCompoMarge = montantCompoTxHoraire + montantCompoTxHoraire * (c.composition.tauxHoraire / 100);
        const montantCompo = c.quantite * montantCompoMarge;
        this.montantDerniereCommandeAcquittee = this.montantDerniereCommandeAcquittee + montantCompo + resp.forfaitDplct + resp.forfaitMo;
        console.log(resp, this.montantDerniereCommandeAcquittee);
      });
    });
  }

  private isDerniereCommandeEnCours(commande: Evenement): boolean{
    return new Date(commande.datePrevue) > new Date();
  }

  private isCommandeAcquittee(commande: Evenement): boolean{
    return new Date(commande.datePrevue) < new Date();
  }

}
