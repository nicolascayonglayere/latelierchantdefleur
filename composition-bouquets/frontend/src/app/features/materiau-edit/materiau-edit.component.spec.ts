import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MateriauEditComponent } from './materiau-edit.component';

describe('MateriauEditComponent', () => {
  let component: MateriauEditComponent;
  let fixture: ComponentFixture<MateriauEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MateriauEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MateriauEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
