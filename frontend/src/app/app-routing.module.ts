import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { UnauthenticatdUserCompaniesComponent } from './unauthenticatd-user-companies/unauthenticatd-user-companies.component';
import { CompanyComponent } from './company/company.component';
import { CustomerReservationsComponent } from './customer-reservations/customer-reservations.component';
import { AuthGuard } from './helpers/auth.guard';
import { Role } from './model/users/role';

const routes: Routes = [
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '',
    component: UnauthenticatdUserCompaniesComponent
  },
  {
    path: 'company',
    component: CompanyComponent
  },
  {
    path: 'customer/reservations',
    component: CustomerReservationsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.Customer]}
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
