import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

import { AppComponent } from './app.component';
import { MenuNavigationComponent } from './features/menu-navigation/menu-navigation.component';
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

const appRoutes: Routes = [
  { path: 'atelier-chant-de-fleur/materiaux', component: MateriauxDisplayComponent },
  { path: 'atelier-chant-de-fleur/tiges', component: TigesDisplayComponent },
  { path: 'atelier-chant-de-fleur/tiges/edit/:id', component: TigeEditComponent },
  { path: 'atelier-chant-de-fleur/materiaux/edit/:id', component: MateriauEditComponent },
  { path: 'atelier-chant-de-fleur/fournisseurs', component: FournisseursDisplayComponent },
  { path: 'atelier-chant-de-fleur/fournisseurs/edit/:id', component: FournisseurEditComponent }
];

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
    FournisseurEditComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    DataTablesModule,
    HttpClientModule,
    FontAwesomeModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
