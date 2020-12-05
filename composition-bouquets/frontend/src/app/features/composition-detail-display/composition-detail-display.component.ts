import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ImageComposition } from './../../model/ImageComposition';
import { CompositionService } from './../../services/composition.service';
import { AfterViewInit, Component, OnInit, SecurityContext } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Composition } from 'src/app/model/Composition';
import { CompositionDisplay } from 'src/app/model/CompositionDisplay';
import { faPlus, faUpload } from '@fortawesome/free-solid-svg-icons';
import { ElementComposition } from 'src/app/model/ElementComposition';

@Component({
  selector: 'app-composition-detail-display',
  templateUrl: './composition-detail-display.component.html',
  styleUrls: ['./composition-detail-display.component.css']
})
export class CompositionDetailDisplayComponent implements OnInit {
  faUpload = faUpload;
  faPlus = faPlus;
  composition: Composition;
  compoDisplay: CompositionDisplay;
  fileInfos: ImageComposition[];
  imgUrl: SafeUrl[] = [];
  compoEvt: ElementComposition[] = [];

  constructor(private compositionService: CompositionService,
              private route: ActivatedRoute,
              private compoService: CompositionService, private sanitizer: DomSanitizer,
              private routerLink: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(resp => {
      this.compoService.getById(+resp.get('id')).subscribe(comp => {
        this.composition = comp;
        this.compoDisplay = this.constructCompoDisplay(this.composition);
        this.refreshListImageDipslay();
      });
    });
  }

  onClickAjoutComposition(composition: CompositionDisplay): void{
    const elt: ElementComposition = new ElementComposition();
    elt.id = composition.id;
    elt.nom = composition.nom;
    elt.type = 'COMPOSITION';
    elt.prixUnitaire = composition.prixUnitaire / 100;
    elt.quantite = 1;
    this.compoEvt.push(elt);
    this.compositionService.recuperationElements(elt);
  }

  onClickAddImageDialog(composition: CompositionDisplay): void{
    this.routerLink.navigate(['atelier-chant-de-fleur', 'compositions', 'images', composition.id]);
  }

  private refreshListImageDipslay(): void{
    this.composition.images.forEach(data => {
      const objectUrl = 'data:image/jpeg;base64,' + data.content;
      data.imgDisplay = this.sanitizer.bypassSecurityTrustUrl(objectUrl);
      // data.imgDisplay = this.sanitizer.sanitize(SecurityContext.HTML, this.sanitizer.bypassSecurityTrustUrl(objectUrl));
      this.imgUrl.push(this.sanitizer.bypassSecurityTrustUrl(objectUrl));
    });
  }

  private constructCompoDisplay(compo: Composition): CompositionDisplay {
    console.log(compo);
    const compoDisplay = new CompositionDisplay();
    compoDisplay.id = compo.id;
    if(compo.nom === null){
      compoDisplay.nom = 'Composition sans nom';
    }else{
      compoDisplay.nom = compo.nom;
    }
    compoDisplay.dateCreation = compo.dateCreation;
    compoDisplay.dureeCreation = compo.dureeCreation;
    compoDisplay.prixUnitaire = compo.prixUnitaire;
    compoDisplay.tiges = compo.elements.filter(e => e.type === 'TIGE');
    compoDisplay.materiaux = compo.elements.filter(e => e.type === 'MATERIAU');
    compoDisplay.qteImage = compo.images.length;
    return compoDisplay;
  }

}
