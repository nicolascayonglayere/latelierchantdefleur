import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, Inject, OnInit } from '@angular/core';
import { CompositionDelete } from 'src/app/model/CompositionDelete';

@Component({
  selector: 'app-composition-del-dialog',
  templateUrl: './composition-del-dialog.component.html',
  styleUrls: ['./composition-del-dialog.component.css']
})
export class CompositionDelDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: CompositionDelete) { }

  ngOnInit(): void {
  }

}
