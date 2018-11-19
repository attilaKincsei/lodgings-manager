import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLodgingsComponent } from './user-lodgings.component';

describe('UserLodgingsComponent', () => {
  let component: UserLodgingsComponent;
  let fixture: ComponentFixture<UserLodgingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserLodgingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserLodgingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
