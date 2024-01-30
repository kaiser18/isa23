import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from 'src/app/model/reservation/reservation';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  getCreatedForCustomer(customerId: Number) : Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${environment.baseUrl}/${environment.reservation}/${environment.getCreatedForCustomer}?customerId=${customerId}`);
  }
}
