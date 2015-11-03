package uk.ac.lincoln.students.jamie.helloworld;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.*;
import android.content.Intent;

// add below imports for location purposes
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.List;
import java.util.Locale;
import android.net.Uri;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create the TestActivity activity
        super.onCreate(savedInstanceState);

        // Get the message from the intent started in the helloWorld activity
        Intent intent = getIntent();
        String message = intent.getStringExtra("testParameter");

        // Create a new TextView widget programmatically
        TextView textView = new TextView(this);
        textView.setTextSize(40);

        // Set the TextView to the string message -
        // which was passed as a parameter from the HelloWorld activity
        textView.setText(message);
        // Set the TextView widget as the activity UI layout
        // setContentView(textView);

        // get location code
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Use network provider to get last known location
        String locationProvider = LocationManager.GPS_PROVIDER;
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        // create a few new variable to get and store the lat/long coordinates of last known location
        double lat;
        double longi;

        // check if a last known location exists
        if (lastKnownLocation == null)
        {
            // if no last location is available set lat/long to zero
            lat = 53.228029;  // lat of Lincoln is 53.228029;
            longi = -0.546055; // longi of Lincoln is -0.546055;
        }
        else
        {
            // if last location exists then get/set the lat/long
            lat = lastKnownLocation.getLatitude();
            longi = lastKnownLocation.getLongitude();
        }

        // bind the lat long coordinates to the programmatically created TextView for displaying
        textView.setText("Location:\n" + lat +"\n" + longi);

        // Set the text view as the activity layout
        // note we could create a new layout file to do this too
        // setContentView(textView);

        // Intent for Google Maps, if another map app handles the geo tag it may show more than one app option
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, longi);
        Intent intent_map = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent_map);
    }

}
