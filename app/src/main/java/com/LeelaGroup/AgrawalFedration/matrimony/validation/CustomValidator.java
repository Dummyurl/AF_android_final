package com.LeelaGroup.AgrawalFedration.matrimony.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by USer on 22-05-2017.
 */

public class CustomValidator {
    // validating email id
    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    public boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }
    public boolean isValidName(String name) {
        if (name !=null && name.length() != 0 && name.matches("[a-zA-Z ]+")) {

            return true;
        }
        return false;
    }
    public boolean isValidNumber(String number) {
        if (number !=null && number.length() != 0 && number.matches("[0-9]+")) {

            return true;
        }
        return false;
    }
    public boolean isValidWeight(String number) {
        if (number !=null && number.length() != 0 ) {

            return true;
        }
        return false;
    }
    public boolean isValidPincode(String number) {
        if (number !=null && number.length() == 6 && number.matches("[0-9]+")) {

            return true;
        }
        return false;
    }
    public boolean isValidSiblings(String number) {
        if (number !=null && number.length() == 1 && number.matches("[0-3]+")) {

            return true;
        }
        return false;
    }
   /* public boolean isValidWeight(String number) {
        if (number !=null && number.length() != 0 && number.matches("[0-9]+")) {

            return true;
        }
        return false;
    }
*/
    public boolean isValidMobile(String mobile) {
        if (mobile !=null && mobile.length() >= 10 && mobile.matches("[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
            return true;
        }
        return false;
    }
    public boolean isEmptyField(String field) {
        if (field.length() != 0 && field != null) {
            return true;
        }
        return false;
    }
    public boolean isRadioGroupSelected(String radioGroup){
        if (radioGroup!=null){
            return true;
        }
        return false;
    }




}
