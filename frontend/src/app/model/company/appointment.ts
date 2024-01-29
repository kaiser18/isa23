import { CompanyAdmin } from "../users/companyAdmin";
import { AppointmentStatus } from "./appointmentStatus";
import { Company } from "./company";
import { Period } from "./period";

export class Appointment {
    public id: Number;
    public timePeriod: Period;
    public companyAdmin: CompanyAdmin;
    public company: Company;
    public status: AppointmentStatus;

    constructor(id: Number, appointmentPeriod: Period, companyAdmin: CompanyAdmin, company: Company,
        status: AppointmentStatus) {
            this.id = id;
            this.timePeriod = appointmentPeriod;
            this.companyAdmin = companyAdmin;
            this.company = company;
            this.status = status;
        }
}