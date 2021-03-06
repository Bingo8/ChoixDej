package com.example.weibinwang.myapplication.Common.Validator;

/**
 * Created by weibinwang on 2018/2/22.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static Pattern pattern ;
    private static EmailValidator intsnace = new EmailValidator();

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    private EmailValidator() {
        //do nothing.
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean validateEmail(final String hex) {

        Matcher matcher = pattern.matcher(hex);

        return matcher.matches();
    }
}
