import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LodgingAddComponent } from './lodging-add.component';

describe('LodgingAddComponent', () => {
  let component: LodgingAddComponent;
  let fixture: ComponentFixture<LodgingAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LodgingAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LodgingAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
