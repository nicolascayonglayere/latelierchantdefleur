import { TestBed } from '@angular/core/testing';

import { TigeService } from './tige.service';

describe('TigeService', () => {
  let service: TigeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TigeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
