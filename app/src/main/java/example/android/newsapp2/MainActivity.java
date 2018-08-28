package example.android.newsapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.Adapter;
import android.widget.ListView;

//public class MainActivity extends AppCompatActivity {
//
//   @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<NewsItem>> {
    NewsAdapter newsAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsAdapter = new NewsAdapter(this, new ArrayList<NewsItem>());
        ListView employeeListView = (ListView) findViewById(R.id.employees);
        employeeListView.setAdapter(newsAdapter);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    }
    @Override
    public Loader<List<NewsItem>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(MainActivity.this);
    }
    @Override
    public void onLoadFinished(Loader<List<NewsItem>> loader, List<NewsItem> data) {
        newsAdapter.setEmployees(data);
    }
    @Override
    public void onLoaderReset(Loader<List<NewsItem>> loader) {
        newsAdapter.setEmployees(new ArrayList<NewsItem>());
    }
}