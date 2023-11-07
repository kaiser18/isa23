import { Address } from "../address/address";

export class User {
    public id: number;
    public name: string;
    public surname: string;
    public address: Address;
    public phoneNumber: string;
    public email: string;
    public password: string;
    
    constructor(id: number, name: string, surname: string, address: Address, phoneNumber: string, email: string, password: string) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
}