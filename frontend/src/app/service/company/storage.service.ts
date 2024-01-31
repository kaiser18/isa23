import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Storage } from 'src/app/model/company/storage';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor(private http: HttpClient) { }

  getAvailableByCompany(id: Number) : Observable<Storage[]> {
    return this.http.get<Storage[]>(`${environment.baseUrl}/${environment.storage}/${environment.getAvailableByCompany}?c=${id}`);
  }
}
