import { Composition } from 'src/app/model/Composition';

import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-composition-add-dialog',
  templateUrl: './composition-add-dialog.component.html',
  styleUrls: ['./composition-add-dialog.component.css'],
})
export class CompositionAddDialogComponent {
  constructor(
    // public dialogRef: MatDialogRef<CompositionAddDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Composition
  ) { console.log(data);}

  // onNoClick(): void {
  //   this.dialogRef.close();
  // }
}
