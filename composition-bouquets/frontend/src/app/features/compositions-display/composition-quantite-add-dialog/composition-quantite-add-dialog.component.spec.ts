import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompositionQuantiteAddDialogComponent } from './composition-quantite-add-dialog.component';

describe('CompositionQuantiteAddDialogComponent', () => {
  let component: CompositionQuantiteAddDialogComponent;
  let fixture: ComponentFixture<CompositionQuantiteAddDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompositionQuantiteAddDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompositionQuantiteAddDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
