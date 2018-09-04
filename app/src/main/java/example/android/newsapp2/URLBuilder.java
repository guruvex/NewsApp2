package example.android.newsapp2;

/**
 * Created by james on 8/30/2018.
 * custom url string builder.
 * very simple system.
 */

public class URLBuilder {

    public static final String HttpRequest = "http://content.guardianapis.com/search?show-tags=contributor&q=";
    public static final String GUARDIAN_KEY = "&api-key=a8fc710c-26a0-4c93-85b9-7319adb3f0b9";

    public String BuildURL(String GUARDIAN_REQUEST) {
        // add web address and key to input string and return full web address.
        return HttpRequest + GUARDIAN_REQUEST + GUARDIAN_KEY;
    }
}

