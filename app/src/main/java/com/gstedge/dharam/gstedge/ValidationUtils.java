package com.gstedge.dharam.gstedge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dharam on 7/18/2018.
 */

public class ValidationUtils {

    private Pattern pattern;
    private Matcher matcher;

    private static final String USERNAME_PATTERN =
            "((?=.*\\d)(?=.*[a-zA-Z]).{8,20})";

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-zA-Z]).{8,15})";

    public ValidationUtils(){
        String string_pattern=null;
        if (string_pattern.equals(PASSWORD_PATTERN))
        pattern = Pattern.compile(PASSWORD_PATTERN);
        else
            pattern = Pattern.compile(USERNAME_PATTERN);

    }

    /**
     * Validate password with regular expression
     * @param patternString password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate( String patternString){

        if (patternString.equals(USERNAME_PATTERN)) {
            matcher = pattern.matcher(patternString);
        }
        else {
            patternString = PASSWORD_PATTERN;
            matcher = pattern.matcher(patternString);
        }
        return matcher.matches();

    }
}
