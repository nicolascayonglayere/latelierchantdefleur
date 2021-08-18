// import {Injectable} from '@angular/core';
// import {HttpClient} from '@angular/common/http';
// import {Observable, of} from 'rxjs';
// import {KeycloakProperties} from '../model/KeycloakProperties';

// @Injectable({
//   providedIn: 'root'
// })
// export class KeycloakConfigService {
//   private config: KeycloakProperties;

//   constructor(private http: HttpClient) {
//   }

//   getConfig(): Observable<KeycloakProperties> {
//     if (this.config) {
//       return of(this.config);
//     } else {
//       const configObservable = this.http.get<KeycloakProperties>('http://127.0.0.1:8181/atelier-chant-de-fleur/keycloak/config');
//       configObservable.subscribe(config => {this.config = config; console.log(config, this.config); });
//       return configObservable;
//     }
//   }
// }
