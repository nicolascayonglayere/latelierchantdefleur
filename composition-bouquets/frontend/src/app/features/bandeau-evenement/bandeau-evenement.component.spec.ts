import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BandeauEvenementComponent } from './bandeau-evenement.component';

describe('BandeauEvenementComponent', () => {
  let component: BandeauEvenementComponent;
  let fixture: ComponentFixture<BandeauEvenementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BandeauEvenementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BandeauEvenementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
