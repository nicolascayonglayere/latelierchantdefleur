import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientsDisplayComponent } from './clients-display.component';

describe('ClientsDisplayComponent', () => {
  let component: ClientsDisplayComponent;
  let fixture: ComponentFixture<ClientsDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientsDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientsDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
