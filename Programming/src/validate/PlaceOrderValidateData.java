package validate;

public class PlaceOrderValidateData {

    public boolean validatePhoneNumber(String phoneNumber) {
        // check the phoneNumber has 10 digits
        if (phoneNumber.length() != 10)
            return false;
        if (Character.compare(phoneNumber.charAt(0), '0') != 0)
            return false;
        // check the phoneNumber contains only number
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public boolean validateName(String name) {
        // Check name is not null
        if (name == null)
            return false;
        // Check if contain leter space only
        if (name.trim().length() == 0)
            return false;
        // Check if contain only leter and space
        if (name.matches("^[a-zA-Z ]*$") == false)
            return false;
        return true;
    }

    public boolean validateAddress(String address) {
        // Check address is not null
        if (address == null)
            return false;
        // Check if contain leter space only
        if (address.trim().length() == 0)
            return false;
        // Check if contain only leter and space
        if (address.matches("^[a-zA-Z ]*$") == false)
            return false;
        return true;
    }
}
