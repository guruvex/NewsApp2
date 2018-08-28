package example.android.newsapp2;

/**
 * Created by james on 7/31/2018.
 * object to hold news data
 */

public class NewsItem {

    private String mTitle;
    private String mWebURL;
    private String mSectionName;
    private String mDate;
    private String mAuthor;

    public NewsItem (String title, String webURL, String sectionName, String date, String author){
        mTitle = title;
        mWebURL = webURL;
        mSectionName = sectionName;
        mDate = date;
        mAuthor = author;
    }
    public String getTitle(){return mTitle;}
    public String getWebURL(){return mWebURL;}
    public String getSectionName(){return mSectionName;}
    public String getDate() {return mDate;}
    public String getAuthor() {return mAuthor;}
}

