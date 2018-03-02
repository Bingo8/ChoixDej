package com.example.weibinwang.myapplication.Common.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by weibinwang on 2018/2/22.
 */

public class ContactValidator {
    private static ContactValidator instance = new ContactValidator();
    private static String PATTERN1 ="\\d{8}";
    private static String PATTERN2 = "\\d{2}[-\\.\\s]\\d{2}[-\\.\\s]d{2}[-\\.\\s]\\d{2}";

    public ContactValidator(){
        //do nothing
    }

    public boolean validateContact(final String contact){

        Pattern pattern1 = Pattern.compile(PATTERN1);
        Pattern pattern2 = Pattern.compile(PATTERN2);
        Matcher m1 = pattern1.matcher(contact);
        Matcher m2 = pattern2.matcher(contact);

        return (m1.matches()||m2.matches());
    }
}
