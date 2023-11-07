import { City } from "./city";
import { Country } from "./country";

export class Address {
    public street: string;
    public number: string;
    public city: City;
    public country: Country;

    constructor(street: string, number: string, city: City, country: Country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
    }
}