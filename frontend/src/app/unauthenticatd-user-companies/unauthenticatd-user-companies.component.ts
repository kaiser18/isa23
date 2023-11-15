import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { Router } from '@angular/router';
import { CompanyService } from '../service/company/company.service';
import { Company } from '../model/company/company';
import { MatSort, Sort } from '@angular/material/sort';

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

  getGradeValues(event) {
    //TODO grade filtering
  }

  routeToCompany(c) {
    //TODO make company profile page
    var string = c.id.toString();
    this.router.navigateByUrl('/company', {state: { id: string} });
  }

  getAllCompanies() {
    this.companyService.findAll().subscribe(data => {
      this.companies = data;
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

  
