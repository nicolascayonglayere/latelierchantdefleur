import { TestBed } from '@angular/core/testing';

import { EvenementSelectedService } from './evenement-selected.service';

describe('EvenementSelectedService', () => {
  let service: EvenementSelectedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EvenementSelectedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
