import { Component, OnInit } from '@angular/core';
import { Company } from '../model/company/company';
import { CompanyService } from '../service/company/company.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { StorageService } from '../service/company/storage.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  public company: Company;
  public equipment: Storage[] = new Array();
  public profile: boolean = true;

  constructor(private service: CompanyService, private router: Router, private authService: AuthenticationService, private storageService: StorageService) {

  }

  ngOnInit(): void {
    localStorage.removeItem('companyId');
    localStorage.setItem('companyId', String(history.state.id));
    console.log(history.state.id);

    this.loadCompany();
    this.loadStorage();
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
      res => {
        this.equipment = res;
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
