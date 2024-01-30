import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReservationStorage } from 'src/app/model/reservation/reservationStorage';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationStorageService {

  constructor(private http: HttpClient) { }

  getEquipmentByReservation(id: Number) : Observable<ReservationStorage[]> {
    return this.http.get<ReservationStorage[]>(`${environment.baseUrl}/${environment.reservationStorage}/${environment.getEquipmentByReservation}?r=${id}`);
  }
}
