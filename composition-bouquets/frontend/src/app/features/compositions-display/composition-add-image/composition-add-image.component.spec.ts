import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositionAddImageComponent } from './composition-add-image.component';

describe('CompositionAddImageComponent', () => {
  let component: CompositionAddImageComponent;
  let fixture: ComponentFixture<CompositionAddImageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositionAddImageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositionAddImageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
