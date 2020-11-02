import { Component, OnInit } from '@angular/core';
import { Materiau } from '../../model/Materiau';
import { DataTablesResponse } from '../../model/DataTablesResponse';
import { MateriauService } from '../../services/materiau.service';

@Component({
  selector: 'app-materiaux-display',
  templateUrl: './materiaux-display.component.html',
  styleUrls: ['./materiaux-display.component.css']
})
export class MateriauxDisplayComponent implements OnInit {

  tsMateriaux: Materiau[];
  materiaux: Materiau[];
  dtOptions: DataTables.Settings = {};
  dataTablesData: DataTablesResponse;

  constructor(private materiauService: MateriauService) { }

  ngOnInit(): void {
    const that = this;
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      serverSide: true,
      processing: true,      
      ajax: (dataTablesParameters: any, callback) => {
        that.materiauService.getAllDataTablesMateriaux(dataTablesParameters)
        .subscribe(resp => {
          that.materiaux = resp.data;
          // if(that.tsMateriaux.length > that.dtOptions.pageLength){
          //   that.materiaux = that.constructionMateriaux(that.tsMateriaux, that.dtOptions.pageLength);
          // }          
            callback({
              recordsTotal: resp.recordsTotal,
              recordsFiltered: resp.recordsFiltered,
              data: []
            });
          });          
      },
      columns: [{ data: 'id' }, { data: 'nom' }, { data: 'prixUnitaire'}]
    };


  }

  private constructionMateriaux(tsMateriaux: Materiau[], size: number): Materiau[]{
    for (let index = 0; index < size; index++) {
      this.materiaux.push(tsMateriaux[index]);        
    } 
    return this.materiaux;
  }
}
