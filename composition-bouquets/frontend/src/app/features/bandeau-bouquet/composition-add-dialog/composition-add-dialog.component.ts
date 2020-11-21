import { Observable } from 'rxjs';
import { Composition } from 'src/app/model/Composition';

import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-composition-add-dialog',
  templateUrl: './composition-add-dialog.component.html',
  styleUrls: ['./composition-add-dialog.component.css'],
})
export class CompositionAddDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: Composition
  ) { }

}
