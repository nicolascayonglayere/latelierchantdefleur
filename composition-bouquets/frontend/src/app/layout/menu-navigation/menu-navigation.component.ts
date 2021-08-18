import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {KeycloakService} from 'keycloak-angular';

const rootUrl = 'http://127.0.0.1:8181/atelier-chant-de-fleur/keycloak';

@Component({
  selector: 'app-menu-navigation',
  templateUrl: './menu-navigation.component.html',
  styleUrls: ['./menu-navigation.component.css']
})
export class MenuNavigationComponent implements OnInit {

  constructor(private httpClient: HttpClient, private router: Router,  protected keycloakService: KeycloakService) { }

  ngOnInit(): void {
  }

  logout() : void {
    console.log('DECO');
    // this.keycloakService.logout();
    // sessionStorage.clear();
    // this.router.navigate(['/']);
      // this.httpClient.get(rootUrl+'/logout').subscribe(resp => {
      //   console.log(resp);

      // });
  }

}
