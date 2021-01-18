import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { faInfoCircle, faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Composition } from './../../model/Composition';
import { MateriauService } from './../../services/materiau.service';
import { TigeService } from './../../services/tige.service';
import { ElementComposition } from '../../model/ElementComposition';
import { Fournisseur } from '../../model/Fournisseur';
import { EvenementService } from '../../services/evenement.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Evenement } from 'src/app/model/Evenement';
import { SnackbarSuccessComponent } from 'src/app/layout/snackbar/snackbar-success/snackbar-success.component';

@Component({
  selector: 'app-evenement-detail-display',
  templateUrl: './evenement-detail-display.component.html',
  styleUrls: ['./evenement-detail-display.component.css']
})
export class EvenementDetailDisplayComponent implements OnInit {
  faInfoCircle = faInfoCircle;
  faEdit = faEdit;
  faTrash = faTrash;

  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  evenement: Evenement;

  fournisseurs: FournisseurElement[] = [];
  tiges: ElementComposition[] = [];
  materiaux: ElementComposition[] = [];

  mntTigesTot = 0;
  mntMatTot = 0;
  mntCompoTot = 0;
  mntCompoTva = 0;

  tpstravailCompo = 0;

  compoDisplay: CompoElt[] = [];

  forfait = 0;
  mainOeuvre = 0;

  constructor(private evtService: EvenementService,
              private route: ActivatedRoute, private tigeService: TigeService, private matService: MateriauService,
              private snackBar: MatSnackBar, private router: Router) { }


  ngOnInit(): void {
    this.route.paramMap.subscribe(resp => {
      this.evtService.getById(+resp.get('id')).subscribe(data => {
        this.evenement = data;
        this.forfait = this.evenement.forfaitDplct;
        this.mainOeuvre = this.evenement.forfaitMo;
        const tigesEvt: ElementComposition[] = [];
        const matEvt: ElementComposition[] = [];
        this.evenement.compositions.forEach(c => {
          // this.m
          this.mntCompoTot = this.mntCompoTot + c.composition.prixUnitaire * c.quantite;
          this.mntCompoTva = this.mntCompoTva + (c.composition.prixUnitaire * c.quantite) * (c.composition.tva / 100);
          this.tpstravailCompo = this.tpstravailCompo + c.composition.dureeCreation;
          if (! this.compoDisplay.find(cd => cd.compo.id === c.id)){
            const compDisp = new CompoElt();
            compDisp.compo = c.composition;
            if (c.composition.nom === null){
              compDisp.compo.nom = 'Composition sans nom';
            }
            compDisp.qte = c.quantite; // this.calculQteCompo(this.evenement.compositions, c.id);
            this.compoDisplay.push(compDisp);
          }
          c.composition.elements.forEach(e => {
            if (e.type === 'TIGE'){
              tigesEvt.push(e);
            }
            if (e.type === 'MATERIAU'){
              matEvt.push(e);
            }
          });


      });
        tigesEvt.forEach(e => {
        if (!this.tiges.find(t => t.id === e.id)){
          e.quantite = this.calculQte(tigesEvt, e.id);
          this.tiges.push(e);
        }
      });

        matEvt.forEach(e => {
        if (!this.materiaux.find(m => m.id === e.id)){
          e.quantite = this.calculQte(matEvt, e.id);
          this.materiaux.push(e);
        }
      });
        this.tiges.forEach(t => {
        this.tigeService.getById(t.id).subscribe(tige => {
          if (!this.fournisseurs.find(f => f.fournisseur.id === tige.fournisseurRest.id)){
            const fournisseurElt = new FournisseurElement();
            fournisseurElt.tiges = [];
            fournisseurElt.tiges.push(t);
            fournisseurElt.fournisseur = tige.fournisseurRest;
            fournisseurElt.materiaux = [];
            this.fournisseurs.push(fournisseurElt);
          }
          else{
            const fournElet = this.fournisseurs.filter(f => f.fournisseur.id === tige.fournisseurRest.id)[0];
            if (! fournElet.tiges.find(tg => tg.id === t.id)){
              fournElet.tiges.push(t);
            }else {
              fournElet.tiges.find(tg => tg.id === t.id).quantite = fournElet.tiges.find(tg => tg.id === t.id).quantite + t.quantite;
            }
          }
        });
        this.mntTigesTot = this.mntTigesTot + t.quantite * t.prixUnitaire;
      });

        this.materiaux.forEach(m => {
        this.matService.getById(m.id).subscribe(mat => {
          if (!this.fournisseurs.find(f => f.fournisseur.id === mat.fournisseurRest.id)){
            const fournisseurElt = new FournisseurElement();
            fournisseurElt.materiaux = [];
            fournisseurElt.materiaux.push(m);
            fournisseurElt.tiges = [];
            fournisseurElt.fournisseur = mat.fournisseurRest;
            this.fournisseurs.push(fournisseurElt);
          }else{
            const fournElet = this.fournisseurs.filter(f => f.fournisseur.id === mat.fournisseurRest.id)[0];
            if (! fournElet.materiaux.find(tg => tg.id === m.id)){
              fournElet.materiaux.push(m);
            }else {
              fournElet.materiaux.find(tg => tg.id === m.id).quantite =
              fournElet.materiaux.find(tg => tg.id === m.id).quantite + m.quantite;
            }
          }
        });
        this.mntMatTot = this.mntMatTot + m.quantite * m.prixUnitaire;
      });
    });
  });
}

onChangeAddComplementMo(): void{
  this.mntCompoTot = this.mntCompoTot + this.mainOeuvre;
}

onChangeAddComplementForfait(): void{
  this.mntCompoTot = this.mntCompoTot + this.forfait;
}

onClickSaveEvenement(): void{
  console.log('SAVE');
}

onClikDeleteEvt(): void {
  this.evtService.delete(this.evenement.id).subscribe(resp => {
    this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Suppression effectuée !'
    });
    this.router.navigate(['atelier-chant-de-fleur', 'evenements']);
  }, err => {
    this.snackBar.openFromComponent(SnackbarSuccessComponent, {
      ...this.configFailed,
      data: 'Erreur lors de la suppression !'
    });
  });
}

captureScreen(): void{
  this.evtService.downloadDevis(this.evenement.id).subscribe(resp => {
    const blob: any = new Blob([resp], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);
    window.open(url);
    this.snackBar.openFromComponent(SnackbarSuccessComponent, {
      ...this.configSuccess,
      data: 'Téléchargement réussi !'
  });
}, err => {
  this.snackBar.openFromComponent(SnackbarSuccessComponent, {
    ...this.configFailed,
    data: 'Erreur lors du téléchargement !'
  });
  });
  }

private calculQte(list: any, id: number): number{
  let qte = 0;
  list.forEach((element, i) => {
    if (element.id === id){
      qte = list[i].quantite + qte;
    }
  });
  return qte;
}

  private calculQteCompo(list: Composition[], id: number): number{
    let qte = 0;
    list.forEach(element => {
      if (element.id === id){
        qte = 1 + qte;
      }
    });
    return qte;
  }
}

export class FournisseurElement{
  fournisseur: Fournisseur;
  tiges: ElementComposition[];
  materiaux: ElementComposition[];
}

export class CompoElt{
  compo: Composition;
  qte: number;
}
