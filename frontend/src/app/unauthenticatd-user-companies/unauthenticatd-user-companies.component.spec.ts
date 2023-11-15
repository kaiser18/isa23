import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthenticatdUserCompaniesComponent } from './unauthenticatd-user-companies.component';

describe('UnauthenticatdUserCompaniesComponent', () => {
  let component: UnauthenticatdUserCompaniesComponent;
  let fixture: ComponentFixture<UnauthenticatdUserCompaniesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthenticatdUserCompaniesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UnauthenticatdUserCompaniesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
