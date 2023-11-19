import { Address } from "../address/address";

export class Company {
    public id: Number;
    public name: String;
    public address: Address;
    public description: String;
    public averageGrade: number;

    constructor(id: Number, name: String, address: Address, description: String, averageGrade: number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageGrade = averageGrade;
    }
}