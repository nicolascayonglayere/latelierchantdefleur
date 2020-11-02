import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TigesDisplayComponent } from './tiges-display.component';

describe('TigesDisplayComponent', () => {
  let component: TigesDisplayComponent;
  let fixture: ComponentFixture<TigesDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TigesDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TigesDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
