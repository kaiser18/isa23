export class Storage {
    public equipmentId: Number;
    public equipmentName: String;
    public quantity: Number;
    public companyId: Number;

    constructor(equipmentId: Number, equipmentName: String, quantity: Number, companyId: Number) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.quantity = quantity;
        this.companyId = companyId;
    }
}