import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateReservation } from 'src/app/model/reservation/createReservation';
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

  cancel(reservationId: Number) {
    return this.http.post(`${environment.baseUrl}/${environment.reservation}/${environment.cancel}`, reservationId, {responseType : 'text'});
  }

  createReservation(res: CreateReservation) : Observable<Reservation> {
    return this.http.post<Reservation>(`${environment.baseUrl}/${environment.reservation}/${environment.createRes}`, res);
  }
}
