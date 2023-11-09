import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { AuthenticatedUser } from 'src/app/model/users/authenticatedUser';
import { Authentication } from 'src/app/model/users/authentication';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private userSubject: BehaviorSubject<AuthenticatedUser>;
  public user: Observable<AuthenticatedUser>;

  constructor(private http: HttpClient, private router: Router) {
    this.userSubject = new BehaviorSubject<AuthenticatedUser>(JSON.parse(localStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  public getUserValue() : AuthenticatedUser {
    return this.userSubject.value;
  }

  login(credentials: Authentication) {
    return this.http.post<AuthenticatedUser>(`${environment.baseUrl}/${environment.authenticate}/${environment.login}`, credentials)
    .pipe(map(response => {
      localStorage.setItem('user', JSON.stringify(response));
      console.log(response.token);
      this.userSubject.next(response);
      return response;
    }));
  }

  logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('userId');
    localStorage.removeItem("userRole");
    this.userSubject.next(null);
    this.router.navigate(['/login']);
  }
}
