import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticatedUser } from './model/users/authenticateduser';
import { AuthenticationService } from './service/authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MedicalEquipment';
  private router: Router;
  user: AuthenticatedUser;

  constructor(private authService: AuthenticationService) {
    this.authService.user.subscribe(x => this.user = x);
  }

  onInit() {
    this.router.navigate(['']);
  }

  logout(){
    this.authService.logout();  
  }
}
