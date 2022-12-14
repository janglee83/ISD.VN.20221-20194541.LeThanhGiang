import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import controller.PlaceOrderController;

class ValidateNameTest {
    
    private PlaceOrderController placeOrderController;

    @BeforeEach
    public void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
        " , false",
        ", false",
        "LeThanh, true",
        "Le Thanh Giang, true",
        "Le Thanh Giang4, false",
        "213123!@#!@$#, false",
        "4Le Thanh giang, false"
    })

    public void test(String name, boolean expected) {
        boolean isValid = placeOrderController.validateName(name);
        assertEquals(expected, isValid);
    }

}
