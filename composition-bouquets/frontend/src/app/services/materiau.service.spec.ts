import { TestBed } from '@angular/core/testing';

import { MateriauService } from './materiau.service';

describe('MateriauService', () => {
  let service: MateriauService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MateriauService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
