package edu.neu.madcourse.numad21s_navyasaiporanki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickActivity extends AppCompatActivity {

    TextView read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        read = findViewById(R.id.displayText);
        loadSaveRecord();
    }

    public void btnPressed(View view){
        TextView displayText = findViewById(R.id.displayText);
        TextView text = findViewById(view.getId());
        String pressed = "Pressed:";
        displayText.setText(pressed+text.getText().toString());
    }

    @Override
    public void onBackPressed() {
        saveData();
        Intent intent = new Intent(ClickActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void saveData() {
        //saveLast("last_record", read.getText().toString());
        saveLastData("last_record", read.getText().toString());
    }

    private void saveLastData(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    private void loadSaveRecord() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        read.setText(sharedPreferences.getString("last_record", ""));
    }

}
