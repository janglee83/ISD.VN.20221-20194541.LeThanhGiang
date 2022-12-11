import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
    ValidatePhoneNumberTest.class, 
    ValidateNameTest.class, 
    ValidateAddressTest.class,
    ValidatePlaceRushAddressTest.class,
    ValidateMeidaPlaceRushOrderTest.class })
public class AllTests {

}
