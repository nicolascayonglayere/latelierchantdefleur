import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MateriauxDisplayComponent } from './materiaux-display.component';

describe('MateriauxDisplayComponent', () => {
  let component: MateriauxDisplayComponent;
  let fixture: ComponentFixture<MateriauxDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MateriauxDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MateriauxDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
