package controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import entity.shipping.Shipment;

/**
 * This class controls the flow of place rush order usecase in our AIMS project
 * 
 * @author giangleee
 */
public class PlaceRushOrderController extends BaseController {
	/**
	 * Just for logging purpose
	 */
	private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

	
	/** 
	 * @param deliveryData
	 * @param typeDelivery
	 */
	public static void validatePlaceRushOrderData(HashMap<String, String> deliveryData, int typeDelivery) {
		if (typeDelivery == utils.Configs.PLACE_RUSH_ORDER) {
			Shipment shipment = new Shipment(typeDelivery, deliveryData.get("deliveryInstruction"), deliveryData.get("shipmentDetail"), deliveryData.get("deliveryTime"));
		}
	}
}
