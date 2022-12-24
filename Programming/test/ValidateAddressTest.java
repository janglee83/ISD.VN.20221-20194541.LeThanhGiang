import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

public class ValidateAddressTest {
    
    private PlaceOrderController placeOrderController;

    
    /** 
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
        "Trinh xa chau khe tu son Bac Ninh, true",
        "123ADSASD123213Trinh xa chau khe tu son Bac Ninh, false",
        "   Trinh xa chau khe tu son Bac Ninh, true",
        "Trinh xa chau khe tu sASDSADASDSADSADon Bac Ninh, true"
    })

    public void test(String address, boolean expected) {
        boolean isValid = placeOrderController.validateAddress(address);
        assertEquals(expected, isValid);
    }
}
