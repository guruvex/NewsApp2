package example.android.newsapp2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by james on 8/27/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public List<NewsItem> loadInBackground() {
        List<NewsItem> list = new ArrayList<NewsItem>();
        HttpConnection getNews = new HttpConnection();
        String jsonString = "";
        try {
            // retrieves a String value from the preferences.
            Context context = getContext();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String newURL = sharedPreferences.getString(context.getString(R.string.settings_search_key), context.getString(R.string.settings_search_default));
            //use the string in the custom build URL object
            URLBuilder buildURL = new URLBuilder();
            String newURLString = buildURL.BuildURL(newURL);
            // make new json request
            jsonString = getNews.makeHttpRequest(createUrl(newURLString));
            if (jsonString == "") {
                return null;
            }
        } catch (IOException e) {
            Log.d("hi", "exception: " + e.getMessage());
            return null;
        }
        try {
            JSONObject rootJsonObj = new JSONObject(jsonString); // build root jason obj
            JSONObject jsonFirstLevel = rootJsonObj.getJSONObject("response");
            JSONArray jsonDataArray = jsonFirstLevel.getJSONArray("results");
            for (int i = 0; i < jsonDataArray.length(); i++) {
                // pull out each item in the jason array one at a time.
                JSONObject jsonItems = jsonDataArray.getJSONObject(i);
                // dig down to author in json responce
                JSONArray thirdLevel = jsonItems.getJSONArray("tags");
                JSONObject jsonAuthor = thirdLevel.getJSONObject(0);
                // pull the data to load in the objects.
                String webTitle = jsonItems.optString("webTitle").toString();
                String sectionName = jsonItems.optString("sectionName").toString();
                String webUrl = jsonItems.optString("webUrl").toString();
                String date = jsonItems.optString("webPublicationDate");
                String author = jsonAuthor.optString("webTitle");
                // add author to string
                if (author == "") {
                    author = getContext().getString(R.string.no_auth);
                } else {
                    author = getContext().getString(R.string.author) + author;
                }
                // put them in the news item objects.
                list.add(new NewsItem(webTitle, webUrl, sectionName, date, author));
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return list;
    }

    private URL createUrl(String stringUrl) {
        // turns the passed string to a url the HttpConnection can handle
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            System.out.print(exception);
            return null;
        }
        return url;
    }
}