import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositionDetailDisplayComponent } from './composition-detail-display.component';

describe('CompositionDetailDisplayComponent', () => {
  let component: CompositionDetailDisplayComponent;
  let fixture: ComponentFixture<CompositionDetailDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositionDetailDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositionDetailDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
