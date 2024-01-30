export class ReservationStorage {
    public equipmentName: String;
    public quantity: Number;
    public reservationId: Number;

    constructor(equipmentName: String, quantity: Number, reservationId: Number) {
        this.equipmentName = equipmentName;
        this.quantity = quantity;
        this.reservationId = reservationId;
    }
}