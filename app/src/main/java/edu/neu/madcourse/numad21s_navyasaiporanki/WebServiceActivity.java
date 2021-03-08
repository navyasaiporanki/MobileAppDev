package edu.neu.madcourse.numad21s_navyasaiporanki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WebServiceActivity extends AppCompatActivity {


    String cocktailReceived = "";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

    }

    public void fetchRecipes(final View view) throws InterruptedException {


        if (internetPermissions()) {
            //progressBar.setVisibility(View.VISIBLE);
            //Thread.sleep(1000);
            Thread thread = new Thread(new RecipesRunnable(this));
            thread.start();
            thread.join();
            Thread.sleep(1000);
            TextView textView = findViewById(R.id.textView3);
            textView.setText(cocktailReceived);
            //hideProgress();

        }
        else {
            addInternetPermissions();
        }
    }

    private void hideProgress() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        thread.start();
        thread.sleep(1000);
        thread.join();


    }

    private boolean internetPermissions(){
        return ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    private void addInternetPermissions() {
        requestPermissions(new String[] {
                Manifest.permission.INTERNET
        }, 1);
    }

    private String getRequestCall(String url) throws IOException {

        URLConnection urlConnection = null;
        String charset = StandardCharsets.UTF_8.name();
        urlConnection = new URL(url).openConnection();

        urlConnection.setRequestProperty("Accept-Charset", charset);

        InputStream inputStream = urlConnection.getInputStream();
        return streamToString(inputStream);
    }

    private String streamToString(InputStream inputStream) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, len);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }

    class RecipesRunnable implements  Runnable {

        private final WebServiceActivity webServiceActivity;

        RecipesRunnable(WebServiceActivity webServiceActivity) {
            progressBar.setVisibility(View.VISIBLE);
            this.webServiceActivity = webServiceActivity;
        }
        @Override
        public void run() {
            final String API = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
            try {
                final String response =  getRequestCall(API);


                JSONObject obj = new JSONObject(response);
                //ArrayList<Ob> recipes = obj.get("drinks");
                JSONArray c = obj.getJSONArray("drinks");
                String java = c.getJSONObject(0).getString("strDrink");
                //Log.i("navyasai", c.getJSONObject(0).getString("strDrink"));
                cocktailReceived = c.getJSONObject(0).getString("strDrink");


            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}