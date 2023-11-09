import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Role } from '../model/users/role';
import { Authentication } from '../model/users/authentication';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { RegistrationService } from '../service/registration/registration.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  createForm: FormGroup;
  invalidLogin: boolean;
  username: string;
  password: string;
  credentials: Authentication;

  constructor(private service: AuthenticationService, private router: Router, private activatedRoute: ActivatedRoute, private registrationService: RegistrationService) {
    this.activatedRoute.queryParams.subscribe(params => {
      let token = params['token'];

      if (token !== undefined) {
        this.registrationService.activateCustomer(token).subscribe(
          res => {
            alert("Your account is now activated");
          }, 
          error => {
            alert("There was an error with your account activation");
          }
        )
      }
    })
  }

  ngOnInit(): void {
    let user = this.service.getUserValue;
    this.createForm = new FormGroup({
      'username': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, Validators.required)
    });
  }

  login() {
    this.username = this.createForm.value.username;
    this.password = this.createForm.value.password;

    this.credentials = new Authentication(this.username, this.password);

    this.service.login(this.credentials).subscribe(
      res => {
        localStorage.setItem('userId', String(res.id));
        localStorage.setItem('userRole', String(res.role));

        if(res.role == Role.Customer) {
          this.router.navigate(['/customer/home']);
        } else if (res.role == Role.SysAdmin) {
          this.router.navigate(['/']);
        }
        
      },
      error => {
        console.log(error);
        alert("Invalid username or password");
      }
    )
  }
}
