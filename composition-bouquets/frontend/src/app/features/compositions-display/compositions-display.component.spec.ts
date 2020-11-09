import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositionsDisplayComponent } from './compositions-display.component';

describe('CompositionDisplayComponent', () => {
  let component: CompositionsDisplayComponent;
  let fixture: ComponentFixture<CompositionsDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositionsDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositionsDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
