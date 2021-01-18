import { ClientService } from './../../services/client.service';
import { Client } from './../../model/Client';
import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { faEdit } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-clients-display',
  templateUrl: './clients-display.component.html',
  styleUrls: ['./clients-display.component.css']
})
export class ClientsDisplayComponent implements OnInit {
  faEdit = faEdit;

  clientsResearch: Client[] = [];
  clients: Client[] = [];

  pageIndex:number = 0;
  page: PageEvent;// = 1;
  pageSize = 10;
  pageSizeOptions = [10, 25, 50];
  length: number;
  lowValue:number = 0;
  highValue:number = 10;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.clientService.getAll();
    this.clientService.allClientsSource.subscribe(resp => {
      this.clients = resp;
      this.clientsResearch = resp;
      this.length = resp.length;
    });
  }

  onChangeResearch(e: any): void{
    this.clientsResearch = [];
    this.clients.forEach(t => {
      if (t.nom1.toLowerCase().startsWith(e.target.value.toLowerCase())){
        this.clientsResearch.push(t);
      }
    });
  }

  getPaginatorData(event: PageEvent): PageEvent{
    this.lowValue = event.pageIndex * event.pageSize;
    this.highValue = this.lowValue + event.pageSize;
    return event;
}

}
