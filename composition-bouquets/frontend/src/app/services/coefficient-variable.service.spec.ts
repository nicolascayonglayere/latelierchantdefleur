import { TestBed } from '@angular/core/testing';

import { CoefficientVariableService } from './coefficient-variable.service';

describe('CoefficientVariableService', () => {
  let service: CoefficientVariableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CoefficientVariableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
