import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TigeService } from '../../services/tige.service'
import { Tige } from '../../model/Tige'
import { CompositionService} from '../../services/composition.service';
import { ElementComposition } from 'src/app/model/ElementComposition';

@Component({
  selector: 'app-tiges-display',
  templateUrl: './tiges-display.component.html',
  styleUrls: ['./tiges-display.component.css']
})
export class TigesDisplayComponent implements OnInit {

tiges: Tige[];
@Output() tigeCompositionEvent = new EventEmitter<Tige[]>();
tigeComposition: ElementComposition[] = [];
eltSelected: ElementComposition;

  constructor(private tigeService: TigeService, 
              private compositionservice: CompositionService) { }

  ngOnInit(): void {
    this.tigeService.getAll().subscribe(tiges => {
      this.tiges = tiges;
    });
    this.compositionservice.currentElements.subscribe(resp => {
      this.eltSelected = new ElementComposition();
      this.eltSelected = resp;
    });
  }

  onClickAjoutComposition(tige: Tige) {
    const elt: ElementComposition = new ElementComposition();
    elt.id = tige.id;
    elt.nom = tige.nom;
    elt.type = 'TIGE';
    elt.prixUnitaire = tige.prixUnitaire/100;
    elt.quantite = 1;
    this.tigeComposition.push(elt);
    console.log(this.tigeComposition);
    // this.compositionservice.recuperationElements(this.tigeComposition);
    this.compositionservice.recuperationElements(elt);
  }

}
