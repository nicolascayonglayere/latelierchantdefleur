import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreationCompositionComponent } from './creation-composition.component';

describe('CreationCompositionComponent', () => {
  let component: CreationCompositionComponent;
  let fixture: ComponentFixture<CreationCompositionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreationCompositionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreationCompositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
