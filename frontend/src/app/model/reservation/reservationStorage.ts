export class ReservationStorage {
    public equipmentId: Number;
    public equipmentName: String;
    public quantity: Number;

    constructor(equipmentId: Number, equipmentName: String, quantity: Number) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.quantity = quantity;
    }
}