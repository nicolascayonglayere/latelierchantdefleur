import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositionAddDialogComponent } from './composition-add-dialog.component';

describe('CompositionAddDialogComponent', () => {
  let component: CompositionAddDialogComponent;
  let fixture: ComponentFixture<CompositionAddDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositionAddDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositionAddDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
