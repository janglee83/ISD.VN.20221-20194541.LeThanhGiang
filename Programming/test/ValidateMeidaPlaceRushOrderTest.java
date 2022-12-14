import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

public class ValidateMeidaPlaceRushOrderTest {
    
    private PlaceOrderController placeOrderController;

    @BeforeEach
    void setup() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
        "book 1",
        "book 2",
        "book 3",
    })

    @Test
    public void test(String titleProduct) {
        boolean isValid = placeOrderController.validateMediaPlaceRushorder();
        assertEquals(true, isValid);
    }
}
