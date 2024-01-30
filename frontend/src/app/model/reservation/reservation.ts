import { Appointment } from "../company/appointment";
import { Company } from "../company/company";
import { Customer } from "../users/customer";
import { ReservationStatus } from "./reservationStatus";

export class Reservation {
    public id: Number;
    public appointment: Appointment;
    public customer: Customer;
    public company: Company;
    public status: ReservationStatus;

    constructor(id: Number, appointment: Appointment, customer: Customer,
        company: Company, status: ReservationStatus) {
            this.id = id;
            this.appointment = appointment;
            this.customer = customer;
            this.company = company;
            this.status = status;
        }
}