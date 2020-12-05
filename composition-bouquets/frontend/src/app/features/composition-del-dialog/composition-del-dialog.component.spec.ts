import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositionDelDialogComponent } from './composition-del-dialog.component';

describe('CompositionDelDialogComponent', () => {
  let component: CompositionDelDialogComponent;
  let fixture: ComponentFixture<CompositionDelDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositionDelDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositionDelDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
