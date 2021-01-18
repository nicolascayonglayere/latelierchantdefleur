import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EvenementAddDialogComponent } from './commande-add-dialog.component';

describe('EvenementAddDialogComponent', () => {
  let component: EvenementAddDialogComponent;
  let fixture: ComponentFixture<EvenementAddDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EvenementAddDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EvenementAddDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
