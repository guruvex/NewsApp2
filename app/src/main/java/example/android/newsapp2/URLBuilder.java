package example.android.newsapp2;

/**
 * Created by james on 8/30/2018.
 */

public class URLBuilder {

    private String URLString;

    public String BuildURL (String GUARDIAN_REQUEST) {

        URLString = "http://content.guardianapis.com/search?show-tags=contributor&q="
                + GUARDIAN_REQUEST +
                "&api-key=a8fc710c-26a0-4c93-85b9-7319adb3f0b9";

        return URLString;
    }

}
