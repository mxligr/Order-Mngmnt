package BLL.Vals;

import Model.Client;
import java.util.regex.Pattern;

/**
 *
 * @author Grama Malina Bianca
 * @since 13 May, 2021
 *
 *	Validates the phone number of a client
 */

public class PhoneNumberValidator implements Val<Client> {

    private static final String PHONE_NUMBER_PATTERN = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";

    @Override
    public void validate(Client t) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        if (!pattern.matcher(t.getPhoneNumber()).matches()) {
            throw new IllegalArgumentException("Phone number is not a valid number!");
        }
    }
}
