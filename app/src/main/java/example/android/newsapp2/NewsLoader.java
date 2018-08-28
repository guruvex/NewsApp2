package example.android.newsapp2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

        Log.e("i am here","");

        HttpConnection getNews = new HttpConnection();
        String jsonString = "";
        try {
            jsonString = getNews.makeHttpRequest(createUrl("http://content.guardianapis.com/search?show-tags=contributor&q=debates&api-key=a8fc710c-26a0-4c93-85b9-7319adb3f0b9"));
        } catch (IOException e) {

            Log.d("hi","exception: "+e.getMessage());
            return null;
        }

        try {

            JSONObject rootJsonObj = new JSONObject(jsonString); // build root jason obj
            JSONObject jsonFirstLevel = rootJsonObj.getJSONObject("response");
            JSONArray jsonDataArray = jsonFirstLevel.getJSONArray("results");
            for (int i = 0; i < jsonDataArray.length(); i++) {
                // pull out each item in the jason array one at a time.
                JSONObject jsonItems = jsonDataArray.getJSONObject(i);
                // pull the data to load in the objects.
                String webTitle = jsonItems.optString("webTitle").toString();
                String sectionName = jsonItems.optString("sectionName").toString();
                String webUrl = jsonItems.optString("webUrl").toString();
                String date = jsonItems.optString("webPublicationDate");

                //JSONArray jsonThirdLevel = jsonDataArray.getJSONObject("tags");

                // put them in the news item objects.
                list.add(new NewsItem(webTitle, webUrl, sectionName, date));
            }
            } catch (Exception e) {
            System.out.print(e);
        }
        // this list under the line will go away.
        //list.add(new NewsItem("emp1", "Brahma","",""));
        //list.add(new NewsItem("emp2", "Vishnu","",""));
        //list.add(new NewsItem("emp3", "Mahesh","",""));
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