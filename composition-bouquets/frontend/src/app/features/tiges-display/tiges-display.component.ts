import { NgForm } from '@angular/forms';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TigeService } from '../../services/tige.service';
import { Tige } from '../../model/Tige';
import { CompositionService} from '../../services/composition.service';
import { ElementComposition } from 'src/app/model/ElementComposition';
import { faPlus, faCheckCircle } from '@fortawesome/free-solid-svg-icons';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-tiges-display',
  templateUrl: './tiges-display.component.html',
  styleUrls: ['./tiges-display.component.css'],
  providers: [NgbPaginationConfig]
})
export class TigesDisplayComponent implements OnInit {
  faPlus = faPlus;
  faCheckCircle = faCheckCircle;

tiges: Tige[];
@Output() tigeCompositionEvent = new EventEmitter<Tige[]>();
tigeComposition: ElementComposition[] = [];
eltSelected: ElementComposition;
quantiteElt: number = 1;
page = 1;
pageSize = 10;

  constructor(private tigeService: TigeService,
              private compositionservice: CompositionService,
              config: NgbPaginationConfig) {
                config.size = 'sm';
                config.boundaryLinks = true;
               }

  ngOnInit(): void {
    this.tigeService.getAll().subscribe(tiges => {
      this.tiges = tiges;
    });
    this.compositionservice.currentElements.subscribe(resp => {
      this.eltSelected = new ElementComposition();
      this.eltSelected = resp;
    });
  }

  onClickAjoutComposition(tige: Tige): void {
    const elt: ElementComposition = new ElementComposition();
    elt.id = tige.id;
    elt.nom = tige.nom;
    elt.type = 'TIGE';
    elt.prixUnitaire = tige.prixUnitaire / 100;
    elt.quantite = this.quantiteElt;
    this.tigeComposition.push(elt);
    console.log(this.tigeComposition);
    // this.compositionservice.recuperationElements(this.tigeComposition);
    this.compositionservice.recuperationElements(elt);
  }

  onSubmitQuantite(form: NgForm): void{
    this.quantiteElt = form.value['quantite'];
  }

}
