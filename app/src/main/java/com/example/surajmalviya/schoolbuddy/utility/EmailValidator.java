package com.example.surajmalviya.schoolbuddy.utility;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public static boolean validate(final String hex) {

         matcher = pattern.matcher(hex);
         boolean match=matcher.matches();
        // Log.d("Match",match+"");
         return match;
    }
}