import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from 'src/app/model/company/company';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<Company[]> {
    return this.http.get<Company[]>(`${environment.baseUrl}/${environment.company}/${environment.findAll}`);
  }

  findByName(name: String): Observable<Company[]> {
    return this.http.get<Company[]>(`${environment.baseUrl}/${environment.company}/getByName?name=${name}`);
  }

  findByAddress(address : String) : Observable<Company[]>{
    return this.http.get<Company[]>(`${environment.baseUrl}/${environment.company}/getByAddress?address=${address}`);
  }

  getCompanyById(id: Number): Observable<Company> {
    return this.http.get<Company>(`${environment.baseUrl}/${environment.company}/${environment.getById}?id=${id}`);
  }
}
