package edu.neu.madcourse.numad21s_navyasaiporanki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class LocatorActivity extends AppCompatActivity {
    LocationManager locationManager;
    String gpsEnabledMessage = "GPS is starting. Please Wait or Try Again";
    String pleaseEnableGPS = "Please Enable GPS!";
    String[] permissionStrings = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);
    }

    public void getCurrentLocation(View view) {

        ActivityCompat.requestPermissions(this, permissionStrings, 1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        if (!locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
            Snackbar.make(view, pleaseEnableGPS, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
        else{
            updateOrGetUsersCurrentLocation();
        }
    }

    private void updateOrGetUsersCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(LocatorActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this, permissionStrings, 1);
        }
        else if (ActivityCompat.checkSelfPermission(LocatorActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissionStrings , 1);
        }
        else{
            TextView txtLocationValue = findViewById(R.id.txtLocationValue);

            Location currentGPS =  locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location passiveLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (currentGPS != null){
                txtLocationValue.setText(String.format("Latitude: %s, Longitude: %s", currentGPS.getLatitude(), currentGPS.getLongitude()));
            }
            if (networkLocation != null){
                txtLocationValue.setText(String.format("Latitude: %s, Longitude: %s", networkLocation.getLatitude(), networkLocation.getLongitude()));
            }
            if (passiveLocation != null){
                txtLocationValue.setText(String.format("Latitude: %s, Longitude: %s", passiveLocation.getLatitude(), passiveLocation.getLongitude()));
            }
            else{
                Snackbar.make(txtLocationValue, gpsEnabledMessage, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {

                    }
                });
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {

                    }
                });
            }
        }
    }



}