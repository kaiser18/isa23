import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from 'src/app/model/company/appointment';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private http: HttpClient) { }

  getAvailableForCompany(companyId: Number) : Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${environment.baseUrl}/${environment.appointment}/${environment.getAvailableByCompany}?companyId=${companyId}`);
  }
}
