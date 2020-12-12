import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

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


const appRoutes: Routes = [
  { path: 'atelier-chant-de-fleur/materiaux', component: MateriauxDisplayComponent },
  { path: 'atelier-chant-de-fleur/tiges', component: TigesDisplayComponent },
  { path: 'atelier-chant-de-fleur/tiges/edit/:id', component: TigeEditComponent },
  { path: 'atelier-chant-de-fleur/materiaux/edit/:id', component: MateriauEditComponent },
  { path: 'atelier-chant-de-fleur/fournisseurs', component: FournisseursDisplayComponent },
  { path: 'atelier-chant-de-fleur/fournisseurs/edit/:id', component: FournisseurEditComponent },
  { path: 'atelier-chant-de-fleur/compositions', component: CompositionsDisplayComponent },
  { path: 'atelier-chant-de-fleur/compositions/images/:id', component: CompositionAddImageComponent },
  { path: 'atelier-chant-de-fleur/compositions/:id', component: CompositionDetailDisplayComponent },
  { path: 'atelier-chant-de-fleur/evenements/:id', component: EvenementDetailDisplayComponent },
  { path: 'atelier-chant-de-fleur/evenements', component: EvenementsDisplayComponent},
  { path: 'atelier-chant-de-fleur/evenements/:id/edit', component: EvenementEditComponent },
  // { path: '', redirectTo: 'atelier-chant-de-fleur/tiges', pathMatch: 'full' },
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
    EvenementEditComponent
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
    WavesModule
  ],
  providers: [
    RxStompService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
