import { TestBed } from '@angular/core/testing';

import { LodgingsService } from './lodgings.service';

describe('LodgingsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LodgingsService = TestBed.get(LodgingsService);
    expect(service).toBeTruthy();
  });
});
