import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerReservationsComponent } from './customer-reservations.component';

describe('CustomerReservationsComponent', () => {
  let component: CustomerReservationsComponent;
  let fixture: ComponentFixture<CustomerReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerReservationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
