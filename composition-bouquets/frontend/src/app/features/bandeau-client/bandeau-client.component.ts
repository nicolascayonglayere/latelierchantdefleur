import {AfterViewChecked, AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Client} from 'src/app/model/Client';
import {Evenement} from '../../model/Evenement';
import {EvenementService} from '../../services/evenement.service';
import {ClientService} from '../../services/client.service';
import {ClientSelectedService} from '../../services/client-selected.service';

@Component({
  selector: 'app-bandeau-client',
  templateUrl: './bandeau-client.component.html',
  styleUrls: ['./bandeau-client.component.css']
})
export class BandeauClientComponent implements OnInit, OnChanges{//}, AfterViewChecked {

  @Input() idClientSelected: number;

  clientSelected: Client;
  totTtesCommandes = 0;
  derniereCommandeEnCours = true;
  derniereCommandeAcquittee: Evenement;
  montantDerniereCommandeAcquittee = 0;
  montantDerniereCommandeEnCours = 0;

  constructor(private evtService: EvenementService, private clientService: ClientService, private clientSelectedService: ClientSelectedService) { }

  ngOnInit(): void {
    console.log('INIT bandeau CLIENT');
    this.clientSelectedService.currentClientSelected.subscribe(data => {
      console.log('INIT bandeau CLIENT', data);
      if(data.id){
        this.clientSelected = data;
        this.idClientSelected = data.id;
        this.init();
      }
    });
    // console.log('INIT bandeau CLIENT', this.idClientSelected);
    // if (this.idClientSelected){
    //   console.log('INIT bandeau CLIENT', this.idClientSelected);
    //   this.init();
    // }
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log('CHANGE bandeua-client', changes);
    if (changes.idClientSelected && this.idClientSelected){
      console.log('CHANGES', changes.idClientSelected, this.idClientSelected);
      this.init();
    }
  }
  //
  // ngAfterViewChecked(): void {
  //   if (this.idClientSelected){
  //     console.log('INIT CLIENT AFTER VIEW', this.idClientSelected);
  //     this.init();
  //   }
  // }

  private init(): void {
    // console.log('INIT', this.idClientSelected);
    this.derniereCommandeAcquittee = new Evenement();
    this.totTtesCommandes = 0;
    this.montantDerniereCommandeAcquittee = 0;
    this.montantDerniereCommandeEnCours = 0;

    this.clientService.getById(this.idClientSelected).subscribe(data => {
      console.log('INIT', data);
      this.clientSelected = data;
      if (this.clientSelected?.evenementsRest.length > 0){
        this.calculMontantToutesCommandes(this.clientSelected.evenementsRest);
        this.derniereCommandeEnCours = this.isDerniereCommandeEnCours(this.clientSelected.evenementsRest[0]);
        if (this.derniereCommandeEnCours){
          this.calculMontantDerniereCommandeEnCours(this.clientSelected.evenementsRest[0]);
        }
        const derniereCommandeAcquitteeVide = this.clientSelected.evenementsRest.filter(e => this.isCommandeAcquittee(e))[0];
        console.log('Last commande', derniereCommandeAcquitteeVide);
        if (derniereCommandeAcquitteeVide){
          this.calculMontantDerniereCommandeAcquittee(derniereCommandeAcquitteeVide);
        }
      }
    });
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
    console.log('idCommandeAcquittee', new Date(commande.datePrevue) < new Date());
    return new Date(commande.datePrevue) < new Date();
  }

}
