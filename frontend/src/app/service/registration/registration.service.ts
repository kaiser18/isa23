import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Customer } from 'src/app/model/users/customer';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }

  registerCustomer(data: Customer) {
    return this.http.post(`${environment.baseUrl}/${environment.customer}/${environment.registration}`, data, {responseType: 'text'});
  }
}
