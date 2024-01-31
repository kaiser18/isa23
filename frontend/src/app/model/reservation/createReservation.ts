import { ReservationStorage } from "./reservationStorage";

export class CreateReservation {
    public appointmentId: Number;
    public customerId: Number;
    public companyId: Number;
    public reservationStorages: ReservationStorage[];

    constructor(appointmentId: Number, customerId: Number, companyId: Number, reservationStorages: ReservationStorage[]) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.companyId = companyId;
        this.reservationStorages = reservationStorages;
    }
}