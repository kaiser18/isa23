import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { ReservationService } from '../service/reservation/reservation.service';
import { ReservationStorageService } from '../service/reservation/reservation-storage.service';
import { MatSort, Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { Reservation } from '../model/reservation/reservation';
import { ReservationStorage } from '../model/reservation/reservationStorage';

@Component({
  selector: 'app-customer-reservations',
  templateUrl: './customer-reservations.component.html',
  styleUrls: ['./customer-reservations.component.css']
})
export class CustomerReservationsComponent implements OnInit{
  constructor(private authService: AuthenticationService, private reservationService: ReservationService, private reservationStorageService: ReservationStorageService, private router: Router) {

  }

  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {}

  ngOnInit(): void {
    this.getReservationsForCustomer();
  }

  public reservations: Reservation[] = new Array();
  public equipment: ReservationStorage[] = new Array();

  getReservationsForCustomer() {
    this.reservationService.getCreatedForCustomer(Number(localStorage.getItem("userId"))).subscribe(
      data => {
        this.reservations = data;
        for (let r of this.reservations) {
          this.getEquipmentByReservation(r.id);
        }
      }
    )
  }

  getEquipmentByReservation(r) {
    this.reservationStorageService.getEquipmentByReservation(r).subscribe(
      data => {
        this.equipment = data;
      }
    )
  }

  sortData(sort: Sort) {
    const data = this.reservations.slice();

    if (!sort.active || sort.direction === '') {
      this.reservations = data;
      return;
    }

    this.reservations = data.sort((a,b) => {
      const isAsc = sort.direction === 'asc';
      switch(sort.active) {
        case 'reservationId': return compare(a.id, b.id, isAsc);
        case 'startTime': return compare(a.appointment.timePeriod.startTime, b.appointment.timePeriod.startTime, isAsc);
        case 'endTime': return compare(a.appointment.timePeriod.endTime, b.appointment.timePeriod.endTime, isAsc);
        case 'company': return compare(a.company.name, b.company.name, isAsc);
        case 'companyAdmin': return compare(a.appointment.companyAdmin.name, b.appointment.companyAdmin.name, isAsc);
        default: return 0;
      }
    })
  } 

  cancelReservation(r) {
    //TODO
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  checkLoggedInUser() {
    return this.authService.getUserValue();
  }
}

function compare(a: Number | String | Date, b: Number | String | Date, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
