package example.android.newsapp2;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by james on 8/27/2018.
 */

public class NewsAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<NewsItem> newsItems = new ArrayList<NewsItem>();

    public NewsAdapter(Context context, List<NewsItem> news) {
        this.newsItems = news;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        NewsItem news = (NewsItem) getItem(position);
        if (view == null) {
            view = inflater.inflate(R.layout.news_layout, null);
        }
        TextView empid = (TextView) view.findViewById(R.id.empid);
        empid.setText(news.getTitle());
        TextView empname = (TextView) view.findViewById(R.id.empname);
        empname.setText(news.getWebURL());
        return view;
    }

    @Override
    public Object getItem(int position) {
        return newsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return newsItems.size();
    }

    public void setEmployees(List<NewsItem> data) {
        newsItems.addAll(data);
        notifyDataSetChanged();
    }
}