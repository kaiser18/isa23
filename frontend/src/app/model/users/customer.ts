import { Address } from "../address/address";
import { Role } from "./role";

export class Customer {
    public name: string;
    public surname: string;
    public address: Address;
    public phoneNumber: string;
    public email: string;
    public password: string;
    public profession: string;
    public professionInfo: string;
    public role: Role;
    public authorities: Number[];
    
    constructor(name: string, surname: string, address: Address, phoneNumber: string, email: string, password: string, profession: string, professionInfo: string, role: Role, authorities: Number[]) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.profession = profession;
        this.professionInfo = professionInfo;
        this.role = role;
        this.authorities = authorities;
    }
}