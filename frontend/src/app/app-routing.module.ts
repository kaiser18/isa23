import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { UnauthenticatdUserCompaniesComponent } from './unauthenticatd-user-companies/unauthenticatd-user-companies.component';
import { CompanyComponent } from './company/company.component';

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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
