import { Component, OnInit } from '@angular/core';
import { Company } from '../model/company/company';
import { CompanyService } from '../service/company/company.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication/authentication.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  public company: Company;
  public equipment: String[] = new Array();
  public profile: boolean = true;

  constructor(private service: CompanyService, private router: Router, private authService: AuthenticationService) {

  }

  ngOnInit(): void {
    localStorage.removeItem('companyId');
    localStorage.setItem('companyId', String(history.state.id));
    console.log(history.state.id);

    this.loadCompany();
  }

  loadCompany() {
    this.service.getCompanyById(Number(localStorage.getItem('companyId'))).subscribe(
      data => {
        this.company = data;
      }
    )
  }

  checkLoggedInUser() {
    return this.authService.getUserValue();
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
