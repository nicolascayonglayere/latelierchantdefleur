import { TestBed } from '@angular/core/testing';

import { ImageCompositionService } from './image-composition.service';

describe('ImageCompositionService', () => {
  let service: ImageCompositionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImageCompositionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
