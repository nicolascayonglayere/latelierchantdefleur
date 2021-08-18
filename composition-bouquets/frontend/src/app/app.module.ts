import { BrowserModule } from '@angular/platform-browser';
import {NgModule, Component, APP_INITIALIZER} from '@angular/core';
import { UiSwitchModule } from 'ngx-ui-switch';
import { AppComponent } from './app.component';
import { MenuNavigationComponent } from './layout/menu-navigation/menu-navigation.component';
import { TigesDisplayComponent } from './features/tiges-display/tiges-display.component';
import { MateriauxDisplayComponent } from './features/materiaux-display/materiaux-display.component';
import { RouterModule, Routes } from '@angular/router';
import { BandeauBouquetComponent } from './features/bandeau-bouquet/bandeau-bouquet.component';
import { DataTablesModule } from 'angular-datatables';
import { HttpClientModule } from '@angular/common/http';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TigeEditComponent } from './features/tige-edit/tige-edit.component';
import { MateriauEditComponent } from './features/materiau-edit/materiau-edit.component';
import { FournisseursDisplayComponent } from './features/fournisseurs-display/fournisseurs-display.component';
import { FournisseurEditComponent } from './features/fournisseur-edit/fournisseur-edit.component';
import { CompositionsDisplayComponent } from './features/compositions-display/compositions-display.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDividerModule } from '@angular/material/divider';
import { SnackbarSuccessComponent } from './layout/snackbar/snackbar-success/snackbar-success.component';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { CompositionAddDialogComponent } from './features/bandeau-bouquet/composition-add-dialog/composition-add-dialog.component';
import { CompositionAddImageComponent } from './features/compositions-display/composition-add-image/composition-add-image.component';
import { EvenementDetailDisplayComponent } from './features/evenement-detail-display/evenement-detail-display.component';
import { EvenementAddDialogComponent } from './features/bandeau-bouquet/evenement-add-dialog/evenement-add-dialog.component';
import { EvenementsDisplayComponent } from './features/evenements-display/evenements-display.component';
import { CompositionDetailDisplayComponent } from './features/composition-detail-display/composition-detail-display.component';
import { CarouselModule, WavesModule } from 'angular-bootstrap-md';
import { CompositionDelDialogComponent } from './features/composition-del-dialog/composition-del-dialog.component';
import { EvenementEditComponent } from './features/evenement-edit/evenement-edit.component';
import { RxStompService  } from '@stomp/ng2-stompjs';
import { ClientEditComponent } from './features/client-edit/client-edit.component';
import { ClientsDisplayComponent } from './features/clients-display/clients-display.component';
import { BandeauClientComponent } from './features/bandeau-client/bandeau-client.component';
import { CreationCompositionComponent } from './features/creation-composition/creation-composition.component';
import {MatTabsModule} from '@angular/material/tabs';
import { BandeauEvenementComponent } from './features/bandeau-evenement/bandeau-evenement.component';
import { CreationEvenementComponent } from './features/creation-evenement/creation-evenement.component';
import { CompositionQuantiteAddDialogComponent } from './features/compositions-display/composition-quantite-add-dialog/composition-quantite-add-dialog.component';
import { TableauDeBordComponent } from './features/tableau-de-bord/tableau-de-bord.component';
import {CalendarModule, DateAdapter} from 'angular-calendar';
import {adapterFactory} from 'angular-calendar/date-adapters/date-fns';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import {KeycloakAngularModule, KeycloakService} from 'keycloak-angular';
// import {KeycloakConfigService} from './services/keycloak-config.service';
import {filter, flatMap} from 'rxjs/operators';
// import {AppAuthGuard} from './app-auth-guard';

registerLocaleData(localeFr);

const appRoutes: Routes = [
  { path: 'atelier-chant-de-fleur/materiaux', component: MateriauxDisplayComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/tiges', component: TigesDisplayComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/tiges/edit/:id', component: TigeEditComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/materiaux/edit/:id', component: MateriauEditComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/fournisseurs', component: FournisseursDisplayComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/fournisseurs/edit/:id', component: FournisseurEditComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/compositions', component: CompositionsDisplayComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/compositions/images/:id', component: CompositionAddImageComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/compositions/:id', component: CompositionDetailDisplayComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/evenements/:id', component: EvenementDetailDisplayComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/evenements', component: EvenementsDisplayComponent},//, canActivate: [AppAuthGuard]},
  { path: 'atelier-chant-de-fleur/evenements/:id/edit', component: EvenementEditComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/clients', component: ClientsDisplayComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/clients/:id/edit', component: ClientEditComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/creation-composition', component: CreationCompositionComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/creation-evenement', component: CreationEvenementComponent},//, canActivate: [AppAuthGuard] },
  { path: 'atelier-chant-de-fleur/home', component: TableauDeBordComponent},//, canActivate: [AppAuthGuard] },
  { path: '', redirectTo: 'atelier-chant-de-fleur/home', pathMatch: 'full' },
];

// export function initializer(keycloakService: KeycloakService,
//                             keycloakConfigService: KeycloakConfigService): () => Promise<boolean> {
//   return (): Promise<boolean> => keycloakConfigService.getConfig()
//     .pipe(
//       filter(config => config.enabled),
//       flatMap(config => {
//         return keycloakService.init({
//           config: {
//             url: config.authServerUrl,
//             realm: config.realm,
//             clientId: config.resource
//             // credentials: {
//             //   secret: config.credentials.secret
//             // }
//           },
//           initOptions: {
//             onLoad: 'check-sso',
//             checkLoginIframe: false
//           }
//         });
//       })).toPromise();
// }

@NgModule({
  declarations: [
    AppComponent,
    MenuNavigationComponent,
    TigesDisplayComponent,
    MateriauxDisplayComponent,
    BandeauBouquetComponent,
    TigeEditComponent,
    MateriauEditComponent,
    FournisseursDisplayComponent,
    FournisseurEditComponent,
    CompositionsDisplayComponent,
    SnackbarSuccessComponent,
    CompositionAddDialogComponent,
    CompositionAddImageComponent,
    EvenementDetailDisplayComponent,
    EvenementAddDialogComponent,
    EvenementsDisplayComponent,
    CompositionDetailDisplayComponent,
    CompositionDelDialogComponent,
    EvenementEditComponent,
    ClientEditComponent,
    ClientsDisplayComponent,
    BandeauClientComponent,
    CreationCompositionComponent,
    BandeauEvenementComponent,
    CreationEvenementComponent,
    CompositionQuantiteAddDialogComponent,
    TableauDeBordComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    DataTablesModule,
    HttpClientModule,
    FontAwesomeModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatListModule,
    MatPaginatorModule,
    MatCardModule,
    MatDialogModule,
    MatTooltipModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDividerModule,
    NgxMaterialTimepickerModule.setLocale('fr-FR'),
    CarouselModule,
    WavesModule,
    UiSwitchModule,
    MatTabsModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    KeycloakAngularModule,
  ],
  providers: [
    RxStompService,
    // {
    //   provide: APP_INITIALIZER,
    //   useFactory: initializer,
    //   multi: true,
    //   deps: [KeycloakService, KeycloakConfigService]
    // }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
