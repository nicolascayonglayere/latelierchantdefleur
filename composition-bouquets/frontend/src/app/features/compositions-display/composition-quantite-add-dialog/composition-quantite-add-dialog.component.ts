import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {CompositionCommande} from '../../../model/CompositionCommande';

@Component({
  selector: 'app-composition-quantite-add-dialog',
  templateUrl: './composition-quantite-add-dialog.component.html',
  styleUrls: ['./composition-quantite-add-dialog.component.css']
})
export class CompositionQuantiteAddDialogComponent implements OnInit {

  quantite = 0;

  constructor(@Inject(MAT_DIALOG_DATA) public data: CompositionCommande) { }

  ngOnInit(): void {
  }

}
