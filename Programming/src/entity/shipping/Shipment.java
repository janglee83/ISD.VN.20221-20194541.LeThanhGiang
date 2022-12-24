package entity.shipping;

import java.time.LocalDate;
import java.util.Date;

public class Shipment {
    
	private int shipType;

	private String deliveryInstruction;

	private String shipmentDetail;

	private String deliveryTime;

	//constructor operation
	public Shipment(int shipType, String deliveryInstruction, String shipmentDetail, String deliveryTime) {
		super();
		if (shipType == utils.Configs.PLACE_RUSH_ORDER) {
			this.deliveryInstruction = deliveryInstruction;
			this.shipmentDetail = shipmentDetail;
			this.deliveryTime = deliveryTime;
		}
	}

	public Shipment(int shipType) {
		super();
	}

	
	/** 
	 * @return String
	 */
	//getter setter method
	public String getDeliveryInstruction() {
		return this.deliveryInstruction;
	}

    public void validateDeliveryInfo(){
        // TODO: implement later on
    }

    
    /** 
     * @return Shipment
     */
    public Shipment createNewShipment(){
        // TODO: implement later on
        return new Shipment();
    }
}
