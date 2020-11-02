import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BandeauBouquetComponent } from './bandeau-bouquet.component';

describe('BandeauBouquetComponent', () => {
  let component: BandeauBouquetComponent;
  let fixture: ComponentFixture<BandeauBouquetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BandeauBouquetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BandeauBouquetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
