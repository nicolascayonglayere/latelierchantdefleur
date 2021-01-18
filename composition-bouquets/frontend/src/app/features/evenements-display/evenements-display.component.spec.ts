import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EvenementsDisplayComponent } from './commandes-display.component';

describe('EvenementsDisplayComponent', () => {
  let component: EvenementsDisplayComponent;
  let fixture: ComponentFixture<EvenementsDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EvenementsDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EvenementsDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
