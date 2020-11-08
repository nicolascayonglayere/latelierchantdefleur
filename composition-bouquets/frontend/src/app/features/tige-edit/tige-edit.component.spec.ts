import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TigeEditComponent } from './tige-edit.component';

describe('TigeEditComponent', () => {
  let component: TigeEditComponent;
  let fixture: ComponentFixture<TigeEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TigeEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TigeEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
