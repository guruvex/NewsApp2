package example.android.newsapp2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * news app built by james heath.
 * thanks to Arvind Rai, July 09, 2015 AsyncTaskLoader Example with ListView
 * to get my project on track.
 * https://www.concretepage.com/android/android-asynctaskloader-example-with-listview-and-baseadapter
 */

public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<NewsItem>> {
    NewsAdapter newsAdapter;
    TextView errorText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        errorText = findViewById(R.id.error_text);
        errorText.setVisibility(View.GONE);

        if(isConnected()) {
        newsAdapter = new NewsAdapter(this, new ArrayList<NewsItem>());
        ListView newsListView = (ListView) findViewById(R.id.employees);
        newsListView.setAdapter(newsAdapter);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
        } else{
            Toast.makeText(this,  "there is no interent connection", Toast.LENGTH_SHORT).show();
            errorText.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public Loader<List<NewsItem>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(MainActivity.this);
    }
    @Override
    public void onLoadFinished(Loader<List<NewsItem>> loader, List<NewsItem> data) {
        if (data != null) {
            newsAdapter.setNews(data);
        }else{
            errorText.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<NewsItem>> loader) {
        newsAdapter.setNews(new ArrayList<NewsItem>());
    }
    /**
     * check for network
     * */
    private boolean isConnected() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}