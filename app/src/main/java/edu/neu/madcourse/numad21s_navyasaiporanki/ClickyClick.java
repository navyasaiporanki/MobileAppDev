package edu.neu.madcourse.numad21s_navyasaiporanki;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClickyClick extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_click);

        mTextView = (TextView) findViewById(R.id.text);


    }
}