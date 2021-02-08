package edu.neu.madcourse.numad21s_navyasaiporanki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class ClickActivity extends AppCompatActivity {

    TextView dataToBeSaved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        dataToBeSaved = findViewById(R.id.displayText);
        getSavedDataIfAny();
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
        saveUserClickedData("last_record", dataToBeSaved.getText().toString());
    }

    private void saveUserClickedData(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    private void getSavedDataIfAny() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        dataToBeSaved.setText(sharedPreferences.getString("last_record", ""));
    }

}
