import { Component, OnInit } from '@angular/core';
import { Company } from '../model/company/company';
import { CompanyService } from '../service/company/company.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { StorageService } from '../service/company/storage.service';
import { Role } from '../model/users/role';
import { Appointment } from '../model/company/appointment';
import { AppointmentService } from '../service/company/appointment.service';
import { Storage } from '../model/company/storage';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ReservationStorage } from '../model/reservation/reservationStorage';
import { CreateReservation } from '../model/reservation/createReservation';
import { ReservationService } from '../service/reservation/reservation.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  public company: Company;
  public equipment: Storage[] = new Array();
  public availableAppointments: Appointment[] = new Array();
  public profile: boolean = true;
  public makeReservation: boolean = false;

  public addEquipmentForm: FormGroup;
  public equipmentR: Storage[] = new Array();
  public equipmentToOrder: Storage;
  public quantityToOrder: Number;

  public reservedEquipment: ReservationStorage[] = new Array();
  public selectedAppointment: Appointment;

  public reservationForm: FormGroup;
  public appointments: Appointment[] = new Array();

  public currentUserId: Number;
  public companyId: Number;

  constructor(private service: CompanyService, private router: Router, private authService: AuthenticationService, private storageService: StorageService, private appointmentService: AppointmentService, private reservationService: ReservationService) {

  }

  ngOnInit(): void {
    localStorage.removeItem('companyId');
    localStorage.setItem('companyId', String(history.state.id));
    console.log(history.state.id);

    this.loadCompany();
    this.loadStorage();
    this.loadAvailableAppointments();

    this.addEquipmentForm = new FormGroup({
      'equipmentToOrder': new FormControl(null, Validators.required),
      'quantityToOrder': new FormControl(null, Validators.required)
    });

    this.reservationForm = new FormGroup({
      'selectedAppointment': new FormControl(null, Validators.required)
    });
  }

  loadCompany() {
    this.service.getCompanyById(Number(localStorage.getItem('companyId'))).subscribe(
      data => {
        this.company = data;
      }
    )
  }

  loadStorage() {
    this.storageService.getAvailableByCompany(Number(localStorage.getItem('companyId'))).subscribe(
      data => {
        this.equipment = data;
        this.equipmentR = data;
        }
    )
  }

  loadAvailableAppointments() {
    this.appointmentService.getAvailableForCompany(Number(localStorage.getItem('companyId'))).subscribe(
      data => {
        this.availableAppointments = data;
        this.appointments = data;
      }
    )
  }

  showMakeReservation() {
    this.profile = false;
    this.makeReservation = true;
  }

  cancelReservation() {
    this.profile = true;
    this.makeReservation = false;
  }

  addToReservation() {
    let itemExists = this.reservedEquipment.filter(equip => equip.equipmentId == this.addEquipmentForm.value.equipmentToOrder.equipmentId).length == 0;
    if (itemExists) {
      let equip = this.equipmentR.filter(e => e.equipmentId == this.addEquipmentForm.value.equipmentToOrder.equipmentId);
      let newRStorage = new ReservationStorage(equip[0].equipmentId, equip[0].equipmentName, this.addEquipmentForm.value.quantityToOrder);

      this.reservedEquipment.push(newRStorage);
    }
    else {
      for (var i in this.reservedEquipment) {
        if (this.reservedEquipment[i].equipmentId == this.addEquipmentForm.value.equipmentToOrder.equipmentId) {
          this.reservedEquipment[i].quantity += this.addEquipmentForm.value.quantityToOrder;
          break;
        }
      }
    }
   }

  cancelItem(itemId: Number) {
    this.reservedEquipment = this.reservedEquipment.filter(i => i.equipmentId !== itemId);
  }

  submitReservation() {
    this.currentUserId = Number(localStorage.getItem('userId'));
    this.companyId = Number(localStorage.getItem('companyId'));

    let createRes = new CreateReservation(this.reservationForm.value.selectedAppointment.id, this.currentUserId, this.companyId, this.reservedEquipment);

    console.log(createRes);

    this.reservationService.createReservation(createRes).subscribe(
      result => {
        alert("You have successfully made a reservation");
        console.log(result);
        this.router.navigate(['/customer/reservations']);
      },
      err => {
        alert("There was an error while making a reservation");
      }
    )
  }
 
  isCustomer() {
    return this.authService.getUserValue() && this.authService.getUserValue().role === Role.Customer
  }

  checkLoggedInUser() {
    return this.authService.getUserValue();
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
