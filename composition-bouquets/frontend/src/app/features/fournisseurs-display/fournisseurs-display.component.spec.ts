import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FournisseursDisplayComponent } from './fournisseurs-display.component';

describe('FournisseursDisplayComponent', () => {
  let component: FournisseursDisplayComponent;
  let fixture: ComponentFixture<FournisseursDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FournisseursDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FournisseursDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
