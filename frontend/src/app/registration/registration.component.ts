import { Component, OnInit } from '@angular/core';
import { Address } from '../model/address/address';
import { Country } from '../model/address/country';
import { City } from '../model/address/city';
import { Role } from '../model/users/role';
import { Customer } from '../model/users/customer';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from '../service/registration/registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  createForm: FormGroup;

  formData: FormData;

  name: string;
  surname: string;
  street: string;
  number: string;
  city: string;
  country: string;
  phoneNumber: string;
  email: string;
  password: string;
  confirmPassword: string;
  profession: string;
  professionInfo: string;

  address: Address;
  cityObj: City;
  countryObj: Country;
  public customer: Customer;

  constructor(private router: Router, private service: RegistrationService) {}

  ngOnInit(): void {
    this.createForm = new FormGroup({
      'name' : new FormControl(null,[Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'surname' : new FormControl(null, [Validators.required, Validators.pattern("^[a-zšđćčžA-ZŠĐŽČĆ ]*$")]),
      'street': new FormControl(null, [Validators.required]),
      'number': new FormControl(null, [Validators.required, Validators.pattern("^[0-9]*$")]),
      'city': new FormControl(null, [Validators.required]),
      'country': new FormControl(null, [Validators.required]),
      'phoneNumber': new FormControl(null, [Validators.required, Validators.pattern("^[0-9]*$")]),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, [Validators.required, Validators.minLength(10)]),
      'confirmPassword': new FormControl(null, [Validators.required, Validators.minLength(10)]),
      'profession': new FormControl(null, [Validators.required]),
      'professionInfo': new FormControl(null, [Validators.required]),
    });
  }

  register() {
    this.name = this.createForm.value.name;
    this.surname = this.createForm.value.surname;
    this.street = this.createForm.value.street;
    this.number = this.createForm.value.number;
    this.city = this.createForm.value.city;
    this.country = this.createForm.value.country;
    this.phoneNumber = this.createForm.value.phoneNumber;
    this.email = this.createForm.value.email;
    this.password = this.createForm.value.password;
    this.confirmPassword = this.createForm.value.confirmPassword;
    this.profession = this.createForm.value.profession;
    this.professionInfo = this.createForm.value.professionInfo;

    
    this.cityObj = new City(this.city);
    this.countryObj = new Country(this.country);
    this.address = new Address(this.street, this.number, this.cityObj, this.countryObj);

    var role : Role;
    role = Role.Customer;

    var authorities : Number[] = new Array();

    this.customer = new Customer(this.name, this.surname, this.address, this.phoneNumber, this.email, this.password, this.profession, this.professionInfo, role, authorities, 0);

    if (this.passwordCheck()) {
      this.service.registerCustomer(this.customer).subscribe(
        res => {
          this.createForm.reset();
          alert('Success');
          this.router.navigate(['/login']);
        },
        error => {
          alert('Email is already in use!');
        }
      )
    } else {
      alert('Passwords do not match');
    }
  }

  passwordCheck() {
    return this.password == this.confirmPassword;
  }
}
