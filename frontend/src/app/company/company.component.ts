import { Component, OnInit } from '@angular/core';
import { Company } from '../model/company/company';
import { CompanyService } from '../service/company/company.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { StorageService } from '../service/company/storage.service';
import { Role } from '../model/users/role';
import { Appointment } from '../model/company/appointment';
import { AppointmentService } from '../service/company/appointment.service';

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

  public quantityToOrder: Number;

  constructor(private service: CompanyService, private router: Router, private authService: AuthenticationService, private storageService: StorageService, private appointmentService: AppointmentService) {

  }

  ngOnInit(): void {
    localStorage.removeItem('companyId');
    localStorage.setItem('companyId', String(history.state.id));
    console.log(history.state.id);

    this.loadCompany();
    this.loadStorage();
    this.loadAvailableAppointments();
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
        }
    )
  }

  loadAvailableAppointments() {
    this.appointmentService.getAvailableForCompany(Number(localStorage.getItem('companyId'))).subscribe(
      data => {
        this.availableAppointments = data;
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

  addToOrder(e, q) {
    //TODO
  }

  chooseAppointment(a) {
    //TODO
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
