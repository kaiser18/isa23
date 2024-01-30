import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { Router } from '@angular/router';
import { CompanyService } from '../service/company/company.service';
import { Company } from '../model/company/company';
import { MatSort, Sort } from '@angular/material/sort';
import { Role } from '../model/users/role';

@Component({
  selector: 'app-unauthenticatd-user-companies',
  templateUrl: './unauthenticatd-user-companies.component.html',
  styleUrls: ['./unauthenticatd-user-companies.component.css']
})
export class UnauthenticatdUserCompaniesComponent implements AfterViewInit {
  
  @ViewChild(MatSort) sort: MatSort;
  ngAfterViewInit() {
  }
  
  constructor(private auth: AuthenticationService, private router: Router, private companyService: CompanyService) {
    
  }

  public companies: Company[] = new Array();
  public companiesCopy: Company[] = new Array();

  ngOnInit(): void {
    this.getAllCompanies();
  }

  checkLoggedInUser() {
    return this.auth.getUserValue();
  }

  logout(){
    this.auth.logout();
    this.router.navigate(['/login']);
  }

  isCustomer() {
    return this.auth.getUserValue() && this.auth.getUserValue().role === Role.Customer
  }

  getGradeValues(event) {
    if (event.value == 1){
      this.companies = this.companiesCopy;
      this.companies = this.companies.filter(c => c.averageGrade >= 0 &&
         c.averageGrade <=1);
    }else if(event.value == 2){
      this.companies = this.companiesCopy;
      this.companies = this.companies.filter(c => c.averageGrade >= 1 &&
         c.averageGrade <=2);
    }else if(event.value == 3){
      this.companies = this.companiesCopy;
      this.companies = this.companies.filter(c => c.averageGrade >= 2 &&
         c.averageGrade <=3);
    }
    else if(event.value == 4){
      this.companies = this.companiesCopy;
      this.companies = this.companies.filter(c => c.averageGrade >= 3 &&
         c.averageGrade <=4);
    } else if(event.value == 5){
      this.companies = this.companiesCopy;
      this.companies = this.companies.filter(c => c.averageGrade >= 4 &&
         c.averageGrade <=5);
    }else {
      this.companies = this.companiesCopy;
    }
  }

  routeToCompany(c) {
    var string = c.id.toString();
    this.router.navigateByUrl('/company', {state: { id: string} });
  }

  getAllCompanies() {
    this.companyService.findAll().subscribe(data => {
      this.companies = data;
      this.companiesCopy = data;
    });
  }

  findByName(inputName : String){
    this.companyService.findByName(inputName).subscribe(data =>
      {
        this.companies = data;
      })
  }

  findByAddress(inputAdr : String){
    this.companyService.findByAddress(inputAdr).subscribe(data =>
      {
        this.companies = data;
      })
  }

  sortData(sort: Sort){
    const data = this.companies.slice();
    if (!sort.active || sort.direction === '') {
      this.companies = data;
      return;
    }

    this.companies = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'grade': return compare(a.averageGrade, b.averageGrade, isAsc);
        default: return 0;
      }
     });
  }


}

function compare(a: Number | string, b: Number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

  
