package edu.neu.madcourse.numad21s_navyasaiporanki;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemsList extends ArrayAdapter {
    private final Activity activity;
    private final List<String> namesOfTheLink;
    private final List<String> namesOfTheURLs;

    public ItemsList(Activity activity, List<String> urlName, List<String> urlList) {
        super(activity,R.layout.url_list , urlName);
        this.activity = activity;
        this.namesOfTheLink = urlName;
        this.namesOfTheURLs = urlList;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.url_list, null, true);
        TextView nameTextView =  rowView.findViewById(R.id.websiteName);
        TextView urlTextView =  rowView.findViewById(R.id.urlLink);
        nameTextView.setText(namesOfTheLink.get(pos));
        urlTextView.setText(namesOfTheURLs.get(pos));
        return rowView;
    }
}
