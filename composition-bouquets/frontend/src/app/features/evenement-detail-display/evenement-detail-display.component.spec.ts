import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EvenementDetailDisplayComponent } from './evenement-detail-display.component';

describe('EvenementCreateComponent', () => {
  let component: EvenementDetailDisplayComponent;
  let fixture: ComponentFixture<EvenementDetailDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EvenementDetailDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EvenementDetailDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
