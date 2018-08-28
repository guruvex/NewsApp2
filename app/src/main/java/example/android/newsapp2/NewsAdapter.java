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
        TextView newsTitle = (TextView) view.findViewById(R.id.news_story_title);
        newsTitle.setText(news.getTitle());
        TextView newsCatagory = (TextView) view.findViewById(R.id.news_story_catagory);
        newsCatagory.setText(news.getSectionName());
        TextView newsDate = (TextView) view.findViewById(R.id.news_date);
        newsDate.setText(news.getDate());
        TextView newsAuthor = (TextView) view.findViewById(R.id.news_author);
        newsAuthor.setText(news.getAuthor());
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

    public void setNews(List<NewsItem> data) {
        newsItems.addAll(data);
        notifyDataSetChanged();
    }
}